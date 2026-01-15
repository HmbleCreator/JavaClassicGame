package com.digitalchocolate.androidrollergapp;

import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* loaded from: classes.dex */
public class SpriteObject {
    public static final boolean CACHE_GRAPHICS_BUFFERS = true;
    public static final int LOOP_ANIMATION_PREFERENCE = -2;
    public static final int LOOP_FOREVER = -1;
    private static Vector smFrameCacheFree;
    private static Vector smFrameCacheUsed;
    private int mAnimationSpeed;
    private Animation[] mAnimations;
    private Animation mCurrentAnimation;
    private int mCurrentFrameIndex;
    private int mElapsedTime;
    private boolean mForceEachFrame;
    private int mLoopCount;
    private int mLoopCounter;
    private int mLoopedCount;
    private boolean mReversed;
    private static boolean smFrameBufferCaching = true;
    public static int TIME_ACCURACY = 10;
    public static int ANIMATION_SPEED_X1 = 1 << TIME_ACCURACY;

    public SpriteObject() {
        this.mAnimationSpeed = ANIMATION_SPEED_X1;
    }

    public SpriteObject(int i) {
        this(DavinciUtilities.loadAnimation(i));
    }

    public SpriteObject(Animation animation) {
        this(new Animation[]{animation});
    }

    public SpriteObject(Animation animation, boolean z) {
        this(new Animation[]{animation}, z);
    }

    public SpriteObject(int[] iArr) {
        this(DavinciUtilities.loadAnimations(iArr));
    }

    public SpriteObject(Animation[] animationArr) {
        this.mAnimationSpeed = ANIMATION_SPEED_X1;
        set(animationArr, -2);
    }

    public SpriteObject(Animation[] animationArr, boolean z) {
        this.mAnimationSpeed = ANIMATION_SPEED_X1;
        set(animationArr, z ? -1 : 1);
    }

    public SpriteObject(Object[] objArr, boolean z) {
        this.mAnimationSpeed = ANIMATION_SPEED_X1;
        Animation[] animationArr = new Animation[objArr.length];
        int length = objArr.length;
        while (true) {
            int i = length - 1;
            if (i < 0) {
                break;
            }
            animationArr[i] = (Animation) objArr[i];
            length = i;
        }
        set(animationArr, z ? -1 : 1);
    }

    public static void disableAllEffects() {
        Toolkit.getRenderingPlatform().disableAllEffects();
    }

    private static DChocImage getFrameBufferFromCache(int i, int i2) {
        DChocImage dChocImage;
        boolean z;
        if (smFrameCacheUsed == null) {
            smFrameCacheUsed = new Vector();
            smFrameCacheFree = new Vector();
        }
        DChocImage dChocImage2 = null;
        int size = smFrameCacheFree.size();
        while (true) {
            int i3 = size - 1;
            if (i3 < 0) {
                dChocImage = dChocImage2;
                z = false;
                break;
            }
            dChocImage = (DChocImage) smFrameCacheFree.elementAt(i3);
            dChocImage.clearRegion();
            if (i <= dChocImage.getWidth() && i2 <= dChocImage.getHeight()) {
                smFrameCacheFree.removeElementAt(i3);
                z = true;
                break;
            }
            dChocImage2 = dChocImage;
            size = i3;
        }
        if (!z) {
            dChocImage = new DChocImage((i * 12) / 10, (i2 * 12) / 10);
        }
        if (i != dChocImage.getImage().getWidth() || i2 != dChocImage.getHeight()) {
            dChocImage.setRegion(0, 0, i, i2);
        }
        smFrameCacheUsed.addElement(dChocImage);
        return dChocImage;
    }

    public static boolean isFilteringEnabled() {
        return Toolkit.getRenderingPlatform().isFilterEnabled(3);
    }

    public static boolean isFrameBufferCachingEnabled() {
        return smFrameBufferCaching;
    }

    public static void releaseEffectBuffer() {
    }

