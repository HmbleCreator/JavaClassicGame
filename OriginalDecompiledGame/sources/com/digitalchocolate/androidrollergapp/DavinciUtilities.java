package com.digitalchocolate.androidrollergapp;

import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* loaded from: classes.dex */
public class DavinciUtilities {
    public static final int ANGLE_ACCURACY = 8;
    public static final int BILINEAR_ACCURACY = 8;
    private static final boolean BREW_TRANSPARENCY = false;
    public static final boolean ENABLE_CLIP_RECTS = false;
    public static final boolean ENABLE_COLLISION_BOXES = true;
    public static final boolean ENABLE_FILLED_CIRCLES = true;
    public static final boolean ENABLE_FILLED_POLYGONS = false;
    public static final boolean ENABLE_FILLED_RECTS = true;
    public static final boolean ENABLE_LINES = true;
    public static final boolean ENABLE_NESTED_ANIMATIONS = false;
    public static final boolean ENABLE_RECTS = true;
    private static final int IMAGE_HANDLE_SIZE = 5;
    public static final int NO_SCALING = 1024;
    public static final int PIXELS_24BIT = 254;
    public static final int PIXELS_32BIT = 255;
    public static final int PIXELS_INDEXED = -1;
    private static final int PIXELS_RAW_FORMAT = 253;
    public static final int RENDERABLE_TYPE_BEZIER = 8;
    public static final int RENDERABLE_TYPE_CLIP_RECT = 7;
    public static final int RENDERABLE_TYPE_FILLEDCIRCLE = 5;
    public static final int RENDERABLE_TYPE_FILLEDRECT = 2;
    public static final int RENDERABLE_TYPE_IMAGE = 1;
    public static final int RENDERABLE_TYPE_LINE = 4;
    public static final int RENDERABLE_TYPE_NESTED_ANIMATION = 9;
    public static final int RENDERABLE_TYPE_POLYGON = 6;
    public static final int RENDERABLE_TYPE_RECT = 3;
    public static final int SCALE_ACCURACY = 10;
    private static final int SCALING_BIT = 8;
    public static final int SIN_ACCURACY = 15;
    private static final int TEXTURE_PACK_BIT = 16;
    public static final int TRANSFORMATION_FLIP_HORIZONTAL = 1;
    public static final int TRANSFORMATION_FLIP_VERTICAL = 2;
    public static final int TRANSFORMATION_NONE = 0;
    public static final boolean USE_ANIMATION_CACHING = false;
    public static final boolean USE_IMAGE_CACHING = false;
    public static final boolean USE_MIDP2_EFFECTS = true;
    public static final boolean USE_MIDP2_TRANSFORMATIONS = true;
    public static final boolean USE_NOKIAUI_TRANSFORMATIONS = false;
    public static final boolean USE_REALTIME_TRANSFORMATIONS = true;
    private static final boolean USE_REALTIME_TRANSFORMATIONS_FOR_ALPHA_IMAGES = true;
    public static final boolean USE_TEXTURES = false;
    private static Hashtable smAnimationCache;
    private static int[] smCrcTable;
    public static int smCurrentLoadingStep;
    private static Hashtable smImageCache;
    private static int[] smImageHandles;
    private static int smImageHandlesSize;
    private static int[][] smPalettes;
    private static Vector smSpriteObjectCache;
    private static int[] smSpriteObjectID;
    private static short[] smSpriteObjectReference;
    private static int[] smTemp;
    private static short[][][] smTextureCoordinate;
    private static DChocImage[] smTextureImage;
    private static int[] smTextureRID;
    private static final int INITIAL_SIZE = 649;
    private static Vector smImages = new Vector(INITIAL_SIZE, INITIAL_SIZE);
    private static short[] smImageReferences = new short[INITIAL_SIZE];
    private static int[] smImageIDs = new int[INITIAL_SIZE];
    private static byte[] smPaletteIndices = new byte[INITIAL_SIZE];
    private static short[] smImageTransformation = new short[INITIAL_SIZE];

    private static void addImageToLoadList(int i, int i2, int i3, int i4, int i5) {
        if (smImageHandles == null) {
            smImageHandles = new int[3245];
        }
        int i6 = smImageHandlesSize * 5;
        if (i6 == smImageHandles.length) {
            int[] iArr = new int[smImageHandles.length << 1];
            System.arraycopy(smImageHandles, 0, iArr, 0, i6);
            smImageHandles = iArr;
        }
        smImageHandles[i6] = i;
        smImageHandles[i6 + 1] = i2;
        smImageHandles[i6 + 2] = i3;
        smImageHandles[i6 + 3] = i4;
        smImageHandles[i6 + 4] = i5;
        smImageHandlesSize++;
        while (i6 > 0 && smImageHandles[i6 + 1] < smImageHandles[(i6 + 1) - 5]) {
            swap(smImageHandles, i6, i6 - 5, 5);
            i6 -= 5;
        }
    }

    private static int adler32(byte[] bArr, int i, int i2, int i3) {
        int i4 = i2 + i;
        int i5 = 65535 & i3;
        int i6 = i3 >>> 16;
        for (int i7 = i; i7 < i4; i7++) {
            i5 = (i5 + (bArr[i7] & 255)) % 65521;
            i6 = (i6 + i5) % 65521;
        }
        return i5 + (i6 << 16);
    }

