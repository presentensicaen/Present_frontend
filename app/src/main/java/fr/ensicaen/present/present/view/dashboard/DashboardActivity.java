package fr.ensicaen.present.present.view.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.presenter.dashboard.DashboardPresenter;
import fr.ensicaen.present.present.presenter.dashboard.IDashboardPresenter;
import fr.ensicaen.present.present.view.enter_code.EnterCodeActivity;
import fr.ensicaen.present.present.view.choose_call_type.ChooseCallTypeActivity;

public class DashboardActivity extends Activity implements IDashboardView {

    private Button _launchCallButton;
    private Button _answerCallButton;
    private IDashboardPresenter _presenter;
    private Button _reviewCallButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _presenter = new DashboardPresenter(this);

        setContentView(R.layout.activity_dashboard);

        _launchCallButton = findViewById(R.id.launchCall);
        setLaunchCallAction();

        _answerCallButton = findViewById(R.id.answerCall);
        setAnswerCallAction();

        _reviewCallButton = findViewById(R.id.review_call_button);
        setReviewCallsAction();
    }

    private void setLaunchCallAction() {
        _launchCallButton.setOnClickListener(v -> _presenter.onLaunchCallClick());
    }

    private void setAnswerCallAction() {
        _answerCallButton.setOnClickListener(v -> _presenter.onAnswerCallClick());
    }

    private void setReviewCallsAction() {
        _reviewCallButton.setOnClickListener(v -> _presenter.onReviewOldCallsClick());

    }

    public void goToGenerateCode() {
        Intent intent = new Intent(DashboardActivity.this, ChooseCallTypeActivity.class);
        startActivity(intent);
    }

    public void goToEnterCode() {
        Intent intent = new Intent(DashboardActivity.this, EnterCodeActivity.class);
        startActivity(intent);
    }
}
