package fr.ensicaen.present.present.models;
/* 
 *
 * ENSICAEN
 * 6 Boulevard Marechal Juin
 * F-14050 Caen Cedex
 *
 * This file is owned by The Présent ! Team and created by
 * Quentin DEBROISE, Julian EASTERLY, Jeanne LEYMARIE, Pierre NICOL, and Coline SMAGGHE.
 * No portion of this document may be reproduced, copied
 * or revised without written permission of the authors.
 */

import android.graphics.drawable.Drawable;

import com.google.gson.annotations.SerializedName;

/*
 * @author The Présent ! Team <easterly@ecole.ensicaen.fr>
 * @version 0.0.1 - 21/01/18
 */
public class PreviousCallUserModel {
    @SerializedName("photoUrl")
    private String _photoUrl;
    @SerializedName("name")
    private String _name;

    private Drawable _photo;

    public PreviousCallUserModel(String photo, String name) {
        _photoUrl = photo;
        _name = name;
        _photo = null;
    }

    @Override
    public String toString() {
        return "PreviousCallUserModel{" +
                "_photoUrl='" + _photoUrl + '\'' +
                ", _name='" + _name + '\'' +
                '}';
    }

    public String getPhotoUrl() {
        return _photoUrl;
    }

    public String getName() {
        return _name;
    }

    public Drawable getPhoto() {
        return _photo;
    }

    public void setPhoto(Drawable photo) {
        _photo = photo;
    }
}
