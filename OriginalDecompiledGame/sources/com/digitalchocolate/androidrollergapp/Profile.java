package com.digitalchocolate.androidrollergapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;

/* loaded from: classes.dex */
public class Profile {
    public static final int HEAVY_CAR = 2;
    public static final int[] HISCORE_POINTS = {378000, 343000, 293000, 260000, 213000, 164000, 117000, 77000, 32000, 10000, 0};
    public static final int LIGHT_CAR = 4;
    public static final int NORMAL_CAR = 1;
    public static final int NUM_AWARDS = 21;
    public static final int NUM_THEMES = 9;
    public static final int NUM_TOTAL_SMILEYS = 495;
    public static final int NUM_TOTAL_TRACKS = 99;
    public static final int NUM_TOTAL_TROPHIES = 297;
    public static final int NUM_TRACKS = 11;
    public static final int NUM_TROPHIES_PER_TRACK = 3;
    public static final byte PERFECT_PRIZE = 4;
    public static final byte SAFE_PRIZE = 2;
    public static final byte SMILEY3_PRIZE = 1;
    public boolean mAllSmileys;
    public boolean[] mAwardsUnlocked;
    public byte mCartUnlocked;
    public int mCrashed;
    public int mDistance;
    public boolean mEndShown;
    public boolean mFirstTimePreview;
    public boolean mFirstTimePreviewCarts;
    public boolean mIsFirstTime;
    public int mLastUnlockedTheme;
    public int mLastUnlockedTrack;
    public int mLevelsUnlocked;
    public boolean mNewLevel;
    public boolean mNewThemeUnlocked;
    public int mNumExciters;
    public int mNumGod;
    public int mNumNitros;
    public int mNumNoBraking;
    public int mNumStickies;
    public int mNumWinged;
    public int mPlayTime;
    public byte[] mPrizesUnlocked;
    public int mSelectedTheme;
    public int mSelectedTrack;
    public boolean mSurvival;
    public boolean[] mThemeUnlocked;
    public boolean mTournament;
    public boolean[][] mTrackBrake;
    public int[][] mTrackRides;
    public int[][] mTrackScore;
    public boolean[][] mTrackUnlocked;

    public int getSmileys(int i) {
        int i2 = (i / 11) % 9;
        int i3 = (((this.mTrackScore[i2][i - (i2 * 11)] * 20) * 4) / (ResultsTable.TOP_SCORE_VALUES[i] * 100)) / 20;
        return ((this.mPrizesUnlocked[i] & 1) == 0 || (this.mPrizesUnlocked[i] & 2) == 0) ? i3 : i3 + 1;
    }

    public int getTotalScore() {
        int i = 0;
        int i2 = 0;
        while (i < 9) {
            int i3 = i2;
            for (int i4 = 0; i4 < 11; i4++) {
                i3 += this.mTrackScore[i][i4];
            }
            i++;
            i2 = i3;
        }
        return i2;
    }

