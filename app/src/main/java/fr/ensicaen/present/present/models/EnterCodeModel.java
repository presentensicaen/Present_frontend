package fr.ensicaen.present.present.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jeanne on 17/01/2018.
 */

public class EnterCodeModel {
    @SerializedName("id") private String _id;
    @SerializedName("code") private String _code;

    public EnterCodeModel(String _id, String _code){
        this._id = _id;
        this._code = _code;
    }

    @Override
    public String toString(){
        return "EnterCodeModel{" +
                "_id='" + _id + '\'' +
                "_code='" + _code + '\'' +
                '}';
    }
}
