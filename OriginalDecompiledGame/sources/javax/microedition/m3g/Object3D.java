package javax.microedition.m3g;

/* loaded from: classes.dex */
public abstract class Object3D {
    Object3D() {
    }

    public void addAnimationTrack(AnimationTrack animationTrack) {
    }

    public final native int animate(int i);

    public final Object3D duplicate() {
        return null;
    }

    public Object3D find(int i) {
        return null;
    }

    public AnimationTrack getAnimationTrack(int i) {
        return null;
    }

    public native int getAnimationTrackCount();

    public int getReferences(Object3D[] object3DArr) {
        return -1;
    }

    public native int getUserID();

    public Object getUserObject() {
        return null;
    }

    public native void removeAnimationTrack(AnimationTrack animationTrack);

    public native void setUserID(int i);

    public void setUserObject(Object obj) {
    }
}
