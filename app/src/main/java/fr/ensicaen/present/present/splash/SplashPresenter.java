package fr.ensicaen.present.present.splash;

import android.os.Handler;

/**
 * Created by jueast on 03/12/17.
 */

public class SplashPresenter implements ISplashPresenter {

    private ISplashScreenView _view;

    public SplashPresenter(ISplashScreenView view){
        _view = view;
    }

    @Override
    public void checkTokenValidity() {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                _view.openLoginActivity();
                _view.finish();
            }
        }, 1000);

    }

    @Override
    public void onDestroy() {

    }
}
