package com.digitalchocolate.androidrollergapp;

import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public abstract class Button implements ButtonActions {
    protected static final int STATE_IDLE = 0;
    protected static final int STATE_PRESSED = 1;
    protected int mAction;
    protected int mAreaX;
    protected int mAreaY;
    protected SpriteObject mBackgroundImage;
    protected ImageFont mFont;
    public int mHeight;
    protected SpriteObject mIcon;
    public int mIndex;
    public boolean mIsVisible;
    public int mLayer;
    public int mNext;
    protected int mNextState;
    public int mPrevious;
    protected int mState;
    protected String mText;
    protected int mTextAreaHeight;
    protected int mTextAreaWidth;
    protected int mTextAreaX;
    protected int mTextAreaY;
    public int mType;
    public int mValue;
    public int mWidth;
    public int mX;
    public int mY;

    protected Button(int i, int i2, int i3, int i4, int i5, int i6, ImageFont imageFont, int i7, int i8, int i9) {
        this.mBackgroundImage = null;
        this.mTextAreaWidth = 0;
        this.mTextAreaHeight = 0;
        if (i8 != -1) {
            this.mBackgroundImage = new SpriteObject(DavinciUtilities.loadAnimation(i8, true), false);
            if (this.mBackgroundImage.getHeight() > 42) {
                this.mBackgroundImage.getHeight();
            }
            if (this.mBackgroundImage.getWidth() > i4) {
                this.mBackgroundImage.getWidth();
            }
            CollisionBox collisionBox = this.mBackgroundImage.getCollisionBox(0);
            if (collisionBox != null) {
                this.mTextAreaX = collisionBox.getX();
                this.mTextAreaY = collisionBox.getY();
                this.mTextAreaWidth = collisionBox.getWidth();
                this.mTextAreaHeight = collisionBox.getHeight();
            }
        }
        this.mIndex = i;
        this.mIsVisible = true;
        this.mX = i2;
        this.mY = i3;
        this.mWidth = i4;
        this.mHeight = i5;
        if (this.mY - (i5 >> 1) < 0) {
            this.mY = i5 >> 1;
        } else if (this.mY + (i5 >> 1) > Toolkit.getScreenHeight()) {
            this.mY = Toolkit.getScreenHeight() - (i5 >> 1);
        }
        if (this.mX - (i4 >> 1) < 0) {
            this.mX = i4 >> 1;
        } else if (this.mX + (i4 >> 1) > Toolkit.getScreenWidth()) {
            this.mX = Toolkit.getScreenWidth() - (i4 >> 1);
        }
        this.mAreaX = this.mX - (this.mWidth >> 1);
        this.mAreaY = this.mY - (this.mHeight >> 1);
        this.mLayer = i6;
        this.mValue = 0;
        this.mFont = imageFont;
        this.mText = Toolkit.getText(i7);
        if (this.mText != null) {
            if (this.mTextAreaWidth == 0) {
                this.mTextAreaX = this.mAreaX;
                this.mTextAreaY = this.mAreaY;
                this.mTextAreaWidth = this.mWidth;
                this.mTextAreaHeight = this.mHeight;
            } else {
                this.mTextAreaX += this.mX;
                this.mTextAreaY += this.mY;
            }
        }
        this.mAction = i9;
        this.mPrevious = -1;
        this.mNext = -1;
    }

    public void destroy() {
        if (this.mBackgroundImage != null) {
            this.mBackgroundImage.freeResources();
        }
    }

    public abstract void draw(Graphics graphics);

    public int getValue() {
        return this.mValue;
    }

    public void logicUpdate(int i) {
        if (this.mNextState != this.mState) {
            this.mState = this.mNextState;
        }
    }

    public abstract int pointerEventOccurred(int i, int i2, int i3);
}
