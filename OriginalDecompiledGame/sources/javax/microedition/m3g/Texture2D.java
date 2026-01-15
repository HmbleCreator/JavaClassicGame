package javax.microedition.m3g;

/* loaded from: classes.dex */
public class Texture2D extends Transformable {
    public static final int FILTER_BASE_LEVEL = 208;
    public static final int FILTER_LINEAR = 209;
    public static final int FILTER_NEAREST = 210;
    public static final int FUNC_ADD = 224;
    public static final int FUNC_BLEND = 225;
    public static final int FUNC_DECAL = 226;
    public static final int FUNC_MODULATE = 227;
    public static final int FUNC_REPLACE = 228;
    public static final int WRAP_CLAMP = 240;
    public static final int WRAP_REPEAT = 241;

    public Texture2D(Image2D image2D) {
    }

    public native int getBlendColor();

    public native int getBlending();

    public Image2D getImage() {
        return null;
    }

    public native int getWrappingS();

    public native int getWrappingT();

    public native void setBlendColor(int i);

    public native void setBlending(int i);

    public native void setFiltering(int i, int i2);

    public void setImage(Image2D image2D) {
    }

    public native void setWrapping(int i, int i2);
}
