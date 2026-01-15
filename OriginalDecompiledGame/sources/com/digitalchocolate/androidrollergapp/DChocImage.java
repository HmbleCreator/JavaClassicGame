package com.digitalchocolate.androidrollergapp;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* loaded from: classes.dex */
public class DChocImage {
    public static final byte COLOR_DEPTH_4444 = 2;
    public static final byte COLOR_DEPTH_5551 = 1;
    public static final byte COLOR_DEPTH_565 = 3;
    public static final byte COLOR_DEPTH_8888 = 0;
    public static final byte COLOR_DEPTH_DEFAULT = -1;
    private static Graphics smGetRGBGraphics;
    private static Image smGetRGBImage;
    private int[][] mBuffer;
    private Image mDelegate;
    private int[] mEffectParam;
    private Graphics mGraphics;
    private int mRegionHeight;
    private int mRegionWidth;
    private int mRegionX;
    private int mRegionY;

    public DChocImage(int i) {
        this(Toolkit.getImage(i));
    }

    public DChocImage(int i, byte b) {
        this(Toolkit.getImage(i));
    }

    public DChocImage(int i, int i2) {
        this.mDelegate = Image.createImage(i, i2);
        this.mGraphics = this.mDelegate.getGraphics();
    }

    public DChocImage(DChocImage dChocImage) {
        this.mDelegate = dChocImage.getImage();
        if (dChocImage.isMutable()) {
            this.mGraphics = dChocImage.getGraphics();
        }
        setRegion(dChocImage.getRegionX(), dChocImage.getRegionY(), dChocImage.getRegionWidth(), dChocImage.getRegionHeight());
    }

    public DChocImage(Image image) {
        this.mDelegate = image;
        if (image.isMutable()) {
            this.mGraphics = image.getGraphics();
        }
    }

    public void clearRegion() {
        setRegion(0, 0, 0, 0);
    }

    public void copyEffectParam(int[] iArr) {
        if (iArr == null) {
            this.mEffectParam = null;
            return;
        }
        int length = iArr.length;
        if (this.mEffectParam == null) {
            this.mEffectParam = new int[length];
        }
        while (true) {
            length--;
            if (length < 0) {
                return;
            } else {
                this.mEffectParam[length] = iArr[length];
            }
        }
    }

    public int[][] getBuffer() {
        return this.mBuffer;
    }

    public int[] getEffectParams() {
        return this.mEffectParam;
    }

    public Graphics getGraphics() {
        return this.mGraphics;
    }

    public int getHeight() {
        return this.mRegionHeight != 0 ? this.mRegionHeight : this.mDelegate.getHeight();
    }

    public Image getImage() {
        return this.mDelegate;
    }

    public void getRGB(int[] iArr, int i, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int i8;
        if (isRegioned()) {
            int i9 = this.mRegionX + i3;
            i7 = this.mRegionY + i4;
            i8 = i9;
        } else {
            i7 = i4;
            i8 = i3;
        }
        this.mDelegate.getRGB(iArr, i, i2, i8, i7, i5, i6);
    }

    public int getRegionHeight() {
        return this.mRegionHeight;
    }

    public int getRegionWidth() {
        return this.mRegionWidth;
    }

    public int getRegionX() {
        return this.mRegionX;
    }

    public int getRegionY() {
        return this.mRegionY;
    }

    public int getWidth() {
        return this.mRegionWidth != 0 ? this.mRegionWidth : this.mDelegate.getWidth();
    }

    public boolean isLoadingCompleted() {
        return true;
    }

    public boolean isMutable() {
        return this.mDelegate.isMutable();
    }

    public boolean isRegioned() {
        return (this.mRegionWidth == 0 || this.mRegionHeight == 0) ? false : true;
    }

    public void setBuffer(int[][] iArr) {
        this.mBuffer = iArr;
    }

    public void setRegion(int i, int i2, int i3, int i4) {
        this.mRegionX = i;
        this.mRegionY = i2;
        this.mRegionWidth = i3;
        this.mRegionHeight = i4;
        if (isMutable()) {
            if (isRegioned()) {
                this.mGraphics.setClip(i, i2, i3, i4);
            } else {
                this.mGraphics.setClip(0, 0, this.mDelegate.getWidth(), this.mDelegate.getHeight());
            }
        }
    }
}
