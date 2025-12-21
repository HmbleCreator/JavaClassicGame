/**
 * Rollercoaster Rush - Renderer
 * Handles all canvas rendering
 */

class Renderer {
    constructor(canvas) {
        this.canvas = canvas;
        this.ctx = canvas.getContext('2d');
        this.camera = { x: 0, y: 0, zoom: 1, targetX: 0, targetY: 0, smoothing: 0.1 };
        this.colors = {
            track: { rail: '#c0c0c0', railDark: '#808080', glow: 'rgba(0, 255, 255, 0.3)', support: '#404060' },
            coaster: { body: '#ff3366', accent: '#ff6699', wheel: '#333344' },
            coin: { gold: '#ffd700', glow: 'rgba(255, 215, 0, 0.5)' }
        };
        this.particles = [];
        this.stars = [];
        this.initStars();
        this.resize();
        window.addEventListener('resize', () => this.resize());
    }

    resize() {
        const dpr = window.devicePixelRatio || 1;
        const rect = this.canvas.getBoundingClientRect();
        this.canvas.width = rect.width * dpr;
        this.canvas.height = rect.height * dpr;
        this.ctx.scale(dpr, dpr);
        this.width = rect.width;
        this.height = rect.height;
    }

    initStars() {
        for (let i = 0; i < 100; i++) {
            this.stars.push({
                x: Math.random() * 2000 - 500, y: Math.random() * 2000 - 500,
                size: Math.random() * 2 + 0.5, twinkle: Math.random() * Math.PI * 2
            });
        }
    }

    clear() { this.ctx.clearRect(0, 0, this.width, this.height); }

    drawBackground(time = 0) {
        const gradient = this.ctx.createLinearGradient(0, 0, 0, this.height);
        gradient.addColorStop(0, '#0a0a1a');
        gradient.addColorStop(0.5, '#1a1a3e');
        gradient.addColorStop(1, '#0a0a1a');
        this.ctx.fillStyle = gradient;
        this.ctx.fillRect(0, 0, this.width, this.height);

        this.ctx.save();
        const parallaxX = -this.camera.x * 0.1;
        const parallaxY = -this.camera.y * 0.1;
        this.stars.forEach(star => {
            const x = ((star.x + parallaxX) % this.width + this.width) % this.width;
            const y = ((star.y + parallaxY) % this.height + this.height) % this.height;
            const twinkle = Math.sin(time * 0.003 + star.twinkle) * 0.5 + 0.5;
            this.ctx.beginPath();
            this.ctx.arc(x, y, star.size * twinkle, 0, Math.PI * 2);
            this.ctx.fillStyle = `rgba(255, 255, 255, ${0.3 + twinkle * 0.7})`;
            this.ctx.fill();
        });
        this.ctx.restore();
    }

    updateCamera(targetX, targetY) {
        this.camera.targetX = targetX - this.width / 2;
        this.camera.targetY = targetY - this.height / 2;
        this.camera.x += (this.camera.targetX - this.camera.x) * this.camera.smoothing;
        this.camera.y += (this.camera.targetY - this.camera.y) * this.camera.smoothing;
    }

    beginCamera() {
        this.ctx.save();
        this.ctx.translate(-this.camera.x, -this.camera.y);
    }

    endCamera() { this.ctx.restore(); }

    drawTrack(track, progress = 0) {
        if (!track || !track.points || track.points.length < 2) return;
        this.drawTrackSupports(track);
        this.drawTrackGlow(track);
        this.drawTrackRails(track);
    }

    drawTrackGlow(track) {
        this.ctx.beginPath();
        for (let t = 0; t <= 1; t += 0.01) {
            const pos = track.getPointAt(t);
            if (t === 0) this.ctx.moveTo(pos.x, pos.y);
            else this.ctx.lineTo(pos.x, pos.y);
        }
        this.ctx.strokeStyle = this.colors.track.glow;
        this.ctx.lineWidth = 20;
        this.ctx.lineCap = 'round';
        this.ctx.stroke();
    }

    drawTrackRails(track) {
        const railOffset = 8;
        for (let rail = -1; rail <= 1; rail += 2) {
            this.ctx.beginPath();
            for (let t = 0; t <= 1; t += 0.01) {
                const pos = track.getPointAt(t);
                const normal = track.getNormalAt(t);
                const x = pos.x + normal.x * railOffset * rail;
                const y = pos.y + normal.y * railOffset * rail;
                if (t === 0) this.ctx.moveTo(x, y);
                else this.ctx.lineTo(x, y);
            }
            this.ctx.strokeStyle = this.colors.track.railDark;
            this.ctx.lineWidth = 6;
            this.ctx.stroke();
            this.ctx.strokeStyle = this.colors.track.rail;
            this.ctx.lineWidth = 4;
            this.ctx.stroke();
        }
        this.drawTrackTies(track);
    }

