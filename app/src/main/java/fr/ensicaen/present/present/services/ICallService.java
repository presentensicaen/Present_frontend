package fr.ensicaen.present.present.services;


import org.json.JSONObject;

import fr.ensicaen.present.present.models.ApiResponseModel;
import fr.ensicaen.present.present.models.CallModel;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


/**
 * Created by pierr on 17/01/18.
 */

public interface ICallService {

    @Headers("Content-Type: application/json")
    @POST("generate-code/")
    Observable<ApiResponseModel<CallModel>> createCall(@Body JSONObject jsonString);
}
