package com.digitalchocolate.androidrollergapp;

import com.mascotcapsule.micro3d.v3.ActionTable;
import com.mascotcapsule.micro3d.v3.AffineTrans;
import com.mascotcapsule.micro3d.v3.Effect3D;
import com.mascotcapsule.micro3d.v3.Figure;
import com.mascotcapsule.micro3d.v3.Vector3D;
import javax.microedition.m3g.Mesh;
import javax.microedition.m3g.Transform;

/* loaded from: classes.dex */
public class DajmMesh {
    private Mesh m_m3gMesh;
    private Transform m_m3gTransform;
    private ActionTable m_mascotAction;
    public AffineTrans m_mascotAffineTrans;
    private Effect3D m_mascotEffect;
    private Figure m_mascotFigure;
    private AffineTrans m_mascotFixTrans;
    private AffineTrans m_mascotTmpTrans;
    private Vector3D m_mascotTmpVector;

    public DajmMesh(Object obj) {
    }

    public final void animate(int i) {
    }

    public final void copyTransformTo(DajmMesh dajmMesh) {
    }

    public final void fixAppearanceUnlit() {
    }

    public final DajmMesh getClone() {
        return null;
    }

    public final void getMatrix(float[] fArr) {
    }

    public final void hideGrouped() {
    }

    public final void m3gGenNormals() {
    }

    public final Mesh m3gGetMesh() {
        return this.m_m3gMesh;
    }

    public final Transform m3gGetTransform() {
        return this.m_m3gTransform;
    }

    public final Effect3D mascotGetEffect() {
        return this.m_mascotEffect;
    }

    public final Figure mascotGetFigure() {
        return this.m_mascotFigure;
    }

    public final void mascotSetAction(ActionTable actionTable) {
        this.m_mascotAction = actionTable;
    }

    public final void postRotate(float f, float f2, float f3, float f4) {
    }

    public final void postRotateQuat(float f, float f2, float f3, float f4) {
    }

    public final void postScale(float f, float f2, float f3) {
    }

    public final void postTranslate(float f, float f2, float f3) {
    }

    public final void render() {
    }

    public final void renderGrouped() {
    }

    public final void setAlphaEnabled(boolean z) {
    }

    public final void setIdentity() {
    }

    public final void setM3gDepthOffset(int i) {
    }

    public final void setMatrix(float[] fArr) {
    }

    public final void setPerspectiveEnabled(boolean z) {
    }

    public final void showGrouped() {
    }

    public final void transformPoints(float[] fArr) {
    }
}
