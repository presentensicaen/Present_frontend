package fr.ensicaen.present.present.view.entercode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

    //@TODO later, get the user id - for now fake id
    public static final String _id = "99111";

    private IEnterCodePresenter _presenter;

    private EditText _codeText;
    private Button _enterCodeButton;
    private Button _returnToDashboardButton;
    private ViewGroup _loadingAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        _presenter = new EnterCodePresenter(this);
        initializeEnterCodeActivity();
        initializeLayoutComponents();
    }

    public void initializeLayoutComponents(){
        _codeText = findViewById(R.id.editText2);
        _enterCodeButton = findViewById(R.id.enter_code);
        _returnToDashboardButton = findViewById(R.id.return_dashboard);
        _loadingAnimation = findViewById(R.id.loading_animation);
        setOnEnterCodeButtonClick();
        setOnReturnDashboardButtonClick();
    }

    public void initializeEnterCodeActivity() {
        setTheme(R.style.AppTheme);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_enter_code);
    }

    public void setOnEnterCodeButtonClick(){
        _enterCodeButton.setOnClickListener(view -> _presenter.onEnterCodeButtonClick(_id, _codeText.getText().toString()));
    }

    public void displaySuccessMessage(){
        findViewById(R.id.message_container).setVisibility(View.VISIBLE);
        TextView message_header = findViewById(R.id.message_header);
        message_header.setText(R.string.success_message_header);
        TextView message_text = findViewById(R.id.message_text);
        message_text.setText(R.string.success_message_text);
        findViewById(R.id.return_dashboard).setVisibility(View.VISIBLE);

    }

    public void displayErrorMessage(){
        findViewById(R.id.message_container).setVisibility(View.VISIBLE);
        TextView message_header = findViewById(R.id.message_header);
        message_header.setText(R.string.error_message_header);
        TextView message_text = findViewById(R.id.message_text);
        message_text.setText(R.string.error_message_text);
    }

    public void setOnReturnDashboardButtonClick(){
        _returnToDashboardButton.setOnClickListener(view -> goToDashboard());
    }

    public void goToDashboard(){
        Intent intent = new Intent(EnterCodeActivity.this, DashboardActivity.class);
        startActivity(intent);

    }

    @Override
    public void showLoadingAnimation() {
        _loadingAnimation.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingAnimation() {
        _loadingAnimation.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showToast(String message, int toastDuration) {
        Toast.makeText(this,message, toastDuration).show();
    }

    @Override
    public Config getConfigAccessor() throws IOException{
        return new Config(this);
    }
}
