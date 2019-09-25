package com.openclassrooms.entrevoisins.model;

import java.util.Objects;

/**
 * Model object representing a Neighbour
 */
public class Neighbour {

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
     * @param  phoneNumber
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
