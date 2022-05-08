package com.vashinger.admin.modal;

public class AddDeliveryBoyModal {

    String deliveryBoyCode, deliveryBoyName , deliveryBoyNumber;

    public AddDeliveryBoyModal() {
    }

    public AddDeliveryBoyModal(String deliveryBoyCode, String deliveryBoyName, String deliveryBoyNumber) {
        this.deliveryBoyCode = deliveryBoyCode;
        this.deliveryBoyName = deliveryBoyName;
        this.deliveryBoyNumber = deliveryBoyNumber;
    }

    public String getDeliveryBoyCode() {
        return deliveryBoyCode;
    }

    public void setDeliveryBoyCode(String deliveryBoyCode) {
        this.deliveryBoyCode = deliveryBoyCode;
    }

    public String getDeliveryBoyName() {
        return deliveryBoyName;
    }

    public void setDeliveryBoyName(String deliveryBoyName) {
        this.deliveryBoyName = deliveryBoyName;
    }

    public String getDeliveryBoyNumber() {
        return deliveryBoyNumber;
    }

    public void setDeliveryBoyNumber(String deliveryBoyNumber) {
        this.deliveryBoyNumber = deliveryBoyNumber;
    }
}
