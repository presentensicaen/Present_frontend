package fr.ensicaen.present.present.view.launch_call;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.presenter.launch_call.LaunchCallPresenter;
import fr.ensicaen.present.present.presenter.launch_call.ILaunchCallPresenter;
import fr.ensicaen.present.present.utils.Config;


public class LaunchCallActivity extends Activity implements ILaunchCallView {

    private ILaunchCallPresenter _presenter;
    private Button _launchCall;
    private Spinner _timeSpinner;
    private TextView _code;


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
    }

    private void initializeConfigureCallActivity() {
        setTheme(R.style.AppTheme);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        setContentView(R.layout.activity_launch_call);

    }


    private void setLaunchCallButtonClick() {
        _launchCall.setOnClickListener(v -> {

            /*To hide the virtual keyboard */
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);

            _presenter.onLaunchCallButtonClick(
                    _timeSpinner.getSelectedItem().toString()
            );
        });
    }

    @Override
    public Config getConfigAccessor() throws IOException {
        return new Config(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showToast(String message, int toastDuration) {
        Toast.makeText(this, message, toastDuration).show();
    }

}
