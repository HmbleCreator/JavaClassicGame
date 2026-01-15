package com.digitalchocolate.androidrollergapp;

import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* loaded from: classes.dex */
public class FlowProcessor {
    private static final boolean DEBUG_FLOW = false;
    public static final int NO_LOADING = -1;
    private static final int NO_STATE_CHANGE = -1;
    private static final boolean USE_3SK_FUNCTIONALITY = false;
    private int mBranchCount;
    private int mBranchStartIndex;
    private short[] mCurrentBranch;
    private short[] mCurrentGameScreen;
    private MenuObject mCurrentMenu;
    private boolean mDavinciLoading;
    private int mDavinciLoadingPercentage;
    private short[] mEventTargets;
    private byte[] mFlowBinary;
    private FlowHandler mFlowHandler;
    private int mGameScreenCount;
    private int mGameScreenStartIndex;
    private boolean mGoingForward;
    private short[][] mLoadedBranches;
    private short[][] mLoadedGameScreens;
    private MenuObject[] mLoadedMenus;
    private boolean mLoading;
    private int mLoadingCounter;
    private int mMaxLoadingCount;
    private int mMenuScreenCount;
    private byte[] mMenuSelectedItems;
    private short[][] mMenuSoftkeyEvents;
    private short[] mMenusToLoad;
    private boolean mSleepCounter;
    private int mStateGroupLoadingCount;
    private byte[] mStateGroups;
    private short[] mStateHistory;
    private int mStateHistoryPointer;
    private int mStateLoadingCount;
    private short[] mStateOffsets;
    private boolean mStoreStateToHistory;
    private int mWildEventCount;
    private int mCurrentState = -1;
    private int mNextState = -1;

    public FlowProcessor(int i, FlowHandler flowHandler) throws IOException {
        initializeFlow(i, flowHandler);
    }

    private void activateBranch(int i) {
        if (this.mLoadedBranches[i - this.mBranchStartIndex] == null) {
            loadBranch(i);
        }
        this.mCurrentBranch = this.mLoadedBranches[i - this.mBranchStartIndex];
        this.mStoreStateToHistory = false;
    }

    private void activateGameScreen(int i) {
        if (this.mLoadedGameScreens[i - this.mGameScreenStartIndex] == null) {
            loadGameScreen(i);
        }
        this.mCurrentGameScreen = this.mLoadedGameScreens[i - this.mGameScreenStartIndex];
        this.mStoreStateToHistory = this.mFlowHandler.shouldStoreStateToHistory(i);
    }

    private void activateMenuScreen(int i) {
        this.mCurrentMenu = this.mLoadedMenus[i];
        if (this.mGoingForward) {
            this.mCurrentMenu.setSelectedItem(0);
        } else {
            this.mCurrentMenu.setSelectedItem(this.mMenuSelectedItems[this.mCurrentState] & DChocImage.COLOR_DEPTH_DEFAULT);
        }
        this.mFlowHandler.processMenu(i, this.mCurrentMenu, 2);
        this.mCurrentMenu.setVisible();
        this.mFlowHandler.processMenu(i, this.mCurrentMenu, 3);
        this.mStoreStateToHistory = this.mFlowHandler.shouldStoreStateToHistory(i);
    }

    private void activateState(int i) {
        if (i < this.mMenuScreenCount) {
            activateMenuScreen(i);
        } else if (i < this.mMenuScreenCount + this.mGameScreenCount) {
            activateGameScreen(i);
        } else {
            activateBranch(i);
        }
    }

