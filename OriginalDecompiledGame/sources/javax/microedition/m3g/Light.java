package javax.microedition.m3g;

/* loaded from: classes.dex */
public class Light extends Node {
    public static final int AMBIENT = 128;
    public static final int DIRECTIONAL = 129;
    public static final int OMNI = 130;
    public static final int SPOT = 131;

    public native int getColor();

    public native float getConstantAttenuation();

    public native float getIntensity();

    public native float getLinearAttenuation();

    public native int getMode();

    public native float getQuadraticAttenuation();

    public native float getSpotAngle();

    public native float getSpotExponent();

    public native void setAttenuation(float f, float f2, float f3);

    public native void setColor(int i);

    public native void setIntensity(float f);

    public native void setMode(int i);

    public native void setSpotAngle(float f);

    public native void setSpotExponent(float f);
}
