package com.example.zebeedemo.Models;

public class OrderModel {

    private long orderId;
    private double totalPrice;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(final long orderId) {
        this.orderId = orderId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(final double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
