package javax.microedition.media;

import android.media.MediaPlayer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.midlet.MIDlet;

/* loaded from: classes.dex */
public class BasicPlayer implements Player {
    int a;
    private MediaPlayer b;
    private File c;

    public BasicPlayer(InputStream inputStream) throws IllegalStateException, IOException, IllegalArgumentException {
        this.a = 0;
        if (inputStream == null) {
            throw new RuntimeException("stream is null");
        }
        File fileStreamPath = MIDlet.getMIDletInstance().getFileStreamPath("_sound_data");
        fileStreamPath.mkdirs();
        this.c = File.createTempFile("temp" + System.currentTimeMillis(), "", fileStreamPath);
        FileOutputStream fileOutputStream = new FileOutputStream(this.c);
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i <= 0) {
                this.b = new MediaPlayer();
                this.b.setDataSource(new FileInputStream(this.c).getFD());
                this.b.setOnCompletionListener(new b(this));
                this.b.setOnErrorListener(new a(this));
                inputStream.close();
                this.a = 200;
                return;
            }
            fileOutputStream.write(bArr, 0, i);
        }
    }

    @Override // javax.microedition.media.Player
    public void addPlayerListener(PlayerListener playerListener) {
    }

    @Override // javax.microedition.media.Player
    public void close() throws IllegalStateException {
        try {
            stop();
        } catch (MediaException e) {
        }
        this.b.release();
        this.c.delete();
        this.a = 0;
    }

    @Override // javax.microedition.media.Player
    public void deallocate() throws IllegalStateException {
        if (this.a == 400) {
            try {
                stop();
            } catch (MediaException e) {
            }
        } else {
            this.b.reset();
            this.a = 200;
        }
    }

    @Override // javax.microedition.media.Player
    public String getContentType() {
        return null;
    }

    @Override // javax.microedition.media.Controllable
    public Control getControl(String str) {
        if (!str.equals("VolumeControl") && !str.equals("MIDIControl") && str.equals("RateControl")) {
            return null;
        }
        return null;
    }

    @Override // javax.microedition.media.Controllable
    public Control[] getControls() {
        return new Control[0];
    }

    @Override // javax.microedition.media.Player
    public long getDuration() {
        return this.b.getDuration();
    }

    @Override // javax.microedition.media.Player
    public long getMediaTime() {
        return this.b.getCurrentPosition();
    }

    @Override // javax.microedition.media.Player
    public int getState() {
        return this.a;
    }

    @Override // javax.microedition.media.Player
    public TimeBase getTimeBase() {
        throw new RuntimeException("unsupported method");
    }

    @Override // javax.microedition.media.Player
    public void prefetch() throws IllegalStateException, IOException, MediaException {
        try {
            this.b.prepare();
            this.a = 300;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override // javax.microedition.media.Player
    public void realize() throws MediaException {
    }

    @Override // javax.microedition.media.Player
    public void removePlayerListener(PlayerListener playerListener) {
    }

    @Override // javax.microedition.media.Player
    public void setLoopCount(int i) {
        if (i == 1) {
            this.b.setLooping(false);
        } else {
            this.b.setLooping(true);
        }
    }

    @Override // javax.microedition.media.Player
    public long setMediaTime(long j) throws IllegalStateException, MediaException {
        this.b.seekTo((int) j);
        return j;
    }

    @Override // javax.microedition.media.Player
    public void setTimeBase(TimeBase timeBase) throws MediaException {
        throw new RuntimeException("unsupported method");
    }

    @Override // javax.microedition.media.Player
    public void start() throws IllegalStateException, MediaException {
        if (this.a == 200 || this.a == 0) {
            throw new MediaException("Player can not be started; do prefetch.");
        }
        if (this.a == 400) {
            return;
        }
        this.b.start();
        this.a = 400;
    }

    @Override // javax.microedition.media.Player
    public void stop() throws IllegalStateException, MediaException {
        if (this.a == 300) {
            return;
        }
        if (this.a != 400) {
            throw new IllegalStateException();
        }
        try {
            this.b.pause();
            this.b.seekTo(0);
            this.a = 300;
        } catch (Exception e) {
            throw new MediaException(e.getMessage());
        }
    }
}
