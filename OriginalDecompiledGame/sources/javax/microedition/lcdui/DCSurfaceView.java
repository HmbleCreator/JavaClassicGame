package javax.microedition.lcdui;

import android.content.Context;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes.dex */
public class DCSurfaceView extends SurfaceView implements SurfaceHolder.Callback, IDCView {
    GL10 a;
    private SurfaceHolder b;
    private a c;
    private android.graphics.Canvas d;
    private boolean e;
    private Displayable f;
    private KeyCharacterMap.KeyData g;
    private char[] h;

    private class a {
        EGL10 a;
        EGLDisplay b;
        EGLSurface c;
        EGLContext d;
        final /* synthetic */ DCSurfaceView e;
    }

    public DCSurfaceView(Context context, Displayable displayable) {
        super(context);
        this.d = null;
        this.f = displayable;
        this.b = getHolder();
        this.b.addCallback(this);
        this.h = new char[1];
        this.g = new KeyCharacterMap.KeyData();
    }

    private static int a(KeyEvent keyEvent) {
        switch (keyEvent.getAction()) {
            case 0:
                return 0;
            case 1:
                return 1;
            default:
                return -1;
        }
    }

    private void a(KeyEvent keyEvent, int i) {
        keyEvent.getKeyData(this.g);
        char[] cArr = this.g.meta;
        int metaState = keyEvent.getMetaState();
        if ((metaState != 0 && metaState != 1) || i == 57 || i == 58 || i == 59 || i == 60 || i == 66 || i == 25 || i == 24 || i == 23 || i == 20 || i == 21 || i == 22 || i == 19 || i == 4 || i == 67 || i == 82 || i == 5 || i == 27 || i == 80) {
            this.f.keyEventWithChars(i, a(keyEvent), null);
        } else {
            this.h[0] = cArr[keyEvent.getMetaState()];
            this.f.keyEventWithChars(i, a(keyEvent), this.h);
        }
    }

    @Override // javax.microedition.lcdui.IDCView
    public void createGraphicsContext() {
    }

    @Override // javax.microedition.lcdui.IDCView
    public void destroyGraphicsContext() {
        a aVar = this.c;
        aVar.e.a.glDisableClientState(32888);
        aVar.e.a.glDisableClientState(32884);
        aVar.e.a.glDisable(3042);
        aVar.e.a.glDisable(3553);
        aVar.e.a.glPopMatrix();
        if (aVar.c != null) {
            aVar.a.eglMakeCurrent(aVar.b, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
            aVar.a.eglDestroySurface(aVar.b, aVar.c);
            aVar.c = null;
        }
        if (aVar.d != null) {
            aVar.a.eglDestroyContext(aVar.b, aVar.d);
            aVar.d = null;
        }
        if (aVar.b != null) {
            aVar.a.eglTerminate(aVar.b);
            aVar.b = null;
        }
    }

    /* JADX WARN: Finally extract failed */
    @Override // javax.microedition.lcdui.IDCView
    public void doDraw() {
        try {
            this.d = this.b.lockCanvas();
            if (this.d != null) {
                synchronized (this.b) {
                    onDraw(this.d);
                }
            }
            if (this.d != null) {
                this.b.unlockCanvasAndPost(this.d);
            }
        } catch (Throwable th) {
            if (this.d != null) {
                this.b.unlockCanvasAndPost(this.d);
            }
            throw th;
        }
    }

    @Override // javax.microedition.lcdui.IDCView
    public GL getGraphicsContext() {
        return this.a;
    }

    @Override // javax.microedition.lcdui.IDCView
    public boolean isGraphicsContextReady() {
        return this.e;
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    protected void onDraw() {
        GLGraphics gLGraphics = GLGraphics.getInstance();
        gLGraphics.setGl(this.a);
        this.f.paint(gLGraphics);
    }

    @Override // android.view.View
    protected void onDraw(android.graphics.Canvas canvas) {
        Graphics graphics = Graphics.getInstance();
        graphics.setCanvas(canvas);
        this.f.paint(graphics);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 79 || i == 85 || i == 86 || i == 90 || i == 87 || i == 88 || i == 89) {
            return false;
        }
        this.f.keyPressed(i);
        a(keyEvent, i);
        return (i == 24 || i == 25) ? false : true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 79 || i == 85 || i == 86 || i == 90 || i == 87 || i == 88 || i == 89) {
            return false;
        }
        this.f.keyReleased(i);
        a(keyEvent, i);
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) throws InterruptedException {
        if (motionEvent.getAction() == 0) {
            this.f.pointerPressed((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        if (motionEvent.getAction() == 1) {
            this.f.pointerReleased((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        if (motionEvent.getAction() == 2) {
            this.f.pointerDragged((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
        }
        return true;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }
}
