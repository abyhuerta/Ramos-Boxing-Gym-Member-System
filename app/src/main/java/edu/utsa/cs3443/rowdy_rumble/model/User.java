package edu.utsa.cs3443.rowdy_rumble.model;

public abstract class User {
    private String name;
    private String userName;
    private String password;

    public String getName() {
        return name;
    }

    public User(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public String getUserName(){
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
