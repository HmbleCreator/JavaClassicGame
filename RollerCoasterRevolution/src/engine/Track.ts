import { Graphics } from 'pixi.js';
import { TrackLoader, TrackData, TrackSegment, TRACK_FLAGS } from './TrackLoader';

export interface TrackPoint {
    x: number;
    y: number;
    flags: number;
}

export class Track {
    public points: TrackPoint[] = [];
    public isLoaded: boolean = false;
    private trackData: TrackData | null = null;

    constructor() {
        // Track starts empty, call loadTrack to populate
    }

    /**
     * Load track from JSON file
     */
    async loadTrack(trackId: number): Promise<void> {
        try {
            this.trackData = await TrackLoader.load(trackId, 400);

            // Use the pre-computed segments as our track points
            this.points = this.trackData.segments.map(seg => ({
                x: seg.x,
                y: seg.y,
                flags: seg.flags,
            }));

            this.isLoaded = true;
            console.log(`Track ${trackId} loaded with ${this.points.length} points`);
        } catch (error) {
            console.error('Failed to load track:', error);
            // Fall back to test track
            this.generateTestTrack();
        }
    }

    /**
     * Generate a simple test track (fallback)
     */
    private generateTestTrack() {
        this.points = [];
        const baseY = 400;

        // Simple hill track for testing
        for (let x = 0; x < 2000; x += 5) {
            const hillY = Math.sin(x * 0.005) * 100;
            this.points.push({
                x: x,
                y: baseY - hillY,
                flags: 0,
            });
        }
        this.isLoaded = true;
    }

    public getLength(): number {
        return this.points.length;
    }

    public getAngleAt(index: number): number {
        if (index < 0 || index >= this.points.length - 1) return 0;
        const p1 = this.points[index];
        const p2 = this.points[index + 1];
        return Math.atan2(p2.y - p1.y, p2.x - p1.x);
    }

    public getPointAt(index: number): TrackPoint {
        if (index < 0) return this.points[0];
        if (index >= this.points.length) return this.points[this.points.length - 1];
        return this.points[index];
    }

    public isHole(index: number): boolean {
        return (this.points[index]?.flags & TRACK_FLAGS.HOLE) !== 0;
    }

    public isTunnel(index: number): boolean {
        return (this.points[index]?.flags & TRACK_FLAGS.TUNNEL) !== 0;
    }

    public isFalling(index: number): boolean {
        return (this.points[index]?.flags & TRACK_FLAGS.FALL) !== 0;
    }

    public render(graphics: Graphics, cameraX: number, cameraY: number = 0) {
        if (!this.isLoaded || this.points.length === 0) return;

        const screenWidth = 800;
        const screenHeight = 600;
        const buffer = 200;

        // Draw track rail (main line) - draw ALL points, culling only skips drawing
        graphics.setStrokeStyle({ width: 6, color: 0x544733 });

        let wasVisible = false;
        let lastScreenX = 0;
        let lastScreenY = 0;

        for (let i = 0; i < this.points.length; i++) {
            const p = this.points[i];
            const screenX = p.x - cameraX;
            const screenY = p.y - cameraY;

            // Check if this point is visible
            const isVisible =
                screenX >= -buffer && screenX <= screenWidth + buffer &&
                screenY >= -buffer && screenY <= screenHeight + buffer;

            // Skip holes in the track
            if (this.isHole(i)) {
                wasVisible = false;
                continue;
            }

            if (!wasVisible) {
                // Start a new path segment
                graphics.moveTo(screenX, screenY);
                wasVisible = true;
            } else {
                // Continue the path
                graphics.lineTo(screenX, screenY);
            }

            lastScreenX = screenX;
            lastScreenY = screenY;
        }
        graphics.stroke();

        // Draw Ties / Sleepers (vertical lines)
        graphics.setStrokeStyle({ width: 3, color: 0x322816 });
        for (let i = 0; i < this.points.length; i += 10) {
            const p = this.points[i];
            const screenX = p.x - cameraX;
            const screenY = p.y - cameraY;

            // Only draw visible ties
            const isVisible =
                screenX >= -buffer && screenX <= screenWidth + buffer &&
                screenY >= -buffer && screenY <= screenHeight + buffer;

            if (!isVisible) continue;
            if (this.isHole(i)) continue;

            graphics.moveTo(screenX, screenY);
            graphics.lineTo(screenX, screenY + 12);
        }
        graphics.stroke();
    }
}
