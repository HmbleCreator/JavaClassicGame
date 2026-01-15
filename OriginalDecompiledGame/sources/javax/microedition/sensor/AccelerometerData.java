package javax.microedition.sensor;

/* loaded from: classes.dex */
public class AccelerometerData implements Data {
    private int[] a = new int[1];
    private ChannelInfo b;

    public AccelerometerData(ChannelInfo channelInfo) {
        this.b = channelInfo;
    }

    @Override // javax.microedition.sensor.Data
    public ChannelInfo getChannelInfo() {
        return this.b;
    }

    @Override // javax.microedition.sensor.Data
    public double[] getDoubleValues() {
        throw new RuntimeException("unsupported method");
    }

    @Override // javax.microedition.sensor.Data
    public int[] getIntValues() {
        return this.a;
    }

    @Override // javax.microedition.sensor.Data
    public Object[] getObjectValues() {
        throw new RuntimeException("unsupported method");
    }

    @Override // javax.microedition.sensor.Data
    public long getTimestamp(int i) {
        throw new RuntimeException("unsupported method");
    }

    @Override // javax.microedition.sensor.Data
    public float getUncertainty(int i) {
        throw new RuntimeException("unsupported method");
    }

    @Override // javax.microedition.sensor.Data
    public boolean isValid(int i) {
        throw new RuntimeException("unsupported method");
    }

    @Override // javax.microedition.sensor.Data
    public void setData(double[] dArr) {
        this.a[0] = (int) dArr[0];
    }
}
