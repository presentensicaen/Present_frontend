package fr.ensicaen.present.present.configureCall;

import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import fr.ensicaen.present.present.R;
import fr.ensicaen.present.present.models.ApiResponseModel;
import fr.ensicaen.present.present.models.UserModel;

/**
 * Created by pierr on 31/12/2017.
 */

public class ConfigurePresenter implements IConfigurePresenter {

    private final IConfigureView _view;


    public void createCall(){


    }

    @Override
    public void onLaunchCallButtonClick(String duration){
        _view.setSuccessMessage();
    }

    public ConfigurePresenter(IConfigureView _view) {
        this._view = _view;
    }

    /*
    private void handleLoginSuccessResponse(ApiResponseModel<UserModel.UserObjectHolder> response){
        _user = response.getData().getUser();
    }

    private void handleLoginErrorResponse(Throwable throwable) {
        _view.hideLoadingAnimation();
        //@TODO handle error
        Toast.makeText(
                _view.getContext(),
                "Erreur "+throwable.getLocalizedMessage(),
                Toast.LENGTH_SHORT
        ).show();
    }*/


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
