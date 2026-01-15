package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public class Cheats {
    public static int smCartCount;
    private static int smEventToReturn;
    public static boolean CHEAT_FINISH_TRACK_ENABLED = true;
    public static boolean CHEAT_CART_COUNT_ENABLED = true;
    public static boolean CHEAT_CHEATS_MENU_ENABLED = true;
    private static int CHEAT_CART_COUNT_MIN = 2;
    private static int CHEAT_CART_COUNT_MAX = 4;
    public static boolean smCheatsEnabled = false;
    public static boolean smInteractiveFallingDownOn = false;
    public static boolean smTornadoOn = false;
    public static boolean smCheatsInvincible = false;

    public static void enableCheats() {
        smCheatsEnabled = true;
    }

    public static void keyEventOccurred(int i) {
        if (smCheatsEnabled) {
            if (CHEAT_FINISH_TRACK_ENABLED) {
                Profile profile = Game.smProfile[Game.smNumProfile];
                if ((i & 32) != 0) {
                    GameEngine.smTrain.accumSmiley(ResultsTable.TOP_SCORE_VALUES[DCThrillOld.smLevelIdx]);
                    ResultsCareerSimple.smGetHappy = true;
                    ResultsCareerSimple.smGetSpeed = true;
                    ResultsCareerSimple.smGetJump = true;
                    smEventToReturn = 2;
                } else if ((i & 64) != 0) {
                    GameEngine.smTrain.accumSmiley(ResultsTable.TOP_SCORE_VALUES[DCThrillOld.smLevelIdx] / 2);
                    ResultsCareerSimple.smGetHappy = true;
                    ResultsCareerSimple.smGetSpeed = true;
                    smEventToReturn = 2;
                }
            }
            if (CHEAT_CART_COUNT_ENABLED) {
                if (smCartCount < CHEAT_CART_COUNT_MAX && (i & 4) != 0) {
                    smCartCount++;
                    GameEngine.setCarCnt(smCartCount);
                    GameEngine.setPassengerCnt(smCartCount * 2);
                } else if (smCartCount > CHEAT_CART_COUNT_MIN && (i & 8) != 0) {
                    smCartCount--;
                    GameEngine.setCarCnt(smCartCount);
                    GameEngine.setPassengerCnt(smCartCount * 2);
                }
            }
            if (!CHEAT_CHEATS_MENU_ENABLED || (i & 128) == 0) {
                return;
            }
            smEventToReturn = 18;
        }
    }

    public static void reset() {
        smEventToReturn = 0;
        if (CHEAT_CART_COUNT_ENABLED) {
            smCartCount = 2;
        }
    }

    public static int updateGame() {
        return smEventToReturn;
    }
}
