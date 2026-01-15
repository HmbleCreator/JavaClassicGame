package javax.microedition.lcdui.game;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public abstract class GameCanvas extends Canvas {
    protected GameCanvas(boolean z) {
    }

    public void flushGraphics() {
        throw new RuntimeException("Method not supported");
    }

    public void flushGraphics(int i, int i2, int i3, int i4) {
        throw new RuntimeException("Method not supported");
    }

    protected Graphics getGraphics() {
        throw new RuntimeException("Method not supported");
    }

    public void setFullScreenMode(boolean z) {
    }
}
