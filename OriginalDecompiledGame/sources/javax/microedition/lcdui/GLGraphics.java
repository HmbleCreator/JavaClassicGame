package javax.microedition.lcdui;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;
import javax.microedition.khronos.opengles.GL11Ext;
import javax.microedition.midlet.MIDlet;

/* loaded from: classes.dex */
public class GLGraphics extends Graphics {
    public static final int RENDER_MODE_QUAD = 0;
    public static final int RENDER_MODE_TEXTURE_EXT = 1;
    public static final int RENDER_MODE_VBO = 2;
    private static GLGraphics b;
    public static int renderMode = 0;
    private GL10 a;
    private float c;
    private float d;
    private float e;
    private int f;
    private int g;
    private int h = MIDlet.getWidth();
    private int i = MIDlet.getHeight();
    private boolean j;
    private int k;
    private int l;

    protected GLGraphics() {
    }

    private static int a(int i) {
        int i2 = 1;
        while (i2 < i) {
            i2 <<= 1;
        }
        return i2;
    }

    protected static GLGraphics getInstance() {
        if (b == null) {
            b = new GLGraphics();
        }
        return b;
    }

    @Override // javax.microedition.lcdui.Graphics
    public void clipRect(int i, int i2, int i3, int i4) {
        int i5 = this.f;
        int i6 = this.g;
        if (i > this.f) {
            i5 = i;
        }
        if (i2 > this.g) {
            i6 = i2;
        }
        setClip(i5, i6, i + i3 < this.f + this.h ? (i + i3) - i5 : (this.f + this.h) - i5, i2 + i4 < this.g + this.i ? (i2 + i4) - i6 : (this.g + this.i) - i6);
    }

    @Override // javax.microedition.lcdui.Graphics
    public void drawArc(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int i8 = (i3 / 2) + i;
        int i9 = (i4 / 2) + i2;
        int i10 = 1;
        if (i6 < 0) {
            i10 = -1;
            i7 = -i6;
        } else {
            i7 = i6;
        }
        float[] fArr = new float[i7 << 2];
        float[] fArr2 = new float[i7 << 3];
        float f = (3.1415927f * i5) / 180.0f;
        int i11 = 0;
        int i12 = 0;
        for (int i13 = 0; i13 <= i7; i13++) {
            float fCos = i8 + ((((float) Math.cos(f)) * i3) / 2.0f);
            float fSin = i9 - (((i10 * ((float) Math.sin(f))) * i4) / 2.0f);
            fArr[i12] = fCos;
            int i14 = i12 + 1;
            fArr[i14] = fSin;
            i12 = i14 + 1;
            fArr2[i11] = this.c;
            int i15 = i11 + 1;
            fArr2[i15] = this.d;
            int i16 = i15 + 1;
            fArr2[i16] = this.e;
            int i17 = i16 + 1;
            fArr2[i17] = 1.0f;
            i11 = i17 + 1;
            f += 0.017453292f;
        }
        this.a.glDisable(3042);
        this.a.glDisable(3553);
        this.a.glDisableClientState(32888);
        this.a.glEnableClientState(32886);
        this.a.glVertexPointer(2, 5126, 0, FloatBuffer.wrap(fArr));
        this.a.glColorPointer(4, 5126, 0, FloatBuffer.wrap(fArr2));
        this.a.glDrawArrays(3, 0, i12 >> 1);
        this.a.glDisableClientState(32886);
        this.a.glEnable(3042);
        this.a.glEnable(3553);
        this.a.glEnableClientState(32888);
    }

    @Override // javax.microedition.lcdui.Graphics
    public void drawChars(char[] cArr, int i, int i2, int i3, int i4, int i5) {
    }

