package fr.ensicaen.present.present.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import fr.ensicaen.present.present.R;

/**
 * Created by jueast on 01/01/18.
 */

public class Config {
    private static final String TAG = "CONFIG";
    private static Properties _props = null;

    public static void loadProperties(Context context) throws IOException {
        Resources resources = context.getResources();
        if (_props == null) {
            InputStream rawResource = resources.openRawResource(R.raw.default_config);
            _props = new Properties();
            _props.load(rawResource);
        }

    }

    public static String property(Context context, String name) {
        try {
            loadProperties(context);
            return _props.getProperty(name);
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Unable to find the config file: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "Failed to open config file.");
        }

        return null;
    }

    public static String property(String name) {
        if (_props != null) {
            return _props.getProperty(name);
        }
        return null;
    }
}