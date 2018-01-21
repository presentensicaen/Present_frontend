package fr.ensicaen.present.present.utils.imagetools;
/* 
 *
 * ENSICAEN
 * 6 Boulevard Marechal Juin
 * F-14050 Caen Cedex
 *
 * This file is owned by The Présent ! Team and created by
 * Quentin DEBROISE, Julian EASTERLY, Jeanne LEYMARIE, Pierre NICOL, and Coline SMAGGHE.
 * No portion of this document may be reproduced, copied
 * or revised without written permission of the authors.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.annotations.Nullable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
 * @author The Présent ! Team <easterly@ecole.ensicaen.fr>
 * @version 0.0.1 - 21/01/18
 */
public class HTTPImageLoader {

    public HTTPImageLoader(){}

    public Observable<Bitmap> getImageObservable(String url){
        return Observable.defer(() -> {
           try{
               return Observable.just(getImage(url));
           }catch(IOException e){
               return null;
           }
        });
    }

    @Nullable
    private Bitmap getImage(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Bitmap image = null;
        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            try {
                image = BitmapFactory.decodeStream(response.body().byteStream());
            } catch (Exception e) {
               return null;
            }
        }
        return image;
    }
}
