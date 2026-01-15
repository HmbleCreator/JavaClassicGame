package com.digitalchocolate.androidrollergapp;

import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class PushButton extends Button {
    private int mScaleX;
    private int mScaleY;
    private int mTimer;

    public PushButton(int i, int i2, int i3, int i4, int i5, int i6, ImageFont imageFont, int i7, int i8, int i9) {
        super(i, i2, i3, i4, i5, i6, imageFont, i7, i8, i9);
        this.mType = 0;
        this.mTimer = 0;
        this.mScaleX = 1024;
        this.mScaleY = 1024;
    }

    @Override // com.digitalchocolate.androidrollergapp.Button
    public void draw(Graphics graphics) {
        graphics.setClip(0, 0, Toolkit.getScreenWidth(), Toolkit.getScreenHeight());
        if (this.mBackgroundImage != null) {
            graphics.setColor(16514043);
            if (this.mState == 1) {
                this.mBackgroundImage.draw(graphics, this.mX, this.mY);
            } else {
                this.mBackgroundImage.draw(graphics, this.mX, this.mY);
            }
        }
        if (this.mText != null) {
            ScrollingTextField.draw(graphics, this.mText, this.mFont, this.mTextAreaX, this.mTextAreaY, this.mTextAreaWidth, this.mTextAreaHeight);
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.Button
    public void logicUpdate(int i) {
        super.logicUpdate(i);
        if (this.mTimer > 0) {
            this.mTimer -= i;
            this.mScaleX = 1024;
            this.mScaleY = 1024;
            if (this.mTimer > 500) {
                int i2 = ((600 - this.mTimer) * 128) / 100;
                this.mScaleY = 1024 - i2;
                this.mScaleX = (i2 * 2) + this.mScaleX;
                return;
            }
            if (this.mTimer > 400) {
                int i3 = ((this.mTimer - 400) * 128) / 100;
                this.mScaleY = 1024 - i3;
                this.mScaleX = (i3 * 2) + this.mScaleX;
                return;
            }
            if (this.mTimer > 300) {
                int i4 = ((400 - this.mTimer) * 64) / 100;
                this.mScaleY = 1024 - i4;
                this.mScaleX = (i4 * 2) + this.mScaleX;
                return;
            }
            if (this.mTimer > 200) {
                int i5 = ((this.mTimer - 200) * 64) / 100;
                this.mScaleY = 1024 - i5;
                this.mScaleX = (i5 * 2) + this.mScaleX;
                return;
            }
            if (this.mTimer > 100) {
                int i6 = ((200 - this.mTimer) * 32) / 100;
                this.mScaleY = 1024 - i6;
                this.mScaleX = (i6 * 2) + this.mScaleX;
                return;
            }
            int i7 = (this.mTimer * 32) / 100;
            this.mScaleY = 1024 - i7;
            this.mScaleX = (i7 * 2) + this.mScaleX;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.Button
    public int pointerEventOccurred(int i, int i2, int i3) {
        int i4 = this.mX - (this.mWidth >> 1);
        int i5 = this.mY - (this.mHeight >> 1);
        if (i <= i4 || i >= i4 + this.mWidth || i2 <= i5 || i2 >= this.mHeight + i5) {
            if (i3 == 2) {
                this.mNextState = 0;
                if (this.mBackgroundImage != null) {
                    this.mBackgroundImage.setAnimationFrame(0);
                }
                return -2;
            }
        } else {
            if (i3 == 0) {
                this.mNextState = 1;
                if (this.mBackgroundImage != null) {
                    this.mBackgroundImage.setAnimationFrame(1);
                }
                this.mTimer = 600;
                return -2;
            }
            if (i3 == 1 && this.mNextState == 1) {
                this.mNextState = 0;
                if (this.mBackgroundImage != null) {
                    this.mBackgroundImage.setAnimationFrame(0);
                }
                return this.mAction;
            }
        }
        return -1;
    }
}
