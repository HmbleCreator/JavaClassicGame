package javax.microedition.media.control;

import javax.microedition.media.Control;

/* loaded from: classes.dex */
public interface RateControl extends Control {
    int getMaxRate();

    int getMinRate();

    int setRate(int i);
}