    @Override // javax.microedition.lcdui.Graphics
    public void drawImage(Image image, int i, int i2, int i3) {
        int hAdjustForImage = getHAdjustForImage(i3, image.getWidth()) + i;
        int vAdjustForImage = i2 + getVAdjustForImage(i3, image.getHeight());
        float fMax = Math.max(this.f, hAdjustForImage);
        float fMin = Math.min(this.f + this.h, image.getWidth() + hAdjustForImage);
        float fMax2 = Math.max(this.g, vAdjustForImage);
        float fMin2 = Math.min(this.g + this.i, image.getHeight() + vAdjustForImage);
        if (fMin <= fMax || fMin2 <= fMax2) {
            return;
        }
        switch (renderMode) {
            case 0:
                int texWidth = image.getTexWidth();
                int texHeight = image.getTexHeight();
                this.a.glBindTexture(3553, image.getTexture());
                this.a.glVertexPointer(2, 5126, 0, FloatBuffer.wrap(new float[]{fMax, fMax2, fMax, fMin2, fMin, fMax2, fMin, fMin2}));
                this.a.glTexCoordPointer(2, 5126, 0, FloatBuffer.wrap(new float[]{(fMax - hAdjustForImage) / texWidth, (fMax2 - vAdjustForImage) / texHeight, (fMax - hAdjustForImage) / texWidth, (fMin2 - vAdjustForImage) / texHeight, (fMin - hAdjustForImage) / texWidth, (fMax2 - vAdjustForImage) / texHeight, (fMin - hAdjustForImage) / texWidth, (fMin2 - vAdjustForImage) / texHeight}));
                this.a.glDrawArrays(5, 0, 4);
                break;
            case 1:
                this.a.glBindTexture(3553, image.getTexture());
                ((GL11Ext) this.a).glDrawTexfOES(fMax, (MIDlet.getHeight() - fMax2) - image.getHeight(), 0.0f, image.getWidth(), image.getHeight());
                break;
            case 2:
                GL11 gl11 = (GL11) this.a;
                float[] fArr = {fMax, fMax2, fMax, fMin2, fMin, fMax2, fMin, fMin2};
                int texWidth2 = image.getTexWidth();
                int texHeight2 = image.getTexHeight();
                float[] fArr2 = {(fMax - hAdjustForImage) / texWidth2, (fMax2 - vAdjustForImage) / texHeight2, (fMax - hAdjustForImage) / texWidth2, (fMin2 - vAdjustForImage) / texHeight2, (fMin - hAdjustForImage) / texWidth2, (fMax2 - vAdjustForImage) / texHeight2, (fMin - hAdjustForImage) / texWidth2, (fMin2 - vAdjustForImage) / texHeight2};
                if (this.k != 0) {
                    FloatBuffer floatBufferWrap = FloatBuffer.wrap(fArr);
                    gl11.glBindBuffer(34962, this.k);
                    gl11.glBufferData(34962, floatBufferWrap.capacity() << 5, floatBufferWrap, 35044);
                    gl11.glVertexPointer(2, 5126, 0, 0);
                }
                if (this.l != 0) {
                    FloatBuffer floatBufferWrap2 = FloatBuffer.wrap(fArr2);
                    gl11.glBindBuffer(34962, this.l);
                    gl11.glBufferData(34962, floatBufferWrap2.capacity() << 5, floatBufferWrap2, 35044);
                    gl11.glBindTexture(3553, image.getTexture());
                    gl11.glTexCoordPointer(2, 5126, 0, 0);
                }
                gl11.glDrawArrays(5, 0, 4);
                gl11.glBufferData(34962, 0, null, 35044);
                gl11.glBindBuffer(34962, 0);
                break;
        }
    }

