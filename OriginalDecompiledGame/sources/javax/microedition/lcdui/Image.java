package javax.microedition.lcdui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.dchoc.Constants;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class Image {
    private Bitmap a;
    private int b = -1;
    private int c;
    private int d;
    private boolean e;

    private Image(Bitmap bitmap) {
        this.a = bitmap;
    }

    public static Image createImage(int i, int i2) {
        Image image = new Image(Bitmap.createBitmap(i, i2, Constants.SUPPRESS_ALPHA ? Bitmap.Config.RGB_565 : Bitmap.Config.ARGB_8888));
        image.e = true;
        return image;
    }

    public static Image createImage(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new NullPointerException();
        }
        return new Image(BitmapFactory.decodeStream(inputStream));
    }

    public static Image createImage(String str) throws IOException {
        InputStream resourceAsStream = Image.class.getResourceAsStream(str);
        if (resourceAsStream == null) {
            throw new IOException();
        }
        return createImage(resourceAsStream);
    }

    public static Image createImage(Image image) {
        return new Image(Bitmap.createBitmap(image.a));
    }

    public static Image createImage(Image image, int i, int i2, int i3, int i4, int i5) {
        throw new RuntimeException("unsupported method");
    }

    public static Image createImage(byte[] bArr, int i, int i2) {
        return new Image(BitmapFactory.decodeByteArray(bArr, i, i2));
    }

    public static Image createRGBImage(int[] iArr, int i, int i2, boolean z) {
        return new Image(Bitmap.createBitmap(iArr, i, i2, z ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565));
    }

    public Bitmap getBmp() {
        return this.a;
    }

    public Graphics getGraphics() {
        if (isMutable()) {
            return new Graphics(this.a);
        }
        throw new IllegalStateException();
    }

    public int getHeight() {
        return this.a.getHeight();
    }

    public void getRGB(int[] iArr, int i, int i2, int i3, int i4, int i5, int i6) {
        this.a.getPixels(iArr, i, i2, i3, i4, i5, i6);
    }

    public int getTexHeight() {
        return this.d;
    }

    public int getTexWidth() {
        return this.c;
    }

    public int getTexture() {
        return this.b;
    }

    public int getWidth() {
        return this.a.getWidth();
    }

    public boolean isMutable() {
        return this.e;
    }

    public void setTexHeight(int i) {
        this.d = i;
    }

    public void setTexWidth(int i) {
        this.c = i;
    }

    public void setTexture(int i) {
        this.b = i;
    }
}
