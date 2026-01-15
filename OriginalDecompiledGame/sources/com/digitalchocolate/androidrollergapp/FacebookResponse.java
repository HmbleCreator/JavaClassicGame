package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public class FacebookResponse {
    public static final int RESPONSE_ERROR = -1;
    public static final int RESPONSE_OK = 0;
    private FacebookGraphAPIRequest request;
    private String responseDataString;
    private int responseState;

    public FacebookResponse(FacebookGraphAPIRequest facebookGraphAPIRequest, int i) {
        this.request = facebookGraphAPIRequest;
        this.responseState = i;
    }

    public FacebookGraphAPIRequest getRequest() {
        return this.request;
    }

    public int getResponseState() {
        return this.responseState;
    }

    public String getResponseString() {
        return this.responseDataString;
    }

    public void setResponseString(String str) {
        this.responseDataString = str;
    }
}
