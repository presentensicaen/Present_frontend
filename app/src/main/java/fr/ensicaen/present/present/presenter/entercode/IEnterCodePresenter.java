package fr.ensicaen.present.present.presenter.entercode;

import org.json.JSONObject;

/**
 * Created by Jeanne on 14/12/2017.
 */

public interface IEnterCodePresenter {
    void onEnterCodeButtonClick(String id, String code);

    void verifyCode(JSONObject payload);
}