    private static void allocateTempMemory(int i) {
        if (smTemp == null || smTemp.length < i) {
            smTemp = new int[i];
        }
    }

    public static final int bilinearFilter(int[] iArr, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9 = i >> 8;
        int i10 = i2 >> 8;
        int i11 = (i10 * i3) + i9;
        if (i9 != -1 && i10 != -1) {
            int i12 = iArr[i11];
            int i13 = i9 < i3 - 1 ? iArr[i11 + 1] : 16777215 & i12;
            int i14 = 16777215 & i13;
            int i15 = 16777215 & i12;
            if (i10 < i4 - 1) {
                int i16 = i11 + i3;
                i7 = i9 < i3 - 1 ? iArr[i16 + 1] : i14;
                i8 = iArr[i16];
                i5 = i13;
                i6 = i12;
            } else {
                i8 = i15;
                i7 = i14;
                i5 = i13;
                i6 = i12;
            }
        } else if (i9 == -1) {
            int i17 = i11 + 1;
            if (i10 == -1) {
                int i18 = iArr[i3 + i17];
                int i19 = 16777215 & i18;
                i5 = i19;
                i6 = i19;
                i7 = i18;
                i8 = i19;
            } else {
                int i20 = iArr[i17];
                int i21 = 16777215 & i20;
                int i22 = i10 < i4 - 1 ? iArr[i3 + i17] : i21;
                i5 = i20;
                i6 = i21;
                i7 = i22;
                i8 = 16777215 & i22;
            }
        } else {
            int i23 = i11 + i3;
            int i24 = iArr[i23];
            int i25 = 16777215 & i24;
            int i26 = i9 < i3 - 1 ? iArr[i23 + 1] : i25;
            i5 = 16777215 & i26;
            i6 = i25;
            i7 = i26;
            i8 = i24;
        }
        int i27 = i2 & 255;
        int i28 = i & 255;
        int i29 = i6 >>> 24;
        int i30 = i29 + ((((i8 >>> 24) - i29) * i27) >> 8);
        int i31 = i5 >>> 24;
        int i32 = (i30 + ((((i31 + ((((i7 >>> 24) - i31) * i27) >> 8)) - i30) * i28) >> 8)) << 24;
        int i33 = 16711935 & i6;
        int i34 = (i33 + ((((16711935 & i8) - i33) * i27) >> 8)) & 16711935;
        int i35 = 16711935 & i5;
        int i36 = i6 & 65280;
        int i37 = ((((i8 & 65280) - i36) * i27) >> 8) + i36;
        int i38 = i5 & 65280;
        return ((i37 + ((i28 * ((((i27 * ((i7 & 65280) - i38)) >> 8) + i38) - i37)) >> 8)) & 65280) | i32 | ((i34 + (((((i35 + ((((16711935 & i7) - i35) * i27) >> 8)) & 16711935) - i34) * i28) >> 8)) & 16711935);
    }

    private static void clearImageLoadingList() {
        smImageHandles = null;
        smCurrentLoadingStep = 0;
        smImageHandlesSize = 0;
    }

    public static final int cos(int i) {
        return MathUtils.cos(i);
    }

    private static int crc32(byte[] bArr, int i, int i2) {
        if (smCrcTable == null) {
            smCrcTable = new int[256];
            for (int i3 = 0; i3 < 256; i3++) {
                int i4 = i3;
                for (int i5 = 0; i5 < 8; i5++) {
                    i4 = (i4 & 1) != 0 ? (i4 >>> 1) ^ (-306674912) : i4 >>> 1;
                    smCrcTable[i3] = i4;
                }
            }
        }
        int i6 = -1;
        int i7 = i2 + i;
        for (int i8 = i; i8 < i7; i8++) {
            i6 = (i6 >>> 8) ^ smCrcTable[(bArr[i8] ^ i6) & 255];
        }
        return i6 ^ (-1);
    }

    public static DChocImage createMIDP2Image(int[] iArr, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6) {
        return new DChocImage(Image.createRGBImage(getImagePixels(iArr, bArr, i, i2, i3, i4, i5, i6), getScaledValue(i, i6), getScaledValue(i2, i6), i3 != 254));
    }

