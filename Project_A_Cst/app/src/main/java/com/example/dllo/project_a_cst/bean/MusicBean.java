package com.example.dllo.project_a_cst.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dllo on 16/11/24.
 */

public class MusicBean implements Parcelable{

    String musicName,singerName,musicUrl;
    Long duringTime;

    public MusicBean(String musicName, String singerName, String musicUrl, Long duringTime) {
        this.musicName = musicName;
        this.singerName = singerName;
        this.musicUrl = musicUrl;
        this.duringTime = duringTime;
    }

    protected MusicBean(Parcel in) {
        musicName = in.readString();
        singerName = in.readString();
        musicUrl = in.readString();
    }

    public static final Creator<MusicBean> CREATOR = new Creator<MusicBean>() {
        @Override
        public MusicBean createFromParcel(Parcel in) {
            return new MusicBean(in);
        }

        @Override
        public MusicBean[] newArray(int size) {
            return new MusicBean[size];
        }
    };

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public Long getDuringTime() {
        return duringTime;
    }

    public void setDuringTime(Long duringTime) {
        this.duringTime = duringTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(musicName);
        dest.writeString(singerName);
        dest.writeString(musicUrl);
    }
}
