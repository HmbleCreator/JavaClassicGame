package javax.microedition.sensor;

/* loaded from: classes.dex */
public interface ConditionListener {
    void conditionMet(SensorConnection sensorConnection, Data data, Condition condition);
}
