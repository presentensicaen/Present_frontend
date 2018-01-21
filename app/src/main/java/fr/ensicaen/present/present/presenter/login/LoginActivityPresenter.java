package fr.ensicaen.present.present.presenter.login;

import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import fr.ensicaen.present.present.models.ApiResponseModel;
import fr.ensicaen.present.present.models.UserModel;
import fr.ensicaen.present.present.services.IUserService;
import fr.ensicaen.present.present.session.SessionManager;
import fr.ensicaen.present.present.utils.Config;
import fr.ensicaen.present.present.utils.api.ServiceFactory;
import fr.ensicaen.present.present.view.login.ILoginView;
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
    private Config _config;
    private SessionManager session;

    public LoginActivityPresenter(ILoginView view) {
        _view = view;
        session = new SessionManager(_view.getContext());
        _animationStarted = false;
        try {
            _config = _view.getConfigAccessor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LoginActivityPresenter(ILoginView view, Config c, SessionManager s) {
        _view = view;
        _animationStarted = false;
        _config = c;
        session = s;
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
                .doOnSubscribe(this::onLoginAttemptStart)
                .doOnComplete(this::onVerificationComplete)
                .subscribe(this::handleLoginSuccessResponse, this::handleLoginErrorResponse);

    }

    private void onLoginAttemptStart(Disposable disposable) {
        _view.showLoadingAnimation();
        //@TODO check if internet connection exists ?
    }


    public void onVerificationComplete() {
        _view.hideLoadingAnimation();
        if (!isUserValidated()) {
            //@TODO make this a constant
            _view.showToast("Error : login failed", Toast.LENGTH_SHORT);
        } else {
            //@TODO make this a constant
            _view.showToast("Bienvenue " + _user.getDisplayName(), Toast.LENGTH_SHORT);
            session.createLoginSession(_user.getDisplayName(), _user.getId());
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
    public void setUser(UserModel user) {
        _user = user;
    }

    public UserModel getUser() {
        return _user;
    }

    public boolean getAnimationStarted() {
        return _animationStarted;
    }
}
