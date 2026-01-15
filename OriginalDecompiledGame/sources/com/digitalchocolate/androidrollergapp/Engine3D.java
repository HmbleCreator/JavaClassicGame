package com.digitalchocolate.androidrollergapp;

import java.util.Vector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.m3g.Appearance;
import javax.microedition.m3g.IndexBuffer;
import javax.microedition.m3g.Transform;
import javax.microedition.m3g.TriangleStripArray;
import javax.microedition.m3g.VertexArray;
import javax.microedition.m3g.VertexBuffer;

/* loaded from: classes.dex */
class Engine3D {
    private static final float CAMERA_ANGLE_REPLAY = 95.0f;
    private static final float CAMERA_ANGLE_REPLAY_BACK = 45.0f;
    public static final float CAMERA_DIST_PLAY = 2400.0f;
    private static final float CAMERA_DIST_REPLAY = 1300.0f;
    private static final float CAMERA_DIST_REPLAY_BACK = 2000.0f;
    private static final int CAR_CNT_MAX = 4;
    private static final float COS_45 = 0.707f;
    private static final boolean DEBUG_SHOW_CUR_SEGMENT_START = false;
    private static final boolean DEBUG_SHOW_SIDE_VIEW = false;
    private static final int DECO_FIRST_UID = 500;
    private static final int FLY_CAR_CNT = 12;
    private static final int FLY_TIME = 2000;
    private static final float LIGHT_X = 0.707f;
    private static final int MASCOT_TRACK_COLOR_LUT_LENGTH = 128;
    private static final int MASCOT_TRACK_COLOR_SHIFT = 80;
    private static final float PEOPLE_UP_DELTA = 65.0f;
    public static final int PROP_COUNT = 8;
    private static final int QUAD_PER_SEGMENT = 6;
    public static final int REPLAY_STATE_BACK = 2;
    public static final int REPLAY_STATE_BOTTOM = 3;
    public static final int REPLAY_STATE_POINT = 0;
    public static final int REPLAY_STATE_SIDE = 1;
    public static final float SCALE_LOGIC_TO_WORLD = 0.001953125f;
    private static final int TRACK_PIECE_NUM = 76;
    private static final int TRI_PER_SEGMENT = 1;
    private static final float TRKPROF_DOWN_DY = 12.0f;
    private static final float TRKPROF_DY = 64.0f;
    private static final float TRKPROF_UP_DX = 12.0f;
    private static final float TRKPROF_UP_DY = 8.0f;
    private static final int TYPE_CAR = 0;
    private static final int TYPE_PARACHUTE_00 = 1;
    private static final int TYPE_PARACHUTE_01 = 2;
    private static final int TYPE_PARACHUTE_10 = 3;
    private static final int TYPE_PARACHUTE_11 = 4;
    private static final int UPDATE_DELTA_TIME = 20;
    private static final boolean USE_DEBUG_CAMERA = false;
    private static final boolean USE_M3G_FOG = false;
    private static final boolean USE_SKY_BOX = false;
    private static final boolean VERBOSE = false;
    private static final float VISIBILITY = 4500.0f;
    private static final int VISIBILITY_DELTA_X = 2250;
    private static final int VISIBILITY_DELTA_Y = 3093;
    private static final int VTX_BUFFER_CACHE_SIZE = 10;
    public static int debugTimeDelta;
    private static int[] sm_propAngles;
    private static Image[] sm_propImages;
    private static int[] sm_propTypes;
    private static int[] sm_propY;
    private Image m_background;
    private float m_backgroundAngle;
    private float m_boostAlpha;
    private float m_camAngle;
    private float m_camX;
    private float m_camY;
    private float m_camZ;
    private int m_curMesh;
    private int m_curSegment;
    private Effects m_effects;
    private int m_fixedSegmentIdx;
    private int m_holeMask;
    private byte[] m_idxBufIdx;
    private short[] m_mascotCurrentPieceIdx;
    private byte[] m_mascotHoleData;
    private byte[] m_mascotPrimitiveCnt;
    private int[][] m_mascotQuadColors;
    private int[][] m_mascotQuadVertices;
    private int[] m_mascotTrackColorLUT;
    private short[][] m_mascotTrackVertices;
    private byte[][] m_mascotTrackVtxColors;
    private int[][] m_mascotTriColors;
    private int[][] m_mascotTriVertices;
    private float[] m_pieceBoxMax;
    private float[] m_pieceBoxMin;
    private float[] m_pieceLoc;
    private int m_replayCameraSegment;
    private int m_replayCameraState;
    private int m_replayCameraStateTimer;
    private int m_splineCnt;
    public short[] m_splineToCurveAngle;
    public float[] m_splineToCurveX;
    public float[] m_splineToCurveY;
    private short[] m_splineToTiltAngle;
    private short[] m_splineToTiltAngleFiltered;
    private float m_trackAngle;
    private Appearance m_trackApp;
    private int m_trackColorB;
    private int m_trackColorG;
    private int m_trackColorR;
    private float[] m_trackFactoryPiece;
    private byte[] m_trackFactoryPieceColors;
    private float[] m_trackFactorySegment;
    private byte[] m_trackFactorySegmentColors;
    private float[] m_trackFactorySegmentNormals;
    private short[] m_trackFactoryShortPiece;
    private IndexBuffer[] m_trackIdxBuf;
    private int m_trackPieceCnt;
    private VertexBuffer[] m_trackVtxBuf;
    private VertexArray[] m_trackVtxColArray;
    private VertexArray[] m_trackVtxLocArray;
    private Train m_train;
    private boolean m_useFixedSegment;
    private float m_wobbleDelta;
    public static boolean DEBUG_SHOW_REF_PATH = false;
    private static final float LIGHT_Y = 0.0f;
    private static final float TRKPROF_DX = 80.0f;
    private static final float TRKPROF_DOWN_DX = 18.4f;
    private static final float[] TRACK_PROFILE = {-80.0f, LIGHT_Y, LIGHT_Y, 1.0f, -68.0f, LIGHT_Y, -8.0f, 1.0f, -92.0f, LIGHT_Y, -8.0f, 1.0f, TRKPROF_DX, LIGHT_Y, LIGHT_Y, 1.0f, 92.0f, LIGHT_Y, -8.0f, 1.0f, 68.0f, LIGHT_Y, -8.0f, 1.0f, LIGHT_Y, LIGHT_Y, -64.0f, 1.0f, TRKPROF_DOWN_DX, LIGHT_Y, -76.0f, 1.0f, -18.4f, LIGHT_Y, -76.0f, 1.0f};
    private static final float[] TRACK_PROFILE_NRML_M3G = {LIGHT_Y, LIGHT_Y, 1.0f, LIGHT_Y, 1.0f, LIGHT_Y, LIGHT_Y, LIGHT_Y, -1.0f, LIGHT_Y, LIGHT_Y, LIGHT_Y, LIGHT_Y, LIGHT_Y, 1.0f, LIGHT_Y, 1.0f, LIGHT_Y, LIGHT_Y, LIGHT_Y, -1.0f, LIGHT_Y, LIGHT_Y, LIGHT_Y, LIGHT_Y, LIGHT_Y, 1.0f, LIGHT_Y, 1.0f, LIGHT_Y, LIGHT_Y, LIGHT_Y, -1.0f, LIGHT_Y, LIGHT_Y, LIGHT_Y};
    private static final float LIGHT_Z = -0.707f;
    private static final float[] TRACK_PROFILE_NRML_MASCOT = {LIGHT_Z, LIGHT_Y, 0.707f, LIGHT_Y, 0.707f, LIGHT_Y, 0.707f, LIGHT_Y, -1.0f, LIGHT_Y, LIGHT_Y, LIGHT_Y, 0.707f, LIGHT_Y, 0.707f, LIGHT_Y, LIGHT_Z, LIGHT_Y, 0.707f, LIGHT_Y, -1.0f, LIGHT_Y, LIGHT_Y, LIGHT_Y, 0.707f, LIGHT_Y, 0.707f, LIGHT_Y, LIGHT_Z, LIGHT_Y, 0.707f, LIGHT_Y, LIGHT_Y, LIGHT_Y, 1.0f, LIGHT_Y};
    private static final float[] TRACK_PROFILE_NRML = TRACK_PROFILE_NRML_M3G;
    private static final int VERTICES_PER_SEGMENT = TRACK_PROFILE.length >> 2;
    private static final int[][] PROP_RIDS = new int[0][];
    private static final int[] HORIZON_OFFSET = {TextIDs.TID_MENU_HOTSEAT_2PLAYER, 128, TextIDs.TID_CUTSCENE_FIRSTTIME};
    private static final int[] BACKGROUND_RIDS = new int[0];
    public static final int[] BACKGROUND_TOP_COLORS = {8565749, 4342121, 8565749};
    private static final int[] BACKGROUND_BOTTOM_COLORS = {16495206, 5124700, 7447533};
    private static final int[] TRACK_COLORS = {13217036, 16776991, 16726057};
    public static float[] smProjectArray = new float[4];
    private static final float[] SKY_BOX_Z_OFFSETS = {350.0f, 100.0f, 100.0f};
    private static final int[] COUNTDOWN_START_CAM_DIST = {300, 1000, 1000};
    private static final int[] COUNTDOWN_START_CAM_ANGLE = {0, 45, 60};
    private static final int[] COUNTDOWN_START_CAM_HEIGHT = {180, CustomMenuObject.PC_PITCH_SCREEN_WIDTH, -800};
    private static final int[] COUNTDOWN_START_CAM_LOOKAT_DIST = {1200, 200, 300};
    private boolean m_use3dBG = false;
    private DajmMesh[] m_carFront = new DajmMesh[3];
    private DajmMesh[] m_car = new DajmMesh[3];
    private DajmMesh[] m_dudes = new DajmMesh[2];
    private DajmMesh[] m_hands = new DajmMesh[2];
    private DajmMesh[] m_parachuteDudes = new DajmMesh[4];
    private DajmMesh[] m_trainMeshes = new DajmMesh[4];
    private DajmMesh[] m_peopleMeshes = new DajmMesh[4];
    private DajmMesh[] m_handMeshes = new DajmMesh[4];
    private DajmMesh[][] m_skyBoxes = (DajmMesh[][]) null;
    private int m_currentBackgroundIdx = -1;
    private short[] m_visibiltyBegin = new short[76];
    private short[] m_visibiltyEnd = new short[76];
    private DajmMesh[][] m_decos = (DajmMesh[][]) null;
    int m_time = 0;
    private int m_nextFreeFlyCarIdx = 0;
    private float[] m_flyCarX = new float[12];
    private float[] m_flyCarY = new float[12];
    private float[] m_flyCarZ = new float[12];
    private float[] m_flyVelX = new float[12];
    private float[] m_flyVelY = new float[12];
    private float[] m_flyVelZ = new float[12];
    private int[] m_flyTimer = new int[12];
    private byte[] m_flyObjType = new byte[12];
    private int m_prevPieceIdx = -1;
    private Vector m_decoVec = new Vector();

