package com.example.dllo.project_a_cst.my_database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 16/12/13.
 */
@Entity
public class MyPerson {
    @Id
    private Long id;

    private String musicName, Singer,musicId,musicUrl;

    @Generated(hash = 757001676)
    public MyPerson(Long id, String musicName, String Singer, String musicId,
            String musicUrl) {
        this.id = id;
        this.musicName = musicName;
        this.Singer = Singer;
        this.musicId = musicId;
        this.musicUrl = musicUrl;
    }

    @Generated(hash = 721319872)
    public MyPerson() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMusicName() {
        return this.musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getSinger() {
        return this.Singer;
    }

    public void setSinger(String Singer) {
        this.Singer = Singer;
    }

    public String getMusicId() {
        return this.musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public String getMusicUrl() {
        return this.musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }
}
