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

import com.google.gson.annotations.SerializedName;

/*
 * @author The Présent ! Team <easterly@ecole.ensicaen.fr>
 * @version 0.0.1 - 21/01/18
 */
public class PreviousCallModel {
    @SerializedName("event")
    private String _event;
    @SerializedName("date")
    private String _date;
    @SerializedName("code")
    private String _code;

    public PreviousCallModel(String event, String date, String code) {
        _event = event;
        _date = date;
        _code = code;
    }

    @Override
    public String toString() {
        return "PreviousCallModel{" +
                "_event='" + _event + '\'' +
                ", _date='" + _date + '\'' +
                ", _code='" + _code + '\'' +
                '}';
    }

    public String getEvent() {
        return _event;
    }

    public String getDate() {
        return _date;
    }

    public String getCode() {
        return _code;
    }
}
