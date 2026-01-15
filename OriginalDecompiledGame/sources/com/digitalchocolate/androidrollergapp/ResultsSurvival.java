package com.digitalchocolate.androidrollergapp;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class ResultsSurvival extends GameScreens {
    private static final int RESULTS_COORDS = 12;
    private static final int RESULTS_COORDS_BOX_H = 3;
    private static final int RESULTS_COORDS_BOX_W = 2;
    private static final int RESULTS_COORDS_BOX_X = 0;
    private static final int RESULTS_COORDS_BOX_Y = 1;
    private static final int RESULTS_COORDS_SCORE_TITLE_X = 4;
    private static final int RESULTS_COORDS_SCORE_TITLE_Y = 5;
    private static final int RESULTS_COORDS_SCORE_X = 6;
    private static final int RESULTS_COORDS_SCORE_Y = 7;
    private static final int RESULTS_COORDS_TRACK_TITLE_X = 8;
    private static final int RESULTS_COORDS_TRACK_TITLE_Y = 9;
    private static final int RESULTS_COORDS_TRACK_X = 10;
    private static final int RESULTS_COORDS_TRACK_Y = 11;
    private static final int RESULTS_TEXTS = 2;
    private static final int RESULTS_TEXT_SCORE_TITLE = 0;
    private static final int RESULTS_TEXT_TRACK_TITLE = 1;
    public static int smNumTracksPlayed;
    public static int smTotalScore;
    private int mSelectionFontHeight;
    private ImageFont mSelectionImageFont;
    private int mTextFontHeight;
    private ImageFont mTextImageFont;
    private ImageFont mTitleBarImageFont;
    private int mTitleFontHeight;

    public ResultsSurvival(ImageFont imageFont, ImageFont imageFont2, ImageFont imageFont3) {
        this.mTitleBarImageFont = imageFont;
        this.mSelectionImageFont = imageFont2;
        this.mTextImageFont = imageFont3;
        this.mTitleFontHeight = this.mTitleBarImageFont.getHeight();
        this.mSelectionFontHeight = this.mSelectionImageFont.getHeight();
        this.mTextFontHeight = this.mTextImageFont.getHeight();
        this.mTitleTextId = 273;
    }

    private void loadTexts() {
        this.mPoolText = new String[2][];
        this.mPoolText[0] = new String[1];
        this.mPoolText[0][0] = Toolkit.getText(TextIDs.TID_RESULTS_TOTAL_SCORE).toUpperCase();
        this.mPoolText[1] = new String[1];
        this.mPoolText[1][0] = Toolkit.getText(TextIDs.TID_RESULTS_TRACKS).toUpperCase();
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    protected void calculateCoords() {
        this.mPoolInt = new int[12];
        this.mPoolInt[0] = 10;
        this.mPoolInt[2] = this.mCanvasWidth - 20;
        this.mPoolInt[3] = this.mTitleFontHeight + (this.mSeparatorY * 2) + (this.mSelectionFontHeight * 2) + this.mTextFontHeight;
        if (this.mPoolInt[3] > this.mCanvasHeight - Toolkit.getSoftKeyAreaHeight()) {
            this.mPoolInt[3] = (this.mCanvasHeight - Toolkit.getSoftKeyAreaHeight()) - 12;
        }
        this.mPoolInt[1] = ((this.mCanvasHeight - Toolkit.getSoftKeyAreaHeight()) - this.mPoolInt[3]) / 2;
        this.mPoolInt[4] = this.mPoolInt[0] + ((this.mPoolInt[2] - this.mSelectionImageFont.stringWidth(this.mPoolText[0][0])) / 2);
        this.mPoolInt[5] = this.mPoolInt[1] + this.mSeparatorY;
        this.mPoolInt[6] = this.mPoolInt[0] + ((this.mPoolInt[2] - (this.mSelectionImageFont.stringWidth(GameScreens.NUMBERS_WIDTH_ONE_DIGIT) * 6)) / 2);
        this.mPoolInt[7] = (this.mPoolInt[5] + this.mSelectionFontHeight) - this.mSeparatorY;
        this.mPoolInt[8] = this.mPoolInt[0] + ((this.mPoolInt[2] - this.mTextImageFont.stringWidth(this.mPoolText[1][0])) / 2);
        this.mPoolInt[9] = this.mPoolInt[7] + this.mTitleFontHeight + this.mSeparatorY;
        this.mPoolInt[10] = this.mPoolInt[0] + ((this.mPoolInt[2] - (this.mSelectionImageFont.stringWidth(GameScreens.NUMBERS_MAX_WIDTH) / 2)) / 2);
        this.mPoolInt[11] = this.mPoolInt[9] + this.mTextFontHeight;
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void doDraw(Graphics graphics) {
        this.mBackground.doDraw(graphics);
        CustomMenuObject.drawPopup(graphics, this.mPoolInt[0], this.mPoolInt[1], this.mPoolInt[2], this.mPoolInt[3], true);
        this.mSelectionImageFont.drawString(graphics, this.mPoolText[0][0], this.mPoolInt[4], this.mPoolInt[5], 20);
        numbersDraw(graphics, this.mTitleBarImageFont, smTotalScore, 6, this.mPoolInt[6], this.mPoolInt[7] + 0, true);
        this.mTextImageFont.drawString(graphics, this.mPoolText[1][0], this.mPoolInt[8], this.mPoolInt[9], 20);
        numbersDraw(graphics, this.mSelectionImageFont, smNumTracksPlayed, 2, this.mPoolInt[10], this.mPoolInt[11], true);
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public int getLoadingCount() {
        return 3;
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void load(int i) {
        if (i == 0) {
            loadTexts();
            loadBackground();
        } else if (i == 1) {
            calculateCoords();
            this.mUpdateSoftKeys = true;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public int logicUpdate(int i) throws IOException {
        super.logicUpdate(i);
        return (this.mPressedSK == 1 || (this.mKeys & 16) != 0) ? 48 : 0;
    }

    public void pointerEventOccurred(int i, int i2) {
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    protected void updateSoftKeys() {
        Game.removeAllSoftKeys();
        Game.setSoftKey(1, 0);
    }
}
