package fr.ensicaen.present.present.login;

import android.os.Handler;


/**
 * Created by jueast on 03/12/17.
 */

public class LoginActivityPresenter implements ILoginPresenter {

    private ILoginView _view;
    private boolean animationStarted;

    public LoginActivityPresenter(ILoginView view){
        _view = view;
        animationStarted = false;

    }

    @Override
    public boolean onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus || animationStarted) {
            return false;
        }

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                _view.animate();
            }
        }, 500);

        return true;
    }


}
