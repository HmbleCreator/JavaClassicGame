package com.digitalchocolate.androidrollergapp;

import java.util.Random;

/* loaded from: classes.dex */
public final class Util {
    private static final int ARC_SIN_TABLE_SIZE = 128;
    public static final int INT_FALSE = 0;
    public static final int INT_TRUE = 1;
    private static final int RANDOM_COEFF = -1644122275;
    public static final int SIN_MAX_ABS_VALUE = 4096;
    public static final int SIN_SAMPLES_DEFAULT = 360;
    private static int mRnd;
    private static int[] smArcSinTable;
    private static int smSinSampleCount;
    private static short[] smSinTable;
    private static short[] smSqqTable;
    private static final Random smRandom = new Random();
    public static int ARC_SIN_45 = 2896;
    public static int RAD_360 = 25736;
    private static int rnd = 0;
    private static int RND_Cont = (int) ((System.currentTimeMillis() >> 8) & 15);
    private static int[] RND_Data = {440777937, 1385877358, -210086711, 541049956, 838251821};

    public static final void DumpMem(String str) {
    }

    public static String convertNumberToString(int i) {
        String str = i == 0 ? GameScreens.NUMBERS_WIDTH_ONE_DIGIT : "";
        int i2 = 0;
        for (int i3 = i; i3 > 0; i3 /= 10) {
            int i4 = i3 % 10;
            i2++;
            if (i2 == 4) {
                i2 = 1;
                str = Toolkit.getText(297) + str;
            }
            str = i4 + str;
        }
        return str;
    }

    public static final int cos(int i) {
        return sin((smSinSampleCount >> 2) + i);
    }

    public static final int cos(int i, int i2) {
        return sin((smSinSampleCount >> 2) + i, i2);
    }

    public static final float getAngle(float f, float f2) {
        float f3;
        float f4;
        float f5;
        float fFpToFloat;
        if (f >= 0.0f) {
            if (f2 >= 0.0f) {
                f3 = 0.0f;
                f5 = f;
                f4 = f2;
            } else {
                f4 = f;
                f5 = -f2;
                f3 = 0.25f;
            }
        } else if (f2 < 0.0f) {
            f3 = 0.5f;
            f5 = -f;
            f4 = -f2;
        } else {
            f3 = 0.75f;
            f4 = -f;
            f5 = f2;
        }
        if (f5 > FP.fpToFloat(ARC_SIN_45)) {
            int iFpToFloat = (int) (f4 * (128.0f / FP.fpToFloat(ARC_SIN_45)));
            if (iFpToFloat < 0) {
                iFpToFloat = 0;
            }
            if (iFpToFloat >= 128) {
                iFpToFloat = 127;
            }
            fFpToFloat = 0.25f - FP.fpToFloat(smArcSinTable[iFpToFloat]);
        } else {
            int iFpToFloat2 = (int) ((128.0f / FP.fpToFloat(ARC_SIN_45)) * f5);
            if (iFpToFloat2 < 0) {
                iFpToFloat2 = 0;
            }
            if (iFpToFloat2 >= 128) {
                iFpToFloat2 = 127;
            }
            fFpToFloat = FP.fpToFloat(smArcSinTable[iFpToFloat2]);
        }
        return f3 + fFpToFloat;
    }

    public static final int getAngle(int i, int i2, boolean z) {
        int iDiv;
        int iDiv2;
        int i3;
        int fp;
        int fp2;
        if (z) {
            int length = getLength(i, i2);
            if (length == 0) {
                return 0;
            }
            iDiv2 = FP.div(i, length);
            iDiv = FP.div(i2, length);
        } else {
            iDiv = i2;
            iDiv2 = i;
        }
        if (iDiv2 >= 0) {
            if (iDiv >= 0) {
                i3 = iDiv2;
                iDiv2 = iDiv;
                fp = 0;
            } else {
                i3 = -iDiv;
                fp = FP.toFP(25, 100);
            }
        } else if (iDiv < 0) {
            i3 = -iDiv2;
            iDiv2 = -iDiv;
            fp = FP.toFP(5, 10);
        } else {
            iDiv2 = -iDiv2;
            i3 = iDiv;
            fp = FP.toFP(75, 100);
        }
        if (i3 > ARC_SIN_45) {
            int iMul = FP.mul(iDiv2, FP.div(128, ARC_SIN_45));
            if (iMul < 0) {
                iMul = 0;
            }
            if (iMul >= 128) {
                iMul = 127;
            }
            fp2 = FP.toFP(25, 100) - smArcSinTable[iMul];
        } else {
            int iMul2 = FP.mul(i3, FP.div(128, ARC_SIN_45));
            if (iMul2 < 0) {
                iMul2 = 0;
            }
            if (iMul2 >= 128) {
                iMul2 = 127;
            }
            fp2 = smArcSinTable[iMul2];
        }
        return fp + fp2;
    }

    public static final int getLength(int i, int i2) {
        return sqrtFP(FP.mul(i, i) + FP.mul(i2, i2));
    }

    public static final int getLength(int i, int i2, int i3) {
        return sqrtFP(FP.mul(i, i) + FP.mul(i2, i2) + FP.mul(i3, i3));
    }

    public static final int getLengthFast(int i, int i2) {
        int i3 = i2 < 0 ? -i2 : i2;
        int i4 = i < 0 ? -i : i;
        if (i3 < i4) {
            int i5 = i4;
            i4 = i3;
            i3 = i5;
        }
        return i3 + (i4 >> 1);
    }

