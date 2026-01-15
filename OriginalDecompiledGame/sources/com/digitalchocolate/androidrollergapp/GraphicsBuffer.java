package com.digitalchocolate.androidrollergapp;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* loaded from: classes.dex */
public class GraphicsBuffer {
    private static final int BUFFER_BLUR_ALPHA = 7;
    private static final int BUFFER_BLUR_BLUE = 6;
    private static final int BUFFER_BLUR_GREEN = 5;
    private static final int BUFFER_BLUR_RED = 4;
    private static final int BUFFER_BLUR_RESULT = 3;
    private static final int BUFFER_COLOR = 0;
    private static final int BUFFER_EXTRA = 120;
    private static final int BUFFER_MODIFIED = 1;
    private static final int BUFFER_ORIGINAL = 8;
    private static final int BUFFER_TARGET = 2;
    public static final int NEUTRAL_COLOR = -8355712;
    public static final int NO_SCALING = 1024;
    private static final int PARAM_ANGLE = 2;
    private static final int PARAM_BILINEAR_FILTERING = 1;
    private static final int PARAM_COLOR_MODIFICATION = 0;
    private static final int PARAM_COUNT = 6;
    private static final int PARAM_REPAINTED = 5;
    private static final int PARAM_SCALE_HEIGHT = 4;
    private static final int PARAM_SCALE_WIDTH = 3;
    public static final int RENDER_MODE_ADD = 1;
    public static final int RENDER_MODE_NORMAL = 0;
    public static final int RENDER_MODE_SUB = 2;
    public static final int SCALE_ACCURACY = 10;
    private static final int TRANSPARENT_COLOR = -65281;
    private static int[] smTransparentColor;
    private int mBlurBoxHeight;
    private int mBlurBoxWidth;
    private Graphics mBufferGraphics;
    private Image mBufferImage;
    private int mFrameHeight;
    private int mFramePivotX;
    private int mFramePivotY;
    private int mFrameWidth;
    private boolean mImageSource;
    private int[] mLastEffectParam;
    private int mRenderMode;
    private Image mRenderTarget;
    private int mRotatedHeight;
    private int mRotatedWidth;
    private int[] mRotatedX;
    private int[] mRotatedY;
    private int[][] mBuffer = new int[9][];
    private int[] mEffectParam = new int[6];

    private boolean allocateBuffer(int i, int i2, boolean z) {
        if (this.mBuffer[i] != null && i2 <= this.mBuffer[i].length) {
            return false;
        }
        this.mBuffer[i] = new int[z ? (i2 * 120) / 100 : i2];
        return true;
    }

    private void copyEffectParams() {
        int i = 6;
        while (true) {
            i--;
            if (i < 0) {
                return;
            } else {
                this.mLastEffectParam[i] = this.mEffectParam[i];
            }
        }
    }

