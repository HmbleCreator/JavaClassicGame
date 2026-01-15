package com.digitalchocolate.androidrollergapp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* loaded from: classes.dex */
public class GameMobile extends Game implements MenuIDs {
    private static final int CHEAT_BOX_DURATION = 2000;
    private static final boolean DEBUG_QUERY_BRANCH_VALUES = false;
    private static final int LANGUAGE_UNDEFINED = -1;
    private static final String RECORD_STORE_NAME = "gamedata";
    private static final String RECORD_STORE_NAME_SETTINGS = "settings";
    private static final boolean SAVING_SETTINGS_REQUIRED = true;
    private static final boolean USE_VOLUME_SLIDERS = false;
    public static IController mController;
    private ApplicationControl mApplicationControl;
    private MenuObject mCheatBox;
    private int mCheatBoxTimer;
    private int[] mCheatCodeInputBuffer;
    private int mCheatCodeInputBufferIndex;
    private boolean mCheatsEnabled;
    private boolean mControllerActivated;
    private boolean mControllerAppStarted;
    private MenuObject mCurrentMenu;
    private int mCurrentMusic;
    private GameScreens[] mCustomMenuScreen;
    private SpriteObject mDchocLogo;
    private boolean mDrawGameBackground;
    private boolean mEnableChocolateClub;
    private boolean mEnableGetMoreGames;
    private boolean mEnableTellAFriend;
    private boolean mGMGBrowserStarted;
    private boolean mGMGVisited;
    public RollerGameEngine mGameEngine;
    private IHighScore mHighScore;
    private boolean mHighScoreManagerAppStarted;
    private boolean mInterruption;
    private CustomMenuObject mLastMenu;
    private ILicenseManager mLicenseManager;
    private boolean mLicenseManagerActivated;
    private boolean mLicenseManagerAppStarted;
    private SpriteObject mLoadingGfx;
    private int mLoadingLines;
    private String mLoadingOk;
    private String mLoadingStr;
    private String[][] mLoadingText;
    private int mLoadingTextHeight;
    private CustomMenuObject mLogoBackground;
    private boolean mMenuResourcesLoaded;
    private boolean mPauseEventScheduled;
    private int mPreviousState;
    private boolean mReplayingTrack;
    private ImageFont mSimpleFont;
    private ImageFont mSimpleFontWhite;
    private boolean mSkipSoundQuery;
    private SpriteObject mTitleCart;
    private Image mTitleLogo;
    private String[] mTitleText;
    private boolean resumemainMenuSound;
    private static final int[] CHEAT_CODE = {9, 13, 9, 14, 15, 10, 14};
    public static boolean DISABLE_CONTROLLER_MANUALLY = true;
    public static int smAwardSelection = -1;
    private static int[] ROLLER_TIDS = {TextIDs.TID_SELECTION_ROLLER_TM1_1, TextIDs.TID_SELECTION_ROLLER_TM1_2, TextIDs.TID_SELECTION_ROLLER_TM1_3, TextIDs.TID_SELECTION_ROLLER_TM2_1, TextIDs.TID_SELECTION_ROLLER_TM2_2, TextIDs.TID_SELECTION_ROLLER_TM2_3, TextIDs.TID_SELECTION_ROLLER_TM3_1, TextIDs.TID_SELECTION_ROLLER_TM3_2, TextIDs.TID_SELECTION_ROLLER_TM3_3};
    private static final int[] LOADING_TIPS_ID = {TextIDs.TID_LOADING_1, TextIDs.TID_LOADING_2, TextIDs.TID_LOADING_3, TextIDs.TID_LOADING_4, TextIDs.TID_LOADING_5, 224, 225, 226, 227, 228};
    private static final int[] LOADING_TIPS_HOTSEAT_ID = {-1, -1, TextIDs.TID_LOADING_3, TextIDs.TID_LOADING_4, TextIDs.TID_LOADING_5, -1, -1, 226, -1, -1};
    private int mLogoCounter = 3000;
    private int mSelectedLanguage = -1;
    private boolean mFreeTrialMode = false;
    private boolean mLoadingDraw = false;

    public GameMobile(ApplicationControl applicationControl, ImageFont imageFont, ImageFont imageFont2, ImageFont imageFont3) throws IOException {
        this.mCurrentMusic = -1;
        this.mApplicationControl = applicationControl;
        this.mGameEngine = new RollerGameEngine(imageFont, imageFont2, imageFont3);
        setFonts(imageFont, imageFont2, imageFont3);
        this.mEnableTellAFriend = false;
        this.mEnableGetMoreGames = false;
        this.mEnableChocolateClub = Toolkit.getToolkitProperty(23) != null;
        mController = Toolkit.getUsedController();
        DISABLE_CONTROLLER_MANUALLY = !mController.isControllerEnabled();
        try {
            this.mSimpleFont = new ImageFont(null, null, Font.getFont(32, 0, 0), 0, -1);
            this.mSimpleFontWhite = new ImageFont(null, null, Font.getFont(32, 0, 0), 16777215, -1);
        } catch (IOException e) {
        }
        loadGame();
        this.mCurrentMusic = -1;
        loadSettings();
        Tuner.paramsLoad();
    }

    private void createCheatBox() {
        int screenWidth = Toolkit.getScreenWidth() - (Toolkit.getScreenWidth() >> 2);
        int screenHeight = (Toolkit.getScreenHeight() - (Toolkit.getScreenHeight() >> 2)) - Toolkit.getSoftKeyAreaHeight();
        int screenWidth2 = (Toolkit.getScreenWidth() - screenWidth) >> 1;
        int screenHeight2 = (Toolkit.getScreenHeight() - screenHeight) >> 1;
        this.mCheatBoxTimer = 2000;
        this.mCheatBox = new MenuObject();
        this.mCheatBox.setScreen(1, 1, 3);
        this.mCheatBox.setItem(0, 1, Toolkit.getText(28), null, -1);
        this.mCheatBox.setSoftkey(1, 0);
        this.mCheatBox.setStyle(4);
        this.mCheatBox.setBounds(screenWidth2, screenHeight2, screenWidth, screenHeight);
    }

    private void drawMenuBackground(Graphics graphics, int i) {
        graphics.setColor(16777215);
        graphics.fillRect(0, 0, Toolkit.getScreenWidth(), Toolkit.getScreenHeight());
    }

    private void drawTitleScreen(Graphics graphics) {
        this.mLogoBackground.doDraw(graphics);
        graphics.setClip(0, 0, Toolkit.getScreenWidth(), Toolkit.getScreenHeight());
        if (this.mTitleCart != null) {
            this.mTitleCart.draw(graphics, (Toolkit.getScreenWidth() >> 1) + (this.mTitleLogo.getWidth() >> 3), Toolkit.getScreenHeight());
        }
        int screenWidth = Toolkit.getScreenWidth() >> 1;
        int screenHeight = Toolkit.getScreenHeight() >> 1;
        if (this.mTitleCart != null) {
            screenHeight -= (this.mTitleLogo.getHeight() >> 2) + 10;
        }
        graphics.drawImage(this.mTitleLogo, screenWidth, screenHeight, 3);
    }

    private void freeCheatBox() {
        if (this.mCheatBox != null) {
            this.mCheatBox.releaseScreen();
            this.mCheatBox = null;
        }
    }

    private void freeMenuResources() {
        this.mMenuResourcesLoaded = false;
    }