    public static byte[] createPng(int[] iArr, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int length;
        int i8;
        int i9;
        int i10;
        boolean z = (i5 & 1) != 0;
        boolean z2 = (i5 & 2) != 0;
        boolean z3 = i3 == -1 && i4 != 0;
        int i11 = 4;
        if (i3 == -1) {
            i11 = 1;
            i7 = 1;
            length = (iArr.length * 3) + 4 + 4 + 4 + 1 + 4 + 4 + 4;
        } else if (i3 == 254) {
            i11 = 3;
            i7 = 4;
            length = 0;
        } else {
            i7 = 4;
            length = 0;
        }
        int i12 = (i * i6) >> 10;
        if (i12 <= 0) {
            i12 = 1;
        }
        int i13 = (i2 * i6) >> 10;
        if (i13 <= 0) {
            i13 = 1;
        }
        int i14 = 65531 - (65531 % ((i12 * i7) + 1));
        int i15 = (i12 * i13 * i7) + i13;
        int i16 = ((i15 - 1) / i14) + 1;
        int i17 = i15 + 6 + (i16 * 5);
        byte[] bArr2 = new byte[length + i17 + 57];
        bArr2[0] = -119;
        bArr2[1] = 80;
        bArr2[2] = 78;
        bArr2[3] = 71;
        bArr2[4] = 13;
        bArr2[5] = 10;
        bArr2[6] = 26;
        bArr2[7] = 10;
        bArr2[11] = 13;
        bArr2[12] = 73;
        bArr2[13] = 72;
        bArr2[14] = 68;
        bArr2[15] = 82;
        bArr2[18] = (byte) (i12 >> 8);
        bArr2[19] = (byte) i12;
        bArr2[22] = (byte) (i13 >> 8);
        bArr2[23] = (byte) i13;
        bArr2[24] = 8;
        bArr2[25] = (byte) (i3 == -1 ? 3 : 6);
        int iCrc32 = crc32(bArr2, 12, 17);
        bArr2[29] = (byte) (iCrc32 >> 24);
        bArr2[30] = (byte) (iCrc32 >> 16);
        bArr2[31] = (byte) (iCrc32 >> 8);
        bArr2[32] = (byte) iCrc32;
        if (i3 == -1) {
            int length2 = iArr.length * 3;
            bArr2[35] = (byte) (length2 >> 8);
            bArr2[36] = (byte) length2;
            bArr2[37] = 80;
            bArr2[38] = 76;
            bArr2[39] = 84;
            bArr2[40] = 69;
            int i18 = 41;
            int i19 = 0;
            while (i19 < iArr.length) {
                int i20 = i18 + 1;
                bArr2[i18] = (byte) (iArr[i19] >> 16);
                int i21 = i20 + 1;
                bArr2[i20] = (byte) (iArr[i19] >> 8);
                bArr2[i21] = (byte) iArr[i19];
                i19++;
                i18 = i21 + 1;
            }
            int iCrc322 = crc32(bArr2, (i18 - length2) - 4, length2 + 4);
            int i22 = i18 + 1;
            bArr2[i18] = (byte) (iCrc322 >>> 24);
            int i23 = i22 + 1;
            bArr2[i22] = (byte) (iCrc322 >>> 16);
            int i24 = i23 + 1;
            bArr2[i23] = (byte) (iCrc322 >>> 8);
            bArr2[i24] = (byte) iCrc322;
            int i25 = i24 + 1 + 3;
            int i26 = i25 + 1;
            bArr2[i25] = 1;
            int i27 = i26 + 1;
            bArr2[i26] = 116;
            int i28 = i27 + 1;
            bArr2[i27] = 82;
            int i29 = i28 + 1;
            bArr2[i28] = 78;
            bArr2[i29] = 83;
            int i30 = i29 + 1 + 1;
            int iCrc323 = crc32(bArr2, i30 - 5, 5);
            int i31 = i30 + 1;
            bArr2[i30] = (byte) (iCrc323 >>> 24);
            int i32 = i31 + 1;
            bArr2[i31] = (byte) (iCrc323 >>> 16);
            int i33 = i32 + 1;
            bArr2[i32] = (byte) (iCrc323 >>> 8);
            i8 = i33 + 1;
            bArr2[i33] = (byte) iCrc323;
        } else {
            i8 = 33;
        }
        int i34 = i8 + 1;
        bArr2[i8] = (byte) (i17 >> 24);
        int i35 = i34 + 1;
        bArr2[i34] = (byte) (i17 >> 16);
        int i36 = i35 + 1;
        bArr2[i35] = (byte) (i17 >> 8);
        int i37 = i36 + 1;
        bArr2[i36] = (byte) i17;
        int i38 = i37 + 1;
        bArr2[i37] = 73;
        int i39 = i38 + 1;
        bArr2[i38] = 68;
        int i40 = i39 + 1;
        bArr2[i39] = 65;
        int i41 = i40 + 1;
        bArr2[i40] = 84;
        int i42 = i41 + 1;
        bArr2[i41] = 120;
        int i43 = i42 + 1;
        bArr2[i42] = 1;
        boolean z4 = i6 == 1024 && i5 == 0 && z3;
        int i44 = i7 * i12;
        int i45 = 0;
        int i46 = i43;
        int i47 = i14;
        int i48 = 0;
        int iAdler32 = 1;
        while (i45 < i16) {
            if (i45 == i16 - 1) {
                bArr2[i46] = 1;
                i47 = ((i15 - 1) % i14) + 1;
            }
            int i49 = i46 + 1;
            int i50 = i49 + 1;
            bArr2[i49] = (byte) i47;
            int i51 = i50 + 1;
            bArr2[i50] = (byte) (i47 >> 8);
            int i52 = 65535 ^ i47;
            int i53 = i51 + 1;
            bArr2[i51] = (byte) i52;
            bArr2[i53] = (byte) (i52 >> 8);
            int i54 = i53 + 1;
            int i55 = i48;
            int i56 = 0;
            while (i55 < i13 && i56 < i47) {
                int i57 = i56 + 1;
                int i58 = i54 + 1;
                int i59 = z2 ? (i13 - i55) - 1 : i55;
                if (i6 != 1024) {
                    i59 = (i59 << 10) / i6;
                }
                int i60 = (i59 * i * i11) + i4;
                if (i3 != -1 || i6 != 1024) {
                    i10 = i58;
                    for (int i61 = 0; i61 < i12; i61++) {
                        int i62 = z ? (i12 - i61) - 1 : i61;
                        if (i6 != 1024) {
                            i62 = (i62 << 10) / i6;
                        }
                        int i63 = (i62 * i11) + i60;
                        int i64 = i10 + 1;
                        bArr2[i10] = bArr[i63];
                        if (i3 != -1) {
                            int i65 = i64 + 1;
                            int i66 = i63 + 1;
                            bArr2[i64] = bArr[i66];
                            int i67 = i65 + 1;
                            int i68 = i66 + 1;
                            bArr2[i65] = bArr[i68];
                            i10 = i67 + 1;
                            bArr2[i67] = i3 == 255 ? bArr[i68 + 1] : (byte) -1;
                        } else {
                            i10 = i64;
                        }
                    }
                } else if (z) {
                    int i69 = (i60 + i12) - 1;
                    i10 = i58;
                    int i70 = i12;
                    while (true) {
                        i70--;
                        if (i70 >= 0) {
                            bArr2[i10] = bArr[i69];
                            i69--;
                            i10++;
                        }
                    }
                } else {
                    i10 = i58;
                    int i71 = i12;
                    while (true) {
                        i71--;
                        if (i71 >= 0) {
                            bArr2[i10] = bArr[i60];
                            i60++;
                            i10++;
                        }
                    }
                }
                i54 = i10;
                i56 = i57 + i44;
                i55++;
            }
            i45++;
            iAdler32 = !z4 ? adler32(bArr2, i54 - i47, i47, iAdler32) : iAdler32;
            i48 = i55;
            i46 = i54;
        }
        if (z4) {
            int i72 = i46 + 1;
            bArr2[i46] = bArr[2];
            int i73 = i72 + 1;
            bArr2[i72] = bArr[3];
            int i74 = i73 + 1;
            bArr2[i73] = bArr[4];
            int i75 = i74 + 1;
            bArr2[i74] = bArr[5];
            int i76 = i75 + 1;
            bArr2[i75] = bArr[6];
            int i77 = i76 + 1;
            bArr2[i76] = bArr[7];
            i9 = i77 + 1;
            bArr2[i77] = bArr[8];
            bArr2[i9] = bArr[9];
        } else {
            int i78 = i46 + 1;
            bArr2[i46] = (byte) (iAdler32 >>> 24);
            int i79 = i78 + 1;
            bArr2[i78] = (byte) (iAdler32 >>> 16);
            int i80 = i79 + 1;
            bArr2[i79] = (byte) (iAdler32 >>> 8);
            int i81 = i80 + 1;
            bArr2[i80] = (byte) iAdler32;
            int iCrc324 = crc32(bArr2, i37, i81 - i37);
            int i82 = i81 + 1;
            bArr2[i81] = (byte) (iCrc324 >>> 24);
            int i83 = i82 + 1;
            bArr2[i82] = (byte) (iCrc324 >>> 16);
            int i84 = i83 + 1;
            bArr2[i83] = (byte) (iCrc324 >>> 8);
            bArr2[i84] = (byte) iCrc324;
            i9 = i84;
        }
        int i85 = i9 + 5;
        int i86 = i85 + 1;
        bArr2[i85] = 73;
        int i87 = i86 + 1;
        bArr2[i86] = 69;
        int i88 = i87 + 1;
        bArr2[i87] = 78;
        int i89 = i88 + 1;
        bArr2[i88] = 68;
        int i90 = i89 + 1;
        bArr2[i89] = -82;
        int i91 = i90 + 1;
        bArr2[i90] = 66;
        bArr2[i91] = 96;
        bArr2[i91 + 1] = -126;
        return bArr2;
    }

