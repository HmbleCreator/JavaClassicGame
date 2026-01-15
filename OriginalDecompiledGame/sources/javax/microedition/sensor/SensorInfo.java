package javax.microedition.sensor;

/* loaded from: classes.dex */
public interface SensorInfo {
    public static final int CONN_EMBEDDED = 1;
    public static final int CONN_REMOTE = 2;
    public static final int CONN_SHORT_RANGE_WIRELESS = 4;
    public static final int CONN_WIRED = 8;

    ChannelInfo[] getChannelInfos();

    int getConnectionType();

    String getContextType();

    String getDescription();

    int getMaxBufferSize();

    String getModel();

    Object getProperty(String str);

    String[] getPropertyNames();

    String getQuantity();

    String getUrl();

    boolean isAvailabilityPushSupported();

    boolean isAvailable();

    boolean isConditionPushSupported();
}
