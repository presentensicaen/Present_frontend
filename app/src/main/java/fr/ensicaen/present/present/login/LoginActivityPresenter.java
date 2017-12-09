package fr.ensicaen.present.present.login;

import android.os.Handler;


/**
 * Created by jueast on 03/12/17.
 */

public final class LoginActivityPresenter implements ILoginPresenter {

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

    @Override
    public void onConnectionButtonClick(String email, String password) {
        if(verifyCredentials(email, password)){
           _view.goToDashboard(); //@TODO needs to save a user model somewhere
        }
    }

    private boolean verifyCredentials(String email, String password){
        /*@TODO this needs to of course do a real verification */
        return true;
    }

}
