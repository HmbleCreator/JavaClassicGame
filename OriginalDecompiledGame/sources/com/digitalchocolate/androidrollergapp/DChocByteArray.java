package com.digitalchocolate.androidrollergapp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

/* loaded from: classes.dex */
public class DChocByteArray implements IByteArray {
    private ByteArrayOutputStream byteArrayOut;
    private byte[] data;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private int offset;
    private boolean readMode;
    private int size;

    private DChocByteArray() {
    }

    public static DChocByteArray createByteArrayForReading(byte[] bArr, int i, int i2) {
        DChocByteArray dChocByteArray = new DChocByteArray();
        dChocByteArray.dataIn = new DataInputStream(new ByteArrayInputStream(bArr, i, i2));
        dChocByteArray.data = bArr;
        dChocByteArray.size = i2;
        dChocByteArray.offset = i;
        dChocByteArray.readMode = true;
        return dChocByteArray;
    }

    public static DChocByteArray createByteArrayForWriting() {
        DChocByteArray dChocByteArray = new DChocByteArray();
        dChocByteArray.byteArrayOut = new ByteArrayOutputStream();
        dChocByteArray.dataOut = new DataOutputStream(dChocByteArray.byteArrayOut);
        dChocByteArray.readMode = false;
        return dChocByteArray;
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public byte[] getBytes() {
        if (!this.readMode) {
            return this.byteArrayOut.toByteArray();
        }
        if (this.offset == 0 && this.size == this.data.length) {
            return this.data;
        }
        byte[] bArr = new byte[this.size];
        System.arraycopy(this.data, this.offset, bArr, 0, this.size);
        return bArr;
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public int getSize() {
        return this.readMode ? this.size : this.byteArrayOut.size();
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public boolean readBoolean() throws EOFException {
        try {
            return this.dataIn.readBoolean();
        } catch (EOFException e) {
            throw e;
        } catch (IOException e2) {
            return false;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public byte readByte() throws EOFException {
        try {
            return this.dataIn.readByte();
        } catch (EOFException e) {
            throw e;
        } catch (IOException e2) {
            return (byte) 0;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public int readInt() throws EOFException {
        try {
            return this.dataIn.readInt();
        } catch (EOFException e) {
            throw e;
        } catch (IOException e2) {
            return 0;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public short readShort() throws EOFException {
        try {
            return this.dataIn.readShort();
        } catch (EOFException e) {
            throw e;
        } catch (IOException e2) {
            return (short) 0;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public String readUTF() throws EOFException {
        try {
            return this.dataIn.readUTF();
        } catch (EOFException e) {
            throw e;
        } catch (IOException e2) {
            return null;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public int readUnsignedByte() throws EOFException {
        try {
            return this.dataIn.readUnsignedByte();
        } catch (EOFException e) {
            throw e;
        } catch (IOException e2) {
            return 0;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public int readUnsignedShort() throws EOFException {
        try {
            return this.dataIn.readUnsignedShort();
        } catch (EOFException e) {
            throw e;
        } catch (IOException e2) {
            return 0;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public void skip(int i) throws IOException {
        try {
            this.dataIn.skipBytes(i);
        } catch (IOException e) {
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public void writeBoolean(boolean z) throws IOException {
        if (this.readMode) {
            return;
        }
        try {
            this.dataOut.writeBoolean(z);
        } catch (IOException e) {
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public void writeByte(int i) throws IOException {
        if (this.readMode) {
            return;
        }
        try {
            this.dataOut.writeByte(i);
        } catch (IOException e) {
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public void writeInt(int i) throws IOException {
        if (this.readMode) {
            return;
        }
        try {
            this.dataOut.writeInt(i);
        } catch (IOException e) {
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public void writeShort(int i) throws IOException {
        if (this.readMode) {
            return;
        }
        try {
            this.dataOut.writeShort(i);
        } catch (IOException e) {
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public void writeUTF(String str) throws IOException {
        if (this.readMode) {
            return;
        }
        try {
            this.dataOut.writeUTF(str);
        } catch (IOException e) {
        }
    }
}