    @Override // javax.microedition.lcdui.Graphics
    public void drawLine(int i, int i2, int i3, int i4) {
        this.a.glDisableClientState(32888);
        this.a.glDisable(3553);
        this.a.glDisable(3042);
        FloatBuffer floatBufferAllocate = FloatBuffer.allocate(4);
        floatBufferAllocate.put(new float[]{i, i2, i3, i4});
        float[] fArr = {this.c, this.d, this.e, 0.0f, this.c, this.d, this.e, 0.0f};
        FloatBuffer floatBufferAllocate2 = FloatBuffer.allocate(8);
        floatBufferAllocate2.put(fArr);
        this.a.glEnableClientState(32886);
        this.a.glVertexPointer(2, 5126, 0, floatBufferAllocate);
        this.a.glColorPointer(4, 5126, 0, floatBufferAllocate2);
        this.a.glDrawArrays(1, 0, 2);
        this.a.glDisableClientState(32886);
        this.a.glEnableClientState(32888);
        this.a.glEnable(3553);
        this.a.glEnable(3042);
    }

    @Override // javax.microedition.lcdui.Graphics
    public void drawRGB(int[] iArr, int i, int i2, int i3, int i4, int i5, int i6, boolean z) {
        int[] iArr2 = new int[1];
        this.a.glGenTextures(1, iArr2, 0);
        this.a.glBindTexture(3553, iArr2[0]);
        int iA = a(i5);
        int iA2 = a(i6);
        int[] iArr3 = new int[(iA * iA2) << 2];
        for (int i7 = 0; i7 < i6; i7++) {
            for (int i8 = 0; i8 < i5; i8++) {
                int i9 = iArr[(i7 * i5) + i8];
                int i10 = ((i7 * iA) + i8) << 2;
                int i11 = i10 + 1;
                iArr3[i10] = (i9 >> 16) & 255;
                int i12 = i11 + 1;
                iArr3[i11] = (i9 >> 8) & 255;
                int i13 = i12 + 1;
                iArr3[i12] = i9 & 255;
                if (z) {
                    iArr3[i13] = i9 >>> 24;
                } else {
                    iArr3[i13] = 255;
                }
            }
        }
        this.a.glTexImage2D(3553, 0, 6408, iA, iA2, 0, 6408, 5121, IntBuffer.wrap(iArr3));
        this.a.glTexParameterx(3553, 10241, 9728);
        int i14 = i3 + i5;
        int i15 = i4 + i6;
        this.a.glVertexPointer(2, 5126, 0, FloatBuffer.wrap(new float[]{i3, i4, i3, i15, i14, i4, i14, i15}));
        this.a.glTexCoordPointer(2, 5126, 0, FloatBuffer.wrap(new float[]{0.0f, 0.0f, 0.0f, (1.0f * i6) / iA2, (1.0f * i5) / iA, 0.0f, (1.0f * i5) / iA, (1.0f * i6) / iA2}));
        this.a.glDrawArrays(5, 0, 4);
        this.a.glDisableClientState(32884);
        this.a.glDeleteTextures(1, iArr2, 0);
    }

    @Override // javax.microedition.lcdui.Graphics
    public void drawRect(int i, int i2, int i3, int i4) {
        this.a.glDisable(3042);
        this.a.glDisable(3553);
        this.a.glDisableClientState(32888);
        float[] fArr = {this.c, this.d, this.e, 0.0f, this.c, this.d, this.e, 0.0f, this.c, this.d, this.e, 0.0f, this.c, this.d, this.e, 0.0f};
        this.a.glEnableClientState(32886);
        this.a.glVertexPointer(2, 5126, 0, FloatBuffer.wrap(new float[]{i, i2, i, i2 + i4, i + i3, i2 + i4, i + i3, i2}));
        this.a.glColorPointer(4, 5126, 0, FloatBuffer.wrap(fArr));
        this.a.glDrawArrays(2, 0, 4);
        this.a.glDisableClientState(32886);
        this.a.glEnable(3042);
        this.a.glEnable(3553);
        this.a.glEnableClientState(32888);
    }

