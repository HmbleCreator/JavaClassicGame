package com.digitalchocolate.androidrollergapp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class ReferenceHighScore implements IHighScore {
    private static final int COLUMN_COUNT = 1;
    private static final int INT_TABLE_SIZE = 5;
    private static final int INT_VAR_CURRENT_SCREEN = 3;
    private static final int INT_VAR_CURRENT_TABLE_ID = 2;
    private static final int INT_VAR_NUMBER_OF_COLUMNS = 1;
    private static final int INT_VAR_NUMBER_OF_TABLES = 0;
    private static final int INT_VAR_PLAYER_RANK = 4;
    private static final int KEY_COLUMN = 0;
    private static final boolean LOWEST_SCORE_IS_BEST = false;
    private static final int MAX_NUMBER_OF_SCORES = 5;
    private static final int MENU_NO_EVENT = -1;
    private static final String RMS_NAME = "hs";
    private static final int SCREEN_COUNT = 2;
    private static final int SCREEN_EXIT = -7;
    private static final int SCREEN_TABLE_SELECTION = 0;
    private static final int SCREEN_VIEW_SCORES = 1;
    private static int[] m_intVars;
    private static int[][][] m_ownScores;
    private static String[][] m_scoreTableStrings;
    private boolean m_comingFromGame;
    private IMenu m_currentMenu;
    private int[] m_lastScreens;
    private IMenu[] m_menus;

    public ReferenceHighScore() throws IOException {
        m_intVars = new int[5];
        m_intVars[0] = DChocMIDlet.getInstance().getHighscoreTables().length;
        m_intVars[1] = 1;
        m_ownScores = (int[][][]) Array.newInstance((Class<?>) Integer.TYPE, m_intVars[0], 6, m_intVars[1]);
        readRecordStore();
    }

    public static IHighScore getHighScoreManager() {
        return new ReferenceHighScore();
    }

    private String getScoreString(int i, int i2) {
        String str = i2 + ". ";
        for (int i3 = 0; i3 < 1; i3++) {
            str = str + DChocMIDlet.getInstance().getFormattedScore(i, i3, m_ownScores[m_intVars[2]][i2 - 1][i3]);
            if (i2 != 0) {
                str = str + " ";
            }
        }
        return str;
    }

    private int localProcessEvents(int[] iArr) {
        if (iArr[0] == 0) {
            if (iArr[1] == 5) {
                m_intVars[3] = this.m_lastScreens[m_intVars[3]];
                if (m_intVars[3] == -7) {
                    this.m_currentMenu = null;
                    this.m_menus = null;
                    return 1;
                }
            }
        } else if (iArr[0] == 1 && m_intVars[3] == 0) {
            m_intVars[2] = iArr[1];
            m_intVars[3] = 1;
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002f A[PHI: r2
  0x002f: PHI (r2v6 int) = (r2v4 int), (r2v5 int) binds: [B:13:0x002d, B:18:0x004e] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int localUpdateScores(int r8, int[] r9, java.lang.String r10) throws java.io.IOException {
        /*
            r7 = this;
            r4 = 4
            r6 = 0
            r0 = 6
            r1 = 1
            int[] r1 = new int[r1]
            r2 = r6
        L7:
            int r3 = r9.length
            if (r2 >= r3) goto L11
            r3 = r9[r2]
            r1[r2] = r3
            int r2 = r2 + 1
            goto L7
        L11:
            r2 = r1[r6]
            int[][][] r3 = com.digitalchocolate.androidrollergapp.ReferenceHighScore.m_ownScores
            r3 = r3[r8]
            r3 = r3[r4]
            r3 = r3[r6]
            if (r2 > r3) goto L1f
            r0 = -1
        L1e:
            return r0
        L1f:
            r2 = r0
            r0 = r4
        L21:
            if (r0 < 0) goto L65
            r3 = r1[r6]
            int[][][] r4 = com.digitalchocolate.androidrollergapp.ReferenceHighScore.m_ownScores
            r4 = r4[r8]
            r4 = r4[r0]
            r4 = r4[r6]
            if (r3 > r4) goto L3e
        L2f:
            r3 = r1[r6]
            int[][][] r4 = com.digitalchocolate.androidrollergapp.ReferenceHighScore.m_ownScores
            r4 = r4[r8]
            r4 = r4[r0]
            r4 = r4[r6]
            if (r3 <= r4) goto L5b
            int r0 = r0 + (-1)
            goto L21
        L3e:
            int[][][] r3 = com.digitalchocolate.androidrollergapp.ReferenceHighScore.m_ownScores
            r3 = r3[r8]
            int r4 = r0 + 1
            int[][][] r5 = com.digitalchocolate.androidrollergapp.ReferenceHighScore.m_ownScores
            r5 = r5[r8]
            r5 = r5[r0]
            r3[r4] = r5
            int r2 = r2 + (-1)
            if (r0 != 0) goto L2f
            int[][][] r3 = com.digitalchocolate.androidrollergapp.ReferenceHighScore.m_ownScores
            r3 = r3[r8]
            r3[r0] = r1
            r0 = r2
        L57:
            r7.writeRecordStore()
            goto L1e
        L5b:
            int[][][] r3 = com.digitalchocolate.androidrollergapp.ReferenceHighScore.m_ownScores
            r3 = r3[r8]
            int r0 = r0 + 1
            r3[r0] = r1
            r0 = r2
            goto L57
        L65:
            r0 = r2
            goto L57
        */
        throw new UnsupportedOperationException("Method not decompiled: com.digitalchocolate.androidrollergapp.ReferenceHighScore.localUpdateScores(int, int[], java.lang.String):int");
    }

    private void readRecordStore() throws IOException {
        try {
            byte[] record = Toolkit.readRecord(RMS_NAME);
            if (record != null) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(record);
                DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                for (int i = 0; i < m_intVars[0]; i++) {
                    for (int i2 = 0; i2 < 5; i2++) {
                        for (int i3 = 0; i3 < m_intVars[1]; i3++) {
                            m_ownScores[i][i2][i3] = dataInputStream.readInt();
                        }
                    }
                }
                dataInputStream.close();
                byteArrayInputStream.close();
            }
        } catch (Exception e) {
        }
    }

    private void writeRecordStore() throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            for (int i = 0; i < m_intVars[0]; i++) {
                for (int i2 = 0; i2 < 5; i2++) {
                    for (int i3 = 0; i3 < m_intVars[1]; i3++) {
                        dataOutputStream.writeInt(m_ownScores[i][i2][i3]);
                    }
                }
            }
            Toolkit.writeRecord(RMS_NAME, byteArrayOutputStream.toByteArray());
            dataOutputStream.close();
            byteArrayOutputStream.close();
        } catch (Exception e) {
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IHighScore
    public void clearScores() throws IOException {
        for (int i = 0; i < m_intVars[0]; i++) {
            for (int i2 = 0; i2 < 5; i2++) {
                for (int i3 = 0; i3 < m_intVars[1]; i3++) {
                    m_ownScores[i][i2][i3] = 0;
                }
            }
        }
        writeRecordStore();
    }

    @Override // com.digitalchocolate.androidrollergapp.IHighScore
    public void doDraw(Graphics graphics) {
        if (this.m_currentMenu != null) {
            this.m_currentMenu.doDraw(graphics, 0, 0);
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IHighScore
    public String getHighScoreMenuItemLabel() {
        return Toolkit.getText(-2);
    }

    @Override // com.digitalchocolate.androidrollergapp.IHighScore
    public String getPlayerName() {
        return null;
    }

    @Override // com.digitalchocolate.androidrollergapp.IHighScore
    public int[] getScore(int i, int i2) {
        return m_ownScores[i][i2 - 1];
    }

    @Override // com.digitalchocolate.androidrollergapp.IHighScore
    public void initMenus() {
        m_scoreTableStrings = DChocMIDlet.getInstance().getHighscoreTables();
        this.m_menus = new IMenu[2];
        this.m_lastScreens = new int[2];
        if (m_intVars[0] != 1) {
            this.m_menus[0] = DChocMIDlet.getInstance().getNewMenuObject();
            this.m_lastScreens[0] = -7;
            this.m_menus[0].setScreen(0, m_intVars[0], 0);
            this.m_menus[0].setTitleBar(Toolkit.getText(-2), null, 1);
            for (int i = 0; i < m_intVars[0]; i++) {
                this.m_menus[0].setItem(i, 0, m_scoreTableStrings[i][0], null, i);
            }
            this.m_menus[0].setSoftkey(0, 0);
            this.m_menus[0].setSoftkey(5, 1);
            this.m_menus[0].setSize(Toolkit.getScreenWidth(), Toolkit.getScreenHeight());
        }
        this.m_menus[1] = DChocMIDlet.getInstance().getNewMenuObject();
        this.m_menus[1].setScreen(1, 1, 0);
        this.m_menus[1].setSoftkey(5, 1);
    }

    @Override // com.digitalchocolate.androidrollergapp.IHighScore
    public int insertScore(int i, int[] iArr, String str) {
        m_intVars[2] = i;
        m_intVars[4] = localUpdateScores(i, iArr, str);
        return m_intVars[4];
    }

    @Override // com.digitalchocolate.androidrollergapp.IHighScore
    public void keyEventOccurred(int i, int i2) {
        if (this.m_currentMenu != null) {
            this.m_currentMenu.keyEventOccurred(i, i2);
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IHighScore
    public int logicUpdate(int i) {
        if (this.m_menus == null) {
            initMenus();
        }
        if (this.m_currentMenu == this.m_menus[m_intVars[3]]) {
            int[] iArrLogicUpdate = this.m_currentMenu.logicUpdate(i);
            if (iArrLogicUpdate != null) {
                return localProcessEvents(iArrLogicUpdate);
            }
            return 0;
        }
        this.m_currentMenu = this.m_menus[m_intVars[3]];
        if (m_intVars[3] == 1) {
            if (this.m_comingFromGame || m_intVars[0] <= 1) {
                this.m_lastScreens[1] = -7;
            } else {
                this.m_lastScreens[1] = 0;
            }
            this.m_currentMenu.setTitleBar(m_scoreTableStrings[m_intVars[2]][0], null, 1);
            String str = "";
            for (int i2 = 1; i2 <= 5; i2++) {
                str = str + getScoreString(m_intVars[2], i2);
                if (i2 < 5) {
                    str = str + "\\n";
                }
            }
            this.m_currentMenu.setItem(0, 1, str, null, -1);
            this.m_currentMenu.setSize(Toolkit.getScreenWidth(), Toolkit.getScreenHeight());
        }
        this.m_currentMenu.setVisible();
        return 0;
    }

    @Override // com.digitalchocolate.androidrollergapp.IHighScore
    public void pointerEventOccurred(int i, int i2, int i3) {
        if (this.m_currentMenu != null) {
            this.m_currentMenu.pointerEventOccurred(i, i2, i3);
        }
    }

    @Override // com.digitalchocolate.androidrollergapp.IHighScore
    public boolean setState(int i) {
        this.m_comingFromGame = i == 1;
        if (m_intVars[0] == 1 || this.m_comingFromGame) {
            m_intVars[3] = 1;
        } else {
            m_intVars[3] = 0;
        }
        return (i == 2 || i == 1) ? false : true;
    }
}
