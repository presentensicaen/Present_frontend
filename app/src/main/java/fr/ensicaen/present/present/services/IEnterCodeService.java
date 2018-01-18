package fr.ensicaen.present.present.services;


import org.json.JSONObject;

import fr.ensicaen.present.present.models.ApiResponseModel;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import io.reactivex.Observable;

/**
 * Created by Jeanne on 17/01/2018.
 */

public interface IEnterCodeService {
    @Headers("Content-Type: application/json")
    @POST("enterCode/")
    /*on s'attend a observer une reponse de l'api - status + data avec data = string*/
    Observable<ApiResponseModel<String>>checkCode(@Body JSONObject jsonString);
}
