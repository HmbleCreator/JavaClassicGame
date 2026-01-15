package javax.microedition.m3g;

/* loaded from: classes.dex */
public class Fog extends Object3D {
    public static final int EXPONENTIAL = 80;
    public static final int LINEAR = 81;

    public native int getColor();

    public native float getDensity();

    public native float getFarDistance();

    public native int getMode();

    public native float getNearDistance();

    public native void setColor(int i);

    public native void setDensity(float f);

    public native void setLinear(float f, float f2);

    public native void setMode(int i);
}
