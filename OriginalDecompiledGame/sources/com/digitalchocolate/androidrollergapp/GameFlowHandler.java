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
public class GameFlowHandler implements FlowHandler, MenuIDs {
    private static final int CHEAT_BOX_DURATION = 2000;
    private static final boolean DEBUG_QUERY_BRANCH_VALUES = false;
    private static final int LANGUAGE_UNDEFINED = -1;
    private static final String RECORD_STORE_NAME = "gamedata";
    private static final String RECORD_STORE_NAME_SETTINGS = "settings";
    private static final boolean SAVING_SETTINGS_REQUIRED = true;
    private static final boolean USE_VOLUME_SLIDERS = false;
    private boolean debriefingOkPressed;
    private ApplicationControl mApplicationControl;
    private MenuObject mCheatBox;
    private int mCheatBoxTimer;
    private int[] mCheatCodeInputBuffer;
    private int mCheatCodeInputBufferIndex;
    private boolean mCheatsEnabled;
    private MenuObject mCurrentMenu;
    private int mCurrentMusic;
    private int mCurrentState;
    private Image mDchocLogo;
    private boolean mDemoTimerEnabled;
    private boolean mGMGBrowserStarted;
    private boolean mGMGVisited;
    private ILicenseManager mLicenseManager;
    private boolean mLicenseManagerActivated;
    private boolean mLicenseManagerAppStarted;
    private boolean mMenuResourcesLoaded;
    private boolean mPauseEventScheduled;
    private int mPreviousState;
    private ImageFont mSimpleFont;
    private boolean mSkipSoundQuery;
    private Image mTitleLogo;
    private String[] mTitleText;
    private static final int[] CHEAT_CODE = {14, 15, 14, 11};
    private static int smStateDrawnLastTime = -1;
    private int mLogoCounter = 3000;
    private int mSelectedLanguage = -1;
    private boolean mFreeTrialMode = false;
    private boolean mEnableTellAFriend = false;
    private boolean mEnableGetMoreGames = false;

    public GameFlowHandler(ApplicationControl applicationControl) {
        this.mCurrentMusic = -1;
        this.mApplicationControl = applicationControl;
        try {
            this.mSimpleFont = new ImageFont(null, null, Font.getFont(32, 0, 0), 0, -1);
        } catch (IOException e) {
        }
        loadGame();
        this.mCurrentMusic = -1;
        loadSettings();
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
    }

    private void drawTitleScreen(Graphics graphics) {
        graphics.setColor(16777215);
        graphics.fillRect(0, 0, Toolkit.getScreenWidth(), Toolkit.getScreenHeight());
        graphics.drawImage(this.mTitleLogo, Toolkit.getScreenWidth() >> 1, Toolkit.getScreenHeight() >> 1, 3);
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
        return true;
    }

    private boolean isTextBox(MenuObject menuObject) {
        return menuObject.getStyle() == 4 || menuObject.getStyle() == 5;
    }

    private void loadGame() {
        byte[] record = Toolkit.readRecord(RECORD_STORE_NAME);
        if (record != null) {
            try {
                new DataInputStream(new ByteArrayInputStream(record)).read();
            } catch (IOException e) {
            }
        }
    }

