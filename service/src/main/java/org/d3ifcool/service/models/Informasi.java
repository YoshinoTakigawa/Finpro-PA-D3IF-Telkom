package org.d3ifcool.service.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 27/01/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
public class Informasi implements Parcelable {
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("info_judul")
    private String info_judul;

    @Expose
    @SerializedName("info_deskripsi")
    private String info_deskripsi;

    @Expose
    @SerializedName("created_at")
    private String info_tanggal;

    @Expose
    @SerializedName("publisher")
    private String publisher;

    @Expose
    @SerializedName("foto")
    private String foto;

    public Informasi(int id, String info_judul, String info_deskripsi, String info_tanggal, String publisher, String foto) {
        this.id = id;
        this.info_judul = info_judul;
        this.info_deskripsi = info_deskripsi;
        this.info_tanggal = info_tanggal;
        this.publisher = publisher;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo_judul() {
        return info_judul;
    }

    public void setInfo_judul(String info_judul) {
        this.info_judul = info_judul;
    }

    public String getInfo_deskripsi() {
        return info_deskripsi;
    }

    public void setInfo_deskripsi(String info_deskripsi) {
        this.info_deskripsi = info_deskripsi;
    }

    public String getInfo_tanggal() {
        return info_tanggal;
    }

    public void setInfo_tanggal(String info_tanggal) {
        this.info_tanggal = info_tanggal;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.info_judul);
        dest.writeString(this.info_deskripsi);
        dest.writeString(this.info_tanggal);
        dest.writeString(this.publisher);
        dest.writeString(this.foto);
    }

    protected Informasi(Parcel in) {
        this.id = in.readInt();
        this.info_judul = in.readString();
        this.info_deskripsi = in.readString();
        this.info_tanggal = in.readString();
        this.publisher = in.readString();
        this.foto = in.readString();
    }

    public static final Creator<Informasi> CREATOR = new Creator<Informasi>() {
        @Override
        public Informasi createFromParcel(Parcel source) {
            return new Informasi(source);
        }

        @Override
        public Informasi[] newArray(int size) {
            return new Informasi[size];
        }
    };
}
