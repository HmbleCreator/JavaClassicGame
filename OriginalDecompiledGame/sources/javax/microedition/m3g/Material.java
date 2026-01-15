package javax.microedition.m3g;

/* loaded from: classes.dex */
public class Material extends Object3D {
    public static final int AMBIENT = 1024;
    public static final int DIFFUSE = 2048;
    public static final int EMISSIVE = 4096;
    public static final int SPECULAR = 8192;

    public native int getColor(int i);

    public native float getShininess();

    public native boolean isVertexColorTrackingEnabled();

    public native void setColor(int i, int i2);

    public native void setShininess(float f);

    public native void setVertexColorTrackingEnable(boolean z);
}
