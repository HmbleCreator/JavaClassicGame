package com.digitalchocolate.androidrollergapp;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class AwardsMenu extends GameScreens {
    private static final int AWARDS_COORDS = 6;
    private static final int AWARDS_COORD_BOX_H = 3;
    private static final int AWARDS_COORD_BOX_W = 2;
    private static final int AWARDS_COORD_BOX_X = 0;
    private static final int AWARDS_COORD_BOX_Y = 1;
    private static final int AWARDS_COORD_CELL_H = 5;
    private static final int AWARDS_COORD_CELL_W = 4;
    private static final int[] AWARDS_IDS = {ResourceIDs.ANM_R_A_ACHIEVEMENTS_LOW_01, ResourceIDs.ANM_R_A_ACHIEVEMENTS_LOW_02, ResourceIDs.ANM_R_A_ACHIEVEMENTS_LOW_03, ResourceIDs.ANM_R_A_ACHIEVEMENTS_LOW_04, ResourceIDs.ANM_R_A_ACHIEVEMENTS_LOW_05, ResourceIDs.ANM_R_A_ACHIEVEMENTS_LOW_06, ResourceIDs.ANM_R_A_ACHIEVEMENTS_LOW_07, ResourceIDs.ANM_R_A_ACHIEVEMENTS_LOW_08, ResourceIDs.ANM_R_A_ACHIEVEMENTS_LOW_09, ResourceIDs.ANM_R_A_ACHIEVEMENTS_LOW_10, ResourceIDs.ANM_R_A_ACHIEVEMENTS_LOW_11, ResourceIDs.ANM_R_A_ACHIEVEMENTS_LOW_12, ResourceIDs.ANM_R_A_ACHIEVEMENTS_LOW_13, ResourceIDs.ANM_R_A_ACHIEVEMENTS_LOW_14, ResourceIDs.ANM_R_A_ACHIEVEMENTS_LOW_15, ResourceIDs.ANM_R_A_ACHIEVEMENTS_LOW_16, ResourceIDs.ANM_R_A_ACHIEVEMENTS_LOW_17, ResourceIDs.ANM_R_A_ACHIEVEMENTS_LOW_18, ResourceIDs.ANM_R_A_ACHIEVEMENTS_LOW_19, ResourceIDs.ANM_R_A_ACHIEVEMENTS_LOW_20, ResourceIDs.ANM_R_A_ACHIEVEMENTS_LOW_21};
    private static final int AWARDS_SPRITES = 26;
    private static final int AWARDS_SPR_ARROW_DOWN = 23;
    private static final int AWARDS_SPR_ARROW_LEFT = 24;
    private static final int AWARDS_SPR_ARROW_RIGHT = 25;
    private static final int AWARDS_SPR_ARROW_UP = 22;
    private static final int AWARDS_SPR_NOAWARD = 21;
    private static final int NUM_AWARDS = 21;
    private static final int NUM_COLUMNS = 7;
    private static final int NUM_ROWS = 3;
    private int iconHeight;
    private int iconWidth;
    private int mCurrentColumn;
    private int mCurrentRow;
    public int mCurrentSelection;
    private Profile mProfile;
    private int mSelectionFontHeight;
    private ImageFont mSelectionImageFont;
    private int mTextFontHeight;
    private ImageFont mTextImageFont;
    private ImageFont mTitleBarImageFont;
    private int mTitleFontHeight;
    private boolean pointerDragged;
    private int xPointerPos;
    private int yPointerPos;

    public AwardsMenu(ImageFont imageFont, ImageFont imageFont2, ImageFont imageFont3, Profile profile) {
        this.mTitleBarImageFont = imageFont;
        this.mSelectionImageFont = imageFont2;
        this.mTextImageFont = imageFont3;
        this.mTitleFontHeight = this.mTitleBarImageFont.getHeight();
        this.mSelectionFontHeight = this.mSelectionImageFont.getHeight();
        this.mTextFontHeight = this.mTextImageFont.getHeight();
        this.mProfile = profile;
        this.mTitleTextId = 77;
    }

    private void loadSprites() {
        this.mPoolSprite = new SpriteObject[26];
        for (int i = 0; i < 21; i++) {
            this.mPoolSprite[i] = new SpriteObject(DavinciUtilities.loadAnimation(AWARDS_IDS[i], true), false);
        }
        this.mPoolSprite[21] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_A_ACHIEVEMENTS_LOW_LING, true), false);
        this.mPoolSprite[22] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_MENU_SCROLL_ARROW_UP, true), true);
        this.mPoolSprite[23] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_MENU_SCROLL_ARROW_DOWN, true), true);
        this.mPoolSprite[24] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_MENU_SCROLL_ARROW_LEFT, true), true);
        this.mPoolSprite[25] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_MENU_SCROLL_ARROW_RIGHT, true), true);
    }

    private void scrollDown() {
        this.mCurrentRow++;
        if (this.mCurrentRow > 2) {
            this.mCurrentRow = 0;
        }
    }

    private void scrollLeft() {
        this.mCurrentColumn--;
        if (this.mCurrentColumn < 0) {
            this.mCurrentColumn = 6;
        }
    }

    private void scrollRight() {
        this.mCurrentColumn++;
        if (this.mCurrentColumn > 6) {
            this.mCurrentColumn = 0;
        }
    }

    private void scrollUp() {
        this.mCurrentRow--;
        if (this.mCurrentRow < 0) {
            this.mCurrentRow = 2;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    protected void calculateCoords() {
        this.mPoolInt = new int[6];
        this.iconWidth = 0;
        this.iconHeight = 0;
        for (int i = 0; i <= 21; i++) {
            int width = this.mPoolSprite[i].getWidth();
            int height = this.mPoolSprite[i].getHeight();
            if (this.iconWidth < width) {
                this.iconWidth = width;
            }
            if (this.iconHeight < height) {
                this.iconHeight = height;
            }
        }
        this.iconHeight += 5;
        this.mPoolInt[4] = this.iconWidth + 5;
        this.mPoolInt[5] = this.iconHeight + 5;
        this.mPoolInt[2] = this.mPoolInt[4] * 7;
        this.mPoolInt[3] = this.mPoolInt[5] * 3;
        this.mPoolInt[0] = (this.mCanvasWidth - this.mPoolInt[2]) / 2;
        this.mPoolInt[1] = this.mDrawAreaY + ((this.mDrawAreaHeight - this.mPoolInt[3]) / 2);
        this.mCurrentRow = 0;
        this.mCurrentColumn = 0;
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void doDraw(Graphics graphics) {
        this.mBackground.doDraw(graphics);
        CustomMenuObject.drawPopup(graphics, this.mPoolInt[0], this.mPoolInt[1], this.mPoolInt[2], this.mPoolInt[3], true);
        int i = this.mPoolInt[0] + this.mPoolInt[4];
        graphics.setColor(0);
        int i2 = this.mPoolInt[4] / 2;
        int i3 = this.mPoolInt[5] / 2;
        int i4 = i;
        for (int i5 = 0; i5 < 6; i5++) {
            graphics.drawLine(i4, this.mPoolInt[1], i4, this.mPoolInt[1] + this.mPoolInt[3]);
            i4 += this.mPoolInt[4];
        }
        int i6 = this.mPoolInt[1] + this.mPoolInt[5];
        for (int i7 = 0; i7 < 2; i7++) {
            graphics.drawLine(this.mPoolInt[0], i6, this.mPoolInt[0] + this.mPoolInt[2], i6);
            i6 += this.mPoolInt[5];
        }
        int i8 = this.mPoolInt[0];
        int i9 = this.mPoolInt[1];
        graphics.setColor(11265024);
        graphics.fillRect((this.mPoolInt[4] * this.mCurrentColumn) + i8 + 1, (this.mPoolInt[5] * this.mCurrentRow) + i9 + 1, this.mPoolInt[4] - 1, this.mPoolInt[5] - 1);
        int i10 = i8 + i2;
        int i11 = i3 + i9;
        for (int i12 = 0; i12 < 3; i12++) {
            int i13 = i10;
            for (int i14 = 0; i14 < 7; i14++) {
                int i15 = (i12 * 7) + i14;
                if (this.mProfile.mAwardsUnlocked[i15]) {
                    this.mPoolSprite[i15].draw(graphics, i13, i11);
                } else {
                    this.mPoolSprite[21].draw(graphics, i13, i11);
                }
                i13 += this.mPoolInt[4];
            }
            i11 += this.mPoolInt[5];
            i10 = i8 + i2;
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
            loadBackground();
        } else if (i == 2) {
            calculateCoords();
            this.mUpdateSoftKeys = true;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public int logicUpdate(int i) throws IOException {
        super.logicUpdate(i);
        if (this.mPressedSK == 5) {
            resetKeys();
            return 52;
        }
        if (this.mPressedSK == 0 || (this.mKeys & 16) != 0) {
            resetKeys();
            return 53;
        }
        if ((this.mKeys & 1) != 0) {
            scrollLeft();
        } else if ((this.mKeys & 2) != 0) {
            scrollRight();
        } else if ((this.mKeys & 4) != 0) {
            scrollUp();
        } else if ((this.mKeys & 8) != 0) {
            scrollDown();
        }
        this.mCurrentSelection = (this.mCurrentRow * 7) + this.mCurrentColumn;
        resetKeys();
        return 0;
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void pointerEventOccurred(int i, int i2, int i3) {
        int i4;
        super.pointerEventOccurred(i, i2, i3);
        int i5 = this.mPoolInt[0];
        int i6 = this.mPoolInt[1];
        int i7 = this.mPoolInt[4] / 2;
        int i8 = this.mPoolInt[5] / 2;
        int i9 = (this.mPoolInt[4] * this.mCurrentColumn) + i5 + 1;
        int i10 = (this.mPoolInt[5] * this.mCurrentRow) + i6 + 1;
        int i11 = this.mPoolInt[4] - 1;
        int i12 = this.mPoolInt[5] - 1;
        if (i3 != 0 || i < i5 || i > this.mPoolInt[2] + i5 || i2 < i6 || i2 > this.mPoolInt[3] + i6) {
            i4 = 0;
        } else {
            int i13 = (i - i5) / i11;
            int i14 = (i2 - i6) / i12;
            if (this.mCurrentColumn == i13 && this.mCurrentRow == i14) {
                i4 = 16;
            } else {
                if (i13 > 6) {
                    this.mCurrentColumn = 6;
                } else if (i13 < 0) {
                    this.mCurrentColumn = 0;
                } else {
                    this.mCurrentColumn = i13;
                }
                if (i14 > 2) {
                    this.mCurrentRow = 2;
                    i4 = 0;
                } else if (i14 < 0) {
                    this.mCurrentRow = 0;
                    i4 = 0;
                } else {
                    this.mCurrentRow = i14;
                    i4 = 0;
                }
            }
        }
        if (i3 == 0) {
            this.mKeys = i4 | this.mKeys;
        } else if (i3 == 1) {
            this.mKeys = (i4 ^ (-1)) & this.mKeys;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    protected void updateSoftKeys() {
        Game.removeAllSoftKeys();
        Game.setSoftKey(0, 0);
        Game.setSoftKey(5, 0);
    }
}