    private void doBlurPreComputation(int[] iArr, int i, int i2) {
        int i3 = i * i2;
        allocateBuffer(3, i3, true);
        allocateBuffer(7, i3, true);
        allocateBuffer(4, i3, true);
        allocateBuffer(5, i3, true);
        allocateBuffer(6, i3, true);
        int[] iArr2 = this.mBuffer[7];
        iArr2[0] = iArr[0] >>> 24;
        int i4 = 1;
        int i5 = (1 + i) - 1;
        while (i4 < i5) {
            iArr2[i4] = (iArr[i4] >>> 24) + iArr2[i4 - 1];
            i4++;
        }
        int i6 = i4;
        int i7 = i2;
        while (true) {
            i7--;
            if (i7 < 1) {
                break;
            }
            int i8 = i6 + 1;
            iArr2[i6] = (iArr[i8] >>> 24) + iArr2[i8 - i];
            int i9 = (i8 + i) - 1;
            while (i8 < i9) {
                iArr2[i8] = (((iArr[i8] >>> 24) + iArr2[i8 - 1]) - iArr2[(i8 - i) - 1]) + iArr2[i8 - i];
                i8++;
            }
            i6 = i8;
        }
        for (int i10 = 1; i10 <= 2; i10++) {
            int i11 = i10 << 3;
            int[] iArr3 = this.mBuffer[4];
            if (i10 == 2) {
                iArr3 = this.mBuffer[5];
            }
            iArr3[0] = (iArr[0] >>> i11) & 255;
            int i12 = 1;
            int i13 = (1 + i) - 1;
            while (i12 < i13) {
                iArr3[i12] = ((iArr[i12] >>> i11) & 255) + iArr3[i12 - 1];
                i12++;
            }
            int i14 = i12;
            int i15 = i2;
            while (true) {
                i15--;
                if (i15 >= 1) {
                    int i16 = i14 + 1;
                    iArr3[i14] = ((iArr[i16] >>> i11) & 255) + iArr3[i16 - i];
                    int i17 = (i16 + i) - 1;
                    while (i16 < i17) {
                        iArr3[i16] = ((((iArr[i16] >>> i11) & 255) + iArr3[i16 - 1]) - iArr3[(i16 - i) - 1]) + iArr3[i16 - i];
                        i16++;
                    }
                    i14 = i16;
                }
            }
        }
        int[] iArr4 = this.mBuffer[6];
        iArr4[0] = iArr[0] & 255;
        int i18 = 1;
        int i19 = (1 + i) - 1;
        while (i18 < i19) {
            iArr4[i18] = (iArr[i18] & 255) + iArr4[i18 - 1];
            i18++;
        }
        int i20 = i18;
        int i21 = i2;
        while (true) {
            i21--;
            if (i21 < 1) {
                return;
            }
            int i22 = i20 + 1;
            iArr4[i20] = (iArr[i22] & 255) + iArr4[i22 - i];
            int i23 = (i22 + i) - 1;
            while (i22 < i23) {
                iArr4[i22] = (((iArr[i22] & 255) + iArr4[i22 - 1]) - iArr4[(i22 - i) - 1]) + iArr4[i22 - i];
                i22++;
            }
            i20 = i22;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x0367  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x02c0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x02ae A[PHI: r24
  0x02ae: PHI (r24v7 int) = (r24v1 int), (r24v10 int) binds: [B:56:0x0244, B:78:0x02ac] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x02b4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void doRotation(int r35, int r36, int r37) {
        /*
            Method dump skipped, instructions count: 893
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.digitalchocolate.androidrollergapp.GraphicsBuffer.doRotation(int, int, int):void");
    }

    private void drawBuffer(Graphics graphics, int[] iArr, int i, int i2, int i3, int i4) {
        int clipY;
        int clipHeight;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int clipX = graphics.getClipX();
        int clipWidth = graphics.getClipWidth() + clipX;
        if (clipWidth > 0 && (clipHeight = graphics.getClipHeight() + (clipY = graphics.getClipY())) > 0) {
            if (clipY < 0) {
                clipY = 0;
            }
            if (clipX < 0) {
                clipX = 0;
            }
            if (i >= clipWidth || i2 >= clipHeight || i + i3 <= clipX || i2 + i4 <= clipY) {
                return;
            }
            int i11 = i + i3 > clipWidth ? i3 - ((i + i3) - clipWidth) : i3;
            int i12 = i2 + i4 > clipHeight ? i4 - ((i2 + i4) - clipHeight) : i4;
            if (i < clipX) {
                i5 = i11 + (i - clipX);
                i6 = 0 - (i - clipX);
                i7 = clipX;
            } else {
                i5 = i11;
                i6 = 0;
                i7 = i;
            }
            if (i2 < clipY) {
                i8 = (i2 - clipY) + i12;
                i9 = 0 - (i2 - clipY);
                i10 = clipY;
            } else {
                i8 = i12;
                i9 = 0;
                i10 = i2;
            }
            if (this.mRenderMode == 0) {
                graphics.drawRGB(iArr, (i9 * i3) + i6, i3, i7, i10, i5, i8, true);
                return;
            }
            allocateBuffer(2, i3 * i4, true);
            int[] iArr2 = this.mBuffer[2];
            this.mRenderTarget.getRGB(iArr2, (i9 * i3) + i6, i3, i7, i10, i5, i8);
            if (this.mRenderMode != 1) {
                int i13 = i3 * i4;
                while (true) {
                    i13--;
                    if (i13 < 0) {
                        break;
                    }
                    int i14 = iArr2[i13];
                    int i15 = iArr[i13];
                    int i16 = i15 >>> 24;
                    int i17 = ((16711935 & i15) * i16) >> 8;
                    int i18 = (16711680 & i14) - (16711680 & i17);
                    if (i18 < 0) {
                        i18 = 0;
                    }
                    int i19 = (65280 & i14) - ((((i15 & 65280) * i16) >> 8) & 65280);
                    if (i19 < 0) {
                        i19 = 0;
                    }
                    int i20 = (i14 & 255) - (i17 & 255);
                    if (i20 < 0) {
                        i20 = 0;
                    }
                    iArr2[i13] = i20 | i19 | i18;
                }
            } else {
                int i21 = i3 * i4;
                while (true) {
                    i21--;
                    if (i21 < 0) {
                        break;
                    }
                    int i22 = iArr2[i21];
                    int i23 = iArr[i21];
                    int i24 = i23 >>> 24;
                    int i25 = ((16711935 & i23) * i24) >> 8;
                    int i26 = (16711680 & i22) + (16711680 & i25);
                    if (i26 > 16711680) {
                        i26 = 16711680;
                    }
                    int i27 = ((((i23 & 65280) * i24) >> 8) & 65280) + (65280 & i22);
                    if (i27 > 65280) {
                        i27 = 65280;
                    }
                    int i28 = (i22 & 255) + (i25 & 255);
                    if (i28 > 255) {
                        i28 = 255;
                    }
                    iArr2[i21] = i28 | i27 | i26;
                }
            }
            graphics.drawRGB(iArr2, (i9 * i3) + i6, i3, i7, i10, i5, i8, false);
        }
    }

    private void fixTransparentPixels(int[] iArr, int i, int i2) {
        int i3 = smTransparentColor[0];
        int i4 = i * i2;
        while (true) {
            i4--;
            if (i4 < 0) {
                return;
            }
            if (iArr[i4] == i3) {
                iArr[i4] = 0;
            }
        }
    }

    private int[] getPixelBuffer() {
        return this.mEffectParam[0] != -8355712 ? this.mBuffer[0] : this.mBuffer[8];
    }

    private boolean readPixelsAgain() {
        return this.mLastEffectParam == null || this.mEffectParam[5] != this.mLastEffectParam[5];
    }

    public void createBuffer(int i, int i2, int i3, int i4) {
        int height;
        int width;
        this.mImageSource = false;
        this.mFramePivotX = i3;
        this.mFramePivotY = i4;
        this.mFrameWidth = i;
        this.mFrameHeight = i2;
        if (this.mBufferImage != null) {
            width = this.mBufferImage.getWidth();
            height = this.mBufferImage.getHeight();
        } else {
            height = 0;
            width = 0;
        }
        if (i > width || i2 > height) {
            this.mBufferImage = Image.createImage(i, i2);
            this.mBufferGraphics = this.mBufferImage.getGraphics();
        }
        allocateBuffer(8, i * i2, false);
        if (smTransparentColor == null) {
            smTransparentColor = new int[]{TRANSPARENT_COLOR};
            Image.createRGBImage(smTransparentColor, 1, 1, false).getRGB(smTransparentColor, 0, 1, 0, 0, 1, 1);
        }
        this.mBufferGraphics.setColor(smTransparentColor[0]);
        this.mBufferGraphics.fillRect(0, 0, i, i2);
        int[] iArr = this.mEffectParam;
        iArr[5] = iArr[5] + 1;
    }

    public void createBuffer(Image image, int i, int i2, int i3) {
        this.mImageSource = true;
        this.mFramePivotX = i;
        this.mFramePivotY = i2;
        this.mFrameWidth = image.getWidth();
        this.mFrameHeight = image.getHeight();
        allocateBuffer(8, this.mFrameWidth * this.mFrameHeight, false);
        image.getRGB(this.mBuffer[8], 0, this.mFrameWidth, 0, 0, this.mFrameWidth, this.mFrameHeight);
        transformBuffer(i3);
        int[] iArr = this.mEffectParam;
        iArr[5] = iArr[5] + 1;
    }

    public int[] doBlur(int[] iArr, int i, int i2, int i3, int i4) {
        int i5 = i3 < 1 ? 1 : i3;
        int i6 = i4 < 1 ? 1 : i4;
        doBlurPreComputation(iArr, i, i2);
        int[] iArr2 = this.mBuffer[3];
        int i7 = 65536 / (((i5 << 1) + 1) * ((i6 << 1) + 1));
        int[] iArr3 = this.mBuffer[7];
        int[] iArr4 = this.mBuffer[4];
        int[] iArr5 = this.mBuffer[5];
        int[] iArr6 = this.mBuffer[6];
        int i8 = 0;
        int i9 = 0;
        while (i9 < i2) {
            int i10 = i9 - i6;
            if (i10 < 0) {
                i10 = 0;
            }
            int i11 = i9 + i6;
            if (i11 >= i2) {
                i11 = i2 - 1;
            }
            int i12 = i10 * i;
            int i13 = i11 * i;
            int i14 = i8;
            int i15 = 0;
            while (i15 < i) {
                int i16 = i15 - i5;
                if (i16 < 0) {
                    i16 = 0;
                }
                int i17 = i15 + i5;
                if (i17 >= i) {
                    i17 = i - 1;
                }
                int i18 = i17 + i13;
                int i19 = i16 + i12;
                int i20 = i16 + i13;
                int i21 = i17 + i12;
                iArr2[i14] = (((((iArr3[i18] + iArr3[i19]) - iArr3[i20]) - iArr3[i21]) * i7) >> 16) << 24;
                iArr2[i14] = (((((iArr4[i18] + iArr4[i19]) - iArr4[i20]) - iArr4[i21]) * i7) & 16711680) | iArr2[i14];
                iArr2[i14] = ((((((iArr5[i18] + iArr5[i19]) - iArr5[i20]) - iArr5[i21]) * i7) >> 16) << 8) | iArr2[i14];
                iArr2[i14] = (((((iArr6[i18] + iArr6[i19]) - iArr6[i20]) - iArr6[i21]) * i7) >> 16) | iArr2[i14];
                i15++;
                i14++;
            }
            i9++;
            i8 = i14;
        }
        return iArr2;
    }

    public void draw(Graphics graphics, int i, int i2) {
        int[] iArr;
        int i3;
        int i4;
        boolean z = true;
        if (this.mLastEffectParam != null) {
            boolean z2 = false;
            int i5 = 6;
            while (true) {
                i5--;
                if (i5 < 0) {
                    break;
                } else if (this.mLastEffectParam[i5] != this.mEffectParam[i5]) {
                    z2 = true;
                    break;
                }
            }
            if (!z2) {
                z = false;
            }
        } else {
            this.mLastEffectParam = new int[6];
        }
        int i6 = this.mFrameWidth;
        int i7 = this.mEffectParam[3];
        int i8 = (i6 * i7) >> 10;
        if (i8 <= 0) {
            i8 = 1;
        }
        int i9 = this.mFrameHeight;
        int i10 = this.mEffectParam[4];
        int i11 = (i9 * i10) >> 10;
        if (i11 <= 0) {
            i11 = 1;
        }
        int i12 = this.mFramePivotX;
        int i13 = this.mFramePivotY;
        int[] pixelBuffer = getPixelBuffer();
        int i14 = this.mEffectParam[2];
        if (i14 != 0) {
            if (z) {
                doRotation(i14, i7, i10);
            }
            int iSin = DavinciUtilities.sin(i14);
            int iCos = DavinciUtilities.cos(i14);
            int i15 = (i7 * ((this.mFramePivotX << 1) - this.mFrameWidth)) >> 10;
            int i16 = (((this.mFramePivotY << 1) - this.mFrameHeight) * i10) >> 10;
            int i17 = this.mRotatedWidth;
            int i18 = this.mRotatedHeight;
            i12 = (((i15 * iCos) - (i16 * iSin)) + (i17 << 15)) >> 16;
            i13 = (((iSin * i15) + (iCos * i16)) + (i18 << 15)) >> 16;
            i4 = i18;
            iArr = this.mBuffer[1];
            i3 = i17;
        } else if (i7 == 1024 && i10 == 1024) {
            i4 = i9;
            i3 = i6;
            iArr = pixelBuffer;
        } else {
            allocateBuffer(1, i8 * i11, true);
            int[] iArr2 = this.mBuffer[1];
            if (z) {
                int i19 = (i6 << 10) / i8;
                int i20 = (i9 << 10) / i11;
                int i21 = 0;
                if (this.mEffectParam[1] != 0) {
                    int i22 = 0;
                    int i23 = i11;
                    while (true) {
                        i23--;
                        if (i23 < 0) {
                            break;
                        }
                        int i24 = ((i22 >> 10) * i6) << 10;
                        int i25 = 0;
                        int i26 = i22 >> 2;
                        int i27 = i21;
                        int i28 = i8;
                        while (true) {
                            i28--;
                            if (i28 >= 0) {
                                iArr2[i27] = DavinciUtilities.bilinearFilter(pixelBuffer, i25 >> 2, i26, i6, i9);
                                i25 += i19;
                                i27++;
                            }
                        }
                        i22 += i20;
                        i21 = i27;
                    }
                } else {
                    int i29 = i11;
                    int i30 = 0;
                    int i31 = 0;
                    while (true) {
                        i29--;
                        if (i29 < 0) {
                            break;
                        }
                        int i32 = ((i30 >> 10) * i6) << 10;
                        int i33 = i31;
                        int i34 = i8;
                        while (true) {
                            i34--;
                            if (i34 >= 0) {
                                int i35 = i33 + 1;
                                iArr2[i33] = pixelBuffer[i32 >> 10];
                                i32 += i19;
                                if (i34 > 8) {
                                    int i36 = i35 + 1;
                                    iArr2[i35] = pixelBuffer[i32 >> 10];
                                    int i37 = i32 + i19;
                                    int i38 = i36 + 1;
                                    iArr2[i36] = pixelBuffer[i37 >> 10];
                                    int i39 = i37 + i19;
                                    int i40 = i38 + 1;
                                    iArr2[i38] = pixelBuffer[i39 >> 10];
                                    int i41 = i39 + i19;
                                    int i42 = i40 + 1;
                                    iArr2[i40] = pixelBuffer[i41 >> 10];
                                    int i43 = i41 + i19;
                                    int i44 = i42 + 1;
                                    iArr2[i42] = pixelBuffer[i43 >> 10];
                                    int i45 = i43 + i19;
                                    int i46 = i44 + 1;
                                    iArr2[i44] = pixelBuffer[i45 >> 10];
                                    int i47 = i45 + i19;
                                    int i48 = i46 + 1;
                                    iArr2[i46] = pixelBuffer[i47 >> 10];
                                    int i49 = i47 + i19;
                                    iArr2[i48] = pixelBuffer[i49 >> 10];
                                    i32 = i49 + i19;
                                    i34 -= 8;
                                    i33 = i48 + 1;
                                } else {
                                    i33 = i35;
                                }
                            }
                        }
                        i30 += i20;
                        i31 = i33;
                    }
                }
            }
            i13 = (i13 * i10) >> 10;
            i12 = (i12 * i7) >> 10;
            iArr = iArr2;
            i3 = i8;
            i4 = i11;
        }
        drawBuffer(graphics, (!z || (this.mBlurBoxWidth <= 1 && this.mBlurBoxHeight <= 1)) ? iArr : doBlur(iArr, i3, i4, this.mBlurBoxWidth, this.mBlurBoxHeight), i - i12, i2 - i13, i3, i4);
        copyEffectParams();
    }

    public void draw(Graphics graphics, Image image, int i, int i2, int i3) {
        int i4 = this.mRenderMode;
        Image image2 = this.mRenderTarget;
        setRenderMode(i, image);
        draw(graphics, i2, i3);
        setRenderMode(i4, image2);
    }

    public void freeImageBuffer() {
        this.mBufferImage = null;
        this.mBufferGraphics = null;
    }

    public void freeResources() {
        int length = this.mBuffer.length;
        while (true) {
            length--;
            if (length < 0) {
                this.mLastEffectParam = null;
                freeImageBuffer();
                return;
            }
            this.mBuffer[length] = null;
        }
    }

    public int getColorModification() {
        return this.mEffectParam[0];
    }

    public Graphics getGraphics() {
        return this.mBufferGraphics;
    }

    public int[] getPreviousEffectParams() {
        return this.mLastEffectParam;
    }

    public int getRotationAngle() {
        return this.mEffectParam[2];
    }

    public int getScaleHeight() {
        return this.mEffectParam[4];
    }

    public int getScaleWidth() {
        return this.mEffectParam[3];
    }

    public void prepareFrame(int i, int i2, int i3, int i4, boolean z) {
        createBuffer(i, i2, i3, i4);
    }

    public void prepareFrame(Image image, int i, int i2) {
        createBuffer(image, i, i2, 0);
    }

    public void setBilinearFiltering(boolean z) {
        this.mEffectParam[1] = z ? 1 : 0;
    }

    public void setBlur(int i, int i2) {
        if (i == this.mBlurBoxWidth && i2 == this.mBlurBoxHeight) {
            return;
        }
        this.mBlurBoxWidth = i;
        this.mBlurBoxHeight = i2;
    }

    public void setRenderMode(int i, Image image) {
        this.mRenderMode = i;
        this.mRenderTarget = image;
    }

    public void setRotation(int i) {
        this.mEffectParam[2] = i;
    }

    public void setScale(int i) {
        setScale(i, i);
    }

    public void setScale(int i, int i2) {
        this.mEffectParam[3] = i;
        this.mEffectParam[4] = i2;
    }

    public void transformBuffer(int i) {
        DavinciUtilities.transformBuffer(this.mBuffer[8], this.mFrameWidth, this.mFrameHeight, i);
    }

    public void updateBufferARGB(int i) {
        this.mEffectParam[0] = i;
        int[] iArr = this.mBuffer[8];
        if (!this.mImageSource && readPixelsAgain()) {
            this.mBufferImage.getRGB(iArr, 0, this.mFrameWidth, 0, 0, this.mFrameWidth, this.mFrameHeight);
            fixTransparentPixels(iArr, this.mFrameWidth, this.mFrameHeight);
        }
        if (i != -8355712) {
            allocateBuffer(0, this.mFrameWidth * this.mFrameHeight, false);
            int[] iArr2 = this.mBuffer[0];
            int i2 = (-16777216) & i;
            int i3 = ((16711680 & i) - 8388608) << 1;
            if (i3 == 16646144) {
                i3 = 16711680;
            }
            int i4 = ((65280 & i) - 32768) << 1;
            if (i4 == 65024) {
                i4 = 65280;
            }
            int i5 = ((i & 255) - 128) << 1;
            if (i5 == 254) {
                i5 = 255;
            }
            if (!this.mImageSource) {
                int i6 = this.mFrameWidth * this.mFrameHeight;
                while (true) {
                    i6--;
                    if (i6 < 0) {
                        break;
                    }
                    int i7 = iArr[i6];
                    if (((-16777216) & i7) == 0) {
                        iArr2[i6] = 0;
                    } else {
                        int i8 = (16711680 & i7) + i3;
                        if (i8 > 16711680) {
                            i8 = 16711680;
                        } else if (i8 < 0) {
                            i8 = 0;
                        }
                        int i9 = (65280 & i7) + i4;
                        if (i9 > 65280) {
                            i9 = 65280;
                        } else if (i9 < 0) {
                            i9 = 0;
                        }
                        int i10 = (i7 & 255) + i5;
                        if (i10 > 255) {
                            i10 = 255;
                        } else if (i10 < 0) {
                            i10 = 0;
                        }
                        iArr2[i6] = i10 | i8 | i2 | i9;
                    }
                }
            } else {
                int i11 = i2 >>> 24;
                if (i11 == 255) {
                    i11 = 256;
                }
                int i12 = i11 << 16;
                int i13 = this.mFrameWidth * this.mFrameHeight;
                while (true) {
                    i13--;
                    if (i13 < 0) {
                        break;
                    }
                    int i14 = iArr[i13];
                    int i15 = (16711680 & i14) + i3;
                    if (i15 > 16711680) {
                        i15 = 16711680;
                    } else if (i15 < 0) {
                        i15 = 0;
                    }
                    int i16 = (65280 & i14) + i4;
                    if (i16 > 65280) {
                        i16 = 65280;
                    } else if (i16 < 0) {
                        i16 = 0;
                    }
                    int i17 = (i14 & 255) + i5;
                    if (i17 > 255) {
                        i17 = 255;
                    } else if (i17 < 0) {
                        i17 = 0;
                    }
                    iArr2[i13] = (((i14 >>> 24) * i12) & (-16777216)) | i15 | i16 | i17;
                }
            }
        }
        int[] iArr3 = this.mEffectParam;
        iArr3[5] = iArr3[5] + 1;
    }
}
