package com.digitalchocolate.androidrollergapp;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

/* loaded from: classes.dex */
public abstract class DChocMIDlet extends MIDlet implements Runnable {
    public static final int APP_EVENT_ACTIVATE_CONTROLLER = 7;
    public static final int APP_EVENT_ACTIVATE_LICENSE_MANAGER = 5;
    public static final int APP_EVENT_EXIT = 3;
    public static final int APP_EVENT_MOUSE_ENTERED = 5;
    public static final int APP_EVENT_MOUSE_EXITED = 6;
    public static final int APP_EVENT_PAUSE = 0;
    public static final int APP_EVENT_RESUME = 1;
    public static final int APP_EVENT_SCREEN_SIZE_CHANGED = 4;
    public static final int APP_EVENT_START = 2;
    private static final int DELTA_AVG_FRAME_COUNT = 8;
    private static final int DELTA_AVG_FRAME_COUNT_SHIFT = 3;
    private static final int GAME_ACTION_ID_NONE = -100;
    public static final int GESTURE_FIXED_POINT_ACCURACY = 10;
    public static final int GESTURE_INDEX_MULTI_DRAG_DELTA_X = 0;
    public static final int GESTURE_INDEX_MULTI_DRAG_DELTA_Y = 1;
    public static final int GESTURE_INDEX_ROTATION = 3;
    public static final int GESTURE_INDEX_SCALE = 2;
    private static final int INITIAL_DELTA_TIME = 40;
    public static final int LETTERBOX_BOTTOM = 1;
    public static final int LETTERBOX_LEFT = 2;
    public static final int LETTERBOX_RIGHT = 3;
    public static final int LETTERBOX_TOP = 0;
    private static final int MAX_DELTA_TIME = 500;
    public static final int TOUCH_EVENT_DRAGGED = 2;
    public static final int TOUCH_EVENT_PRESSED = 0;
    public static final int TOUCH_EVENT_RELEASED = 1;
    public static final int TOUCH_INDEX_EVENT = 2;
    public static final int TOUCH_INDEX_ID = 3;
    public static final int TOUCH_INDEX_X = 0;
    public static final int TOUCH_INDEX_Y = 1;
    private static int fps;
    private static boolean sm_disclaimerScreenActive;
    private static int sm_disclaimerScreenTimer;
    private static boolean sm_disclaimerSkipPressed;
    private static boolean sm_disclaimerTimerDisabled;
    private static DChocMIDlet sm_instance;
    private static Thread sm_mainLoop;
    private static boolean sm_skipLoop;
    private static boolean sm_skipManualPause;
    private static boolean sm_skipTimer;
    private boolean isCallInterrupted = false;
    private boolean m_appPaused;
    private boolean m_appStarted;
    private boolean m_beingDestroyed;
    private boolean m_callSeriallyCalled;
    private int m_cumulativeDeltaTime;
    private int m_currentDeltaTimeTableIndex;
    private int[] m_deltaTimeTable;
    protected boolean m_exitApplication;
    private boolean m_initialized;
    private long m_previousTime;
    private boolean m_resumeApplication;
    private boolean m_skipLogicUpdate;

    protected DChocMIDlet() {
        sm_instance = this;
        Toolkit.create(this);
    }

    public static void enableDisclaimerTimer(boolean z) {
        sm_disclaimerTimerDisabled = !z;
    }

    public static int getFPS() {
        return fps;
    }

    public static DChocMIDlet getInstance() {
        return sm_instance;
    }

    public static boolean isDisclaimerScreenActive() {
        return sm_disclaimerScreenActive;
    }

