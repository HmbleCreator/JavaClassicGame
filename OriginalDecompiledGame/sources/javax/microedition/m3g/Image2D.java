package javax.microedition.m3g;

/* loaded from: classes.dex */
public class Image2D extends Object3D {
    public static final int ALPHA = 96;
    public static final int LUMINANCE = 97;
    public static final int LUMINANCE_ALPHA = 98;
    public static final int RGB = 99;
    public static final int RGBA = 100;

    public Image2D(int i, int i2, int i3) {
    }

    public Image2D(int i, int i2, int i3, byte[] bArr) {
    }

    public Image2D(int i, int i2, int i3, byte[] bArr, byte[] bArr2) {
    }

    public Image2D(int i, Object obj) {
    }

    public native int getFormat();

    public native int getHeight();

    public native int getWidth();

    public native boolean isMutable();

    public native void set(int i, int i2, int i3, int i4, byte[] bArr);
}
