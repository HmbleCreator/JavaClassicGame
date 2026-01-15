package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public class HTTPResponse {
    public static final int RESPONSE_CODE_ERROR = -1;
    public static final int RESPONSE_CODE_OK = 200;
    private DChocByteArray data;
    private int responseCode;

    public HTTPResponse(int i, DChocByteArray dChocByteArray) {
        this.responseCode = i;
        this.data = dChocByteArray;
    }

    public DChocByteArray getData() {
        return this.data;
    }

    public int getResponseCode() {
        return this.responseCode;
    }
}
