package javax.microedition.lcdui;

/* loaded from: classes.dex */
public abstract class Canvas extends Displayable {
    public static final int DOWN = 20;
    public static final int EVENT_KEY_PRESSED = 0;
    public static final int EVENT_KEY_RELEASED = 1;
    public static final int EVENT_KEY_REPEATED = 2;
    public static final int FIRE = 23;
    public static final int GAME_A = 29;
    public static final int GAME_B = 30;
    public static final int GAME_C = 31;
    public static final int GAME_D = 32;
    public static final int KEY_NUM0 = 7;
    public static final int KEY_NUM1 = 8;
    public static final int KEY_NUM2 = 9;
    public static final int KEY_NUM3 = 10;
    public static final int KEY_NUM4 = 11;
    public static final int KEY_NUM5 = 12;
    public static final int KEY_NUM6 = 13;
    public static final int KEY_NUM7 = 14;
    public static final int KEY_NUM8 = 15;
    public static final int KEY_NUM9 = 16;
    public static final int KEY_POUND = 18;
    public static final int KEY_STAR = 17;
    public static final int LEFT = 21;
    public static final int RIGHT = 22;
    public static final int UP = 19;

    public int getGameAction(int i) {
        switch (i) {
            case 9:
            case 19:
                return 19;
            case 10:
            case 14:
            case 16:
            case 17:
            case 18:
            default:
                return i;
            case 11:
            case 21:
                return 21;
            case 12:
            case 23:
                return 23;
            case 13:
            case 22:
                return 22;
            case 15:
            case 20:
                return 20;
        }
    }

    public final boolean isGLReady() {
        return view.isGraphicsContextReady();
    }

    @Override // javax.microedition.lcdui.Displayable
    protected void keyEventWithChars(int i, int i2, char[] cArr) {
    }

    @Override // javax.microedition.lcdui.Displayable
    protected void keyPressed(int i) {
    }

    @Override // javax.microedition.lcdui.Displayable
    protected void keyReleased(int i) {
    }

    @Override // javax.microedition.lcdui.Displayable
    protected void keyRepeated(int i) {
    }

    public final void makeGl() {
        view.createGraphicsContext();
    }

    @Override // javax.microedition.lcdui.Displayable
    protected void pointerDragged(int i, int i2) {
    }

    @Override // javax.microedition.lcdui.Displayable
    protected void pointerPressed(int i, int i2) {
    }

    @Override // javax.microedition.lcdui.Displayable
    protected void pointerReleased(int i, int i2) {
    }

    public final void releaseGL() {
    }

    public final void repaint() {
        view.doDraw();
    }

    public final void serviceRepaints() {
    }
}
