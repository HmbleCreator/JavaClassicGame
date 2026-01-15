package javax.microedition.m3g;

/* loaded from: classes.dex */
public class Background extends Object3D {
    public static final int BORDER = 32;
    public static final int REPEAT = 33;

    public native int getColor();

    public native int getCropHeight();

    public native int getCropWidth();

    public native int getCropX();

    public native int getCropY();

    public Image2D getImage() {
        return null;
    }

    public native int getImageModeX();

    public native int getImageModeY();

    public native boolean isColorClearEnabled();

    public native boolean isDepthClearEnabled();

    public native void setColor(int i);

    public native void setColorClearEnable(boolean z);

    public native void setCrop(int i, int i2, int i3, int i4);

    public native void setDepthClearEnable(boolean z);

    public void setImage(Image2D image2D) {
    }

    public native void setImageMode(int i, int i2);
}