    Engine3D() {
        DajmGraphics.init(0, 0, Toolkit.getScreenWidth(), Toolkit.getScreenHeight());
        this.m_effects = new Effects();
        this.m_effects.initBoostMesh();
        this.m_effects.initSparksMesh();
        this.m_trackFactorySegment = new float[VERTICES_PER_SEGMENT * 4];
        this.m_trackFactorySegmentNormals = new float[VERTICES_PER_SEGMENT * 4];
        this.m_trackFactorySegmentColors = new byte[VERTICES_PER_SEGMENT * 3];
        int i = VERTICES_PER_SEGMENT * 7;
        this.m_trackFactoryPiece = new float[i * 3];
        this.m_trackFactoryShortPiece = new short[i * 3];
        this.m_trackFactoryPieceColors = new byte[i * 3];
        preCacheModels();
        for (int i2 = 0; i2 < 3; i2++) {
            this.m_carFront[i2] = DajmGraphics.findMesh((i2 * 10) + TextIDs.TID_HELP_MODES_CONTENT_GHOST, -1, false);
            this.m_car[i2] = DajmGraphics.findMesh((i2 * 10) + 100, -1, false);
            this.m_carFront[i2].fixAppearanceUnlit();
            this.m_car[i2].fixAppearanceUnlit();
        }
        this.m_dudes[0] = DajmGraphics.findMesh(200, -1, false);
        this.m_dudes[1] = DajmGraphics.findMesh(210, -1, false);
        this.m_dudes[0].fixAppearanceUnlit();
        this.m_dudes[1].fixAppearanceUnlit();
        this.m_hands[0] = DajmGraphics.findMesh(201, -1, false);
        this.m_hands[1] = DajmGraphics.findMesh(TextIDs.TID_CHEATS_MAX_SPEED, -1, false);
        this.m_hands[0].fixAppearanceUnlit();
        this.m_hands[1].fixAppearanceUnlit();
        for (int i3 = 0; i3 < this.m_parachuteDudes.length; i3++) {
            this.m_parachuteDudes[i3] = DajmGraphics.findMesh(i3 + TextIDs.TID_ACHIEVEMENTS_TITLE_20, -1, false);
            this.m_parachuteDudes[i3].fixAppearanceUnlit();
        }
        this.m_pieceLoc = new float[228];
        this.m_pieceBoxMin = new float[228];
        this.m_pieceBoxMax = new float[228];
    }

    public static void DumpMem(String str) {
    }

    private void addSegment(Transform transform, int i) {
        boolean zIsHole = TrackModel.isHole(null, i);
        System.arraycopy(TRACK_PROFILE, 0, this.m_trackFactorySegment, 0, TRACK_PROFILE.length);
        System.arraycopy(TRACK_PROFILE_NRML, 0, this.m_trackFactorySegmentNormals, 0, TRACK_PROFILE_NRML.length);
        transform.transform(this.m_trackFactorySegment);
        transform.transform(this.m_trackFactorySegmentNormals);
        for (int i2 = 0; i2 < VERTICES_PER_SEGMENT; i2++) {
            int i3 = i2 * 3;
            int i4 = i2 * 4;
            getLightScale(this.m_trackFactorySegmentNormals[i4 + 0], this.m_trackFactorySegmentNormals[i4 + 1], this.m_trackFactorySegmentNormals[i4 + 2]);
        }
        if (this.m_curSegment == 0) {
            for (int i5 = 0; i5 < VERTICES_PER_SEGMENT; i5++) {
                int i6 = ((VERTICES_PER_SEGMENT * 6) + i5) * 3;
                int i7 = i5 * 4;
                int i8 = i5 * 3;
                this.m_trackFactoryPiece[i6 + 0] = this.m_trackFactorySegment[i7 + 0];
                this.m_trackFactoryPiece[i6 + 1] = this.m_trackFactorySegment[i7 + 1];
                this.m_trackFactoryPiece[i6 + 2] = this.m_trackFactorySegment[i7 + 2];
                this.m_trackFactoryPieceColors[i6 + 0] = this.m_trackFactorySegmentColors[i8 + 0];
                this.m_trackFactoryPieceColors[i6 + 1] = this.m_trackFactorySegmentColors[i8 + 1];
                this.m_trackFactoryPieceColors[i6 + 2] = this.m_trackFactorySegmentColors[i8 + 2];
            }
            updateVertexArray();
        }
        int i9 = 1 << this.m_curSegment;
        this.m_holeMask &= i9 ^ (-1);
        if (zIsHole) {
            this.m_holeMask |= i9;
        }
        for (int i10 = 0; i10 < VERTICES_PER_SEGMENT; i10++) {
            int i11 = (this.m_curSegment * VERTICES_PER_SEGMENT * 3) + (i10 * 3);
            int i12 = i10 * 4;
            int i13 = i10 * 3;
            this.m_trackFactoryPiece[i11 + 0] = this.m_trackFactorySegment[i12 + 0];
            this.m_trackFactoryPiece[i11 + 1] = this.m_trackFactorySegment[i12 + 1];
            this.m_trackFactoryPiece[i11 + 2] = this.m_trackFactorySegment[i12 + 2];
            this.m_trackFactoryPieceColors[i11 + 0] = this.m_trackFactorySegmentColors[i13 + 0];
            this.m_trackFactoryPieceColors[i11 + 1] = this.m_trackFactorySegmentColors[i13 + 1];
            this.m_trackFactoryPieceColors[i11 + 2] = this.m_trackFactorySegmentColors[i13 + 2];
        }
        this.m_curSegment++;
        if (this.m_curSegment == 6) {
            this.m_curMesh++;
            this.m_curSegment = 0;
        }
    }

    private int clampVisIdx(int i) {
        return Math.min(Math.max(i, 0), this.m_splineCnt - 6);
    }

    private void clearTrack() {
        this.m_curSegment = 0;
        this.m_curMesh = 0;
        this.m_trackPieceCnt = 0;
    }

    private TriangleStripArray createIndexBuffer(int i, int i2) {
        Vector vector = new Vector();
        Vector vector2 = new Vector();
        for (int i3 = i; i3 < i2; i3++) {
            int i4 = VERTICES_PER_SEGMENT * i3;
            int i5 = VERTICES_PER_SEGMENT;
            vector.addElement(new Integer(i4 + 1));
            vector.addElement(new Integer(i4 + 5));
            vector.addElement(new Integer(i4 + 6));
            vector.addElement(new Integer(i4 + 1));
            vector2.addElement(new Integer(4));
            vector.addElement(new Integer(i4 + 1));
            vector.addElement(new Integer(i4 + 1 + i5));
            vector.addElement(new Integer(i4 + 0));
            vector.addElement(new Integer(i4 + 0 + i5));
            vector.addElement(new Integer(i4 + 2));
            vector.addElement(new Integer(i4 + 2 + i5));
            vector.addElement(new Integer(i4 + 1));
            vector.addElement(new Integer(i4 + 1 + i5));
            vector2.addElement(new Integer(8));
            vector.addElement(new Integer(i4 + 4));
            vector.addElement(new Integer(i4 + 4 + i5));
            vector.addElement(new Integer(i4 + 3));
            vector.addElement(new Integer(i4 + 3 + i5));
            vector.addElement(new Integer(i4 + 5));
            vector.addElement(new Integer(i4 + 5 + i5));
            vector.addElement(new Integer(i4 + 4));
            vector.addElement(new Integer(i4 + 4 + i5));
            vector2.addElement(new Integer(8));
            vector.addElement(new Integer(i4 + 7));
            vector.addElement(new Integer(i4 + 7 + i5));
            vector.addElement(new Integer(i4 + 6));
            vector.addElement(new Integer(i4 + 6 + i5));
            vector.addElement(new Integer(i4 + 8));
            vector.addElement(new Integer(i4 + 8 + i5));
            vector.addElement(new Integer(i4 + 7));
            vector.addElement(new Integer(i4 + 7 + i5));
            vector2.addElement(new Integer(8));
        }
        int[] iArr = new int[vector.size()];
        int[] iArr2 = new int[vector2.size()];
        for (int i6 = 0; i6 < iArr.length; i6++) {
            iArr[i6] = ((Integer) vector.elementAt(i6)).intValue();
        }
        for (int i7 = 0; i7 < iArr2.length; i7++) {
            iArr2[i7] = ((Integer) vector2.elementAt(i7)).intValue();
        }
        return new TriangleStripArray(iArr, iArr2);
    }

