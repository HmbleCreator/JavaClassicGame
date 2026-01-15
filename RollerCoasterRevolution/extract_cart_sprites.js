const fs = require('fs');
const path = require('path');

const RAW_DIR = 'c:\\Users\\amiku\\Downloads\\JavaClassicGame\\extracted-assets\\raw';
const OUTPUT_DIR = 'c:\\Users\\amiku\\Downloads\\JavaClassicGame\\RollerCoasterRevolution\\public\\sprites';

if (!fs.existsSync(OUTPUT_DIR)) {
    fs.mkdirSync(OUTPUT_DIR, { recursive: true });
}

function extractFromAnm(anmId) {
    const rawName = `r14_${anmId}.bin`;
    const filePath = path.join(RAW_DIR, rawName);

    if (!fs.existsSync(filePath)) return;

    const buffer = fs.readFileSync(filePath);
    let offset = 0;

    function readUByte() { const v = buffer.readUInt8(offset); offset += 1; return v; }
    function readByte() { const v = buffer.readInt8(offset); offset += 1; return v; }
    function readUShort() { const v = buffer.readUInt16BE(offset); offset += 2; return v; }
    function readShort() { const v = buffer.readInt16BE(offset); offset += 2; return v; }
    function readInt() { const v = buffer.readInt32BE(offset); offset += 4; return v; }

    try {
        const i3 = readUByte(); // Byte 0
        const z3 = (i3 & 1) === 0; // UseByteCoords
        const z4 = (i3 & 2) === 0; // ByteDuration

        const frameCount = readUByte(); // Byte 1

        const loopPref = readUByte(); // Byte 2
        readUByte(); // Byte 3 (Skip)

        console.log(`[${rawName}] Frames: ${frameCount}, Loop: ${loopPref}, Byte0: ${i3}`);

        // Dimensions
        if (z3) {
            readByte(); readByte(); readUByte(); readUByte();
        } else {
            readShort(); readShort(); readShort(); readShort();
        }

        readUByte(); // Alignment

        // Frame Refs
        const frameRefs = [];
        if (frameCount > 1) {
            for (let i = 0; i < frameCount; i++) frameRefs.push(readUByte());
        }

        // Frame Durations
        for (let i = 0; i < frameCount; i++) {
            if (z4) readUByte(); else readUShort();
        }

        // Timeline check
        const timelineByte = readUByte();
        if ((timelineByte & 1) !== 0) {
            return;
        }

        if (frameCount === 36) {
            console.log(`*** FOUND CANDIDATE: ${rawName} (36 Frames) ***`);

            for (let f = 0; f < 36; f++) {
                if (frameRefs.length > 0 && frameRefs[f] !== f) continue; // Reference, skip/reuse

                // Parse Frame Data
                // Dimensions
                if (z3) offset += 2; else offset += 4;

                // Collision
                const colCount = readUByte();
                for (let c = 0; c < colCount; c++) {
                    readUByte(); // type?
                    if (z3) offset += 4; else offset += 8;
                }

                // Renderables
                const renderCount = readUByte();

                for (let r = 0; r < renderCount; r++) {
                    const type = readUByte(); // 1 = Image
                    if (type === 1) {
                        if (z3) offset += 2; else offset += 4; // xy
                        readUByte(); // flags
                        const imageId = readInt(); // IMAGE ID!
                        readUByte(); // palette

                        const fileIdx = imageId >>> 16;
                        const resIdx = imageId & 0xFFFF;
                        const rawFile = `r${fileIdx}_${resIdx}.bin`;
                        const rawPath = path.join(RAW_DIR, rawFile);

                        if (fs.existsSync(rawPath)) {
                            // Verify PNG
                            const rawBuf = fs.readFileSync(rawPath);
                            if (rawBuf.length > 8 && rawBuf.toString('hex', 0, 8) === '89504e470d0a1a0a') {
                                // Save!
                                const destName = `r14_${anmId}_${String(f).padStart(2, '0')}.png`;
                                fs.copyFileSync(rawPath, path.join(OUTPUT_DIR, destName));
                                console.log(`Saved ${destName}`);
                            }
                        }
                    } else {
                        // Skip other types (lines, rects)
                        if (type === 4) offset += (z3 ? 2 : 4) + 4;
                        else offset += (z3 ? 2 : 4) + 3;
                    }
                }
            }
        }

    } catch (e) {
        console.error(`Error processing ${rawName}:`, e);
    }
}