    @Override // javax.microedition.lcdui.Graphics
    public void drawRegion(Image image, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int hAdjustForImage = getHAdjustForImage(i8, i3) + i6;
        int vAdjustForImage = getVAdjustForImage(i8, i4) + i7;
        float[] fArr = {0.0f, 0.0f, 0.0f, i4, i3, 0.0f, i3, i4};
        float[] fArr2 = {1.0f * (i / image.getTexWidth()), 1.0f * (i2 / image.getTexHeight()), 1.0f * (i / image.getTexWidth()), 1.0f * ((i2 + i4) / image.getTexHeight()), 1.0f * ((i + i3) / image.getTexWidth()), 1.0f * (i2 / image.getTexHeight()), 1.0f * ((i + i3) / image.getTexWidth()), 1.0f * ((i2 + i4) / image.getTexHeight())};
        this.a.glPushMatrix();
        this.a.glTranslatef(hAdjustForImage, vAdjustForImage, 0.0f);
        if (i5 != 0) {
            if (i5 >= 4) {
                this.a.glTranslatef(i4 * 0.5f, i3 * 0.5f, 0.0f);
            } else {
                this.a.glTranslatef(i3 * 0.5f, i4 * 0.5f, 0.0f);
            }
            switch (i5) {
                case 1:
                    this.a.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
                    this.a.glScalef(-1.0f, 1.0f, 1.0f);
                    break;
                case 2:
                    this.a.glScalef(-1.0f, 1.0f, 1.0f);
                    break;
                case 3:
                    this.a.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
                    break;
                case 4:
                    this.a.glRotatef(270.0f, 0.0f, 0.0f, 1.0f);
                    this.a.glScalef(-1.0f, 1.0f, 1.0f);
                    break;
                case 5:
                    this.a.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
                    break;
                case 6:
                    this.a.glRotatef(270.0f, 0.0f, 0.0f, 1.0f);
                    break;
                case 7:
                    this.a.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
                    this.a.glScalef(-1.0f, 1.0f, 1.0f);
                    break;
            }
            this.a.glTranslatef((-i3) * 0.5f, (-i4) * 0.5f, 0.0f);
        }
        this.a.glBindTexture(3553, image.getTexture());
        this.a.glVertexPointer(2, 5126, 0, FloatBuffer.wrap(fArr));
        this.a.glTexCoordPointer(2, 5126, 0, FloatBuffer.wrap(fArr2));
        this.a.glDrawArrays(5, 0, 4);
        this.a.glPopMatrix();
    }

    @Override // javax.microedition.lcdui.Graphics
    public void drawRoundRect(int i, int i2, int i3, int i4, int i5, int i6) {
        drawArc((i + i3) - i5, i2, i5, i6, 0, 90);
        drawArc(i, i2, i5, i6, 90, 90);
        drawArc(i, (i2 + i4) - i6, i5, i6, 180, 90);
        drawArc((i + i3) - i5, (i2 + i4) - i6, i5, i6, 270, 90);
        drawLine((i5 / 2) + i, i2, (i + i3) - (i5 / 2), i2);
        drawLine(i, (i6 / 2) + i2, i, (i2 + i4) - (i6 / 2));
        drawLine((i5 / 2) + i, i2 + i4, (i + i3) - (i5 / 2), i2 + i4);
        drawLine(i + i3, (i2 + i4) - (i6 / 2), i + i3, (i6 / 2) + i2);
    }

    @Override // javax.microedition.lcdui.Graphics
    public void drawString(String str, int i, int i2, int i3) {
    }