    private void set(Animation[] animationArr, int i) {
        if (this.mAnimations != null) {
            freeResources();
        }
        this.mAnimations = animationArr;
        int length = animationArr.length;
        while (true) {
            length--;
            if (length < 0) {
                setAnimation(0, i, true);
                return;
            }
            DavinciUtilities.modRefCounters(animationArr[length], 1);
        }
    }

    public static void setBlur(int i, int i2) {
        Toolkit.getRenderingPlatform().setBlur(i, i2);
    }

    public static void setColorModification(int i) {
        Toolkit.getRenderingPlatform().setColorModification(i);
    }

    public static void setFiltering(boolean z) {
        Toolkit.getRenderingPlatform().setFiltering(3, z);
    }

    public static void setFrameBufferCaching(boolean z) {
        smFrameBufferCaching = z;
    }

    public static void setRenderMode(int i) {
        setRenderMode(i, null);
    }

    public static void setRenderMode(int i, DChocImage dChocImage) {
        Toolkit.getRenderingPlatform().setGraphicsContext(dChocImage);
        Toolkit.getRenderingPlatform().setRenderMode(i);
    }

    public static void setRotation(int i) {
        Toolkit.getRenderingPlatform().setRotation(i);
    }

    public static void setScale(int i, int i2) {
        Toolkit.getRenderingPlatform().setScale(i, i2);
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public SpriteObject m0clone() {
        if (this.mLoopCount == -2) {
            return new SpriteObject(this.mAnimations);
        }
        return new SpriteObject(this.mAnimations, this.mLoopCount == -1);
    }

    public void copyTo(SpriteObject spriteObject) {
        spriteObject.set(this.mAnimations, this.mLoopCount);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:85:0x023b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void draw(int r28, int r29) {
        /*
            Method dump skipped, instructions count: 646
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.digitalchocolate.androidrollergapp.SpriteObject.draw(int, int):void");
    }

    public void draw(Graphics graphics, int i, int i2) {
        Toolkit.getRenderingPlatform().setGraphicsContext(graphics);
        draw(i, i2);
    }

    public void draw(Graphics graphics, int i, int i2, int i3, int i4, int i5) {
        draw(graphics, null, 0, i, i2, i3, i4, i5, i5);
    }

    public void draw(Graphics graphics, DChocImage dChocImage, int i, int i2, int i3, int i4, int i5, int i6) {
        draw(graphics, dChocImage, i, i2, i3, i4, i5, i6, i6);
    }

    public void draw(Graphics graphics, DChocImage dChocImage, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        IRenderingPlatform renderingPlatform = Toolkit.getRenderingPlatform();
        renderingPlatform.pushParameters();
        if (dChocImage != null) {
            renderingPlatform.setGraphicsContext(dChocImage);
        } else {
            renderingPlatform.setGraphicsContext(graphics);
        }
        renderingPlatform.setColorModification(i4);
        renderingPlatform.setRotation(i5);
        renderingPlatform.setScale(i6, i7);
        renderingPlatform.setRenderMode(i);
        draw(i2, i3);
        renderingPlatform.popParameters();
    }

    public void draw(Graphics graphics, Image image, int i, int i2) {
        draw(graphics, image, i, i2);
    }

    public void drawAligned(int i, int i2, int i3, int i4) {
        int alignment = this.mCurrentAnimation.getAlignment();
        draw((alignment & 32) != 0 ? i + i3 : (alignment & 16) != 0 ? (i3 >> 1) + i : i, (alignment & 8) != 0 ? i2 + i4 : (alignment & 4) != 0 ? (i4 >> 1) + i2 : i2);
    }

    public void drawAlpha(Graphics graphics, int i, int i2, int i3) {
        draw(graphics, null, 0, i, i2, (i3 << 24) | 8421504, 0, 1024, 1024);
    }

    public void drawRotated(Graphics graphics, int i, int i2, int i3) {
        draw(graphics, null, 0, i, i2, getCurrentFrameColorModification(), i3, 1024, 1024);
    }

    public void freeResources() {
        if (this.mAnimations == null) {
            return;
        }
        int length = this.mAnimations.length;
        while (true) {
            length--;
            if (length < 0) {
                DavinciUtilities.releaseUnreferencedImages();
                this.mAnimations = null;
                return;
            }
            this.mAnimations[length].freeResources();
        }
    }

    public int getAnimationCount() {
        return this.mAnimations.length;
    }

    public int getAnimationLength() {
        return this.mCurrentAnimation.getDuration();
    }

    public CollisionBox getCollisionBox(int i) {
        return this.mCurrentAnimation.getFrame(this.mCurrentFrameIndex).getCollisionBox(i);
    }

    public CollisionBox[] getCollisionBoxes() {
        return this.mCurrentAnimation.getFrame(this.mCurrentFrameIndex).getCollisionBoxes();
    }

    public Animation getCurrentAnimationData() {
        return this.mCurrentAnimation;
    }

    public int getCurrentAnimationIndex() {
        int i = 0;
        while (this.mCurrentAnimation != this.mAnimations[i]) {
            i++;
        }
        return i;
    }

    public int getCurrentFrameAlpha() {
        return getCurrentFrameColorModification() >>> 24;
    }

    public int getCurrentFrameColorModification() {
        if (!hasAnimationTimeline()) {
            return -8355712;
        }
        return this.mCurrentAnimation.getTimelineValue(0, getElapsedTime(), isLooping());
    }

    public int getCurrentFrameIndex() {
        return this.mCurrentFrameIndex;
    }

    public int getElapsedTime() {
        return this.mCurrentAnimation.getFrameStartTime(this.mCurrentFrameIndex) + (this.mElapsedTime >> TIME_ACCURACY);
    }

    public int getFrameCount() {
        return this.mCurrentAnimation.getFrameCount();
    }

    public int getFrameHeight() {
        return getFrameHeight(this.mCurrentFrameIndex);
    }

    public int getFrameHeight(int i) {
        return this.mCurrentAnimation.getFrame(i).getHeight();
    }

    public int getFrameWidth() {
        return getFrameWidth(this.mCurrentFrameIndex);
    }

    public int getFrameWidth(int i) {
        return this.mCurrentAnimation.getFrame(i).getWidth();
    }

    public int getHeight() {
        return this.mCurrentAnimation.getHeight();
    }

    public boolean getLoopPreference() {
        return this.mCurrentAnimation.isLoopingPreferenced();
    }

    public int getLoopedCount() {
        return this.mLoopedCount;
    }

    public int getPivotX() {
        return this.mCurrentAnimation.getX();
    }

    public int getPivotY() {
        return this.mCurrentAnimation.getY();
    }

    public int getTimelineX() {
        if (hasAnimationTimeline()) {
            return this.mCurrentAnimation.getTimelineValue(1, getElapsedTime(), isLooping());
        }
        return 0;
    }

    public int getTimelineY() {
        if (hasAnimationTimeline()) {
            return this.mCurrentAnimation.getTimelineValue(2, getElapsedTime(), isLooping());
        }
        return 0;
    }

    public int getWidth() {
        return this.mCurrentAnimation.getWidth();
    }

    public boolean hasAnimationTimeline() {
        return this.mCurrentAnimation.hasTimeline();
    }

    public boolean isFinishedAnimation() {
        return this.mLoopCounter == 0;
    }

    public boolean isLooping() {
        return (this.mLoopCounter == 0 || (this.mLoopCounter == 1 && this.mCurrentFrameIndex == getFrameCount() - 1)) ? false : true;
    }

    public void logicUpdate(int i) {
        int i2;
        if (i > 0) {
            i2 = this.mAnimationSpeed * i;
            if (i2 == 0 && this.mAnimationSpeed > 0) {
                i2 = 1;
            }
        } else {
            i2 = i;
        }
        if (this.mReversed) {
            this.mElapsedTime -= i2;
            while (this.mElapsedTime < 0) {
                this.mCurrentFrameIndex--;
                if (this.mCurrentFrameIndex == -1) {
                    if (this.mLoopCounter > 0) {
                        this.mLoopCounter--;
                    }
                    if (this.mLoopCounter == 0) {
                        this.mCurrentFrameIndex = 0;
                        this.mElapsedTime = 0;
                        return;
                    } else {
                        this.mCurrentFrameIndex = this.mCurrentAnimation.getFrameCount() - 1;
                        this.mLoopedCount++;
                    }
                }
                int frameDuration = this.mCurrentAnimation.getFrameDuration(this.mCurrentFrameIndex);
                if (frameDuration != Integer.MAX_VALUE) {
                    frameDuration <<= TIME_ACCURACY;
                }
                this.mElapsedTime += frameDuration;
                if (this.mForceEachFrame) {
                    this.mElapsedTime = frameDuration - 1;
                }
            }
            return;
        }
        int frameDuration2 = this.mCurrentAnimation.getFrameDuration(this.mCurrentFrameIndex);
        int i3 = frameDuration2 << TIME_ACCURACY;
        this.mElapsedTime = i2 + this.mElapsedTime;
        int i4 = i3;
        while ((this.mElapsedTime >> TIME_ACCURACY) >= frameDuration2) {
            this.mElapsedTime -= i4;
            if (this.mForceEachFrame) {
                this.mElapsedTime = 0;
            }
            int frameCount = this.mCurrentAnimation.getFrameCount();
            this.mCurrentFrameIndex++;
            if (this.mCurrentFrameIndex >= frameCount) {
                if (this.mLoopCounter > 0) {
                    this.mLoopCounter--;
                }
                if (this.mLoopCounter == 0) {
                    this.mCurrentFrameIndex = frameCount - 1;
                    this.mElapsedTime = i4 - 1;
                    return;
                } else {
                    this.mCurrentFrameIndex = 0;
                    this.mLoopedCount++;
                }
            }
            int frameDuration3 = this.mCurrentAnimation.getFrameDuration(this.mCurrentFrameIndex);
            frameDuration2 = frameDuration3;
            i4 = frameDuration3 << TIME_ACCURACY;
        }
    }

    public void setAnimation(int i, int i2, boolean z) {
        this.mCurrentAnimation = this.mAnimations[i];
        this.mLoopCount = i2;
        this.mForceEachFrame = z;
        if (this.mReversed) {
            setAnimationFrame(getFrameCount() - 1);
        } else {
            setAnimationFrame(0);
        }
    }

    public void setAnimationFrame(int i) {
        this.mCurrentFrameIndex = i;
        this.mLoopCounter = this.mLoopCount;
        this.mLoopedCount = 0;
        if (this.mLoopCount == -2) {
            this.mLoopCounter = 1;
            if (this.mCurrentAnimation.isLoopingPreferenced()) {
                this.mLoopCounter = -1;
            }
        }
        if (this.mReversed) {
            this.mElapsedTime = (this.mCurrentAnimation.getFrameDuration(i) << TIME_ACCURACY) - 1;
        } else {
            this.mElapsedTime = 0;
        }
    }

    public void setAnimationSpeed(int i) {
        this.mAnimationSpeed = i;
    }

    public void setElapsedTime(int i) {
        int animationLength = getAnimationLength();
        int i2 = isLooping() ? i % animationLength : i >= animationLength ? animationLength - 1 : i;
        setAnimationFrame(0);
        boolean z = this.mForceEachFrame;
        int i3 = this.mAnimationSpeed;
        this.mForceEachFrame = false;
        this.mAnimationSpeed = ANIMATION_SPEED_X1;
        logicUpdate(i2);
        this.mAnimationSpeed = i3;
        this.mForceEachFrame = z;
    }

    public void setLooping(int i) {
        int elapsedTime = getElapsedTime();
        setAnimation(getCurrentAnimationIndex(), i, this.mForceEachFrame);
        setElapsedTime(elapsedTime);
    }

    public void setReversedPlayback(boolean z) {
        this.mReversed = z;
    }
}