    public static void drawAntiAliasedLine(Graphics graphics, int i, int i2, int i3, int i4, int i5) {
        IRenderingPlatform renderingPlatform = Toolkit.getRenderingPlatform();
        renderingPlatform.pushParameters();
        renderingPlatform.setGraphicsContext(graphics);
        renderingPlatform.setColorARGB(i5);
        renderingPlatform.drawLine(i, i2, i3, i4);
        renderingPlatform.popParameters();
    }

    public static void fillApproximateBezier(Graphics graphics, int i, int i2, int[] iArr, int i3, int i4) {
        IRenderingPlatform renderingPlatform = Toolkit.getRenderingPlatform();
        renderingPlatform.setGraphicsContext(graphics);
        renderingPlatform.setPivot(i, i2);
        renderingPlatform.fillBezier(iArr, i3, i4);
    }

    public static void fillApproximateBezier(Graphics graphics, int i, int i2, short[] sArr, int i3, int i4) {
        int i5 = i4 * 6;
        allocateTempMemory(i5);
        int i6 = 0;
        int i7 = i3;
        while (i6 < i5) {
            smTemp[i6] = sArr[i7];
            i6++;
            i7++;
        }
        fillApproximateBezier(graphics, i, i2, smTemp, 0, i4);
    }

    public static void fillEllipse(Graphics graphics, int i, int i2, int i3, int i4) {
        IRenderingPlatform renderingPlatform = Toolkit.getRenderingPlatform();
        renderingPlatform.setGraphicsContext(graphics);
        renderingPlatform.fillEllipse(i, i2, i3, i4);
    }

