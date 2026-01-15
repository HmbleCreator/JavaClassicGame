package javax.microedition.media;

import android.media.MediaPlayer;

/* loaded from: classes.dex */
final class a implements MediaPlayer.OnErrorListener {
    a(BasicPlayer basicPlayer) {
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) throws IllegalStateException {
        System.err.println("Error in Player- what: " + i + " extra:" + i2);
        if (mediaPlayer == null) {
            return false;
        }
        mediaPlayer.stop();
        mediaPlayer.release();
        return false;
    }
}
