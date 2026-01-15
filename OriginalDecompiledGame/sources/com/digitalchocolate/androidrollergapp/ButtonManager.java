package com.digitalchocolate.androidrollergapp;

import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class ButtonManager {
    public static final int LAYER_ALL = -1;
    public static final int LAYER_GAME = 1;
    public static final int LAYER_LOADING_BAR = 0;
    public static final int LAYER_MENU = 3;
    public static final int LAYER_TEXT_BOX = 2;
    private static final int POOL_SIZE = 30;
    public static final int TYPE_PUSH_BUTTON = 0;
    public static final int TYPE_SLIDER = 1;
    private static int mButtonCount;
    private static Button[] mButtonPool;
    private static int mFirst;
    private static int mLast;

    public static int addButton(int i, int i2, int i3, int i4, int i5, int i6, ImageFont imageFont, int i7, int i8) {
        return addButton(i, i2, i3, i4, i5, i6, imageFont, i7, -1, i8);
    }

    public static int addButton(int i, int i2, int i3, int i4, int i5, int i6, ImageFont imageFont, int i7, int i8, int i9) {
        int iFirstEmpty = firstEmpty();
        if (iFirstEmpty > -1) {
            switch (i) {
                case 0:
                    mButtonPool[iFirstEmpty] = new PushButton(iFirstEmpty, i2, i3, i4, i5, i6, imageFont, i7, i8, i9);
                    break;
                case 1:
                    mButtonPool[iFirstEmpty] = new SliderButton(iFirstEmpty, i2, i3, i4, i5, i6, imageFont, i7, i8, i9);
                    break;
            }
            mButtonCount++;
            if (mFirst == -1) {
                mFirst = iFirstEmpty;
            }
            if (mLast != -1) {
                mButtonPool[mLast].mNext = iFirstEmpty;
                mButtonPool[iFirstEmpty].mPrevious = mLast;
            }
            mLast = iFirstEmpty;
        }
        return iFirstEmpty;
    }

    public static int addButton(int i, int i2, int i3, int i4, int i5, ImageFont imageFont, int i6, int i7) {
        return addButton(i, i2, i3, 0, 0, i5, imageFont, i6, i4, i7);
    }

    public static void createPool() {
        mButtonPool = new Button[30];
        mLast = -1;
        mFirst = -1;
        mButtonCount = 0;
    }

    public static void destroyButton(int i) {
        if (i >= 30 || mButtonPool[i] == null) {
            return;
        }
        int i2 = mButtonPool[i].mPrevious;
        int i3 = mButtonPool[i].mNext;
        if (i2 != -1) {
            mButtonPool[i2].mNext = i3;
        } else {
            mFirst = i3;
        }
        if (i3 != -1) {
            mButtonPool[i3].mPrevious = i2;
        } else {
            mLast = i2;
        }
        mButtonPool[i].destroy();
        mButtonPool[i] = null;
        mButtonCount--;
    }

    public static void destroyPool() {
        for (int i = 0; i < 30; i++) {
            if (mButtonPool[i] != null) {
                mButtonPool[i].destroy();
                mButtonPool[i] = null;
            }
        }
        mLast = -1;
        mFirst = -1;
        mButtonCount = 0;
    }

    public static void drawPool(Graphics graphics, int i) {
        graphics.setClip(0, 0, Toolkit.getScreenWidth(), Toolkit.getScreenHeight());
        for (int i2 = mFirst; i2 != -1; i2 = mButtonPool[i2].mNext) {
            if (mButtonPool[i2].mIsVisible && (i == -1 || mButtonPool[i2].mLayer == i)) {
                mButtonPool[i2].draw(graphics);
            }
        }
    }

    private static int firstEmpty() {
        if (mButtonCount < 30) {
            for (int i = 0; i < 30; i++) {
                if (mButtonPool[i] == null) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void hideButton(int i) {
        if (mButtonPool[i] != null) {
            mButtonPool[i].mIsVisible = false;
        }
    }

    public static int pointerEventOccurred(int i, int i2, int i3, int i4) {
        int iPointerEventOccurred = -1;
        for (int i5 = mFirst; i5 != -1; i5 = mButtonPool[i5].mNext) {
            if (mButtonPool[i5].mIsVisible && ((i4 == -1 || mButtonPool[i5].mLayer == i4) && (iPointerEventOccurred = mButtonPool[i5].pointerEventOccurred(i, i2, i3)) != -1 && iPointerEventOccurred != -2)) {
                return iPointerEventOccurred;
            }
        }
        return iPointerEventOccurred;
    }

    public static void showButton(int i) {
        if (mButtonPool[i] != null) {
            mButtonPool[i].mIsVisible = true;
        }
    }

    public static void updatePool(int i, int i2) {
        for (int i3 = mFirst; i3 != -1; i3 = mButtonPool[i3].mNext) {
            if (mButtonPool[i3].mIsVisible && (i2 == -1 || mButtonPool[i3].mLayer == i2)) {
                mButtonPool[i3].logicUpdate(i);
            }
        }
    }
}
