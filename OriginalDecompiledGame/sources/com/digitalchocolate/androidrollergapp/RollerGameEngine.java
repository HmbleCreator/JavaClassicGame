package com.digitalchocolate.androidrollergapp;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class RollerGameEngine {
    public static final int EVENT_COMPLETED = 2;
    public static final int EVENT_NONE = 0;
    public static final int EVENT_PAUSED = 1;
    public static final int GAME_MODE_CAREER = 0;
    public static final int GAME_MODE_HOTSEAT = 1;
    public static final int GAME_MODE_MENU = 3;
    public static final int GAME_MODE_SURVIVAL = 2;
    private static final int GS_GAME = 0;
    private static final int GS_NONE = -1;
    public static final int HOTSEAT_MODE_GHOST = 0;
    public static final int HOTSEAT_MODE_TOURNAMENT = 1;
    public static final int KEY_ACTION_MASK = 16;
    public static final int KEY_DOWN_MASK = 8;
    public static final int KEY_LEFT_MASK = 1;
    public static final int KEY_POUND_MASK = 64;
    public static final int KEY_RIGHT_MASK = 2;
    public static final int KEY_STAR_MASK = 32;
    public static final int KEY_UP_MASK = 4;
    public static final int KEY_ZERO_MASK = 128;
    private static final int MAX_UPDATE_TIME = 333;
    private static final int MIN_UPDATE_TIME = 16;
    public static boolean smGameCrashed;
    public static int smGameMode;
    public static int smHotSeatMode;
    private static boolean smThrillInitialized = false;
    public static boolean smTrackMenu = false;
    private int mGameState;
    private int mKeys;
    private int mNextGameState;
    private int mPressedSK = -1;
    private MenuObject mTextBox;
    private boolean mUpdateBackground;
    private boolean mUpdateSoftKeys;
    private int mUpdateTimer;

    public RollerGameEngine(ImageFont imageFont, ImageFont imageFont2, ImageFont imageFont3) {
    }

    private void changeGameState(int i) {
        this.mNextGameState = i;
    }

    public static void initialize() {
        Entity.loadManager();
        if (!smThrillInitialized) {
            DCThrillOld.initialize();
            smThrillInitialized = true;
        }
        Cheats.reset();
        DCThrillOld.smPlayerCnt = smGameMode == 1 ? Game.smNumPlayerToPlay : 0;
        DCThrillOld.smCurrentPlayer = ResultsHotSeat.smPlayerIndex + 1;
        smTrackMenu = false;
    }

    public static void loadStateGroup(int i, int i2) {
        switch (i) {
            case 0:
                DCThrillOld.loadStateGroup(i, i2);
                smTrackMenu = true;
                break;
            case 2:
                Profile profile = Game.smProfile[Game.smNumProfile];
                if (i2 == 0) {
                    if (smGameMode == 2) {
                        int iRnd = Util.rnd(DCThrillOld.smLevelsUnlocked);
                        int i3 = (iRnd / 11) % 9;
                        profile.mSelectedTheme = i3;
                        profile.mSelectedTrack = iRnd - (i3 * 11);
                    }
                    initialize();
                }
                DCThrillOld.smSelectedCountry = profile.mSelectedTheme % 3;
                DCThrillOld.smSelectedTheme = profile.mSelectedTheme;
                DCThrillOld.smSelectedTrack = profile.mSelectedTrack;
                DCThrillOld.smLevelsUnlocked = profile.mLevelsUnlocked;
                DCThrillOld.loadStateGroup(i, i2);
                break;
            case 9:
                DCThrillOld.loadStateGroup(i, i2);
                break;
        }
    }

    private void resetKeys() {
        this.mPressedSK = -1;
        this.mKeys = 0;
    }

    public static void unloadStateGroup(int i, int i2) {
        DCThrillOld.unloadStateGroup(i);
    }

    private void updateGameState() {
        int i = this.mNextGameState;
        this.mGameState = this.mNextGameState;
        this.mNextGameState = -1;
        if (this.mNextGameState == 0) {
            smGameCrashed = false;
        }
        resetKeys();
        updateSoftKeys();
    }

    private void updateSoftKeys() {
        Game.removeAllSoftKeys();
        switch (this.mGameState) {
            case 0:
                if (!DCThrillOld.smShowTextBox) {
                    Game.setSoftKey(9, 0);
                    break;
                } else {
                    Game.setSoftKey(1, 0);
                    break;
                }
        }
        this.mUpdateBackground = true;
    }

    public void continueGame() {
        resetKeys();
        this.mUpdateSoftKeys = true;
        this.mUpdateBackground = true;
        DChocMIDlet.getInstance().resetTimer();
    }

    public void doDraw(Graphics graphics) {
        if (this.mUpdateBackground || this.mTextBox == null) {
            DCThrillOld.doDraw(graphics);
        }
    }

    public void enableCheats() {
        Cheats.enableCheats();
    }

    public void keyEventOccurred(int i, int i2) {
        int i3;
        if (i2 == 0 || i2 == 1) {
            switch (Toolkit.getToolkitGameAction(i)) {
                case 7:
                    i3 = 128;
                    break;
                case 8:
                case 10:
                case 14:
                case 16:
                default:
                    i3 = 0;
                    break;
                case 9:
                    i3 = 4;
                    break;
                case 11:
                    i3 = 1;
                    break;
                case 12:
                    i3 = 16;
                    break;
                case 13:
                    i3 = 2;
                    break;
                case 15:
                    i3 = 8;
                    break;
                case 17:
                    i3 = 32;
                    break;
                case 18:
                    i3 = 64;
                    break;
            }
            if (i2 == 0) {
                this.mKeys = i3 | this.mKeys;
            } else {
                this.mKeys = (i3 ^ (-1)) & this.mKeys;
            }
        }
        Cheats.keyEventOccurred(this.mKeys);
        DCThrillOld.keyEventOccurred(i, i2);
        resetKeys();
    }

    public void load(int i) {
    }

    public int logicUpdate(int i) throws IOException {
        if (this.mNextGameState != -1) {
            updateGameState();
            return 0;
        }
        if (this.mUpdateSoftKeys) {
            updateSoftKeys();
            this.mUpdateSoftKeys = false;
        }
        if (this.mPressedSK != -1) {
            int i2 = this.mPressedSK;
            resetKeys();
            if (i2 == 9) {
                return 1;
            }
        }
        this.mUpdateTimer += i;
        if (this.mUpdateTimer < 16) {
            return 0;
        }
        int i3 = this.mUpdateTimer;
        if (i3 > MAX_UPDATE_TIME) {
            i3 = MAX_UPDATE_TIME;
        }
        this.mUpdateTimer = 0;
        int iLogicUpdate = DCThrillOld.logicUpdate(i3);
        int iUpdateGame = Cheats.updateGame();
        if (iUpdateGame != 0) {
            iLogicUpdate = iUpdateGame;
            Cheats.reset();
        }
        if (iLogicUpdate != 4 && iLogicUpdate != 2) {
            return iLogicUpdate;
        }
        smGameCrashed = iLogicUpdate == 4;
        if (smGameMode == 0) {
            return 17;
        }
        if (smGameMode == 1) {
            return 19;
        }
        if (smGameMode == 2) {
            return 20;
        }
        return iLogicUpdate;
    }

    public void playGame() {
        this.mUpdateSoftKeys = true;
        changeGameState(0);
    }

    public void pointerEventOccurred(int i, int i2, int i3) {
        DCThrillOld.pointerEventOccurred(i, i2, i3);
    }

    public void screenSizeChanged() {
    }
}
