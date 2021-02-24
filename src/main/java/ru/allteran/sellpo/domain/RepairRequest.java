package ru.allteran.sellpo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RepairRequest {
    @Id
    private String id;
    private String productName;
    private String productType;
    private String requestDate;
    private String sellDate;
    private String repairmanComment;

    private String status; //status - string const, that describes in app.properties

    private long totalPrice;
    private long componentPrice;
    private long netProfit;

    private String clientName;
    private String clientPhone;

    private int posId;
    private String sellerId;

    private String requestReason; //as client describes
    private String repairReason; //actual reason from repairman
    private String equipSet; //setting of equip that client gives to seller
    private String deviceState; // actual state of device if there any scratches or so on

    public RepairRequest() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRepairmanComment() {
        return repairmanComment;
    }

    public void setRepairmanComment(String repairmanComment) {
        this.repairmanComment = repairmanComment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEquipSet() {
        return equipSet;
    }

    public void setEquipSet(String equipSet) {
        this.equipSet = equipSet;
    }

    public String getDeviceState() {
        return deviceState;
    }

    public void setDeviceState(String deviceState) {
        this.deviceState = deviceState;
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

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
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
