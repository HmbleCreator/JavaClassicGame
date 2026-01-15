package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public interface IMouse {
    MouseCursor getMouseCursor();

    int getMouseX();

    int getMouseY();

    boolean isCursorShown();

    boolean isMouseOverCanvas();

    void setMouseCursor(MouseCursor mouseCursor);

    void showCursor(boolean z);
}
