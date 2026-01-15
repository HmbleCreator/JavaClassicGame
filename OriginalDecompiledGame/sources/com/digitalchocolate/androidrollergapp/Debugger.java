package com.digitalchocolate.androidrollergapp;

import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class Debugger {
    public static final boolean ASSERT_ON = false;
    private static final int BUFFER_SIZE = 5;
    private static final int CLEAR_TIME = 10000;
    public static final boolean DEBUG = false;
    private static final int DEBUGGER_TEXT_BACKGROUND_COLOR = 0;
    private static final int DEBUGGER_TEXT_COLOR = 16777215;
    public static final boolean DEBUG_TOOLKIT = false;
    public static final boolean EXCEPTION_ON = false;
    public static final boolean PP_VERBOSE_ON = false;
    public static final boolean PROFILER_ON = false;
    public static final boolean USE_RMS = false;
    public static final boolean VERBOSE_ON = false;
    private static long sm_timer;
    private static String[] sm_buffer = new String[5];
    private static int sm_bufferIndex = 0;
    private static String sm_timerString = "";
    private static String sm_heapUsage = "";
    private static boolean sm_read = false;
    private static long sm_prevTime = 0;
    private static long sm_clearTimer = 10000;
    private static Font sm_font = Font.getFont(32, 0, 0);
    private static int sm_heartBeat = 0;

    private static void addToBuffer(String str) {
        sm_buffer[sm_bufferIndex] = str;
        sm_bufferIndex++;
        sm_bufferIndex %= 5;
        sm_clearTimer = 10000L;
    }

    public static void clear() {
        for (int i = 0; i < sm_buffer.length; i++) {
            sm_buffer[i] = null;
        }
        sm_timerString = "";
        sm_heapUsage = "";
    }

    public static void doAssert(boolean z, String str) {
    }

    public static void doDraw(Graphics graphics) {
        int i;
        int i2;
        long jCurrentTimeMillis = System.currentTimeMillis();
        Font font = graphics.getFont();
        graphics.setFont(sm_font);
        int height = sm_font.getHeight() + 3;
        if (sm_prevTime != 0) {
            sm_clearTimer -= jCurrentTimeMillis - sm_prevTime;
        }
        sm_prevTime = jCurrentTimeMillis;
        if (sm_clearTimer <= 0) {
            clear();
            sm_clearTimer = 10000L;
        }
        int screenWidth = Toolkit.getScreenWidth() >> 3;
        graphics.setColor(0);
        graphics.fillRect((sm_heartBeat & 7) * screenWidth, 0, screenWidth, 2);
        graphics.setColor(16777215);
        graphics.fillRect(((sm_heartBeat + 1) & 7) * screenWidth, 0, screenWidth, 2);
        sm_heartBeat = (sm_heartBeat + 1) & 7;
        if (sm_bufferIndex > 0) {
            i = sm_bufferIndex - 1;
            i2 = 0;
        } else {
            i = 4;
            i2 = 0;
        }
        do {
            if (sm_buffer[i] != null) {
                String[] strArrSplitString = splitString(sm_buffer[i], sm_font, Toolkit.getScreenWidth());
                int i3 = i2;
                for (int i4 = 0; i4 < strArrSplitString.length; i4++) {
                    graphics.setColor(0);
                    graphics.drawString(strArrSplitString[i4], 1, i3 - 1, 20);
                    graphics.drawString(strArrSplitString[i4], 1, i3 + 1, 20);
                    graphics.drawString(strArrSplitString[i4], 0, i3, 20);
                    graphics.drawString(strArrSplitString[i4], 2, i3, 20);
                    graphics.setColor(16777215);
                    graphics.drawString(strArrSplitString[i4], 1, i3, 20);
                    i3 += height;
                }
                i2 = i3;
            }
            i--;
            if (i < 0) {
                i = 4;
            }
        } while (i != sm_bufferIndex);
        graphics.setFont(font);
    }

    public static void exceptionCaught(Throwable th, String str) {
    }

    public static void heapUsage() {
    }

    private static String[] splitString(String str, Font font, int i) {
        int length = str.length();
        Vector vector = new Vector();
        int iIndexOf = 0;
        int i2 = 0;
        while (iIndexOf < length) {
            iIndexOf = str.indexOf("\\n", i2);
            if (iIndexOf == -1) {
                iIndexOf = length;
            }
            int i3 = i2;
            boolean z = false;
            while (!z) {
                int i4 = -1;
                int iStringWidth = 0;
                int i5 = i3;
                while (iStringWidth < i && i5 < iIndexOf) {
                    char cCharAt = str.charAt(i5);
                    iStringWidth += font.stringWidth("" + cCharAt);
                    i5++;
                    if (cCharAt == ' ') {
                        i4 = i5;
                    }
                }
                if (i5 == iIndexOf && iStringWidth <= i) {
                    z = true;
                    i4 = i5;
                } else if (i4 == -1) {
                    i4 = i5 - 1;
                }
                vector.addElement(str.substring(i3, i4));
                i3 = (!z || i4 >= length) ? i4 : i4 + 2;
            }
            i2 = i3;
        }
        String[] strArr = new String[vector.size()];
        for (int i6 = 0; i6 < strArr.length; i6++) {
            strArr[i6] = ((String) vector.elementAt(i6)).trim();
        }
        return strArr;
    }

    public static void startTimer() {
    }

    public static void stopTimer() {
    }

    public static void verbose(String str) {
    }

    public static void verbosePP(String str) {
    }
}
