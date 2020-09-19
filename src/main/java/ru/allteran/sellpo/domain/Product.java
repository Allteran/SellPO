package ru.allteran.sellpo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {
    @Id
    private String id;

    private String name;
    private ProductType type;
    private double price;
    private double maxReward;
    private double minReward;

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMaxReward() {
        return maxReward;
    }

    public void setMaxReward(double maxReward) {
        this.maxReward = maxReward;
    }

    public double getMinReward() {
        return minReward;
    }

    public void setMinReward(double minReward) {
        this.minReward = minReward;
    }
}
