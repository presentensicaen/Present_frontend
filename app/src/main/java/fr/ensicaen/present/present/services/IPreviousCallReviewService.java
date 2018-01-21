package fr.ensicaen.present.present.services;


import org.json.JSONObject;

import java.util.ArrayList;

import fr.ensicaen.present.present.models.ApiResponseModel;
import fr.ensicaen.present.present.models.PreviousCallModel;
import fr.ensicaen.present.present.models.PreviousCallUserModel;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


/**
 * Created by jueast on 30/12/17.
 */

public interface IPreviousCallReviewService {

    @Headers("Content-Type: application/json")
    @POST("/previous-calls")
    Observable<ApiResponseModel<ArrayList<PreviousCallModel>>> getPreviousCalls(@Body JSONObject jsonString);

    @Headers("Content-Type: application/json")
    @POST("/get-users-that-responded")
    Observable<ApiResponseModel<ArrayList<PreviousCallUserModel>>> getConcernedUsers(@Body JSONObject jsonString);
}
