import { Application, Graphics, Text, TextStyle, Container, Sprite, Texture, Assets } from 'pixi.js';
import { Track } from './Track';
import { Cart } from './Cart';
import { Background } from './Background';
import { audioManager } from './AudioManager';

type GameState = 'loading' | 'playing' | 'completed' | 'paused';

export class GameLoop {
    private app: Application;
    private isRunning: boolean = false;
    private track: Track;
    private cart: Cart;
    private background: Background;
    private currentLevel: number = 1;
    private totalLevels: number = 99;
    private gameState: GameState = 'loading';

    // Scoring & Time
    private startTime: number = 0;
    private elapsedTime: number = 0;
    private currentScore: number = 0;

    // Audio state
    private audioInitialized: boolean = false;
    private lastTrackClickSegment: number = 0;
    private wasAirborne: boolean = false;

    // Pixi Graphics
    private backgroundGraphics: Graphics;
    private trackGraphics: Graphics;
    private cartGraphics: Graphics; // For debug/effects
    private cartSprites: Sprite[] = []; // Array of sprites for the train
    private cartTextures: Texture[] = [];
    private cartBodyTextures: Texture[] = [];
    private hudText: Text;
    private overlayContainer: Container;
    private overlayText: Text;

    // Train configuration
    private readonly CAR_COUNT = 3;
    private readonly CAR_SPACING = 25; // derived from CAR_DIST 102400 >> 12

    // Bound update function for proper removal
    private boundUpdate: (ticker: any) => void;

    // Camera state
    private cameraX: number = 0;
    private cameraY: number = 0;
    private readonly SCREEN_WIDTH = 800;
    private readonly SCREEN_HEIGHT = 600;

    constructor(app: Application, startLevel: number = 1) {
        this.app = app;
        this.currentLevel = startLevel;
        this.track = new Track();
        this.cart = new Cart(300);
        this.background = new Background(this.SCREEN_WIDTH, this.SCREEN_HEIGHT);

        // Setup Pixi graphics (order matters for layering)
        this.backgroundGraphics = new Graphics();
        this.app.stage.addChild(this.backgroundGraphics);

        this.trackGraphics = new Graphics();
        this.app.stage.addChild(this.trackGraphics);

        // Cart Sprites (Train)
        for (let i = 0; i < this.CAR_COUNT; i++) {
            const sprite = new Sprite();
            sprite.anchor.set(0.5);
            sprite.scale.set(1.5);
            sprite.visible = false;
            this.app.stage.addChild(sprite);
            this.cartSprites.push(sprite);
        }

        this.cartGraphics = new Graphics();
        this.app.stage.addChild(this.cartGraphics);

        // Setup HUD text with shadow for visibility
        const style = new TextStyle({
            fontFamily: '"Press Start 2P", monospace',
            fontSize: 12,
            fill: 0xFFFFFF,
            stroke: { color: 0x000000, width: 3 },
        });
        this.hudText = new Text({ text: 'Loading...', style });
        this.hudText.x = 10;
        this.hudText.y = 10;
        this.app.stage.addChild(this.hudText);

        // Setup overlay for completion screen
        this.overlayContainer = new Container();
        this.overlayContainer.visible = false;
        this.app.stage.addChild(this.overlayContainer);

        const overlayBg = new Graphics();
        overlayBg.rect(0, 0, this.SCREEN_WIDTH, this.SCREEN_HEIGHT);
        overlayBg.fill({ color: 0x000000, alpha: 0.7 });
        this.overlayContainer.addChild(overlayBg);

        const completionStyle = new TextStyle({
            fontFamily: '"Press Start 2P", monospace',
            fontSize: 20,
            fill: 0x00FF00,
            align: 'center',
        });
        this.overlayText = new Text({ text: '', style: completionStyle });
        this.overlayText.anchor = { x: 0.5, y: 0.5 };
        this.overlayText.x = this.SCREEN_WIDTH / 2;
        this.overlayText.y = this.SCREEN_HEIGHT / 2;
        this.overlayContainer.addChild(this.overlayText);

        // Bind update function
        this.boundUpdate = this.update.bind(this);

        // Setup keyboard input
        this.setupInput();

        // Load starting level and sprites
        this.loadSprites().then(() => {
            this.loadLevel(this.currentLevel);
        });
    }

