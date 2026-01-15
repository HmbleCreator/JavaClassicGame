package com.digitalchocolate.androidrollergapp;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
class Tuner {
    public static final int ACHIEVEMENT_AIR_TIME_1_LIMIT = 30000;
    public static final int ACHIEVEMENT_AIR_TIME_2_LIMIT = 120000;
    public static final int ACHIEVEMENT_CRASH_COUNT_1_LIMIT = 1;
    public static final int ACHIEVEMENT_GHOST_BEATEN_1_LIMIT = 5;
    public static final int ACHIEVEMENT_GHOST_BEATEN_2_LIMIT = 10;
    public static final int ACHIEVEMENT_GHOST_BEATEN_3_LIMIT = 33;
    public static final int ACHIEVEMENT_MULTIPLAYER_PLAY_COUNT_LIMIT = 5;
    public static final int ACHIEVEMENT_PLAY_TIME_1_LIMIT = 3600000;
    public static final int ACHIEVEMENT_PLAY_TIME_2_LIMIT = 10800000;
    public static final int ACHIEVEMENT_PLAY_TIME_3_LIMIT = 32400000;
    public static final int ACHIEVEMENT_TOTAL_SCORE_1_LIMIT = 100000;
    public static final int ACHIEVEMENT_TOTAL_SCORE_2_LIMIT = 200000;
    public static final int ACHIEVEMENT_TOTAL_SCORE_3_LIMIT = 1000000;
    public static final int ACHIEVEMENT_TRACKS_COMPLETED_WITH_CAR_1_LIMIT = 11;
    public static final int ACHIEVEMENT_TRACKS_COMPLETED_WITH_CAR_2_LIMIT = 33;
    public static final int ACHIEVEMENT_TRACKS_COMPLETED_WITH_CAR_3_LIMIT = 99;
    public static final int AIR_TIME_MULTIPLIER_LIMIT = 2000;
    public static final boolean ALL_TRACKS_UNLOCKED = false;
    public static final int GHOST_BEATEN_BONUS = 10;
    public static final int G_FORCE_POINTS_LIMIT = 500;
    public static final int LOOP_CRASH_TIME_LIMIT = 300;
    public static final int MULTIPLIER_CAP = 9;
    public static final int MULTIPLIER_EXPIRE_TIME = 5000;
    public static int PARAMS_CONVOY_INTERACTIVE_BREAK_GO_SPEEDX = 0;
    public static int PARAMS_CONVOY_INTERACTIVE_BREAK_GO_TIME = 0;
    public static int PARAMS_CONVOY_INTERACTIVE_SPRINGS_IMPULSEY = 0;
    public static int PARAMS_CONVOY_INTERACTIVE_TRACK_FALLING_TIME = 0;
    public static int PARAMS_CONVOY_PICKUPS_EXCITER_MULTIPLIER = 0;
    public static final int PARAMS_CONVOY_PICKUPS_JUMP_SPEEDX = 22500;
    public static int PARAMS_CONVOY_PICKUPS_NITRO_ACC_NORMAL_PERCENT = 0;
    public static int PARAMS_CONVOY_PICKUPS_NITRO_ACC_UPHILL_PERCENT = 0;
    public static int PARAMS_CONVOY_PICKUPS_NITRO_FRICTION_PERCENT = 0;
    public static int[] PARAMS_CONVOY_PICKUPS_TIME = null;
    public static int PARAMS_SCORE_AIR_MEASSURE = 0;
    public static int PARAMS_SCORE_AIR_POINTS_PER_MEASSURE = 0;
    public static int PARAMS_SCORE_G_FORCE_MULTIPLIER = 0;
    public static int PARAMS_TORNADO_ACCELERATION_X_FP = 0;
    public static int[] PARAMS_TORNADO_MAX_VEL_X_FP = null;
    public static final int PREVIEW_ATTRIBUTE_BAR_VALUES_MAX = 100;
    private static final String RECORD_STORE_NAME_PARAMS = "params";
    public static final int SINGLE_CAR_CRASH_PENALTY = 819200;
    public static final int G_FORCE_TO_POINTS_MULTIPLIER = FP.toFP(10);
    public static final int SPEED_MULTIPLIER_INCREASE_LIMIT = FP.toFP(8);
    public static final int SPEED_MULTIPLIER_INCREASE_UNLOCK_LOW_LIMIT = FP.toFP(7);
    public static final int SPEED_LINE_APPEAR_LIMIT = FP.toFP(6);
    public static final int SPEED_LINE_DISAPPEAR_LIMIT = FP.toFP(3);
    public static final int[][] TRACK_PAR_SCORES = {new int[]{10300, 1900, 2900, 3200, 4300, 6100, 3100, 3700, 2700, 4500, 1380, 1900, 2900, 3200, 4300, 6100, 3100, 3700, 2700, 4500, 1380, 1900, 2900, 3200, 4300, 6100, 3100, 3700, 2700, 4500, 1380, 1900, 2900}, new int[]{2370, 2800, 2550, 4200, 4500, 3700, 3300, 3200, 2700, 5000, 2370, 2800, 2550, 4200, 4500, 3700, 3300, 3200, 2700, 5000, 2370, 2800, 2550, 4200, 4500, 3700, 3300, 3200, 2700, 5000, 2370, 2800, 2550}, new int[]{2700, 4300, 4000, 4700, 4300, 4800, 4100, 5000, 4000, 5000, 2700, 4300, 4000, 4700, 4300, 4800, 4100, 5000, 4000, 5000, 2700, 4300, 4000, 4700, 4300, 4800, 4100, 5000, 4000, 5000, 2700, 4300, 4000}};
    public static final int[][] CART_ATTRIBUTES = {new int[]{10, 5, 12, 8, -19, -25}, new int[]{6, 5, 9, 5, -24, -30}, new int[]{15, 8, 16, 9, -14, -20}};
    public static final int[][] PREVIEW_ATTRIBUTE_BAR_VALUES = {new int[]{40, 60}, new int[]{65, 35}, new int[]{20, 80}};
    public static final int ACHIEVEMENT_DISTANCE_ON_TRACK_1_LIMIT = FP.toFP(1000);
    public static final int ACHIEVEMENT_DISTANCE_ON_TRACK_2_LIMIT = FP.toFP(5000);
    public static final int ACHIEVEMENT_DISTANCE_ON_TRACK_3_LIMIT = FP.toFP(20000);
    public static final int[] SCORE_LIMIT_CART_COUNT = {2000, 4000};
    public static final int[] SCORE_LIMIT_PASSENGER_COUNT = {1000, 3000, 7000};

