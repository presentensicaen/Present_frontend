package fr.ensicaen.present.present.view.entercode;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import fr.ensicaen.present.present.utils.providers.PermissionCodes;
import fr.ensicaen.present.present.view.dashboard.DashboardActivity;


/**
 * Created by Jeanne on 14/12/2017.
 */
public class EnterCodeActivity extends AppCompatActivity implements IEnterCodeView, ActivityCompat.OnRequestPermissionsResultCallback {
    private IEnterCodePresenter _presenter;
    private ViewGroup _loadingAnimation;

    private double _distance = 0;
    private boolean _requestPresence = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        _presenter = new EnterCodePresenter(this);
        initializeLayoutComponents();
        initializeEnterCodeActivity();

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PermissionCodes.REQUEST_COARSE_LOCATION);
        }

    }

    public void showLoader(){
        findViewById(R.id.progressBar2).setVisibility(View.INVISIBLE);
    }

    public void hideLoader(){
        findViewById(R.id.progressBar2).setVisibility(View.VISIBLE);
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        _presenter.onPermissionResponseReceieved(requestCode, grantResults);
    }

    public void goToDashboard() {
        Intent intent = new Intent(EnterCodeActivity.this, DashboardActivity.class);
        startActivity(intent);

    }

    public void initializeLayoutComponents() {
        setContentView(R.layout.activity_enter_code);
        _loadingAnimation = findViewById(R.id.loading_animation);
    }



    @SuppressLint("MissingPermission")
    @Override
    public void initializeEnterCodeActivity() {

        Button enterCodeButton = findViewById(R.id.enter_code);
        Button returnToDashboardButton = findViewById(R.id.return_dashboard);


        //@TODO c'est fat
        enterCodeButton.setOnClickListener(view -> {
            findViewById(R.id.message_container).setVisibility(View.VISIBLE);
            TextView message_header = findViewById(R.id.message_header);
            message_header.setText(R.string.success_message_header);
            TextView message_text = findViewById(R.id.message_text);
            message_text.setText(R.string.success_message_text);
            findViewById(R.id.return_dashboard).setVisibility(View.VISIBLE);

        });

        returnToDashboardButton.setOnClickListener(view -> goToDashboard());
    }


    void testPresence(){
        /**@TODO refactor and place in presenter */
        if (_distance > 0.2){
            findViewById(R.id.message_container).setVisibility(View.VISIBLE);
            TextView message_header = findViewById(R.id.message_header);
            message_header.setText(R.string.error_message_header);
            TextView message_text = findViewById(R.id.message_text);
            message_text.setText("Va en cours petit malin !");
        }
        else if (_presenter.getMessage()) {
            findViewById(R.id.message_container).setVisibility(View.VISIBLE);
            TextView message_header = findViewById(R.id.message_header);
            message_header.setText(R.string.success_message_header);
            TextView message_text = findViewById(R.id.message_text);
            message_text.setText(R.string.success_message_text);
            findViewById(R.id.return_dashboard).setVisibility(View.VISIBLE);
        }
        else {
            findViewById(R.id.message_container).setVisibility(View.VISIBLE);
            TextView message_header = findViewById(R.id.message_header);
            message_header.setText(R.string.error_message_header);
            TextView message_text = findViewById(R.id.message_text);
            message_text.setText(R.string.error_message_text);
        }
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
        Toast.makeText(this, message, toastDuration).show();
    }

    @Override
    public Config getConfigAccessor() throws IOException {
        return new Config(this);
    }

    @Override
    public void returnToPreviousPage() {
        goToDashboard();
    }

    @Override
    public LocationManager createLocationManager() {
        return (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

}
