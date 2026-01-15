package com.digitalchocolate.androidrollergapp;

import com.mascotcapsule.micro3d.v3.Graphics3D;
import com.nokia.mid.ui.DirectGraphics;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* loaded from: classes.dex */
public class MIDP2RenderingPlatform implements IRenderingPlatform {
    private static final int BEZIER_POINT_PER_PIXELS = 8;
    private static final int BEZIER_STEP_FP = 15;
    private static final int BILINEAR_ACCURACY = 8;
    private static final int BUFFER_BLUR_CHANNEL = 4;
    private static final int BUFFER_BLUR_RESULT = 3;
    private static final int BUFFER_COLOR = 1;
    private static final int BUFFER_COUNT = 5;
    private static final int BUFFER_EXTRA = 120;
    private static final int BUFFER_MODIFIED = 2;
    private static final int BUFFER_ORIGINAL = 0;
    private static final int PARAM_ANGLE = 2;
    private static final int PARAM_BLUR_HEIGHT = 11;
    private static final int PARAM_BLUR_WIDTH = 10;
    private static final int PARAM_COLOR_MODIFICATION = 0;
    private static final int PARAM_COUNT = 13;
    private static final int PARAM_DO_GET_RGB = 5;
    private static final int PARAM_FILTERS = 1;
    private static final int PARAM_PIVOT_X = 7;
    private static final int PARAM_PIVOT_Y = 8;
    private static final int PARAM_PRIMITIVE_COLOR = 12;
    private static final int PARAM_RENDER_MODE = 9;
    private static final int PARAM_SCALE_HEIGHT = 4;
    private static final int PARAM_SCALE_WIDTH = 3;
    private static final int PARAM_TRANSFORMATION = 6;
    private static final int TEMP_BUFFER_BG = 2;
    private static final int TEMP_BUFFER_GENERIC = 0;
    private static final int TEMP_BUFFER_GENERIC_2 = 1;
    private static final int TEMP_BUFFER_LINE = 3;
    private static final int TRANSPARENT_COLOR = -65281;
    private static int[] smBezierPolygon;
    private static int[] smColorKey;
    private static int[] smEdgeDeltaX;
    private static int[] smEdgeEndY;
    private static int[] smEdgeLastX;
    private static int[] smEdgeStartX;
    private static int[] smEdgeStartY;
    private static int[] smEllipseX;
    private static int[] smPolygonEdge;
    private static int[] smStopPoint;
    private static int[][] smTempBuffer = new int[4][];
    private static Graphics smTempColorKeyGraphics;
    private static Image smTempColorKeyImage;
    private static IRenderingPlatform sm_renderingPlatform;
    private int[] mEffectParam = new int[13];
    private Vector mEffectStack;
    private int mFrameHeight;
    private int mFrameWidth;
    private Graphics mGraphicsContext;
    private Vector mGraphicsStack;
    private int[] mPolygonX;
    private int[] mPolygonY;
    private DChocImage mRenderTarget;
    private Vector mRenderTargetStack;
    private int mRotatedHeight;
    private int mRotatedMinX;
    private int mRotatedMinY;
    private int mRotatedWidth;
    private int[] mRotatedX;
    private int[] mRotatedY;
    private Vector mUsableEffectStorage;

    public MIDP2RenderingPlatform() {
        disableAllEffects();
    }

    private void allocateBuffer(int i, int i2, boolean z, int[][] iArr) {
        if (iArr[i] == null || i2 > iArr[i].length) {
            iArr[i] = new int[z ? (i2 * 120) / 100 : i2];
        }
    }

    private int bezierPoint(int i, int i2, int i3, int i4, int i5) {
        int i6 = 32768 - i5;
        int i7 = (i6 * i6) >> 15;
        int i8 = (i5 * i5) >> 15;
        return (((((i6 * (i8 * 3)) >> 15) * i3) + ((((i7 * (i5 * 3)) >> 15) * i2) + (((i7 * i6) >> 15) * i))) + (((i8 * i5) >> 15) * i4)) >> 15;
    }

    private int bilinearFilter(int[] iArr, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9 = i >> 8;
        int i10 = i2 >> 8;
        int i11 = (i10 * i3) + i9;
        if (i9 != -1 && i10 != -1) {
            i5 = iArr[i11];
            int i12 = i9 < i3 - 1 ? iArr[i11 + 1] : 16777215 & i5;
            int i13 = 16777215 & i12;
            int i14 = 16777215 & i5;
            if (i10 < i4 - 1) {
                int i15 = i11 + i3;
                i6 = i12;
                int i16 = i9 < i3 - 1 ? iArr[i15 + 1] : i13;
                i8 = iArr[i15];
                i7 = i16;
            } else {
                i8 = i14;
                i7 = i13;
                i6 = i12;
            }
        } else if (i9 == -1) {
            int i17 = i11 + 1;
            if (i10 == -1) {
                int i18 = iArr[i17 + i3];
                int i19 = 16777215 & i18;
                i6 = i19;
                i5 = i19;
                i7 = i18;
                i8 = i19;
            } else {
                i6 = iArr[i17];
                i5 = 16777215 & i6;
                int i20 = i10 < i4 - 1 ? iArr[i17 + i3] : i5;
                i7 = i20;
                i8 = 16777215 & i20;
            }
        } else {
            int i21 = i11 + i3;
            int i22 = iArr[i21];
            i5 = 16777215 & i22;
            int i23 = i9 < i3 - 1 ? iArr[i21 + 1] : i5;
            i6 = 16777215 & i23;
            i7 = i23;
            i8 = i22;
        }
        int i24 = i2 & 255;
        int i25 = i & 255;
        int i26 = i5 >>> 24;
        int i27 = i26 + ((((i8 >>> 24) - i26) * i24) >> 8);
        int i28 = i6 >>> 24;
        int i29 = (i27 + ((((i28 + ((((i7 >>> 24) - i28) * i24) >> 8)) - i27) * i25) >> 8)) << 24;
        int i30 = 16711935 & i5;
        int i31 = (i30 + ((((16711935 & i8) - i30) * i24) >> 8)) & 16711935;
        int i32 = 16711935 & i6;
        int i33 = i5 & 65280;
        int i34 = ((((i8 & 65280) - i33) * i24) >> 8) + i33;
        int i35 = i6 & 65280;
        return ((i34 + ((((((((i7 & 65280) - i35) * i24) >> 8) + i35) - i34) * i25) >> 8)) & 65280) | i29 | ((i31 + (((((i32 + ((((16711935 & i7) - i32) * i24) >> 8)) & 16711935) - i31) * i25) >> 8)) & 16711935);
    }

    private void calculateRotatedFrameSize(int i, int i2, int i3) {
        int iSin = MathUtils.sin(i);
        int iCos = MathUtils.cos(i);
        int i4 = this.mFrameWidth;
        int i5 = this.mFrameHeight;
        int i6 = ((-i4) * i2) >> 10;
        int i7 = (((i4 << 1) * i2) >> 10) + i6;
        int i8 = ((-i5) * i3) >> 10;
        int i9 = (((i5 << 1) * i3) >> 10) + i8;
        this.mRotatedX[0] = (i6 * iCos) - (i8 * iSin);
        this.mRotatedY[0] = (i8 * iCos) + (i6 * iSin);
        this.mRotatedX[1] = (i7 * iCos) - (i8 * iSin);
        this.mRotatedY[1] = (i8 * iCos) + (i7 * iSin);
        this.mRotatedX[2] = (i7 * iCos) - (i9 * iSin);
        this.mRotatedY[2] = (i7 * iSin) + (i9 * iCos);
        this.mRotatedX[3] = (i6 * iCos) - (i9 * iSin);
        this.mRotatedY[3] = (iSin * i6) + (iCos * i9);
        int i10 = AnimationFrame.MAX_DURATION;
        int i11 = Integer.MAX_VALUE;
        int i12 = 4;
        int i13 = Integer.MIN_VALUE;
        int i14 = Integer.MIN_VALUE;
        while (true) {
            i12--;
            if (i12 < 0) {
                break;
            }
            if (this.mRotatedX[i12] > i14) {
                i14 = this.mRotatedX[i12];
            }
            if (this.mRotatedX[i12] < i11) {
                i11 = this.mRotatedX[i12];
            }
            if (this.mRotatedY[i12] > i13) {
                i13 = this.mRotatedY[i12];
            }
            if (this.mRotatedY[i12] < i10) {
                i10 = this.mRotatedY[i12];
            }
        }
        int i15 = i11 >> 16;
        int i16 = i10 >> 16;
        int i17 = (i13 >> 16) + 1;
        this.mRotatedWidth = ((i14 >> 16) + 1) - i15;
        if (this.mRotatedWidth == 0) {
            this.mRotatedWidth = 1;
        }
        this.mRotatedHeight = i17 - i16;
        if (this.mRotatedHeight == 0) {
            this.mRotatedHeight = 1;
        }
        this.mRotatedMinX = i15;
        this.mRotatedMinY = i16;
    }

