package javax.microedition.lcdui;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.TextPaint;
import javax.microedition.midlet.MIDlet;

/* loaded from: classes.dex */
public class Graphics {
    public static final int BASELINE = 64;
    public static final int BOTTOM = 32;
    public static final int DOTTED = 1;
    public static final int HCENTER = 1;
    public static final int LEFT = 4;
    public static final int RIGHT = 8;
    public static final int SOLID = 0;
    public static final int TOP = 16;
    public static final int VCENTER = 2;
    private static Graphics g;
    private android.graphics.Canvas a;
    private Paint b;
    private Paint c;
    private Font d;
    private int e;
    private int f;
    private int[] h;
    private Path i;

    protected Graphics() {
        this.d = Font.getDefaultFont();
        this.b = new Paint();
        this.b.setStyle(Paint.Style.STROKE);
        setFont(this.d);
        this.c = new Paint(this.b);
        this.c.setStyle(Paint.Style.FILL);
        this.i = new Path();
    }

    protected Graphics(Bitmap bitmap) {
        this();
        this.a = new android.graphics.Canvas(bitmap);
        this.a.save();
    }

    private int a(int i) {
        if ((i & 16) != 0) {
            return Math.round(this.b.ascent()) * (-1);
        }
        if ((i & 2) != 0) {
            throw new IllegalArgumentException();
        }
        if ((i & 32) != 0) {
            return Math.round(this.b.descent()) * (-1);
        }
        if ((i & 64) != 0) {
            return 0;
        }
        throw new IllegalArgumentException();
    }

    private static Paint.Align b(int i) {
        if ((i & 4) != 0) {
            return Paint.Align.LEFT;
        }
        if ((i & 8) != 0) {
            return Paint.Align.RIGHT;
        }
        if ((i & 1) != 0) {
            return Paint.Align.CENTER;
        }
        throw new IllegalArgumentException();
    }

    protected static Graphics getInstance() {
        if (g == null) {
            g = new Graphics();
        }
        return g;
    }

    public void clipRect(int i, int i2, int i3, int i4) {
        this.a.clipRect(i, i2, i + i3, i2 + i4);
    }

    public void copyArea(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        throw new RuntimeException("Method not supported");
    }

    public void drawArc(int i, int i2, int i3, int i4, int i5, int i6) {
        this.a.drawArc(new RectF(i, i2, i + i3, i2 + i4), i5, -i6, false, this.b);
    }

    public void drawChar(char c, int i, int i2, int i3) {
        this.b.setTextAlign(b(i3));
        this.a.drawText(new char[]{c}, 0, 1, i, i2 + a(i3), this.b);
    }

    public void drawChars(char[] cArr, int i, int i2, int i3, int i4, int i5) {
        this.b.setTextAlign(b(i5));
        this.a.drawText(cArr, i, i2, i3, i4 + a(i5), this.b);
    }

    public void drawImage(Image image, int i, int i2, int i3) {
        this.a.drawBitmap(image.getBmp(), getHAdjustForImage(i3, image.getWidth()) + i, getVAdjustForImage(i3, image.getHeight()) + i2, this.b);
    }

    public void drawLine(int i, int i2, int i3, int i4) {
        this.a.drawLine(i, i2, i3, i4, this.b);
    }

    public void drawRGB(int[] iArr, int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        this.a.drawBitmap(iArr, i, i2, i3, i4, i5, i6, z, this.b);
    }

    public void drawRect(int i, int i2, int i3, int i4) {
        this.a.drawRect(i, i2, i3 + i, i4 + i2, this.b);
    }

    public void drawRegion(Image image, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        drawRegionNormal(image, i, i2, i3, i4, i5, i6, i7, i8);
    }

    public void drawRegionAlt(Image image, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (this.h == null) {
            this.h = new int[MIDlet.getWidth() * MIDlet.getHeight()];
        }
        image.getRGB(this.h, 0, image.getWidth(), i, i2, i3, i4);
        drawRGB(this.h, 0, image.getWidth(), i6 + getHAdjustForImage(i8, i3), i7 + getVAdjustForImage(i8, i4), i3, i4, true);
    }

