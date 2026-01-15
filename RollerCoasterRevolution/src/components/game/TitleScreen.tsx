'use client';

import { useEffect, useState, useCallback } from 'react';
import { audioManager } from '@/engine/AudioManager';

interface TitleScreenProps {
    onStartGame: (level: number) => void;
}

export default function TitleScreen({ onStartGame }: TitleScreenProps) {
    const [selectedLevel, setSelectedLevel] = useState(1);
    const [audioInitialized, setAudioInitialized] = useState(false);
    const [showLevelSelect, setShowLevelSelect] = useState(false);

    // Initialize audio and play title music
    useEffect(() => {
        const handleFirstInteraction = () => {
            if (!audioInitialized) {
                audioManager.init();
                audioManager.playTitleMusic();
                setAudioInitialized(true);
            }
        };

        window.addEventListener('keydown', handleFirstInteraction, { once: true });
        window.addEventListener('click', handleFirstInteraction, { once: true });

        return () => {
            window.removeEventListener('keydown', handleFirstInteraction);
            window.removeEventListener('click', handleFirstInteraction);
        };
    }, [audioInitialized]);

    // Handle keyboard input
    useEffect(() => {
        const handleKeyDown = (e: KeyboardEvent) => {
            if (showLevelSelect) {
                switch (e.key) {
                    case 'ArrowUp':
                        setSelectedLevel(l => Math.min(99, l + 1));
                        break;
                    case 'ArrowDown':
                        setSelectedLevel(l => Math.max(1, l - 1));
                        break;
                    case 'ArrowRight':
                        setSelectedLevel(l => Math.min(99, l + 10));
                        break;
                    case 'ArrowLeft':
                        setSelectedLevel(l => Math.max(1, l - 10));
                        break;
                    case 'Enter':
                        onStartGame(selectedLevel);
                        break;
                    case 'Escape':
                        setShowLevelSelect(false);
                        break;
                }
            } else {
                if (e.key === 'Enter' || e.key === ' ') {
                    onStartGame(1);
                } else if (e.key === 'l' || e.key === 'L') {
                    setShowLevelSelect(true);
                }
            }
        };

        window.addEventListener('keydown', handleKeyDown);
        return () => window.removeEventListener('keydown', handleKeyDown);
    }, [showLevelSelect, selectedLevel, onStartGame]);

    return (
        <div className="absolute inset-0 flex flex-col items-center justify-center bg-gradient-to-b from-sky-400 via-sky-500 to-green-600">
            {/* Decorative clouds */}
            <div className="absolute top-10 left-20 w-32 h-16 bg-white/70 rounded-full blur-sm" />
            <div className="absolute top-20 right-32 w-48 h-20 bg-white/60 rounded-full blur-sm" />
            <div className="absolute top-8 left-1/2 w-40 h-14 bg-white/80 rounded-full blur-sm" />

            {/* Main title */}
            <div className="relative z-10 text-center mb-8">
                <h1 className="text-6xl font-bold text-white drop-shadow-lg mb-2"
                    style={{
                        textShadow: '4px 4px 0 #1e40af, -2px -2px 0 #60a5fa',
                        fontFamily: 'Impact, sans-serif'
                    }}>
                    ROLLER COASTER
                </h1>
                <h2 className="text-5xl font-bold text-yellow-400 drop-shadow-lg"
                    style={{
                        textShadow: '3px 3px 0 #b45309, -1px -1px 0 #fcd34d',
                        fontFamily: 'Impact, sans-serif'
                    }}>
                    REVOLUTION
                </h2>
            </div>

            {/* Decorative roller coaster silhouette */}
            <div className="relative z-0 w-full h-24 mb-8">
                <svg viewBox="0 0 800 100" className="w-full h-full" preserveAspectRatio="none">
                    <path
                        d="M0,80 Q100,20 200,60 T400,30 T600,70 T800,40"
                        fill="none"
                        stroke="#374151"
                        strokeWidth="8"
                        strokeLinecap="round"
                    />
                    <path
                        d="M0,85 Q100,25 200,65 T400,35 T600,75 T800,45"
                        fill="none"
                        stroke="#4b5563"
                        strokeWidth="4"
                        strokeLinecap="round"
                    />
                </svg>
            </div>

            {/* Menu options */}
            <div className="relative z-10 flex flex-col items-center gap-4">
                {!showLevelSelect ? (
                    <>
                        <button
                            onClick={() => onStartGame(1)}
                            className="px-8 py-4 bg-green-500 hover:bg-green-400 text-white text-2xl font-bold rounded-lg shadow-lg transform hover:scale-105 transition-all"
                            style={{ textShadow: '2px 2px 0 #166534' }}
                        >
                            ▶ START GAME
                        </button>
                        <button
                            onClick={() => setShowLevelSelect(true)}
                            className="px-6 py-3 bg-blue-500 hover:bg-blue-400 text-white text-xl font-bold rounded-lg shadow-lg transform hover:scale-105 transition-all"
                        >
                            🎯 SELECT LEVEL
                        </button>
                        <p className="text-white/80 text-sm mt-4">
                            Press ENTER to start • L for level select
                        </p>
                    </>
                ) : (
                    <div className="bg-black/50 backdrop-blur-sm rounded-xl p-8 text-center">
                        <h3 className="text-2xl text-white font-bold mb-4">SELECT LEVEL</h3>
                        <div className="flex items-center gap-4 mb-4">
                            <button
                                onClick={() => setSelectedLevel(l => Math.max(1, l - 1))}
                                className="px-4 py-2 bg-gray-600 hover:bg-gray-500 text-white text-2xl rounded"
                            >
                                ◀
                            </button>
                            <span className="text-5xl text-yellow-400 font-bold min-w-[120px]">
                                {selectedLevel}
                            </span>
                            <button
                                onClick={() => setSelectedLevel(l => Math.min(99, l + 1))}
                                className="px-4 py-2 bg-gray-600 hover:bg-gray-500 text-white text-2xl rounded"
                            >
                                ▶
                            </button>
                        </div>
                        <p className="text-white/60 text-sm mb-4">Use ←→ for ±1, ↑↓ for ±10</p>
                        <div className="flex gap-4 justify-center">
                            <button
                                onClick={() => onStartGame(selectedLevel)}
                                className="px-6 py-3 bg-green-500 hover:bg-green-400 text-white font-bold rounded-lg"
                            >
                                START LEVEL {selectedLevel}
                            </button>
                            <button
                                onClick={() => setShowLevelSelect(false)}
                                className="px-6 py-3 bg-gray-600 hover:bg-gray-500 text-white font-bold rounded-lg"
                            >
                                BACK
                            </button>
                        </div>
                    </div>
                )}
            </div>

            {/* Footer */}
            <div className="absolute bottom-4 text-white/60 text-sm">
                Original Game © Digital Chocolate • Web Rebuild 2026
            </div>
        </div>
    );
}