    private void createPixelBuffer(DChocImage dChocImage, int i) {
        int i2 = this.mFrameWidth * this.mFrameHeight;
        int[][] buffer = dChocImage.getBuffer();
        allocateBuffer(0, i2, false, buffer);
        dChocImage.getRGB(buffer[0], 0, this.mFrameWidth, 0, 0, this.mFrameWidth, this.mFrameHeight);
        if (dChocImage.isMutable()) {
            fixTransparentPixels(buffer[0], this.mFrameWidth, this.mFrameHeight);
        }
        transformBuffer(buffer[0], this.mFrameWidth, this.mFrameHeight, i);
    }

    private int[] doBlur(int[] iArr, int i, int i2, int i3, int i4, int[][] iArr2) {
        int i5 = i3 < 1 ? 1 : i3;
        int i6 = i4 < 1 ? 1 : i4;
        int i7 = i * i2;
        allocateBuffer(3, i7, true, iArr2);
        allocateBuffer(4, i7, true, iArr2);
        int[] iArr3 = iArr2[3];
        int[] iArr4 = iArr2[4];
        int i8 = 65536 / (((i5 << 1) + 1) * ((i6 << 1) + 1));
        while (true) {
            i7--;
            if (i7 < 0) {
                break;
            }
            iArr3[i7] = 0;
        }
        int i9 = 0;
        while (true) {
            int i10 = i9;
            if (i10 > 24) {
                return iArr3;
            }
            doBlurPreComputation(iArr, i, i2, iArr4, i10);
            int i11 = 0;
            int i12 = 0;
            while (i12 < i2) {
                int i13 = i12 - i6;
                if (i13 < 0) {
                    i13 = 0;
                }
                int i14 = i12 + i6;
                if (i14 >= i2) {
                    i14 = i2 - 1;
                }
                int i15 = i13 * i;
                int i16 = i14 * i;
                int i17 = i11;
                int i18 = 0;
                while (i18 < i) {
                    int i19 = i18 - i5;
                    if (i19 < 0) {
                        i19 = 0;
                    }
                    int i20 = i18 + i5;
                    if (i20 >= i) {
                        i20 = i - 1;
                    }
                    iArr3[i17] = ((((((iArr4[i20 + i16] + iArr4[i19 + i15]) - iArr4[i19 + i16]) - iArr4[i20 + i15]) * i8) >> 16) << i10) | iArr3[i17];
                    i18++;
                    i17++;
                }
                i12++;
                i11 = i17;
            }
            i9 = i10 + 8;
        }
    }

