package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public class MouseCursor {
    private DChocImage cursorImage;
    private int hotSpotX;
    private int hotSpotY;

    public MouseCursor(DChocImage dChocImage, int i, int i2) {
        this.cursorImage = dChocImage;
        this.hotSpotX = i;
        this.hotSpotY = i2;
    }

    public int getHotSpotX() {
        return this.hotSpotX;
    }

    public int getHotSpotY() {
        return this.hotSpotY;
    }

    public DChocImage getImage() {
        return this.cursorImage;
    }
}
