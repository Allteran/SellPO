package ru.allteran.sellpo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ProductType {
    @Id
    private String id;
    private String name;
    private ProductTypeCode typeCode;

    public ProductType() {
    }

    public ProductType(String id, String name, ProductTypeCode typeCode) {
        this.id = id;
        this.name = name;
        this.typeCode = typeCode;
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

    public ProductTypeCode getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(ProductTypeCode typeCode) {
        this.typeCode = typeCode;
    }
}
