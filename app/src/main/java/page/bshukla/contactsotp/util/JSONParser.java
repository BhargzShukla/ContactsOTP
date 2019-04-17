package page.bshukla.contactsotp.util;

import android.content.res.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public class JSONParser {
    private String jsonString;
    private BufferedReader bufferedReader = null;

    public JSONParser(Resources resources, int id) {
        InputStream jsonResourceInputStream = resources.openRawResource(id);
        Writer stringWriter = new StringWriter();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(jsonResourceInputStream, StandardCharsets.UTF_8));
            String line = bufferedReader.readLine();
            while (line != null) {
                stringWriter.write(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        jsonString = stringWriter.toString();
    }

    public <T> T constructUsingGson(Type type) {
        Gson gsonObject = new GsonBuilder().create();
        return gsonObject.fromJson(jsonString, type);
    }
}
