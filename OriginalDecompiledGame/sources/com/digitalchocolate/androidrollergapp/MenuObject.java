package com.digitalchocolate.androidrollergapp;

import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* loaded from: classes.dex */
public class MenuObject implements IMenu {
    public static final int ALIGNMENT_BOTTOM = 8;
    public static final int ALIGNMENT_RIGHT = 4;
    protected static final int BACKGROUND_GRADIENT_SLIDE_HEIGHT = 2;
    protected static final int BORDER_THICKNESS = 4;
    protected static final int BOUNDS_HEIGHT = 3;
    protected static final int BOUNDS_WIDTH = 2;
    protected static final int BOUNDS_X = 0;
    protected static final int BOUNDS_Y = 1;
    protected static final boolean DEBUG_LAYOUT = false;
    protected static final boolean ENABLE_BACKGROUND_GRADIENT = true;
    protected static final boolean ENABLE_BORDERS = false;
    protected static final boolean ENABLE_DAVINCI_ICONS = true;
    protected static final boolean ENABLE_DAVINCI_SCROLL_ARROWS = true;
    protected static final boolean ENABLE_EXAMPLE_HORIZONTAL_ARROWS = false;
    protected static final boolean ENABLE_EXTENDED_ITEM_PARAMETERS = false;
    protected static final boolean ENABLE_IMAGE_FONT_IMPLEMENTATION = true;
    protected static final boolean ENABLE_IMAGE_ICONS = true;
    protected static final boolean ENABLE_INPUT_ITEMS = true;
    public static final boolean ENABLE_ITEM_BLINK = false;
    protected static final boolean ENABLE_ITEM_SCROLLING = true;
    protected static final boolean ENABLE_ITEM_SPECIFIC_TITLE_BARS = false;
    protected static final boolean ENABLE_SLIDER_ITEMS = false;
    protected static final boolean ENABLE_SMOOTH_TEXT_SCROLLING = true;
    protected static final boolean ENABLE_SUB_MENUS = false;
    protected static final boolean ENABLE_TABLE_ITEMS = true;
    protected static final boolean ENABLE_TITLE_BARS = true;
    protected static final int ENSURE_SCROLL_SCROLLED = 1;
    protected static final int ENSURE_SCROLL_STARTED = 0;
    protected static final int ENSURE_SCROLL_STOP_AFTER_SCROLLED = 2;
    public static final int EXTENDED_PARAMETER_DEFAULT = -1;
    protected static final int HORIZONTAL_SCROLLING_DELAY = 40;
    protected static final int HORIZONTAL_SCROLLING_TURNOVER_DELAY = 2000;
    protected static final int INPUT_CURSOR_BLINK_DURATION = 1000;
    protected static final int INPUT_KEY_VARIATION_DELAY = 750;
    protected static final int INVALID_DRAWING_COORDINATE = Integer.MAX_VALUE;
    protected static final int INVALID_ITEM_INDEX = -1;
    public static final int ITEM_ICON_INDEX_LOCKED = 2;
    public static final int ITEM_ICON_INDEX_NOT_SELECTED = 0;
    public static final int ITEM_ICON_INDEX_SELECTED = 1;
    public static final int ITEM_ICON_INDEX_SETTING_OFF = 3;
    protected static final int ITEM_INDEX_TITLE_BAR = -2;
    public static final int ITEM_STATE_LOCKED = 1;
    public static final int ITEM_STATE_NORMAL = 0;
    protected static final int ITEM_TYPE_INPUT_BOX = 3;
    protected static final int ITEM_TYPE_SLIDER = 4;
    protected static final int ITEM_TYPE_SUB_MENU = 7;
    protected static final int ITEM_TYPE_SWITCH = 5;
    protected static final int ITEM_TYPE_TABLE = 6;
    protected static final int ITEM_X_MARGIN = 5;
    protected static final int ITEM_Y_MARGIN = 5;
    protected static final int LINE_VISIBLE_TIME = 2000;
    public static final int SCREEN_TYPE_EXAMPLE_HORIZONTAL_ARROWS = 100;
    public static final int SCREEN_TYPE_MENU_COMBINER = 3;
    protected static final int SCROLLING_DIRECTION_LEFT = 0;
    protected static final int SCROLLING_DIRECTION_RIGHT = 1;
    protected static final int SCROLLING_EVENT_DOWN = 1;
    protected static final int SCROLLING_EVENT_NONE = 0;
    protected static final int SCROLLING_EVENT_UP = 2;
    protected static final int SCROLL_ANIMATION_INDEX_CLICKED = 1;
    protected static final int SCROLL_ANIMATION_INDEX_NORMAL = 0;
    protected static final int SCROLL_ANIMATION_INDEX_PRESSED = 2;
    protected static final int SLIDER_SECTION_WIDTH = 9;
    public static final int STYLE_BLANK = 1;
    public static final int STYLE_BOX = 4;
    public static final int STYLE_BOX_EMBEDDED_TITLE_BAR = 5;
    public static final int STYLE_DEFAULT = 3;
    public static final int STYLE_FULL_SCREEN = 3;
    public static final int STYLE_SUB_MENU = 6;
    public static final int STYLE_SUB_MENU_BORDERS = 7;
    public static final int STYLE_TRANSPARENT = 2;
    protected static final int TABLE_CELL_X_BORDER = 3;
    public static final int TABLE_COLUMN_WEIGHT_COMPACT = -1;
    protected static final int TEXT_LINE_Y_MARGIN = 0;
    protected static final int TITLE_BAR_GRADIENT_SLIDE_HEIGHT = 2;
    protected static final boolean USE_VERTICAL_INPUT_CURSOR = true;
    protected static final int VERTICAL_SCROLLING_LINE_DELAY = 300;
    static boolean drawn = false;
    protected static Font smDefaultSelectionFont;
    protected static ImageFont smDefaultSelectionImageFont;
    protected static Font smDefaultTextFont;
    public static ImageFont smDefaultTextImageFont;
    protected static Font smDefaultTitleBarFont;
    protected static ImageFont smDefaultTitleBarImageFont;
    protected static int smScrollArrowDownSpace;
    protected static int smScrollArrowLeftSpace;
    protected static int smScrollArrowRightSpace;
    protected static int smScrollArrowUpSpace;
    protected static SpriteObject smScrollDownAnimations;
    protected static Image smScrollImage;
    protected static SpriteObject smScrollLeftAnimations;
    protected static SpriteObject smScrollRightAnimations;
    protected static SpriteObject smScrollUpAnimations;
    protected static Font smSplitStringFont;
    protected static ImageFont smSplitStringImageFont;
    protected int mBackgroundColor;
    protected int mBackgroundGradientColor;
    private int mBlinkingTimer;
    protected int mCurrentPage;
    protected boolean mEnableBackground;
    protected boolean mEnableBorders;
    protected int mEnsureScrollOnce;
    protected boolean mHasSeveralSubMenus;
    protected boolean mHasTitleBar;
    protected int mHeight;
    protected int mInputBoxHeight;
    protected char[] mInputChars;
    protected int mInputCursorBlinkTimer;
    protected boolean mInputEditing;
    protected int[] mInputItemMappingTypes;
    protected StringBuffer[] mInputItemTexts;
    protected int mInputKeyVariation;
    protected int mInputKeyVariationTimer;
    protected boolean mInputKeyVariationTimerShouldDecrease;
    protected int mInputPreviousKey;
    protected int mInputTextPosition;
    protected int mItemAlignment;
    protected int[] mItemAlignments;
    protected int mItemAreaHeight;
    protected int mItemAreaWidth;
    protected int mItemAreaX;
    protected int mItemAreaY;
    private int[][] mItemBlinkSequence;
    protected int[] mItemBounds;
    protected int[] mItemCornerRows;
    protected int[] mItemEvents;
    protected Image[][] mItemIcons;
    protected int[] mItemImageAreaWidths;
    protected int[] mItemMargins;
    protected String[] mItemSourceTexts;
    protected SpriteObject[] mItemSprites;
    protected int[] mItemStates;
    protected int[] mItemTextAreaWidths;
    protected String[][] mItemTexts;
    protected String[] mItemTitleBarSourceTexts;
    protected String[][] mItemTitleBarTexts;
    protected int[] mItemTypes;
    protected int mLastDrawX;
    protected int mLastDrawY;
    protected boolean mLooseItemAreaAlignment;
    protected int mMaxItemCount;
    protected boolean mNewMenuEventAvailable;
    protected int[] mPageBreaks;
    protected int mPageHeight;
    protected int mScreenMaxHeight;
    protected int mScreenMaxWidth;
    protected int mScreenMinX;
    protected int mScreenMinY;
    protected int mScreenSoftkeyAreaHeight;
    protected int mScreenType;
    protected int mScreenXMargin;
    protected int mScreenYMargin;
    protected int mScrollArrowDownY;
    protected int mScrollArrowUpY;
    protected int mScrollArrowX;
    protected int mScrollingAreaHeight;
    protected int mScrollingEvent;
    protected int mScrollingTimer;
    protected int mSelectedIndex;
    protected int mSelectedItemBackgroundColor;
    protected int mSelectedItemScrollingDirection;
    protected int mSelectedItemScrollingOffset;
    protected int mSelectedItemUpdateTimer;
    protected int mSelectedItemVisibleLine;
    protected Font mSelectionFont;
    protected int mSelectionFontHeight;
    protected ImageFont mSelectionImageFont;
    protected int mSelectionItemHeight;
    protected int mSelectionItemsPerScreen;
    protected boolean mSeparateTitleBarBackground;
    protected int[] mSettingItemImageAreaWidths;
    protected int[] mSettingItemTextAreaWidths;
    protected int[] mSettingItemValueCounts;
    protected int[] mSettingItemValues;
    protected int mStyle;
    protected MenuObject[] mSubMenus;
    protected Image[][] mSwitchItemIcons;
    protected SpriteObject[] mSwitchItemSprites;
    protected String[][] mSwitchItemTexts;
    protected int[][] mTableItemColumnAlignments;
    protected int[][] mTableItemColumnWeights;
    protected int[][] mTableItemColumnWidths;
    protected Image[][] mTableItemIcons;
    protected int[][] mTableItemRowHeights;
    protected String[][] mTableItemSourceTexts;
    protected SpriteObject[][] mTableItemSprites;
    protected String[][][] mTableItemTexts;
    protected int mTemporaryScrollingDirection;
    protected int mTemporaryScrollingOffset;
    protected int mTemporaryUpdateTimer;
    protected int mTemporaryVisibleLine;
    protected int mTextColor;
    protected Font mTextFont;
    protected int mTextFontHeight;
    protected ImageFont mTextImageFont;
    protected int mTitleBarAlignment;
    protected int mTitleBarBackgroundColor;
    protected int mTitleBarBackgroundGradientColor;
    protected Font mTitleBarFont;
    protected int mTitleBarFontHeight;
    protected int mTitleBarHeight;
    protected Image mTitleBarImage;
    protected int mTitleBarImageAreaWidth;
    protected ImageFont mTitleBarImageFont;
    protected int mTitleBarScrollingDirection;
    protected int mTitleBarScrollingOffset;
    protected String mTitleBarSourceText;
    protected SpriteObject mTitleBarSprite;
    protected String[] mTitleBarText;
    protected int mTitleBarTextAreaWidth;
    protected int mTitleBarUpdateTimer;
    protected int mTitleBarVisibleLine;
    protected int mTitleBarX;
    protected boolean mTitleBarsOnTop;
    protected int mVerticalScrollingOffset;
    protected int mWidth;
    protected int mX;
    protected int mY;
    protected final int[] mLastMenuEvent = new int[2];
    private final int[] mSoftKeys = new int[2];
    private int pointerXPos = -999;
    private int pointerYPos = -999;

