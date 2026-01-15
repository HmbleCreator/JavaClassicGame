package com.digitalchocolate.androidrollergapp;

import java.io.IOException;
import java.lang.reflect.Array;
import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class MenuSelectTrack extends GameScreens {
    private static final int BACK_ATLANTIS = 2;
    private static final int BACK_CHINA = 1;
    private static final int BACK_INCA = 0;
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int SELECTION_SCROLL_SPEED = 300;
    private static final int TRACK_COORDS = 21;
    private static final int TRACK_COORD_ARROW1_X = 15;
    private static final int TRACK_COORD_ARROW2_X = 16;
    private static final int TRACK_COORD_ARROWS_Y = 17;
    private static final int TRACK_COORD_BACK_X = 14;
    private static final int TRACK_COORD_BOX_H = 3;
    private static final int TRACK_COORD_BOX_W = 2;
    private static final int TRACK_COORD_BOX_X = 0;
    private static final int TRACK_COORD_BOX_Y = 1;
    private static final int TRACK_COORD_GIFTS_X = 11;
    private static final int TRACK_COORD_GIFTS_Y = 12;
    private static final int TRACK_COORD_GIFT_W = 13;
    private static final int TRACK_COORD_OFFSET_X = 20;
    private static final int TRACK_COORD_POINT_W = 6;
    private static final int TRACK_COORD_POINT_X = 4;
    private static final int TRACK_COORD_POINT_Y = 5;
    private static final int TRACK_COORD_SMILEY_BAR_W = 19;
    private static final int TRACK_COORD_SMILEY_X = 9;
    private static final int TRACK_COORD_SMILEY_Y = 10;
    private static final int TRACK_COORD_TITLE_X = 7;
    private static final int TRACK_COORD_TITLE_Y = 8;
    private static final int TRACK_SPRITES = 23;
    private static final int TRACK_SPRITES_ATLANTIS = 27;
    private static final int TRACK_SPRITES_CHINA = 9;
    private static final int TRACK_SPRITES_INCA = 8;
    private static final int TRACK_SPRITE_BACKGROUND = 19;
    private static final int TRACK_SPRITE_BAMBOO1 = 2;
    private static final int TRACK_SPRITE_BAMBOO2 = 3;
    private static final int TRACK_SPRITE_BAMBOO3 = 4;
    private static final int TRACK_SPRITE_BAMBOO4 = 5;
    private static final int TRACK_SPRITE_BAMBOO5 = 8;
    private static final int TRACK_SPRITE_BAR = 11;
    private static final int TRACK_SPRITE_CRANE1 = 1;
    private static final int TRACK_SPRITE_CRANE2 = 7;
    private static final int TRACK_SPRITE_DOLPH1 = 5;
    private static final int TRACK_SPRITE_DOLPH2 = 23;
    private static final int TRACK_SPRITE_GIFT1 = 15;
    private static final int TRACK_SPRITE_GIFT2 = 16;
    private static final int TRACK_SPRITE_GIFT3 = 17;
    private static final int TRACK_SPRITE_LEAF1 = 0;
    private static final int TRACK_SPRITE_LEAF2 = 6;
    private static final int TRACK_SPRITE_LEFT = 20;
    private static final int TRACK_SPRITE_NOGIFT = 14;
    private static final int TRACK_SPRITE_PARROT1 = 6;
    private static final int TRACK_SPRITE_PARROT2 = 7;
    private static final int TRACK_SPRITE_PLANT1 = 0;
    private static final int TRACK_SPRITE_PLANT2 = 2;
    private static final int TRACK_SPRITE_PLANT3 = 4;
    private static final int TRACK_SPRITE_REF1_1 = 3;
    private static final int TRACK_SPRITE_REF1_10 = 20;
    private static final int TRACK_SPRITE_REF1_11 = 21;
    private static final int TRACK_SPRITE_REF1_12 = 22;
    private static final int TRACK_SPRITE_REF1_2 = 4;
    private static final int TRACK_SPRITE_REF1_3 = 6;
    private static final int TRACK_SPRITE_REF1_4 = 9;
    private static final int TRACK_SPRITE_REF1_5 = 10;
    private static final int TRACK_SPRITE_REF1_6 = 11;
    private static final int TRACK_SPRITE_REF1_7 = 12;
    private static final int TRACK_SPRITE_REF1_8 = 13;
    private static final int TRACK_SPRITE_REF1_9 = 14;
    private static final int TRACK_SPRITE_REF2_1 = 0;
    private static final int TRACK_SPRITE_REF2_10 = 19;
    private static final int TRACK_SPRITE_REF2_2 = 1;
    private static final int TRACK_SPRITE_REF2_3 = 2;
    private static final int TRACK_SPRITE_REF2_4 = 7;
    private static final int TRACK_SPRITE_REF2_5 = 8;
    private static final int TRACK_SPRITE_REF2_6 = 15;
    private static final int TRACK_SPRITE_REF2_7 = 16;
    private static final int TRACK_SPRITE_REF2_8 = 17;
    private static final int TRACK_SPRITE_REF2_9 = 18;
    private static final int TRACK_SPRITE_RIGHT = 21;
    private static final int TRACK_SPRITE_SELECTED = 18;
    private static final int TRACK_SPRITE_SMILIES_EMPTY = 13;
    private static final int TRACK_SPRITE_SMILIES_FULL = 12;
    private static final int TRACK_SPRITE_TORNADO = 22;
    private static final int TRACK_SPRITE_WATER1 = 1;
    private static final int TRACK_SPRITE_WATER2 = 3;
    private static final int TRACK_SPRITE_WATER3 = 5;
    private static final int TRACK_SPRITE_WAVE1 = 24;
    private static final int TRACK_SPRITE_WAVE2 = 25;
    private static final int TRACK_SPRITE_WAVE3 = 26;
    private static final int TRACK_TEXTS = 11;
    public static final int TS_NEWLEVEL = 2;
    public static final int TS_SCROLLING = 1;
    public static final int TS_UNLOCK = 3;
    public static final int TS_WAIT = 0;
    private int[] mAnimationSpeed;
    private int[] mAnimationX;
    private int[] mAnimationY;
    private SpriteObject[] mAnimationsSpr;
    private int[] mCollisionBoxX;
    private int[] mCollisionBoxY;
    private int mCurrentPosition;
    private int mCurrentSelection;
    private int mCurrentTheme;
    private int mDirection;
    private int mDolphin;
    private int mNextPosition;
    private int mNextSelection;
    private int mNumBoxes;
    private int mNumTiles;
    private Profile mProfile;
    private int mSelectionFontHeight;
    private ImageFont mSelectionImageFont;
    private int mTextFontHeight;
    private ImageFont mTextImageFont;
    private ImageFont mTitleBarImageFont;
    private int mTitleFontHeight;
    private boolean pointerDragged;
    public static boolean backlevel = false;
    private static final int[] AREA_NAMES_IDS = {TextIDs.TID_SELECTION_ROLLER_TM1_1, TextIDs.TID_SELECTION_ROLLER_TM1_2, TextIDs.TID_SELECTION_ROLLER_TM1_3, TextIDs.TID_SELECTION_ROLLER_TM2_1, TextIDs.TID_SELECTION_ROLLER_TM2_2, TextIDs.TID_SELECTION_ROLLER_TM2_3, TextIDs.TID_SELECTION_ROLLER_TM3_1, TextIDs.TID_SELECTION_ROLLER_TM3_2, TextIDs.TID_SELECTION_ROLLER_TM3_3};
    public int mTrackState = 0;
    private int xPointerPos = -999;

    public MenuSelectTrack(ImageFont imageFont, ImageFont imageFont2, ImageFont imageFont3, Profile profile) {
        this.mTitleBarImageFont = imageFont;
        this.mSelectionImageFont = imageFont2;
        this.mTextImageFont = imageFont3;
        this.mTitleFontHeight = this.mTitleBarImageFont.getHeight();
        this.mSelectionFontHeight = this.mSelectionImageFont.getHeight();
        this.mTextFontHeight = this.mTextImageFont.getHeight();
        this.mProfile = profile;
        int i = this.mProfile.mSelectedTrack;
        this.mNextSelection = i;
        this.mCurrentSelection = i;
        this.mCurrentTheme = this.mProfile.mSelectedTheme;
        this.mTitleTextId = TextIDs.TID_SELECTION_ROLLER_TM1_1;
    }

    private void drawBackground(Graphics graphics, int i, int i2) {
        int i3 = this.mPoolInt[14] >> 12;
        if (this.mCurrentTheme % 3 == 0) {
            this.mPoolSprite[19].draw(graphics, i3 + i, this.mCanvasHeight / 2);
            for (int i4 = 0; i4 < this.mNumBoxes; i4++) {
                this.mAnimationsSpr[i4].draw(graphics, this.mCollisionBoxX[i4] + i3 + i, this.mCollisionBoxY[i4]);
            }
            return;
        }
        if (this.mCurrentTheme % 3 == 1) {
            this.mPoolSprite[19].draw(graphics, i3 + i, this.mCanvasHeight / 2);
            for (int i5 = 0; i5 < this.mNumBoxes; i5++) {
                this.mAnimationsSpr[i5].draw(graphics, this.mCollisionBoxX[i5] + i3 + i, this.mCollisionBoxY[i5]);
            }
            return;
        }
        if (this.mCurrentTheme % 3 == 2) {
            for (int i6 = 0; i6 < this.mNumBoxes; i6++) {
                if (i6 != 5 && i6 != 23) {
                    this.mAnimationsSpr[i6].draw(graphics, this.mCollisionBoxX[i6] + i3 + i, this.mCollisionBoxY[i6]);
                }
            }
            this.mPoolSprite[19].draw(graphics, i3 + i, this.mCanvasHeight / 2);
            if (this.mDolphin > -1) {
                this.mAnimationsSpr[this.mDolphin].draw(graphics, i3 + this.mCollisionBoxX[this.mDolphin] + i, this.mCollisionBoxY[this.mDolphin]);
            }
        }
    }

    private void loadSprites() {
        this.mPoolSprite = new SpriteObject[23];
        initGraphics();
        this.mPoolSprite[11] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_POINT_TRACK, true), false);
        this.mPoolSprite[12] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_A_SMILES_BAR_FULL, true), false);
        this.mPoolSprite[13] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_SMILES_BAR_EMPTY, true), false);
        this.mPoolSprite[14] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_A_PRIZES_LOW_NO_PRIZE, true), false);
        this.mPoolSprite[15] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_A_PRIZES_LOW_JUMP, true), false);
        this.mPoolSprite[16] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_A_PRIZES_LOW_SPEED, true), false);
        this.mPoolSprite[17] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_A_PRIZES_LOW_HAPPY, true), false);
        this.mPoolSprite[20] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_MENU_SCROLL_ARROW_LEFT, true), true);
        this.mPoolSprite[21] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_MENU_SCROLL_ARROW_RIGHT, true), true);
        this.mPoolSprite[22] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_POINT_HIGH_TORNADO, true), false);
        this.mPoolSprite[18] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_POINT_HIGH_SELECT, true), true);
    }

    private void loadText() {
        this.mPoolText = (String[][]) Array.newInstance((Class<?>) String.class, 11, 1);
        for (int i = 0; i < 11; i++) {
            this.mPoolText[i][0] = Toolkit.getText(TextIDs.TID_SELECTION_TRACK) + " " + (i + 1);
        }
    }

    private void setDolphin() {
        int iRnd = Util.rnd(100);
        this.mDolphin = 5;
        if (iRnd < 50) {
            this.mDolphin = 23;
        }
        this.mAnimationsSpr[this.mDolphin].setAnimation(0, 1, true);
    }

    private void trackScrollLeft() {
        if (this.mNextSelection > 0) {
            this.mNextSelection--;
            this.mDirection = 0;
            this.mNextPosition = (-this.mPoolInt[6]) << 12;
        }
    }

    private void trackScrollRight() {
        if (this.mNextSelection < 10) {
            this.mNextSelection++;
            this.mDirection = 1;
            this.mNextPosition = this.mPoolInt[6] << 12;
        }
    }

    public void calculateBackground() {
        if (this.mPoolSprite[19] != null) {
            this.mPoolInt[14] = ((this.mCanvasWidth / 2) - ((this.mPoolInt[6] * this.mCurrentSelection) / 2)) << 12;
            CollisionBox[] collisionBoxes = this.mPoolSprite[19].getCollisionBoxes();
            this.mNumBoxes = collisionBoxes.length;
            this.mCollisionBoxX = new int[this.mNumBoxes];
            this.mCollisionBoxY = new int[this.mNumBoxes];
            for (int i = 0; i < this.mNumBoxes; i++) {
                int id = collisionBoxes[i].getId();
                this.mCollisionBoxX[id] = collisionBoxes[i].getX();
                this.mCollisionBoxY[id] = collisionBoxes[i].getY() + (this.mCanvasHeight / 2);
            }
            if (this.mCurrentTheme % 3 == 0) {
                this.mAnimationX = new int[2];
                this.mAnimationY = new int[2];
                this.mAnimationSpeed = new int[2];
                int[] iArr = this.mAnimationX;
                int[] iArr2 = this.mAnimationX;
                int i2 = (-100) << (this.mPoolInt[14] + 12);
                iArr2[1] = i2;
                iArr[0] = i2;
                this.mAnimationY[0] = (this.mCanvasHeight / 2) + (this.mCanvasWidth / 2);
                this.mAnimationY[1] = this.mAnimationY[0] + 5;
                this.mAnimationY[0] = this.mAnimationY[0] << 12;
                this.mAnimationY[1] = this.mAnimationY[1] << 12;
                this.mAnimationSpeed[0] = 163840;
                this.mAnimationSpeed[1] = 122880;
            } else if (this.mCurrentTheme % 3 == 2) {
                setDolphin();
            }
            this.mNumTiles = (this.mPoolSprite[19].getWidth() / (this.mPoolInt[6] * 11)) + 2;
            if (this.mPoolSprite[19].getWidth() % (this.mPoolInt[6] * 11) > 0) {
                this.mNumTiles++;
            }
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    protected void calculateCoords() {
        this.mPoolInt = new int[21];
        this.mPoolInt[0] = 10;
        this.mPoolInt[2] = this.mCanvasWidth - 20;
        this.mPoolInt[3] = this.mSelectionFontHeight + this.mPoolSprite[15].getHeight() + (this.mSeparatorY * 3);
        int height = this.mPoolInt[3] + this.mPoolSprite[0].getHeight() + (this.mSeparatorY * 4);
        this.mPoolInt[5] = ((this.mDrawAreaHeight - height) / 2) + this.mDrawAreaY + this.mPoolSprite[0].getPivotY();
        this.mPoolInt[4] = this.mCanvasWidth / 2;
        this.mPoolInt[1] = this.mPoolInt[5] + this.mPoolSprite[0].getHeight() + (this.mSeparatorY * 4);
        this.mPoolInt[7] = (this.mCanvasWidth - this.mSelectionImageFont.stringWidth(this.mPoolText[this.mCurrentSelection][0])) / 2;
        this.mPoolInt[8] = this.mPoolInt[1] + this.mSeparatorY;
        this.mPoolInt[13] = this.mPoolSprite[15].getWidth() + 5;
        this.mPoolInt[9] = this.mPoolInt[0] + 5;
        this.mPoolInt[11] = (this.mPoolInt[0] + this.mPoolInt[2]) - (this.mPoolInt[13] * 3);
        this.mPoolInt[12] = this.mPoolInt[8] + 5 + this.mPoolSprite[15].getPivotY() + this.mSelectionFontHeight;
        this.mPoolInt[10] = (this.mPoolInt[12] + ((this.mPoolSprite[15].getHeight() - this.mPoolSprite[13].getHeight()) / 2)) - this.mTextFontHeight;
        this.mPoolInt[6] = this.mPoolSprite[11].getWidth();
        this.mPoolInt[17] = this.mPoolInt[5];
        this.mPoolInt[15] = this.mPoolInt[4] - this.mPoolSprite[0].getWidth();
        this.mPoolInt[16] = this.mPoolInt[4] + this.mPoolSprite[0].getWidth();
        this.mPoolInt[19] = this.mPoolSprite[12].getCollisionBoxes()[0].getWidth();
        this.mPoolInt[20] = (this.mPoolSprite[12].getWidth() - this.mPoolInt[19]) / 2;
        calculateBackground();
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void doDraw(Graphics graphics) {
        graphics.setClip(0, 0, this.mCanvasWidth, this.mCanvasHeight);
        if (this.mCurrentTheme % 3 == 0) {
            CustomMenuObject.drawGradientRect(graphics, 0, 0, this.mCanvasWidth, this.mCanvasHeight, 7371342, 9662812, 3);
        } else if (this.mCurrentTheme % 3 == 1) {
            CustomMenuObject.drawGradientRect(graphics, 0, 0, this.mCanvasWidth, this.mCanvasHeight, 8819317, 12827265, 3);
        } else {
            CustomMenuObject.drawGradientRect(graphics, 0, 0, this.mCanvasWidth, this.mCanvasHeight, 7645692, 5008544, 3);
        }
        for (int i = 0; i < this.mNumTiles; i++) {
            drawBackground(graphics, i * 320, 0);
        }
        if (this.mCurrentTheme % 3 == 0) {
            this.mAnimationsSpr[7].draw(graphics, this.mAnimationX[1] >> 12, this.mAnimationY[1] >> 12);
            this.mAnimationsSpr[6].draw(graphics, this.mAnimationX[0] >> 12, this.mAnimationY[0] >> 12);
        }
        this.mBackground.drawTitleBarBackground(graphics, 0, 0, this.mCanvasWidth, this.mBackground.mTitleBarHeight);
        this.mBackground.drawTitleBar(graphics, ((this.mCanvasWidth / 2) - (this.mTitleBarImageFont.stringWidth(this.mBackground.mTitleBarText[0]) / 2)) + 0, 0, this.mBackground.mTitleBarHeight);
        if (this.mTrackState == 0) {
            this.mPoolSprite[18].draw(graphics, this.mPoolInt[4], this.mPoolInt[5]);
        }
        int i2 = this.mCurrentPosition >> 12;
        for (int i3 = 0; i3 < 10; i3++) {
            int i4 = ((this.mPoolInt[4] + (this.mPoolInt[6] * i3)) - (this.mPoolInt[6] * this.mCurrentSelection)) + i2;
            if (i4 < this.mCanvasWidth && this.mPoolSprite[0].getWidth() + i4 > 0) {
                this.mPoolSprite[11].draw(graphics, i4, this.mPoolInt[5]);
            }
        }
        for (int i5 = 0; i5 < 11; i5++) {
            int i6 = ((this.mPoolInt[4] + (this.mPoolInt[6] * i5)) - (this.mPoolInt[6] * this.mCurrentSelection)) + i2;
            if (i6 < this.mCanvasWidth && this.mPoolSprite[0].getWidth() + i6 > 0) {
                this.mPoolSprite[i5].draw(graphics, i6, this.mPoolInt[5]);
            }
            if (Engine2D.TORNADO_LEVEL[(this.mCurrentTheme * 11) + i5]) {
                this.mPoolSprite[22].draw(graphics, i6, this.mPoolInt[5]);
            }
        }
        int i7 = (i2 * this.mCanvasWidth) / this.mPoolInt[6];
        CustomMenuObject.drawPopup(graphics, this.mPoolInt[0] + i7, this.mPoolInt[1], this.mPoolInt[2], this.mPoolInt[3], true);
        this.mSelectionImageFont.drawString(graphics, this.mPoolText[this.mCurrentSelection][0], this.mPoolInt[7] + i7, this.mPoolInt[8], 20);
        int i8 = (this.mCurrentTheme * 11) + this.mCurrentSelection;
        for (int i9 = 0; i9 < 3; i9++) {
            if ((this.mProfile.mPrizesUnlocked[i8] & 1) != 0 && i9 == 0) {
                this.mPoolSprite[15].draw(graphics, this.mPoolInt[11] + (this.mPoolInt[13] * i9) + i7, this.mPoolInt[12]);
            } else if ((this.mProfile.mPrizesUnlocked[i8] & 2) != 0 && i9 == 1) {
                this.mPoolSprite[16].draw(graphics, this.mPoolInt[11] + (this.mPoolInt[13] * i9) + i7, this.mPoolInt[12]);
            } else if ((this.mProfile.mPrizesUnlocked[i8] & 4) == 0 || i9 != 2) {
                this.mPoolSprite[14].draw(graphics, this.mPoolInt[11] + (this.mPoolInt[13] * i9) + i7, this.mPoolInt[12]);
            } else {
                this.mPoolSprite[17].draw(graphics, this.mPoolInt[11] + (this.mPoolInt[13] * i9) + i7, this.mPoolInt[12]);
            }
        }
        this.mPoolSprite[13].draw(graphics, this.mPoolInt[9] + i7, this.mPoolInt[10]);
        int i10 = this.mPoolInt[19] / 5;
        int i11 = i10 * 4;
        int i12 = (this.mProfile.mTrackScore[this.mCurrentTheme][this.mCurrentSelection] * i11) / (ResultsTable.TOP_SCORE_VALUES[i8] * 100);
        if ((this.mProfile.mPrizesUnlocked[i8] & 1) != 0) {
            i12 += i10 / 2;
        }
        if ((this.mProfile.mPrizesUnlocked[i8] & 2) != 0) {
            i12 += i10 / 2;
        }
        int i13 = ((this.mProfile.mPrizesUnlocked[i8] & 4) != 0 || i12 < i10 * 5) ? i12 : i12 - (i10 / 2);
        if (i13 > 0) {
            graphics.setClip(this.mPoolInt[9] + i7, this.mPoolInt[10], i13, this.mCanvasHeight);
            this.mPoolSprite[12].draw(graphics, i7 + this.mPoolInt[9], this.mPoolInt[10]);
        }
        int i14 = ((this.mNextPosition >> 12) * this.mCanvasWidth) / this.mPoolInt[6];
        CustomMenuObject.drawPopup(graphics, this.mPoolInt[0] + i14, this.mPoolInt[1], this.mPoolInt[2], this.mPoolInt[3], true);
        this.mSelectionImageFont.drawString(graphics, this.mPoolText[this.mNextSelection][0], this.mPoolInt[7] + i14, this.mPoolInt[8], 20);
        int i15 = (this.mCurrentTheme * 11) + this.mNextSelection;
        for (int i16 = 0; i16 < 3; i16++) {
            if ((this.mProfile.mPrizesUnlocked[i15] & 1) != 0 && i16 == 0) {
                this.mPoolSprite[15].draw(graphics, this.mPoolInt[11] + (this.mPoolInt[13] * i16) + i14, this.mPoolInt[12]);
            } else if ((this.mProfile.mPrizesUnlocked[i15] & 2) != 0 && i16 == 1) {
                this.mPoolSprite[16].draw(graphics, this.mPoolInt[11] + (this.mPoolInt[13] * i16) + i14, this.mPoolInt[12]);
            } else if ((this.mProfile.mPrizesUnlocked[i15] & 4) == 0 || i16 != 2) {
                this.mPoolSprite[14].draw(graphics, this.mPoolInt[11] + (this.mPoolInt[13] * i16) + i14, this.mPoolInt[12]);
            } else {
                this.mPoolSprite[17].draw(graphics, this.mPoolInt[11] + (this.mPoolInt[13] * i16) + i14, this.mPoolInt[12]);
            }
        }
        this.mPoolSprite[13].draw(graphics, this.mPoolInt[9] + i14, this.mPoolInt[10]);
        int i17 = (this.mProfile.mTrackScore[this.mCurrentTheme][this.mNextSelection] * i11) / (ResultsTable.TOP_SCORE_VALUES[i15] * 100);
        if ((this.mProfile.mPrizesUnlocked[i15] & 1) != 0) {
            i17 += i10 / 2;
        }
        if ((this.mProfile.mPrizesUnlocked[i15] & 2) != 0) {
            i17 += i10 / 2;
        }
        int i18 = ((this.mProfile.mPrizesUnlocked[i15] & 4) != 0 || i17 < i10 * 5) ? i17 : i17 - (i10 / 2);
        if (i18 > 0) {
            graphics.setClip(this.mPoolInt[9] + this.mPoolInt[20] + i14, this.mPoolInt[10], i18, this.mCanvasHeight);
            this.mPoolSprite[12].draw(graphics, i14 + this.mPoolInt[9] + this.mPoolInt[20], this.mPoolInt[10]);
        }
        if (this.mTrackState == 0) {
            graphics.setClip(0, 0, this.mCanvasWidth, this.mCanvasHeight);
            if (this.mCurrentSelection > 0) {
                this.mPoolSprite[20].draw(graphics, this.mPoolInt[15], this.mPoolInt[17]);
            }
            if (this.mCurrentSelection < 10) {
                this.mPoolSprite[21].draw(graphics, this.mPoolInt[16], this.mPoolInt[17]);
            }
        }
        if (this.mTextBox != null) {
            this.mTextBox.doDraw(graphics);
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public int getLoadingCount() {
        return 3;
    }

    public void initGraphics() {
        for (int i = 0; i < 11; i++) {
            if (!this.mProfile.mTrackUnlocked[this.mCurrentTheme][i]) {
                this.mPoolSprite[i] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_POINT_HIGH_LOCK, true), false);
            } else if (this.mProfile.getSmileys((this.mCurrentTheme * 11) + i) == 5) {
                this.mPoolSprite[i] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_POINT_HIGH_COMPLETE, true), false);
            } else if (this.mProfile.mLastUnlockedTrack == i && this.mProfile.mLastUnlockedTheme == this.mCurrentTheme) {
                this.mPoolSprite[i] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_POINT_HIGH_NOT_PLAY, true), false);
            } else {
                this.mPoolSprite[i] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_POINT_HIGH_UNSELECT, true), false);
            }
        }
        int i2 = this.mCurrentTheme % 3;
        if (i2 == 0) {
            this.mPoolSprite[19] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_BACKGROUND_01, true), false);
            this.mAnimationsSpr = new SpriteObject[8];
            this.mAnimationsSpr[1] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_BACKGROUND_01_WATER, true), true);
            this.mAnimationsSpr[3] = this.mAnimationsSpr[1].m0clone();
            this.mAnimationsSpr[5] = this.mAnimationsSpr[1].m0clone();
            this.mAnimationsSpr[3].setAnimationFrame(2);
            this.mAnimationsSpr[5].setAnimationFrame(3);
            this.mAnimationsSpr[0] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_BACKGROUND_01_PLANT, true), true);
            this.mAnimationsSpr[2] = this.mAnimationsSpr[0].m0clone();
            this.mAnimationsSpr[4] = this.mAnimationsSpr[0].m0clone();
            this.mAnimationsSpr[6] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_BACKGROUND_CARROT_01, true), true);
            this.mAnimationsSpr[7] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_BACKGROUND_CARROT_02, true), true);
            return;
        }
        if (i2 == 1) {
            this.mPoolSprite[19] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_BACKGROUND_02, true), false);
            this.mAnimationsSpr = new SpriteObject[9];
            this.mAnimationsSpr[0] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_BACKGROUND_LEAF, true), true);
            this.mAnimationsSpr[6] = this.mAnimationsSpr[0].m0clone();
            this.mAnimationsSpr[1] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_BACKGROUND_CRANE, true), true);
            this.mAnimationsSpr[7] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_BACKGROUND_CRANE_02, true), true);
            this.mAnimationsSpr[2] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_BACKGROUND_BAMBOO, true), true);
            this.mAnimationsSpr[3] = this.mAnimationsSpr[2].m0clone();
            this.mAnimationsSpr[4] = this.mAnimationsSpr[2].m0clone();
            this.mAnimationsSpr[5] = this.mAnimationsSpr[2].m0clone();
            this.mAnimationsSpr[8] = this.mAnimationsSpr[2].m0clone();
            return;
        }
        if (i2 == 2) {
            this.mPoolSprite[19] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_BACKGROUND_03, true), false);
            this.mAnimationsSpr = new SpriteObject[27];
            this.mAnimationsSpr[0] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_BACKGROUND_SEA_REFLECTION_02, true), true);
            this.mAnimationsSpr[3] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_BACKGROUND_SEA_REFLECTION, true), true);
            this.mAnimationsSpr[5] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_BACKGROUND_DOLPHIN, true), false);
            this.mAnimationsSpr[24] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_BACKGROUND_SEA_WAVES, true), true);
            this.mAnimationsSpr[1] = this.mAnimationsSpr[0].m0clone();
            this.mAnimationsSpr[1].setAnimationFrame(1);
            this.mAnimationsSpr[2] = this.mAnimationsSpr[0].m0clone();
            this.mAnimationsSpr[2].setAnimationFrame(2);
            this.mAnimationsSpr[7] = this.mAnimationsSpr[0].m0clone();
            this.mAnimationsSpr[7].setAnimationFrame(3);
            this.mAnimationsSpr[8] = this.mAnimationsSpr[0].m0clone();
            this.mAnimationsSpr[15] = this.mAnimationsSpr[0].m0clone();
            this.mAnimationsSpr[15].setAnimationFrame(1);
            this.mAnimationsSpr[16] = this.mAnimationsSpr[0].m0clone();
            this.mAnimationsSpr[16].setAnimationFrame(2);
            this.mAnimationsSpr[17] = this.mAnimationsSpr[0].m0clone();
            this.mAnimationsSpr[17].setAnimationFrame(3);
            this.mAnimationsSpr[18] = this.mAnimationsSpr[0].m0clone();
            this.mAnimationsSpr[19] = this.mAnimationsSpr[0].m0clone();
            this.mAnimationsSpr[19].setAnimationFrame(1);
            this.mAnimationsSpr[4] = this.mAnimationsSpr[3].m0clone();
            this.mAnimationsSpr[4].setAnimationFrame(1);
            this.mAnimationsSpr[6] = this.mAnimationsSpr[3].m0clone();
            this.mAnimationsSpr[6].setAnimationFrame(2);
            this.mAnimationsSpr[9] = this.mAnimationsSpr[3].m0clone();
            this.mAnimationsSpr[9].setAnimationFrame(3);
            this.mAnimationsSpr[10] = this.mAnimationsSpr[3].m0clone();
            this.mAnimationsSpr[11] = this.mAnimationsSpr[3].m0clone();
            this.mAnimationsSpr[11].setAnimationFrame(1);
            this.mAnimationsSpr[12] = this.mAnimationsSpr[3].m0clone();
            this.mAnimationsSpr[12].setAnimationFrame(2);
            this.mAnimationsSpr[13] = this.mAnimationsSpr[3].m0clone();
            this.mAnimationsSpr[13].setAnimationFrame(3);
            this.mAnimationsSpr[14] = this.mAnimationsSpr[3].m0clone();
            this.mAnimationsSpr[20] = this.mAnimationsSpr[3].m0clone();
            this.mAnimationsSpr[20].setAnimationFrame(1);
            this.mAnimationsSpr[21] = this.mAnimationsSpr[3].m0clone();
            this.mAnimationsSpr[22] = this.mAnimationsSpr[3].m0clone();
            this.mAnimationsSpr[23] = this.mAnimationsSpr[5].m0clone();
            this.mAnimationsSpr[25] = this.mAnimationsSpr[24].m0clone();
            this.mAnimationsSpr[26] = this.mAnimationsSpr[24].m0clone();
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void load(int i) {
        if (i == 0) {
            loadSprites();
            return;
        }
        if (i == 1) {
            loadText();
            loadBackground();
        } else if (i == 2) {
            calculateCoords();
            this.mUpdateSoftKeys = true;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public int logicUpdate(int i) throws IOException {
        super.logicUpdate(i);
        if (this.mTextBox != null) {
            this.mTextBox.logicUpdate(i);
            if (this.mPressedSK == 1 || (this.mKeys & 16) != 0) {
                resetKeys();
                destroyTextBox();
            }
            return 0;
        }
        if (this.mCurrentTheme == 0) {
            for (int i2 = 0; i2 < this.mNumBoxes; i2++) {
                this.mAnimationsSpr[i2].logicUpdate(i);
            }
            for (int i3 = 0; i3 < 2; i3++) {
                this.mAnimationsSpr[i3 + 6].logicUpdate(i);
                int[] iArr = this.mAnimationX;
                iArr[i3] = iArr[i3] + ((this.mAnimationSpeed[i3] * i) / 1000);
                int[] iArr2 = this.mAnimationY;
                iArr2[i3] = iArr2[i3] - ((this.mAnimationSpeed[i3] * i) / 2000);
                if (this.mAnimationX[i3] > (this.mCanvasWidth << 12)) {
                    this.mAnimationX[i3] = -409600;
                    this.mAnimationY[i3] = (((this.mCanvasHeight / 2) + (this.mCanvasWidth / 2)) + (i3 * 5)) << 12;
                }
            }
        } else if (this.mCurrentTheme % 3 == 1) {
            for (int i4 = 0; i4 < this.mNumBoxes; i4++) {
                this.mAnimationsSpr[i4].logicUpdate(i);
            }
        } else if (this.mCurrentTheme % 3 == 2) {
            for (int i5 = 0; i5 < this.mNumBoxes; i5++) {
                if (i5 != 5 && i5 != 23) {
                    this.mAnimationsSpr[i5].logicUpdate(i);
                }
            }
            if (this.mDolphin > -1) {
                this.mAnimationsSpr[this.mDolphin].logicUpdate(i);
                if (this.mAnimationsSpr[this.mDolphin].isFinishedAnimation()) {
                    this.mDolphin = -1;
                }
            } else if (Util.rnd(1000) < 10) {
                setDolphin();
            }
        }
        switch (this.mTrackState) {
            case 0:
                this.mPoolSprite[18].logicUpdate(i);
                this.mPoolSprite[20].logicUpdate(i);
                this.mPoolSprite[21].logicUpdate(i);
                if (this.mPressedSK == 5) {
                    if (RollerGameEngine.smGameMode == 1) {
                        backlevel = true;
                    }
                    resetKeys();
                    return 12;
                }
                if (this.mPressedSK == 0 || (this.mKeys & 16) != 0) {
                    resetKeys();
                    if (this.mProfile.mTrackUnlocked[this.mCurrentTheme][this.mCurrentSelection]) {
                        this.mProfile.mSelectedTrack = this.mCurrentSelection;
                        return 11;
                    }
                    if (RollerGameEngine.smGameMode == 1) {
                        createTextBox(Toolkit.getText(TextIDs.TID_LVL_LOCKED_HOTSEAT));
                    } else {
                        createTextBox(Toolkit.getText(148));
                    }
                    this.mUpdateSoftKeys = true;
                    return 0;
                }
                if ((this.mKeys & 2) != 0) {
                    trackScrollRight();
                } else if ((this.mKeys & 1) != 0) {
                    trackScrollLeft();
                }
                if (this.mNextPosition != 0) {
                    this.mTrackState = 1;
                    resetKeys();
                    break;
                }
                break;
            case 1:
                int i6 = this.mNextPosition;
                if (this.mDirection == 1) {
                    this.mCurrentPosition -= (1228800 * i) / 1000;
                    this.mNextPosition -= (1228800 * i) / 1000;
                    if (this.mNextPosition < 0 || (this.mKeys & 2) != 0) {
                        this.mNextPosition = 0;
                        resetKeys();
                    }
                    if (this.mCurrentTheme == 0) {
                        for (int i7 = 0; i7 < 2; i7++) {
                            int[] iArr3 = this.mAnimationX;
                            iArr3[i7] = iArr3[i7] - ((1228800 * i) / 1000);
                        }
                    }
                } else {
                    this.mCurrentPosition += (1228800 * i) / 1000;
                    this.mNextPosition += (1228800 * i) / 1000;
                    if (this.mNextPosition > 0 || (this.mKeys & 1) != 0) {
                        this.mNextPosition = 0;
                        resetKeys();
                    }
                    if (this.mCurrentTheme == 0) {
                        for (int i8 = 0; i8 < 2; i8++) {
                            int[] iArr4 = this.mAnimationX;
                            iArr4[i8] = iArr4[i8] + ((1228800 * i) / 1000);
                        }
                    }
                }
                int[] iArr5 = this.mPoolInt;
                iArr5[14] = ((this.mNextPosition - i6) / 2) + iArr5[14];
                if (this.mNextPosition == 0) {
                    this.mCurrentPosition = 0;
                    this.mCurrentSelection = this.mNextSelection;
                    this.mTrackState = 0;
                    this.mPoolInt[7] = (this.mCanvasWidth - this.mTextImageFont.stringWidth(this.mPoolText[this.mCurrentSelection][0])) / 2;
                    break;
                }
                break;
            case 2:
                this.mTrackState = 0;
                break;
        }
        return 0;
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void pointerEventOccurred(int i, int i2, int i3) {
        super.pointerEventOccurred(i, i2, i3);
        int pivotX = this.mPoolInt[15] - this.mPoolSprite[20].getPivotX();
        int pivotX2 = this.mPoolInt[16] - this.mPoolSprite[20].getPivotX();
        int pivotY = this.mPoolInt[17] - this.mPoolSprite[20].getPivotY();
        int width = this.mPoolSprite[20].getWidth() + 20;
        int height = this.mPoolSprite[20].getHeight() + 20;
        int i4 = (i <= pivotX || i >= pivotX + width || pivotY > i2 || pivotY + height < i2) ? (pivotX2 > i || pivotX2 + width < i || pivotY > i2 || pivotY + height < i2) ? 0 : 2 : 1;
        if (i3 == 0) {
            this.xPointerPos = i;
            this.mKeys = i4 | this.mKeys;
        } else if (i3 != 1) {
            if (i3 == 2) {
            }
        } else {
            this.mKeys = (i4 ^ (-1)) & this.mKeys;
            this.pointerDragged = false;
        }
    }

    public void setCurrentSelection(boolean z) {
        this.mCurrentTheme = this.mProfile.mSelectedTheme;
        if (z) {
            int i = 0;
            while (i < 11 && !this.mProfile.mTrackUnlocked[this.mCurrentTheme][i]) {
                i++;
            }
            if (RollerGameEngine.smGameMode == 1 || this.mCurrentTheme != this.mProfile.mLastUnlockedTheme || i == 11) {
                this.mNextSelection = 0;
                this.mCurrentSelection = 0;
            } else {
                int i2 = this.mProfile.mSelectedTrack;
                this.mNextSelection = i2;
                this.mCurrentSelection = i2;
            }
        }
        destroyPoolText();
        loadText();
        setMenuTitle(AREA_NAMES_IDS[this.mCurrentTheme]);
    }

    public void setMenuTitle(int i) {
        this.mBackground.setTitleBar((this.mProfile.mSelectedTheme + 1) + " . " + Toolkit.getText(i).toUpperCase(), null, 3);
        this.mBackground.setSize(this.mCanvasWidth, this.mCanvasHeight);
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    protected void updateSoftKeys() {
        Game.removeAllSoftKeys();
        if (this.mTextBox != null) {
            Game.setSoftKey(1, 0);
        } else {
            Game.setSoftKey(0, 0);
            Game.setSoftKey(5, 0);
        }
    }
}
