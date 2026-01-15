package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public class EmptyWcrmClient implements IWcrmClient {
    public static IWcrmClient getWcrmClient() {
        return null;
    }

    @Override // com.digitalchocolate.androidrollergapp.IWcrmClient
    public IWcrmParams createNewParams() {
        return null;
    }

    @Override // com.digitalchocolate.androidrollergapp.IWcrmClient
    public void initializeWithFbParameters(String str, String str2, String str3, int i, int i2) {
    }

    @Override // com.digitalchocolate.androidrollergapp.IWcrmClient
    public void initializeWithFbUserId(String str, int i, int i2) {
    }

    @Override // com.digitalchocolate.androidrollergapp.IWcrmClient
    public void trackEvent(String str, String str2, String str3, IWcrmParams iWcrmParams) {
    }
}
