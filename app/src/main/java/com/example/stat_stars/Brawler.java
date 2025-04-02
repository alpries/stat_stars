package com.example.stat_stars;

import java.util.Objects;

public class Brawler {
    String brawlerName;
    String trophies;
    public Brawler(String brawlerName, String trophies) {
        this.brawlerName = brawlerName;
        this.trophies = trophies;
    }
    @Override
    public String toString() {
        return brawlerName + ": Trophies: " + trophies;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brawler brawler = (Brawler) o;
        return brawlerName == brawler.brawlerName && trophies == brawler.trophies;
    }
    @Override
    public int hashCode() {return Objects.hash(brawlerName,trophies);}
}
