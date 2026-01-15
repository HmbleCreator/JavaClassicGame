const fs = require('fs');
const path = require('path');

const RAW_DIR = 'c:\\Users\\amiku\\Downloads\\JavaClassicGame\\extracted-assets\\raw';

// Candidate files (adjust range as needed)
const candidates = [];
for (let i = 0; i < 20; i++) {
    candidates.push(`r14_${i}.bin`);
}

console.log(`Probing ${candidates.length} files in ${RAW_DIR}...`);

candidates.forEach(filename => {
    const filePath = path.join(RAW_DIR, filename);
    if (!fs.existsSync(filePath)) return;

    try {
        const buffer = fs.readFileSync(filePath);

        // Simple heuristic for ANM:
        // Byte 0: Format?
        // Byte 2: Frame Count (Int16)
        // Byte 4: Loop Count (Int16)

        // Verify it looks like ANM (check simple header values)
        if (buffer.length < 10) return;

        const frameCount = buffer.readInt16BE(2);
        const loopCount = buffer.readInt16BE(4);

        // Count unique Image IDs referenced
        // This requires traversing the frames, which is complex if offsets are variable.
        // But for identification, Frame Count is usually enough.

        // If it returns 36, it's a rotation sprite.

        console.log(`[${filename}] Size: ${buffer.length}, Frames: ${frameCount}, Loops: ${loopCount}`);

        if (frameCount === 36) {
            console.log(`   *** CANDIDATE FOR ROTATION SPRITE (Body/Char) ***`);
        }

    } catch (e) {
        // console.log(`Error reading ${filename}`);
    }
});