    private boolean isMenuItemVisible(int i, int i2) {
        switch (i) {
            case 0:
                if (i2 != 8 && i2 != 1 && i2 != 7 && i2 != 4 && i2 != 0 && i2 != 12 && i2 != 5) {
                    if (i2 == 2) {
                        return true;
                    }
                    if (i2 == 3) {
                        return false;
                    }
                    if (i2 == 10) {
                        return this.mEnableChocolateClub;
                    }
                    if (i2 == 5) {
                        return false;
                    }
                    if (i2 == 11) {
                        return true;
                    }
                }
                return false;
            case 2:
                if (i2 == 4) {
                    return true;
                }
                if (i2 == 7) {
                    return true;
                }
                if (i2 == 5) {
                    return true;
                }
                if (i2 == 3) {
                    return true;
                }
                break;
            case 4:
                if (i2 == 0) {
                    return true;
                }
                if (i2 != 1 && i2 != 2) {
                    if (i2 == 4) {
                        return Toolkit.getLanguageDescriptions().length > 1;
                    }
                    if (i2 == 3) {
                        return mController.getControllerMenuItemLabel() != null;
                    }
                }
                return false;
            case 10:
                if (i2 == 1) {
                    return true;
                }
                if (i2 != 2 && i2 != 4) {
                    if (i2 == 3) {
                        return mController.getControllerMenuItemLabel() != null;
                    }
                    if (i2 == 5) {
                        return RollerGameEngine.smGameMode == 0;
                    }
                }
                return false;
            case 13:
                if (i2 == 5) {
                    return true;
                }
                if (i2 == 1) {
                    return true;
                }
                if (i2 == 4) {
                    return true;
                }
                if (i2 == 3) {
                    return true;
                }
                break;
            case 21:
                if (i2 == 0) {
                    return (this.mReplayingTrack || (smProfile[smNumProfile].mSelectedTheme == 8 && smProfile[smNumProfile].mSelectedTrack == 10)) ? false : true;
                }
                break;
        }
        return true;
    }

    private boolean isTextBox(MenuObject menuObject) {
        return menuObject.getStyle() == 4 || menuObject.getStyle() == 5;
    }