    private async loadSprites() {
        try {
            // Helper to load 36 frames
            const loadFrames = async (prefix: string) => {
                const promises = [];
                for (let i = 0; i < 36; i++) {
                    const num = String(i).padStart(2, '0');
                    promises.push(Assets.load(`/sprites/${prefix}_${num}.png`));
                }
                try {
                    return await Promise.all(promises);
                } catch (e) {
                    console.warn(`Failed to load ${prefix}, fallback?`, e);
                    return [];
                }
            };

            this.cartTextures = await loadFrames('cart'); // Head r14_2

            // Load types
            const boyTextures = await loadFrames('cart_body_boy'); // r14_15
            const girlTextures = await loadFrames('cart_body_girl'); // r14_18
            const emptyTextures = await loadFrames('cart_body_empty'); // r14_0 (fallback)

            // Store fallback if body textures missing
            this.cartBodyTextures = boyTextures.length > 0 ? boyTextures : this.cartTextures;

            // Pre-assign textures to array logic
            // We want specific passengers for specific cars.
            // We'll store them in a map or array of TextureArrays?
            // Actually, simplified:
            // Car 0: Head
            // Car 1: Boy
            // Car 2: Girl

            this.carTextureSets = [
                this.cartTextures,
                boyTextures.length ? boyTextures : (emptyTextures.length ? emptyTextures : this.cartTextures),
                girlTextures.length ? girlTextures : (emptyTextures.length ? emptyTextures : this.cartTextures)
            ];

            console.log('Cart sprites loaded.');
        } catch (e) {
            console.error('Failed to load cart sprites:', e);
        }
    }

    // Add property to class
    private carTextureSets: Texture[][] = [];

    private async loadLevel(levelNum: number) {
        this.gameState = 'loading';
        this.currentLevel = levelNum;
        this.overlayContainer.visible = false;
        this.hudText.text = `Loading Level ${levelNum}...`;

        try {
            await this.track.loadTrack(levelNum);
            this.cart = new Cart(300);
            this.cameraX = 0;
            this.cameraY = 0;
            this.lastTrackClickSegment = 0;
            this.wasAirborne = false;
            this.gameState = 'playing';
            this.startTime = Date.now();
            this.elapsedTime = 0;
            this.currentScore = 0;
            this.hudText.text = `Level ${levelNum} | D=Go | A=Brake | M=Mute`;

            // Start the original game music!
            audioManager.playGameMusic();
        } catch (error) {
            console.error('Failed to load level:', error);
            this.hudText.text = `Error loading Level ${levelNum}!`;
        }
    }

    private setupInput() {
        window.addEventListener('keydown', (e) => {
            // Initialize audio on first interaction
            if (!this.audioInitialized) {
                audioManager.init();
                this.audioInitialized = true;
                // Start music now that audio is initialized
                if (this.gameState === 'playing') {
                    audioManager.playGameMusic();
                }
            }

            if (e.key === 'ArrowRight' || e.key === 'd' || e.key === 'D') {
                this.cart.accelerating = true;
            }
            if (e.key === 'ArrowLeft' || e.key === 'a' || e.key === 'A') {
                this.cart.braking = true;
            }
            if (e.key === 'r' || e.key === 'R') {
                this.restartLevel();
            }
            if (e.key === 'm' || e.key === 'M') {
                const muted = audioManager.toggleMute();
                console.log(muted ? 'Audio muted' : 'Audio unmuted');
            }
            if (e.key === 'Enter' || e.key === ' ') {
                if (this.gameState === 'completed') {
                    this.nextLevel();
                }
            }
        });

        window.addEventListener('keyup', (e) => {
            if (e.key === 'ArrowRight' || e.key === 'd' || e.key === 'D') {
                this.cart.accelerating = false;
            }
            if (e.key === 'ArrowLeft' || e.key === 'a' || e.key === 'A') {
                this.cart.braking = false;
            }
        });
    }

    private restartLevel() {
        this.loadLevel(this.currentLevel);
    }

    private nextLevel() {
        if (this.currentLevel < this.totalLevels) {
            this.loadLevel(this.currentLevel + 1);
        } else {
            this.showGameComplete();
        }
    }

