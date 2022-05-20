package com.unideb.model;

import javax.persistence.*;

@Entity
public class Music {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "title", unique = true, nullable = false)
    private String title;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "album", nullable = false)
    private String album;
    @Column(name = "genre", nullable = false)
    private String genre;
    @Column(name = "length", nullable = false)
    private int length;

    public Music() {
    }

    public Music(String title, String author, String album, String genre, int length) {
        this.title = title;
        this.author = author;
        this.album = album;
        this.genre = genre;
        this.length = length;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
