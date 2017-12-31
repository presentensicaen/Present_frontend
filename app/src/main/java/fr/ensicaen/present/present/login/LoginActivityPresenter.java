package fr.ensicaen.present.present.login;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import fr.ensicaen.present.present.BuildConfig;
import fr.ensicaen.present.present.models.ApiResponseModel;
import fr.ensicaen.present.present.models.UserModel;
import fr.ensicaen.present.present.services.IUserService;
import fr.ensicaen.present.present.utils.api.ServiceFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
        verifyLoginCredentials(email, password);
    }

    private void verifyLoginCredentials(String email, String password){
        _user = null;

        IUserService service = ServiceFactory
                .createRetrofitService(IUserService.class, BuildConfig.API_URL);

        service.loginUser(createLoginPayload(email, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> _view.showLoadingAnimation())
                .doOnComplete(this::onVerificationComplete)
                .subscribe(this::handleLoginSuccessResponse, this::handleLoginErrorResponse);

    }

    private void onVerificationComplete() {
        Context c =  _view.getContext();
        if(!isUserValidated()){
            _view.hideLoadingAnimation();
            Toast.makeText(c,"Erreur : login failed", Toast.LENGTH_SHORT).show();
        }else{
            _view.hideLoadingAnimation();
            //@TODO make this a constant
            Toast.makeText(c,"Bienvenue "+_user.getDisplayName(), Toast.LENGTH_SHORT).show();
            _view.goToDashboard();
            _view.finish();
        }
    }

    private boolean isUserValidated() {
        return _user != null;
    }

    private void handleLoginSuccessResponse(ApiResponseModel<UserModel.UserObjectHolder> response){
        _user = response.getData().getUser();
    }

    private void handleLoginErrorResponse(Throwable throwable) {
        //@TODO handle error
        Toast.makeText(
                _view.getContext(),
                "Erreur "+throwable.getLocalizedMessage(),
                Toast.LENGTH_SHORT
        ).show();
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

}
