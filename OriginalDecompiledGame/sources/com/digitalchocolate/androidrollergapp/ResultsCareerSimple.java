package com.digitalchocolate.androidrollergapp;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class ResultsCareerSimple extends GameScreens {
    private static final int ACHIEV_1000_POINTS = 12;
    private static final int ACHIEV_10_LVL_NO_BRAK = 18;
    private static final int ACHIEV_10_STICKIES = 2;
    private static final int ACHIEV_1ST_RANKING = 14;
    private static final int ACHIEV_20_EXCITERS = 6;
    private static final int ACHIEV_20_NITRO = 4;
    private static final int ACHIEV_30_WINGED = 3;
    private static final int ACHIEV_3_SMILEYS3 = 8;
    private static final int ACHIEV_40000_POINTS = 13;
    private static final int ACHIEV_50_LEVELS = 15;
    private static final int ACHIEV_5_GOD = 5;
    private static final int ACHIEV_5_SAFE = 9;
    private static final int ACHIEV_5_SMILEYS = 0;
    private static final int ACHIEV_5_SMILEYS_IN_20_LVL = 1;
    private static final int ACHIEV_80_EXCITERS = 7;
    private static final int ACHIEV_ALL_CARS = 17;
    private static final int ACHIEV_ALL_LEVELS = 16;
    private static final int ACHIEV_ALL_PERFECTS = 10;
    private static final int ACHIEV_SPEED_REACHED = 11;
    private static final int ACHIEV_TOTAL_RIDE = 19;
    private static final int ACHIEV_TOTAL_TIME = 20;
    private static final int MAX_PROGBARTIME = 4000;
    private static final int RESULTS_COORDS = 25;
    private static final int RESULTS_COORDS_BAR_H = 13;
    private static final int RESULTS_COORDS_BAR_SECTION_W = 24;
    private static final int RESULTS_COORDS_BAR_W = 12;
    private static final int RESULTS_COORDS_BAR_X = 10;
    private static final int RESULTS_COORDS_BAR_Y = 11;
    private static final int RESULTS_COORDS_BEST_SCORE_X = 8;
    private static final int RESULTS_COORDS_BEST_SCORE_Y = 9;
    private static final int RESULTS_COORDS_BORDER_BAR_H = 17;
    private static final int RESULTS_COORDS_BORDER_BAR_W = 16;
    private static final int RESULTS_COORDS_BORDER_BAR_X = 14;
    private static final int RESULTS_COORDS_BORDER_BAR_Y = 15;
    private static final int RESULTS_COORDS_BOX_H = 3;
    private static final int RESULTS_COORDS_BOX_W = 2;
    private static final int RESULTS_COORDS_BOX_X = 0;
    private static final int RESULTS_COORDS_BOX_Y = 1;
    private static final int RESULTS_COORDS_GIFTS_H = 21;
    private static final int RESULTS_COORDS_GIFTS_W = 20;
    private static final int RESULTS_COORDS_GIFTS_X = 18;
    private static final int RESULTS_COORDS_GIFTS_Y = 19;
    private static final int RESULTS_COORDS_SCORE_TITLE_X = 4;
    private static final int RESULTS_COORDS_SCORE_TITLE_Y = 5;
    private static final int RESULTS_COORDS_SCORE_X = 6;
    private static final int RESULTS_COORDS_SCORE_Y = 7;
    private static final int RESULTS_COORDS_TROPHI_X = 22;
    private static final int RESULTS_COORDS_TROPHI_Y = 23;
    private static final int RESULTS_SPRITES = 10;
    private static final int RESULTS_SPRITE_BAR_EMPTY = 5;
    private static final int RESULTS_SPRITE_BAR_FULL = 4;
    private static final int RESULTS_SPRITE_GIFTS_1 = 1;
    private static final int RESULTS_SPRITE_GIFTS_2 = 2;
    private static final int RESULTS_SPRITE_GIFTS_3 = 3;
    private static final int RESULTS_SPRITE_GIFTS_NONE = 0;
    private static final int RESULTS_SPRITE_STARS = 9;
    private static final int RESULTS_SPRITE_TROPHI1 = 6;
    private static final int RESULTS_SPRITE_TROPHI2 = 7;
    private static final int RESULTS_SPRITE_TROPHI3 = 8;
    private static final int RESULTS_TEXTS = 5;
    private static final int RESULTS_TEXT_BEST_SCORE = 4;
    private static final int RESULTS_TEXT_SCORE_TITLE = 3;
    private static final int RESULTS_TEXT_TROPHIE_1 = 0;
    private static final int RESULTS_TEXT_TROPHIE_2 = 1;
    private static final int RESULTS_TEXT_TROPHIE_3 = 2;
    private static final int RS_AWARDS = 2;
    private static final int RS_EVALUATEAWARDS = 4;
    private static final int RS_PROGBAR = 0;
    private static final int RS_TROPHIES = 1;
    private static final int RS_WAIT = 3;
    private static final int TIME_PARTICLE = 200;
    private static final int TROPHI_BLINK = 200;
    private static final int TROPHI_TIMER = 2000;
    public static int smBestScore;
    public static int smCurrentTrack;
    public static boolean smDrawBigTrophie;
    public static boolean smGetHappy;
    public static boolean smGetJump;
    public static boolean smGetSpeed;
    public static int smMaxVelocity;
    public static int smScoreTrack;
    public static int smTotalScore;
    private boolean[] mAchievsToGive;
    public boolean mGetHappy;
    public boolean mGetJump;
    public boolean mGetSpeed;
    private boolean mNewArea;
    private int mNumNoBrakes;
    private int mNumPerfectTrophies;
    private int mNumSafeTrophies;
    private int mNumSmileys3Trophies;
    private int mNumTotalSmileys;
    private int mParticleTime;
    private Profile mProfile;
    private int mProgBarClip;
    private int mProgBarClipScore;
    private int mProgBarClipTrophie;
    private int mProgBarInc;
    private int mProgBarIncTrophies;
    private int mProgBarTime;
    private int mResultsState = 4;
    private int mScoreIncrement;
    private int mSelectionFontHeight;
    private ImageFont mSelectionImageFont;
    private boolean[] mShowPopups;
    private int mTextFontHeight;
    private ImageFont mTextImageFont;
    private ImageFont mTitleBarImageFont;
    private int mTitleFontHeight;
    private int mTrophieBlink;
    private int mTrophieState;
    private int mTrophieTimer;
    private static int[] ACHIEVS_TITLES_TIDS = {TextIDs.TID_ACHIEVEMENTS_TITLE_1, TextIDs.TID_ACHIEVEMENTS_TITLE_2, TextIDs.TID_ACHIEVEMENTS_TITLE_3, TextIDs.TID_ACHIEVEMENTS_TITLE_4, TextIDs.TID_ACHIEVEMENTS_TITLE_5, TextIDs.TID_ACHIEVEMENTS_TITLE_6, TextIDs.TID_ACHIEVEMENTS_TITLE_7, TextIDs.TID_ACHIEVEMENTS_TITLE_8, TextIDs.TID_ACHIEVEMENTS_TITLE_9, 240, 241, TextIDs.TID_ACHIEVEMENTS_TITLE_12, TextIDs.TID_ACHIEVEMENTS_TITLE_13, TextIDs.TID_ACHIEVEMENTS_TITLE_14, TextIDs.TID_ACHIEVEMENTS_TITLE_15, TextIDs.TID_ACHIEVEMENTS_TITLE_16, TextIDs.TID_ACHIEVEMENTS_TITLE_17, TextIDs.TID_ACHIEVEMENTS_TITLE_18, TextIDs.TID_ACHIEVEMENTS_TITLE_19, TextIDs.TID_ACHIEVEMENTS_TITLE_20, TextIDs.TID_ACHIEVEMENTS_TITLE_21};
    private static int[] ACHIEVS_DESC_TIDS = {TextIDs.TID_ACHIEVEMENTS_LOCKED_1, TextIDs.TID_ACHIEVEMENTS_LOCKED_2, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272};
    private static int[] ACHIEVS_RES_RIDS = {ResourceIDs.ANM_R_A_ACHIEVEMENTS_01, ResourceIDs.ANM_R_A_ACHIEVEMENTS_02, ResourceIDs.ANM_R_A_ACHIEVEMENTS_03, ResourceIDs.ANM_R_A_ACHIEVEMENTS_04, ResourceIDs.ANM_R_A_ACHIEVEMENTS_05, ResourceIDs.ANM_R_A_ACHIEVEMENTS_06, ResourceIDs.ANM_R_A_ACHIEVEMENTS_07, ResourceIDs.ANM_R_A_ACHIEVEMENTS_08, ResourceIDs.ANM_R_A_ACHIEVEMENTS_09, ResourceIDs.ANM_R_A_ACHIEVEMENTS_10, ResourceIDs.ANM_R_A_ACHIEVEMENTS_11, ResourceIDs.ANM_R_A_ACHIEVEMENTS_12, ResourceIDs.ANM_R_A_ACHIEVEMENTS_13, ResourceIDs.ANM_R_A_ACHIEVEMENTS_14, ResourceIDs.ANM_R_A_ACHIEVEMENTS_15, ResourceIDs.ANM_R_A_ACHIEVEMENTS_16, ResourceIDs.ANM_R_A_ACHIEVEMENTS_17, ResourceIDs.ANM_R_A_ACHIEVEMENTS_18, ResourceIDs.ANM_R_A_ACHIEVEMENTS_19, ResourceIDs.ANM_R_A_ACHIEVEMENTS_20, ResourceIDs.ANM_R_A_ACHIEVEMENTS_21};

    public ResultsCareerSimple(ImageFont imageFont, ImageFont imageFont2, ImageFont imageFont3, Profile profile) {
        this.mTitleBarImageFont = imageFont;
        this.mSelectionImageFont = imageFont2;
        this.mTextImageFont = imageFont3;
        this.mTitleFontHeight = this.mTitleBarImageFont.getHeight();
        this.mSelectionFontHeight = this.mSelectionImageFont.getHeight();
        this.mTextFontHeight = this.mTextImageFont.getHeight();
        this.mTitleTextId = 273;
        this.mProfile = profile;
    }

    private void countAchievValues() {
        this.mNumTotalSmileys = 0;
        if (!this.mProfile.mAwardsUnlocked[1]) {
            for (int i = 0; i < 99; i++) {
                if (this.mProfile.getSmileys(i) == 5) {
                    this.mNumTotalSmileys++;
                }
            }
        }
        this.mNumSmileys3Trophies = 0;
        this.mNumSafeTrophies = 0;
        this.mNumPerfectTrophies = 0;
        this.mNumNoBrakes = 0;
        for (int i2 = 0; i2 < 99; i2++) {
            if ((this.mProfile.mPrizesUnlocked[i2] & 1) != 0) {
                this.mNumSmileys3Trophies++;
            }
            if ((this.mProfile.mPrizesUnlocked[i2] & 2) != 0) {
                this.mNumSafeTrophies++;
            }
            if ((this.mProfile.mPrizesUnlocked[i2] & 4) != 0) {
                this.mNumPerfectTrophies++;
            }
        }
        for (int i3 = 0; i3 < 9; i3++) {
            for (int i4 = 0; i4 < 11; i4++) {
                if (this.mProfile.mTrackBrake[i3][i4]) {
                    this.mNumNoBrakes++;
                }
            }
        }
    }

    private void evaluateAchivements() {
        if (!this.mProfile.mAwardsUnlocked[0] && this.mProfile.getSmileys(smCurrentTrack) == 5) {
            boolean[] zArr = this.mAchievsToGive;
            this.mProfile.mAwardsUnlocked[0] = true;
            zArr[0] = true;
        }
        if (!this.mProfile.mAwardsUnlocked[1] && this.mNumTotalSmileys >= 20) {
            boolean[] zArr2 = this.mAchievsToGive;
            this.mProfile.mAwardsUnlocked[1] = true;
            zArr2[1] = true;
        }
        if (!this.mProfile.mAwardsUnlocked[2] && this.mProfile.mNumStickies >= 10) {
            boolean[] zArr3 = this.mAchievsToGive;
            this.mProfile.mAwardsUnlocked[2] = true;
            zArr3[2] = true;
        }
        if (!this.mProfile.mAwardsUnlocked[3] && this.mProfile.mNumWinged >= 30) {
            boolean[] zArr4 = this.mAchievsToGive;
            this.mProfile.mAwardsUnlocked[3] = true;
            zArr4[3] = true;
        }
        if (!this.mProfile.mAwardsUnlocked[4] && this.mProfile.mNumNitros >= 20) {
            boolean[] zArr5 = this.mAchievsToGive;
            this.mProfile.mAwardsUnlocked[4] = true;
            zArr5[4] = true;
        }
        if (!this.mProfile.mAwardsUnlocked[5] && this.mProfile.mNumGod >= 5) {
            boolean[] zArr6 = this.mAchievsToGive;
            this.mProfile.mAwardsUnlocked[5] = true;
            zArr6[5] = true;
        }
        if (!this.mProfile.mAwardsUnlocked[6] && this.mProfile.mNumExciters >= 20) {
            boolean[] zArr7 = this.mAchievsToGive;
            this.mProfile.mAwardsUnlocked[6] = true;
            zArr7[6] = true;
        }
        if (!this.mProfile.mAwardsUnlocked[7] && this.mProfile.mNumExciters >= 80) {
            boolean[] zArr8 = this.mAchievsToGive;
            this.mProfile.mAwardsUnlocked[7] = true;
            zArr8[7] = true;
        }
        if (!this.mProfile.mAwardsUnlocked[8] && this.mNumSmileys3Trophies >= 3) {
            boolean[] zArr9 = this.mAchievsToGive;
            this.mProfile.mAwardsUnlocked[8] = true;
            zArr9[8] = true;
        }
        if (!this.mProfile.mAwardsUnlocked[9] && this.mNumSafeTrophies >= 5) {
            boolean[] zArr10 = this.mAchievsToGive;
            this.mProfile.mAwardsUnlocked[9] = true;
            zArr10[9] = true;
        }
        if (!this.mProfile.mAwardsUnlocked[10] && this.mNumPerfectTrophies == 99) {
            boolean[] zArr11 = this.mAchievsToGive;
            this.mProfile.mAwardsUnlocked[10] = true;
            zArr11[10] = true;
        }
        if (!this.mProfile.mAwardsUnlocked[11] && smMaxVelocity >= 650) {
            boolean[] zArr12 = this.mAchievsToGive;
            this.mProfile.mAwardsUnlocked[11] = true;
            zArr12[11] = true;
        }
        if (!this.mProfile.mAwardsUnlocked[12] && smScoreTrack >= 1000) {
            boolean[] zArr13 = this.mAchievsToGive;
            this.mProfile.mAwardsUnlocked[12] = true;
            zArr13[12] = true;
        }
        if (!this.mProfile.mAwardsUnlocked[13] && smTotalScore > 40000) {
            boolean[] zArr14 = this.mAchievsToGive;
            this.mProfile.mAwardsUnlocked[13] = true;
            zArr14[13] = true;
        }
        if (!this.mProfile.mAwardsUnlocked[14] && smTotalScore >= Profile.HISCORE_POINTS[0]) {
            boolean[] zArr15 = this.mAchievsToGive;
            this.mProfile.mAwardsUnlocked[14] = true;
            zArr15[14] = true;
        }
        if (!this.mProfile.mAwardsUnlocked[15] && smCurrentTrack == 49) {
            boolean[] zArr16 = this.mAchievsToGive;
            this.mProfile.mAwardsUnlocked[15] = true;
            zArr16[15] = true;
        }
        if (!this.mProfile.mAwardsUnlocked[16] && smCurrentTrack == 98) {
            boolean[] zArr17 = this.mAchievsToGive;
            this.mProfile.mAwardsUnlocked[16] = true;
            zArr17[16] = true;
        }
        if (!this.mProfile.mAwardsUnlocked[17] && this.mProfile.mCartUnlocked == 7) {
            boolean[] zArr18 = this.mAchievsToGive;
            this.mProfile.mAwardsUnlocked[17] = true;
            zArr18[17] = true;
        }
        if (!this.mProfile.mAwardsUnlocked[18] && this.mNumNoBrakes >= 10) {
            boolean[] zArr19 = this.mAchievsToGive;
            this.mProfile.mAwardsUnlocked[18] = true;
            zArr19[18] = true;
        }
        if (!this.mProfile.mAwardsUnlocked[19] && this.mProfile.mDistance >= 25000) {
            boolean[] zArr20 = this.mAchievsToGive;
            this.mProfile.mAwardsUnlocked[19] = true;
            zArr20[19] = true;
        }
        if (this.mProfile.mAwardsUnlocked[20] || this.mProfile.mPlayTime < 7200000) {
            return;
        }
        boolean[] zArr21 = this.mAchievsToGive;
        this.mProfile.mAwardsUnlocked[20] = true;
        zArr21[20] = true;
    }

    private void loadSprites() {
        this.mPoolSprite = new SpriteObject[10];
        this.mPoolSprite[0] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_A_PRIZES_LOW_NO_PRIZE, true), false);
        this.mPoolSprite[1] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_A_PRIZES_LOW_SPEED, true), false);
        this.mPoolSprite[2] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_A_PRIZES_LOW_JUMP, true), false);
        this.mPoolSprite[3] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_A_PRIZES_LOW_HAPPY, true), false);
        this.mPoolSprite[4] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_A_SMILES_BAR_FULL, true), false);
        this.mPoolSprite[5] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_SMILES_BAR_EMPTY, true), false);
        this.mPoolSprite[6] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_A_PRIZES_HIGH_SPEED, true), false);
        this.mPoolSprite[7] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_A_PRIZES_HIGH_JUMP, true), false);
        this.mPoolSprite[8] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_A_PRIZES_HIGH_HAPPY, true), false);
        this.mPoolSprite[9] = new SpriteObject(DavinciUtilities.loadAnimation(ResourceIDs.ANM_R_E_BOOM_STARS, true), true);
    }

    private void loadTexts() {
        this.mPoolText = new String[5][];
        this.mPoolText[0] = new String[1];
        this.mPoolText[0][0] = Toolkit.getText(TextIDs.TID_TROPHY_THREE);
        this.mPoolText[1] = new String[1];
        this.mPoolText[1][0] = Toolkit.getText(TextIDs.TID_TROPHY_SAFE);
        this.mPoolText[2] = new String[1];
        this.mPoolText[2][0] = Toolkit.getText(TextIDs.TID_TROPHY_PERFECT);
        this.mPoolText[3] = new String[1];
        this.mPoolText[3][0] = Toolkit.getText(TextIDs.TID_RESULTS_SCORE);
        this.mPoolText[4] = new String[1];
        setBestScoreText();
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    protected void calculateCoords() {
        this.mGetHappy = false;
        this.mGetJump = false;
        this.mGetSpeed = false;
        this.mPoolInt = new int[25];
        this.mPoolInt[0] = 10;
        this.mPoolInt[2] = this.mCanvasWidth - 20;
        this.mPoolInt[3] = this.mTitleFontHeight + (this.mSelectionFontHeight * 2) + this.mPoolSprite[4].getHeight() + this.mPoolSprite[1].getHeight() + (this.mSeparatorY * 6);
        smDrawBigTrophie = true;
        if (this.mPoolInt[3] > this.mDrawAreaHeight) {
            this.mPoolInt[3] = (this.mCanvasHeight - Toolkit.getSoftKeyAreaHeight()) - 12;
            smDrawBigTrophie = false;
        }
        if (!smDrawBigTrophie) {
            this.mPoolInt[1] = ((this.mCanvasHeight - Toolkit.getSoftKeyAreaHeight()) - this.mPoolInt[3]) / 2;
            this.mPoolInt[4] = (this.mPoolInt[0] + ((this.mPoolInt[2] - this.mSelectionImageFont.stringWidth(this.mPoolText[3][0])) / 2)) - (this.mSelectionImageFont.stringWidth(GameScreens.NUMBERS_MAX_WIDTH) / 2);
            this.mPoolInt[5] = this.mPoolInt[1] + this.mSeparatorY;
            this.mPoolInt[6] = this.mPoolInt[4] + this.mSelectionImageFont.stringWidth(this.mPoolText[3][0]);
            this.mPoolInt[7] = this.mPoolInt[5];
            this.mPoolInt[15] = this.mPoolInt[7] + this.mSelectionFontHeight + (this.mSeparatorY * 2);
            this.mPoolInt[17] = this.mSelectionFontHeight;
            this.mPoolInt[24] = (this.mPoolInt[3] + 20) / 7;
            this.mPoolInt[16] = (this.mPoolInt[24] * 5) + 4;
            this.mPoolInt[14] = this.mPoolInt[0] + ((this.mPoolInt[2] - this.mPoolInt[16]) / 2);
            this.mPoolInt[10] = this.mPoolInt[14] + 2;
            this.mPoolInt[11] = this.mPoolInt[15] + 2;
            this.mPoolInt[12] = this.mPoolInt[16] - 4;
            this.mPoolInt[13] = this.mPoolInt[17] - 4;
            this.mPoolInt[20] = this.mPoolSprite[1].getWidth();
            this.mPoolInt[21] = this.mPoolSprite[1].getHeight();
            this.mPoolInt[18] = this.mPoolInt[0] + ((this.mPoolInt[2] - ((this.mPoolInt[20] * 3) + 10)) / 2) + this.mPoolSprite[1].getPivotX();
            this.mPoolInt[19] = this.mPoolInt[11] + this.mPoolInt[13] + (this.mSeparatorY * 2) + this.mPoolSprite[1].getPivotY();
            this.mPoolInt[8] = this.mPoolInt[0] + ((this.mPoolInt[2] - this.mSelectionImageFont.stringWidth(this.mPoolText[4][0])) / 2);
            this.mPoolInt[9] = ((this.mPoolInt[19] + this.mPoolInt[21]) + (this.mSeparatorY * 2)) - this.mPoolSprite[1].getPivotY();
            return;
        }
        int height = this.mPoolInt[3] + this.mPoolSprite[6].getHeight() + (this.mSeparatorY * 2);
        if (height >= this.mCanvasHeight - Toolkit.getSoftKeyAreaHeight()) {
            smDrawBigTrophie = false;
            this.mPoolInt[1] = ((this.mCanvasHeight - Toolkit.getSoftKeyAreaHeight()) - this.mPoolInt[3]) / 2;
        } else {
            smDrawBigTrophie = true;
            this.mPoolInt[23] = ((this.mCanvasHeight - Toolkit.getSoftKeyAreaHeight()) - height) / 2;
            this.mPoolInt[1] = this.mPoolInt[23] + this.mPoolSprite[6].getHeight() + (this.mSeparatorY * 2);
            int[] iArr = this.mPoolInt;
            iArr[23] = iArr[23] + this.mPoolSprite[6].getPivotY();
        }
        this.mPoolInt[22] = this.mCanvasWidth / 2;
        this.mPoolInt[4] = this.mPoolInt[0] + ((this.mPoolInt[2] - this.mSelectionImageFont.stringWidth(this.mPoolText[3][0])) / 2);
        this.mPoolInt[5] = this.mPoolInt[1] + this.mSeparatorY;
        this.mPoolInt[6] = this.mPoolInt[0] + ((this.mPoolInt[2] - this.mSelectionImageFont.stringWidth(GameScreens.NUMBERS_MAX_WIDTH)) / 2);
        this.mPoolInt[7] = (this.mPoolInt[5] + this.mSelectionFontHeight) - this.mSeparatorY;
        this.mPoolInt[12] = this.mPoolSprite[4].getCurrentAnimationData().getWidth();
        this.mPoolInt[10] = (this.mCanvasWidth - this.mPoolSprite[4].getWidth()) / 2;
        this.mPoolInt[11] = this.mPoolInt[7] + this.mSelectionFontHeight + (this.mSeparatorY * 2);
        this.mPoolInt[13] = this.mPoolSprite[4].getHeight();
        this.mPoolInt[20] = this.mPoolSprite[1].getWidth();
        this.mPoolInt[21] = this.mPoolSprite[1].getHeight();
        this.mPoolInt[18] = this.mPoolInt[0] + ((this.mPoolInt[2] - ((this.mPoolInt[20] * 3) + 10)) / 2) + this.mPoolSprite[1].getPivotX();
        this.mPoolInt[19] = this.mPoolInt[11] + this.mPoolInt[13] + (this.mSeparatorY * 2) + this.mPoolSprite[1].getPivotY();
        this.mPoolInt[8] = this.mPoolInt[0] + ((this.mPoolInt[2] - this.mSelectionImageFont.stringWidth(this.mPoolText[4][0])) / 2);
        this.mPoolInt[9] = ((this.mPoolInt[19] + this.mPoolInt[21]) + this.mSeparatorY) - this.mPoolSprite[1].getPivotY();
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void doDraw(Graphics graphics) {
        graphics.setClip(0, 0, this.mCanvasWidth, this.mCanvasHeight);
        CustomMenuObject.drawPopup(graphics, this.mPoolInt[0], this.mPoolInt[1], this.mPoolInt[2], this.mPoolInt[3], true);
        this.mSelectionImageFont.drawString(graphics, this.mPoolText[3][0], this.mPoolInt[4], this.mPoolInt[5], 20);
        numbersDraw(graphics, this.mTitleBarImageFont, this.mProgBarInc, 4, this.mPoolInt[6] + 0, this.mPoolInt[7] + 0, false);
        this.mSelectionImageFont.drawString(graphics, this.mPoolText[4][0], this.mPoolInt[8], this.mPoolInt[9] + 0, 20);
        int width = (this.mPoolSprite[4].getWidth() - this.mPoolInt[12]) / 2;
        this.mPoolSprite[5].draw(graphics, this.mPoolInt[10], this.mPoolInt[11]);
        graphics.setClip(width + this.mPoolInt[10], this.mPoolInt[11], this.mProgBarClip, this.mPoolInt[13]);
        this.mPoolSprite[4].draw(graphics, this.mPoolInt[10], this.mPoolInt[11]);
        graphics.setClip(0, 0, this.mCanvasWidth, this.mCanvasHeight);
        if (this.mResultsState == 0) {
            Engine2D.particlesDraw(graphics, 4);
        }
        int i = this.mPoolInt[18];
        for (int i2 = 0; i2 < 3; i2++) {
            if (this.mGetJump && i2 == 0 && !smGetJump) {
                this.mPoolSprite[1].draw(graphics, i, this.mPoolInt[19]);
            } else if (this.mGetSpeed && i2 == 1 && !smGetSpeed) {
                this.mPoolSprite[2].draw(graphics, i, this.mPoolInt[19]);
            } else if (this.mGetHappy && i2 == 2 && !smGetHappy) {
                this.mPoolSprite[3].draw(graphics, i, this.mPoolInt[19]);
            } else {
                this.mPoolSprite[0].draw(graphics, i, this.mPoolInt[19]);
            }
            i += this.mPoolInt[20] + 5;
        }
        if (this.mResultsState == 1 || this.mResultsState == 3) {
            int i3 = this.mPoolInt[20] + 5;
            if (smGetJump) {
                if (smDrawBigTrophie) {
                    this.mPoolSprite[9].draw(graphics, this.mPoolInt[22], this.mPoolInt[23]);
                    this.mPoolSprite[6].draw(graphics, this.mPoolInt[22], this.mPoolInt[23]);
                }
                if (this.mTrophieState == 1) {
                    this.mPoolSprite[1].draw(graphics, this.mPoolInt[18], this.mPoolInt[19]);
                    if (smDrawBigTrophie) {
                        this.mTitleBarImageFont.drawString(graphics, this.mPoolText[0][0], ((this.mCanvasWidth - this.mTitleBarImageFont.stringWidth(this.mPoolText[0][0])) / 2) - 0, (this.mPoolInt[23] - (this.mTitleFontHeight / 2)) + 0, 20);
                    }
                } else {
                    this.mPoolSprite[0].draw(graphics, this.mPoolInt[18], this.mPoolInt[19]);
                }
            } else if (smGetSpeed) {
                if (smDrawBigTrophie) {
                    this.mPoolSprite[9].draw(graphics, this.mPoolInt[22], this.mPoolInt[23]);
                    this.mPoolSprite[7].draw(graphics, this.mPoolInt[22], this.mPoolInt[23]);
                }
                if (this.mTrophieState == 1) {
                    this.mPoolSprite[2].draw(graphics, i3 + this.mPoolInt[18], this.mPoolInt[19]);
                    if (smDrawBigTrophie) {
                        this.mTitleBarImageFont.drawString(graphics, this.mPoolText[1][0], ((this.mCanvasWidth - this.mTitleBarImageFont.stringWidth(this.mPoolText[1][0])) / 2) - 0, (this.mPoolInt[23] - (this.mTitleFontHeight / 2)) + 0, 20);
                    }
                } else {
                    this.mPoolSprite[0].draw(graphics, i3 + this.mPoolInt[18], this.mPoolInt[19]);
                }
            } else if (smGetHappy || this.mGetHappy) {
                if (smDrawBigTrophie) {
                    this.mPoolSprite[9].draw(graphics, this.mPoolInt[22], this.mPoolInt[23]);
                    this.mPoolSprite[8].draw(graphics, this.mPoolInt[22], this.mPoolInt[23]);
                }
                if (this.mTrophieState == 1) {
                    this.mPoolSprite[3].draw(graphics, (i3 * 2) + this.mPoolInt[18], this.mPoolInt[19]);
                    if (smDrawBigTrophie) {
                        this.mTitleBarImageFont.drawString(graphics, this.mPoolText[2][0], ((this.mCanvasWidth - this.mTitleBarImageFont.stringWidth(this.mPoolText[2][0])) / 2) - 0, (this.mPoolInt[23] - (this.mTitleFontHeight / 2)) + 0, 20);
                    }
                } else {
                    this.mPoolSprite[0].draw(graphics, (i3 * 2) + this.mPoolInt[18], this.mPoolInt[19]);
                }
            }
        }
        if (this.mTextBox != null) {
            this.mTextBox.doDraw(graphics);
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public int getLoadingCount() {
        return 3;
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void keyEventOccurred(int i, int i2) {
        if (this.mTextBox != null) {
            this.mTextBox.keyEventOccurred(i, i2);
        }
        super.keyEventOccurred(i, i2);
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void load(int i) {
        if (i == 0) {
            loadTexts();
            return;
        }
        if (i == 1) {
            loadSprites();
        } else if (i == 2) {
            calculateCoords();
            this.mUpdateSoftKeys = true;
            this.mScoreIncrement = 0;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public int logicUpdate(int i) throws IOException {
        super.logicUpdate(i);
        if (GameEngine.smEngine2d.mIngameFireworksHasFinished) {
            GameEngine.smEngine2d.ingameFireworksInit(5000);
        }
        if (this.mTextBox != null) {
            this.mTextBox.logicUpdate(i);
            if (this.mPressedSK == 1 || (this.mKeys & 16) != 0) {
                destroyTextBox();
                resetKeys();
            }
            return 0;
        }
        int i2 = this.mPoolInt[12] / 5;
        int i3 = i2 * 4;
        switch (this.mResultsState) {
            case 0:
                if (this.mProgBarTime < MAX_PROGBARTIME) {
                    this.mProgBarTime += i;
                    int i4 = this.mProgBarInc;
                    this.mProgBarInc = (this.mProgBarTime * smScoreTrack) / MAX_PROGBARTIME;
                    if (this.mProgBarInc > smScoreTrack) {
                        this.mProgBarInc = smScoreTrack;
                    }
                    if (this.mProgBarInc < smScoreTrack) {
                        int i5 = this.mProgBarInc - i4;
                        int screenWidth = Toolkit.getScreenWidth() + GameEngine.smEngine2d.mHudScoreSmileOffX;
                        int i6 = GameEngine.smEngine2d.mHudScoreSmileY;
                        this.mParticleTime += i;
                        if (this.mParticleTime >= 200 && smDrawBigTrophie) {
                            Engine2D.particlesAddMovement(Engine2D.particlesAddParticle(2, screenWidth, i6, 4), this.mPoolInt[6], this.mPoolInt[7], Util.rnd((MAX_PROGBARTIME - this.mProgBarTime) - 500) + 500);
                            this.mParticleTime = 0;
                        }
                        GameEngine.smTrain.mPoints -= i5 << 12;
                        GameEngine.smTrain.mPointsTarget -= i5 << 12;
                        if (GameEngine.smTrain.mPoints < 0) {
                            GameEngine.smTrain.mPoints = 0;
                            GameEngine.smTrain.mPointsTarget = 0;
                        }
                        this.mProgBarClip = (this.mProgBarInc * i3) / (GameEngine.smCurrentLevelTotalScore * 100);
                    }
                }
                if (this.mPressedSK == 14 || (this.mKeys & 16) != 0 || this.mProgBarTime >= MAX_PROGBARTIME) {
                    this.mResultsState = 1;
                    this.mProgBarInc = smScoreTrack;
                    this.mProgBarTime = 0;
                    if (GameEngine.smCurrentLevelTotalScore == 0) {
                        this.mProgBarClip = 0;
                    } else {
                        this.mProgBarClip = (this.mProgBarInc * i3) / (GameEngine.smCurrentLevelTotalScore * 100);
                    }
                    this.mProgBarClipScore = this.mProgBarClip;
                    this.mProgBarIncTrophies = this.mProgBarInc;
                    int i7 = (GameEngine.smCurrentLevelTotalScore * 100) / 5;
                    if (smGetJump) {
                        this.mScoreIncrement += i7 / 2;
                    }
                    if (smGetSpeed) {
                        this.mScoreIncrement = (i7 / 2) + this.mScoreIncrement;
                    }
                    this.mUpdateSoftKeys = true;
                    break;
                }
                break;
            case 1:
                this.mPoolSprite[9].logicUpdate(i);
                if (smGetJump || smGetSpeed || smGetHappy) {
                    this.mTrophieTimer += i;
                    this.mTrophieBlink += i;
                    this.mProgBarTime += i;
                    this.mProgBarIncTrophies = (this.mProgBarTime * this.mScoreIncrement) / MAX_PROGBARTIME;
                    if (this.mProgBarIncTrophies > this.mScoreIncrement) {
                        this.mProgBarIncTrophies = this.mScoreIncrement;
                    }
                    if (this.mProgBarIncTrophies < this.mScoreIncrement) {
                        if (GameEngine.smCurrentLevelTotalScore == 0) {
                            this.mProgBarClipTrophie = 0;
                        } else {
                            this.mProgBarClipTrophie = (this.mProgBarIncTrophies * i2) / ((GameEngine.smCurrentLevelTotalScore * 100) / 5);
                        }
                        this.mProgBarClip = this.mProgBarClipTrophie + this.mProgBarClipScore;
                    }
                    if (this.mTrophieBlink >= 200) {
                        this.mTrophieBlink = 0;
                        this.mTrophieState = this.mTrophieState == 0 ? 1 : 0;
                    }
                    if (this.mTrophieTimer > 2000) {
                        if (smGetJump) {
                            smGetJump = false;
                            this.mGetJump = true;
                        } else if (smGetSpeed) {
                            smGetSpeed = false;
                            this.mGetSpeed = true;
                        } else if (smGetHappy) {
                            smGetHappy = false;
                            this.mGetHappy = true;
                        }
                        this.mTrophieTimer = 0;
                        this.mTrophieState = 1;
                    }
                } else {
                    this.mProgBarIncTrophies = this.mScoreIncrement;
                    if (GameEngine.smCurrentLevelTotalScore == 0) {
                        this.mProgBarClipTrophie = 0;
                    } else {
                        this.mProgBarClipTrophie = (this.mProgBarIncTrophies * i2) / ((GameEngine.smCurrentLevelTotalScore * 100) / 5);
                    }
                    this.mProgBarClip = this.mProgBarClipTrophie + this.mProgBarClipScore;
                    this.mResultsState = 2;
                }
                if (this.mPressedSK == 14 || (this.mKeys & 16) != 0) {
                    if (smGetJump) {
                        smGetJump = false;
                        this.mGetJump = true;
                    }
                    if (smGetSpeed) {
                        smGetSpeed = false;
                        this.mGetSpeed = true;
                    }
                    if (smGetHappy) {
                        smGetHappy = false;
                        this.mGetHappy = true;
                    }
                    this.mResultsState = 2;
                    this.mProgBarIncTrophies = this.mScoreIncrement;
                    if (GameEngine.smCurrentLevelTotalScore == 0) {
                        this.mProgBarClipTrophie = 0;
                    } else {
                        this.mProgBarClipTrophie = (i2 * this.mProgBarIncTrophies) / ((GameEngine.smCurrentLevelTotalScore * 100) / 5);
                    }
                    this.mProgBarClip = this.mProgBarClipTrophie + this.mProgBarClipScore;
                    resetKeys();
                    this.mTrophieState = 1;
                    break;
                }
                break;
            case 2:
                for (int i8 = 0; i8 < 21; i8++) {
                    if (this.mAchievsToGive[i8]) {
                        createTextBox(Toolkit.getText(ACHIEVS_TITLES_TIDS[i8]), Toolkit.getText(ACHIEVS_DESC_TIDS[i8]), ACHIEVS_RES_RIDS[i8]);
                        this.mAchievsToGive[i8] = false;
                        return 0;
                    }
                }
                if (this.mShowPopups[0]) {
                    createTextBox(Toolkit.getText(TextIDs.TID_GAME_CART_UNLOCK_TITLE) + " - " + Toolkit.getText(TextIDs.TID_RESULTS_HEAVY), Toolkit.getText(TextIDs.TID_GAME_CART_UNLOCK), ResourceIDs.ANM_R_P_SELECT_CART_03);
                    this.mShowPopups[0] = false;
                    return 0;
                }
                if (this.mShowPopups[1]) {
                    createTextBox(Toolkit.getText(TextIDs.TID_GAME_CART_UNLOCK_TITLE) + " - " + Toolkit.getText(TextIDs.TID_RESULTS_JUMPER), Toolkit.getText(TextIDs.TID_GAME_CART_UNLOCK), ResourceIDs.ANM_R_P_SELECT_CART_02);
                    this.mShowPopups[1] = false;
                    return 0;
                }
                if (this.mShowPopups[2]) {
                    createTextBox(Toolkit.getText(TextIDs.TID_RESULTS_AREA_UNLOCK));
                    this.mShowPopups[2] = false;
                    return 0;
                }
                if (this.mShowPopups[3]) {
                    createTextBox(new String[]{Toolkit.getText(TextIDs.TID_MODE_UNLOCKED), Toolkit.getText(TextIDs.TID_MODE_UNLOCKED_SURVIVAL)});
                    this.mShowPopups[3] = false;
                    return 0;
                }
                if (this.mShowPopups[4]) {
                    createTextBox(new String[]{Toolkit.getText(TextIDs.TID_MODE_UNLOCKED), Toolkit.getText(TextIDs.TID_MODE_UNLOCKED_TOURNAMENT)});
                    this.mShowPopups[4] = false;
                    return 0;
                }
                if (this.mShowPopups[5]) {
                    createTextBox(Toolkit.getText(131), Toolkit.getText(132));
                    this.mShowPopups[5] = false;
                    return 0;
                }
                if (this.mShowPopups[6]) {
                    createTextBox(Toolkit.getText(TextIDs.TID_CUTSCENE_ALLSMILEYS));
                    this.mShowPopups[6] = false;
                    return 0;
                }
                this.mUpdateSoftKeys = true;
                this.mResultsState = 3;
                break;
            case 3:
                if (this.mPressedSK == 1 || (this.mKeys & 16) != 0) {
                    resetKeys();
                    return 28;
                }
                break;
            case 4:
                this.mAchievsToGive = new boolean[21];
                evaluateAchivements();
                this.mShowPopups = new boolean[7];
                if ((this.mProfile.mCartUnlocked & 2) == 0 && smCurrentTrack == 21) {
                    Profile profile = this.mProfile;
                    profile.mCartUnlocked = (byte) (profile.mCartUnlocked | 2);
                    this.mShowPopups[0] = true;
                }
                if ((this.mProfile.mCartUnlocked & 4) == 0 && smCurrentTrack == 54) {
                    Profile profile2 = this.mProfile;
                    profile2.mCartUnlocked = (byte) (profile2.mCartUnlocked | 4);
                    this.mShowPopups[1] = true;
                }
                if (this.mNewArea) {
                    this.mNewArea = false;
                    this.mShowPopups[2] = true;
                }
                if (this.mProfile.mTrackUnlocked[1][0] && !this.mProfile.mSurvival) {
                    this.mProfile.mSurvival = true;
                    this.mShowPopups[3] = true;
                }
                if (this.mProfile.mTrackUnlocked[1][0] && !this.mProfile.mTournament) {
                    this.mProfile.mTournament = true;
                    this.mShowPopups[4] = true;
                }
                if (DCThrillOld.smLevelIdx == 98 && !this.mProfile.mEndShown) {
                    this.mProfile.mEndShown = true;
                    this.mShowPopups[5] = true;
                }
                if (DCThrillOld.smLevelIdx == 98 && this.mNumTotalSmileys >= 99 && !this.mProfile.mAllSmileys) {
                    this.mProfile.mAllSmileys = true;
                    this.mShowPopups[6] = true;
                }
                this.mUpdateSoftKeys = true;
                this.mResultsState = 0;
                return -3;
        }
        resetKeys();
        return 0;
    }

    public void pointerEventOccurred(int i, int i2) {
    }

    public void resetTrack() {
        smGetHappy = false;
        smGetJump = false;
        smGetSpeed = false;
        this.mProgBarInc = 0;
        this.mProgBarIncTrophies = 0;
        this.mProgBarTime = 0;
        this.mProgBarClip = 0;
        this.mProgBarClipScore = 0;
        this.mProgBarClipTrophie = 0;
        this.mGetHappy = false;
        this.mGetJump = false;
        this.mGetSpeed = false;
        this.mResultsState = 4;
        this.mAchievsToGive = null;
        this.mShowPopups = null;
        this.mScoreIncrement = 0;
    }

    public void setBestScoreText() {
        if (smBestScore < 10) {
            this.mPoolText[4][0] = Toolkit.getText(TextIDs.TID_RESULTS_BEST_SCORE) + " 000" + smBestScore;
        } else if (smBestScore < 100) {
            this.mPoolText[4][0] = Toolkit.getText(TextIDs.TID_RESULTS_BEST_SCORE) + " 00" + smBestScore;
        } else if (smBestScore < 1000) {
            this.mPoolText[4][0] = Toolkit.getText(TextIDs.TID_RESULTS_BEST_SCORE) + " 0" + smBestScore;
        } else {
            this.mPoolText[4][0] = Toolkit.getText(TextIDs.TID_RESULTS_BEST_SCORE) + " " + smBestScore;
        }
        countAchievValues();
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void setVisible() {
        super.setVisible();
        this.mNewArea = this.mProfile.mNewThemeUnlocked;
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    protected void updateSoftKeys() {
        Game.removeAllSoftKeys();
        if (this.mTextBox != null) {
            Game.setSoftKey(1, 0);
        }
        switch (this.mResultsState) {
            case 0:
                Game.setSoftKey(14, 0);
                break;
            case 1:
                Game.setSoftKey(14, 0);
                break;
            case 3:
                Game.setSoftKey(1, 0);
                break;
        }
    }
}
