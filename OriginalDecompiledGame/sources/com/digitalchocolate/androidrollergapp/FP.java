package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public final class FP {
    public static final int FIXED_ACCURACY = 12;
    public static final int FIXED_SQRT_SHIFT = 6;
    public static final int FLOAT_FIXED_CONVERSION_OPERANT = 4096;

    public static final int div(int i, int i2) {
        return (int) ((i << 12) / i2);
    }

    public static final float fpToFloat(int i) {
        return i / 4096.0f;
    }

    public static final int mul(int i, int i2) {
        return (int) ((i * i2) >> 12);
    }

    public static final int toFP(float f) {
        return (int) ((4096.0f * f) + (f < 0.0f ? -0.5f : 0.5f));
    }

    public static final int toFP(int i) {
        return i << 12;
    }

    public static final int toFP(int i, int i2) {
        return div(toFP(i), toFP(i2));
    }

    public static final int toInt(int i) {
        return i >> 12;
    }
}
