package de.treim.piepshow;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
        this.inflater=inflater;
        content=inflater.inflate(R.layout.fragment_emergency,container,false);
        content.findViewById(R.id.emergency_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send = new Intent(Intent.ACTION_SENDTO);
                String uriText = "mailto:" + Uri.encode("reim.tobias@gmail.com") + "?subject="
                        + Uri.encode("Verletzter Vogel gefunden") + "&body="
                        + Uri.encode("Guten Tag,\r\n\r\nIch habe an folgenden Koordinaten einen Verletzten Vogel gefunden:\r\n");
                Uri uri = Uri.parse(uriText);
                send.setData(uri);
                startActivity(Intent.createChooser(send, "Send mail..."));
            }
        });
        return content;
    }
}


