package fr.ensicaen.present.present.splash;

/**
 * Created by jueast on 03/12/17.
 */

public interface ISplashScreenView {

    void openLoginActivity();

    void openDashboardActivity();

    void startSyncService();

    void showProgressBar();

    void finish();
}