    private void loadMenuResources() {
        this.mMenuResourcesLoaded = true;
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
        }
    }

    private void saveGame() throws IOException {
        if (this.mCheatsEnabled) {
            return;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.write(0);
            dataOutputStream.close();
            Toolkit.writeRecord(RECORD_STORE_NAME, byteArrayOutputStream.toByteArray(), 0);
        } catch (IOException e) {
        }
    }

    private void saveSettings() {
        byte[] bArr = new byte[4];
        bArr[0] = (byte) this.mSelectedLanguage;
        Toolkit.writeRecord(RECORD_STORE_NAME_SETTINGS, bArr, 1);
    }

    private void setSoundsEnabled(boolean z) {
        Toolkit.getMusicVolume();
        int i = z ? 3 : 0;
        Toolkit.setSoundEffectVolume(i);
        Toolkit.setMusicVolume(i);
    }

    private void updateMusic(int i) {
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void controllerActivated() {
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void doDraw(int i, Graphics graphics) {
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void drawLoadingScreen(Graphics graphics, int i) {
        int screenWidth = Toolkit.getScreenWidth();
        int screenHeight = Toolkit.getScreenHeight();
        graphics.setColor(16777215);
        graphics.fillRect(0, 0, screenWidth, screenHeight);
        int i2 = screenWidth >> 1;
        int iMax = Math.max(screenHeight >> 6, 5);
        int i3 = (screenWidth - i2) >> 1;
        int i4 = (screenHeight - iMax) >> 1;
        graphics.setColor(0);
        graphics.drawRect(i3, i4, i2 - 1, iMax - 1);
        graphics.setColor(13395456);
        graphics.fillRect(i3 + 2, i4 + 2, ((i2 - 4) * i) / 100, iMax - 4);
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void drawMenu(int i, Graphics graphics, CustomMenuObject customMenuObject) {
        if (isTextBox(customMenuObject)) {
            graphics.setColor(16777215);
            graphics.fillRect(0, 0, Toolkit.getScreenWidth(), Toolkit.getScreenHeight());
        } else {
            drawMenuBackground(graphics, i);
        }
        customMenuObject.doDraw(graphics);
        if (i != 0 || this.mCheatBox == null) {
            return;
        }
        this.mCheatBox.doDraw(graphics);
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public boolean evaluateBranchCondition(int i) {
        return true;
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void eventOccurred(int i, int i2) {
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public int getDavinciLoadingPercentage(int i) {
        return 75;
    }

    public int getLoadingTip() {
        return -1;
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public int getStateGroupLoadingCount(int i) {
        switch (i) {
            case 0:
                if (this.mMenuResourcesLoaded) {
                    break;
                }
                break;
        }
        return 0;
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public int getStateLoadingCount(int i) {
        switch (i) {
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
        return true;
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
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void licenseManagerActivated() {
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void loadState(int i, int i2) {
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void loadStateGroup(int i, int i2) {
        switch (i) {
            case 0:
                if (!this.mMenuResourcesLoaded) {
                    loadMenuResources();
                    break;
                }
                break;
            case 2:
                DCThrillOld.loadStateGroup(i, i2);
                break;
            case 3:
                if (i2 == 0 || i2 == 1 || i2 == 2 || i2 == 3) {
                }
                break;
            case 5:
                if (i2 != 0) {
                    if (i2 == 1) {
                        loadTitleScreen();
                        break;
                    }
                } else {
                    this.mDchocLogo = DavinciUtilities.getImage(-1, 0, 1024);
                    break;
                }
                break;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public int logicUpdate(int i, int i2) {
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
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void menuSetScreen(int i, MenuObject menuObject, int i2, int i3, int i4) {
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
        menuObject.setBounds(i3, i5, i4, i2);
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
        this.mCurrentMusic = -1;
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void pointerEventOccurred(int i, int i2, int i3, int i4) {
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
                    break;
            }
        }
        if (i2 != 2) {
            if (i2 != 3 || i == 8 || i != 7) {
            }
        } else {
            int i3 = Toolkit.getMusicVolume() == 0 ? 0 : 1;
            switch (i) {
                case 4:
                    menuObject.setItemIntValue(0, i3);
                    break;
                case 10:
                    menuObject.setItemIntValue(1, i3);
                    break;
            }
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public String processText(int i, int i2, int i3) {
        int i4;
        if (i == 3) {
            if (i3 == 0 && this.mFreeTrialMode) {
                i4 = -2;
            }
            return Toolkit.getText(i4);
        }
        if (i == 0) {
            if (i3 == 0) {
                return null;
            }
            if (i3 == 12) {
                return null;
            }
        }
        i4 = i2;
        return Toolkit.getText(i4);
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void screenSizeChanged() {
        if (this.mCurrentState != 43 && this.mCurrentState != 45 && this.mCurrentState != 46 && this.mCurrentState != 49 && this.mCurrentState != 47 && this.mCurrentState != 52 && this.mCurrentState != 53 && this.mCurrentState == 48) {
        }
        DCThrillOld.screenSizeChanged();
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

    public void setLoadingTip(int i) {
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

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void switchState(int i, int i2, MenuObject menuObject) {
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void unloadState(int i, int i2) {
    }

    @Override // com.digitalchocolate.androidrollergapp.FlowHandler
    public void unloadStateGroup(int i, int i2) {
        switch (i) {
            case 0:
                freeMenuResources();
                break;
            case 2:
                DCThrillOld.unloadStateGroup(i);
                break;
            case 5:
                this.mDchocLogo = null;
                this.mTitleLogo = null;
                break;
        }
    }
}
