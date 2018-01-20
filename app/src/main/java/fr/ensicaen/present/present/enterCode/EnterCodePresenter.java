package fr.ensicaen.present.present.enterCode;

import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import fr.ensicaen.present.present.models.EnterCodeModel;
import fr.ensicaen.present.present.services.IEnterCodeService;
import fr.ensicaen.present.present.utils.Config;
import fr.ensicaen.present.present.utils.api.ServiceFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Jeanne on 14/12/2017.
 */

public class EnterCodePresenter implements IEnterCodePresenter{

    private IEnterCodeView _view;
    private Config _config;
    private EnterCodeModel _verifyCode;


    public EnterCodePresenter(IEnterCodeView view){
        _view = view;
        try {
            _config = _view.getConfigAccessor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EnterCodePresenter(IEnterCodeView view, Config c){
        _view = view;
        _config = c;
    }

    @Override
    public void onEnterCodeButtonClick(String id, String code){
        verifyCode(createEnterCodePayload(id, code));
    }


    public void verifyCode(JSONObject payload){
        _verifyCode = null;

        IEnterCodeService service = ServiceFactory.createRetrofitService(IEnterCodeService.class, _config.property("API_URL"));
        service.checkCode(payload)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(this::onCodeVerificationComplete);
    }



    public void onCodeVerificationComplete(){
        if(!isCodeRight()){
            _view.displaySuccessMessage();
        } else {
            _view.displaySuccessMessage();
        }
    }

    public boolean isCodeRight(){
        return _verifyCode != null;
    }

    public JSONObject createEnterCodePayload(String id, String code) {
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

}
