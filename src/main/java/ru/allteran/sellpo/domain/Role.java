package ru.allteran.sellpo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Role {
    public static final long ID_USER = 8737;
    public static final long ID_ADMIN = 23646;
    public static final long ID_MANAGER = 6262337;

    @Id
    private long id;
    private String name;

    public Role () {}

    public Role(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
