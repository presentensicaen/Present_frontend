package fr.ensicaen.present.present.models;

import com.google.gson.annotations.SerializedName;


/**
 * Created by pierr on 31/12/2017.
 */

public class CallModel {

    @SerializedName("code")
    private String _code;

    public CallModel(String code) {
        this._code = code;
    }

    @Override
    public String toString() {
        return "CallModel{" +
                "_code='" + _code + '\'' +
                '}';
    }

    public String getCode() {
        return _code;
    }


}
