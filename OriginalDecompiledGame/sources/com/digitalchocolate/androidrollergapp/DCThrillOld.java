package com.digitalchocolate.androidrollergapp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* loaded from: classes.dex */
public class DCThrillOld {
    private static final int ACCELEROMETER_MAX_X = 1000;
    public static final int CART_DEFAULT = 0;
    public static final int COUNTRY_CNT = 3;
    public static final int COUNTRY_TRACK_CNT = 33;
    public static final int EVENT_BACK = 3;
    public static final int EVENT_COMPLETED = 2;
    public static final int EVENT_FAILED = 4;
    public static final int EVENT_NONE = 0;
    public static final int EVENT_PAUSED = 1;
    public static final int FAKE_CONTROL_ANALOG_FRAMES = 10;
    public static final int KEY_ACTION_MASK = 16;
    public static final int KEY_BACK_MASK = 32;
    public static final int KEY_CART_CHANGED_MASK = 128;
    public static final int KEY_DOWN_MASK = 8;
    public static final int KEY_LEFT_MASK = 1;
    public static final int KEY_PAUSE_MASK = 64;
    public static final int KEY_RIGHT_MASK = 2;
    public static final int KEY_UP_MASK = 4;
    public static final int MAP_CHUNKS = 3;
    public static final int MAP_TRACKS_PER_CHUNK = 11;
    private static final int MAX_MEDAL_CNT = 5;
    private static final int MAX_PLAYER_CNT = 4;
    private static final int MAX_TRACKS_UNLOCKED_SIMULTANEOUSLY = 2;
    private static final String RS_NAME_CAREER = "c";
    public static final String RS_NAME_GHOST_DATA = "g";
    public static final int TOTAL_LEVEL_COUNT = 99;
    private static final int TUTORIAL_BUTTON_MAX_TIME = 5000;
    private static final int TUTORIAL_COUNT = 27;
    private static final int TUTORIAL_ID_CRASH_FULL = 8;
    private static final int TUTORIAL_ID_CRASH_SMALL = 7;
    private static final int TUTORIAL_ID_HEAVY_CAR_LOCKED = 14;
    private static final int TUTORIAL_ID_HILL = 4;
    private static final int TUTORIAL_ID_HUD = 0;
    private static final int TUTORIAL_ID_INTERACTIVE_BREAK_GO = 17;
    private static final int TUTORIAL_ID_INTERACTIVE_FALLING_RAILS = 20;
    private static final int TUTORIAL_ID_INTERACTIVE_POWERUPS_EXCITER = 23;
    private static final int TUTORIAL_ID_INTERACTIVE_POWERUPS_GOD = 25;
    private static final int TUTORIAL_ID_INTERACTIVE_POWERUPS_JUMP = 26;
    private static final int TUTORIAL_ID_INTERACTIVE_POWERUPS_NITRO = 21;
    private static final int TUTORIAL_ID_INTERACTIVE_POWERUPS_SMILEY = 22;
    public static final int TUTORIAL_ID_INTERACTIVE_POWERUPS_START = 21;
    private static final int TUTORIAL_ID_INTERACTIVE_POWERUPS_STICKY = 24;
    private static final int TUTORIAL_ID_INTERACTIVE_SPRINGS = 16;
    private static final int TUTORIAL_ID_INTERACTIVE_TUNNEL = 18;
    private static final int TUTORIAL_ID_INTERACTIVE_WATER_SPLASH = 19;
    private static final int TUTORIAL_ID_JUMP = 6;
    private static final int TUTORIAL_ID_LIGHT_CAR_LOCKED = 15;
    private static final int TUTORIAL_ID_LOOP = 5;
    private static final int TUTORIAL_ID_PASSENGERS = 10;
    private static final int TUTORIAL_ID_PRESS_BACK_FORWARD = 1;
    private static final int TUTORIAL_ID_SCORE = 3;
    private static final boolean USE_DEBUG_HUD = false;
    private static Image imgControlsBrake;
    private static Image imgControlsGo;
    private static SpriteObject mControlsBrake;
    private static int mControlsBrakeX;
    private static SpriteObject mControlsGo;
    private static int mControlsGoX;
    private static int mControlsY;
    private static int mLastKey;
    public static int mLogicTickCount;
    private static String[] mTutorialText;
    public static int smCurrentPlayer;
    public static int smCurrentTutorial;
    public static int smDebugAccelX;
    public static int smDebugAccelY;
    public static int smDebugAccelZ;
    private static boolean smForwardPressed;
    public static int smGameState;
    private static int smIntroTime;
    private static int smKeys;
    public static int smLatestTrackScore;
    public static boolean smLevelBraking;
    public static int smLevelsUnlocked;
    private static boolean smNonstopTutorial;
    public static boolean smOnlyTutorialText;
    public static int smPlayerCnt;
    private static int smScoringTime;
    public static int smSelectedCart;
    public static int smSelectedCartGhost;
    public static int smSelectedCountry;
    public static int smSelectedTheme;
    public static int smSelectedTrack;
    private static boolean smShowTutorial;
    private static CustomMenuObject smTextBox;
    public static int smTilt;
    public static int smTilt1;
    public static int smTimer;
    private static SpriteObject[] smTouchSideArrows;
    private static MenuObject smTrackPreviewMenu;
    public static SpriteObject smTutorialButtonLeft;
    public static SpriteObject smTutorialButtonLeftAccelerometer;
    public static SpriteObject smTutorialButtonRight;
    public static SpriteObject smTutorialButtonRightAccelerometer;
    private static int smTutorialButtonTimer;
    private static boolean smTutorialButtonsEnabled;
    private static int smTutorialSpecialID;
    private static SpriteObject[] smTutorialSprites;
    private static boolean smTutorialStartCounter;
    public static boolean smShowTextBox = false;
    public static final int CART_COUNT = Tuner.CART_ATTRIBUTES.length;
    public static boolean smCheatsEnabled = false;
    private static boolean smCheatSkip = false;
    private static boolean smCheatSkip2 = false;
    private static int mFPSCounter = 1;
    private static long mFPSLastStamp = 0;
    private static boolean mShowFPS = true;
    private static boolean mShowDebugger = false;
    private static int[] smMultiplayerScores = new int[4];
    private static boolean smRideFinished = false;
    public static boolean smInterruptReplay = false;
    public static boolean smReplay = false;
    public static int smLevelIdx = 0;
    public static boolean[] smTutorialSeen = new boolean[27];
    private static int INDEX_SIDE_ARROW = 0;
    private static int INDEX_SIDE_BACKGROUND = 1;
    private static int SIDE_ARROW_SPRITE_COUNT = 2;
    private static int[] TUTORIAL_TIDS = {165, -1, -1, TextIDs.TID_TUTORIAL_SMILEY, TextIDs.TID_TUTORIAL_HILL, 169, 168, TextIDs.TID_TUTORIAL_CRASH_SMALL, -1, -1, -1, -1, -1, -1, 57, 58, TextIDs.TID_TUTORIAL_SPRINGS, TextIDs.TID_TUTORIAL_BRAKE, TextIDs.TID_TUTORIAL_TUNNEL, -2, TextIDs.TID_TUTORIAL_FALLING, TextIDs.TID_TUTORIAL_NITRO, -1, 177, 176, 179, 178};
    public static boolean smAccelerating = false;
    public static boolean smBraking = false;