    drawTrackTies(track) {
        this.ctx.strokeStyle = this.colors.track.railDark;
        this.ctx.lineWidth = 3;
        for (let t = 0; t <= 1; t += 0.02) {
            const pos = track.getPointAt(t);
            const normal = track.getNormalAt(t);
            this.ctx.beginPath();
            this.ctx.moveTo(pos.x + normal.x * 10, pos.y + normal.y * 10);
            this.ctx.lineTo(pos.x - normal.x * 10, pos.y - normal.y * 10);
            this.ctx.stroke();
        }
    }

    drawTrackSupports(track) {
        const groundY = this.height + this.camera.y + 100;
        this.ctx.strokeStyle = this.colors.track.support;
        this.ctx.lineWidth = 4;
        for (let t = 0.05; t <= 0.95; t += 0.08) {
            const pos = track.getPointAt(t);
            if (pos.y < groundY - 50) {
                this.ctx.beginPath();
                this.ctx.moveTo(pos.x, pos.y);
                this.ctx.lineTo(pos.x, Math.min(pos.y + 200, groundY));
                this.ctx.stroke();
            }
        }
    }

    drawCoaster(position, angle, passengers = [], speed = 0) {
        this.ctx.save();
        this.ctx.translate(position.x, position.y);
        this.ctx.rotate(angle);
        const carWidth = 50, carHeight = 25;

        // Wheels
        this.ctx.fillStyle = this.colors.coaster.wheel;
        [{ x: -carWidth / 3, y: carHeight / 2 }, { x: carWidth / 3, y: carHeight / 2 }].forEach(w => {
            this.ctx.beginPath();
            this.ctx.arc(w.x, w.y, 6, 0, Math.PI * 2);
            this.ctx.fill();
        });

        // Body
        this.ctx.fillStyle = this.colors.coaster.body;
        this.ctx.beginPath();
        this.ctx.roundRect(-carWidth / 2, -carHeight / 2, carWidth, carHeight, 8);
        this.ctx.fill();

        // Accent
        this.ctx.fillStyle = this.colors.coaster.accent;
        this.ctx.beginPath();
        this.ctx.roundRect(-carWidth / 2 + 5, -carHeight / 2 + 3, carWidth - 10, 6, 3);
        this.ctx.fill();

        // Passengers
        for (let i = 0; i < Math.min(passengers.length, 3); i++) {
            const x = -carWidth / 2 + (carWidth / 4) * (i + 1);
            const y = -carHeight / 2 - 8;
            this.ctx.fillStyle = '#ffcc99';
            this.ctx.beginPath();
            this.ctx.arc(x, y, 6, 0, Math.PI * 2);
            this.ctx.fill();
            const expr = passengers[i].happiness > 70 ? '😄' : passengers[i].happiness > 40 ? '😐' : '😨';
            this.ctx.font = '10px Arial';
            this.ctx.textAlign = 'center';
            this.ctx.fillText(expr, x, y + 4);
        }
        this.ctx.restore();
    }

    drawCoins(coins, time = 0) {
        coins.forEach(coin => {
            if (coin.collected) return;
            const bob = Math.sin(time * 0.005 + coin.x * 0.01) * 3;
            const y = coin.y + bob;
            this.ctx.beginPath();
            this.ctx.arc(coin.x, y, 15, 0, Math.PI * 2);
            this.ctx.fillStyle = this.colors.coin.glow;
            this.ctx.fill();
            this.ctx.beginPath();
            this.ctx.arc(coin.x, y, 10, 0, Math.PI * 2);
            this.ctx.fillStyle = this.colors.coin.gold;
            this.ctx.fill();
        });
    }

    addParticle(x, y, type = 'spark') {
        this.particles.push({
            x, y, vx: (Math.random() - 0.5) * 100,
            vy: type === 'coin' ? -80 : (Math.random() - 0.5) * 100 - 50,
            life: 1, type
        });
    }

    updateParticles(deltaTime) {
        for (let i = this.particles.length - 1; i >= 0; i--) {
            const p = this.particles[i];
            p.x += p.vx * deltaTime;
            p.y += p.vy * deltaTime;
            p.vy += 200 * deltaTime;
            p.life -= deltaTime * 2;
            if (p.life <= 0) { this.particles.splice(i, 1); continue; }
            this.ctx.globalAlpha = p.life;
            this.ctx.fillStyle = p.type === 'coin' ? '#ffd700' : '#00ffff';
            this.ctx.beginPath();
            this.ctx.arc(p.x, p.y, 3 * p.life, 0, Math.PI * 2);
            this.ctx.fill();
            this.ctx.globalAlpha = 1;
        }
    }

    drawMarker(x, y, type = 'start') {
        this.ctx.save();
        this.ctx.translate(x, y - 40);
        this.ctx.fillStyle = '#ffffff';
        this.ctx.fillRect(-2, 0, 4, 40);
        this.ctx.fillStyle = type === 'start' ? '#00ff88' : '#ff3366';
        this.ctx.beginPath();
        this.ctx.moveTo(2, 0);
        this.ctx.lineTo(32, 10);
        this.ctx.lineTo(2, 20);
        this.ctx.closePath();
        this.ctx.fill();
        this.ctx.restore();
    }
}

window.Renderer = Renderer;