    public void loadProfile(DataInputStream dataInputStream) throws IOException {
        this.mIsFirstTime = dataInputStream.readBoolean();
        this.mThemeUnlocked = new boolean[9];
        this.mTrackUnlocked = (boolean[][]) Array.newInstance((Class<?>) Boolean.TYPE, 9, 11);
        this.mTrackRides = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 9, 11);
        this.mTrackScore = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 9, 11);
        this.mPrizesUnlocked = new byte[99];
        this.mTrackBrake = (boolean[][]) Array.newInstance((Class<?>) Boolean.TYPE, 9, 11);
        for (int i = 0; i < 9; i++) {
            this.mThemeUnlocked[i] = dataInputStream.readBoolean();
            for (int i2 = 0; i2 < 11; i2++) {
                this.mTrackUnlocked[i][i2] = dataInputStream.readBoolean();
                this.mTrackScore[i][i2] = dataInputStream.readInt();
                this.mTrackRides[i][i2] = dataInputStream.readInt();
                this.mPrizesUnlocked[(i * 11) + i2] = dataInputStream.readByte();
                this.mTrackBrake[i][i2] = dataInputStream.readBoolean();
            }
        }
        this.mLevelsUnlocked = dataInputStream.readInt();
        this.mAwardsUnlocked = new boolean[21];
        for (int i3 = 0; i3 < 21; i3++) {
            this.mAwardsUnlocked[i3] = dataInputStream.readBoolean();
        }
        this.mCrashed = dataInputStream.readInt();
        this.mDistance = dataInputStream.readInt();
        this.mPlayTime = dataInputStream.readInt();
        this.mSelectedTheme = dataInputStream.readInt();
        this.mSelectedTrack = dataInputStream.readInt();
        this.mLastUnlockedTheme = dataInputStream.readInt();
        this.mLastUnlockedTrack = dataInputStream.readInt();
        this.mCartUnlocked = dataInputStream.readByte();
        this.mNumExciters = dataInputStream.readInt();
        this.mNumStickies = dataInputStream.readInt();
        this.mNumNitros = dataInputStream.readInt();
        this.mNumExciters = dataInputStream.readInt();
        this.mNumGod = dataInputStream.readInt();
        this.mNumWinged = dataInputStream.readInt();
        this.mNumNoBraking = dataInputStream.readInt();
        this.mFirstTimePreviewCarts = dataInputStream.readBoolean();
        this.mFirstTimePreview = dataInputStream.readBoolean();
        this.mEndShown = dataInputStream.readBoolean();
        this.mAllSmileys = dataInputStream.readBoolean();
        this.mSurvival = dataInputStream.readBoolean();
        this.mTournament = dataInputStream.readBoolean();
    }

    public void resetProfile() {
        this.mIsFirstTime = true;
        this.mThemeUnlocked = new boolean[9];
        this.mTrackUnlocked = (boolean[][]) Array.newInstance((Class<?>) Boolean.TYPE, 9, 11);
        this.mTrackScore = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 9, 11);
        this.mTrackRides = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 9, 11);
        this.mPrizesUnlocked = new byte[99];
        this.mTrackBrake = (boolean[][]) Array.newInstance((Class<?>) Boolean.TYPE, 9, 11);
        for (int i = 0; i < 9; i++) {
            this.mThemeUnlocked[i] = false;
            for (int i2 = 0; i2 < 11; i2++) {
                this.mTrackUnlocked[i][i2] = false;
                this.mTrackScore[i][i2] = 0;
                this.mTrackRides[i][i2] = 0;
                this.mPrizesUnlocked[(i * 11) + i2] = 0;
                this.mTrackBrake[i][i2] = false;
            }
        }
        this.mThemeUnlocked[0] = true;
        this.mTrackUnlocked[0][0] = true;
        this.mLevelsUnlocked = 1;
        this.mAwardsUnlocked = new boolean[21];
        for (int i3 = 0; i3 < 21; i3++) {
            this.mAwardsUnlocked[i3] = false;
        }
        this.mCrashed = 0;
        this.mDistance = 0;
        this.mPlayTime = 0;
        this.mSelectedTheme = 0;
        this.mSelectedTrack = 0;
        this.mLastUnlockedTheme = 0;
        this.mLastUnlockedTrack = 0;
        this.mCartUnlocked = (byte) 1;
        this.mNumExciters = 0;
        this.mNumStickies = 0;
        this.mNumNitros = 0;
        this.mNumGod = 0;
        this.mNumWinged = 0;
        this.mNumNoBraking = 0;
        this.mFirstTimePreviewCarts = true;
        this.mFirstTimePreview = true;
        this.mEndShown = false;
        this.mAllSmileys = false;
        this.mSurvival = false;
        this.mTournament = false;
    }

    public DataOutputStream saveProfile(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeBoolean(this.mIsFirstTime);
        for (int i = 0; i < 9; i++) {
            dataOutputStream.writeBoolean(this.mThemeUnlocked[i]);
            for (int i2 = 0; i2 < 11; i2++) {
                dataOutputStream.writeBoolean(this.mTrackUnlocked[i][i2]);
                dataOutputStream.writeInt(this.mTrackScore[i][i2]);
                dataOutputStream.writeInt(this.mTrackRides[i][i2]);
                dataOutputStream.writeByte(this.mPrizesUnlocked[(i * 11) + i2]);
                dataOutputStream.writeBoolean(this.mTrackBrake[i][i2]);
            }
        }
        dataOutputStream.writeInt(this.mLevelsUnlocked);
        for (int i3 = 0; i3 < 21; i3++) {
            dataOutputStream.writeBoolean(this.mAwardsUnlocked[i3]);
        }
        dataOutputStream.writeInt(this.mCrashed);
        dataOutputStream.writeInt(this.mDistance);
        dataOutputStream.writeInt(this.mPlayTime);
        dataOutputStream.writeInt(this.mSelectedTheme);
        dataOutputStream.writeInt(this.mSelectedTrack);
        dataOutputStream.writeInt(this.mLastUnlockedTheme);
        dataOutputStream.writeInt(this.mLastUnlockedTrack);
        dataOutputStream.writeByte(this.mCartUnlocked);
        dataOutputStream.writeInt(this.mNumExciters);
        dataOutputStream.writeInt(this.mNumStickies);
        dataOutputStream.writeInt(this.mNumNitros);
        dataOutputStream.writeInt(this.mNumExciters);
        dataOutputStream.writeInt(this.mNumGod);
        dataOutputStream.writeInt(this.mNumWinged);
        dataOutputStream.writeInt(this.mNumNoBraking);
        dataOutputStream.writeBoolean(this.mFirstTimePreviewCarts);
        dataOutputStream.writeBoolean(this.mFirstTimePreview);
        dataOutputStream.writeBoolean(this.mEndShown);
        dataOutputStream.writeBoolean(this.mAllSmileys);
        dataOutputStream.writeBoolean(this.mSurvival);
        dataOutputStream.writeBoolean(this.mTournament);
        return dataOutputStream;
    }

    public void unlockTrack() {
        this.mNewThemeUnlocked = false;
        this.mNewLevel = false;
        if (this.mSelectedTheme == this.mLastUnlockedTheme && this.mSelectedTrack == this.mLastUnlockedTrack) {
            this.mNewLevel = true;
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i >= 11) {
                    break;
                }
                if (!this.mTrackUnlocked[this.mSelectedTheme][i]) {
                    this.mTrackUnlocked[this.mSelectedTheme][i] = true;
                    this.mLastUnlockedTrack = i;
                    this.mSelectedTrack = i;
                    this.mLevelsUnlocked++;
                    break;
                }
                i2++;
                i++;
            }
            this.mNewThemeUnlocked = false;
            if (i2 == 11) {
                int i3 = 0;
                for (int i4 = 0; i4 < 9; i4++) {
                    if (!this.mThemeUnlocked[i4]) {
                        this.mThemeUnlocked[i4] = true;
                        this.mLastUnlockedTheme = i4;
                        this.mSelectedTheme = i4;
                        this.mLastUnlockedTrack = 0;
                        this.mSelectedTrack = 0;
                        this.mTrackUnlocked[this.mSelectedTheme][0] = true;
                        this.mNewThemeUnlocked = true;
                        this.mLevelsUnlocked++;
                        return;
                    }
                    i3++;
                }
            }
        }
    }
}