    private static void careerTrackOver() {
        Profile profile = Game.smProfile[Game.smNumProfile];
        if (GameEngine.getScore() > profile.mTrackScore[profile.mSelectedTheme][profile.mSelectedTrack]) {
            GameEngine.storeGhostData(smLevelIdx);
        }
    }

    private static int clampDeltaTime(int i) {
        return Math.min(i, TextIDs.TID_HELP_ELEMENTS_FALLING);
    }

    public static void cleanUpAfterExit() {
    }

    public static void controllerEventOccurred(int i, int i2, int i3, int i4, int i5) {
        if (isControllerEnabled()) {
            int i6 = i3 > 1000 ? 1000 : i3;
            if (i6 < -1000) {
                i6 = -1000;
            }
            smTilt = (i6 * 10) / 1000;
            int i7 = smTilt < 0 ? 11 : smTilt > 0 ? 13 : 0;
            if (mLastKey != 0) {
                keyEventOccurred(mLastKey, 1);
            }
            if (Math.abs(smTilt) > mLogicTickCount % 10) {
                keyEventOccurred(i7, 0);
                mLastKey = i7;
            }
        }
    }

    public static void doDraw(Graphics graphics) {
        SpriteObject spriteObject;
        SpriteObject spriteObject2;
        int gameAreaWidth = Game.getGameAreaWidth();
        int screenHeight = Toolkit.getScreenHeight();
        try {
            switch (smGameState) {
                case 50:
                case 57:
                    GameEngine.doDraw(graphics);
                    if (smRideFinished && !smReplay) {
                        drawScoreScreen(graphics);
                    }
                    if (smShowTutorial && smTutorialButtonsEnabled) {
                        SpriteObject spriteObject3 = smTutorialButtonLeft;
                        SpriteObject spriteObject4 = smTutorialButtonRight;
                        if (isControllerEnabled()) {
                            spriteObject = smTutorialButtonLeftAccelerometer;
                            spriteObject2 = smTutorialButtonRightAccelerometer;
                        } else {
                            spriteObject = spriteObject3;
                            spriteObject2 = spriteObject4;
                        }
                        spriteObject.setAnimation(smBraking ? 1 : 0, -1, false);
                        spriteObject2.setAnimation(smAccelerating ? 1 : 0, -1, false);
                        spriteObject.logicUpdate(GameEngine.smEngine2d.mIngameTimer);
                        spriteObject2.logicUpdate(GameEngine.smEngine2d.mIngameTimer);
                        spriteObject.draw(graphics, 0, screenHeight >> 1);
                        spriteObject2.draw(graphics, gameAreaWidth, screenHeight >> 1);
                    }
                    if (!isControllerEnabled()) {
                        mControlsBrake.draw(graphics, mControlsBrakeX, mControlsY);
                        mControlsGo.draw(graphics, mControlsGoX, mControlsY);
                    }
                    if (smOnlyTutorialText) {
                        String str = mTutorialText[smTutorialSpecialID - 4];
                        int iStringWidth = Game.smTitleFont.stringWidth(str);
                        int height = Game.smTitleFont.getHeight();
                        int i = (gameAreaWidth - iStringWidth) / 2;
                        int height2 = ((((screenHeight - GameEngine.smEngine2d.mHudSigns.getHeight()) - 0) - Toolkit.getSoftKeyAreaHeight()) - height) - 5;
                        graphics.setClip(0, 0, gameAreaWidth, screenHeight);
                        CustomMenuObject.drawPopup(graphics, i - 5, height2 - 0, iStringWidth + 10, height, true);
                        Game.smTitleFont.drawString(graphics, str, i, height2 - 0, 20);
                        break;
                    }
                    break;
                case 62:
                    GameEngine.doDraw(graphics);
                    break;
            }
            if (smShowTextBox) {
                smTextBox.doDraw(graphics);
            }
        } catch (Throwable th) {
        }
    }

