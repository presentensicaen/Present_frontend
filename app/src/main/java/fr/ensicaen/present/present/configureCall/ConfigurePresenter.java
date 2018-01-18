package fr.ensicaen.present.present.configureCall;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.ensicaen.present.present.models.ApiResponseModel;
import fr.ensicaen.present.present.models.CallModel;
import fr.ensicaen.present.present.services.ICallService;
import fr.ensicaen.present.present.utils.Config;
import fr.ensicaen.present.present.utils.api.NetworkTools;
import fr.ensicaen.present.present.utils.api.ServiceFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pierr on 31/12/2017.
 */

public class ConfigurePresenter implements IConfigurePresenter {

    private final IConfigureView _view;

    private CallModel _call;
    private Handler _handler;

    @Override
    public void createCall() {
        _call = null;

        ICallService service = ServiceFactory
                .createRetrofitService(ICallService.class, Config.property("API_URL"));
        ArrayList<String> _groups = new ArrayList<String>();
        _groups.add("INFO_TP1");
        _groups.add("INFO_TP2");
        service.createCall(createCallPayload("007", 130, _groups))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(this::onCreationAttemptStart)
                .doOnComplete(this::onVerificationComplete)
                .subscribe(this::handleLoginSuccessResponse, this::handleLoginErrorResponse);
    }

    @Override
    public void onLaunchCallButtonClick(String duration) {
        createCall();

    }

    public ConfigurePresenter(IConfigureView _view) {
        this._view = _view;
        _handler = new Handler();
    }


    private void onCreationAttemptStart(Disposable d) {
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
        if (!isCallCreated()) {
            //@TODO make this a constant
            Toast.makeText(c, "Erreur lors de la création", Toast.LENGTH_SHORT).show();
        } else {
            _view.setSuccessMessage(_call.getCode());
        }
    }

    public boolean isCallCreated() {
        return _call != null;
    }


    private void handleLoginSuccessResponse(ApiResponseModel<CallModel> response) {
        _call = response.getData();
    }

    private void handleLoginErrorResponse(Throwable throwable) {
        //@TODO handle error
        Toast.makeText(
                _view.getContext(),
                "Erreur " + throwable.getLocalizedMessage(),
                Toast.LENGTH_SHORT
        ).show();
    }


        private JSONObject createCallPayload(String _id, int _duration, ArrayList<String> _groups) {
            JSONObject jsonPayload = new JSONObject();
            JSONObject jsonData = new JSONObject();
            try {
                jsonPayload.put("id", _id);
                jsonPayload.put("duration", _duration);
                jsonPayload.put("groups", _groups.toString());

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

    /* for the tests */
    ConfigurePresenter(IConfigureView view, Handler handler) {
        _view = view;
        _handler = handler;
    }

    String getCode(){
        return _call.getCode();
    }


}
