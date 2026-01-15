package com.digitalchocolate.androidrollergapp;

import javax.microedition.lcdui.Font;

/* loaded from: classes.dex */
public abstract class Game implements FlowHandler {
    public static final int GAME_EVENT_BACK_FROM_GALLERY = 52;
    public static final int GAME_EVENT_BACK_SELECT_TRACK = 14;
    public static final int GAME_EVENT_BACK_THEME = 12;
    public static final int GAME_EVENT_FINISH_CAREER = 17;
    public static final int GAME_EVENT_FINISH_HOTSEAT = 19;
    public static final int GAME_EVENT_FINISH_SURVIVAL = 20;
    public static final int GAME_EVENT_GO_BACK = 10;
    public static final int GAME_EVENT_GO_CUTSCENE_START = 13;
    public static final int GAME_EVENT_GO_MENU = 29;
    public static final int GAME_EVENT_GO_PLAY_MENU = 48;
    public static final int GAME_EVENT_GO_PREVIEW = 11;
    public static final int GAME_EVENT_GO_SELECT_PARK = 9;
    public static final int GAME_EVENT_GO_SELECT_TRACK = 47;
    public static final int GAME_EVENT_LAUNCH_CHEATS_MENU = 18;
    public static final int GAME_EVENT_NEXT_LEVEL = 28;
    public static final int GAME_EVENT_PLAY_GAME = 5;
    public static final int GAME_EVENT_RESET_GAME = 60;
    public static final int GAME_STATEGROUP_GAME_SCREENS = 2;
    public static final int GAME_STATEGROUP_INTRO = 9;
    public static final int GAME_STATEGROUP_MAIN_MENUS = 0;
    public static final int GAME_STATEGROUP_SPLASH_SCREENS = 5;
    public static final int GAME_STATEGROUP_STARTUP = 3;
    public static final int GAME_STATE_ABOUT = 3;
    public static final int GAME_STATE_CHEATS_GAME = 24;
    public static final int GAME_STATE_CONTINUE = 21;
    public static final int GAME_STATE_GAME = 50;
    public static final int GAME_STATE_GET_MORE_GAMES = 46;
    public static final int GAME_STATE_INTRO = 62;
    public static final int GAME_STATE_LANGUAGE_QUERY = 8;
    public static final int GAME_STATE_LICENSE_MANAGER_ACTIVATED = 48;
    public static final int GAME_STATE_LICENSE_MANAGER_APP_START = 47;
    public static final int GAME_STATE_LICENSE_MANAGER_FROM_MENU = 49;
    public static final int GAME_STATE_LICENSE_MANAGER_GAME_END = 53;
    public static final int GAME_STATE_LICENSE_MANAGER_GAME_START = 52;
    public static final int GAME_STATE_MAIN_MENU = 0;
    public static final int GAME_STATE_PAUSE_MENU = 10;
    public static final int GAME_STATE_RESULTS = 58;
    public static final int GAME_STATE_SETTINGS = 4;
    public static final int GAME_STATE_SETTINGS_LANGUAGE = 9;
    public static final int GAME_STATE_SOUNDS_QUERY = 7;
    public static final int GAME_STATE_SPLASH = 42;
    public static final int GAME_STATE_TELL_A_FRIEND = 45;
    public static final int GAME_STATE_TITLE = 43;
    public static final int GAME_STATE_TRACK_PREVIEW = 57;
    protected static final int NUM_PROFILES = 1;
    public static int smCurrentState;
    public static int smGhostBeatenBonus;
    public static Font smHeavyFont;
    public static int smLoadingTip;
    public static int smNumPlayerToPlay;
    public static int smNumProfile;
    public static Profile[] smProfile = new Profile[1];
    public static ImageFont smSelectionFont;
    public static ImageFont smTextFont;
    public static ImageFont smTitleFont;

    public static final int getGameAreaWidth() {
        return Toolkit.getScreenWidth();
    }

