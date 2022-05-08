package com.vashinger.admin.modal;

public class DeliveryBoyFlashModal {

   private String orderId , status ;

    public DeliveryBoyFlashModal() {
    }

    public DeliveryBoyFlashModal(String orderId, String status) {
        this.orderId = orderId;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
