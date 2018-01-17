package fr.ensicaen.present.present.configureCall;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import fr.ensicaen.present.present.R;


public class ConfigureCallActivity extends AppCompatActivity implements IConfigureView{

    private IConfigurePresenter _presenter;
    private Button _launchCall;
    private Spinner _timeSpinner;
    private EditText _name;


    @Override
    public void setSuccessMessage(){
        findViewById(R.id.code_result_mesg_container).setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _presenter = new ConfigurePresenter(this);
        initializeConfigureCallActivity();
        initializeLayoutComponents();
    }

    private void initializeLayoutComponents() {
        _launchCall = findViewById(R.id.launchCall);
        _timeSpinner = findViewById(R.id.time_spinner);
        _name = findViewById(R.id.eventName);
        setLaunchCallButtonClick();
    }

    private void initializeConfigureCallActivity() {
        setTheme(R.style.AppTheme);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        setContentView(R.layout.activity_configure_call);

    }

    private void setLaunchCallButtonClick(){
        _launchCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                _presenter.onLaunchCallButtonClick(_timeSpinner.getSelectedItem().toString());
            }
        });
    }

    @Override
    public Context getContext() {
        return this;
    }


}
