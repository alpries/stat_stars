// Simple User Model Class - holds basic info about a user (used for display in ListView)
package com.example.stat_stars;

public class Users {
    // VARIABLES + EXPLANATIONS
    String userID;    // Unique ID for the user (can be a player tag or identifier)
    String username;  // Display name or username of the user

    // CONSTRUCTOR - initializes a new user with an ID and username
    public Users(String userID, String username) {
        this.userID = userID;
        this.username = username;
    }

    // GETTERS - used to access private variables from outside this class

    public String getUserID() {
        return userID; // returns the user's ID
    }

    public String getUsername() {
        return username; // returns the user's name
    }
}
