package com.nokia.mid.ui;

import android.os.PowerManager;
import javax.microedition.midlet.MIDlet;

/* loaded from: classes.dex */
public class DeviceControl {
    private static PowerManager.WakeLock a = ((PowerManager) MIDlet.getMIDletInstance().getSystemService("power")).newWakeLock(26, "DC Lock");

    private DeviceControl() {
    }

    public static void flashLights(long j) {
        a.acquire(j);
    }

    public static void setLights(int i, int i2) {
        if (i2 < 0 && i2 > 100) {
            throw new IllegalArgumentException("level out of range");
        }
        a.acquire(15000L);
    }

    public static void startVibra(int i, long j) {
    }

    public static void stopVibra() {
    }
}
