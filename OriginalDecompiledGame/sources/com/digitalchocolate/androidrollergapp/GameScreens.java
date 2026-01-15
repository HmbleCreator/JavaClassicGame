package com.digitalchocolate.androidrollergapp;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class GameScreens {
    public static final int BOX_BORDER_SIZE = 6;
    public static final int EVENT_COMPLETED = 2;
    public static final int EVENT_NONE = 0;
    public static final int EVENT_PAUSED = 1;
    public static final int EVENT_SAVE_GAME = -3;
    protected static final int GS_NONE = -1;
    public static final int KEY_ACTION_MASK = 16;
    public static final int KEY_DOWN_MASK = 8;
    public static final int KEY_LEFT_MASK = 1;
    public static final int KEY_RIGHT_MASK = 2;
    public static final int KEY_UP_MASK = 4;
    private static final int MAX_UPDATE_TIME = 333;
    private static final int MIN_UPDATE_TIME = 16;
    public static final String NUMBERS_MAX_WIDTH = "00/00";
    public static final String SPEED_MAX_WIDTH = "000";
    public static final boolean USE_BACKGROUND_FRAME = false;
    private static final int WIDTH_REFERENCE = 240;
    protected static SpriteObject smBackgroundFrame;
    protected CustomMenuObject mBackground;
    protected int mDrawAreaHeight;
    protected int mDrawAreaY;
    protected int mGameState;
    public int mKeys;
    protected int mNextGameState;
    protected int[] mPoolInt;
    protected SpriteObject[] mPoolSprite;
    protected String[][] mPoolText;
    protected int mSeparatorX;
    protected int mSeparatorY;
    protected CustomMenuObject mTextBox;
    protected int mTitleBarHeight;
    protected int mTitleTextId;
    protected boolean mUpdateSoftKeys;
    protected int mUpdateTimer;
    public static final String NUMBERS_WIDTH_ONE_DIGIT = "0";
    private static String[] smNumbersDigitString = {NUMBERS_WIDTH_ONE_DIGIT, "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    protected int mPressedSK = -1;
    protected int mCanvasWidth = Toolkit.getScreenWidth();
    protected int mCanvasHeight = Toolkit.getScreenHeight();
    protected int mSoftKeysHeight = Toolkit.getSoftKeyAreaHeight();

    public GameScreens() {
        this.mSeparatorY = (this.mCanvasHeight * 5) / 320;
        this.mSeparatorX = (this.mCanvasWidth * 5) / 240;
        if (this.mSeparatorY == 0) {
            this.mSeparatorY = 1;
        }
        if (this.mSeparatorX == 0) {
            this.mSeparatorX = 1;
        }
        this.mDrawAreaHeight = (this.mCanvasHeight - this.mSoftKeysHeight) - this.mSeparatorY;
        this.mDrawAreaY = this.mSeparatorY;
    }

    public static void drawTimer(Graphics graphics, ImageFont imageFont, int i, int i2, int i3) {
        if (i > 599990) {
            i = 599990;
        }
        int i4 = i / 1000;
        int iStringWidth = imageFont.stringWidth(NUMBERS_MAX_WIDTH) / 4;
        int iStringWidth2 = imageFont.stringWidth(":");
        numbersDraw(graphics, imageFont, i4 / 60, 1, i2, i3, true);
        int i5 = i2 + iStringWidth;
        imageFont.drawString(graphics, ":", i5, i3, 20);
        int i6 = i5 + iStringWidth2;
        numbersDraw(graphics, imageFont, i4 % 60, 2, i6, i3, true);
        int i7 = i6 + (iStringWidth * 2);
        imageFont.drawString(graphics, ":", i7, i3, 20);
        numbersDraw(graphics, imageFont, (i - (i4 * 1000)) / 10, 2, i7 + iStringWidth2, i3, true);
    }

    public static void numbersDraw(Graphics graphics, ImageFont imageFont, int i, int i2, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int i7;
        int i8;
        boolean z2;
        int iStringWidth = i3 + (imageFont.stringWidth(NUMBERS_WIDTH_ONE_DIGIT) * i2);
        int i9 = 1;
        int i10 = 0;
        while (i10 <= i2 && i >= i9) {
            i9 *= 10;
            i10++;
        }
        if (i == 0) {
            i10 = 1;
        }
        if (z) {
            i5 = 0;
            i6 = i;
            i7 = i2;
            i8 = iStringWidth;
            z2 = false;
        } else {
            i5 = 0;
            i6 = i;
            i7 = i10;
            i8 = iStringWidth;
            z2 = false;
        }
        while (!z2) {
            int iStringWidth2 = i8 - imageFont.stringWidth(NUMBERS_WIDTH_ONE_DIGIT);
            if (i7 > i5) {
                imageFont.drawString(graphics, smNumbersDigitString[i6 % 10], iStringWidth2, i4, 20);
            } else {
                imageFont.drawString(graphics, " ", iStringWidth2, i4, 20);
            }
            int i11 = i6 / 10;
            i5++;
            if (i2 == -1) {
                i6 = i11;
                z2 = i11 == 0;
                i8 = iStringWidth2;
            } else {
                i6 = i11;
                z2 = i2 == i5;
                i8 = iStringWidth2;
            }
        }
    }

    private void updateGameState() {
        int i = this.mNextGameState;
        this.mGameState = this.mNextGameState;
        this.mNextGameState = -1;
    }

    protected void calculateCoords() {
        screenSizeChanged();
    }

    protected void changeGameState(int i) {
        this.mNextGameState = i;
    }

    public void createTextBox(String str) {
        destroyTextBox();
        this.mTextBox = new CustomMenuObject();
        this.mTextBox.setScreen(1, 1, 3);
        this.mTextBox.setStyle(4);
        this.mTextBox.setItemDvc(0, 1, str, null, 0);
        int screenHeight = Toolkit.getScreenHeight() - (Toolkit.getSoftKeyAreaHeight() << 1);
        this.mTextBox.setBounds(15, Toolkit.getScreenHeight() / 4, Toolkit.getScreenWidth() - 30, Toolkit.getScreenHeight() / 2);
        DChocMIDlet.getInstance().resetTimer();
        this.mUpdateSoftKeys = true;
    }

    public void createTextBox(String str, int i) {
        destroyTextBox();
        this.mTextBox = new CustomMenuObject();
        this.mTextBox.setScreen(1, 1, 3);
        this.mTextBox.setStyle(4);
        this.mTextBox.setItemDvc(0, 1, str, null, 0);
        this.mTextBox.setBounds(15, Toolkit.getScreenHeight() / 4, Toolkit.getScreenWidth() - 30, this.mTextBox.getPreferredHeight(Toolkit.getScreenHeight() / 4, i));
        DChocMIDlet.getInstance().resetTimer();
        this.mUpdateSoftKeys = true;
    }

    public void createTextBox(String str, int i, int i2, int i3, int i4, int i5) {
        destroyTextBox();
        this.mTextBox = new CustomMenuObject();
        this.mTextBox.setScreen(1, 1, i5);
        this.mTextBox.setStyle(4);
        this.mTextBox.setItemDvc(0, 1, str, null, 0);
        this.mTextBox.setBounds(i, i2, i3, i4);
        DChocMIDlet.getInstance().resetTimer();
    }

    public void createTextBox(String str, String str2) {
        destroyTextBox();
        this.mTextBox = new CustomMenuObject();
        this.mTextBox.setScreen(1, 1, 3);
        this.mTextBox.setTitleBar(str, null, 3);
        this.mTextBox.setStyle(5);
        this.mTextBox.setItemDvc(0, 1, str2, null, 0);
        int softKeyAreaHeight = Toolkit.getSoftKeyAreaHeight();
        int preferredHeight = this.mTextBox.getPreferredHeight(this.mCanvasWidth - (this.mSeparatorX * 4), this.mCanvasHeight - softKeyAreaHeight);
        this.mTextBox.setBounds(this.mSeparatorX * 2, ((this.mCanvasHeight - softKeyAreaHeight) - preferredHeight) / 2, Toolkit.getScreenWidth() - (this.mSeparatorX * 4), preferredHeight);
        DChocMIDlet.getInstance().resetTimer();
        this.mUpdateSoftKeys = true;
    }

    public void createTextBox(String str, String str2, int i) {
        destroyTextBox();
        this.mTextBox = new CustomMenuObject();
        this.mTextBox.setScreen(1, 3, 3);
        this.mTextBox.setStyle(4);
        SpriteObject spriteObject = new SpriteObject(DavinciUtilities.loadAnimation(i, true), false);
        this.mTextBox.setItemDvc(0, 1, str, null, 0);
        this.mTextBox.setItemDvc(1, 1, null, spriteObject, 0);
        this.mTextBox.setItemDvc(2, 1, str2, null, 0);
        int softKeyAreaHeight = Toolkit.getSoftKeyAreaHeight();
        int preferredHeight = this.mTextBox.getPreferredHeight(this.mCanvasWidth - (this.mSeparatorX * 4), this.mCanvasHeight - softKeyAreaHeight);
        this.mTextBox.setBounds(this.mSeparatorX * 2, ((this.mCanvasHeight - softKeyAreaHeight) - preferredHeight) / 2, Toolkit.getScreenWidth() - (this.mSeparatorX * 4), preferredHeight);
        DChocMIDlet.getInstance().resetTimer();
        this.mUpdateSoftKeys = true;
    }

    public void createTextBox(String[] strArr) {
        destroyTextBox();
        int length = strArr.length;
        this.mTextBox = new CustomMenuObject();
        this.mTextBox.setScreen(1, length, 3);
        this.mTextBox.setStyle(4);
        for (int i = 0; i < length; i++) {
            this.mTextBox.setItemDvc(i, 1, strArr[i], null, 0);
        }
        int screenHeight = Toolkit.getScreenHeight() - (Toolkit.getSoftKeyAreaHeight() << 1);
        this.mTextBox.setBounds(15, Toolkit.getScreenHeight() / 4, Toolkit.getScreenWidth() - 30, Toolkit.getScreenHeight() / 2);
        DChocMIDlet.getInstance().resetTimer();
        this.mUpdateSoftKeys = true;
    }

    public void destroyPool() {
        this.mPoolInt = null;
        destroyPoolSprite();
        destroyPoolText();
    }

    public void destroyPoolSprite() {
        if (this.mPoolSprite != null) {
            int length = this.mPoolSprite.length;
            for (int i = 0; i < length; i++) {
                if (this.mPoolSprite[i] != null) {
                    this.mPoolSprite[i].freeResources();
                    this.mPoolSprite[i] = null;
                }
            }
            this.mPoolSprite = null;
        }
    }

    public void destroyPoolText() {
        if (this.mPoolText != null) {
            int length = this.mPoolText.length;
            for (int i = 0; i < length; i++) {
                if (this.mPoolText[i] != null) {
                    int length2 = this.mPoolText[i].length;
                    for (int i2 = 0; i2 < length2; i2++) {
                        this.mPoolText[i][i2] = null;
                    }
                }
                this.mPoolText[i] = null;
            }
            this.mPoolText = (String[][]) null;
        }
    }

    protected void destroyTextBox() {
        if (this.mTextBox != null) {
            this.mTextBox.releaseScreen();
            this.mTextBox = null;
            this.mUpdateSoftKeys = true;
        }
    }

    public void doDraw(Graphics graphics) {
        if (this.mBackground != null) {
            this.mBackground.doDraw(graphics);
        }
    }

    public int getLoadingCount() {
        return 3;
    }

    public void keyEventOccurred(int i, int i2) {
        int i3;
        if (this.mTextBox != null) {
        }
        if (i2 != 0 && i2 != 1) {
            if (i2 == 3) {
                this.mPressedSK = i;
                return;
            }
            return;
        }
        switch (Toolkit.getToolkitGameAction(i)) {
            case 9:
                i3 = 4;
                break;
            case 10:
            case 14:
            default:
                i3 = 0;
                break;
            case 11:
                i3 = 1;
                break;
            case 12:
                i3 = 16;
                break;
            case 13:
                i3 = 2;
                break;
            case 15:
                i3 = 8;
                break;
        }
        if (i2 == 0) {
            this.mKeys = i3 | this.mKeys;
        } else {
            this.mKeys = (i3 ^ (-1)) & this.mKeys;
        }
    }

    protected void load(int i) {
    }

    protected void loadBackground() {
        this.mBackground = new CustomMenuObject();
        this.mBackground.setScreen(0, 0, 0);
        this.mBackground.setTitleBar(Toolkit.getText(this.mTitleTextId).toUpperCase(), null, 3);
        this.mBackground.setSize(this.mCanvasWidth, this.mCanvasHeight);
        this.mTitleBarHeight = this.mBackground.mTitleBarHeight;
        this.mDrawAreaY = this.mSeparatorY + this.mTitleBarHeight;
        this.mDrawAreaHeight = (this.mCanvasHeight - this.mSoftKeysHeight) - this.mDrawAreaY;
    }

    public int logicUpdate(int i) throws IOException {
        if (this.mUpdateSoftKeys) {
            this.mUpdateSoftKeys = false;
            updateSoftKeys();
        }
        if (this.mNextGameState != -1) {
            updateGameState();
            return 0;
        }
        if (this.mBackground != null) {
            this.mBackground.logicUpdate(i);
        }
        return 0;
    }

    public void pointerEventOccurred(int i, int i2, int i3) {
        if (this.mTextBox != null) {
            this.mTextBox.pointerEventOccurred(i, i2, i3);
        }
    }

    protected void resetKeys() {
        this.mPressedSK = -1;
        this.mKeys = 0;
    }

    public void screenSizeChanged() {
    }

    public void setVisible() {
        this.mUpdateSoftKeys = true;
        resetKeys();
    }

    protected void updateSoftKeys() {
    }
}
