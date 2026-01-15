package com.digitalchocolate.androidrollergapp;

import java.io.IOException;
import java.lang.reflect.Array;
import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class StatisticsScreen extends GameScreens {
    private static final int STATS_TEXT_ACHIEVMENTS = 7;
    private static final int STATS_TEXT_CRASHED = 4;
    private static final int STATS_TEXT_DISTANCE = 6;
    private static final int STATS_TEXT_PROGRESS = 3;
    private static final int STATS_TEXT_SCORE = 0;
    private static final int STATS_TEXT_SMILEYS = 2;
    private static final int STATS_TEXT_TIME = 1;
    private static final int STATS_TEXT_TROPHIES = 5;
    private Profile mProfile;
    private int mSelectionFontHeight;
    private ImageFont mSelectionImageFont;
    private int mTextFontHeight;
    private ImageFont mTextImageFont;
    private ImageFont mTitleBarImageFont;
    private int mTitleFontHeight;
    private static final int STATS_TEXT_TRACK = 8;
    private static final int STATS_TEXTS = 9;
    private static final int[] STATS_TEXT_IDS = {180, TextIDs.TID_STATS_2, TextIDs.TID_STATS_3, TextIDs.TID_STATS_4, TextIDs.TID_STATS_6, TextIDs.TID_STATS_7, TextIDs.TID_STATS_8, TextIDs.TID_STATS_9, TextIDs.TID_STATS_5};
    private static final int[] STATS_THEMES_IDS = {TextIDs.TID_SELECTION_ROLLER_TM1_1, TextIDs.TID_SELECTION_ROLLER_TM1_2, TextIDs.TID_SELECTION_ROLLER_TM1_3, TextIDs.TID_SELECTION_ROLLER_TM2_1, TextIDs.TID_SELECTION_ROLLER_TM2_2, TextIDs.TID_SELECTION_ROLLER_TM2_3, TextIDs.TID_SELECTION_ROLLER_TM3_1, TextIDs.TID_SELECTION_ROLLER_TM3_2, TextIDs.TID_SELECTION_ROLLER_TM3_3};

    public StatisticsScreen(ImageFont imageFont, ImageFont imageFont2, ImageFont imageFont3, Profile profile) {
        this.mTitleBarImageFont = imageFont;
        this.mSelectionImageFont = imageFont2;
        this.mTextImageFont = imageFont3;
        this.mTitleFontHeight = this.mTitleBarImageFont.getHeight();
        this.mSelectionFontHeight = this.mSelectionImageFont.getHeight();
        this.mTextFontHeight = this.mTextImageFont.getHeight();
        this.mProfile = profile;
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void doDraw(Graphics graphics) {
        CustomMenuObject.drawGradientRect(graphics, 0, 0, this.mCanvasWidth, this.mCanvasHeight, 1141255, 6988587, 3);
        this.mBackground.doDraw(graphics);
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void keyEventOccurred(int i, int i2) {
        super.keyEventOccurred(i, i2);
        this.mBackground.keyEventOccurred(i, i2);
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void load(int i) throws IOException {
        if (i == 0) {
            this.mTitleTextId = 78;
            return;
        }
        if (i == 1) {
            loadTexts();
        } else if (i == 2) {
            setStatisticsToMenu();
            this.mUpdateSoftKeys = true;
        }
    }

    public void loadTexts() throws IOException {
        String str;
        String str2;
        this.mPoolText = (String[][]) Array.newInstance((Class<?>) String.class, STATS_TEXTS, 1);
        for (int i = 0; i < STATS_TEXTS; i++) {
            this.mPoolText[i][0] = Toolkit.getText(STATS_TEXT_IDS[i]);
        }
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i4 < 9) {
            int i9 = i3;
            int i10 = i8;
            int i11 = i5;
            int i12 = i6;
            int smileys = i7;
            for (int i13 = 0; i13 < 11; i13++) {
                smileys += this.mProfile.getSmileys((i4 * 11) + i13);
                i9 += this.mProfile.mTrackScore[i4][i13];
                if (i12 <= this.mProfile.mTrackRides[i4][i13] && this.mProfile.mTrackRides[i4][i13] > 0) {
                    i12 = this.mProfile.mTrackRides[i4][i13];
                    i11 = i13;
                    i10 = i4;
                }
            }
            i4++;
            i7 = smileys;
            i6 = i12;
            i5 = i11;
            i8 = i10;
            i3 = i9;
        }
        for (int i14 = 0; i14 < 99; i14++) {
            if ((this.mProfile.mPrizesUnlocked[i14] & 4) != 0) {
                i2++;
            }
        }
        this.mPoolText[0][0] = Toolkit.replaceParameters(Toolkit.getText(STATS_TEXT_IDS[0]), new String[]{"" + Util.convertNumberToString(i3)});
        int i15 = this.mProfile.mPlayTime / 1000;
        int i16 = i15 / 3600;
        int i17 = i15 - (i16 * 3600);
        int i18 = i17 / 60;
        this.mPoolText[1][0] = Toolkit.replaceParameters(Toolkit.getText(STATS_TEXT_IDS[1]), new String[]{"" + i16, "" + i18, "" + (i17 - (i18 * 60))});
        this.mPoolText[2][0] = Toolkit.replaceParameters(Toolkit.getText(STATS_TEXT_IDS[2]), new String[]{"" + i7});
        this.mPoolText[3][0] = Toolkit.replaceParameters(Toolkit.getText(STATS_TEXT_IDS[3]), new String[]{"" + (((((i7 * 100) / Profile.NUM_TOTAL_SMILEYS) + ((i2 * 100) / 99)) + ((0 * 100) / 21)) / 3)});
        String text = Toolkit.getText(STATS_THEMES_IDS[i8]);
        String str3 = Toolkit.getText(TextIDs.TID_SELECTION_TRACK) + " " + (i5 + 1);
        if (i6 == 0) {
            str = " ";
            str2 = " ";
        } else {
            str = text;
            str2 = str3;
        }
        this.mPoolText[STATS_TEXT_TRACK][0] = Toolkit.replaceParameters(Toolkit.getText(STATS_TEXT_IDS[STATS_TEXT_TRACK]), new String[]{str, str2});
        this.mPoolText[4][0] = Toolkit.replaceParameters(Toolkit.getText(STATS_TEXT_IDS[4]), new String[]{"" + this.mProfile.mCrashed});
        this.mPoolText[5][0] = Toolkit.replaceParameters(Toolkit.getText(STATS_TEXT_IDS[5]), new String[]{"" + i2});
        this.mPoolText[6][0] = Toolkit.replaceParameters(Toolkit.getText(STATS_TEXT_IDS[6]), new String[]{"" + Util.convertNumberToString(this.mProfile.mDistance)});
        int i19 = 0;
        for (int i20 = 0; i20 < 21; i20++) {
            i19 += this.mProfile.mAwardsUnlocked[i20] ? 1 : 0;
        }
        this.mPoolText[7][0] = Toolkit.replaceParameters(Toolkit.getText(STATS_TEXT_IDS[7]), new String[]{"" + i19});
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public int logicUpdate(int i) throws IOException {
        super.logicUpdate(i);
        this.mBackground.logicUpdate(i);
        return this.mPressedSK == 5 ? 52 : 0;
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void pointerEventOccurred(int i, int i2, int i3) {
        super.pointerEventOccurred(i, i2, i3);
        this.mBackground.pointerEventOccurred(i, i2, i3);
    }

    public void setStatisticsToMenu() {
        this.mBackground = new CustomMenuObject();
        this.mBackground.setScreen(1, STATS_TEXTS, 0);
        this.mBackground.setTitleBar(Toolkit.getText(this.mTitleTextId).toUpperCase(), null, 3);
        for (int i = 0; i < STATS_TEXTS; i++) {
            this.mBackground.setItem(i, 1, this.mPoolText[i][0], null, 0);
        }
        this.mBackground.setSize(this.mCanvasWidth, this.mCanvasHeight);
        this.mBackground.setStyle(3);
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    protected void updateSoftKeys() {
        Game.setSoftKey(5, 0);
    }
}
