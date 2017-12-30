package fr.ensicaen.present.present.login;

import android.content.Context;

/**
 * Created by jueast on 03/12/17.
 */

public interface ILoginView {

    void animate();

    void goToDashboard();

    void finish();

    Context getContext();

    void showLoadingAnimation();

    void hideLoadingAnimation();
}
