package com.mascotcapsule.micro3d.v3;

/* loaded from: classes.dex */
public class AffineTrans {
    public int m00;
    public int m01;
    public int m02;
    public int m03;
    public int m10;
    public int m11;
    public int m12;
    public int m13;
    public int m20;
    public int m21;
    public int m22;
    public int m23;

    public AffineTrans() {
    }

    public AffineTrans(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
    }

    public AffineTrans(AffineTrans affineTrans) {
    }

    public AffineTrans(int[] iArr) {
    }

    public AffineTrans(int[] iArr, int i) {
    }

    public AffineTrans(int[][] iArr) {
    }

    public final void get(int[] iArr) {
    }

    public final void get(int[] iArr, int i) {
    }

    public final void lookAt(Vector3D vector3D, Vector3D vector3D2, Vector3D vector3D3) {
    }

    public final void mul(AffineTrans affineTrans) {
    }

    public final void mul(AffineTrans affineTrans, AffineTrans affineTrans2) {
    }

    public final void multiply(AffineTrans affineTrans) {
    }

    public final void multiply(AffineTrans affineTrans, AffineTrans affineTrans2) {
    }

    public final void rotationV(Vector3D vector3D, int i) {
    }

    public final void rotationX(int i) {
    }

    public final void rotationY(int i) {
    }

    public final void rotationZ(int i) {
    }

    public final void set(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
    }

    public final void set(AffineTrans affineTrans) {
    }

    public final void set(int[] iArr) {
    }

    public final void set(int[] iArr, int i) {
    }

    public final void set(int[][] iArr) {
    }

    public final native void setIdentity();

    public final void setRotation(Vector3D vector3D, int i) {
    }

    public final native void setRotationX(int i);

    public final native void setRotationY(int i);

    public final native void setRotationZ(int i);

    public final void setViewTrans(Vector3D vector3D, Vector3D vector3D2, Vector3D vector3D3) {
    }

    public final Vector3D transPoint(Vector3D vector3D) {
        return null;
    }

    public final Vector3D transform(Vector3D vector3D) {
        return null;
    }
}
