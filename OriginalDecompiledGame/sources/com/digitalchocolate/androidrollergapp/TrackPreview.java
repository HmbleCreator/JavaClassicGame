package com.digitalchocolate.androidrollergapp;

import java.io.IOException;
import java.lang.reflect.Array;
import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class TrackPreview extends GameScreens {
    private static final int[] AREA_NAMES_IDS = {TextIDs.TID_SELECTION_ROLLER_TM1_1, TextIDs.TID_SELECTION_ROLLER_TM1_2, TextIDs.TID_SELECTION_ROLLER_TM1_3, TextIDs.TID_SELECTION_ROLLER_TM2_1, TextIDs.TID_SELECTION_ROLLER_TM2_2, TextIDs.TID_SELECTION_ROLLER_TM2_3, TextIDs.TID_SELECTION_ROLLER_TM3_1, TextIDs.TID_SELECTION_ROLLER_TM3_2, TextIDs.TID_SELECTION_ROLLER_TM3_3};
    private static final int PASS_COORDS = 12;
    private static final int PASS_COORD_BOX_H = 26;
    private static final int PASS_COORD_BOX_W = 25;
    private static final int PASS_COORD_BOX_X = 23;
    private static final int PASS_COORD_BOX_Y = 24;
    private static final int PASS_COORD_PLAYER_X = 31;
    private static final int PASS_COORD_PLAYER_Y = 32;
    private static final int PASS_COORD_SCORE_X = 33;
    private static final int PASS_COORD_SOCRE_Y = 34;
    private static final int PASS_COORD_TEXT_X = 29;
    private static final int PASS_COORD_TEXT_Y = 30;
    private static final int PASS_COORD_TITLE_X = 27;
    private static final int PASS_COORD_TITLE_Y = 28;
    private static final int PASS_TEXTS = 3;
    private static final int PASS_TEXT_PLAYER = 4;
    private static final int PASS_TEXT_TEXT = 3;
    private static final int PASS_TEXT_TITLE = 2;
    private static final int PREVIEW_COORDS = 23;
    private static final int PREVIEW_COORD_ARROW1_X = 6;
    private static final int PREVIEW_COORD_ARROW2_X = 8;
    private static final int PREVIEW_COORD_ARROWS_Y = 10;
    private static final int PREVIEW_COORD_ATRIBARS_W = 21;
    private static final int PREVIEW_COORD_ATRIBARS_X = 20;
    private static final int PREVIEW_COORD_ATRIBUTE1_Y = 18;
    private static final int PREVIEW_COORD_ATRIBUTE2_Y = 19;
    private static final int PREVIEW_COORD_ATRIBUTES_X = 17;
    private static final int PREVIEW_COORD_BOX_H = 3;
    private static final int PREVIEW_COORD_BOX_W = 2;
    private static final int PREVIEW_COORD_BOX_X = 0;
    private static final int PREVIEW_COORD_BOX_Y = 1;
    private static final int PREVIEW_COORD_CART_X = 7;
    private static final int PREVIEW_COORD_CART_Y = 9;
    private static final int PREVIEW_COORD_SCORE_X = 15;
    private static final int PREVIEW_COORD_SCORE_Y = 16;
    private static final int PREVIEW_COORD_TITLE_X = 4;
    private static final int PREVIEW_COORD_TITLE_Y = 5;
    private static final int PREVIEW_COORD_TRACK_H = 14;
    private static final int PREVIEW_COORD_TRACK_W = 13;
    private static final int PREVIEW_COORD_TRACK_X = 11;
    private static final int PREVIEW_COORD_TRACK_Y = 12;
    private static final int PREVIEW_SPRITES = 12;
    private static final int PREVIEW_SPR_ARROW_LEFT = 0;
    private static final int PREVIEW_SPR_ARROW_LEFT_CLICK = 1;
    private static final int PREVIEW_SPR_ARROW_RIGHT = 2;
    private static final int PREVIEW_SPR_ARROW_RIGHT_CLICK = 3;
    private static final int PREVIEW_SPR_ATRIBUTE1 = 7;
    private static final int PREVIEW_SPR_ATRIBUTE2 = 8;
    private static final int PREVIEW_SPR_ATRIBUTE_BAR_EMPTY = 9;
    private static final int PREVIEW_SPR_ATRIBUTE_BAR_FULL = 10;
    private static final int PREVIEW_SPR_CART1 = 4;
    private static final int PREVIEW_SPR_CART2 = 5;
    private static final int PREVIEW_SPR_CART3 = 6;
    private static final int PREVIEW_SPR_CURSOR = 11;
    private static final int PREVIEW_STATE_PASS = 0;
    private static final int PREVIEW_STATE_WAIT = 1;
    private static final int PREVIEW_TEXTS = 2;
    private static final int PREVIEW_TEXT_SCORE = 1;
    private static final int PREVIEW_TEXT_TITLE = 0;
    private static final int PREVIEW_VALUE_TIMER = 22;
    private boolean dragLeft;
    private boolean dragRight;
    private int mCartSelection;
    private int mPreviewState;
    private Profile mProfile;
    private int mSelectionFontHeight;
    private ImageFont mSelectionImageFont;
    private int mTextFontHeight;
    private ImageFont mTextImageFont;
    private ImageFont mTitleBarImageFont;
    private int mTitleFontHeight;
    private String[] mTutorialText;
    private boolean pointerDragged;
    private int xPointerPos = -999;

    public TrackPreview(ImageFont imageFont, ImageFont imageFont2, ImageFont imageFont3, Profile profile) {
        this.mTitleBarImageFont = imageFont;
        this.mSelectionImageFont = imageFont2;
        this.mTextImageFont = imageFont3;
        this.mTitleFontHeight = this.mTitleBarImageFont.getHeight();
        this.mSelectionFontHeight = this.mSelectionImageFont.getHeight();
        this.mTextFontHeight = this.mTextImageFont.getHeight();
        this.mTitleTextId = 87;
        this.mProfile = profile;
        this.mCartSelection = 0;
        ScrollingTextField.resetTimer();
    }

    private boolean cartLeftScroll() {
        this.mCartSelection--;
        if (this.mCartSelection < 0) {
            this.mCartSelection = 2;
        }
        if (this.mCartSelection != 2 || (this.mProfile.mCartUnlocked & 4) != 0 || !this.mProfile.mFirstTimePreviewCarts) {
            return false;
        }
        createTextBox(Toolkit.getText(58));
        resetKeys();
        this.mProfile.mFirstTimePreviewCarts = false;
        return true;
    }

    private boolean cartRightScroll() {
        this.mCartSelection++;
        if (this.mCartSelection > 2) {
            this.mCartSelection = 0;
        }
        if (this.mCartSelection != 1 || (this.mProfile.mCartUnlocked & 2) != 0 || !this.mProfile.mFirstTimePreviewCarts) {
            return false;
        }
        createTextBox(Toolkit.getText(57));
        resetKeys();
        this.mProfile.mFirstTimePreviewCarts = false;
        return true;
    }

    private void drawBar(Graphics graphics, int i, int i2, int i3, SpriteObject spriteObject) {
        spriteObject.setAnimation(0, 0, true);
        spriteObject.draw(graphics, i, i2);
        int width = i3 - spriteObject.getWidth();
        int width2 = spriteObject.getWidth() + i;
        spriteObject.setAnimation(2, 0, true);
        spriteObject.draw(graphics, i + i3, i2);
        int width3 = width - spriteObject.getWidth();
        spriteObject.setAnimation(1, 0, true);
        int width4 = spriteObject.getWidth();
        int i4 = width2;
        for (int i5 = 0; i5 < width3 - width4; i5 += width4) {
            spriteObject.draw(graphics, i4, i2);
            i4 += width4;
        }
        spriteObject.draw(graphics, (i + i3) - (width4 * 2), i2);
    }

    private void loadSprites() {
        if ((this.mProfile.mCartUnlocked & 2) == 0) {
        }
        if ((this.mProfile.mCartUnlocked & 4) == 0) {
        }
        this.mPoolSprite = new SpriteObject[12];
        this.mPoolSprite[0] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_MENU_SCROLL_ARROW_LEFT, false), true);
        this.mPoolSprite[2] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_MENU_SCROLL_ARROW_RIGHT, false), true);
        this.mPoolSprite[1] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_MENU_SCROLL_ARROW_LEFT_CLICK, true), true);
        this.mPoolSprite[3] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_MENU_SCROLL_ARROW_RIGHT_CLICK, true), true);
        this.mPoolSprite[4] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_SELECT_CART_01, true), true);
        this.mPoolSprite[5] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_SELECT_CART_03, true), true);
        this.mPoolSprite[6] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_SELECT_CART_02, true), true);
        this.mPoolSprite[7] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_SELECT_CARTS_WEIGHT, true), false);
        this.mPoolSprite[8] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_SELECT_CARTS_SPEED, true), false);
        this.mPoolSprite[11] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_HUD_TRACK_BAR_STAR, true), true);
        this.mPoolSprite[9] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_P_MAP_POINT_LOW_LOCK, true), false);
    }

    private void loadTexts() throws IOException {
        this.mPoolText = (String[][]) Array.newInstance((Class<?>) String.class, RollerGameEngine.smHotSeatMode == 1 ? ((Game.smNumPlayerToPlay + 3) - 1) + 2 : 2, 1);
        int i = this.mProfile.mSelectedTheme;
        this.mPoolText[0][0] = (i + 1) + " . " + Toolkit.getText(AREA_NAMES_IDS[i]) + " - " + Toolkit.getText(TextIDs.TID_SELECTION_TRACK) + " " + ((DCThrillOld.smLevelIdx - (((DCThrillOld.smLevelIdx / 11) % 9) * 11)) + 1);
        if (RollerGameEngine.smGameMode == 0) {
            int i2 = this.mProfile.mTrackScore[this.mProfile.mSelectedTheme][this.mProfile.mSelectedTrack];
            if (i2 < 10) {
                this.mPoolText[1][0] = Toolkit.getText(TextIDs.TID_RESULTS_BEST_SCORE) + " 000" + i2;
            } else if (i2 < 100) {
                this.mPoolText[1][0] = Toolkit.getText(TextIDs.TID_RESULTS_BEST_SCORE) + " 00" + i2;
            } else if (i2 < 1000) {
                this.mPoolText[1][0] = Toolkit.getText(TextIDs.TID_RESULTS_BEST_SCORE) + " 0" + i2;
            } else {
                this.mPoolText[1][0] = Toolkit.getText(TextIDs.TID_RESULTS_BEST_SCORE) + " " + i2;
            }
        } else if (RollerGameEngine.smGameMode == 2) {
            if (ResultsSurvival.smNumTracksPlayed < 10) {
                this.mPoolText[1][0] = Toolkit.getText(TextIDs.TID_RESULTS_TRACKS) + " 0" + ResultsSurvival.smNumTracksPlayed;
            } else {
                this.mPoolText[1][0] = Toolkit.getText(TextIDs.TID_RESULTS_TRACKS) + " " + ResultsSurvival.smNumTracksPlayed;
            }
        }
        if (RollerGameEngine.smHotSeatMode == 1) {
            for (int i3 = 0; i3 < Game.smNumPlayerToPlay; i3++) {
                this.mPoolText[i3 + 4][0] = Toolkit.getText(130) + " " + (i3 + 1);
            }
        }
        if (this.mProfile.mFirstTimePreview) {
            this.mTutorialText = new String[2];
            String text = Toolkit.getText(TextIDs.TID_TUTORIAL_CART_SELECTION);
            this.mTutorialText[0] = text.substring(0, text.length() - 2);
            this.mTutorialText[1] = Toolkit.getText(162);
        }
    }

    private void previewDrawTrack(Graphics graphics, int i, int i2, int i3, int i4) {
        graphics.setClip(i, i2, i3 + 1, i4 + 1);
        graphics.setColor(3777823);
        graphics.drawRect(i, i2, i3, i4);
        int i5 = i + 1;
        int i6 = i2 + 1;
        int i7 = i3 - 2;
        int i8 = i4 - 2;
        graphics.setColor(9303555);
        graphics.drawRect(i5, i6, i7, i8);
        int i9 = i5 + 1;
        int i10 = i6 + 1;
        int i11 = i7 - 1;
        int i12 = i8 - 1;
        graphics.setColor(467458);
        graphics.fillRect(i9, i10, i11, i12);
        graphics.setClip(i9, i10, i11, i12);
        int i13 = TrackModel.smTrackPointCount * 30;
        int i14 = (((this.mPoolInt[22] % i13) * TrackModel.smTrackPointCount) / i13) % TrackModel.smTrackPointCount;
        int i15 = ((i11 / 2) + i9) - ((TrackModel.smTrackPointX[i14] >> 12) / 15);
        int i16 = ((i12 / 2) + i10) - ((TrackModel.smTrackPointY[i14] >> 12) / 15);
        graphics.setColor(1338376);
        int i17 = 270 / 15;
        for (int i18 = (-i17) / 2; i18 < i11 + i17; i18 += i17) {
            int i19 = i9 + i18 + (i15 % i17);
            graphics.drawLine(i19, i10, i19, i10 + i12);
        }
        for (int i20 = (-i17) / 2; i20 < i12 + i17; i20 += i17) {
            int i21 = i10 + i20 + (i16 % i17);
            graphics.drawLine(i9, i21, i9 + i11, i21);
        }
        int i22 = TrackModel.smTrackPointCount - 1;
        graphics.setColor(16776960);
        graphics.fillRect(((TrackModel.smTrackPointX[0] >> 12) / 15) + i15, (((TrackModel.smTrackPointY[0] >> 12) / 15) + i16) - (15 / 2), 3, 15);
        graphics.fillRect(((TrackModel.smTrackPointX[i22] >> 12) / 15) + i15, (((TrackModel.smTrackPointY[i22] >> 12) / 15) + i16) - (15 / 2), 3, 15);
        for (int i23 = 0; i23 < i22; i23++) {
            if (!TrackModel.isHole(null, i23)) {
                int i24 = ((TrackModel.smTrackPointX[i23] >> 12) / 15) + i15;
                int i25 = ((TrackModel.smTrackPointY[i23] >> 12) / 15) + i16;
                int i26 = ((TrackModel.smTrackPointX[i23 + 1] >> 12) / 15) + i15;
                int i27 = ((TrackModel.smTrackPointY[i23 + 1] >> 12) / 15) + i16;
                graphics.drawLine(i24, i25, i26, i27);
                graphics.drawLine(i24, i25 + 2, i26, i27 + 2);
                graphics.drawLine(i24, i25 + 1, i26, i27 + 1);
            }
        }
        Entity.drawEntitiesPreview(graphics, 15, -i15, i16);
        this.mPoolSprite[11].draw(graphics, i9 + (i11 / 2), i10 + (i12 / 2));
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    protected void calculateCoords() {
        this.mPoolInt = new int[RollerGameEngine.smHotSeatMode == 1 ? 23 + 12 : 23];
        this.mPoolInt[0] = 10;
        this.mPoolInt[2] = this.mCanvasWidth - 20;
        this.mPoolInt[1] = (this.mDrawAreaY + (this.mSeparatorY * 2)) - 0;
        this.mPoolInt[3] = (this.mDrawAreaHeight - (this.mSeparatorY * 4)) + 0;
        this.mPoolInt[4] = (this.mCanvasWidth - this.mSelectionImageFont.stringWidth(this.mPoolText[0][0])) / 2;
        this.mPoolInt[5] = this.mPoolInt[1];
        this.mPoolInt[6] = this.mPoolInt[0] + this.mSeparatorX + this.mPoolSprite[0].getPivotX();
        this.mPoolInt[7] = this.mPoolInt[0] + this.mPoolSprite[0].getWidth() + this.mPoolSprite[4].getPivotX() + (this.mSeparatorX * 2);
        this.mPoolInt[8] = this.mPoolInt[0] + this.mPoolSprite[4].getWidth() + (this.mSeparatorX * 3) + this.mPoolSprite[2].getPivotX() + this.mPoolSprite[0].getWidth();
        this.mPoolInt[9] = this.mPoolInt[5] + this.mSeparatorY + this.mPoolSprite[4].getPivotY() + this.mSelectionFontHeight;
        this.mPoolInt[10] = this.mPoolInt[9];
        this.mPoolInt[18] = this.mPoolInt[5] + this.mSeparatorY + this.mPoolSprite[7].getPivotY() + this.mSelectionFontHeight;
        this.mPoolInt[19] = this.mPoolInt[18] + this.mPoolSprite[7].getHeight() + this.mSeparatorY;
        this.mPoolInt[17] = this.mPoolInt[8] + this.mPoolSprite[2].getWidth() + (this.mSeparatorX * 4) + this.mPoolSprite[8].getPivotX();
        this.mPoolInt[20] = this.mPoolInt[17] + this.mPoolSprite[7].getWidth() + this.mSeparatorX;
        this.mPoolInt[21] = (this.mCanvasWidth - 15) - this.mPoolInt[20];
        this.mPoolInt[11] = this.mPoolInt[0] + this.mSeparatorX;
        this.mPoolInt[12] = this.mPoolInt[9] + (this.mSeparatorY * 2) + this.mPoolSprite[4].getPivotX();
        this.mPoolInt[13] = this.mPoolInt[2] - (this.mSeparatorX * 2);
        this.mPoolInt[14] = ((((this.mPoolInt[3] - this.mPoolInt[12]) - this.mSelectionFontHeight) + this.mPoolInt[1]) - (this.mSeparatorY * 2)) + 0;
        if (RollerGameEngine.smGameMode == 2) {
            this.mPoolInt[15] = (this.mCanvasWidth - this.mTextImageFont.stringWidth(this.mPoolText[1][0])) / 2;
        } else if (RollerGameEngine.smGameMode == 0) {
            this.mPoolInt[15] = (this.mCanvasWidth - this.mSelectionImageFont.stringWidth(this.mPoolText[1][0])) / 2;
        } else {
            this.mPoolInt[14] = ((this.mPoolInt[3] - this.mPoolInt[12]) + this.mPoolInt[1]) - (this.mSeparatorY * 2);
        }
        this.mPoolInt[16] = ((this.mPoolInt[12] + this.mPoolInt[14]) + (this.mSeparatorY * 2)) - 0;
        this.mPoolInt[22] = 0;
        if (RollerGameEngine.smHotSeatMode == 1) {
            this.mPoolInt[25] = this.mCanvasWidth - 20;
            this.mPoolInt[23] = 10;
            this.mPoolInt[26] = this.mTitleFontHeight + (this.mTextFontHeight * (Game.smNumPlayerToPlay + 1)) + (this.mSeparatorY * 2);
            this.mPoolInt[24] = this.mDrawAreaY + ((this.mDrawAreaHeight - this.mPoolInt[26]) / 2);
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void doDraw(Graphics graphics) {
        this.mBackground.doDraw(graphics);
        CustomMenuObject.drawPopup(graphics, this.mPoolInt[0], this.mPoolInt[1], this.mPoolInt[2], this.mPoolInt[3], true);
        ScrollingTextField.draw(graphics, this.mPoolText[0][0], this.mSelectionImageFont, this.mPoolInt[0] + 5, this.mPoolInt[5], this.mPoolInt[2] - 10, this.mSelectionFontHeight);
        this.mPoolSprite[0].draw(graphics, this.mPoolInt[6], this.mPoolInt[10]);
        this.mPoolSprite[2].draw(graphics, this.mPoolInt[8], this.mPoolInt[10]);
        this.mPoolSprite[this.mCartSelection + 4].draw(graphics, this.mPoolInt[7], this.mPoolInt[9]);
        this.mPoolSprite[7].draw(graphics, this.mPoolInt[17], this.mPoolInt[18]);
        this.mPoolSprite[8].draw(graphics, this.mPoolInt[17], this.mPoolInt[19]);
        if ((this.mCartSelection == 1 && (this.mProfile.mCartUnlocked & 2) == 0) || (this.mCartSelection == 2 && (this.mProfile.mCartUnlocked & 4) == 0)) {
            this.mPoolSprite[9].draw(graphics, this.mPoolInt[7], this.mPoolInt[9] - (this.mPoolSprite[9].getHeight() >> 1));
        }
        int i = this.mPoolInt[21];
        int height = this.mPoolSprite[7].getHeight();
        int i2 = this.mPoolInt[20];
        int pivotY = this.mPoolInt[18] - this.mPoolSprite[7].getPivotY();
        graphics.setColor(Statics.TRACK_PREVIEW_BAR_FRAME_COLOR);
        graphics.fillRect(i2, pivotY, i, height);
        int i3 = i2 + 2;
        int i4 = pivotY + 2;
        int i5 = i - 4;
        int i6 = height - 4;
        graphics.setColor(Statics.TRACK_PREVIEW_BAR_EMPTY_COLOR);
        graphics.fillRect(i3, i4, i5, i6);
        int i7 = (this.mPoolInt[21] * Tuner.PREVIEW_ATTRIBUTE_BAR_VALUES[this.mCartSelection][0]) / 100;
        graphics.setColor(Statics.TRACK_PREVIEW_BAR_FULL_COLOR);
        graphics.fillRect(i3, i4, i7, i6);
        int pivotY2 = this.mPoolInt[19] - this.mPoolSprite[7].getPivotY();
        int i8 = pivotY2 + 2;
        graphics.setColor(Statics.TRACK_PREVIEW_BAR_FRAME_COLOR);
        graphics.fillRect(i2, pivotY2, i, height);
        graphics.setColor(Statics.TRACK_PREVIEW_BAR_EMPTY_COLOR);
        graphics.fillRect(i3, i8, i5, i6);
        int i9 = (this.mPoolInt[21] * Tuner.PREVIEW_ATTRIBUTE_BAR_VALUES[this.mCartSelection][1]) / 100;
        graphics.setColor(Statics.TRACK_PREVIEW_BAR_FULL_COLOR);
        graphics.fillRect(i3, i8, i9, i6);
        previewDrawTrack(graphics, this.mPoolInt[11], this.mPoolInt[12], this.mPoolInt[13], this.mPoolInt[14]);
        graphics.setClip(0, 0, this.mCanvasWidth, this.mCanvasHeight);
        if (RollerGameEngine.smGameMode == 2) {
            ScrollingTextField.draw(graphics, this.mPoolText[1][0], this.mTextImageFont, this.mPoolInt[0] + 5, this.mPoolInt[16], this.mPoolInt[2] - 10, this.mSelectionFontHeight);
        } else if (RollerGameEngine.smGameMode == 0) {
            this.mSelectionImageFont.drawString(graphics, this.mPoolText[1][0], this.mPoolInt[15], this.mPoolInt[16], 20);
        }
        if (this.mPreviewState == 0) {
            CustomMenuObject.drawPopup(graphics, this.mPoolInt[23], this.mPoolInt[24], this.mPoolInt[25], this.mPoolInt[26], true);
            this.mTitleBarImageFont.drawString(graphics, this.mPoolText[2][0], this.mPoolInt[27], this.mPoolInt[28], 20);
            ScrollingTextField.draw(graphics, this.mPoolText[3][0], this.mTextImageFont, this.mPoolInt[0] + 5, this.mPoolInt[30], this.mPoolInt[2] - 10, this.mSelectionFontHeight);
            int i10 = this.mPoolInt[32];
            int i11 = 0;
            while (true) {
                int i12 = i11;
                int i13 = i10;
                if (i12 >= Game.smNumPlayerToPlay) {
                    break;
                }
                this.mTextImageFont.drawString(graphics, this.mPoolText[i12 + 4][0], this.mPoolInt[31], i13, 20);
                numbersDraw(graphics, this.mTextImageFont, ResultsHotSeat.smScore[i12], 5, this.mPoolInt[33], i13, false);
                i10 = this.mTextFontHeight + i13;
                i11 = i12 + 1;
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

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void keyEventOccurred(int i, int i2) {
        if (this.mTextBox != null) {
            this.mTextBox.keyEventOccurred(i, i2);
        }
        super.keyEventOccurred(i, i2);
        Cheats.keyEventOccurred(this.mKeys);
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void load(int i) throws IOException {
        if (i == 1) {
            loadTexts();
            return;
        }
        if (i == 2) {
            loadSprites();
            return;
        }
        if (i == 3) {
            this.mUpdateSoftKeys = true;
            loadBackground();
            this.mBackground.setStyle(2);
            this.mBackground.setSize(this.mCanvasWidth, this.mCanvasHeight);
            calculateCoords();
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
        this.mPoolSprite[this.mCartSelection + 4].logicUpdate(i);
        ScrollingTextField.logicUpdate(i);
        if (this.mProfile.mFirstTimePreview) {
            this.mProfile.mFirstTimePreview = false;
            createTextBox(this.mTutorialText);
            return 0;
        }
        if (this.mPreviewState == 1) {
            int[] iArr = this.mPoolInt;
            iArr[22] = iArr[22] + i;
            this.mPoolSprite[0].logicUpdate(i);
            this.mPoolSprite[2].logicUpdate(i);
            this.mPoolSprite[11].logicUpdate(i);
            if (this.dragLeft) {
                this.dragLeft = false;
                if (cartLeftScroll()) {
                    return 0;
                }
            } else if (this.dragRight) {
                this.dragRight = false;
                if (cartRightScroll()) {
                    return 0;
                }
            }
            if (this.mPressedSK == 5) {
                return 14;
            }
            if (this.mPressedSK == 1 || (this.mKeys & 16) != 0) {
                if (this.mCartSelection == 1 && (this.mProfile.mCartUnlocked & 2) == 0) {
                    createTextBox(Toolkit.getText(57));
                    resetKeys();
                    return 0;
                }
                if (this.mCartSelection == 2 && (this.mProfile.mCartUnlocked & 4) == 0) {
                    createTextBox(Toolkit.getText(58));
                    resetKeys();
                    return 0;
                }
                resetKeys();
                DCThrillOld.smSelectedCart = this.mCartSelection;
                return 13;
            }
            if ((this.mKeys & 1) != 0) {
                if (cartLeftScroll()) {
                    return 0;
                }
            } else if ((this.mKeys & 2) != 0 && cartRightScroll()) {
                return 0;
            }
            resetKeys();
        } else if (this.mPressedSK == 1 || (this.mKeys & 16) != 0) {
            this.mPreviewState = 1;
            this.mUpdateSoftKeys = true;
            resetKeys();
        }
        return DCThrillOld.logicUpdate(i);
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void pointerEventOccurred(int i, int i2, int i3) {
        super.pointerEventOccurred(i, i2, i3);
        int pivotX = this.mPoolInt[6] - this.mPoolSprite[0].getPivotX();
        int pivotX2 = this.mPoolInt[8] - this.mPoolSprite[0].getPivotX();
        int pivotY = this.mPoolInt[10] - this.mPoolSprite[0].getPivotY();
        int width = this.mPoolSprite[0].getWidth() + 20;
        int height = this.mPoolSprite[0].getHeight() + 20;
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

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void setVisible() {
        super.setVisible();
        this.mPreviewState = 1;
        if (RollerGameEngine.smHotSeatMode != 1) {
            if (RollerGameEngine.smHotSeatMode == 0) {
                createTextBox(Toolkit.replaceParameters(Toolkit.getText(TextIDs.TID_HOTSEAT_PASS), new String[]{"" + (ResultsHotSeat.smPlayerIndex + 1)}));
                return;
            }
            return;
        }
        this.mPreviewState = 0;
        this.mPoolText[2][0] = Toolkit.getText(53) + " " + (ResultsHotSeat.smTimesPlayed + 1) + " / 5";
        this.mPoolText[3][0] = Toolkit.replaceParameters(Toolkit.getText(TextIDs.TID_HOTSEAT_PASS), new String[]{"" + (ResultsHotSeat.smPlayerIndex + 1)});
        this.mPoolInt[27] = (this.mCanvasWidth - this.mTitleBarImageFont.stringWidth(this.mPoolText[2][0])) / 2;
        this.mPoolInt[28] = this.mPoolInt[24] + this.mSeparatorY;
        this.mPoolInt[29] = (this.mCanvasWidth - this.mTextImageFont.stringWidth(this.mPoolText[3][0])) / 2;
        this.mPoolInt[30] = this.mPoolInt[28] + this.mTitleFontHeight;
        this.mPoolInt[31] = this.mPoolInt[23] + 5;
        this.mPoolInt[32] = this.mPoolInt[30] + this.mTextFontHeight;
        this.mPoolInt[33] = ((this.mPoolInt[23] + this.mPoolInt[25]) - 5) - (this.mTextImageFont.stringWidth(GameScreens.NUMBERS_WIDTH_ONE_DIGIT) * 5);
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    protected void updateSoftKeys() {
        Game.removeAllSoftKeys();
        if (this.mTextBox != null) {
            Game.setSoftKey(1, 0);
            return;
        }
        if (this.mPreviewState != 1) {
            Game.setSoftKey(1, 0);
            return;
        }
        Game.setSoftKey(1, 0);
        if (RollerGameEngine.smGameMode == 0 || ((RollerGameEngine.smGameMode == 2 && ResultsSurvival.smNumTracksPlayed == 0) || (RollerGameEngine.smGameMode == 1 && ResultsHotSeat.smPlayerIndex == 0 && ResultsHotSeat.smTimesPlayed == 0))) {
            Game.setSoftKey(5, 0);
        }
    }
}
