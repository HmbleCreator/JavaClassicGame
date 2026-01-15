package javax.microedition.sensor;

import java.io.IOException;

/* loaded from: classes.dex */
public interface SensorConnection {
    public static final int STATE_CLOSED = 4;
    public static final int STATE_LISTENING = 2;
    public static final int STATE_OPENED = 1;

    Channel getChannel(ChannelInfo channelInfo);

    Data[] getData(int i) throws IOException;

    Data[] getData(int i, long j, boolean z, boolean z2, boolean z3) throws IOException;

    SensorInfo getSensorInfo();

    int getState();

    void removeDataListener();

    void setDataListener(DataListener dataListener, int i);

    void setDataListener(DataListener dataListener, int i, long j, boolean z, boolean z2, boolean z3);
}
