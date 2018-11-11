package com.example.manu.cinemaappv2;

public class Films {
    int id;
    String titles;
    String director;
    String guionist;
    String genre;
    String subgenre;
    String actor1;
    String actor2;
    String actor3;
    String actor4;

    public Films(int anInt, String string, String cursorString, String s, String string1, String cursorString1, String s1, String string2, String cursorString2, String s2, String string3) {

    }

    public Films(String titles, String director, String guionist, String genre, String subgenre, String actor1, String actor2, String actor3, String actor4) {
        this.titles = titles;
        this.director = director;
        this.guionist = guionist;
        this.genre = genre;
        this.subgenre = subgenre;
        this.actor1 = actor1;
        this.actor2 = actor2;
        this.actor3 = actor3;
        this.actor4 = actor4;
    }

    public Films(int id, String titles, String director, String guionist, String genre, String subgenre, String actor1, String actor2, String actor3, String actor4) {
        this.id = id;
        this.titles = titles;
        this.director = director;
        this.guionist = guionist;
        this.genre = genre;
        this.subgenre = subgenre;
        this.actor1 = actor1;
        this.actor2 = actor2;
        this.actor3 = actor3;
        this.actor4 = actor4;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGuionist() {
        return guionist;
    }

    public void setGuionist(String guionist) {
        this.guionist = guionist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSubgenre() {
        return subgenre;
    }

    public void setSubgenre(String subgenre) {
        this.subgenre = subgenre;
    }

    public String getActor1() {
        return actor1;
    }

    public void setActor1(String actor1) {
        this.actor1 = actor1;
    }

    public String getActor2() {
        return actor2;
    }

    public void setActor2(String actor2) {
        this.actor2 = actor2;
    }

    public String getActor3() {
        return actor3;
    }

    public void setActor3(String actor3) {
        this.actor3 = actor3;
    }

    public String getActor4() {
        return actor4;
    }

    public void setActor4(String actor4) {
        this.actor4 = actor4;
    }
}
