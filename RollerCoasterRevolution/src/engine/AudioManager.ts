import { Howl, Howler } from 'howler';

/**
 * AudioManager - Using Original Game Audio
 * 
 * Plays extracted MIDI files (converted to MP3) from the original game:
 * - title_music.mp3 - Title screen background music
 * - game_music.mp3 - In-game background music (looping)
 * - volume_changed.mp3 - Volume change sound effect
 */

export class AudioManager {
    private isInitialized: boolean = false;
    private isMuted: boolean = false;

    // Music tracks
    private titleMusic: Howl | null = null;
    private gameMusic: Howl | null = null;
    private volumeChangeSound: Howl | null = null;

    // Simple tone generator for minimal SFX
    private audioContext: AudioContext | null = null;
    private masterGain: GainNode | null = null;

    constructor() { }

    public init(): void {
        if (this.isInitialized) return;

        try {
            // Initialize Howler
            Howler.volume(1.0); // Boost global volume
            console.log('🔊 AudioManager: Initializing with Howler.js...');

            // Load title music
            this.titleMusic = new Howl({
                src: ['/audio/title_music.mp3'],
                loop: true,
                volume: 0.6,
                preload: true,
                html5: true, // Force HTML5 Audio which sometimes bypasses Web Audio issues
                onload: () => console.log('✅ Loaded: title_music.mp3'),
                onloaderror: (id, err) => console.error('❌ Failed to load title_music.mp3:', err),
                onplayerror: (id, err) => {
                    console.error('❌ Playback failed for title_music:', err);
                    this.unlockAudio();
                }
            });

            // Load game music
            this.gameMusic = new Howl({
                src: ['/audio/game_music.mp3'],
                loop: true,
                volume: 0.5,
                preload: true,
                html5: true, // Force HTML5 Audio
                onload: () => console.log('✅ Loaded: game_music.mp3'),
                onloaderror: (id, err) => console.error('❌ Failed to load game_music.mp3:', err),
                onplay: () => console.log('🎵 Playing: game_music.mp3'),
                onplayerror: (id, err) => {
                    console.error('❌ Playback failed for game_music:', err);
                    this.unlockAudio();
                }
            });

            // Load volume change beep
            this.volumeChangeSound = new Howl({
                src: ['/audio/volume_changed.mp3'],
                volume: 0.8,
                preload: true,
                onload: () => console.log('✅ Loaded: volume_changed.mp3'),
                onloaderror: (id, err) => console.error('❌ Failed to load volume_changed.mp3:', err),
            });

            // Initialize Web Audio for simple SFX
            const AudioContextClass = window.AudioContext || (window as any).webkitAudioContext;
            this.audioContext = new AudioContextClass();
            this.masterGain = this.audioContext.createGain();
            this.masterGain.connect(this.audioContext.destination);
            this.masterGain.gain.value = 0.15;

            this.isInitialized = true;
            console.log('✅ AudioManager initialized with original game music!');
        } catch (e) {
            console.error('❌ Failed to initialize AudioManager:', e);
        }
    }

    // === Music Controls ===

    /**
     * Play title screen music
     */
    public playTitleMusic(): void {
        this.unlockAudio();
        if (!this.isInitialized || this.isMuted) return;
        console.log('🎵 playTitleMusic() called');
        this.stopAllMusic();
        this.titleMusic?.play();
    }

    /**
     * Play in-game music
     */
    public playGameMusic(): void {
        this.unlockAudio();
        if (!this.isInitialized || this.isMuted) return;
        console.log('🎵 playGameMusic() called');
        this.stopAllMusic();
        this.gameMusic?.play();
    }

    /**
     * Stop all music
     */
    public stopAllMusic(): void {
        this.titleMusic?.stop();
        this.gameMusic?.stop();
    }

    /**
     * Pause music (for pause screen)
     */
    public pauseMusic(): void {
        this.titleMusic?.pause();
        this.gameMusic?.pause();
    }

    /**
     * Resume music
     */
    public resumeMusic(): void {
        // Resume whichever was playing
        if (this.titleMusic?.playing() === false && this.titleMusic.seek() > 0) {
            this.titleMusic.play();
        }
        if (this.gameMusic?.playing() === false && this.gameMusic.seek() > 0) {
            this.gameMusic.play();
        }
    }

    // === Sound Effects ===

    /**
     * No-op - engine sound not needed with music
     */
    public updateEngine(_speed: number): void {
        // Original game didn't have engine sounds - just music
    }

    /**
     * Soft track click
     */
    public playTrackClick(): void {
        this.playTone(400, 0.02, 0.02);
    }

