/**
 * Rollercoaster Rush - Passenger System
 * Manages passenger happiness and reactions
 */

class PassengerSystem {
    constructor(count = 3) {
        this.passengers = [];
        for (let i = 0; i < count; i++) {
            this.passengers.push({ happiness: 100, excitement: 0, lastReaction: '' });
        }
        this.averageHappiness = 100;
        this.reactionCooldown = 0;
        this.currentReaction = '';
    }

    reset() {
        this.passengers.forEach(p => { p.happiness = 100; p.excitement = 0; p.lastReaction = ''; });
        this.averageHappiness = 100;
        this.currentReaction = '';
    }

    update(speed, curvature, dangerLevel, isInLoop, deltaTime) {
        this.reactionCooldown = Math.max(0, this.reactionCooldown - deltaTime);

        this.passengers.forEach(p => {
            // Danger decreases happiness
            if (dangerLevel > 0.5) {
                p.happiness -= dangerLevel * 30 * deltaTime;
                if (this.reactionCooldown <= 0) this.currentReaction = dangerLevel > 0.8 ? '😱 TOO FAST!' : '😰 Scary!';
            }
            // Too slow is boring
            else if (speed < 80 && Math.abs(curvature) < 0.5) {
                p.happiness -= 5 * deltaTime;
                if (this.reactionCooldown <= 0) this.currentReaction = '😴 Boring...';
            }
            // Exciting moments
            else if (isInLoop || (speed > 200 && dangerLevel < 0.3)) {
                p.happiness = Math.min(100, p.happiness + 10 * deltaTime);
                p.excitement = Math.min(100, p.excitement + 20 * deltaTime);
                if (this.reactionCooldown <= 0) this.currentReaction = isInLoop ? '🎢 WHEEE!' : '😄 Amazing!';
            }
            // Normal riding
            else {
                p.happiness = Math.min(100, p.happiness + 2 * deltaTime);
                this.currentReaction = '';
            }

            p.happiness = Math.max(0, Math.min(100, p.happiness));
            p.excitement = Math.max(0, p.excitement - 5 * deltaTime);
        });

        if (this.currentReaction) this.reactionCooldown = 1.5;
        this.averageHappiness = this.passengers.reduce((sum, p) => sum + p.happiness, 0) / this.passengers.length;

        return { happiness: this.averageHappiness, reaction: this.currentReaction };
    }

    collectCoin() {
        this.passengers.forEach(p => { p.happiness = Math.min(100, p.happiness + 5); });
        this.currentReaction = '🪙 Nice!';
        this.reactionCooldown = 0.5;
    }

    getPassengers() { return this.passengers; }
    getAverageHappiness() { return this.averageHappiness; }
}

window.PassengerSystem = PassengerSystem;
