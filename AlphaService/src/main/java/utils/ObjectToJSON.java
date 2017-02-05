package utils;

import com.google.gson.Gson;

/**
 * Created by Alankrit on 04-Feb-17.
 */
public class ObjectToJSON {
    private static final Gson gson = new Gson();
    public static String convert (Object o) {
        return gson.toJson(o);
    }
}
