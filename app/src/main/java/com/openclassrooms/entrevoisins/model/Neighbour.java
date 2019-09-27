package com.openclassrooms.entrevoisins.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 * Model object representing a Neighbour
 */
public class Neighbour implements Parcelable {

    /** Identifier */
    private Integer id;

    /** Full name */
    private String name;

    /** Avatar */
    private String avatarUrl;

    /** Detail name */
    private String detailName;

    /** Place */
    private String place;

    /** Phone Number */
    private String phoneNumber;

    /** facebook */
    private String facebook;

    /** about */
    private String about;

    /** Favorite */
    private boolean isFavorite;

    /**
     * Constructor
     * @param id
     * @param name
     * @param avatarUrl
     * @param place
     * @param  phoneNumber
     * @param facebook
     * @param about
     */
    public Neighbour(Integer id, String name, String avatarUrl, String place, String phoneNumber, String facebook, String about) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.detailName = name;
        this.place = place;
        this.phoneNumber = phoneNumber;
        this.facebook = facebook;
        this.about = about;
        this.isFavorite = false;
    }



    public Neighbour(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        avatarUrl = in.readString();
        detailName = in.readString();
        place = in.readString();
        phoneNumber = in.readString();
        facebook = in.readString();
        about = in.readString();
        isFavorite = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(avatarUrl);
        dest.writeString(detailName);
        dest.writeString(place);
        dest.writeString(phoneNumber);
        dest.writeString(facebook);
        dest.writeString(about);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Neighbour> CREATOR = new Creator<Neighbour>() {
        @Override
        public Neighbour createFromParcel(Parcel in) {
            return new Neighbour(in);
        }

        @Override
        public Neighbour[] newArray(int size) {
            return new Neighbour[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getPlace() {
        return place;
    }

    public String getPhoneNumber() {return phoneNumber;}

    public String getFacebook() {
        return facebook;
    }

    public String getAbout() {
        return about;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
