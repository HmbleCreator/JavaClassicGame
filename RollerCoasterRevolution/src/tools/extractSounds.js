/**
 * Sound Resource Extractor
 * 
 * Extracts MIDI files from Digital Chocolate packed resource format.
 * Based on Toolkit.java resource loading code.
 * 
 * Resource ID format: (fileNum << 16) | indexInFile
 * 
 * Sound Resources Found:
 * - RID_SND_TITLE = 720896 (0xB0000) = r11, index 0
 * - RID_SND_EFFECT_VOLUME_CHANGED = 786432 (0xC0000) = r12, index 0
 * - RID_SND_GAME = 786433 (0xC0001) = r12, index 1
 */

const fs = require('fs');
const path = require('path');

const ASSETS_DIR = path.join(__dirname, '..', '..', '..', 'OriginalDecompiledGame', 'resources', 'assets');
const OUTPUT_DIR = path.join(__dirname, '..', '..', 'public', 'audio');

// Create output directory
if (!fs.existsSync(OUTPUT_DIR)) {
    fs.mkdirSync(OUTPUT_DIR, { recursive: true });
}

/**
 * Read 4-byte big-endian integer from buffer
 */
function readInt32BE(buffer, offset) {
    return ((buffer[offset] & 0xFF) << 24) |
        ((buffer[offset + 1] & 0xFF) << 16) |
        ((buffer[offset + 2] & 0xFF) << 8) |
        (buffer[offset + 3] & 0xFF);
}

/**
 * Extract a resource from a packed file
 * Based on Toolkit.getResourceBytesFromLoadedFile()
 */
function extractResource(fileData, resourceIndex) {
    const numResources = readInt32BE(fileData, 4);
    const tableOffset = 8;

    if (resourceIndex >= numResources) {
        console.error(`Resource index ${resourceIndex} out of bounds (max: ${numResources - 1})`);
        return null;
    }

    const startOffset = readInt32BE(fileData, tableOffset + (resourceIndex * 4));

    let endOffset;
    if (resourceIndex === numResources - 1) {
        endOffset = fileData.length;
    } else {
        endOffset = readInt32BE(fileData, tableOffset + ((resourceIndex + 1) * 4));
    }

    const resourceLength = endOffset - startOffset;
    console.log(`  Resource ${resourceIndex}: offset ${startOffset}, length ${resourceLength}`);

    return fileData.slice(startOffset, endOffset);
}

/**
 * Detect file type from magic bytes
 */
function detectFileType(data) {
    if (!data || data.length < 4) return 'bin';

    // MIDI: starts with "MThd"
    if (data[0] === 0x4D && data[1] === 0x54 && data[2] === 0x68 && data[3] === 0x64) {
        return 'mid';
    }

    // WAV: starts with "RIFF"
    if (data[0] === 0x52 && data[1] === 0x49 && data[2] === 0x46 && data[3] === 0x46) {
        return 'wav';
    }

    // MP3: starts with 0xFF 0xFB or ID3 tag
    if ((data[0] === 0xFF && (data[1] & 0xFE) === 0xFA) ||
        (data[0] === 0x49 && data[1] === 0x44 && data[2] === 0x33)) {
        return 'mp3';
    }

    // AMR: starts with "#!AMR"
    if (data[0] === 0x23 && data[1] === 0x21 && data[2] === 0x41 && data[3] === 0x4D) {
        return 'amr';
    }

    return 'bin';
}

// Sound resources to extract
const soundResources = [
    { name: 'title_music', rid: 720896 },      // RID_SND_TITLE
    { name: 'volume_changed', rid: 786432 },   // RID_SND_EFFECT_VOLUME_CHANGED
    { name: 'game_music', rid: 786433 },       // RID_SND_GAME
];

console.log('=== Sound Resource Extractor ===\n');

for (const sound of soundResources) {
    const fileNum = (sound.rid >>> 16) & 0xFFFF;
    const resourceIndex = sound.rid & 0xFFFF;

    console.log(`Extracting ${sound.name}:`);
    console.log(`  Resource ID: ${sound.rid} (0x${sound.rid.toString(16).toUpperCase()})`);
    console.log(`  File: r${fileNum}, Index: ${resourceIndex}`);

    const resourceFilePath = path.join(ASSETS_DIR, `r${fileNum}`);

    if (!fs.existsSync(resourceFilePath)) {
        console.error(`  ERROR: File not found: ${resourceFilePath}`);
        continue;
    }

    try {
        const fileData = fs.readFileSync(resourceFilePath);
        console.log(`  File size: ${fileData.length} bytes`);

        const resourceData = extractResource(fileData, resourceIndex);

        if (resourceData) {
            const fileType = detectFileType(resourceData);
            const outputFilename = `${sound.name}.${fileType}`;
            const outputPath = path.join(OUTPUT_DIR, outputFilename);

            fs.writeFileSync(outputPath, resourceData);
            console.log(`  Saved: ${outputFilename} (${resourceData.length} bytes, type: ${fileType})`);
        }
    } catch (err) {
        console.error(`  ERROR: ${err.message}`);
    }

    console.log('');
}

console.log(`\nExtraction complete! Check ${OUTPUT_DIR}`);
