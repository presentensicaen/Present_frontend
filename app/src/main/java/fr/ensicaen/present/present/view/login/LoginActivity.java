package fr.ensicaen.present.present.view.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.presenter.login.ILoginPresenter;
import fr.ensicaen.present.present.presenter.login.LoginActivityPresenter;
import fr.ensicaen.present.present.session.SessionManager;
import fr.ensicaen.present.present.utils.Config;
import fr.ensicaen.present.present.view.dashboard.DashboardActivity;

public class LoginActivity extends Activity implements ILoginView {

    public static final int STARTUP_DELAY = 300;
    public static final int ANIM_ITEM_DURATION = 1000;
    public static final int ITEM_DELAY = 300;

    private ILoginPresenter _presenter;
    private ImageView _logoImageView;
    private ViewGroup _loginContainer;
    private Button _loginButton;
    private EditText _emailText;
    private EditText _passwordText;
    private ViewGroup _loadingAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _presenter = new LoginActivityPresenter(this);
        initializeActivity();
        initializeLayoutComponents();

    }

    private void initializeLayoutComponents() {
        _logoImageView = findViewById(R.id.login_logo);
        _loginContainer = findViewById(R.id.logo_option_container);
        _loginButton = findViewById(R.id.login_connect_button);
        _emailText = findViewById(R.id.login_email);
        _passwordText = findViewById(R.id.login_password);
        _passwordText.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());
        _loadingAnimation = findViewById(R.id.loading_animation);

        setConnectionButtonClickAction();
    }

    private void initializeActivity() {
        setTheme(R.style.AppTheme);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (_presenter.onWindowFocusChanged(hasFocus)) {
            super.onWindowFocusChanged(hasFocus);
        }
    }

    public void animate() {
        new Handler().postDelayed(() -> {
            translateLogo();
            animateContent();
            _presenter.onAnimationFinished();
        }, 500);

    }

    @Override
    public void goToDashboard() {
        Intent loginIntent = new Intent(LoginActivity.this, DashboardActivity.class);
        startActivity(loginIntent);
    }

    @Override
    public void showLoadingAnimation() {
        _loadingAnimation.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingAnimation() {
        _loadingAnimation.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showToast(String message, int toastDuration) {
        Toast.makeText(this, message, toastDuration).show();
    }

    @Override
    public Config getConfigAccessor() throws IOException {
        return new Config(this);
    }

    private void animateContent() {
        for (int i = 0; i < _loginContainer.getChildCount(); i++) {
            View v = _loginContainer.getChildAt(i);
            animateIfButton(v, ITEM_DELAY * i);
            animateIfTextInput(v, ITEM_DELAY * i);
        }
    }

    private void animateIfTextInput(View v, int delay) {
        if (!(v instanceof EditText)) {
            return;
        }
        ViewCompat.animate(v)
                .translationY(50).alpha(1)
                .setStartDelay((long) delay + 500)
                .setDuration(1000)
                .setInterpolator(new DecelerateInterpolator())
                .start();
    }

    private void animateIfButton(View v, int delay) {
        if (!(v instanceof Button)) {
            return;
        }

        ViewCompat.animate(v)
                .scaleY(1).scaleX(1)
                .setStartDelay((long) delay + 500)
                .setDuration(500)
                .setInterpolator(new DecelerateInterpolator())
                .start();
    }

    private void translateLogo() {
        ViewCompat.animate(_logoImageView)
                .translationY(-200)
                .setStartDelay(STARTUP_DELAY)
                .setDuration(ANIM_ITEM_DURATION).setInterpolator(
                new DecelerateInterpolator(1.2f)).start();
    }

    private void setConnectionButtonClickAction() {
        _loginButton.setOnClickListener(v -> _presenter.onConnectionButtonClick(
                _emailText.getText().toString(),
                _passwordText.getText().toString()
        ));
    }

    @Override
    public Context getContext(){
        return this.getApplicationContext();
    }


}
