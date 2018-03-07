package fr.ensicaen.present.present.view.subscribe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.presenter.subscribe.ISubscribePresenter;
import fr.ensicaen.present.present.presenter.subscribe.SubscribeActivityPresenter;

/**
 * Created by leymarie on 07/03/18.
 */

public class SubscribeActivity extends Activity implements ISubscribeView {

    private ISubscribePresenter _presenter;
    private EditText _lastname;
    private EditText _firstname;
    private EditText _mail;
    private EditText _studentID;
    private Button _subscribeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _presenter = new SubscribeActivityPresenter(this);
        initializeActivity();
        initComponents();
    }

    private void initComponents(){
        _lastname = findViewById(R.id.lastname_text);
        _lastname = findViewById(R.id.firstname_text);
        _lastname = findViewById(R.id.mail_text);
        _lastname = findViewById(R.id.studentID_text);
        _subscribeButton = findViewById(R.id.subscribe_button);
    }

    private void initializeActivity(){
        setTheme(R.style.AppTheme);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        setContentView(R.layout.activity_subscribe);

    }
}
