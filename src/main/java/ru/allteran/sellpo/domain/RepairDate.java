package ru.allteran.sellpo.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * RepairDate is kind of custom Date class that contains actual Date to sort and filter entities by date
 * and stringDate to display date into frontend as I need right now without any parses and so on
 */
public class RepairDate {
    private Date date;
    private String stringDate;

    public RepairDate(Date date) {
        this.date = date;
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        stringDate = formatter.format(date);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStringDate() {
        return stringDate;
    }

}
