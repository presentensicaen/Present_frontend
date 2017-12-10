package fr.ensicaen.present.present.tests2delete;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IRetroFitExample {
    @GET("users/{user}/")
    Call<ArrayList<HobbiesExample>> getHobbies(@Path("user") String user);
}

