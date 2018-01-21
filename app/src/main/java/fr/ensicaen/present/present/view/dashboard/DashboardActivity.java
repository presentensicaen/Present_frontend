package fr.ensicaen.present.present.view.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.presenter.dashboard.DashboardPresenter;
import fr.ensicaen.present.present.presenter.dashboard.IDashboardPresenter;
import fr.ensicaen.present.present.utils.Config;
import fr.ensicaen.present.present.view.choosecalltype.ChooseCallTypeActivity;
import fr.ensicaen.present.present.view.choosepreviouscall.ChoosePreviousCallActivity;
import fr.ensicaen.present.present.view.entercode.EnterCodeActivity;
import fr.ensicaen.present.present.view.reviewcall.ReviewCallActivity;

public class DashboardActivity extends Activity implements IDashboardView {

    private Button _launchCallButton;
    private Button _answerCallButton;
    private IDashboardPresenter _presenter;
    private Button _reviewCallButton;
    private ViewGroup _loadingAnimation;

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

        _loadingAnimation = findViewById(R.id.loading_animation);

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

    @Override
    public void goToReviewCall() {
        Intent intent = new Intent(DashboardActivity.this, ChoosePreviousCallActivity.class);
        startActivity(intent);
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

    @Override
    public Config getConfigAccessor() throws IOException {
        return new Config(this);
    }
}
