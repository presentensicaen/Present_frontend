package fr.ensicaen.present.present.view.entercode;

import android.location.LocationManager;
import android.view.View;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.view.IGenericView;

/**
 * Created by Jeanne on 14/12/2017.
 */

public interface IEnterCodeView extends IGenericView {
    void initializeEnterCodeActivity();
    void returnToPreviousPage();
    void showLoader();


    void hideLoader();

    LocationManager createLocationManager();
}
