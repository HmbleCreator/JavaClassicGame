package javax.microedition.m3g;

/* loaded from: classes.dex */
public class KeyframeSequence extends Object3D {
    public static final int CONSTANT = 192;
    public static final int LINEAR = 176;
    public static final int LOOP = 193;
    public static final int SLERP = 177;
    public static final int SPLINE = 178;
    public static final int SQUAD = 179;
    public static final int STEP = 180;

    public KeyframeSequence(int i, int i2, int i3) {
    }

    public native int getDuration();

    public native int getRepeatMode();

    public native void setDuration(int i);

    public native void setKeyframe(int i, int i2, float[] fArr);

    public native void setRepeatMode(int i);

    public native void setValidRange(int i, int i2);
}
