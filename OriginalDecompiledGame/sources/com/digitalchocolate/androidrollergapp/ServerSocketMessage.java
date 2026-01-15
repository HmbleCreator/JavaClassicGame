package com.digitalchocolate.androidrollergapp;

import java.io.EOFException;
import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public class ServerSocketMessage implements IByteArray {
    private static final String CHARSET = "utf-8";
    private static final int CONTINUE_MASK = 128;
    private static byte[] EMPTY_MESSAGE_BYTES = new byte[0];
    private static final int FIRST_BYTE_NEGATIVE = 64;
    private static final int FIRST_BYTE_NEGATIVE_CONTINUE = 192;
    private static final int FIRST_BYTE_POSITIVE_CONTINUE = 128;
    private static final int FIVE_BYTE_NEGATIVE_FILL = Integer.MIN_VALUE;
    private static final int FOUR_BIT_MASK = 15;
    private static final int FOUR_BYTE_NEGATIVE_FILL = -134217728;
    public static final int MAXIMUM_MESSAGE_LENGTH = 300000;
    public static final int MAXIMUM_STRING_LENGTH = 300000;
    private static final int NEEDS_FOUR_BYTES = 134217728;
    private static final int NEEDS_ONE_BYTE = 64;
    private static final int NEEDS_THREE_BYTES = 1048576;
    private static final int NEEDS_TWO_BYTES = 8192;
    private static final int NEGATIVE_MASK = 64;
    private static final int NEGATIVE_NEEDS_FOUR_BYTES = -134217728;
    private static final int NEGATIVE_NEEDS_ONE_BYTE = -64;
    private static final int NEGATIVE_NEEDS_THREE_BYTES = -1048576;
    private static final int NEGATIVE_NEEDS_TWO_BYTES = -8192;
    private static final int ONE_BYTE_NEGATIVE_FILL = -64;
    private static final int SEVEN_BIT_MASK = 127;
    private static final int SIX_BIT_MASK = 63;
    private static final int SUBSEQUENT_BYTE_CONTINUE = 128;
    private static final int THREE_BYTE_NEGATIVE_FILL = -1048576;
    private static final int TWO_BYTE_NEGATIVE_FILL = -8192;
    private byte[] messageBytes;
    private int messageType;
    private boolean readMode;
    private int initialOffset = 0;
    private int initialLength = 0;
    private int offset = 0;
    private int bitOffset = 0;
    private int proxySessionId = -1;

    private ServerSocketMessage() {
    }

    public static ServerSocketMessage createIncomingMessage(int i, byte[] bArr, int i2, int i3) {
        ServerSocketMessage serverSocketMessage = new ServerSocketMessage();
        serverSocketMessage.messageBytes = bArr;
        serverSocketMessage.initialLength = i3;
        serverSocketMessage.initialOffset = i2;
        serverSocketMessage.offset = i2;
        serverSocketMessage.readMode = true;
        serverSocketMessage.messageType = i;
        return serverSocketMessage;
    }

    public static ServerSocketMessage createOutgoingMessage(int i) {
        ServerSocketMessage serverSocketMessage = new ServerSocketMessage();
        serverSocketMessage.messageBytes = EMPTY_MESSAGE_BYTES;
        serverSocketMessage.readMode = false;
        serverSocketMessage.messageType = i;
        return serverSocketMessage;
    }

    private int decodeNextInt() {
        byte[] bArr = this.messageBytes;
        int i = this.offset;
        this.offset = i + 1;
        byte b = bArr[i];
        if ((b & 64) == 0) {
            int i2 = 0 | (b & 63);
            if ((b & 128) == 0) {
                return i2;
            }
            byte[] bArr2 = this.messageBytes;
            int i3 = this.offset;
            this.offset = i3 + 1;
            byte b2 = bArr2[i3];
            int i4 = i2 | ((b2 & 127) << 6);
            if ((b2 & 128) == 0) {
                return i4;
            }
            byte[] bArr3 = this.messageBytes;
            int i5 = this.offset;
            this.offset = i5 + 1;
            byte b3 = bArr3[i5];
            int i6 = i4 | ((b3 & 127) << 13);
            if ((b3 & 128) == 0) {
                return i6;
            }
            byte[] bArr4 = this.messageBytes;
            int i7 = this.offset;
            this.offset = i7 + 1;
            byte b4 = bArr4[i7];
            int i8 = i6 | ((b4 & 127) << 20);
            if ((b4 & 128) == 0) {
                return i8;
            }
            byte[] bArr5 = this.messageBytes;
            int i9 = this.offset;
            this.offset = i9 + 1;
            return i8 | ((bArr5[i9] & 127) << 27);
        }
        int i10 = 0 | (b & 63);
        if ((b & 128) == 0) {
            return i10 | (-64);
        }
        byte[] bArr6 = this.messageBytes;
        int i11 = this.offset;
        this.offset = i11 + 1;
        byte b5 = bArr6[i11];
        int i12 = i10 | ((b5 & 127) << 6);
        if ((b5 & 128) == 0) {
            return i12 | (-8192);
        }
        byte[] bArr7 = this.messageBytes;
        int i13 = this.offset;
        this.offset = i13 + 1;
        byte b6 = bArr7[i13];
        int i14 = i12 | ((b6 & 127) << 13);
        if ((b6 & 128) == 0) {
            return i14 | (-1048576);
        }
        byte[] bArr8 = this.messageBytes;
        int i15 = this.offset;
        this.offset = i15 + 1;
        byte b7 = bArr8[i15];
        int i16 = i14 | ((b7 & 127) << 20);
        if ((b7 & 128) == 0) {
            return i16 | (-134217728);
        }
        byte[] bArr9 = this.messageBytes;
        int i17 = this.offset;
        this.offset = i17 + 1;
        return i16 | ((bArr9[i17] & 127) << 27) | Integer.MIN_VALUE;
    }

    private String decodeNextString() {
        int iDecodeNextInt = decodeNextInt();
        if (iDecodeNextInt < 0) {
            throw new RuntimeException("Negative string length encountered.");
        }
        if (this.offset + iDecodeNextInt > 300000) {
            throw new RuntimeException("Too long string in message. Offset goes over limit for message length.");
        }
        try {
            String str = new String(this.messageBytes, this.offset, iDecodeNextInt, CHARSET);
            this.offset = iDecodeNextInt + this.offset;
            return str;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    private void encodeInt(int i) {
        if (i < 0) {
            if (i > -64) {
                ensureCapacity(1);
                byte[] bArr = this.messageBytes;
                int i2 = this.offset;
                this.offset = i2 + 1;
                bArr[i2] = (byte) ((i & 63) | 64);
                return;
            }
            if (i > -8192) {
                ensureCapacity(2);
                byte[] bArr2 = this.messageBytes;
                int i3 = this.offset;
                this.offset = i3 + 1;
                bArr2[i3] = (byte) ((i & 63) | 192);
                byte[] bArr3 = this.messageBytes;
                int i4 = this.offset;
                this.offset = i4 + 1;
                bArr3[i4] = (byte) ((i >> 6) & 127);
                return;
            }
            if (i > -1048576) {
                ensureCapacity(3);
                byte[] bArr4 = this.messageBytes;
                int i5 = this.offset;
                this.offset = i5 + 1;
                bArr4[i5] = (byte) ((i & 63) | 192);
                byte[] bArr5 = this.messageBytes;
                int i6 = this.offset;
                this.offset = i6 + 1;
                bArr5[i6] = (byte) (((i >> 6) & 127) | 128);
                byte[] bArr6 = this.messageBytes;
                int i7 = this.offset;
                this.offset = i7 + 1;
                bArr6[i7] = (byte) ((i >> 13) & 127);
                return;
            }
            if (i > -134217728) {
                ensureCapacity(4);
                byte[] bArr7 = this.messageBytes;
                int i8 = this.offset;
                this.offset = i8 + 1;
                bArr7[i8] = (byte) ((i & 63) | 192);
                byte[] bArr8 = this.messageBytes;
                int i9 = this.offset;
                this.offset = i9 + 1;
                bArr8[i9] = (byte) (((i >> 6) & 127) | 128);
                byte[] bArr9 = this.messageBytes;
                int i10 = this.offset;
                this.offset = i10 + 1;
                bArr9[i10] = (byte) (((i >> 13) & 127) | 128);
                byte[] bArr10 = this.messageBytes;
                int i11 = this.offset;
                this.offset = i11 + 1;
                bArr10[i11] = (byte) ((i >> 20) & 127);
                return;
            }
            ensureCapacity(5);
            byte[] bArr11 = this.messageBytes;
            int i12 = this.offset;
            this.offset = i12 + 1;
            bArr11[i12] = (byte) ((i & 63) | 192);
            byte[] bArr12 = this.messageBytes;
            int i13 = this.offset;
            this.offset = i13 + 1;
            bArr12[i13] = (byte) (((i >> 6) & 127) | 128);
            byte[] bArr13 = this.messageBytes;
            int i14 = this.offset;
            this.offset = i14 + 1;
            bArr13[i14] = (byte) (((i >> 13) & 127) | 128);
            byte[] bArr14 = this.messageBytes;
            int i15 = this.offset;
            this.offset = i15 + 1;
            bArr14[i15] = (byte) (((i >> 20) & 127) | 128);
            byte[] bArr15 = this.messageBytes;
            int i16 = this.offset;
            this.offset = i16 + 1;
            bArr15[i16] = (byte) ((i >> 27) & 15);
            return;
        }
        if (i < 64) {
            ensureCapacity(1);
            byte[] bArr16 = this.messageBytes;
            int i17 = this.offset;
            this.offset = i17 + 1;
            bArr16[i17] = (byte) (i & 63);
            return;
        }
        if (i < 8192) {
            ensureCapacity(2);
            byte[] bArr17 = this.messageBytes;
            int i18 = this.offset;
            this.offset = i18 + 1;
            bArr17[i18] = (byte) ((i & 63) | 128);
            byte[] bArr18 = this.messageBytes;
            int i19 = this.offset;
            this.offset = i19 + 1;
            bArr18[i19] = (byte) ((i >> 6) & 127);
            return;
        }
        if (i < 1048576) {
            ensureCapacity(3);
            byte[] bArr19 = this.messageBytes;
            int i20 = this.offset;
            this.offset = i20 + 1;
            bArr19[i20] = (byte) ((i & 63) | 128);
            byte[] bArr20 = this.messageBytes;
            int i21 = this.offset;
            this.offset = i21 + 1;
            bArr20[i21] = (byte) (((i >> 6) & 127) | 128);
            byte[] bArr21 = this.messageBytes;
            int i22 = this.offset;
            this.offset = i22 + 1;
            bArr21[i22] = (byte) ((i >> 13) & 127);
            return;
        }
        if (i < NEEDS_FOUR_BYTES) {
            ensureCapacity(4);
            byte[] bArr22 = this.messageBytes;
            int i23 = this.offset;
            this.offset = i23 + 1;
            bArr22[i23] = (byte) ((i & 63) | 128);
            byte[] bArr23 = this.messageBytes;
            int i24 = this.offset;
            this.offset = i24 + 1;
            bArr23[i24] = (byte) (((i >> 6) & 127) | 128);
            byte[] bArr24 = this.messageBytes;
            int i25 = this.offset;
            this.offset = i25 + 1;
            bArr24[i25] = (byte) (((i >> 13) & 127) | 128);
            byte[] bArr25 = this.messageBytes;
            int i26 = this.offset;
            this.offset = i26 + 1;
            bArr25[i26] = (byte) ((i >> 20) & 127);
            return;
        }
        ensureCapacity(5);
        byte[] bArr26 = this.messageBytes;
        int i27 = this.offset;
        this.offset = i27 + 1;
        bArr26[i27] = (byte) ((i & 63) | 128);
        byte[] bArr27 = this.messageBytes;
        int i28 = this.offset;
        this.offset = i28 + 1;
        bArr27[i28] = (byte) (((i >> 6) & 127) | 128);
        byte[] bArr28 = this.messageBytes;
        int i29 = this.offset;
        this.offset = i29 + 1;
        bArr28[i29] = (byte) (((i >> 13) & 127) | 128);
        byte[] bArr29 = this.messageBytes;
        int i30 = this.offset;
        this.offset = i30 + 1;
        bArr29[i30] = (byte) (((i >> 20) & 127) | 128);
        byte[] bArr30 = this.messageBytes;
        int i31 = this.offset;
        this.offset = i31 + 1;
        bArr30[i31] = (byte) ((i >> 27) & 15);
    }

    private void encodeString(String str) throws UnsupportedEncodingException {
        if (str == null) {
            encodeInt(0);
            return;
        }
        byte[] bytes = null;
        try {
            bytes = str.getBytes(CHARSET);
        } catch (UnsupportedEncodingException e) {
        }
        int length = bytes.length;
        encodeInt(length);
        ensureCapacity(length);
        System.arraycopy(bytes, 0, this.messageBytes, this.offset, length);
        this.offset += length;
        this.bitOffset = 0;
    }

    private void ensureCapacity(int i) {
        if (this.offset + i > this.messageBytes.length) {
            byte[] bArr = this.messageBytes;
            this.messageBytes = new byte[this.messageBytes.length + i + getCapacityIncrement()];
            System.arraycopy(bArr, 0, this.messageBytes, 0, bArr.length);
        }
    }

    private int getCapacityIncrement() {
        return 100;
    }

    private int getEncodingLength() {
        return this.offset;
    }

    private byte[] getMessageBytes() {
        if (this.messageBytes == null) {
            this.messageBytes = EMPTY_MESSAGE_BYTES;
        } else {
            packMessageBytes();
        }
        return this.messageBytes;
    }

    private void packMessageBytes() {
        if (this.messageBytes.length > getEncodingLength()) {
            byte[] bArr = new byte[getEncodingLength()];
            System.arraycopy(this.messageBytes, 0, bArr, 0, getEncodingLength());
            this.messageBytes = bArr;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public byte[] getBytes() {
        if (!this.readMode) {
            return getMessageBytes();
        }
        byte[] bArr = new byte[this.initialLength];
        System.arraycopy(this.messageBytes, this.initialOffset, bArr, 0, this.initialLength);
        return bArr;
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public int getSize() {
        return this.readMode ? this.initialLength : getEncodingLength();
    }

    public int getType() {
        return this.messageType;
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public boolean readBoolean() {
        throw new RuntimeException("Not supported (yet).");
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public byte readByte() {
        throw new RuntimeException("Not supported.");
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public int readInt() {
        return decodeNextInt();
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public short readShort() throws EOFException {
        throw new RuntimeException("Not supported.");
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public String readUTF() {
        return decodeNextString();
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public int readUnsignedByte() throws EOFException {
        throw new RuntimeException("Not supported.");
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public int readUnsignedShort() throws EOFException {
        throw new RuntimeException("Not supported.");
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public void skip(int i) {
        throw new RuntimeException("Not supported.");
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public void writeBoolean(boolean z) {
        throw new RuntimeException("Not supported (yet).");
    }

    public void writeByte(byte b) {
        throw new RuntimeException("Not supported.");
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public void writeByte(int i) {
        throw new RuntimeException("Not supported.");
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public void writeInt(int i) {
        encodeInt(i);
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public void writeShort(int i) {
        throw new RuntimeException("Not supported.");
    }

    @Override // com.digitalchocolate.androidrollergapp.IByteArray
    public void writeUTF(String str) throws UnsupportedEncodingException {
        encodeString(str);
    }
}
