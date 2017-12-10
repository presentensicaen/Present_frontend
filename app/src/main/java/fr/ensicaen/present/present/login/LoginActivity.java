package fr.ensicaen.present.present.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.dashboard.DashboardActivity;
import fr.ensicaen.present.present.generateCode.GenerateCodeActivity;
import fr.ensicaen.present.present.selectGroups.SelectGroups;
import fr.ensicaen.present.present.utils.Animations.Animator;

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

        setConnectionButtonClickAction();
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

    @Override
    public void goToDashboard() {
        Intent loginIntent = new Intent(LoginActivity.this, DashboardActivity.class);
        startActivity(loginIntent);
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

    private void setConnectionButtonClickAction(){
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _presenter.onConnectionButtonClick(
                        _emailText.getText().toString(),
                        _passwordText.getText().toString()
                );
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent);

            }
        });
    }
}
