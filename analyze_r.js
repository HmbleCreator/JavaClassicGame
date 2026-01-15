const fs = require('fs');
const path = require('path');

const targetFile = 'c:\\Users\\amiku\\Downloads\\JavaClassicGame\\OriginalDecompiledGame\\resources\\assets\\r14';

function readInt(buffer, offset) {
    return buffer.readUInt32BE(offset);
}

function analyzeFile(filepath) {
    console.log(`Analyzing ${filepath}...`);
    try {
        const data = fs.readFileSync(filepath);

        if (data.length < 8) {
            console.log("File too short");
            return;
        }

        const magic = readInt(data, 0); // Unused or Magic
        const count = readInt(data, 4);

        console.log(`Magic (0-3): ${magic.toString(16)}`);
        console.log(`Count (4-7): ${count}`);

        console.log("--- Items ---");
        for (let i = 0; i < Math.min(count, 10); i++) {
            const offsetLoc = 8 + (i * 4);
            const itemOffset = readInt(data, offsetLoc);

            let nextOffset;
            if (i === count - 1) {
                // For the last item, we need to guess or use file length.
                // Toolkit logic suggests it uses total length for the last item if it's indeed the last one in the block.
                // But let's just use file length for now.
                nextOffset = data.length;
            } else {
                nextOffset = readInt(data, offsetLoc + 4);
            }

            const size = nextOffset - itemOffset;

            let header = "EOF";
            let isPng = false;

            if (itemOffset + 8 <= data.length) {
                const headerBuf = data.slice(itemOffset, itemOffset + 8);
                header = headerBuf.toString('hex');
                // PNG Signature: 89 50 4E 47 0D 0A 1A 0A
                if (headerBuf.toString('hex').startsWith('89504e47')) {
                    isPng = true;
                }
            }

            console.log(`Index ${i}: Offset ${itemOffset}, Size ${size}, Header: ${header} (${isPng ? 'PNG' : 'Unknown'})`);
        }

    } catch (err) {
        console.error("Error:", err.message);
    }
}

if (fs.existsSync(targetFile)) {
    analyzeFile(targetFile);
} else {
    console.log(`File not found: ${targetFile}`);
}
