import type { Metadata } from 'next'
import { Inter, Press_Start_2P } from 'next/font/google'
import './globals.css'

const inter = Inter({ subsets: ['latin'] })
const pixel = Press_Start_2P({
    weight: '400',
    subsets: ['latin'],
    variable: '--font-pixel'
})

export const metadata: Metadata = {
    title: 'Roller Coaster Revolution',
    description: 'A modern rebuild of the classic Java game',
}

export default function RootLayout({
    children,
}: {
    children: React.ReactNode
}) {
    return (
        <html lang="en">
            <body className={`${inter.className} ${pixel.variable} bg-zinc-900 text-white`}>{children}</body>
        </html>
    )
}
