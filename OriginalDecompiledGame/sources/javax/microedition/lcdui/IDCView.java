package javax.microedition.lcdui;

import javax.microedition.khronos.opengles.GL;

/* loaded from: classes.dex */
public interface IDCView {
    void createGraphicsContext();

    void destroyGraphicsContext();

    void doDraw();

    GL getGraphicsContext();

    boolean isGraphicsContextReady();
}
