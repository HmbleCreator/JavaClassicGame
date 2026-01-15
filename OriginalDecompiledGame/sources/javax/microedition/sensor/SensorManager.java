package javax.microedition.sensor;

import javax.microedition.midlet.MIDlet;

/* loaded from: classes.dex */
public class SensorManager {
    public static String CONTEXT_ACCELERATION = "acceleration";
    private static MIDlet a;
    private static android.hardware.SensorManager b;

    public static SensorInfo[] findSensors(String str, String str2) {
        if (str.equalsIgnoreCase(CONTEXT_ACCELERATION)) {
            MIDlet mIDletInstance = MIDlet.getMIDletInstance();
            a = mIDletInstance;
            b = (android.hardware.SensorManager) mIDletInstance.getSystemService("sensor");
            AccelerometerConnection accelerometerConnection = new AccelerometerConnection();
            if (b.registerListener(accelerometerConnection, 2)) {
                b.unregisterListener(accelerometerConnection);
                return new SensorInfo[]{new AccelerometerInfo()};
            }
        }
        return new SensorInfo[0];
    }
}
