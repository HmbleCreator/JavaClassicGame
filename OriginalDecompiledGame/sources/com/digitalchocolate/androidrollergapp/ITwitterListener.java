package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public interface ITwitterListener {
    public static final int EVENT_LOGIN = 0;
    public static final int EVENT_LOGOUT = 1;
    public static final int EVENT_UPDATE_STATUS = 2;
    public static final int STATUS_LOGIN_FAILED = 3;
    public static final int STATUS_NETWORK_ERROR = 1;
    public static final int STATUS_NOT_LOGGED_IN = 2;
    public static final int STATUS_OK = 0;

    void responseReceived(int i, int i2);
}
