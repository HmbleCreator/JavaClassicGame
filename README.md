# Rollercoaster Revolution: Web Remake

A faithful web-based remake of the classic J2ME game "Rollercoaster Revolution 99 Tracks" by Digital Chocolate.

## 📖 About The Project


https://github.com/user-attachments/assets/78d94176-e14b-4fda-8966-e9e08548cdc0


This project aims to preserve and modernize the classic mobile game by reverse-engineering its original assets and game logic, then re-implementing them using modern web technologies. We are manually analyzing the original Java source code and binary resource contents to replicate the authentic "feel" of the physics and gameplay.

- **Original Game**: Rollercoaster Revolution (J2ME/Java)
- **New Tech Stack**: TypeScript, Next.js, Pixi.js

## 🚀 Features

- **Authentic Assets**: Graphics and sprites are extracted directly from the original game's binary resource pack.
- **Physics Replication**: The physics engine is being carefully tuned to match the original fixed-point math and acceleration curves.
- **Multi-Car Train**: Fully implemented train physics with proper car spacing and passenger rendering.
- **99 Tracks**: Support for loading the original track data (in progress).

## 🛠️ Getting Started

Follow these instructions to set up and run the game on your local machine.

### Prerequisites

- **Node.js**: Version 18 or higher is recommended.
- **npm**: Comes with Node.js.

### Installation

1. Navigate to the project directory:
   ```bash
   cd RollerCoasterRevolution
   ```

2. Install the necessary dependencies:
   ```bash
   npm install
   ```

### Running the Game

Start the development server:
```bash
npm run dev
```

Once the server is running, open your browser and navigate to:
[http://localhost:3000](http://localhost:3000)

## 📂 Project Structure

- **`src/engine`**: Core game logic (GameLoop, Physics, Rendering).
- **`src/components`**: React UI components (GameCanvas, HUD).
- **`public/sprites`**: Extracted game assets.

### Asset Extraction Tools
We have developed custom Node.js scripts to parse the original game's proprietary binary formats:

- **`extract_cart_sprites.js`**: parses `ANM` (Animation) binary files to extract and reconstruct sprite frames (e.g., the coaster cars and passengers).
- **`probe_anm.js`**: scans binary files to identify animation structures.
- **`map_sprites.js`**: maps internal Resource IDs to human-readable filenames.
- **`analyze_r.js`**: analyzes the structure of the main resource pack.

## ⚠️ Disclaimer
This is a fan-made preservation project created for educational purposes. All original game assets and intellectual property belong to their respective owners.