    private void loadGame() throws IOException {
        int i = smNumProfile;
        for (int i2 = 0; i2 < 1; i2++) {
            smProfile[i2] = new Profile();
            byte[] record = Toolkit.readRecord(RECORD_STORE_NAME + i2);
            if (record != null) {
                try {
                    DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(record));
                    dataInputStream.read();
                    smProfile[i2].loadProfile(dataInputStream);
                } catch (IOException e) {
                }
            } else {
                smNumProfile = i2;
                resetGame();
            }
        }
        smNumProfile = i;
    }

    private void loadLoadinResources() {
        this.mLoadingGfx = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_R_M_LOADINGBAR_EMPTY, ResourceIDs.ANM_R_M_LOADINGBAR_FULL, ResourceIDs.ANM_R_M_BACKGROUND_MENU_SPLASH}, true), false);
        this.mLoadingText = new String[10][];
        for (int i = 0; i < 10; i++) {
            this.mLoadingText[i] = CustomMenuObject.splitString(Toolkit.getText(LOADING_TIPS_ID[i]), smTextFont, Toolkit.getScreenWidth() - 10);
        }
        this.mLoadingOk = Toolkit.getText(TextIDs.TID_LOADING_PRESSOK);
        this.mLoadingStr = Toolkit.getText(TextIDs.TID_LOADING);
    }

    private void loadMenuResources() {
        this.mMenuResourcesLoaded = true;
    }

    private void loadSelectRoller(MenuObject menuObject) {
        SpriteObject spriteObject = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.SPR_MENU_ICON_PLAY, ResourceIDs.SPR_MENU_ICON_PLAY, ResourceIDs.SPR_MENU_ICON_LOCK}, true), false);
        for (int i = 0; i < menuObject.mMaxItemCount; i++) {
            if (smProfile[smNumProfile].mThemeUnlocked[i]) {
                menuObject.setItemDvc(i, 0, Toolkit.getText(ROLLER_TIDS[i]), spriteObject, menuObject.getItemEvent(i));
            } else {
                menuObject.setItemDvc(i, 0, Toolkit.getText(ROLLER_TIDS[i]) + " (" + Toolkit.getText(27) + ")", spriteObject, menuObject.getItemEvent(i));
            }
        }
        menuObject.setSize(Toolkit.getScreenWidth(), Toolkit.getScreenHeight());
    }

    private void loadSelectTrack(MenuObject menuObject, boolean z) {
        Profile profile = smProfile[smNumProfile];
        SpriteObject spriteObject = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.SPR_MENU_ICON_PLAY, ResourceIDs.SPR_MENU_ICON_PLAY, ResourceIDs.SPR_MENU_ICON_LOCK}, true), false);
        for (int i = 0; i < 11; i++) {
            menuObject.setItemDvc(i, 0, profile.mTrackUnlocked[profile.mSelectedTheme][i] ? Toolkit.getText(TextIDs.TID_SELECTION_TRACK) + " " + (i + 1) : Toolkit.getText(TextIDs.TID_SELECTION_TRACK) + " " + (i + 1) + " (" + Toolkit.getText(27) + ")", spriteObject, menuObject.getItemEvent(i));
            menuObject.setSelectionItemState(i, 0);
        }
        menuObject.setSize(Toolkit.getScreenWidth(), Toolkit.getScreenHeight());
        if (z) {
            int i2 = 0;
            while (i2 < 11 && !profile.mTrackUnlocked[profile.mSelectedTheme][i2]) {
                i2++;
            }
            if (RollerGameEngine.smGameMode == 1 || profile.mSelectedTheme != profile.mLastUnlockedTheme || i2 == 11) {
                profile.mSelectedTrack = 0;
            } else {
                profile.mSelectedTrack = 0;
                if (profile.mSelectedTheme == profile.mLastUnlockedTheme) {
                    profile.mSelectedTrack = profile.mLastUnlockedTrack;
                }
            }
        }
        menuObject.setSelectedItem(profile.mSelectedTrack);
    }

    private void loadSettings() {
        if (Toolkit.getToolkitProperty(7) != null) {
            this.mSelectedLanguage = Toolkit.getSelectedLanguageIndex();
        } else if (Toolkit.getLanguageDescriptions().length == 1) {
            this.mSelectedLanguage = 0;
        }
        byte[] record = Toolkit.readRecord(RECORD_STORE_NAME_SETTINGS);
        if (record != null) {
            this.mSelectedLanguage = record[0];
            this.mApplicationControl.setLanguage(this.mSelectedLanguage);
        }
    }

    private void loadTitleScreen() {
        if (this.mTitleLogo == null) {
            this.mTitleLogo = Toolkit.getImage(ResourceIDs.RID_GFX_TITLE);
            if (this.mTitleLogo.getHeight() * 2 < Toolkit.getScreenHeight()) {
                this.mTitleCart = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_M_BACKGROUND_MENU_SPLASH, true), false);
            }
        }
        this.mLogoBackground = new CustomMenuObject();
        this.mLogoBackground.setScreen(0, 0, 0);
        this.mLogoBackground.setSize(Toolkit.getScreenWidth(), Toolkit.getScreenHeight());
    }

    private void resetGame() throws IOException {
        DCThrillOld.resetCareer();
        smProfile[smNumProfile].resetProfile();
        System.out.println("resetGhostData");
        GameEngine.resetGhostData();
        saveGame();
    }

    private void saveGame() throws IOException {
        if (this.mCheatsEnabled) {
            return;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.write(0);
            smProfile[smNumProfile].saveProfile(dataOutputStream).close();
            Toolkit.writeRecord(RECORD_STORE_NAME + smNumProfile, byteArrayOutputStream.toByteArray(), 0);
        } catch (IOException e) {
        }
    }

    private void saveSettings() {
        byte[] bArr = new byte[4];
        bArr[0] = (byte) this.mSelectedLanguage;
        Toolkit.writeRecord(RECORD_STORE_NAME_SETTINGS, bArr, 1);
    }

    private void setLoadingTip() {
        while (true) {
            smLoadingTip = Util.rnd(10);
            if ((RollerGameEngine.smGameMode == 0 ? LOADING_TIPS_ID[smLoadingTip] : LOADING_TIPS_HOTSEAT_ID[smLoadingTip]) != -1 && this.mLoadingText[smLoadingTip] != null) {
                this.mLoadingLines = this.mLoadingText[smLoadingTip].length;
                this.mLoadingTextHeight = smTextFont.getHeight() * this.mLoadingLines;
                return;
            }
        }
    }

    private void setReturnKeyAsExit() {
    }

    private void setSoundsEnabled(boolean z) {
        Toolkit.getMusicVolume();
        int i = z ? 3 : 0;
        Toolkit.setSoundEffectVolume(i);
        Toolkit.setMusicVolume(i);
    }

    private void setTheme() {
        boolean z;
        boolean z2 = true;
        int i = 0;
        while (true) {
            if (i >= 9) {
                z = z2;
                break;
            }
            int i2 = 0;
            while (true) {
                if (i2 >= 11) {
                    break;
                }
                if (!smProfile[smNumProfile].mTrackUnlocked[i][i2]) {
                    z2 = false;
                    break;
                }
                i2++;
            }
            if (!z2) {
                z = z2;
                break;
            }
            i++;
        }
        if (z) {
            smProfile[smNumProfile].mSelectedTheme = 0;
        } else {
            smProfile[smNumProfile].mSelectedTheme = smProfile[smNumProfile].mLastUnlockedTheme;
        }
    }

    private void updateMusic(int i) {
        int i2;
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 5:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
            case 22:
            case 23:
            case 36:
            case 37:
            case 38:
            case 39:
            case 43:
            case 55:
            case 56:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 83:
            case 84:
            case 65536:
            case MenuIDs.STATE_SELECT_TRACK /* 65537 */:
                i2 = ResourceIDs.RID_SND_TITLE;
                break;
            case 20:
                i2 = -1;
                break;
            case 50:
            case 57:
                i2 = ResourceIDs.RID_SND_GAME;
                break;
            case 72:
            case 78:
            case 79:
                return;
            default:
                i2 = -1;
                break;
        }
        if (i2 == -1) {
            Toolkit.stopMusic();
        } else if (i2 != this.mCurrentMusic) {
            Toolkit.playMusic(i2, -1);
        }
        this.mCurrentMusic = i2;
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void controllerActivated() {
        this.mControllerActivated = true;
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void doDraw(int i, Graphics graphics) {
        try {
            switch (i) {
                case 42:
                case 47:
                case 48:
                case 49:
                case 52:
                case 53:
                    graphics.setColor(16777215);
                    graphics.fillRect(0, 0, Toolkit.getScreenWidth(), Toolkit.getScreenHeight());
                    this.mDchocLogo.draw(graphics, Toolkit.getScreenWidth() >> 1, Toolkit.getScreenHeight() >> 1);
                    break;
                case 43:
                    drawTitleScreen(graphics);
                    break;
                case 44:
                case 45:
                case 46:
                case 51:
                case 54:
                case 65:
                case 66:
                case 67:
                    drawMenuBackground(graphics, i);
                    mController.doDraw(graphics);
                    break;
                case 50:
                    this.mGameEngine.doDraw(graphics);
                    break;
                case 55:
                case 59:
                case 60:
                case 61:
                case 64:
                    this.mCustomMenuScreen[0].doDraw(graphics);
                    break;
                case 56:
                case 58:
                case 63:
                    if (i == 58) {
                        this.mGameEngine.doDraw(graphics);
                    }
                    this.mCustomMenuScreen[1].doDraw(graphics);
                    break;
                case 57:
                    GameEngine.smEngine2d.ingameDraw(graphics);
                    this.mCustomMenuScreen[0].doDraw(graphics);
                    break;
                case 62:
                    DCThrillOld.doDraw(graphics);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void drawLoadingScreen(Graphics graphics, int i) {
        boolean z;
        int i2;
        int screenWidth = Toolkit.getScreenWidth();
        int screenHeight = Toolkit.getScreenHeight();
        if (!this.mLoadingDraw) {
            DCRoller.drawLoadingScreen(graphics, i);
            return;
        }
        if (DCRoller.setBackKeyInLoadingScreen) {
            Game.removeAllSoftKeys();
            DCRoller.setBackKeyInLoadingScreen = false;
        }
        CustomMenuObject.drawGradientRect(graphics, 0, 0, screenWidth, screenHeight, 1141255, 6988587, 3);
        int softKeyAreaHeight = Toolkit.getSoftKeyAreaHeight();
        boolean z2 = smLoadingTip > -1;
        int i3 = screenHeight / 2;
        if (!z2) {
            i3 = 0;
        }
        this.mLoadingGfx.setAnimation(0, 0, false);
        int height = this.mLoadingGfx.getHeight();
        int height2 = height + 5 + smSelectionFont.getHeight();
        if (!z2) {
            int i4 = i3;
            z = z2;
            i2 = i4;
        } else if (screenHeight - (((i3 + height2) + softKeyAreaHeight) + 15) >= (smTextFont.getHeight() * 3) + 5) {
            int i5 = i3;
            z = z2;
            i2 = i5;
        } else {
            z = false;
            i2 = 0;
        }
        int i6 = screenWidth / 2;
        int height3 = !z ? i2 + ((((screenHeight - height2) - i2) / 2) - smSelectionFont.getHeight()) : i2 + 15;
        int width = this.mLoadingGfx.getWidth();
        this.mLoadingGfx.draw(graphics, i6, height3);
        int i7 = (width * i) / 100;
        graphics.setClip(i6 - this.mLoadingGfx.getPivotX(), height3, i7, height);
        this.mLoadingGfx.setAnimation(1, 0, false);
        this.mLoadingGfx.draw(graphics, i6, height3);
        if (z) {
            graphics.setClip(0, 0, screenWidth, screenHeight);
            this.mLoadingGfx.setAnimation(2, 0, false);
            this.mLoadingGfx.draw(graphics, (screenWidth / 2) + (this.mLoadingGfx.getWidth() / 5), (screenHeight / 2) + 15);
        }
        graphics.setClip(0, 0, screenWidth, screenHeight);
        int i8 = height3 + height + 5;
        if (i7 < width || smLoadingTip == -1) {
            smSelectionFont.drawString(graphics, this.mLoadingStr, (screenWidth - smSelectionFont.stringWidth(this.mLoadingStr)) / 2, i8, 20);
        } else {
            ScrollingTextField.draw(graphics, this.mLoadingOk, smSelectionFont, 5, i8, screenWidth - 10, softKeyAreaHeight + 0);
        }
        int height4 = smSelectionFont.getHeight() + i8;
        if (smLoadingTip <= -1 || this.mLoadingTextHeight + height4 >= screenHeight - softKeyAreaHeight) {
            return;
        }
        int i9 = 0;
        while (true) {
            int i10 = height4;
            if (i9 >= this.mLoadingLines) {
                return;
            }
            smTextFont.drawString(graphics, this.mLoadingText[smLoadingTip][i9], (screenWidth - smTextFont.stringWidth(this.mLoadingText[smLoadingTip][i9])) / 2, i10, 20);
            height4 = smTextFont.getHeight() + 0 + i10;
            i9++;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void drawMenu(int i, Graphics graphics, CustomMenuObject customMenuObject) {
        if (!isTextBox(customMenuObject)) {
            drawMenuBackground(graphics, i);
            if (i == 8 || i == 7) {
                graphics.setColor(9486550);
                graphics.fillRect(0, Toolkit.getScreenHeight() - Toolkit.getSoftKeyAreaHeight(), Toolkit.getScreenWidth(), Toolkit.getSoftKeyAreaHeight());
            }
        }
        if ((smCurrentState == 10 || smCurrentState == 11 || smCurrentState == 20 || smCurrentState == 21) && this.mDrawGameBackground) {
            this.mGameEngine.doDraw(graphics);
        } else if (this.mLastMenu != null && this.mInterruption) {
            this.mLastMenu.doDraw(graphics);
        }
        customMenuObject.doDraw(graphics);
        if (i != 0 || this.mCheatBox == null) {
            return;
        }
        this.mCheatBox.doDraw(graphics);
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public boolean evaluateBranchCondition(int i) throws IOException {
        switch (i) {
            case 68:
                return this.mSelectedLanguage == -1;
            case 69:
                return false;
            case 70:
                return true;
            case 71:
                return false;
            case 72:
                return false;
            case 73:
                return false;
            case 74:
                boolean z = smProfile[smNumProfile].mIsFirstTime;
                if (!z) {
                    return z;
                }
                smProfile[smNumProfile].mIsFirstTime = false;
                saveGame();
                return z;
            case 75:
                return RollerGameEngine.smGameMode == 1;
            case 76:
                return RollerGameEngine.smGameMode == 2;
            case 77:
                return RollerGameEngine.smHotSeatMode == 0 ? smNumPlayerToPlay > ResultsHotSeat.smPlayerIndex : ResultsHotSeat.smTimesPlayed < 5;
            case 78:
            case 79:
                return RollerGameEngine.smGameCrashed;
            case 80:
                return smProfile[smNumProfile].mNewThemeUnlocked;
            case 81:
                return RollerGameEngine.smHotSeatMode == 1;
            case 82:
                return false;
            case 83:
                return smProfile[smNumProfile].mTrackUnlocked[1][0];
            case 84:
                return smProfile[smNumProfile].mTrackUnlocked[1][0];
            case 85:
                return this.mControllerAppStarted;
            case 86:
                return RollerGameEngine.smHotSeatMode == 1;
            case 87:
                return true;
            case MenuIDs.STATE_BRANC_AREA_LOCKED /* 65543 */:
                return !smProfile[smNumProfile].mThemeUnlocked[smProfile[smNumProfile].mSelectedTheme];
            case MenuIDs.STATE_BRANCH_LEVEL_UNLOCKED /* 65544 */:
                return smProfile[smNumProfile].mTrackUnlocked[smProfile[smNumProfile].mSelectedTheme][smProfile[smNumProfile].mSelectedTrack];
            default:
                return false;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void eventOccurred(int i, int i2) throws IOException {
        switch (i2) {
            case MenuIDs.EVENT_SET_SELECTED_TRACK /* -257 */:
                smProfile[smNumProfile].mSelectedTrack = this.mCurrentMenu.getSelectedItem();
                this.mReplayingTrack = smProfile[smNumProfile].mTrackRides[smProfile[smNumProfile].mSelectedTheme][smProfile[smNumProfile].mSelectedTrack] > 0;
                break;
            case MenuIDs.EVENT_SET_SELECTED_ROLLER /* -256 */:
                smProfile[smNumProfile].mSelectedTheme = this.mCurrentMenu.getSelectedItem() % 9;
                break;
            case 5:
                RollerGameEngine.smHotSeatMode = -1;
                RollerGameEngine.smGameMode = 0;
                this.mGameEngine.playGame();
                setTheme();
                break;
            case 6:
                DChocMIDlet.getInstance().notifyDestroyed();
                break;
            case 7:
            case 8:
                RollerGameEngine.smHotSeatMode = -1;
                RollerGameEngine.smGameMode = this.mCurrentMenu.getSelectedItem();
                if (i2 == 8) {
                    ResultsSurvival.smNumTracksPlayed = 0;
                    ResultsSurvival.smTotalScore = 0;
                }
                this.mGameEngine.playGame();
                break;
            case 11:
                this.mReplayingTrack = smProfile[smNumProfile].mTrackRides[smProfile[smNumProfile].mSelectedTheme][smProfile[smNumProfile].mSelectedTrack] > 0;
                break;
            case 17:
                GameEngine.smEngine2d.mEnableCameraLag = false;
                Engine2D.smEnableCameraYLag = false;
                Profile profile = smProfile[smNumProfile];
                profile.mDistance += GameEngine.smTotalDistanceOnTrack;
                if (RollerGameEngine.smGameCrashed) {
                    ResultsCareerSimple.smGetJump = false;
                    ResultsCareerSimple.smGetSpeed = false;
                    ResultsCareerSimple.smGetHappy = false;
                } else {
                    if (!DCThrillOld.smLevelBraking && !profile.mTrackBrake[profile.mSelectedTheme][profile.mSelectedTrack]) {
                        profile.mTrackBrake[profile.mSelectedTheme][profile.mSelectedTrack] = true;
                    }
                    int[] iArr = profile.mTrackRides[profile.mSelectedTheme];
                    int i3 = profile.mSelectedTrack;
                    iArr[i3] = iArr[i3] + 1;
                    int smileys = GameEngine.getSmileys() * 100;
                    ResultsCareerSimple.smBestScore = profile.mTrackScore[profile.mSelectedTheme][profile.mSelectedTrack];
                    if (smileys > ResultsCareerSimple.smBestScore) {
                        profile.mTrackScore[profile.mSelectedTheme][profile.mSelectedTrack] = smileys;
                        ResultsCareerSimple.smBestScore = smileys;
                    }
                    ResultsCareerSimple.smScoreTrack = smileys;
                    ResultsCareerSimple.smTotalScore = profile.getTotalScore();
                    ResultsCareerSimple.smCurrentTrack = DCThrillOld.smLevelIdx;
                    if (ResultsCareerSimple.smGetSpeed && (profile.mPrizesUnlocked[ResultsCareerSimple.smCurrentTrack] & 2) == 0) {
                        byte[] bArr = profile.mPrizesUnlocked;
                        int i4 = ResultsCareerSimple.smCurrentTrack;
                        bArr[i4] = (byte) (bArr[i4] | 2);
                    }
                    if (ResultsCareerSimple.smGetJump && (profile.mPrizesUnlocked[ResultsCareerSimple.smCurrentTrack] & 1) == 0) {
                        byte[] bArr2 = profile.mPrizesUnlocked;
                        int i5 = ResultsCareerSimple.smCurrentTrack;
                        bArr2[i5] = (byte) (bArr2[i5] | 1);
                    }
                    ResultsCareerSimple.smGetHappy = ResultsCareerSimple.smGetJump && ResultsCareerSimple.smGetSpeed && GameEngine.smCurrentLevelTotalScore <= smileys / 100;
                    if ((profile.mPrizesUnlocked[ResultsCareerSimple.smCurrentTrack] & 4) == 0 && ResultsCareerSimple.smGetHappy) {
                        byte[] bArr3 = profile.mPrizesUnlocked;
                        int i6 = ResultsCareerSimple.smCurrentTrack;
                        bArr3[i6] = (byte) (bArr3[i6] | 4);
                    }
                    ResultsCareerSimple.smMaxVelocity = GameEngine.smBestSpeed;
                    profile.unlockTrack();
                    profile.mNumExciters += Entity.smNumExciters;
                    profile.mNumGod += Entity.smNumGod;
                    profile.mNumNitros += Entity.smNumNitros;
                    profile.mNumStickies += Entity.smNumStickies;
                    profile.mNumWinged += Entity.smNumWinged;
                    Entity.smNumExciters = 0;
                    Entity.smNumGod = 0;
                    Entity.smNumNitros = 0;
                    Entity.smNumStickies = 0;
                    Entity.smNumWinged = 0;
                    GameEngine.resetCrashIndices();
                }
                profile.mCrashed += GameEngine.smTotalCrashCount;
                profile.mPlayTime += GameEngine.smTotalPlayTime;
                saveGame();
                break;
            case 19:
                if (RollerGameEngine.smGameCrashed) {
                    ResultsHotSeat.smTime[ResultsHotSeat.smPlayerIndex] = 599990;
                } else {
                    if (GameEngine.smTotalPlayTime > 599990) {
                        GameEngine.smTotalPlayTime = 599990;
                    }
                    ResultsHotSeat.smTime[ResultsHotSeat.smPlayerIndex] = GameEngine.smTotalPlayTime;
                }
                int[] iArr2 = ResultsHotSeat.smScore;
                int i7 = ResultsHotSeat.smPlayerIndex;
                iArr2[i7] = iArr2[i7] + (GameEngine.getSmileys() * 100);
                ResultsHotSeat.smPlayerIndex++;
                if (ResultsHotSeat.smPlayerIndex == smNumPlayerToPlay && RollerGameEngine.smHotSeatMode == 1) {
                    ResultsHotSeat.smTimesPlayed++;
                    if (ResultsHotSeat.smTimesPlayed < 5) {
                        ResultsHotSeat.smPlayerIndex = 0;
                        int iRnd = Util.rnd(DCThrillOld.smLevelsUnlocked);
                        int i8 = (iRnd / 11) % 9;
                        smProfile[smNumProfile].mSelectedTheme = i8;
                        smProfile[smNumProfile].mSelectedTrack = iRnd - (i8 * 11);
                    }
                }
                DCThrillOld.multiplayerTrackOver();
                break;
            case 20:
                ResultsSurvival.smTotalScore += GameEngine.getSmileys() * 100;
                if (!RollerGameEngine.smGameCrashed) {
                    ResultsSurvival.smNumTracksPlayed++;
                    break;
                }
                break;
            case 21:
                this.mGameEngine.continueGame();
                break;
            case 22:
            case 55:
                setSoundsEnabled(this.mCurrentMenu.getItemIntValue(i2 == 55 ? 0 : 1) != 0);
                Toolkit.playMusic(ResourceIDs.RID_SND_EFFECT_VOLUME_CHANGED, 1);
                break;
            case 23:
            case 24:
            case 25:
            case 56:
            case 57:
            case 58:
                int i9 = this.mCurrentMenu.getItemIntValue(i2 == 58 ? 3 : 3) == 0 ? 5 : 4;
                if (i9 == 5) {
                    DISABLE_CONTROLLER_MANUALLY = true;
                } else {
                    DISABLE_CONTROLLER_MANUALLY = false;
                }
                mController.setState(i9);
                break;
            case 26:
                DCThrillOld.restartLevel();
                if (this.mCustomMenuScreen[1] != null) {
                    ((ResultsCareerSimple) this.mCustomMenuScreen[1]).resetTrack();
                }
                int i10 = DCThrillOld.smLevelIdx;
                int i11 = (i10 / 11) % 9;
                Profile profile2 = smProfile[smNumProfile];
                profile2.mSelectedTheme = i11;
                profile2.mSelectedTrack = i10 - (i11 * 11);
                Engine2D.smIngameResetNeeded = true;
                this.mDrawGameBackground = false;
                break;
            case 27:
            case 33:
                GameEngine.resetCrashIndices();
                DCThrillOld.saveCareer();
                saveGame();
                break;
            case 28:
                DCThrillOld.smReplay = false;
                break;
            case 29:
                DCThrillOld.saveCareer();
                saveGame();
                break;
            case 30:
                smProfile[smNumProfile].mSelectedTheme = smProfile[smNumProfile].mLastUnlockedTheme;
                smProfile[smNumProfile].mSelectedTrack = smProfile[smNumProfile].mLastUnlockedTrack;
                break;
            case 31:
                smProfile[smNumProfile].mSelectedTheme = (DCThrillOld.smLevelIdx / 11) % 9;
                smProfile[smNumProfile].mSelectedTrack = DCThrillOld.smSelectedTrack;
                break;
            case 32:
                DCThrillOld.restartReplay();
                break;
            case 34:
                if (this.mCurrentMenu.getItemIntValue(2) == 1) {
                    Profile profile3 = smProfile[smNumProfile];
                    profile3.mLevelsUnlocked = 0;
                    for (int i12 = 0; i12 < 9; i12++) {
                        profile3.mThemeUnlocked[i12] = true;
                        for (int i13 = 0; i13 < 11; i13++) {
                            profile3.mTrackUnlocked[i12][i13] = true;
                            profile3.mLevelsUnlocked++;
                        }
                    }
                    profile3.mLastUnlockedTheme = 8;
                    profile3.mLastUnlockedTrack = 10;
                }
                if (this.mCurrentMenu.getItemIntValue(3) == 1) {
                    Profile profile4 = smProfile[smNumProfile];
                    profile4.mCartUnlocked = (byte) (profile4.mCartUnlocked | 2);
                    profile4.mCartUnlocked = (byte) (profile4.mCartUnlocked | 4);
                }
                Cheats.smCheatsInvincible = this.mCurrentMenu.getItemIntValue(7) == 1;
                if (Cheats.smCheatsInvincible) {
                    GameEngine.smTrain.mCrashVelLimit = -10240000;
                    GameEngine.smTrain.mFullCrashVelLimit = -10240000;
                } else {
                    GameEngine.smTrain.mCrashVelLimit = Tuner.CART_ATTRIBUTES[GameEngine.smTrain.mSelectedCart][4] << 10;
                    GameEngine.smTrain.mFullCrashVelLimit = Tuner.CART_ATTRIBUTES[GameEngine.smTrain.mSelectedCart][5] << 10;
                }
                Tuner.paramsSave();
                break;
            case 35:
                Tuner.paramsReset();
                break;
            case 36:
                Profile profile5 = smProfile[smNumProfile];
                for (int i14 = 0; i14 < 11; i14++) {
                    profile5.mTrackUnlocked[profile5.mSelectedTheme][i14] = true;
                }
                break;
            case 37:
                Tuner.PARAMS_CONVOY_PICKUPS_TIME[0] = Integer.parseInt(this.mCurrentMenu.getItemStringValue(0));
                Tuner.PARAMS_CONVOY_PICKUPS_NITRO_ACC_UPHILL_PERCENT = Integer.parseInt(this.mCurrentMenu.getItemStringValue(1));
                Tuner.PARAMS_CONVOY_PICKUPS_NITRO_ACC_NORMAL_PERCENT = Integer.parseInt(this.mCurrentMenu.getItemStringValue(2));
                Tuner.PARAMS_CONVOY_PICKUPS_NITRO_FRICTION_PERCENT = Integer.parseInt(this.mCurrentMenu.getItemStringValue(3));
                break;
            case 38:
                Tuner.PARAMS_CONVOY_PICKUPS_TIME[2] = Integer.parseInt(this.mCurrentMenu.getItemStringValue(0));
                Tuner.PARAMS_CONVOY_PICKUPS_EXCITER_MULTIPLIER = Integer.parseInt(this.mCurrentMenu.getItemStringValue(1));
                break;
            case 39:
                Tuner.PARAMS_CONVOY_PICKUPS_TIME[3] = Integer.parseInt(this.mCurrentMenu.getItemStringValue(0));
                break;
            case 40:
                Tuner.PARAMS_CONVOY_PICKUPS_TIME[4] = Integer.parseInt(this.mCurrentMenu.getItemStringValue(0));
                break;
            case 41:
                Tuner.PARAMS_CONVOY_INTERACTIVE_BREAK_GO_TIME = Integer.parseInt(this.mCurrentMenu.getItemStringValue(0));
                Tuner.PARAMS_CONVOY_INTERACTIVE_BREAK_GO_SPEEDX = Integer.parseInt(this.mCurrentMenu.getItemStringValue(1));
                break;
            case 42:
                Cheats.smInteractiveFallingDownOn = this.mCurrentMenu.getItemIntValue(1) == 1;
                Tuner.PARAMS_CONVOY_INTERACTIVE_TRACK_FALLING_TIME = Integer.parseInt(this.mCurrentMenu.getItemStringValue(0));
                break;
            case 43:
                Tuner.PARAMS_CONVOY_INTERACTIVE_SPRINGS_IMPULSEY = Integer.parseInt(this.mCurrentMenu.getItemStringValue(0));
                break;
            case 44:
                Tuner.PARAMS_TORNADO_ACCELERATION_X_FP = Integer.parseInt(this.mCurrentMenu.getItemStringValue(1));
                Tuner.PARAMS_TORNADO_MAX_VEL_X_FP[(DCThrillOld.smSelectedTheme * 11) + DCThrillOld.smSelectedTrack] = Integer.parseInt(this.mCurrentMenu.getItemStringValue(2));
                Cheats.smTornadoOn = this.mCurrentMenu.getItemIntValue(0) == 1;
                break;
            case 45:
                Tuner.PARAMS_SCORE_G_FORCE_MULTIPLIER = Integer.parseInt(this.mCurrentMenu.getItemStringValue(0));
                Tuner.PARAMS_SCORE_AIR_POINTS_PER_MEASSURE = Integer.parseInt(this.mCurrentMenu.getItemStringValue(1));
                Tuner.PARAMS_SCORE_AIR_MEASSURE = Integer.parseInt(this.mCurrentMenu.getItemStringValue(2));
                break;
            case 46:
                for (int i15 = 0; i15 < 40; i15++) {
                    Entity.ENTITY_PICKUP_HEIGHT[i15] = Integer.parseInt(this.mCurrentMenu.getItemStringValue(i15));
                }
                break;
            case 47:
                if (RollerGameEngine.smGameMode == 1) {
                    ResultsHotSeat.smPlayerIndex = 0;
                    ResultsHotSeat.smTime = new int[smNumPlayerToPlay];
                    break;
                }
                break;
            case 49:
                smNumPlayerToPlay = this.mCurrentMenu.getSelectedItem() + 2;
                ResultsHotSeat.smTime = new int[smNumPlayerToPlay];
                ResultsHotSeat.smScore = new int[smNumPlayerToPlay];
                ResultsHotSeat.smPlayerIndex = 0;
                ResultsHotSeat.smTimesPlayed = 0;
                break;
            case 50:
                if (smCurrentState == 37) {
                    RollerGameEngine.smHotSeatMode = 0;
                    smProfile[smNumProfile].mSelectedTheme = 0;
                    break;
                } else {
                    RollerGameEngine.smHotSeatMode = 1;
                    break;
                }
            case 53:
                this.mCustomMenuScreen[1].setVisible();
                break;
            case 59:
                this.mSelectedLanguage = this.mCurrentMenu.getSelectedItem();
                if (this.mSelectedLanguage != Toolkit.getSelectedLanguageIndex()) {
                    this.mApplicationControl.setLanguage(this.mSelectedLanguage);
                    saveSettings();
                    loadLoadinResources();
                    break;
                }
                break;
            case 60:
                resetGame();
                ((StatisticsScreen) this.mCustomMenuScreen[0]).loadTexts();
                ((StatisticsScreen) this.mCustomMenuScreen[0]).setStatisticsToMenu();
                break;
            case 61:
                setSoundsEnabled(true);
                break;
            case 62:
                setSoundsEnabled(false);
                break;
            case 63:
                this.mSelectedLanguage = this.mCurrentMenu.getSelectedItem();
                this.mApplicationControl.setLanguage(this.mSelectedLanguage);
                saveSettings();
                break;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public int getDavinciLoadingPercentage(int i) {
        return 75;
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public int getStateGroupLoadingCount(int i) {
        smLoadingTip = -1;
        switch (i) {
            case 0:
                return DCThrillOld.getLoadingCount(i) + 1;
            case 1:
            case 4:
            default:
                return 0;
            case 2:
                setLoadingTip();
                return DCThrillOld.getLoadingCount(i);
            case 3:
                return 4;
            case 5:
                return 20;
            case 6:
            case 8:
                return 6;
            case 7:
                return 3;
            case 9:
                return DCThrillOld.getLoadingCount(i);
            case 10:
                return 1;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public int getStateLoadingCount(int i) {
        switch (i) {
            case 44:
            case 45:
            case 46:
            case 49:
                return 100;
            case 47:
            case 48:
            default:
                return 0;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public boolean isChildGroup(int i, int i2) {
        return i == 0 && i2 == 1;
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public boolean isMenuNeeded(int i) {
        if ((i != 8 && i != 9) || ((i != 8 || this.mSelectedLanguage == -1) && Toolkit.getLanguageDescriptions().length != 1)) {
            return true;
        }
        return false;
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void keyEventOccurred(int i, int i2, int i3) {
        if (this.resumemainMenuSound) {
            this.resumemainMenuSound = false;
            updateMusic(i);
        }
        if (i3 != 3 || i2 == 6) {
        }
        int toolkitGameAction = Toolkit.getToolkitGameAction(i2);
        boolean z = (i3 == 0 && toolkitGameAction == 12) || (i3 == 3 && Toolkit.getSoftKeyType(i2) == 0);
        switch (i) {
            case 0:
                if (i3 == 0 && Toolkit.getToolkitProperty(16) != null) {
                    if (this.mCheatCodeInputBuffer == null) {
                        this.mCheatCodeInputBuffer = new int[CHEAT_CODE.length];
                    }
                    this.mCheatCodeInputBuffer[this.mCheatCodeInputBufferIndex % CHEAT_CODE.length] = toolkitGameAction;
                    boolean z2 = false;
                    for (int i4 = 0; i4 < CHEAT_CODE.length && !z2; i4++) {
                        if (this.mCheatCodeInputBuffer[((this.mCheatCodeInputBufferIndex + i4) + 1) % CHEAT_CODE.length] != CHEAT_CODE[i4]) {
                            z2 = true;
                        }
                    }
                    if (!z2) {
                        this.mCheatsEnabled = true;
                        this.mGameEngine.enableCheats();
                        createCheatBox();
                    }
                    this.mCheatCodeInputBufferIndex++;
                    break;
                }
                break;
            case 7:
                if (i3 == 3 && i2 == 6) {
                    DChocMIDlet.getInstance().m_exitApplication = true;
                    break;
                }
                break;
            case 8:
                if (i3 == 0 && (toolkitGameAction == 9 || toolkitGameAction == 15)) {
                    Toolkit.setSoftKeyLabel(0, Toolkit.getLanguageDescriptions()[this.mCurrentMenu.getSelectedItem()][1]);
                }
                if (i3 == 3 && i2 == 6) {
                    DChocMIDlet.getInstance().notifyDestroyed();
                    break;
                }
                break;
            case 42:
            case 43:
                if (z) {
                    this.mLogoCounter = 0;
                }
                if (i3 == 3 && i2 == 6) {
                    DChocMIDlet.getInstance().m_exitApplication = true;
                    break;
                }
                break;
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 51:
            case 52:
            case 53:
            case 54:
            case 65:
            case 66:
            case 67:
                mController.keyEventOccurred(i2, i3);
                break;
            case 50:
                this.mGameEngine.keyEventOccurred(i2, i3);
                break;
            case 55:
            case 57:
            case 59:
            case 60:
            case 61:
            case 64:
                if (this.mCustomMenuScreen != null && this.mCustomMenuScreen[0] != null) {
                    this.mCustomMenuScreen[0].keyEventOccurred(i2, i3);
                    break;
                }
                break;
            case 56:
            case 58:
            case 63:
                if (this.mCustomMenuScreen != null && this.mCustomMenuScreen[1] != null) {
                    this.mCustomMenuScreen[1].keyEventOccurred(i2, i3);
                    break;
                }
                break;
            case 62:
                if (i3 != 3 || i2 != 6) {
                    DCThrillOld.keyEventOccurred(i2, i3);
                    break;
                } else {
                    DChocMIDlet.getInstance().m_exitApplication = true;
                    break;
                }
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void licenseManagerActivated() {
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void loadState(int i, int i2) {
        switch (i) {
            case 44:
            case 45:
            case 46:
            case 49:
            case 66:
                if (i2 == 1) {
                    mController.setState(3);
                    mController.initMenus();
                    break;
                }
                break;
            case 50:
                this.mGameEngine.load(i2);
                break;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void loadStateGroup(int i, int i2) {
        try {
            switch (i) {
                case 0:
                    if (i2 != 0) {
                        RollerGameEngine.loadStateGroup(i, i2 - 1);
                    } else if (!this.mMenuResourcesLoaded) {
                        loadMenuResources();
                    }
                    if (i2 == 0) {
                        this.mCustomMenuScreen = new GameScreens[1];
                        this.mCustomMenuScreen[0] = new StatisticsScreen(smTitleFont, smSelectionFont, smTextFont, smProfile[smNumProfile]);
                    }
                    if (this.mCustomMenuScreen != null) {
                        this.mCustomMenuScreen[0].load(i2);
                        break;
                    }
                    break;
                case 2:
                    RollerGameEngine.loadStateGroup(i, i2);
                    if (i2 == 0) {
                        this.mCustomMenuScreen = new GameScreens[2];
                        this.mCustomMenuScreen[0] = new TrackPreview(smTitleFont, smSelectionFont, smTextFont, smProfile[smNumProfile]);
                        if (RollerGameEngine.smGameMode == 0) {
                            this.mCustomMenuScreen[1] = new ResultsCareerSimple(smTitleFont, smSelectionFont, smTextFont, smProfile[smNumProfile]);
                            ((ResultsCareerSimple) this.mCustomMenuScreen[1]).resetTrack();
                        }
                    }
                    this.mCustomMenuScreen[0].load(i2);
                    if (this.mCustomMenuScreen[1] != null) {
                        this.mCustomMenuScreen[1].load(i2);
                    }
                    CustomMenuObject.destroyMenuResources();
                    break;
                case 3:
                    if (i2 != 0) {
                        if (i2 != 1 && i2 != 2 && i2 != 3) {
                            if (i2 != 4) {
                                if (i2 == 5 && this.mControllerAppStarted) {
                                    mController.initMenus();
                                    break;
                                }
                            } else {
                                this.mControllerAppStarted = mController.setState(2);
                                break;
                            }
                        }
                    } else {
                        loadLoadinResources();
                        break;
                    }
                    break;
                case 5:
                    if (i2 == 0) {
                        CustomMenuObject.loadMenuResources();
                        break;
                    }
                    break;
                case 6:
                    if (i2 == 0) {
                        this.mCustomMenuScreen = new GameScreens[2];
                        this.mCustomMenuScreen[0] = new MenuSelectArea(smTitleFont, smSelectionFont, smTextFont, smProfile[smNumProfile]);
                    }
                    if (this.mCustomMenuScreen[0] != null) {
                        this.mCustomMenuScreen[0].load(i2);
                    }
                    if (i2 == 0) {
                        this.mCustomMenuScreen[1] = new MenuSelectTrack(smTitleFont, smSelectionFont, smTextFont, smProfile[smNumProfile]);
                    }
                    if (this.mCustomMenuScreen[1] != null) {
                        this.mCustomMenuScreen[1].load(i2);
                        break;
                    }
                    break;
                case 7:
                    if (i2 == 0) {
                        this.mCustomMenuScreen = new GameScreens[1];
                        if (RollerGameEngine.smGameMode == 1 && ResultsHotSeat.smPlayerIndex == smNumPlayerToPlay) {
                            this.mCustomMenuScreen[0] = new ResultsHotSeat(smTitleFont, smSelectionFont, smTextFont);
                        } else if (RollerGameEngine.smGameMode == 2) {
                            this.mCustomMenuScreen[0] = new ResultsSurvival(smTitleFont, smSelectionFont, smTextFont);
                        }
                    }
                    if (this.mCustomMenuScreen[0] != null) {
                        this.mCustomMenuScreen[0].load(i2);
                        break;
                    }
                    break;
                case 8:
                    if (i2 == 0) {
                        this.mCustomMenuScreen = new GameScreens[2];
                        this.mCustomMenuScreen[0] = new AwardsMenu(smTitleFont, smSelectionFont, smTextFont, smProfile[smNumProfile]);
                    } else if (i2 == 3) {
                        this.mCustomMenuScreen[1] = new AwardsTextScreen(smTitleFont, smSelectionFont, smTextFont, smProfile[smNumProfile]);
                    }
                    if (this.mCustomMenuScreen[0] != null) {
                        this.mCustomMenuScreen[0].load(i2);
                    }
                    if (this.mCustomMenuScreen[1] != null) {
                        this.mCustomMenuScreen[1].load(i2);
                        break;
                    }
                    break;
                case 9:
                    if (i2 == 0) {
                        loadLoadinResources();
                    }
                    RollerGameEngine.loadStateGroup(i, i2);
                    break;
                case 10:
                    if (i2 == 0) {
                        loadTitleScreen();
                        break;
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public int logicUpdate(int i, int i2) throws IOException {
        if (this.mControllerActivated && shouldStoreStateToHistory(smCurrentState)) {
            this.mControllerActivated = false;
            return 1;
        }
        if (this.mPauseEventScheduled) {
            this.mPauseEventScheduled = false;
            if (i == 50) {
                if (GameEngine.smGameEngineState != 2) {
                    return 15;
                }
                DCThrillOld.smInterruptReplay = true;
                return -1;
            }
            this.mCurrentMusic = -1;
            this.mInterruption = true;
        }
        switch (i) {
            case 42:
                this.mDchocLogo.logicUpdate(i2);
                if (this.mDchocLogo.isFinishedAnimation() || this.mLogoCounter == 0) {
                    return -2;
                }
                break;
            case 43:
                if (this.mLogoBackground != null) {
                    this.mLogoBackground.logicUpdate(i2);
                }
                this.mLogoCounter -= i2;
                if (this.mLogoCounter < 0) {
                    return -2;
                }
                break;
            case 44:
            case 45:
            case 46:
            case 51:
            case 54:
            case 65:
            case 66:
            case 67:
                if (mController.logicUpdate(i2) != 0) {
                    if (i == 67) {
                        switch (this.mPreviousState) {
                            case 50:
                                return 64;
                        }
                    }
                    return i == 65 ? -2 : -3;
                }
                break;
            case 47:
            case 48:
            case 49:
            case 52:
            case 53:
            case 62:
                return DCThrillOld.logicUpdate(i2) == 2 ? -2 : -1;
            case 50:
                int iLogicUpdate = this.mGameEngine.logicUpdate(i2);
                if (iLogicUpdate == 1) {
                    return 15;
                }
                if (iLogicUpdate == 2) {
                    if (this.mCheatsEnabled) {
                    }
                    return -2;
                }
                if (iLogicUpdate != 0) {
                    return iLogicUpdate;
                }
                break;
            case 55:
            case 57:
            case 59:
            case 60:
            case 61:
            case 64:
                int iLogicUpdate2 = this.mCustomMenuScreen[0].logicUpdate(i2);
                if (iLogicUpdate2 != 0) {
                    if (iLogicUpdate2 != -3) {
                        return iLogicUpdate2;
                    }
                    saveGame();
                    break;
                }
                break;
            case 56:
            case 58:
            case 63:
                if (i == 58) {
                    this.mGameEngine.logicUpdate(i2);
                }
                int iLogicUpdate3 = this.mCustomMenuScreen[1].logicUpdate(i2);
                if (iLogicUpdate3 != 0) {
                    if (iLogicUpdate3 != -3) {
                        return iLogicUpdate3;
                    }
                    saveGame();
                    break;
                }
                break;
        }
        if (this.mCheatBoxTimer > 0) {
            this.mCheatBoxTimer -= i2;
            if (this.mCheatBoxTimer <= 0) {
                freeCheatBox();
            }
        }
        return -1;
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void menuSetInputItem(int i, MenuObject menuObject, int i2, int i3, String str, String str2, SpriteObject spriteObject, Image[] imageArr, int i4, int i5) {
        if (spriteObject != null) {
            menuObject.setInputItemDvc(i2, i3, str, str2, spriteObject, i4, i5);
        } else {
            menuObject.setInputItem(i2, i3, str, str2, imageArr, i4, i5);
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void menuSetItem(int i, MenuObject menuObject, int i2, int i3, String str, SpriteObject spriteObject, Image[] imageArr, int i4) {
        if (isMenuItemVisible(i, i2)) {
            if (spriteObject != null) {
                menuObject.setItemDvc(i2, i3, str, spriteObject, i4);
            } else {
                menuObject.setItem(i2, i3, str, imageArr, i4);
            }
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void menuSetScreen(int i, MenuObject menuObject, int i2, int i3, int i4) {
        if (i != 8 && i != 9) {
            menuObject.setScreen(i2, i3, i4);
            return;
        }
        String[][] languageDescriptions = Toolkit.getLanguageDescriptions();
        menuObject.setScreen(i2, languageDescriptions.length, i4);
        int i5 = i == 8 ? 63 : 59;
        for (int i6 = 0; i6 < languageDescriptions.length; i6++) {
            menuObject.setItem(i6, 0, languageDescriptions[i6][0], null, i5);
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void menuSetSize(int i, MenuObject menuObject) {
        int i2;
        int i3;
        int i4;
        int i5;
        int screenWidth = Toolkit.getScreenWidth();
        int screenHeight = Toolkit.getScreenHeight();
        if (isTextBox(menuObject)) {
            int screenWidth2 = Toolkit.getScreenWidth();
            int screenHeight2 = Toolkit.getScreenHeight() - Toolkit.getSoftKeyAreaHeight();
            int preferredWidth = menuObject.getPreferredWidth(screenWidth2);
            int preferredHeight = menuObject.getPreferredHeight(preferredWidth, screenHeight2);
            int i6 = (screenHeight2 - preferredHeight) >> 1;
            i3 = (screenWidth2 - preferredWidth) >> 1;
            i5 = i6;
            i4 = preferredWidth;
            i2 = preferredHeight;
        } else {
            i2 = screenHeight;
            i3 = 0;
            i4 = screenWidth;
            i5 = 0;
        }
        ((CustomMenuObject) menuObject).setBounds(i3, i5, i4, i2);
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void menuSetSliderItem(int i, MenuObject menuObject, int i2, String str, SpriteObject spriteObject, Image[] imageArr, int i3, int i4, int i5) {
        if (isMenuItemVisible(i, i2)) {
            if (spriteObject != null) {
                menuObject.setSliderItemDvc(i2, str, spriteObject, i3, i4, i5);
            } else {
                menuObject.setSliderItem(i2, str, imageArr, i3, i4, i5);
            }
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void menuSetSoftkey(int i, MenuObject menuObject, int i2, int i3) {
        menuObject.setSoftkey(i2, i3);
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void menuSetStyle(int i, MenuObject menuObject, int i2) {
        menuObject.setStyle(i2);
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void menuSetSwitchItem(int i, MenuObject menuObject, int i2, String str, SpriteObject spriteObject, Image[] imageArr, String[] strArr, SpriteObject spriteObject2, Image[] imageArr2, int i3) {
        if (isMenuItemVisible(i, i2)) {
            if (spriteObject == null && spriteObject2 == null) {
                menuObject.setSwitchItem(i2, str, imageArr, strArr, imageArr2, i3);
            } else {
                menuObject.setSwitchItemDvc(i2, str, spriteObject, strArr, spriteObject2, i3);
            }
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void menuSetTitleBar(int i, MenuObject menuObject, String str, Image image, int i2) {
        menuObject.setTitleBar(image == null ? str : null, image, i2);
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void menuSetTitleBarDvc(int i, MenuObject menuObject, String str, SpriteObject spriteObject, int i2) {
        menuObject.setTitleBarDvc(spriteObject == null ? str : null, spriteObject, i2);
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void pauseGame() {
        this.mPauseEventScheduled = true;
        this.resumemainMenuSound = true;
        this.mCurrentMusic = -1;
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void pointerEventOccurred(int i, int i2, int i3, int i4) {
        if (this.resumemainMenuSound) {
            this.resumemainMenuSound = false;
            updateMusic(i);
        }
        switch (i) {
            case 8:
                if (i4 == 0) {
                    Toolkit.setSoftKeyLabel(0, Toolkit.getLanguageDescriptions()[this.mCurrentMenu.getSelectedItem()][1]);
                    break;
                }
                break;
            case 42:
            case 43:
                if (i4 == 0) {
                    this.mLogoCounter = 0;
                    break;
                }
                break;
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 51:
            case 52:
            case 53:
            case 54:
            case 65:
            case 66:
            case 67:
                mController.pointerEventOccurred(i2, i3, i4);
                break;
            case 50:
                this.mGameEngine.pointerEventOccurred(i2, i3, i4);
                break;
            case 55:
            case 57:
            case 59:
            case 60:
            case 61:
            case 64:
                if (this.mCustomMenuScreen != null && this.mCustomMenuScreen[0] != null) {
                    this.mCustomMenuScreen[0].pointerEventOccurred(i2, i3, i4);
                    break;
                }
                break;
            case 56:
            case 58:
            case 63:
                if (this.mCustomMenuScreen != null && this.mCustomMenuScreen[1] != null) {
                    this.mCustomMenuScreen[1].pointerEventOccurred(i2, i3, i4);
                    break;
                }
                break;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void processMenu(int i, MenuObject menuObject, int i2) {
        if (i2 == 0) {
        }
        if (i2 == 1) {
            switch (i) {
                case 7:
                    menuObject.setImageFonts(null, this.mSimpleFont, this.mSimpleFont);
                    break;
                case 8:
                    menuObject.setImageFonts(null, this.mSimpleFont, this.mSimpleFont);
                    try {
                        Toolkit.setSoftkeyImageFont(new ImageFont(null, null, Font.getFont(32, 1, 0), 16777215, 0));
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                case 9:
                    menuObject.setImageFonts(null, this.mSimpleFontWhite, this.mSimpleFontWhite);
                    break;
            }
        }
        if (i2 != 2) {
            if (i2 != 3 || i == 8 || i != 7) {
            }
            return;
        }
        int i3 = Toolkit.getMusicVolume() == 0 ? 0 : 1;
        switch (i) {
            case 4:
                menuObject.setItemIntValue(0, i3);
                menuObject.setItemIntValue(3, mController.isControllerEnabled() ? 1 : 0);
                break;
            case 10:
                menuObject.setItemIntValue(1, i3);
                menuObject.setItemIntValue(3, mController.isControllerEnabled() ? 1 : 0);
                break;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public String processText(int i, int i2, int i3) {
        int i4;
        if (i == 3) {
            i4 = (i3 == 0 && this.mFreeTrialMode) ? -2 : i2;
            if (i4 == 31 && mController.getControllerAboutText() != null) {
                return Toolkit.getText(i4) + "\\n\\n" + mController.getControllerAboutText();
            }
        } else {
            if (i2 == 108 && i == 5) {
                if (mController.isControllerSupported()) {
                    return Toolkit.getText(TextIDs.TID_HELP_CONTROLS_CONTENT) + Toolkit.getText(TextIDs.TID_HELP_CONTROLS_TILT);
                }
            } else {
                if (i == 0) {
                    if (i3 != 0 && i3 != 12 && i3 != 4) {
                        if (i3 == 5) {
                            i4 = i2;
                        }
                    }
                    return null;
                }
                if (i2 == 30 && ((i == 4 && i3 == 3) || (i == 10 && i3 == 3))) {
                    return mController.getControllerMenuItemLabel();
                }
                if (i2 == 49 && this.mEnableChocolateClub) {
                    return Toolkit.replaceParameters(Toolkit.getText(i2), new String[]{Toolkit.getChocolateClubCode()});
                }
            }
            i4 = i2;
        }
        return Toolkit.getText(i4);
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void screenSizeChanged() {
        if (smCurrentState != 43) {
            if (smCurrentState == 44 || smCurrentState == 54 || smCurrentState == 51) {
                this.mHighScore.initMenus();
            } else if (smCurrentState != 45 && smCurrentState != 46 && smCurrentState != 49 && smCurrentState != 47 && smCurrentState != 52 && smCurrentState != 53 && smCurrentState != 48 && smCurrentState == 63) {
                smAwardSelection = ((AwardsMenu) this.mCustomMenuScreen[0]).mCurrentSelection;
            }
        }
        this.mGameEngine.screenSizeChanged();
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void setLoading(int i, boolean z) {
        if (!z) {
            DChocMIDlet.getInstance().resetTimer();
            return;
        }
        Toolkit.stopMusic();
        Toolkit.stopSoundEffect();
        this.mCurrentMusic = -1;
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public boolean shouldStoreStateToHistory(int i) {
        switch (i) {
            case 7:
            case 8:
            case 42:
            case 43:
                return false;
            default:
                return true;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003b  */
    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void switchState(int r13, int r14, com.digitalchocolate.androidrollergapp.MenuObject r15) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1808
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.digitalchocolate.androidrollergapp.GameMobile.switchState(int, int, com.digitalchocolate.androidrollergapp.MenuObject):void");
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void unloadState(int i, int i2) {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0016  */
    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void unloadStateGroup(int r4, int r5) {
        /*
            r3 = this;
            r2 = 0
            switch(r4) {
                case 0: goto Lb;
                case 1: goto L4;
                case 2: goto Lf;
                case 3: goto L4;
                case 4: goto L4;
                case 5: goto L8;
                case 6: goto L12;
                case 7: goto L12;
                case 8: goto L12;
                default: goto L4;
            }
        L4:
            com.digitalchocolate.androidrollergapp.RollerGameEngine.unloadStateGroup(r4, r5)
            return
        L8:
            r3.mTitleLogo = r2
            goto L4
        Lb:
            r3.freeMenuResources()
            goto L4
        Lf:
            com.digitalchocolate.androidrollergapp.CustomMenuObject.loadMenuResources()
        L12:
            com.digitalchocolate.androidrollergapp.GameScreens[] r0 = r3.mCustomMenuScreen
            if (r0 == 0) goto L4
            r0 = 0
        L17:
            com.digitalchocolate.androidrollergapp.GameScreens[] r1 = r3.mCustomMenuScreen
            int r1 = r1.length
            if (r0 >= r1) goto L30
            com.digitalchocolate.androidrollergapp.GameScreens[] r1 = r3.mCustomMenuScreen
            r1 = r1[r0]
            if (r1 == 0) goto L2d
            com.digitalchocolate.androidrollergapp.GameScreens[] r1 = r3.mCustomMenuScreen
            r1 = r1[r0]
            r1.destroyPool()
            com.digitalchocolate.androidrollergapp.GameScreens[] r1 = r3.mCustomMenuScreen
            r1[r0] = r2
        L2d:
            int r0 = r0 + 1
            goto L17
        L30:
            r3.mCustomMenuScreen = r2
            goto L4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.digitalchocolate.androidrollergapp.GameMobile.unloadStateGroup(int, int):void");
    }
}