    public static final int getLengthFast(int i, int i2, int i3) {
        return getLengthFast(getLengthFast(i, i2), i3);
    }

    private static final void initArcSin() {
        smArcSinTable = new int[128];
        for (int i = 0; i < 128; i++) {
            smArcSinTable[i] = FP.div(taylorArcSin(FP.div(FP.mul(ARC_SIN_45, FP.toFP(i)), FP.toFP(128))), RAD_360);
        }
    }

    public static final void initMath() {
        initSinTable();
        initArcSin();
        initSQRTTable();
    }

    public static final void initSQRTTable() {
        smSqqTable = new short[256];
        for (int i = 0; i < 256; i++) {
            int i2 = i << 8;
            int i3 = 0;
            for (int i4 = 256; i4 != 0; i4 >>= 1) {
                i3 ^= i4;
                if (i3 * i3 > i2) {
                    i3 ^= i4;
                }
            }
            smSqqTable[i] = (short) i3;
        }
    }

    public static final void initSinTable() {
        initSinTable(SIN_SAMPLES_DEFAULT);
    }

    public static final void initSinTable(int i) {
        smSinSampleCount = i;
        smSinTable = new short[smSinSampleCount];
        int fp = FP.toFP(62830) / (smSinSampleCount * 10000);
        int i2 = 0;
        int fp2 = FP.toFP(1);
        for (int i3 = 0; i3 < smSinSampleCount / 4; i3++) {
            smSinTable[i3] = (short) i2;
            fp2 -= FP.toInt(i2 * fp);
            i2 += FP.toInt(fp2 * fp);
        }
        for (int i4 = smSinSampleCount / 4; i4 < smSinSampleCount; i4++) {
            if (i4 < smSinSampleCount / 2) {
                smSinTable[i4] = smSinTable[((smSinSampleCount / 4) - 1) - (i4 - (smSinSampleCount / 4))];
            } else {
                smSinTable[i4] = (short) (-smSinTable[i4 - (smSinSampleCount / 2)]);
            }
        }
    }

    public static final int rand() {
        return smRandom.nextInt();
    }

    public static int rnd(int i) {
        int[] iArr = RND_Data;
        int i2 = RND_Cont % 5;
        int i3 = iArr[i2];
        int[] iArr2 = RND_Data;
        int i4 = RND_Cont + 1;
        RND_Cont = i4;
        iArr[i2] = i3 ^ iArr2[i4 % 5];
        if (RND_Cont > 23) {
            RND_Cont = 0;
        }
        rnd = (((RND_Data[RND_Cont % 5] >> RND_Cont) & 255) * i) >> 8;
        return rnd;
    }

    public static int rule3(int i, int i2, int i3) {
        return (i * i3) / i2;
    }

    public static final int sin(int i) {
        int i2 = i % SIN_SAMPLES_DEFAULT;
        if (i2 < 0) {
            i2 += SIN_SAMPLES_DEFAULT;
        }
        return smSinTable[Math.abs((i2 * smSinSampleCount) / SIN_SAMPLES_DEFAULT)];
    }

    public static final int sin(int i, int i2) {
        return FP.toInt(FP.mul(sin(i), FP.toFP(i2)));
    }

    public static final int sqrt(int i) {
        if (i < 0) {
            return 0;
        }
        if (i < 65536) {
            if (i < 256) {
                return smSqqTable[i] >> 4;
            }
            int i2 = i >= 4096 ? i >= 16384 ? smSqqTable[i >> 8] + 1 : (smSqqTable[i >> 6] >> 1) + 1 : i >= 1024 ? (smSqqTable[i >> 4] >> 2) + 1 : (smSqqTable[i >> 2] >> 3) + 1;
            return i2 * i2 > i ? i2 - 1 : i2;
        }
        if (i < 16777216) {
            int i3 = i >= 1048576 ? i >= 4194304 ? smSqqTable[i >> 16] << 4 : smSqqTable[i >> 14] << 3 : i >= 262144 ? smSqqTable[i >> 12] << 2 : smSqqTable[i >> 10] << 1;
            int i4 = ((i / i3) + (i3 + 1)) >> 1;
            return i4 * i4 > i ? i4 - 1 : i4;
        }
        int i5 = i >= 268435456 ? i >= 1073741824 ? smSqqTable[i >> 24] << 8 : smSqqTable[i >> 22] << 7 : i >= 67108864 ? smSqqTable[i >> 20] << 6 : smSqqTable[i >> 18] << 5;
        int i6 = ((i / i5) + (i5 + 1)) >> 1;
        int i7 = ((i / i6) + (i6 + 1)) >> 1;
        return i7 * i7 > i ? i7 - 1 : i7;
    }

    public static final int sqrtFP(int i) {
        return sqrt(i) << 6;
    }

    private static final int taylorArcSin(int i) {
        int iMul = FP.mul(i, i);
        int iMul2 = FP.mul(iMul, i);
        int iMul3 = FP.mul(iMul2, iMul);
        int iMul4 = FP.mul(iMul3, iMul);
        int fp = FP.toFP(1, 6);
        int fp2 = FP.toFP(3, 40);
        int fp3 = FP.toFP(15, 336);
        return FP.mul(fp3, iMul4) + FP.mul(fp, iMul2) + i + FP.mul(fp2, iMul3);
    }
}
