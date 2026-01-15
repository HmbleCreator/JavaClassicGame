package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public class EmptyHTTPConnection implements IHTTPConnection {
    public static IHTTPConnection getHTTPConnection() {
        return null;
    }

    @Override // com.digitalchocolate.androidrollergapp.IHTTPConnection
    public void sendRequest(HTTPRequest hTTPRequest) {
    }

    @Override // com.digitalchocolate.androidrollergapp.IHTTPConnection
    public void setResponseListener(IHTTPResponseListener iHTTPResponseListener) {
    }
}
