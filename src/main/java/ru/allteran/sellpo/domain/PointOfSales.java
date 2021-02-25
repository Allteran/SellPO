package ru.allteran.sellpo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PointOfSales {
    private String dealerId;

    @Id
    private String id;
    private String city;
    private String street;

    public PointOfSales() {
    }

    public PointOfSales(String dealerId, String id, String city, String street) {
        this.dealerId = dealerId;
        this.id = id;
        this.city = city;
        this.street = street;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "PointOfSales{" +
                "dealerId=" + dealerId +
                ", id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
