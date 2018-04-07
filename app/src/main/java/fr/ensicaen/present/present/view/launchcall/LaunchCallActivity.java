package fr.ensicaen.present.present.view.launchcall;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.presenter.launchcall.ILaunchCallPresenter;
import fr.ensicaen.present.present.presenter.launchcall.LaunchCallPresenter;
import fr.ensicaen.present.present.utils.Config;


public class LaunchCallActivity extends AppCompatActivity implements ILaunchCallView {

    private ILaunchCallPresenter _presenter;
    private Button _launchCall;
    private Spinner _timeSpinner;
    private TextView _code;
    private ViewGroup _loadingAnimation;


    @Override
    public void setSuccessMessage(String code) {
        _code.setText(code);
        findViewById(R.id.code_result_mesg_container).setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _presenter = new LaunchCallPresenter(this);
        initializeConfigureCallActivity();
        initializeLayoutComponents();

    }

    private void initializeLayoutComponents() {
        _launchCall = findViewById(R.id.launchCall);
        _timeSpinner = findViewById(R.id.time_spinner);
        _code = findViewById(R.id.code);
        setLaunchCallButtonClick();
        _loadingAnimation = findViewById(R.id.loading_animation);
    }

    private void initializeConfigureCallActivity() {
        setTheme(R.style.AppTheme);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        setContentView(R.layout.activity_launch_call);

    }


    private void setLaunchCallButtonClick() {
        _launchCall.setOnClickListener(v -> {
            setSuccessMessage("BOB77");
        });
    }

    @Override
    public Config getConfigAccessor() throws IOException {
        return new Config(this);
    }

    @Override
    public void showLoadingAnimation() { _loadingAnimation.setVisibility(View.VISIBLE); }

    @Override
    public void hideLoadingAnimation() {
        _loadingAnimation.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showToast(String message, int toastDuration) {
        Toast.makeText(this, message, toastDuration).show();
    }

}
