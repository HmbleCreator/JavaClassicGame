package javax.microedition.sensor;

/* loaded from: classes.dex */
public interface DataListener {
    void dataReceived(SensorConnection sensorConnection, Data[] dataArr, boolean z);
}