    private void doBlurPreComputation(int[] iArr, int i, int i2, int[] iArr2, int i3) {
        iArr2[0] = (iArr[0] >>> i3) & 255;
        int i4 = (1 + i) - 1;
        int i5 = 1;
        while (i5 < i4) {
            iArr2[i5] = ((iArr[i5] >>> i3) & 255) + iArr2[i5 - 1];
            i5++;
        }
        int i6 = i2;
        while (true) {
            i6--;
            if (i6 < 1) {
                return;
            }
            int i7 = i5 + 1;
            iArr2[i5] = ((iArr[i7] >>> i3) & 255) + iArr2[i7 - i];
            int i8 = (i7 + i) - 1;
            while (i7 < i8) {
                iArr2[i7] = ((((iArr[i7] >>> i3) & 255) + iArr2[i7 - 1]) - iArr2[(i7 - i) - 1]) + iArr2[i7 - i];
                i7++;
            }
            i5 = i7;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x017e A[PHI: r23
  0x017e: PHI (r23v13 int) = (r23v1 int), (r23v16 int) binds: [B:30:0x0118, B:53:0x017c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0190 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void doRotation(int r34, int r35, int r36, int[][] r37) {
        /*
            Method dump skipped, instructions count: 547
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.digitalchocolate.androidrollergapp.MIDP2RenderingPlatform.doRotation(int, int, int, int[][]):void");
    }

    private static void drawAlphaHLine(int[] iArr, int i, int i2, int i3, boolean z) {
        int i4;
        int i5;
        int i6 = 16777215 & i3;
        if (i2 == 1) {
            iArr[i] = i6 | Graphics3D.COMMAND_END | iArr[i];
            return;
        }
        int i7 = 65535 / (i2 + 1);
        if (z) {
            i4 = i7;
            i5 = 0;
        } else {
            i4 = -i7;
            i5 = 65535;
        }
        int i8 = i5 + i4;
        int i9 = i2 + i;
        for (int i10 = i; i10 < i9; i10++) {
            iArr[i10] = iArr[i10] | ((i8 >> 8) << 24) | i6;
            i8 += i4;
        }
    }

    private void drawBuffer(int[] iArr, int i, int i2, int i3, int i4) {
        int clipX;
        int clipWidth;
        int clipY;
        int clipHeight;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        if (i3 <= 0 || i4 <= 0 || (clipWidth = getClipWidth() + (clipX = getClipX())) <= 0 || (clipHeight = getClipHeight() + (clipY = getClipY())) <= 0) {
            return;
        }
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
        if (i < clipX) {
            i5 = i11 + (i - clipX);
            i6 = 0 - (i - clipX);
            i7 = clipX;
        } else {
            i5 = i11;
            i6 = 0;
            i7 = i;
        }
        int i12 = i2 + i4 > clipHeight ? i4 - ((i2 + i4) - clipHeight) : i4;
        if (i2 < clipY) {
            i8 = i12 + (i2 - clipY);
            i9 = 0 - (i2 - clipY);
            i10 = clipY;
        } else {
            i8 = i12;
            i9 = 0;
            i10 = i2;
        }
        int renderMode = getRenderMode();
        if (renderMode == 0) {
            this.mGraphicsContext.drawRGB(iArr, (i9 * i3) + i6, i3, i7, i10, i5, i8, true);
            return;
        }
        if (this.mRenderTarget != null) {
            allocateBuffer(2, i3 * i4, true, smTempBuffer);
            int[] iArr2 = smTempBuffer[2];
            this.mRenderTarget.getRGB(iArr2, (i9 * i3) + i6, i3, i7, i10, i5, i8);
            if (renderMode != 1) {
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
            this.mGraphicsContext.drawRGB(iArr2, (i9 * i3) + i6, i3, i7, i10, i5, i8, false);
        }
    }

    private void drawImageWithoutEffects(DChocImage dChocImage, int i, int i2, int i3) {
        int regionY;
        int regionHeight;
        int regionWidth;
        Image image = dChocImage.getImage();
        int pivotX = i + getPivotX();
        int pivotY = i2 + getPivotY();
        boolean zIsRegioned = dChocImage.isRegioned();
        if (i3 != 0) {
            int i4 = (i3 & 1) != 0 ? 2 : 0;
            if ((i3 & 2) != 0) {
                i4 |= 1;
            }
            int i5 = (i3 & 4) != 0 ? i4 | 5 : (i3 & 8) != 0 ? i4 | 3 : (i3 & 16) != 0 ? i4 | 6 : i4;
            if (zIsRegioned) {
                this.mGraphicsContext.drawRegion(image, dChocImage.getRegionX(), dChocImage.getRegionY(), dChocImage.getRegionWidth(), dChocImage.getRegionHeight(), i5, pivotX, pivotY, 20);
                return;
            } else {
                this.mGraphicsContext.drawRegion(image, 0, 0, dChocImage.getWidth(), dChocImage.getHeight(), i5, pivotX, pivotY, 20);
                return;
            }
        }
        int clipX = 0;
        int clipY = 0;
        int clipWidth = 0;
        int clipHeight = 0;
        if (zIsRegioned) {
            clipX = this.mGraphicsContext.getClipX();
            clipY = this.mGraphicsContext.getClipY();
            clipWidth = this.mGraphicsContext.getClipWidth();
            clipHeight = this.mGraphicsContext.getClipHeight();
            if ((i3 & 4) == 0 && (i3 & 16) == 0) {
                regionHeight = dChocImage.getRegionWidth();
                regionWidth = dChocImage.getRegionHeight();
            } else {
                regionHeight = dChocImage.getRegionHeight();
                regionWidth = dChocImage.getRegionWidth();
            }
            if (pivotX + regionHeight <= clipX || pivotY + regionWidth <= clipY || pivotX >= clipX + clipWidth || pivotY >= clipY + clipHeight) {
                return;
            } else {
                this.mGraphicsContext.clipRect(pivotX, pivotY, regionHeight, regionWidth);
            }
        }
        int i6 = clipHeight;
        int i7 = clipX;
        int i8 = clipY;
        int i9 = clipWidth;
        if (i3 == 0) {
            if (zIsRegioned) {
                pivotX -= dChocImage.getRegionX();
                regionY = pivotY - dChocImage.getRegionY();
            } else {
                regionY = pivotY;
            }
            this.mGraphicsContext.drawImage(image, pivotX, regionY, 20);
        }
        if (zIsRegioned) {
            this.mGraphicsContext.setClip(i7, i8, i9, i6);
        }
    }

    private void fixTransparentPixels(int[] iArr, int i, int i2) {
        int colorKey = getColorKey();
        int i3 = i * i2;
        while (true) {
            i3--;
            if (i3 < 0) {
                return;
            }
            if (iArr[i3] == colorKey) {
                iArr[i3] = 0;
            }
        }
    }

    private int getColorKey() {
        if (smColorKey == null) {
            setColorKey(TRANSPARENT_COLOR);
        }
        return smColorKey[0];
    }

    private int getColorModification(int[] iArr) {
        return iArr[0];
    }

    private DirectGraphics getDirectGraphics() {
        return null;
    }

    private int[] getPixelBuffer(int[][] iArr) {
        return this.mEffectParam[0] != -8355712 ? iArr[1] : iArr[0];
    }

    private int getRange(int i, int i2) {
        int iAbs = Math.abs(i2);
        int iAbs2 = Math.abs(i);
        return iAbs2 > iAbs ? ((iAbs * TextIDs.TID_HELP_CARTS_CONTENT) >> 8) + iAbs2 : iAbs + ((iAbs2 * TextIDs.TID_HELP_CARTS_CONTENT) >> 8);
    }

    public static IRenderingPlatform getRenderingPlatform() {
        if (sm_renderingPlatform == null) {
            sm_renderingPlatform = new MIDP2RenderingPlatform();
        }
        return sm_renderingPlatform;
    }

    private int getRotation(int[] iArr) {
        return iArr[2];
    }

    private int getScaleHeight(int[] iArr) {
        return iArr[4];
    }

    private int getScaleWidth(int[] iArr) {
        return iArr[3];
    }

    private boolean isAnyEffectEnabled() {
        return (getColorModification() == -8355712 && getScaleWidth() == 1024 && getScaleHeight() == 1024 && getRotation() == 0 && getRenderMode() == 0 && getBlurWidth() <= 1 && getBlurHeight() <= 1) ? false : true;
    }

    private boolean renderPrimitiveWithAPI() {
        return getColorModification() == -8355712 && !isFilterEnabled(2) && getRenderMode() == 0 && getBlurWidth() <= 1 && getBlurHeight() <= 1;
    }

    private void transformBuffer(int[] iArr, int i, int i2, int i3) {
        if (i3 == 0) {
            return;
        }
        if ((i3 & 3) == 3) {
            int i4 = i * i2;
            int i5 = i4 >> 1;
            int i6 = 0;
            for (int i7 = i4 - 1; i7 >= i5; i7--) {
                int i8 = iArr[i7];
                iArr[i7] = iArr[i6];
                iArr[i6] = i8;
                i6++;
            }
        } else if ((i3 & 1) != 0) {
            int i9 = i >> 1;
            int i10 = i2;
            int i11 = 0;
            while (true) {
                i10--;
                if (i10 < 0) {
                    break;
                }
                int i12 = i11 + i9;
                int i13 = i11;
                int i14 = (i11 + i) - 1;
                while (i13 < i12) {
                    int i15 = iArr[i13];
                    iArr[i13] = iArr[i14];
                    iArr[i14] = i15;
                    i14--;
                    i13++;
                }
                i11 = (i - i9) + i13;
            }
        } else if ((i3 & 2) != 0) {
            int i16 = (i2 - 1) * i;
            int i17 = (i2 >> 1) * i;
            int i18 = i;
            while (true) {
                i18--;
                if (i18 < 0) {
                    break;
                }
                int i19 = i16 + i18;
                int i20 = i18 + i17;
                int i21 = i18;
                while (i21 < i20) {
                    int i22 = iArr[i21];
                    iArr[i21] = iArr[i19];
                    iArr[i19] = i22;
                    i21 += i;
                    i19 -= i;
                }
            }
        }
        if ((i3 & 8) != 0) {
            int i23 = i * i2;
            int i24 = i23 >> 1;
            int i25 = 0;
            for (int i26 = i23 - 1; i26 >= i24; i26--) {
                int i27 = iArr[i26];
                iArr[i26] = iArr[i25];
                iArr[i25] = i27;
                i25++;
            }
            return;
        }
        if ((i3 & 4) == 0 && (i3 & 16) == 0) {
            return;
        }
        allocateBuffer(0, i * i2, true, smTempBuffer);
        int[] iArr2 = smTempBuffer[0];
        int i28 = i * i2;
        while (true) {
            i28--;
            if (i28 < 0) {
                break;
            } else {
                iArr2[i28] = iArr[i28];
            }
        }
        if ((i3 & 4) != 0) {
            int i29 = 0;
            int i30 = 0;
            while (i29 < i) {
                int i31 = ((i2 - 1) * i) + i29;
                int i32 = i30;
                int i33 = i2;
                while (true) {
                    i33--;
                    if (i33 >= 0) {
                        iArr[i32] = iArr2[i31];
                        i31 -= i;
                        i32++;
                    }
                }
                i29++;
                i30 = i32;
            }
            return;
        }
        int i34 = i;
        int i35 = 0;
        while (true) {
            i34--;
            if (i34 < 0) {
                return;
            }
            int i36 = i34;
            int i37 = i35;
            int i38 = i2;
            while (true) {
                i38--;
                if (i38 >= 0) {
                    iArr[i37] = iArr2[i36];
                    i36 += i;
                    i37++;
                }
            }
            i35 = i37;
        }
    }

    private void updateBufferARGB(int i, DChocImage dChocImage) {
        if (i == -8355712) {
            return;
        }
        int[][] buffer = dChocImage.getBuffer();
        int[] iArr = buffer[0];
        allocateBuffer(1, this.mFrameWidth * this.mFrameHeight, false, buffer);
        int[] iArr2 = buffer[1];
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
        if (dChocImage.isMutable()) {
            if (i3 == 0 && i4 == 0 && i5 == 0) {
                int i6 = this.mFrameWidth * this.mFrameHeight;
                while (true) {
                    i6--;
                    if (i6 < 0) {
                        return;
                    }
                    int i7 = iArr[i6];
                    if (((-16777216) & i7) == 0) {
                        iArr2[i6] = 0;
                    } else {
                        iArr2[i6] = (i7 & 16777215) | i2;
                    }
                }
            } else {
                int i8 = this.mFrameWidth * this.mFrameHeight;
                while (true) {
                    i8--;
                    if (i8 < 0) {
                        return;
                    }
                    int i9 = iArr[i8];
                    if (((-16777216) & i9) == 0) {
                        iArr2[i8] = 0;
                    } else {
                        int i10 = (16711680 & i9) + i3;
                        if (i10 > 16711680) {
                            i10 = 16711680;
                        } else if (i10 < 0) {
                            i10 = 0;
                        }
                        int i11 = (65280 & i9) + i4;
                        if (i11 > 65280) {
                            i11 = 65280;
                        } else if (i11 < 0) {
                            i11 = 0;
                        }
                        int i12 = (i9 & 255) + i5;
                        if (i12 > 255) {
                            i12 = 255;
                        } else if (i12 < 0) {
                            i12 = 0;
                        }
                        iArr2[i8] = i12 | i10 | i2 | i11;
                    }
                }
            }
        } else if (i2 == 255) {
            int i13 = this.mFrameWidth * this.mFrameHeight;
            while (true) {
                i13--;
                if (i13 < 0) {
                    return;
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
                iArr2[i13] = (i14 & (-16777216)) | i15 | i16 | i17;
            }
        } else {
            int i18 = i2 >>> 8;
            if (i3 == 0 && i4 == 0 && i5 == 0) {
                int i19 = this.mFrameWidth * this.mFrameHeight;
                while (true) {
                    i19--;
                    if (i19 < 0) {
                        return;
                    }
                    int i20 = iArr[i19];
                    iArr2[i19] = (i20 & 16777215) | (((i20 >>> 24) * i18) & (-16777216));
                }
            } else {
                int i21 = this.mFrameWidth * this.mFrameHeight;
                while (true) {
                    i21--;
                    if (i21 < 0) {
                        return;
                    }
                    int i22 = iArr[i21];
                    int i23 = (16711680 & i22) + i3;
                    if (i23 > 16711680) {
                        i23 = 16711680;
                    } else if (i23 < 0) {
                        i23 = 0;
                    }
                    int i24 = (65280 & i22) + i4;
                    if (i24 > 65280) {
                        i24 = 65280;
                    } else if (i24 < 0) {
                        i24 = 0;
                    }
                    int i25 = (i22 & 255) + i5;
                    if (i25 > 255) {
                        i25 = 255;
                    } else if (i25 < 0) {
                        i25 = 0;
                    }
                    iArr2[i21] = (((i22 >>> 24) * i18) & (-16777216)) | i23 | i24 | i25;
                }
            }
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void clipRect(int i, int i2, int i3, int i4) {
        int clipX = getClipX();
        int clipY = getClipY();
        int clipWidth = getClipWidth();
        int clipHeight = getClipHeight();
        if (i + i3 <= clipX || i2 + i4 <= clipY || i >= clipX + clipWidth || i2 >= clipY + clipHeight) {
            this.mGraphicsContext.setClip(clipX, clipY, 0, 0);
            return;
        }
        int i5 = i + i3;
        int i6 = i5 > clipX + clipWidth ? clipWidth + clipX : i5;
        if (i >= clipX) {
            clipX = i;
        }
        int i7 = i2 + i4;
        int i8 = i7 > clipY + clipHeight ? clipHeight + clipY : i7;
        if (i2 >= clipY) {
            clipY = i2;
        }
        this.mGraphicsContext.setClip(clipX, clipY, i6 - clipX, i8 - clipY);
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void disableAllEffects() {
        setColorModification(-8355712);
        setRotation(0);
        setScale(1024, 1024);
        setRenderMode(0);
        setBlur(0, 0);
        setFilters(0);
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void drawImage(DChocImage dChocImage, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int width = (i4 & 1) != 0 ? i - (dChocImage.getWidth() >> 1) : (i4 & 8) != 0 ? i - dChocImage.getWidth() : i;
        int height = (i4 & 2) != 0 ? i2 - (dChocImage.getHeight() >> 1) : (i4 & 32) != 0 ? i2 - dChocImage.getHeight() : i2;
        if (!isAnyEffectEnabled()) {
            drawImageWithoutEffects(dChocImage, width, height, i3);
            return;
        }
        boolean z = true;
        int[] effectParams = dChocImage.getEffectParams();
        if (effectParams != null) {
            boolean z2 = false;
            int i9 = 13;
            while (true) {
                i9--;
                if (i9 >= 0) {
                    if (i9 != 12 && effectParams[i9] != this.mEffectParam[i9]) {
                        z2 = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (!z2) {
                z = false;
            }
        }
        int[][] buffer = dChocImage.getBuffer();
        if (buffer == null) {
            buffer = new int[5][];
            dChocImage.setBuffer(buffer);
        }
        int[][] iArr = buffer;
        this.mFrameWidth = dChocImage.getWidth();
        this.mFrameHeight = dChocImage.getHeight();
        if (effectParams == null || effectParams[5] != 0 || i3 != effectParams[6]) {
            createPixelBuffer(dChocImage, i3);
            z = true;
        }
        if ((i3 & 4) != 0 || (i3 & 16) != 0) {
            int i10 = this.mFrameWidth;
            this.mFrameWidth = this.mFrameHeight;
            this.mFrameHeight = i10;
        }
        int i11 = this.mFrameWidth;
        int scaleWidth = getScaleWidth();
        int scaleHeight = getScaleHeight();
        int i12 = (i11 * scaleWidth) >> 10;
        if (i12 >= 1) {
            int i13 = this.mFrameHeight;
            int i14 = (i13 * scaleHeight) >> 10;
            if (i14 >= 1) {
                int rotation = getRotation();
                int colorModification = getColorModification();
                if (z || effectParams == null || colorModification != getColorModification(effectParams)) {
                    updateBufferARGB(colorModification, dChocImage);
                    z = true;
                }
                int[] pixelBuffer = getPixelBuffer(iArr);
                boolean z3 = (effectParams != null && rotation == getRotation(effectParams) && scaleWidth == getScaleWidth(effectParams) && scaleHeight == getScaleHeight(effectParams)) ? z : true;
                if (rotation != 0) {
                    if (z3) {
                        doRotation(rotation, scaleWidth, scaleHeight, iArr);
                    } else {
                        calculateRotatedFrameSize(rotation, scaleWidth, scaleHeight);
                    }
                    int iSin = MathUtils.sin(rotation);
                    int iCos = MathUtils.cos(rotation);
                    int i15 = ((((-width) << 1) - this.mFrameWidth) * scaleWidth) >> 10;
                    int i16 = ((((-height) << 1) - this.mFrameHeight) * scaleHeight) >> 10;
                    int i17 = this.mRotatedWidth;
                    int i18 = this.mRotatedHeight;
                    int i19 = -((((i15 * iCos) - (i16 * iSin)) + (i17 << 15)) >> 16);
                    int i20 = -((((iSin * i15) + (iCos * i16)) + (i18 << 15)) >> 16);
                    pixelBuffer = iArr[2];
                    i5 = i18;
                    i6 = i17;
                    i8 = i19;
                    i7 = i20;
                } else if (scaleWidth == 1024 && scaleHeight == 1024) {
                    i5 = i13;
                    i6 = i11;
                    int i21 = height;
                    i8 = width;
                    i7 = i21;
                } else {
                    allocateBuffer(2, i12 * i14, true, iArr);
                    int[] iArr2 = iArr[2];
                    if (z3) {
                        int i22 = (i11 << 10) / i12;
                        int i23 = (i13 << 10) / i14;
                        int i24 = 0;
                        if (!isFilterEnabled(1)) {
                            int i25 = 0;
                            int i26 = i14;
                            while (true) {
                                i26--;
                                if (i26 < 0) {
                                    break;
                                }
                                int i27 = ((i25 >> 10) * i11) << 10;
                                int i28 = i24;
                                int i29 = i12;
                                while (true) {
                                    i29--;
                                    if (i29 >= 0) {
                                        int i30 = i28 + 1;
                                        iArr2[i28] = pixelBuffer[i27 >> 10];
                                        i27 += i22;
                                        if (i29 > 8) {
                                            int i31 = i30 + 1;
                                            iArr2[i30] = pixelBuffer[i27 >> 10];
                                            int i32 = i27 + i22;
                                            int i33 = i31 + 1;
                                            iArr2[i31] = pixelBuffer[i32 >> 10];
                                            int i34 = i32 + i22;
                                            int i35 = i33 + 1;
                                            iArr2[i33] = pixelBuffer[i34 >> 10];
                                            int i36 = i34 + i22;
                                            int i37 = i35 + 1;
                                            iArr2[i35] = pixelBuffer[i36 >> 10];
                                            int i38 = i36 + i22;
                                            int i39 = i37 + 1;
                                            iArr2[i37] = pixelBuffer[i38 >> 10];
                                            int i40 = i38 + i22;
                                            int i41 = i39 + 1;
                                            iArr2[i39] = pixelBuffer[i40 >> 10];
                                            int i42 = i40 + i22;
                                            int i43 = i41 + 1;
                                            iArr2[i41] = pixelBuffer[i42 >> 10];
                                            int i44 = i42 + i22;
                                            iArr2[i43] = pixelBuffer[i44 >> 10];
                                            i27 = i44 + i22;
                                            i29 -= 8;
                                            i28 = i43 + 1;
                                        } else {
                                            i28 = i30;
                                        }
                                    }
                                }
                                i25 += i23;
                                i24 = i28;
                            }
                        } else {
                            int i45 = 0;
                            int i46 = i14;
                            while (true) {
                                int i47 = i46 - 1;
                                if (i47 < 0) {
                                    break;
                                }
                                int i48 = ((i45 >> 10) * i11) << 10;
                                int i49 = i45 >> 2;
                                int i50 = 0;
                                int i51 = i24;
                                int i52 = i12;
                                while (true) {
                                    int i53 = i52 - 1;
                                    if (i53 >= 0) {
                                        iArr2[i51] = bilinearFilter(pixelBuffer, i50 >> 2, i49, i11, i13);
                                        i50 += i22;
                                        i51++;
                                        i52 = i53;
                                    }
                                }
                                i24 = i51;
                                i45 += i23;
                                i46 = i47;
                            }
                        }
                    }
                    int i54 = (width * scaleWidth) >> 10;
                    i5 = i14;
                    i6 = i12;
                    i7 = (height * scaleHeight) >> 10;
                    i8 = i54;
                    pixelBuffer = iArr2;
                }
                drawBuffer((getBlurWidth() > 1 || getBlurHeight() > 1) ? z3 ? doBlur(pixelBuffer, i6, i5, getBlurWidth(), getBlurHeight(), iArr) : iArr[3] : pixelBuffer, getPivotX() + i8, i7 + getPivotY(), i6, i5);
                this.mEffectParam[5] = 0;
                this.mEffectParam[6] = i3;
                dChocImage.copyEffectParam(this.mEffectParam);
            }
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void drawLine(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int scaleWidth = getScaleWidth();
        int scaleHeight = getScaleHeight();
        int pivotX = getPivotX();
        int pivotY = getPivotY();
        if (scaleWidth == 1024 && scaleHeight == 1024) {
            i6 = i4;
            i5 = i3;
            i8 = i2;
            i7 = i;
        } else {
            int i17 = (i * scaleWidth) >> 10;
            int i18 = (i2 * scaleHeight) >> 10;
            int i19 = (scaleHeight * i4) >> 10;
            i5 = (scaleWidth * i3) >> 10;
            i6 = i19;
            i7 = i17;
            i8 = i18;
        }
        if (getRotation() != 0) {
            int iCos = MathUtils.cos(getRotation());
            int iSin = MathUtils.sin(getRotation());
            int i20 = ((i7 * iCos) - (i8 * iSin)) >> 15;
            i8 = ((i8 * iCos) + (i7 * iSin)) >> 15;
            int i21 = ((i5 * iCos) - (i6 * iSin)) >> 15;
            i6 = ((i6 * iCos) + (i5 * iSin)) >> 15;
            i5 = i21;
            i7 = i20;
        }
        int i22 = i7 + pivotX;
        int i23 = pivotX + i5;
        int i24 = i8 + pivotY;
        int i25 = i6 + pivotY;
        if (!isFilterEnabled(2)) {
            this.mGraphicsContext.drawLine(i22, i24, i23, i25);
            return;
        }
        int iAbs = Math.abs(i23 - i22) + 3;
        int iAbs2 = Math.abs(i25 - i24) + 3;
        allocateBuffer(3, iAbs * iAbs2, true, smTempBuffer);
        int[] iArr = smTempBuffer[3];
        int color = getColor();
        int i26 = color >>> 24;
        if (i26 == 255) {
            i26 = 256;
        }
        int i27 = color & 16777215;
        if (iAbs >= iAbs2) {
            int i28 = iAbs - 1;
            if (i23 < i22) {
                i13 = i25;
                i14 = i23;
                i15 = i22;
                i16 = i24;
            } else {
                i13 = i24;
                i14 = i22;
                i25 = i24;
                i15 = i23;
                i23 = i22;
                i16 = i25;
            }
            int i29 = i15 - i14;
            int i30 = i29 != 0 ? ((i16 - i13) << 16) / i29 : 0;
            int i31 = i30 < 0 ? iAbs2 - 2 : 0;
            int i32 = i31 << 16;
            if (i30 <= 65535) {
                i32 += i30;
            }
            int i33 = i32;
            for (int i34 = 0; i34 <= i29; i34++) {
                int i35 = ((i33 >> 16) * i28) + i34;
                int i36 = 65535 & i33;
                iArr[i35] = ((((65535 - i36) * i26) >> 16) << 24) | i27;
                if (i36 != 0) {
                    iArr[i35 + i28] = (((i36 * i26) >> 16) << 24) | i27;
                }
                i33 += i30;
            }
            drawBuffer(iArr, i23, i25 - i31, i28, iAbs2);
            int i37 = i32;
            for (int i38 = 0; i38 <= i29; i38++) {
                int i39 = ((i37 >> 16) * i28) + i38;
                iArr[i39] = 0;
                iArr[i39 + i28] = 0;
                i37 += i30;
            }
            return;
        }
        int i40 = iAbs2 - 1;
        if (i25 < i24) {
            i9 = i25;
            i10 = i23;
            i24 = i25;
            i11 = i23;
            i12 = i24;
        } else {
            i9 = i24;
            i10 = i22;
            i11 = i22;
            i22 = i23;
            i12 = i25;
        }
        int i41 = i12 - i24;
        int i42 = i41 != 0 ? ((i22 - i10) << 16) / i41 : 0;
        int i43 = i42 < 0 ? iAbs - 2 : 0;
        int i44 = i43 << 16;
        if (i42 <= 65535) {
            i44 += i42;
        }
        int i45 = i41 * iAbs;
        int i46 = i44;
        for (int i47 = 0; i47 <= i45; i47 += iAbs) {
            int i48 = (i46 >> 16) + i47;
            int i49 = 65535 & i46;
            iArr[i48] = ((((65535 - i49) * i26) >> 16) << 24) | i27;
            if (i49 != 0) {
                iArr[i48 + 1] = (((i49 * i26) >> 16) << 24) | i27;
            }
            i46 += i42;
        }
        drawBuffer(iArr, i11 - i43, i9, iAbs, i40);
        int i50 = i44;
        for (int i51 = 0; i51 <= i45; i51 += iAbs) {
            int i52 = (i50 >> 16) + i51;
            iArr[i52] = 0;
            iArr[i52 + 1] = 0;
            i50 += i42;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void drawRect(int i, int i2, int i3, int i4) {
        int scaleWidth = getScaleWidth();
        int scaleHeight = getScaleHeight();
        this.mGraphicsContext.drawRect(((i * scaleWidth) >> 10) + getPivotX(), ((i2 * scaleHeight) >> 10) + getPivotY(), (scaleWidth * i3) >> 10, (scaleHeight * i4) >> 10);
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void fillBezier(int[] iArr, int i, int i2) {
        int i3;
        int i4;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < 2) {
            if (i5 == 1 && (smBezierPolygon == null || smBezierPolygon.length < i6 * 2)) {
                smBezierPolygon = new int[i6 * 2];
            }
            int i8 = 0;
            int i9 = i7;
            int i10 = i6;
            int i11 = i;
            while (i8 < i2) {
                int i12 = iArr[i11];
                int i13 = iArr[i11 + 1];
                int i14 = iArr[i11 + 4];
                int i15 = iArr[i11 + 5];
                int i16 = i11 + 6;
                int i17 = i8 == i2 - 1 ? i : i16;
                int i18 = iArr[i17 + 2];
                int i19 = iArr[i17 + 3];
                int i20 = iArr[i17];
                int i21 = iArr[i17 + 1];
                if (i12 != i14 || i13 != i15 || i18 != i20 || i19 != i21) {
                    int range = getRange(i12 - i14, i13 - i15) + getRange(i14 - i18, i15 - i19) + getRange(i18 - i20, i19 - i21);
                    int i22 = range > 8 ? range / 8 : 1;
                    if (i5 == 0) {
                        i4 = i10 + i22;
                        i3 = i9;
                    } else {
                        int i23 = 32768 / i22;
                        int i24 = 0;
                        int i25 = 0;
                        int i26 = i9;
                        while (true) {
                            int i27 = i24;
                            if (i25 >= i22) {
                                break;
                            }
                            int i28 = i26 + 1;
                            smBezierPolygon[i26] = (short) bezierPoint(i12, i14, i18, i20, i27);
                            smBezierPolygon[i28] = (short) bezierPoint(i13, i15, i19, i21, i27);
                            i24 = i27 + i23;
                            i25++;
                            i26 = i28 + 1;
                        }
                        i3 = i26;
                        i4 = i10;
                    }
                } else if (i5 == 0) {
                    i4 = i10 + 1;
                    i3 = i9;
                } else {
                    int i29 = i9 + 1;
                    smBezierPolygon[i9] = (short) i12;
                    smBezierPolygon[i29] = (short) i13;
                    i3 = i29 + 1;
                    i4 = i10;
                }
                i8++;
                i9 = i3;
                i10 = i4;
                i11 = i16;
            }
            i5++;
            i6 = i10;
            i7 = i9;
        }
        fillPolygon(smBezierPolygon, 0, i6);
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void fillEllipse(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int scaleWidth = (getScaleWidth() * i3) >> 10;
        int scaleHeight = (getScaleHeight() * i4) >> 10;
        if (scaleWidth <= 0 || scaleHeight <= 0) {
            return;
        }
        int scaleWidth2 = (getScaleWidth() * i) >> 10;
        int scaleHeight2 = (getScaleHeight() * i2) >> 10;
        if (getRotation() != 0) {
            int i10 = scaleWidth >> 1;
            int i11 = scaleHeight >> 1;
            int i12 = scaleWidth2 + i10;
            int i13 = scaleHeight2 + i11;
            int iCos = MathUtils.cos(getRotation());
            int iSin = MathUtils.sin(getRotation());
            int i14 = ((i12 * iCos) - (i13 * iSin)) >> 15;
            int i15 = ((i12 * iSin) + (i13 * iCos)) >> 15;
            i5 = i14 - i10;
            i6 = i15 - i11;
        } else {
            i5 = scaleWidth2;
            i6 = scaleHeight2;
        }
        int pivotX = i5 + getPivotX();
        int pivotY = getPivotY() + i6;
        if (renderPrimitiveWithAPI()) {
            this.mGraphicsContext.fillArc(pivotX, pivotY, scaleWidth, scaleHeight, 0, Util.SIN_SAMPLES_DEFAULT);
            return;
        }
        boolean zIsFilterEnabled = isFilterEnabled(2);
        int i16 = scaleWidth >> 1;
        int i17 = (scaleHeight << 16) / scaleWidth;
        if (scaleHeight > scaleWidth) {
            i7 = scaleHeight >> 1;
            i8 = 65536;
            i9 = (scaleWidth << 16) / scaleHeight;
        } else {
            i7 = i16;
            i8 = i17;
            i9 = 65536;
        }
        int i18 = scaleHeight + 1;
        if (smEllipseX == null || i18 > smEllipseX.length) {
            smEllipseX = new int[(i18 * 12) / 10];
        }
        for (int i19 = 0; i19 < i18; i19++) {
            smEllipseX[i19] = -1;
        }
        if (zIsFilterEnabled) {
            allocateBuffer(0, scaleWidth * scaleHeight, true, smTempBuffer);
        }
        int[] iArr = smTempBuffer[0];
        int color = zIsFilterEnabled ? getColor() | (-16777216) : 0;
        int i20 = (-i7) << 1;
        int i21 = 1 - i7;
        int i22 = i7;
        int i23 = 0;
        int i24 = 1;
        while (i23 < i22) {
            if (i21 >= 0) {
                i22--;
                i20 += 2;
                i21 += i20;
            }
            i23++;
            i24 += 2;
            i21 += i24;
            smEllipseX[((i7 - i22) * i8) >> 16] = i23;
            smEllipseX[((i7 + i22) * i8) >> 16] = i23;
            smEllipseX[((i7 - i23) * i8) >> 16] = i22;
            smEllipseX[((i7 + i23) * i8) >> 16] = i22;
        }
        smEllipseX[(i8 * i7) >> 16] = i7;
        int i25 = scaleWidth & 1;
        int i26 = Integer.MIN_VALUE;
        int i27 = 0;
        int i28 = 0;
        int i29 = 0;
        for (int i30 = 0; i30 < scaleHeight; i30++) {
            int i31 = smEllipseX[i30];
            if (i31 != -1) {
                int i32 = ((i7 - i31) * i9) >> 16;
                int i33 = (((i31 + i7) * i9) >> 16) + i25;
                if (zIsFilterEnabled) {
                    int i34 = i27;
                    int i35 = 0;
                    while (i35 < i32) {
                        iArr[i34] = 0;
                        i35++;
                        i34++;
                    }
                    while (i35 < i33) {
                        iArr[i34] = color;
                        i35++;
                        i34++;
                    }
                    while (i35 < scaleWidth) {
                        iArr[i34] = 0;
                        i35++;
                        i34++;
                    }
                    if (i26 != Integer.MIN_VALUE) {
                        int i36 = i32 - i29;
                        if (i36 < 0) {
                            drawAlphaHLine(iArr, ((i30 - 1) * scaleWidth) + i32, -i36, color, true);
                        } else if (i36 > 0) {
                            drawAlphaHLine(iArr, i29 + (i30 * scaleWidth), i36, color, true);
                        }
                        int i37 = i33 - i28;
                        if (i37 < 0) {
                            drawAlphaHLine(iArr, (i30 * scaleWidth) + i33, -i37, color, false);
                        } else if (i37 > 0) {
                            drawAlphaHLine(iArr, i28 + ((i30 - 1) * scaleWidth), i37, color, false);
                        }
                    }
                    i27 = i34;
                    i28 = i33;
                    i29 = i32;
                    i26 = i30;
                } else {
                    this.mGraphicsContext.fillRect(pivotX + i32, pivotY + i30, i33 - i32, 1);
                }
            } else if (zIsFilterEnabled) {
                int i38 = i27;
                int i39 = scaleWidth;
                while (true) {
                    i39--;
                    if (i39 < 0) {
                        break;
                    }
                    iArr[i38] = 0;
                    i38++;
                }
                i27 = i38;
            }
        }
        if (zIsFilterEnabled) {
            drawBuffer(iArr, pivotX, pivotY, scaleWidth, scaleHeight);
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void fillPolygon(int[] iArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int[] iArr2;
        int scaleWidth = getScaleWidth();
        int scaleHeight = getScaleHeight();
        int pivotX = getPivotX();
        int pivotY = getPivotY();
        if (this.mPolygonX == null || this.mPolygonX.length < i2) {
            this.mPolygonX = new int[i2];
            this.mPolygonY = new int[i2];
        }
        int rotation = getRotation();
        int iSin = MathUtils.sin(rotation);
        int iCos = MathUtils.cos(rotation);
        int i7 = 0;
        int i8 = i;
        while (i7 < i2) {
            int i9 = i8 + 1;
            int i10 = (iArr[i8] * scaleWidth) >> 10;
            int i11 = i9 + 1;
            int i12 = (iArr[i9] * scaleHeight) >> 10;
            this.mPolygonX[i7] = (((i10 * iCos) - (i12 * iSin)) >> 15) + pivotX;
            this.mPolygonY[i7] = (((i10 * iSin) + (i12 * iCos)) >> 15) + pivotY;
            i7++;
            i8 = i11;
        }
        if (renderPrimitiveWithAPI() && i2 == 3) {
            this.mGraphicsContext.fillTriangle(this.mPolygonX[0], this.mPolygonY[0], this.mPolygonX[1], this.mPolygonY[1], this.mPolygonX[2], this.mPolygonY[2]);
            return;
        }
        boolean zIsFilterEnabled = isFilterEnabled(2);
        if (smPolygonEdge == null || i2 > smPolygonEdge.length) {
            smPolygonEdge = new int[i2];
            smEdgeStartY = new int[i2];
            smEdgeEndY = new int[i2];
            smEdgeStartX = new int[i2];
            smEdgeDeltaX = new int[i2];
            smStopPoint = new int[i2];
        }
        if (zIsFilterEnabled && (smEdgeLastX == null || i2 > smEdgeLastX.length)) {
            smEdgeLastX = new int[i2];
        }
        int i13 = Graphics3D.COMMAND_END;
        int color = zIsFilterEnabled ? getColor() | (-16777216) : 0;
        if (zIsFilterEnabled) {
            int i14 = Integer.MAX_VALUE;
            int i15 = Integer.MIN_VALUE;
            int i16 = Integer.MAX_VALUE;
            for (int i17 = 0; i17 < i2; i17++) {
                int i18 = this.mPolygonX[i17];
                if (i18 > i13) {
                    i13 = i18;
                }
                if (i18 < i14) {
                    i14 = i18;
                }
                int i19 = this.mPolygonY[i17];
                if (i19 > i15) {
                    i15 = i19;
                }
                if (i19 < i16) {
                    i16 = i19;
                }
            }
            int i20 = (i15 - i16) + 1;
            int i21 = (i13 - i14) + 1;
            allocateBuffer(0, i21 * i20, true, smTempBuffer);
            i4 = i16;
            i3 = i21;
            i6 = i14;
            i5 = i20;
            iArr2 = smTempBuffer[0];
        } else {
            i3 = 0;
            i4 = Integer.MAX_VALUE;
            i5 = 0;
            i6 = Integer.MAX_VALUE;
            iArr2 = null;
        }
        for (int i22 = 0; i22 < i2; i22++) {
            if (zIsFilterEnabled) {
                smEdgeStartX[i22] = (this.mPolygonX[i22] - i6) << 16;
                smEdgeStartY[i22] = this.mPolygonY[i22] - i4;
            } else {
                smEdgeStartX[i22] = this.mPolygonX[i22] << 16;
                smEdgeStartY[i22] = this.mPolygonY[i22];
            }
        }
        for (int i23 = 0; i23 < i2; i23++) {
            int i24 = i23 + 1;
            if (i24 == i2) {
                i24 = 0;
            }
            smEdgeEndY[i23] = smEdgeStartY[i24];
            smEdgeDeltaX[i23] = smEdgeStartX[i24];
        }
        int i25 = 0;
        for (int i26 = 0; i26 < i2; i26++) {
            int i27 = smEdgeEndY[i26] - smEdgeStartY[i26];
            if (i27 != 0) {
                if (i27 < 0) {
                    int i28 = smEdgeStartY[i26];
                    smEdgeStartY[i26] = smEdgeEndY[i26];
                    smEdgeEndY[i26] = i28;
                    int i29 = smEdgeStartX[i26];
                    smEdgeStartX[i26] = smEdgeDeltaX[i26];
                    smEdgeDeltaX[i26] = i29;
                    i27 = -i27;
                }
                int[] iArr3 = smEdgeDeltaX;
                iArr3[i26] = iArr3[i26] - smEdgeStartX[i26];
                int[] iArr4 = smEdgeDeltaX;
                iArr4[i26] = iArr4[i26] / i27;
            }
            int i30 = smEdgeStartY[i26];
            int i31 = smEdgeEndY[i26];
            boolean z = false;
            boolean z2 = false;
            int i32 = i25;
            while (true) {
                i32--;
                if (i32 < 0) {
                    break;
                }
                if (smStopPoint[i32] == i30) {
                    z2 = true;
                }
                if (smStopPoint[i32] == i31) {
                    z = true;
                }
            }
            if (!z2) {
                smStopPoint[i25] = i30;
                i25++;
            }
            if (!z) {
                smStopPoint[i25] = i31;
                i25++;
            }
        }
        for (int i33 = 0; i33 < i25; i33++) {
            for (int i34 = i33 + 1; i34 < i25; i34++) {
                if (smStopPoint[i33] > smStopPoint[i34]) {
                    int i35 = smStopPoint[i33];
                    smStopPoint[i33] = smStopPoint[i34];
                    smStopPoint[i34] = i35;
                }
            }
        }
        int i36 = 1;
        int i37 = smStopPoint[0];
        int i38 = -1;
        while (true) {
            int i39 = 0;
            int i40 = i2;
            while (true) {
                i40--;
                if (i40 < 0) {
                    break;
                }
                if (smEdgeStartY[i40] <= i37 && smEdgeEndY[i40] > i37) {
                    smPolygonEdge[i39] = i40;
                    i39++;
                }
            }
            if (zIsFilterEnabled && i39 != i38) {
                int i41 = i39;
                while (true) {
                    i41--;
                    if (i41 < 0) {
                        break;
                    } else {
                        smEdgeLastX[i41] = Integer.MAX_VALUE;
                    }
                }
                i38 = i39;
            }
            for (int i42 = 0; i42 < i39; i42++) {
                for (int i43 = i42 + 1; i43 < i39; i43++) {
                    int i44 = smPolygonEdge[i42];
                    int i45 = smEdgeStartX[i44];
                    int i46 = smPolygonEdge[i43];
                    int i47 = smEdgeStartX[i46];
                    if (i45 > i47 || (i45 == i47 && smEdgeDeltaX[i44] > smEdgeDeltaX[i46])) {
                        smPolygonEdge[i43] = i44;
                        smPolygonEdge[i42] = i46;
                    }
                }
            }
            int i48 = i36 + 1;
            int i49 = smStopPoint[i36];
            if (i48 == i25) {
                i49++;
            }
            while (i37 < i49) {
                if (zIsFilterEnabled) {
                    int i50 = i37 * i3;
                    int i51 = i50 + i3;
                    while (i50 < i51) {
                        iArr2[i50] = 0;
                        i50++;
                    }
                }
                int i52 = Integer.MIN_VALUE;
                int i53 = 0;
                while (i53 < i39) {
                    int i54 = i53 + 1;
                    int i55 = smPolygonEdge[i53];
                    int i56 = smEdgeStartX[i55];
                    int i57 = i54 + 1;
                    int i58 = smPolygonEdge[i54];
                    int i59 = smEdgeStartX[i58];
                    int i60 = ((i56 & 65535) >> 15) + (i56 >> 16);
                    int i61 = ((i59 & 65535) >> 15) + (i59 >> 16) + 1;
                    if (i60 >= i52) {
                        i52 = i60;
                    }
                    if (zIsFilterEnabled) {
                        int i62 = i57 - 2;
                        int i63 = i57 - 1;
                        int i64 = (i37 * i3) + i52;
                        int i65 = (i61 - i52) + i64;
                        while (i64 < i65) {
                            iArr2[i64] = color;
                            i64++;
                        }
                        if (smEdgeLastX[i62] != Integer.MAX_VALUE) {
                            int i66 = i52 - smEdgeLastX[i62];
                            if (i66 < 0) {
                                drawAlphaHLine(iArr2, ((i37 - 1) * i3) + i52, -i66, color, true);
                            } else if (i66 > 0) {
                                drawAlphaHLine(iArr2, (i37 * i3) + smEdgeLastX[i62], i66, color, true);
                            }
                        }
                        if (smEdgeLastX[i63] != Integer.MAX_VALUE) {
                            int i67 = i61 - smEdgeLastX[i63];
                            if (i67 < 0) {
                                drawAlphaHLine(iArr2, (i37 * i3) + i61, -i67, color, false);
                            } else if (i67 > 0) {
                                drawAlphaHLine(iArr2, ((i37 - 1) * i3) + smEdgeLastX[i63], i67, color, false);
                            }
                        }
                        smEdgeLastX[i62] = i52;
                        smEdgeLastX[i63] = i61;
                    } else {
                        this.mGraphicsContext.fillRect(i52, i37, i61 - i52, 1);
                    }
                    int[] iArr5 = smEdgeStartX;
                    iArr5[i55] = iArr5[i55] + smEdgeDeltaX[i55];
                    int[] iArr6 = smEdgeStartX;
                    iArr6[i58] = iArr6[i58] + smEdgeDeltaX[i58];
                    i53 = i57;
                    i52 = i61;
                }
                i37++;
            }
            if (i48 >= i25) {
                break;
            } else {
                i36 = i48;
            }
        }
        if (zIsFilterEnabled) {
            drawBuffer(iArr2, i6, i4, i3, i5);
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void fillRect(int i, int i2, int i3, int i4) {
        int color = getColor();
        int i5 = color >>> 24;
        if (i5 == 0) {
            return;
        }
        if (getRotation() != 0) {
            allocateBuffer(0, 8, false, smTempBuffer);
            int[] iArr = smTempBuffer[0];
            iArr[0] = i;
            iArr[1] = i2;
            iArr[2] = (i + i3) - 1;
            iArr[3] = i2;
            iArr[4] = iArr[2];
            iArr[5] = (i2 + i4) - 1;
            iArr[6] = i;
            iArr[7] = iArr[5];
            fillPolygon(iArr, 0, 4);
            return;
        }
        int scaleWidth = getScaleWidth();
        int scaleHeight = getScaleHeight();
        int pivotX = ((i * scaleWidth) >> 10) + getPivotX();
        int pivotY = ((i2 * scaleHeight) >> 10) + getPivotY();
        int i6 = (scaleWidth * i3) >> 10;
        int i7 = (scaleHeight * i4) >> 10;
        if (i5 == 255) {
            this.mGraphicsContext.fillRect(pivotX, pivotY, i6, i7);
            return;
        }
        int i8 = i6 * i7;
        allocateBuffer(0, i8, true, smTempBuffer);
        int[] iArr2 = smTempBuffer[0];
        while (true) {
            i8--;
            if (i8 < 0) {
                drawBuffer(iArr2, pivotX, pivotY, i6, i7);
                return;
            }
            iArr2[i8] = color;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void fillTriangle(int i, int i2, int i3, int i4, int i5, int i6) {
        allocateBuffer(0, 6, false, smTempBuffer);
        int[] iArr = smTempBuffer[0];
        iArr[0] = i;
        iArr[1] = i2;
        iArr[2] = i3;
        iArr[3] = i4;
        iArr[4] = i5;
        iArr[5] = i6;
        fillPolygon(iArr, 0, 3);
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public int getBlurHeight() {
        return this.mEffectParam[11];
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public int getBlurWidth() {
        return this.mEffectParam[10];
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public int getClipHeight() {
        return this.mGraphicsContext.getClipHeight();
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public int getClipWidth() {
        return this.mGraphicsContext.getClipWidth();
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public int getClipX() {
        return this.mGraphicsContext.getClipX();
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public int getClipY() {
        return this.mGraphicsContext.getClipY();
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public int getColor() {
        return this.mEffectParam[12];
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public int getColorModification() {
        return getColorModification(this.mEffectParam);
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public int getFilters() {
        return this.mEffectParam[1];
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public Graphics getGraphicsContext() {
        return this.mGraphicsContext;
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public int getPivotX() {
        return this.mEffectParam[7];
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public int getPivotY() {
        return this.mEffectParam[8];
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public int getRenderMode() {
        return this.mEffectParam[9];
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public int getRotation() {
        return getRotation(this.mEffectParam);
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public int getScaleHeight() {
        return getScaleHeight(this.mEffectParam);
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public int getScaleWidth() {
        return getScaleWidth(this.mEffectParam);
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public boolean isFilterEnabled(int i) {
        return (getFilters() & i) == i;
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public boolean isFrameUpToDate(DChocImage dChocImage) {
        int[] effectParams = dChocImage.getEffectParams();
        if (effectParams == null) {
            return false;
        }
        int i = 13;
        while (true) {
            i--;
            if (i < 0) {
                return true;
            }
            switch (i) {
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                    break;
                default:
                    if (this.mEffectParam[i] == effectParams[i]) {
                        break;
                    } else {
                        return false;
                    }
            }
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void makeImageTransparent(DChocImage dChocImage, int i, int i2) {
        Graphics graphics = dChocImage.getGraphics();
        int color = graphics.getColor();
        graphics.setColor(getColorKey());
        graphics.fillRect(0, 0, i, i2);
        graphics.setColor(color);
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void popParameters() {
        int size = this.mEffectStack.size() - 1;
        int[] iArr = (int[]) this.mEffectStack.elementAt(size);
        this.mEffectStack.removeElementAt(size);
        int i = 13;
        while (true) {
            i--;
            if (i < 0) {
                this.mUsableEffectStorage.addElement(iArr);
                setGraphicsContext((Graphics) this.mGraphicsStack.elementAt(size));
                this.mGraphicsStack.removeElementAt(size);
                this.mRenderTarget = (DChocImage) this.mRenderTargetStack.elementAt(size);
                this.mRenderTargetStack.removeElementAt(size);
                setColorARGB(getColor());
                return;
            }
            this.mEffectParam[i] = iArr[i];
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void pushParameters() {
        int[] iArr;
        if (this.mEffectStack == null) {
            this.mEffectStack = new Vector();
            this.mUsableEffectStorage = new Vector();
            this.mGraphicsStack = new Vector();
            this.mRenderTargetStack = new Vector();
        }
        this.mEffectStack.addElement(this.mEffectParam);
        this.mGraphicsStack.addElement(this.mGraphicsContext);
        this.mRenderTargetStack.addElement(this.mRenderTarget);
        if (this.mUsableEffectStorage.size() > 0) {
            iArr = (int[]) this.mUsableEffectStorage.elementAt(0);
            this.mUsableEffectStorage.removeElementAt(0);
        } else {
            iArr = new int[13];
        }
        int i = 13;
        while (true) {
            i--;
            if (i < 0) {
                this.mEffectParam = iArr;
                return;
            }
            iArr[i] = this.mEffectParam[i];
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void setBlur(int i, int i2) {
        this.mEffectParam[10] = i;
        this.mEffectParam[11] = i2;
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void setClip(int i, int i2, int i3, int i4) {
        this.mGraphicsContext.setClip(i, i2, i3, i4);
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void setColor(int i) {
        setColorARGB((-16777216) | i);
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void setColor(int i, int i2, int i3, int i4) {
        setColorARGB(((i & 255) << 24) | ((i2 & 255) << 16) | ((i3 & 255) << 8) | (i4 & 255));
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void setColorARGB(int i) {
        this.mEffectParam[12] = i;
        this.mGraphicsContext.setColor(i);
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void setColorKey(int i) {
        int i2 = (-16777216) | i;
        if (smColorKey == null) {
            smColorKey = new int[1];
            smTempColorKeyImage = Image.createImage(1, 1);
            smTempColorKeyGraphics = smTempColorKeyImage.getGraphics();
        }
        smTempColorKeyGraphics.setColor(i2);
        smTempColorKeyGraphics.fillRect(0, 0, 1, 1);
        smTempColorKeyImage.getRGB(smColorKey, 0, 1, 0, 0, 1, 1);
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void setColorModification(int i) {
        this.mEffectParam[0] = i;
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void setColorModification(int i, int i2, int i3, int i4) {
        setColorModification(((((i2 >> 1) + 128) & 255) << 16) | ((i & 255) << 24) | ((((i3 >> 1) + 128) & 255) << 8) | (((i4 >> 1) + 128) & 255));
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void setFiltering(int i, boolean z) {
        if (z) {
            int[] iArr = this.mEffectParam;
            iArr[1] = iArr[1] | i;
        } else {
            int[] iArr2 = this.mEffectParam;
            iArr2[1] = iArr2[1] & (i ^ (-1));
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void setFilters(int i) {
        this.mEffectParam[1] = i;
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void setGraphicsContext(DChocImage dChocImage) {
        setGraphicsContext(dChocImage.getGraphics());
        this.mRenderTarget = dChocImage;
        int[] effectParams = dChocImage.getEffectParams();
        if (effectParams != null) {
            effectParams[5] = effectParams[5] + 1;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void setGraphicsContext(Graphics graphics) {
        this.mGraphicsContext = graphics;
        this.mRenderTarget = null;
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void setPivot(int i, int i2) {
        this.mEffectParam[7] = i;
        this.mEffectParam[8] = i2;
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void setRenderMode(int i) {
        this.mEffectParam[9] = i;
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void setRotation(int i) {
        this.mEffectParam[2] = i;
    }

    @Override // com.digitalchocolate.androidrollergapp.IRenderingPlatform
    public void setScale(int i, int i2) {
        this.mEffectParam[3] = i;
        this.mEffectParam[4] = i2;
    }
}
