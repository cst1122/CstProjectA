package com.example.dllo.project_a_cst.my_database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 16/12/13.
 */

@Entity
public class MyMusicPerson {
    @Id
    private Long id;

    private long musicId;
    private String title;
    private String artist;
    private long albumId;
    private String url;

    @Transient
    private String album;
    @Transient
    private long duration;
    @Transient
    private long size;


    @Generated(hash = 767676859)
    public MyMusicPerson(Long id, long musicId, String title, String artist,
            long albumId, String url) {
        this.id = id;
        this.musicId = musicId;
        this.title = title;
        this.artist = artist;
        this.albumId = albumId;
        this.url = url;
    }
    @Generated(hash = 1911925085)
    public MyMusicPerson() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getMusicId() {
        return this.musicId;
    }
    public void setMusicId(long musicId) {
        this.musicId = musicId;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getArtist() {
        return this.artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public long getAlbumId() {
        return this.albumId;
    }
    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
