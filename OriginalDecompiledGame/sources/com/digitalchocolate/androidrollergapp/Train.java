package com.digitalchocolate.androidrollergapp;

import com.mascotcapsule.micro3d.v3.Graphics3D;
import java.lang.reflect.Array;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* loaded from: classes.dex */
public class Train {
    private static final int ACCELERATION_SHIFT = 6;
    private static final int CANNON_ANM_TIME_1_LIGHT = 300;
    private static final int CANNON_ANM_TIME_MOVE_UP_15 = 200;
    private static final int CANNON_LIGHTS_COUNT = 3;
    private static int CANNON_LOW_LENGTH = 0;
    private static int CANNON_OFFX = 0;
    private static int CANNON_OFFY = 0;
    private static int CANNON_TIP_H = 0;
    private static final int CAR_DIST = 102400;
    private static final int COLOR_SPEEDLINE = 16711422;
    private static final int COLOR_SPEEDLINE_BORDER_IN = 16645629;
    private static final int COLOR_SPEEDLINE_BORDER_OUT = 16579836;
    public static final int CONVOY_BB_HEIGHT_FP = 163840;
    public static final int CONVOY_BB_SEMIHEIGHT_FP = 81920;
    public static final int CONVOY_BB_SEMIWIDTH_FP = 81920;
    public static final int CONVOY_BB_WIDTH_FP = 163840;
    private static final int CONVOY_COLOR_NORMAL = 0;
    public static final int CONVOY_PICKUPS_COUNT = 8;
    public static final int CONVOY_PICKUPS_EXCITER = 2;
    public static final int CONVOY_PICKUPS_GOD = 4;
    public static final int CONVOY_PICKUPS_JUMP = 5;
    private static String[] CONVOY_PICKUPS_NAMES = null;
    public static final int CONVOY_PICKUPS_NITRO = 0;
    public static final int CONVOY_PICKUPS_SMILEY = 1;
    public static final int CONVOY_PICKUPS_SPRINGS = 6;
    public static final int CONVOY_PICKUPS_STICKY = 3;
    private static final int CONVOY_PICKUPS_TIME_INFINITE = 99999;
    public static final int CONVOY_PICKUPS_WATER = 7;
    private static final int CRASH_SHOW_TIME = 1500;
    public static final int DECIMAL_SHIFT = 12;
    private static final int FLUID_FRICTION_SHIFT = 10;
    public static final int GRAVITY_CONST = 448;
    public static final int GRAVITY_SEMI_CONST = 224;
    private static final int JUMP_IMPULSE_OFFSET_Y_FP = 229376;
    private static final int JUMP_IMPULSE_SEMI_TIME_FP = 245760;
    public static final int MAX_NUM_CARTS = 3;
    private static final int MULTIPLIER_AIR = 1;
    private static final int MULTIPLIER_SPEED = 0;
    private static final int ON_AIR_SPEED_FOR_POINTS = 2048;
    private static final int PARTICLES_JUMP_SPAWN_PERIOD_MS = 200;
    public static final int PARTICLES_JUMP_SPAWN_WHILE_MS = 2000;
    public static final int PICKUP_BREAKS = 0;
    public static final int PICKUP_SPLASH = 1;
    public static final int PICKUP_TUNNEL = 2;
    public static final int REF_PATH_CNT = 6;
    public static final int REF_PATH_LEN = 5;
    public static final int REF_PATH_MIN_Y = 3;
    public static final int REF_PATH_SEG_IDX = 2;
    public static final int REF_PATH_VEL_Y = 5;
    public static final int REF_PATH_X = 0;
    public static final int REF_PATH_Y = 1;
    public static final int SCORE_BRAKE_GO_POINTS_PER_CART = 409600;
    public static final int SCORE_FALLING_TRACK_PER_STRETCH_PER_CART = 15;
    private static final int SCORE_PER_RANGE = 50;
    private static final int SCORE_SPEED_SCALE_1_PERCENT = 50;
    private static final int SCORE_SPEED_SCALE_2_PERCENT = 100;
    private static final int SCORE_SPEED_SCALE_3_PERCENT = 150;
    private static final int SCORE_SPEED_SCALE_4_PERCENT = 200;
    public static final int SCORE_SPRINGS_POINTS_PER_CART = 409600;
    public static final int SCORE_TUNNEL_POINTS_PER_CART = 409600;
    public static final int SCORE_WATER_POINTS_PER_CART = 614400;
    private static final int SINGLE_CRASH_SHOW_TIME = 500;
    private static final int SPARKS_TIME_LANDING = 300;
    private static final int SPEEDLINE_ALPHA_MAX = 176;
    private static final int SPEEDLINE_LINES_COUNT = 12;
    private static final int SPEEDLINE_SPEED_MAX = 300;
    private static final int SPEEDLINE_SPEED_MIN = 100;
    public static final int SPEEDLINE_STATE_DISABLED = 0;
    public static final int SPEEDLINE_STATE_ENABLED = 1;
    public static final int SPEEDLINE_STATE_ENABLED_ONE_TIME = 2;
    private static final int STOP_GO_ANGLE_MAX = 45;
    private static final int STOP_GO_ANGLE_STEP = 15;
    private static final int TIME_LIGHTS_START = 1200;
    private static final int TIME_MOVE_UP = 600;
    private static final int TIME_TOTAL = 1800;
    private static final int TRACK_PUSH_EPSILON = 640;
    private static final int TRACK_SUCK_LIMIT = 1024;
    private static final int TRAIL_COLOR_INTERIOR = 16777215;
    private static final int TRAIL_HEIGHT_MIN = 10;
    private static final int TRAIL_HEIGHT_OFFSET = 4;
    private static final int TRAIL_MARGIN_LINES_COUNT = 5;
    private static final int TRAIL_SPEED_MAX = 300;
    private static final int TRAIL_SPEED_MIN = 150;
    private static final int TRAIL_SPEED_OFFSET = 37;
    public static final int TRAIL_STATE_DISABLED = 0;
    public static final int TRAIL_STATE_ENABLED = 1;
    public static final int TRAIL_STATE_ENABLED_ONE_TIME = 2;
    public static final int TRAIN_SCORE_SHIFT = 4;
    private static final int TRAIN_SEGMENTS_MAX = 150;
    public static final int TRAIN_STATE_CRASH = 2;
    public static final int TRAIN_STATE_DROP = 1;
    public static final int TRAIN_STATE_ON_TRACK = 0;
    public static final int TRAIN_STATE_STOPPED = 4;
    public static final int TRAIN_STATE_TRACK_OVER = 3;
    public static final int TRAIN_TYPE_COUNT = 2;
    public static final int TRAIN_TYPE_GHOST_ID = 1;
    public static final int TRAIN_TYPE_MAIN_ID = 0;
    public static final int UPDATE_DELTA_TIME = 20;
    public static final int UPLIFT_MAX = 20;
    private static final int UP_HILL_NRML_X_LIMIT = -1500;
    private static int[] colors;
    private Image buffer;
    private Graphics gBackup;
    private int mAccelerationNormal;
    private int mAccelerationUphill;
    public boolean mBrakeNeeded;
    private int mBrakingMag;
    public int mCamPointX;
    public int mCamPointY;
    private int mCannonAngle;
    private int mCannonCarY;
    private int mCannonDirection;
    public int[] mCarAngle;
    public int mCarCnt;
    private int mCarCntPrev;
    public int[] mCarX;
    public int[] mCarY;
    private int mCartResetTarget;
    public int mColor;
    public int mCrashVelLimit;
    public Engine2D mEngine2D;
    public Engine3D mEngine3D;
    public int mFirstCarPrevX;
    public int mFirstCarPrevY;
    private int mFluidFriction;
    public int mFullCrashVelLimit;
    public int mGForceFiltered;
    private int mGameTime;
    public int mGravity;
    public boolean mIsGhost;
    public int mJumpDeltaX;
    private int mJumpNextSolidSegmentID;
    private int mLastMultiplierIncrease;
    private int mLastNotOnAirX;
    public int mLastSegment;
    public boolean mOnAir;
    public int mOnAirCycleCnt;
    public int mOnAirCycleCntPrev;
    private boolean mParticlesJumpEnabled;
    private int mParticlesJumpSpawnTimer;
    public int mParticlesJumpTimer;
    public int mPassengerCnt;
    public byte[] mPassengers;
    public int mPickUpIndex;
    public int mPickUpIndexSpeed;
    public int mPickUpSpeedTimer;
    public boolean[] mPickUpsEnabled;
    public int mPickUpsEnabledCount;
    public int[] mPickUpsSorted;
    public int[] mPickUpsTime;
    public int mPoints;
    public int mPointsTarget;
    private int mPointsUnreportedPenalty;
    public int mPrevPoints;
    public boolean mPuffEnabled;
    public int mRefPathIdx;
    private int mScoreMultiplier;
    public boolean mScoreWaterGiven;
    public int[] mSegmentIdx;
    public int mSegmentLastIdx;
    public int mSegmentNrmlX;
    public int mSegmentNrmlY;
    public int mSelectedCart;
    public int mSingleCarCrashedCycle;
    private int mSmileys;
    public int mSpeed;
    private int[] mSpeedlineAngle;
    private int mSpeedlineHeight;
    private int mSpeedlineSegmentStart;
    public int mSpeedlineState;
    private int[] mSpeedlineXFP;
    private int[] mSpeedlineYFP;
    public int mState;
    private int mStateCycleCnt;
    private int mStoppedTimer;
    private boolean mTouchDown;
    public boolean mTrackContact;
    private int[] mTrailAngle;
    private int mTrailHeight;
    private int mTrailInteriorHeight;
    private int mTrailSegmentStart;
    public int mTrailState;
    private int[] mTrailXFP;
    private int[] mTrailYFP;
    private int mUpdateTime;
    public int mUplift;
    public int mVelX;
    public int mVelY;
    private int mWaterSpeedMagnitude;
    private int pixelLength;
    private int[] pixels;
    private static final int JUMP_IMPULSE_TIME_FP = 491520;
    private static final int JUMP_IMPULSE_TIME_SQUARED_FP = FP.mul(JUMP_IMPULSE_TIME_FP, JUMP_IMPULSE_TIME_FP);
    private static final boolean[] CONVOY_PICKUPS_TIME_DRIVEN = {true, true, true, true, true, true, false, true};
    private static int[] TRAIL_COLOR_MARGIN = {Statics.TRAIL_MARGIN_LINES_COLOR_0, Statics.TRAIL_MARGIN_LINES_COLOR_1, Statics.TRAIL_MARGIN_LINES_COLOR_2, Statics.TRAIL_MARGIN_LINES_COLOR_3, Statics.TRAIL_MARGIN_LINES_COLOR_4};
    public boolean mCannonLaunchCondition = false;
    private boolean mAccelerate = false;
    private boolean mBrake = false;
    public boolean mIsBraking = false;
    public boolean mIsAccelerating = false;
    private boolean mAccelerateAllowed = true;
    private boolean mBrakeAllowed = true;
    public boolean mEmitSparks = false;
    private int mLandingSparkTimer = 0;
    private int mCurrentSpeedMultiPlier = 1;
    private int mCurrentAirTimeMultiPlier = 1;
    private boolean mSpeedMultiplierLocked = false;
    private boolean mAirTimeMultiplierLocked = false;
    private boolean mPointIncreaseLocked = false;
    private int mMultiplierExpireTimer = 0;
    public int[][] mRefPathData = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 5, 6);
    private int[] mEmphasizeXFP = new int[3];
    private int[] mEmphasizeYFP = new int[3];
    final int SPEEDLINE_SPEED_RANGE = 200;
    private int mScreenWidth = 0;
    private int mScreenHeight = 0;

    public Train(int i, int i2, int i3, boolean z) {
        this.mIsGhost = false;
        this.mRefPathIdx = 0;
        this.mIsGhost = z;
        int i4 = TrackModel.smTrackPointY[8];
        this.mLastSegment = TrackModel.smTrackPointCount - 8;
        this.mPassengerCnt = i2;
        this.mState = 0;
        this.mStateCycleCnt = 0;
        this.mBrakeNeeded = false;
        this.mJumpDeltaX = 122880;
        int i5 = i < 2 ? 2 : i;
        this.mGameTime = 0;
        this.mUpdateTime = 0;
        this.mPassengers = new byte[i5];
        this.mCarX = new int[i5];
        this.mCarY = new int[i5];
        this.mCarAngle = new int[i5];
        this.mVelX = 0;
        this.mVelY = 0;
        this.mSegmentIdx = new int[i5];
        this.mSegmentNrmlX = 0;
        this.mSegmentNrmlY = -4096;
        this.mSpeed = 0;
        this.mTrackContact = true;
        this.mOnAir = false;
        this.mOnAirCycleCnt = 0;
        this.mTouchDown = false;
        this.mUplift = 0;
        int i6 = i2;
        for (int i7 = 0; i7 < i5; i7++) {
            int iMin = Math.min(2, i6);
            i6 -= 2;
            this.mPassengers[i7] = (byte) Math.max(0, iMin);
            this.mCarX[i7] = ((-i7) * CAR_DIST) + TrackModel.smTrackPointX[8];
            this.mCarY[i7] = i4;
            this.mCarAngle[i7] = 0;
            this.mSegmentIdx[i7] = 8;
        }
        this.mLastNotOnAirX = this.mCarX[0];
        int length = this.mRefPathData.length;
        for (int i8 = 0; i8 < length; i8++) {
            this.mRefPathData[i8][0] = (((i8 - length) + 1) * CAR_DIST) + TrackModel.smTrackPointX[8];
            this.mRefPathData[i8][1] = i4;
            this.mRefPathData[i8][2] = 0;
            this.mRefPathData[i8][3] = i4;
            this.mRefPathData[i8][5] = 0;
        }
        this.mRefPathIdx = length - 1;
        this.mCarCnt = i5;
        this.mCarCntPrev = i5;
        updateCamPoint();
        if (this.mIsGhost) {
            this.mSelectedCart = DCThrillOld.smSelectedCartGhost;
        } else {
            this.mSelectedCart = DCThrillOld.smSelectedCart;
        }
        this.mAccelerationUphill = Tuner.CART_ATTRIBUTES[this.mSelectedCart][0];
        this.mAccelerationNormal = Tuner.CART_ATTRIBUTES[this.mSelectedCart][1];
        this.mFluidFriction = Tuner.CART_ATTRIBUTES[this.mSelectedCart][2];
        this.mBrakingMag = Tuner.CART_ATTRIBUTES[this.mSelectedCart][3];
        if (Cheats.smCheatsInvincible || RollerGameEngine.smTrackMenu) {
            this.mCrashVelLimit = -10240000;
            this.mFullCrashVelLimit = -10240000;
        } else if (GameEngine.smGameEngineState == 4) {
            this.mCrashVelLimit = ((Tuner.CART_ATTRIBUTES[2][4] << 10) * 100) / 100;
            this.mFullCrashVelLimit = ((Tuner.CART_ATTRIBUTES[2][5] << 10) * 100) / 100;
        } else {
            this.mCrashVelLimit = ((Tuner.CART_ATTRIBUTES[this.mSelectedCart][4] << 10) * 100) / 100;
            if (GameEngine.smGameEngineState == 2) {
                this.mFullCrashVelLimit = -10240000;
            } else {
                this.mFullCrashVelLimit = ((Tuner.CART_ATTRIBUTES[this.mSelectedCart][5] << 10) * 100) / 100;
            }
        }
        init();
    }

    private void changeState(int i) {
        switch (i) {
            case 2:
                this.mOnAirCycleCnt = 0;
                this.mStateCycleCnt = 0;
                this.mTrailState = 0;
                break;
        }
        this.mState = i;
    }

    public static void drawCannonLowEnd(Graphics graphics, int i, int i2) {
        colors = new int[]{54473, 64495, 64495, 32886, 32886, Statics.TRACK_COUNTRY_1_COLOR_3, Statics.TRACK_COUNTRY_1_COLOR_3, Statics.TRACK_COUNTRY_1_COLOR_4, 64495, 64495, 54473};
        CANNON_LOW_LENGTH = TextIDs.TID_HELP_CARTS_CONTENT;
        CANNON_OFFY = 5;
        CANNON_TIP_H = 20;
        CANNON_OFFX = -((CANNON_LOW_LENGTH * 3) / 10);
        int i3 = (GameEngine.smTrain.mCannonAngle * 56) / 100;
        int i4 = CANNON_OFFX + i + 15;
        int i5 = CANNON_OFFY + i2;
        int iCos = (((CANNON_LOW_LENGTH * 2) + ((CANNON_LOW_LENGTH * Util.cos(i3)) >> 12)) / 3) + i4;
        int iSin = (CANNON_OFFY + i5) - ((Util.sin(i3) * CANNON_LOW_LENGTH) >> 12);
        for (int i6 = 0; i6 < colors.length; i6++) {
            graphics.setColor(colors[i6]);
            graphics.drawLine(i4, i5 + i6, iCos, iSin + i6);
        }
        int i7 = CANNON_TIP_H;
        int i8 = i7 / 2;
        graphics.setColor(5263440);
        graphics.fillRoundRect(i4 - (i8 / 2), i5 - (i7 / 3), i8, i7, i8 / 4, i7 / 4);
    }

    public static final int getAngle(int i, int i2, int i3) {
        int length = Util.getLength(i, i2);
        if (length < FP.toFP(5, 10)) {
            return i3;
        }
        int i4 = FP.toInt(Util.getAngle(FP.div(i, length), FP.div(i2, length), false) * Util.SIN_SAMPLES_DEFAULT) + 270;
        return i4 > 360 ? i4 - 360 : i4;
    }

    private final int getInterpolatedY(int i, int i2) {
        int i3 = TrackModel.smTrackPointY[i];
        int i4 = TrackModel.smTrackPointX[i];
        int i5 = TrackModel.smTrackPointY[i + 1];
        int i6 = (TrackModel.smTrackPointX[i + 1] - i4) >> 6;
        if (i6 >= 64) {
            return i3 + ((((i2 - i4) / i6) * (i5 - i3)) >> 6);
        }
        if (i6 == 0) {
            return -16777216;
        }
        return Graphics3D.PRIMITVE_POINTS;
    }

    private int getSpeedToScoreScale() {
        return 3;
    }

    private void initFlyingCar(int i, boolean z) {
        this.mEngine2D.ingameInitFlyingCar(getCarX(i), getCarY(i), this.mSegmentIdx[i], this.mPassengers[i], z, i, this.mIsGhost);
    }

    private void initFlyingCart(int i, boolean z) {
        this.mEngine2D.ingameInitFlyingCar(getCarX(i), getCarY(i), this.mSegmentIdx[i], this.mPassengers[i], z, i, this.mIsGhost);
    }

    private final void initNewSegment() {
        int i = this.mSegmentIdx[0];
        int i2 = TrackModel.smTrackPointX[i + 1] - TrackModel.smTrackPointX[i];
        int i3 = TrackModel.smTrackPointY[i + 1] - TrackModel.smTrackPointY[i];
        int length = Util.getLength(i2, i3);
        this.mSegmentNrmlX = FP.div(i3, length);
        this.mSegmentNrmlY = FP.div(-i2, length);
    }

    public static final int interpolateAngle(int i, int i2, int i3) {
        int i4;
        int i5;
        int i6 = 4096 - i3;
        if (Math.abs(i2 - i) <= 180) {
            i4 = i2;
            i5 = i;
        } else if (i2 > i) {
            i4 = i2 - 360;
            i5 = i;
        } else {
            i5 = i - 360;
            i4 = i2;
        }
        return ((i6 * i4) + (i5 * i3)) >> 12;
    }

    private void speedlineAddSegment(int i, int i2, int i3) {
        if (this.mIsGhost) {
            return;
        }
        if (this.mSpeedlineAngle[this.mSpeedlineSegmentStart] != i3) {
            this.mSpeedlineSegmentStart = (this.mSpeedlineSegmentStart + 1) % TextIDs.TID_SELECTION_ROLLER_TM1_1;
            this.mSpeedlineAngle[this.mSpeedlineSegmentStart] = i3;
        }
        this.mSpeedlineXFP[this.mSpeedlineSegmentStart] = i;
        this.mSpeedlineYFP[this.mSpeedlineSegmentStart] = i2;
    }

    private void speedlineLoad() {
        if (this.mIsGhost) {
            return;
        }
        if (this.mSpeedlineXFP == null) {
            this.mSpeedlineXFP = new int[TextIDs.TID_SELECTION_ROLLER_TM1_1];
            this.mSpeedlineYFP = new int[TextIDs.TID_SELECTION_ROLLER_TM1_1];
            this.mSpeedlineAngle = new int[TextIDs.TID_SELECTION_ROLLER_TM1_1];
        }
        this.mSpeedlineSegmentStart = 0;
        this.mSpeedlineState = 1;
    }

    private void speedlineUpdate() {
        if (this.mIsGhost || this.mCarCnt <= 0 || this.mSpeedlineState == 0) {
            return;
        }
        int iSin = Util.sin(this.mCarAngle[0]);
        int iCos = Util.cos(this.mCarAngle[0]);
        int carX = (getCarX(0) - (iCos * 18)) - (iSin * 10);
        int carY = ((iSin * 18) + getCarY(0)) - (iCos * 10);
        this.mSpeedlineHeight = 300;
        speedlineAddSegment(carX, carY, this.mCarAngle[0]);
    }

    private void trailAddSegment(int i, int i2, int i3) {
        if (this.mIsGhost) {
            return;
        }
        if (this.mTrailAngle[this.mTrailSegmentStart] != i3) {
            this.mTrailSegmentStart = (this.mTrailSegmentStart + 1) % TextIDs.TID_SELECTION_ROLLER_TM1_1;
            this.mTrailAngle[this.mTrailSegmentStart] = i3;
        }
        this.mTrailXFP[this.mTrailSegmentStart] = i;
        this.mTrailYFP[this.mTrailSegmentStart] = i2;
    }

    private void trailLoad() {
        if (this.mIsGhost) {
            return;
        }
        if (this.mTrailXFP == null) {
            this.mTrailXFP = new int[TextIDs.TID_SELECTION_ROLLER_TM1_1];
            this.mTrailYFP = new int[TextIDs.TID_SELECTION_ROLLER_TM1_1];
            this.mTrailAngle = new int[TextIDs.TID_SELECTION_ROLLER_TM1_1];
        }
        this.mTrailSegmentStart = 0;
        this.mTrailState = 0;
    }

    private void trailUpdate() {
        if (this.mIsGhost || this.mCarCnt <= 0) {
            return;
        }
        for (int i = 0; i < this.mCarCnt; i++) {
            int iSin = Util.sin(this.mCarAngle[i]);
            int iCos = Util.cos(this.mCarAngle[i]);
            this.mEmphasizeXFP[i] = (getCarX(i) - (iCos * 18)) - (iSin * 10);
            this.mEmphasizeYFP[i] = ((iSin * 18) + getCarY(i)) - (iCos * 10);
        }
        int i2 = (this.mSpeed * 30) >> 12;
        if (i2 >= 150) {
            if (i2 > 300) {
                i2 = 300;
            }
            this.mTrailInteriorHeight = (i2 - TextIDs.TID_SELECTION_ROLLER_TM1_1) / 37;
            this.mTrailHeight = this.mTrailInteriorHeight + 10;
        } else {
            this.mTrailHeight = 0;
            if (this.mTrailState == 2) {
                this.mTrailState = 0;
            }
        }
        trailAddSegment(this.mEmphasizeXFP[0], this.mEmphasizeYFP[0], this.mCarAngle[0]);
    }

    private final void updateCamPoint() {
        if (!RollerGameEngine.smTrackMenu) {
            if (hasFailed()) {
                return;
            }
            this.mCamPointX = getCarX(0);
            this.mCamPointY = getCarY(0);
            return;
        }
        this.mCamPointX = TrackModel.getTrackPointX(40);
        this.mCamPointY = TrackModel.getTrackPointY(40);
        if (getCarX(0) > TrackModel.getTrackPointX(this.mCartResetTarget)) {
            init();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:155:0x037a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void updateImpl() {
        /*
            Method dump skipped, instructions count: 894
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.digitalchocolate.androidrollergapp.Train.updateImpl():void");
    }

    private final void updateOnAir() {
        int i;
        int i2 = this.mSegmentIdx[0];
        int i3 = ((((this.mCarX[0] - TrackModel.smTrackPointX[i2]) >> 4) * this.mSegmentNrmlX) >> 12) + ((((this.mCarY[0] - TrackModel.smTrackPointY[i2]) >> 4) * this.mSegmentNrmlY) >> 12);
        this.mTrackContact = this.mPickUpsEnabled[3] || i3 <= 0;
        if (this.mTrackContact) {
            if (this.mState != 1 && TrackModel.isHole(this, i2)) {
                this.mPuffEnabled = false;
                dropTrain(true);
                this.mTrackContact = false;
            } else if (this.mCarCnt > 1) {
                int i4 = 0;
                for (int i5 = this.mCarCnt - 1; i5 > 0 && TrackModel.isHole(this, this.mSegmentIdx[i5]) && TrackModel.isFall(this, this.mSegmentIdx[i5]); i5--) {
                    dropCart(i5, true);
                    if (!this.mIsGhost) {
                        i4++;
                    }
                }
            }
            if (this.mOnAirCycleCnt * 20 > 500) {
                this.mTouchDown = true;
                this.mLandingSparkTimer = 300;
                if (this.mParticlesJumpEnabled) {
                    this.mParticlesJumpEnabled = false;
                }
                if (this.mPickUpsEnabled[5]) {
                    this.mAccelerateAllowed = true;
                    this.mBrakeAllowed = true;
                    this.mGravity = 224;
                }
                if (this.mPickUpsEnabled[6]) {
                    convoyPickUpDisable(6);
                }
            }
            this.mOnAirCycleCnt = 0;
        } else {
            if (i3 > 2000) {
                this.mOnAirCycleCnt++;
            }
            if (this.mSegmentNrmlX <= 0 && ((this.mSegmentNrmlY >= 0 || (this.mSegmentNrmlY < 0 && this.mCarY[0] > TrackModel.smTrackPointY[i2])) && this.mOnAirCycleCnt * 20 > 300)) {
                this.mPuffEnabled = false;
                dropTrain(true);
            }
        }
        this.mIsBraking = false;
        this.mIsAccelerating = false;
        this.mOnAir = i3 > TIME_LIGHTS_START || this.mPickUpsEnabled[6];
        if (this.mOnAir) {
            this.mCarAngle[0] = interpolateAngle(getAngle(this.mVelX, this.mVelY, this.mCarAngle[0]), this.mCarAngle[0], 256);
            return;
        }
        if (this.mAccelerate) {
            i = this.mSegmentNrmlX < UP_HILL_NRML_X_LIMIT ? this.mAccelerationUphill : this.mAccelerationNormal;
            this.mIsAccelerating = true;
        } else if (!this.mBrake || this.mSegmentNrmlY >= 0) {
            i = 0;
        } else {
            i = -this.mBrakingMag;
            if (this.mState == 0) {
                this.mBrakeNeeded = true;
            }
            this.mIsBraking = true;
        }
        if (((-this.mSegmentNrmlY) * this.mVelX) + (this.mSegmentNrmlX * this.mVelY) > 0 || this.mSegmentNrmlY >= 0) {
            this.mVelX -= (this.mSegmentNrmlY * i) >> 6;
            this.mVelY += (this.mSegmentNrmlX * i) >> 6;
            updateSpeedVariable();
            if (this.mSpeed < 5120 && i < 0) {
                this.mVelX = 0;
                this.mVelY = 0;
                this.mSpeed = 0;
            }
        } else if (TrackModel.isHole(this, i2)) {
            this.mPuffEnabled = false;
            dropTrain(false);
        } else {
            if (this.mSpeed > (-this.mCrashVelLimit)) {
                while (this.mCarCnt > 0) {
                    this.mCarCnt--;
                    initFlyingCart(this.mCarCnt, true);
                }
                changeState(2);
            }
            this.mVelX = 0;
            this.mVelY = 0;
            this.mSpeed = 0;
            if (i > 0) {
                this.mVelX -= (this.mSegmentNrmlY * i) >> 6;
                this.mVelY += (i * this.mSegmentNrmlX) >> 6;
                updateSpeedVariable();
            }
        }
        this.mCarAngle[0] = interpolateAngle(TrackModel.smTrackSlope[this.mSegmentIdx[0] + 1], this.mCarAngle[0], 1024);
    }

    private final void updateReferencePath() {
        int i = (this.mCarX[0] - this.mRefPathData[this.mRefPathIdx][0]) >> 6;
        int i2 = (this.mCarY[0] - this.mRefPathData[this.mRefPathIdx][1]) >> 6;
        int iSqrtFP = Util.sqrtFP((i * i) + (i2 * i2));
        if (iSqrtFP > CAR_DIST) {
            int i3 = this.mRefPathData[this.mRefPathIdx][0] >> 12;
            int i4 = this.mRefPathData[this.mRefPathIdx][1] >> 12;
            this.mRefPathIdx = (this.mRefPathIdx + 1) % 5;
            int i5 = CAR_DIST / (iSqrtFP >> 12);
            int i6 = 4096 - i5;
            this.mRefPathData[this.mRefPathIdx][0] = (i3 * i6) + ((this.mCarX[0] >> 12) * i5);
            this.mRefPathData[this.mRefPathIdx][1] = (i4 * i6) + (i5 * (this.mCarY[0] >> 12));
            this.mRefPathData[this.mRefPathIdx][2] = this.mSegmentIdx[0];
            this.mRefPathData[this.mRefPathIdx][5] = 0;
            this.mRefPathData[this.mRefPathIdx][3] = this.mOnAir ? getInterpolatedY(this.mSegmentIdx[0], this.mRefPathData[this.mRefPathIdx][0]) : this.mRefPathData[this.mRefPathIdx][1];
            if (this.mRefPathData[this.mRefPathIdx][3] < this.mRefPathData[this.mRefPathIdx][1]) {
                this.mRefPathData[this.mRefPathIdx][3] = this.mRefPathData[this.mRefPathIdx][1];
            }
            iSqrtFP -= CAR_DIST;
        }
        int i7 = iSqrtFP / 25;
        int i8 = 4096 - i7;
        int i9 = this.mRefPathIdx;
        int i10 = this.mRefPathData[i9][0] >> 12;
        int i11 = this.mRefPathData[i9][1] >> 12;
        int i12 = i9;
        int i13 = 1;
        int i14 = i11;
        int i15 = i10;
        while (i13 < this.mCarCnt) {
            i12--;
            if (i12 < 0) {
                i12 = 4;
            }
            int i16 = this.mRefPathData[i12][0] >> 12;
            int i17 = this.mRefPathData[i12][1] >> 12;
            this.mCarX[i13] = (i15 * i7) + (i16 * i8);
            this.mCarY[i13] = (i14 * i7) + (i17 * i8);
            this.mSegmentIdx[i13] = this.mRefPathData[i12][2];
            i13++;
            i14 = i17;
            i15 = i16;
        }
        for (int i18 = 1; i18 < this.mCarCnt; i18++) {
            this.mCarAngle[i18] = getAngle(this.mCarX[i18 - 1] - this.mCarX[i18], this.mCarY[i18 - 1] - this.mCarY[i18], this.mCarAngle[i18]);
        }
        if (this.mSegmentNrmlY <= 0 || !this.mPickUpsEnabled[3]) {
            for (int i19 = 0; i19 < this.mRefPathData.length; i19++) {
                int[] iArr = this.mRefPathData[i19];
                iArr[5] = iArr[5] + (!this.mTrackContact ? this.mGravity >> 1 : this.mGravity);
                int[] iArr2 = this.mRefPathData[i19];
                iArr2[1] = iArr2[1] + this.mRefPathData[i19][5];
                this.mRefPathData[i19][1] = Math.min(this.mRefPathData[i19][3], this.mRefPathData[i19][1]);
            }
        }
    }

    private final void updateSegmentIdx() {
        int i = this.mSegmentIdx[0];
        int i2 = 0;
        while (useNextSegment()) {
            int[] iArr = this.mSegmentIdx;
            iArr[0] = iArr[0] + 1;
            initNewSegment();
            i2++;
        }
        if (i != this.mSegmentIdx[0]) {
            this.mSegmentLastIdx = i;
        }
    }

    private final void updateSpeedVariable() {
        int iAbs = Math.abs(this.mVelX);
        int iAbs2 = Math.abs(this.mVelY);
        if (iAbs <= iAbs2) {
            iAbs2 = iAbs;
            iAbs = iAbs2;
        }
        this.mSpeed = iAbs + ((iAbs2 * 13) >> 5);
        if (this.mIsGhost || this.mSpeed <= GameEngine.smMaximumSpeedOnPreviousTrack) {
            return;
        }
        GameEngine.smMaximumSpeedOnPreviousTrack = this.mSpeed;
    }

    private final boolean useNextSegment() {
        int i = this.mSegmentIdx[0] + 1;
        char c = this.mIsGhost ? (char) 1 : (char) 0;
        if (TrackModel.smTrackPointTimer[c][i] == 0 && TrackModel.isFall(this, i)) {
            TrackModel.smTrackPointTimer[c][i] = Tuner.PARAMS_CONVOY_INTERACTIVE_TRACK_FALLING_TIME;
        }
        int i2 = (TrackModel.smTrackPointX[i] - this.mCarX[0]) >> 2;
        return ((!this.mOnAir || this.mSegmentNrmlY >= 0) ? ((((TrackModel.smTrackPointY[i] - this.mCarY[0]) >> 2) * this.mSegmentNrmlX) >> 12) + (-((i2 * this.mSegmentNrmlY) >> 12)) : i2) < 0;
    }

    public int accumScore(int i, int i2) {
        int speedToScoreScale = getSpeedToScoreScale();
        if (this.mPickUpIndexSpeed == -1) {
            this.mPickUpIndexSpeed = this.mPickUpIndex;
        }
        int i3 = (speedToScoreScale / 50) - 1;
        this.mPickUpsSorted[this.mPickUpIndexSpeed] = i3 + 8 + (i2 * 4);
        if (this.mPickUpIndexSpeed != this.mPickUpIndex) {
            this.mPickUpIndex++;
        }
        this.mPickUpSpeedTimer = 2000;
        Engine2D.smParticlesNumFacesPerPassenger = speedToScoreScale / 50;
        GameEngine.smEngine2d.mScorePrizeTimer = 2000;
        accumScore((speedToScoreScale * i) / 100);
        return i3;
    }

    public void accumScore(int i) {
        this.mPointsTarget += (i * 100) / 100;
    }

    public void accumSmiley(int i) {
        this.mEngine2D.ingameScoreIncreaseNotify(i);
        this.mSmileys += i;
    }

    public void convoyInit() {
        this.mPickUpsEnabled = new boolean[8];
        this.mPickUpsSorted = new int[8];
        this.mPickUpsTime = new int[8];
        this.mPickUpIndex = 0;
        for (int i = 0; i < 8; i++) {
            this.mPickUpsSorted[i] = -1;
        }
        this.mScoreMultiplier = 1;
        this.mPickUpsEnabledCount = 0;
    }

    public void convoyPickUpDisable(int i) {
        if (i != 7 && i != 2) {
            for (int i2 = 0; i2 < 8; i2++) {
                if (this.mPickUpsSorted[i2] == i) {
                    this.mPickUpsSorted[i2] = -1;
                    this.mPickUpIndex--;
                }
            }
            for (int i3 = 0; i3 < 7; i3++) {
                this.mPickUpsSorted[i3] = this.mPickUpsSorted[i3 + 1];
            }
            this.mPickUpsSorted[7] = -1;
            this.mPickUpsEnabledCount--;
        }
        this.mPickUpsEnabled[i] = false;
        switch (i) {
            case 0:
                this.mAccelerationUphill = Tuner.CART_ATTRIBUTES[this.mSelectedCart][0];
                this.mAccelerationNormal = Tuner.CART_ATTRIBUTES[this.mSelectedCart][1];
                this.mFluidFriction = Tuner.CART_ATTRIBUTES[this.mSelectedCart][2];
                this.mTrailState = 0;
                break;
            case 2:
                this.mScoreMultiplier = 1;
                break;
            case 3:
                this.mCarX[0] = TrackModel.smTrackPointX[this.mSegmentIdx[0]];
                this.mCarY[0] = TrackModel.smTrackPointY[this.mSegmentIdx[0]];
                this.mCrashVelLimit = Tuner.CART_ATTRIBUTES[this.mSelectedCart][4] << 10;
                this.mFullCrashVelLimit = Tuner.CART_ATTRIBUTES[this.mSelectedCart][5] << 10;
                break;
            case 4:
                this.mCrashVelLimit = Tuner.CART_ATTRIBUTES[this.mSelectedCart][4] << 10;
                this.mFullCrashVelLimit = Tuner.CART_ATTRIBUTES[this.mSelectedCart][5] << 10;
                break;
            case 5:
            case 6:
                if (i == 5) {
                    this.mGravity = GRAVITY_CONST;
                    break;
                } else {
                    this.mAccelerateAllowed = true;
                    this.mBrakeAllowed = true;
                    this.mFluidFriction = Tuner.CART_ATTRIBUTES[this.mSelectedCart][2];
                    break;
                }
        }
    }

    public void convoyPickUpEnable(int i) {
        if (i != 7 && i != 2 && !this.mPickUpsEnabled[i]) {
            this.mPickUpsSorted[this.mPickUpIndex] = i;
            this.mPickUpIndex++;
            this.mPickUpsEnabledCount++;
        }
        this.mPickUpsEnabled[i] = true;
        this.mPickUpsTime[i] = Tuner.PARAMS_CONVOY_PICKUPS_TIME[i];
        switch (i) {
            case 0:
                this.mAccelerationUphill = (Tuner.CART_ATTRIBUTES[this.mSelectedCart][0] * Tuner.PARAMS_CONVOY_PICKUPS_NITRO_ACC_UPHILL_PERCENT) / 100;
                this.mAccelerationNormal = (Tuner.CART_ATTRIBUTES[this.mSelectedCart][1] * Tuner.PARAMS_CONVOY_PICKUPS_NITRO_ACC_NORMAL_PERCENT) / 100;
                this.mFluidFriction = (Tuner.CART_ATTRIBUTES[this.mSelectedCart][2] * Tuner.PARAMS_CONVOY_PICKUPS_NITRO_FRICTION_PERCENT) / 100;
                this.mTrailState = 1;
                break;
            case 2:
                this.mScoreMultiplier = Tuner.PARAMS_CONVOY_PICKUPS_EXCITER_MULTIPLIER;
                break;
            case 3:
                this.mCrashVelLimit = -10240000;
                this.mFullCrashVelLimit = -10240000;
                break;
            case 4:
                this.mCrashVelLimit = -10240000;
                this.mFullCrashVelLimit = -10240000;
                break;
            case 5:
            case 6:
                if (i == 6) {
                    this.mAccelerateAllowed = false;
                    this.mBrakeAllowed = false;
                    int length = TrackModel.smTrackFlags.length;
                    int i2 = this.mSegmentIdx[0];
                    this.mJumpNextSolidSegmentID = this.mSegmentIdx[0];
                    while (this.mJumpNextSolidSegmentID < length && !TrackModel.isHole(this, this.mJumpNextSolidSegmentID)) {
                        this.mJumpNextSolidSegmentID++;
                    }
                    if (this.mJumpNextSolidSegmentID == length) {
                        convoyPickUpDisable(i);
                    } else {
                        int i3 = this.mJumpNextSolidSegmentID - 1;
                        while (this.mJumpNextSolidSegmentID < length && TrackModel.isHole(this, this.mJumpNextSolidSegmentID)) {
                            this.mJumpNextSolidSegmentID++;
                        }
                        if (this.mJumpNextSolidSegmentID == length) {
                            convoyPickUpDisable(i);
                        } else {
                            int trackPointY = (TrackModel.getTrackPointY(this.mJumpNextSolidSegmentID) - TrackModel.getTrackPointY(i3)) - JUMP_IMPULSE_OFFSET_Y_FP;
                            if (i == 6) {
                                trackPointY -= (Tuner.PARAMS_CONVOY_INTERACTIVE_SPRINGS_IMPULSEY * JUMP_IMPULSE_OFFSET_Y_FP) / 100;
                            }
                            this.mVelY = FP.div(trackPointY - FP.mul(224, JUMP_IMPULSE_TIME_SQUARED_FP), JUMP_IMPULSE_TIME_FP);
                            if (i == 5) {
                                this.mVelX = FP.div((TrackModel.smTrackPointX[this.mJumpNextSolidSegmentID] - TrackModel.smTrackPointX[i2]) + JUMP_IMPULSE_OFFSET_Y_FP, JUMP_IMPULSE_TIME_FP);
                            } else {
                                accumScore(409600 * this.mCarCnt);
                            }
                        }
                    }
                    this.mFluidFriction = 0;
                } else {
                    this.mGravity = 224;
                }
                this.mParticlesJumpEnabled = true;
                break;
        }
    }

    public void convoyRelease() {
        this.mPickUpsEnabled = null;
        this.mPickUpsTime = null;
    }

    public void convoyUpdate(int i) {
        for (int i2 = 0; i2 < 8; i2++) {
            if (this.mPickUpsEnabled[i2] && CONVOY_PICKUPS_TIME_DRIVEN[i2]) {
                int[] iArr = this.mPickUpsTime;
                iArr[i2] = iArr[i2] - i;
                if (this.mPickUpsTime[i2] <= 0) {
                    convoyPickUpDisable(i2);
                }
            }
        }
    }

    public void dropCart(int i, boolean z) {
        this.mCarCnt--;
        initFlyingCart(i, z);
        if (this.mCarCnt == 0) {
            dropTrain(z);
        }
    }

    public void dropTrain(boolean z) {
        this.mState = 1;
        this.mStateCycleCnt = 0;
        while (this.mCarCnt > 0) {
            dropCart(this.mCarCnt - 1, z);
        }
    }

    public final boolean exitMe() {
        if (trackEnded()) {
            return true;
        }
        return (this.mState == 0 || this.mState == 4 || this.mStateCycleCnt * 20 <= CRASH_SHOW_TIME) ? false : true;
    }

    public final boolean fullCrash() {
        return hasFailed() && this.mStateCycleCnt * 20 > CRASH_SHOW_TIME;
    }

    public int getAveragedDeltaMovementX() {
        return ((this.mFirstCarPrevX - this.mCarX[0]) * 2) / 3;
    }

    public int getAveragedDeltaMovementY() {
        return ((this.mFirstCarPrevY - this.mCarY[0]) * 2) / 3;
    }

    public int getCarX(int i) {
        return (this.mCarX[i] * 3) / 2;
    }

    public int getCarY(int i) {
        return (this.mCarY[i] * 3) / 2;
    }

    public final int getCurrentMultiplier() {
        return this.mScoreMultiplier;
    }

    public final int getDeltaPoints() {
        return this.mPoints - this.mPrevPoints;
    }

    public final int getNewFallenCarCount() {
        return this.mCarCntPrev - this.mCarCnt;
    }

    public final int getScore() {
        return this.mPoints >> 12;
    }

    public final int getSmilyes() {
        return this.mSmileys;
    }

    public final boolean hasFailed() {
        return this.mState == 1 || this.mState == 2;
    }

    public void init() {
        int i = TrackModel.smTrackPointY[8];
        this.mLastSegment = TrackModel.smTrackPointCount - 8;
        this.mState = 0;
        this.mJumpDeltaX = 122880;
        this.mStateCycleCnt = 0;
        this.mBrakeNeeded = false;
        this.mGameTime = 0;
        this.mUpdateTime = 0;
        this.mVelX = 0;
        this.mVelY = 0;
        this.mSegmentNrmlX = 0;
        this.mSegmentNrmlY = -4096;
        this.mSpeed = 0;
        this.mTrackContact = true;
        this.mOnAir = false;
        this.mOnAirCycleCnt = 0;
        this.mTouchDown = false;
        this.mUplift = 0;
        for (int i2 = 0; i2 < this.mCarCnt; i2++) {
            this.mCarX[i2] = ((-i2) * CAR_DIST) + TrackModel.smTrackPointX[8];
            this.mCarY[i2] = i;
            this.mCarAngle[i2] = 0;
            this.mSegmentIdx[i2] = 8;
        }
        this.mSegmentLastIdx = -1;
        this.mLastNotOnAirX = this.mCarX[0];
        this.mCartResetTarget = Util.rnd(50) + 50;
        updateCamPoint();
        for (int i3 = 0; i3 < this.mCarCnt; i3++) {
            this.mSegmentIdx[i3] = 0;
        }
        int length = this.mRefPathData.length;
        for (int i4 = 0; i4 < length; i4++) {
            this.mRefPathData[i4][0] = (((i4 - length) + 1) * CAR_DIST) + TrackModel.smTrackPointX[8];
            this.mRefPathData[i4][1] = i;
            this.mRefPathData[i4][2] = 0;
            this.mRefPathData[i4][3] = i;
            this.mRefPathData[i4][5] = 0;
        }
        this.mRefPathIdx = length - 1;
        this.mPoints = DCThrillOld.smLatestTrackScore;
        this.mSmileys = 0;
        convoyInit();
        this.mParticlesJumpEnabled = false;
        this.mParticlesJumpTimer = -1;
        this.mScoreWaterGiven = false;
        trailLoad();
        this.mGravity = GRAVITY_CONST;
        this.mPuffEnabled = true;
    }

    public boolean isUserBraking() {
        return (!this.mIsBraking || this.mState == 4 || trackEnded()) ? false : true;
    }

    public final boolean lastMultiplierWasAir() {
        return this.mLastMultiplierIncrease == 1;
    }

    public final boolean lastMultiplierWasSpeed() {
        return this.mLastMultiplierIncrease == 0;
    }

    public final void lockPoints() {
        this.mPoints = this.mPointsTarget;
    }

    public final boolean singleCrash() {
        return this.mSingleCarCrashedCycle != 0 && (this.mStateCycleCnt - this.mSingleCarCrashedCycle) * 20 > 500;
    }

    public void speedlineDraw(Graphics graphics, int i, int i2) {
        int i3;
        int i4;
        if (this.mIsGhost || this.mSpeedlineHeight <= 0) {
            return;
        }
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i7 < 150 && i6 > -10) {
            int i8 = this.mSpeedlineSegmentStart - i7;
            int i9 = i8 < 0 ? i8 + TextIDs.TID_SELECTION_ROLLER_TM1_1 : i8 % TextIDs.TID_SELECTION_ROLLER_TM1_1;
            int i10 = ((this.mSpeedlineXFP[i9] - this.mCamPointX) >> 12) + i;
            int i11 = ((this.mSpeedlineYFP[i9] - this.mCamPointY) >> 12) + i2;
            int iSin = Util.sin(this.mSpeedlineAngle[i9]);
            int iCos = Util.cos(this.mSpeedlineAngle[i9]);
            if (i7 == 0) {
                i5 = i11;
                i6 = i10;
            }
            if (this.mEngine2D.debugDrawOptimizationMode != this.mEngine2D.DEBUG_OPT_ORIGINAL) {
            }
            for (int i12 = 0; i12 < 2; i12++) {
                for (int i13 = 0; i13 < 12; i13++) {
                    int i14 = (iSin * i13) >> 12;
                    int i15 = (iCos * i13) >> 12;
                    if (i12 == 0) {
                        int i16 = i15 * (-1);
                        i3 = i14 * (-1);
                        i4 = i16;
                    } else {
                        i3 = i14;
                        i4 = i15;
                    }
                    if (i13 == 12 - 1) {
                        graphics.setColor(COLOR_SPEEDLINE_BORDER_OUT);
                    } else if (i13 == 12 - 2) {
                        graphics.setColor(COLOR_SPEEDLINE_BORDER_IN);
                    } else {
                        graphics.setColor(COLOR_SPEEDLINE);
                    }
                    int i17 = i10 + i3;
                    int i18 = i11 + i4;
                    int i19 = i3 + i6;
                    int i20 = i4 + i5;
                    graphics.drawLine(i17, i18, i19, i20);
                    if (1 != 0) {
                        graphics.drawLine(i17, i18 + 1, i19, i20 + 1);
                    }
                }
            }
            i7++;
            i5 = i11;
            i6 = i10;
        }
    }

    public final boolean trackEnded() {
        return this.mState == 3;
    }

    public void trailDraw(Graphics graphics, int i, int i2) {
        int i3;
        int i4;
        if (this.mIsGhost) {
            return;
        }
        if (this.mTrailHeight > 0) {
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            while (i7 < 150 && i6 > -10) {
                int i8 = this.mTrailSegmentStart - i7;
                int i9 = i8 < 0 ? i8 + TextIDs.TID_SELECTION_ROLLER_TM1_1 : i8 % TextIDs.TID_SELECTION_ROLLER_TM1_1;
                int i10 = ((this.mTrailXFP[i9] - this.mCamPointX) >> 12) + i;
                int i11 = ((this.mTrailYFP[i9] - this.mCamPointY) >> 12) + i2;
                int iSin = Util.sin(this.mTrailAngle[i9]);
                int iCos = Util.cos(this.mTrailAngle[i9]);
                if (i7 == 0) {
                    i5 = i11;
                    i6 = i10;
                }
                if (this.mEngine2D.debugDrawOptimizationMode != this.mEngine2D.DEBUG_OPT_ORIGINAL) {
                }
                int i12 = this.mTrailInteriorHeight + 5;
                for (int i13 = 0; i13 < 2; i13++) {
                    for (int i14 = 0; i14 < i12; i14++) {
                        int i15 = (iSin * i14) >> 12;
                        int i16 = (iCos * i14) >> 12;
                        if (i13 == 0) {
                            int i17 = i16 * (-1);
                            i3 = i15 * (-1);
                            i4 = i17;
                        } else {
                            i3 = i15;
                            i4 = i16;
                        }
                        if (i14 < this.mTrailInteriorHeight) {
                            graphics.setColor(16777215);
                        } else {
                            graphics.setColor(TRAIL_COLOR_MARGIN[i14 - this.mTrailInteriorHeight]);
                        }
                        int i18 = i10 + i3;
                        int i19 = i11 + i4;
                        int i20 = i3 + i6;
                        int i21 = i4 + i5;
                        graphics.drawLine(i18, i19, i20, i21);
                        if (1 != 0) {
                            graphics.drawLine(i18, i19 + 1, i20, i21 + 1);
                        }
                    }
                }
                i7++;
                i5 = i11;
                i6 = i10;
            }
        }
        for (int i22 = 0; i22 < this.mCarCnt; i22++) {
            int i23 = ((this.mEmphasizeXFP[i22] - this.mCamPointX) >> 12) + i;
            int i24 = ((this.mEmphasizeYFP[i22] - this.mCamPointY) >> 12) + i2;
            Engine2D.smEmphasizeSO.setAnimation(0, -1, false);
            Engine2D.smEmphasizeSO.logicUpdate(this.mEngine2D.mIngameTimer);
            Engine2D.smEmphasizeSO.draw(graphics, i23, i24);
        }
    }

    public void update(int i) {
        this.mGameTime += i;
        this.mCarCntPrev = this.mCarCnt;
        this.mOnAirCycleCntPrev = this.mOnAirCycleCnt;
        this.mFirstCarPrevX = this.mCarX[0];
        this.mFirstCarPrevY = this.mCarY[0];
        Engine2D.smFallingTrackUpdateCount = 0;
        while (this.mGameTime > this.mUpdateTime) {
            if (!this.mIsGhost) {
                GameEngine.updateInput();
            }
            if (this.mState == 4) {
                this.mSpeed = 0;
                this.mStoppedTimer -= 20;
                boolean z = (this.mIsGhost && GameEngine.getAccelerateGhost()) || (!this.mIsGhost && GameEngine.getAccelerate());
                if (!z && this.mCannonDirection != 0) {
                    this.mCannonLaunchCondition = true;
                }
                if (z && this.mCannonLaunchCondition) {
                    this.mState = 0;
                    this.mStateCycleCnt = 0;
                    this.mBrakingMag = Tuner.CART_ATTRIBUTES[this.mSelectedCart][3];
                    Engine2D.breakGoUsed(this.mIsGhost ? 1 : 0);
                    this.mVelX = Tuner.PARAMS_CONVOY_INTERACTIVE_BREAK_GO_SPEEDX;
                    this.mVelY = ((-Tuner.PARAMS_CONVOY_INTERACTIVE_BREAK_GO_SPEEDX) * this.mCarAngle[0]) / 45;
                    this.mTrailState = 2;
                    GameEngine.smEngine2d.mIngameCrashPuff.setAnimationFrame(0);
                    GameEngine.smEngine2d.mIngameCrashCoordX = getCarX(0);
                    GameEngine.smEngine2d.mIngameCrashCoordY = getCarY(0);
                    GameEngine.smEngine2d.mIngameDrawCrashPuff = true;
                } else {
                    this.mAccelerate = false;
                    this.mBrake = true;
                    if (!this.mIsGhost) {
                        Engine2D.smIngameBreakGoSO.logicUpdate(20);
                    }
                    if (this.mStoppedTimer < 0) {
                        if (this.mCannonDirection == 0) {
                            this.mCannonDirection = 1;
                        } else {
                            this.mCannonDirection = -this.mCannonDirection;
                        }
                        this.mStoppedTimer = 600;
                    }
                    int i2 = 600 - this.mStoppedTimer;
                    if (this.mCannonDirection < 0) {
                        i2 = this.mStoppedTimer;
                    }
                    this.mCannonAngle = 0;
                    if (this.mCannonDirection != 0) {
                        this.mCannonAngle = (i2 * 45) / 600;
                        for (int i3 = 0; i3 < this.mCarCnt; i3++) {
                            this.mCarAngle[i3] = (this.mCannonAngle * 8) / 10;
                            this.mCarY[i3] = this.mCannonCarY - ((Util.sin(this.mCannonAngle) * (this.mCarCnt - i3)) * 15);
                        }
                        if (!this.mIsGhost) {
                            Engine2D.smIngameBreakGoSO.setAnimationFrame((this.mCannonAngle / 10) + 3);
                        }
                    }
                }
            } else if (this.mIsGhost) {
                this.mAccelerate = GameEngine.getAccelerateGhost();
                this.mBrake = GameEngine.getBrakeGhost();
            } else {
                this.mAccelerate = this.mAccelerateAllowed && GameEngine.getAccelerate();
                this.mBrake = this.mBrakeAllowed && GameEngine.getBrake();
            }
            this.mEmitSparks = (this.mIsBraking && this.mSpeed > 4096) || this.mLandingSparkTimer > 0;
            if (this.mState != 4) {
                updateImpl();
            }
            updateCamPoint();
            if (!this.mIsGhost) {
                this.mEngine2D.ingameUpdateSpeedLines(20);
            }
            this.mUpdateTime += 20;
            this.mLandingSparkTimer -= 20;
            if (this.mParticlesJumpEnabled || this.mParticlesJumpTimer >= 0) {
                this.mParticlesJumpSpawnTimer -= i;
                if (this.mParticlesJumpTimer >= 0) {
                    this.mParticlesJumpTimer -= i;
                }
                if (this.mParticlesJumpSpawnTimer < 0 && this.mCarCnt > 0) {
                    this.mParticlesJumpSpawnTimer = 200;
                    int i4 = this.mCarCnt - 1;
                    Engine2D.particlesAddParticle(1, this.mCarX[i4] - FP.mul(Util.cos(this.mCarAngle[i4]), 81920), FP.mul(Util.sin(this.mCarAngle[i4]), 81920) + this.mCarY[i4], 0, 4);
                }
            }
            trailUpdate();
        }
    }

    public final void updatePoints() {
        if (DCThrillOld.replayIsRequested() || Game.smCurrentState == 58) {
            return;
        }
        if (!this.mIsGhost && this.mGForceFiltered > GameEngine.smMaximumGForceOnPreviousTrack) {
            GameEngine.smMaximumGForceOnPreviousTrack = this.mGForceFiltered;
        }
        if (this.mMultiplierExpireTimer > 0) {
            this.mMultiplierExpireTimer -= 20;
            if (this.mMultiplierExpireTimer <= 0) {
                this.mCurrentSpeedMultiPlier = 1;
                this.mCurrentAirTimeMultiPlier = 1;
            }
        }
        if (this.mPointsUnreportedPenalty > 0) {
            accumScore(-this.mPointsUnreportedPenalty);
            this.mPointsUnreportedPenalty = 0;
            this.mPointIncreaseLocked = true;
            this.mCurrentSpeedMultiPlier = 1;
            this.mCurrentAirTimeMultiPlier = 1;
            if (this.mPointsTarget < 0) {
                this.mPointsTarget = 0;
            }
        } else {
            if (this.mSpeed >= Tuner.SPEED_MULTIPLIER_INCREASE_LIMIT) {
                if (!this.mSpeedMultiplierLocked) {
                    this.mCurrentSpeedMultiPlier++;
                    this.mMultiplierExpireTimer = 5000;
                    this.mSpeedMultiplierLocked = true;
                    this.mLastMultiplierIncrease = 0;
                }
            } else if (this.mSpeed <= Tuner.SPEED_MULTIPLIER_INCREASE_UNLOCK_LOW_LIMIT) {
                this.mSpeedMultiplierLocked = false;
            }
            if (this.mTrackContact || this.mOnAirCycleCnt * 20 < 2000 || this.mSpeed <= 2048) {
                this.mAirTimeMultiplierLocked = false;
            } else if (!this.mAirTimeMultiplierLocked) {
                this.mCurrentAirTimeMultiPlier++;
                this.mMultiplierExpireTimer = 5000;
                this.mAirTimeMultiplierLocked = true;
                this.mLastMultiplierIncrease = 1;
            }
            if (this.mGForceFiltered <= 500) {
                this.mPointIncreaseLocked = false;
            } else if (!this.mPointIncreaseLocked) {
                accumScore(this.mGForceFiltered * Tuner.PARAMS_SCORE_G_FORCE_MULTIPLIER * this.mPassengerCnt * getCurrentMultiplier());
            }
            if (this.mOnAirCycleCnt > 0) {
                accumScore((((Tuner.PARAMS_SCORE_AIR_POINTS_PER_MEASSURE << 12) * 20) / Tuner.PARAMS_SCORE_AIR_MEASSURE) * this.mPassengerCnt * getCurrentMultiplier());
            }
        }
        if (this.mPointsTarget != this.mPoints) {
            int i = this.mPointsTarget - this.mPoints;
            int iMax = Math.max(1, Math.abs(i) >> 2);
            if (i > 0) {
                this.mPoints += iMax;
            } else {
                this.mPoints -= iMax;
            }
        }
    }
}
