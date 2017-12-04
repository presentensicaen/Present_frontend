package fr.ensicaen.present.present.login;

import android.app.Activity;
import android.os.Bundle;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;


import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.utils.Animations.Animator;

public final class LoginActivity extends Activity implements ILoginView {

    public static final int STARTUP_DELAY = 300;
    public static final int ANIM_ITEM_DURATION = 1000;
    public static final int ITEM_DELAY = 300;

    private ILoginPresenter _presenter;
    private ImageView _logoImageView;
    private ViewGroup _loginContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeActivity();
        _presenter = new LoginActivityPresenter(this);
        _logoImageView = findViewById(R.id.splash_logo);
        _loginContainer = findViewById(R.id.splash_option_container);
    }

    private void initializeActivity(){
        setTheme(R.style.AppTheme);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(_presenter.onWindowFocusChanged(hasFocus)){
            super.onWindowFocusChanged(hasFocus);
        }
    }

    public void animate() {
        translateLogo();
        animateContent();
    }

    private void animateContent(){
        Animator animator = new Animator();
        for (int i = 0; i < _loginContainer.getChildCount(); i++) {
            View v = _loginContainer.getChildAt(i);
            animator.animate(v, ITEM_DELAY * i);
        }
    }


    private void translateLogo(){
        ViewCompat.animate(_logoImageView)
                .translationY(-100)
                .setStartDelay(STARTUP_DELAY)
                .setDuration(ANIM_ITEM_DURATION).setInterpolator(
                new DecelerateInterpolator(1.2f)).start();
    }
}
