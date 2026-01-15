package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public interface IWcrmClient {
    public static final int ENVIRONMENT_DEV = 0;
    public static final int ENVIRONMENT_PRODUCTION = 2;
    public static final int ENVIRONMENT_STAGE = 1;

    IWcrmParams createNewParams();

    void initializeWithFbParameters(String str, String str2, String str3, int i, int i2);

    void initializeWithFbUserId(String str, int i, int i2);

    void trackEvent(String str, String str2, String str3, IWcrmParams iWcrmParams);
}