    /**
     * Jump sound
     */
    public playJumpSound(): void {
        if (!this.audioContext || !this.masterGain || this.isMuted) return;

        const osc = this.audioContext.createOscillator();
        const gain = this.audioContext.createGain();

        osc.connect(gain);
        gain.connect(this.masterGain);

        osc.type = 'sine';
        const now = this.audioContext.currentTime;

        osc.frequency.setValueAtTime(250, now);
        osc.frequency.linearRampToValueAtTime(400, now + 0.08);

        gain.gain.setValueAtTime(0, now);
        gain.gain.linearRampToValueAtTime(0.06, now + 0.01);
        gain.gain.linearRampToValueAtTime(0, now + 0.1);

        osc.start(now);
        osc.stop(now + 0.1);
    }

    /**
     * Landing sound
     */
    public playLandSound(): void {
        this.playTone(80, 0.08, 0.04);
    }

    /**
     * Level Complete - play volume change beep as celebration
     */
    public playLevelComplete(): void {
        if (!this.isInitialized || this.isMuted) return;

        // Use the volume changed sound as a "ding"
        this.volumeChangeSound?.play();

        // Also play ascending tones
        if (this.audioContext && this.masterGain) {
            const notes = [440, 554, 659, 880];
            const now = this.audioContext.currentTime;

            notes.forEach((freq, i) => {
                const osc = this.audioContext!.createOscillator();
                const gain = this.audioContext!.createGain();

                osc.connect(gain);
                gain.connect(this.masterGain!);

                osc.type = 'sine';
                osc.frequency.value = freq;

                const startTime = now + (i * 0.12);

                gain.gain.setValueAtTime(0, startTime);
                gain.gain.linearRampToValueAtTime(0.06, startTime + 0.02);
                gain.gain.linearRampToValueAtTime(0, startTime + 0.15);

                osc.start(startTime);
                osc.stop(startTime + 0.2);
            });
        }
    }

    /**
     * Crash sound
     */
    public playCrashSound(): void {
        if (!this.audioContext || !this.masterGain || this.isMuted) return;

        const bufferSize = this.audioContext.sampleRate * 0.15;
        const buffer = this.audioContext.createBuffer(1, bufferSize, this.audioContext.sampleRate);
        const data = buffer.getChannelData(0);

        for (let i = 0; i < bufferSize; i++) {
            data[i] = (Math.random() * 2 - 1) * (1 - i / bufferSize);
        }

        const noise = this.audioContext.createBufferSource();
        noise.buffer = buffer;

        const filter = this.audioContext.createBiquadFilter();
        filter.type = 'lowpass';
        filter.frequency.value = 500;

        const gain = this.audioContext.createGain();
        gain.gain.value = 0.1;

        noise.connect(filter);
        filter.connect(gain);
        gain.connect(this.masterGain);

        noise.start();
    }

    // === Helpers ===

    /**
     * Unlock Audio Context (fix for autoplay policies)
     */
    public unlockAudio(): void {
        if (Howler.ctx && Howler.ctx.state !== 'running') {
            Howler.ctx.resume().then(() => {
                console.log('✅ Audio Context Resumed (Howler)');
            });
        }
        if (this.audioContext && this.audioContext.state !== 'running') {
            this.audioContext.resume().then(() => {
                console.log('✅ Audio Context Resumed (WebAudio)');
            });
        }
    }

    /**
     * Helper for simple tones
     */
    private playTone(freq: number, duration: number, vol: number): void {
        this.unlockAudio();
        if (!this.audioContext || !this.masterGain || this.isMuted) return;

        const osc = this.audioContext.createOscillator();
        const gain = this.audioContext.createGain();

        osc.connect(gain);
        gain.connect(this.masterGain);

        osc.type = 'sine';
        osc.frequency.value = freq;

        const now = this.audioContext.currentTime;

        gain.gain.setValueAtTime(0, now);
        gain.gain.linearRampToValueAtTime(vol, now + 0.005);
        gain.gain.linearRampToValueAtTime(0, now + duration);

        osc.start(now);
        osc.stop(now + duration);
    }

    // === Mute Control ===

    public toggleMute(): boolean {
        this.isMuted = !this.isMuted;

        if (this.isMuted) {
            Howler.mute(true);
            if (this.masterGain) this.masterGain.gain.value = 0;
        } else {
            Howler.mute(false);
            if (this.masterGain) this.masterGain.gain.value = 0.15;
        }

        return this.isMuted;
    }

    public getMuted(): boolean {
        return this.isMuted;
    }
}

export const audioManager = new AudioManager();
