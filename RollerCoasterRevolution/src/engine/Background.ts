import { Graphics } from 'pixi.js';

/**
 * Enhanced background renderer
 * Draws a parallax sky with gradient, clouds, and ground
 */
export class Background {
    private width: number;
    private height: number;

    constructor(width: number = 800, height: number = 600) {
        this.width = width;
        this.height = height;
    }

    /**
     * Render the background
     */
    public render(graphics: Graphics, cameraX: number, cameraY: number): void {
        // Sky gradient (top to horizon)
        this.drawSkyGradient(graphics);

        // Clouds (parallax - move slower than camera)
        this.drawClouds(graphics, cameraX * 0.3);

        // Distant hills (parallax)
        this.drawHills(graphics, cameraX * 0.5, cameraY * 0.3);

        // Ground below track
        this.drawGround(graphics, cameraX, cameraY);
    }

    private drawSkyGradient(graphics: Graphics): void {
        // Simple gradient effect using rectangles
        const colors = [
            0x87CEEB, // Light sky blue
            0xB0E0E6, // Powder blue
            0xE0F4FF, // Very light blue
        ];

        const sectionHeight = this.height / colors.length;
        colors.forEach((color, i) => {
            graphics.rect(0, i * sectionHeight, this.width, sectionHeight + 1);
            graphics.fill({ color });
        });
    }

    private drawClouds(graphics: Graphics, offsetX: number): void {
        const cloudPositions = [
            { x: 100, y: 50, scale: 1.0 },
            { x: 350, y: 80, scale: 0.7 },
            { x: 550, y: 40, scale: 1.2 },
            { x: 750, y: 90, scale: 0.8 },
            { x: 950, y: 60, scale: 1.1 },
            { x: 1200, y: 45, scale: 0.9 },
        ];

        graphics.setStrokeStyle({ width: 0 });

        cloudPositions.forEach(cloud => {
            const x = ((cloud.x - offsetX) % (this.width + 300)) - 100;
            if (x < -100 || x > this.width + 100) return;

            this.drawCloud(graphics, x, cloud.y, cloud.scale);
        });
    }

    private drawCloud(graphics: Graphics, x: number, y: number, scale: number): void {
        const baseRadius = 20 * scale;

        // Draw fluffy cloud shape
        graphics.circle(x, y, baseRadius);
        graphics.fill({ color: 0xFFFFFF, alpha: 0.9 });

        graphics.circle(x + baseRadius * 0.8, y - baseRadius * 0.3, baseRadius * 0.7);
        graphics.fill({ color: 0xFFFFFF, alpha: 0.9 });

        graphics.circle(x - baseRadius * 0.6, y + baseRadius * 0.2, baseRadius * 0.6);
        graphics.fill({ color: 0xFFFFFF, alpha: 0.9 });

        graphics.circle(x + baseRadius * 0.5, y + baseRadius * 0.3, baseRadius * 0.5);
        graphics.fill({ color: 0xFFFFFF, alpha: 0.9 });
    }

    private drawHills(graphics: Graphics, offsetX: number, offsetY: number): void {
        // Distant hills
        graphics.setStrokeStyle({ width: 0 });

        const hillY = 400 - offsetY;
        const hillHeight = 150;

        // Multiple overlapping hills
        for (let i = 0; i < 8; i++) {
            const hillX = (i * 200 - offsetX % 400 + 100);
            const variance = Math.sin(i * 1.5) * 30;

            graphics.ellipse(hillX, hillY + variance, 180, hillHeight);
            graphics.fill({ color: 0x228B22, alpha: 0.4 }); // Forest green, semi-transparent
        }
    }

    private drawGround(graphics: Graphics, cameraX: number, cameraY: number): void {
        // Ground at the bottom
        const groundY = 500 - cameraY;

        if (groundY < this.height) {
            graphics.rect(0, groundY, this.width, this.height - groundY + 100);
            graphics.fill({ color: 0x8B4513 }); // Saddle brown (dirt)

            // Grass layer
            graphics.rect(0, groundY, this.width, 15);
            graphics.fill({ color: 0x228B22 }); // Forest green
        }
    }
}
