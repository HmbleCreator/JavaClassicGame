package com.digitalchocolate.androidrollergapp;

import java.io.DataInputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class TimelineChannel {
    private static final int[] INT_ARRAY_SIZE_0 = new int[0];
    private static final int MODE_CONSTANT = 1;
    private static final int MODE_LINEAR = 0;
    public static final int TIMELINE_COLOR_MODIFICATION = 0;
    public static final int TIMELINE_POS_ROTATION = 5;
    public static final int TIMELINE_POS_SCALE_HEIGHT = 4;
    public static final int TIMELINE_POS_SCALE_WIDTH = 3;
    public static final int TIMELINE_POS_X = 1;
    public static final int TIMELINE_POS_Y = 2;
    public static final int TIMELINE_TYPE_COUNT = 6;
    private int mDuration;
    private int[] mMode;
    private int[] mTime;
    private int mType;
    private int[] mValue;

    public TimelineChannel(int i, int i2) {
        this.mType = i;
        this.mDuration = i2;
    }

    private int getIndexInTime(int i) {
        int length = this.mTime.length;
        do {
            length--;
            if (length < 0) {
                return -1;
            }
        } while (i < this.mTime[length]);
        return length;
    }

    private int getNeutralValue() {
        if (this.mType == 0) {
            return -8355712;
        }
        return (this.mType == 3 || this.mType == 4) ? 1024 : 0;
    }

    public int getEventCount() {
        return this.mTime.length;
    }

    public int getValue(int i, boolean z) {
        int i2;
        if (this.mTime.length == 0) {
            return getNeutralValue();
        }
        int indexInTime = getIndexInTime(i);
        if (this.mMode[indexInTime] == 1) {
            return this.mValue[indexInTime];
        }
        int i3 = i - this.mTime[indexInTime];
        int i4 = indexInTime + 1;
        if (i4 != this.mTime.length) {
            i2 = this.mTime[i4] - this.mTime[indexInTime];
        } else {
            if (!z) {
                return this.mValue[indexInTime];
            }
            i4 = 0;
            i2 = this.mDuration - this.mTime[indexInTime];
        }
        if (this.mMode[indexInTime] == 0) {
            return this.mType == 0 ? DavinciUtilities.interpolateColor(this.mValue[indexInTime], this.mValue[i4], i3, i2) : DavinciUtilities.interpolateValue(this.mValue[indexInTime], this.mValue[i4], i3, i2);
        }
        return -1;
    }

    public void load(DataInputStream dataInputStream, boolean z) throws IOException {
        int i = dataInputStream.read();
        if (i == 0) {
            this.mValue = INT_ARRAY_SIZE_0;
            this.mTime = INT_ARRAY_SIZE_0;
            this.mMode = INT_ARRAY_SIZE_0;
        } else {
            this.mValue = new int[i];
            this.mTime = new int[i];
            this.mMode = new int[i];
        }
        for (int i2 = 0; i2 < i; i2++) {
            this.mTime[i2] = dataInputStream.readInt();
            this.mMode[i2] = dataInputStream.read();
            int i3 = -1;
            if (this.mType == 0 || this.mType == 3 || this.mType == 4) {
                i3 = dataInputStream.readInt();
            } else if (this.mType == 1 || this.mType == 2) {
                i3 = z ? dataInputStream.readByte() : dataInputStream.readShort();
            } else if (this.mType == 5) {
                i3 = dataInputStream.readInt() << 0;
            }
            this.mValue[i2] = i3;
        }
    }
}
