package de.treim.piepshow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import uk.co.deanwild.flowtextview.FlowTextView;

public class BirdFragment extends Fragment {
    View content;
    LayoutInflater inflater;
    SharedPreferences prefs;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        content = inflater.inflate(R.layout.fragment_bird, container, false);
        prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Unsere Vögel");
        final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 350);
        lp.setMargins(15, 15, 15, 15);
        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONArray response;
                try {
                    response = new JSONArray(M.getRequest("http://treim.de:3000/birds"));
                    for (int i = response.length()-1; i >= 0; i--) {
                        System.out.println("Setting bird " + i);
                        final JSONObject current = response.getJSONObject(i);
                        if (current.getString("description") == null|| current.getString("description").equals("null")) continue;
                        final View entry = inflater.inflate(R.layout.bird_entry, null);
                        FlowTextView flowTextView = entry.findViewById(R.id.bird_entry_text);
                        String source = "<html><b>";
                        source += current.getString("name") + "</b><br>";
                        source += current.getString("description") + "</html>";
                        Spanned html = Html.fromHtml(source);
                        flowTextView.setText(html);
                        Bitmap image=M.decodeBmp(current.getJSONObject("image").getJSONArray("data"));
                        ((ImageView)entry.findViewById(R.id.entry_image)).setImageBitmap(image);
                        entry.setLayoutParams(lp);
                        entry.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getContext(), DetailsActivity.class);
                                try {
                                    SharedPreferences.Editor ed=prefs.edit();
                                    ed.putString("jsonobject",current.toString());
                                    ed.apply();
                                    intent.putExtra("id", current.getInt("id"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                startActivity(intent);
                            }
                        });
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((LinearLayout) content.findViewById(R.id.bird_scroll)).addView(entry);
                            }
                        });
                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        }).start();


        return content;
    }
}


