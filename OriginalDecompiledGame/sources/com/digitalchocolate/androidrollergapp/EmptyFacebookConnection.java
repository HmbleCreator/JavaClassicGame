package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public class EmptyFacebookConnection implements IFacebookConnection {
    public static IFacebookConnection getFacebookConnection(String str) {
        return null;
    }

    @Override // com.digitalchocolate.androidrollergapp.IFacebookConnection
    public void getFriends() {
    }

    @Override // com.digitalchocolate.androidrollergapp.IFacebookConnection
    public boolean isAlreadyLoggedIn() {
        return false;
    }

    @Override // com.digitalchocolate.androidrollergapp.IFacebookConnection
    public void login() {
    }

    @Override // com.digitalchocolate.androidrollergapp.IFacebookConnection
    public void login(String[] strArr) {
    }

    @Override // com.digitalchocolate.androidrollergapp.IFacebookConnection
    public void setFacebookResponseListener(IFaceBookResponseListener iFaceBookResponseListener) {
    }
}
