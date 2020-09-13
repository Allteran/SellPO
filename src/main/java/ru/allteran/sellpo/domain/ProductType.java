package ru.allteran.sellpo.domain;

public class ProductType {
    private String name;
    private ProductTypeCode typeCode;

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
