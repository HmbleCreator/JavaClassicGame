package com.digitalchocolate.androidrollergapp;

import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Enumeration;
import java.util.Vector;

/* loaded from: classes.dex */
public class TrackModel {
    private static final int CONTROL_POINT_SCALE = 3;
    private static final boolean DEBUG_VERBOSE = false;
    public static final int EXTRA_TRACK_POINTS = 8;
    private static final int EXTRA_TRACK_POINTS_SAFE = 24;
    public static final int MAX_SPLINE_LENGTH = 450;
    public static final int OBJECTTYPE_PICKUP_COUNT = 7;
    private static final int OBJECTTYPE_PICKUP_END = 15;
    public static final int OBJECTTYPE_PICKUP_EXCITER = 11;
    public static final int OBJECTTYPE_PICKUP_GOD = 14;
    public static final int OBJECTTYPE_PICKUP_JUMP = 13;
    public static final int OBJECTTYPE_PICKUP_NITRO = 9;
    public static final int OBJECTTYPE_PICKUP_SMILEY = 10;
    public static final int OBJECTTYPE_PICKUP_SPRINGS = 15;
    private static final int OBJECTTYPE_PICKUP_START = 9;
    public static final int OBJECTTYPE_PICKUP_STICKY = 12;
    public static final int OBJECTTYPE_SIGN_BREAK_GO = 5;
    private static final int OBJECTTYPE_SIGN_END = 8;
    public static final int OBJECTTYPE_SIGN_FALLING_RAILS = 8;
    public static final int OBJECTTYPE_SIGN_HILL = 2;
    public static final int OBJECTTYPE_SIGN_JUMP = 1;
    public static final int OBJECTTYPE_SIGN_LOOP = 3;
    public static final int OBJECTTYPE_SIGN_SPRINGS = 4;
    private static final int OBJECTTYPE_SIGN_START = 1;
    public static final int OBJECTTYPE_SIGN_TUNNEL = 6;
    public static final int OBJECTTYPE_SIGN_WATER_SPLASH = 7;
    public static final int SEGMENTS_PER_PIECE = 6;
    public static final int SIGN_LOC = 1;
    public static final int SIGN_TYPE = 0;
    public static final int TRACK_COUNT = 2;
    public static final int TRACK_FLAG_BREAK_GO_MASK = 2;
    public static final int TRACK_FLAG_FALL_MASK = 8;
    public static final int TRACK_FLAG_HOLE_MASK = 1;
    public static final int TRACK_FLAG_REPLAY_0 = 16;
    public static final int TRACK_FLAG_REPLAY_1 = 32;
    public static final int TRACK_FLAG_TUNNEL_MASK = 4;
    public static final int TRACK_GHOST = 1;
    public static final int TRACK_USER = 0;
    private static final int X = 0;
    private static final int Y = 1;
    private static final int Z = 2;
    public static byte[] replayCameraData;
    public static boolean[] smHasPole;
    public static int smHeightMax;
    public static int smHeightMaxPointID;
    public static int smHeightMin;
    public static int smHeightMinPointID;
    public static int[] smPieceEndIdx;
    public static int[] smScaledTrackPointX;
    public static int[] smScaledTrackPointY;
    static short[] smTrackCurve;
    public static int smTrackEndSegmentIdx;
    public static int smTrackFallingEndId;
    public static int smTrackFallingStartId;
    public static byte[][] smTrackFlags;
    public static int smTrackPointCount;
    public static int[][] smTrackPointOffY;
    public static int[][] smTrackPointTimer;
    public static int[][] smTrackPointVelY;
    public static int[] smTrackPointX;
    public static int[] smTrackPointY;
    static int[] smTrackSlope;
    static int[][] sm_signs;
    public static boolean[] trackHasVerticalSeparator;
    public static final int UNIT_TO_METERS_OPERANT = FP.div(FP.toFP(1), FP.toFP(381) / 25);
    static int[] COUNTRY_IDX_TO_RID = {131072, ResourceIDs.RID_LEVELS_COUNTRY_1, 262144, ResourceIDs.RID_LEVELS_EXTRA};
    private static final int TRACK_EXTRA_POINT_DISTANCE = FP.toFP(30);

