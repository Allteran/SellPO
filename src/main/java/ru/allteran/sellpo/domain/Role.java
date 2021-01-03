package ru.allteran.sellpo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Document
public class Role implements GrantedAuthority{
    public static final long ID_USER = 8737;
    public static final long ID_ADMIN = 23646;
    public static final long ID_MANAGER = 6262337;

    public static final String NAME_DISPLAY_MANAGER = "Управляющий менеджер дилера";
    public static final String NAME_DISPLAY_USER = "Менеджер по продажам";
    public static final String NAME_DISPLAY_ADMIN="Администратор системы";

    @Id
    private long id;
    private String name;
    private String displayName;

    public Role() {
    }

    public Role(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    @Override
    public String getAuthority() {
        return name;
    }
}
