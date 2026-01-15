package javax.microedition.sensor;

/* loaded from: classes.dex */
public interface Channel {
    void addCondition(ConditionListener conditionListener, Condition condition);

    ChannelInfo getChannelInfo();

    String getChannelUrl();

    Condition[] getConditions(ConditionListener conditionListener);

    void removeAllConditions();

    void removeCondition(ConditionListener conditionListener, Condition condition);

    void removeConditionListener(ConditionListener conditionListener);
}
