package javax.microedition.lcdui;

import android.view.View;
import javax.microedition.midlet.MIDlet;

/* loaded from: classes.dex */
public class Display {
    private static Display a;
    private static MIDlet b;
    private static Displayable c;

    private Display() {
    }

    public static Display getDisplay(MIDlet mIDlet) {
        b = mIDlet;
        if (a == null) {
            a = new Display();
        }
        return a;
    }

    public void callSerially(Runnable runnable) {
    }

    public Displayable getCurrent() {
        return c;
    }

    public void setCurrent(Displayable displayable) {
        displayable.setView(b);
        b.setContentView((View) Displayable.view);
        c = displayable;
    }

    public void setCurrent(List list) {
    }

    public boolean vibrate(int i) {
        return false;
    }
}