    private void createM3GBuffers() {
        this.m_idxBufIdx = new byte[76];
        this.m_trackVtxBuf = new VertexBuffer[76];
        this.m_trackVtxLocArray = new VertexArray[76];
        this.m_trackVtxColArray = new VertexArray[76];
        this.m_trackApp = new Appearance();
        int i = VERTICES_PER_SEGMENT * 7;
        for (int i2 = 0; i2 < 76; i2++) {
            this.m_trackVtxBuf[i2] = new VertexBuffer();
            this.m_trackVtxLocArray[i2] = new VertexArray(i, 3, 2);
            this.m_trackVtxColArray[i2] = new VertexArray(i, 3, 1);
            this.m_trackVtxBuf[i2].setDefaultColor(16711680);
        }
        this.m_trackIdxBuf = new IndexBuffer[14];
        this.m_trackIdxBuf[0] = createIndexBuffer(0, 6);
        this.m_trackIdxBuf[1] = createIndexBuffer(1, 6);
        this.m_trackIdxBuf[2] = createIndexBuffer(2, 6);
        this.m_trackIdxBuf[3] = createIndexBuffer(3, 6);
        this.m_trackIdxBuf[4] = createIndexBuffer(4, 6);
        this.m_trackIdxBuf[5] = createIndexBuffer(5, 6);
        this.m_trackIdxBuf[6] = null;
        this.m_trackIdxBuf[7] = createIndexBuffer(0, 6);
        this.m_trackIdxBuf[8] = createIndexBuffer(0, 5);
        this.m_trackIdxBuf[9] = createIndexBuffer(0, 4);
        this.m_trackIdxBuf[10] = createIndexBuffer(0, 3);
        this.m_trackIdxBuf[11] = createIndexBuffer(0, 2);
        this.m_trackIdxBuf[12] = createIndexBuffer(0, 1);
        this.m_trackIdxBuf[13] = null;
    }

    private void createMascotBuffers() {
        this.m_mascotTrackVertices = new short[76][];
        this.m_mascotTrackVtxColors = new byte[76][];
        this.m_mascotHoleData = new byte[76];
        int i = VERTICES_PER_SEGMENT * 7;
        for (int i2 = 0; i2 < 76; i2++) {
            this.m_mascotTrackVertices[i2] = new short[i * 3];
            this.m_mascotTrackVtxColors[i2] = new byte[i];
        }
        this.m_mascotQuadVertices = new int[10][];
        this.m_mascotQuadColors = new int[10][];
        this.m_mascotTriVertices = new int[10][];
        this.m_mascotTriColors = new int[10][];
        this.m_mascotCurrentPieceIdx = new short[10];
        this.m_mascotPrimitiveCnt = new byte[10];
        resetMascotBufferCache();
        for (int i3 = 0; i3 < 10; i3++) {
            this.m_mascotQuadVertices[i3] = new int[432];
            this.m_mascotQuadColors[i3] = new int[36];
            this.m_mascotTriVertices[i3] = new int[54];
            this.m_mascotTriColors[i3] = new int[6];
        }
    }

    private void decoVecReady() {
        int size = this.m_decoVec.size();
        if (size > 0) {
            DajmMesh[] dajmMeshArr = new DajmMesh[size];
            for (int i = 0; i < size; i++) {
                dajmMeshArr[i] = (DajmMesh) this.m_decoVec.elementAt(i);
            }
            this.m_decos[this.m_prevPieceIdx] = dajmMeshArr;
        }
        this.m_decoVec.removeAllElements();
    }

    private void drawBG(Graphics graphics) {
        if (this.m_background == null) {
            return;
        }
        int screenHeight = Toolkit.getScreenHeight();
        int screenWidth = Toolkit.getScreenWidth();
        if (GameEngine.smGameEngineState == 2 && this.m_replayCameraState == 3) {
            graphics.setColor(BACKGROUND_TOP_COLORS[this.m_currentBackgroundIdx]);
            graphics.fillRect(0, 0, screenWidth, screenHeight);
            return;
        }
        int width = this.m_background.getWidth();
        int height = this.m_background.getHeight();
        int i = (int) (this.m_backgroundAngle * 2.0f);
        int i2 = (i % width) - width;
        int i3 = (HORIZON_OFFSET[this.m_currentBackgroundIdx] * screenHeight) >> 7;
        if (i3 - height > 0) {
            graphics.setColor(BACKGROUND_TOP_COLORS[this.m_currentBackgroundIdx]);
            graphics.fillRect(0, 0, screenWidth, i3 - height);
        }
        for (int i4 = i2; i4 < screenWidth + width; i4 += width) {
            graphics.drawImage(this.m_background, i4, i3, 36);
        }
        if (i3 < screenHeight) {
            graphics.setColor(BACKGROUND_BOTTOM_COLORS[this.m_currentBackgroundIdx]);
            graphics.fillRect(0, i3, screenWidth, screenHeight - i3);
        }
        for (int i5 = 0; i5 < 8; i5++) {
            int i6 = i - sm_propAngles[i5];
            if (i6 > (-sm_propImages[sm_propTypes[i5]].getWidth()) && i6 < screenWidth) {
                graphics.drawImage(sm_propImages[sm_propTypes[i5]], i6, sm_propY[i5] + i3, 20);
            }
        }
    }

    private void dumpBinary(int i) {
        int i2 = i;
        for (int i3 = 0; i3 < 6; i3++) {
            System.out.print(i2 & 1);
            i2 >>= 1;
        }
        System.out.println();
    }

    private void fixDepthWrite(DajmMesh dajmMesh) {
    }

    private int getIdxBufIdx(int i) {
        int i2 = 0;
        int i3 = i;
        for (int i4 = 0; i4 < 6; i4++) {
            i2 += i3 & 1;
            i3 >>= 1;
        }
        return (i & 1) == 1 ? i2 : i2 + 6 + 1;
    }

    private int getLastVisible(int i, int i2) {
        int i3 = i * 6;
        int i4 = i2 * 6;
        while (isInside(i3, i * 6) && i3 + i4 >= 0 && (i3 + i4) / 6 < this.m_trackPieceCnt) {
            i3 += i4;
        }
        return i3 / 6;
    }

    private float getLightScale(float f, float f2, float f3) {
        return (Math.min(0.99f, Math.max(LIGHT_Y, (((-f) * 0.707f) - (f2 * LIGHT_Y)) - (LIGHT_Z * f3))) * 0.7f) + 0.3f;
    }

    private int getMascotHoleData(int i) {
        int i2 = 0;
        int i3 = i;
        for (int i4 = 0; i4 < 6; i4++) {
            i2 += i3 & 1;
            i3 >>= 1;
        }
        return (i & 1) == 1 ? i2 : -i2;
    }

    private void initMascotLUT() {
        this.m_mascotTrackColorLUT = new int[128];
        for (int i = 0; i < 128; i++) {
            float f = (i + 80) / 208.0f;
            this.m_mascotTrackColorLUT[i] = ((int) (f * this.m_trackColorB)) | (((int) (this.m_trackColorR * f)) << 16) | (((int) (this.m_trackColorG * f)) << 8);
        }
    }

    private void initTrainMeshes() {
        DajmGraphics.clearGroup();
        int i = 0;
        while (i < this.m_trainMeshes.length) {
            this.m_trainMeshes[i] = (i == 0 ? this.m_carFront[this.m_currentBackgroundIdx] : this.m_car[this.m_currentBackgroundIdx]).getClone();
            DajmGraphics.addToGroup(this.m_trainMeshes[i]);
            this.m_peopleMeshes[i] = this.m_dudes[i & 1].getClone();
            DajmGraphics.addToGroup(this.m_peopleMeshes[i]);
            this.m_handMeshes[i] = this.m_hands[i & 1].getClone();
            DajmGraphics.addToGroup(this.m_handMeshes[i]);
            i++;
        }
    }

    private void initVisibility() {
        for (int i = 0; i < this.m_trackPieceCnt; i++) {
            int lastVisible = getLastVisible(i, -1);
            int lastVisible2 = getLastVisible(i, 1);
            this.m_visibiltyBegin[i] = (short) lastVisible;
            this.m_visibiltyEnd[i] = (short) (lastVisible2 + 1);
        }
    }

    private static final float interpolate(float f, float f2, float f3) {
        return ((f2 - f) * f3) + f;
    }

