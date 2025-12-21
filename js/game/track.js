/**
 * Rollercoaster Rush - Track System
 * Bezier curve-based track with physics properties
 */

class Track {
    constructor(data) {
        this.id = data.id;
        this.name = data.name;
        this.difficulty = data.difficulty;
        this.points = data.points || [];
        this.coins = data.coins || [];
        this.parTime = data.parTime || 60;
        this.starThresholds = data.starThresholds || { one: 0, two: 50, three: 90 };
        this.totalLength = 0;
        if (this.points.length > 0) this.calculateLength();
    }

    calculateLength() {
        this.totalLength = 0;
        const steps = 100;
        let prevPoint = this.getPointAt(0);
        for (let i = 1; i <= steps; i++) {
            const t = i / steps;
            const point = this.getPointAt(t);
            this.totalLength += Math.sqrt(Math.pow(point.x - prevPoint.x, 2) + Math.pow(point.y - prevPoint.y, 2));
            prevPoint = point;
        }
    }

    getPointAt(t) {
        if (this.points.length < 2) return { x: 0, y: 0 };
        t = Math.max(0, Math.min(1, t));
        const segmentCount = this.points.length - 1;
        const segmentT = t * segmentCount;
        const segmentIndex = Math.min(Math.floor(segmentT), segmentCount - 1);
        const localT = segmentT - segmentIndex;

        const p0 = this.points[segmentIndex];
        const p1 = this.points[segmentIndex + 1];
        const cp1 = p0.cp2 || { x: p0.x + 50, y: p0.y };
        const cp2 = p1.cp1 || { x: p1.x - 50, y: p1.y };

        return this.cubicBezier(p0, cp1, cp2, p1, localT);
    }

    cubicBezier(p0, p1, p2, p3, t) {
        const t2 = t * t, t3 = t2 * t;
        const mt = 1 - t, mt2 = mt * mt, mt3 = mt2 * mt;
        return {
            x: mt3 * p0.x + 3 * mt2 * t * p1.x + 3 * mt * t2 * p2.x + t3 * p3.x,
            y: mt3 * p0.y + 3 * mt2 * t * p1.y + 3 * mt * t2 * p2.y + t3 * p3.y
        };
    }

    getDerivativeAt(t) {
        if (this.points.length < 2) return { x: 1, y: 0 };
        t = Math.max(0, Math.min(1, t));
        const segmentCount = this.points.length - 1;
        const segmentT = t * segmentCount;
        const segmentIndex = Math.min(Math.floor(segmentT), segmentCount - 1);
        const localT = segmentT - segmentIndex;

        const p0 = this.points[segmentIndex];
        const p1 = this.points[segmentIndex + 1];
        const cp1 = p0.cp2 || { x: p0.x + 50, y: p0.y };
        const cp2 = p1.cp1 || { x: p1.x - 50, y: p1.y };

        const mt = 1 - localT;
        return {
            x: 3 * mt * mt * (cp1.x - p0.x) + 6 * mt * localT * (cp2.x - cp1.x) + 3 * localT * localT * (p1.x - cp2.x),
            y: 3 * mt * mt * (cp1.y - p0.y) + 6 * mt * localT * (cp2.y - cp1.y) + 3 * localT * localT * (p1.y - cp2.y)
        };
    }

    getTangentAt(t) {
        const d = this.getDerivativeAt(t);
        const len = Math.sqrt(d.x * d.x + d.y * d.y);
        return len > 0 ? { x: d.x / len, y: d.y / len } : { x: 1, y: 0 };
    }

    getNormalAt(t) {
        const tangent = this.getTangentAt(t);
        return { x: -tangent.y, y: tangent.x };
    }

    getSlopeAt(t) {
        const tangent = this.getTangentAt(t);
        return tangent.y / (tangent.x || 0.001);
    }

    getCurvatureAt(t) {
        const d1 = this.getDerivativeAt(t);
        const epsilon = 0.001;
        const d2 = this.getDerivativeAt(Math.min(1, t + epsilon));
        const ddx = (d2.x - d1.x) / epsilon;
        const ddy = (d2.y - d1.y) / epsilon;
        const speed = Math.sqrt(d1.x * d1.x + d1.y * d1.y);
        if (speed < 0.001) return 0;
        return (d1.x * ddy - d1.y * ddx) / Math.pow(speed, 3);
    }

    getAngleAt(t) {
        const tangent = this.getTangentAt(t);
        return Math.atan2(tangent.y, tangent.x);
    }

    progressToDistance(progress) { return progress * this.totalLength; }
    distanceToProgress(distance) { return this.totalLength > 0 ? distance / this.totalLength : 0; }

    getStartPosition() { return this.getPointAt(0); }
    getEndPosition() { return this.getPointAt(1); }
}

window.Track = Track;
