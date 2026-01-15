'use client';

import { useEffect, useRef, useState, useCallback } from 'react';
import { Application } from 'pixi.js';
import { GameLoop } from '@/engine/GameLoop';
import { audioManager } from '@/engine/AudioManager';
import TitleScreen from './TitleScreen';

type GameState = 'title' | 'playing' | 'paused';

export default function GameCanvas() {
    const canvasRef = useRef<HTMLCanvasElement>(null);
    const [gameState, setGameState] = useState<GameState>('title');
    const [isInitialized, setIsInitialized] = useState(false);
    const [currentLevel, setCurrentLevel] = useState(1);
    const gameLoopRef = useRef<GameLoop | null>(null);
    const appRef = useRef<Application | null>(null);

    // Initialize PixiJS app once
    const initPixi = useCallback(async () => {
        if (!canvasRef.current || appRef.current) return;

        try {
            const app = new Application();
            await app.init({
                canvas: canvasRef.current,
                preference: 'webgl',  // Force WebGL instead of WebGPU
                width: 800,
                height: 600,
                backgroundColor: 0x87CEEB,
                resolution: 1,
                autoDensity: true,
                antialias: false,
            });
            appRef.current = app;
            setIsInitialized(true);
        } catch (error) {
            console.error("Failed to initialize Pixi Application:", error);
        }
    }, []);

    // Start game with specified level
    const handleStartGame = useCallback((level: number) => {
        if (!appRef.current) return;

        // Stop title music, start game music
        audioManager.stopAllMusic();
        audioManager.playGameMusic();

        setCurrentLevel(level);
        setGameState('playing');

        // Create/restart game loop
        if (gameLoopRef.current) {
            gameLoopRef.current.destroy();
        }

        const loop = new GameLoop(appRef.current, level);
        gameLoopRef.current = loop;
        loop.start();
    }, []);

    // Handle pause
    const handlePause = useCallback(() => {
        if (gameState === 'playing' && gameLoopRef.current) {
            setGameState('paused');
            gameLoopRef.current.stop();
            audioManager.pauseMusic();
        }
    }, [gameState]);

    // Handle resume
    const handleResume = useCallback(() => {
        if (gameState === 'paused' && gameLoopRef.current) {
            setGameState('playing');
            gameLoopRef.current.start();
            audioManager.resumeMusic();
        }
    }, [gameState]);

    // Return to title
    const handleReturnToTitle = useCallback(() => {
        if (gameLoopRef.current) {
            gameLoopRef.current.destroy();
            gameLoopRef.current = null;
        }
        audioManager.stopAllMusic();
        setGameState('title');
    }, []);

    // Initialize Pixi on mount
    useEffect(() => {
        initPixi();

        return () => {
            if (gameLoopRef.current) {
                gameLoopRef.current.destroy();
                gameLoopRef.current = null;
            }
            if (appRef.current) {
                appRef.current.destroy(true, { children: true, texture: true });
                appRef.current = null;
            }
            setIsInitialized(false);
        };
    }, [initPixi]);

    // Handle keyboard shortcuts
    useEffect(() => {
        const handleKeyDown = (e: KeyboardEvent) => {
            if (e.key === 'Escape') {
                if (gameState === 'playing') {
                    handlePause();
                } else if (gameState === 'paused') {
                    handleResume();
                }
            }
        };

        window.addEventListener('keydown', handleKeyDown);
        return () => window.removeEventListener('keydown', handleKeyDown);
    }, [gameState, handlePause, handleResume]);

    // Handle page visibility for auto-pause
    useEffect(() => {
        const handleVisibilityChange = () => {
            if (document.hidden && gameState === 'playing') {
                handlePause();
            }
        };

        document.addEventListener('visibilitychange', handleVisibilityChange);
        return () => document.removeEventListener('visibilitychange', handleVisibilityChange);
    }, [gameState, handlePause]);

    return (
        <div className="relative w-full h-full flex items-center justify-center bg-black">
            {/* Canvas (always present, hidden during title) */}
            <canvas
                ref={canvasRef}
                className={`max-w-full max-h-full border-4 border-gray-800 shadow-2xl ${gameState === 'title' ? 'opacity-0' : 'opacity-100'
                    } transition-opacity duration-300`}
            />

            {/* Title Screen Overlay */}
            {gameState === 'title' && (
                <TitleScreen onStartGame={handleStartGame} />
            )}

            {/* Pause Overlay */}
            {gameState === 'paused' && (
                <div className="absolute inset-0 flex flex-col items-center justify-center bg-black/70 backdrop-blur-sm">
                    <h2 className="text-5xl font-bold text-white mb-8">PAUSED</h2>
                    <div className="flex gap-4">
                        <button
                            onClick={handleResume}
                            className="px-8 py-4 bg-green-500 hover:bg-green-400 text-white text-xl font-bold rounded-lg"
                        >
                            ▶ RESUME
                        </button>
                        <button
                            onClick={handleReturnToTitle}
                            className="px-8 py-4 bg-gray-600 hover:bg-gray-500 text-white text-xl font-bold rounded-lg"
                        >
                            🏠 MENU
                        </button>
                    </div>
                    <p className="text-white/60 mt-4">Press ESC to resume</p>
                </div>
            )}

            {/* Loading indicator */}
            {!isInitialized && gameState !== 'title' && (
                <div className="absolute inset-0 flex items-center justify-center text-white font-bold">
                    Initializing Engine...
                </div>
            )}
        </div>
    );
}
