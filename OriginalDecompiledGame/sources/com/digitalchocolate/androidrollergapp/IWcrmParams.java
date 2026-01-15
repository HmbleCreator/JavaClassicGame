package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public interface IWcrmParams {
    public static final int APPLICATION_FRIENDS_TOTAL = 20;
    public static final int CAMPAIGN_CHANNEL = 26;
    public static final int CAMPAIGN_SOURCE = 25;
    public static final int FRIENDS_TOTAL = 19;
    public static final int GAME_CURRENCY_BALANCE = 11;
    public static final int GAME_CURRENCY_DELTA = 10;
    public static final int GAME_CURRENCY_TOTAL = 12;
    public static final int IS_BOOKMARKED = 22;
    public static final int IS_FAN = 21;
    public static final int LEVEL = 6;
    public static final int OTHER_USER_ID = 0;
    public static final int P1 = 1;
    public static final int P2 = 2;
    public static final int P3 = 3;
    public static final int P4 = 4;
    public static final int P5 = 5;
    public static final int PAID_CURRENCY_BALANCE = 14;
    public static final int PAID_CURRENCY_DELTA = 13;
    public static final int PAID_CURRENCY_TOTAL = 15;
    public static final int PARENT_EVENT_ID = 30;
    public static final int PRODUCT = 23;
    public static final int PRODUCT_DETAIL = 24;
    public static final int REAL_CURRENCY_BALANCE = 17;
    public static final int REAL_CURRENCY_DELTA = 16;
    public static final int REAL_CURRENCY_TOTAL = 18;
    public static final int RESOURCE_1_BALANCE = 8;
    public static final int RESOURCE_1_DELTA = 7;
    public static final int RESOURCE_1_TOTAL = 9;
    public static final int SESSION_ACCUMULATED_TIME = 29;
    public static final int SESSION_DURATION = 28;
    public static final int SESSION_ID = 27;
    public static final String[] SUPPORTED_PARAMS = {"other_user_id", "p1", "p2", "p3", "p4", "p5", "level", "resource_1_delta", "resource_1_balance", "resource_1_total", "game_currency_delta", "game_currency_balance", "game_currency_total", "paid_currency_delta", "paid_currency_balance", "paid_currency_total", "real_currency_delta", "real_currency_balance", "real_currency_total", "friends_total", "application_friends_total", "is_fan", "is_bookmarked", "product", "product_detail", "campaign_source", "campaign_channel", "session_id", "session_duration", "session_accumulated_time"};

    String getParamString();

    void setValue(int i, String str);
}
