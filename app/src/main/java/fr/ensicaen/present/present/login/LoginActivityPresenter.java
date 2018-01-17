package fr.ensicaen.present.present.login;

import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import fr.ensicaen.present.present.models.ApiResponseModel;
import fr.ensicaen.present.present.models.UserModel;
import fr.ensicaen.present.present.services.IUserService;
import fr.ensicaen.present.present.utils.Config;
import fr.ensicaen.present.present.utils.api.NetworkTools;
import fr.ensicaen.present.present.utils.api.ServiceFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by jueast on 03/12/17.
 */

public class LoginActivityPresenter implements ILoginPresenter {

    private ILoginView _view;
    private boolean _animationStarted;
    private UserModel _user;

    public LoginActivityPresenter(ILoginView view){
        _view = view;
        _animationStarted = false;
    }



    @Override
    public boolean onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus || _animationStarted) {
            return false;
        }
        _view.animate();
        return true;
    }

    @Override
    public void onConnectionButtonClick(String email, String password) {
        verifyLoginCredentials(email, password);
    }

    @Override
    public void onAnimationFinished() {
        _animationStarted = false;
    }

    private void verifyLoginCredentials(String email, String password){
        _user = null;

        IUserService service = ServiceFactory
                .createRetrofitService(IUserService.class, Config.property("API_URL"));

        service.loginUser(createLoginPayload(email, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(this::onLoginAttemptStart)
                .doOnComplete(this::onVerificationComplete)
                .subscribe(this::handleLoginSuccessResponse, this::handleLoginErrorResponse);

    }

    private void onLoginAttemptStart(Disposable d){
        _view.showLoadingAnimation();
        try {
           _view.verifyNetworkConnection();
        } catch (NetworkTools.NoInternetException e) {
            _view.hideLoadingAnimation();
            //@TODO make this a constant
            _view.showToast("Erreur : Network error", Toast.LENGTH_SHORT);
            d.dispose();
        }
    }

    private void onVerificationComplete() {
        if(!isUserValidated()){
            _view.hideLoadingAnimation();
            //@TODO make this a constant
            _view.showToast("Erreur : login failed", Toast.LENGTH_SHORT);
        }else{
            _view.hideLoadingAnimation();
            //@TODO make this a constant
            _view.showToast("Bienvenue "+_user.getDisplayName(), Toast.LENGTH_SHORT);
            _view.goToDashboard();
            _view.finish();
        }
    }

    public boolean isUserValidated() {
        return _user != null;
    }

    private void handleLoginSuccessResponse(ApiResponseModel<UserModel.UserObjectHolder> response){
        _user = response.getData().getUser();
    }

    private void handleLoginErrorResponse(Throwable throwable) {
        _view.hideLoadingAnimation();
        _view.showToast("Erreur "+throwable.getLocalizedMessage(), Toast.LENGTH_SHORT);
    }


    private JSONObject createLoginPayload(String email, String password) {
        JSONObject jsonPayload = new JSONObject();
        JSONObject jsonData = new JSONObject();
        try {
            jsonPayload.put("username", email);
            jsonPayload.put("password", password);
            jsonData.put("data", jsonPayload);
            return jsonData;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonPayload;
    }

    /*
    * F O R  T E S T I N G   O N L Y
    */

    void setUser(UserModel user){
        _user = user;
    }

    UserModel getUser(){
        return _user;
    }
}
