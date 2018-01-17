package fr.ensicaen.present.present.enterCode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        Button enterCodeButton = (Button) findViewById(R.id.enter_code);
        Button returnToDashboardButton = (Button) findViewById(R.id.return_dashboard);
        enterCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.success_message_container).setVisibility(View.VISIBLE);
            }
        });

        returnToDashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToDashboard();
            }
        });
    }

    public void goToDashboard() {
        Intent intent = new Intent(EnterCodeActivity.this, DashboardActivity.class);
        startActivity(intent);

    }

    public void initializeLayoutComponents() {
        setContentView(R.layout.activity_enter_code);
    }

}