    private void changeState() throws IOException {
        int iCurrentTimeMillis;
        DChocMIDlet.skipTimer();
        while (this.mNextState != -1) {
            if (!this.mLoading) {
                byte b = this.mCurrentState != -1 ? this.mStateGroups[this.mCurrentState] : (byte) -1;
                byte b2 = this.mStateGroups[this.mNextState];
                if (this.mCurrentState != -1) {
                    if (b != -1) {
                        if (b != b2 && !this.mFlowHandler.isChildGroup(b, b2)) {
                            this.mFlowHandler.unloadStateGroup(b, b2);
                            int length = this.mStateGroups.length;
                            while (true) {
                                length--;
                                if (length < 0) {
                                    break;
                                }
                                if (this.mStateGroups[length] == b && isMenuState(length)) {
                                    if (this.mLoadedMenus[length] != null) {
                                    }
                                    removeFromMenuCache(length);
                                }
                            }
                        }
                    } else if (isMenuState(this.mCurrentState)) {
                        if (this.mLoadedMenus[this.mCurrentState] != null) {
                        }
                        removeFromMenuCache(this.mCurrentState);
                    }
                    this.mFlowHandler.unloadState(this.mCurrentState, this.mNextState);
                    deactivateState();
                }
                this.mStateGroupLoadingCount = 0;
                if (b2 == -1 || b2 == b) {
                    int i = this.mNextState;
                    if (isMenuState(i) && this.mLoadedMenus[i] == null) {
                        this.mMenusToLoad = new short[]{(short) i};
                    } else {
                        this.mMenusToLoad = new short[0];
                    }
                } else {
                    if (!this.mFlowHandler.isChildGroup(b2, b)) {
                        this.mStateGroupLoadingCount = this.mFlowHandler.getStateGroupLoadingCount(b2);
                    }
                    int i2 = 0;
                    for (int i3 = 0; i3 < this.mStateGroups.length; i3++) {
                        if (this.mStateGroups[i3] == b2 && isMenuState(i3) && this.mLoadedMenus[i3] == null) {
                            i2++;
                        }
                    }
                    this.mMenusToLoad = new short[i2];
                    int i4 = 0;
                    for (int i5 = 0; i5 < this.mStateGroups.length; i5++) {
                        if (this.mStateGroups[i5] == b2 && isMenuState(i5) && this.mLoadedMenus[i5] == null) {
                            this.mMenusToLoad[i4] = (short) i5;
                            i4++;
                        }
                    }
                }
                this.mStateLoadingCount = this.mFlowHandler.getStateLoadingCount(this.mNextState);
                this.mMaxLoadingCount = this.mStateGroupLoadingCount + this.mStateLoadingCount + this.mMenusToLoad.length;
                this.mDavinciLoadingPercentage = this.mFlowHandler.getDavinciLoadingPercentage(this.mNextState);
                if (this.mMaxLoadingCount > 0) {
                    this.mFlowHandler.setLoading(this.mNextState, true);
                    this.mLoadingCounter = 0;
                    this.mLoading = true;
                    this.mDavinciLoading = false;
                    Game.removeAllSoftKeys();
                    return;
                }
            } else {
                if (this.mLoadingCounter < this.mMaxLoadingCount) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    do {
                        if (this.mDavinciLoading) {
                            DavinciUtilities.loadNext();
                        } else if (this.mLoadingCounter < this.mStateGroupLoadingCount) {
                            this.mFlowHandler.loadStateGroup(this.mStateGroups[this.mNextState], this.mLoadingCounter);
                        } else if (this.mLoadingCounter < this.mStateLoadingCount) {
                            this.mFlowHandler.loadState(this.mNextState, this.mLoadingCounter - this.mStateGroupLoadingCount);
                        } else {
                            loadMenu(this.mMenusToLoad[(this.mLoadingCounter - this.mStateGroupLoadingCount) - this.mStateLoadingCount]);
                        }
                        this.mLoadingCounter++;
                        iCurrentTimeMillis = (int) (System.currentTimeMillis() - jCurrentTimeMillis);
                        if (this.mLoadingCounter >= this.mMaxLoadingCount) {
                            return;
                        }
                    } while (iCurrentTimeMillis < 50);
                    return;
                }
                if (!this.mDavinciLoading) {
                    this.mMaxLoadingCount = DavinciUtilities.getAdditionalLoadingCount();
                    if (this.mMaxLoadingCount > 0) {
                        this.mLoadingCounter = 0;
                        this.mDavinciLoading = true;
                        return;
                    }
                }
                this.mFlowHandler.setLoading(this.mNextState, false);
                this.mLoading = false;
            }
            int i6 = this.mCurrentState;
            this.mCurrentState = this.mNextState;
            this.mNextState = -1;
            int i7 = this.mStateHistoryPointer;
            while (true) {
                i7--;
                if (i7 >= 0) {
                    if (this.mStateHistory[i7] == this.mCurrentState) {
                        this.mStateHistoryPointer = i7;
                        break;
                    }
                } else {
                    break;
                }
            }
            activateState(this.mCurrentState);
            this.mFlowHandler.switchState(i6, this.mCurrentState, this.mCurrentMenu);
            if (this.mCurrentBranch != null) {
                processBranch(this.mCurrentState);
            }
            if (this.mCurrentState == 57) {
                Game.removeAllSoftKeys();
                Game.setSoftKey(1, 0);
            }
        }
    }

    private void clearMenuCache() {
        int i = this.mMenuScreenCount;
        while (true) {
            i--;
            if (i < 0) {
                break;
            } else {
                removeFromMenuCache(i);
            }
        }
        if (this.mNextState == -1) {
            this.mNextState = this.mCurrentState;
        }
        this.mCurrentState = -1;
        this.mLoading = false;
    }

    private void deactivateState() {
        this.mCurrentMenu = null;
        this.mCurrentGameScreen = null;
        this.mCurrentBranch = null;
        Game.removeAllSoftKeys();
    }

    private void initializeFlow(int i, FlowHandler flowHandler) throws IOException {
        this.mFlowHandler = flowHandler;
        byte[] resourceBytes = Toolkit.getResourceBytes(i);
        this.mFlowBinary = resourceBytes;
        int i2 = resourceBytes[0] & DChocImage.COLOR_DEPTH_DEFAULT;
        this.mWildEventCount = resourceBytes[1] & DChocImage.COLOR_DEPTH_DEFAULT;
        this.mEventTargets = new short[i2];
        int i3 = 2;
        int i4 = 0;
        while (i4 < i2) {
            int i5 = i3 + 1;
            this.mEventTargets[i4] = (short) (((resourceBytes[i3] & DChocImage.COLOR_DEPTH_DEFAULT) << 8) | (resourceBytes[i5] & DChocImage.COLOR_DEPTH_DEFAULT));
            i4++;
            i3 = i5 + 1;
        }
        int i6 = i3 + 1;
        int i7 = i6 + 1;
        this.mMenuScreenCount = resourceBytes[i6] & DChocImage.COLOR_DEPTH_DEFAULT;
        this.mGameScreenStartIndex = this.mMenuScreenCount;
        int i8 = i7 + 1;
        this.mGameScreenCount = resourceBytes[i7] & DChocImage.COLOR_DEPTH_DEFAULT;
        this.mBranchStartIndex = this.mGameScreenStartIndex + this.mGameScreenCount;
        int i9 = i8 + 1;
        int i10 = resourceBytes[i8] & DChocImage.COLOR_DEPTH_DEFAULT;
        int i11 = this.mMenuScreenCount + this.mGameScreenCount + i10;
        this.mStateGroups = new byte[i11];
        int i12 = i9;
        int i13 = 0;
        while (i13 < i11) {
            this.mStateGroups[i13] = resourceBytes[i12];
            i13++;
            i12++;
        }
        this.mStateOffsets = new short[i11];
        int i14 = 0;
        while (i14 < i11) {
            int i15 = i12 + 1;
            this.mStateOffsets[i14] = (short) (((resourceBytes[i12] & DChocImage.COLOR_DEPTH_DEFAULT) << 8) | (resourceBytes[i15] & DChocImage.COLOR_DEPTH_DEFAULT));
            i14++;
            i12 = i15 + 1;
        }
        this.mNextState = resourceBytes[i12] & DChocImage.COLOR_DEPTH_DEFAULT;
        this.mLoadedMenus = new MenuObject[this.mMenuScreenCount];
        this.mMenuSoftkeyEvents = new short[this.mMenuScreenCount][];
        this.mLoadedGameScreens = new short[this.mGameScreenCount][];
        this.mLoadedBranches = new short[i10][];
        this.mMenuSelectedItems = new byte[this.mMenuScreenCount];
        int i16 = this.mMenuScreenCount;
        int i17 = 0;
        while (i17 < this.mGameScreenCount) {
            loadGameScreen(i16);
            i17++;
            i16++;
        }
        int i18 = 0;
        while (i18 < i10) {
            loadBranch(i16);
            i18++;
            i16++;
        }
        this.mBranchCount = i10;
        this.mStateHistory = new short[this.mMenuScreenCount + i10 + this.mGameScreenCount];
        this.mStateHistoryPointer = 0;
        this.mStoreStateToHistory = false;
    }

    private boolean isGameState(int i) {
        return i >= this.mGameScreenStartIndex && i < this.mGameScreenStartIndex + this.mGameScreenCount;
    }

    private boolean isMenuState(int i) {
        return i < this.mMenuScreenCount;
    }

    private void loadBranch(int i) {
        byte[] bArr = this.mFlowBinary;
        short s = this.mStateOffsets[this.mMenuScreenCount + this.mGameScreenCount + (i - this.mBranchStartIndex)];
        short[] sArr = {(short) (bArr[s] & DChocImage.COLOR_DEPTH_DEFAULT), (short) (bArr[s + 1] & DChocImage.COLOR_DEPTH_DEFAULT)};
        if (sArr[0] == 255) {
            sArr[0] = -1;
        }
        if (sArr[1] == 255) {
            sArr[1] = -1;
        }
        this.mLoadedBranches[i - this.mBranchStartIndex] = sArr;
    }

    private void loadGameScreen(int i) {
        byte[] bArr = this.mFlowBinary;
        short s = this.mStateOffsets[this.mMenuScreenCount + (i - this.mGameScreenStartIndex)];
        int i2 = s + 1;
        int i3 = bArr[s] & 255;
        short[] sArr = new short[i3];
        int i4 = i2;
        int i5 = 0;
        while (i5 < i3) {
            int i6 = i4 + 1;
            sArr[i5] = (short) (bArr[i4] & DChocImage.COLOR_DEPTH_DEFAULT);
            if (sArr[i5] == 255) {
                sArr[i5] = -1;
            }
            i5++;
            i4 = i6;
        }
        this.mLoadedGameScreens[i - this.mGameScreenStartIndex] = sArr;
    }

    private static int loadImagesOrAnimations(byte[] bArr, int i, int i2, Image[][] imageArr, SpriteObject[] spriteObjectArr) {
        imageArr[0] = null;
        spriteObjectArr[0] = null;
        int[] iArr = new int[i2];
        byte[] bArr2 = new byte[i2];
        boolean z = false;
        boolean z2 = false;
        int i3 = i;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i3 + 1;
            int i6 = i5 + 1;
            int i7 = ((bArr[i3] & DChocImage.COLOR_DEPTH_DEFAULT) << 24) | ((bArr[i5] & DChocImage.COLOR_DEPTH_DEFAULT) << 16);
            int i8 = i6 + 1;
            int i9 = i7 | ((bArr[i6] & DChocImage.COLOR_DEPTH_DEFAULT) << 8);
            int i10 = i8 + 1;
            iArr[i4] = i9 | (bArr[i8] & DChocImage.COLOR_DEPTH_DEFAULT);
            z |= iArr[i4] != -1;
            i3 = i10 + 1;
            bArr2[i4] = bArr[i10];
            z2 |= bArr2[i4] == 2 || bArr2[i4] == 3;
        }
        if (z) {
            if (z2) {
                spriteObjectArr[0] = new SpriteObject(DavinciUtilities.loadAnimations(iArr, false), true);
            } else {
                imageArr[0] = new Image[i2];
                for (int i11 = 0; i11 < i2; i11++) {
                    imageArr[0][i11] = bArr2[i11] == 0 ? Toolkit.getImage(iArr[i11]) : bArr2[i11] == 1 ? DavinciUtilities.getImage(iArr[i11], 0, 1024) : null;
                }
            }
        }
        return i3;
    }

    private void loadMenu(int i) {
        if (!this.mFlowHandler.isMenuNeeded(i)) {
            return;
        }
        byte[] bArr = this.mFlowBinary;
        short s = this.mStateOffsets[i];
        int i2 = s + 1;
        byte b = bArr[s];
        Vector vector = new Vector();
        Image[][] imageArr = new Image[1][];
        SpriteObject[] spriteObjectArr = new SpriteObject[1];
        int i3 = i2 + 1;
        byte b2 = bArr[i2];
        int i4 = i3 + 1;
        byte b3 = bArr[i3];
        CustomMenuObject customMenuObject = new CustomMenuObject();
        this.mFlowHandler.processMenu(i, customMenuObject, 0);
        this.mFlowHandler.menuSetScreen(i, customMenuObject, b2, b, b3);
        int i5 = i4 + 1;
        this.mFlowHandler.menuSetStyle(i, customMenuObject, bArr[i4]);
        int i6 = i5 + 1;
        int i7 = i6 + 1;
        int i8 = ((bArr[i5] & DChocImage.COLOR_DEPTH_DEFAULT) << 24) | ((bArr[i6] & DChocImage.COLOR_DEPTH_DEFAULT) << 16);
        int i9 = i7 + 1;
        String strProcessText = this.mFlowHandler.processText(i, i8 | ((bArr[i7] & DChocImage.COLOR_DEPTH_DEFAULT) << 8) | (bArr[i9] & DChocImage.COLOR_DEPTH_DEFAULT), -1);
        int iLoadImagesOrAnimations = loadImagesOrAnimations(bArr, i9 + 1, 1, imageArr, spriteObjectArr);
        Image image = imageArr[0] == null ? null : imageArr[0][0];
        SpriteObject spriteObject = spriteObjectArr[0];
        int i10 = iLoadImagesOrAnimations + 1;
        byte b4 = bArr[iLoadImagesOrAnimations];
        if ((strProcessText != null && strProcessText.length() != 0) || spriteObject != null || image != null) {
            if (spriteObject != null) {
                this.mFlowHandler.menuSetTitleBarDvc(i, customMenuObject, strProcessText.toUpperCase(), spriteObject, b4);
            } else {
                this.mFlowHandler.menuSetTitleBar(i, customMenuObject, strProcessText.toUpperCase(), image, b4);
            }
        }
        int i11 = i10 + 1;
        byte b5 = bArr[i10];
        int i12 = i11 + 1;
        int i13 = bArr[i11] & DChocImage.COLOR_DEPTH_DEFAULT;
        if (i13 != 255) {
            vector.addElement(new Integer(b5));
            vector.addElement(new Integer(i13));
        }
        this.mFlowHandler.menuSetSoftkey(i, customMenuObject, b5, 0);
        int i14 = i12 + 1;
        byte b6 = bArr[i12];
        int i15 = i14 + 1;
        int i16 = bArr[i14] & DChocImage.COLOR_DEPTH_DEFAULT;
        if (i16 != 255) {
            vector.addElement(new Integer(b6));
            vector.addElement(new Integer(i16));
        }
        if (i == 8) {
        }
        if (i == 7) {
        }
        this.mFlowHandler.menuSetSoftkey(i, customMenuObject, b6, 1);
        int iLoadImagesOrAnimations2 = i15;
        for (int i17 = 0; i17 < b; i17++) {
            int i18 = iLoadImagesOrAnimations2 + 1;
            byte b7 = bArr[iLoadImagesOrAnimations2];
            switch (b7) {
                case 0:
                    int i19 = i18 + 1;
                    int i20 = i19 + 1;
                    int i21 = ((bArr[i19] & DChocImage.COLOR_DEPTH_DEFAULT) << 16) | ((bArr[i18] & DChocImage.COLOR_DEPTH_DEFAULT) << 24);
                    int i22 = i20 + 1;
                    String strProcessText2 = this.mFlowHandler.processText(i, i21 | ((bArr[i20] & DChocImage.COLOR_DEPTH_DEFAULT) << 8) | (bArr[i22] & DChocImage.COLOR_DEPTH_DEFAULT), i17);
                    int iLoadImagesOrAnimations3 = loadImagesOrAnimations(bArr, i22 + 1, 3, imageArr, spriteObjectArr);
                    Image[] imageArr2 = imageArr[0];
                    SpriteObject spriteObject2 = spriteObjectArr[0];
                    int i23 = iLoadImagesOrAnimations3 + 1;
                    int i24 = bArr[iLoadImagesOrAnimations3] & DChocImage.COLOR_DEPTH_DEFAULT;
                    this.mFlowHandler.menuSetItem(i, customMenuObject, i17, b7, strProcessText2, spriteObject2, imageArr2, i24 == 255 ? -1 : i24);
                    iLoadImagesOrAnimations2 = i23;
                    break;
                case 1:
                    int i25 = i18 + 1;
                    int i26 = i25 + 1;
                    int i27 = ((bArr[i25] & DChocImage.COLOR_DEPTH_DEFAULT) << 16) | ((bArr[i18] & DChocImage.COLOR_DEPTH_DEFAULT) << 24);
                    int i28 = i26 + 1;
                    String strProcessText3 = this.mFlowHandler.processText(i, i27 | ((bArr[i26] & DChocImage.COLOR_DEPTH_DEFAULT) << 8) | (bArr[i28] & DChocImage.COLOR_DEPTH_DEFAULT), i17);
                    int iLoadImagesOrAnimations4 = loadImagesOrAnimations(bArr, i28 + 1, 4, imageArr, spriteObjectArr);
                    Image[] imageArr3 = imageArr[0];
                    SpriteObject spriteObject3 = spriteObjectArr[0];
                    int i29 = iLoadImagesOrAnimations4 + 1;
                    int i30 = bArr[iLoadImagesOrAnimations4];
                    String[] strArr = new String[i30];
                    int i31 = i29;
                    int i32 = 0;
                    while (i32 < i30) {
                        int i33 = i31 + 1;
                        int i34 = i33 + 1;
                        int i35 = ((bArr[i31] & DChocImage.COLOR_DEPTH_DEFAULT) << 24) | ((bArr[i33] & DChocImage.COLOR_DEPTH_DEFAULT) << 16);
                        int i36 = i34 + 1;
                        strArr[i32] = this.mFlowHandler.processText(i, i35 | ((bArr[i34] & DChocImage.COLOR_DEPTH_DEFAULT) << 8) | (bArr[i36] & DChocImage.COLOR_DEPTH_DEFAULT), i17);
                        i32++;
                        i31 = i36 + 1;
                    }
                    int iLoadImagesOrAnimations5 = loadImagesOrAnimations(bArr, i31, i30, imageArr, spriteObjectArr);
                    Image[] imageArr4 = imageArr[0];
                    SpriteObject spriteObject4 = spriteObjectArr[0];
                    int i37 = iLoadImagesOrAnimations5 + 1;
                    int i38 = bArr[iLoadImagesOrAnimations5] & DChocImage.COLOR_DEPTH_DEFAULT;
                    this.mFlowHandler.menuSetSwitchItem(i, customMenuObject, i17, strProcessText3, spriteObject3, imageArr3, strArr, spriteObject4, imageArr4, i38 == 255 ? -1 : i38);
                    iLoadImagesOrAnimations2 = i37;
                    break;
                case 2:
                    int i39 = i18 + 1;
                    int i40 = i39 + 1;
                    int i41 = ((bArr[i39] & DChocImage.COLOR_DEPTH_DEFAULT) << 16) | ((bArr[i18] & DChocImage.COLOR_DEPTH_DEFAULT) << 24);
                    int i42 = i40 + 1;
                    String strProcessText4 = this.mFlowHandler.processText(i, i41 | ((bArr[i40] & DChocImage.COLOR_DEPTH_DEFAULT) << 8) | (bArr[i42] & DChocImage.COLOR_DEPTH_DEFAULT), i17);
                    int iLoadImagesOrAnimations6 = loadImagesOrAnimations(bArr, i42 + 1, 4, imageArr, spriteObjectArr);
                    Image[] imageArr5 = imageArr[0];
                    SpriteObject spriteObject5 = spriteObjectArr[0];
                    int i43 = iLoadImagesOrAnimations6 + 1;
                    int i44 = bArr[iLoadImagesOrAnimations6] & DChocImage.COLOR_DEPTH_DEFAULT;
                    int i45 = i43 + 1;
                    int i46 = bArr[i43] & DChocImage.COLOR_DEPTH_DEFAULT;
                    int i47 = i45 + 1;
                    int i48 = bArr[i45] & DChocImage.COLOR_DEPTH_DEFAULT;
                    this.mFlowHandler.menuSetSliderItem(i, customMenuObject, i17, strProcessText4, spriteObject5, imageArr5, i44, i46, i48 == 255 ? -1 : i48);
                    iLoadImagesOrAnimations2 = i47;
                    break;
                case 3:
                    int i49 = i18 + 1;
                    int i50 = bArr[i18] & DChocImage.COLOR_DEPTH_DEFAULT;
                    int i51 = i49 + 1;
                    int i52 = i51 + 1;
                    int i53 = ((bArr[i49] & DChocImage.COLOR_DEPTH_DEFAULT) << 24) | ((bArr[i51] & DChocImage.COLOR_DEPTH_DEFAULT) << 16);
                    int i54 = i52 + 1;
                    int i55 = i53 | ((bArr[i52] & DChocImage.COLOR_DEPTH_DEFAULT) << 8);
                    int i56 = i54 + 1;
                    String strProcessText5 = this.mFlowHandler.processText(i, i55 | (bArr[i54] & DChocImage.COLOR_DEPTH_DEFAULT), i17);
                    int i57 = i56 + 1;
                    int i58 = (bArr[i56] & DChocImage.COLOR_DEPTH_DEFAULT) << 24;
                    int i59 = i57 + 1;
                    int i60 = ((bArr[i57] & DChocImage.COLOR_DEPTH_DEFAULT) << 16) | i58;
                    int i61 = i59 + 1;
                    String strProcessText6 = this.mFlowHandler.processText(i, i60 | ((bArr[i59] & DChocImage.COLOR_DEPTH_DEFAULT) << 8) | (bArr[i61] & DChocImage.COLOR_DEPTH_DEFAULT), i17);
                    int iLoadImagesOrAnimations7 = loadImagesOrAnimations(bArr, i61 + 1, 3, imageArr, spriteObjectArr);
                    Image[] imageArr6 = imageArr[0];
                    SpriteObject spriteObject6 = spriteObjectArr[0];
                    int i62 = iLoadImagesOrAnimations7 + 1;
                    int i63 = bArr[iLoadImagesOrAnimations7] & DChocImage.COLOR_DEPTH_DEFAULT;
                    iLoadImagesOrAnimations2 = i62 + 1;
                    int i64 = bArr[i62] & DChocImage.COLOR_DEPTH_DEFAULT;
                    this.mFlowHandler.menuSetInputItem(i, customMenuObject, i17, i50, strProcessText5, strProcessText6, spriteObject6, imageArr6, i63, i64 == 255 ? -1 : i64);
                    break;
                case 4:
                    int i65 = i18 + 1;
                    int i66 = i65 + 1;
                    int i67 = ((bArr[i65] & DChocImage.COLOR_DEPTH_DEFAULT) << 16) | ((bArr[i18] & DChocImage.COLOR_DEPTH_DEFAULT) << 24);
                    int i68 = i66 + 1;
                    String strProcessText7 = this.mFlowHandler.processText(i, i67 | ((bArr[i66] & DChocImage.COLOR_DEPTH_DEFAULT) << 8) | (bArr[i68] & DChocImage.COLOR_DEPTH_DEFAULT), i17);
                    iLoadImagesOrAnimations2 = loadImagesOrAnimations(bArr, i68 + 1, 1, imageArr, spriteObjectArr);
                    this.mFlowHandler.menuSetItem(i, customMenuObject, i17, 1, strProcessText7, spriteObjectArr[0], imageArr[0], -1);
                    break;
                default:
                    iLoadImagesOrAnimations2 = i18;
                    break;
            }
        }
        this.mFlowHandler.processMenu(i, customMenuObject, 1);
        this.mFlowHandler.menuSetSize(i, customMenuObject);
        customMenuObject.setSelectedItem(0);
        this.mLoadedMenus[i] = customMenuObject;
        if (vector.size() <= 0) {
            return;
        }
        short[] sArr = new short[vector.size()];
        int i69 = 0;
        while (true) {
            int i70 = i69;
            if (i70 >= vector.size()) {
                this.mMenuSoftkeyEvents[i] = sArr;
                return;
            } else {
                sArr[i70] = (short) ((Integer) vector.elementAt(i70)).intValue();
                i69 = i70 + 1;
            }
        }
    }

    public static int loadResourceID(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = ((bArr[i2] & DChocImage.COLOR_DEPTH_DEFAULT) << 16) | (bArr[i] << 24);
        int i5 = i3 + 1;
        int i6 = i4 | ((bArr[i3] & DChocImage.COLOR_DEPTH_DEFAULT) << 8);
        int i7 = i5 + 1;
        return i6 | (bArr[i5] & DChocImage.COLOR_DEPTH_DEFAULT);
    }

    private void processBranch(int i) {
        if (this.mFlowHandler.evaluateBranchCondition(i)) {
            processEvent(this.mCurrentBranch[0]);
        } else {
            processEvent(this.mCurrentBranch[1]);
        }
    }

    private void processEvent(int i) {
        short s = this.mStateHistoryPointer > 0 ? this.mStateHistory[this.mStateHistoryPointer - 1] : (short) -1;
        if (i == -1) {
            return;
        }
        if (i == -2) {
            if (this.mCurrentGameScreen == null || this.mCurrentGameScreen.length <= 0) {
                return;
            }
            processEvent(this.mCurrentGameScreen[0]);
            return;
        }
        if (i == -3) {
            this.mGoingForward = false;
            this.mNextState = s;
            this.mStateHistoryPointer--;
            return;
        }
        if (i >= 0) {
            short s2 = this.mEventTargets[i];
            if (s2 != -1) {
                this.mGoingForward = true;
                this.mNextState = s2;
                if (isMenuState(this.mCurrentState)) {
                    this.mMenuSelectedItems[this.mCurrentState] = (byte) this.mCurrentMenu.getSelectedItem();
                }
                if (s == s2) {
                    this.mGoingForward = false;
                    this.mStateHistoryPointer--;
                } else if (this.mStoreStateToHistory) {
                    short[] sArr = this.mStateHistory;
                    int i2 = this.mStateHistoryPointer;
                    this.mStateHistoryPointer = i2 + 1;
                    sArr[i2] = (short) this.mCurrentState;
                }
            }
            this.mFlowHandler.eventOccurred(this.mCurrentState, i);
        }
    }

    private void processSoftkeyEvent(int i) {
        if (Toolkit.getSoftKeyType(i) == 2) {
            processEvent(-3);
        }
    }

    private void removeFromMenuCache(int i) {
        if (this.mLoadedMenus[i] != null) {
            this.mLoadedMenus[i].releaseScreen();
            this.mLoadedMenus[i] = null;
        }
    }

    public void controllerActivated() {
        this.mFlowHandler.controllerActivated();
    }

    public void doDraw(Graphics graphics) {
        if (this.mLoading) {
            Game.smLoadingTip = -1;
        }
        if (this.mLoading || Game.smLoadingTip > -1) {
            this.mFlowHandler.drawLoadingScreen(graphics, getLoadingPercentage());
            return;
        }
        this.mFlowHandler.doDraw(this.mCurrentState, graphics);
        if (this.mCurrentMenu != null) {
            this.mFlowHandler.drawMenu(this.mCurrentState, graphics, (CustomMenuObject) this.mCurrentMenu);
        }
    }

    public int getLoadingPercentage() {
        if (!this.mLoading) {
            return Game.smLoadingTip > -1 ? 100 : -1;
        }
        int i = 100 - this.mDavinciLoadingPercentage;
        if (this.mDavinciLoading) {
            return i + ((this.mLoadingCounter * this.mDavinciLoadingPercentage) / this.mMaxLoadingCount);
        }
        int i2 = (i * this.mLoadingCounter) / this.mMaxLoadingCount;
        if (this.mLoadingCounter == this.mMaxLoadingCount && DavinciUtilities.getAdditionalLoadingCount() == 0) {
            return 100;
        }
        return i2;
    }

    public void keyEventOccurred(int i, int i2) {
        if (this.mLoading || Game.smLoadingTip <= -1) {
            if (this.mCurrentMenu != null) {
                this.mCurrentMenu.keyEventOccurred(i, i2);
            }
            if (this.mFlowHandler != null) {
                this.mFlowHandler.keyEventOccurred(this.mCurrentState, i, i2);
                return;
            }
            return;
        }
        int toolkitGameAction = Toolkit.getToolkitGameAction(i);
        if (i2 == 0 || i2 == 3) {
            if (i == 1 || toolkitGameAction == 12) {
                Game.smLoadingTip = -1;
            }
        }
    }

    public void languageChanged() {
        clearMenuCache();
    }

    public void licenseManagerActivated() {
        this.mFlowHandler.licenseManagerActivated();
    }

    public void logicUpdate(int i) throws IOException {
        boolean z;
        boolean z2;
        if (this.mNextState != -1) {
            changeState();
            return;
        }
        if (this.mLoading || Game.smLoadingTip > -1) {
            ScrollingTextField.logicUpdate(i);
            return;
        }
        processEvent(this.mFlowHandler.logicUpdate(this.mCurrentState, i));
        if (this.mCurrentMenu != null) {
            int[] iArrLogicUpdate = this.mCurrentMenu.logicUpdate(i);
            this.mMenuSelectedItems[this.mCurrentState] = (byte) this.mCurrentMenu.getSelectedItem();
            if (iArrLogicUpdate != null) {
                if (iArrLogicUpdate[0] == 1) {
                    processEvent(iArrLogicUpdate[1]);
                    return;
                }
                if (iArrLogicUpdate[0] == 0) {
                    short[] sArr = this.mMenuSoftkeyEvents[this.mCurrentState];
                    if (sArr != null) {
                        int i2 = 0;
                        boolean z3 = false;
                        while (i2 < sArr.length) {
                            int i3 = i2 + 1;
                            short s = sArr[i2];
                            int i4 = i3 + 1;
                            short s2 = sArr[i3];
                            if (s == iArrLogicUpdate[1]) {
                                processEvent(s2);
                                z2 = true;
                            } else {
                                z2 = z3;
                            }
                            z3 = z2;
                            i2 = i4;
                        }
                        z = z3;
                    } else {
                        z = false;
                    }
                    if (z) {
                        return;
                    }
                    processSoftkeyEvent(iArrLogicUpdate[1]);
                }
            }
        }
    }

    public void pause() {
        this.mFlowHandler.pauseGame();
    }

    public void pointerEventOccurred(int i, int i2, int i3) {
        if (this.mLoading) {
            return;
        }
        if (this.mCurrentMenu != null) {
            this.mCurrentMenu.pointerEventOccurred(i, i2, i3);
        }
        if (this.mFlowHandler != null) {
            this.mFlowHandler.pointerEventOccurred(this.mCurrentState, i, i2, i3);
        }
    }

    public void screenSizeChanged() {
        clearMenuCache();
        this.mFlowHandler.screenSizeChanged();
    }
}