    Tuner() {
    }

    public static void paramsLoad() {
        PARAMS_CONVOY_PICKUPS_TIME = new int[8];
        PARAMS_TORNADO_MAX_VEL_X_FP = new int[100];
        paramsReset();
    }

    public static void paramsReset() {
        for (int i = 0; i < 8; i++) {
            PARAMS_CONVOY_PICKUPS_TIME[i] = 5000;
        }
        PARAMS_CONVOY_PICKUPS_TIME[4] = 8000;
        PARAMS_CONVOY_PICKUPS_TIME[2] = 2000;
        PARAMS_CONVOY_PICKUPS_TIME[7] = 5000;
        PARAMS_CONVOY_PICKUPS_TIME[5] = 3000;
        PARAMS_CONVOY_PICKUPS_NITRO_ACC_UPHILL_PERCENT = 85;
        PARAMS_CONVOY_PICKUPS_NITRO_ACC_NORMAL_PERCENT = 160;
        PARAMS_CONVOY_PICKUPS_NITRO_FRICTION_PERCENT = 40;
        PARAMS_CONVOY_PICKUPS_EXCITER_MULTIPLIER = 2;
        PARAMS_CONVOY_INTERACTIVE_BREAK_GO_TIME = 2000;
        PARAMS_CONVOY_INTERACTIVE_BREAK_GO_SPEEDX = 45000;
        PARAMS_CONVOY_INTERACTIVE_TRACK_FALLING_TIME = TextIDs.TID_SELECTION_ROLLER_TM1_1;
        PARAMS_CONVOY_INTERACTIVE_SPRINGS_IMPULSEY = 100;
        PARAMS_TORNADO_ACCELERATION_X_FP = 60000;
        for (int i2 = 0; i2 < 100; i2++) {
            PARAMS_TORNADO_MAX_VEL_X_FP[i2] = 280000;
        }
        PARAMS_TORNADO_MAX_VEL_X_FP[10] = 250000;
        PARAMS_TORNADO_MAX_VEL_X_FP[21] = 190000;
        PARAMS_TORNADO_MAX_VEL_X_FP[26] = 300000;
        PARAMS_TORNADO_MAX_VEL_X_FP[32] = 300000;
        PARAMS_SCORE_AIR_POINTS_PER_MEASSURE = 20;
        PARAMS_SCORE_AIR_MEASSURE = 1000;
        PARAMS_SCORE_G_FORCE_MULTIPLIER = 4;
    }

    public static void paramsSave() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        for (int i = 0; i < 8; i++) {
            try {
                dataOutputStream.writeInt(PARAMS_CONVOY_PICKUPS_TIME[i]);
            } catch (IOException e) {
                return;
            }
        }
        dataOutputStream.writeInt(PARAMS_CONVOY_PICKUPS_NITRO_ACC_UPHILL_PERCENT);
        dataOutputStream.writeInt(PARAMS_CONVOY_PICKUPS_NITRO_ACC_NORMAL_PERCENT);
        dataOutputStream.writeInt(PARAMS_CONVOY_PICKUPS_NITRO_FRICTION_PERCENT);
        dataOutputStream.writeInt(PARAMS_CONVOY_PICKUPS_EXCITER_MULTIPLIER);
        dataOutputStream.writeInt(PARAMS_CONVOY_INTERACTIVE_BREAK_GO_TIME);
        dataOutputStream.writeInt(PARAMS_CONVOY_INTERACTIVE_BREAK_GO_SPEEDX);
        dataOutputStream.writeInt(PARAMS_CONVOY_INTERACTIVE_TRACK_FALLING_TIME);
        dataOutputStream.writeInt(PARAMS_CONVOY_INTERACTIVE_SPRINGS_IMPULSEY);
        dataOutputStream.writeInt(PARAMS_TORNADO_ACCELERATION_X_FP);
        for (int i2 = 0; i2 < 100; i2++) {
            dataOutputStream.writeInt(PARAMS_TORNADO_MAX_VEL_X_FP[i2]);
        }
        dataOutputStream.writeInt(PARAMS_SCORE_AIR_POINTS_PER_MEASSURE);
        dataOutputStream.writeInt(PARAMS_SCORE_AIR_MEASSURE);
        dataOutputStream.writeInt(PARAMS_SCORE_G_FORCE_MULTIPLIER);
        dataOutputStream.close();
        Toolkit.writeRecord(RECORD_STORE_NAME_PARAMS, byteArrayOutputStream.toByteArray(), 0);
    }
}