    private showLevelComplete() {
        this.gameState = 'completed';

        // Calculate Score: Base 10000 - (Time in seconds * 100)
        const timeBonus = Math.max(0, 10000 - Math.floor(this.elapsedTime * 100));
        const levelBonus = this.currentLevel * 500;
        const totalScore = timeBonus + levelBonus;

        // Save High Score
        const savedScore = localStorage.getItem(`level_${this.currentLevel}_score`);
        const highScore = savedScore ? Math.max(parseInt(savedScore), totalScore) : totalScore;
        localStorage.setItem(`level_${this.currentLevel}_score`, highScore.toString());

        // Format time
        const minutes = Math.floor(this.elapsedTime / 60);
        const seconds = Math.floor(this.elapsedTime % 60);
        const ms = Math.floor((this.elapsedTime * 100) % 100);
        const timeStr = `${minutes}:${seconds.toString().padStart(2, '0')}.${ms.toString().padStart(2, '0')}`;

        this.overlayContainer.visible = true;
        this.overlayText.text = `LEVEL ${this.currentLevel} COMPLETE!\n\n` +
            `Time: ${timeStr}\n` +
            `Score: ${totalScore}\n` +
            `High Score: ${highScore}\n\n` +
            `ENTER = Next Level`;

        audioManager.playLevelComplete();
    }

    private showGameComplete() {
        this.gameState = 'completed';
        this.overlayContainer.visible = true;
        this.overlayText.text = `CONGRATULATIONS!\n\nAll ${this.totalLevels} levels\ncompleted!\n\nR = Restart`;
        audioManager.playLevelComplete();
    }

    public start() {
        if (this.isRunning) return;
        this.isRunning = true;
        this.app.ticker.add(this.boundUpdate);
    }

    public stop() {
        this.isRunning = false;
        if (this.app.ticker) {
            this.app.ticker.remove(this.boundUpdate);
        }
    }

    private update(ticker: any) {
        if (this.gameState === 'loading' || this.gameState === 'completed') return;

        const deltaSeconds = (ticker.deltaMS || 16.67) / 1000;
        this.elapsedTime += deltaSeconds;

        // Store previous airborne state
        const prevAirborne = this.cart.isAirborne;

        // Update Cart Physics
        this.cart.update(this.track, deltaSeconds);

        // Audio: Track click sounds (every 10 segments)
        const currentSegment = Math.floor(this.cart.segmentIndex / 10);
        if (currentSegment !== this.lastTrackClickSegment && !this.cart.isAirborne) {
            audioManager.playTrackClick();
            this.lastTrackClickSegment = currentSegment;
        }

        // Audio: Jump and land sounds
        if (this.cart.isAirborne && !prevAirborne) {
            audioManager.playJumpSound();
        }
        if (!this.cart.isAirborne && prevAirborne) {
            audioManager.playLandSound();
        }

        // Audio: Continuous engine sound (called every frame)
        // Normalize speed to 0-1 range (max speed is 30)
        const normalizedSpeed = this.cart.isAirborne ? 0 : Math.abs(this.cart.speed) / 30;
        audioManager.updateEngine(normalizedSpeed);

        // Check for level completion
        const trackLength = this.track.getLength();
        if (trackLength > 0) {
            const lastPoint = this.track.getPointAt(trackLength - 1);
            if (this.cart.x >= lastPoint.x - 100) {
                this.showLevelComplete();
                return;
            }
        }

        // Update camera
        this.updateCamera(deltaSeconds);

        // Render background (with parallax)
        this.backgroundGraphics.clear();
        this.background.render(this.backgroundGraphics, this.cameraX, this.cameraY);

        // Render track
        this.trackGraphics.clear();
        this.track.render(this.trackGraphics, this.cameraX, this.cameraY);

        // Render cart
        this.cartGraphics.clear();
        this.renderCart();

        // Update HUD
        const speed = Math.abs(this.cart.speed).toFixed(1);
        const progress = ((this.cart.x / (this.track.getPointAt(trackLength - 1)?.x || 1)) * 100).toFixed(0);

        // Format time
        const minutes = Math.floor(this.elapsedTime / 60);
        const seconds = Math.floor(this.elapsedTime % 60);
        const ms = Math.floor((this.elapsedTime * 100) % 100);
        const timeStr = `${minutes}:${seconds.toString().padStart(2, '0')}.${ms.toString().padStart(2, '0')}`;

        const muteStatus = audioManager.getMuted() ? '🔇' : '🔊';
        this.hudText.text = `L${this.currentLevel} | ${timeStr} | SPD:${speed} | ${progress}% | ${muteStatus}`;
    }