    public static void drawGradientRect(Graphics graphics, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
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
        int screenHeight = Toolkit.getScreenHeight();
        int i14 = i7;
        for (int i15 = 0; i15 < i4; i15 += i14) {
            if (i15 + i14 > i4) {
                i14 = i4 - i15;
            }
            graphics.setColor(((i15 * i11) / (i4 - 1)) + i8, ((i15 * i12) / (i4 - 1)) + i9, ((i15 * i13) / (i4 - 1)) + i10);
            if (i2 + i15 + i14 >= 0) {
                if (i2 + i15 > screenHeight) {
                    return;
                } else {
                    graphics.fillRect(i, i2 + i15, i3, i14);
                }
            }
        }
    }

    public static void setBorderPieces(SpriteObject spriteObject) {
    }

    public static void setDefaultFonts(Font font, Font font2, Font font3) {
    }

    public static void setDefaultImageFonts(ImageFont imageFont, ImageFont imageFont2, ImageFont imageFont3) {
        smDefaultTitleBarImageFont = imageFont;
        smDefaultTextImageFont = imageFont2;
        smDefaultSelectionImageFont = imageFont3;
    }

    public static void setDefaultImageFonts(ImageFont imageFont, ImageFont imageFont2, ImageFont imageFont3, ImageFont imageFont4) {
        setDefaultImageFonts(imageFont, imageFont2, imageFont3);
    }

    public static void setHorizontalScrollArrows(SpriteObject spriteObject, SpriteObject spriteObject2) {
    }

    public static void setScrollArrows(Image image) {
    }

    public static void setScrollArrowsDvc(SpriteObject spriteObject, SpriteObject spriteObject2) {
        smScrollUpAnimations = spriteObject;
        smScrollDownAnimations = spriteObject2;
        smScrollArrowUpSpace = 0;
        for (int i = 0; i < spriteObject.getAnimationCount(); i++) {
            spriteObject.setAnimation(i, -1, true);
            smScrollArrowUpSpace = Math.max(smScrollArrowUpSpace, spriteObject.getHeight() + 2);
        }
        smScrollArrowDownSpace = 0;
        for (int i2 = 0; i2 < spriteObject2.getAnimationCount(); i2++) {
            spriteObject2.setAnimation(i2, -1, true);
            smScrollArrowDownSpace = Math.max(smScrollArrowDownSpace, spriteObject2.getHeight() + 2);
        }
    }

    protected static String[] splitString(String str, int i, int i2, int i3, boolean z) {
        int iCharWidth;
        int i4;
        int i5;
        int i6;
        char[] cArr = {'.', '/'};
        if (z) {
            i -= smSplitStringImageFont.getEmptyStringWidth();
        }
        int iIndexOf = 0;
        int length = str.length();
        Vector vector = new Vector();
        if (str.length() == 0) {
            vector.addElement(str);
        }
        int i7 = 0;
        int i8 = 0;
        while (iIndexOf < length) {
            iIndexOf = str.indexOf("\\n", i7);
            if (iIndexOf == -1) {
                iIndexOf = length;
            }
            int i9 = i7;
            int i10 = i8;
            boolean z2 = false;
            while (!z2) {
                int i11 = -1;
                if (i10 < i3) {
                    i4 = i9;
                    i5 = i - i2;
                    iCharWidth = 0;
                } else {
                    iCharWidth = 0;
                    i4 = i9;
                    i5 = i;
                }
                while (iCharWidth <= i5 && i4 < iIndexOf) {
                    char cCharAt = str.charAt(i4);
                    iCharWidth = z ? iCharWidth + smSplitStringImageFont.charWidth(cCharAt) : iCharWidth + smSplitStringFont.charWidth(cCharAt);
                    i4++;
                    if (cCharAt == ' ') {
                        i11 = i4;
                    }
                }
                if (i4 == iIndexOf && iCharWidth <= i5) {
                    z2 = true;
                    i6 = i4;
                } else if (i11 != -1) {
                    i6 = i11 - 1;
                } else {
                    int i12 = i4 - 1;
                    boolean z3 = false;
                    int i13 = i12;
                    while (i12 > i9 && !z3) {
                        char cCharAt2 = str.charAt(i12);
                        int i14 = i13;
                        boolean z4 = z3;
                        int length2 = cArr.length;
                        while (true) {
                            length2--;
                            if (length2 < 0 || z4) {
                                break;
                            }
                            if (cCharAt2 == cArr[length2]) {
                                i14 = i12 + 1;
                                z4 = true;
                            }
                        }
                        i12--;
                        z3 = z4;
                        i13 = i14;
                    }
                    i6 = i13;
                }
                vector.addElement(str.substring(i9, i6));
                i10++;
                i9 = (!z2 || i6 >= length) ? i11 != -1 ? i6 + 1 : i6 : i6 + 2;
            }
            i8 = i10;
            i7 = i9;
        }
        String[] strArr = new String[vector.size()];
        int i15 = 0;
        while (true) {
            int i16 = i15;
            if (i16 >= strArr.length) {
                break;
            }
            strArr[i16] = (String) vector.elementAt(i16);
            i15 = i16 + 1;
        }
        if (z) {
            smSplitStringImageFont = null;
        } else {
            smSplitStringFont = null;
        }
        return strArr;
    }

    public static String[] splitString(String str, ImageFont imageFont, int i) {
        smSplitStringImageFont = imageFont;
        return splitString(str, i, 0, 0, true);
    }

