package javax.microedition.media;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.midlet.MIDlet;

/* loaded from: classes.dex */
public class Manager {
    private static BasicPlayer a;

    public static Player createPlayer(InputStream inputStream, String str) throws IOException, MediaException {
        BasicPlayer basicPlayer = new BasicPlayer(inputStream);
        a = basicPlayer;
        return basicPlayer;
    }

    public static Player createPlayer(String str) throws IOException, MediaException {
        BasicPlayer basicPlayer = new BasicPlayer(MIDlet.getMIDletInstance().getClass().getResourceAsStream(str));
        a = basicPlayer;
        return basicPlayer;
    }

    public static String[] getSupportedContentTypes(String str) {
        throw new RuntimeException("method not supported");
    }

    public static String[] getSupportedProtocols(String str) {
        throw new RuntimeException("method not supported");
    }

    public static void playTone(int i, int i2, int i3) throws MediaException {
        throw new RuntimeException("method not supported");
    }
}
