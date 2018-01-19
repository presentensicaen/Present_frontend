package fr.ensicaen.present.present.view.splash;


import java.io.IOException;

import fr.ensicaen.present.present.utils.Config;

/**
 * Created by jueast on 03/12/17.
 */

public interface ISplashScreenView {

    void openLoginActivity();

    void finish();

    Config getConfigAccessor() throws IOException;
}
