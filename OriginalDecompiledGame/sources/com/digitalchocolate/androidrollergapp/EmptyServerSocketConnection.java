package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public class EmptyServerSocketConnection implements IServerSocketConnection {
    public static IServerSocketConnection getServerSocketConnection() {
        return null;
    }

    @Override // com.digitalchocolate.androidrollergapp.IServerSocketConnection
    public void connect(String str, int i) {
    }

    @Override // com.digitalchocolate.androidrollergapp.IServerSocketConnection
    public void disconnect() {
    }

    @Override // com.digitalchocolate.androidrollergapp.IServerSocketConnection
    public int getConnectionState() {
        return -1;
    }

    @Override // com.digitalchocolate.androidrollergapp.IServerSocketConnection
    public ServerSocketMessage getMessage() {
        return null;
    }

    @Override // com.digitalchocolate.androidrollergapp.IServerSocketConnection
    public boolean isMessageAvailable() {
        return false;
    }

    @Override // com.digitalchocolate.androidrollergapp.IServerSocketConnection
    public boolean sendMessage(ServerSocketMessage serverSocketMessage) {
        return false;
    }
}
