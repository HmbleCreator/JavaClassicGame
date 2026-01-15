package com.digitalchocolate.androidrollergapp;

import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public interface ILicenseManager {
    public static final int APP_START = 2;
    public static final int ENTER_FROM_MENU = 4;
    public static final int GAME_END = 5;
    public static final int GAME_PAUSE = 6;
    public static final int GAME_RESUME = 7;
    public static final int GAME_START = 3;
    public static final int ITEM_POSITION_BOTTOM = 1;
    public static final int ITEM_POSITION_TOP = 0;
    public static final int STATE_FREE_TRIAL_MODE = 4;
    public static final int STATE_LICENSE_INVALID = 3;
    public static final int STATE_LICENSE_VALID = 2;
    public static final int STATE_NOT_FINISHED = 0;

    void doDraw(Graphics graphics);

    void endDemo();

    String getLicenseManagerMenuItemLabel();

    int getLicenseManagerMenuItemPosition();

    int getLicenseMode();

    void initMenus();

    void keyEventOccurred(int i, int i2);

    int logicUpdate(int i);

    void pointerEventOccurred(int i, int i2, int i3);

    boolean setState(int i);

    void updateTimer(int i);
}
