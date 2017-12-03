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
        _presenter.checkTokenValidity();
/*        _presenter = new SplashPresenter(this);
        //_presenter.checkTokenValidity();
        setTheme(R.style.AppTheme);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);*/
    }

/*    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        if (!hasFocus || animationStarted) {
            return;
        }

        animate();

        super.onWindowFocusChanged(hasFocus);
    }


    private void animate() {
        ImageView logoImageView = (ImageView) findViewById(R.id.splash_logo);
        ViewGroup container = (ViewGroup) findViewById(R.id.splash_option_container);

        ViewCompat.animate(logoImageView)
                .translationY(-50)
                .setStartDelay(STARTUP_DELAY)
                .setDuration(ANIM_ITEM_DURATION).setInterpolator(
                new DecelerateInterpolator(1.2f)).start();

        for (int i = 0; i < container.getChildCount(); i++) {
            View v = container.getChildAt(i);
            ViewPropertyAnimatorCompat viewAnimator;

            if (!(v instanceof Button)) {
                viewAnimator = ViewCompat.animate(v)
                        .translationY(50).alpha(1)
                        .setStartDelay((ITEM_DELAY * i) + 500)
                        .setDuration(1000);
            } else {
                viewAnimator = ViewCompat.animate(v)
                        .scaleY(1).scaleX(1)
                        .setStartDelay((ITEM_DELAY * i) + 500)
                        .setDuration(500);
            }

            viewAnimator.setInterpolator(new DecelerateInterpolator()).start();
        }
    }*/

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
