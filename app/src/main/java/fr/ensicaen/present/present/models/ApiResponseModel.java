package fr.ensicaen.present.present.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jueast on 30/12/17.
 */

public class ApiResponseModel<T> {

    @SerializedName("status")
    private int _status;
    @SerializedName("data")
    private T _data;

    public ApiResponseModel(int _status, T _data) {
        this._status = _status;
        this._data = _data;
    }

    public int getStatus() {
        return _status;
    }

    public T getData() {
        return _data;
    }
}
