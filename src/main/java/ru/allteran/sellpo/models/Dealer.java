package ru.allteran.sellpo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Dealer {
    public static final String ID_DEFAULT_DEALER = "default_dealer";
    public static final String NAME_DEFAULT_DEALER = "DEFAULT_DEALER";

    @Id
    private String id;
    private String name;

    public Dealer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Dealer() {
    }

    public static org.bson.Document dealerToDocument(Dealer dealer) {
        org.bson.Document dealerDoc = new org.bson.Document();
        dealerDoc.put("_id", dealer.getId());
        dealerDoc.put("name", dealer.getName());
        return dealerDoc;
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
}
