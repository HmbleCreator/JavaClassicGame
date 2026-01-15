package javax.microedition.m3g;

/* loaded from: classes.dex */
public class PolygonMode extends Object3D {
    public static final int CULL_BACK = 160;
    public static final int CULL_FRONT = 161;
    public static final int CULL_NONE = 162;
    public static final int SHADE_FLAT = 164;
    public static final int SHADE_SMOOTH = 165;
    public static final int WINDING_CCW = 168;
    public static final int WINDING_CW = 169;

    public native int getCulling();

    public native int getShading();

    public native int getWinding();

    public native boolean isTwoSidedLightingEnabled();

    public native void setCulling(int i);

    public native void setLocalCameraLightingEnable(boolean z);

    public native void setPerspectiveCorrectionEnable(boolean z);

    public native void setShading(int i);

    public native void setTwoSidedLightingEnable(boolean z);

    public native void setWinding(int i);
}
