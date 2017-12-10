package fr.ensicaen.present.present.login;

import android.os.Handler;


/**
 * Created by jueast on 03/12/17.
 */

public class LoginActivityPresenter implements ILoginPresenter {

    private ILoginView _view;
    private boolean _animationStarted;

    public LoginActivityPresenter(ILoginView view){
        _view = view;
        _animationStarted = false;

    }

    @Override
    public boolean onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus || _animationStarted) {
            return false;
        }

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                _view.animate();
                _animationStarted = false;
            }
        }, 500);

        return true;
    }

    @Override
    public void onConnectionButtonClick(String email, String password) {
        if(verifyCredentials(email, password)){
           _view.goToDashboard(); //@TODO needs to save a user model somewhere
            _view.finish();

        }
    }

    private boolean verifyCredentials(String email, String password){
        /*@TODO this needs to of course do a real verification */
        return true;
    }

}
