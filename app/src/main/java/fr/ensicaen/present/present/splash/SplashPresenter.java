package fr.ensicaen.present.present.splash;

import android.os.Handler;

import java.io.IOException;

import fr.ensicaen.present.present.utils.Config;

/**
 * Created by jueast on 03/12/17.
 */

public final class SplashPresenter implements ISplashPresenter {

    private ISplashScreenView _view;

    public SplashPresenter(ISplashScreenView view){
        _view = view;
        _view.loadProperties();
    }

    //for tests
    protected SplashPresenter(ISplashScreenView view, Config mockConfig){
        _view = view;
    }

    @Override
    public void checkTokenValidity() {
        /**@TOTO replace with real token verification*/
        _view.openLoginActivity();
        _view.finish();
    }

    @Override
    public void onDestroy() {

    }
}
