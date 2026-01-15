package javax.microedition.sensor;

/* loaded from: classes.dex */
public class AccelerometerChannelInfo implements ChannelInfo {
    private String a;
    private MeasurementRange[] b = new MeasurementRange[1];

    public AccelerometerChannelInfo(String str) {
        this.a = str;
        this.b[0] = new MeasurementRange();
    }

    @Override // javax.microedition.sensor.ChannelInfo
    public float getAccuracy() {
        throw new RuntimeException("unsupported method");
    }

    @Override // javax.microedition.sensor.ChannelInfo
    public int getDataType() {
        return 2;
    }

    @Override // javax.microedition.sensor.ChannelInfo
    public MeasurementRange[] getMeasurementRanges() {
        return this.b;
    }

    @Override // javax.microedition.sensor.ChannelInfo
    public String getName() {
        return this.a;
    }

    @Override // javax.microedition.sensor.ChannelInfo
    public int getScale() {
        throw new RuntimeException("unsupported method");
    }

    @Override // javax.microedition.sensor.ChannelInfo
    public Unit getUnit() {
        throw new RuntimeException("unsupported method");
    }
}
