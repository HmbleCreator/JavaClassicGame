package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public class MathUtils {
    public static final int ANGLE_ACCURACY = 8;
    public static final int SIN_ACCURACY = 15;
    private static int randSeed;
    private static int[] smSinTable;

    public static final int cos(int i) {
        return sin(i + 16384);
    }

    private static void precalcSinTable() {
        smSinTable = new int[256];
        int i = 0;
        int i2 = 0;
        int i3 = 32768;
        while (i < 64) {
            smSinTable[i] = i2;
            i3 -= (i2 * 804) >> 15;
            i2 += (i3 * 804) >> 15;
            i++;
        }
        smSinTable[i] = 32768;
        int i4 = i + 1;
        while (i4 <= 128) {
            smSinTable[i4] = smSinTable[64 - (i4 - 64)];
            i4++;
        }
        while (i4 < 256) {
            smSinTable[i4] = -smSinTable[i4 - 128];
            i4++;
        }
    }

    public static final int random() {
        int i = (randSeed * 1103515245) + 12345;
        randSeed = i;
        return i;
    }

    public static final int random(int i) {
        return ((random() >>> 15) * i) >>> 17;
    }

    public static final boolean randomBoolean() {
        return random() > 0;
    }

    public static void setSeed(int i) {
        randSeed = i;
    }

    public static final int sin(int i) {
        if (smSinTable == null) {
            precalcSinTable();
        }
        int i2 = i & 255;
        int i3 = i >> 8;
        return (((256 - i2) * smSinTable[i3 & 255]) + (smSinTable[(i3 + 1) & 255] * i2)) >> 8;
    }
}
