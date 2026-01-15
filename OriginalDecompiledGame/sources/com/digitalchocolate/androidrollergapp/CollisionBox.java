package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public class CollisionBox {
    private int mHeight;
    private int mId;
    private int mWidth;
    private int mX;
    private int mY;

    public CollisionBox(int i, int i2, int i3, int i4, int i5) {
        this.mX = i;
        this.mY = i2;
        this.mWidth = i3;
        this.mHeight = i4;
        this.mId = i5;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getId() {
        return this.mId;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getX() {
        return this.mX;
    }

    public int getY() {
        return this.mY;
    }
}
