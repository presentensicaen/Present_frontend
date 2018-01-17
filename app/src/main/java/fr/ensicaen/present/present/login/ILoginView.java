package fr.ensicaen.present.present.login;

import fr.ensicaen.present.present.utils.api.NetworkTools;

/**
 * Created by jueast on 03/12/17.
 */

public interface ILoginView {

    void animate();

    void goToDashboard();

    void finish();

    void showLoadingAnimation();

    void hideLoadingAnimation();

    void showToast(String message, int toastDuration);

    void verifyNetworkConnection() throws NetworkTools.NoInternetException;
}
