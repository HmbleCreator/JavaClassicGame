package com.digitalchocolate.androidrollergapp;

import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class IReg {
    private static final int INTERNAL_STATE_FINISHED = 2;
    private static final int INTERNAL_STATE_SHOW_MENU = 0;
    private static final int INTERNAL_STATE_SHOW_RESPONSE = 1;
    public static final int IREG_CONFIG_EXCLUSIVE_CONTENT_IN_FREE = 2;
    public static final int IREG_CONFIG_EXCLUSIVE_CONTENT_IN_FULL = 3;
    public static final int IREG_CONFIG_NO_EXCLUSIVE_CONTENT = 1;
    public static final int IREG_TEXT_BULLET1 = 2;
    public static final int IREG_TEXT_BULLET2 = 3;
    public static final int IREG_TEXT_BULLET3 = 4;
    public static final int IREG_TEXT_CALL_TO_ACTION = 1;
    public static final int IREG_TEXT_HEADING = 0;
    public static final int IREG_TEXT_RESPONSE_MESSAGE = 5;
    private static final String RECORD_REGISTERED = "iReg.registered";
    public static final int STATE_DONE = 1;
    public static final int STATE_NOT_FINISHED = 0;
    private static IMenu sm_menu = null;
    private static IMenu sm_response = null;
    private static boolean sm_firstTime = true;
    private static String[] sm_texts = {"Heading", "Call to action", "Bullet 1", "Bullet 2", "Bullet 3", "Response message"};
    private static int sm_state = 0;

    public static void doDraw(Graphics graphics) {
        if (sm_state == 0) {
            sm_menu.doDraw(graphics, 0, 0);
        } else {
            sm_response.doDraw(graphics, 0, 0);
        }
    }

    public static void iRegCancelRegistration() {
        sm_state = 2;
        Toolkit.writeRecord(RECORD_REGISTERED, new byte[]{0});
        DChocMIDlet.getInstance().iRegNotifyRegistrationEvent(false, true);
    }

    public static boolean iRegIsRegistered() {
        byte[] record = Toolkit.readRecord(RECORD_REGISTERED);
        return (record == null || record[0] == 0) ? false : true;
    }

    public static void iRegStartRegistration() {
        sm_menu = DChocMIDlet.getInstance().getNewMenuObject();
        sm_menu.setScreen(1, 5, 1);
        sm_menu.setTitleBar(sm_texts[0], null, 1);
        sm_menu.setItem(0, 1, sm_texts[1], null, 0);
        sm_menu.setItem(1, 1, sm_texts[2], null, 0);
        sm_menu.setItem(2, 1, sm_texts[3], null, 0);
        sm_menu.setItem(3, 1, sm_texts[4], null, 0);
        sm_menu.setSoftkey(1, 0);
        sm_menu.setSoftkey(11, 1);
        sm_menu.setSize(Toolkit.getScreenWidth(), Toolkit.getScreenHeight());
        sm_response = DChocMIDlet.getInstance().getNewMenuObject();
        sm_response.setScreen(1, 1, 1);
        sm_response.setTitleBar(sm_texts[0], null, 1);
        sm_response.setItem(0, 1, sm_texts[5], null, 0);
        sm_response.setSoftkey(1, 0);
        sm_response.setSize(Toolkit.getScreenWidth(), Toolkit.getScreenHeight());
        sm_firstTime = true;
        sm_state = 0;
    }

    public static void keyEventOccurred(int i, int i2) {
        if (i2 == 3) {
            if (sm_state != 0) {
                if (i == 1) {
                    sm_state = 2;
                    return;
                }
                return;
            }
            if (i == 1) {
                Toolkit.writeRecord(RECORD_REGISTERED, new byte[]{1});
                sm_response.setVisible();
                sm_state = 1;
                DChocMIDlet.getInstance().iRegNotifyRegistrationEvent(true, true);
            }
            if (i == 11) {
                Toolkit.writeRecord(RECORD_REGISTERED, new byte[]{0});
                sm_state = 2;
                DChocMIDlet.getInstance().iRegNotifyRegistrationEvent(false, true);
            }
        }
    }

    public static int logicUpdate(int i) {
        if (sm_firstTime) {
            sm_menu.setVisible();
            sm_firstTime = false;
        }
        return sm_state != 2 ? 0 : 1;
    }

    public static void pointerEventOccurred(int i, int i2, int i3) {
    }

    void iRegSetText(int i, String str) {
        sm_texts[i] = str;
    }
}
