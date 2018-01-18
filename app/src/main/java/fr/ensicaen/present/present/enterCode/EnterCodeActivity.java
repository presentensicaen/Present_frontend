package fr.ensicaen.present.present.enterCode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.dashboard.DashboardActivity;
import fr.ensicaen.present.present.utils.Config;

/**
 * Created by Jeanne on 14/12/2017.
 */

public class EnterCodeActivity extends Activity implements IEnterCodeView {

    //@TODO later, get the user id - for now fake id
    public static final String _id = "99111";

    private IEnterCodePresenter _presenter;

    private EditText _codeText;
    private Button _enterCodeButton;
    private Button _returnToDashboardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        _presenter = new EnterCodePresenter(this);
        initializeEnterCodeActivity();
        initializeLayoutComponents();
    }

    public void initializeLayoutComponents(){
        _codeText = findViewById(R.id.editText2);
        _enterCodeButton = (Button)findViewById(R.id.enter_code);
        _returnToDashboardButton = (Button)findViewById(R.id.return_dashboard);
        setOnEnterCodeButtonAction();
        setReturnDashboardButtonAction();
    }

    public void initializeEnterCodeActivity() {
        setContentView(R.layout.activity_enter_code);
    }

    public void setOnEnterCodeButtonAction(){
        _enterCodeButton.setOnClickListener(view -> _presenter.onEnterCodeButtonClick(_id, _codeText.getText().toString()));
        if(_presenter.getMessage()){
            displaySuccessMessage();
        } else {
            displayFailureMessage();
        }
    }

    private void displaySuccessMessage(){
        findViewById(R.id.message_container).setVisibility(View.VISIBLE);
        TextView message_header = findViewById(R.id.message_header);
        message_header.setText(R.string.success_message_header);
        TextView message_text = findViewById(R.id.message_text);
        message_text.setText(R.string.success_message_text);
        findViewById(R.id.return_dashboard).setVisibility(View.VISIBLE);

    }

    private void displayFailureMessage(){
        findViewById(R.id.message_container).setVisibility(View.VISIBLE);
        TextView message_header = findViewById(R.id.message_header);
        message_header.setText(R.string.error_message_header);
        TextView message_text = findViewById(R.id.message_text);
        message_text.setText(R.string.error_message_text);
    }

    public void setReturnDashboardButtonAction(){
        _returnToDashboardButton.setOnClickListener(view -> goToDashboard());
    }

    @Override
    public void goToDashboard(){
        Intent intent = new Intent(EnterCodeActivity.this, DashboardActivity.class);
        startActivity(intent);

    }

    @Override
    public Config getConfigAccessor() throws IOException {
        return new Config(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

}
