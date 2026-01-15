package com.digitalchocolate.androidrollergapp;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class MainMenu extends GameScreens {
    private int mEventToReturn;
    private ImageFont mSelectionImageFont;
    private ImageFont mTextImageFont;
    private ImageFont mTitleBarImageFont;

    public MainMenu(ImageFont imageFont, ImageFont imageFont2, ImageFont imageFont3) {
        this.mTitleBarImageFont = imageFont;
        this.mSelectionImageFont = imageFont2;
        this.mTextImageFont = imageFont3;
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void doDraw(Graphics graphics) {
        super.doDraw(graphics);
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void load(int i) {
        if (i == 0) {
            updateSoftKeys();
            this.mTitleTextId = 72;
            loadBackground();
            int i2 = 0 >> 1;
            int screenHeight = ((Toolkit.getScreenHeight() - this.mTitleBarHeight) - (0 * 5)) / 7;
            int screenWidth = Toolkit.getScreenWidth() >> 1;
            int i3 = this.mTitleBarHeight + (screenHeight * 2) + 0;
            ButtonManager.addButton(0, screenWidth, i3, 0, 0, 0, this.mTextImageFont, 33, -1, ButtonActions.ACTION_CUSTOM_GO_PLAY);
            int i4 = i3 + screenHeight + 0;
            ButtonManager.addButton(0, screenWidth, i4, 0, 0, 0, this.mTextImageFont, 35, -1, ButtonActions.ACTION_CUSTOM_GO_SETTINGS);
            int i5 = i4 + screenHeight + 0;
            ButtonManager.addButton(0, screenWidth, i5, 0, 0, 0, this.mTextImageFont, 9, -1, ButtonActions.ACTION_CUSTOM_GO_HELP);
            int i6 = i5 + screenHeight + 0;
            ButtonManager.addButton(0, screenWidth, i6, 0, 0, 0, this.mTextImageFont, 45, -1, ButtonActions.ACTION_CUSTOM_GO_ABOUT);
            ButtonManager.addButton(0, screenWidth, i6 + (screenHeight * 2) + 0, 0, 0, 0, this.mTextImageFont, 4, -1, ButtonActions.ACTION_CUSTOM_GO_EXIT);
            this.mEventToReturn = -1;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public int logicUpdate(int i) throws IOException {
        super.logicUpdate(i);
        calculateCoords();
        return this.mEventToReturn;
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    public void pointerEventOccurred(int i, int i2, int i3) {
        switch (ButtonManager.pointerEventOccurred(i, i2, i3, 0)) {
            case ButtonActions.ACTION_CUSTOM_GO_PLAY /* 66660 */:
                this.mEventToReturn = 5;
                break;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.GameScreens
    protected void updateSoftKeys() {
        Game.removeAllSoftKeys();
    }
}
