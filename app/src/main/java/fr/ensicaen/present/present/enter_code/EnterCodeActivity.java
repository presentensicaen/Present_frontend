package fr.ensicaen.present.present.enter_code;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.dashboard.DashboardActivity;

/**
 * Created by Jeanne on 14/12/2017.
 */

public class EnterCodeActivity extends Activity implements IEnterCodeView {
    private IEnterCodePresenter _presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _presenter = new EnterCodePresenter(this);
        initializeLayoutComponents();
        initializeEnterCodeActivity();
    }

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

    public void goToDashboard() {
        Intent intent = new Intent(EnterCodeActivity.this, DashboardActivity.class);
        startActivity(intent);

    }

    public void initializeLayoutComponents() {
        setContentView(R.layout.activity_enter_code);
    }

}
