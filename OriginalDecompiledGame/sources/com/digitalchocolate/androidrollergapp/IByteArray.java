package com.digitalchocolate.androidrollergapp;

import java.io.EOFException;

/* loaded from: classes.dex */
public interface IByteArray {
    byte[] getBytes();

    int getSize();

    boolean readBoolean() throws EOFException;

    byte readByte() throws EOFException;

    int readInt() throws EOFException;

    short readShort() throws EOFException;

    String readUTF() throws EOFException;

    int readUnsignedByte() throws EOFException;

    int readUnsignedShort() throws EOFException;

    void skip(int i);

    void writeBoolean(boolean z);

    void writeByte(int i);

    void writeInt(int i);

    void writeShort(int i);

    void writeUTF(String str);
}
