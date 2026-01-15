package com.digitalchocolate.androidrollergapp;

import com.mascotcapsule.micro3d.v3.AffineTrans;
import com.mascotcapsule.micro3d.v3.FigureLayout;
import com.mascotcapsule.micro3d.v3.Texture;
import com.mascotcapsule.micro3d.v3.Vector3D;
import java.io.IOException;
import java.util.Hashtable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.m3g.Appearance;
import javax.microedition.m3g.Background;
import javax.microedition.m3g.Camera;
import javax.microedition.m3g.Graphics3D;
import javax.microedition.m3g.Group;
import javax.microedition.m3g.Image2D;
import javax.microedition.m3g.Loader;
import javax.microedition.m3g.Mesh;
import javax.microedition.m3g.Object3D;
import javax.microedition.m3g.Texture2D;
import javax.microedition.m3g.Transform;
import javax.microedition.m3g.World;

/* loaded from: classes.dex */
public class DajmGraphics {
    public static final boolean DEBUG_VERBOSE = false;
    private static final float DEFAULT_FOV = 45.0f;
    public static final boolean EDITOR_HACK = false;
    private static final boolean FIX_CORRUPT_M3G_DATA_BUG = false;
    public static final boolean FIX_SPRITE3D = false;
    public static final float M3G_FAR = 5500.0f;
    public static final float M3G_NEAR = 40.0f;
    public static final int MASCOT_ANIM_SCALE = 2000;
    private static final int MASCOT_FAR = 4000;
    public static final int MASCOT_FAR_SORT = 4;
    private static final int MASCOT_NEAR = 64;
    public static final int MASCOT_NEAR_SORT = 8;
    public static final int MASCOT_ONE = 4096;
    public static final int MASCOT_SHIFT = 12;
    private static final int MAX_MASCOT_TEXTURES = 4;
    public static final boolean USE_AFFINE_TEX = false;
    public static final boolean USE_GROUP = false;
    public static final boolean USE_LINEAR_FILTERING = false;
    public static final boolean USE_M3G = false;
    public static final boolean USE_MASCOT = false;
    private static final boolean USE_MEM_FREE = true;
    private static float m_fov;
    private static Group sm_group;
    private static Background sm_m3gBackground;
    public static Camera sm_m3gCamera;
    private static Graphics3D sm_m3gG3D;
    private static Transform sm_m3gTransform;
    private static Transform sm_m3gTransformTmp;
    private static AffineTrans sm_mascotAffineTrans;
    private static com.mascotcapsule.micro3d.v3.Graphics3D sm_mascotG3D;
    private static FigureLayout sm_mascotLayout;
    private static Texture[] sm_mascotTextures;
    private static Vector3D sm_mascotTmpVectorA;
    private static Vector3D sm_mascotTmpVectorB;
    private static Vector3D sm_mascotTmpVectorC;
    private static Hashtable sm_meshCache;
    private static float[] sm_mtx;
    private static int sm_screenAreaHeight;
    private static int sm_screenClippingBottom;
    private static int sm_screenClippingTop;
    private static float sm_screenCorrection;
    private static int sm_screenHeight;
    private static float sm_screenRatio;
    private static int sm_screenWidth;
    private static Hashtable sm_spriteCache;
    private static Hashtable sm_worldCache;

    private DajmGraphics() {
    }

    public static final void addToGroup(DajmMesh dajmMesh) {
    }

    public static final void applyClip(Object obj) {
    }

    public static final void bind(Object obj) {
        applyClip(obj);
    }

    public static final void clearCache() {
        sm_meshCache.clear();
        System.gc();
    }

    public static final void clearCacheObject(int i) {
        sm_meshCache.remove(new Integer(i));
        System.gc();
    }

    public static final void clearGroup() {
    }

    public static final void fillCache(int[] iArr, int i) {
    }

    public static final DajmMesh findMesh(int i, int i2, boolean z) {
        DajmMesh dajmMesh = (DajmMesh) sm_meshCache.get(new Integer(i));
        if (dajmMesh != null || !z) {
        }
        return dajmMesh;
    }

