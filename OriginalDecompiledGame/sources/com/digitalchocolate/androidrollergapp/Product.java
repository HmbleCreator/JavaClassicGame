package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public class Product {
    public static final int PRICE_MULTIPLIER = 1000;
    private int dchocId;
    private String description;
    private DChocImage icon;
    private int iconRID;
    private int price;
    private String priceString;
    private String thirdPartyId;
    private String title;
    private byte type;

    public Product(int i, String str, byte b, String str2, String str3, DChocImage dChocImage, int i2, int i3, String str4) {
        this.dchocId = i;
        this.thirdPartyId = str;
        this.title = str2;
        this.description = str3;
        this.icon = dChocImage;
        this.iconRID = i2;
        this.price = i3;
        this.priceString = str4;
        this.type = b;
    }

    public int getDchocId() {
        return this.dchocId;
    }

    public String getDescription() {
        return this.description;
    }

    public DChocImage getIcon() {
        return this.icon;
    }

    public int getIconRid() {
        return this.iconRID;
    }

    public int getPrice() {
        return this.price;
    }

    public String getPriceString() {
        return this.priceString;
    }

    public String getThirdPartyId() {
        return this.thirdPartyId;
    }

    public String getTitle() {
        return this.title;
    }

    public byte getType() {
        return this.type;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setIcon(DChocImage dChocImage) {
        this.icon = dChocImage;
    }

    public void setPrice(int i) {
        this.price = i;
    }

    public void setPriceString(String str) {
        this.priceString = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
