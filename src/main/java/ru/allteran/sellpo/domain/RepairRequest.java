package ru.allteran.sellpo.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RepairRequest {
    private String id;
    private String productName;
    private String productType;
    private String requestDate;
    private String toRepairmanDate;
    private String fromRepairmanDate;
    private String sellDate;

    private long totalPrice;
    private long componentPrice;
    private long netProfit;

    private String clientName;
    private String clientPhoneNumber;

    private int posId;
    private String sellerId;

    private String requestReason;
    private String repairReason;

    public RepairRequest() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getToRepairmanDate() {
        return toRepairmanDate;
    }

    public void setToRepairmanDate(String toRepairmanDate) {
        this.toRepairmanDate = toRepairmanDate;
    }

    public String getFromRepairmanDate() {
        return fromRepairmanDate;
    }

    public void setFromRepairmanDate(String fromRepairmanDate) {
        this.fromRepairmanDate = fromRepairmanDate;
    }

    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getComponentPrice() {
        return componentPrice;
    }

    public void setComponentPrice(long componentPrice) {
        this.componentPrice = componentPrice;
    }

    public long getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(long netProfit) {
        this.netProfit = netProfit;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public int getPosId() {
        return posId;
    }

    public void setPosId(int posId) {
        this.posId = posId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getRequestReason() {
        return requestReason;
    }

    public void setRequestReason(String requestReason) {
        this.requestReason = requestReason;
    }

    public String getRepairReason() {
        return repairReason;
    }

    public void setRepairReason(String repairReason) {
        this.repairReason = repairReason;
    }
}
