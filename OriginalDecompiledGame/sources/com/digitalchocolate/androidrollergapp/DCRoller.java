package com.digitalchocolate.androidrollergapp;

import java.io.IOException;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* loaded from: classes.dex */
public class DCRoller extends DChocMIDlet implements ApplicationControl {
    public static boolean setBackKeyInLoadingScreen = false;
    private ImageFont buffSelectionImageFont;
    private ImageFont buffTextImageFont;
    private ImageFont buffTitlebarFont;
    private Image dchocLogoImage;
    private FlowProcessor mCurrentFlowProcessor;
    private FlowProcessor mGameFlowProcessor;
    private int mInitialFlowLoadingPercentage;
    private boolean mInitialized;
    private Image mLetterBoxImage;
    private boolean mLoading;
    private int mLoadingCounter;
    private int mMaxLoadingCount;
    private FlowProcessor mNextFlowProcessor;
    private Image mPreviewImage;
    private ImageFont mSelectionImageFont;
    private boolean mSoftKeysInitialized;
    private ImageFont mTextImageFont;
    private ImageFont mTitleBarImageFont;
    private ImageFont softKeyImageFont;

    public static void drawLoadingScreen(Graphics graphics, int i) {
        if (setBackKeyInLoadingScreen) {
            Game.removeAllSoftKeys();
            setBackKeyInLoadingScreen = false;
        }
        graphics.setColor(16777215);
        graphics.fillRect(0, 0, Toolkit.getScreenWidth(), Toolkit.getScreenHeight());
        int screenWidth = (Toolkit.getScreenWidth() * 88) / 176;
        int screenHeight = (Toolkit.getScreenHeight() << 3) / 208;
        if (screenHeight < 5) {
            screenHeight = 5;
        }
        int screenWidth2 = (Toolkit.getScreenWidth() - screenWidth) >> 1;
        int screenHeight2 = (Toolkit.getScreenHeight() - screenHeight) >> 1;
        graphics.setColor(9486550);
        graphics.drawRect(screenWidth2, screenHeight2, screenWidth - 1, screenHeight - 1);
        graphics.fillRect(screenWidth2 + 2, screenHeight2 + 2, ((screenWidth - 4) * i) / 100, screenHeight - 4);
    }

    private void drawLoadingScreenWithLogo(Graphics graphics, int i) {
        graphics.setColor(16777215);
        graphics.fillRect(0, 0, Toolkit.getScreenWidth(), Toolkit.getScreenHeight());
        graphics.drawImage(this.dchocLogoImage, Toolkit.getScreenWidth() >> 1, Toolkit.getScreenHeight() >> 1, 3);
        int screenWidth = (Toolkit.getScreenWidth() * 88) / 176;
        int screenHeight = (Toolkit.getScreenHeight() << 3) / 208;
        if (screenHeight < 5) {
            screenHeight = 5;
        }
        int screenWidth2 = (Toolkit.getScreenWidth() - screenWidth) >> 1;
        int screenHeight2 = ((Toolkit.getScreenHeight() + this.dchocLogoImage.getHeight()) + 4) >> 1;
        graphics.setColor(9486550);
        graphics.drawRect(screenWidth2, screenHeight2, screenWidth - 1, screenHeight - 1);
        graphics.fillRect(screenWidth2 + 2, screenHeight2 + 2, ((screenWidth - 4) * i) / 100, screenHeight - 4);
    }

    private void initialize() throws IOException {
        Toolkit.setBacklightControlEnabled(true);
        if (!this.mLoading) {
            this.mMaxLoadingCount = 6;
            this.mLoadingCounter = 0;
            this.dchocLogoImage = Toolkit.getImage(ResourceIDs.RID_GFX_DCHOC_LOGO);
            setBackKeyInLoadingScreen = true;
            this.mLoading = true;
            return;
        }
        if (this.mLoadingCounter >= this.mMaxLoadingCount) {
            this.mInitialized = true;
            this.mLoading = false;
            this.mNextFlowProcessor = this.mGameFlowProcessor;
            resetTimer();
            return;
        }
        switch (this.mLoadingCounter) {
            case 0:
                initializeFonts();
                initializeSoftkeys();
                Toolkit.removeAllSoftKeys();
                break;
            case 1:
                initializeGraphics();
                break;
            case 2:
                initializeScrollArrows();
                break;
            case 3:
                this.mGameFlowProcessor = new FlowProcessor(65536, new GameMobile(this, this.mTitleBarImageFont, this.mTextImageFont, this.mSelectionImageFont));
                break;
            case 4:
                CustomMenuObject.loadMenuResources();
                RollerGameEngine.initialize();
                break;
            case 5:
                for (int i = 0; i < DavinciUtilities.getAdditionalLoadingCount(); i++) {
                    DavinciUtilities.loadNext();
                }
                break;
        }
        this.mLoadingCounter++;
    }

