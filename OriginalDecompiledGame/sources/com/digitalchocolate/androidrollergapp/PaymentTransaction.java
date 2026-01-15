package com.digitalchocolate.androidrollergapp;

/* loaded from: classes.dex */
public class PaymentTransaction {
    public static final int STATUS_ERROR = 1;
    public static final int STATUS_LOGIN_FAILED = 2;
    public static final int STATUS_OK = 0;
    public static final int STATUS_RESTORE_FAILED = 4;
    public static final int STATUS_USER_CANCELLED = 3;
    private PurchaseRequest request;
    private int status;
    private boolean success;

    public PaymentTransaction(PurchaseRequest purchaseRequest, boolean z, int i) {
        this.request = purchaseRequest;
        this.success = z;
        this.status = i;
    }

    public PurchaseRequest getPurchaseRequest() {
        return this.request;
    }

    public int getStatus() {
        return this.status;
    }

    public boolean isSuccess() {
        return this.success;
    }
}
