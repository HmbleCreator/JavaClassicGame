package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public interface IServerSocketConnection {
    public static final int CONNECTION_STATE_CONNECTED = 1;
    public static final int CONNECTION_STATE_CONNECTING = 0;
    public static final int CONNECTION_STATE_DISCONNECTED = -1;

    void connect(String str, int i);

    void disconnect();

    int getConnectionState();

    ServerSocketMessage getMessage();

    boolean isMessageAvailable();

    boolean sendMessage(ServerSocketMessage serverSocketMessage);
}
