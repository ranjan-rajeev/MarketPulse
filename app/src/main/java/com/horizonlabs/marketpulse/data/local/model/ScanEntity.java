package com.horizonlabs.marketpulse.data.local.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Rajeev Ranjan -  ABPB on 22-07-2019.
 */
@Entity
public class ScanEntity implements Parcelable {

    /**
     * id : 1
     * name : Top gainers
     * tag : Intraday Bullish
     * color : green
     * criteria : [{"type":"plain_text","text":"Sort - %price change in descending order"}]
     */
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String tag;
    private String color;
    private String criteria;

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.tag);
        dest.writeString(this.color);
        dest.writeString(this.criteria);
    }

    public ScanEntity() {
    }

    protected ScanEntity(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.tag = in.readString();
        this.color = in.readString();
        this.criteria = in.readString();
    }

    public static final Parcelable.Creator<ScanEntity> CREATOR = new Parcelable.Creator<ScanEntity>() {
        @Override
        public ScanEntity createFromParcel(Parcel source) {
            return new ScanEntity(source);
        }

        @Override
        public ScanEntity[] newArray(int size) {
            return new ScanEntity[size];
        }
    };
}