    public static String[] splitString(String str, Font font, int i) {
        smSplitStringFont = font;
        return splitString(str, i, 0, 0, false);
    }

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
        if (this.mScreenType == 0) {
            this.mSelectionItemHeight = Math.max(i2, this.mSelectionFontHeight + 10);
            if (z4) {
                this.mInputBoxHeight = this.mTextFontHeight + 5 + 5;
                this.mSelectionItemHeight += this.mInputBoxHeight;
            }
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
                this.mItemAreaY += 5;
                this.mItemAreaHeight -= 10;
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

    protected void calculateItemSizes(int i, int i2, boolean z) {
        int height;
        int width;
        int width2;
        int height2;
        int i3;
        int i4;
        for (int i5 = 0; i5 < this.mMaxItemCount; i5++) {
            if (this.mItemTypes[i5] == 6) {
                calculateTableItemLayout(i5, z);
            } else {
                if (this.mScreenType != 0) {
                    if (this.mItemIcons[i5] != null) {
                        int i6 = 5 << 1;
                        width = this.mItemIcons[i5][0].getWidth() + 10;
                        height = this.mItemIcons[i5][0].getHeight();
                    } else {
                        width = 0;
                        height = 0;
                    }
                    if (this.mItemSprites[i5] != null) {
                        int i7 = 5 << 1;
                        width = this.mItemSprites[i5].getWidth() + 10;
                        height = this.mItemSprites[i5].getHeight();
                    }
                } else {
                    height = i2;
                    width = i;
                }
                this.mItemImageAreaWidths[i5] = width;
                int i8 = 0;
                if (this.mItemTypes[i5] == 5) {
                    int i9 = 0;
                    Image[] imageArr = this.mSwitchItemIcons[i5];
                    if (imageArr != null) {
                        int iMax = 0;
                        for (int i10 = 0; i10 < imageArr.length; i10++) {
                            if (imageArr[i10] != null) {
                                int i11 = 5 << 1;
                                iMax = Math.max(iMax, imageArr[i10].getWidth() + 10);
                            }
                        }
                        i9 = iMax;
                    }
                    SpriteObject spriteObject = this.mSwitchItemSprites[i5];
                    if (spriteObject != null) {
                        int iMax2 = i9;
                        for (int i12 = 0; i12 < spriteObject.getAnimationCount(); i12++) {
                            spriteObject.setAnimation(i12, -1, true);
                            int i13 = 5 << 1;
                            iMax2 = Math.max(iMax2, spriteObject.getWidth() + 10);
                        }
                        i9 = iMax2;
                    }
                    this.mSettingItemImageAreaWidths[i5] = i9;
                    int i14 = 0;
                    if (this.mSwitchItemTexts[i5] != null) {
                        int iMax3 = 0;
                        for (int i15 = 0; i15 < this.mSwitchItemTexts[i5].length; i15++) {
                            String str = this.mSwitchItemTexts[i5][i15];
                            if (str != null) {
                                iMax3 = Math.max(iMax3, this.mSelectionImageFont.stringWidth(str));
                            }
                        }
                        int i16 = 5 << 1;
                        i14 = iMax3 + 10;
                    }
                    this.mSettingItemTextAreaWidths[i5] = i14;
                    i8 = 0 + i9 + i14;
                }
                if (this.mScreenType == 1) {
                    if (this.mItemIcons[i5] != null) {
                        int i17 = 5 << 1;
                        width2 = this.mItemIcons[i5][0].getWidth() + 10;
                        height2 = ((this.mItemIcons[i5][0].getHeight() - 1) / (this.mTextFontHeight + 0)) + 1;
                    } else {
                        width2 = 0;
                        height2 = 0;
                    }
                    if (this.mItemSprites[i5] != null) {
                        int i18 = 5 << 1;
                        width2 = this.mItemSprites[i5].getWidth() + 10;
                        height2 = ((this.mItemSprites[i5].getHeight() - 1) / (this.mTextFontHeight + 0)) + 1;
                    }
                    this.mItemCornerRows[i5] = height2;
                } else {
                    width2 = 0;
                    height2 = 0;
                }
                if (this.mScreenType == 0) {
                    i8 += width;
                }
                String[] strArrSplitString = this.mItemTexts[i5];
                String str2 = this.mItemSourceTexts[i5];
                if (str2 != null) {
                    if (this.mScreenType != 0) {
                        int i19 = (this.mItemAreaWidth - (5 << 1)) - i8;
                        smSplitStringImageFont = this.mScreenType == 0 ? this.mSelectionImageFont : this.mTextImageFont;
                        strArrSplitString = splitString(str2, i19, width2, height2, true);
                    } else {
                        strArrSplitString = new String[]{str2};
                    }
                }
                if (strArrSplitString != null) {
                    int iMax4 = 0;
                    for (int i20 = 0; i20 < strArrSplitString.length; i20++) {
                        String str3 = strArrSplitString[i20];
                        int iStringWidth = this.mScreenType != 0 ? this.mTextImageFont.stringWidth(str3) : this.mSelectionImageFont.stringWidth(str3);
                        if (i20 < height2) {
                            iStringWidth += width2;
                        }
                        iMax4 = Math.max(iMax4, iStringWidth);
                    }
                    int i21 = 5 << 1;
                    this.mItemTextAreaWidths[i5] = Math.min(iMax4, (this.mItemAreaWidth - i8) - (5 << 1)) + 10;
                    int i22 = this.mItemTextAreaWidths[i5] + i8;
                    int iMax5 = Math.max(height, this.mScreenType != 0 ? (this.mTextFontHeight * strArrSplitString.length) + (strArrSplitString.length * 0) : this.mSelectionFontHeight + 10);
                    if (!z) {
                        this.mItemSourceTexts[i5] = null;
                    }
                    this.mItemTexts[i5] = strArrSplitString;
                    i4 = i22;
                    i3 = iMax5;
                } else if (this.mItemIcons[i5] == null && this.mItemSprites[i5] == null) {
                    i3 = 0;
                    i4 = i8;
                } else {
                    i3 = height;
                    i4 = i8;
                }
                if (this.mScreenType == 1 && strArrSplitString == null) {
                    i4 += width;
                }
                if (!z && (this.mItemTypes[i5] == 5 || this.mItemTypes[i5] == 4)) {
                    i4 = this.mItemAreaWidth;
                }
                if (this.mScreenType == 0 && i3 > 0) {
                    i3 = this.mSelectionItemHeight;
                }
                this.mItemBounds[(i5 * 4) + 2] = i4;
                this.mItemBounds[(i5 * 4) + 3] = i3;
            }
        }
    }

    protected void calculateTableItemLayout(int i, boolean z) {
        int i2 = this.mItemAreaWidth - (5 << 1);
        int[] iArr = this.mTableItemColumnWeights[i];
        int length = iArr.length;
        int length2 = 0;
        if (this.mTableItemSourceTexts[i] != null) {
            length2 = this.mTableItemSourceTexts[i].length;
        } else if (this.mTableItemIcons[i] != null) {
            length2 = this.mTableItemIcons[i].length;
        } else if (this.mTableItemSprites[i] != null) {
            length2 = this.mTableItemSprites[i].length;
        }
        int i3 = length2 / length;
        int[] iArr2 = new int[length];
        int[] iArr3 = new int[i3];
        String[][] strArr = new String[length2][];
        this.mTableItemColumnWidths[i] = iArr2;
        this.mTableItemRowHeights[i] = iArr3;
        this.mTableItemTexts[i] = strArr;
        int i4 = i2;
        for (int i5 = 0; i5 < length; i5++) {
            if (iArr[i5] == -1) {
                int iMax = 0;
                for (int i6 = i5; i6 < length2; i6 += length) {
                    if (this.mTableItemSourceTexts[i] != null && this.mTableItemSourceTexts[i][i6] != null) {
                        String str = this.mTableItemSourceTexts[i][i6];
                        iMax = Math.max(iMax, this.mTextImageFont.stringWidth(str));
                        strArr[i6] = new String[]{str};
                    }
                    if (this.mTableItemIcons[i] != null && this.mTableItemIcons[i][i6] != null) {
                        iMax = Math.max(iMax, this.mTableItemIcons[i][i6].getWidth());
                    }
                    if (this.mTableItemSprites[i] != null && this.mTableItemSprites[i][i6] != null) {
                        iMax = Math.max(iMax, this.mTableItemSprites[i][i6].getWidth());
                    }
                }
                int i7 = iMax + 6;
                iArr2[i5] = i7;
                i4 -= i7;
            }
        }
        for (int i8 = 0; i8 < length; i8++) {
            if (iArr[i8] != -1) {
                iArr2[i8] = (iArr[i8] * i4) / 100;
            }
        }
        int i9 = i2;
        for (int i10 = 0; i10 < length; i10++) {
            i9 -= iArr2[i10];
        }
        int i11 = length - 1;
        iArr2[i11] = i9 + iArr2[i11];
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        while (i12 < i3) {
            int iMax2 = 0;
            int i15 = i14;
            int i16 = 0;
            while (i16 < length) {
                if (iArr[i16] != -1) {
                    int i17 = iArr2[i16] - 6;
                    if (this.mTableItemSourceTexts[i] != null && this.mTableItemSourceTexts[i][i15] != null) {
                        this.mTableItemTexts[i][i15] = splitString(this.mTableItemSourceTexts[i][i15], this.mTextImageFont, i17);
                    }
                }
                if (this.mTableItemSprites[i] != null && this.mTableItemSprites[i][i15] != null) {
                    iMax2 = Math.max(iMax2, this.mTableItemSprites[i][i15].getHeight());
                } else if (this.mTableItemIcons[i] != null && this.mTableItemIcons[i][i15] != null) {
                    iMax2 = Math.max(iMax2, this.mTableItemIcons[i][i15].getHeight());
                } else if (this.mTableItemSourceTexts[i] != null && this.mTableItemSourceTexts[i][i15] != null) {
                    iMax2 = Math.max(iMax2, this.mTableItemTexts[i][i15].length * this.mTextFontHeight);
                }
                i16++;
                i15++;
            }
            int i18 = ((((iMax2 + 0) - 1) / (this.mTextFontHeight + 0)) + 1) * (this.mTextFontHeight + 0);
            iArr3[i12] = i18;
            i12++;
            i13 = i18 + i13;
            i14 = i15;
        }
        if (z) {
            int iMax3 = 0;
            int i19 = 0;
            for (int i20 = 0; i20 < length; i20++) {
                if (iArr[i20] != -1) {
                    int iMax4 = 0;
                    int i21 = i19;
                    int i22 = 0;
                    while (i22 < i3) {
                        if (this.mTableItemSprites[i] != null && this.mTableItemSprites[i][i21] != null) {
                            iMax4 = Math.max(iMax4, this.mTableItemSprites[i][i21].getWidth());
                        } else if (this.mTableItemIcons[i] != null && this.mTableItemIcons[i][i21] != null) {
                            iMax4 = Math.max(iMax4, this.mTableItemIcons[i][i21].getWidth());
                        } else if (this.mTableItemTexts[i] != null && this.mTableItemTexts[i][i21] != null) {
                            int iMax5 = iMax4;
                            for (String str2 : this.mTableItemTexts[i][i21]) {
                                iMax5 = Math.max(iMax5, this.mTextImageFont.stringWidth(str2));
                            }
                            iMax4 = iMax5;
                        }
                        i22++;
                        i21++;
                    }
                    iMax3 = Math.max(iMax3, ((((iMax4 + 6) * 100) + iArr[i20]) - 1) / iArr[i20]);
                    i19 = i21;
                }
            }
            int i23 = 0;
            int i24 = iMax3;
            for (int i25 = 0; i25 < length; i25++) {
                if (iArr[i25] != -1) {
                    iArr2[i25] = (iArr[i25] * iMax3) / 100;
                    i24 -= iArr2[i25];
                }
                i23 += iArr2[i25];
            }
            int i26 = length - 1;
            iArr2[i26] = iArr2[i26] + i24;
            i2 = i23 + i24;
        }
        if (!z) {
            this.mTableItemSourceTexts[i] = null;
        }
        int i27 = 5 << 1;
        this.mItemBounds[(i * 4) + 2] = i2 + 10;
        this.mItemBounds[(i * 4) + 3] = i13;
    }

    protected void calculateTitleBarLayout(boolean z) {
        int height;
        int width;
        boolean z2;
        int iMax;
        if (!this.mHasTitleBar) {
            this.mTitleBarHeight = 0;
            return;
        }
        if (this.mTitleBarImage != null) {
            width = this.mTitleBarImage.getWidth() + 10;
            height = this.mTitleBarImage.getHeight();
        } else {
            height = 0;
            width = 0;
        }
        if (this.mTitleBarSprite != null) {
            width = this.mTitleBarSprite.getWidth() + 10;
            height = this.mTitleBarSprite.getHeight();
        }
        this.mTitleBarImageAreaWidth = width;
        this.mTitleBarHeight = Math.max(this.mTitleBarFontHeight, height) + 10;
        int i = this.mTitleBarsOnTop ? this.mScreenMaxWidth : this.mWidth;
        int i2 = (i - 10) - width;
        if (this.mTitleBarSourceText != null) {
            this.mTitleBarText = new String[]{this.mTitleBarSourceText};
        }
        if (this.mTitleBarText != null) {
            iMax = 0;
            for (int i3 = 0; i3 < this.mTitleBarText.length; i3++) {
                iMax = Math.max(iMax, this.mTitleBarImageFont.stringWidth(this.mTitleBarText[i3]));
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
            this.mTitleBarTextAreaWidth = Math.min(iMax, (i - width) - 10) + 10;
            width += this.mTitleBarTextAreaWidth;
        } else {
            this.mTitleBarTextAreaWidth = 0;
        }
        this.mTitleBarX = 0;
        if ((this.mTitleBarAlignment & 1) != 0) {
            this.mTitleBarX = (i - width) >> 1;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IMenu
    public void destroyItem(int i) {
        if (this.mItemTypes[i] != -1 && this.mSelectedIndex == i) {
            this.mSelectedIndex = getNextSelectableItemIndex(i);
            if (this.mSelectedIndex < i) {
                this.mSelectedIndex = getPreviousSelectableItemIndex(i);
            }
        }
        if (this.mItemIcons != null) {
            this.mItemIcons[i] = null;
        }
        if (this.mSwitchItemIcons != null) {
            this.mSwitchItemIcons[i] = null;
        }
        if (this.mTableItemIcons != null) {
            this.mTableItemIcons[i] = null;
        }
        if (this.mItemSprites != null && this.mItemSprites[i] != null) {
            this.mItemSprites[i].freeResources();
            this.mItemSprites[i] = null;
        }
        if (this.mSwitchItemSprites != null && this.mSwitchItemSprites[i] != null) {
            this.mSwitchItemSprites[i].freeResources();
            this.mSwitchItemSprites[i] = null;
        }
        if (this.mTableItemSprites != null && this.mTableItemSprites[i] != null) {
            for (int i2 = 0; i2 < this.mTableItemSprites[i].length; i2++) {
                if (this.mTableItemSprites[i][i2] != null) {
                    this.mTableItemSprites[i][i2].freeResources();
                }
            }
            this.mTableItemSprites[i] = null;
        }
        this.mItemSourceTexts[i] = null;
        this.mItemTexts[i] = null;
        this.mItemTypes[i] = -1;
        if (this.mSwitchItemTexts != null) {
            this.mSwitchItemTexts[i] = null;
        }
        if (this.mTableItemColumnWeights != null) {
            this.mTableItemColumnWeights[i] = null;
            this.mTableItemColumnAlignments[i] = null;
            this.mTableItemSourceTexts[i] = null;
        }
        if (this.mTableItemColumnWidths != null) {
            this.mTableItemColumnWidths[i] = null;
            this.mTableItemRowHeights[i] = null;
            this.mTableItemTexts[i] = (String[][]) null;
        }
    }

    public void doDraw(Graphics graphics) {
        doDraw(graphics, this.mX, this.mY);
    }

    @Override // com.digitalchocolate.androidrollergapp.IMenu
    public void doDraw(Graphics graphics, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int clipX = graphics.getClipX();
        int clipY = graphics.getClipY();
        int clipWidth = graphics.getClipWidth();
        int clipHeight = graphics.getClipHeight();
        int i11 = this.mWidth;
        int i12 = this.mHeight;
        if (this.mHasTitleBar) {
            if (this.mTitleBarsOnTop) {
                int i13 = this.mScreenMinX;
                i9 = this.mScreenMinY;
                i10 = i13;
                i8 = this.mScreenMaxWidth;
            } else {
                i8 = this.mWidth;
                i9 = i2;
                i10 = i;
            }
            i3 = this.mTitleBarHeight;
            i4 = i8;
            i5 = i9;
            i6 = i10;
        } else {
            i3 = 0;
            i4 = 0;
            i5 = i2;
            i6 = i;
        }
        if (this.mEnableBackground) {
            graphics.setClip(i, i2, i11, i12);
            drawBackground(graphics, i, i2, i11, i12);
        }
        if (this.mHasTitleBar && this.mSeparateTitleBarBackground) {
            graphics.setClip(i6, i5, i4, i3);
            drawTitleBarBackground(graphics, i6, i5, i4, i3);
            int i14 = i2 + i3;
            int i15 = i12 - i3;
        }
        if (this.mHasTitleBar) {
            int i16 = this.mTitleBarX + i6;
            graphics.setClip(i16, i5, i4, this.mTitleBarHeight);
            drawTitleBar(graphics, i16, i5, i3);
            i7 = this.mTitleBarHeight + i2;
        } else {
            i7 = i2;
        }
        graphics.setClip(i, i7, this.mWidth, this.mHeight - this.mTitleBarHeight);
        int i17 = ((this.mItemAlignment & 2) == 0 || this.mScrollingAreaHeight >= this.mItemAreaHeight) ? i2 : ((this.mItemAreaHeight - this.mScrollingAreaHeight) >> 1) + i2;
        if (this.mScreenType == 0) {
            int i18 = 0;
            while (true) {
                int i19 = i18;
                if (i19 >= this.mMaxItemCount) {
                    break;
                }
                int i20 = this.mItemAreaX + i + this.mItemBounds[(i19 * 4) + 0];
                int i21 = this.mItemBounds[(i19 * 4) + 1] - this.mVerticalScrollingOffset;
                int i22 = this.mItemBounds[(i19 * 4) + 2];
                int i23 = this.mItemBounds[(i19 * 4) + 3];
                if (i21 >= 0 && i21 + i23 <= this.mItemAreaHeight) {
                    int i24 = i21 + this.mItemAreaY + i17;
                    graphics.setClip(this.mItemAreaX + i, this.mItemAreaY + i17, this.mItemAreaWidth, this.mItemAreaHeight);
                    drawItem(graphics, i19, i20, i24, i22, i23, i);
                }
                i18 = i19 + 1;
            }
        } else if (this.mScreenType == 1) {
            int i25 = 0;
            while (true) {
                int i26 = i25;
                if (i26 >= this.mMaxItemCount) {
                    break;
                }
                int i27 = this.mItemAreaX + this.mItemBounds[(i26 * 4) + 0];
                int i28 = (this.mItemAreaY + this.mItemBounds[(i26 * 4) + 1]) - this.mVerticalScrollingOffset;
                int i29 = this.mItemBounds[(i26 * 4) + 2];
                int i30 = this.mItemBounds[(i26 * 4) + 3];
                if (i28 + i30 >= this.mItemAreaY && i28 < this.mItemAreaY + this.mItemAreaHeight) {
                    graphics.setClip(this.mItemAreaX + i, this.mItemAreaY + i17, this.mItemAreaWidth, this.mItemAreaHeight);
                    drawItem(graphics, i26, i + i27, i17 + i28, i29, i30 - 0, i);
                }
                i25 = i26 + 1;
            }
        }
        drawScrollArrows(graphics, i, i17 + 0, clipX, clipY, clipWidth, clipHeight);
        graphics.setClip(clipX, clipY, clipWidth, clipHeight);
        this.mLastDrawX = i;
        this.mLastDrawY = i17;
    }

    protected void drawBackground(Graphics graphics, int i, int i2, int i3, int i4) {
        drawGradientRect(graphics, i, i2, i3, i4, this.mBackgroundColor, this.mBackgroundGradientColor, 2);
    }

    protected void drawBorders(Graphics graphics, int i, int i2, int i3, int i4) {
        int clipX = graphics.getClipX();
        int clipY = graphics.getClipY();
        int clipWidth = graphics.getClipWidth();
        int clipHeight = graphics.getClipHeight();
        graphics.setClip(i, i2, i3, i4);
        graphics.setColor(255);
        graphics.drawRect(i, i2, i3 - 1, i4 - 1);
        graphics.drawRect(i + 1, i2 + 1, i3 - 3, i4 - 3);
        graphics.drawRect(i + 3, i2 + 3, i3 - 7, i4 - 7);
        graphics.setColor(this.mBackgroundColor);
        graphics.drawRect(i + 2, i2 + 2, i3 - 5, i4 - 5);
        graphics.setClip(clipX, clipY, clipWidth, clipHeight);
    }

    protected void drawExampleHorizontalArrowsScreen(Graphics graphics, int i, int i2) {
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x022e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void drawInput(javax.microedition.lcdui.Graphics r21, int r22, int r23, int r24, int r25, int r26) {
        /*
            Method dump skipped, instructions count: 564
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.digitalchocolate.androidrollergapp.MenuObject.drawInput(javax.microedition.lcdui.Graphics, int, int, int, int, int):void");
    }

    protected void drawItem(Graphics graphics, int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = this.mItemTextAreaWidths[i];
        int i8 = (this.mScreenType != 1 || i7 <= 0) ? this.mItemImageAreaWidths[i] : 0;
        if (i == this.mSelectedIndex && this.mScreenType == 0) {
            graphics.setColor(this.mSelectedItemBackgroundColor);
            graphics.fillRect(this.mItemAreaX + i6, i3, this.mItemAreaWidth, i5);
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

    protected void drawItemIcon(Graphics graphics, int i, int i2, int i3, int i4, int i5) {
        int i6;
        int height;
        int height2;
        boolean z = i == -2;
        if (z || this.mScreenType == 1) {
            i6 = 0;
        } else {
            i6 = this.mItemStates[i] == 0 ? this.mSelectedIndex == i ? 1 : 0 : 2;
            if ((this.mItemTypes[i] == 5 || this.mItemTypes[i] == 4) && this.mSettingItemValues[i] == 0) {
                i6 = 3;
            }
        }
        Image image = null;
        if (z) {
            image = this.mTitleBarImage;
        } else if (this.mItemIcons[i] != null) {
            image = this.mItemIcons[i][i6];
        }
        if (image != null) {
            int width = ((i4 - image.getWidth()) >> 1) + i2;
            if (z || this.mScreenType == 0) {
                height2 = ((i5 - image.getHeight()) >> 1) + i3;
            } else if (this.mItemTexts[i] == null || this.mItemTexts[i].length <= this.mItemCornerRows[i]) {
                height2 = i3;
            } else {
                int i7 = (this.mItemCornerRows[i] * (this.mTextFontHeight + 0)) - 0;
                if (image.getHeight() > i7) {
                    i7 += 0;
                }
                height2 = ((i7 - image.getHeight()) >> 1) + i3;
            }
            graphics.drawImage(image, width, height2, 20);
        }
        SpriteObject spriteObject = z ? this.mTitleBarSprite : this.mItemSprites[i];
        if (spriteObject != null) {
            if (i6 != spriteObject.getCurrentAnimationIndex()) {
                spriteObject.setAnimation(i6, -1, true);
            }
            int width2 = ((i4 - spriteObject.getWidth()) >> 1) + i2;
            if (z || this.mScreenType == 0) {
                height = ((i5 - spriteObject.getHeight()) >> 1) + i3;
            } else if (this.mItemTexts[i] == null || this.mItemTexts[i].length <= this.mItemCornerRows[i]) {
                height = i3;
            } else {
                int i8 = (this.mItemCornerRows[i] * (this.mTextFontHeight + 0)) - 0;
                if (spriteObject.getHeight() > i8) {
                    i8 += 0;
                }
                height = ((i8 - spriteObject.getHeight()) >> 1) + i3;
            }
            spriteObject.draw(graphics, width2 + spriteObject.getPivotX(), height + spriteObject.getPivotY());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x01ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void drawItemText(javax.microedition.lcdui.Graphics r22, int r23, int r24, int r25, int r26, int r27) {
        /*
            Method dump skipped, instructions count: 468
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.digitalchocolate.androidrollergapp.MenuObject.drawItemText(javax.microedition.lcdui.Graphics, int, int, int, int, int):void");
    }

    @Override // com.digitalchocolate.androidrollergapp.IMenu
    public void drawLoadingScreen(Graphics graphics, String[] strArr, int i) {
    }

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
            smScrollUpAnimations.draw(graphics, width + smScrollUpAnimations.getPivotX(), height + smScrollUpAnimations.getPivotY());
        }
        if (z4) {
            int i9 = this.mScrollArrowX + i;
            int i10 = this.mScrollArrowDownY + i2;
            int width2 = i9 - (smScrollDownAnimations.getWidth() >> 1);
            int height2 = i10 - (smScrollDownAnimations.getHeight() >> 1);
            graphics.setClip(i3, i4, i5, i6);
            smScrollDownAnimations.draw(graphics, width2 + smScrollDownAnimations.getPivotX(), height2 + smScrollDownAnimations.getPivotY());
        }
    }

    protected void drawSlider(Graphics graphics, int i, int i2, int i3, int i4, int i5) {
    }

    protected void drawSubMenus(Graphics graphics, int i, int i2) {
    }

    protected void drawSwitch(Graphics graphics, int i, int i2, int i3, int i4, int i5) {
        String str;
        Image image;
        int i6 = this.mSettingItemTextAreaWidths[i];
        int i7 = this.mSettingItemImageAreaWidths[i];
        int i8 = ((i2 + i4) - i6) - i7;
        int i9 = this.mSettingItemValues[i];
        if (this.mSwitchItemIcons[i] != null && (image = this.mSwitchItemIcons[i][i9]) != null) {
            graphics.drawImage(image, (i7 >> 1) + i8, (i5 >> 1) + i3, 3);
        }
        if (this.mSwitchItemSprites[i] != null) {
            SpriteObject spriteObject = this.mSwitchItemSprites[i];
            if (i9 != spriteObject.getCurrentAnimationIndex()) {
                spriteObject.setAnimation(i9, -1, true);
            }
            spriteObject.draw(graphics, spriteObject.getPivotX() + i8 + ((i7 - spriteObject.getWidth()) >> 1), spriteObject.getPivotY() + i3 + ((i5 - spriteObject.getHeight()) >> 1));
        }
        if (this.mSwitchItemTexts[i] == null || (str = this.mSwitchItemTexts[i][i9]) == null) {
            return;
        }
        this.mSelectionImageFont.drawString(graphics, str, i8 + i7 + 5, i3 + ((i5 - this.mSelectionFontHeight) >> 1), 20);
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x01b4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void drawTable(javax.microedition.lcdui.Graphics r30, int r31, int r32, int r33, int r34, int r35) {
        /*
            Method dump skipped, instructions count: 458
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.digitalchocolate.androidrollergapp.MenuObject.drawTable(javax.microedition.lcdui.Graphics, int, int, int, int, int):void");
    }

    protected void drawTitleBar(Graphics graphics, int i, int i2, int i3) {
        int i4 = this.mTitleBarTextAreaWidth;
        int i5 = this.mTitleBarImageAreaWidth;
        drawItemIcon(graphics, -2, i, i2, i5, i3);
        drawItemText(graphics, -2, i + i5, i2, i4, i3);
    }

    protected void drawTitleBarBackground(Graphics graphics, int i, int i2, int i3, int i4) {
        drawGradientRect(graphics, i, i2, i3, i4, this.mTitleBarBackgroundColor, this.mTitleBarBackgroundGradientColor, 2);
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getItemEvent(int i) {
        return this.mItemEvents[i];
    }

    @Override // com.digitalchocolate.androidrollergapp.IMenu
    public int getItemIntValue(int i) {
        return this.mSettingItemValues[i];
    }

    @Override // com.digitalchocolate.androidrollergapp.IMenu
    public String getItemStringValue(int i) {
        if (this.mItemTypes[i] == 3) {
            return this.mInputItemTexts[i].toString();
        }
        return null;
    }

    protected int getNextSelectableItemIndex(int i) {
        if (this.mMaxItemCount == 0) {
            return -1;
        }
        int i2 = this.mMaxItemCount;
        int i3 = i;
        while (true) {
            i3 = (i3 + 1) % this.mMaxItemCount;
            int i4 = i2 - 1;
            if (i2 <= 0) {
                return -1;
            }
            if (isItemSelectable(i3)) {
                return i3;
            }
            i2 = i4;
        }
    }

    public int getPreferredHeight(int i, int i2) {
        setSize(i, i2, true);
        int iMin = Math.min(this.mItemAreaHeight, this.mScrollingAreaHeight);
        if (this.mScreenType == 1) {
            int i3 = this.mTextFontHeight + 0;
            iMin = (iMin / i3) * i3;
        } else if (this.mScreenType == 0) {
            int i4 = this.mSelectionItemHeight;
            iMin = (iMin / i4) * i4;
        }
        return this.mHeight - (this.mItemAreaHeight - iMin);
    }

    public int getPreferredWidth(int i) {
        int iMax = 0;
        setSize(i, Integer.MAX_VALUE, true);
        int iMax2 = !this.mTitleBarsOnTop ? Math.max(0, this.mTitleBarTextAreaWidth + this.mTitleBarImageAreaWidth) : 0;
        for (int i2 = 0; i2 < this.mMaxItemCount; i2++) {
            iMax = Math.max(iMax, this.mItemBounds[(i2 * 4) + 2]);
        }
        return Math.max(iMax2, this.mWidth - (this.mItemAreaWidth - iMax));
    }

    protected int getPreviousSelectableItemIndex(int i) {
        if (this.mMaxItemCount == 0) {
            return -1;
        }
        int i2 = i == -1 ? 0 : i;
        int i3 = this.mMaxItemCount;
        while (true) {
            i2 = ((i2 + this.mMaxItemCount) - 1) % this.mMaxItemCount;
            int i4 = i3 - 1;
            if (i3 <= 0) {
                return -1;
            }
            if (isItemSelectable(i2)) {
                return i2;
            }
            i3 = i4;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IMenu
    public int getSelectedItem() {
        return this.mSelectedIndex;
    }

    public int getSelectionItemState(int i) {
        return this.mItemStates[i];
    }

    public int getStyle() {
        return this.mStyle;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public boolean isBlinkStateHidden(int i) {
        return false;
    }

    protected boolean isItemSelectable(int i) {
        return this.mItemTypes[i] == 0 || this.mItemTypes[i] == 3 || this.mItemTypes[i] == 4 || this.mItemTypes[i] == 5 || this.mItemTypes[i] == 7 || this.mItemTypes[i] == 6;
    }

    /* JADX WARN: Removed duplicated region for block: B:126:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:5:0x000c A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01c1  */
    @Override // com.digitalchocolate.androidrollergapp.IMenu
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void keyEventOccurred(int r13, int r14) {
        /*
            Method dump skipped, instructions count: 724
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.digitalchocolate.androidrollergapp.MenuObject.keyEventOccurred(int, int):void");
    }

    @Override // com.digitalchocolate.androidrollergapp.IMenu
    public int[] logicUpdate(int i) {
        if (this.mInputKeyVariationTimerShouldDecrease && this.mInputKeyVariationTimer > 0) {
            this.mInputKeyVariationTimer -= i;
        }
        this.mInputCursorBlinkTimer += i;
        this.mInputKeyVariationTimerShouldDecrease = true;
        if (this.mScrollingEvent != 0 && this.mScrollingAreaHeight > this.mItemAreaHeight) {
            this.mScrollingTimer += i;
            int i2 = (this.mScrollingTimer * (this.mTextFontHeight + 0)) / 300;
            this.mScrollingTimer -= (i2 * 300) / (this.mTextFontHeight + 0);
            if (this.mScrollingEvent == 2) {
                this.mVerticalScrollingOffset -= i2;
            } else if (this.mScrollingEvent == 1) {
                this.mVerticalScrollingOffset = i2 + this.mVerticalScrollingOffset;
            }
            if (this.mEnsureScrollOnce == 2) {
                this.mScrollingEvent = 0;
            } else {
                this.mEnsureScrollOnce = 1;
            }
            this.mVerticalScrollingOffset = Math.max(this.mVerticalScrollingOffset, 0);
            this.mVerticalScrollingOffset = Math.min(this.mVerticalScrollingOffset, this.mScrollingAreaHeight - this.mItemAreaHeight);
        }
        if (this.mHasTitleBar) {
            updateTitleBar(i);
        }
        for (int i3 = 0; i3 < this.mMaxItemCount; i3++) {
            updateItem(i3, i);
        }
        smScrollUpAnimations.logicUpdate(i);
        smScrollDownAnimations.logicUpdate(i);
        if (this.mScreenType == 1) {
            int i4 = this.mScrollingEvent == 2 ? 2 : 0;
            if (i4 != smScrollUpAnimations.getCurrentAnimationIndex()) {
                smScrollUpAnimations.setAnimation(i4, -1, true);
            }
            int i5 = this.mScrollingEvent == 1 ? 2 : 0;
            if (i5 != smScrollDownAnimations.getCurrentAnimationIndex()) {
                smScrollDownAnimations.setAnimation(i5, -1, true);
            }
        } else {
            if (smScrollUpAnimations.isFinishedAnimation()) {
                smScrollUpAnimations.setAnimation(0, -1, true);
            }
            if (smScrollDownAnimations.isFinishedAnimation()) {
                smScrollDownAnimations.setAnimation(0, -1, true);
            }
        }
        if (!this.mNewMenuEventAvailable) {
            return null;
        }
        this.mNewMenuEventAvailable = false;
        return this.mLastMenuEvent;
    }

    @Override // com.digitalchocolate.androidrollergapp.IMenu
    public void pointerEventOccurred(int i, int i2, int i3) {
        boolean z;
        boolean z2 = this.mVerticalScrollingOffset > 0;
        boolean z3 = this.mVerticalScrollingOffset + this.mItemAreaHeight < this.mScrollingAreaHeight;
        int i4 = i - this.mLastDrawX;
        int i5 = i2 - this.mLastDrawY;
        if (i3 != 0) {
            if (i3 == 1) {
                this.mScrollingEvent = 0;
                return;
            } else {
                if (i3 == 2) {
                }
                return;
            }
        }
        this.pointerXPos = i4;
        this.pointerYPos = i5;
        if (this.mScrollingAreaHeight > this.mItemAreaHeight) {
            if (z2 && i4 >= (this.mScrollArrowX - (smScrollUpAnimations.getWidth() >> 1)) - 20 && i4 < this.mScrollArrowX + (smScrollUpAnimations.getWidth() >> 1) + 20 && i5 >= (this.mScrollArrowUpY - (smScrollUpAnimations.getHeight() >> 2)) - 20 && i5 < this.mScrollArrowUpY + (smScrollUpAnimations.getHeight() >> 1) + 20) {
                if (this.mScreenType == 0) {
                    int previousSelectableItemIndex = getPreviousSelectableItemIndex(this.mSelectedIndex);
                    if (previousSelectableItemIndex != -1) {
                        setSelectedItem(previousSelectableItemIndex);
                        smScrollUpAnimations.setAnimation(1, 1, true);
                        selectionChanged();
                    }
                } else {
                    this.mScrollingEvent = 2;
                }
            }
            if (z3 && i4 >= (this.mScrollArrowX - (smScrollDownAnimations.getWidth() >> 1)) - 20 && i4 < this.mScrollArrowX + (smScrollDownAnimations.getWidth() >> 1) + 20 && i5 >= (this.mScrollArrowDownY - (smScrollDownAnimations.getHeight() >> 2)) - 20 && i5 < this.mScrollArrowDownY + (smScrollDownAnimations.getHeight() >> 1) + 20) {
                if (this.mScreenType == 0) {
                    int nextSelectableItemIndex = getNextSelectableItemIndex(this.mSelectedIndex);
                    if (nextSelectableItemIndex != -1) {
                        setSelectedItem(nextSelectableItemIndex);
                        smScrollDownAnimations.setAnimation(1, 1, true);
                        selectionChanged();
                    }
                } else {
                    this.mScrollingEvent = 1;
                }
            }
        }
        if (this.mScreenType == 0) {
            int i6 = i4 - this.mItemAreaX;
            int i7 = i5 - this.mItemAreaY;
            if (i6 < 0 || i6 >= this.mItemAreaWidth || i7 < 0 || i7 >= this.mItemAreaHeight) {
                return;
            }
            int i8 = this.mVerticalScrollingOffset + i7;
            for (int i9 = 0; i9 < this.mMaxItemCount; i9++) {
                int i10 = this.mItemBounds[(i9 * 4) + 1];
                int i11 = this.mItemBounds[(i9 * 4) + 3];
                if (i8 >= i10 && i8 < i10 + i11) {
                    if (this.mSelectedIndex == i9) {
                        if (this.mItemTypes[i9] == 0) {
                            z = true;
                        } else if (this.mItemTypes[i9] == 5 || this.mItemTypes[i9] == 4) {
                            this.mSettingItemValues[i9] = (this.mSettingItemValues[i9] + 1) % this.mSettingItemValueCounts[i9];
                            z = true;
                        } else {
                            if (this.mItemTypes[i9] == 3) {
                                setInputEditingMode(true);
                            }
                            z = false;
                        }
                        if (z) {
                            this.mNewMenuEventAvailable = true;
                            this.mLastMenuEvent[0] = 1;
                            this.mLastMenuEvent[1] = this.mItemEvents[i9];
                        }
                    } else {
                        setSelectedItem(i9);
                        selectionChanged();
                        setInputEditingMode(false);
                    }
                }
            }
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IMenu
    public void releaseScreen() {
        if (this.mItemTypes != null) {
            for (int i = 0; i < this.mMaxItemCount; i++) {
                destroyItem(i);
            }
        }
        if (this.mTitleBarSprite != null) {
            this.mTitleBarSprite.freeResources();
        }
    }

    protected void selectionChanged() {
        Game.removeAllSoftKeys();
        if (((this.mItemTypes == null || this.mSelectedIndex >= this.mItemTypes.length || this.mSelectedIndex == -1) ? -1 : this.mItemTypes[this.mSelectedIndex]) == 3) {
            Game.setSoftKey(3, 0);
        } else {
            int i = (this.mItemStates == null || this.mSelectedIndex >= this.mItemStates.length || this.mSelectedIndex == -1) ? 0 : this.mItemStates[this.mSelectedIndex];
            if (this.mSoftKeys[0] != -1 && i != 1) {
                Game.setSoftKey(this.mSoftKeys[0], 0);
            }
        }
        if (this.mSoftKeys[1] != -1) {
            Game.setSoftKey(this.mSoftKeys[1], 0);
        }
        if (this.mScreenType == 1) {
            this.mVerticalScrollingOffset = 0;
            this.mCurrentPage = 0;
        }
        this.mSelectedItemUpdateTimer = 0;
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        setSize(i3, i4);
        this.mX = i;
        this.mY = i2;
        this.mLastDrawX = i;
        this.mLastDrawY = i2;
    }

    public void setExtendedItemParameters(int i, int i2, int i3) {
    }

    public void setFonts(Font font, Font font2, Font font3) {
    }

    public void setImageFonts(ImageFont imageFont, ImageFont imageFont2, ImageFont imageFont3) {
        if (imageFont != null) {
            this.mTitleBarImageFont = imageFont;
            this.mTitleBarFontHeight = imageFont.getHeight();
        }
        if (imageFont2 != null) {
            this.mTextImageFont = imageFont2;
            this.mTextFontHeight = imageFont2.getHeight();
        }
        if (imageFont3 != null) {
            this.mSelectionImageFont = imageFont3;
            this.mSelectionFontHeight = imageFont3.getHeight();
        }
    }

    protected void setInputEditingMode(boolean z) {
        if (!z) {
            this.mInputEditing = false;
            setSelectedItem(this.mSelectedIndex);
            selectionChanged();
        } else {
            this.mInputEditing = true;
            Game.removeAllSoftKeys();
            Game.setSoftKey(1, 0);
            Game.setSoftKey(8, 0);
            this.mInputTextPosition = this.mInputItemTexts[this.mSelectedIndex].length();
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IMenu
    public void setInputItem(int i, int i2, String str, String str2, Image[] imageArr, int i3, int i4) {
        setItem(i, 3, str2, imageArr, i4);
        if (this.mInputItemMappingTypes == null) {
            this.mInputItemMappingTypes = new int[this.mMaxItemCount];
            this.mInputItemTexts = new StringBuffer[this.mMaxItemCount];
        }
        int iMax = Math.max(2, i3);
        if (this.mInputChars == null || iMax > this.mInputChars.length) {
            this.mInputChars = new char[iMax];
        }
        this.mInputItemTexts[i] = new StringBuffer(iMax);
        switch (i2) {
            case 0:
                this.mInputItemMappingTypes[i] = 0;
                break;
            case 1:
                this.mInputItemMappingTypes[i] = 1;
                break;
            case 2:
                this.mInputItemMappingTypes[i] = 3;
                break;
            case 3:
                this.mInputItemMappingTypes[i] = 2;
                break;
        }
        if (str != null) {
            this.mInputItemTexts[i].append(str);
        }
    }

    public void setInputItemDvc(int i, int i2, String str, String str2, SpriteObject spriteObject, int i3, int i4) {
        setInputItem(i, i2, str, str2, null, i3, i4);
        this.mItemSprites[i] = spriteObject;
    }

    @Override // com.digitalchocolate.androidrollergapp.IMenu
    public void setItem(int i, int i2, String str, Image[] imageArr, int i3) {
        if (this.mItemTypes[i] != -1) {
            destroyItem(i);
        }
        this.mItemTypes[i] = i2;
        this.mItemEvents[i] = i3;
        this.mItemIcons[i] = imageArr;
        this.mItemSourceTexts[i] = str;
    }

    public void setItemBlink(int i, int[] iArr) {
    }

    public void setItemDvc(int i, int i2, String str, SpriteObject spriteObject, int i3) {
        setItem(i, i2, str, null, i3);
        this.mItemSprites[i] = spriteObject;
    }

    public void setItemIntValue(int i, int i2) {
        this.mSettingItemValues[i] = i2;
    }

    public void setItemSpecificTitleBar(int i, String str) {
    }

    @Override // com.digitalchocolate.androidrollergapp.IMenu
    public void setScreen(int i, int i2, int i3) {
        this.mScreenType = i;
        this.mSelectedIndex = -1;
        this.mVerticalScrollingOffset = 0;
        this.mScrollingEvent = 0;
        this.mItemAlignment = i3;
        this.mScrollingTimer = 0;
        this.mTitleBarAlignment = i3;
        this.mInputEditing = false;
        this.mItemTypes = new int[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            this.mItemTypes[i4] = -1;
        }
        this.mItemSourceTexts = new String[i2];
        this.mItemTexts = new String[i2][];
        this.mItemEvents = new int[i2];
        this.mItemStates = new int[i2];
        this.mItemIcons = new Image[i2][];
        this.mItemSprites = new SpriteObject[i2];
        this.mMaxItemCount = i2;
        this.mTitleBarScrollingDirection = 1;
        this.mHasTitleBar = false;
        this.mSelectedItemScrollingDirection = 1;
        this.mSoftKeys[0] = -1;
        this.mSoftKeys[1] = -1;
        setImageFonts(smDefaultTitleBarImageFont, smDefaultTextImageFont, smDefaultSelectionImageFont);
        setStyle(3);
        this.mScreenMaxWidth = Toolkit.getScreenWidth();
        this.mScreenMaxHeight = Toolkit.getScreenHeight();
        this.mScreenSoftkeyAreaHeight = Toolkit.getSoftKeyAreaHeight();
    }

    public void setScreenBounds(int i, int i2, int i3, int i4, int i5) {
        this.mScreenMinX = i;
        this.mScreenMinY = i2;
        this.mScreenMaxWidth = i3;
        this.mScreenMaxHeight = i4;
        this.mScreenSoftkeyAreaHeight = i5;
    }

    @Override // com.digitalchocolate.androidrollergapp.IMenu
    public void setSelectedItem(int i) {
        this.mSelectedIndex = getNextSelectableItemIndex(i - 1);
        if (this.mSelectedIndex == -1) {
            return;
        }
        this.mSelectedItemUpdateTimer = 0;
        this.mSelectedItemScrollingOffset = 0;
        this.mSelectedItemScrollingDirection = 0;
        if (this.mScrollingAreaHeight > this.mItemAreaHeight) {
            int i2 = this.mVerticalScrollingOffset / this.mSelectionItemHeight;
            int i3 = this.mScrollingAreaHeight / this.mSelectionItemHeight;
            int i4 = this.mItemBounds[(i * 4) + 1] / this.mSelectionItemHeight;
            this.mVerticalScrollingOffset = Math.min(Math.max(Math.min(Math.max(i2, i4 - (this.mSelectionItemsPerScreen >> 1)), i4 - ((this.mSelectionItemsPerScreen - 1) >> 1)), 0), i3 - this.mSelectionItemsPerScreen) * this.mSelectionItemHeight;
        } else {
            this.mVerticalScrollingOffset = 0;
        }
        this.mSelectedItemScrollingDirection = 0;
        this.mSelectedItemScrollingOffset = 0;
    }

    public void setSelectionItemState(int i, int i2) {
        this.mItemStates[i] = i2;
    }

    @Override // com.digitalchocolate.androidrollergapp.IMenu
    public void setSize(int i, int i2) {
        setSize(i, i2, false);
    }

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
        this.mLastDrawX = Integer.MAX_VALUE;
        this.mLastDrawY = Integer.MAX_VALUE;
    }

    public void setSliderItem(int i, String str, Image[] imageArr, int i2, int i3, int i4) {
    }

    public void setSliderItemDvc(int i, String str, SpriteObject spriteObject, int i2, int i3, int i4) {
    }

    @Override // com.digitalchocolate.androidrollergapp.IMenu
    public void setSoftkey(int i, int i2) {
        this.mSoftKeys[i2] = i;
    }

    public void setStyle(int i) {
        switch (i) {
            case 1:
                this.mEnableBackground = true;
                this.mBackgroundColor = 16777215;
                this.mBackgroundGradientColor = 16777215;
                this.mTitleBarsOnTop = false;
                this.mSeparateTitleBarBackground = false;
                this.mTextColor = 0;
                this.mSelectedItemBackgroundColor = 9486550;
                this.mEnableBorders = false;
                this.mLooseItemAreaAlignment = true;
                break;
            case 2:
                this.mEnableBackground = false;
                this.mTitleBarsOnTop = true;
                this.mTextColor = 16777215;
                this.mSelectedItemBackgroundColor = 16776960;
                this.mEnableBorders = false;
                this.mLooseItemAreaAlignment = true;
                break;
            case 3:
                this.mEnableBackground = true;
                this.mBackgroundColor = 1141255;
                this.mBackgroundGradientColor = 6988587;
                this.mTitleBarsOnTop = false;
                this.mSeparateTitleBarBackground = true;
                this.mTitleBarBackgroundColor = 7592200;
                this.mTitleBarBackgroundGradientColor = 7592200;
                this.mTextColor = 16777215;
                this.mSelectedItemBackgroundColor = 16776960;
                this.mEnableBorders = false;
                this.mLooseItemAreaAlignment = true;
                break;
            case 4:
                this.mEnableBackground = true;
                this.mBackgroundColor = 1141255;
                this.mBackgroundGradientColor = 6988587;
                this.mTitleBarsOnTop = true;
                this.mSeparateTitleBarBackground = true;
                this.mTitleBarBackgroundColor = 7592200;
                this.mTitleBarBackgroundGradientColor = 7592200;
                this.mTextColor = 16777215;
                this.mSelectedItemBackgroundColor = 16776960;
                this.mEnableBorders = true;
                this.mLooseItemAreaAlignment = false;
                break;
            case 5:
                this.mEnableBackground = true;
                this.mBackgroundColor = 1141255;
                this.mBackgroundGradientColor = 6988587;
                this.mTitleBarsOnTop = false;
                this.mSeparateTitleBarBackground = true;
                this.mTextColor = 16777215;
                this.mSelectedItemBackgroundColor = 16776960;
                this.mEnableBorders = true;
                this.mLooseItemAreaAlignment = false;
                break;
        }
        this.mScreenXMargin = 9;
        this.mScreenYMargin = 11;
        this.mStyle = i;
    }

    public void setSubMenuItem(int i, MenuObject menuObject) {
    }

    public void setSwitchItem(int i, String str, Image[] imageArr, String[] strArr, Image[] imageArr2, int i2) {
        setItem(i, 5, str, imageArr, i2);
        if (this.mSettingItemValues == null) {
            this.mSettingItemValues = new int[this.mMaxItemCount];
            this.mSettingItemValueCounts = new int[this.mMaxItemCount];
        }
        this.mSettingItemValues[i] = 0;
        this.mSettingItemValueCounts[i] = strArr != null ? strArr.length : 0;
        if (this.mSwitchItemTexts == null) {
            this.mSwitchItemTexts = new String[this.mMaxItemCount][];
        }
        this.mSwitchItemTexts[i] = strArr;
        this.mSettingItemValueCounts[i] = Math.max(this.mSettingItemValueCounts[i], imageArr2 != null ? imageArr2.length : 0);
        if (this.mSwitchItemIcons == null) {
            this.mSwitchItemIcons = new Image[this.mMaxItemCount][];
        }
        this.mSwitchItemIcons[i] = imageArr2;
        if (this.mSwitchItemSprites == null) {
            this.mSwitchItemSprites = new SpriteObject[this.mMaxItemCount];
        }
    }

    public void setSwitchItemDvc(int i, String str, SpriteObject spriteObject, String[] strArr, SpriteObject spriteObject2, int i2) {
        setSwitchItem(i, str, null, strArr, null, i2);
        this.mSettingItemValueCounts[i] = Math.max(this.mSettingItemValueCounts[i], spriteObject2 != null ? spriteObject2.getAnimationCount() : 0);
        this.mItemSprites[i] = spriteObject;
        this.mSwitchItemSprites[i] = spriteObject2;
    }

    public void setTableItem(int i, int[] iArr, int[] iArr2, String[] strArr, Image[] imageArr) {
        setItem(i, 6, null, null, -1);
        if (this.mTableItemColumnWeights == null) {
            this.mTableItemColumnWeights = new int[this.mMaxItemCount][];
            this.mTableItemColumnAlignments = new int[this.mMaxItemCount][];
            this.mTableItemSourceTexts = new String[this.mMaxItemCount][];
            this.mTableItemIcons = new Image[this.mMaxItemCount][];
        }
        if (this.mTableItemSprites == null) {
            this.mTableItemSprites = new SpriteObject[this.mMaxItemCount][];
        }
        this.mTableItemColumnWeights[i] = iArr;
        this.mTableItemColumnAlignments[i] = iArr2;
        this.mTableItemSourceTexts[i] = strArr;
        this.mTableItemIcons[i] = imageArr;
    }

    public void setTableItemDvc(int i, int[] iArr, int[] iArr2, String[] strArr, SpriteObject[] spriteObjectArr) {
        setTableItem(i, iArr, iArr2, strArr, null);
        this.mTableItemSprites[i] = spriteObjectArr;
    }

    public void setTextColor(int i) {
        this.mTextColor = i;
    }

    @Override // com.digitalchocolate.androidrollergapp.IMenu
    public void setTitleBar(String str, Image image, int i) {
        this.mHasTitleBar = (str == null && image == null) ? false : true;
        this.mTitleBarSourceText = str;
        this.mTitleBarImage = image;
        this.mTitleBarAlignment = i;
    }

    public void setTitleBarDvc(String str, SpriteObject spriteObject, int i) {
        setTitleBar(str, null, i);
        this.mHasTitleBar |= spriteObject != null;
        this.mTitleBarSprite = spriteObject;
    }

    @Override // com.digitalchocolate.androidrollergapp.IMenu
    public void setVisible() {
        selectionChanged();
        this.mScrollingEvent = 0;
        this.mTitleBarUpdateTimer = 0;
        smScrollUpAnimations.setAnimation(0, -1, true);
        smScrollDownAnimations.setAnimation(0, -1, true);
    }

    protected void updateHorizontalScrolling(int i, int i2, int i3) {
        int i4 = i2 - i3;
        if (i4 <= 0) {
            this.mTemporaryScrollingOffset = 0;
            return;
        }
        int i5 = -25;
        int i6 = i4 + 25;
        this.mTemporaryUpdateTimer -= i;
        while (this.mTemporaryUpdateTimer < 0) {
            if (this.mTemporaryScrollingDirection == 1) {
                this.mTemporaryScrollingOffset++;
            } else {
                this.mTemporaryScrollingOffset--;
            }
            if (this.mTemporaryScrollingOffset > i6) {
                this.mTemporaryScrollingOffset = i6;
                this.mTemporaryScrollingDirection = 0;
            } else if (this.mTemporaryScrollingOffset < i5) {
                this.mTemporaryScrollingOffset = i5;
                this.mTemporaryScrollingDirection = 1;
            }
            this.mTemporaryUpdateTimer += 40;
        }
    }

    protected void updateItem(int i, int i2) {
        if (this.mItemSprites[i] != null && this.mItemSprites[i] != null) {
            this.mItemSprites[i].logicUpdate(i2);
        }
        if (this.mSwitchItemSprites != null && this.mSwitchItemSprites[i] != null) {
            this.mSwitchItemSprites[i].logicUpdate(i2);
        }
        if (this.mTableItemSprites != null && this.mTableItemSprites[i] != null) {
            for (int i3 = 0; i3 < this.mTableItemSprites[i].length; i3++) {
                SpriteObject spriteObject = this.mTableItemSprites[i][i3];
                if (spriteObject != null) {
                    spriteObject.logicUpdate(i2);
                }
            }
        }
        if (i != this.mSelectedIndex || this.mItemTexts[i] == null) {
            return;
        }
        this.mTemporaryUpdateTimer = this.mSelectedItemUpdateTimer;
        String[] strArr = this.mItemTexts[i];
        if (strArr != null) {
            this.mTemporaryScrollingOffset = this.mSelectedItemScrollingOffset;
            this.mTemporaryScrollingDirection = this.mSelectedItemScrollingDirection;
            updateHorizontalScrolling(i2, this.mSelectionImageFont.stringWidth(strArr[0]), this.mItemTextAreaWidths[i] - (5 << 1));
            this.mSelectedItemScrollingOffset = this.mTemporaryScrollingOffset;
            this.mSelectedItemScrollingDirection = this.mTemporaryScrollingDirection;
        }
        this.mSelectedItemUpdateTimer = this.mTemporaryUpdateTimer;
    }

    protected void updateTitleBar(int i) {
        if (this.mTitleBarSprite != null) {
            this.mTitleBarSprite.logicUpdate(i);
        }
        String[] strArr = this.mTitleBarText != null ? this.mTitleBarText : null;
        if (strArr != null) {
            this.mTemporaryUpdateTimer = this.mTitleBarUpdateTimer;
            this.mTemporaryScrollingOffset = this.mTitleBarScrollingOffset;
            this.mTemporaryScrollingDirection = this.mTitleBarScrollingDirection;
            updateHorizontalScrolling(i, this.mTitleBarImageFont.stringWidth(strArr[0]), this.mTitleBarTextAreaWidth - 10);
            this.mTitleBarScrollingOffset = this.mTemporaryScrollingOffset;
            this.mTitleBarScrollingDirection = this.mTemporaryScrollingDirection;
            this.mTitleBarUpdateTimer = this.mTemporaryUpdateTimer;
        }
    }

    protected void updateVisibleLine(int i, int i2) {
        this.mTemporaryUpdateTimer -= i;
        if (this.mTemporaryUpdateTimer < 0) {
            this.mTemporaryVisibleLine++;
            if (this.mTemporaryVisibleLine >= i2) {
                this.mTemporaryVisibleLine = 0;
            }
            this.mTemporaryUpdateTimer += 2000;
        }
    }
}
