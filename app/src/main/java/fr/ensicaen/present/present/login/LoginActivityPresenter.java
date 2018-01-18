package fr.ensicaen.present.present.login;

import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import fr.ensicaen.present.present.models.ApiResponseModel;
import fr.ensicaen.present.present.models.UserModel;
import fr.ensicaen.present.present.services.IUserService;
import fr.ensicaen.present.present.utils.Config;
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
    private Config _config;

    public LoginActivityPresenter(ILoginView view) {
        _view = view;
        _animationStarted = false;
        try {
            _config = _view.getConfigAccessor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LoginActivityPresenter(ILoginView view, Config c) {
        _view = view;
        _animationStarted = false;
        _config = c;
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
    public void onAnimationFinished() {
        _animationStarted = false;
    }

    @Override
    public void onConnectionButtonClick(String email, String password) {
        attempLogin(createLoginPayload(email, password));
    }

    public void attempLogin(JSONObject payload) {
        _user = null;

        IUserService service = ServiceFactory
                .createRetrofitService(IUserService.class, _config.property("API_URL"));
        service.loginUser(payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(this::onVerificationComplete)
                .subscribe(this::handleLoginSuccessResponse, this::handleLoginErrorResponse);

    }


    public void onVerificationComplete() {
        if (!isUserValidated()) {
            _view.hideLoadingAnimation();
            //@TODO make this a constant
            _view.showToast("Error : login failed", Toast.LENGTH_SHORT);
        } else {
            _view.hideLoadingAnimation();
            //@TODO make this a constant
            _view.showToast("Bienvenue " + _user.getDisplayName(), Toast.LENGTH_SHORT);
            _view.goToDashboard();
            _view.finish();
        }
    }

    private void handleLoginSuccessResponse(ApiResponseModel<UserModel.UserObjectHolder> response) {
        _user = response.getData().getUser();
    }

    private void handleLoginErrorResponse(Throwable throwable) {
        _view.hideLoadingAnimation();
        _view.showToast("Erreur " + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT);
    }

    public boolean isUserValidated() {
        return _user != null;
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
    void setUser(UserModel user) {
        _user = user;
    }

    UserModel getUser() {
        return _user;
    }

    boolean getAnimationStarted() {
        return _animationStarted;
    }
}
