package javax.microedition.media.control;

import javax.microedition.media.Control;

/* loaded from: classes.dex */
public interface VolumeControl extends Control {
    int getLevel();

    boolean isMuted();

    int setLevel(int i);

    void setMute(boolean z);
}
