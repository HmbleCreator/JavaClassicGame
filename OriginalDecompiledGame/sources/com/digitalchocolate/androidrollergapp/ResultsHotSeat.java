package com.digitalchocolate.androidrollergapp;

import java.io.IOException;
import java.lang.reflect.Array;
import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class ResultsHotSeat extends GameScreens {
    private static final int RESULTS_COORDS_BOX_H = 3;
    private static final int RESULTS_COORDS_BOX_W = 2;
    private static final int RESULTS_COORDS_BOX_X = 0;
    private static final int RESULTS_COORDS_BOX_Y = 1;
    private static final int RESULTS_COORDS_PLAYER1_X = 8;
    private static final int RESULTS_COORDS_PLAYER1_Y = 9;
    private static final int RESULTS_COORDS_PLAYER2_Y = 11;
    private static final int RESULTS_COORDS_SCORE_X = 10;
    private static final int RESULTS_COORDS_WINNER_X = 6;
    private static final int RESULTS_COORDS_WINNER_Y = 7;
    private static final int RESULTS_INTS = 12;
    private static final int RESULTS_NUM_ROWS = 4;
    private static final int RESULTS_TEXTS = 2;
    private static final int RESULTS_TEXT_PLAYER = 1;
    private static final int RESULTS_TEXT_WINNER = 0;
    private static final int RESULTS_TOTAL_H = 5;
    public static int smPlayerIndex;
    public static int[] smScore;
    public static int[] smTime;
    public static int smTimesPlayed;
    private int mNumDraws;
    private int mNumPlayers = smTime.length;
    private int[] mPlayerNamesIdx;
    private String[] mPlayersNames;
    private int mSelectionFontHeight;
    private ImageFont mSelectionImageFont;
    private int mTextFontHeight;
    private ImageFont mTextImageFont;
    private ImageFont mTitleBarImageFont;
    private int mTitleFontHeight;

    public ResultsHotSeat(ImageFont imageFont, ImageFont imageFont2, ImageFont imageFont3) {
        this.mTitleBarImageFont = imageFont;
        this.mSelectionImageFont = imageFont2;
        this.mTextImageFont = imageFont3;
        this.mTitleFontHeight = this.mTitleBarImageFont.getHeight();
        this.mSelectionFontHeight = this.mSelectionImageFont.getHeight();
        this.mTextFontHeight = this.mTextImageFont.getHeight();
        this.mTitleTextId = 273;
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    protected void calculateCoords() {
        this.mPlayerNamesIdx = new int[this.mNumPlayers];
        for (int i = 0; i < this.mNumPlayers; i++) {
            smTime[i] = (smTime[i] / 10) * 10;
            this.mPlayerNamesIdx[i] = i;
        }
        if (RollerGameEngine.smHotSeatMode == 0) {
            for (int i2 = 0; i2 < this.mNumPlayers; i2++) {
                for (int i3 = 0; i3 < this.mNumPlayers - 1; i3++) {
                    if (smTime[i3] > smTime[i3 + 1]) {
                        int i4 = smTime[i3];
                        smTime[i3] = smTime[i3 + 1];
                        smTime[i3 + 1] = i4;
                        int i5 = this.mPlayerNamesIdx[i3];
                        this.mPlayerNamesIdx[i3] = this.mPlayerNamesIdx[i3 + 1];
                        this.mPlayerNamesIdx[i3 + 1] = i5;
                    }
                }
            }
            this.mNumDraws = 1;
            for (int i6 = 1; i6 < this.mNumPlayers; i6++) {
                if (smTime[0] == smTime[i6]) {
                    this.mNumDraws++;
                }
            }
        } else {
            for (int i7 = 0; i7 < this.mNumPlayers; i7++) {
                for (int i8 = 0; i8 < this.mNumPlayers - 1; i8++) {
                    if (smScore[i8] < smScore[i8 + 1]) {
                        int i9 = smScore[i8];
                        smScore[i8] = smScore[i8 + 1];
                        smScore[i8 + 1] = i9;
                        int i10 = this.mPlayerNamesIdx[i8];
                        this.mPlayerNamesIdx[i8] = this.mPlayerNamesIdx[i8 + 1];
                        this.mPlayerNamesIdx[i8 + 1] = i10;
                    }
                }
            }
            this.mNumDraws = 1;
            for (int i11 = 1; i11 < this.mNumPlayers; i11++) {
                if (smScore[0] == smScore[i11]) {
                    this.mNumDraws++;
                }
            }
        }
        this.mPoolText = (String[][]) Array.newInstance((Class<?>) String.class, 2, 1);
        this.mPoolText[0][0] = Toolkit.getText(this.mNumDraws == 1 ? 128 : 129);
        this.mPoolText[1][0] = Toolkit.getText(130);
        this.mPoolInt = new int[12];
        this.mPoolInt[2] = this.mCanvasWidth - 20;
        this.mPoolInt[0] = 10;
        this.mPoolInt[3] = (this.mTitleFontHeight * (this.mNumDraws + 1)) + (this.mSeparatorY * 2);
        this.mPoolInt[4] = this.mNumPlayers - 1;
        this.mPoolInt[5] = this.mPoolInt[3] + this.mSeparatorY + (this.mPoolInt[4] * this.mSelectionFontHeight);
        if (this.mNumDraws == 1) {
            this.mPoolInt[1] = (((this.mCanvasHeight - this.mSoftKeysHeight) - this.mPoolInt[5]) / 2) + 0;
        } else {
            this.mPoolInt[1] = (((this.mCanvasHeight - this.mSoftKeysHeight) - this.mPoolInt[5]) / 2) + 0;
        }
        this.mPoolInt[6] = (this.mCanvasWidth - this.mTitleBarImageFont.stringWidth(this.mPoolText[0][0])) / 2;
        this.mPoolInt[7] = this.mPoolInt[1] + (this.mSeparatorY / 2);
        this.mPoolInt[8] = this.mPoolInt[0] + 2;
        this.mPoolInt[9] = this.mPoolInt[7] + this.mTitleFontHeight;
        if (RollerGameEngine.smHotSeatMode == 0) {
            this.mPoolInt[10] = ((this.mPoolInt[0] + this.mPoolInt[2]) - this.mSelectionImageFont.stringWidth("0:00:00 ")) - 5;
        } else {
            this.mPoolInt[10] = ((this.mPoolInt[0] + this.mPoolInt[2]) - (this.mSelectionImageFont.stringWidth(GameScreens.NUMBERS_WIDTH_ONE_DIGIT) * 5)) - this.mSeparatorX;
        }
        this.mPoolInt[11] = this.mPoolInt[1] + this.mPoolInt[3] + this.mSeparatorY;
        this.mPlayersNames = new String[this.mNumPlayers];
        for (int i12 = 0; i12 < this.mNumPlayers; i12++) {
            this.mPlayersNames[i12] = (i12 + 1) + ". " + this.mPoolText[1][0] + " " + (this.mPlayerNamesIdx[i12] + 1);
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void doDraw(Graphics graphics) {
        this.mBackground.doDraw(graphics);
        CustomMenuObject.drawPopup(graphics, this.mPoolInt[0], this.mPoolInt[1], this.mPoolInt[2] + 0, this.mPoolInt[3] - 2, true);
        this.mTitleBarImageFont.drawString(graphics, this.mPoolText[0][0], this.mPoolInt[6], this.mPoolInt[7] + 0, 20);
        for (int i = 0; i < this.mNumDraws; i++) {
            this.mSelectionImageFont.drawString(graphics, this.mPlayersNames[i], this.mPoolInt[8] - 0, (this.mSelectionFontHeight * i) + this.mPoolInt[9], 20);
            if (RollerGameEngine.smHotSeatMode == 0) {
                GameScreens.drawTimer(graphics, this.mSelectionImageFont, smTime[i], this.mPoolInt[10] - 0, this.mPoolInt[9] + (this.mSelectionFontHeight * i));
            } else {
                GameScreens.numbersDraw(graphics, this.mSelectionImageFont, smScore[i], 5, this.mPoolInt[10], (this.mSelectionFontHeight * i) + this.mPoolInt[9], false);
            }
        }
        int i2 = this.mNumDraws;
        while (true) {
            int i3 = i2;
            if (i3 >= this.mNumPlayers) {
                return;
            }
            this.mSelectionImageFont.drawString(graphics, this.mPlayersNames[i3], this.mPoolInt[8] - 0, (this.mSelectionFontHeight * (i3 - this.mNumDraws)) + this.mPoolInt[11], 20);
            if (RollerGameEngine.smHotSeatMode == 0) {
                GameScreens.drawTimer(graphics, this.mSelectionImageFont, smTime[i3], this.mPoolInt[10] - 0, this.mPoolInt[11] + (this.mSelectionFontHeight * (i3 - this.mNumDraws)));
            } else {
                GameScreens.numbersDraw(graphics, this.mSelectionImageFont, smScore[i3], 5, this.mPoolInt[10], (this.mSelectionFontHeight * (i3 - this.mNumDraws)) + this.mPoolInt[11], false);
            }
            i2 = i3 + 1;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public int getLoadingCount() {
        return 3;
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void load(int i) {
        if (smPlayerIndex < this.mNumPlayers) {
            return;
        }
        if (i == 0) {
            loadBackground();
        } else if (i == 1) {
            calculateCoords();
            this.mUpdateSoftKeys = true;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public int logicUpdate(int i) throws IOException {
        super.logicUpdate(i);
        if (this.mPressedSK == 13) {
            return 29;
        }
        if (this.mPressedSK == 1 || (this.mKeys & 16) != 0) {
            smPlayerIndex = 0;
            return RollerGameEngine.smHotSeatMode == 0 ? 47 : 48;
        }
        resetKeys();
        return 0;
    }

    public void pointerEventOccurred(int i, int i2) {
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    protected void updateSoftKeys() {
        Game.removeAllSoftKeys();
        Game.setSoftKey(1, 0);
        Game.setSoftKey(13, 0);
    }
}
