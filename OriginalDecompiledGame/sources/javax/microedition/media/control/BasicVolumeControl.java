package javax.microedition.media.control;

import android.media.AudioManager;
import javax.microedition.midlet.MIDlet;

/* loaded from: classes.dex */
public class BasicVolumeControl implements VolumeControl {
    private int b;
    private double c;
    private AudioManager a = (AudioManager) MIDlet.getMIDletInstance().getSystemService("audio");
    private int d = this.a.getStreamMaxVolume(3);

    public BasicVolumeControl() {
        this.a.setStreamVolume(3, this.d, 0);
        this.c = 100.0d / this.d;
    }

    @Override // javax.microedition.media.control.VolumeControl
    public int getLevel() {
        return this.b;
    }

    @Override // javax.microedition.media.control.VolumeControl
    public boolean isMuted() {
        return this.a.getStreamVolume(3) == 0;
    }

    @Override // javax.microedition.media.control.VolumeControl
    public int setLevel(int i) {
        if (i >= 0 && i <= 100.0d) {
            this.a.setStreamVolume(3, (int) Math.round(i / this.c), 0);
            this.b = i;
        }
        return this.b;
    }

    @Override // javax.microedition.media.control.VolumeControl
    public void setMute(boolean z) {
        this.a.setStreamVolume(3, z ? 0 : this.b, 0);
    }
}
