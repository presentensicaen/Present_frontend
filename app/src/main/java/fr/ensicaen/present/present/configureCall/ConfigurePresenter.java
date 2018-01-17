package fr.ensicaen.present.present.configureCall;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pierr on 31/12/2017.
 */

public class ConfigurePresenter implements IConfigurePresenter {

    private final IConfigureView _view;


    public ConfigurePresenter(IConfigureView _view) {
        this._view = _view;
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
