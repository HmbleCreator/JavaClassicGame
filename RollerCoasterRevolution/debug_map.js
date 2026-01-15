const fs = require('fs');
const path = require('path');

const LOG_FILE = 'c:\\Users\\amiku\\Downloads\\JavaClassicGame\\RollerCoasterRevolution\\debug_log.txt';

function log(msg) {
    fs.appendFileSync(LOG_FILE, msg + '\n');
    console.log(msg);
}

try {
    log("Starting debug_map.js");

    const RAW_DIR = 'c:\\Users\\amiku\\Downloads\\JavaClassicGame\\extracted-assets\\raw';
    const OUT_DIR = 'c:\\Users\\amiku\\Downloads\\JavaClassicGame\\RollerCoasterRevolution\\public\\sprites';
    const RESOURCE_IDS_PATH = 'c:\\Users\\amiku\\Downloads\\JavaClassicGame\\OriginalDecompiledGame\\sources\\com\\digitalchocolate\\androidrollergapp\\ResourceIDs.java';

    log(`Checking RAW_DIR: ${RAW_DIR} -> ${fs.existsSync(RAW_DIR)}`);
    log(`Checking RES_PATH: ${RESOURCE_IDS_PATH} -> ${fs.existsSync(RESOURCE_IDS_PATH)}`);

    if (fs.existsSync(RAW_DIR)) {
        const files = fs.readdirSync(RAW_DIR);
        log(`RAW_DIR file count: ${files.length}`);
        if (files.length > 0) log(`First file: ${files[0]}`);
    }

    if (fs.existsSync(RESOURCE_IDS_PATH)) {
        const content = fs.readFileSync(RESOURCE_IDS_PATH, 'utf8');
        log(`ResourceIDs length: ${content.length}`);
    }

    log("Debug complete.");
} catch (e) {
    log(`ERROR: ${e.message}`);
}
