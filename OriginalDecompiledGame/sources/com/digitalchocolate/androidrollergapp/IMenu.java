package com.digitalchocolate.androidrollergapp;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* loaded from: classes.dex */
public interface IMenu {
    public static final int ALIGNMENT_HCENTER = 1;
    public static final int ALIGNMENT_LEFT = 0;
    public static final int ALIGNMENT_TOP = 0;
    public static final int ALIGNMENT_VCENTER = 2;
    public static final int EVENT_ITEM = 1;
    public static final int EVENT_SOFTKEY = 0;
    public static final int INPUT_TYPE_EMAIL = 4;
    public static final int INPUT_TYPE_LOCALIZED_TEXT = 4;
    public static final int INPUT_TYPE_LONG_TEXT = 1;
    public static final int INPUT_TYPE_NUMBER = 3;
    public static final int INPUT_TYPE_PHONE_NUMBER = 2;
    public static final int INPUT_TYPE_SHORT_TEXT = 0;
    public static final int INPUT_TYPE_TEXT = 1;
    public static final int INPUT_TYPE_USERNAME = 0;
    public static final int ITEM_TYPE_EMPTY = -1;
    public static final int ITEM_TYPE_IMAGE = 1;
    public static final int ITEM_TYPE_SELECTION = 0;
    public static final int ITEM_TYPE_TEXT = 1;
    public static final int NO_PAGING = -1;
    public static final int NO_SOFTKEY = -1;
    public static final int SCREEN_TYPE_MENU_SCREEN = 0;
    public static final int SCREEN_TYPE_TEXT_BOX = 2;
    public static final int SCREEN_TYPE_TEXT_SCREEN = 1;
    public static final int SOFTKEY_MIDDLE = 2;
    public static final int SOFTKEY_NEGATIVE = 1;
    public static final int SOFTKEY_POSITIVE = 0;

    void destroyItem(int i);

    void doDraw(Graphics graphics, int i, int i2);

    void drawLoadingScreen(Graphics graphics, String[] strArr, int i);

    int getItemIntValue(int i);

    String getItemStringValue(int i);

    int getSelectedItem();

    void keyEventOccurred(int i, int i2);

    int[] logicUpdate(int i);

    void pointerEventOccurred(int i, int i2, int i3);

    void releaseScreen();

    void setInputItem(int i, int i2, String str, String str2, Image[] imageArr, int i3, int i4);

    void setItem(int i, int i2, String str, Image[] imageArr, int i3);

    void setScreen(int i, int i2, int i3);

    void setSelectedItem(int i);

    void setSize(int i, int i2);

    void setSoftkey(int i, int i2);

    void setTitleBar(String str, Image image, int i);

    void setVisible();
}
