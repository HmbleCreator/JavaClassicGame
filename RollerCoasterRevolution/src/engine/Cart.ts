import { Track } from './Track';

// Physics constants
const GRAVITY = 0.4;            // Gravity strength on track
const ACCELERATION = 0.6;       // User acceleration
const BRAKING_MAG = 0.4;        // Braking strength  
const FRICTION = 0.995;         // Track friction
const MAX_SPEED = 30.0;         // Maximum speed cap
const MIN_GAP_SPEED = 5.0;      // Minimum speed to cross a gap

export class Cart {
    public x: number = 0;
    public y: number = 0;
    public speed: number = 1;   // Small initial speed
    public angle: number = 0;
    public segmentIndex: number = 0;
    public isAirborne: boolean = false;

    // Track progress
    private arcProgress: number = 0;

    // Input state
    public accelerating: boolean = false;
    public braking: boolean = false;

    constructor(startX: number = 50) {
        this.x = startX;
        this.arcProgress = 0;
        this.segmentIndex = 0;
    }

    public update(track: Track, deltaSeconds: number) {
        if (!track.isLoaded || track.points.length === 0) return;

        const dt = Math.min(deltaSeconds, 0.05) * 60; // Normalize to 60fps, cap delta

        // Get current segment
        const segIdx = Math.floor(this.arcProgress);
        const safeIdx = Math.max(0, Math.min(segIdx, track.points.length - 2));
        this.segmentIndex = safeIdx;

        const point = track.getPointAt(safeIdx);
        const nextPoint = track.getPointAt(safeIdx + 1);

        // Interpolate position
        const segFrac = this.arcProgress - segIdx;
        this.x = point.x + (nextPoint.x - point.x) * segFrac;
        this.y = point.y + (nextPoint.y - point.y) * segFrac;

        this.angle = track.getAngleAt(safeIdx);

        // Check if we're on a gap
        const isOnGap = track.isHole(safeIdx);
        this.isAirborne = isOnGap;

        // Gap crossing logic
        if (isOnGap) {
            if (this.speed >= MIN_GAP_SPEED) {
                // Sufficient speed - continue across gap
                // Just keep moving forward, gravity pulls down slightly
                this.y += 2; // Visual drop effect
            } else {
                // Not enough speed - fall!
                // Respawn behind the gap
                let respawnIdx = safeIdx - 1;
                while (respawnIdx > 0 && track.isHole(respawnIdx)) {
                    respawnIdx--;
                }
                respawnIdx = Math.max(0, respawnIdx - 5);

                this.arcProgress = respawnIdx;
                this.speed = 1;
                const respawnPoint = track.getPointAt(respawnIdx);
                this.x = respawnPoint.x;
                this.y = respawnPoint.y;
                return;
            }
        }

        // Physics calculation
        let force = 0;

        if (this.accelerating) force += ACCELERATION;
        if (this.braking) {
            force -= BRAKING_MAG;
            this.speed *= 0.95;
        }

        // Gravity on slope (only when not on gap)
        if (!isOnGap) {
            force += Math.sin(this.angle) * GRAVITY;
        }

        // Friction
        this.speed *= FRICTION;

        // Apply force
        this.speed += force * dt;

        // Speed limits
        if (this.speed > MAX_SPEED) this.speed = MAX_SPEED;
        if (this.speed < -MAX_SPEED * 0.3) this.speed = -MAX_SPEED * 0.3;

        // Move along track
        this.arcProgress += this.speed * 0.1 * dt;

        // Track bounds
        if (this.arcProgress < 0) {
            this.arcProgress = 0;
            this.speed = 0;
        }
        if (this.arcProgress >= track.points.length - 2) {
            this.arcProgress = track.points.length - 2;
        }
    }
}
