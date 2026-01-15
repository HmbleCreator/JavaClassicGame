package javax.microedition.m3g;

/* loaded from: classes.dex */
public class CompositingMode extends Object3D {
    public static final int ALPHA = 64;
    public static final int ALPHA_ADD = 65;
    public static final int MODULATE = 66;
    public static final int MODULATE_X2 = 67;
    public static final int REPLACE = 68;

    public native float getAlphaThreshold();

    public native int getBlending();

    public native float getDepthOffsetFactor();

    public native float getDepthOffsetUnits();

    public native boolean isAlphaWriteEnabled();

    public native boolean isColorWriteEnabled();

    public native boolean isDepthTestEnabled();

    public native boolean isDepthWriteEnabled();

    public native void setAlphaThreshold(float f);

    public native void setAlphaWriteEnable(boolean z);

    public native void setBlending(int i);

    public native void setColorWriteEnable(boolean z);

    public native void setDepthOffset(float f, float f2);

    public native void setDepthTestEnable(boolean z);

    public native void setDepthWriteEnable(boolean z);
}
