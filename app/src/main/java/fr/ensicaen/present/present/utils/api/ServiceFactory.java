package fr.ensicaen.present.present.utils.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jueast on 30/12/17.
 */

public class ServiceFactory {

    private ServiceFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> T createRetrofitService(final Class<T> rfclass, final String endpoint) {
        final Retrofit adapter = new Retrofit.Builder()
                .baseUrl(endpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return adapter.create(rfclass);
    }
}
