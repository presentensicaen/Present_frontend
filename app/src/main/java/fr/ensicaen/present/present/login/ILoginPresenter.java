package fr.ensicaen.present.present.login;

import android.text.Editable;

/**
 * Created by jueast on 03/12/17.
 */

public interface ILoginPresenter {

    boolean onWindowFocusChanged(boolean hasFocus);


    void onConnectionButtonClick(String email, String password);
}
