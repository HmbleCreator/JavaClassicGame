package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public class FacebookGraphAPIRequest {
    private String requestConnection;
    private String requestId;

    public FacebookGraphAPIRequest(String str, String str2) {
        this.requestId = str;
        this.requestConnection = str2;
    }

    public String getRequestConnection() {
        return this.requestConnection;
    }

    public String getRequestId() {
        return this.requestId;
    }
}
