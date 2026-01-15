package javax.microedition.lcdui;

import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import javax.microedition.khronos.opengles.GL;

/* loaded from: classes.dex */
public class DCView extends View implements IDCView {
    private Displayable a;

    public DCView(Context context, Displayable displayable) {
        super(context);
        this.a = displayable;
    }

    @Override // javax.microedition.lcdui.IDCView
    public void createGraphicsContext() {
    }

    @Override // javax.microedition.lcdui.IDCView
    public void destroyGraphicsContext() {
    }

    @Override // javax.microedition.lcdui.IDCView
    public void doDraw() {
        postInvalidate();
    }

    @Override // javax.microedition.lcdui.IDCView
    public GL getGraphicsContext() {
        return null;
    }

    @Override // javax.microedition.lcdui.IDCView
    public boolean isGraphicsContextReady() {
        return true;
    }

    @Override // android.view.View
    protected void onDraw(android.graphics.Canvas canvas) {
        Graphics graphics = Graphics.getInstance();
        graphics.setCanvas(canvas);
        this.a.paint(graphics);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        this.a.keyPressed(i);
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        this.a.keyReleased(i);
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.a.pointerPressed((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        if (motionEvent.getAction() == 1) {
            this.a.pointerReleased((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        if (motionEvent.getAction() == 2) {
            this.a.pointerDragged((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        return true;
    }
}
