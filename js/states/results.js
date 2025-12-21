/**
 * Rollercoaster Rush - Results State
 * Shows track completion results and star ratings
 */

class ResultsState {
    constructor(game) {
        this.game = game;
        this.setupEventListeners();
    }

    setupEventListeners() {
        document.getElementById('btn-retry')?.addEventListener('click', () => this.retry());
        document.getElementById('btn-next')?.addEventListener('click', () => this.nextTrack());
        document.getElementById('btn-menu')?.addEventListener('click', () => this.backToMenu());
    }

    show(success, data) {
        document.querySelectorAll('.screen').forEach(s => s.classList.remove('active'));
        document.getElementById('results-screen')?.classList.add('active');
        document.getElementById('game-hud')?.classList.add('hidden');
        document.getElementById('btn-pause')?.classList.add('hidden');

        const title = document.getElementById('results-title');
        title.textContent = success ? 'TRACK COMPLETE!' : 'CRASHED!';
        title.className = success ? '' : 'failed';

        // Calculate stars
        const stars = this.calculateStars(success, data);

        // Animate stars
        const starsDisplay = document.getElementById('stars-display');
        const starElements = starsDisplay.querySelectorAll('.star');
        starElements.forEach((star, i) => {
            star.className = 'star';
            star.textContent = '☆';
            if (i < stars) {
                setTimeout(() => {
                    star.classList.add('earned');
                    star.textContent = '★';
                }, 300 * (i + 1));
            }
        });

        // Show stats
        document.getElementById('result-happiness').textContent = Math.round(data.happiness) + '%';
        document.getElementById('result-coins').textContent = `${data.coinsCollected} / ${data.coinsTotal}`;

        const minutes = Math.floor(data.time / 60);
        const seconds = Math.floor(data.time % 60);
        document.getElementById('result-time').textContent = `${minutes}:${seconds.toString().padStart(2, '0')}`;

        // Save progress
        if (success) {
            this.saveProgress(data.trackId, stars, data.coinsCollected);
        }

        // Show/hide next button
        const nextBtn = document.getElementById('btn-next');
        const currentIndex = TRACKS_DATA.findIndex(t => t.id === data.trackId);
        nextBtn.style.display = (success && currentIndex < TRACKS_DATA.length - 1) ? 'inline-flex' : 'none';
    }

    calculateStars(success, data) {
        if (!success) return 0;

        const track = TRACKS_DATA.find(t => t.id === data.trackId);
        const thresholds = track?.starThresholds || { one: 0, two: 50, three: 90 };

        const coinPercent = (data.coinsCollected / data.coinsTotal) * 100;
        const score = (data.happiness * 0.6) + (coinPercent * 0.4);

        if (score >= thresholds.three && data.time <= track.parTime) return 3;
        if (score >= thresholds.two) return 2;
        return 1;
    }

    saveProgress(trackId, stars, coins) {
        let progress = JSON.parse(localStorage.getItem('rollercoaster_progress') || '{}');

        if (!progress.completedTracks) progress.completedTracks = [];
        if (!progress.trackStars) progress.trackStars = {};
        if (!progress.totalCoins) progress.totalCoins = 0;

        if (!progress.completedTracks.includes(trackId)) {
            progress.completedTracks.push(trackId);
        }

        if (!progress.trackStars[trackId] || progress.trackStars[trackId] < stars) {
            progress.trackStars[trackId] = stars;
        }

        progress.totalCoins += coins;

        localStorage.setItem('rollercoaster_progress', JSON.stringify(progress));
    }

    retry() {
        this.game.startPlaying();
    }

    nextTrack() {
        const currentIndex = TRACKS_DATA.findIndex(t => t.id === this.game.currentTrack.id);
        if (currentIndex < TRACKS_DATA.length - 1) {
            this.game.loadTrack(TRACKS_DATA[currentIndex + 1].id);
            this.game.startPlaying();
        }
    }

    backToMenu() {
        document.getElementById('results-screen')?.classList.remove('active');
        this.game.menu.showMainMenu();
    }
}

window.ResultsState = ResultsState;
