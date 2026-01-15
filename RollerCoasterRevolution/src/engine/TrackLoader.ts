// Track data interface matching the JSON structure
export interface TrackSegment {
    x: number;
    y: number;
    flags: number;
}

export interface TrackControlPoint {
    x: number;
    y: number;
}

export interface TrackData {
    id: number;
    controlPoints: TrackControlPoint[];
    segments: TrackSegment[];
}

// Track flag constants from original TrackModel.java
export const TRACK_FLAGS = {
    HOLE: 1,      // TRACK_FLAG_HOLE_MASK
    BREAK_GO: 2,  // TRACK_FLAG_BREAK_GO_MASK
    TUNNEL: 4,    // TRACK_FLAG_TUNNEL_MASK
    FALL: 8,      // TRACK_FLAG_FALL_MASK
    REPLAY_0: 16, // TRACK_FLAG_REPLAY_0
    REPLAY_1: 32, // TRACK_FLAG_REPLAY_1
};

export class TrackLoader {
    private static cache: Map<number, TrackData> = new Map();

    /**
     * Load a track from JSON file
     * @param trackId Track number (1-99)
     * @param baseY Base Y position for the track (canvas center offset)
     */
    static async load(trackId: number, baseY: number = 400): Promise<TrackData> {
        // Check cache first
        if (this.cache.has(trackId)) {
            return this.cache.get(trackId)!;
        }

        try {
            const response = await fetch(`/tracks/track_${trackId}.json`);
            if (!response.ok) {
                throw new Error(`Failed to load track ${trackId}: ${response.status}`);
            }

            const rawData = await response.json();

            // Process the track data
            const processedData = this.processTrackData(rawData, baseY);

            // Cache it
            this.cache.set(trackId, processedData);

            return processedData;
        } catch (error) {
            console.error(`Error loading track ${trackId}:`, error);
            throw error;
        }
    }

    /**
     * Process raw track data from JSON
     * - Flip Y coordinates (JSON uses negative Y for up, canvas uses positive Y for down)
     * - Apply base offset so track sits at a reasonable position on screen
     */
    private static processTrackData(rawData: any, baseY: number): TrackData {
        // Scale factor to increase track length by 33%
        const SCALE_X = 1.50;

        // Process segments - flip Y, apply offset, scale X
        const segments: TrackSegment[] = rawData.segments.map((seg: any) => ({
            x: seg.x * SCALE_X,
            // Original Y: negative = up, positive = down
            // Canvas Y: positive = down
            // So we negate Y and add baseY offset to bring track into view
            y: baseY - seg.y, // This flips Y and offsets
            flags: seg.flags || 0,
        }));

        // Process control points similarly (with X scaling)
        const controlPoints: TrackControlPoint[] = rawData.controlPoints.map((cp: any) => ({
            x: cp.x * SCALE_X,
            y: baseY - cp.y,
        }));

        return {
            id: rawData.id,
            controlPoints,
            segments,
        };
    }

    /**
     * Clear the cache
     */
    static clearCache() {
        this.cache.clear();
    }
}
