package fr.ensicaen.present.present.utils;

import android.content.Context;
import android.content.res.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import fr.ensicaen.present.present.R;

/**
 * Created by jueast on 01/01/18.
 */

public class Config {
    private Properties _props = null;

    public Config(Context c) throws IOException {
        loadProperties(c);
    }

    private void loadProperties(Context context) throws IOException {
        Resources resources = context.getResources();
        if (_props == null) {
            InputStream rawResource = resources.openRawResource(R.raw.config);
            _props = new Properties();
            _props.load(rawResource);
        }

    }

    public String property(String name) {
        if (_props != null) {
            return _props.getProperty(name);
        }
        return null;
    }
}