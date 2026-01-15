import Matter from 'matter-js';

export class Physics {
    public engine: Matter.Engine;
    public runner: Matter.Runner;

    // Constants from original game (converted)
    // Original GRAVITY_CONST = 448 (fixed point 12 bit) -> 448/4096 approx 0.109
    // Matter.js default gravity is 1 (scaled). We might need to adjust this.
    private static readonly GRAVITY_SCALE = 0.001;

    constructor() {
        this.engine = Matter.Engine.create();
        this.runner = Matter.Runner.create();

        // Disable default gravity initially to control it manually if needed, 
        // or tune it to match the "rail" feel.
        this.engine.gravity.y = 1; // Standard gravity for now
        this.engine.gravity.scale = Physics.GRAVITY_SCALE;
    }

    public update(delta: number) {
        Matter.Engine.update(this.engine, delta);
    }

    public addBody(body: Matter.Body) {
        Matter.World.add(this.engine.world, body);
    }

    public createGround() {
        // Test ground
        const ground = Matter.Bodies.rectangle(400, 580, 810, 60, { isStatic: true });
        this.addBody(ground);
        return ground;
    }

    public createTestBox(x: number, y: number) {
        const box = Matter.Bodies.rectangle(x, y, 40, 40);
        this.addBody(box);
        return box;
    }
}
