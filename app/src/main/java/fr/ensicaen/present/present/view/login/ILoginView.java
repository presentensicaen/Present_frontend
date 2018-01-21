package fr.ensicaen.present.present.view.login;

import android.content.Context;

import java.io.IOException;

import fr.ensicaen.present.present.utils.Config;

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

    Config getConfigAccessor() throws IOException;

    Context getContext();
}