    private boolean isInside(int i, int i2) {
        return Math.abs((((float) (TrackModel.smTrackPointX[i] - TrackModel.smTrackPointX[i2])) * 0.001953125f) - 1100.0f) < 2250.0f && Math.abs(((float) (TrackModel.smTrackPointY[i] - TrackModel.smTrackPointY[i2])) * 0.001953125f) < 3093.0f;
    }

    private void preCacheModels() {
        for (int i = HttpConnection.HTTP_NOT_IMPLEMENTED; i <= 528; i++) {
            DajmMesh dajmMeshFindMesh = DajmGraphics.findMesh(i, -1, false);
            if (dajmMeshFindMesh != null) {
                dajmMeshFindMesh.setPerspectiveEnabled(false);
                dajmMeshFindMesh.fixAppearanceUnlit();
            }
        }
        DajmGraphics.fillCache(new int[]{100, TextIDs.TID_HELP_MODES_CONTENT_GHOST, TextIDs.TID_HELP_ACHIEVEMENTS, TextIDs.TID_HELP_ACHIEVEMENTS_CONTENT, TextIDs.TID_HELP_ELEMENTS_FALLING, TextIDs.TID_HOTSEAT_NUMBER, 200, 210, 201, TextIDs.TID_CHEATS_MAX_SPEED, TextIDs.TID_ACHIEVEMENTS_TITLE_20, TextIDs.TID_ACHIEVEMENTS_TITLE_21, TextIDs.TID_ACHIEVEMENTS_LOCKED_1, TextIDs.TID_ACHIEVEMENTS_LOCKED_2}, -1);
    }

    public static void releaseEngine3D() {
        sm_propAngles = null;
        sm_propTypes = null;
        sm_propY = null;
        sm_propImages = null;
    }

