package fr.ensicaen.present.present.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.io.IOException;

import fr.ensicaen.present.present.login.LoginActivity;
import fr.ensicaen.present.present.utils.Config;

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
    public void loadProperties() {
        try {
            Config.loadProperties(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
