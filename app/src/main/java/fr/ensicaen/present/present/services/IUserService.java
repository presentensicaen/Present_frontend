package fr.ensicaen.present.present.services;


import org.json.JSONObject;

import fr.ensicaen.present.present.models.ApiResponseModel;
import fr.ensicaen.present.present.models.UserModel;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


/**
 * Created by jueast on 30/12/17.
 */

public interface IUserService {

    @Headers("Content-Type: application/json")
    @POST("connect/")
    Observable<ApiResponseModel<UserModel.UserObjectHolder>> loginUser(@Body JSONObject jsonString);
}
