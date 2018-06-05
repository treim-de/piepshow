package de.treim.piepshow;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

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

    public static byte[] encodeBmp(Bitmap bmp) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 45, byteArrayOS);
        return byteArrayOS.toByteArray();
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

    public static boolean hasLocationPermission(Context context){
        return (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);
    }
    public static Location getLastKnownLocation(Context context) {
        LocationManager mLocationManager = (LocationManager)context.getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }
}
