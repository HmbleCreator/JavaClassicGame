package com.digitalchocolate.androidrollergapp;

import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public interface IController {
    public static final int ACCELEROMETER_EVENT_SWEEP_X = 2000;
    public static final int ACCELEROMETER_EVENT_SWEEP_Y = 2001;
    public static final int ACCELEROMETER_EVENT_SWEEP_Z = 2002;
    public static final int CONTROLLER_BUTTON_1 = 0;
    public static final int CONTROLLER_BUTTON_2 = 1;
    public static final int CONTROLLER_BUTTON_3 = 2;
    public static final int CONTROLLER_BUTTON_4 = 3;
    public static final int CONTROLLER_DIRECTIONAL_1 = 4;
    public static final int CONTROLLER_DIRECTIONAL_2 = 5;
    public static final int CONTROLLER_EVENT_ACTION_1 = -1004;
    public static final int CONTROLLER_EVENT_ACTION_2 = -1005;
    public static final int CONTROLLER_EVENT_ACTION_3 = -1006;
    public static final int CONTROLLER_EVENT_ACTION_4 = -1007;
    public static final int CONTROLLER_EVENT_DOWN = -1001;
    public static final int CONTROLLER_EVENT_LEFT = -1002;
    public static final int CONTROLLER_EVENT_RIGHT = -1003;
    public static final int CONTROLLER_EVENT_UP = -1000;
    public static final int STATE_ENTER_ACTIVATE_FROM_MENU = 3;
    public static final int STATE_ENTER_APP_START = 2;
    public static final int STATE_ENTER_DISABLE_CONTROLLER = 5;
    public static final int STATE_ENTER_ENABLE_CONTROLLER = 4;
    public static final int STATE_RETURN_FINISHED = 1;
    public static final int STATE_RETURN_NOT_FINISHED = 0;

    void doDraw(Graphics graphics);

    String getControllerAboutText();

    String getControllerMenuItemLabel();

    void initMenus();

    boolean isControllerEnabled();

    boolean isControllerSupported();

    void keyEventOccurred(int i, int i2);

    int logicUpdate(int i);

    void pointerEventOccurred(int i, int i2, int i3);

    boolean setState(int i);
}
