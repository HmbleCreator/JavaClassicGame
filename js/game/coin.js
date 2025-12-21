/**
 * Rollercoaster Rush - Coin System
 * Manages coin collection and scoring
 */

class CoinSystem {
    constructor() {
        this.coins = [];
        this.collected = 0;
        this.total = 0;
        this.score = 0;
    }

    loadCoins(track) {
        this.coins = [];
        this.collected = 0;
        this.score = 0;

        if (track.coins && track.coins.length > 0) {
            track.coins.forEach(coinData => {
                const pos = track.getPointAt(coinData.t);
                const normal = track.getNormalAt(coinData.t);
                this.coins.push({
                    x: pos.x + (normal.x * (coinData.offset || 20)),
                    y: pos.y + (normal.y * (coinData.offset || 20)),
                    t: coinData.t,
                    value: coinData.value || 10,
                    collected: false
                });
            });
        } else {
            // Auto-generate coins
            for (let t = 0.1; t < 0.95; t += 0.05 + Math.random() * 0.05) {
                const pos = track.getPointAt(t);
                const normal = track.getNormalAt(t);
                const offset = (Math.random() - 0.5) * 30;
                this.coins.push({
                    x: pos.x + normal.x * offset,
                    y: pos.y + normal.y * offset - 20,
                    t: t,
                    value: Math.random() > 0.8 ? 25 : 10,
                    collected: false
                });
            }
        }
        this.total = this.coins.length;
    }

    checkCollection(coasterPos, radius = 30) {
        let collectedCoin = null;
        this.coins.forEach(coin => {
            if (coin.collected) return;
            const dist = Math.sqrt(Math.pow(coin.x - coasterPos.x, 2) + Math.pow(coin.y - coasterPos.y, 2));
            if (dist < radius) {
                coin.collected = true;
                this.collected++;
                this.score += coin.value;
                collectedCoin = coin;
            }
        });
        return collectedCoin;
    }

    getCoins() { return this.coins; }
    getCollected() { return this.collected; }
    getTotal() { return this.total; }
    getScore() { return this.score; }
    getPercentage() { return this.total > 0 ? (this.collected / this.total) * 100 : 0; }
}

window.CoinSystem = CoinSystem;
