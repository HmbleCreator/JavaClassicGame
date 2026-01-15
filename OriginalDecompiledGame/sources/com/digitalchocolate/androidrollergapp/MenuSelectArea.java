package com.digitalchocolate.androidrollergapp;

import java.io.IOException;
import java.lang.reflect.Array;
import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class MenuSelectArea extends GameScreens {
    private static final int LEFT = 0;
    private static final int MAX_SELECTIONS = 9;
    private static final int RIGHT = 1;
    private static final int SELECTION_COORD_ARROW1_X = 12;
    private static final int SELECTION_COORD_ARROW2_X = 13;
    private static final int SELECTION_COORD_ARROWS_Y = 14;
    private static final int SELECTION_COORD_BOX_H = 3;
    private static final int SELECTION_COORD_BOX_W = 2;
    private static final int SELECTION_COORD_BOX_X = 0;
    private static final int SELECTION_COORD_BOX_Y = 1;
    private static final int SELECTION_COORD_ICON_X = 4;
    private static final int SELECTION_COORD_ICON_Y = 5;
    private static final int SELECTION_COORD_NEXTITLE_X = 15;
    private static final int SELECTION_COORD_NEXTSUB_X = 16;
    private static final int SELECTION_COORD_SMILES_X = 10;
    private static final int SELECTION_COORD_SMILES_Y = 11;
    private static final int SELECTION_COORD_SUBTITLE_X = 8;
    private static final int SELECTION_COORD_SUBTITLE_Y = 9;
    private static final int SELECTION_COORD_TITLE_X = 6;
    private static final int SELECTION_COORD_TITLE_Y = 7;
    private static final int SELECTION_FACES_SPRITE = 0;
    private static final int SELECTION_HEAD1_SPRITE = 3;
    private static final int SELECTION_HEAD2_SPRITE = 4;
    private static final int SELECTION_HEAD3_SPRITE = 5;
    private static final int SELECTION_LEFT_SPRITE = 6;
    private static final int SELECTION_LOCKS_SPRITE = 1;
    private static final int SELECTION_NUM_COORDS = 17;
    private static final int SELECTION_NUM_SPRITES = 8;
    private static final int SELECTION_NUM_TXT = 12;
    private static final int SELECTION_RIGHT_SPRITE = 7;
    private static final int SELECTION_SCROLL_SPEED = 400;
    private static final int SELECTION_TXT_SUBTITLE1 = 3;
    private static final int SELECTION_TXT_SUBTITLE2 = 4;
    private static final int SELECTION_TXT_SUBTITLE3 = 5;
    private static final int SELECTION_TXT_SUBTITLE4 = 6;
    private static final int SELECTION_TXT_SUBTITLE5 = 7;
    private static final int SELECTION_TXT_SUBTITLE6 = 8;
    private static final int SELECTION_TXT_SUBTITLE7 = 9;
    private static final int SELECTION_TXT_SUBTITLE8 = 10;
    private static final int SELECTION_TXT_SUBTITLE9 = 11;
    private static final int SELECTION_TXT_TITLE1 = 0;
    private static final int SELECTION_TXT_TITLE2 = 1;
    private static final int SELECTION_TXT_TITLE3 = 2;
    private static final int SELECTION_VOID_SPRITE = 2;
    private static final int SS_SCROLLING = 0;
    private static final int SS_WAIT = 1;
    private static int mCurrentSel = 0;
    private int mCurrentPosition;
    private int mCurrentSelection;
    private int mDirection;
    private int mNextPosition;
    private int mNextSelection;
    private Profile mProfile;
    private int mSelectionFontHeight;
    private ImageFont mSelectionImageFont;
    private int mTextFontHeight;
    private ImageFont mTextImageFont;
    private ImageFont mTitleBarImageFont;
    private int mTitleFontHeight;
    private boolean pointerDragged;
    private int mSelectionState = 1;
    private int xPointerPos = -999;

    public MenuSelectArea(ImageFont imageFont, ImageFont imageFont2, ImageFont imageFont3, Profile profile) {
        this.mTitleBarImageFont = imageFont;
        this.mSelectionImageFont = imageFont2;
        this.mTextImageFont = imageFont3;
        this.mTitleFontHeight = this.mTitleBarImageFont.getHeight();
        this.mSelectionFontHeight = this.mSelectionImageFont.getHeight();
        this.mTextFontHeight = this.mTextImageFont.getHeight();
        this.mProfile = profile;
        this.mTitleTextId = 84;
    }

    private void areaScrollLeft() {
        if (this.mNextSelection > 0) {
            this.mNextSelection--;
            this.mDirection = 0;
            this.mNextPosition = (-this.mCanvasWidth) << 12;
        }
    }

    private void areaScrollRight() {
        if (this.mNextSelection < 8) {
            this.mNextSelection++;
            this.mDirection = 1;
            this.mNextPosition = this.mCanvasWidth << 12;
        }
    }

    private void loadSprites() {
        this.mPoolSprite = new SpriteObject[8];
        this.mPoolSprite[0] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_POINT_LOW_COMPLETE, true), false);
        this.mPoolSprite[1] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_POINT_LOW_LOCK, true), false);
        this.mPoolSprite[2] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_POINT_LOW_UNSELECT, true), false);
        this.mPoolSprite[3] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_ICON_MAP_THEME_01, true), false);
        this.mPoolSprite[4] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_ICON_MAP_THEME_02, true), false);
        this.mPoolSprite[5] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_ICON_MAP_THEME_03, true), false);
        this.mPoolSprite[6] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_MENU_SCROLL_ARROW_LEFT, true), true);
        this.mPoolSprite[7] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_MENU_SCROLL_ARROW_RIGHT, true), true);
    }

    private void loadText() {
        this.mPoolText = (String[][]) Array.newInstance((Class<?>) String.class, 12, 1);
        this.mPoolText[0][0] = Toolkit.getText(81);
        this.mPoolText[1][0] = Toolkit.getText(82);
        this.mPoolText[2][0] = Toolkit.getText(83);
        this.mPoolText[3][0] = "1. " + Toolkit.getText(TextIDs.TID_SELECTION_ROLLER_TM1_1).toUpperCase();
        this.mPoolText[4][0] = "2. " + Toolkit.getText(TextIDs.TID_SELECTION_ROLLER_TM1_2).toUpperCase();
        this.mPoolText[5][0] = "3. " + Toolkit.getText(TextIDs.TID_SELECTION_ROLLER_TM1_3).toUpperCase();
        this.mPoolText[6][0] = "4. " + Toolkit.getText(TextIDs.TID_SELECTION_ROLLER_TM2_1).toUpperCase();
        this.mPoolText[7][0] = "5. " + Toolkit.getText(TextIDs.TID_SELECTION_ROLLER_TM2_2).toUpperCase();
        this.mPoolText[8][0] = "6. " + Toolkit.getText(TextIDs.TID_SELECTION_ROLLER_TM2_3).toUpperCase();
        this.mPoolText[9][0] = "7. " + Toolkit.getText(TextIDs.TID_SELECTION_ROLLER_TM3_1).toUpperCase();
        this.mPoolText[10][0] = "8. " + Toolkit.getText(TextIDs.TID_SELECTION_ROLLER_TM3_2).toUpperCase();
        this.mPoolText[11][0] = "9. " + Toolkit.getText(TextIDs.TID_SELECTION_ROLLER_TM3_3).toUpperCase();
    }

    private void updateCoords() {
        this.mPoolInt[16] = (this.mCanvasWidth - this.mTitleBarImageFont.stringWidth(this.mPoolText[this.mNextSelection + 3][0])) / 2;
        this.mPoolInt[15] = (this.mCanvasWidth - this.mSelectionImageFont.stringWidth(this.mPoolText[(this.mNextSelection % 3) + 0][0])) / 2;
        this.mPoolInt[8] = (this.mCanvasWidth - this.mTitleBarImageFont.stringWidth(this.mPoolText[this.mCurrentSelection + 3][0])) / 2;
        this.mPoolInt[6] = (this.mCanvasWidth - this.mSelectionImageFont.stringWidth(this.mPoolText[(this.mCurrentSelection % 3) + 0][0])) / 2;
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    protected void calculateCoords() {
        this.mPoolInt = new int[17];
        this.mPoolInt[3] = this.mTitleFontHeight + this.mSelectionFontHeight + this.mPoolSprite[0].getHeight() + (this.mSeparatorY * 3);
        int pivotY = this.mPoolInt[3] + this.mPoolSprite[3].getPivotY();
        this.mPoolInt[4] = this.mCanvasWidth / 2;
        this.mPoolInt[5] = ((this.mDrawAreaHeight - pivotY) / 2) + this.mDrawAreaY + this.mPoolSprite[3].getHeight();
        this.mPoolInt[0] = -10;
        this.mPoolInt[2] = this.mCanvasWidth + 20;
        this.mPoolInt[1] = this.mPoolInt[5];
        if (this.mPoolInt[1] + this.mPoolInt[3] > this.mCanvasHeight - Toolkit.getSoftKeyAreaHeight()) {
            int softKeyAreaHeight = (this.mPoolInt[1] + this.mPoolInt[3]) - (this.mCanvasHeight - Toolkit.getSoftKeyAreaHeight());
            int[] iArr = this.mPoolInt;
            iArr[1] = iArr[1] - (this.mSeparatorY + softKeyAreaHeight);
            int[] iArr2 = this.mPoolInt;
            iArr2[5] = iArr2[5] - (softKeyAreaHeight + this.mSeparatorY);
        }
        this.mPoolInt[8] = (this.mCanvasWidth - this.mTitleBarImageFont.stringWidth(this.mPoolText[this.mCurrentSelection + 3][0])) / 2;
        this.mPoolInt[9] = this.mPoolInt[5] + this.mSeparatorY;
        this.mPoolInt[6] = (this.mCanvasWidth - this.mSelectionImageFont.stringWidth(this.mPoolText[(this.mCurrentSelection % 3) + 0][0])) / 2;
        this.mPoolInt[7] = this.mPoolInt[9] + this.mTitleFontHeight;
        this.mPoolInt[16] = this.mPoolInt[8];
        this.mPoolInt[15] = this.mPoolInt[6];
        this.mPoolInt[10] = (((this.mCanvasWidth - (this.mPoolSprite[0].getWidth() * 11)) - 25) / 2) + this.mPoolSprite[0].getPivotX();
        this.mPoolInt[11] = this.mPoolInt[7] + this.mSelectionFontHeight + this.mPoolSprite[0].getPivotY() + this.mSeparatorY;
        this.mPoolInt[12] = this.mPoolSprite[6].getPivotY() + 5;
        this.mPoolInt[13] = (this.mCanvasWidth - 5) - this.mPoolSprite[6].getPivotY();
        this.mPoolInt[14] = this.mPoolInt[9] + this.mTitleFontHeight;
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void doDraw(Graphics graphics) {
        this.mBackground.drawBackground(graphics, 0, 0, this.mCanvasWidth, this.mCanvasHeight);
        CustomMenuObject.drawPopup(graphics, this.mPoolInt[0], this.mPoolInt[1], this.mPoolInt[2], this.mPoolInt[3], true);
        int i = this.mCurrentPosition >> 12;
        this.mPoolSprite[(this.mCurrentSelection % 3) + 3].setAnimationFrame(this.mCurrentSelection / 3);
        this.mPoolSprite[(this.mCurrentSelection % 3) + 3].draw(graphics, this.mPoolInt[4] + i, this.mPoolInt[5]);
        this.mTitleBarImageFont.drawString(graphics, this.mPoolText[this.mCurrentSelection + 3][0], this.mPoolInt[8] + i, this.mPoolInt[9], 20);
        this.mSelectionImageFont.drawString(graphics, this.mPoolText[(this.mCurrentSelection % 3) + 0][0], this.mPoolInt[6] + i, this.mPoolInt[7], 20);
        int i2 = this.mCurrentSelection;
        int width = this.mPoolSprite[0].getWidth() + 2;
        int i3 = this.mPoolInt[10] + i + 2;
        for (int i4 = 0; i4 < 11; i4++) {
            if (!this.mProfile.mTrackUnlocked[i2][i4]) {
                this.mPoolSprite[1].draw(graphics, i3, this.mPoolInt[11]);
            } else if (this.mProfile.getSmileys((i2 * 11) + i4) == 5) {
                this.mPoolSprite[0].draw(graphics, i3, this.mPoolInt[11]);
            } else {
                this.mPoolSprite[2].draw(graphics, i3, this.mPoolInt[11]);
            }
            i3 += width;
        }
        if (this.mNextPosition != 0) {
            int i5 = this.mNextPosition >> 12;
            this.mPoolSprite[(this.mNextSelection % 3) + 3].setAnimationFrame(this.mNextSelection / 3);
            this.mPoolSprite[(this.mNextSelection % 3) + 3].draw(graphics, this.mPoolInt[4] + i5, this.mPoolInt[5]);
            this.mTitleBarImageFont.drawString(graphics, this.mPoolText[this.mNextSelection + 3][0], this.mPoolInt[16] + i5, this.mPoolInt[9], 20);
            this.mSelectionImageFont.drawString(graphics, this.mPoolText[(this.mNextSelection % 3) + 0][0], this.mPoolInt[15] + i5, this.mPoolInt[7], 20);
            int i6 = this.mNextSelection;
            int width2 = this.mPoolSprite[0].getWidth() + 2;
            int i7 = this.mPoolInt[10] + i5;
            for (int i8 = 0; i8 < 11; i8++) {
                if (!this.mProfile.mTrackUnlocked[i6][i8]) {
                    this.mPoolSprite[1].draw(graphics, i7, this.mPoolInt[11]);
                } else if (this.mProfile.getSmileys((i6 * 11) + i8) == 5) {
                    this.mPoolSprite[0].draw(graphics, i7, this.mPoolInt[11]);
                } else {
                    this.mPoolSprite[2].draw(graphics, i7, this.mPoolInt[11]);
                }
                i7 += width2;
            }
        }
        if (this.mSelectionState == 1) {
            if (this.mCurrentSelection > 0) {
                this.mPoolSprite[6].draw(graphics, this.mPoolInt[12], this.mPoolInt[14] + 0);
            }
            if (this.mCurrentSelection < 8) {
                this.mPoolSprite[7].draw(graphics, this.mPoolInt[13], this.mPoolInt[14] + 0);
            }
        }
        this.mBackground.drawTitleBarBackground(graphics, 0, 0, this.mCanvasWidth, this.mBackground.mTitleBarHeight);
        this.mBackground.drawTitleBar(graphics, (this.mCanvasWidth - this.mTitleBarImageFont.stringWidth(this.mBackground.mTitleBarText[0])) / 2, 0, this.mBackground.mTitleBarHeight);
        if (this.mTextBox != null) {
            this.mTextBox.doDraw(graphics);
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public int getLoadingCount() {
        return 3;
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
        switch (this.mSelectionState) {
            case 0:
                if (this.mDirection == 1) {
                    this.mCurrentPosition -= (ResourceIDs.PIXELDATA_92_DEFAULT_0 * i) / 1000;
                    this.mNextPosition -= (ResourceIDs.PIXELDATA_92_DEFAULT_0 * i) / 1000;
                    if (this.mNextPosition < 0 || (this.mKeys & 2) != 0) {
                        this.mNextPosition = 0;
                        resetKeys();
                    }
                } else {
                    this.mCurrentPosition += (ResourceIDs.PIXELDATA_92_DEFAULT_0 * i) / 1000;
                    this.mNextPosition += (ResourceIDs.PIXELDATA_92_DEFAULT_0 * i) / 1000;
                    if (this.mNextPosition > 0 || (this.mKeys & 1) != 0) {
                        this.mNextPosition = 0;
                        resetKeys();
                    }
                }
                if (this.mNextPosition == 0) {
                    this.mCurrentPosition = 0;
                    this.mCurrentSelection = this.mNextSelection;
                    this.mPoolInt[8] = this.mPoolInt[16];
                    this.mPoolInt[6] = this.mPoolInt[15];
                    this.mSelectionState = 1;
                    break;
                }
                break;
            case 1:
                this.mPoolSprite[6].logicUpdate(i);
                this.mPoolSprite[7].logicUpdate(i);
                if (this.mPressedSK != 5) {
                    if (this.mPressedSK != 0 && (this.mKeys & 16) == 0) {
                        if ((this.mKeys & 2) != 0) {
                            areaScrollRight();
                        } else if ((this.mKeys & 1) != 0) {
                            areaScrollLeft();
                        }
                        if (this.mNextPosition != 0) {
                            updateCoords();
                            this.mSelectionState = 0;
                            resetKeys();
                            break;
                        }
                    } else {
                        resetKeys();
                        this.mProfile.mSelectedTheme = this.mCurrentSelection;
                        mCurrentSel = this.mCurrentSelection;
                        if (this.mProfile.mThemeUnlocked[this.mProfile.mSelectedTheme]) {
                            return 9;
                        }
                        if (RollerGameEngine.smGameMode == 1) {
                            createTextBox(Toolkit.getText(161));
                        } else {
                            createTextBox(Toolkit.getText(160));
                        }
                        this.mUpdateSoftKeys = true;
                        return 0;
                    }
                } else {
                    resetKeys();
                    return 10;
                }
                break;
        }
        return 0;
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void pointerEventOccurred(int i, int i2, int i3) {
        super.pointerEventOccurred(i, i2, i3);
        int pivotX = this.mPoolInt[12] - this.mPoolSprite[6].getPivotX();
        int pivotX2 = this.mPoolInt[13] - this.mPoolSprite[6].getPivotX();
        int pivotY = this.mPoolInt[14] - this.mPoolSprite[6].getPivotY();
        int width = this.mPoolSprite[6].getWidth() + 20;
        int height = this.mPoolSprite[6].getHeight() + 20;
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

    public void setCurrentSelection() {
        int i = this.mProfile.mSelectedTheme;
        this.mNextSelection = i;
        this.mCurrentSelection = i;
        if (RollerGameEngine.smGameMode == 1) {
            if (MenuSelectTrack.backlevel) {
                this.mCurrentSelection = mCurrentSel;
                MenuSelectTrack.backlevel = false;
            } else {
                this.mCurrentSelection = 0;
                this.mNextSelection = 0;
            }
        }
        updateCoords();
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