    private static void drawScoreScreen(Graphics graphics) {
    }

    public static int getLoadingCount(int i) {
        switch (i) {
            case 0:
            case 2:
            case 9:
                return GameEngine.getLoadingStepCount();
            default:
                return 0;
        }
    }

    private static void handleTutorialEvents(int i) throws IOException {
        if (smGameState == 50 && GameEngine.smGameEngineState == 1) {
            if (!smTutorialSeen[0] && smPlayerCnt == 1) {
                setTutorialTextBox(null, 0, false);
                return;
            }
            if (!smTutorialSeen[4] && GameEngine.smEngine2d.mHudCurrentSign == 1 && !smOnlyTutorialText) {
                setTutorialText(4);
                return;
            }
            if (!smTutorialSeen[5] && GameEngine.smEngine2d.mHudCurrentSign == 0 && !smOnlyTutorialText) {
                setTutorialText(5);
            } else {
                if (smTutorialSeen[6] || GameEngine.smEngine2d.mHudCurrentSign != 2 || smOnlyTutorialText) {
                    return;
                }
                setTutorialText(6);
            }
        }
    }

    private static final void handleUnlockEvents() throws IOException {
        if (RollerGameEngine.smGameMode == 0) {
            careerTrackOver();
        }
        saveCareer();
    }

    private static void initTextBox() {
        smTextBox = new CustomMenuObject();
        smTextBox.setScreen(1, 2, 1);
        smTextBox.setStyle(5);
        smTrackPreviewMenu = new MenuObject();
        smTrackPreviewMenu.setScreen(1, 1, 1);
        smTrackPreviewMenu.setTitleBar(Toolkit.getText(87), null, 0);
        smTrackPreviewMenu.setStyle(3);
    }

    public static void initialize() {
        Util.initMath();
        GameEngine.initEngine();
        loadHUDGraphics();
        initTextBox();
        resetCareer();
        loadCareer();
    }

    public static boolean isControllerEnabled() {
        if (Toolkit.getUsedController() == null) {
            return false;
        }
        return Toolkit.getUsedController().isControllerEnabled();
    }