    private void initializeFonts() {
        try {
            Font font = Font.getFont(32, 0, 0);
            Font font2 = Font.getFont(32, 1, 0);
            Font font3 = Font.getFont(32, 1, 0);
            Image image = Toolkit.getImage(ResourceIDs.RID_GFX_FONT_LIGHT);
            Image image2 = Toolkit.getImage(ResourceIDs.RID_GFX_FONT_HEAVY);
            Image image3 = Toolkit.getImage(524288);
            this.mTextImageFont = new ImageFont(image, Toolkit.getResourceStream(ResourceIDs.RID_DAT_FONT_LIGHT), font, 16777215, -1);
            this.mSelectionImageFont = new ImageFont(image2, Toolkit.getResourceStream(ResourceIDs.RID_DAT_FONT_HEAVY), font2, 16777215, 0);
            this.mTitleBarImageFont = new ImageFont(image3, Toolkit.getResourceStream(ResourceIDs.RID_DAT_FONT_HEADLINES), font3, 16776960, 0);
            MenuObject.setDefaultImageFonts(this.mTitleBarImageFont, this.mTextImageFont, this.mSelectionImageFont);
            Toolkit.setSoftkeyImageFont(this.mSelectionImageFont);
        } catch (IOException e) {
        }
    }

    private void initializeGraphics() {
        DavinciUtilities.initialize();
    }

