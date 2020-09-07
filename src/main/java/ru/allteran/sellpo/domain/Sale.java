package ru.allteran.sellpo.domain;

import java.util.List;

public class Sale {
    private String date;
    private int posId;
    private double employeeId;
    private Product product;
    private PayType payType;

    public Sale() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPosId() {
        return posId;
    }

    public void setPosId(int posId) {
        this.posId = posId;
    }

    public double getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(double employeeId) {
        this.employeeId = employeeId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }
}