    public static void keyEventOccurred(int i, int i2) {
        if (GameMobile.smCurrentState == 50 && smGameState == 50) {
            Toolkit.forceBacklightOn();
        }
        if (smShowTextBox) {
            smKeys = 0;
            if ((i2 != 3 || i != 1) && (i2 != 0 || Toolkit.getToolkitGameAction(i) != 12)) {
                smTextBox.keyEventOccurred(i, i2);
                return;
            }
            smShowTextBox = false;
            smTutorialSeen[smCurrentTutorial] = true;
            updateSoftKeys();
        }
        switch (smGameState) {
            case 50:
                if (i2 == 3) {
                    if (i == 9) {
                        smKeys |= 64;
                    } else if (i == 14 && smRideFinished) {
                        smInterruptReplay = true;
                    }
                }
                if (i2 == 0) {
                    int toolkitGameAction = Toolkit.getToolkitGameAction(i);
                    if (toolkitGameAction == 12 && smRideFinished) {
                        smInterruptReplay = true;
                    }
                    switch (toolkitGameAction) {
                        case 9:
                            smKeys |= 4;
                            break;
                        case 11:
                            smKeys |= 1;
                            break;
                        case 12:
                            smKeys |= 16;
                            break;
                        case 13:
                            smKeys |= 2;
                            break;
                        case 15:
                            smKeys |= 8;
                            break;
                    }
                }
                if (i2 == 1) {
                    switch (Toolkit.getToolkitGameAction(i)) {
                        case 9:
                            smKeys &= -5;
                            break;
                        case 11:
                            smKeys &= -2;
                            break;
                        case 12:
                            smKeys &= -17;
                            break;
                        case 13:
                            smKeys &= -3;
                            break;
                        case 15:
                            smKeys &= -9;
                            break;
                    }
                }
                break;
            case 62:
                if ((i2 == 0 && Toolkit.getToolkitGameAction(i) == 12) || (i2 == 3 && i == 14)) {
                    smKeys |= 16;
                    break;
                }
                break;
        }
    }

