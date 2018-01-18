package fr.ensicaen.present.present.enterCode;

import android.content.Context;

import java.io.IOException;

import fr.ensicaen.present.present.utils.Config;

/**
 * Created by Jeanne on 14/12/2017.
 */

interface IEnterCodeView {
    void initializeEnterCodeActivity();

    void initializeLayoutComponents();

    Context getContext();

    void goToDashboard();

    Config getConfigAccessor() throws IOException;
}
