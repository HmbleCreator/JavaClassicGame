/**
 * Rollercoaster Rush - Playing State
 * Main gameplay loop and state management
 */

class PlayingState {
    constructor(game) {
        this.game = game;
        this.isPlaying = false;
        this.isPaused = false;
        this.time = 0;
        this.lastTime = 0;
        this.animationId = null;
        this.setupEventListeners();
    }

    setupEventListeners() {
        document.getElementById('btn-pause')?.addEventListener('click', () => this.pause());
        document.getElementById('btn-resume')?.addEventListener('click', () => this.resume());
        document.getElementById('btn-restart')?.addEventListener('click', () => this.restart());
        document.getElementById('btn-quit')?.addEventListener('click', () => this.quit());
    }

    start() {
        this.hideAllScreens();
        document.getElementById('game-hud')?.classList.remove('hidden');
        document.getElementById('btn-pause')?.classList.remove('hidden');

        this.isPlaying = true;
        this.isPaused = false;
        this.time = 0;
        this.lastTime = performance.now();

        this.game.coaster.reset();
        this.game.passengers.reset();
        this.game.coins.loadCoins(this.game.currentTrack);
        this.game.input.reset();

        // Set initial camera position
        const startPos = this.game.currentTrack.getStartPosition();
        this.game.renderer.camera.x = startPos.x - this.game.renderer.width / 2;
        this.game.renderer.camera.y = startPos.y - this.game.renderer.height / 2;

        this.loop();
    }

    loop() {
        if (!this.isPlaying) return;

        const now = performance.now();
        const deltaTime = Math.min((now - this.lastTime) / 1000, 0.05);
        this.lastTime = now;

        if (!this.isPaused) {
            this.time += deltaTime;
            this.update(deltaTime);
        }

        this.render(now);
        this.animationId = requestAnimationFrame(() => this.loop());
    }

    update(deltaTime) {
        const track = this.game.currentTrack;
        const coaster = this.game.coaster;
        const controls = this.game.input.getControls();

        // Update coaster physics
        const result = coaster.update(track, this.game.physics, controls, deltaTime);

        // Calculate danger level
        const dangerLevel = this.game.physics.calculateDangerLevel(result.speed, result.curvature);
        const isInLoop = Math.abs(result.curvature) > 1.5;

        // Update passengers
        const passengerResult = this.game.passengers.update(
            result.speed, result.curvature, dangerLevel, isInLoop, deltaTime
        );

        // Check coin collection
        const collectedCoin = this.game.coins.checkCollection(coaster.position);
        if (collectedCoin) {
            this.game.passengers.collectCoin();
            this.game.renderer.addParticle(collectedCoin.x, collectedCoin.y, 'coin');
        }

        // Update HUD
        this.updateHUD(result.speed, passengerResult.happiness);

        // Check for finish or crash
        if (coaster.isFinished) {
            this.finish(true);
        } else if (coaster.isCrashed) {
            this.finish(false);
        }
    }

    render(time) {
        const renderer = this.game.renderer;
        const coaster = this.game.coaster;
        const track = this.game.currentTrack;

        renderer.clear();
        renderer.drawBackground(time);

        // Update camera to follow coaster
        renderer.updateCamera(coaster.position.x, coaster.position.y);

        renderer.beginCamera();

        // Draw track
        renderer.drawTrack(track, coaster.progress);

        // Draw start/finish markers
        const startPos = track.getStartPosition();
        const endPos = track.getEndPosition();
        renderer.drawMarker(startPos.x, startPos.y, 'start');
        renderer.drawMarker(endPos.x, endPos.y, 'finish');

        // Draw coins
        renderer.drawCoins(this.game.coins.getCoins(), time);

        // Draw coaster
        renderer.drawCoaster(
            coaster.position,
            coaster.angle,
            this.game.passengers.getPassengers(),
            coaster.getSpeed()
        );

        // Update particles
        renderer.updateParticles((time - this.lastTime) / 1000);

        renderer.endCamera();
    }

    updateHUD(speed, happiness) {
        const displaySpeed = this.game.physics.toDisplaySpeed(speed);
        document.getElementById('speed-value').textContent = displaySpeed;
        document.getElementById('happiness-value').textContent = Math.round(happiness) + '%';
        document.getElementById('coins-collected').textContent = this.game.coins.getCollected();

        const happinessFill = document.getElementById('happiness-fill');
        happinessFill.style.width = happiness + '%';
        happinessFill.className = 'meter-fill';
        if (happiness < 30) happinessFill.classList.add('danger');
        else if (happiness < 60) happinessFill.classList.add('warning');
    }

    pause() {
        this.isPaused = true;
        document.getElementById('pause-screen')?.classList.add('active');
    }

    resume() {
        this.isPaused = false;
        this.lastTime = performance.now();
        document.getElementById('pause-screen')?.classList.remove('active');
    }

    restart() {
        document.getElementById('pause-screen')?.classList.remove('active');
        this.start();
    }

    quit() {
        this.stop();
        this.game.menu.showMainMenu();
    }

    stop() {
        this.isPlaying = false;
        if (this.animationId) cancelAnimationFrame(this.animationId);
    }

    finish(success) {
        this.stop();
        this.game.results.show(success, {
            happiness: this.game.passengers.getAverageHappiness(),
            coinsCollected: this.game.coins.getCollected(),
            coinsTotal: this.game.coins.getTotal(),
            time: this.time,
            trackId: this.game.currentTrack.id
        });
    }

    hideAllScreens() {
        document.querySelectorAll('.screen').forEach(s => s.classList.remove('active'));
    }
}

window.PlayingState = PlayingState;
