package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public interface IFacebookConnection {
    void getFriends();

    boolean isAlreadyLoggedIn();

    void login();

    void login(String[] strArr);

    void setFacebookResponseListener(IFaceBookResponseListener iFaceBookResponseListener);
}
