package ru.allteran.sellpo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Sale {
    @Id
    private String id;

    private String date;
    private int posId;
    private double employeeId;
    private Product product;
    private PayType payType;

    public Sale() {
    }

    public Sale(String id, String date, int posId, double employeeId, Product product, PayType payType) {
        this.id = id;
        this.date = date;
        this.posId = posId;
        this.employeeId = employeeId;
        this.product = product;
        this.payType = payType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
