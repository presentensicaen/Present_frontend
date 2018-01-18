package fr.ensicaen.present.present.enterCode;


import android.content.Context;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

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
    //@TODO add a boolean : messageServer : 1 right code, 0 wrong code

    public EnterCodePresenter(IEnterCodeView view){
        _view = view;
    }

    public void onEnterCodeButtonClick(String id, String code){
        verifyCode(id, code);
    }


    public void verifyCode(String id, String code){
        IEnterCodeService service;
        service = ServiceFactory.createRetrofitService(IEnterCodeService.class, Config.property("API_URL"));
        service.checkCode(createEnterCodePayload(id, code))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(this::connectionToServerAttempt);
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



    private void onCodeVerificationComplete(ApiResponseModel<String> response) {

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

    //@TODO create fonction getMessage server : right now boolean
    //@TODO later : date (message from server)
    public boolean getMessage(){ return true;}

}
