package fr.ensicaen.present.present.enterCode;


import android.content.Context;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import fr.ensicaen.present.present.models.ApiResponseModel;
import fr.ensicaen.present.present.models.EnterCodeModel;
import fr.ensicaen.present.present.services.IEnterCodeService;
import fr.ensicaen.present.present.utils.Config;
import fr.ensicaen.present.present.utils.api.NetworkTools;
import fr.ensicaen.present.present.utils.api.ServiceFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Jeanne on 14/12/2017.
 */

public class EnterCodePresenter implements IEnterCodePresenter{

    private IEnterCodeView _view;
    private Config _config;

    //@TODO _serverMessage will be of type data
    private boolean _serverMessage;


    public EnterCodePresenter(IEnterCodeView view){
        _view = view;
        try {
            _config = _view.getConfigAccessor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onEnterCodeButtonClick(String id, String code){
        verifyCode(id, code);
    }


    public void verifyCode(String id, String code){
        IEnterCodeService service;
        service = ServiceFactory.createRetrofitService(IEnterCodeService.class, _config.property("API_URL"));
        service.checkCode(createEnterCodePayload(id, code))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(this::connectionToServerAttempt)
                .subscribe(this::onCodeVerificationComplete);
    }

    private void connectionToServerAttempt(Disposable d){
        Context c = _view.getContext();
        try {
            NetworkTools.verifyConnection(c);
        } catch (NetworkTools.NoInternetException e) {
            //@TODO make this a constant
            Toast.makeText(c, "Erreur : Network error", Toast.LENGTH_SHORT).show();
            d.dispose();
        }
    }

    private void onCodeVerificationComplete(ApiResponseModel<String> response){
        if(response.getStatus() == 200){
            _serverMessage = true;
        } else {
            _serverMessage = false;
        }
    }

    private JSONObject createEnterCodePayload(String id, String code) {
        JSONObject jsonPayload = new JSONObject();
        JSONObject jsonData = new JSONObject();

        try {
            jsonPayload.put("id", id);
            jsonPayload.put("code", code);
            jsonData.put("data", jsonPayload);
            return jsonData;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonPayload;
    }


    //@TODO getMessage should return the data send by the server (when implemented)
    public boolean getMessage(){ return _serverMessage;}

}
