package javax.microedition.io;

import java.io.IOException;
import javax.microedition.midlet.MIDlet;
import javax.microedition.sensor.AccelerometerConnection;
import javax.microedition.sensor.AccelerometerInfo;

/* loaded from: classes.dex */
public class Connector {
    public static Connection open(String str) throws IOException {
        if (str.equals(AccelerometerInfo.ACCELEROMETER_URL)) {
            AccelerometerConnection accelerometerConnection = new AccelerometerConnection();
            MIDlet.getMIDletInstance().setAccelerometerConnection(accelerometerConnection);
            return accelerometerConnection;
        }
        if (str.startsWith("http:")) {
            return new HttpConnectionImpl(str);
        }
        return null;
    }
}
