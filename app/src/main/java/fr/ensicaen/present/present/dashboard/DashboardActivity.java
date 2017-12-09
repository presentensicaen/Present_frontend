package fr.ensicaen.present.present.dashboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.generateCode.GenerateCodeActivity;

public class DashboardActivity extends AppCompatActivity implements IDashboardView{

    private Button _launchCallButton;
    private IDashboardPresenter _presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _presenter = new DashboardPresenter(this);

        setContentView(R.layout.activity_dashboard);

        _launchCallButton = (Button)findViewById(R.id.launchCall);
        setLaunchCallAction();
    }

    private void setLaunchCallAction() {
        _launchCallButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                _presenter.onLaunchCallClick();
            }
        });
    }

    public void goToGenerateCode() {
        Intent intent = new Intent(DashboardActivity.this, GenerateCodeActivity.class);
        startActivity(intent);
    }
}
