package de.treim.piepshow;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EmergencyFragment extends Fragment {
    View content;
    LayoutInflater inflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        content = inflater.inflate(R.layout.fragment_emergency, container, false);
        content.findViewById(R.id.emergency_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (M.hasLocationPermission(getContext())) {
                    new android.support.v7.app.AlertDialog.Builder(getContext()).setTitle("Einrichtung").setMessage("Ein Foto des Vogels hilft sehr bei der Einschätzung des Notfalls. Bitte öffnen Sie Ihre Kamera-App und nehmen ein Foto auf, bevor Sie den Button betätigen")
                            .setPositiveButton("Hab ich", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    sendMail();
                                }
                            }).setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
                }else{
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                            7);
                }
            }
        });
        return content;
    }

    public void sendMail(){
        Intent send = new Intent(Intent.ACTION_SENDTO);
        Location location=M.getLastKnownLocation(getContext());
        String uriText = "mailto:" + Uri.encode("reim.tobias@gmail.com") + "?subject="
                + Uri.encode("Verletzter Vogel gefunden") + "&body="
                + Uri.encode("Guten Tag,\r\n\r\nIch habe an folgenden Koordinaten einen verletzten Vogel gefunden:\r\n\r\n" +
                "Latitude: "+location.getLatitude()+"\r\n" +
                "Longitude: "+location.getLongitude()+"\r\n\r\n" +
        "Mit freundlichen Grüßen");
        Uri uri = Uri.parse(uriText);
        send.setData(uri);
        startActivity(Intent.createChooser(send, "Send mail..."));
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        sendMail();
    }
}


