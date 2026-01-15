package com.digitalchocolate.androidrollergapp;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* loaded from: classes.dex */
public class ImageFont {
    public static final int BORDER_DISABLED = -1;
    private static final int CHARACTER_NOT_FOUND = -1;
    private static final int SYSTEM_FONT_BORDER_WIDTH = 1;
    private byte[] mCharacterAscents;
    private byte[] mCharacterHeights;
    private int mCharacterSpacing;
    private byte[] mCharacterWidths;
    private byte[] mCharacterXs;
    private short[] mCharacterYs;
    private boolean mEnableSystemBorders;
    private int mFontAscent;
    private int mFontDescent;
    private Image mFontImage;
    private int[] mHashTableEntries;
    private Font mSystemFont;
    private int mSystemFontBorderColor;
    private int mSystemFontColor;

    public ImageFont(Image image, DataInputStream dataInputStream, Font font, int i, int i2) throws IOException {
        if (image == null) {
            this.mSystemFont = font;
            this.mSystemFontColor = i;
            this.mSystemFontBorderColor = i2;
            this.mEnableSystemBorders = i2 != -1;
            return;
        }
        this.mFontImage = image;
        int i3 = dataInputStream.readShort();
        this.mCharacterSpacing = dataInputStream.readByte() * 1;
        this.mFontAscent = dataInputStream.readByte() * 1;
        this.mFontDescent = dataInputStream.readByte() * 1;
        int i4 = 1;
        while (i4 < (i3 * 4) / 3) {
            i4 <<= 1;
        }
        hashTableCreate(i4);
        this.mCharacterXs = new byte[i3];
        this.mCharacterYs = new short[i3];
        this.mCharacterWidths = new byte[i3];
        this.mCharacterHeights = new byte[i3];
        this.mCharacterAscents = new byte[i3];
        while (i3 > 0) {
            int i5 = dataInputStream.read();
            int i6 = i3;
            char c = dataInputStream.readChar();
            while (true) {
                int i7 = i5 - 1;
                if (i5 > 0) {
                    int i8 = i6 - 1;
                    this.mCharacterXs[i8] = (byte) (dataInputStream.readByte() * 1);
                    this.mCharacterYs[i8] = (short) (dataInputStream.readShort() * 1);
                    this.mCharacterWidths[i8] = (byte) (dataInputStream.readByte() * 1);
                    this.mCharacterHeights[i8] = (byte) (dataInputStream.readByte() * 1);
                    this.mCharacterAscents[i8] = (byte) (dataInputStream.readByte() * 1);
                    hashTablePut(c, (short) i8);
                    c = (char) (c + 1);
                    i6 = i8;
                    i5 = i7;
                }
            }
            i3 = i6;
        }
    }

    private int getCharIndex(char c) {
        int iHashTableGet = hashTableGet(c);
        return iHashTableGet == -1 ? hashTableGet(' ') : iHashTableGet;
    }

    private void hashTableCreate(int i) {
        this.mHashTableEntries = new int[i];
    }

    private int hashTableGet(char c) {
        int i;
        int iHashTableHashCode = hashTableHashCode(c);
        for (int i2 = 0; i2 < this.mHashTableEntries.length && (i = this.mHashTableEntries[(this.mHashTableEntries.length - 1) & iHashTableHashCode]) != 0; i2++) {
            if ((i >>> 16) == c) {
                return 65535 & i;
            }
            iHashTableHashCode++;
        }
        return -1;
    }

    private int hashTableHashCode(char c) {
        return (c + 4660) * (c + 17185);
    }

    private void hashTablePut(char c, short s) {
        int iHashTableHashCode = hashTableHashCode(c);
        for (int i = 0; i < this.mHashTableEntries.length; i++) {
            if (this.mHashTableEntries[(this.mHashTableEntries.length - 1) & iHashTableHashCode] == 0) {
                this.mHashTableEntries[iHashTableHashCode & (this.mHashTableEntries.length - 1)] = (c << 16) | (65535 & s);
                return;
            }
            iHashTableHashCode++;
        }
    }

    public int charWidth(char c) {
        if (this.mFontImage == null) {
            return this.mSystemFont.charWidth(c);
        }
        return this.mCharacterWidths[getCharIndex(c)] + this.mCharacterSpacing;
    }

