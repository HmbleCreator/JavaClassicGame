/**
 * Rollercoaster Rush - Menu State
 * Handles main menu and track selection UI
 */

class MenuState {
    constructor(game) {
        this.game = game;
        this.setupEventListeners();
    }

    setupEventListeners() {
        document.getElementById('btn-play')?.addEventListener('click', () => this.startGame());
        document.getElementById('btn-tracks')?.addEventListener('click', () => this.showTrackSelection());
        document.getElementById('btn-back-menu')?.addEventListener('click', () => this.showMainMenu());
    }

    showMainMenu() {
        this.hideAllScreens();
        document.getElementById('menu-screen')?.classList.add('active');
        document.getElementById('game-hud')?.classList.add('hidden');
        document.getElementById('btn-pause')?.classList.add('hidden');
        this.updateStats();
    }

    showTrackSelection() {
        this.hideAllScreens();
        document.getElementById('track-screen')?.classList.add('active');
        this.populateTrackList();
    }

    hideAllScreens() {
        document.querySelectorAll('.screen').forEach(s => s.classList.remove('active'));
    }

    updateStats() {
        const progress = this.game.getProgress();
        document.getElementById('total-stars').textContent = progress.totalStars;
        document.getElementById('max-stars').textContent = progress.maxStars;
        document.getElementById('total-coins').textContent = progress.totalCoins;
    }

    populateTrackList() {
        const container = document.getElementById('track-list');
        if (!container) return;
        container.innerHTML = '';

        const progress = this.game.getProgress();

        TRACKS_DATA.forEach((trackData, index) => {
            const isUnlocked = index === 0 || progress.unlockedTracks.includes(trackData.id) ||
                (index > 0 && progress.completedTracks.includes(TRACKS_DATA[index - 1].id));
            const stars = progress.trackStars[trackData.id] || 0;

            const card = document.createElement('div');
            card.className = `track-card ${isUnlocked ? '' : 'locked'}`;
            card.innerHTML = `
                <div class="track-header">
                    <span class="track-number">Track ${trackData.id}</span>
                    <span class="track-difficulty ${trackData.difficulty}">${trackData.difficulty}</span>
                </div>
                <div class="track-name">${trackData.name}</div>
                <div class="track-stars">
                    ${[1, 2, 3].map(i => `<span class="star ${i <= stars ? 'earned' : ''}">★</span>`).join('')}
                </div>
            `;

            if (isUnlocked) {
                card.addEventListener('click', () => {
                    this.game.loadTrack(trackData.id);
                    this.game.startPlaying();
                });
            }

            container.appendChild(card);
        });
    }

    startGame() {
        const progress = this.game.getProgress();
        let trackId = 1;
        for (let i = TRACKS_DATA.length - 1; i >= 0; i--) {
            if (progress.completedTracks.includes(TRACKS_DATA[i].id)) {
                trackId = TRACKS_DATA[Math.min(i + 1, TRACKS_DATA.length - 1)].id;
                break;
            }
        }
        this.game.loadTrack(trackId);
        this.game.startPlaying();
    }
}

window.MenuState = MenuState;
