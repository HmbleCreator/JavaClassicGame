package javax.microedition.m3g;

/* loaded from: classes.dex */
public class Sprite3D extends Node {
    public Sprite3D(boolean z, Image2D image2D, Appearance appearance) {
    }

    public Appearance getAppearance() {
        return null;
    }

    public native int getCropHeight();

    public native int getCropWidth();

    public native int getCropX();

    public native int getCropY();

    public Image2D getImage() {
        return null;
    }

    public native boolean isScaled();

    public void setAppearance(Appearance appearance) {
    }

    public native void setCrop(int i, int i2, int i3, int i4);

    public void setImage(Image2D image2D) {
    }
}
