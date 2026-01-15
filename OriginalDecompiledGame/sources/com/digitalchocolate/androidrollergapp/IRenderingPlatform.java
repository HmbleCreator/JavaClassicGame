package com.digitalchocolate.androidrollergapp;

import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public interface IRenderingPlatform {
    public static final int BOTTOM = 32;
    public static final int FILTER_ANTI_ALIAS_PRIMITIVES = 2;
    public static final int FILTER_BILINEAR = 1;
    public static final int HCENTER = 1;
    public static final int LEFT = 4;
    public static final int NEUTRAL_COLOR = -8355712;
    public static final int NO_SCALING = 1024;
    public static final int RENDER_MODE_ADD = 1;
    public static final int RENDER_MODE_NORMAL = 0;
    public static final int RENDER_MODE_SUB = 2;
    public static final int RIGHT = 8;
    public static final int SCALE_ACCURACY = 10;
    public static final int TOP = 16;
    public static final int TRANS_MIRROR_HORIZONTAL = 1;
    public static final int TRANS_MIRROR_VERTICAL = 2;
    public static final int TRANS_NONE = 0;
    public static final int TRANS_ROTATE_180 = 8;
    public static final int TRANS_ROTATE_270 = 16;
    public static final int TRANS_ROTATE_90 = 4;
    public static final int VCENTER = 2;

    void clipRect(int i, int i2, int i3, int i4);

    void disableAllEffects();

    void drawImage(DChocImage dChocImage, int i, int i2, int i3, int i4);

    void drawLine(int i, int i2, int i3, int i4);

    void drawRect(int i, int i2, int i3, int i4);

    void fillBezier(int[] iArr, int i, int i2);

    void fillEllipse(int i, int i2, int i3, int i4);

    void fillPolygon(int[] iArr, int i, int i2);

    void fillRect(int i, int i2, int i3, int i4);

    void fillTriangle(int i, int i2, int i3, int i4, int i5, int i6);

    int getBlurHeight();

    int getBlurWidth();

    int getClipHeight();

    int getClipWidth();

    int getClipX();

    int getClipY();

    int getColor();

    int getColorModification();

    int getFilters();

    Graphics getGraphicsContext();

    int getPivotX();

    int getPivotY();

    int getRenderMode();

    int getRotation();

    int getScaleHeight();

    int getScaleWidth();

    boolean isFilterEnabled(int i);

    boolean isFrameUpToDate(DChocImage dChocImage);

    void makeImageTransparent(DChocImage dChocImage, int i, int i2);

    void popParameters();

    void pushParameters();

    void setBlur(int i, int i2);

    void setClip(int i, int i2, int i3, int i4);

    void setColor(int i);

    void setColor(int i, int i2, int i3, int i4);

    void setColorARGB(int i);

    void setColorKey(int i);

    void setColorModification(int i);

    void setColorModification(int i, int i2, int i3, int i4);

    void setFiltering(int i, boolean z);

    void setFilters(int i);

    void setGraphicsContext(DChocImage dChocImage);

    void setGraphicsContext(Graphics graphics);

    void setPivot(int i, int i2);

    void setRenderMode(int i);

    void setRotation(int i);

    void setScale(int i, int i2);
}