    @Override // javax.microedition.lcdui.Graphics
    public void fillArc(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int i8 = (i3 / 2) + i;
        int i9 = (i4 / 2) + i2;
        int i10 = 1;
        if (i6 < 0) {
            i10 = -1;
            i7 = -i6;
        } else {
            i7 = i6;
        }
        int i11 = i7 + 1;
        float[] fArr = new float[i11 << 2];
        float[] fArr2 = new float[i11 << 3];
        fArr[0] = i8;
        fArr[1] = i9;
        fArr2[0] = this.c;
        fArr2[1] = this.d;
        fArr2[2] = this.e;
        fArr2[3] = 1.0f;
        float f = (3.1415927f * i5) / 180.0f;
        int i12 = 0 + 1 + 1 + 1 + 1;
        int i13 = 0 + 1 + 1;
        for (int i14 = 0; i14 <= i7; i14++) {
            float fCos = i8 + ((((float) Math.cos(f)) * i3) / 2.0f);
            float fSin = i9 - (((i10 * ((float) Math.sin(f))) * i4) / 2.0f);
            fArr[i13] = fCos;
            int i15 = i13 + 1;
            fArr[i15] = fSin;
            i13 = i15 + 1;
            fArr2[i12] = this.c;
            int i16 = i12 + 1;
            fArr2[i16] = this.d;
            int i17 = i16 + 1;
            fArr2[i17] = this.e;
            int i18 = i17 + 1;
            fArr2[i18] = 1.0f;
            i12 = i18 + 1;
            f += 0.017453292f;
        }
        this.a.glDisable(3042);
        this.a.glDisable(3553);
        this.a.glDisableClientState(32888);
        this.a.glEnableClientState(32886);
        this.a.glVertexPointer(2, 5126, 0, FloatBuffer.wrap(fArr));
        this.a.glColorPointer(4, 5126, 0, FloatBuffer.wrap(fArr2));
        this.a.glDrawArrays(6, 0, i13 >> 1);
        this.a.glDisableClientState(32886);
        this.a.glEnable(3042);
        this.a.glEnable(3553);
        this.a.glEnableClientState(32888);
    }

    @Override // javax.microedition.lcdui.Graphics
    public void fillRect(int i, int i2, int i3, int i4) {
        this.a.glDisable(3042);
        this.a.glDisable(3553);
        this.a.glDisableClientState(32888);
        float[] fArr = {this.c, this.d, this.e, 0.0f, this.c, this.d, this.e, 0.0f, this.c, this.d, this.e, 0.0f, this.c, this.d, this.e, 0.0f};
        this.a.glVertexPointer(2, 5126, 0, FloatBuffer.wrap(new float[]{i, i2, i, i2 + i4, i + i3, i2, i + i3, i2 + i4}));
        this.a.glColorPointer(4, 5126, 0, FloatBuffer.wrap(fArr));
        this.a.glEnableClientState(32886);
        this.a.glDrawArrays(5, 0, 4);
        this.a.glDisableClientState(32886);
        this.a.glEnable(3042);
        this.a.glEnable(3553);
        this.a.glEnableClientState(32888);
    }

    @Override // javax.microedition.lcdui.Graphics
    public void fillRoundRect(int i, int i2, int i3, int i4, int i5, int i6) {
        fillArc((i + i3) - i5, i2, i5, i6, 0, 90);
        fillArc(i, i2, i5, i6, 90, 90);
        fillArc(i, (i2 + i4) - i6, i5, i6, 180, 90);
        fillArc((i + i3) - i5, (i2 + i4) - i6, i5, i6, 270, 90);
        fillRect((i5 / 2) + i, i2, i3 - i5, i4);
        fillRect(i, (i6 / 2) + i2, i5, i4 - i6);
        fillRect((i + i3) - (i5 / 2), (i6 / 2) + i2, i5 / 2, i4 - i6);
    }

    @Override // javax.microedition.lcdui.Graphics
    public void fillTriangle(int i, int i2, int i3, int i4, int i5, int i6) {
        float[] fArr = {this.c, this.d, this.e, 0.0f, this.c, this.d, this.e, 0.0f, this.c, this.d, this.e, 0.0f};
        this.a.glDisable(3042);
        this.a.glDisable(3553);
        this.a.glDisableClientState(32888);
        this.a.glEnableClientState(32886);
        this.a.glVertexPointer(2, 5126, 0, FloatBuffer.wrap(new float[]{i, i2, i3, i4, i5, i6}));
        this.a.glColorPointer(4, 5126, 0, FloatBuffer.wrap(fArr));
        this.a.glDrawArrays(4, 0, 3);
        this.a.glDisableClientState(32886);
        this.a.glEnable(3042);
        this.a.glEnable(3553);
        this.a.glEnableClientState(32888);
    }

