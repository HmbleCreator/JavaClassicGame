package com.digitalchocolate.androidrollergapp;

import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class EmptyLicenseManager implements ILicenseManager {
    public static ILicenseManager getLicenseManager() {
        return null;
    }

    @Override // com.digitalchocolate.androidrollergapp.ILicenseManager
    public void doDraw(Graphics graphics) {
    }

    @Override // com.digitalchocolate.androidrollergapp.ILicenseManager
    public void endDemo() {
    }

    @Override // com.digitalchocolate.androidrollergapp.ILicenseManager
    public String getLicenseManagerMenuItemLabel() {
        return null;
    }

    @Override // com.digitalchocolate.androidrollergapp.ILicenseManager
    public int getLicenseManagerMenuItemPosition() {
        return 0;
    }

    @Override // com.digitalchocolate.androidrollergapp.ILicenseManager
    public int getLicenseMode() {
        return 2;
    }

    @Override // com.digitalchocolate.androidrollergapp.ILicenseManager
    public void initMenus() {
    }

    @Override // com.digitalchocolate.androidrollergapp.ILicenseManager
    public void keyEventOccurred(int i, int i2) {
    }

    @Override // com.digitalchocolate.androidrollergapp.ILicenseManager
    public int logicUpdate(int i) {
        return 0;
    }

    @Override // com.digitalchocolate.androidrollergapp.ILicenseManager
    public void pointerEventOccurred(int i, int i2, int i3) {
    }

    @Override // com.digitalchocolate.androidrollergapp.ILicenseManager
    public boolean setState(int i) {
        return false;
    }

    @Override // com.digitalchocolate.androidrollergapp.ILicenseManager
    public void updateTimer(int i) {
    }
}
