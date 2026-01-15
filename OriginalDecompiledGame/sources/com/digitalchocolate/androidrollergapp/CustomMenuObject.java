package com.digitalchocolate.androidrollergapp;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* loaded from: classes.dex */
public class CustomMenuObject extends MenuObject {
    private static final int BG_ENHANCED_LAYERS_COUNT = 5;
    private static final int DOWN = 5;
    private static final int DOWN_LEFT = 6;
    private static final int DOWN_RIGHT = 4;
    private static final int LEFT = 7;
    public static final int PC_PITCH_CONTENT_HEIGHT = 480;
    public static final int PC_PITCH_CONTENT_WIDTH = 640;
    public static final int PC_PITCH_CONTENT_X = 80;
    public static final int PC_PITCH_CONTENT_Y = 60;
    public static final int PC_PITCH_SCREEN_HEIGHT = 600;
    public static final int PC_PITCH_SCREEN_WIDTH = 800;
    private static final int RIGHT = 3;
    private static final int UP = 1;
    private static final int UP_LEFT = 0;
    private static final int UP_RIGHT = 2;
    public static SpriteObject[] smBackground;
    private static int smBackgroundOffXFP;
    private static SpriteObject smBackgroundSides;
    private static SpriteObject smPopupGfxGreen;
    private static SpriteObject smPopupGfxOrange;
    private static SpriteObject smTitleSprite;
    public Image mTitleLogo;

    public static void alphaFillDraw(Graphics graphics, int i, int i2, int i3, int i4, int i5) {
        int[] iArr = new int[i4];
        for (int i6 = 0; i6 < i4; i6++) {
            iArr[i6] = i;
        }
        graphics.setClip(i2, i3, i4, i5);
        int i7 = i3 + 1;
        graphics.drawRGB(iArr, 0, 0, i2, i3, i4, i5, true);
    }

    public static void destroyMenuResources() {
        if (smBackgroundSides != null) {
            smBackgroundSides.freeResources();
            smBackgroundSides = null;
        }
        if (smBackground != null) {
            int length = smBackground.length;
            for (int i = 0; i < length; i++) {
                smBackground[i].freeResources();
                smBackground[i] = null;
            }
        }
        smBackground = null;
    }

    public static void drawGradientRectAlpha(Graphics graphics, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        if (i5 == i6) {
            graphics.setColor(i5);
            graphics.fillRect(i, i2, i3, i4);
            return;
        }
        int i8 = (16711680 & i5) >> 16;
        int i9 = (65280 & i5) >> 8;
        int i10 = i5 & 255;
        int i11 = ((16711680 & i6) >> 16) - i8;
        int i12 = ((65280 & i6) >> 8) - i9;
        int i13 = (i6 & 255) - i10;
        int i14 = i7;
        int i15 = 0;
        while (i15 < i4) {
            if (i15 + i14 > i4) {
                i14 = i4 - i15;
            }
            int i16 = i14;
            int i17 = ((((i15 * i13) / (i4 - 1)) + i10) & 255) | (((((i15 * i11) / (i4 - 1)) + i8) & 255) << 16) | (-1728053248) | (((((i15 * i12) / (i4 - 1)) + i9) & 255) << 8);
            graphics.setColor(i17);
            alphaFillDraw(graphics, i17, i, i2 + i15, i3, i16);
            i15 += i16;
            i14 = i16;
        }
        graphics.setClip(0, 0, Toolkit.getScreenWidth(), Toolkit.getScreenHeight());
    }

    public static void drawGradientRectVertical(Graphics graphics, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        if (i5 == i6) {
            graphics.setColor(i5);
            graphics.fillRect(i, i2, i3, i4);
            return;
        }
        int i8 = (16711680 & i5) >> 16;
        int i9 = (65280 & i5) >> 8;
        int i10 = i5 & 255;
        int i11 = ((16711680 & i6) >> 16) - i8;
        int i12 = ((65280 & i6) >> 8) - i9;
        int i13 = (i6 & 255) - i10;
        int i14 = i7;
        for (int i15 = 0; i15 < i3; i15 += i14) {
            if (i15 + i14 > i3) {
                i14 = i3 - i15;
            }
            graphics.setColor(((i15 * i11) / (i3 - 1)) + i8, ((i15 * i12) / (i3 - 1)) + i9, ((i15 * i13) / (i3 - 1)) + i10);
            graphics.fillRect(i + i15, i2, i14, i4);
        }
    }

