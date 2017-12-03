package fr.ensicaen.present.present.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import fr.ensicaen.present.present.login.LoginActivity;
import fr.ensicaen.present.present.R;

public class SplashScreenActivity extends Activity implements ISplashScreenView {


    private ISplashPresenter _presenter;
    public static final int STARTUP_DELAY = 300;
    public static final int ANIM_ITEM_DURATION = 1000;
    public static final int ITEM_DELAY = 300;

    private boolean animationStarted = false;

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
