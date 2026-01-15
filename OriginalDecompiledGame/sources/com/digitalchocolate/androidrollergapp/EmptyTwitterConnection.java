package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public class EmptyTwitterConnection implements ITwitterConnection {
    public static ITwitterConnection getTwitterConnection() {
        return null;
    }

    @Override // com.digitalchocolate.androidrollergapp.ITwitterConnection
    public String getPassword() {
        return null;
    }

    @Override // com.digitalchocolate.androidrollergapp.ITwitterConnection
    public String getUsername() {
        return null;
    }

    @Override // com.digitalchocolate.androidrollergapp.ITwitterConnection
    public boolean isLoggedIn() {
        return false;
    }

    @Override // com.digitalchocolate.androidrollergapp.ITwitterConnection
    public void login(String str, String str2) {
    }

    @Override // com.digitalchocolate.androidrollergapp.ITwitterConnection
    public void logout() {
    }

    @Override // com.digitalchocolate.androidrollergapp.ITwitterConnection
    public void setTwitterListener(ITwitterListener iTwitterListener) {
    }

    @Override // com.digitalchocolate.androidrollergapp.ITwitterConnection
    public void updateStatus(String str) {
    }
}
