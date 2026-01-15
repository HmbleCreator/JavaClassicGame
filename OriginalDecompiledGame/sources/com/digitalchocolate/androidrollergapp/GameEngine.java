package com.digitalchocolate.androidrollergapp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class GameEngine {
    private static final int ACHIEVEMENT_AIR_TIME_1 = 6;
    private static final int ACHIEVEMENT_AIR_TIME_2 = 7;
    private static final int ACHIEVEMENT_COMPLETED_CHINA = 9;
    private static final int ACHIEVEMENT_COMPLETED_EUROPE = 11;
    private static final int ACHIEVEMENT_COMPLETED_USA = 10;
    public static final int ACHIEVEMENT_COUNT = 29;
    private static final int ACHIEVEMENT_CRASH_COUNT_1 = 8;
    private static final int ACHIEVEMENT_DISTANCE_ON_TRACK_1 = 3;
    private static final int ACHIEVEMENT_DISTANCE_ON_TRACK_2 = 4;
    private static final int ACHIEVEMENT_DISTANCE_ON_TRACK_3 = 5;
    private static final int ACHIEVEMENT_GHOST_BEATEN_1 = 25;
    private static final int ACHIEVEMENT_GHOST_BEATEN_2 = 26;
    private static final int ACHIEVEMENT_GHOST_BEATEN_3 = 27;
    private static final int ACHIEVEMENT_MULTIPLAYER_PLAY_COUNT = 15;
    private static final int ACHIEVEMENT_PLAY_TIME_1 = 0;
    private static final int ACHIEVEMENT_PLAY_TIME_2 = 1;
    private static final int ACHIEVEMENT_PLAY_TIME_3 = 2;
    public static final int ACHIEVEMENT_STATUS_LOCKED = 0;
    public static final int ACHIEVEMENT_STATUS_NEEDS_REPORTING = 1;
    public static final int ACHIEVEMENT_STATUS_UNLOCKED = 2;
    private static final int ACHIEVEMENT_TOTAL_SCORE_1 = 12;
    private static final int ACHIEVEMENT_TOTAL_SCORE_2 = 13;
    private static final int ACHIEVEMENT_TOTAL_SCORE_3 = 14;
    private static final int ACHIEVEMENT_TRACKS_COMPLETED_WITH_HEAVY_CAR_1 = 22;
    private static final int ACHIEVEMENT_TRACKS_COMPLETED_WITH_HEAVY_CAR_2 = 23;
    private static final int ACHIEVEMENT_TRACKS_COMPLETED_WITH_HEAVY_CAR_3 = 24;
    private static final int ACHIEVEMENT_TRACKS_COMPLETED_WITH_LIGHT_CAR_1 = 16;
    private static final int ACHIEVEMENT_TRACKS_COMPLETED_WITH_LIGHT_CAR_2 = 17;
    private static final int ACHIEVEMENT_TRACKS_COMPLETED_WITH_LIGHT_CAR_3 = 18;
    private static final int ACHIEVEMENT_TRACKS_COMPLETED_WITH_MEDIUM_CAR_1 = 19;
    private static final int ACHIEVEMENT_TRACKS_COMPLETED_WITH_MEDIUM_CAR_2 = 20;
    private static final int ACHIEVEMENT_TRACKS_COMPLETED_WITH_MEDIUM_CAR_3 = 21;
    private static final int ACHIEVEMENT_TRACK_COMPLETED_WITH_ZERO_POINTS = 28;
    public static final int COUNTDOWN_TIME = 3000;
    public static final int CRASH_SEQ_COUNT = 1;
    private static final int FIREWORKS_TIME = 2500;
    public static final int GAME_ENGINE_STATE_COUNTDOWN = 0;
    public static final int GAME_ENGINE_STATE_END = 3;
    public static final int GAME_ENGINE_STATE_INTRO = 4;
    public static final int GAME_ENGINE_STATE_PLAY = 1;
    public static final int GAME_ENGINE_STATE_REPLAY = 2;
    public static final int GAME_EVENT_DEBRIEF = 1;
    public static final int GAME_EVENT_FAIL = 2;
    public static final int GAME_EVENT_NONE = 0;
    private static final byte GHOST_METADATA_CAR_COUNT = 0;
    private static final byte GHOST_METADATA_CAR_TYPE = 3;
    private static final byte GHOST_METADATA_COUNT = 4;
    private static final byte GHOST_METADATA_PASSANGER_COUNT = 1;
    public static final int MASK_HEAVY_CAR = 4;
    public static final int MASK_LIGHT_CAR = 1;
    public static final int MASK_MEDIUM_CAR = 2;
    private static final int MAX_REPLAY_LENGTH = 9000;
    private static final byte REPEAT_START_TAG = -1;
    public static final int SMILEY_STATE_NONE = 0;
    public static final int SMILEY_STATE_SCORE_COMING = 1;
    public static final int SMILEY_STATE_SWEEP = 2;
    public static final int SMILEY_SWEEP_TIME = 500;
    private static final String STRING_R = "R";
    public static int mSmileyType;
    private static boolean smAccelerateState;
    private static boolean smAccelerateStateGhost;
    public static int smBestHappy;
    public static int smBestJump;
    public static int smBestSpeed;
    private static boolean smBrakeState;
    private static boolean smBrakeStateGhost;
    public static int smCarCnt;
    public static int smCarCntGhost;
    public static int smCurrentLevelTotalScore;
    public static Engine2D smEngine2d;
    public static Engine3D smEngine3d;
    private static int smInputBufferIdx;
    private static int smInputBufferIdxGhost;
    public static int smLongestJump;
    public static int smLongestJumpOnPreviousTrack;
    public static int smMaximumGForce;
    public static int smMaximumGForceOnPreviousTrack;
    public static int smMaximumSpeed;
    public static int smMaximumSpeedOnPreviousTrack;
    public static int smPassengerCnt;
    public static int smPassengerCntGhost;
    private static int smScreenH;
    private static int smScreenW;
    public static boolean smTornadoAnimationFinish;
    public static int smTotalAirTime;
    public static int smTotalCrashCount;
    public static int smTotalDistanceOnTrack;
    public static int smTotalMultiplayerRacesPlayed;
    public static int smTotalPlayTime;
    public static int smTotalTimesGhostBeaten;
    public static Train smTrain;
    public static Train smTrainGhost;
    public static int smGameEngineState = 0;
    public static int smGameStateTimer = 0;
    private static byte[] smInputBuffer = new byte[2250];
    private static byte[] smInputBufferGhost = new byte[2250];
    private static byte[][] smPackedGhostData = new byte[99][];
    private static byte[][] smGhostMetaData = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, 99, 4);
    public static int[] smCrashSegIndices = new int[1];
    public static boolean smGhostEnabled = false;
    public static boolean smGhostMultiplayerMode = false;
    public static boolean smGhostWasBeaten = false;
    private static int mSmileyState = 0;
    private static int mSmileyStateTime = 0;
    private static int mSmileyPoint = 0;
    private static int mSmileyWobbleTime = 0;
    public static byte[] mAchievementStatus = new byte[29];
    public static byte[][] smTrackCompletionStatus = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, 3, 33);

    private static final void addCrashSegIdx(int i) {
        for (int i2 = 0; i2 > 0; i2--) {
            smCrashSegIndices[i2] = smCrashSegIndices[i2 - 1];
        }
        smCrashSegIndices[0] = i;
    }

    private static final void clearInputBuffer() {
        for (int i = 0; i < smInputBuffer.length; i++) {
            smInputBuffer[i] = 0;
        }
    }

    public static final void createTrains() {
        smTrain = new Train(smCarCnt, smPassengerCnt, TrackModel.smTrackPointY[0], false);
        if (!(smGhostEnabled && !smGhostMultiplayerMode && RollerGameEngine.smGameMode == 0) && (!smGhostEnabled || !smGhostMultiplayerMode || DCThrillOld.smCurrentPlayer <= 1 || RollerGameEngine.smGameMode == 2)) {
            smTrainGhost = null;
        } else {
            smTrainGhost = new Train(smCarCntGhost, smPassengerCntGhost, TrackModel.smTrackPointY[0], true);
        }
        smEngine2d.ingameAttachTrain(smTrain, smTrainGhost);
    }

    public static void doDraw(Graphics graphics) {
        int gameAreaWidth = Game.getGameAreaWidth();
        int screenHeight = Toolkit.getScreenHeight();
        if (smGameEngineState == 1 || smGameEngineState == 0 || smGameEngineState == 3) {
            smEngine2d.ingameDraw(graphics);
            drawHud(graphics);
            return;
        }
        int i = screenHeight >> 3;
        smEngine2d.ingameDraw(graphics);
        graphics.setColor(0);
        graphics.fillRect(0, 0, gameAreaWidth, i);
        graphics.fillRect(0, screenHeight - i, gameAreaWidth, i);
        if (smGameEngineState == 2 && Game.smCurrentState == 50 && (System.currentTimeMillis() & 1023) > 511) {
            int i2 = gameAreaWidth >> 5;
            Game.smTextFont.drawString(graphics, STRING_R, i2, i2 + 0, 20);
        }
    }

    private static final void drawHud(Graphics graphics) {
        smScreenW = Game.getGameAreaWidth();
        smScreenH = Toolkit.getScreenHeight();
        drawSmiley(graphics);
        smEngine2d.hudDraw(graphics);
        if (Game.smCurrentState != 58) {
            Engine2D.particlesDraw(graphics, 4);
        }
    }

    private static final void drawSmiley(Graphics graphics) {
        graphics.setClip(0, 0, smScreenW, smScreenH);
    }

    public static final void finalizeLoading() {
        smEngine2d.finalizeLoading();
    }

    public static final boolean getAccelerate() {
        return smAccelerateState;
    }

    public static final boolean getAccelerateGhost() {
        return smAccelerateStateGhost;
    }

    public static final boolean getBrake() {
        return smBrakeState;
    }

    public static final boolean getBrakeGhost() {
        return smBrakeStateGhost;
    }

    public static boolean getFullCrash() {
        return smTrain.fullCrash();
    }

    public static final int getLoadingStepCount() {
        return Engine2D.getLoadingStepCount() + 6;
    }

    public static final int getScore() {
        if (smTrain.hasFailed()) {
            return 0;
        }
        return smTrain.getScore();
    }

    public static boolean getSingleCarCrash() {
        return smTrain.singleCrash();
    }

    private static final int getSmileyIdx(int i) {
        if (i > 200) {
            return 2;
        }
        return i > 100 ? 1 : 0;
    }

    public static final int getSmileys() {
        if (smTrain.hasFailed() && RollerGameEngine.smGameMode == 0) {
            return 0;
        }
        return smTrain.getSmilyes();
    }

    public static final boolean ghostModeAvailable(int i) {
        return smPackedGhostData[i] != null;
    }

    public static void initEngine() {
        loadGhostData();
        resetCrashIndices();
    }

    public static final void initIntroMode() {
        for (int i = 0; i < smInputBuffer.length; i++) {
            smInputBuffer[i] = -86;
        }
        smGameEngineState = 4;
        initReplayMode();
        smEngine2d.updateIngame(0);
    }

    public static final void initReplayMode() {
        smInputBufferIdx = 0;
        if (smGameEngineState != 4) {
            smGameEngineState = 2;
        }
        smTornadoAnimationFinish = false;
        smGameStateTimer = 0;
        smTrain = new Train(smCarCnt, smPassengerCnt, TrackModel.smTrackPointY[0], false);
        smEngine2d.ingameAttachTrain(smTrain, null);
    }

    public static final boolean isPlayerPlaying() {
        return smGameEngineState == 1;
    }

    public static final void loadGhostData() throws IOException {
        byte[] record = Toolkit.readRecord(DCThrillOld.RS_NAME_GHOST_DATA);
        if (record != null) {
            try {
                DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(record));
                for (int i = 0; i < smPackedGhostData.length; i++) {
                    int i2 = dataInputStream.readShort();
                    if (i2 > 0) {
                        smPackedGhostData[i] = new byte[i2];
                        for (int i3 = 0; i3 < i2; i3++) {
                            smPackedGhostData[i][i3] = dataInputStream.readByte();
                        }
                    }
                }
                for (int i4 = 0; i4 < smGhostMetaData.length; i4++) {
                    for (int i5 = 0; i5 < smGhostMetaData[i4].length; i5++) {
                        smGhostMetaData[i4][i5] = dataInputStream.readByte();
                    }
                }
            } catch (IOException e) {
            }
        }
    }

    public static final void loadLevel(int i, int i2, int i3, int i4, int i5) {
        byte[] bArrUnpackGhostData;
        if (smEngine2d != null) {
            smEngine2d.mHudCurrentSign = -1;
        }
        switch (i2) {
            case 0:
                smCarCnt = i4;
                smPassengerCnt = i5;
                smTotalPlayTime = 0;
                smTotalAirTime = 0;
                smTotalDistanceOnTrack = 0;
                smTotalCrashCount = 0;
                break;
            case 1:
                if (smEngine2d == null) {
                    smEngine2d = new Engine2D();
                    break;
                }
                break;
            case 2:
                if (i < ResultsTable.TOP_SCORE_VALUES.length) {
                    smCurrentLevelTotalScore = ResultsTable.TOP_SCORE_VALUES[i];
                }
                TrackModel.loadLevel(i);
                break;
            case 3:
                break;
            case 4:
                if (smGhostEnabled && !smGhostMultiplayerMode && !RollerGameEngine.smTrackMenu && (bArrUnpackGhostData = unpackGhostData(smPackedGhostData[i])) != null) {
                    smInputBufferGhost = bArrUnpackGhostData;
                    smInputBufferIdxGhost = 0;
                    smCarCntGhost = smGhostMetaData[i][0];
                    smPassengerCntGhost = smGhostMetaData[i][1];
                    DCThrillOld.smSelectedCartGhost = smGhostMetaData[i][3];
                    break;
                }
                break;
            case 5:
                smGameEngineState = 0;
                smGameStateTimer = 0;
                smInputBufferIdx = 0;
                clearInputBuffer();
                resetSmiley();
                break;
            default:
                int loadingStepCount = i2 - Engine2D.getLoadingStepCount();
                if (loadingStepCount < Engine2D.getLoadingStepCount()) {
                    smEngine2d.loadResources(loadingStepCount);
                    break;
                }
                break;
        }
    }

    public static int logicUpdate(int i) {
        smGameStateTimer += i;
        if (smGameEngineState == 3 || smGameEngineState == 1 || smGameEngineState == 2 || smGameEngineState == 4) {
            if (smEngine2d.mScorePrizeTimer == -1) {
                smEngine2d.mScorePrizePointsBackup = smTrain.mPointsTarget;
            }
            smTrain.update(i);
            if (smGameEngineState == 1) {
                smTotalPlayTime += i;
                if (RollerGameEngine.smGameMode == 0) {
                    updateStatistics(i);
                }
                if (smGhostEnabled && smTrainGhost != null && !smTrainGhost.exitMe()) {
                    smTrainGhost.update(i);
                }
            }
        }
        smEngine2d.ingameCalculateCamera(true);
        smEngine2d.updateCamera(i);
        smEngine2d.updateIngame(i);
        if (smGameEngineState != 4) {
            smEngine2d.updateHud(i);
        }
        if (smGameEngineState == 1) {
            updateSmiley(i);
        }
        if (smTrain.exitMe()) {
            if (smTrain.hasFailed()) {
                if (smGameEngineState == 1 && RollerGameEngine.smGameMode == 0) {
                    addCrashSegIdx(smTrain.mSegmentIdx[0]);
                }
                Engine2D.particlesInit();
                return 2;
            }
            if (smGameEngineState == 2) {
                Engine2D.particlesInit();
                return 1;
            }
            if (RollerGameEngine.smGameMode == 0 && smTrainGhost != null && !smTrainGhost.exitMe() && !smGhostWasBeaten) {
                smGhostWasBeaten = true;
            }
        }
        if (smGameEngineState == 2 && smInputBufferIdx >= smInputBuffer.length) {
            return 1;
        }
        switch (smGameEngineState) {
            case 0:
                if (DCThrillOld.smAccelerating) {
                    smGameEngineState = 1;
                    smGameStateTimer = 0;
                    break;
                }
                break;
            case 1:
                if (smTrain.trackEnded()) {
                    smGameEngineState = 3;
                    Engine2D.particlesInit();
                    if (RollerGameEngine.smGameMode == 0 && !ResultsCareerSimple.smGetSpeed && smTrain.mCarCnt > 1) {
                        Engine2D.particlesAddMovement(Engine2D.particlesAddParticle(4, smEngine2d.ingameGetCameraX(), smEngine2d.ingameGetCameraY(), 4), Game.getGameAreaWidth() + smEngine2d.mHudScoreSmileOffX, smEngine2d.mHudScoreSmileY, 1000);
                        ResultsCareerSimple.smGetSpeed = true;
                    }
                    smGameStateTimer = 0;
                    break;
                }
                break;
            case 3:
                if (smGameStateTimer > FIREWORKS_TIME) {
                    smTrain.lockPoints();
                    smGameStateTimer = 0;
                    return 1;
                }
                break;
        }
        return 0;
    }

    private static byte[] packGhostData(byte[] bArr) {
        int i;
        boolean z;
        boolean z2;
        int i2;
        boolean z3;
        boolean z4;
        int i3;
        int i4;
        int length = bArr.length;
        for (int length2 = bArr.length - 1; length2 >= 0 && bArr[length2] == 0; length2--) {
            length--;
        }
        byte[] bArr2 = new byte[length];
        boolean z5 = false;
        byte b = bArr[0];
        int i5 = 0;
        int i6 = 1;
        int i7 = 1;
        while (i7 < length) {
            if (i7 == length - 1) {
                z = true;
                z2 = true;
            } else {
                z = false;
                z2 = false;
            }
            if (b == bArr[i7] && i6 < 255) {
                i2 = i6 + 1;
                z3 = z5;
                z4 = z2;
            } else if (z) {
                z4 = true;
                i2 = i6;
                z3 = true;
            } else {
                i2 = i6;
                z3 = z5;
                z4 = true;
            }
            if (z4) {
                if (i2 > 3) {
                    bArr2[i5] = -1;
                    int i8 = i5 + 1;
                    bArr2[i8] = (byte) (i2 - 128);
                    int i9 = i8 + 1;
                    bArr2[i9] = b;
                    i3 = i9 + 1;
                } else {
                    for (int i10 = 0; i10 < i2; i10++) {
                        bArr2[i5] = b;
                        i5++;
                    }
                    i3 = i5;
                }
                i4 = 1;
            } else {
                i3 = i5;
                i4 = i2;
            }
            byte b2 = bArr[i7];
            i7++;
            boolean z6 = z3;
            i6 = i4;
            b = b2;
            i5 = i3;
            z5 = z6;
        }
        if (z5) {
            bArr2[i5] = bArr[length - 1];
            i = i5 + 1;
        } else {
            i = i5;
        }
        byte[] bArr3 = new byte[i];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr3.length);
        return bArr3;
    }

    private static final byte readInputState(boolean z) {
        byte[] bArr;
        int i;
        if (z) {
            bArr = smInputBufferGhost;
            i = smInputBufferIdxGhost;
        } else {
            bArr = smInputBuffer;
            i = smInputBufferIdx;
        }
        int i2 = i >> 2;
        int i3 = i & 3;
        if (i2 >= bArr.length) {
            return (byte) 1;
        }
        int i4 = i + 1;
        int i5 = bArr[i2] >> (i3 * 2);
        if (z) {
            smInputBufferIdxGhost = i4;
        } else {
            smInputBufferIdx = i4;
        }
        return (byte) (i5 & 3);
    }

    public static void releaseEngine() {
        smEngine2d = null;
        smTrain = null;
        smTrainGhost = null;
    }

    public static final void resetCrashIndices() {
        for (int i = 0; i < 1; i++) {
            smCrashSegIndices[i] = -1;
        }
    }

    public static final void resetGhostData() throws IOException {
        smPackedGhostData = new byte[99][];
        smGhostMetaData = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, 99, 4);
        saveGhostData();
    }

    private static final void resetSmiley() {
        mSmileyState = 0;
        mSmileyStateTime = 0;
    }

    public static final void restartGhost() {
        smInputBufferIdx = 0;
        smTotalAirTime = 0;
        smTotalDistanceOnTrack = 0;
        smTotalPlayTime = 0;
        smTotalCrashCount = 0;
        smInputBufferIdxGhost = 0;
        clearInputBuffer();
        smGameEngineState = 0;
        smGameStateTimer = 0;
    }

    public static final void restartLevel() {
        restartGhost();
        smTrain = new Train(smCarCnt, smPassengerCnt, TrackModel.smTrackPointY[0], false);
        if (smGhostEnabled) {
            smTrainGhost = new Train(smCarCntGhost, smPassengerCntGhost, TrackModel.smTrackPointY[0], true);
            smGhostWasBeaten = false;
        } else {
            smTrainGhost = null;
        }
        smEngine2d.ingameAttachTrain(smTrain, smTrainGhost);
        resetSmiley();
    }

    public static final void saveGhostData() throws IOException {
        if (Cheats.smCheatsEnabled) {
            return;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        for (int i = 0; i < smPackedGhostData.length; i++) {
            try {
                if (smPackedGhostData[i] == null) {
                    dataOutputStream.writeShort(0);
                } else {
                    dataOutputStream.writeShort(smPackedGhostData[i].length);
                    dataOutputStream.write(smPackedGhostData[i]);
                }
            } catch (IOException e) {
            }
        }
        for (int i2 = 0; i2 < smGhostMetaData.length; i2++) {
            for (int i3 = 0; i3 < smGhostMetaData[i2].length; i3++) {
                dataOutputStream.write(smGhostMetaData[i2][i3]);
            }
        }
        Toolkit.writeRecord(DCThrillOld.RS_NAME_GHOST_DATA, byteArrayOutputStream.toByteArray());
    }

    private static final void saveInputState(byte b) {
        int i = smInputBufferIdx >> 2;
        int i2 = smInputBufferIdx & 3;
        if (i < smInputBuffer.length) {
            byte[] bArr = smInputBuffer;
            bArr[i] = (byte) (((byte) (b << (i2 * 2))) | bArr[i]);
            smInputBufferIdx++;
        }
    }

    public static void setCarCnt(int i) {
        smCarCnt = i;
    }

    public static void setPassengerCnt(int i) {
        smPassengerCnt = i;
    }

    public static boolean smileyActive() {
        return mSmileyState != 0;
    }

    public static final void storeGhostData(int i) {
        byte[] bArr = new byte[smInputBuffer.length];
        System.arraycopy(smInputBuffer, 0, bArr, 0, smInputBuffer.length);
        if (smGhostMultiplayerMode) {
            smPassengerCntGhost = smPassengerCnt;
            smCarCntGhost = smCarCnt;
            DCThrillOld.smSelectedCartGhost = DCThrillOld.smSelectedCart;
        } else {
            smGhostMetaData[i][0] = (byte) smCarCnt;
            smGhostMetaData[i][1] = (byte) smPassengerCnt;
            smGhostMetaData[i][3] = (byte) DCThrillOld.smSelectedCart;
            smPackedGhostData[i] = packGhostData(bArr);
            saveGhostData();
        }
        if (smGhostEnabled) {
            smInputBufferGhost = bArr;
        }
    }

    private static byte[] unpackGhostData(byte[] bArr) {
        byte[] bArr2 = new byte[2250];
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            byte b = bArr[i];
            i++;
            if (b == -1) {
                int i3 = bArr[i] + 128;
                int i4 = i + 1;
                byte b2 = bArr[i4];
                i = i4 + 1;
                int i5 = i2;
                for (int i6 = 0; i6 < i3; i6++) {
                    bArr2[i5] = b2;
                    i5++;
                }
                i2 = i5;
            } else {
                bArr2[i2] = b;
                i2++;
            }
        }
        return bArr2;
    }

    public static final void updateAchievementsIngame(int i) {
        int fp = FP.toFP(5, 10);
        if (!smTrain.hasFailed()) {
            smTotalPlayTime += i;
            if (mAchievementStatus[0] == 0 && smTotalPlayTime > 3600000) {
                Debugger.verbose("Unlocked achievement: Play game for a time 1");
                mAchievementStatus[0] = 1;
            } else if (mAchievementStatus[1] == 0 && smTotalPlayTime > 10800000) {
                Debugger.verbose("Unlocked achievement: Play game for a time 2");
                mAchievementStatus[1] = 1;
            } else if (mAchievementStatus[2] == 0 && smTotalPlayTime > 32400000) {
                Debugger.verbose("Unlocked achievement: Play game for a time 3");
                mAchievementStatus[2] = 1;
            }
            if (!smTrain.mOnAir) {
                int iAbs = Math.abs(Util.getLengthFast(smTrain.getAveragedDeltaMovementX(), smTrain.getAveragedDeltaMovementY()));
                if (iAbs > fp) {
                    smTotalDistanceOnTrack += FP.mul(iAbs, TrackModel.UNIT_TO_METERS_OPERANT);
                }
                if (mAchievementStatus[3] == 0 && smTotalDistanceOnTrack > Tuner.ACHIEVEMENT_DISTANCE_ON_TRACK_1_LIMIT) {
                    Debugger.verbose("Unlocked achievement: Travel certain distance on track 1");
                    mAchievementStatus[3] = 1;
                } else if (mAchievementStatus[4] == 0 && smTotalDistanceOnTrack > Tuner.ACHIEVEMENT_DISTANCE_ON_TRACK_2_LIMIT) {
                    Debugger.verbose("Unlocked achievement: Travel certain distance on track 2");
                    mAchievementStatus[4] = 1;
                } else if (mAchievementStatus[5] == 0 && smTotalDistanceOnTrack > Tuner.ACHIEVEMENT_DISTANCE_ON_TRACK_3_LIMIT) {
                    Debugger.verbose("Unlocked achievement: Travel certain distance on track 3");
                    mAchievementStatus[5] = 1;
                }
            } else if (smTrain.mOnAirCycleCnt < smTrain.mOnAirCycleCntPrev) {
                smTotalAirTime += smTrain.mOnAirCycleCntPrev * 20;
                if (mAchievementStatus[6] == 0 && smTotalAirTime > 30000) {
                    Debugger.verbose("Unlocked achievement: Reach certain total air time 1");
                    mAchievementStatus[6] = 1;
                } else if (mAchievementStatus[7] == 0 && smTotalAirTime > 120000) {
                    Debugger.verbose("Unlocked achievement: Reach certain total air time 2");
                    mAchievementStatus[7] = 1;
                }
            }
        }
        int newFallenCarCount = smTrain.getNewFallenCarCount();
        if (newFallenCarCount > 0) {
            smTotalCrashCount = newFallenCarCount + smTotalCrashCount;
            if (mAchievementStatus[8] != 0 || smTotalCrashCount <= 1) {
                return;
            }
            Debugger.verbose("Unlocked achievement: Crash certain number of times 1");
            mAchievementStatus[8] = 1;
        }
    }

    public static final void updateInput() {
        if (smGameEngineState == 1) {
            boolean z = DCThrillOld.smBraking;
            boolean z2 = DCThrillOld.smAccelerating;
            byte b = z ? (byte) (0 | 1) : (byte) 0;
            if (z2) {
                b = (byte) (b | 2);
            }
            saveInputState(b);
            smBrakeState = z;
            smAccelerateState = z2;
            if (smGhostEnabled) {
                byte inputState = readInputState(true);
                smBrakeStateGhost = (inputState & 1) != 0;
                smAccelerateStateGhost = (inputState & 2) != 0;
                return;
            }
            return;
        }
        if (smGameEngineState == 2) {
            if (Engine2D.TORNADO_LEVEL[Engine2D.smInteractiveCurrentLevel] && smTornadoAnimationFinish) {
                byte inputState2 = readInputState(false);
                smBrakeState = (inputState2 & 1) != 0;
                smAccelerateState = (inputState2 & 2) != 0;
                smEngine2d.mTornadoEnabled = true;
                return;
            }
            if (Engine2D.TORNADO_LEVEL[Engine2D.smInteractiveCurrentLevel]) {
                return;
            }
            byte inputState3 = readInputState(false);
            smBrakeState = (inputState3 & 1) != 0;
            smAccelerateState = (inputState3 & 2) != 0;
            return;
        }
        if (smGameEngineState == 4) {
            byte inputState4 = readInputState(false);
            smBrakeState = (inputState4 & 1) != 0;
            smAccelerateState = (inputState4 & 2) != 0;
        } else {
            if (smGameEngineState != 3) {
                smBrakeState = false;
                smAccelerateState = false;
                return;
            }
            byte b2 = 1 != 0 ? (byte) (0 | 1) : (byte) 0;
            if (0 != 0) {
                b2 = (byte) (b2 | 2);
            }
            saveInputState(b2);
            smBrakeState = true;
            smAccelerateState = false;
        }
    }

    private static final void updateSmiley(int i) {
        switch (mSmileyState) {
            case 0:
                if (smTrain.getDeltaPoints() > 0) {
                    mSmileyState = 1;
                    mSmileyStateTime = 0;
                    mSmileyPoint = smTrain.getScore();
                    break;
                }
                break;
            case 1:
                mSmileyType = getSmileyIdx(smTrain.getScore() - mSmileyPoint);
                if (smTrain.getDeltaPoints() <= 0) {
                    mSmileyState = 2;
                    mSmileyStateTime = 0;
                    break;
                }
                break;
            case 2:
                if (mSmileyStateTime > 500) {
                    mSmileyState = 0;
                    mSmileyStateTime = 0;
                    break;
                }
                break;
        }
        mSmileyStateTime += i;
        mSmileyWobbleTime += (((smTrain.getScore() + 100) - mSmileyPoint) * i) >> 8;
    }

    public static final void updateStatistics(int i) {
        if (smTrain.mOnAir) {
            smTotalAirTime += i;
        } else {
            smTotalDistanceOnTrack = FP.mul(Math.abs(Util.getLengthFast(smTrain.getAveragedDeltaMovementX(), smTrain.getAveragedDeltaMovementY())), TrackModel.UNIT_TO_METERS_OPERANT);
            smTotalAirTime = 0;
        }
        int i2 = (smTrain.mSpeed * 30) >> 12;
        if (smBestSpeed < i2) {
            smBestSpeed = i2;
        }
        int newFallenCarCount = smTrain.getNewFallenCarCount();
        if (newFallenCarCount > 0) {
            smTotalCrashCount = newFallenCarCount + smTotalCrashCount;
        }
    }
}
