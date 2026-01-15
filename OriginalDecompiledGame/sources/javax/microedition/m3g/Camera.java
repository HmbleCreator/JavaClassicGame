package javax.microedition.m3g;

/* loaded from: classes.dex */
public class Camera extends Node {
    public static final int GENERIC = 48;
    public static final int PARALLEL = 49;
    public static final int PERSPECTIVE = 50;

    public int getProjection(Transform transform) {
        return -1;
    }

    public int getProjection(float[] fArr) {
        return -1;
    }

    public native void setGeneric(Transform transform);

    public native void setParallel(float f, float f2, float f3, float f4);

    public native void setPerspective(float f, float f2, float f3, float f4);
}