    public static void fillPolygon(Graphics graphics, int i, int i2, int[] iArr, int i3, int i4) {
        IRenderingPlatform renderingPlatform = Toolkit.getRenderingPlatform();
        renderingPlatform.setGraphicsContext(graphics);
        renderingPlatform.setPivot(i, i2);
        renderingPlatform.fillPolygon(iArr, i3, i4);
    }

    public static void fillPolygon(Graphics graphics, int i, int i2, short[] sArr, int i3, int i4) {
        int i5 = i4 * 2;
        allocateTempMemory(i5);
        int i6 = 0;
        int i7 = i3;
        while (i6 < i5) {
            smTemp[i6] = sArr[i7];
            i6++;
            i7++;
        }
        fillPolygon(graphics, i, i2, smTemp, 0, i4);
    }

    public static void fillTriangle(Graphics graphics, int i, int i2, int i3, int i4, int i5, int i6) {
        IRenderingPlatform renderingPlatform = Toolkit.getRenderingPlatform();
        renderingPlatform.setGraphicsContext(graphics);
        renderingPlatform.setColor(graphics.getColor());
        renderingPlatform.fillTriangle(i, i2, i3, i4, i5, i6);
    }

    private static int findIndex(int i, int i2, int i3) {
        int size = smImages.size();
        while (true) {
            size--;
            if (size < 0) {
                return -1;
            }
            if (smImageIDs[size] == i) {
                if (i == -1) {
                    return size;
                }
                if (i2 == (smPaletteIndices[size] & DChocImage.COLOR_DEPTH_DEFAULT) && i3 == smImageTransformation[size]) {
                    return size;
                }
            }
        }
    }

    public static void freeCRCTable() {
        smCrcTable = null;
    }

    public static void freeImageResources() {
        smImages.removeAllElements();
        smImageIDs = new int[INITIAL_SIZE];
        smPaletteIndices = new byte[INITIAL_SIZE];
        smImageTransformation = new short[INITIAL_SIZE];
        smImageReferences = new short[INITIAL_SIZE];
        clearImageLoadingList();
    }

    public static int getAdditionalLoadingCount() {
        return smImageHandlesSize;
    }

