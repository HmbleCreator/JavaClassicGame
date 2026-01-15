package com.digitalchocolate.androidrollergapp;

import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public interface IHighScore {
    public static final int APP_START = 2;
    public static final int CHIPS_INDEX = -1;
    public static final int DID_NOT_MAKE_IT_TO_LIST = -1;
    public static final int ENTER_FROM_GAME = 1;
    public static final int ENTER_FROM_MENU = 0;
    public static final int STATE_DONE = 1;
    public static final int STATE_NOT_FINISHED = 0;

    void clearScores();

    void doDraw(Graphics graphics);

    String getHighScoreMenuItemLabel();

    String getPlayerName();

    int[] getScore(int i, int i2);

    void initMenus();

    int insertScore(int i, int[] iArr, String str);

    void keyEventOccurred(int i, int i2);

    int logicUpdate(int i);

    void pointerEventOccurred(int i, int i2, int i3);

    boolean setState(int i);
}
