package com.digitalchocolate.androidrollergapp;

import java.util.Hashtable;

/* loaded from: classes.dex */
public class HTTPRequest {
    private DChocByteArray data;
    private Hashtable headers = new Hashtable();
    private String url;

    public HTTPRequest(String str) {
        this.url = str;
    }

    public DChocByteArray getData() {
        return this.data;
    }

    public Hashtable getHeaders() {
        return this.headers;
    }

    public String getURL() {
        return this.url;
    }

    public void setData(DChocByteArray dChocByteArray) {
        this.data = dChocByteArray;
    }

    public void setHeaderField(String str, String str2) {
        this.headers.put(str, str2);
    }
}
