package com.honey.e_commerce.javabean;

/**
 * 1.类的用途
 * 2.@authorAdministrator
 */

public class RelignData {
    String username;
    String password;

    public RelignData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RelignData() {
        super();
    }

    @Override
    public String toString() {
        return "RelignData{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
