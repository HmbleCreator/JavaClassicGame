package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public class EmptyMouse implements IMouse {
    public static IMouse getMouse() {
        return null;
    }

    @Override // com.digitalchocolate.androidrollergapp.IMouse
    public MouseCursor getMouseCursor() {
        return null;
    }

    @Override // com.digitalchocolate.androidrollergapp.IMouse
    public int getMouseX() {
        return 0;
    }

    @Override // com.digitalchocolate.androidrollergapp.IMouse
    public int getMouseY() {
        return 0;
    }

    @Override // com.digitalchocolate.androidrollergapp.IMouse
    public boolean isCursorShown() {
        return false;
    }

    @Override // com.digitalchocolate.androidrollergapp.IMouse
    public boolean isMouseOverCanvas() {
        return false;
    }

    @Override // com.digitalchocolate.androidrollergapp.IMouse
    public void setMouseCursor(MouseCursor mouseCursor) {
    }

    @Override // com.digitalchocolate.androidrollergapp.IMouse
    public void showCursor(boolean z) {
    }
}