    public static boolean openBrowser(String str) {
        try {
            if (!sm_instance.platformRequest(str)) {
                return true;
            }
            sm_instance.destroyApp(false);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void setCurrent(Displayable displayable) {
        Display.getDisplay(sm_instance).setCurrent(displayable);
    }

    public static void skipDisclaimer() {
        sm_disclaimerSkipPressed = true;
    }

    public static void skipLoop() {
        sm_skipLoop = true;
    }

    public static void skipManualPause() {
        sm_skipManualPause = true;
    }

    public static void skipTimer() {
        sm_skipTimer = true;
    }

    public abstract void appEventOccurred(int i);

    protected void checkManualPause() {
        if (!sm_skipManualPause && getRealDeltaTime() > 2000) {
            Toolkit.pauseMusic();
            Toolkit.stopSoundEffect();
            appEventOccurred(0);
            Toolkit.toolkitEventOccurred(0);
            appEventOccurred(1);
            Toolkit.toolkitEventOccurred(1);
        }
        sm_skipManualPause = false;
    }

    public void controllerEventOccurred(int i, int i2, int i3, int i4, int i5) {
        Toolkit.translateControllerEventToKeyEvent(i2, i3, i4, i5);
    }

    @Override // javax.microedition.midlet.MIDlet
    protected void destroyApp(boolean z) throws MIDletStateChangeException, InterruptedException {
        Toolkit.stopSoundEffect();
        Toolkit.stopMusic();
        this.m_beingDestroyed = z;
        this.m_exitApplication = true;
        if (z) {
            appEventOccurred(3);
            try {
                if (sm_mainLoop != null) {
                    sm_mainLoop.join();
                    sm_mainLoop = null;
                }
            } catch (Exception e) {
            }
        }
    }

    public abstract void doDraw(Graphics graphics);

    public void doPostDraw(Graphics graphics) {
    }

    public void drawCustomSoftkeyArea(Graphics graphics, int i, int i2, int i3, int i4) {
    }

    public void drawLetterBox(Graphics graphics, int i, int i2, int i3) {
    }

    public void drawMouseCursor(Graphics graphics, int i, int i2) {
    }

    public void exitApplication() {
        this.m_exitApplication = true;
    }

    public String getApplicationID() {
        return Statics.APPLICATION_ID;
    }

    public String getApplicationName() {
        return Toolkit.getText(50);
    }

    public abstract String getFormattedScore(int i, int i2, int i3);

    public abstract String[][] getHighscoreTables();

    public IMenu getNewMenuObject() {
        return new MenuObject();
    }

    public int getRealDeltaTime() {
        int i = this.m_currentDeltaTimeTableIndex - 1;
        if (i < 0) {
            i = 7;
        }
        return this.m_deltaTimeTable[i];
    }

    public int iRegGetConfiguration() {
        return 1;
    }

    public void iRegNotifyRegistrationEvent(boolean z, boolean z2) {
    }

    public boolean iRegWillShowPopup() {
        return true;
    }

    public abstract void keyEventOccurred(int i, int i2);

    public void keyEventOccurred(int i, int i2, int i3, char[] cArr) {
    }

    public abstract void logicUpdate(int i);

    public int mapControllerEventToGameAction(int i) {
        switch (i) {
            case IController.CONTROLLER_EVENT_ACTION_4 /* -1007 */:
                return 7;
            case IController.CONTROLLER_EVENT_ACTION_3 /* -1006 */:
                return 18;
            case IController.CONTROLLER_EVENT_ACTION_2 /* -1005 */:
                return 17;
            case IController.CONTROLLER_EVENT_ACTION_1 /* -1004 */:
                return 12;
            case IController.CONTROLLER_EVENT_RIGHT /* -1003 */:
                return 13;
            case IController.CONTROLLER_EVENT_LEFT /* -1002 */:
                return 11;
            case IController.CONTROLLER_EVENT_DOWN /* -1001 */:
                return 15;
            case IController.CONTROLLER_EVENT_UP /* -1000 */:
                return 9;
            default:
                return GAME_ACTION_ID_NONE;
        }
    }

    @Override // javax.microedition.midlet.MIDlet
    protected void pauseApp() {
        if (this.m_appPaused) {
            return;
        }
        Toolkit.pauseMusic();
        Toolkit.stopSoundEffect();
        appEventOccurred(0);
        Toolkit.toolkitEventOccurred(0);
        this.m_appPaused = true;
    }

    public abstract void pointerEventOccurred(int i, int i2, int i3);

    public void resetTimer() {
        this.m_previousTime = System.currentTimeMillis();
        this.m_currentDeltaTimeTableIndex = 0;
        for (int i = 0; i < 8; i++) {
            this.m_deltaTimeTable[i] = 40;
        }
        this.m_cumulativeDeltaTime = 320;
    }

    public void resume() {
    }

    @Override // java.lang.Runnable
    public void run() {
        do {
            try {
                if (this.isCallInterrupted) {
                    Toolkit.stopSoundEffect();
                    Toolkit.stopMusic();
                } else {
                    if (!this.m_initialized) {
                        this.m_deltaTimeTable = new int[8];
                        resetTimer();
                        Toolkit.initialize();
                        appEventOccurred(2);
                        this.m_initialized = true;
                    }
                    if (this.m_resumeApplication) {
                        appEventOccurred(1);
                        Toolkit.toolkitEventOccurred(1);
                        this.m_appPaused = false;
                        this.m_resumeApplication = false;
                        Toolkit.doRepaint();
                    }
                    if (sm_disclaimerScreenActive) {
                    }
                    if (!this.m_appPaused && !this.m_exitApplication) {
                        int iUpdateTimer = updateTimer();
                        Toolkit.processBufferedKeyEvent();
                        if (!this.m_skipLogicUpdate) {
                            logicUpdate(iUpdateTimer);
                        }
                        Toolkit.toolkitEventOccurred(2);
                        Toolkit.doRepaint();
                    }
                    Thread.sleep(10L);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!this.m_exitApplication);
        if (this.m_exitApplication) {
            if (this.m_beingDestroyed) {
                notifyDestroyed();
                return;
            }
            Toolkit.stopSoundEffect();
            Toolkit.stopMusic();
            appEventOccurred(3);
            notifyDestroyed();
        }
    }

    protected void setCallInterrupted(boolean z) {
    }

    public void skipLogicUpdate(boolean z) {
        this.m_skipLogicUpdate = z;
        Toolkit.pauseMusic();
        Toolkit.stopSoundEffect();
    }

    public void start() {
    }

    @Override // javax.microedition.midlet.MIDlet
    protected void startApp() throws MIDletStateChangeException {
        if (this.m_appStarted && this.m_appPaused) {
            this.m_previousTime = System.currentTimeMillis();
            this.m_resumeApplication = true;
        } else {
            if (this.m_appStarted) {
                return;
            }
            Toolkit.setVisible(true);
            if (sm_mainLoop == null) {
                sm_mainLoop = new Thread(this);
                sm_mainLoop.start();
            }
            this.m_appStarted = true;
        }
    }

    public void touchEventOccurred(int[][] iArr, int[] iArr2) {
    }

    protected int updateTimer() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int i = (int) (jCurrentTimeMillis - this.m_previousTime);
        this.m_previousTime = jCurrentTimeMillis;
        this.m_cumulativeDeltaTime -= Math.min(this.m_deltaTimeTable[this.m_currentDeltaTimeTableIndex], 500);
        this.m_cumulativeDeltaTime += Math.min(i, 500);
        this.m_deltaTimeTable[this.m_currentDeltaTimeTableIndex] = i;
        this.m_currentDeltaTimeTableIndex = (this.m_currentDeltaTimeTableIndex + 1) & 7;
        return this.m_cumulativeDeltaTime >> 3;
    }
}
