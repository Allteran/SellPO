package ru.allteran.sellpo.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RepairDeviceStatus {
    public static final String ID_NEW_REQUEST = "new_request";
    public static final String ID_REPAIRING = "waiting_for_repairman";
    public static final String ID_DONE = "repair_done";
    public static final String ID_UNDONE = "repair_undone";
    public static final String ID_PAID = "paid_and_picked";

    public static final String NEW_REQUEST = "Принят";
    public static final String REPAIRING = "Ремонтируется";
    public static final String DONE = "Готово. Ожидает клиента";
    public static final String UNDONE = "НРП. Ожидает клиента";
    public static final String PAID = "Оплачено";

    @Id
    private String id;
    private String status;

    public RepairDeviceStatus() {
    }

    public RepairDeviceStatus(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
