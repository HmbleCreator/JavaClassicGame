package com.digitalchocolate.androidrollergapp;

import java.io.IOException;
import java.lang.reflect.Array;
import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class MenuSelectTrackTrial extends GameScreens {
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int TRACK_COORDS = 11;
    private static final int TRACK_COORD_BOX_H = 3;
    private static final int TRACK_COORD_BOX_W = 2;
    private static final int TRACK_COORD_BOX_X = 0;
    private static final int TRACK_COORD_BOX_Y = 1;
    private static final int TRACK_COORD_GIFTS_X = 8;
    private static final int TRACK_COORD_GIFTS_Y = 9;
    private static final int TRACK_COORD_GIFT_W = 10;
    private static final int TRACK_COORD_SMILEY_X = 6;
    private static final int TRACK_COORD_SMILEY_Y = 7;
    private static final int TRACK_COORD_TITLE_X = 4;
    private static final int TRACK_COORD_TITLE_Y = 5;
    private static final int TRACK_SPRITES = 19;
    private static final int TRACK_SPRITE_BACKGROUND = 18;
    private static final int TRACK_SPRITE_GIFT1 = 14;
    private static final int TRACK_SPRITE_GIFT2 = 15;
    private static final int TRACK_SPRITE_GIFT3 = 16;
    private static final int TRACK_SPRITE_NOGIFT = 13;
    private static final int TRACK_SPRITE_SELECTED = 17;
    private static final int TRACK_SPRITE_SMILIES_EMPTY = 12;
    private static final int TRACK_SPRITE_SMILIES_FULL = 11;
    private static final int TRACK_TEXTS = 11;
    public static final int TS_NEWLEVEL = 2;
    public static final int TS_SCROLLING = 1;
    public static final int TS_UNLOCK = 3;
    public static final int TS_WAIT = 0;
    int height;
    private int mCurrentSelection;
    private int mCurrentTheme;
    private boolean mGoNext;
    private int mNumBoxes;
    private int[] mPointsX;
    private int[] mPointsY;
    private Profile mProfile;
    private int mSelectionFontHeight;
    private ImageFont mSelectionImageFont;
    private int mTextFontHeight;
    private ImageFont mTextImageFont;
    private ImageFont mTitleBarImageFont;
    private int mTitleFontHeight;
    public int mTrackState = 0;
    int width;
    int x;
    int y;

    public MenuSelectTrackTrial(ImageFont imageFont, ImageFont imageFont2, ImageFont imageFont3, Profile profile) {
        this.mTitleBarImageFont = imageFont;
        this.mSelectionImageFont = imageFont2;
        this.mTextImageFont = imageFont3;
        this.mTitleFontHeight = this.mTitleBarImageFont.getHeight();
        this.mSelectionFontHeight = this.mSelectionImageFont.getHeight();
        this.mTextFontHeight = this.mTextImageFont.getHeight();
        this.mProfile = profile;
        this.mTitleTextId = TextIDs.TID_SELECTION_ROLLER_TM1_1;
    }

    private void loadSprites() {
        this.mPoolSprite = new SpriteObject[19];
        initGraphics();
        this.mPoolSprite[11] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_A_SMILES_BAR_FULL, true), false);
        this.mPoolSprite[12] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_SMILES_BAR_EMPTY, true), false);
        this.mPoolSprite[13] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_A_PRIZES_LOW_NO_PRIZE, true), false);
        this.mPoolSprite[14] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_A_PRIZES_LOW_JUMP, true), false);
        this.mPoolSprite[15] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_A_PRIZES_LOW_SPEED, true), false);
        this.mPoolSprite[16] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_A_PRIZES_LOW_HAPPY, true), false);
        this.mPoolSprite[18] = new SpriteObject(DavinciUtilities.loadAnimation(-1, true), false);
    }

    private void loadText() {
        this.mPoolText = (String[][]) Array.newInstance((Class<?>) String.class, 11, 1);
        for (int i = 0; i < 11; i++) {
            this.mPoolText[i][0] = ((this.mCurrentTheme * 11) + i + 1) + Toolkit.getText(TextIDs.TID_SELECTION_TRACK) + " " + (i + 1);
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    protected void calculateCoords() {
        super.calculateCoords();
        this.x = 0;
        this.y = 0;
        this.width = this.mCanvasWidth;
        this.height = this.mCanvasHeight;
        this.mPoolInt = new int[11];
        CollisionBox[] collisionBoxes = this.mPoolSprite[18].getCollisionBoxes();
        this.mNumBoxes = collisionBoxes.length;
        this.mPointsX = new int[this.mNumBoxes - 1];
        this.mPointsY = new int[this.mNumBoxes - 1];
        for (int i = 0; i < this.mNumBoxes; i++) {
            int id = collisionBoxes[i].getId();
            if (id == 11) {
                this.mPoolInt[0] = this.x + collisionBoxes[i].getX();
                this.mPoolInt[1] = this.y + collisionBoxes[i].getY() + this.height;
                this.mPoolInt[2] = collisionBoxes[i].getWidth();
                this.mPoolInt[3] = collisionBoxes[i].getHeight();
            } else {
                this.mPointsX[id] = this.x + collisionBoxes[i].getX();
                this.mPointsY[id] = this.y + collisionBoxes[i].getY() + this.height;
            }
        }
        this.mPoolInt[4] = (this.width - this.mTextImageFont.stringWidth(this.mPoolText[this.mCurrentSelection][0])) / 2;
        this.mPoolInt[5] = this.mPoolInt[1] + this.mSeparatorY;
        this.mPoolInt[10] = this.mPoolSprite[14].getWidth() + 5;
        this.mPoolInt[6] = this.mPoolInt[0] + 5;
        this.mPoolInt[8] = ((this.mPoolInt[0] + this.mPoolInt[2]) - (this.mPoolInt[10] * 3)) + this.mPoolSprite[14].getPivotX();
        this.mPoolInt[9] = this.mPoolInt[5] + 5 + this.mPoolSprite[14].getPivotY() + this.mTextFontHeight;
        this.mPoolInt[7] = (this.mPoolInt[9] + ((this.mPoolSprite[14].getHeight() - this.mPoolSprite[12].getHeight()) / 2)) - this.mTextFontHeight;
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void doDraw(Graphics graphics) {
        graphics.setClip(this.x, this.y, this.width, this.height);
        this.mPoolSprite[18].draw(graphics, this.x, this.y + this.height);
        this.mBackground.drawTitleBarBackground(graphics, this.x, this.y, this.width, this.mBackground.mTitleBarHeight);
        this.mBackground.drawTitleBar(graphics, (this.mCanvasWidth - this.mTitleBarImageFont.stringWidth(this.mBackground.mTitleBarText[0])) / 2, this.y, this.mBackground.mTitleBarHeight);
        for (int i = 0; i < this.mNumBoxes - 1; i++) {
            this.mPoolSprite[i].draw(graphics, this.mPointsX[i], this.mPointsY[i]);
        }
        CustomMenuObject.drawPopup(graphics, this.mPoolInt[0], this.mPoolInt[1], this.mPoolInt[2], this.mPoolInt[3], true);
        this.mTextImageFont.drawString(graphics, this.mPoolText[this.mCurrentSelection][0], this.mPoolInt[4], this.mPoolInt[5], 20);
        this.mPoolSprite[12].draw(graphics, this.mPoolInt[6], this.mPoolInt[7]);
        int i2 = (this.mCurrentTheme * 11) + this.mCurrentSelection;
        int width = (this.mPoolSprite[11].getWidth() / 5) * this.mProfile.getSmileys(i2);
        if (width > 0) {
            graphics.setClip(this.mPoolInt[6], this.mPoolInt[7], width, this.mCanvasHeight);
            this.mPoolSprite[11].draw(graphics, this.mPoolInt[6], this.mPoolInt[7]);
        }
        graphics.setClip(0, 0, this.mCanvasWidth, this.mCanvasHeight);
        for (int i3 = 0; i3 < 3; i3++) {
            if ((this.mProfile.mPrizesUnlocked[i2] & 1) != 0 && i3 == 0) {
                this.mPoolSprite[14].draw(graphics, this.mPoolInt[8], this.mPoolInt[9]);
            } else if ((this.mProfile.mPrizesUnlocked[i2] & 2) != 0 && i3 == 1) {
                this.mPoolSprite[15].draw(graphics, this.mPoolInt[8] + this.mPoolInt[10], this.mPoolInt[9]);
            } else if ((this.mProfile.mPrizesUnlocked[i2] & 4) == 0 || i3 != 2) {
                this.mPoolSprite[13].draw(graphics, this.mPoolInt[8] + (this.mPoolInt[10] * i3), this.mPoolInt[9]);
            } else {
                this.mPoolSprite[16].draw(graphics, this.mPoolInt[8] + (this.mPoolInt[10] * 2), this.mPoolInt[9]);
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
                this.mPoolSprite[i] = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_R_P_MAP_POINT_HIGH_LOCK, -1}, true), false);
            } else if (this.mProfile.mLastUnlockedTrack == i && this.mProfile.mLastUnlockedTheme == this.mCurrentTheme) {
                this.mPoolSprite[i] = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_R_P_MAP_POINT_HIGH_NOT_PLAY, -1}, true), false);
            } else if (this.mProfile.getSmileys((this.mCurrentTheme * 9) + i) == 5) {
                this.mPoolSprite[i] = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_R_P_MAP_POINT_HIGH_COMPLETE, -1}, true), false);
            } else {
                this.mPoolSprite[i] = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_R_P_MAP_POINT_HIGH_UNSELECT, -1}, true), false);
            }
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
        switch (this.mTrackState) {
            case 0:
                if (this.mPressedSK != 5) {
                    if (this.mPressedSK != 0 && (this.mKeys & 16) == 0 && !this.mGoNext) {
                        int i2 = this.mCurrentSelection;
                        if ((this.mKeys & 2) != 0) {
                            this.mCurrentSelection++;
                            if (this.mCurrentSelection > 10) {
                                this.mCurrentSelection = 0;
                            }
                        } else if ((this.mKeys & 1) != 0) {
                            this.mCurrentSelection--;
                            if (this.mCurrentSelection < 0) {
                                this.mCurrentSelection = 10;
                            }
                        }
                        if (i2 != this.mCurrentSelection) {
                            this.mPoolSprite[i2].setAnimation(0, 1, true);
                            this.mPoolSprite[this.mCurrentSelection].setAnimation(1, 1, true);
                            resetKeys();
                            break;
                        }
                    } else {
                        resetKeys();
                        if (this.mProfile.mTrackUnlocked[this.mCurrentTheme][this.mCurrentSelection]) {
                            this.mProfile.mSelectedTrack = this.mCurrentSelection;
                            return 11;
                        }
                        createTextBox(Toolkit.getText(148));
                        this.mUpdateSoftKeys = true;
                        return 0;
                    }
                } else {
                    resetKeys();
                    return 12;
                }
                break;
        }
        return 0;
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void pointerEventOccurred(int i, int i2, int i3) {
        super.pointerEventOccurred(i, i2, i3);
        if (i3 != 2) {
            if (i3 == 0) {
                int pivotX = this.mPointsX[this.mCurrentSelection] - this.mPoolSprite[this.mCurrentSelection].getPivotX();
                int pivotY = this.mPointsY[this.mCurrentSelection] - this.mPoolSprite[this.mCurrentSelection].getPivotY();
                this.mGoNext = i > pivotX && i < pivotX + this.mPoolSprite[this.mCurrentSelection].getWidth() && i2 > pivotY && i2 < pivotY + this.mPoolSprite[this.mCurrentSelection].getHeight();
                return;
            }
            return;
        }
        int i4 = this.mCurrentSelection;
        for (int i5 = 0; i5 < 11; i5++) {
            int pivotX2 = this.mPointsX[i5] - this.mPoolSprite[i5].getPivotX();
            int pivotY2 = this.mPointsY[i5] - this.mPoolSprite[i5].getPivotY();
            int width = this.mPoolSprite[i5].getWidth();
            int height = this.mPoolSprite[i5].getHeight();
            if (i > pivotX2 && i < pivotX2 + width && i2 > pivotY2 && i2 < pivotY2 + height) {
                this.mCurrentSelection = i5;
                if (i4 != this.mCurrentSelection) {
                    this.mPoolSprite[i4].setAnimation(0, 1, true);
                    this.mPoolSprite[this.mCurrentSelection].setAnimation(1, 1, true);
                    return;
                }
                return;
            }
        }
    }

    public void setCurrentSelection() {
        this.mCurrentTheme = this.mProfile.mSelectedTheme;
        int i = 0;
        while (i < 11 && !this.mProfile.mTrackUnlocked[this.mCurrentTheme][i]) {
            i++;
        }
        if (RollerGameEngine.smGameMode == 1 || this.mCurrentTheme != this.mProfile.mLastUnlockedTheme || i == 11) {
            this.mCurrentSelection = 0;
        } else {
            this.mCurrentSelection = this.mProfile.mSelectedTrack;
        }
        destroyPoolText();
        loadText();
        setMenuTitle(TextIDs.TID_SELECTION_ROLLER_TM1_1);
        this.mPoolSprite[this.mCurrentSelection].setAnimation(1, 1, true);
    }

    public void setMenuTitle(int i) {
        this.mBackground.setTitleBar(Toolkit.getText(i).toUpperCase(), null, 3);
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
