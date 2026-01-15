package javax.microedition.m3g;

import java.util.Hashtable;

/* loaded from: classes.dex */
public class Graphics3D {
    public static final int ANTIALIAS = 2;
    public static final int DITHER = 4;
    public static final int TRUE_COLOR = 8;

    Graphics3D() {
    }

    public static final Graphics3D getInstance() {
        return null;
    }

    public static final synchronized Hashtable getProperties() {
        return null;
    }

    public native int addLight(Light light, Transform transform);

    public synchronized void bindTarget(Object obj) {
    }

    public synchronized void bindTarget(Object obj, boolean z, int i) {
    }

    public synchronized void clear(Background background) {
    }

    public synchronized void releaseTarget() {
    }

    public synchronized void render(Node node, Transform transform) {
    }

    public void render(VertexBuffer vertexBuffer, IndexBuffer indexBuffer, Appearance appearance, Transform transform) {
    }

    public synchronized void render(VertexBuffer vertexBuffer, IndexBuffer indexBuffer, Appearance appearance, Transform transform, int i) {
    }

    public synchronized void render(World world) {
    }

    public native void resetLights();

    public native void setCamera(Camera camera, Transform transform);

    public native void setDepthRange(float f, float f2);

    public native void setLight(int i, Light light, Transform transform);

    public void setViewport(int i, int i2, int i3, int i4) {
    }
}
