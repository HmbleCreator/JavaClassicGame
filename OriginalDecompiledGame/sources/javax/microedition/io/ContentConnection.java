package javax.microedition.io;

/* loaded from: classes.dex */
public interface ContentConnection extends StreamConnection {
    String getEncoding();

    long getLength();

    String getType();
}
