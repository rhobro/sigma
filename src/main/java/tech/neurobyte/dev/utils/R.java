package tech.neurobyte.dev.utils;

import java.io.InputStream;
import java.net.URL;

public class R {
    public static URL getResource(String path) {
        return R.class.getClassLoader().getResource(path);
    }

    public static InputStream getResourceAsStream(String path) {
        return R.class.getClassLoader().getResourceAsStream(path);
    }
}
