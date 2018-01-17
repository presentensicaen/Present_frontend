package fr.ensicaen.present.present.dashboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.enterCode.EnterCodeActivity;
import fr.ensicaen.present.present.generateCode.GenerateCodeActivity;

public class DashboardActivity extends AppCompatActivity implements IDashboardView{

    private Button _launchCallButton;
    private Button _answerCallButton;
    private IDashboardPresenter _presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _presenter = new DashboardPresenter(this);

        setContentView(R.layout.activity_dashboard);

        _launchCallButton = (Button)findViewById(R.id.launchCall);
        setLaunchCallAction();

        _answerCallButton = (Button)findViewById(R.id.answerCall);
        setAnswerCallAction();
    }

    private void setLaunchCallAction() {
        _launchCallButton.setOnClickListener(v -> _presenter.onLaunchCallClick());
    }

    private void setAnswerCallAction(){
        _answerCallButton.setOnClickListener(v -> _presenter.onAnswerCallClick());
    }

    public void goToGenerateCode() {
        Intent intent = new Intent(DashboardActivity.this, GenerateCodeActivity.class);
        startActivity(intent);
    }

    public void goToEnterCode(){
        Intent intent = new Intent(DashboardActivity.this, EnterCodeActivity.class);
        startActivity(intent);
    }
}
