package com.example.stat_stars;

import java.util.Objects;

// Simple class representing a Brawler with a name and trophies count
public class Brawler {
    String brawlerName; // Name of the Brawler
    String trophies;    // Trophies associated with the Brawler

    // Constructor to initialize the Brawler object with name and trophies
    public Brawler(String brawlerName, String trophies) {
        this.brawlerName = brawlerName;
        this.trophies = trophies;
    }

    // toString method for easy printing and debugging
    // Returns the Brawler in "Name: Trophies: value" format
    @Override
    public String toString() {
        return brawlerName + ": Trophies: " + trophies;
    }

    // Overrides the equals method to compare two Brawler objects
    // Compares both the name and trophies fields
    @Override
    public boolean equals(Object o) {
        // Check if the same object is being compared
        if (this == o) return true;

        // Check if the other object is null or of a different class
        if (o == null || getClass() != o.getClass()) return false;

        // Cast the object to Brawler and compare the fields
        Brawler brawler = (Brawler) o;

        // Use .equals() instead of == for String comparison
        return brawlerName.equals(brawler.brawlerName) && trophies.equals(brawler.trophies);
    }

    // Overrides hashCode so Brawler objects can be used in hash-based collections (like HashMap or HashSet)
    @Override
    public int hashCode() {
        return Objects.hash(brawlerName, trophies);
    }
}
