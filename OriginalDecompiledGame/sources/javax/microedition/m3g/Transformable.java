package javax.microedition.m3g;

/* loaded from: classes.dex */
public abstract class Transformable extends Object3D {
    Transformable() {
    }

    public native void getCompositeTransform(Transform transform);

    public native void getOrientation(float[] fArr);

    public native void getScale(float[] fArr);

    public native void getTransform(Transform transform);

    public native void getTranslation(float[] fArr);

    public native void postRotate(float f, float f2, float f3, float f4);

    public native void preRotate(float f, float f2, float f3, float f4);

    public native void scale(float f, float f2, float f3);

    public native void setOrientation(float f, float f2, float f3, float f4);

    public native void setScale(float f, float f2, float f3);

    public native void setTransform(Transform transform);

    public native void setTranslation(float f, float f2, float f3);

    public native void translate(float f, float f2, float f3);
}