    public static DChocImage getDChocImage(int i) {
        return getDChocImage(i, 0, 1024);
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0006, code lost:
    
        r0 = getImageFromCache(r6, r7, r8, r8, 0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.digitalchocolate.androidrollergapp.DChocImage getDChocImage(int r6, int r7, int r8) throws java.io.IOException {
        /*
            r5 = 0
            r0 = 0
            r1 = -1
            if (r6 != r1) goto L6
        L5:
            return r0
        L6:
            com.digitalchocolate.androidrollergapp.DChocImage r0 = getImageFromCache(r6, r7, r8, r8, r5)
            if (r0 != 0) goto L5
            java.io.DataInputStream r1 = com.digitalchocolate.androidrollergapp.Toolkit.getResourceStream(r6)
            int r2 = r1.read()     // Catch: java.io.IOException -> L31
            int r2 = r2 >> 4
            int r2 = r2 + 1
            int r3 = r1.readInt()     // Catch: java.io.IOException -> L31
            int r4 = r1.read()     // Catch: java.io.IOException -> L31
            r1.close()     // Catch: java.io.IOException -> L31
            int r1 = r8 * r2
            com.digitalchocolate.androidrollergapp.DChocImage r0 = getImage(r3, r4, r7, r1)     // Catch: java.io.IOException -> L31
        L29:
            r1 = r6
            r2 = r7
            r3 = r8
            r4 = r8
            putImageToCache(r0, r1, r2, r3, r4, r5)
            goto L5
        L31:
            r1 = move-exception
            goto L29
        */
        throw new UnsupportedOperationException("Method not decompiled: com.digitalchocolate.androidrollergapp.DavinciUtilities.getDChocImage(int, int, int):com.digitalchocolate.androidrollergapp.DChocImage");
    }

    private static DChocImage getImage(int i, int i2, int i3, int i4) throws IOException {
        int i5;
        int length;
        int i6;
        if (i2 == 253) {
            return new DChocImage(i);
        }
        byte[] resourceBytes = Toolkit.getResourceBytes(i);
        int i7 = (resourceBytes[1] & DChocImage.COLOR_DEPTH_DEFAULT) | (resourceBytes[0] << 8);
        int[] iArr = null;
        if (i2 == 254 || i2 == 255) {
            int length2 = (resourceBytes.length - 2) / i7;
            if (i2 == 254) {
                i5 = 2;
                length = length2 / 3;
                i6 = i2;
            } else {
                i5 = 2;
                length = length2 >> 2;
                i6 = i2;
            }
        } else {
            length = (resourceBytes.length - 10) / i7;
            i5 = 10;
            iArr = smPalettes[i2];
            i6 = -1;
        }
        return createMIDP2Image(iArr, resourceBytes, i7, length, i6, i5, i3, i4);
    }

    public static Image getImage(int i) {
        return getDChocImage(i).getImage();
    }

    public static Image getImage(int i, int i2, int i3) {
        return getDChocImage(i, i2, i3).getImage();
    }

    public static DChocImage getImageFromCache(int i) {
        return (DChocImage) smImages.elementAt(i);
    }

    private static DChocImage getImageFromCache(int i, int i2, int i3, int i4, int i5) {
        return null;
    }

    private static int[] getImagePixels(int[] iArr, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int i8 = 4;
        if (i3 == -1) {
            i8 = 1;
        } else if (i3 == 254) {
            i8 = 3;
        }
        int scaledValue = getScaledValue(i, i6);
        int scaledValue2 = getScaledValue(i2, i6);
        int[] iArr2 = new int[scaledValue * scaledValue2];
        int i9 = 0;
        for (int i10 = 0; i10 < scaledValue2; i10++) {
            int i11 = ((i6 != 1024 ? (i10 << 10) / i6 : i10) * i * i8) + i4;
            if (i3 == -1 && i6 == 1024) {
                i7 = i9;
                int i12 = scaledValue;
                while (true) {
                    i12--;
                    if (i12 >= 0) {
                        iArr2[i7] = iArr[bArr[i11] & DChocImage.COLOR_DEPTH_DEFAULT];
                        i11++;
                        i7++;
                    }
                }
            } else {
                i7 = i9;
                for (int i13 = 0; i13 < scaledValue; i13++) {
                    int i14 = ((i6 != 1024 ? (i13 << 10) / i6 : i13) * i8) + i11;
                    if (i3 == -1) {
                        iArr2[i7] = iArr[bArr[i14] & DChocImage.COLOR_DEPTH_DEFAULT];
                        i7++;
                    } else {
                        int i15 = (bArr[i14] & DChocImage.COLOR_DEPTH_DEFAULT) << 16;
                        int i16 = i14 + 1;
                        int i17 = (bArr[i16] & DChocImage.COLOR_DEPTH_DEFAULT) << 8;
                        int i18 = i16 + 1;
                        int i19 = bArr[i18] & DChocImage.COLOR_DEPTH_DEFAULT;
                        if (i3 == 255) {
                            iArr2[i7] = ((bArr[i18 + 1] & DChocImage.COLOR_DEPTH_DEFAULT) << 24) | i15 | i17 | i19;
                            i7++;
                        } else {
                            iArr2[i7] = i15 | (-16777216) | i17 | i19;
                            i7++;
                        }
                    }
                }
            }
            i9 = i7;
        }
        transformBuffer(iArr2, scaledValue, scaledValue2, i5);
        return iArr2;
    }

    public static int getMovementX(byte[] bArr, int i) {
        if (i != -1) {
            int i2 = i << 1;
            int i3 = (bArr[i2 + 1] & DChocImage.COLOR_DEPTH_DEFAULT) | (bArr[i2] << 8);
            int i4 = i3 + 1;
            if (bArr[i3] == 0) {
                int i5 = i4 + 1;
                return (short) ((bArr[i5] & DChocImage.COLOR_DEPTH_DEFAULT) | (bArr[i4] << 8));
            }
        }
        return 0;
    }

    public static int getMovementY(byte[] bArr, int i) {
        if (i != -1) {
            int i2 = i << 1;
            int i3 = (bArr[i2 + 1] & DChocImage.COLOR_DEPTH_DEFAULT) | (bArr[i2] << 8);
            int i4 = i3 + 1;
            if (bArr[i3] == 0) {
                int i5 = i4 + 2;
                return (short) ((bArr[i5] << 8) | (bArr[i5 + 1] & DChocImage.COLOR_DEPTH_DEFAULT));
            }
        }
        return 0;
    }

    public static final int getRange(int i, int i2) {
        int iAbs = Math.abs(i2);
        int iAbs2 = Math.abs(i);
        return iAbs2 > iAbs ? ((iAbs * TextIDs.TID_HELP_CARTS_CONTENT) >> 8) + iAbs2 : iAbs + ((iAbs2 * TextIDs.TID_HELP_CARTS_CONTENT) >> 8);
    }

    private static int getScaledValue(int i, int i2) {
        int i3 = (i * i2) >> 10;
        if (i3 > 0) {
            return i3;
        }
        return 1;
    }

    public static SpriteObject getSpriteObjectFromCache(int i) {
        return (SpriteObject) smSpriteObjectCache.elementAt(i);
    }

    public static boolean hasNestedAnimations(Animation animation, int i) {
        return animation.hasNestedAnimations(i);
    }

    public static void initialize() {
    }

    public static int interpolateColor(int i, int i2, int i3, int i4) {
        return (interpolateValue(i >>> 24, i2 >>> 24, i3, i4) << 24) | (interpolateValue((i >> 16) & 255, (i2 >> 16) & 255, i3, i4) << 16) | (interpolateValue(i & 65280, i2 & 65280, i3, i4) & 65280) | interpolateValue(i & 255, i2 & 255, i3, i4);
    }

    public static int interpolateValue(int i, int i2, int i3, int i4) {
        return (((i2 - i) * i3) / i4) + i;
    }

    private static boolean isTextureReferenced(int i) {
        int size = smImages.size();
        while (true) {
            size--;
            if (size < 0) {
                return false;
            }
            if (smImageReferences[size] > 0 && (smImageIDs[size] >> 16) == i) {
                return true;
            }
        }
    }

    public static Animation loadAnimation(int i) {
        return loadAnimation(i, false);
    }

    public static Animation loadAnimation(int i, boolean z) {
        if (!z) {
            resetLoading();
        }
        try {
            return new Animation(i, z);
        } catch (IOException e) {
            return null;
        }
    }

    public static int loadAnimationIntoCache(int i, boolean z) {
        if (smSpriteObjectID == null) {
            smSpriteObjectID = new int[INITIAL_SIZE];
            smSpriteObjectReference = new short[INITIAL_SIZE];
            smSpriteObjectCache = new Vector(INITIAL_SIZE);
        }
        int size = smSpriteObjectCache.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (i == smSpriteObjectID[i2] && getSpriteObjectFromCache(i2) != null) {
                return i2;
            }
        }
        int i3 = 0;
        while (i3 < size && getSpriteObjectFromCache(i3) != null) {
            i3++;
        }
        if (i3 >= smSpriteObjectID.length) {
            int length = smSpriteObjectID.length;
            int i4 = length << 1;
            short[] sArr = new short[i4];
            int[] iArr = new int[i4];
            System.arraycopy(smSpriteObjectReference, 0, sArr, 0, length);
            System.arraycopy(smSpriteObjectID, 0, iArr, 0, length);
            smSpriteObjectReference = sArr;
            smSpriteObjectID = iArr;
        }
        if (i3 == size) {
            smSpriteObjectCache.addElement(null);
        }
        smSpriteObjectCache.setElementAt(new SpriteObject(), i3);
        smSpriteObjectID[i3] = i;
        smSpriteObjectCache.setElementAt(new SpriteObject(loadAnimation(i, z)), i3);
        return i3;
    }

    public static Animation[] loadAnimations(int[] iArr) {
        return loadAnimations(iArr, false);
    }

    public static Animation[] loadAnimations(int[] iArr, boolean z) {
        Animation[] animationArr = new Animation[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            animationArr[i] = loadAnimation(iArr[i], z);
        }
        return animationArr;
    }

    public static void loadImageIntoCache(int i, int i2, int i3, int i4, boolean z, int[] iArr) throws IOException {
        iArr[1] = 0;
        if (i2 == 253) {
        }
        int i5 = ((i4 - 1) << 8) | 0;
        int iFindIndex = findIndex(i, i2, i5);
        if (iFindIndex != -1) {
            iArr[0] = iFindIndex;
            if (1 != 0) {
                iArr[1] = i3;
                return;
            }
            return;
        }
        int iFindIndex2 = findIndex(-1, 0, 0);
        if (iFindIndex2 == -1) {
            iFindIndex2 = smImages.size();
            if (iFindIndex2 == smImageIDs.length) {
                int[] iArr2 = new int[iFindIndex2 << 1];
                byte[] bArr = new byte[iFindIndex2 << 1];
                short[] sArr = new short[iFindIndex2 << 1];
                short[] sArr2 = new short[iFindIndex2 << 1];
                System.arraycopy(smImageIDs, 0, iArr2, 0, iFindIndex2);
                System.arraycopy(smPaletteIndices, 0, bArr, 0, iFindIndex2);
                System.arraycopy(smImageReferences, 0, sArr, 0, iFindIndex2);
                System.arraycopy(smImageTransformation, 0, sArr2, 0, iFindIndex2);
                smImageIDs = iArr2;
                smPaletteIndices = bArr;
                smImageReferences = sArr;
                smImageTransformation = sArr2;
            }
            smImageReferences[iFindIndex2] = 0;
        }
        smImageIDs[iFindIndex2] = i;
        smPaletteIndices[iFindIndex2] = (byte) i2;
        smImageTransformation[iFindIndex2] = (short) i5;
        if (iFindIndex2 == smImages.size()) {
            smImages.addElement(null);
        }
        if (z) {
            smImages.setElementAt(getImage(i, i2, 0, i4 << 10), iFindIndex2);
        } else {
            addImageToLoadList(iFindIndex2, i, i2, 0, i4);
        }
        iArr[0] = iFindIndex2;
        if (1 != 0) {
            iArr[1] = i3;
        }
    }

    public static void loadNext() throws IOException {
        int i = smCurrentLoadingStep * 5;
        int i2 = i + 1;
        int i3 = smImageHandles[i];
        if (getImageFromCache(i3) == null) {
            int i4 = i2 + 1;
            int i5 = i4 + 1;
            smImages.setElementAt(getImage(smImageHandles[i2], smImageHandles[i4], smImageHandles[i5], smImageHandles[i5 + 1] << 10), i3);
        }
        smCurrentLoadingStep++;
        if (smCurrentLoadingStep == smImageHandlesSize) {
            clearImageLoadingList();
        }
    }

    private static DChocImage loadTextureImage(int i, int i2, int i3, int i4) {
        int i5 = i >> 16;
        if (smTextureImage[i5] == null) {
            smTextureImage[i5] = getImage(smTextureRID[i5], i2, i3, i4);
        }
        DChocImage dChocImage = new DChocImage(smTextureImage[i5]);
        short[] sArr = smTextureCoordinate[i5][65535 & i];
        dChocImage.setRegion(sArr[0], sArr[1], sArr[2], sArr[3]);
        return dChocImage;
    }

    public static void modRefCounters(Animation animation, int i) {
        if (animation == null) {
            return;
        }
        int frameCount = animation.getFrameCount();
        while (true) {
            frameCount--;
            if (frameCount < 0) {
                return;
            }
            if (!animation.isFrameReference(frameCount)) {
                int[][] renderables = animation.getFrame(frameCount).getRenderables();
                int length = renderables.length;
                while (true) {
                    length--;
                    if (length >= 0) {
                        int[] iArr = renderables[length];
                        if (iArr[0] == 1) {
                            int i2 = iArr[3];
                            int i3 = smImageReferences[i2] + i;
                            if (i3 < 0) {
                                i3 = 0;
                            }
                            smImageReferences[i2] = (short) i3;
                        }
                    }
                }
            }
        }
    }

    private static void putImageToCache(DChocImage dChocImage, int i, int i2, int i3, int i4, int i5) {
        if (smImageCache == null) {
            smImageCache = new Hashtable();
        }
        smImageCache.put("" + i2 + "." + i3 + "." + i4 + "." + i, dChocImage);
    }

    public static void releaseUnreferencedImages() {
        int size = smImages.size();
        while (true) {
            size--;
            if (size < 0) {
                return;
            }
            if (smImageReferences[size] == 0 && getImageFromCache(size) != null) {
                smImages.setElementAt(null, size);
                smImageIDs[size] = -1;
            }
        }
    }

    public static void resetLoading() {
        smCurrentLoadingStep = 0;
    }

    public static final int sin(int i) {
        return MathUtils.sin(i);
    }

    public static void swap(int[] iArr, int i, int i2, int i3) {
        int i4 = i3;
        int i5 = i2;
        int i6 = i;
        while (true) {
            i4--;
            if (i4 < 0) {
                return;
            }
            int i7 = iArr[i6];
            iArr[i6] = iArr[i5];
            iArr[i5] = i7;
            i6++;
            i5++;
        }
    }

    private static void textureInit() throws IOException {
        DataInputStream resourceStream = Toolkit.getResourceStream(-1);
        int i = resourceStream.readShort();
        smTextureRID = new int[i];
        smTextureImage = new DChocImage[i];
        smTextureCoordinate = new short[i][][];
        for (int i2 = 0; i2 < i; i2++) {
            resourceStream.read();
            int i3 = resourceStream.readInt();
            resourceStream.read();
            smTextureRID[i2] = i3;
            int i4 = resourceStream.readShort();
            smTextureCoordinate[i2] = (short[][]) Array.newInstance((Class<?>) Short.TYPE, i4, 4);
            for (int i5 = 0; i5 < i4; i5++) {
                for (int i6 = 0; i6 < 4; i6++) {
                    smTextureCoordinate[i2][i5][i6] = resourceStream.readShort();
                }
            }
        }
        resourceStream.close();
    }

    public static void transformBuffer(int[] iArr, int i, int i2, int i3) {
        int i4 = 0;
        if (i3 == 0) {
            return;
        }
        if ((i3 & 3) == 3) {
            int i5 = i * i2;
            int i6 = i5 >> 1;
            for (int i7 = i5 - 1; i7 >= i6; i7--) {
                int i8 = iArr[i7];
                iArr[i7] = iArr[i4];
                iArr[i4] = i8;
                i4++;
            }
            return;
        }
        if ((i3 & 1) != 0) {
            int i9 = i >> 1;
            int i10 = i2;
            while (true) {
                i10--;
                if (i10 < 0) {
                    return;
                }
                int i11 = i4 + i9;
                int i12 = i4;
                int i13 = (i4 + i) - 1;
                while (i12 < i11) {
                    int i14 = iArr[i12];
                    iArr[i12] = iArr[i13];
                    iArr[i13] = i14;
                    i13--;
                    i12++;
                }
                i4 = (i - i9) + i12;
            }
        } else {
            int i15 = (i2 - 1) * i;
            int i16 = (i2 >> 1) * i;
            int i17 = i;
            while (true) {
                i17--;
                if (i17 < 0) {
                    return;
                }
                int i18 = i15 + i17;
                int i19 = i17 + i16;
                int i20 = i17;
                while (i20 < i19) {
                    int i21 = iArr[i20];
                    iArr[i20] = iArr[i18];
                    iArr[i18] = i21;
                    i20 += i;
                    i18 -= i;
                }
            }
        }
    }
}
