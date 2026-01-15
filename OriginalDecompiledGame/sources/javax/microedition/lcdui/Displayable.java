package javax.microedition.lcdui;

import android.view.View;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.midlet.MIDlet;

/* loaded from: classes.dex */
public abstract class Displayable {
    protected static IDCView view;

    public static IDCView getView() {
        return view;
    }

    protected GL getGraphicsContext() {
        return view.getGraphicsContext();
    }

    public int getHeight() {
        return MIDlet.getHeight();
    }

    public int getWidth() {
        return MIDlet.getWidth();
    }

    protected abstract void keyEventWithChars(int i, int i2, char[] cArr);

    protected abstract void keyPressed(int i);

    protected abstract void keyReleased(int i);

    protected abstract void keyRepeated(int i);

    protected abstract void paint(Graphics graphics);

    protected abstract void pointerDragged(int i, int i2);

    protected abstract void pointerPressed(int i, int i2);

    protected abstract void pointerReleased(int i, int i2);

    protected void setView(MIDlet mIDlet) {
        DCSurfaceView dCSurfaceView = new DCSurfaceView(mIDlet, this);
        view = dCSurfaceView;
        dCSurfaceView.setFocusable(true);
        ((View) view).setFocusableInTouchMode(true);
    }
}
