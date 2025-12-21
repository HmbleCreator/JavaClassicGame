/**
 * Rollercoaster Rush - Input Handler
 * Handles touch and keyboard controls
 */

class InputHandler {
    constructor() {
        this.controls = { boost: false, brake: false };
        this.touchZones = { left: null, right: null };
        this.setupKeyboard();
        this.setupTouch();
    }

    setupKeyboard() {
        document.addEventListener('keydown', (e) => {
            if (e.key === 'ArrowRight' || e.key === 'd' || e.key === 'D') this.controls.boost = true;
            if (e.key === 'ArrowLeft' || e.key === 'a' || e.key === 'A') this.controls.brake = true;
            if (e.key === ' ') this.controls.boost = true;
            if (e.key === 'Escape') this.onPause && this.onPause();
        });

        document.addEventListener('keyup', (e) => {
            if (e.key === 'ArrowRight' || e.key === 'd' || e.key === 'D') this.controls.boost = false;
            if (e.key === 'ArrowLeft' || e.key === 'a' || e.key === 'A') this.controls.brake = false;
            if (e.key === ' ') this.controls.boost = false;
        });
    }

    setupTouch() {
        const brakeZone = document.getElementById('control-brake');
        const boostZone = document.getElementById('control-boost');

        if (brakeZone) {
            brakeZone.addEventListener('touchstart', (e) => { e.preventDefault(); this.controls.brake = true; brakeZone.classList.add('active'); });
            brakeZone.addEventListener('touchend', () => { this.controls.brake = false; brakeZone.classList.remove('active'); });
            brakeZone.addEventListener('mousedown', () => { this.controls.brake = true; brakeZone.classList.add('active'); });
            brakeZone.addEventListener('mouseup', () => { this.controls.brake = false; brakeZone.classList.remove('active'); });
            brakeZone.addEventListener('mouseleave', () => { this.controls.brake = false; brakeZone.classList.remove('active'); });
        }

        if (boostZone) {
            boostZone.addEventListener('touchstart', (e) => { e.preventDefault(); this.controls.boost = true; boostZone.classList.add('active'); });
            boostZone.addEventListener('touchend', () => { this.controls.boost = false; boostZone.classList.remove('active'); });
            boostZone.addEventListener('mousedown', () => { this.controls.boost = true; boostZone.classList.add('active'); });
            boostZone.addEventListener('mouseup', () => { this.controls.boost = false; boostZone.classList.remove('active'); });
            boostZone.addEventListener('mouseleave', () => { this.controls.boost = false; boostZone.classList.remove('active'); });
        }
    }

    getControls() { return { ...this.controls }; }
    reset() { this.controls = { boost: false, brake: false }; }
}

window.InputHandler = InputHandler;
