package fr.ensicaen.present.present.presenter.entercode;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import fr.ensicaen.present.present.utils.providers.GPSMessage;
import fr.ensicaen.present.present.utils.providers.GPSPublishSubject;
import fr.ensicaen.present.present.utils.providers.PermissionCodes;
import fr.ensicaen.present.present.view.entercode.IEnterCodeView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Jeanne on 14/12/2017.
 */

public class EnterCodePresenter implements IEnterCodePresenter {

    private IEnterCodeView _view;
    private LocationManager _locationManager;
    private double _longitude;
    private double _latitude;
    private GPSPublishSubject _gpsSubject;

    public EnterCodePresenter(IEnterCodeView view) {
        _view = view;
        _locationManager = _view.createLocationManager();
        _gpsSubject = new GPSPublishSubject();
    }

    public void sendCode(String text) {
        if (_hasCodeBeenEntered(text)) {
            _recoverGPSCoordinates();
            //@TODO send code;
        }
    }

    private boolean _hasCodeBeenEntered(String text) {
        if("".equals(text.trim())){
            _view.showToast("Your code cannot be empty", Toast.LENGTH_LONG);
            return false;
        }
        return true;
    }

    @SuppressLint("MissingPermission")
    private void _recoverGPSCoordinates() {
        _gpsSubject = new GPSPublishSubject();
        _locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 0, _gpsSubject);

        _gpsSubject.getObservable()
                .filter(gpsMessage -> gpsMessage.getLocation() != null)
                .timeout(50000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> _view.showLoadingAnimation())
                .subscribe(new GPSSubscriber());
    }

    public boolean getMessage() {
        return false;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onPermissionResponseReceieved(int requestCode, int[] grantResults) {
        switch(requestCode){
            case PermissionCodes.REQUEST_COARSE_LOCATION:
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                _view.showToast("GPS is required for this attendance check", Toast.LENGTH_LONG);
                _view.returnToPreviousPage();
            }
        }

    }


    private final class GPSSubscriber implements Observer<GPSMessage> {
        Disposable _disposable;

        @Override
        public void onSubscribe(Disposable d) {
            //_view.showLoadingAnimation();
            _view.showLoader();
            _disposable = d;
        }

        @Override
        public void onNext(GPSMessage gpsMessage) {
            if(gpsMessage.getLocation() != null){
                //_view.hideLoadingAnimation();
                _view.hideLoader();
                _longitude = gpsMessage.getLocation().getLongitude();
                _latitude = gpsMessage.getLocation().getLatitude();
                _view.showToast(_longitude+", "+_latitude, Toast.LENGTH_SHORT);
                _disposable.dispose();
            }else{
                _view.showToast(gpsMessage.getMessage(), Toast.LENGTH_SHORT);
            }

        }

        @Override
        public void onError(Throwable t) {
            //_view.hideLoadingAnimation();
            _view.hideLoader();
            t.printStackTrace();
            _disposable.dispose();
            _view.showToast("GPS cannot be found. Try again", Toast.LENGTH_SHORT);

        }

        @Override
        public void onComplete() {
            //_view.hideLoadingAnimation();
            _view.hideLoader();
            _disposable.dispose();
        }
    }
}
