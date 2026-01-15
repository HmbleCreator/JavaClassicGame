package javax.microedition.m3g;

/* loaded from: classes.dex */
public abstract class Node extends Transformable {
    public static final int NONE = 144;
    public static final int ORIGIN = 145;
    public static final int X_AXIS = 146;
    public static final int Y_AXIS = 147;
    public static final int Z_AXIS = 148;

    Node() {
    }

    public final native void align(Node node);

    public native float getAlphaFactor();

    public Node getParent() {
        return null;
    }

    public native int getScope();

    public native boolean getTransformTo(Node node, Transform transform);

    public native boolean isPickingEnabled();

    public native boolean isRenderingEnabled();

    public void setAlignment(Node node, int i, Node node2, int i2) {
    }

    public native void setAlphaFactor(float f);

    public native void setPickingEnable(boolean z);

    public native void setRenderingEnable(boolean z);

    public native void setScope(int i);
}
