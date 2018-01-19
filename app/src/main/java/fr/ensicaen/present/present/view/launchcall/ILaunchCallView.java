package fr.ensicaen.present.present.view.launchcall;

import android.content.Context;

import java.io.IOException;

import fr.ensicaen.present.present.utils.Config;

/**
 * Created by pierr on 31/12/2017.
 */

public interface ILaunchCallView {

    void setSuccessMessage(String code);

    Context getContext();

    void showToast(String message, int toastDuration);

    Config getConfigAccessor() throws IOException;

}
