package com.digitalchocolate.androidrollergapp;

import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class SliderButton extends Button {
    private int mTargetSize;
    private int mTargetX;
    private int mTargetY;

    public SliderButton(int i, int i2, int i3, int i4, int i5, int i6, ImageFont imageFont, int i7, int i8, int i9) {
        super(i, i2, i3, i4, i5, i6, imageFont, i7, i8, i9);
        this.mType = 1;
        this.mTargetSize = this.mHeight + 4;
        this.mTargetX = (this.mX + ((this.mValue * this.mWidth) / 100)) - (this.mTargetSize >> 1);
        this.mTargetY = this.mY + (this.mHeight >> 1);
    }

    @Override // com.digitalchocolate.androidrollergapp.Button
    public void draw(Graphics graphics) {
        graphics.setClip(this.mX - this.mTargetSize, this.mY - this.mTargetSize, this.mWidth + (this.mTargetSize << 1), this.mHeight + (this.mTargetSize << 1));
        graphics.setColor(0);
        graphics.fillRect(this.mX + 1, this.mY + 1, this.mWidth, this.mHeight);
        graphics.setColor(4473924);
        graphics.fillRect(this.mX, this.mY, this.mWidth, this.mHeight);
        graphics.setColor(16711680);
        graphics.fillRect(this.mX, this.mY, (this.mTargetX - this.mX) + (this.mTargetSize >> 1), this.mHeight);
        graphics.setColor(0);
        graphics.fillArc(this.mTargetX + 1, this.mTargetY + 1, this.mTargetSize, this.mTargetSize, 0, Util.SIN_SAMPLES_DEFAULT);
        graphics.setColor(16448250);
        graphics.fillArc(this.mTargetX, this.mTargetY, this.mTargetSize, this.mTargetSize, 0, Util.SIN_SAMPLES_DEFAULT);
        if (this.mText != null) {
            graphics.setClip(0, 0, 320, CustomMenuObject.PC_PITCH_CONTENT_HEIGHT);
            ScrollingTextField.draw(graphics, this.mText, this.mFont, this.mX + 2, this.mY - this.mHeight, this.mWidth - 2, this.mHeight);
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.Button
    public int pointerEventOccurred(int i, int i2, int i3) {
        if (i3 == 0 && i > this.mTargetX - (this.mTargetSize >> 1) && i < this.mTargetX + this.mTargetSize && i2 > this.mTargetY - this.mHeight && i2 < this.mTargetY + this.mHeight) {
            this.mNextState = 1;
        } else if (i3 == 2 && this.mState == 1) {
            this.mValue = ((i - this.mX) * 100) / this.mWidth;
            if (this.mValue < 0) {
                this.mValue = 0;
            } else if (this.mValue > 100) {
                this.mValue = 100;
            }
            this.mTargetX = (this.mX + ((this.mValue * this.mWidth) / 100)) - (this.mTargetSize >> 1);
        }
        if (i3 != 1) {
            return -1;
        }
        this.mNextState = 0;
        return -1;
    }
}
