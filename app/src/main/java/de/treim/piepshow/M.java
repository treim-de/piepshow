package de.treim.piepshow;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tobias on 08.05.18.
 */

public class M {
    public static Bitmap decodeBmp(JSONArray jsonArray) {
        byte[] byteArray = new byte[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                byteArray[i] = (byte) jsonArray.getInt(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    public static JSONArray encodeBmp(Bitmap bmp) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOS);
        byte[] byteArray = byteArrayOS.toByteArray();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < byteArray.length; i++) {
            try {
                jsonArray.put(i, byteArray[i]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray;
    }

    public static String getRequest(String url) {
        HttpURLConnection get;
        try {
            get = (HttpURLConnection) new URL(url).openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(get.getInputStream()));
            String line;
            StringBuilder body = new StringBuilder();
            while ((line = in.readLine()) != null) body.append(line + "\n");
            get.disconnect();
            return body.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
