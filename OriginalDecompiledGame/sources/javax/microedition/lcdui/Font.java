package javax.microedition.lcdui;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;

/* loaded from: classes.dex */
public class Font {
    public static final int FACE_MONOSPACE = 32;
    public static final int FACE_PROPORTIONAL = 64;
    public static final int FACE_SYSTEM = 0;
    public static final int FONT_INPUT_TEXT = 1;
    public static final int FONT_STATIC_TEXT = 0;
    public static final int SIZE_LARGE = 16;
    public static final int SIZE_MEDIUM = 0;
    public static final int SIZE_SMALL = 8;
    public static final int STYLE_BOLD = 1;
    public static final int STYLE_ITALIC = 2;
    public static final int STYLE_PLAIN = 0;
    public static final int STYLE_UNDERLINED = 3;
    private Typeface a;
    private TextPaint b;
    private int c;
    private int d;
    private Paint.FontMetricsInt e;

    private Font(Typeface typeface, TextPaint textPaint, int i, int i2, int i3) {
        this.a = typeface;
        this.b = textPaint;
        this.c = i;
        this.d = i2;
        this.e = textPaint.getFontMetricsInt();
    }

    public static Font getDefaultFont() {
        return new Font(Typeface.DEFAULT, new TextPaint(), 0, 0, 0);
    }

    public static Font getFont(int i) {
        throw new RuntimeException("Unsupported Method");
    }

    public static Font getFont(int i, int i2, int i3) {
        Typeface typeface;
        int i4;
        TextPaint textPaint = new TextPaint();
        if (i == 32) {
            typeface = Typeface.MONOSPACE;
        } else if (i == 0) {
            typeface = Typeface.DEFAULT;
        } else {
            if (i != 64) {
                throw new IllegalArgumentException("Face not supported");
            }
            typeface = Typeface.SANS_SERIF;
        }
        int i5 = -1;
        if (i2 == 0) {
            i5 = 0;
        } else if (i2 == 1) {
            i5 = 1;
        } else if (i2 == 2) {
            i5 = 2;
        } else {
            if (i2 != 3) {
                throw new IllegalArgumentException("Style not supported");
            }
            textPaint.setUnderlineText(true);
        }
        Typeface typefaceCreate = Typeface.create(typeface, i5);
        textPaint.setTypeface(typefaceCreate);
        switch (i3) {
            case 16:
                i4 = 25;
                break;
            default:
                i4 = i3;
                break;
        }
        textPaint.setTextSize(i4);
        return new Font(typefaceCreate, textPaint, i, i2, i4);
    }

    public int charWidth(char c) {
        return charsWidth(new char[]{c}, 0, 1);
    }

    public int charsWidth(char[] cArr, int i, int i2) {
        return stringWidth(new String(cArr, i, i2));
    }

    public int getBaselinePosition() {
        return this.e.top * (-1);
    }

    public int getFace() {
        return this.c;
    }

    public int getHeight() {
        return (this.e.ascent * (-1)) + this.e.descent + this.e.leading;
    }

    public int getSize() {
        return (int) this.b.getTextSize();
    }

    public int getStyle() {
        return this.d;
    }

    protected TextPaint getTextPaint() {
        return this.b;
    }

    protected Typeface getTypeface() {
        return this.a;
    }

    public boolean isBold() {
        return this.a.isBold();
    }

    public boolean isItalic() {
        return this.a.isItalic();
    }

    public boolean isPlain() {
        return (this.a.isItalic() || this.a.isBold()) ? false : true;
    }

    public boolean isUnderlined() {
        return this.b.isUnderlineText();
    }

    public int stringWidth(String str) {
        return (int) this.b.measureText(str);
    }

    public int subStringWidth(String str, int i, int i2) {
        return stringWidth(str.substring(i, i2));
    }
}
