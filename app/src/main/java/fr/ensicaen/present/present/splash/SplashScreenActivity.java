package fr.ensicaen.present.present.splash;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import fr.ensicaen.present.present.R;

public class SplashScreenActivity extends Activity implements ISplashScreenView {


    private final int SPLASH_DISPLAY_LENGTH_MILLISECONDS = 1000;
    private ISplashPresenter _presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                _presenter.checkTokenValidity();
            }
        }, SPLASH_DISPLAY_LENGTH_MILLISECONDS);

        _presenter = new SplashPresenter(this);


    }

    @Override
    public void openLoginActivity() {

    }

    @Override
    public void openDashboardActivity() {

    }

    @Override
    public void startSyncService() {

    }

    @Override
    public void showProgressBar() {

    }
}
