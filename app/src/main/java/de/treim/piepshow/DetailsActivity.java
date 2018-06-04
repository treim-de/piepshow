package de.treim.piepshow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;


public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Details");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        try {
            JSONObject current=new JSONObject(prefs.getString("jsonobject",""));
            final String name=current.getString("name");
            ((TextView)findViewById(R.id.details_headline)).setText(name);
            ((TextView)findViewById(R.id.detais_species)).setText(current.getString("species"));
            ((TextView)findViewById(R.id.details_description)).setText(current.getString("description"));
            Bitmap image=M.decodeBmp(current.getJSONObject("image").getJSONArray("data"));
            ((ImageView)findViewById(R.id.details_image)).setImageBitmap(image);
            findViewById(R.id.details_donate).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String link="https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=reim.tobias@gmail.com&item_name=Vogelstation&amount=10&item_number="+name+"&currency_code=EUR";
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                    startActivity(browserIntent);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
