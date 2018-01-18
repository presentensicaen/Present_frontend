package fr.ensicaen.present.present.enterCode;

import android.content.Context;

/**
 * Created by Jeanne on 14/12/2017.
 */

interface IEnterCodeView {
    void initializeEnterCodeActivity();

    void initializeLayoutComponents();

    Context getContext();

    void goToDashboard();
}
