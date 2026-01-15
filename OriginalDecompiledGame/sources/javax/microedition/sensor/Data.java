package javax.microedition.sensor;

/* loaded from: classes.dex */
public interface Data {
    ChannelInfo getChannelInfo();

    double[] getDoubleValues();

    int[] getIntValues();

    Object[] getObjectValues();

    long getTimestamp(int i);

    float getUncertainty(int i);

    boolean isValid(int i);

    void setData(double[] dArr);
}