    @Override // javax.microedition.lcdui.Graphics
    public int getBlueComponent() {
        return (int) this.e;
    }

    @Override // javax.microedition.lcdui.Graphics
    public int getClipHeight() {
        return this.i;
    }

    @Override // javax.microedition.lcdui.Graphics
    public int getClipWidth() {
        return this.h;
    }

    @Override // javax.microedition.lcdui.Graphics
    public int getClipX() {
        return this.f;
    }

    @Override // javax.microedition.lcdui.Graphics
    public int getClipY() {
        return this.g;
    }

    @Override // javax.microedition.lcdui.Graphics
    public int getColor() {
        return (((int) (this.c * 255.0f)) << 16) | (((int) (this.d * 255.0f)) << 8) | ((int) (this.e * 255.0f));
    }

    @Override // javax.microedition.lcdui.Graphics
    public int getGreenComponent() {
        return (int) this.d;
    }

    @Override // javax.microedition.lcdui.Graphics
    public int getRedComponent() {
        return (int) this.c;
    }

    @Override // javax.microedition.lcdui.Graphics
    public void setClip(int i, int i2, int i3, int i4) {
        this.f = i;
        this.g = i2;
        this.h = i3;
        this.i = i4;
        this.a.glEnable(3089);
        this.a.glScissor(this.f, (MIDlet.getHeight() - this.g) - this.i, this.h, this.i);
    }

    @Override // javax.microedition.lcdui.Graphics
    public void setColor(int i) {
        setColor((i >> 16) & 255, (i >> 8) & 255, i & 255);
    }

    @Override // javax.microedition.lcdui.Graphics
    public void setColor(int i, int i2, int i3) {
        this.c = i / 255.0f;
        this.d = i2 / 255.0f;
        this.e = i3 / 255.0f;
    }

    protected void setGl(GL gl) {
        this.a = (GL10) gl;
        if (!this.j) {
            String strGlGetString = this.a.glGetString(7939);
            if (strGlGetString.indexOf("GL_OES_draw_texture") != -1) {
                renderMode = 1;
            } else if (strGlGetString.indexOf("GL_ARB_vertex_buffer_object") != -1) {
                renderMode = 2;
                GL10 gl10 = this.a;
                if (this.k == 0 && (gl10 instanceof GL11)) {
                    GL11 gl11 = (GL11) gl10;
                    int[] iArr = new int[1];
                    gl11.glGenBuffers(1, iArr, 0);
                    this.k = iArr[0];
                    gl11.glGenBuffers(1, iArr, 0);
                    this.l = iArr[0];
                    System.out.println("mTextureBufferIndex = " + this.l);
                }
            }
            this.j = true;
        }
        this.a.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        this.a.glViewport(0, 0, MIDlet.getWidth(), MIDlet.getHeight());
        this.a.glClear(16384);
        this.a.glBlendFunc(1, 771);
        this.a.glEnable(3553);
        this.a.glEnable(3042);
        this.a.glEnableClientState(32884);
        this.a.glEnableClientState(32888);
        this.a.glMatrixMode(5889);
        this.a.glLoadIdentity();
        this.a.glOrthof(0.0f, MIDlet.getWidth(), 0.0f, MIDlet.getHeight(), -1.0f, 1.0f);
        this.a.glMatrixMode(5888);
        this.a.glLoadIdentity();
        this.a.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        this.a.glTranslatef(0.0f, -MIDlet.getHeight(), 0.0f);
        this.a.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
        this.a.glPushMatrix();
    }
}
