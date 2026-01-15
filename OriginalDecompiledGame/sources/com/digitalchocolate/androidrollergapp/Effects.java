package com.digitalchocolate.androidrollergapp;

import com.mascotcapsule.micro3d.v3.AffineTrans;
import com.mascotcapsule.micro3d.v3.Effect3D;
import javax.microedition.m3g.Appearance;
import javax.microedition.m3g.IndexBuffer;
import javax.microedition.m3g.Transform;
import javax.microedition.m3g.VertexArray;
import javax.microedition.m3g.VertexBuffer;

/* loaded from: classes.dex */
public class Effects {
    private static final int BOOST_H = 15;
    private static final short BOOST_MESH_LEN = 290;
    private static final short BOOST_MESH_WIDTH = 16;
    private static final int BOOST_U = 128;
    private static final int BOOST_V = 160;
    private static final int BOOST_VCNT = 4;
    private static final int BOOST_W = 15;
    private static final byte CF = -1;
    private static final int SPARKS_CNT = 6;
    private static final int SPARKS_CYCLES = 18;
    private static final short SPARKS_GRAVITY = -23;
    private static final short SPARKS_LENGTH_SCALE = 3;
    private static final float SPARKS_VBUF_SCALE = 0.0625f;
    private static final int SPARKS_VCNT = 18;
    private static final int SPARK_COLOR = -65536;
    public static final float UV_SCALE = 128.0f;
    public static final int UV_SCALE_INT = 128;
    private Appearance mBoostApp;
    private IndexBuffer mBoostIBuf;
    private VertexArray mBoostUVArr;
    private VertexBuffer mBoostVBuf;
    private VertexArray mBoostVLocArr;
    private boolean mDrawSparks;
    private int mEmitTimer;
    public Effect3D mMascotEffect3D;
    public AffineTrans mMascotTrans1;
    public AffineTrans mMascotTrans2;
    private Appearance mSparksApp;
    private boolean mSparksBuffersDirty;
    private IndexBuffer mSparksIBuf;
    private VertexBuffer mSparksVBuf;
    private VertexArray mSparksVColArr;
    private VertexArray mSparksVLocArr;
    private static final short[] BOOST_VLOC = {0, 0, -8, 0, 0, 8, 0, -290, -5, 0, -290, 5};
    private static final int[] BOOST_INDICES = {0, 1, 2, 3};
    private static final int[] BOOST_INDICES_LEN = {4};
    private static final int[] SPARKS_INDICES = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
    private static final int[] SPARKS_INDICES_LEN = {3, 3, 3, 3, 3, 3};
    private static final byte CH = -128;
    private static final byte[] SPARKS_VTX_COLORS = {-1, 0, 0, 0, 0, 0, -1, 0, 0, -1, -1, 0, 0, 0, 0, -1, -1, 0, -1, 0, -1, 0, 0, 0, -1, 0, -1, 0, CH, -1, 0, 0, 0, 0, CH, -1, -1, -1, -1, 0, 0, 0, -1, -1, -1, -1, CH, 0, 0, 0, 0, -1, CH, 0};
    private static float[] sm_wobbleBase = {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private Transform mEffectTrn = new Transform();
    private short[] mBoostUVs = {0, 127, 127, 127, 0, 0, 127, 0};
    private final int[] mMascotVertices = {0, 0, -8, 0, 0, 8, 0, -290, 8, 0, -290, -8};
    private final int[] mMascotUVs = {128, TextIDs.TID_TUTORIAL_NITRO, TextIDs.TID_RANKING_6, TextIDs.TID_TUTORIAL_NITRO, TextIDs.TID_RANKING_6, 160, 128, 160};
    private short[] mSparksVLoc = new short[54];
    private short[] mSparksVel = new short[18];
    private short[] mSparksLoc = new short[18];
    private short[] mSparkTime = new short[6];
    private int[] mSparksVBufMascot = new int[54];
    private int[] mSparksColorsMascot = {8454143, 8454143, 8454143, 8454143, 8454143, 8454143};

    private final void updateSparksVBuf() {
        if (this.mSparksBuffersDirty) {
            this.mSparksBuffersDirty = false;
        }
    }

    public final void initBoostMesh() {
    }

    public final void initSparksMesh() {
    }

    public final void renderBoost(float f, float f2, float f3, float f4, float f5) {
    }

    public final void renderSparks(DajmMesh dajmMesh) {
        if (this.mDrawSparks) {
            updateSparksVBuf();
        }
    }

    public final void resetSparks() {
        this.mEmitTimer = -1;
        for (int i = 0; i < 6; i++) {
            this.mSparkTime[i] = 18;
        }
        this.mSparksBuffersDirty = true;
        this.mDrawSparks = false;
    }

    public final void updateBoost(float f) {
    }

    public final void updateSparks(boolean z) {
        if (z) {
            this.mEmitTimer++;
            if (this.mEmitTimer > 3) {
                this.mEmitTimer = 0;
            }
        } else {
            this.mEmitTimer = -1;
        }
        boolean z2 = this.mEmitTimer == 0;
        this.mDrawSparks = false;
        this.mSparksBuffersDirty = true;
        boolean z3 = z2;
        for (int i = 0; i < 6; i++) {
            if (this.mSparkTime[i] < 18) {
                this.mDrawSparks = true;
                short s = this.mSparksVel[(i * 3) + 0];
                short s2 = this.mSparksVel[(i * 3) + 1];
                short s3 = this.mSparksVel[(i * 3) + 2];
                short[] sArr = this.mSparksLoc;
                int i2 = (i * 3) + 0;
                sArr[i2] = (short) (s + sArr[i2]);
                short[] sArr2 = this.mSparksLoc;
                int i3 = (i * 3) + 1;
                sArr2[i3] = (short) (s2 + sArr2[i3]);
                short[] sArr3 = this.mSparksLoc;
                int i4 = (i * 3) + 2;
                sArr3[i4] = (short) (s3 + sArr3[i4]);
                short[] sArr4 = this.mSparksVel;
                int i5 = (i * 3) + 2;
                sArr4[i5] = (short) (sArr4[i5] + SPARKS_GRAVITY);
                short[] sArr5 = this.mSparkTime;
                sArr5[i] = (short) (sArr5[i] + 1);
            } else if (z3) {
                this.mDrawSparks = true;
                this.mSparksLoc[(i * 3) + 0] = 80;
                this.mSparksLoc[(i * 3) + 1] = (short) (16.0f * (120.0f - ((240.0f * (Util.rand() & 255)) / 255.0f)));
                this.mSparksLoc[(i * 3) + 2] = 0;
                this.mSparksVel[(i * 3) + 0] = (short) (((((Util.rand() & 255) / 255.0f) * 10.0f) + 15.0f) * 10.0f);
                this.mSparksVel[(i * 3) + 1] = (short) ((-10.0f) * (6.0f + (8.0f * ((Util.rand() & 255) / 255.0f))));
                this.mSparksVel[(i * 3) + 2] = (short) (((((Util.rand() & 255) / 255.0f) * 15.0f) + 15.0f) * 10.0f);
                this.mSparkTime[i] = 0;
                z3 = false;
            }
        }
    }

    public void wobbleBase(DajmMesh dajmMesh, float f, float f2) {
    }
}
