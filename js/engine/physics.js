/**
 * Rollercoaster Rush - Physics Engine
 * Handles gravity, momentum, friction, and track physics
 */

class PhysicsEngine {
    constructor() {
        // Physics constants
        this.GRAVITY = 980; // pixels per second squared (9.8 m/s² scaled)
        this.FRICTION = 0.02; // Base friction coefficient
        this.AIR_RESISTANCE = 0.001; // Air drag coefficient
        this.MAX_SPEED = 800; // Maximum speed in pixels/second
        this.MIN_SPEED = 20; // Minimum speed to prevent stopping
        
        // Boost/Brake modifiers
        this.BOOST_FORCE = 300; // Acceleration when boosting
        this.BRAKE_FORCE = 400; // Deceleration when braking
        
        // Safety thresholds
        this.SAFE_SPEED_MULTIPLIER = 1.5; // Speed multiplier for safe navigation
        this.DANGER_SPEED_MULTIPLIER = 2.0; // Speed above this causes unhappiness
        
        // Loop physics
        this.MIN_LOOP_SPEED = 200; // Minimum speed to complete a loop
        this.CENTRIPETAL_FACTOR = 0.8; // Centripetal force factor
    }
    
    /**
     * Calculate velocity based on track slope and current state
     * @param {Object} state - Current physics state
     * @param {number} slope - Track slope at current position (-1 to 1)
     * @param {number} deltaTime - Time since last update in seconds
     * @param {Object} controls - Current control state {boost, brake}
     * @returns {Object} Updated physics state
     */
    update(state, slope, curvature, deltaTime, controls = {}) {
        let { velocity, position } = state;
        
        // Calculate gravitational component based on slope
        // Positive slope = going up, negative = going down
        const gravityEffect = this.GRAVITY * Math.sin(Math.atan(slope));
        
        // Apply gravity (negative because positive velocity = forward movement)
        velocity -= gravityEffect * deltaTime;
        
        // Apply friction (always opposes motion)
        const frictionForce = this.FRICTION * this.GRAVITY * Math.cos(Math.atan(slope));
        if (velocity > 0) {
            velocity -= frictionForce * deltaTime;
        } else if (velocity < 0) {
            velocity += frictionForce * deltaTime;
        }
        
        // Apply air resistance (proportional to velocity squared)
        const airDrag = this.AIR_RESISTANCE * velocity * Math.abs(velocity);
        velocity -= airDrag * deltaTime;
        
        // Apply boost
        if (controls.boost) {
            velocity += this.BOOST_FORCE * deltaTime;
        }
        
        // Apply brake
        if (controls.brake) {
            velocity -= this.BRAKE_FORCE * deltaTime;
            // Prevent reversing from braking
            if (velocity < 0) velocity = 0;
        }
        
        // Apply centripetal force in curves (helps maintain speed in loops)
        if (Math.abs(curvature) > 0.1) {
            const centripetalBoost = Math.abs(curvature) * this.CENTRIPETAL_FACTOR * 50;
            velocity += centripetalBoost * deltaTime;
        }
        
        // Clamp velocity
        velocity = Math.max(-this.MAX_SPEED * 0.3, Math.min(this.MAX_SPEED, velocity));
        
        // Ensure minimum forward speed (prevent getting stuck)
        if (velocity > 0 && velocity < this.MIN_SPEED && !controls.brake) {
            velocity = this.MIN_SPEED;
        }
        
        // Update position based on velocity
        position += velocity * deltaTime;
        
        return {
            velocity,
            position,
            speed: Math.abs(velocity),
            isMovingForward: velocity >= 0
        };
    }
    
    /**
     * Calculate the danger level based on speed and track curvature
     * @param {number} speed - Current speed
     * @param {number} curvature - Track curvature at current position
     * @returns {number} Danger level 0-1 (0 = safe, 1 = dangerous)
     */
    calculateDangerLevel(speed, curvature) {
        // Higher curvature = lower safe speed
        const safeSpeed = this.calculateSafeSpeed(curvature);
        const dangerSpeed = safeSpeed * this.DANGER_SPEED_MULTIPLIER;
        
        if (speed <= safeSpeed) {
            return 0;
        } else if (speed >= dangerSpeed) {
            return 1;
        } else {
            return (speed - safeSpeed) / (dangerSpeed - safeSpeed);
        }
    }
    
    /**
     * Calculate safe speed for a given curvature
     * @param {number} curvature - Track curvature (0 = straight, higher = sharper)
     * @returns {number} Safe speed for this curvature
     */
    calculateSafeSpeed(curvature) {
        const baseSafeSpeed = 400;
        const curvaturePenalty = Math.abs(curvature) * 200;
        return Math.max(100, baseSafeSpeed - curvaturePenalty);
    }
    
    /**
     * Check if speed is sufficient to complete a loop
     * @param {number} speed - Current speed
     * @param {number} loopRadius - Radius of the loop
     * @returns {boolean} True if speed is sufficient
     */
    canCompleteLoop(speed, loopRadius) {
        // Minimum speed increases with loop radius
        const minSpeed = this.MIN_LOOP_SPEED * Math.sqrt(loopRadius / 100);
        return speed >= minSpeed;
    }
    
    /**
     * Calculate excitement level based on speed and maneuvers
     * @param {number} speed - Current speed
     * @param {number} curvature - Track curvature
     * @param {boolean} isInLoop - Whether currently in a loop
     * @returns {number} Excitement level 0-1
     */
    calculateExcitement(speed, curvature, isInLoop) {
        let excitement = 0;
        
        // Speed contributes to excitement
        excitement += Math.min(speed / this.MAX_SPEED, 0.5);
        
        // Curvature adds thrill
        excitement += Math.min(Math.abs(curvature) * 0.3, 0.3);
        
        // Loops are exciting!
        if (isInLoop) {
            excitement += 0.2;
        }
        
        return Math.min(excitement, 1);
    }
    
    /**
     * Convert internal speed to display km/h
     * @param {number} speed - Internal speed value
     * @returns {number} Speed in km/h for display
     */
    toDisplaySpeed(speed) {
        // Scale for nice display numbers (0-200+ km/h range)
        return Math.round(speed * 0.25);
    }
}

// Export for use in other modules
window.PhysicsEngine = PhysicsEngine;
