package fr.ensicaen.present.present.presenter.subscribe;

import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import fr.ensicaen.present.present.utils.Config;
import fr.ensicaen.present.present.view.subscribe.ISubscribeView;

/**
 * Created by leymarie on 07/03/18.
 */

public class SubscribeActivityPresenter implements ISubscribePresenter {
    private ISubscribeView _view;
    private Config _config;

    public SubscribeActivityPresenter(ISubscribeView view){
        _view = view;
        try {
            _config = _view.getConfigAccessor();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onSubscribeButtonClick(String lastname, String firstname, String id, String mail){
        subscribe(createSubcribePayload(lastname, firstname, id, mail));
    }

    private void subscribe(JSONObject payload){

    }

    public JSONObject createSubcribePayload(String lastname, String firstname, String id, String mail){
        JSONObject jsonPayload = new JSONObject();
        return jsonPayload;
    }
}
