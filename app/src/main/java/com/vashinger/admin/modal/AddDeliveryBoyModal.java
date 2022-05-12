package com.vashinger.admin.modal;

public class AddDeliveryBoyModal {

    String deliveryBoyCode, deliveryBoyName , deliveryBoyNumber, deliveryBoyStatus;

    public AddDeliveryBoyModal(String deliveryBoyCode, String deliveryBoyName, String deliveryBoyNumber, String deliveryBoyStatus) {
        this.deliveryBoyCode = deliveryBoyCode;
        this.deliveryBoyName = deliveryBoyName;
        this.deliveryBoyNumber = deliveryBoyNumber;
        this.deliveryBoyStatus = deliveryBoyStatus;
    }

    public AddDeliveryBoyModal() {
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

    public String getDeliveryBoyStatus() {
        return deliveryBoyStatus;
    }

    public void setDeliveryBoyStatus(String deliveryBoyStatus) {
        this.deliveryBoyStatus = deliveryBoyStatus;
    }
}
