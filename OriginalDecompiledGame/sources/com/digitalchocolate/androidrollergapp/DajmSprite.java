package com.digitalchocolate.androidrollergapp;

import com.mascotcapsule.micro3d.v3.AffineTrans;
import com.mascotcapsule.micro3d.v3.Effect3D;
import com.mascotcapsule.micro3d.v3.Texture;
import com.mascotcapsule.micro3d.v3.Vector3D;
import javax.microedition.m3g.Image2D;
import javax.microedition.m3g.Mesh;
import javax.microedition.m3g.Sprite3D;
import javax.microedition.m3g.Transform;

/* loaded from: classes.dex */
public class DajmSprite {
    private static final boolean FIX_GX40_ALPHA_BUG = false;
    private static final int MASCOT_SPRITE_COMMAND = 83898392;
    private Sprite3D m_m3gSprite;
    private Mesh m_m3gSpriteMesh;
    private Transform m_m3gTmpPosition;
    private Transform m_m3gTmpTrans;
    private Transform m_m3gTransform;
    private AffineTrans m_mascotAffineTrans;
    private Effect3D m_mascotEffect;
    private AffineTrans m_mascotFixTrans;
    private int[] m_mascotSpriteData;
    private int[] m_mascotSpritePosition;
    private Texture m_mascotTexture;
    private AffineTrans m_mascotTmpTrans;
    private Vector3D m_mascotTmpVector;
    private float[] m_mtxPosition;
    private float[] m_mtxRotation;
    private int m_scale;

    public DajmSprite(Image2D image2D, int i, int i2, int i3, int i4, int i5) {
        this.m_scale = i;
    }

    public final void postRotate(float f, float f2, float f3, float f4) {
    }

    public final void postRotateQuat(float f, float f2, float f3, float f4) {
    }

    public final void postTranslate(float f, float f2, float f3) {
    }

    public final void render() {
    }

    public final void setIdentity() {
    }

    public final void setScale(int i) {
        this.m_scale = i;
    }
}