    private void renderCars() {
        if (DEBUG_SHOW_REF_PATH) {
            for (int i = 0; i < this.m_train.mRefPathData.length; i++) {
                int i2 = this.m_train.mRefPathData[i][2];
                short s = this.m_splineToCurveAngle[i2];
                float fSin = Util.sin(s, 1024) / 1024.0f;
                float f = (this.m_train.mRefPathData[i][0] * 0.001953125f) - (TrackModel.smTrackPointX[i2] * 0.001953125f);
                this.m_car[0].setIdentity();
                this.m_car[0].postTranslate((fSin * f) + this.m_splineToCurveX[i2], this.m_splineToCurveY[i2] + ((Util.sin(s + 90, 1024) / 1024.0f) * f), (-this.m_train.mRefPathData[i][1]) * 0.001953125f);
                this.m_car[0].render();
            }
        }
        int i3 = DEBUG_SHOW_REF_PATH ? 1 : this.m_train.mCarCnt;
        for (int i4 = 0; i4 < this.m_trainMeshes.length; i4++) {
            this.m_trainMeshes[i4].hideGrouped();
            this.m_peopleMeshes[i4].hideGrouped();
            this.m_handMeshes[i4].hideGrouped();
        }
        float f2 = (35.0f * this.m_train.mUplift) / 20.0f;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = this.m_train.mSegmentIdx[i5];
            short s2 = this.m_splineToCurveAngle[i6];
            float f3 = (this.m_train.mCarX[i5] * 0.001953125f) - (TrackModel.smTrackPointX[i6] * 0.001953125f);
            DajmMesh dajmMesh = this.m_trainMeshes[i5];
            dajmMesh.setIdentity();
            dajmMesh.setIdentity();
            dajmMesh.postTranslate(((Util.sin(s2, 1024) / 1024.0f) * f3) + this.m_splineToCurveX[i6], ((Util.sin(s2 + 90, 1024) / 1024.0f) * f3) + this.m_splineToCurveY[i6], (-this.m_train.mCarY[i5]) * 0.001953125f);
            dajmMesh.postRotate(s2 * 0.0027777778f, LIGHT_Y, LIGHT_Y, -1.0f);
            dajmMesh.postRotate(this.m_train.mCarAngle[i5] * 0.0027777778f, 1.0f, LIGHT_Y, LIGHT_Y);
            dajmMesh.postRotate(this.m_splineToTiltAngle[i6] * 0.0027777778f, LIGHT_Y, 1.0f, LIGHT_Y);
            this.m_effects.wobbleBase(dajmMesh, LIGHT_Y, this.m_wobbleDelta);
            dajmMesh.showGrouped();
            dajmMesh.renderGrouped();
            this.m_effects.renderSparks(dajmMesh);
            if (i5 == i3 - 1 && this.m_boostAlpha > 0.05f && !this.m_train.hasFailed()) {
                this.m_effects.updateBoost(this.m_boostAlpha);
                float[] fArr = {-75.0f, -95.0f, 13.0f, 1.0f, 75.0f, -95.0f, 13.0f, 1.0f, -75.0f, -95.0f, 115.0f, 1.0f, 75.0f, -95.0f, 115.0f, 1.0f};
                dajmMesh.transformPoints(fArr);
                int i7 = 0;
                while (true) {
                    int i8 = i7;
                    if (i8 >= fArr.length) {
                        break;
                    }
                    this.m_effects.renderBoost(fArr[i8 + 0], fArr[i8 + 1], fArr[i8 + 2], s2, this.m_train.mCarAngle[i5]);
                    i7 = i8 + 4;
                }
            }
            if (this.m_train.mPassengers[i5] > 0) {
                DajmMesh dajmMesh2 = this.m_peopleMeshes[i5];
                dajmMesh.copyTransformTo(dajmMesh2);
                dajmMesh2.postTranslate(LIGHT_Y, LIGHT_Y, PEOPLE_UP_DELTA + f2);
                dajmMesh2.showGrouped();
                dajmMesh2.renderGrouped();
                if (f2 > 5.0f) {
                    DajmMesh dajmMesh3 = this.m_handMeshes[i5];
                    dajmMesh.copyTransformTo(dajmMesh3);
                    dajmMesh3.postTranslate(LIGHT_Y, LIGHT_Y, PEOPLE_UP_DELTA + (1.5f * f2));
                    dajmMesh3.showGrouped();
                    dajmMesh3.renderGrouped();
                }
            }
        }
        DajmGraphics.renderGroup();
    }

    private void renderFlyingCars() {
        for (int i = 0; i < 12; i++) {
            if (this.m_flyTimer[i] > 0) {
                float f = this.m_flyCarX[i];
                float f2 = this.m_flyCarY[i];
                float f3 = this.m_flyCarZ[i];
                if (this.m_flyObjType[i] == 0) {
                    float f4 = (3.0f * this.m_flyTimer[i]) / CAMERA_DIST_REPLAY_BACK;
                    DajmMesh dajmMesh = this.m_car[this.m_currentBackgroundIdx];
                    dajmMesh.setIdentity();
                    dajmMesh.postTranslate(f, f2, f3 + 40.0f);
                    dajmMesh.postRotate(f4, 0.3f, (-1.4f) + (i * 0.4f), (i & 1) * 0.6f);
                    dajmMesh.postTranslate(LIGHT_Y, LIGHT_Y, -40.0f);
                    dajmMesh.render();
                } else {
                    DajmMesh dajmMesh2 = this.m_parachuteDudes[this.m_flyObjType[i] - 1];
                    float fSin = 0.15f * ((float) Math.sin((i * 2) + ((6.0f * this.m_flyTimer[i]) / CAMERA_DIST_REPLAY_BACK) + 1.0d));
                    dajmMesh2.setIdentity();
                    dajmMesh2.postTranslate(f, f2, f3);
                    dajmMesh2.postRotate(fSin, 1.0f, LIGHT_Y, LIGHT_Y);
                    dajmMesh2.postScale(2.0f, 2.0f, 2.0f);
                    dajmMesh2.render();
                }
            }
        }
    }

    private void renderMascotPiece(int i) {
        try {
            updateMascotBuffers(i);
            int i2 = i % 10;
            int[] iArr = this.m_mascotQuadVertices[i2];
            int[] iArr2 = this.m_mascotQuadColors[i2];
            int[] iArr3 = this.m_mascotTriVertices[i2];
            int[] iArr4 = this.m_mascotTriColors[i2];
            byte b = this.m_mascotPrimitiveCnt[i2];
            if (b == 0) {
                return;
            }
            this.m_effects.mMascotTrans1.setIdentity();
            this.m_effects.mMascotTrans1.m03 = (int) this.m_pieceLoc[(i * 3) + 0];
            this.m_effects.mMascotTrans1.m13 = (int) this.m_pieceLoc[(i * 3) + 1];
            this.m_effects.mMascotTrans1.m23 = (int) this.m_pieceLoc[(i * 3) + 2];
            this.m_effects.mMascotTrans2.mul(DajmGraphics.mascotGetCameraTrans(), this.m_effects.mMascotTrans1);
            DajmGraphics.mascotGetLayout().setAffineTrans(this.m_effects.mMascotTrans2);
            DajmGraphics.mascotGetGraphics3D().renderPrimitives(DajmGraphics.mascotGetTexture(0), 0, 0, DajmGraphics.mascotGetLayout(), this.m_effects.mMascotEffect3D, 67110912, b * 6, iArr, iArr, iArr, iArr2);
            DajmGraphics.mascotGetGraphics3D().renderPrimitives(DajmGraphics.mascotGetTexture(0), 0, 0, DajmGraphics.mascotGetLayout(), this.m_effects.mMascotEffect3D, 50333696, b * 1, iArr3, iArr3, iArr3, iArr4);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void renderSkyBox() {
        for (int length = this.m_skyBoxes[this.m_currentBackgroundIdx].length - 1; length >= 0; length--) {
            DajmMesh dajmMesh = this.m_skyBoxes[this.m_currentBackgroundIdx][length];
            if (dajmMesh != null) {
                dajmMesh.setIdentity();
                dajmMesh.postTranslate(this.m_camX, this.m_camY, this.m_camZ + SKY_BOX_Z_OFFSETS[this.m_currentBackgroundIdx]);
                dajmMesh.postRotate(0.25f, 1.0f, LIGHT_Y, LIGHT_Y);
                dajmMesh.render();
            }
        }
    }

    private void renderTrack(int i, boolean z) {
        int iClampVisIdx = clampVisIdx(i) / 6;
        short s = this.m_visibiltyBegin[iClampVisIdx];
        short s2 = this.m_visibiltyEnd[iClampVisIdx];
        for (int i2 = s; i2 < s2; i2++) {
            DajmMesh[] dajmMeshArr = this.m_decos[i2];
            if (z && dajmMeshArr != null) {
                for (DajmMesh dajmMesh : dajmMeshArr) {
                    dajmMesh.render();
                }
            }
        }
    }

    private void reset() {
        this.m_camAngle = TRKPROF_DX;
        this.m_trackAngle = LIGHT_Y;
        this.m_time = 0;
        setReplayCameraState(2);
        this.m_boostAlpha = LIGHT_Y;
        this.m_wobbleDelta = LIGHT_Y;
    }

    private void resetMascotBufferCache() {
        for (int i = 0; i < 10; i++) {
            this.m_mascotCurrentPieceIdx[i] = -1;
        }
    }

    private void setCamera() {
        this.m_useFixedSegment = false;
        switch (GameEngine.smGameEngineState) {
            case 0:
                float f = GameEngine.smGameStateTimer / 3000.0f;
                float f2 = COUNTDOWN_START_CAM_DIST[this.m_currentBackgroundIdx];
                float f3 = COUNTDOWN_START_CAM_ANGLE[this.m_currentBackgroundIdx];
                setCameraPlay(f3 + ((90.0f - f3) * f * f), f2 + ((2400.0f - f2) * f), COUNTDOWN_START_CAM_HEIGHT[this.m_currentBackgroundIdx] * (1.0f - f), (1.0f - f) * COUNTDOWN_START_CAM_LOOKAT_DIST[this.m_currentBackgroundIdx]);
                break;
            case 1:
                setCameraPlay(this.m_camAngle, 2400.0f, LIGHT_Y, LIGHT_Y);
                break;
            case 2:
                int i = this.m_train.mSegmentIdx[0];
                if (i >= TrackModel.smTrackPointCount) {
                    i = TrackModel.smTrackPointCount - 1;
                }
                byte b = TrackModel.replayCameraData[i];
                if (this.m_replayCameraStateTimer <= 1200 || b != 3) {
                    if (this.m_replayCameraStateTimer > 2400) {
                        setReplayCameraState(((Util.rand() & 511) >> 5) % 3);
                    }
                } else if (this.m_replayCameraState != 3) {
                    setReplayCameraState(3);
                }
                setCameraReplay();
                break;
            case 3:
                setCameraReplay();
                break;
            case 4:
                if (GameEngine.smGameStateTimer >= 6000) {
                    if (GameEngine.smGameStateTimer >= 8000) {
                        if (this.m_replayCameraState != 1) {
                            setReplayCameraState(1);
                        }
                        setCameraReplay();
                        break;
                    } else {
                        if (this.m_replayCameraState != 3) {
                            setReplayCameraState(3);
                        }
                        setCameraReplay();
                        break;
                    }
                } else {
                    setPointToCamera(38, GameEngine.smGameStateTimer);
                    break;
                }
        }
    }

    private void setCameraPlay(float f, float f2, float f3, float f4) {
        int i = this.m_train.mSegmentIdx[0];
        short s = this.m_splineToCurveAngle[i];
        float fSin = Util.sin(s, 1024) / 1024.0f;
        float f5 = (this.m_train.mCamPointX * 0.001953125f) - (TrackModel.smTrackPointX[i] * 0.001953125f);
        float f6 = this.m_splineToCurveX[i] + (fSin * f5);
        float fSin2 = this.m_splineToCurveY[i] + ((Util.sin(s + 90, 1024) / 1024.0f) * f5) + f4;
        float f7 = ((-this.m_train.mCamPointY) * 0.001953125f) + f3;
        double d = (3.141592653589793d * (this.m_trackAngle + f)) / 180.0d;
        float f8 = -((float) Math.sin(d));
        float fCos = (float) Math.cos(d);
        float f9 = f6 - (f2 * f8);
        float f10 = fSin2 - (f2 * fCos);
        DajmGraphics.setLookAt(f9, f10, f7, f8, fCos, 0.1f, LIGHT_Y, LIGHT_Y, 1.0f);
        this.m_camX = f9;
        this.m_camY = f10;
        this.m_camZ = f7;
        this.m_backgroundAngle = this.m_trackAngle + f;
    }

    private void setCameraReplay() {
        float f;
        float f2;
        switch (this.m_replayCameraState) {
            case 0:
                f = 0.0f;
                f2 = 1200.0f;
                break;
            case 1:
                setCameraPlay(CAMERA_ANGLE_REPLAY, CAMERA_DIST_REPLAY, 40.0f, LIGHT_Y);
                return;
            case 2:
                setCameraPlay(CAMERA_ANGLE_REPLAY_BACK, CAMERA_DIST_REPLAY_BACK, 40.0f, LIGHT_Y);
                return;
            case 3:
                f = 2000.0f;
                f2 = 300.0f;
                break;
            default:
                return;
        }
        short s = this.m_splineToCurveAngle[this.m_replayCameraSegment];
        float fSin = Util.sin(s, 1024) / 1024.0f;
        float fSin2 = ((Util.sin(s + 90, 1024) / 1024.0f) * f2) + this.m_splineToCurveX[this.m_replayCameraSegment];
        float f3 = this.m_splineToCurveY[this.m_replayCameraSegment] - (f2 * fSin);
        float f4 = ((-TrackModel.smTrackPointY[this.m_replayCameraSegment]) * 0.001953125f) - f;
        int i = this.m_train.mSegmentIdx[0];
        short s2 = this.m_splineToCurveAngle[i];
        float fSin3 = Util.sin(s2, 1024) / 1024.0f;
        float f5 = (this.m_train.mCamPointX * 0.001953125f) - (TrackModel.smTrackPointX[i] * 0.001953125f);
        float f6 = (fSin3 * f5) + this.m_splineToCurveX[i];
        float fSin4 = ((Util.sin(s2 + 90, 1024) / 1024.0f) * f5) + this.m_splineToCurveY[i];
        float f7 = (-this.m_train.mCamPointY) * 0.001953125f;
        if (this.m_replayCameraState == 0) {
            f4 = f7;
        }
        float f8 = f6 - fSin2;
        float f9 = fSin4 - f3;
        DajmGraphics.setLookAt(fSin2, f3, f4, f8, f9, f7 - f4, LIGHT_Y, LIGHT_Y, 1.0f);
        this.m_camX = fSin2;
        this.m_camY = f3;
        this.m_camZ = f4;
        float f10 = Math.abs(f8) < 0.05f ? 0.0f : f8;
        float f11 = Math.abs(f9) < 0.05f ? 0.0f : f9;
        float fSqrt = 1.0f / ((float) Math.sqrt((f10 * f10) + (f11 * f11)));
        this.m_backgroundAngle = Util.getAngle(f10 * fSqrt, f11 * fSqrt) * (-360.0f);
    }

    private void setPointToCamera(int i, int i2) {
        int iMax = Math.max(0, i2 - 2000);
        short s = this.m_splineToCurveAngle[i];
        float fSin = Util.sin(s, 1024) / 1024.0f;
        float fSin2 = Util.sin(s + 90, 1024) / 1024.0f;
        float fMax = Math.max(2000 - iMax, LIGHT_Y);
        float f = this.m_splineToCurveX[i] + (fSin * 1000.0f);
        float f2 = (1000.0f * fSin2) + this.m_splineToCurveY[i];
        float f3 = (fMax / 2.0f) + ((-TrackModel.smTrackPointY[i]) * 0.001953125f);
        DajmGraphics.setLookAt(f, f2, f3, -fSin, -fSin2, LIGHT_Y, LIGHT_Y, LIGHT_Y, 1.0f);
        this.m_camX = f;
        this.m_camY = f2;
        this.m_camZ = f3;
        this.m_useFixedSegment = true;
        this.m_fixedSegmentIdx = i;
    }

    private void setReplayCameraState(int i) {
        this.m_replayCameraSegment = (this.m_train != null ? this.m_train.mSegmentIdx[0] : 0) + 7;
        this.m_replayCameraSegment = Math.min(this.m_replayCameraSegment, this.m_splineCnt - 1);
        this.m_replayCameraState = i;
        this.m_replayCameraStateTimer = 0;
    }

    private void setTrnFromCurve(Transform transform, int i) {
        float f = TrackModel.smTrackSlope[i];
        transform.setIdentity();
        transform.postTranslate(this.m_splineToCurveX[i], this.m_splineToCurveY[i], -(TrackModel.smTrackPointY[i] * 0.001953125f));
        transform.postRotate(this.m_splineToCurveAngle[i], LIGHT_Y, LIGHT_Y, -1.0f);
        transform.postRotate(f, 1.0f, LIGHT_Y, LIGHT_Y);
        transform.postRotate(this.m_splineToTiltAngle[i], LIGHT_Y, 1.0f, LIGHT_Y);
    }

    private void trackReady() {
        initVisibility();
    }

    private void updateCamera() {
        this.m_camAngle = (Math.max(30.0f, 90.0f - ((9.0f * this.m_train.mSpeed) / 4096.0f)) * 0.05f) + (this.m_camAngle * 0.95f);
        this.m_trackAngle = (this.m_trackAngle * 0.9f) - (0.1f * this.m_splineToCurveAngle[this.m_train.mSegmentIdx[0] + 2]);
    }

    private void updateFlyingCars() {
        for (int i = 0; i < 12; i++) {
            if (this.m_flyTimer[i] > 0) {
                if (this.m_flyObjType[i] == 0) {
                    float[] fArr = this.m_flyVelZ;
                    fArr[i] = fArr[i] - 1.6f;
                }
                float[] fArr2 = this.m_flyCarX;
                fArr2[i] = fArr2[i] + this.m_flyVelX[i];
                float[] fArr3 = this.m_flyCarY;
                fArr3[i] = fArr3[i] + this.m_flyVelY[i];
                float[] fArr4 = this.m_flyCarZ;
                fArr4[i] = fArr4[i] + this.m_flyVelZ[i];
                int[] iArr = this.m_flyTimer;
                iArr[i] = iArr[i] - 20;
            }
        }
    }

    private void updateMascotBuffers(int i) {
        int i2 = i % 10;
        if (this.m_mascotCurrentPieceIdx[i2] == i) {
            return;
        }
        short[] sArr = this.m_mascotTrackVertices[i];
        byte[] bArr = this.m_mascotTrackVtxColors[i];
        int[] iArr = this.m_mascotQuadVertices[i2];
        int[] iArr2 = this.m_mascotQuadColors[i2];
        int[] iArr3 = this.m_mascotTriVertices[i2];
        int[] iArr4 = this.m_mascotTriColors[i2];
        this.m_mascotCurrentPieceIdx[i2] = (short) i;
        byte b = this.m_mascotHoleData[i];
        this.m_mascotPrimitiveCnt[i2] = (byte) (6 - Math.abs((int) b));
        int i3 = (b > 0 ? b : (byte) 0) + 0;
        int i4 = 6 - (b > 0 ? 0 : -b);
        int i5 = VERTICES_PER_SEGMENT;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i3 < i4) {
            int i10 = i3 * i5;
            int i11 = (i10 + 0) * 3;
            int i12 = i6 + 1;
            int i13 = i11 + 1;
            iArr[i6] = sArr[i11];
            int i14 = i12 + 1;
            int i15 = i13 + 1;
            iArr[i12] = sArr[i13];
            int i16 = i14 + 1;
            int i17 = i15 + 1;
            iArr[i14] = sArr[i15];
            int i18 = (i10 + 1) * 3;
            int i19 = i16 + 1;
            int i20 = i18 + 1;
            iArr[i16] = sArr[i18];
            int i21 = i19 + 1;
            int i22 = i20 + 1;
            iArr[i19] = sArr[i20];
            int i23 = i21 + 1;
            int i24 = i22 + 1;
            iArr[i21] = sArr[i22];
            int i25 = (i10 + 1 + i5) * 3;
            int i26 = i23 + 1;
            int i27 = i25 + 1;
            iArr[i23] = sArr[i25];
            int i28 = i26 + 1;
            int i29 = i27 + 1;
            iArr[i26] = sArr[i27];
            int i30 = i28 + 1;
            int i31 = i29 + 1;
            iArr[i28] = sArr[i29];
            int i32 = (i10 + 0 + i5) * 3;
            int i33 = i30 + 1;
            int i34 = i32 + 1;
            iArr[i30] = sArr[i32];
            int i35 = i33 + 1;
            int i36 = i34 + 1;
            iArr[i33] = sArr[i34];
            int i37 = i35 + 1;
            int i38 = i36 + 1;
            iArr[i35] = sArr[i36];
            int i39 = (i10 + 3) * 3;
            int i40 = i37 + 1;
            int i41 = i39 + 1;
            iArr[i37] = sArr[i39];
            int i42 = i40 + 1;
            int i43 = i41 + 1;
            iArr[i40] = sArr[i41];
            int i44 = i42 + 1;
            int i45 = i43 + 1;
            iArr[i42] = sArr[i43];
            int i46 = (i10 + 4) * 3;
            int i47 = i44 + 1;
            int i48 = i46 + 1;
            iArr[i44] = sArr[i46];
            int i49 = i47 + 1;
            int i50 = i48 + 1;
            iArr[i47] = sArr[i48];
            int i51 = i49 + 1;
            int i52 = i50 + 1;
            iArr[i49] = sArr[i50];
            int i53 = (i10 + 4 + i5) * 3;
            int i54 = i51 + 1;
            int i55 = i53 + 1;
            iArr[i51] = sArr[i53];
            int i56 = i54 + 1;
            int i57 = i55 + 1;
            iArr[i54] = sArr[i55];
            int i58 = i56 + 1;
            int i59 = i57 + 1;
            iArr[i56] = sArr[i57];
            int i60 = (i10 + 3 + i5) * 3;
            int i61 = i58 + 1;
            int i62 = i60 + 1;
            iArr[i58] = sArr[i60];
            int i63 = i61 + 1;
            int i64 = i62 + 1;
            iArr[i61] = sArr[i62];
            int i65 = i63 + 1;
            int i66 = i64 + 1;
            iArr[i63] = sArr[i64];
            int i67 = (i10 + 6) * 3;
            int i68 = i65 + 1;
            int i69 = i67 + 1;
            iArr[i65] = sArr[i67];
            int i70 = i68 + 1;
            int i71 = i69 + 1;
            iArr[i68] = sArr[i69];
            int i72 = i70 + 1;
            int i73 = i71 + 1;
            iArr[i70] = sArr[i71];
            int i74 = (i10 + 7) * 3;
            int i75 = i72 + 1;
            int i76 = i74 + 1;
            iArr[i72] = sArr[i74];
            int i77 = i75 + 1;
            int i78 = i76 + 1;
            iArr[i75] = sArr[i76];
            int i79 = i77 + 1;
            int i80 = i78 + 1;
            iArr[i77] = sArr[i78];
            int i81 = (i10 + 7 + i5) * 3;
            int i82 = i79 + 1;
            int i83 = i81 + 1;
            iArr[i79] = sArr[i81];
            int i84 = i82 + 1;
            int i85 = i83 + 1;
            iArr[i82] = sArr[i83];
            int i86 = i84 + 1;
            int i87 = i85 + 1;
            iArr[i84] = sArr[i85];
            int i88 = (i10 + 6 + i5) * 3;
            int i89 = i86 + 1;
            int i90 = i88 + 1;
            iArr[i86] = sArr[i88];
            int i91 = i89 + 1;
            int i92 = i90 + 1;
            iArr[i89] = sArr[i90];
            int i93 = i91 + 1;
            int i94 = i92 + 1;
            iArr[i91] = sArr[i92];
            int i95 = (i10 + 2) * 3;
            int i96 = i93 + 1;
            int i97 = i95 + 1;
            iArr[i93] = sArr[i95];
            int i98 = i96 + 1;
            int i99 = i97 + 1;
            iArr[i96] = sArr[i97];
            int i100 = i98 + 1;
            int i101 = i99 + 1;
            iArr[i98] = sArr[i99];
            int i102 = (i10 + 0) * 3;
            int i103 = i100 + 1;
            int i104 = i102 + 1;
            iArr[i100] = sArr[i102];
            int i105 = i103 + 1;
            int i106 = i104 + 1;
            iArr[i103] = sArr[i104];
            int i107 = i105 + 1;
            int i108 = i106 + 1;
            iArr[i105] = sArr[i106];
            int i109 = (i10 + 0 + i5) * 3;
            int i110 = i107 + 1;
            int i111 = i109 + 1;
            iArr[i107] = sArr[i109];
            int i112 = i110 + 1;
            int i113 = i111 + 1;
            iArr[i110] = sArr[i111];
            int i114 = i112 + 1;
            int i115 = i113 + 1;
            iArr[i112] = sArr[i113];
            int i116 = (i10 + 2 + i5) * 3;
            int i117 = i114 + 1;
            int i118 = i116 + 1;
            iArr[i114] = sArr[i116];
            int i119 = i117 + 1;
            int i120 = i118 + 1;
            iArr[i117] = sArr[i118];
            int i121 = i119 + 1;
            int i122 = i120 + 1;
            iArr[i119] = sArr[i120];
            int i123 = (i10 + 5) * 3;
            int i124 = i121 + 1;
            int i125 = i123 + 1;
            iArr[i121] = sArr[i123];
            int i126 = i124 + 1;
            int i127 = i125 + 1;
            iArr[i124] = sArr[i125];
            int i128 = i126 + 1;
            int i129 = i127 + 1;
            iArr[i126] = sArr[i127];
            int i130 = (i10 + 3) * 3;
            int i131 = i128 + 1;
            int i132 = i130 + 1;
            iArr[i128] = sArr[i130];
            int i133 = i131 + 1;
            int i134 = i132 + 1;
            iArr[i131] = sArr[i132];
            int i135 = i133 + 1;
            int i136 = i134 + 1;
            iArr[i133] = sArr[i134];
            int i137 = (i10 + 3 + i5) * 3;
            int i138 = i135 + 1;
            int i139 = i137 + 1;
            iArr[i135] = sArr[i137];
            int i140 = i138 + 1;
            int i141 = i139 + 1;
            iArr[i138] = sArr[i139];
            int i142 = i140 + 1;
            int i143 = i141 + 1;
            iArr[i140] = sArr[i141];
            int i144 = (i10 + 5 + i5) * 3;
            int i145 = i142 + 1;
            int i146 = i144 + 1;
            iArr[i142] = sArr[i144];
            int i147 = i145 + 1;
            int i148 = i146 + 1;
            iArr[i145] = sArr[i146];
            int i149 = i147 + 1;
            int i150 = i148 + 1;
            iArr[i147] = sArr[i148];
            int i151 = (i10 + 8) * 3;
            int i152 = i149 + 1;
            int i153 = i151 + 1;
            iArr[i149] = sArr[i151];
            int i154 = i152 + 1;
            int i155 = i153 + 1;
            iArr[i152] = sArr[i153];
            int i156 = i154 + 1;
            int i157 = i155 + 1;
            iArr[i154] = sArr[i155];
            int i158 = (i10 + 6) * 3;
            int i159 = i156 + 1;
            int i160 = i158 + 1;
            iArr[i156] = sArr[i158];
            int i161 = i159 + 1;
            int i162 = i160 + 1;
            iArr[i159] = sArr[i160];
            int i163 = i161 + 1;
            int i164 = i162 + 1;
            iArr[i161] = sArr[i162];
            int i165 = (i10 + 6 + i5) * 3;
            int i166 = i163 + 1;
            int i167 = i165 + 1;
            iArr[i163] = sArr[i165];
            int i168 = i166 + 1;
            int i169 = i167 + 1;
            iArr[i166] = sArr[i167];
            int i170 = i168 + 1;
            int i171 = i169 + 1;
            iArr[i168] = sArr[i169];
            int i172 = (i10 + 8 + i5) * 3;
            int i173 = i170 + 1;
            int i174 = i172 + 1;
            iArr[i170] = sArr[i172];
            int i175 = i173 + 1;
            int i176 = i174 + 1;
            iArr[i173] = sArr[i174];
            int i177 = i175 + 1;
            int i178 = i176 + 1;
            iArr[i175] = sArr[i176];
            int i179 = (i10 + 1) * 3;
            int i180 = i8 + 1;
            int i181 = i179 + 1;
            iArr3[i8] = sArr[i179];
            int i182 = i180 + 1;
            int i183 = i181 + 1;
            iArr3[i180] = sArr[i181];
            int i184 = i182 + 1;
            int i185 = i183 + 1;
            iArr3[i182] = sArr[i183];
            int i186 = (i10 + 5) * 3;
            int i187 = i184 + 1;
            int i188 = i186 + 1;
            iArr3[i184] = sArr[i186];
            int i189 = i187 + 1;
            int i190 = i188 + 1;
            iArr3[i187] = sArr[i188];
            int i191 = i189 + 1;
            int i192 = i190 + 1;
            iArr3[i189] = sArr[i190];
            int i193 = (i10 + 6) * 3;
            int i194 = i191 + 1;
            int i195 = i193 + 1;
            iArr3[i191] = sArr[i193];
            int i196 = i194 + 1;
            int i197 = i195 + 1;
            iArr3[i194] = sArr[i195];
            int i198 = i196 + 1;
            int i199 = i197 + 1;
            iArr3[i196] = sArr[i197];
            int i200 = i9 + 1;
            iArr2[i9] = this.m_mascotTrackColorLUT[bArr[i10 + 0]];
            int i201 = i200 + 1;
            iArr2[i200] = this.m_mascotTrackColorLUT[bArr[i10 + 3]];
            int i202 = i201 + 1;
            iArr2[i201] = this.m_mascotTrackColorLUT[bArr[i10 + 6]];
            int i203 = i202 + 1;
            iArr2[i202] = this.m_mascotTrackColorLUT[bArr[i10 + 1]];
            int i204 = i203 + 1;
            iArr2[i203] = this.m_mascotTrackColorLUT[bArr[i10 + 4]];
            i9 = i204 + 1;
            iArr2[i204] = this.m_mascotTrackColorLUT[bArr[i10 + 7]];
            iArr4[i7] = this.m_mascotTrackColorLUT[bArr[i10 + 8]];
            i3++;
            i7++;
            i6 = i177;
            i8 = i198;
        }
    }

    private void updateVertexArray() {
        float fMax = -1.0E9f;
        int i = this.m_curMesh - 1;
        if (i < 0) {
            return;
        }
        getIdxBufIdx(this.m_holeMask);
        float fMax2 = -1.0E9f;
        float fMin = 1.0E9f;
        float fMin2 = 1.0E9f;
        float fMin3 = 1.0E9f;
        float fMax3 = -1.0E9f;
        for (int i2 = 0; i2 < this.m_trackFactoryPiece.length; i2 += 3) {
            float f = this.m_trackFactoryPiece[i2 + 0];
            float f2 = this.m_trackFactoryPiece[i2 + 1];
            float f3 = this.m_trackFactoryPiece[i2 + 2];
            fMin3 = Math.min(fMin3, f);
            fMin2 = Math.min(fMin2, f2);
            fMin = Math.min(fMin, f3);
            fMax2 = Math.max(fMax2, f);
            fMax3 = Math.max(fMax3, f2);
            fMax = Math.max(fMax, f3);
        }
        float f4 = fMax2 - fMin3;
        float f5 = fMax3 - fMin2;
        float f6 = fMax - fMin;
        int i3 = i * 3;
        this.m_pieceLoc[i3 + 0] = (fMax2 + fMin3) * 0.5f;
        this.m_pieceLoc[i3 + 1] = (fMax3 + fMin2) * 0.5f;
        this.m_pieceLoc[i3 + 2] = (fMax + fMin) * 0.5f;
        this.m_pieceBoxMin[i3 + 0] = fMin3;
        this.m_pieceBoxMin[i3 + 1] = fMin2;
        this.m_pieceBoxMin[i3 + 2] = fMin;
        this.m_pieceBoxMax[i3 + 0] = fMax2;
        this.m_pieceBoxMax[i3 + 1] = fMax3;
        this.m_pieceBoxMax[i3 + 2] = fMax;
        this.m_trackPieceCnt = this.m_curMesh;
    }

    public void addDecoration(int i, int i2, short s, short s2, byte b) {
        int i3 = i2 / 6;
        DajmMesh dajmMeshFindMesh = DajmGraphics.findMesh(i + 500, -1, false);
        if (dajmMeshFindMesh != null) {
            DajmMesh clone = dajmMeshFindMesh.getClone();
            short s3 = (short) (this.m_splineToCurveAngle[i2] - 90);
            clone.setIdentity();
            clone.postTranslate(((Util.sin(s3, 1024) / 1024.0f) * s2) + this.m_splineToCurveX[i2], ((Util.sin(s3 + 90, 1024) / 1024.0f) * s2) + this.m_splineToCurveY[i2], ((-TrackModel.smTrackPointY[i2]) * 0.001953125f) + s);
            clone.postRotate((b / 256.0f) - (s3 / 360.0f), LIGHT_Y, LIGHT_Y, 1.0f);
            if (i3 != this.m_prevPieceIdx) {
                decoVecReady();
            }
            this.m_decoVec.addElement(clone);
            this.m_prevPieceIdx = i3;
        }
    }

    public void attachTrain(Train train) {
        this.m_train = train;
        this.m_train.mEngine3D = this;
        reset();
        initTrainMeshes();
        this.m_effects.resetSparks();
    }

    public void clearDecos() {
        this.m_prevPieceIdx = -1;
        this.m_decoVec.removeAllElements();
        this.m_decoVec.ensureCapacity(10);
        this.m_decos = new DajmMesh[76][];
    }

    public void decosReady() {
        decoVecReady();
    }

    public void draw3dBackground(Graphics graphics) {
    }

    public void freeHeap() {
        this.m_decos = (DajmMesh[][]) null;
        this.m_splineToCurveX = null;
        this.m_splineToCurveY = null;
        this.m_splineToCurveAngle = null;
        this.m_splineToTiltAngle = null;
        this.m_splineToTiltAngleFiltered = null;
    }

    public void init3DMapping() {
        if (this.m_splineToCurveX == null) {
            this.m_splineToCurveX = new float[TrackModel.MAX_SPLINE_LENGTH];
            this.m_splineToCurveY = new float[TrackModel.MAX_SPLINE_LENGTH];
            this.m_splineToCurveAngle = new short[TrackModel.MAX_SPLINE_LENGTH];
            this.m_splineToTiltAngle = new short[TrackModel.MAX_SPLINE_LENGTH];
            this.m_splineToTiltAngleFiltered = new short[TrackModel.MAX_SPLINE_LENGTH];
        }
        this.m_splineCnt = TrackModel.smTrackPointCount;
        this.m_splineToCurveAngle = TrackModel.smTrackCurve;
        float f = TrackModel.smTrackPointX[0] * 0.001953125f;
        float fSin = LIGHT_Y;
        this.m_splineToCurveX[0] = 0.0f;
        this.m_splineToCurveY[0] = f;
        this.m_splineToTiltAngle[0] = 0;
        float fSin2 = f;
        for (int i = 1; i < this.m_splineCnt; i++) {
            float f2 = (TrackModel.smTrackPointX[i] - TrackModel.smTrackPointX[i - 1]) * 0.001953125f;
            this.m_splineToTiltAngle[i] = (short) Math.max(Math.min(((this.m_splineToCurveAngle[i] - this.m_splineToCurveAngle[i - 1]) * 500) / f2, CAMERA_ANGLE_REPLAY_BACK), -45.0f);
            short s = this.m_splineToCurveAngle[i - 1];
            fSin += (Util.sin(s, 1024) / 1024.0f) * f2;
            fSin2 += f2 * (Util.sin(s + 90, 1024) / 1024.0f);
            this.m_splineToCurveX[i] = fSin;
            this.m_splineToCurveY[i] = fSin2;
        }
        for (int i2 = 0; i2 < this.m_splineCnt; i2++) {
            int i3 = 0;
            int i4 = 0;
            int i5 = i2 - 4;
            for (int i6 = 0; i6 < 9; i6++) {
                if (i5 >= 0 && i5 < this.m_splineCnt) {
                    i4 += this.m_splineToTiltAngle[i5];
                    i3++;
                }
                i5++;
            }
            this.m_splineToTiltAngleFiltered[i2] = (short) (i4 / i3);
        }
        System.arraycopy(this.m_splineToTiltAngleFiltered, 0, this.m_splineToTiltAngle, 0, this.m_splineCnt);
    }

    public void init3dBackround() {
    }

    public void initBG(int i) {
        int i2 = TRACK_COLORS[i];
        this.m_trackColorR = (i2 >> 16) & 255;
        this.m_trackColorG = (i2 >> 8) & 255;
        this.m_trackColorB = (i2 >> 0) & 255;
        if (i != this.m_currentBackgroundIdx) {
            this.m_currentBackgroundIdx = i;
            this.m_background = Toolkit.getImage(BACKGROUND_RIDS[this.m_currentBackgroundIdx]);
            sm_propAngles = new int[8];
            sm_propTypes = new int[8];
            sm_propY = new int[8];
            sm_propImages = new Image[PROP_RIDS[i].length];
            for (int i3 = 0; i3 < PROP_RIDS[i].length; i3++) {
                sm_propImages[i3] = Toolkit.getImage(PROP_RIDS[i][i3]);
            }
            for (int i4 = 0; i4 < 8; i4++) {
                if (i4 != 0 || PROP_RIDS[i].length <= 1 || sm_propImages[1] == null) {
                    sm_propAngles[i4] = Util.rand() % 500;
                    sm_propTypes[i4] = 0;
                    sm_propY[i4] = (-105) - Math.abs(Util.rand() % 20);
                } else {
                    sm_propAngles[i4] = 0;
                    sm_propTypes[i4] = 1;
                    sm_propY[i4] = -130;
                }
            }
        }
    }

    public void initFlyingCar(int i, int i2, int i3, int i4, boolean z, int i5) {
        short s = this.m_splineToCurveAngle[i3];
        float fSin = Util.sin(s, 1024) / 1024.0f;
        float fSin2 = Util.sin(s + 90, 1024) / 1024.0f;
        float f = (i * 0.001953125f) - (TrackModel.smTrackPointX[i3] * 0.001953125f);
        float f2 = this.m_splineToCurveX[i3] + (fSin * f);
        float f3 = (f * fSin2) + this.m_splineToCurveY[i3];
        float f4 = (-i2) * 0.001953125f;
        if (z) {
            int i6 = this.m_nextFreeFlyCarIdx;
            this.m_flyCarX[i6] = f2;
            this.m_flyCarY[i6] = f3;
            this.m_flyCarZ[i6] = f4;
            this.m_flyTimer[i6] = 2000;
            this.m_flyVelX[i6] = ((Util.rand() & 4) - 8) + (40.0f * fSin);
            this.m_flyVelY[i6] = ((Util.rand() & 4) - 8) + (40.0f * fSin2);
            this.m_flyVelZ[i6] = 40.0f;
            this.m_flyObjType[i6] = 0;
            this.m_nextFreeFlyCarIdx = (this.m_nextFreeFlyCarIdx + 1) % 12;
        }
        for (int i7 = 0; i7 < i4; i7++) {
            int i8 = this.m_nextFreeFlyCarIdx;
            this.m_flyCarX[i8] = f2 - ((50.0f * fSin2) * (i7 + 1));
            this.m_flyCarY[i8] = (50.0f * fSin * (i7 + 1)) + f3;
            this.m_flyCarZ[i8] = 125.0f + f4 + (i7 * 50);
            this.m_flyTimer[i8] = 2000;
            this.m_flyVelX[i8] = fSin;
            this.m_flyVelY[i8] = fSin2;
            this.m_flyVelZ[i8] = -8.0f;
            if ((i5 & 1) == 0) {
                this.m_flyObjType[i8] = (byte) (i7 + 1);
            } else {
                this.m_flyObjType[i8] = (byte) (i7 + 3);
            }
            this.m_nextFreeFlyCarIdx = (this.m_nextFreeFlyCarIdx + 1) % 12;
        }
    }

    public void initNewTrack() {
        this.m_use3dBG = false;
        clearTrack();
        Transform transform = new Transform();
        transform.setIdentity();
        for (int i = 0; i < TrackModel.smTrackPointCount; i++) {
            setTrnFromCurve(transform, i);
            addSegment(transform, i);
        }
        trackReady();
        reset();
    }

    public void logicUpdate(int i) {
        this.m_time += i;
        this.m_time = Math.min(this.m_time, 100);
        this.m_time = Math.max(this.m_time, 0);
        while (this.m_time > 20) {
            updateFlyingCars();
            updateCamera();
            this.m_replayCameraStateTimer += 20;
            this.m_time -= 20;
            this.m_boostAlpha += this.m_train.mSpeed > 24576 ? 0.02f : -0.02f;
            this.m_boostAlpha = Math.max(LIGHT_Y, this.m_boostAlpha);
            this.m_boostAlpha = Math.min(1.0f, this.m_boostAlpha);
            float f = this.m_train.mEmitSparks ? 0.28f : this.m_train.mIsAccelerating ? -0.28f : 0.0f;
            if (Math.abs(this.m_wobbleDelta) < 0.001f) {
                this.m_wobbleDelta = LIGHT_Y;
            }
            this.m_wobbleDelta = (f * 0.1f) + (0.9f * this.m_wobbleDelta);
            this.m_effects.updateSparks(this.m_train.mEmitSparks);
        }
    }

    public void nextCameraMode() {
        setReplayCameraState((this.m_replayCameraState + 1) % 3);
    }

    public void render(Graphics graphics) {
        setCamera();
        drawBG(graphics);
        int i = this.m_train.mSegmentIdx[0];
        if (this.m_useFixedSegment) {
            i = this.m_fixedSegmentIdx;
        }
        try {
            DajmGraphics.bind(graphics);
            renderTrack(i, true);
            renderCars();
            int i2 = 0 + 1 + 1 + 1;
            renderFlyingCars();
            int i3 = i2 + 1;
        } catch (Exception e) {
        } finally {
            DajmGraphics.release(graphics);
        }
    }

    public void renderTrackinator(Object obj, float f, float f2, float f3, boolean z) {
        if (this.m_splineCnt <= 5) {
            return;
        }
        float f4 = z ? 2400.0f : f3;
        try {
            DajmGraphics.bind(obj);
            int iMin = Math.min(Math.max(0, (int) (this.m_splineCnt * f)), this.m_splineCnt - 1);
            float f5 = this.m_splineToCurveAngle[iMin] + f2;
            float fSin = Util.sin((int) f5, 1024) / 1024.0f;
            float fSin2 = Util.sin(((int) f5) + 90, 1024) / 1024.0f;
            DajmGraphics.setLookAt(this.m_splineToCurveX[iMin] - (f4 * fSin), this.m_splineToCurveY[iMin] - (f4 * fSin2), (-TrackModel.smTrackPointY[iMin]) * 0.001953125f, fSin, fSin2, z ? 0.1f : 0.0f, LIGHT_Y, LIGHT_Y, 1.0f);
            renderTrack(iMin, true);
            DajmGraphics.release(obj);
        } catch (Exception e) {
            System.out.println("renderTrackinator: " + e);
            e.printStackTrace();
        }
    }

    public void setEndCamera() {
        setReplayCameraState(1);
    }
}
