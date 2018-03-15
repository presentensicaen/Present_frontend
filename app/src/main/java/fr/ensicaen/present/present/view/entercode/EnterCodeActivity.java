package fr.ensicaen.present.present.view.entercode;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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
import fr.ensicaen.present.present.view.dashboard.DashboardActivity;

import static java.lang.Math.acos;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

/**
 * Created by Jeanne on 14/12/2017.
 */

public class EnterCodeActivity extends Activity implements IEnterCodeView, ActivityCompat.OnRequestPermissionsResultCallback {
    private IEnterCodePresenter _presenter;
    private ViewGroup _loadingAnimation;

    LocationManager locationManager;
    double longitudeNetwork, latitudeNetwork;
    double _distance = 0;
    private static final int REQUEST_COARSE_LOCATION = 1;
    boolean _locationManagerRunning = false;
    boolean _requestPresence = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        super.onCreate(savedInstanceState);
        _presenter = new EnterCodePresenter(this);
        initializeLayoutComponents();
        initializeEnterCodeActivity();

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_COARSE_LOCATION);
        }

        if (!checkLocation()) {
            return;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            showToast("No permission", Toast.LENGTH_LONG);
            return;
        }
        _locationManagerRunning = true;
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 0, locationListenerNetwork);
        showToast("Recherche de votre position", Toast.LENGTH_LONG);

    }


    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_COARSE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                }
                break;
            default:
                break;
        }
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

        TextView tv = (TextView) findViewById(R.id.distanceEnsi);
        tv.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                Log.d("tag","changement position");
                if (_requestPresence){
                    testPresence();
                }
            }
        });


        //@TODO c'est fat
        enterCodeButton.setOnClickListener(view -> {

            if (!_locationManagerRunning){
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60 * 1000, 10, locationListenerNetwork);
                showToast("Recherche de votre position", Toast.LENGTH_LONG);
            }

            if (_distance != 0){
                testPresence();
            } else {
                _requestPresence = true;
            }

        });

        returnToDashboardButton.setOnClickListener(view -> goToDashboard());
    }

    void testPresence(){

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

    /* ########################## PARTIE LOCALISATION ############################################*/


    private boolean checkLocation() {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                        "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

/*
    public void toggleNetworkUpdates(View view) {
        if (!checkLocation()) {
            return;
        }
        Button button = (Button) view;
        if (button.getText().equals("pause")) {
            locationManager.removeUpdates(locationListenerNetwork);
            button.setText("resume");
        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                showToast("No permission", Toast.LENGTH_LONG);
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60 * 1000, 10, locationListenerNetwork);
            showToast("Network provider started running", Toast.LENGTH_LONG);

            button.setText("pause");



        }
    }*/


    public void distance(double latitude1, double longitude1, double latitude2, double longitude2){
        // ACOS(SIN(RADIANS(B2))*SIN(RADIANS(B3))+COS(RADIANS(B2))*COS(RADIANS(B3))*COS(RADIANS(C2-C3)))*6371
        double a= sin(toRadians(latitude1))*sin(toRadians(latitude2));
        double b= cos(toRadians(latitude1))*cos(toRadians(latitude2));
        double c= cos(toRadians(longitude1-longitude2));

        _distance = acos(a+b*c)*6371;

    }


    private final LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {
            TextView tv = (TextView)findViewById(R.id.distanceEnsi);
            longitudeNetwork = location.getLongitude();
            latitudeNetwork = location.getLatitude();
            distance(latitudeNetwork,longitudeNetwork,49.2144397,-0.3665470999999343);
            tv.setText(Double.toString(_distance)+"km");

            locationManager.removeUpdates(locationListenerNetwork);

            /*
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    distance(latitudeNetwork,longitudeNetwork,49.2144397,-0.3665470999999343);

                    tv.setText(Double.toString(_distance)+"km");
                    showToast("Network Provider update", Toast.LENGTH_SHORT);

                }
            });*/
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };



}
