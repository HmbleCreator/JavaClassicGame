package javax.microedition.m3g;

/* loaded from: classes.dex */
public class Transform {
    public Transform() {
    }

    public Transform(Transform transform) {
    }

    public native void get(float[] fArr);

    public native void invert();

    public native void postMultiply(Transform transform);

    public native void postRotate(float f, float f2, float f3, float f4);

    public native void postRotateQuat(float f, float f2, float f3, float f4);

    public native void postScale(float f, float f2, float f3);

    public native void postTranslate(float f, float f2, float f3);

    public void set(Transform transform) {
    }

    public void set(float[] fArr) {
    }

    public native void setIdentity();

    public native void transform(VertexArray vertexArray, float[] fArr, boolean z);

    public void transform(float[] fArr) {
    }

    public native void transpose();
}
