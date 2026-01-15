package javax.microedition.m3g;

/* loaded from: classes.dex */
public class Group extends Node {
    public void addChild(Node node) {
    }

    public Node getChild(int i) {
        return null;
    }

    public native int getChildCount();

    public boolean pick(int i, float f, float f2, float f3, float f4, float f5, float f6, RayIntersection rayIntersection) {
        return false;
    }

    public boolean pick(int i, float f, float f2, Camera camera, RayIntersection rayIntersection) {
        return false;
    }

    public native void removeChild(Node node);
}
