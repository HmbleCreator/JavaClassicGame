package javax.microedition.sensor;

/* loaded from: classes.dex */
public class AccelerometerInfo implements SensorInfo {
    public static String ACCELEROMETER_URL = "sensor:android_accelerometer";
    private static AccelerometerChannelInfo[] a;

    public AccelerometerInfo() {
        AccelerometerChannelInfo[] accelerometerChannelInfoArr = new AccelerometerChannelInfo[3];
        a = accelerometerChannelInfoArr;
        accelerometerChannelInfoArr[0] = new AccelerometerChannelInfo("x");
        a[1] = new AccelerometerChannelInfo("y");
        a[2] = new AccelerometerChannelInfo("z");
    }

    @Override // javax.microedition.sensor.SensorInfo
    public ChannelInfo[] getChannelInfos() {
        return a;
    }

    @Override // javax.microedition.sensor.SensorInfo
    public int getConnectionType() {
        return 1;
    }

    @Override // javax.microedition.sensor.SensorInfo
    public String getContextType() {
        return SensorManager.CONTEXT_ACCELERATION;
    }

    @Override // javax.microedition.sensor.SensorInfo
    public String getDescription() {
        return "Description is not available.";
    }

    @Override // javax.microedition.sensor.SensorInfo
    public int getMaxBufferSize() {
        throw new RuntimeException("unsupported method");
    }

    @Override // javax.microedition.sensor.SensorInfo
    public String getModel() {
        return "android_accelerometer";
    }

    @Override // javax.microedition.sensor.SensorInfo
    public Object getProperty(String str) {
        throw new RuntimeException("unsupported method");
    }

    @Override // javax.microedition.sensor.SensorInfo
    public String[] getPropertyNames() {
        throw new RuntimeException("unsupported method");
    }

    @Override // javax.microedition.sensor.SensorInfo
    public String getQuantity() {
        return SensorManager.CONTEXT_ACCELERATION;
    }

    @Override // javax.microedition.sensor.SensorInfo
    public String getUrl() {
        return ACCELEROMETER_URL;
    }

    @Override // javax.microedition.sensor.SensorInfo
    public boolean isAvailabilityPushSupported() {
        return false;
    }

    @Override // javax.microedition.sensor.SensorInfo
    public boolean isAvailable() {
        return true;
    }

    @Override // javax.microedition.sensor.SensorInfo
    public boolean isConditionPushSupported() {
        return false;
    }
}
