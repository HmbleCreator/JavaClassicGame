package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public interface IHTTPConnection {
    void sendRequest(HTTPRequest hTTPRequest);

    void setResponseListener(IHTTPResponseListener iHTTPResponseListener);
}