const TARGETS = [
    { id: 0, name: 'cart_body_empty' },
    { id: 15, name: 'cart_body_boy' },
    { id: 18, name: 'cart_body_girl' }
];

// Re-use logic but target specific names
function extractTarget(target) {
    const anmId = target.id;
    const rawName = `r14_${anmId}.bin`;
    const filePath = path.join(RAW_DIR, rawName);

    if (!fs.existsSync(filePath)) {
        console.log(`Missing ${rawName}`);
        return;
    }

    const buffer = fs.readFileSync(filePath);
    let offset = 0;
    function readUByte() { const v = buffer.readUInt8(offset); offset += 1; return v; }
    function readByte() { const v = buffer.readInt8(offset); offset += 1; return v; }
    function readUShort() { const v = buffer.readUInt16BE(offset); offset += 2; return v; }
    function readShort() { const v = buffer.readInt16BE(offset); offset += 2; return v; }
    function readInt() { const v = buffer.readInt32BE(offset); offset += 4; return v; }

    try {
        const i3 = readUByte();
        const z3 = (i3 & 1) === 0;
        const z4 = (i3 & 2) === 0;
        const frameCount = readUByte();
        // Skip header
        readUByte(); readUByte();
        if (z3) { readByte(); readByte(); readUByte(); readUByte(); }
        else { readShort(); readShort(); readShort(); readShort(); }
        readUByte();
        const frameRefs = [];
        if (frameCount > 1) { for (let i = 0; i < frameCount; i++) frameRefs.push(readUByte()); }
        for (let i = 0; i < frameCount; i++) { if (z4) readUByte(); else readUShort(); }
        const timelineByte = readUByte();
        if ((timelineByte & 1) !== 0) return;

        console.log(`Extracting ${target.name} from ${rawName}...`);

        for (let f = 0; f < frameCount; f++) {
            if (frameRefs.length > 0 && frameRefs[f] !== f) continue;

            if (z3) offset += 2; else offset += 4; // dims
            const colCount = readUByte();
            for (let c = 0; c < colCount; c++) {
                readUByte();
                if (z3) offset += 4; else offset += 8;
            }
            const renderCount = readUByte();
            for (let r = 0; r < renderCount; r++) {
                const type = readUByte();
                if (type === 1) {
                    if (z3) offset += 2; else offset += 4;
                    readUByte();
                    const imageId = readInt();
                    readUByte();

                    const fileIdx = imageId >>> 16;
                    const resIdx = imageId & 0xFFFF;
                    const rawFile = `r${fileIdx}_${resIdx}.bin`;
                    const rawPath = path.join(RAW_DIR, rawFile);

                    if (fs.existsSync(rawPath)) {
                        // Validate PNG
                        const fd = fs.openSync(rawPath, 'r');
                        const head = Buffer.alloc(8);
                        fs.readSync(fd, head, 0, 8, 0);
                        fs.closeSync(fd);
                        if (head.toString('hex') === '89504e470d0a1a0a') {
                            const destName = `${target.name}_${String(f).padStart(2, '0')}.png`;
                            fs.copyFileSync(rawPath, path.join(OUTPUT_DIR, destName));
                        }
                    }
                } else {
                    if (type === 4) offset += (z3 ? 2 : 4) + 4;
                    else offset += (z3 ? 2 : 4) + 3;
                }
            }
        }
    } catch (e) {
        console.error(e);
    }
}

TARGETS.forEach(extractTarget);
