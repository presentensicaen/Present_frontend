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
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;

import io.reactivex.subjects.PublishSubject;

/*
 * @author The Présent ! Team <easterly@ecole.ensicaen.fr>
 * @version 0.0.1 - 17/03/18
 */
public class GPSPublishSubject implements LocationListener {
    private final PublishSubject<GPSMessage> _gpsMessager = PublishSubject.create();

    public PublishSubject<GPSMessage> getObservable(){
        return _gpsMessager;
    }
    //...
    @Override
    public void onLocationChanged(Location location) {
        _gpsMessager.onNext(new GPSMessage(LocationProvider.AVAILABLE, "Recovered GPS location", location));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        switch(status){
            case LocationProvider.AVAILABLE:
                _gpsMessager.onNext(new GPSMessage(LocationProvider.AVAILABLE, "GPS has been enabled", null));
                break;
            case LocationProvider.OUT_OF_SERVICE:
                _gpsMessager.onNext(new GPSMessage(LocationProvider.OUT_OF_SERVICE, "GPS is out of service", null));
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                _gpsMessager.onNext(new GPSMessage(LocationProvider.TEMPORARILY_UNAVAILABLE, "Awaiting GPS", null));
                break;
        }
    }

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}
}