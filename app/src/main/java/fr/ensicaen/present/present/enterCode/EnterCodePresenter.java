package fr.ensicaen.present.present.enterCode;

import android.app.Activity;
import android.content.Intent;

import fr.ensicaen.present.present.dashboard.DashboardActivity;
import fr.ensicaen.present.present.enterCode.EnterCodeActivity;

/**
 * Created by Jeanne on 14/12/2017.
 */

public class EnterCodePresenter implements IEnterCodePresenter{

    private IEnterCodeView _view;

    public EnterCodePresenter(IEnterCodeView view){
        _view = view;
    }

    public void sendCode() {

    }

    public boolean getMessage(){
        return false;
    }

}
