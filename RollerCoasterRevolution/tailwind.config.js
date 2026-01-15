/** @type {import('tailwindcss').Config} */
module.exports = {
    content: [
        './src/pages/**/*.{js,ts,jsx,tsx,mdx}',
        './src/components/**/*.{js,ts,jsx,tsx,mdx}',
        './src/app/**/*.{js,ts,jsx,tsx,mdx}',
    ],
    theme: {
        extend: {
            colors: {
                'nokia-green': '#C7F0D8',
                'nokia-dark': '#43523d',
            },
            fontFamily: {
                pixel: ['var(--font-pixel)'],
            }
        },
    },
    plugins: [],
}