    private static final void loadCareer() {
        byte[] record = Toolkit.readRecord(RS_NAME_CAREER);
        if (record != null) {
            try {
                DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(record));
                smShowTutorial = dataInputStream.readByte() == 1;
                smSelectedCart = dataInputStream.readByte();
                for (int i = 0; i < 27; i++) {
                    smTutorialSeen[i] = dataInputStream.readByte() == 1;
                }
            } catch (IOException e) {
            }
        }
    }

    private static final void loadHUDGraphics() {
        smTutorialButtonLeft = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_R_G_LEFT, ResourceIDs.ANM_R_G_PRESSLEFT}, true), true);
        smTutorialButtonRight = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_R_G_RIGHT, ResourceIDs.ANM_R_G_PRESSRIGHT}, true), true);
        if (Toolkit.getUsedController() != null) {
            smTutorialButtonLeftAccelerometer = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_R_G_LEFT_ACCELEROMETER, ResourceIDs.ANM_R_G_PRESSLEFT_ACCELEROMETER}, true), true);
            smTutorialButtonRightAccelerometer = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_R_G_RIGHT_ACCELEROMETER, ResourceIDs.ANM_R_G_PRESSRIGHT_ACCELEROMETER}, true), true);
        }
    }

    public static void loadStateGroup(int i, int i2) {
        switch (i) {
            case 0:
                if (i2 == 0) {
                    smSelectedCountry = 0;
                }
                GameEngine.loadLevel(0, i2, 0, 3, 6);
                break;
            case 2:
                if (i2 == 0) {
                    smOnlyTutorialText = false;
                    smPlayerCnt = RollerGameEngine.smGameMode + 1;
                    smLevelIdx = (smSelectedTheme * 11) + smSelectedTrack;
                    if (smPlayerCnt > 1) {
                        GameEngine.smGhostEnabled = true;
                        GameEngine.smGhostMultiplayerMode = true;
                    } else if (GameEngine.ghostModeAvailable(smLevelIdx)) {
                        GameEngine.smGhostEnabled = true;
                        GameEngine.smGhostMultiplayerMode = false;
                    } else {
                        GameEngine.smGhostEnabled = false;
                        GameEngine.smGhostMultiplayerMode = false;
                    }
                }
                GameEngine.loadLevel(smLevelIdx, i2, smSelectedCountry, 3, 6);
                loadTutorialText();
                mControlsGo = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.SPR_CONTROLS_GO}, false), false);
                if (Toolkit.getSelectedLanguageCode().equalsIgnoreCase("es")) {
                    mControlsBrake = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.SPR_CONTROLS_FRENO}, false), false);
                } else {
                    mControlsBrake = new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.SPR_CONTROLS_BRAKE}, false), false);
                }
                mControlsGoX = (((Game.getGameAreaWidth() * 3) / 4) - 10) - 0;
                mControlsBrakeX = ((Game.getGameAreaWidth() / 4) - 50) - 0;
                mControlsY = (((Toolkit.getScreenHeight() * 3) / 4) + 30) - 40;
                break;
            case 9:
                if (i2 == 0) {
                    smSelectedCountry = 0;
                }
                GameEngine.loadLevel(99, i2, 0, 3, 6);
                break;
        }
    }

    private static void loadTutorialText() {
        mTutorialText = new String[3];
        mTutorialText[0] = Toolkit.getText(TUTORIAL_TIDS[4]);
        mTutorialText[1] = Toolkit.getText(TUTORIAL_TIDS[5]);
        mTutorialText[2] = Toolkit.getText(TUTORIAL_TIDS[6]);
        smTutorialSprites = new SpriteObject[11];
        smTutorialSprites[0] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_SMILEY, true), false);
        smTutorialSprites[1] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_SIGNS_RAIL_CHANGE, true), false);
        smTutorialSprites[2] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_SIGNS_SPRING, true), false);
        smTutorialSprites[3] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_SIGNS_TUNNEL, true), false);
        smTutorialSprites[4] = new SpriteObject(DavinciUtilities.loadAnimation(-1, true), false);
        smTutorialSprites[5] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_SIGNS_FALLING_RAILS, true), false);
        smTutorialSprites[6] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_POWER_UP_BASIC_TURBO, true), true);
        smTutorialSprites[7] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_POWER_UP_BASIC_EXCITER, true), true);
        smTutorialSprites[8] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_POWER_UP_BASIC_EXTRA_LIFE, true), true);
        smTutorialSprites[9] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_POWER_UP_BASIC_MAGNET, true), true);
        smTutorialSprites[10] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_POWER_UP_BASIC_WINGED_JUMP, true), true);
    }

    public static int logicUpdate(int i) throws IOException {
        if (smShowTextBox && !RollerGameEngine.smTrackMenu) {
            smTextBox.logicUpdate(i);
            return 0;
        }
        if (!smTutorialStartCounter && ((smKeys & 1) != 0 || (smKeys & 2) != 0)) {
            smTutorialStartCounter = true;
            smTutorialButtonTimer = 0;
        }
        if (smTutorialButtonsEnabled && smTutorialStartCounter) {
            smTutorialButtonTimer += i;
            int i2 = 5000;
            if (isControllerEnabled()) {
                i2 = 5000 * (GameEngine.smEngine2d.ingameGetSpeedHUD() > 100 ? 2 : 4);
            }
            smTutorialButtonsEnabled = smTutorialButtonTimer < i2;
        }
        if (smTutorialButtonTimer > 1000) {
            handleTutorialEvents(i);
        }
        if (smGameState == 50) {
            return updateGame(i);
        }
        if (smGameState == 62) {
            return updateIntro(i);
        }
        return 0;
    }

    public static void multiplayerTrackOver() {
        boolean z;
        int score = GameEngine.getScore();
        smMultiplayerScores[smCurrentPlayer - 1] = score;
        if (smCurrentPlayer > 1) {
            boolean z2 = true;
            for (int i = 0; i < smCurrentPlayer - 1; i++) {
                if (smMultiplayerScores[i] > score) {
                    z2 = false;
                }
            }
            z = z2;
        } else {
            z = true;
        }
        if (z) {
            GameEngine.storeGhostData(smLevelIdx);
        }
        smCurrentPlayer++;
        smScoringTime = 0;
        GameEngine.restartGhost();
        smRideFinished = false;
    }

    public static void pointerEventOccurred(int i, int i2, int i3) {
        if (smShowTextBox) {
            smTextBox.pointerEventOccurred(i, i2, i3);
        }
        switch (smGameState) {
            case 50:
                if (!isControllerEnabled()) {
                    smKeys &= -2;
                    smKeys &= -3;
                    if (i3 == 0) {
                        if (i >= 0 && i < ((mControlsBrakeX + mControlsBrake.getWidth()) + 20) - 0 && i2 >= mControlsY - 10 && i2 < mControlsY + mControlsBrake.getHeight() + 10) {
                            smKeys |= 1;
                            break;
                        } else if (i >= (mControlsGoX - 20) + 0 && i < Game.getGameAreaWidth() - 0 && i2 >= mControlsY - 10 && i2 < mControlsY + mControlsGo.getHeight() + 10) {
                            smKeys |= 2;
                            break;
                        }
                    } else if (i3 == 2) {
                        if (i >= 0 && i < ((mControlsBrakeX + mControlsBrake.getWidth()) + 20) - 0 && i2 >= mControlsY - 10 && i2 < mControlsY + mControlsBrake.getHeight() + 10) {
                            smKeys |= 1;
                            break;
                        } else if (i >= (mControlsGoX - 20) + 0 && i < Game.getGameAreaWidth() - 0 && i2 >= mControlsY - 10 && i2 < mControlsY + mControlsGo.getHeight() + 10) {
                            smKeys |= 2;
                            break;
                        }
                    } else if (i3 == 1) {
                    }
                }
                break;
        }
    }

    public static boolean replayIsRequested() {
        return smRideFinished;
    }

    public static void replaySetRequest(boolean z) {
        smRideFinished = z;
    }

    public static final void resetCareer() {
        smShowTutorial = true;
        for (int i = 0; i < 27; i++) {
            smTutorialSeen[i] = false;
        }
        smTutorialButtonsEnabled = false;
        smTutorialStartCounter = false;
        GameEngine.smTotalDistanceOnTrack = 0;
        GameEngine.smMaximumGForce = 0;
        GameEngine.smMaximumSpeed = 0;
        GameEngine.smLongestJump = 0;
        smSelectedCountry = 0;
        smSelectedTrack = 0;
        smSelectedCart = 0;
        smForwardPressed = false;
    }

    public static void resetKeys() {
        smKeys = 0;
    }

    public static void restartLevel() {
        smScoringTime = 0;
        GameEngine.restartLevel();
        smRideFinished = false;
        smOnlyTutorialText = false;
        smLevelBraking = false;
    }

    public static void restartReplay() {
        GameEngine.initReplayMode();
        smReplay = true;
        smRideFinished = true;
    }

    public static final void saveCareer() throws IOException {
        if (Cheats.smCheatsEnabled) {
            return;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeByte(smShowTutorial ? 1 : 0);
            dataOutputStream.writeByte(smSelectedCart);
            for (int i = 0; i < 27; i++) {
                dataOutputStream.writeByte(smTutorialSeen[i] ? 1 : 0);
            }
        } catch (IOException e) {
        }
        Toolkit.writeRecord(RS_NAME_CAREER, byteArrayOutputStream.toByteArray());
    }

    public static void screenSizeChanged() {
    }

    private static void setTextBox(SpriteObject spriteObject, String str, boolean z) {
        smTextBox.setTitleBar(null, null, 1);
        smTextBox.setItemDvc(0, 1, null, spriteObject, -1);
        smTextBox.setItemDvc(1, 1, str, null, -1);
        smTextBox.setImageFonts(Game.smTitleFont, Game.smTextFont, Game.smSelectionFont);
        smTextBox.setStyle(5);
        int gameAreaWidth = Game.getGameAreaWidth() - 20;
        int screenHeight = Toolkit.getScreenHeight() - Toolkit.getSoftKeyAreaHeight();
        int preferredHeight = smTextBox.getPreferredHeight(gameAreaWidth, screenHeight);
        smTextBox.setSize(gameAreaWidth, Toolkit.getScreenHeight());
        smTextBox.setBounds(10, (screenHeight - preferredHeight) / 2, gameAreaWidth, preferredHeight);
        smTextBox.setVisible();
        smNonstopTutorial = false;
        smShowTextBox = true;
    }

    private static void setTextBox(String str, SpriteObject spriteObject, String str2) {
        smTextBox.setImageFonts(Game.smTitleFont, Game.smTextFont, Game.smSelectionFont);
        int gameAreaWidth = Game.getGameAreaWidth() - 20;
        int screenHeight = Toolkit.getScreenHeight() - Toolkit.getSoftKeyAreaHeight();
        smTextBox.setTitleBar(str, null, 1);
        smTextBox.setItemDvc(0, 1, null, spriteObject, -1);
        smTextBox.setItem(1, 1, str2, null, -1);
        smTextBox.setBounds(10, 0, gameAreaWidth, Toolkit.getScreenHeight());
        int preferredHeight = smTextBox.getPreferredHeight(gameAreaWidth, screenHeight);
        smTextBox.setBounds(10, (screenHeight - preferredHeight) / 2, gameAreaWidth, preferredHeight);
        smTextBox.setSoftkey(1, 0);
        smTextBox.setVisible();
        smShowTextBox = true;
        smNonstopTutorial = false;
    }

    private static void setTextBox(String str, String str2, boolean z) {
        smTextBox.setTitleBar(str, null, 1);
        smTextBox.setItem(0, 1, null, null, -1);
        smTextBox.setItem(1, 1, str2, null, -1);
        smTextBox.setImageFonts(Game.smTitleFont, Game.smTextFont, Game.smSelectionFont);
        smTextBox.setStyle(5);
        int gameAreaWidth = Game.getGameAreaWidth() - 20;
        int screenHeight = Toolkit.getScreenHeight() - Toolkit.getSoftKeyAreaHeight();
        int preferredHeight = smTextBox.getPreferredHeight(gameAreaWidth, screenHeight);
        smTextBox.setSize(gameAreaWidth, Toolkit.getScreenHeight());
        smTextBox.setBounds(10, (screenHeight - preferredHeight) / 2, gameAreaWidth, preferredHeight);
        smTextBox.setVisible();
        smNonstopTutorial = false;
        smShowTextBox = true;
    }

    private static void setTextBox(String str, boolean z) {
        smTextBox.setTitleBar(null, null, 1);
        smTextBox.setItem(0, 1, null, null, -1);
        smTextBox.setItem(1, 1, str, null, -1);
        smTextBox.setImageFonts(Game.smTitleFont, Game.smTextFont, Game.smSelectionFont);
        smTextBox.setStyle(5);
        int gameAreaWidth = Game.getGameAreaWidth() - 20;
        int screenHeight = Toolkit.getScreenHeight() - Toolkit.getSoftKeyAreaHeight();
        int preferredHeight = smTextBox.getPreferredHeight(gameAreaWidth, screenHeight);
        smTextBox.setBounds(10, 0, gameAreaWidth, Toolkit.getScreenHeight());
        smTextBox.setBounds(10, (screenHeight - preferredHeight) / 2, gameAreaWidth, preferredHeight);
        smTextBox.setVisible();
        smNonstopTutorial = false;
        smShowTextBox = true;
    }

    private static void setTutorialText(int i) {
        smCurrentTutorial = i;
        smTutorialSpecialID = i;
        smOnlyTutorialText = true;
    }

    public static void setTutorialTextBox(String str, int i, boolean z) throws IOException {
        String text = Toolkit.getText(TUTORIAL_TIDS[i]);
        smTutorialSprites[0] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_SMILEY, true), false);
        smTutorialSprites[1] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_SIGNS_RAIL_CHANGE, true), false);
        smTutorialSprites[2] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_SIGNS_SPRING, true), false);
        smTutorialSprites[3] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_SIGNS_TUNNEL, true), false);
        smTutorialSprites[4] = new SpriteObject(DavinciUtilities.loadAnimation(-1, true), false);
        smTutorialSprites[5] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_SIGNS_FALLING_RAILS, true), false);
        smTutorialSprites[6] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_POWER_UP_BASIC_TURBO, true), true);
        smTutorialSprites[7] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_POWER_UP_BASIC_EXCITER, true), true);
        smTutorialSprites[8] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_POWER_UP_BASIC_EXTRA_LIFE, true), true);
        smTutorialSprites[9] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_POWER_UP_BASIC_MAGNET, true), true);
        smTutorialSprites[10] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_G_POWER_UP_BASIC_WINGED_JUMP, true), true);
        switch (i) {
            case 3:
                setTextBox(str, smTutorialSprites[0], text);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 22:
            default:
                setTextBox((String) null, (SpriteObject) null, text);
                break;
            case 16:
                setTextBox(str, smTutorialSprites[1], text);
                break;
            case 17:
                setTextBox(str, smTutorialSprites[2], text);
                break;
            case 18:
                setTextBox(str, smTutorialSprites[3], text);
                break;
            case 19:
                setTextBox(str, smTutorialSprites[4], text);
                break;
            case 20:
                setTextBox(str, smTutorialSprites[5], text);
                break;
            case 21:
                setTextBox((String) null, smTutorialSprites[6], text);
                break;
            case 23:
                setTextBox((String) null, smTutorialSprites[7], text);
                break;
            case 24:
                setTextBox((String) null, smTutorialSprites[9], text);
                break;
            case 25:
                setTextBox((String) null, smTutorialSprites[8], text);
                break;
            case 26:
                setTextBox((String) null, smTutorialSprites[10], text);
                break;
        }
        smCurrentTutorial = i;
    }

    public static void setupMultiPlayerResultScreen(MenuObject menuObject, int i, int i2) {
    }

    public static final void setupProfileScreen(MenuObject menuObject, int i, int i2) {
    }

    public static final void setupSimpleProfileScreen(MenuObject menuObject, int i, int i2) {
    }

    public static void switchState(int i, int i2) throws IOException {
        smGameState = i2;
        updateSoftKeys();
        smKeys = 0;
        switch (i2) {
            case 50:
            case 57:
                if (GameEngine.smGameEngineState == 2) {
                    GameEngine.smGhostEnabled = false;
                }
                if (i != 24 && Engine2D.smIngameResetNeeded) {
                    GameEngine.createTrains();
                    if (!smReplay) {
                        String str = Toolkit.getText(TextIDs.TID_SELECTION_TRACK) + " " + (smLevelIdx + 1);
                        if (!smTutorialSeen[3] && smLevelIdx == 0 && RollerGameEngine.smGameMode == 0) {
                            setTutorialTextBox(str, 3, false);
                        } else if (!smTutorialSeen[16] && smLevelIdx == 7) {
                            setTutorialTextBox(str, 16, false);
                        } else if (!smTutorialSeen[17] && smLevelIdx == 5) {
                            setTutorialTextBox(str, 17, false);
                        } else if (!smTutorialSeen[18] && smLevelIdx == 8) {
                            setTutorialTextBox(str, 18, false);
                        } else if (smTutorialSeen[20] || smLevelIdx != 2) {
                            setTextBox(str, (SpriteObject) null, Toolkit.getText(Engine2D.TORNADO_LEVEL[Engine2D.smInteractiveCurrentLevel] ? 56 : 55));
                        } else {
                            setTutorialTextBox(str, 20, false);
                        }
                    }
                    GameEngine.smEngine2d.ingameResetEffects();
                    GameEngine.mSmileyType = 0;
                    GameEngine.smGhostWasBeaten = false;
                    GameEngine.smEngine2d.ingameInitParallax();
                    GameEngine.smEngine2d.hudInit();
                    GameEngine.smEngine2d.scoreInit();
                    GameEngine.smEngine2d.ingameCalculateCamera(false);
                    GameEngine.smEngine2d.updateIngame(0);
                    smLevelBraking = false;
                    break;
                }
                break;
            case 62:
                GameEngine.finalizeLoading();
                GameEngine.initIntroMode();
                smIntroTime = 0;
                break;
        }
    }

    public static void unloadStateGroup(int i) {
        switch (i) {
            case 2:
            case 9:
                TrackModel.releaseTrack();
                break;
        }
    }

    private static int updateGame(int i) throws IOException {
        mLogicTickCount++;
        smAccelerating = (smKeys & 2) != 0;
        smBraking = (smKeys & 1) != 0;
        if (smBraking) {
            smLevelBraking = true;
        }
        if (smAccelerating) {
            if (GameMobile.smCurrentState == 50 && smGameState == 50) {
                Toolkit.forceBacklightOn();
            }
            if (!GameEngine.smEngine2d.mTornadoEnabled) {
                GameEngine.smEngine2d.mTornadoEnabled = true;
            }
            if (!smForwardPressed) {
                smForwardPressed = true;
            }
        }
        if (smCheatsEnabled) {
            if (smPlayerCnt == 1 && (smKeys & 4) != 0) {
                smCheatSkip = true;
            }
            if (smPlayerCnt == 1 && (smKeys & 8) != 0) {
                smCheatSkip2 = true;
            }
        }
        int iLogicUpdate = GameEngine.logicUpdate(clampDeltaTime(i));
        if (smCheatSkip || smCheatSkip2 || smInterruptReplay) {
            smInterruptReplay = false;
            iLogicUpdate = 1;
        }
        switch (iLogicUpdate) {
            case 1:
                if (replayIsRequested()) {
                    return 28;
                }
                if (Game.smCurrentState != 58) {
                    smRideFinished = false;
                    smShowTutorial = false;
                    handleUnlockEvents();
                }
                return 2;
            case 2:
                return 4;
            default:
                if (GameEngine.smileyActive()) {
                    smScoringTime += i;
                }
                if ((smKeys & 64) == 0) {
                    return 0;
                }
                if (!isControllerEnabled()) {
                    if ((smKeys & 1) != 0 || (smKeys & 2) != 0) {
                        smKeys ^= 64;
                        return 0;
                    }
                    smKeys ^= 64;
                }
                if (isControllerEnabled()) {
                    mLastKey = 0;
                }
                return 1;
        }
    }

    private static int updateIntro(int i) {
        int iClampDeltaTime = clampDeltaTime(i);
        smIntroTime += iClampDeltaTime;
        return (GameEngine.logicUpdate(iClampDeltaTime) == 0 && smKeys == 0) ? 0 : 2;
    }

    private static void updateSoftKeys() {
        if (RollerGameEngine.smTrackMenu) {
        }
        Game.removeAllSoftKeys();
        switch (smGameState) {
            case 50:
                if (!smRideFinished) {
                    Game.setSoftKey(9, 0);
                    break;
                } else {
                    Game.setSoftKey(14, 0);
                    break;
                }
            case 62:
                Game.setSoftKey(14, 0);
                break;
        }
    }
}