    public static void drawPopup(Graphics graphics, int i, int i2, int i3, int i4, boolean z) {
        SpriteObject spriteObject;
        graphics.setClip(0, 0, Toolkit.getScreenWidth(), Toolkit.getScreenHeight());
        if (z) {
            drawGradientRect(graphics, i, i2, i3, i4, 1141255, 6988587, 3);
            spriteObject = smPopupGfxGreen;
        } else {
            drawGradientRect(graphics, i, i2, i3, i4, 1141255, 6988587, 3);
            spriteObject = smPopupGfxOrange;
        }
        spriteObject.setAnimation(0, 0, true);
        spriteObject.draw(graphics, i, i2);
        for (int width = 0; width < i3 - spriteObject.getWidth(); width += spriteObject.getWidth()) {
            spriteObject.setAnimation(1, 0, true);
            spriteObject.draw(graphics, width + i, i2);
            spriteObject.setAnimation(5, 0, true);
            spriteObject.draw(graphics, width + i, i2 + i4);
        }
        spriteObject.setAnimation(1, 0, true);
        spriteObject.draw(graphics, (i3 + i) - spriteObject.getWidth(), i2);
        spriteObject.setAnimation(5, 0, true);
        spriteObject.draw(graphics, (i3 + i) - spriteObject.getWidth(), i2 + i4);
        spriteObject.setAnimation(7, 0, true);
        for (int height = 0; height < i4 - spriteObject.getHeight(); height += spriteObject.getHeight()) {
            spriteObject.setAnimation(7, 0, true);
            spriteObject.draw(graphics, i, height + i2);
            spriteObject.setAnimation(3, 0, true);
            spriteObject.draw(graphics, i + i3, height + i2);
        }
        spriteObject.setAnimation(7, 0, true);
        spriteObject.draw(graphics, i, (i2 + i4) - spriteObject.getHeight());
        spriteObject.setAnimation(3, 0, true);
        spriteObject.draw(graphics, i + i3, (i2 + i4) - spriteObject.getHeight());
        spriteObject.setAnimation(2, 0, true);
        spriteObject.draw(graphics, i + i3, i2);
        spriteObject.setAnimation(4, 0, true);
        spriteObject.draw(graphics, i3 + i, i2 + i4);
        spriteObject.setAnimation(6, 0, true);
        spriteObject.draw(graphics, i, i2 + i4);
    }

    public static int drawText(Graphics graphics, String[] strArr, ImageFont imageFont, int i, int i2, int i3) {
        if (strArr == null) {
            return 0;
        }
        int i4 = 0;
        int height = i2;
        while (i4 < strArr.length) {
            imageFont.drawString(graphics, strArr[i4], i, height, i3);
            i4++;
            height = imageFont.getHeight() + height;
        }
        return strArr.length;
    }

