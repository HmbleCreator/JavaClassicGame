/**
 * Rollercoaster Rush - Coaster
 * Main coaster car logic and state
 */

class Coaster {
    constructor() {
        this.reset();
    }

    reset() {
        this.progress = 0;
        this.velocity = 50;
        this.position = { x: 0, y: 0 };
        this.angle = 0;
        this.isFinished = false;
        this.isCrashed = false;
    }

    update(track, physics, controls, deltaTime) {
        if (this.isFinished || this.isCrashed) return;

        const slope = track.getSlopeAt(this.progress);
        const curvature = track.getCurvatureAt(this.progress);

        const state = physics.update(
            { velocity: this.velocity, position: this.progress * track.totalLength },
            slope, curvature, deltaTime, controls
        );

        this.velocity = state.velocity;
        this.progress = track.distanceToProgress(state.position);

        if (this.progress >= 1) {
            this.progress = 1;
            this.isFinished = true;
        } else if (this.progress < 0) {
            this.progress = 0;
            this.velocity = Math.abs(this.velocity);
        }

        this.position = track.getPointAt(this.progress);
        this.angle = track.getAngleAt(this.progress);

        // Check for crash (too slow in loop)
        if (Math.abs(curvature) > 2 && this.velocity < 150) {
            this.isCrashed = true;
        }

        return { speed: Math.abs(this.velocity), curvature, slope };
    }

    getSpeed() { return Math.abs(this.velocity); }
    getDisplaySpeed(physics) { return physics.toDisplaySpeed(this.getSpeed()); }
}

window.Coaster = Coaster;
