package fr.ensicaen.present.present.view.entercode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.presenter.entercode.EnterCodePresenter;
import fr.ensicaen.present.present.presenter.entercode.IEnterCodePresenter;
import fr.ensicaen.present.present.utils.Config;
import fr.ensicaen.present.present.view.dashboard.DashboardActivity;

/**
 * Created by Jeanne on 14/12/2017.
 */

public class EnterCodeActivity extends Activity implements IEnterCodeView {
    private IEnterCodePresenter _presenter;
    private ViewGroup _loadingAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _presenter = new EnterCodePresenter(this);
        initializeLayoutComponents();
        initializeEnterCodeActivity();
    }

    public void goToDashboard() {
        Intent intent = new Intent(EnterCodeActivity.this, DashboardActivity.class);
        startActivity(intent);

    }

    public void initializeLayoutComponents() {
        setContentView(R.layout.activity_enter_code);
        _loadingAnimation = findViewById(R.id.loading_animation);
    }

    @Override
    public void initializeEnterCodeActivity() {
        Button enterCodeButton = findViewById(R.id.enter_code);
        Button returnToDashboardButton = findViewById(R.id.return_dashboard);

        //@TODO c'est fat
        enterCodeButton.setOnClickListener(view -> {
            if (_presenter.getMessage()) {
                findViewById(R.id.message_container).setVisibility(View.VISIBLE);
                TextView message_header = findViewById(R.id.message_header);
                message_header.setText(R.string.success_message_header);
                TextView message_text = findViewById(R.id.message_text);
                message_text.setText(R.string.success_message_text);
                findViewById(R.id.return_dashboard).setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.message_container).setVisibility(View.VISIBLE);
                TextView message_header = findViewById(R.id.message_header);
                message_header.setText(R.string.error_message_header);
                TextView message_text = findViewById(R.id.message_text);
                message_text.setText(R.string.error_message_text);
            }
        });

        returnToDashboardButton.setOnClickListener(view -> goToDashboard());
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
