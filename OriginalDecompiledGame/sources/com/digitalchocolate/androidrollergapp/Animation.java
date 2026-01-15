package com.digitalchocolate.androidrollergapp;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class Animation {
    public static final int ALIGNMENT_BOTTOM = 8;
    public static final int ALIGNMENT_HCENTER = 16;
    public static final int ALIGNMENT_LEFT = 2;
    public static final int ALIGNMENT_RIGHT = 32;
    public static final int ALIGNMENT_TOP = 1;
    public static final int ALIGNMENT_VCENTER = 4;
    private static final int[] INT_ARRAY_SIZE_1 = new int[1];
    private static final int[] MAX_DURATION_FOR_FRAME = {AnimationFrame.MAX_DURATION};
    private static AnimationFrame[] smEmptyFrame;
    private int mAlignment;
    private int mAnimationDuration;
    private boolean mContinuousNestedAnimations;
    private int mDebugRid;
    private AnimationFrame[] mFrame;
    private boolean mFrameBufferCaching = true;
    private int[] mFrameDuration;
    private int[] mFrameReference;
    private int[] mFrameStartTime;
    private int mHeight;
    private boolean mLoopPreference;
    private TimelineChannel[] mTimeline;
    private int mTimelineHeight;
    private int mTimelineWidth;
    private int mTimelineX;
    private int mTimelineY;
    private int mWidth;
    private int mX;
    private int mY;

    public Animation(int i, boolean z) throws IOException {
        boolean z2;
        int i2;
        this.mAnimationDuration = AnimationFrame.MAX_DURATION;
        if (i == -1) {
            createEmptyAnimation();
            return;
        }
        if (!z) {
            DavinciUtilities.resetLoading();
        }
        DataInputStream resourceStream = Toolkit.getResourceStream(i);
        int i3 = resourceStream.read();
        boolean z3 = (i3 & 1) == 0;
        boolean z4 = (i3 & 2) == 0;
        int i4 = resourceStream.read();
        this.mFrameDuration = new int[i4];
        this.mFrameStartTime = new int[i4];
        if (i4 > 1) {
            this.mFrameReference = new int[i4];
        }
        this.mFrame = new AnimationFrame[i4];
        this.mLoopPreference = resourceStream.read() == 1;
        resourceStream.read();
        if (z3) {
            this.mX = resourceStream.readByte();
            this.mY = resourceStream.readByte();
            this.mWidth = resourceStream.read();
            this.mHeight = resourceStream.read();
        } else {
            this.mX = resourceStream.readShort();
            this.mY = resourceStream.readShort();
            this.mWidth = resourceStream.readShort();
            this.mHeight = resourceStream.readShort();
        }
        this.mAlignment = resourceStream.read();
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = resourceStream.read();
            if (i4 > 1) {
                this.mFrameReference[i5] = i6;
            }
            if (!isFrameReference(i5)) {
                this.mFrame[i5] = new AnimationFrame(false, this);
            }
        }
        int i7 = 0;
        boolean z5 = false;
        int i8 = 0;
        while (i8 < i4) {
            int i9 = z4 ? resourceStream.read() : resourceStream.readShort() & 65535;
            if (i9 == 0) {
                i2 = AnimationFrame.MAX_DURATION;
                z2 = true;
            } else {
                int i10 = i9;
                z2 = z5;
                i2 = i10;
            }
            this.mFrameDuration[i8] = i2;
            this.mFrameStartTime[i8] = i7;
            i7 += i2;
            i8++;
            z5 = z2;
        }
        if (!z5) {
            this.mAnimationDuration = this.mFrameStartTime[i4 - 1] + this.mFrameDuration[i4 - 1];
        }
        boolean z6 = resourceStream.read() == 1;
        this.mTimelineX = this.mX;
        this.mTimelineY = this.mY;
        this.mTimelineWidth = this.mWidth;
        this.mTimelineHeight = this.mHeight;
        if (z6) {
            if (z3) {
                this.mTimelineX = resourceStream.readByte();
                this.mTimelineY = resourceStream.readByte();
                this.mTimelineWidth = resourceStream.read();
                this.mTimelineHeight = resourceStream.read();
            } else {
                this.mTimelineX = resourceStream.readShort();
                this.mTimelineY = resourceStream.readShort();
                this.mTimelineWidth = resourceStream.readShort();
                this.mTimelineHeight = resourceStream.readShort();
            }
            this.mTimeline = new TimelineChannel[6];
            for (int i11 = 0; i11 < 6; i11++) {
                this.mTimeline[i11] = new TimelineChannel(i11, getDuration());
                this.mTimeline[i11].load(resourceStream, z3);
            }
        }
        for (int i12 = 0; i12 < i4; i12++) {
            if (!isFrameReference(i12)) {
                this.mFrame[i12].loadFrameData(resourceStream, z3, z);
            }
        }
        resourceStream.close();
        if (i4 > 1) {
            for (int i13 = 0; i13 < i4; i13++) {
                int i14 = this.mFrameReference[i13];
                if (isFrameReference(i13)) {
                    this.mFrame[i13] = this.mFrame[i14];
                }
            }
        }
    }

    private void createEmptyAnimation() {
        if (smEmptyFrame == null) {
            smEmptyFrame = new AnimationFrame[1];
            smEmptyFrame[0] = new AnimationFrame(true, this);
        }
        this.mFrame = smEmptyFrame;
        this.mFrameDuration = MAX_DURATION_FOR_FRAME;
        this.mFrameReference = INT_ARRAY_SIZE_1;
        this.mFrameStartTime = INT_ARRAY_SIZE_1;
    }

    public boolean continuousNestedAnimations() {
        return this.mContinuousNestedAnimations;
    }

    public void doDraw(int i, int i2, int i3) {
        this.mFrame[getFrameInTime(i3)].doDraw(i, i2, i3);
    }

    public void doDraw(Graphics graphics, int i, int i2, int i3) {
        Toolkit.getRenderingPlatform().setGraphicsContext(graphics);
        doDraw(i, i2, i3);
    }

    public void freeResources() {
        int length = this.mFrame.length;
        while (true) {
            length--;
            if (length < 0) {
                DavinciUtilities.modRefCounters(this, -1);
                return;
            }
            this.mFrame[length].freeFrameBufferResources();
        }
    }

    public int getAlignment() {
        return this.mAlignment;
    }

    public int getDuration() {
        return this.mAnimationDuration;
    }

    public AnimationFrame getFrame(int i) {
        return this.mFrame[i];
    }

    public int getFrameColorModification(int i) {
        if (hasTimeline()) {
            return getTimelineValue(0, getFrameStartTime(i), isLoopingPreferenced());
        }
        return -8355712;
    }

    public int getFrameCount() {
        return this.mFrame.length;
    }

    public int getFrameDuration(int i) {
        return this.mFrameDuration[i];
    }

    public int getFrameInTime(int i) {
        int length = this.mFrameDuration.length;
        do {
            length--;
            if (length < 0) {
                return -1;
            }
        } while (i < this.mFrameStartTime[length]);
        return length;
    }

    public int getFrameStartTime(int i) {
        return this.mFrameStartTime[i];
    }

    public int getHeight() {
        return this.mTimelineHeight;
    }

    public int getRID() {
        return this.mDebugRid;
    }

    public int getTimelineValue(int i, int i2, boolean z) {
        return this.mTimeline[i].getValue(i2, z);
    }

    public int getWidth() {
        return this.mTimelineWidth;
    }

    public int getWithoutTimelineHeight() {
        return this.mHeight;
    }

    public int getWithoutTimelineWidth() {
        return this.mWidth;
    }

    public int getWithoutTimelineX() {
        return this.mX;
    }

    public int getWithoutTimelineY() {
        return this.mY;
    }

    public int getX() {
        return this.mTimelineX;
    }

    public int getY() {
        return this.mTimelineY;
    }

    public boolean hasNestedAnimations(int i) {
        return getFrame(i).hasNestedAnimations();
    }

    public boolean hasTimeline() {
        if (this.mTimeline != null) {
            int i = 6;
            do {
                i--;
                if (i >= 0) {
                }
            } while (this.mTimeline[i].getEventCount() <= 0);
            return true;
        }
        return false;
    }

    public boolean isFrameBufferCachingEnabled() {
        return this.mFrameBufferCaching;
    }

    public boolean isFrameReference(int i) {
        return getFrameCount() > 1 && this.mFrameReference[i] != 255;
    }

    public boolean isLoopingPreferenced() {
        return this.mLoopPreference;
    }

    public void setFrameBufferCaching(boolean z) {
        this.mFrameBufferCaching = z;
    }
}
