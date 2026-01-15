package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public class EmptyVirtualKeypad implements IVirtualKeypad {
    private static IVirtualKeypad sm_virtualKeypad;

    public static IVirtualKeypad getUsedVirtualKeypad() {
        return null;
    }

    @Override // com.digitalchocolate.androidrollergapp.IVirtualKeypad
    public int getHeight() {
        return 0;
    }

    @Override // com.digitalchocolate.androidrollergapp.IVirtualKeypad
    public int getWidth() {
        return 0;
    }

    @Override // com.digitalchocolate.androidrollergapp.IVirtualKeypad
    public int getX() {
        return 0;
    }

    @Override // com.digitalchocolate.androidrollergapp.IVirtualKeypad
    public int getY() {
        return 0;
    }

    @Override // com.digitalchocolate.androidrollergapp.IVirtualKeypad
    public boolean isVisible() {
        return false;
    }

    @Override // com.digitalchocolate.androidrollergapp.IVirtualKeypad
    public void setVisible(boolean z) {
    }
}
