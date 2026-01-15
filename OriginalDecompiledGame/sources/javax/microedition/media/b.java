package javax.microedition.media;

import android.media.MediaPlayer;

/* loaded from: classes.dex */
final class b implements MediaPlayer.OnCompletionListener {
    private /* synthetic */ BasicPlayer a;

    b(BasicPlayer basicPlayer) {
        this.a = basicPlayer;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public final void onCompletion(MediaPlayer mediaPlayer) throws IllegalStateException {
        mediaPlayer.seekTo(0);
        this.a.a = 300;
    }
}