    public void drawChar(Graphics graphics, char c, int i, int i2, int i3) {
        int i4;
        int i5;
        if (this.mFontImage != null) {
            int iCharWidth = (i3 & 1) != 0 ? i - (charWidth(c) >> 1) : (i3 & 8) != 0 ? i - charWidth(c) : i;
            int i6 = (i3 & 16) != 0 ? this.mFontAscent + i2 : (i3 & 32) != 0 ? i2 - this.mFontDescent : i2;
            if ((i3 & 2) != 0) {
                return;
            }
            int clipX = graphics.getClipX();
            int clipY = graphics.getClipY();
            int clipWidth = graphics.getClipWidth();
            int clipHeight = graphics.getClipHeight();
            int charIndex = getCharIndex(c);
            byte b = this.mCharacterWidths[charIndex];
            if (c != ' ') {
                byte b2 = this.mCharacterAscents[charIndex];
                byte b3 = this.mCharacterHeights[charIndex];
                int iMax = Math.max(clipX, iCharWidth);
                int iMax2 = Math.max(clipY, i6 - b2);
                graphics.setClip(iMax, iMax2, Math.max(iMax, Math.min(clipX + clipWidth, b + iCharWidth)) - iMax, Math.max(iMax2, Math.min(clipY + clipHeight, b3 + (i6 - b2))) - iMax2);
                graphics.drawImage(this.mFontImage, iCharWidth - (this.mCharacterXs[charIndex] & DChocImage.COLOR_DEPTH_DEFAULT), (i6 - this.mCharacterYs[charIndex]) - b2, 20);
            }
            graphics.setClip(clipX, clipY, clipWidth, clipHeight);
            return;
        }
        graphics.setFont(this.mSystemFont);
        int color = graphics.getColor();
        if (this.mEnableSystemBorders) {
            int i7 = (i3 & 4) != 0 ? i + 1 : (i3 & 8) != 0 ? i - 1 : i;
            int i8 = (i3 & 16) != 0 ? i2 + 1 : (i3 & 32) != 0 ? i2 - 1 : i2;
            graphics.setColor(this.mSystemFontBorderColor);
            graphics.drawChar(c, i7, i8 - 1, i3);
            graphics.drawChar(c, i7 - 1, i8, i3);
            graphics.drawChar(c, i7 + 1, i8, i3);
            graphics.drawChar(c, i7, i8 + 1, i3);
            int i9 = i8;
            i5 = i7;
            i4 = i9;
        } else {
            i4 = i2;
            i5 = i;
        }
        graphics.setColor(this.mSystemFontColor);
        graphics.drawChar(c, i5, i4, i3);
        graphics.setColor(color);
    }

    public void drawString(Graphics graphics, String str, int i, int i2, int i3) {
        int i4;
        int i5;
        if (this.mFontImage == null) {
            graphics.setFont(this.mSystemFont);
            int color = graphics.getColor();
            if (this.mEnableSystemBorders) {
                int i6 = (i3 & 4) != 0 ? i + 1 : (i3 & 8) != 0 ? i - 1 : i;
                int i7 = (i3 & 16) != 0 ? i2 + 1 : (i3 & 32) != 0 ? i2 - 1 : i2;
                graphics.setColor(this.mSystemFontBorderColor);
                graphics.drawString(str, i6, i7 - 1, i3);
                graphics.drawString(str, i6 - 1, i7, i3);
                graphics.drawString(str, i6 + 1, i7, i3);
                graphics.drawString(str, i6, i7 + 1, i3);
                int i8 = i7;
                i5 = i6;
                i4 = i8;
            } else {
                i4 = i2;
                i5 = i;
            }
            graphics.setColor(this.mSystemFontColor);
            graphics.drawString(str, i5, i4, i3);
            graphics.setColor(color);
            return;
        }
        int iStringWidth = (i3 & 1) != 0 ? i - (stringWidth(str) >> 1) : (i3 & 8) != 0 ? i - stringWidth(str) : i;
        int i9 = (i3 & 16) != 0 ? this.mFontAscent + i2 : (i3 & 32) != 0 ? i2 - this.mFontDescent : i2;
        if ((i3 & 2) != 0) {
            return;
        }
        int clipX = graphics.getClipX();
        int clipY = graphics.getClipY();
        int clipWidth = graphics.getClipWidth();
        int clipHeight = graphics.getClipHeight();
        int i10 = iStringWidth;
        for (int i11 = 0; i11 < str.length(); i11++) {
            char cCharAt = str.charAt(i11);
            int charIndex = getCharIndex(cCharAt);
            byte b = this.mCharacterWidths[charIndex];
            if (cCharAt != ' ') {
                byte b2 = this.mCharacterAscents[charIndex];
                byte b3 = this.mCharacterHeights[charIndex];
                int iMax = Math.max(clipX, i10);
                int iMax2 = Math.max(clipY, i9 - b2);
                graphics.setClip(iMax, iMax2, Math.max(iMax, Math.min(clipX + clipWidth, i10 + b)) - iMax, Math.max(iMax2, Math.min(clipY + clipHeight, b3 + (i9 - b2))) - iMax2);
                graphics.drawImage(this.mFontImage, i10 - (this.mCharacterXs[charIndex] & DChocImage.COLOR_DEPTH_DEFAULT), (i9 - this.mCharacterYs[charIndex]) - b2, 20);
            }
            i10 += this.mCharacterSpacing + b;
        }
        graphics.setClip(clipX, clipY, clipWidth, clipHeight);
    }

    public int getBaselinePosition() {
        return this.mFontImage != null ? this.mFontAscent : this.mSystemFont.getBaselinePosition();
    }

    public int getEmptyStringWidth() {
        return (this.mFontImage == null && this.mEnableSystemBorders) ? 2 : 0;
    }

    public final int getHeight() {
        if (this.mFontImage != null) {
            return this.mFontAscent + this.mFontDescent;
        }
        return this.mSystemFont.getHeight() + (this.mEnableSystemBorders ? 2 : 0);
    }

    public final int stringWidth(String str) {
        if (this.mFontImage == null) {
            return this.mSystemFont.stringWidth(str) + (this.mEnableSystemBorders ? 2 : 0);
        }
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            i += this.mCharacterWidths[getCharIndex(str.charAt(i2))] + this.mCharacterSpacing;
        }
        return this.mCharacterSpacing < 0 ? i - this.mCharacterSpacing : i;
    }
}
