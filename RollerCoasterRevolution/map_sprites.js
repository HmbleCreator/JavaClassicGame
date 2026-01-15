const fs = require('fs');
const path = require('path');

const RAW_DIR = 'c:\\Users\\amiku\\Downloads\\JavaClassicGame\\extracted-assets\\raw';
const OUT_DIR = 'c:\\Users\\amiku\\Downloads\\JavaClassicGame\\RollerCoasterRevolution\\public\\sprites';
const RESOURCE_IDS_PATH = 'c:\\Users\\amiku\\Downloads\\JavaClassicGame\\OriginalDecompiledGame\\sources\\com\\digitalchocolate\\androidrollergapp\\ResourceIDs.java';

// Ensure output dir exists
if (!fs.existsSync(OUT_DIR)) {
    fs.mkdirSync(OUT_DIR, { recursive: true });
}

// 1. Parse ResourceIDs.java
console.log("Parsing ResourceIDs.java...");
const resourceIdsContent = fs.readFileSync(RESOURCE_IDS_PATH, 'utf8');
const idMap = {}; // ID -> Name

// Regex for: public static final int NAME = VALUE;
const regex = /public\s+static\s+final\s+int\s+([A-Z0-9_]+)\s*=\s*(-?\d+);/g;
let match;
while ((match = regex.exec(resourceIdsContent)) !== null) {
    const name = match[1];
    const value = parseInt(match[2], 10);

    if (!isNaN(value) && value !== -1) {
        // Prioritize ANM_ names over PIXELDATA_
        if (!idMap[value]) {
            idMap[value] = name;
        } else {
            const currentName = idMap[value];
            if (name.startsWith('ANM_') && !currentName.startsWith('ANM_')) {
                idMap[value] = name;
            } else if (name.startsWith('RID_') && !currentName.startsWith('ANM_') && !currentName.startsWith('RID_')) {
                idMap[value] = name;
            }
        }
    }
}

console.log(`Found ${Object.keys(idMap).length} mapped IDs.`);

// 2. Iterate and Copy
let copiedCount = 0;
let pngCount = 0;

Object.entries(idMap).forEach(([idStr, name]) => {
    const id = parseInt(idStr, 10);

    const fileIndex = id >>> 16;
    const resourceIndex = id & 0xFFFF;

    const rawFilename = `r${fileIndex}_${resourceIndex}.bin`;
    const rawPath = path.join(RAW_DIR, rawFilename);

    if (fs.existsSync(rawPath)) {
        try {
            const data = fs.readFileSync(rawPath);

            // Check for PNG header
            let ext = '.bin';
            if (data.length >= 8 && data.slice(0, 8).toString('hex') === '89504e470d0a1a0a') {
                ext = '.png';
                pngCount++;
            } else if (data.length >= 2 && data.slice(0, 2).toString('hex') === 'ffd8') {
                ext = '.jpg';
            }

            if (ext === '.png' || ext === '.jpg') {
                const outPath = path.join(OUT_DIR, `${name}${ext}`);
                fs.copyFileSync(rawPath, outPath);
                copiedCount++;
            }
        } catch (e) {
            console.error(`Error processing ${rawFilename}:`, e.message);
        }
    }
});

console.log(`Successfully mapped and copied ${copiedCount} sprites (${pngCount} PNGs).`);
