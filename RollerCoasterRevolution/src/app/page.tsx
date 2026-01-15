'use client';

import dynamic from 'next/dynamic';

const GameCanvas = dynamic(() => import('@/components/game/GameCanvas'), {
    ssr: false,
    loading: () => <p className="text-xl">Initializing Engine...</p>
});

export default function Home() {
    return (
        <main className="flex min-h-screen flex-col items-center justify-center p-24 bg-zinc-900">
            <div className="z-10 max-w-5xl w-full flex-col items-center justify-between font-mono text-sm lg:flex">
                <h1 className="text-4xl font-pixel text-nokia-green mb-8">Roller Coaster Revolution</h1>
                <div className="bg-zinc-800 p-1 rounded-lg border-4 border-nokia-green w-full aspect-video flex items-center justify-center overflow-hidden relative">
                    <GameCanvas />
                </div>
            </div>
        </main>
    )
}
