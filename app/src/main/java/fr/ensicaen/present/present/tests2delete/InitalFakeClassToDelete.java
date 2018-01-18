package fr.ensicaen.present.present.tests2delete;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jueast on 14/10/17.
 */

public class InitalFakeClassToDelete {

    private int _testInt;
    private String _baseUrl;

    public InitalFakeClassToDelete(int testInt) {
        _testInt = testInt;
    }

    public int getTestInt() {
        return _testInt;
    }

    public int add(InitalFakeClassToDelete a) {
        return _testInt + a.getTestInt();
    }

    public void setBaseUrl(String url) {
        _baseUrl = url;
    }

    public ArrayList<HobbiesExample> getHobbies(String userName) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(_baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IRetroFitExample service = retrofit.create(IRetroFitExample.class);

        Call<ArrayList<HobbiesExample>> hobbies = service.getHobbies(userName);

        try {
            return hobbies.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<HobbiesExample>();
    }
}
