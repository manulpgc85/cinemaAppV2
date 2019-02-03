package com.example.manu.cinemaappv2.pojos;

public class Ppv {
    String titulo;
    String director;
    String guionist;
    Integer year;
    Integer id;
    String category;


    public Ppv(String titulo, String director, String guionist, Integer year, String category) {
        this.titulo = titulo;
        this.director = director;
        this.guionist = guionist;
        this.year = year;
        this.category = category;
    }

    public Ppv() {

    }

    public Ppv(Integer id, String titulo, String director, String guionist, Integer year) {
        this.titulo = titulo;
        this.director = director;
        this.guionist = guionist;
        this.year = year;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    }