    public static final DajmSprite findSprite(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        DajmSprite dajmSprite = (DajmSprite) sm_spriteCache.get(new Integer(i));
        if (dajmSprite == null) {
            sm_spriteCache.put(new Integer(i), dajmSprite);
        }
        return dajmSprite;
    }

    public static final float getFOV() {
        return m_fov;
    }

    public static final Image2D getImageFromMesh(int i, World world) {
        Image2D image = getTextureFromMesh(i, world).getImage();
        if (image == null) {
            return null;
        }
        return image;
    }

    public static final Texture2D getTextureFromMesh(int i, World world) {
        Appearance appearance;
        Mesh mesh = (Mesh) world.find(i);
        if (mesh != null && (appearance = mesh.getAppearance(0)) != null) {
            return appearance.getTexture(0);
        }
        return null;
    }

    private static World getWorld(int i) {
        try {
            Object3D[] object3DArrLoad = Loader.load("/" + i);
            for (int i2 = 0; i2 < object3DArrLoad.length; i2++) {
                if (object3DArrLoad[i2] instanceof World) {
                    return (World) object3DArrLoad[i2];
                }
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public static final void init(int i, int i2, int i3, int i4) {
        sm_meshCache = new Hashtable();
        sm_spriteCache = new Hashtable();
        sm_group = new Group();
        initCamera(i, i2, i3, i4);
    }

    private static final void initCamera(int i, int i2, int i3, int i4) {
        sm_screenClippingTop = i;
        sm_screenClippingBottom = i2;
        sm_screenWidth = i3;
        sm_screenHeight = i4;
        sm_screenAreaHeight = (i4 - i) - i2;
    }

    public static final void initEditorBG(int i) {
    }

    public static final void loadMascotTexture(int i, int i2) {
    }

    public static final Transform m3gGetCameraTrans() {
        return sm_m3gTransform;
    }

    public static final float m3gGetDepthOffset() {
        return 0.0f;
    }

    public static final Graphics3D m3gGetGraphics3D() {
        return sm_m3gG3D;
    }

    public static final void mascotFlush() {
    }

    public static final AffineTrans mascotGetCameraTrans() {
        return sm_mascotAffineTrans;
    }

    public static final com.mascotcapsule.micro3d.v3.Graphics3D mascotGetGraphics3D() {
        return sm_mascotG3D;
    }

    public static final FigureLayout mascotGetLayout() {
        return sm_mascotLayout;
    }

    public static final Texture mascotGetTexture(int i) {
        return sm_mascotTextures[i];
    }

    public static final void projectPoints2D(float[] fArr) {
    }

    public static final void release(Object obj) {
        ((Graphics) obj).setClip(0, 0, sm_screenWidth, sm_screenHeight);
    }

    public static void releaseDajmGraphics() {
        sm_m3gG3D = null;
        sm_m3gBackground = null;
        sm_m3gCamera = null;
        sm_m3gTransform = null;
        sm_m3gTransformTmp = null;
        sm_mascotG3D = null;
        sm_mascotTextures = null;
        sm_mascotLayout = null;
        sm_mascotAffineTrans = null;
        sm_mascotTmpVectorA = null;
        sm_mascotTmpVectorB = null;
        sm_mascotTmpVectorC = null;
        sm_meshCache = null;
        sm_spriteCache = null;
        sm_worldCache = null;
        sm_group = null;
        sm_mtx = null;
    }

    public static final void renderGroup() {
    }

    public static final void resetViewport(int i, int i2, int i3, int i4) {
        sm_screenClippingTop = i;
        sm_screenClippingBottom = i2;
        sm_screenWidth = i3;
        sm_screenHeight = i4;
        sm_screenAreaHeight = (i4 - i) - i2;
    }

    public static final void setFOV(float f) {
        m_fov = f;
    }

    public static final void setLookAt(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
    }
}
