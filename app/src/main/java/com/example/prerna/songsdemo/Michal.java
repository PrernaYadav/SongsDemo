package com.example.prerna.songsdemo;

public class Michal {

    private String image;
    private String collection;
    private String track;
    private String date;
    private String genre;

    public Michal() {
        // TODO Auto-generated constructor stub
    }

    public Michal(String image, String collection, String track, String date, String genre) {
        super();
        this.image = image;
        this.collection = collection;
        this.track = track;
        this.date = date;

        this.genre = genre;

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

}
