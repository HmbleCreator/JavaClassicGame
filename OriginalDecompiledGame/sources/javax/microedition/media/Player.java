package javax.microedition.media;

/* loaded from: classes.dex */
public interface Player extends Controllable {
    public static final int CLOSED = 0;
    public static final int PREFETCHED = 300;
    public static final int REALIZED = 200;
    public static final int STARTED = 400;
    public static final int TIME_UNKNOWN = -1;
    public static final int UNREALIZED = 100;

    void addPlayerListener(PlayerListener playerListener);

    void close();

    void deallocate();

    String getContentType();

    long getDuration();

    long getMediaTime();

    int getState();

    TimeBase getTimeBase();

    void prefetch() throws MediaException;

    void realize() throws MediaException;

    void removePlayerListener(PlayerListener playerListener);

    void setLoopCount(int i);

    long setMediaTime(long j) throws MediaException;

    void setTimeBase(TimeBase timeBase) throws MediaException;

    void start() throws MediaException;

    void stop() throws MediaException;
}
