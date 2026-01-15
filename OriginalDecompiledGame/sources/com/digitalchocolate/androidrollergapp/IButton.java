package com.digitalchocolate.androidrollergapp;

import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public interface IButton {
    public static final int ALIGNMENT_BOTTOM = 32;
    public static final int ALIGNMENT_CENTER = 67;
    public static final int ALIGNMENT_HCENTER = 1;
    public static final int ALIGNMENT_LEFT = 4;
    public static final int ALIGNMENT_RIGHT = 8;
    public static final int ALIGNMENT_TOP = 16;
    public static final int ALIGNMENT_VCENTER = 66;
    public static final int BUTTON_DISPATCH_EVENT = 6;
    public static final int BUTTON_HIGHLIGHTED = 1;
    public static final int BUTTON_IDLE = 0;
    public static final int BUTTON_LOCKED_HIGHLIGHTED = 4;
    public static final int BUTTON_LOCKED_IDLE = 3;
    public static final int BUTTON_LOCKED_SELECTED = 5;
    public static final int BUTTON_SELECTED = 2;
    public static final int EVENT_DATA_EVENT_ID = 0;
    public static final int EVENT_DATA_EVENT_TYPE = 1;
    public static final int SHRINK_TO_FIT = -1;
    public static final int STATE_NONE = -1;

    void destroy();

    void doDraw(Graphics graphics);

    int getHeight();

    int getPivotX();

    int getPivotY();

    int getWidth();

    int getX();

    int getY();

    int[] logicUpdate(int i);

    boolean pointerEventOccurred(int i, int i2, int i3);

    void resetState();

    void setPosition(int i, int i2);

    void setSize(int i, int i2);
}