    private updateCamera(deltaSeconds: number) {
        const targetX = this.cart.x - this.SCREEN_WIDTH * 0.3;
        const targetY = this.cart.y - this.SCREEN_HEIGHT * 0.5;

        const lerpSpeed = 5.0;
        const t = Math.min(1, lerpSpeed * deltaSeconds);

        this.cameraX = this.cameraX + (targetX - this.cameraX) * t;
        this.cameraY = this.cameraY + (targetY - this.cameraY) * t;

        if (this.cameraX < 0) this.cameraX = 0;
    }

    private renderCart() {
        const screenX = this.cart.x - this.cameraX;
        const screenY = this.cart.y - this.cameraY;
        const angle = this.cart.angle;

        // Render each car in the train
        const segmentSpacing = 0.5; // Approx segments per car (25px / ~50px segment?)
        // Tweak this value to match 25px visual spacing

        // Iterate through all cars
        for (let i = 0; i < this.CAR_COUNT; i++) {
            const sprite = this.cartSprites[i];

            // Select texture set based on car index
            // Car 0 = Head
            // Car 1 = Boy (Set 1)
            // Car 2 = Girl (Set 2)
            const textures = (this.carTextureSets && this.carTextureSets[i])
                ? this.carTextureSets[i]
                : ((i === 0) ? this.cartTextures : this.cartBodyTextures);

            if (!textures || textures.length === 0) {
                sprite.visible = false;
                continue;
            }

            // Calculate position along track for this car
            // Head car (i=0) is at current position
            // Following cars are at previous positions (simple approximate history)
            let carSeg = this.cart.segmentIndex - (i * segmentSpacing);

            // Loop adjustment if track assumes loop? 
            // For now, clamp to 0
            if (carSeg < 0) carSeg = 0;

            // Get interpolated position
            const idx = Math.floor(carSeg);
            const t = carSeg - idx;
            const pp0 = this.track.getPointAt(idx);
            const pp1 = this.track.getPointAt(idx + 1);

            let x, y, carAngle;

            if (i === 0) {
                x = this.cart.x;
                y = this.cart.y;
                carAngle = this.cart.angle;
            } else {
                if (!pp0 || !pp1) {
                    sprite.visible = false;
                    continue;
                }
                x = pp0.x + (pp1.x - pp0.x) * t;
                y = pp0.y + (pp1.y - pp0.y) * t;

                // Calculate angle based on track direction
                const dx = pp1.x - pp0.x;
                const dy = pp1.y - pp0.y;
                carAngle = Math.atan2(dy, dx);
            }

            const sx = x - this.cameraX;
            const sy = y - this.cameraY;

            // Choose Frame
            let degrees = (carAngle * 180 / Math.PI);
            degrees = degrees % 360;
            if (degrees < 0) degrees += 360;
            let frameIndex = Math.round(degrees / 10) % 36;

            sprite.texture = textures[frameIndex] || textures[0];
            sprite.x = sx;
            sprite.y = sy;
            sprite.rotation = 0; // Texture is already rotated
            sprite.visible = true;
        }

        // Hide CartGraphics (visual fallback/debug)
        this.cartGraphics.clear();

        // Airborne indicator
        if (this.cart.isAirborne) {
            this.cartGraphics.setStrokeStyle({ width: 2, color: 0xFFFFFF, alpha: 0.5 });
            const sx = this.cart.x - this.cameraX;
            const sy = this.cart.y - this.cameraY;
            for (let i = 0; i < 3; i++) {
                const lineY = sy - 30 - i * 8;
                this.cartGraphics.moveTo(sx - 15 - i * 5, lineY);
                this.cartGraphics.lineTo(sx - 25 - i * 5, lineY + 5);
            }
            this.cartGraphics.stroke();
        }
    }

    public destroy() {
        this.stop();
    }
}
