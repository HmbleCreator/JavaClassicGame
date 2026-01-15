package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public interface ITwitterConnection {
    String getPassword();

    String getUsername();

    boolean isLoggedIn();

    void login(String str, String str2);

    void logout();

    void setTwitterListener(ITwitterListener iTwitterListener);

    void updateStatus(String str);
}