    private void initializeScrollArrows() {
        MenuObject.setScrollArrowsDvc(new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_MENU_SCROLL_ARROW_UP, ResourceIDs.ANM_MENU_SCROLL_ARROW_UP_CLICK, ResourceIDs.ANM_MENU_SCROLL_ARROW_UP_PRESSED}, false), true), new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_MENU_SCROLL_ARROW_DOWN, ResourceIDs.ANM_MENU_SCROLL_ARROW_DOWN_CLICK, ResourceIDs.ANM_MENU_SCROLL_ARROW_DOWN_PRESSED}, false), true));
        MenuObject.setHorizontalScrollArrows(new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_MENU_SCROLL_ARROW_LEFT, ResourceIDs.ANM_MENU_SCROLL_ARROW_LEFT_CLICK}, false), true), new SpriteObject(DavinciUtilities.loadAnimations(new int[]{ResourceIDs.ANM_MENU_SCROLL_ARROW_RIGHT, ResourceIDs.ANM_MENU_SCROLL_ARROW_RIGHT_CLICK}, false), true));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v4, types: [int] */
    private void initializeSoftkeys() throws IOException {
        Image image;
        if (this.mSoftKeysInitialized) {
            return;
        }
        byte[] resourceBytes = Toolkit.getResourceBytes(ResourceIDs.RID_SOFTKEYS);
        byte b = resourceBytes[0];
        int i = 1;
        while (b > 0) {
            int i2 = i + 1;
            byte b2 = resourceBytes[i];
            int i3 = i2 + 1;
            int i4 = i3 + 1;
            int i5 = ((resourceBytes[i2] & DChocImage.COLOR_DEPTH_DEFAULT) << 24) | ((resourceBytes[i3] & DChocImage.COLOR_DEPTH_DEFAULT) << 16);
            int i6 = i4 + 1;
            int i7 = i5 | ((resourceBytes[i4] & DChocImage.COLOR_DEPTH_DEFAULT) << 8);
            int i8 = i6 + 1;
            int i9 = i7 | (resourceBytes[i6] & DChocImage.COLOR_DEPTH_DEFAULT);
            int i10 = i8 + 1;
            int i11 = i10 + 1;
            int i12 = ((resourceBytes[i10] & DChocImage.COLOR_DEPTH_DEFAULT) << 16) | ((resourceBytes[i8] & DChocImage.COLOR_DEPTH_DEFAULT) << 24);
            int i13 = i11 + 1;
            int i14 = i12 | ((resourceBytes[i11] & DChocImage.COLOR_DEPTH_DEFAULT) << 8);
            int i15 = i13 + 1;
            int i16 = i14 | (resourceBytes[i13] & DChocImage.COLOR_DEPTH_DEFAULT);
            int i17 = i15 + 1;
            byte b3 = resourceBytes[i15];
            if (i16 != -1) {
                Image image2 = DavinciUtilities.getImage(i16, 0, 1024);
                image = image2 == null ? Toolkit.getImage(i16) : image2;
            } else {
                image = null;
            }
            Toolkit.createSoftKey(b2, i9, image, b3);
            b--;
            i = i17;
        }
        this.mSoftKeysInitialized = true;
    }

    private void initializeSystemFonts() {
        try {
            Font font = Font.getFont(32, 0, 0);
            Font font2 = Font.getFont(32, 1, 0);
            Font font3 = Font.getFont(32, 1, 0);
            this.mTextImageFont = new ImageFont(null, null, font, 16777215, -1);
            this.mSelectionImageFont = new ImageFont(null, null, font2, 16777215, 0);
            this.mTitleBarImageFont = new ImageFont(null, null, font3, 16776960, 0);
            MenuObject.setDefaultImageFonts(this.mTitleBarImageFont, this.mTextImageFont, this.mSelectionImageFont);
            Toolkit.setSoftkeyImageFont(this.mSelectionImageFont);
        } catch (IOException e) {
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.DChocMIDlet
    public void appEventOccurred(int i) {
        if (this.mLoading || this.mCurrentFlowProcessor == null) {
            return;
        }
        if (i == 1) {
            this.mCurrentFlowProcessor.pause();
        } else if (i == 7) {
            this.mCurrentFlowProcessor.controllerActivated();
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.DChocMIDlet
    public void controllerEventOccurred(int i, int i2, int i3, int i4, int i5) {
        DCThrillOld.controllerEventOccurred(i, i2, i3, i4, i5);
    }

    @Override // com.digitalchocolate.androidrollergapp.DChocMIDlet
    public void doDraw(Graphics graphics) {
        try {
            if (this.mLoading) {
                drawLoadingScreenWithLogo(graphics, (this.mLoadingCounter * 100) / this.mMaxLoadingCount);
            } else if (this.mCurrentFlowProcessor != null) {
                if (this.mInitialFlowLoadingPercentage != -1) {
                    int i = (this.mInitialFlowLoadingPercentage / 2) + 50;
                    drawLoadingScreen(graphics, this.mInitialFlowLoadingPercentage);
                } else {
                    this.mCurrentFlowProcessor.doDraw(graphics);
                }
            }
        } catch (Exception e) {
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.DChocMIDlet
    public void drawLetterBox(Graphics graphics, int i, int i2, int i3) {
    }

    @Override // com.digitalchocolate.androidrollergapp.DChocMIDlet
    public String getFormattedScore(int i, int i2, int i3) {
        return "" + i3;
    }

    @Override // com.digitalchocolate.androidrollergapp.DChocMIDlet
    public String[][] getHighscoreTables() {
        return new String[][]{new String[]{Toolkit.getText(-2)}};
    }

    @Override // com.digitalchocolate.androidrollergapp.DChocMIDlet
    public void keyEventOccurred(int i, int i2) {
        if (!this.mLoading) {
            if (this.mCurrentFlowProcessor != null) {
                this.mCurrentFlowProcessor.keyEventOccurred(i, i2);
            }
        } else if (i2 == 3 && i == 6) {
            DChocMIDlet.getInstance().m_exitApplication = true;
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.DChocMIDlet
    public void logicUpdate(int i) {
        try {
            if (!this.mInitialized) {
                initialize();
                return;
            }
            if (this.mNextFlowProcessor != this.mCurrentFlowProcessor) {
                this.mCurrentFlowProcessor = this.mNextFlowProcessor;
            }
            if (this.mCurrentFlowProcessor != null) {
                this.mCurrentFlowProcessor.logicUpdate(i);
                if (this.mInitialFlowLoadingPercentage != -1) {
                    this.mInitialFlowLoadingPercentage = this.mCurrentFlowProcessor.getLoadingPercentage();
                }
            }
        } catch (Exception e) {
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.DChocMIDlet
    public void pointerEventOccurred(int i, int i2, int i3) {
        if (this.mLoading || this.mCurrentFlowProcessor == null) {
            return;
        }
        this.mCurrentFlowProcessor.pointerEventOccurred(i, i2, i3);
    }

    @Override // com.digitalchocolate.androidrollergapp.ApplicationControl
    public void setLanguage(int i) throws IOException {
        if (this.mInitialized) {
            this.mGameFlowProcessor.languageChanged();
        }
        String selectedLanguageCode = Toolkit.getSelectedLanguageCode();
        boolean z = selectedLanguageCode.equals("ru") || selectedLanguageCode.equals("zt") || selectedLanguageCode.equals("zs") || selectedLanguageCode.equals("zh");
        Toolkit.setSelectedLanguage(i);
        String selectedLanguageCode2 = Toolkit.getSelectedLanguageCode();
        if (selectedLanguageCode2.equals("ru") || selectedLanguageCode2.equals("zt") || selectedLanguageCode2.equals("zs") || selectedLanguageCode2.equals("zh")) {
            if (!z) {
                this.buffTextImageFont = this.mTextImageFont;
                this.buffSelectionImageFont = this.mSelectionImageFont;
                this.buffTitlebarFont = this.mTitleBarImageFont;
            }
            initializeSystemFonts();
            GameMobile.setFonts(this.mTitleBarImageFont, this.mTextImageFont, this.mSelectionImageFont);
        } else if (z) {
            this.mTextImageFont = this.buffTextImageFont;
            this.mSelectionImageFont = this.buffSelectionImageFont;
            this.mTitleBarImageFont = this.buffTitlebarFont;
            MenuObject.setDefaultImageFonts(this.mTitleBarImageFont, this.mTextImageFont, this.mSelectionImageFont);
            GameMobile.setFonts(this.mTitleBarImageFont, this.mTextImageFont, this.mSelectionImageFont);
        }
        Toolkit.setSoftkeyImageFont(this.mSelectionImageFont);
        this.mSoftKeysInitialized = false;
        initializeSoftkeys();
    }

    @Override // com.digitalchocolate.androidrollergapp.ApplicationControl
    public void switchFlowProcessor(int i) {
        this.mNextFlowProcessor = this.mGameFlowProcessor;
    }
}
