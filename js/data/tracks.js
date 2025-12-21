/**
 * Rollercoaster Rush - Track Data
 * 20 unique tracks across Easy/Medium/Hard difficulties
 */

const TRACKS_DATA = [
    // EASY TRACKS (1-7)
    {
        id: 1, name: "Gentle Hills", difficulty: "easy", parTime: 30,
        starThresholds: { one: 0, two: 50, three: 85 },
        points: [
            { x: 100, y: 300, cp2: { x: 200, y: 300 } },
            { x: 400, y: 350, cp1: { x: 300, y: 350 }, cp2: { x: 500, y: 350 } },
            { x: 700, y: 280, cp1: { x: 600, y: 280 }, cp2: { x: 800, y: 280 } },
            { x: 1000, y: 350, cp1: { x: 900, y: 350 }, cp2: { x: 1100, y: 350 } },
            { x: 1300, y: 300, cp1: { x: 1200, y: 300 } }
        ],
        coins: [{ t: 0.2 }, { t: 0.35 }, { t: 0.5 }, { t: 0.65 }, { t: 0.8 }]
    },
    {
        id: 2, name: "First Drop", difficulty: "easy", parTime: 25,
        starThresholds: { one: 0, two: 55, three: 88 },
        points: [
            { x: 100, y: 150, cp2: { x: 200, y: 150 } },
            { x: 350, y: 180, cp1: { x: 250, y: 150 }, cp2: { x: 400, y: 250 } },
            { x: 500, y: 400, cp1: { x: 450, y: 350 }, cp2: { x: 600, y: 420 } },
            { x: 800, y: 380, cp1: { x: 700, y: 400 }, cp2: { x: 900, y: 360 } },
            { x: 1100, y: 350, cp1: { x: 1000, y: 340 } }
        ],
        coins: [{ t: 0.15 }, { t: 0.3 }, { t: 0.45 }, { t: 0.6 }, { t: 0.75 }, { t: 0.9 }]
    },
    {
        id: 3, name: "Valley Run", difficulty: "easy", parTime: 35,
        starThresholds: { one: 0, two: 50, three: 82 },
        points: [
            { x: 100, y: 200, cp2: { x: 200, y: 200 } },
            { x: 400, y: 400, cp1: { x: 300, y: 350 }, cp2: { x: 500, y: 420 } },
            { x: 700, y: 350, cp1: { x: 600, y: 380 }, cp2: { x: 800, y: 320 } },
            { x: 1000, y: 420, cp1: { x: 900, y: 380 }, cp2: { x: 1100, y: 440 } },
            { x: 1300, y: 350, cp1: { x: 1200, y: 400 } }
        ],
        coins: [{ t: 0.1 }, { t: 0.25 }, { t: 0.4 }, { t: 0.55 }, { t: 0.7 }, { t: 0.85 }]
    },
    {
        id: 4, name: "Smooth Curves", difficulty: "easy", parTime: 28,
        starThresholds: { one: 0, two: 52, three: 86 },
        points: [
            { x: 100, y: 250, cp2: { x: 200, y: 230 } },
            { x: 400, y: 300, cp1: { x: 300, y: 280 }, cp2: { x: 500, y: 350 } },
            { x: 700, y: 250, cp1: { x: 600, y: 300 }, cp2: { x: 800, y: 200 } },
            { x: 1000, y: 300, cp1: { x: 900, y: 230 }, cp2: { x: 1100, y: 320 } },
            { x: 1250, y: 280, cp1: { x: 1150, y: 300 } }
        ],
        coins: [{ t: 0.18 }, { t: 0.36 }, { t: 0.54 }, { t: 0.72 }, { t: 0.88 }]
    },
    {
        id: 5, name: "Bunny Hop", difficulty: "easy", parTime: 32,
        starThresholds: { one: 0, two: 48, three: 84 },
        points: [
            { x: 100, y: 280, cp2: { x: 180, y: 280 } },
            { x: 300, y: 320, cp1: { x: 220, y: 300 }, cp2: { x: 380, y: 340 } },
            { x: 500, y: 260, cp1: { x: 420, y: 290 }, cp2: { x: 580, y: 230 } },
            { x: 700, y: 320, cp1: { x: 620, y: 260 }, cp2: { x: 780, y: 340 } },
            { x: 900, y: 250, cp1: { x: 820, y: 290 }, cp2: { x: 980, y: 220 } },
            { x: 1100, y: 300, cp1: { x: 1020, y: 250 } }
        ],
        coins: [{ t: 0.12 }, { t: 0.28 }, { t: 0.44 }, { t: 0.6 }, { t: 0.76 }, { t: 0.92 }]
    },
    {
        id: 6, name: "Coastal Breeze", difficulty: "easy", parTime: 30,
        starThresholds: { one: 0, two: 50, three: 85 },
        points: [
            { x: 100, y: 300, cp2: { x: 200, y: 280 } },
            { x: 450, y: 350, cp1: { x: 350, y: 320 }, cp2: { x: 550, y: 380 } },
            { x: 800, y: 300, cp1: { x: 700, y: 350 }, cp2: { x: 900, y: 270 } },
            { x: 1150, y: 320, cp1: { x: 1050, y: 280 } }
        ],
        coins: [{ t: 0.15 }, { t: 0.35 }, { t: 0.55 }, { t: 0.75 }, { t: 0.9 }]
    },
    {
        id: 7, name: "Meadow Path", difficulty: "easy", parTime: 28,
        starThresholds: { one: 0, two: 55, three: 88 },
        points: [
            { x: 100, y: 320, cp2: { x: 200, y: 300 } },
            { x: 400, y: 280, cp1: { x: 300, y: 290 }, cp2: { x: 500, y: 260 } },
            { x: 700, y: 340, cp1: { x: 600, y: 280 }, cp2: { x: 800, y: 370 } },
            { x: 1000, y: 300, cp1: { x: 900, y: 350 } }
        ],
        coins: [{ t: 0.2 }, { t: 0.4 }, { t: 0.6 }, { t: 0.8 }]
    },

    // MEDIUM TRACKS (8-14)
    {
        id: 8, name: "First Loop", difficulty: "medium", parTime: 35,
        starThresholds: { one: 0, two: 55, three: 88 },
        points: [
            { x: 100, y: 150, cp2: { x: 200, y: 150 } },
            { x: 400, y: 200, cp1: { x: 300, y: 150 }, cp2: { x: 450, y: 280 } },
            { x: 550, y: 400, cp1: { x: 480, y: 350 }, cp2: { x: 580, y: 480 } },
            { x: 700, y: 450, cp1: { x: 650, y: 500 }, cp2: { x: 800, y: 500 } },
            { x: 850, y: 350, cp1: { x: 900, y: 450 }, cp2: { x: 850, y: 280 } },
            { x: 750, y: 250, cp1: { x: 800, y: 250 }, cp2: { x: 680, y: 200 } },
            { x: 650, y: 300, cp1: { x: 620, y: 220 }, cp2: { x: 700, y: 350 } },
            { x: 900, y: 380, cp1: { x: 800, y: 360 }, cp2: { x: 1000, y: 400 } },
            { x: 1200, y: 350, cp1: { x: 1100, y: 380 } }
        ],
        coins: [{ t: 0.1 }, { t: 0.25 }, { t: 0.4, value: 25 }, { t: 0.55 }, { t: 0.7 }, { t: 0.85 }]
    },
    {
        id: 9, name: "Steep Descent", difficulty: "medium", parTime: 28,
        starThresholds: { one: 0, two: 52, three: 85 },
        points: [
            { x: 100, y: 100, cp2: { x: 200, y: 100 } },
            { x: 350, y: 120, cp1: { x: 280, y: 100 }, cp2: { x: 400, y: 200 } },
            { x: 500, y: 450, cp1: { x: 450, y: 350 }, cp2: { x: 580, y: 500 } },
            { x: 750, y: 400, cp1: { x: 680, y: 480 }, cp2: { x: 850, y: 350 } },
            { x: 1000, y: 420, cp1: { x: 920, y: 380 }, cp2: { x: 1080, y: 450 } },
            { x: 1200, y: 380, cp1: { x: 1150, y: 430 } }
        ],
        coins: [{ t: 0.12 }, { t: 0.28 }, { t: 0.42, value: 25 }, { t: 0.58 }, { t: 0.74 }, { t: 0.88 }]
    },
    {
        id: 10, name: "Snake Run", difficulty: "medium", parTime: 40,
        starThresholds: { one: 0, two: 50, three: 82 },
        points: [
            { x: 100, y: 200, cp2: { x: 200, y: 180 } },
            { x: 350, y: 350, cp1: { x: 280, y: 280 }, cp2: { x: 420, y: 400 } },
            { x: 550, y: 250, cp1: { x: 480, y: 320 }, cp2: { x: 620, y: 180 } },
            { x: 750, y: 380, cp1: { x: 680, y: 250 }, cp2: { x: 820, y: 420 } },
            { x: 950, y: 280, cp1: { x: 880, y: 350 }, cp2: { x: 1020, y: 220 } },
            { x: 1150, y: 350, cp1: { x: 1080, y: 260 } }
        ],
        coins: [{ t: 0.1 }, { t: 0.22 }, { t: 0.38 }, { t: 0.52 }, { t: 0.68 }, { t: 0.82 }, { t: 0.94 }]
    },
    {
        id: 11, name: "Mountain Pass", difficulty: "medium", parTime: 38,
        starThresholds: { one: 0, two: 54, three: 86 },
        points: [
            { x: 100, y: 350, cp2: { x: 200, y: 320 } },
            { x: 400, y: 150, cp1: { x: 300, y: 250 }, cp2: { x: 500, y: 100 } },
            { x: 650, y: 200, cp1: { x: 580, y: 120 }, cp2: { x: 720, y: 280 } },
            { x: 850, y: 400, cp1: { x: 780, y: 320 }, cp2: { x: 920, y: 450 } },
            { x: 1050, y: 300, cp1: { x: 980, y: 420 }, cp2: { x: 1120, y: 250 } },
            { x: 1250, y: 320, cp1: { x: 1180, y: 280 } }
        ],
        coins: [{ t: 0.15 }, { t: 0.3, value: 25 }, { t: 0.48 }, { t: 0.65 }, { t: 0.8 }, { t: 0.92 }]
    },
    {
        id: 12, name: "Corkscrew Intro", difficulty: "medium", parTime: 32,
        starThresholds: { one: 0, two: 56, three: 88 },
        points: [
            { x: 100, y: 180, cp2: { x: 200, y: 160 } },
            { x: 400, y: 220, cp1: { x: 300, y: 180 }, cp2: { x: 480, y: 300 } },
            { x: 550, y: 420, cp1: { x: 520, y: 360 }, cp2: { x: 600, y: 480 } },
            { x: 720, y: 380, cp1: { x: 680, y: 480 }, cp2: { x: 780, y: 300 } },
            { x: 850, y: 250, cp1: { x: 820, y: 280 }, cp2: { x: 920, y: 200 } },
            { x: 1050, y: 300, cp1: { x: 980, y: 230 }, cp2: { x: 1120, y: 340 } },
            { x: 1250, y: 280, cp1: { x: 1180, y: 320 } }
        ],
        coins: [{ t: 0.12 }, { t: 0.28, value: 25 }, { t: 0.45 }, { t: 0.6 }, { t: 0.75 }, { t: 0.9 }]
    },
    {
        id: 13, name: "Double Dip", difficulty: "medium", parTime: 35,
        starThresholds: { one: 0, two: 52, three: 84 },
        points: [
            { x: 100, y: 150, cp2: { x: 200, y: 140 } },
            { x: 350, y: 400, cp1: { x: 280, y: 280 }, cp2: { x: 420, y: 480 } },
            { x: 550, y: 300, cp1: { x: 480, y: 420 }, cp2: { x: 620, y: 220 } },
            { x: 750, y: 450, cp1: { x: 680, y: 280 }, cp2: { x: 820, y: 500 } },
            { x: 950, y: 350, cp1: { x: 880, y: 450 }, cp2: { x: 1020, y: 300 } },
            { x: 1150, y: 320, cp1: { x: 1080, y: 320 } }
        ],
        coins: [{ t: 0.08 }, { t: 0.25, value: 25 }, { t: 0.42 }, { t: 0.58, value: 25 }, { t: 0.75 }, { t: 0.9 }]
    },
    {
        id: 14, name: "Twister", difficulty: "medium", parTime: 42,
        starThresholds: { one: 0, two: 50, three: 82 },
        points: [
            { x: 100, y: 250, cp2: { x: 180, y: 230 } },
            { x: 300, y: 180, cp1: { x: 240, y: 210 }, cp2: { x: 380, y: 140 } },
            { x: 500, y: 320, cp1: { x: 440, y: 180 }, cp2: { x: 560, y: 400 } },
            { x: 680, y: 220, cp1: { x: 620, y: 350 }, cp2: { x: 740, y: 150 } },
            { x: 860, y: 380, cp1: { x: 800, y: 200 }, cp2: { x: 920, y: 450 } },
            { x: 1040, y: 280, cp1: { x: 980, y: 400 }, cp2: { x: 1100, y: 220 } },
            { x: 1220, y: 320, cp1: { x: 1160, y: 250 } }
        ],
        coins: [{ t: 0.1 }, { t: 0.24 }, { t: 0.38 }, { t: 0.52 }, { t: 0.66 }, { t: 0.8 }, { t: 0.92 }]
    },

    // HARD TRACKS (15-20)
    {
        id: 15, name: "Double Loop", difficulty: "hard", parTime: 45,
        starThresholds: { one: 0, two: 60, three: 92 },
        points: [
            { x: 100, y: 120, cp2: { x: 200, y: 100 } },
            { x: 400, y: 180, cp1: { x: 300, y: 120 }, cp2: { x: 480, y: 280 } },
            { x: 550, y: 400, cp1: { x: 520, y: 340 }, cp2: { x: 580, y: 500 } },
            { x: 680, y: 450, cp1: { x: 640, y: 520 }, cp2: { x: 760, y: 500 } },
            { x: 800, y: 350, cp1: { x: 840, y: 450 }, cp2: { x: 800, y: 280 } },
            { x: 720, y: 250, cp1: { x: 760, y: 260 }, cp2: { x: 660, y: 200 } },
            { x: 650, y: 300, cp1: { x: 640, y: 220 }, cp2: { x: 700, y: 380 } },
            { x: 850, y: 420, cp1: { x: 780, y: 400 }, cp2: { x: 920, y: 500 } },
            { x: 1000, y: 450, cp1: { x: 960, y: 520 }, cp2: { x: 1080, y: 500 } },
            { x: 1120, y: 350, cp1: { x: 1160, y: 450 }, cp2: { x: 1120, y: 280 } },
            { x: 1040, y: 250, cp1: { x: 1080, y: 260 }, cp2: { x: 980, y: 200 } },
            { x: 980, y: 320, cp1: { x: 960, y: 220 }, cp2: { x: 1050, y: 380 } },
            { x: 1200, y: 380, cp1: { x: 1150, y: 360 } }
        ],
        coins: [{ t: 0.08 }, { t: 0.2, value: 25 }, { t: 0.35 }, { t: 0.5, value: 25 }, { t: 0.65 }, { t: 0.8, value: 25 }, { t: 0.92 }]
    },
    {
        id: 16, name: "Vertical Drop", difficulty: "hard", parTime: 30,
        starThresholds: { one: 0, two: 58, three: 90 },
        points: [
            { x: 100, y: 80, cp2: { x: 200, y: 80 } },
            { x: 350, y: 100, cp1: { x: 280, y: 80 }, cp2: { x: 380, y: 120 } },
            { x: 420, y: 500, cp1: { x: 400, y: 250 }, cp2: { x: 500, y: 550 } },
            { x: 700, y: 420, cp1: { x: 600, y: 520 }, cp2: { x: 800, y: 350 } },
            { x: 950, y: 380, cp1: { x: 880, y: 360 }, cp2: { x: 1020, y: 420 } },
            { x: 1150, y: 350, cp1: { x: 1100, y: 400 } }
        ],
        coins: [{ t: 0.1 }, { t: 0.28, value: 25 }, { t: 0.45 }, { t: 0.62, value: 25 }, { t: 0.78 }, { t: 0.92, value: 25 }]
    },
    {
        id: 17, name: "Death Spiral", difficulty: "hard", parTime: 50,
        starThresholds: { one: 0, two: 62, three: 94 },
        points: [
            { x: 100, y: 100, cp2: { x: 200, y: 80 } },
            { x: 400, y: 150, cp1: { x: 300, y: 100 }, cp2: { x: 500, y: 250 } },
            { x: 600, y: 400, cp1: { x: 550, y: 320 }, cp2: { x: 650, y: 500 } },
            { x: 750, y: 450, cp1: { x: 700, y: 520 }, cp2: { x: 850, y: 480 } },
            { x: 900, y: 350, cp1: { x: 950, y: 450 }, cp2: { x: 900, y: 250 } },
            { x: 800, y: 200, cp1: { x: 850, y: 220 }, cp2: { x: 720, y: 150 } },
            { x: 700, y: 280, cp1: { x: 680, y: 180 }, cp2: { x: 750, y: 360 } },
            { x: 900, y: 420, cp1: { x: 820, y: 380 }, cp2: { x: 980, y: 480 } },
            { x: 1100, y: 380, cp1: { x: 1050, y: 450 }, cp2: { x: 1180, y: 300 } },
            { x: 1300, y: 350, cp1: { x: 1250, y: 320 } }
        ],
        coins: [{ t: 0.06 }, { t: 0.18, value: 25 }, { t: 0.32 }, { t: 0.46, value: 25 }, { t: 0.6 }, { t: 0.74, value: 25 }, { t: 0.88 }]
    },
    {
        id: 18, name: "Mega Drop", difficulty: "hard", parTime: 35,
        starThresholds: { one: 0, two: 60, three: 92 },
        points: [
            { x: 100, y: 60, cp2: { x: 200, y: 60 } },
            { x: 400, y: 80, cp1: { x: 300, y: 60 }, cp2: { x: 450, y: 150 } },
            { x: 500, y: 520, cp1: { x: 480, y: 350 }, cp2: { x: 600, y: 580 } },
            { x: 800, y: 400, cp1: { x: 700, y: 550 }, cp2: { x: 900, y: 320 } },
            { x: 1050, y: 450, cp1: { x: 980, y: 360 }, cp2: { x: 1120, y: 500 } },
            { x: 1250, y: 380, cp1: { x: 1200, y: 480 } }
        ],
        coins: [{ t: 0.08 }, { t: 0.22, value: 25 }, { t: 0.38 }, { t: 0.55, value: 25 }, { t: 0.72 }, { t: 0.88, value: 25 }]
    },
    {
        id: 19, name: "Infinity Loop", difficulty: "hard", parTime: 55,
        starThresholds: { one: 0, two: 65, three: 95 },
        points: [
            { x: 100, y: 100, cp2: { x: 200, y: 80 } },
            { x: 450, y: 150, cp1: { x: 350, y: 100 }, cp2: { x: 550, y: 250 } },
            { x: 650, y: 380, cp1: { x: 600, y: 300 }, cp2: { x: 700, y: 480 } },
            { x: 800, y: 420, cp1: { x: 750, y: 500 }, cp2: { x: 900, y: 450 } },
            { x: 950, y: 320, cp1: { x: 1000, y: 420 }, cp2: { x: 950, y: 220 } },
            { x: 850, y: 180, cp1: { x: 900, y: 200 }, cp2: { x: 780, y: 140 } },
            { x: 750, y: 250, cp1: { x: 730, y: 160 }, cp2: { x: 800, y: 340 } },
            { x: 920, y: 400, cp1: { x: 860, y: 360 }, cp2: { x: 1000, y: 480 } },
            { x: 1120, y: 420, cp1: { x: 1080, y: 500 }, cp2: { x: 1200, y: 450 } },
            { x: 1280, y: 320, cp1: { x: 1320, y: 420 }, cp2: { x: 1280, y: 220 } },
            { x: 1180, y: 180, cp1: { x: 1220, y: 200 }, cp2: { x: 1120, y: 140 } },
            { x: 1100, y: 280, cp1: { x: 1080, y: 180 }, cp2: { x: 1150, y: 360 } },
            { x: 1300, y: 380, cp1: { x: 1250, y: 340 } }
        ],
        coins: [{ t: 0.05 }, { t: 0.15, value: 25 }, { t: 0.28 }, { t: 0.4, value: 25 }, { t: 0.52 }, { t: 0.65, value: 25 }, { t: 0.78 }, { t: 0.9, value: 25 }]
    },
    {
        id: 20, name: "Ultimate Challenge", difficulty: "hard", parTime: 60,
        starThresholds: { one: 0, two: 68, three: 96 },
        points: [
            { x: 100, y: 80, cp2: { x: 200, y: 60 } },
            { x: 400, y: 120, cp1: { x: 300, y: 80 }, cp2: { x: 500, y: 200 } },
            { x: 600, y: 450, cp1: { x: 550, y: 320 }, cp2: { x: 680, y: 550 } },
            { x: 820, y: 380, cp1: { x: 760, y: 520 }, cp2: { x: 900, y: 280 } },
            { x: 980, y: 200, cp1: { x: 940, y: 240 }, cp2: { x: 1050, y: 120 } },
            { x: 1150, y: 350, cp1: { x: 1100, y: 180 }, cp2: { x: 1200, y: 480 } },
            { x: 1300, y: 420, cp1: { x: 1260, y: 520 }, cp2: { x: 1400, y: 450 } },
            { x: 1480, y: 300, cp1: { x: 1520, y: 420 }, cp2: { x: 1480, y: 200 } },
            { x: 1380, y: 160, cp1: { x: 1420, y: 180 }, cp2: { x: 1320, y: 120 } },
            { x: 1300, y: 280, cp1: { x: 1280, y: 160 }, cp2: { x: 1350, y: 380 } },
            { x: 1500, y: 400, cp1: { x: 1450, y: 360 } }
        ],
        coins: [{ t: 0.04 }, { t: 0.12, value: 25 }, { t: 0.22 }, { t: 0.32, value: 25 }, { t: 0.44 }, { t: 0.56, value: 25 }, { t: 0.68 }, { t: 0.8, value: 25 }, { t: 0.92 }]
    }
];

window.TRACKS_DATA = TRACKS_DATA;
