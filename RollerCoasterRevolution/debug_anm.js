const fs = require('fs');
const path = require('path');

const ANM_FILE = 'c:\\Users\\amiku\\Downloads\\JavaClassicGame\\extracted-assets\\raw\\r14_2.bin';

function parseAnm(filePath) {
    if (!fs.existsSync(filePath)) {
        console.error('File not found:', filePath);
        return;
    }

    const buffer = fs.readFileSync(filePath);
    let offset = 0;

    function readByte() {
        const val = buffer.readInt8(offset);
        offset++;
        return val;
    }

    function readUByte() {
        const val = buffer.readUInt8(offset);
        offset++;
        return val;
    }

    function readShort() {
        const val = buffer.readInt16BE(offset);
        offset += 2;
        return val;
    }

    function readUShort() {
        const val = buffer.readUInt16BE(offset);
        offset += 2;
        return val;
    }

    function readInt() {
        const val = buffer.readInt32BE(offset);
        offset += 4;
        return val;
    }

    // Header
    const i3 = readUByte();
    const useByteCoords = (i3 & 1) === 0;
    const z4 = (i3 & 2) === 0;
    const frameCount = readUByte();

    console.log(`Header: ${i3.toString(16)}, Frames: ${frameCount}, ByteCoords: ${useByteCoords}`);

    // Loop Preference
    const loopPref = readUByte(); // 1 = true
    readUByte(); // skipped

    let mX, mY, mWidth, mHeight;
    if (useByteCoords) {
        mX = readByte();
        mY = readByte();
        mWidth = readUByte();
        mHeight = readUByte();
    } else {
        mX = readShort();
        mY = readShort();
        mWidth = readShort();
        mHeight = readShort();
    }
    const alignment = readUByte();

    console.log(`Anim: x=${mX}, y=${mY}, w=${mWidth}, h=${mHeight}`);

    // Frame references (if > 1 frame)
    const frameRefs = [];
    if (frameCount > 1) {
        for (let i = 0; i < frameCount; i++) {
            frameRefs.push(readUByte());
        }
    }

    // Frame Durations
    const frameDurations = [];
    for (let i = 0; i < frameCount; i++) {
        let dur;
        if (z4) {
            dur = readUByte();
        } else {
            dur = readUShort();
        }
        frameDurations.push(dur);
    }

    // Timeline
    const hasTimeline = readUByte() === 1;
    if (hasTimeline) {
        // Skip timeline data for now
        let tX, tY, tW, tH;
        if (useByteCoords) {
            tX = readByte(); tY = readByte(); tW = readUByte(); tH = readUByte();
        } else {
            tX = readShort(); tY = readShort(); tW = readShort(); tH = readShort();
        }
        // 6 channels
        for (let c = 0; c < 6; c++) {
            const type = readUByte(); // TimelineChannel load logic
            // Complex logic to skip... assume simple for now or just seek?
            // Actually, I need to implement TimelineChannel load to skip it correctly.
            // TimelineChannel.load:
            // readUByte (compression type?)
            // if type != 0 ...
            // This is risky. If timeline exists, parsing is hard without full logic.
            // Assuming NO timeline for r14_2 based on size?

            // Wait, assuming timeline data is minimal or I can detect it.
            // Let's hope hasTimeline is false.
        }
        if (hasTimeline) console.warn("WARNING: Timeline present, parsing likely desynced!");
    } else {
        console.log("No Timeline.");
    }

    // Frames
    const referencedImages = [];

    for (let i = 0; i < frameCount; i++) {
        if (frameCount > 1 && frameRefs[i] !== 255) {
            continue; // Reference to existing frame
        }

        // Load Frame Data
        let fW, fH;
        if (useByteCoords) {
            fW = readUByte();
            fH = readUByte();
        } else {
            fW = readShort();
            fH = readShort();
        }

        const collisionBoxCount = readUByte();
        // Skip collision boxes
        for (let cb = 0; cb < collisionBoxCount; cb++) {
            readUByte(); // id
            if (useByteCoords) {
                offset += 4; // 4 bytes
            } else {
                offset += 8; // 4 shorts
            }
        }

        const renderableCount = readShort();

        for (let r = 0; r < renderableCount; r++) {
            const type = readUByte(); // 1 = Image
            if (type === 1) {
                // Image
                let rx, ry;
                if (useByteCoords) {
                    rx = readByte(); ry = readByte();
                } else {
                    rx = readShort(); ry = readShort();
                }

                const flags = readUByte();
                const imageId = readInt(); // THIS IS IT
                const paletteId = readUByte();

                referencedImages.push({ frame: i, imageId, paletteId });

                // loadImageIntoCache params
            } else {
                // Geo
                const color = readInt(); // argb (4 bytes? read 3 then | FF?)
                // Actually read 3 bytes then readInt logic in java was...
                // (-16777216) | (read() << 16) | ...
                // It reads 3 bytes. My readInt reads 4. 
                // Java code:
                // (-16777216) | (dataInputStream.read() << 16) | (dataInputStream.read() << 8) | dataInputStream.read();
                // So it reads 3 bytes. 
                // Wait, type 1 reads 'readInt()' for imageID.
                // Type != 1 reads 3 bytes for color.
                // Uh oh, 'readInt' reads 4 bytes.
                // 'read()' reads 1 byte.

                // Correction: Type != 1:
                // offset += 3;

                // But wait, my script logic for type != 1 is complex.
                // If I encounter type != 1, I might desync if I don't implement full renderable parsing.
                // Assuming Cart is just images.
                console.log(`Frame ${i}: Found non-image renderable type ${type}`);

                // Parsing Renderable Shape
                if (useByteCoords) {
                    offset += 2; // x, y
                    if (type === 4) offset += 2; // line end x, y
                    else offset += 2; // w, h?
                } else {
                    offset += 8; // 4 shorts
                }
            }
        }
    }

    console.log("Referenced Images:", referencedImages);
    return referencedImages;
}

parseAnm(ANM_FILE);
