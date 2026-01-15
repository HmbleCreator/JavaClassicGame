package javax.microedition.m3g;

/* loaded from: classes.dex */
public class RayIntersection {
    public native float getDistance();

    public Node getIntersected() {
        return null;
    }

    public native float getNormalX();

    public native float getNormalY();

    public native float getNormalZ();

    public native void getRay(float[] fArr);

    public native int getSubmeshIndex();

    public native float getTextureS(int i);

    public native float getTextureT(int i);
}
