package fr.ensicaen.present.present.utils.providers;
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

import android.location.Location;

/*
 * @author The Présent ! Team <easterly@ecole.ensicaen.fr>
 * @version 0.0.1 - 17/03/18
 */
public final class GPSMessage {
    private Location _location;
    private int _status;
    private String _message;

    public GPSMessage(int _status, String _message, Location _location) {
        this._location = _location;
        this._status = _status;
        this._message = _message;
    }

    public Location getLocation() {
        return _location;
    }

    public int getStatus() {
        return _status;
    }

    public String getMessage() {
        return _message;
    }

    public void setLocation(Location _location) {
        this._location = _location;
    }

    public void setStatus(int _status) {
        this._status = _status;
    }

    public void setMessage(String _message) {
        this._message = _message;
    }
}