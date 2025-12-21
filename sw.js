// Service Worker for Rollercoaster Rush PWA
const CACHE_NAME = 'rollercoaster-rush-v1';
const ASSETS = [
    '/',
    '/index.html',
    '/css/styles.css',
    '/js/engine/physics.js',
    '/js/engine/renderer.js',
    '/js/engine/input.js',
    '/js/game/track.js',
    '/js/game/coaster.js',
    '/js/game/passenger.js',
    '/js/game/coin.js',
    '/js/data/tracks.js',
    '/js/states/menu.js',
    '/js/states/playing.js',
    '/js/states/results.js',
    '/js/main.js'
];

self.addEventListener('install', (event) => {
    event.waitUntil(
        caches.open(CACHE_NAME).then((cache) => cache.addAll(ASSETS))
    );
});

self.addEventListener('fetch', (event) => {
    event.respondWith(
        caches.match(event.request).then((response) => response || fetch(event.request))
    );
});
