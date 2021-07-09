package ru.allteran.sellpo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document
public class Product {
    @Id
    private String id;

    @NotBlank(message = "Введите название товара")
    private String name;
    private ProductType type;

    @NotBlank(message = "Введите цену товара")
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
