package fr.ensicaen.present.present.configureCall;

import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.models.ApiResponseModel;
import fr.ensicaen.present.present.models.UserModel;
import fr.ensicaen.present.present.services.ICallService;
import fr.ensicaen.present.present.services.IUserService;
import fr.ensicaen.present.present.utils.Config;
import fr.ensicaen.present.present.utils.api.ServiceFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import fr.ensicaen.present.present.utils.api.NetworkTools;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.content.Context;

/**
 * Created by pierr on 31/12/2017.
 */

public class ConfigurePresenter implements IConfigurePresenter {

    private final IConfigureView _view;

    private UserModel _user;
    private Handler _handler;

    @Override
    public void createCall() {
        _user = null;

        ICallService service = ServiceFactory
                .createRetrofitService(ICallService.class, Config.property("API_URL"));

        service.createCall(createCallPayload("007", 130, null))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(this::onLoginAttemptStart)
                .doOnComplete(this::onVerificationComplete)
                .subscribe(this::handleLoginSuccessResponse, this::handleLoginErrorResponse);

    }

    @Override
    public void onLaunchCallButtonClick(String duration) {
        _view.setSuccessMessage();
    }

    public ConfigurePresenter(IConfigureView _view) {
        this._view = _view;
        _handler = new Handler();
    }


    private void onLoginAttemptStart(Disposable d) {
        Context c = _view.getContext();
        try {
            NetworkTools.verifyConnection(c);
        } catch (NetworkTools.NoInternetException e) {
            //@TODO make this a constant
            Toast.makeText(c, "Erreur : Network error", Toast.LENGTH_SHORT).show();
            d.dispose();
        }
    }

    private void onVerificationComplete() {
        Context c = _view.getContext();
        if (!isUserValidated()) {
            //@TODO make this a constant
            Toast.makeText(c, "Erreur : login failed", Toast.LENGTH_SHORT).show();
        } else {
            //@TODO make this a constant
            Toast.makeText(c, "Bienvenue " + _user.getDisplayName(), Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isUserValidated() {
        return _user != null;
    }


    private void handleLoginSuccessResponse(ApiResponseModel<UserModel.UserObjectHolder> response) {
        _user = response.getData().getUser();
    }

    private void handleLoginErrorResponse(Throwable throwable) {
        //@TODO handle error
        Toast.makeText(
                _view.getContext(),
                "Erreur " + throwable.getLocalizedMessage(),
                Toast.LENGTH_SHORT
        ).show();
    }


    private JSONObject createCallPayload(String _id, int _duration, String[] _groups) {
        JSONObject jsonPayload = new JSONObject();
        JSONObject jsonData = new JSONObject();
        try {
            jsonPayload.put("id", _id);
            jsonPayload.put("duration", _duration);
            jsonPayload.put("groups", _groups);

            jsonData.put("data", jsonPayload);
            return jsonData;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonPayload;
    }

    @Override
    public void onDestroy() {

    }


}
