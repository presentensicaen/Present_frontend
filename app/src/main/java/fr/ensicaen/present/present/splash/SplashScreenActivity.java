package fr.ensicaen.present.present.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import fr.ensicaen.present.present.login.LoginActivity;

public class SplashScreenActivity extends Activity implements ISplashScreenView {


    private ISplashPresenter _presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _presenter = new SplashPresenter(this);
        _presenter.checkTokenValidity();

    }

    @Override
    public void openLoginActivity() {
        Intent loginIntent = new Intent(SplashScreenActivity.this, LoginActivity.class);
        startActivity(loginIntent);
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