    public static final int getSoftKeyAreaHeight() {
        return Toolkit.getSoftKeyAreaHeight();
    }

    public static void loadHiscoresResources(MenuObject menuObject) {
        int i;
        String[] strArr = new String[10];
        String[] strArr2 = new String[10];
        strArr2[0] = Toolkit.getText(TextIDs.TID_RANKING_1);
        strArr2[1] = Toolkit.getText(TextIDs.TID_RANKING_2);
        strArr2[2] = Toolkit.getText(140);
        strArr2[3] = Toolkit.getText(TextIDs.TID_RANKING_4);
        strArr2[4] = Toolkit.getText(TextIDs.TID_RANKING_5);
        strArr2[5] = Toolkit.getText(TextIDs.TID_RANKING_6);
        strArr2[6] = Toolkit.getText(144);
        strArr2[7] = Toolkit.getText(145);
        strArr2[8] = Toolkit.getText(146);
        strArr2[9] = Toolkit.getText(147);
        int totalScore = smProfile[smNumProfile].getTotalScore();
        strArr[0] = Profile.HISCORE_POINTS[0] + "";
        strArr[1] = Profile.HISCORE_POINTS[1] + "";
        strArr[2] = Profile.HISCORE_POINTS[2] + "";
        strArr[3] = Profile.HISCORE_POINTS[3] + "";
        strArr[4] = Profile.HISCORE_POINTS[4] + "";
        strArr[5] = Profile.HISCORE_POINTS[5] + "";
        strArr[6] = Profile.HISCORE_POINTS[6] + "";
        strArr[7] = Profile.HISCORE_POINTS[7] + "";
        strArr[8] = Profile.HISCORE_POINTS[8] + "";
        strArr[9] = totalScore + "";
        int i2 = 9;
        int[] iArr = new int[10];
        for (int i3 = 0; i3 < 10; i3++) {
            iArr[i3] = Profile.HISCORE_POINTS[i3];
        }
        iArr[9] = totalScore;
        int i4 = 9;
        while (true) {
            i = i2;
            if (i4 < 1) {
                break;
            }
            if (iArr[i4] >= iArr[i4 - 1]) {
                i2 = i4 - 1;
                int i5 = iArr[i4 - 1];
                iArr[i4 - 1] = iArr[i4];
                iArr[i4] = i5;
                String str = strArr2[i4 - 1];
                strArr2[i4 - 1] = strArr2[i4];
                strArr2[i4] = str;
                String str2 = strArr[i4 - 1];
                strArr[i4 - 1] = strArr[i4];
                strArr[i4] = str2;
            } else {
                i2 = i;
            }
            i4--;
        }
        menuObject.setScreen(1, 10, 3);
        menuObject.setTitleBar(Toolkit.getText(80), null, 3);
        for (int i6 = 0; i6 < 10; i6++) {
            menuObject.setTableItemDvc(i6, new int[]{14, 56, 30}, new int[]{6, 2, 6}, new String[]{(i6 + 1) + ".", "" + strArr2[i6], iArr[i6] + ""}, null);
        }
        menuObject.setSoftkey(5, 1);
        menuObject.setSelectedItem(i);
        menuObject.setSize(menuObject.mWidth, menuObject.mHeight);
        menuObject.mScrollingEvent = 1;
        menuObject.logicUpdate(0);
        menuObject.mScrollingEvent = 0;
    }

    public static void removeAllSoftKeys() {
        Toolkit.removeAllSoftKeys();
    }

    public static void setFonts(ImageFont imageFont, ImageFont imageFont2, ImageFont imageFont3) {
        smTitleFont = imageFont;
        smTextFont = imageFont2;
        smSelectionFont = imageFont3;
        smHeavyFont = Font.getFont(32, 1, 0);
    }

    public static void setSoftKey(int i, int i2) {
        Toolkit.setSoftKey(i, i2);
    }
}
