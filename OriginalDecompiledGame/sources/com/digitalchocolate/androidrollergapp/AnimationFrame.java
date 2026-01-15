package com.digitalchocolate.androidrollergapp;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class AnimationFrame {
    public static final int MAX_DURATION = Integer.MAX_VALUE;
    private static Hashtable smDirectGraphics;
    private static Vector smFrameBufferCacheFree;
    private CollisionBox[] mCollisionBox;
    private DChocImage mFrameBuffer;
    private int mHeight;
    private Animation mParentAnimation;
    private int[][] mRenderable;
    private int mWidth;
    private static final CollisionBox[] COLLISION_BOX_COUNT_0 = new CollisionBox[0];
    private static final int[][] RENDERABLE_COUNT_0 = new int[0][];
    private static int[] smImageCacheReturnValue = new int[2];

    public AnimationFrame(boolean z, Animation animation) {
        if (z) {
            this.mCollisionBox = COLLISION_BOX_COUNT_0;
            this.mRenderable = RENDERABLE_COUNT_0;
        }
        this.mParentAnimation = animation;
    }

    private DChocImage getNewGraphicsBuffer() {
        return new DChocImage(this.mParentAnimation.getWithoutTimelineWidth(), this.mParentAnimation.getWithoutTimelineHeight());
    }

    public void doDraw(int i, int i2, int i3) {
        IRenderingPlatform renderingPlatform = Toolkit.getRenderingPlatform();
        int pivotX = renderingPlatform.getPivotX();
        int pivotY = renderingPlatform.getPivotY();
        renderingPlatform.setPivot(i, i2);
        int length = this.mRenderable.length;
        for (int i4 = 0; i4 < length; i4++) {
            int[] iArr = this.mRenderable[i4];
            int i5 = iArr[0];
            if (i5 == 1) {
                int i6 = iArr[1];
                int i7 = iArr[2];
                DChocImage imageFromCache = DavinciUtilities.getImageFromCache(iArr[3]);
                int i8 = iArr[4];
                int i9 = (i8 & 1) != 0 ? 1 : 0;
                renderingPlatform.drawImage(imageFromCache, i6, i7, (i8 & 2) != 0 ? i9 | 2 : i9, 20);
            } else {
                renderingPlatform.setColorARGB(iArr[1]);
                if (i5 == 4) {
                    renderingPlatform.drawLine(iArr[2], iArr[3], iArr[4], iArr[5]);
                } else if (i5 == 3) {
                    renderingPlatform.drawRect(iArr[2], iArr[3], iArr[4], iArr[5]);
                } else if (i5 == 2) {
                    renderingPlatform.fillRect(iArr[2], iArr[3], iArr[4], iArr[5]);
                } else if (i5 == 5) {
                    renderingPlatform.fillEllipse(iArr[2], iArr[3], iArr[4], iArr[5]);
                }
            }
        }
        renderingPlatform.setPivot(pivotX, pivotY);
    }

    public void doDraw(Graphics graphics, int i, int i2, int i3) {
        Toolkit.getRenderingPlatform().setGraphicsContext(graphics);
        doDraw(i, i2, i3);
    }

    public void freeFrameBufferResources() {
        if (this.mFrameBuffer != null) {
            this.mFrameBuffer = null;
        }
    }

    public CollisionBox getCollisionBox(int i) {
        int length = this.mCollisionBox.length;
        do {
            length--;
            if (length < 0) {
                return null;
            }
        } while (this.mCollisionBox[length].getId() != i);
        return this.mCollisionBox[length];
    }

    public CollisionBox[] getCollisionBoxes() {
        return this.mCollisionBox;
    }

    public DChocImage getGraphicsBuffer() {
        if (this.mFrameBuffer == null) {
            this.mFrameBuffer = getNewGraphicsBuffer();
        }
        return this.mFrameBuffer;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int[][] getRenderables() {
        return this.mRenderable;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public boolean hasNestedAnimations() {
        return false;
    }

    public void loadFrameData(DataInputStream dataInputStream, boolean z, boolean z2) throws IOException {
        int[] iArr;
        short s;
        short s2;
        int i;
        int i2;
        if (z) {
            this.mWidth = dataInputStream.read();
            this.mHeight = dataInputStream.read();
        } else {
            this.mWidth = dataInputStream.readShort();
            this.mHeight = dataInputStream.readShort();
        }
        int i3 = dataInputStream.read();
        if (i3 == 0) {
            this.mCollisionBox = COLLISION_BOX_COUNT_0;
        } else {
            this.mCollisionBox = new CollisionBox[i3];
        }
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = dataInputStream.read();
            if (z) {
                s = dataInputStream.readByte();
                s2 = dataInputStream.readByte();
                i = dataInputStream.read();
                i2 = dataInputStream.read();
            } else {
                s = dataInputStream.readShort();
                s2 = dataInputStream.readShort();
                i = dataInputStream.readShort();
                i2 = dataInputStream.readShort();
            }
            int i6 = i2;
            this.mCollisionBox[i4] = new CollisionBox(s, s2, i, i6, i5);
        }
        int i7 = dataInputStream.readShort();
        if (i7 == 0) {
            this.mRenderable = RENDERABLE_COUNT_0;
        } else {
            this.mRenderable = new int[i7][];
        }
        for (int i8 = 0; i8 < i7; i8++) {
            int i9 = dataInputStream.read();
            if (i9 == 1) {
                int[] iArr2 = new int[5];
                if (z) {
                    iArr2[1] = dataInputStream.readByte();
                    iArr2[2] = dataInputStream.readByte();
                } else {
                    iArr2[1] = dataInputStream.readShort();
                    iArr2[2] = dataInputStream.readShort();
                }
                int i10 = dataInputStream.read();
                DavinciUtilities.loadImageIntoCache(dataInputStream.readInt(), dataInputStream.read(), i10 & 15, (i10 >> 4) + 1, z2, smImageCacheReturnValue);
                iArr2[3] = smImageCacheReturnValue[0];
                iArr2[4] = smImageCacheReturnValue[1];
                iArr = iArr2;
            } else {
                int i11 = (-16777216) | (dataInputStream.read() << 16) | (dataInputStream.read() << 8) | dataInputStream.read();
                int[] iArr3 = new int[6];
                if (z) {
                    iArr3[2] = dataInputStream.readByte();
                    iArr3[3] = dataInputStream.readByte();
                    if (i9 == 4) {
                        iArr3[4] = dataInputStream.readByte();
                        iArr3[5] = dataInputStream.readByte();
                    } else {
                        iArr3[4] = dataInputStream.read();
                        iArr3[5] = dataInputStream.read();
                    }
                } else {
                    for (int i12 = 0; i12 < 4; i12++) {
                        iArr3[2 + i12] = dataInputStream.readShort();
                    }
                }
                iArr3[1] = i11;
                iArr = iArr3;
            }
            iArr[0] = i9;
            this.mRenderable[i8] = iArr;
        }
    }

    public void setHeight(int i) {
        this.mHeight = i;
    }

    public void setWidth(int i) {
        this.mWidth = i;
    }
}
