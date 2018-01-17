package fr.ensicaen.present.present.configureCall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.login.ILoginPresenter;

public class ConfigureCallActivity extends AppCompatActivity implements IConfigureView{

    private IConfigurePresenter _presenter;
    private Button _launchCall;
    private Spinner _timeSpinner;


    private int _time; /* duration of the call in sec */
    private String _name;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _presenter = new ConfigurePresenter(this);
        setContentView(R.layout.activity_configure_call);
        initializeConfigureCallActivity();
    }

    public void initializeConfigureCallActivity() {
        _launchCall = (Button)findViewById(R.id.launchCall);
        _timeSpinner = (Spinner)findViewById(R.id.time_spinner);
        _launchCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                _presenter.onLaunchCallButtonClick(_timeSpinner.getSelectedItem().toString());

            }
        });
    }

    @Override
    public void setSuccessMessage(){
        findViewById(R.id.code_result_mesg_container).setVisibility(View.VISIBLE);
    }



}
