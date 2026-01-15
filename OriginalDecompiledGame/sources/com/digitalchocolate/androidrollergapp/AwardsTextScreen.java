package com.digitalchocolate.androidrollergapp;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class AwardsTextScreen extends GameScreens {
    private static final int AWARDS_COORDS = 6;
    private static final int AWARDS_COORD_BOX_H = 3;
    private static final int AWARDS_COORD_BOX_W = 2;
    private static final int AWARDS_COORD_BOX_X = 0;
    private static final int AWARDS_COORD_BOX_Y = 1;
    private static final int AWARDS_COORD_ICON_X = 4;
    private static final int AWARDS_COORD_ICON_Y = 5;
    private static final int AWARDS_SPRITES = 25;
    private static final int AWARDS_SPR_ARROW_DOWN = 22;
    private static final int AWARDS_SPR_ARROW_UP = 21;
    private static final int AWARDS_SPR_SHINE = 23;
    private static final int AWARDS_SPR_STARS = 24;
    private static final int NUM_AWARDS = 21;
    private int mCurrentSelection = -1;
    private boolean mDrawBand;
    private int mMaxHeight;
    private int mNumShineBoxes;
    private Profile mProfile;
    private int mSelectionFontHeight;
    private ImageFont mSelectionImageFont;
    private int mShineIndex;
    private int[] mShineX;
    private int[] mShineY;
    private int mTextFontHeight;
    private int mTextHeight;
    private ImageFont mTextImageFont;
    private int mTimeShine;
    private ImageFont mTitleBarImageFont;
    private int mTitleFontHeight;
    private static final int[] AWARDS_IDS = {ResourceIDs.ANM_R_A_ACHIEVEMENTS_01, ResourceIDs.ANM_R_A_ACHIEVEMENTS_02, ResourceIDs.ANM_R_A_ACHIEVEMENTS_03, ResourceIDs.ANM_R_A_ACHIEVEMENTS_04, ResourceIDs.ANM_R_A_ACHIEVEMENTS_05, ResourceIDs.ANM_R_A_ACHIEVEMENTS_06, ResourceIDs.ANM_R_A_ACHIEVEMENTS_07, ResourceIDs.ANM_R_A_ACHIEVEMENTS_08, ResourceIDs.ANM_R_A_ACHIEVEMENTS_09, ResourceIDs.ANM_R_A_ACHIEVEMENTS_10, ResourceIDs.ANM_R_A_ACHIEVEMENTS_11, ResourceIDs.ANM_R_A_ACHIEVEMENTS_12, ResourceIDs.ANM_R_A_ACHIEVEMENTS_13, ResourceIDs.ANM_R_A_ACHIEVEMENTS_14, ResourceIDs.ANM_R_A_ACHIEVEMENTS_15, ResourceIDs.ANM_R_A_ACHIEVEMENTS_16, ResourceIDs.ANM_R_A_ACHIEVEMENTS_17, ResourceIDs.ANM_R_A_ACHIEVEMENTS_18, ResourceIDs.ANM_R_A_ACHIEVEMENTS_19, ResourceIDs.ANM_R_A_ACHIEVEMENTS_20, ResourceIDs.ANM_R_A_ACHIEVEMENTS_21};
    private static final int[] AWARDS_TEXT_TITLES_IDS = {TextIDs.TID_ACHIEVEMENTS_TITLE_1, TextIDs.TID_ACHIEVEMENTS_TITLE_2, TextIDs.TID_ACHIEVEMENTS_TITLE_3, TextIDs.TID_ACHIEVEMENTS_TITLE_4, TextIDs.TID_ACHIEVEMENTS_TITLE_5, TextIDs.TID_ACHIEVEMENTS_TITLE_6, TextIDs.TID_ACHIEVEMENTS_TITLE_7, TextIDs.TID_ACHIEVEMENTS_TITLE_8, TextIDs.TID_ACHIEVEMENTS_TITLE_9, 240, 241, TextIDs.TID_ACHIEVEMENTS_TITLE_12, TextIDs.TID_ACHIEVEMENTS_TITLE_13, TextIDs.TID_ACHIEVEMENTS_TITLE_14, TextIDs.TID_ACHIEVEMENTS_TITLE_15, TextIDs.TID_ACHIEVEMENTS_TITLE_16, TextIDs.TID_ACHIEVEMENTS_TITLE_17, TextIDs.TID_ACHIEVEMENTS_TITLE_18, TextIDs.TID_ACHIEVEMENTS_TITLE_19, TextIDs.TID_ACHIEVEMENTS_TITLE_20, TextIDs.TID_ACHIEVEMENTS_TITLE_21};
    private static final int[] AWARDS_TEXT_DESCRIPTION_IDS = {TextIDs.TID_ACHIEVEMENTS_LOCKED_1, TextIDs.TID_ACHIEVEMENTS_LOCKED_2, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272};

    public AwardsTextScreen(ImageFont imageFont, ImageFont imageFont2, ImageFont imageFont3, Profile profile) {
        this.mTitleBarImageFont = imageFont;
        this.mSelectionImageFont = imageFont2;
        this.mTextImageFont = imageFont3;
        this.mTitleFontHeight = this.mTitleBarImageFont.getHeight();
        this.mSelectionFontHeight = this.mSelectionImageFont.getHeight();
        this.mTextFontHeight = this.mTextImageFont.getHeight();
        this.mProfile = profile;
    }

    private void calculateBoxY() {
        this.mPoolInt[5] = this.mDrawAreaY + this.mSeparatorY;
        this.mPoolInt[3] = this.mTextBox.mHeight;
        this.mPoolInt[1] = this.mPoolInt[5] + this.mMaxHeight + (((this.mDrawAreaHeight - this.mMaxHeight) - this.mPoolInt[3]) / 2);
        this.mDrawBand = true;
        if (this.mPoolInt[3] + this.mPoolInt[1] > this.mDrawAreaY + this.mDrawAreaHeight) {
            this.mDrawBand = false;
            destroyPoolSprite();
            this.mMaxHeight = 0;
            this.mPoolInt[5] = this.mDrawAreaY + this.mSeparatorY;
            this.mPoolInt[3] = this.mTextHeight + (this.mSeparatorY * 2);
            this.mPoolInt[1] = this.mPoolInt[5] + this.mMaxHeight + (((this.mDrawAreaHeight - this.mMaxHeight) - this.mPoolInt[3]) / 2);
        }
        destroyTextBox();
    }

    private void loadIcons(int[] iArr) {
        this.mMaxHeight = 0;
        for (int i = 0; i < 21; i++) {
            if (this.mProfile.mAwardsUnlocked[i]) {
                this.mPoolSprite[i] = new SpriteObject(DavinciUtilities.loadAnimation(iArr[i], true), false);
            } else {
                this.mPoolSprite[i] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_A_ACHIEVEMENTS_LING, true), false);
            }
            if (this.mMaxHeight < this.mPoolSprite[i].getHeight()) {
                this.mMaxHeight = this.mPoolSprite[i].getHeight() - 20;
            }
        }
        this.mPoolSprite[23] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_E_SHINE, true), false);
        this.mPoolSprite[24] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_E_BOOM_STARS, true), true);
    }

    private void loadSprites() {
        this.mPoolSprite = new SpriteObject[25];
        this.mPoolSprite[21] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_MENU_SCROLL_ARROW_UP, true), true);
        this.mPoolSprite[22] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_MENU_SCROLL_ARROW_DOWN, true), true);
        loadIcons(AWARDS_IDS);
    }

    private void setShinePosition() {
        this.mShineIndex = Math.abs(Util.rand()) % this.mNumShineBoxes;
        this.mPoolSprite[23].setAnimation(0, 1, true);
        this.mTimeShine = 2000;
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    protected void calculateCoords() {
        this.mPoolInt = new int[6];
        loadBackground();
        this.mPoolInt[0] = this.mSeparatorX * 2;
        this.mPoolInt[2] = this.mCanvasWidth - (this.mSeparatorX * 4);
        this.mPoolInt[4] = this.mCanvasWidth / 2;
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void doDraw(Graphics graphics) {
        this.mBackground.doDraw(graphics);
        this.mTextBox.doDraw(graphics);
        if (this.mDrawBand) {
            CustomMenuObject.drawPopup(graphics, -10, (this.mPoolInt[5] + (this.mMaxHeight / 2)) - (this.mSeparatorY * 3), this.mCanvasWidth + 20, this.mSeparatorY * 6, false);
            if (this.mProfile.mAwardsUnlocked[this.mCurrentSelection]) {
                this.mPoolSprite[24].draw(graphics, this.mPoolInt[4], this.mPoolInt[5] + (this.mMaxHeight / 2));
            }
            this.mPoolSprite[this.mCurrentSelection].draw(graphics, this.mPoolInt[4], this.mPoolInt[5] + (this.mMaxHeight / 2));
            if (this.mPoolSprite[23].isFinishedAnimation() || !this.mProfile.mAwardsUnlocked[this.mCurrentSelection]) {
                return;
            }
            this.mPoolSprite[23].draw(graphics, this.mShineX[this.mShineIndex], this.mShineY[this.mShineIndex]);
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void keyEventOccurred(int i, int i2) {
        super.keyEventOccurred(i, i2);
        this.mTextBox.keyEventOccurred(i, i2);
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void load(int i) {
        if (i == 3) {
            loadSprites();
        } else if (i == 4) {
            calculateCoords();
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public int logicUpdate(int i) throws IOException {
        super.logicUpdate(i);
        if (this.mDrawBand && this.mProfile.mAwardsUnlocked[this.mCurrentSelection]) {
            this.mPoolSprite[23].logicUpdate(i);
            this.mPoolSprite[24].logicUpdate(i);
            if (this.mPoolSprite[23].isFinishedAnimation()) {
                this.mTimeShine -= i;
                if (this.mTimeShine <= 0) {
                    setShinePosition();
                }
            }
        }
        if (this.mPressedSK == 5) {
            resetKeys();
            return 54;
        }
        this.mTextBox.logicUpdate(i);
        return 0;
    }

    public void setCurrentText(int i) throws IOException {
        String text = Toolkit.getText(AWARDS_TEXT_DESCRIPTION_IDS[i]);
        this.mTextHeight = MenuObject.splitString(text, this.mTextImageFont, this.mPoolInt[2]).length * this.mTextFontHeight;
        createTextBox(text, (this.mDrawAreaHeight - this.mMaxHeight) - this.mTextHeight);
        calculateBoxY();
        createTextBox(text, this.mPoolInt[0], this.mPoolInt[1] - 0, this.mPoolInt[2], this.mPoolInt[3] + 0, 3);
        CollisionBox[] collisionBoxes = this.mPoolSprite[i].getCollisionBoxes();
        this.mNumShineBoxes = collisionBoxes.length;
        this.mShineX = null;
        this.mShineY = null;
        this.mShineX = new int[this.mNumShineBoxes];
        this.mShineY = new int[this.mNumShineBoxes];
        for (int i2 = 0; i2 < this.mNumShineBoxes; i2++) {
            this.mShineX[i2] = collisionBoxes[i2].getX() + this.mPoolInt[4];
            this.mShineY[i2] = collisionBoxes[i2].getY() + this.mPoolInt[5] + (this.mMaxHeight / 2);
        }
        this.mCurrentSelection = i;
        if (this.mDrawBand && this.mProfile.mAwardsUnlocked[this.mCurrentSelection]) {
            setShinePosition();
        }
        this.mBackground.setTitleBar(Toolkit.getText(AWARDS_TEXT_TITLES_IDS[i]), null, 3);
        this.mBackground.setSize(this.mCanvasWidth, this.mCanvasHeight);
        this.mUpdateSoftKeys = true;
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    protected void updateSoftKeys() {
        Game.setSoftKey(5, 0);
    }
}
