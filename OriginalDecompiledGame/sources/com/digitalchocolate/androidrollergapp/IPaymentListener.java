package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public interface IPaymentListener {
    void availableProductsReceived(ProductResponse productResponse);

    void productPurchased(PaymentTransaction paymentTransaction);
}
