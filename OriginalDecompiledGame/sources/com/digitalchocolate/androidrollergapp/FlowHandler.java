package com.digitalchocolate.androidrollergapp;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* loaded from: classes.dex */
public interface FlowHandler {
    public static final int BACK_EVENT = -3;
    public static final int DEFAULT_EVENT = -2;
    public static final int NO_EVENT = -1;
    public static final int PROCESS_MENU_BRING_VISIBLE = 2;
    public static final int PROCESS_MENU_BROUGHT_VISIBLE = 3;
    public static final int PROCESS_MENU_CONTENT_ADDED = 1;
    public static final int PROCESS_MENU_NEWLY_CREATED = 0;

    void controllerActivated();

    void doDraw(int i, Graphics graphics);

    void drawLoadingScreen(Graphics graphics, int i);

    void drawMenu(int i, Graphics graphics, CustomMenuObject customMenuObject);

    boolean evaluateBranchCondition(int i);

    void eventOccurred(int i, int i2);

    int getDavinciLoadingPercentage(int i);

    int getStateGroupLoadingCount(int i);

    int getStateLoadingCount(int i);

    boolean isChildGroup(int i, int i2);

    boolean isMenuNeeded(int i);

    void keyEventOccurred(int i, int i2, int i3);

    void licenseManagerActivated();

    void loadState(int i, int i2);

    void loadStateGroup(int i, int i2);

    int logicUpdate(int i, int i2);

    void menuSetInputItem(int i, MenuObject menuObject, int i2, int i3, String str, String str2, SpriteObject spriteObject, Image[] imageArr, int i4, int i5);

    void menuSetItem(int i, MenuObject menuObject, int i2, int i3, String str, SpriteObject spriteObject, Image[] imageArr, int i4);

    void menuSetScreen(int i, MenuObject menuObject, int i2, int i3, int i4);

    void menuSetSize(int i, MenuObject menuObject);

    void menuSetSliderItem(int i, MenuObject menuObject, int i2, String str, SpriteObject spriteObject, Image[] imageArr, int i3, int i4, int i5);

    void menuSetSoftkey(int i, MenuObject menuObject, int i2, int i3);

    void menuSetStyle(int i, MenuObject menuObject, int i2);

    void menuSetSwitchItem(int i, MenuObject menuObject, int i2, String str, SpriteObject spriteObject, Image[] imageArr, String[] strArr, SpriteObject spriteObject2, Image[] imageArr2, int i3);

    void menuSetTitleBar(int i, MenuObject menuObject, String str, Image image, int i2);

    void menuSetTitleBarDvc(int i, MenuObject menuObject, String str, SpriteObject spriteObject, int i2);

    void pauseGame();

    void pointerEventOccurred(int i, int i2, int i3, int i4);

    void processMenu(int i, MenuObject menuObject, int i2);

    String processText(int i, int i2, int i3);

    void screenSizeChanged();

    void setLoading(int i, boolean z);

    boolean shouldStoreStateToHistory(int i);

    void switchState(int i, int i2, MenuObject menuObject);

    void unloadState(int i, int i2);

    void unloadStateGroup(int i, int i2);
}
