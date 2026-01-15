package com.digitalchocolate.androidrollergapp;

import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class ScrollingTextField {
    private static final int HORIZONTAL_SCROLLING_DELAY = 40;
    private static final int HORIZONTAL_SCROLLING_TURNOVER_DELAY = 2000;
    private static int smScrollTimer;

    public static void draw(Graphics graphics, String str, ImageFont imageFont, int i, int i2, int i3, int i4) {
        int iStringWidth = imageFont.stringWidth(str);
        if (iStringWidth <= i3) {
            imageFont.drawString(graphics, str, i + (i3 >> 1), i2 + ((i4 - imageFont.getHeight()) >> 1), 17);
            return;
        }
        int clipX = graphics.getClipX();
        int clipY = graphics.getClipY();
        int clipWidth = graphics.getClipWidth();
        int clipHeight = graphics.getClipHeight();
        graphics.setClip(i, i2, i3, Toolkit.getScreenHeight());
        int i5 = iStringWidth - i3;
        int i6 = (i5 * 40) + 2000;
        int i7 = i6 + 2000;
        int i8 = smScrollTimer % ((i5 * 40) + i7);
        imageFont.drawString(graphics, str, i + (i8 < 2000 ? 0 : i8 < i6 ? (-(i8 - 2000)) / 40 : i8 < i7 ? -i5 : (-i5) + ((i8 - i7) / 40)), i2 + ((i4 - imageFont.getHeight()) >> 1), 20);
        graphics.setClip(clipX, clipY, clipWidth, clipHeight);
    }

    public static void logicUpdate(int i) {
        smScrollTimer += i;
    }

    public static void resetTimer() {
        smScrollTimer = 0;
    }
}
