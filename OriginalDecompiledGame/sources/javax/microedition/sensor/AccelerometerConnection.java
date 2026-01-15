package javax.microedition.sensor;

import android.hardware.SensorListener;
import java.io.IOException;
import javax.microedition.io.Connection;
import javax.microedition.midlet.MIDlet;

/* loaded from: classes.dex */
public class AccelerometerConnection implements SensorListener, Connection, SensorConnection {
    private static android.hardware.SensorManager a;
    private static int i = 1000;
    private DataListener b;
    private Data[] c;
    private double[] d = new double[1];
    private double[] e = new double[1];
    private double[] f = new double[1];
    private SensorInfo g;
    private int h;

    public AccelerometerConnection() {
        a = (android.hardware.SensorManager) MIDlet.getMIDletInstance().getSystemService("sensor");
        this.g = new AccelerometerInfo();
        this.c = new Data[]{new AccelerometerData(this.g.getChannelInfos()[0]), new AccelerometerData(this.g.getChannelInfos()[1]), new AccelerometerData(this.g.getChannelInfos()[2])};
        this.h = 1;
    }

    @Override // javax.microedition.io.Connection
    public void close() throws IOException {
        a.unregisterListener(this);
        this.h = 4;
    }

    @Override // javax.microedition.sensor.SensorConnection
    public Channel getChannel(ChannelInfo channelInfo) {
        throw new RuntimeException("unsupported method");
    }

    @Override // javax.microedition.sensor.SensorConnection
    public Data[] getData(int i2) throws IOException {
        throw new RuntimeException("unsupported method");
    }

    @Override // javax.microedition.sensor.SensorConnection
    public Data[] getData(int i2, long j, boolean z, boolean z2, boolean z3) throws IOException {
        throw new RuntimeException("unsupported method");
    }

    public DataListener getDataListener() {
        return this.b;
    }

    @Override // javax.microedition.sensor.SensorConnection
    public SensorInfo getSensorInfo() {
        return this.g;
    }

    @Override // javax.microedition.sensor.SensorConnection
    public int getState() {
        return this.h;
    }

    @Override // android.hardware.SensorListener
    public void onAccuracyChanged(int i2, int i3) {
    }

    @Override // android.hardware.SensorListener
    public void onSensorChanged(int i2, float[] fArr) {
        if (i2 != 2 || this.b == null) {
            return;
        }
        this.d[0] = -((fArr[0] * i) / 9.80665f);
        this.e[0] = -((fArr[1] * i) / 9.80665f);
        this.f[0] = -((fArr[2] * i) / 9.80665f);
        this.c[0].setData(this.d);
        this.c[1].setData(this.e);
        this.c[2].setData(this.f);
        this.b.dataReceived(this, this.c, false);
    }

    @Override // javax.microedition.sensor.SensorConnection
    public void removeDataListener() {
        if (this.h == 2) {
            a.unregisterListener(this);
            this.h = 1;
        }
    }

    @Override // javax.microedition.sensor.SensorConnection
    public void setDataListener(DataListener dataListener, int i2) {
        this.b = dataListener;
        a.registerListener(this, 2, 1);
        this.h = 2;
    }

    @Override // javax.microedition.sensor.SensorConnection
    public void setDataListener(DataListener dataListener, int i2, long j, boolean z, boolean z2, boolean z3) {
        throw new RuntimeException("unsupported method");
    }
}