    public static void loadMenuResources() {
        if (smTitleSprite == null) {
            smTitleSprite = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_R_M_BACKGROUND_TITTLE_LEFT, ResourceIDs.ANM_R_M_BACKGROUND_TITTLE_CENTER, ResourceIDs.ANM_R_M_BACKGROUND_TITTLE_RIGHT}, true), false);
        }
        if (smBackgroundSides == null) {
            smBackgroundSides = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_R_M_BACKGROUND_MENU_LEFT, ResourceIDs.ANM_R_M_BACKGROUND_MENU_RIGHT}, true), false);
        }
        if (smPopupGfxGreen == null) {
            smPopupGfxGreen = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_R_M_POPUP_TOP_LEFT, ResourceIDs.ANM_R_M_POPUP_TOP, ResourceIDs.ANM_R_M_POPUP_TOP_RIGHT, ResourceIDs.ANM_R_M_POPUP_RIGHT, ResourceIDs.ANM_R_M_POPUP_DOWN_RIGHT, ResourceIDs.ANM_R_M_POPUP_DOWN, ResourceIDs.ANM_R_M_POPUP_DOWN_LEFT, ResourceIDs.ANM_R_M_POPUP_LEFT}, true), false);
            smPopupGfxOrange = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_R_M_POPUP_TOP_LEFT_01, ResourceIDs.ANM_R_M_POPUP_TOP_01, ResourceIDs.ANM_R_M_POPUP_TOP_RIGHT_01, ResourceIDs.ANM_R_M_POPUP_RIGHT_01, ResourceIDs.ANM_R_M_POPUP_DOWN_RIGHT_01, ResourceIDs.ANM_R_M_POPUP_DOWN_01, ResourceIDs.ANM_R_M_POPUP_DOWN_LEFT_01, ResourceIDs.ANM_R_M_POPUP_LEFT_01}, true), false);
        }
        if (smBackground == null) {
            smBackground = new SpriteObject[1];
            smBackground[0] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_M_BACKGROUND_MENU_CENTER, false), true);
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.MenuObject
    protected void calculateItemLayout(boolean z) {
        int i;
        boolean z2;
        int i2;
        boolean z3;
        int i3;
        if (this.mHasTitleBar) {
            i = this.mTitleBarHeight;
            if (this.mTitleBarsOnTop) {
                i += this.mScreenYMargin;
            }
        } else {
            i = this.mScreenYMargin;
        }
        this.mItemAreaY = i;
        int i4 = (this.mScreenMinY + this.mScreenMaxHeight) - this.mScreenSoftkeyAreaHeight;
        int i5 = this.mY + this.mHeight;
        int iMin = Math.min((i5 - this.mItemAreaY) - this.mScreenYMargin, i4 - this.mItemAreaY);
        int iMin2 = (Math.min((i5 - this.mScreenYMargin) - smScrollArrowDownSpace, i4 - smScrollArrowDownSpace) - this.mItemAreaY) - smScrollArrowUpSpace;
        boolean z4 = false;
        if (this.mScreenType == 0) {
            int i6 = 0;
            z2 = false;
            i2 = 0;
            for (int i7 = 0; i7 < this.mMaxItemCount; i7++) {
                Image[] imageArr = this.mItemIcons[i7];
                if (imageArr != null) {
                    int iMax = i6;
                    int iMax2 = i2;
                    for (int i8 = 0; i8 < imageArr.length; i8++) {
                        if (imageArr[i8] != null) {
                            int i9 = 5 << 1;
                            iMax = Math.max(iMax, imageArr[i8].getWidth() + 10);
                            iMax2 = Math.max(iMax2, imageArr[i8].getHeight() + 10);
                        }
                    }
                    i2 = iMax2;
                    i6 = iMax;
                }
                SpriteObject spriteObject = this.mItemSprites[i7];
                if (spriteObject != null) {
                    int iMax3 = i6;
                    int iMax4 = i2;
                    for (int i10 = 0; i10 < spriteObject.getAnimationCount(); i10++) {
                        spriteObject.setAnimation(i10, -1, true);
                        int i11 = 5 << 1;
                        iMax3 = Math.max(iMax3, spriteObject.getWidth() + 10);
                        iMax4 = Math.max(iMax4, spriteObject.getHeight() + 10);
                    }
                    i2 = iMax4;
                    i6 = iMax3;
                }
                z4 |= this.mItemTypes[i7] == 3;
                z2 |= this.mItemTypes[i7] == 4 || this.mItemTypes[i7] == 5;
                if (this.mItemTypes[i7] == 5) {
                    Image[] imageArr2 = this.mSwitchItemIcons[i7];
                    if (imageArr2 != null) {
                        int iMax5 = i2;
                        for (int i12 = 0; i12 < imageArr2.length; i12++) {
                            if (imageArr2[i12] != null) {
                                iMax5 = Math.max(iMax5, imageArr2[i12].getHeight() + 10);
                            }
                        }
                        i2 = iMax5;
                    }
                    SpriteObject spriteObject2 = this.mSwitchItemSprites[i7];
                    if (spriteObject2 != null) {
                        int iMax6 = i2;
                        for (int i13 = 0; i13 < spriteObject2.getAnimationCount(); i13++) {
                            spriteObject2.setAnimation(i13, -1, true);
                            iMax6 = Math.max(iMax6, spriteObject2.getHeight() + 10);
                        }
                        i2 = iMax6;
                    }
                }
            }
            z3 = false;
            i3 = i6;
        } else if (this.mScreenType == 1) {
            boolean z5 = false;
            for (int i14 = 0; i14 < this.mMaxItemCount; i14++) {
                z5 |= this.mItemTypes[i14] == 6;
            }
            i3 = 0;
            z3 = z5;
            z2 = false;
            i2 = 0;
        } else {
            i3 = 0;
            z3 = false;
            z2 = false;
            i2 = 0;
        }
        this.mItemBounds = new int[this.mMaxItemCount * 4];
        this.mItemImageAreaWidths = new int[this.mMaxItemCount];
        this.mItemTextAreaWidths = new int[this.mMaxItemCount];
        if (this.mScreenType == 1) {
            this.mItemCornerRows = new int[this.mMaxItemCount];
        }
        if (z2) {
            this.mSettingItemImageAreaWidths = new int[this.mMaxItemCount];
            this.mSettingItemTextAreaWidths = new int[this.mMaxItemCount];
        }
        if (z3) {
            this.mTableItemColumnWidths = new int[this.mMaxItemCount][];
            this.mTableItemRowHeights = new int[this.mMaxItemCount][];
            this.mTableItemTexts = new String[this.mMaxItemCount][][];
        }
        this.mSelectionItemHeight = Math.max(i2, this.mSelectionFontHeight + 10);
        if (z4) {
            this.mInputBoxHeight = this.mTextFontHeight + 5 + 5;
            this.mSelectionItemHeight += this.mInputBoxHeight;
        }
        calculateItemSizes(i3, i2, z);
        for (int i15 = 0; i15 < this.mMaxItemCount; i15++) {
            if ((0 != 0 && (this.mItemAlignments[i15] & 1) != 0) || (0 == 0 && (this.mItemAlignment & 1) != 0)) {
                this.mItemBounds[(i15 * 4) + 0] = (this.mItemAreaWidth - this.mItemBounds[(i15 * 4) + 2]) >> 1;
            }
        }
        boolean z6 = false;
        do {
            if (z6) {
                this.mItemAreaY = smScrollArrowUpSpace + i;
                this.mItemAreaHeight = iMin2;
                this.mScrollArrowX = this.mWidth >> 1;
                this.mScrollArrowUpY = (this.mItemAreaY - smScrollArrowUpSpace) + (smScrollArrowUpSpace >> 1);
                this.mScrollArrowDownY = this.mItemAreaY + this.mItemAreaHeight + (smScrollArrowDownSpace >> 1);
            } else {
                this.mItemAreaHeight = iMin;
            }
            if (this.mScreenType == 1) {
                this.mItemAreaY += 2;
                this.mItemAreaHeight -= 5;
                if (!z) {
                    int i16 = this.mTextFontHeight + 0;
                    this.mItemAreaY += (this.mItemAreaHeight % i16) >> 1;
                    this.mItemAreaHeight = i16 * (this.mItemAreaHeight / i16);
                }
            }
            int i17 = 0;
            for (int i18 = 0; i18 < this.mMaxItemCount; i18++) {
                this.mItemBounds[(i18 * 4) + 1] = i17;
                i17 += this.mItemBounds[(i18 * 4) + 3];
                if (this.mScreenType == 1 && i17 != 0) {
                    i17 = (((i17 - 1) / (this.mTextFontHeight + 0)) + 1) * (this.mTextFontHeight + 0);
                }
            }
            if (this.mMaxItemCount > 0) {
                this.mScrollingAreaHeight = this.mItemBounds[((this.mMaxItemCount - 1) * 4) + 1] + this.mItemBounds[((this.mMaxItemCount - 1) * 4) + 3];
            }
            if (z6) {
                z6 = false;
            } else if (this.mScrollingAreaHeight > this.mItemAreaHeight) {
                z6 = true;
            } else if (!z && this.mLooseItemAreaAlignment) {
                if (this.mScreenType == 0 && iMin2 / this.mSelectionItemHeight >= 3) {
                    z6 = true;
                } else if (this.mScreenType == 1 && iMin2 / (this.mTextFontHeight + 0) >= 3) {
                    z6 = true;
                }
            }
        } while (z6);
    }

    @Override // com.digitalchocolate.androidrollergapp.MenuObject
    protected void calculateTitleBarLayout(boolean z) {
        int width;
        boolean z2;
        int iMax;
        int i;
        if (!this.mHasTitleBar) {
            this.mTitleBarHeight = 0;
            return;
        }
        int i2 = this.mStyle != 5 ? 39 : 0;
        if (this.mTitleBarImage != null) {
            width = this.mTitleBarImage.getWidth() + 10;
            this.mTitleBarImage.getHeight();
        } else {
            width = 0;
        }
        if (this.mTitleBarSprite != null) {
            width = this.mTitleBarSprite.getWidth() + 10;
            this.mTitleBarSprite.getHeight();
        }
        this.mTitleBarImageAreaWidth = width;
        if (this.mTitleLogo != null) {
            this.mTitleBarHeight = this.mTitleLogo.getHeight() - 20;
            if (Toolkit.getScreenHeight() - (this.mTitleBarHeight + Toolkit.getSoftKeyAreaHeight()) >= (smScrollArrowDownSpace * 2) + (this.mSelectionFontHeight * 3)) {
                i2 = 0;
            } else if (smTitleSprite != null) {
                smTitleSprite.setAnimation(0, 0, true);
                this.mTitleBarHeight = smTitleSprite.getHeight() + 5;
                this.mTitleLogo = null;
            }
        } else if (smTitleSprite != null) {
            smTitleSprite.setAnimation(0, 0, true);
            this.mTitleBarHeight = smTitleSprite.getHeight() + 5;
        }
        int i3 = this.mTitleBarsOnTop ? this.mScreenMaxWidth : this.mWidth;
        int i4 = (i3 - ((i2 + 5) * 2)) - width;
        if (this.mTitleBarSourceText != null) {
            this.mTitleBarText = new String[]{this.mTitleBarSourceText};
        }
        if (this.mTitleBarText != null) {
            iMax = 0;
            for (int i5 = 0; i5 < this.mTitleBarText.length; i5++) {
                iMax = Math.max(iMax, this.mTitleBarImageFont.stringWidth(this.mTitleBarText[i5]));
            }
            if (!z) {
                this.mTitleBarSourceText = null;
            }
            z2 = true;
        } else {
            z2 = false;
            iMax = 0;
        }
        if (z2) {
            this.mTitleBarTextAreaWidth = Math.min(iMax, ((i3 - width) - 10) - (i2 * 2)) + 10;
            i = this.mTitleBarTextAreaWidth + width;
        } else {
            this.mTitleBarTextAreaWidth = 0;
            i = width;
        }
        this.mTitleBarX = 0;
        if ((this.mTitleBarAlignment & 1) != 0) {
            this.mTitleBarX = (i3 - i) >> 1;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.MenuObject
    protected void drawBackground(Graphics graphics, int i, int i2, int i3, int i4) {
        if (this.mStyle == 4 || this.mStyle == 5) {
            drawPopup(graphics, i, i2, i3, i4, true);
            return;
        }
        if (this.mStyle != 1) {
            graphics.setColor(891101);
            graphics.fillRect(i, i2, i3, i4);
            if (smBackground != null) {
                smBackground[0].draw(graphics, Toolkit.getScreenWidth() / 2, Toolkit.getScreenHeight());
                int screenWidth = (Toolkit.getScreenWidth() / 2) - smBackground[0].getPivotX();
                int width = smBackground[0].getWidth() - smBackground[0].getPivotX();
                smBackgroundSides.setAnimation(0, 0, true);
                int width2 = screenWidth - smBackgroundSides.getWidth();
                for (int width3 = 0; width3 < screenWidth; width3 += smBackgroundSides.getWidth()) {
                    smBackgroundSides.draw(graphics, width2, Toolkit.getScreenHeight());
                    width2 -= smBackgroundSides.getWidth();
                }
                smBackgroundSides.setAnimation(1, 0, true);
                int screenWidth2 = (width + (Toolkit.getScreenWidth() / 2)) - 1;
                for (int width4 = 0; width4 < screenWidth; width4 += smBackgroundSides.getWidth()) {
                    smBackgroundSides.draw(graphics, screenWidth2, Toolkit.getScreenHeight());
                    screenWidth2 += smBackgroundSides.getWidth();
                }
            } else {
                drawGradientRect(graphics, i, i2, i3, i4, 1141255, 6988587, 3);
            }
            if (RollerGameEngine.smTrackMenu) {
                GameEngine.smEngine2d.ingameDrawTrack(graphics);
            }
            if (this.mScreenType == 1) {
                drawPopup(graphics, -10, this.mItemAreaY, i3 + 20, this.mItemAreaHeight, true);
            }
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.MenuObject
    protected void drawItem(Graphics graphics, int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = this.mItemTextAreaWidths[i];
        int i8 = (this.mScreenType != 1 || i7 <= 0) ? this.mItemImageAreaWidths[i] : 0;
        if (i == this.mSelectedIndex && this.mScreenType == 0) {
            graphics.setClip(0, 0, Toolkit.getScreenWidth(), Toolkit.getScreenHeight());
            if (Game.smCurrentState == 8) {
                graphics.setColor(9486550);
                graphics.fillRect(i6, i3, this.mWidth, i5);
            } else {
                drawGradientRectVertical(graphics, i6, i3, this.mWidth, i5, 16711935, 9230860, 3);
                graphics.setColor(15007640);
                graphics.fillRect(i6, i3, this.mWidth, 3);
                graphics.fillRect(i6, (i3 + i5) - 4, this.mWidth, 4);
                graphics.setColor(16777215);
                graphics.fillRect(i6, i3, this.mWidth, 2);
                graphics.fillRect(i6, (i3 + i5) - 3, this.mWidth, 3);
                graphics.setColor(4860160);
                graphics.fillRect(i6, i3, this.mWidth, 1);
                graphics.fillRect(i6, (i3 + i5) - 2, this.mWidth, 2);
            }
        }
        int i9 = this.mItemTypes[i] == 3 ? i5 - this.mInputBoxHeight : i5;
        if (this.mItemTypes[i] == 6) {
            drawTable(graphics, i, i2, i3, i4, i9);
            return;
        }
        drawItemIcon(graphics, i, i2, i3, this.mItemImageAreaWidths[i], i9);
        drawItemText(graphics, i, i2 + i8, i3, i7, i9);
        if (this.mItemTypes[i] == 5) {
            drawSwitch(graphics, i, i2, i3, i4, i9);
        }
        if (this.mItemTypes[i] == 3) {
            drawInput(graphics, i, i6 + this.mItemAreaX, i3 + i9, this.mItemAreaWidth - (5 << 1), this.mInputBoxHeight - 5);
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.MenuObject
    protected void drawScrollArrows(Graphics graphics, int i, int i2, int i3, int i4, int i5, int i6) {
        boolean z = this.mVerticalScrollingOffset > 0;
        boolean z2 = this.mVerticalScrollingOffset + this.mItemAreaHeight < this.mScrollingAreaHeight;
        boolean z3 = z | (smScrollUpAnimations.getCurrentAnimationIndex() == 1);
        boolean z4 = z2 | (smScrollDownAnimations.getCurrentAnimationIndex() == 1);
        if (z3) {
            int i7 = this.mScrollArrowX + i;
            int i8 = this.mScrollArrowUpY + i2;
            int width = i7 - (smScrollUpAnimations.getWidth() >> 1);
            int height = i8 - (smScrollUpAnimations.getHeight() >> 1);
            graphics.setClip(i3, i4, i5, i6);
            smScrollUpAnimations.draw(graphics, width + smScrollUpAnimations.getPivotX(), height - 0);
        }
        if (z4) {
            int i9 = this.mScrollArrowX + i;
            int height2 = this.mScrollArrowDownY + i2;
            int width2 = i9 - (smScrollDownAnimations.getWidth() >> 1);
            if (this.mScreenType != 1) {
                height2 -= smScrollDownAnimations.getHeight() >> 1;
            }
            graphics.setClip(i3, i4, i5, i6);
            smScrollDownAnimations.draw(graphics, width2 + smScrollDownAnimations.getPivotX(), height2 + smScrollDownAnimations.getPivotY());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x0273  */
    @Override // com.digitalchocolate.androidrollergapp.MenuObject
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void drawTable(javax.microedition.lcdui.Graphics r30, int r31, int r32, int r33, int r34, int r35) {
        /*
            Method dump skipped, instructions count: 649
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.digitalchocolate.androidrollergapp.CustomMenuObject.drawTable(javax.microedition.lcdui.Graphics, int, int, int, int, int):void");
    }

    @Override // com.digitalchocolate.androidrollergapp.MenuObject
    protected void drawTitleBar(Graphics graphics, int i, int i2, int i3) {
        if (this.mTitleLogo == null || Game.smCurrentState != 0) {
            int i4 = this.mTitleBarTextAreaWidth;
            int i5 = this.mTitleBarImageAreaWidth;
            drawItemIcon(graphics, -2, i, i2, i5, i3);
            drawItemText(graphics, -2, i + i5, i2 - 5, i4, i3 - 5);
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.MenuObject
    protected void drawTitleBarBackground(Graphics graphics, int i, int i2, int i3, int i4) {
        if (this.mStyle == 5) {
            return;
        }
        if (this.mTitleLogo != null && Game.smCurrentState == 0) {
            graphics.drawImage(this.mTitleLogo, (i3 - this.mTitleLogo.getWidth()) / 2, -15, 20);
        } else if (smTitleSprite != null) {
            graphics.setClip(i, i2, i3, i4);
            smTitleSprite.setAnimation(0, 0, true);
            smTitleSprite.draw(graphics, i, i2);
            int width = i3 - smTitleSprite.getWidth();
            int width2 = smTitleSprite.getWidth() + i;
            smTitleSprite.setAnimation(2, 0, true);
            smTitleSprite.draw(graphics, i + i3, i2);
            int width3 = width - smTitleSprite.getWidth();
            smTitleSprite.setAnimation(1, 0, true);
            int width4 = smTitleSprite.getWidth();
            for (int i5 = 0; i5 < width3 + width4; i5 += width4) {
                smTitleSprite.draw(graphics, width2 + i5, i2);
            }
        }
        graphics.setClip(0, 0, this.mWidth, this.mHeight);
    }

    @Override // com.digitalchocolate.androidrollergapp.MenuObject, com.digitalchocolate.androidrollergapp.IMenu
    public int[] logicUpdate(int i) throws IOException {
        if (smBackground != null) {
            smBackground[0].logicUpdate(i);
        }
        if (RollerGameEngine.smTrackMenu) {
            DCThrillOld.logicUpdate(i);
        }
        return super.logicUpdate(i);
    }

    @Override // com.digitalchocolate.androidrollergapp.MenuObject
    public void setBounds(int i, int i2, int i3, int i4) {
        setSize(i3, i4);
        this.mX = i;
        this.mY = i2;
        this.mLastDrawX = i;
        this.mLastDrawY = i2;
    }

    @Override // com.digitalchocolate.androidrollergapp.MenuObject, com.digitalchocolate.androidrollergapp.IMenu
    public void setSize(int i, int i2) {
        setSize(i, i2, false);
    }

    @Override // com.digitalchocolate.androidrollergapp.MenuObject
    protected void setSize(int i, int i2, boolean z) {
        this.mWidth = i;
        this.mHeight = i2;
        this.mItemAreaX = this.mScreenXMargin;
        this.mItemAreaWidth = i - (this.mScreenXMargin << 1);
        calculateTitleBarLayout(z);
        calculateItemLayout(z);
        if (z) {
            return;
        }
        if (this.mScreenType == 0) {
            this.mSelectionItemsPerScreen = this.mItemAreaHeight / this.mSelectionItemHeight;
        }
        if (this.mSelectedIndex == -1) {
            this.mSelectedIndex = 0;
        }
        this.mSelectedIndex = getNextSelectableItemIndex(this.mSelectedIndex);
        if (this.mSelectedIndex != -1) {
            this.mSelectedIndex = getPreviousSelectableItemIndex(this.mSelectedIndex);
        }
        this.mVerticalScrollingOffset = 0;
        this.mCurrentPage = 0;
        if (this.mSelectedIndex != -1) {
            setSelectedItem(this.mSelectedIndex);
        }
        this.mLastDrawX = AnimationFrame.MAX_DURATION;
        this.mLastDrawY = AnimationFrame.MAX_DURATION;
    }
}
