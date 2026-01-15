package com.digitalchocolate.androidrollergapp;

import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public interface IPaymentManager {
    public static final int STATE_DONE = 1;
    public static final int STATE_NOT_FINISHED = 0;

    void doDraw(Graphics graphics);

    boolean isInAppPaymentSupported();

    void keyEventOccurred(int i, int i2);

    int logicUpdate(int i);

    void pointerEventOccurred(int i, int i2, int i3);

    void purchaseProduct(PurchaseRequest purchaseRequest);

    void requestAvailableProducts();

    void restoreCompletedTransactions();

    void setPaymentListener(IPaymentListener iPaymentListener);
}
