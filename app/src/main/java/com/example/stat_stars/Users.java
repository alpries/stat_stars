package com.example.stat_stars;

public class Users {
    String userID;
    String username;

    public Users(String userID, String username) {
        this.userID = userID;
        this.username = username;
    }

    public String getUserID() { return userID; }
    public String getUsername() { return username; }
}