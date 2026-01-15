package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public class ProductResponse {
    private Product[] products;
    private boolean success;

    public ProductResponse(Product[] productArr, boolean z) {
        this.products = productArr;
        this.success = z;
    }

    public Product[] getProducts() {
        return this.products;
    }

    public boolean isSuccess() {
        return this.success;
    }
}
