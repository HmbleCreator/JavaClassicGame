/**
 * Rollercoaster Rush - Main Game
 * Central game controller and initialization
 */

class Game {
    constructor() {
        this.canvas = document.getElementById('gameCanvas');
        this.renderer = new Renderer(this.canvas);
        this.physics = new PhysicsEngine();
        this.input = new InputHandler();
        this.coaster = new Coaster();
        this.passengers = new PassengerSystem(3);
        this.coins = new CoinSystem();

        this.currentTrack = null;
        this.tracks = new Map();

        // Load all tracks
        TRACKS_DATA.forEach(data => {
            this.tracks.set(data.id, new Track(data));
        });

        // Initialize states
        this.menu = new MenuState(this);
        this.playing = new PlayingState(this);
        this.results = new ResultsState(this);

        // Start at menu
        this.menu.showMainMenu();

        // Draw background immediately
        this.renderBackground();
    }

    renderBackground() {
        const animate = (time) => {
            if (!this.playing.isPlaying) {
                this.renderer.clear();
                this.renderer.drawBackground(time);
            }
            requestAnimationFrame(animate);
        };
        animate(0);
    }

    loadTrack(trackId) {
        this.currentTrack = this.tracks.get(trackId);
        if (!this.currentTrack) {
            console.error('Track not found:', trackId);
            this.currentTrack = this.tracks.get(1);
        }
    }

    startPlaying() {
        this.playing.start();
    }

    getProgress() {
        const saved = JSON.parse(localStorage.getItem('rollercoaster_progress') || '{}');
        return {
            completedTracks: saved.completedTracks || [],
            unlockedTracks: saved.unlockedTracks || [1],
            trackStars: saved.trackStars || {},
            totalCoins: saved.totalCoins || 0,
            totalStars: Object.values(saved.trackStars || {}).reduce((a, b) => a + b, 0),
            maxStars: TRACKS_DATA.length * 3
        };
    }
}

// Initialize game when DOM is loaded
document.addEventListener('DOMContentLoaded', () => {
    window.game = new Game();

    // Hide loading screen
    document.getElementById('loading-screen')?.classList.remove('active');
});
