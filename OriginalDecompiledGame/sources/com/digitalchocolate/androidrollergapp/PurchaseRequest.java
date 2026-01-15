package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public class PurchaseRequest {
    private Product product;
    private int quantity;

    public PurchaseRequest(Product product, int i) {
        this.product = product;
        this.quantity = i;
    }

    public Product getProduct() {
        return this.product;
    }

    public int getQuantity() {
        return this.quantity;
    }
}
