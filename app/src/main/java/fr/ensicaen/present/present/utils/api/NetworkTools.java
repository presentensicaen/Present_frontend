package fr.ensicaen.present.present.utils.api;

import android.content.Context;
import android.net.ConnectivityManager;

import java.io.IOException;

/**
 * Created by jueast on 31/12/17.
 */

public class NetworkTools {
    private final static String A_STABLE_WEBSITE = "www.google.com";

    public static boolean isNetworkAvailable(Context c) {
        final ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public static boolean isOnline(Context c) throws IOException, InterruptedException {
        return isNetworkAvailable(c) && isReachableByPing(A_STABLE_WEBSITE);
    }

    public static boolean isReachableByPing(String site) throws IOException, InterruptedException {
        String command = "ping -c 1 " + site;
        return (Runtime.getRuntime().exec(command).waitFor() == 0);
    }

    public static void verifyConnection(Context c) throws NoInternetException {
        try {
            if (!isOnline(c)) throw new NoInternetException();
        } catch (InterruptedException | IOException e) {
            throw new NoInternetException();
        }

    }

    public static class NoInternetException extends Throwable {
        /*@TODO write exception ? */
    }
}