    public void drawRegionNormal(Image image, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        System.currentTimeMillis();
        Bitmap bmp = image.getBmp();
        Matrix matrix = new Matrix();
        int i9 = 0;
        switch (i5) {
            case 0:
                i9 = 0;
                break;
            case 1:
            case 3:
                i9 = 180;
                break;
            case 4:
            case 6:
                i9 = 270;
                break;
            case 5:
            case 7:
                i9 = 90;
                break;
        }
        matrix.setRotate(i9);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bmp, i, i2, i3, i4, matrix, false);
        int hAdjustForImage = getHAdjustForImage(i8, bitmapCreateBitmap.getWidth()) + i6;
        int vAdjustForImage = getVAdjustForImage(i8, bitmapCreateBitmap.getHeight()) + i7;
        if (i5 == 2 || i5 == 1) {
            Matrix matrix2 = new Matrix();
            matrix2.setScale(-1.0f, 1.0f);
            matrix2.postTranslate(hAdjustForImage + bitmapCreateBitmap.getWidth(), vAdjustForImage);
            this.a.drawBitmap(bitmapCreateBitmap, matrix2, this.b);
            return;
        }
        if (i5 != 4 && i5 != 7) {
            this.a.drawBitmap(bitmapCreateBitmap, hAdjustForImage, vAdjustForImage, this.b);
            return;
        }
        Matrix matrix3 = new Matrix();
        matrix3.setScale(1.0f, -1.0f);
        matrix3.postTranslate(hAdjustForImage, vAdjustForImage + bitmapCreateBitmap.getHeight());
        this.a.drawBitmap(bitmapCreateBitmap, matrix3, this.b);
    }

    public void drawRoundRect(int i, int i2, int i3, int i4, int i5, int i6) {
        this.a.drawRoundRect(new RectF(i, i2, i + i3, i2 + i4), i5, i6, this.b);
    }

    public void drawString(String str, int i, int i2, int i3) {
        this.b.setTextAlign(b(i3));
        this.a.drawText(str, i, a(i3) + i2, this.b);
    }

    public void drawSubstring(String str, int i, int i2, int i3, int i4, int i5) {
        drawString(str.substring(i, i + i2), i3, i4, i5);
    }

    public void fillArc(int i, int i2, int i3, int i4, int i5, int i6) {
        this.a.drawArc(new RectF(i, i2, i + i3, i2 + i4), i5, -i6, true, this.c);
    }

    public void fillRect(int i, int i2, int i3, int i4) {
        this.a.drawRect(i, i2, i3 + i, i4 + i2, this.c);
    }

    public void fillRoundRect(int i, int i2, int i3, int i4, int i5, int i6) {
        this.a.drawRoundRect(new RectF(i, i2, i + i3, i2 + i4), i5, i6, this.c);
    }

    public void fillTriangle(int i, int i2, int i3, int i4, int i5, int i6) {
        this.i.reset();
        this.i.moveTo(i, i2);
        this.i.lineTo(i3, i4);
        this.i.lineTo(i5, i6);
        this.i.close();
        this.a.drawPath(this.i, this.c);
    }

    public int getBlueComponent() {
        throw new RuntimeException("Method not supported");
    }

    public int getClipHeight() {
        return this.a.getClipBounds().height();
    }

    public int getClipWidth() {
        return this.a.getClipBounds().width();
    }

    public int getClipX() {
        return this.a.getClipBounds().left;
    }

    public int getClipY() {
        return this.a.getClipBounds().top;
    }

    public int getColor() {
        return 16777215 & this.b.getColor();
    }

    public int getDisplayColor(int i) {
        throw new RuntimeException("Method not supported");
    }

    public Font getFont() {
        return this.d;
    }

    public int getGrayScale() {
        throw new RuntimeException("Method not supported");
    }

    public int getGreenComponent() {
        throw new RuntimeException("Method not supported");
    }

    protected int getHAdjustForImage(int i, int i2) {
        if ((i & 4) != 0) {
            return 0;
        }
        if ((i & 8) != 0) {
            return -i2;
        }
        if ((i & 1) != 0) {
            return (-i2) / 2;
        }
        throw new IllegalArgumentException();
    }

    public int getRedComponent() {
        throw new RuntimeException("Method not supported");
    }

    public int getStrokeStyle() {
        return 0;
    }

    public int getTranslateX() {
        return this.e;
    }

    public int getTranslateY() {
        return this.f;
    }

    protected int getVAdjustForImage(int i, int i2) {
        if ((i & 16) != 0) {
            return 0;
        }
        if ((i & 2) != 0) {
            return (-i2) / 2;
        }
        if ((i & 32) != 0) {
            return -i2;
        }
        throw new IllegalArgumentException();
    }

    protected void setCanvas(android.graphics.Canvas canvas) {
        this.a = canvas;
        this.a.save();
    }

    public void setClip(int i, int i2, int i3, int i4) {
        this.a.restore();
        this.a.save();
        this.a.clipRect(i, i2, i + i3, i2 + i4);
    }

    public void setColor(int i) {
        int i2 = (i & (-16777216)) == 0 ? i | (-16777216) : i;
        this.b.setColor(i2);
        this.c.setColor(i2);
    }

    public void setColor(int i, int i2, int i3) {
        this.b.setARGB(255, i, i2, i3);
        this.c.setARGB(255, i, i2, i3);
    }

    public void setFont(Font font) {
        TextPaint textPaint = font.getTextPaint();
        this.b.setTypeface(font.getTypeface());
        this.b.setUnderlineText(textPaint.isUnderlineText());
        this.b.setTextSize(textPaint.getTextSize());
        this.d = font;
    }

    public void setGrayScale(int i) {
        throw new RuntimeException("Method not supported");
    }

    public void setStrokeStyle(int i) {
    }

    public void translate(int i, int i2) {
        this.e += i;
        this.f += i2;
        this.a.translate(i, i2);
    }
}
