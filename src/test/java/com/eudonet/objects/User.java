package com.eudonet.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private String clientArea;
    private String clientPassword;
    private String database;
    private String username;
    private String password;

    public User(String clientArea, String clientPassword, String database, String username, String password) {
        this.clientArea = clientArea;
        this.clientPassword = clientPassword;
        this.database = database;
        this.username = username;
        this.password = password;
    }
}
