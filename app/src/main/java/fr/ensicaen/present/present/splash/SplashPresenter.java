package fr.ensicaen.present.present.splash;

import java.io.IOException;

import fr.ensicaen.present.present.utils.Config;

/**
 * Created by jueast on 03/12/17.
 */

public final class SplashPresenter implements ISplashPresenter {

    private ISplashScreenView _view;
    private Config _config;

    public SplashPresenter(ISplashScreenView view){
        _view = view;
        try {
            _config = _view.getConfigAccessor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SplashPresenter(ISplashScreenView view, Config c) {
        _view = view;
        _config = c;
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
