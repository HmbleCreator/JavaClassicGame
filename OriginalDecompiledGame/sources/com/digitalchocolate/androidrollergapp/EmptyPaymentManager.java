package com.digitalchocolate.androidrollergapp;

import javax.microedition.lcdui.Graphics;

/* loaded from: classes.dex */
public class EmptyPaymentManager implements IPaymentManager {
    public static IPaymentManager getUsedPaymentManager() {
        return null;
    }

    @Override // com.digitalchocolate.androidrollergapp.IPaymentManager
    public void doDraw(Graphics graphics) {
    }

    @Override // com.digitalchocolate.androidrollergapp.IPaymentManager
    public boolean isInAppPaymentSupported() {
        return false;
    }

    @Override // com.digitalchocolate.androidrollergapp.IPaymentManager
    public void keyEventOccurred(int i, int i2) {
    }

    @Override // com.digitalchocolate.androidrollergapp.IPaymentManager
    public int logicUpdate(int i) {
        return 1;
    }

    @Override // com.digitalchocolate.androidrollergapp.IPaymentManager
    public void pointerEventOccurred(int i, int i2, int i3) {
    }

    @Override // com.digitalchocolate.androidrollergapp.IPaymentManager
    public void purchaseProduct(PurchaseRequest purchaseRequest) {
    }

    @Override // com.digitalchocolate.androidrollergapp.IPaymentManager
    public void requestAvailableProducts() {
    }

    @Override // com.digitalchocolate.androidrollergapp.IPaymentManager
    public void restoreCompletedTransactions() {
    }

    @Override // com.digitalchocolate.androidrollergapp.IPaymentManager
    public void setPaymentListener(IPaymentListener iPaymentListener) {
    }
}