    private static void createReplayCntrData() {
        int i;
        int i2;
        for (int i3 = 0; i3 < smTrackPointCount; i3++) {
            int i4 = (smTrackSlope[i3] + Util.SIN_SAMPLES_DEFAULT) % Util.SIN_SAMPLES_DEFAULT;
            if (i4 <= 90 || i4 >= 270) {
                replayCameraData[i3] = 2;
            } else {
                replayCameraData[i3] = 3;
            }
        }
        for (int i5 = 0; i5 < smTrackPointCount; i5++) {
            if (replayCameraData[i5] == 3 && (i2 = i5 - 8) > 0) {
                replayCameraData[i2] = 3;
            }
        }
        for (int i6 = smTrackPointCount - 1; i6 >= 0; i6--) {
            if (replayCameraData[i6] == 3 && (i = i6 + 8) < smTrackPointCount) {
                replayCameraData[i] = 3;
            }
        }
    }

    public static final void getSplinePoint(int i, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5) {
        int iMul = FP.mul(i, i);
        int iMul2 = FP.mul(iMul, i);
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = ((iArr[i2] * 2) - (iArr2[i2] * 2)) + iArr3[i2] + iArr4[i2];
            int i4 = (((iArr[i2] * (-3)) + (iArr2[i2] * 3)) - (iArr3[i2] * 2)) - iArr4[i2];
            int i5 = iArr3[i2];
            iArr5[i2] = FP.mul(i3, iMul2) + FP.mul(i4, iMul) + FP.mul(i5, i) + iArr[i2];
        }
    }

    private static final int[][] getSubDivideSplineSegment(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        int i;
        int[] iArr5;
        int[][] iArr6;
        boolean z;
        int[] iArr7;
        int[][] iArr8;
        int fp = FP.toFP(40);
        int fp2 = FP.toFP(5);
        int[][] iArr9 = {iArr, null, iArr2};
        int[] iArr10 = {0, -1, FP.toFP(1)};
        int length = Util.getLength(iArr3[0], iArr3[1]);
        if (length > 0) {
            i = FP.toInt(Util.getAngle(FP.div(iArr3[0], length), FP.div(iArr3[1], length), false) * Util.SIN_SAMPLES_DEFAULT) - 90;
            iArr5 = iArr10;
            iArr6 = iArr9;
        } else {
            i = 0;
            iArr5 = iArr10;
            iArr6 = iArr9;
        }
        while (true) {
            Vector vector = new Vector(iArr6.length >> 1);
            int i2 = 1;
            int i3 = i;
            while (i2 < iArr6.length) {
                if (iArr6[i2] == null) {
                    int i4 = (iArr5[i2 - 1] + iArr5[i2 + 1]) >> 1;
                    int[] iArr11 = new int[3];
                    getSplinePoint(i4, iArr, iArr2, iArr3, iArr4, iArr11);
                    iArr6[i2] = iArr11;
                    iArr5[i2] = i4;
                }
                int i5 = iArr6[i2][0] - iArr6[i2 - 1][0];
                int i6 = iArr6[i2][1] - iArr6[i2 - 1][1];
                int length2 = Util.getLength(i5, i6);
                int i7 = FP.toInt(Util.getAngle(FP.div(i5, length2), FP.div(i6, length2), false) * Util.SIN_SAMPLES_DEFAULT) - 90;
                if (length2 > fp || (Math.abs(i7 - i3) > 5 && length2 > fp2)) {
                    vector.addElement(new Integer(i2));
                }
                i2++;
                i3 = i7;
            }
            if (vector.size() > 0) {
                int[][] iArr12 = new int[iArr6.length + vector.size()][];
                int[] iArr13 = new int[iArr12.length];
                Enumeration enumerationElements = vector.elements();
                int iIntValue = ((Integer) enumerationElements.nextElement()).intValue();
                int i8 = 0;
                for (int i9 = 0; i9 < iArr6.length; i9++) {
                    if (i9 == iIntValue) {
                        iIntValue = enumerationElements.hasMoreElements() ? ((Integer) enumerationElements.nextElement()).intValue() : -1;
                        i8++;
                    }
                    iArr12[i9 + i8] = iArr6[i9];
                    iArr13[i9 + i8] = iArr5[i9];
                }
                z = false;
                iArr8 = iArr12;
                iArr7 = iArr13;
            } else {
                z = true;
                iArr7 = iArr5;
                iArr8 = iArr6;
            }
            if (z) {
                return iArr8;
            }
            iArr5 = iArr7;
            iArr6 = iArr8;
        }
    }

    public static int getTrackPointX(int i) {
        return smScaledTrackPointX[i];
    }

    public static int getTrackPointY(int i) {
        return smScaledTrackPointY[i];
    }

    public static final void initTrackModel(int i) {
        smTrackPointX = new int[i];
        smTrackPointY = new int[i];
        smScaledTrackPointX = new int[i];
        smScaledTrackPointY = new int[i];
        smTrackSlope = new int[i];
        smTrackFlags = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, 2, i);
        smTrackPointTimer = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 2, i);
        smTrackPointOffY = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 2, i);
        smTrackPointVelY = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 2, i);
        smHasPole = new boolean[i];
    }

    public static boolean isBreakGo(int i) {
        return (smTrackFlags[0][i] & 2) != 0;
    }

    public static boolean isFall(Train train, int i) {
        return (smTrackFlags[(train == null || !train.mIsGhost) ? (char) 0 : (char) 1][i] & 8) != 0 || Cheats.smInteractiveFallingDownOn;
    }

    public static boolean isHole(Train train, int i) {
        return (smTrackFlags[(train == null || !train.mIsGhost) ? (char) 0 : (char) 1][i] & 1) != 0;
    }

    public static boolean isTunnel(int i) {
        return (smTrackFlags[0][i] & 4) != 0;
    }

    public static final void loadLevel(int i) {
        DataInputStream resourceStream = null;
        if (0 == 0) {
            try {
                resourceStream = Toolkit.getResourceStream(COUNTRY_IDX_TO_RID[i / 33]);
            } catch (IOException e) {
                return;
            }
        }
        loadLevel(resourceStream, i, i % 33);
        Engine2D.interactiveInit(i);
        resourceStream.close();
    }

    public static void loadLevel(DataInputStream dataInputStream, int i, int i2) throws IOException {
        int i3;
        Entity.smSmileysCount = 0;
        Entity.smNumStickies = 0;
        Entity.smNumNitros = 0;
        Entity.smNumExciters = 0;
        Entity.smNumWinged = 0;
        Entity.smNumGod = 0;
        dataInputStream.read();
        dataInputStream.skip(i2 * 4);
        dataInputStream.skip(dataInputStream.readInt());
        int i4 = dataInputStream.readShort();
        int i5 = i4 + 1;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i5, 2);
        int[][] iArr2 = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i5, 2);
        int[] iArr3 = new int[i5];
        iArr[0][0] = 0;
        iArr[0][1] = 0;
        iArr2[0][0] = 0;
        iArr2[0][1] = 0;
        for (int i6 = 1; i6 < i5; i6++) {
            int i7 = dataInputStream.read();
            int i8 = i7 & 1;
            int i9 = (i7 >> 1) & 1;
            iArr3[i6] = (byte) i7;
            int fp = FP.toFP(dataInputStream.readByte() * 3);
            int fp2 = FP.toFP(dataInputStream.readByte() * 3);
            iArr[i6][0] = fp + iArr[i6 - 1][0];
            iArr[i6][1] = fp2 + iArr[i6 - 1][1];
        }
        Vector vector = new Vector();
        Vector vector2 = new Vector();
        int[] iArr4 = new int[i4];
        int i10 = 0;
        for (int i11 = 0; i11 < 9; i11++) {
            vector.addElement(new int[]{i10, 0});
            vector2.addElement(new Integer(0));
            i10 += TRACK_EXTRA_POINT_DISTANCE;
        }
        for (int i12 = 0; i12 < i5; i12++) {
            int[] iArr5 = iArr[i12];
            iArr5[0] = iArr5[0] + i10;
        }
        smTrackFallingStartId = -1;
        smTrackFallingEndId = -1;
        int[] iArr6 = {0, 0};
        int i13 = 1;
        while (i13 < i5) {
            int[] iArr7 = iArr[i13 - 1];
            int[] iArr8 = iArr[i13];
            int[] iArr9 = {0, 0};
            if (i13 < i5 - 1) {
                int iDiv = FP.div(Math.min(Util.getLengthFast(iArr[i13][0] - iArr[i13 - 1][0], iArr[i13][1] - iArr[i13 - 1][1]), Util.getLengthFast(iArr[i13 + 1][0] - iArr[i13][0], iArr[i13 + 1][1] - iArr[i13][1])), Util.getLengthFast(iArr[i13 + 1][0] - iArr[i13 - 1][0], iArr[i13 + 1][1] - iArr[i13 - 1][1]));
                FP.toFP(5, 10);
                iArr9[0] = FP.mul(iArr[i13 + 1][0] - iArr[i13 - 1][0], iDiv);
                iArr9[1] = FP.mul(iArr[i13 + 1][1] - iArr[i13 - 1][1], iDiv);
            } else {
                iArr9[0] = 0;
                iArr9[1] = 0;
            }
            int[][] subDivideSplineSegment = getSubDivideSplineSegment(iArr7, iArr8, iArr6, iArr9);
            for (int i14 = 0; i14 < subDivideSplineSegment.length - 1; i14++) {
                vector.addElement(subDivideSplineSegment[i14]);
                vector2.addElement(new Integer(iArr3[i13]));
            }
            iArr4[i13 - 1] = subDivideSplineSegment.length - 1;
            i13++;
            iArr6 = iArr9;
        }
        int[] iArr10 = (int[]) vector.lastElement();
        int i15 = iArr10[1];
        int i16 = iArr10[0] + TRACK_EXTRA_POINT_DISTANCE;
        for (int i17 = 0; i17 < 24; i17++) {
            vector.addElement(new int[]{i16, i15});
            vector2.addElement(new Integer(0));
            i16 += TRACK_EXTRA_POINT_DISTANCE;
        }
        Enumeration enumerationElements = vector.elements();
        smTrackPointCount = vector.size();
        Enumeration enumerationElements2 = vector2.elements();
        initTrackModel(smTrackPointCount);
        Vector vector3 = new Vector(50);
        Engine2D.smInteractiveWaterWorldYFP = 0;
        smHeightMin = 0;
        smHeightMax = 0;
        boolean z = false;
        int i18 = 0;
        int trackPointX = 0;
        while (enumerationElements.hasMoreElements()) {
            int[] iArr11 = (int[]) enumerationElements.nextElement();
            int i19 = iArr11[0];
            int i20 = -iArr11[1];
            smTrackPointX[i18] = i19;
            smTrackPointY[i18] = i20;
            smScaledTrackPointX[i18] = (i19 * 3) / 2;
            smScaledTrackPointY[i18] = (i20 * 3) / 2;
            if (smTrackPointY[i18] > Engine2D.smInteractiveWaterWorldYFP) {
                Engine2D.smInteractiveWaterWorldYFP = smTrackPointY[i18];
            }
            int gameAreaWidth = Game.getGameAreaWidth() << 12;
            if (!(z || i18 == 0 || getTrackPointX(i18 - 1) <= getTrackPointX(i18)) || (z && getTrackPointX(i18 - 1) < getTrackPointX(i18))) {
                boolean z2 = !z;
                vector3.addElement(new Integer(i18));
                trackPointX = getTrackPointX(i18);
                z = z2;
            } else if ((!z && getTrackPointX(i18) - trackPointX > gameAreaWidth) || (z && getTrackPointX(i18) - trackPointX < (-gameAreaWidth))) {
                vector3.addElement(new Integer(i18));
                trackPointX = getTrackPointX(i18);
            }
            if (getTrackPointY(i18) < smHeightMin) {
                smHeightMinPointID = i18;
                smHeightMin = getTrackPointY(i18);
            }
            if (getTrackPointY(i18) > smHeightMax) {
                smHeightMaxPointID = i18;
                smHeightMax = getTrackPointY(i18);
            }
            smTrackFlags[0][i18] = ((Integer) enumerationElements2.nextElement()).byteValue();
            smTrackFlags[1][i18] = smTrackFlags[0][i18];
            if (isFall(null, i18)) {
                if (smTrackFallingStartId == -1) {
                    smTrackFallingStartId = i18;
                    smTrackFallingEndId = i18;
                } else {
                    smTrackFallingEndId = i18;
                }
            }
            if (i18 > 0) {
                smTrackSlope[i18] = FP.toInt(Util.getAngle(i19 - smTrackPointX[i18 - 1], i20 - smTrackPointY[i18 - 1], true) * Util.SIN_SAMPLES_DEFAULT) - 90;
            }
            i18++;
        }
        vector3.addElement(new Integer(i18));
        int size = vector3.size();
        smPieceEndIdx = new int[size];
        int i21 = 0;
        while (true) {
            int i22 = i21;
            if (i22 == size) {
                break;
            }
            smPieceEndIdx[i22] = ((Integer) vector3.elementAt(i22)).intValue();
            i21 = i22 + 1;
        }
        int trackPointX2 = getTrackPointX(8);
        boolean z3 = false;
        for (int i23 = 8; i23 != (smTrackPointX.length - 8) - 6; i23++) {
            if ((!z3 && i23 != 0 && getTrackPointX(i23 - 1) > getTrackPointX(i23)) || (z3 && getTrackPointX(i23 - 1) < getTrackPointX(i23))) {
                z3 = !z3;
            }
            if (z3 || getTrackPointX(i23) - trackPointX2 < 573440) {
                smHasPole[i23] = false;
            } else {
                boolean z4 = false;
                int i24 = 0;
                while (true) {
                    if (i24 == smTrackPointX.length - 1) {
                        break;
                    }
                    if (getTrackPointX(i23) >= getTrackPointX(i24) && getTrackPointX(i23) <= getTrackPointX(i24 + 1) && getTrackPointY(i24) > getTrackPointY(i23) && getTrackPointY(i24 + 1) > getTrackPointY(i23)) {
                        z4 = true;
                        break;
                    }
                    i24++;
                }
                if (z4) {
                    smHasPole[i23] = false;
                } else {
                    trackPointX2 = getTrackPointX(i23);
                    smHasPole[i23] = !isHole(null, i23);
                }
            }
        }
        smTrackEndSegmentIdx = smTrackPointCount;
        int i25 = dataInputStream.readShort();
        Vector vector4 = new Vector(30);
        Vector vector5 = new Vector(30);
        Vector vector6 = new Vector(30);
        for (int i26 = 0; i26 < i25; i26++) {
            short s = dataInputStream.readShort();
            int i27 = dataInputStream.read();
            int i28 = dataInputStream.read();
            int i29 = 8;
            for (int i30 = 0; i30 < s; i30++) {
                i29 += iArr4[i30];
            }
            int iDiv2 = FP.div(FP.toFP(1), FP.toFP(iArr4[s]));
            int iDiv3 = FP.div(FP.toFP(i27), FP.toFP(256));
            for (int i31 = 0; i31 < iDiv3; i31 += iDiv2) {
                i29++;
            }
            if (i28 < 9 || i28 > 15) {
                switch (i28) {
                    case 1:
                        vector4.addElement(new Integer(1));
                        vector5.addElement(new Integer(i29));
                        break;
                    case 2:
                        vector4.addElement(new Integer(2));
                        vector5.addElement(new Integer(i29));
                        break;
                    case 3:
                        vector4.addElement(new Integer(3));
                        vector5.addElement(new Integer(i29));
                        break;
                    default:
                        dataInputStream.readShort();
                        dataInputStream.readShort();
                        dataInputStream.readByte();
                        break;
                }
            } else {
                byte b = dataInputStream.readByte();
                if ((i28 != 10 && i28 != 11) || RollerGameEngine.smGameMode != 1 || RollerGameEngine.smHotSeatMode != 0) {
                    vector6.addElement(new Entity(i29, i28 - 9, b));
                }
                if (i28 == 15) {
                    vector4.addElement(new Integer(4));
                    vector5.addElement(new Integer(i29));
                }
            }
        }
        int i32 = 0;
        while (true) {
            int i33 = i32;
            if (i33 < smTrackPointCount) {
                int i34 = 1;
                int i35 = isBreakGo(i33) ? 5 : isFall(null, i33) ? 8 : isTunnel(i33) ? 6 : -1;
                if (i35 > -1) {
                    while (true) {
                        i3 = i34;
                        if (i33 + i3 < smTrackPointCount && ((i35 == 5 && isBreakGo(i33 + i3)) || ((i35 == 8 && isFall(null, i33 + i3)) || (i35 == 6 && isTunnel(i33 + i3))))) {
                            i34 = i3 + 1;
                        }
                    }
                    int size2 = vector5.size();
                    int i36 = 0;
                    while (i36 < size2 && smTrackPointX[((Integer) vector5.elementAt(i36)).intValue()] < smTrackPointX[i33]) {
                        i36++;
                    }
                    vector4.insertElementAt(new Integer(i35), i36);
                    vector5.insertElementAt(new Integer(i33), i36);
                    int i37 = size2 + 1;
                    i34 = i3;
                }
                i32 = i34 + i33;
            } else {
                int size3 = vector4.size();
                sm_signs = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 2, size3);
                int i38 = 0;
                while (true) {
                    int i39 = i38;
                    if (i39 >= size3) {
                        Entity.populateManager(vector6);
                        precalculateVerticalSeparatorPositions();
                        return;
                    } else {
                        sm_signs[0][i39] = ((Integer) vector4.elementAt(i39)).intValue();
                        sm_signs[1][i39] = ((Integer) vector5.elementAt(i39)).intValue();
                        i38 = i39 + 1;
                    }
                }
            }
        }
    }

    private static void precalculateVerticalSeparatorPositions() {
        int i;
        int i2;
        int i3;
        trackHasVerticalSeparator = new boolean[10000];
        int length = smPieceEndIdx.length;
        int i4 = 0;
        int i5 = 0;
        boolean z = false;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 != length; i9++) {
            int i10 = smPieceEndIdx[i9];
            int i11 = smTrackPointX[i5] >> 12;
            int i12 = smTrackPointX[i10 - 1] >> 12;
            if (i9 != length - 1 && (i11 <= i12 || i11 > i12)) {
                i5 = i10;
                z = true;
            } else if (z) {
                if (1 != 0) {
                    int i13 = smTrackPointX[i4];
                    int i14 = smTrackPointY[i4];
                    i4++;
                    i3 = i8;
                    i = i14;
                    i2 = i13;
                } else {
                    i = i6;
                    i2 = i7;
                    i3 = i8;
                }
                while (i4 < i10) {
                    int i15 = smTrackPointX[i4];
                    int i16 = smTrackPointY[i4];
                    int iAbs = Math.abs((i16 - i) >> 12) + Math.abs((i15 - i2) >> 12) + i3;
                    if (iAbs > 40) {
                        trackHasVerticalSeparator[i4] = true;
                        iAbs = 0;
                    }
                    i4++;
                    i2 = i15;
                    i3 = iAbs;
                    i = i16;
                }
                i4 = i10;
                i8 = i3;
                i7 = i2;
                i6 = i;
                z = false;
            } else {
                i4 = i10;
                i5 = i10;
            }
        }
    }

    public static void releaseTrack() {
        smTrackPointX = null;
        smTrackPointY = null;
        smTrackFlags = (byte[][]) null;
        sm_signs = (int[][]) null;
        smTrackSlope = null;
        smPieceEndIdx = null;
        smHasPole = null;
        smTrackPointTimer = (int[][]) null;
        smTrackPointOffY = (int[][]) null;
        smTrackPointVelY = (int[][]) null;
    }

    public static void reset() {
        for (int i = 0; i < smTrackPointCount; i++) {
            if (isFall(null, i)) {
                for (int i2 = 0; i2 < 2; i2++) {
                    smTrackFlags[i2][i] = 8;
                }
            }
            for (int i3 = 0; i3 < 2; i3++) {
                smTrackPointTimer[i3][i] = 0;
                smTrackPointOffY[i3][i] = 0;
                smTrackPointVelY[i3][i] = 0;
            }
        }
    }
}
