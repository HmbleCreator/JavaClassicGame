package com.digitalchocolate.androidrollergapp;

import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class AccelerometerController implements IController {
    private static IController sm_instance;
    private static boolean sm_supported = false;
    private int m_previous_state;
    private AccelerometerDataListener smDataListener;

    public AccelerometerController() {
        try {
            Class.forName("javax.microedition.sensor.DataListener");
            initController();
        } catch (Throwable th) {
            sm_supported = false;
        }
        if (sm_supported) {
            return;
        }
        this.smDataListener = null;
    }

    public static IController getInstance() {
        if (sm_instance == null) {
            sm_instance = new AccelerometerController();
        }
        return sm_instance;
    }

    public static IController getUsedController() {
        return getInstance();
    }

    private void initController() {
        this.smDataListener = new AccelerometerDataListener();
        sm_supported = this.smDataListener.accelerationSupported();
    }

    private boolean isControllerListenerEnabled() {
        if (this.smDataListener != null) {
            return this.smDataListener.isEnabled();
        }
        return false;
    }

    private void setListenerEnabled(boolean z) {
        if (this.smDataListener != null) {
            this.smDataListener.setEnabled(z);
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IController
    public void doDraw(Graphics graphics) {
    }

    @Override // com.digitalchocolate.androidrollergapp.IController
    public String getControllerAboutText() {
        return null;
    }

    @Override // com.digitalchocolate.androidrollergapp.IController
    public String getControllerMenuItemLabel() {
        if (sm_supported) {
            return Toolkit.getText(30);
        }
        return null;
    }

    @Override // com.digitalchocolate.androidrollergapp.IController
    public void initMenus() {
    }

    @Override // com.digitalchocolate.androidrollergapp.IController
    public boolean isControllerEnabled() {
        try {
            if (sm_supported) {
                return isControllerListenerEnabled();
            }
        } catch (Throwable th) {
        }
        return false;
    }

    @Override // com.digitalchocolate.androidrollergapp.IController
    public boolean isControllerSupported() {
        return sm_supported;
    }

    @Override // com.digitalchocolate.androidrollergapp.IController
    public void keyEventOccurred(int i, int i2) {
    }

    @Override // com.digitalchocolate.androidrollergapp.IController
    public int logicUpdate(int i) {
        return 1;
    }

    @Override // com.digitalchocolate.androidrollergapp.IController
    public void pointerEventOccurred(int i, int i2, int i3) {
    }

    @Override // com.digitalchocolate.androidrollergapp.IController
    public boolean setState(int i) {
        if (!sm_supported) {
            return false;
        }
        if (i == 4 || i == 5) {
            try {
                setListenerEnabled(i == 4);
            } catch (Throwable th) {
            }
        }
        return false;
    }
}
