package de.treim.piepshow;

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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uk.co.deanwild.flowtextview.FlowTextView;

public class NewsFragment extends Fragment {
    View content;
    SharedPreferences prefs;
    LayoutInflater inflater;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        content = inflater.inflate(R.layout.fragment_bird, container, false);
        prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Neuigkeiten");
        final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(15, 15, 15, 15);
        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONArray response;
                try {
                    response = new JSONArray(M.getRequest("https://fhdw.treim.de/news"));
                    for (int i=0; i < response.length(); i++) {
                        System.out.println("Setting bird " + i);
                        final JSONObject current = response.getJSONObject(i);
                        if (current.getString("content") == null || current.getString("content").equals("null"))
                            continue;
                        final View entry = inflater.inflate(R.layout.bird_entry, null);
                        FlowTextView flowTextView = entry.findViewById(R.id.bird_entry_text);
                        String source = "<html><b>";
                        source += current.getString("title") + "</b><br>";
                        source += current.getString("content") + "</html>";
                        Spanned html = Html.fromHtml(source);
                        flowTextView.setText(html);
                        try {
                            Bitmap image = M.decodeBmp(current.getJSONObject("image").getJSONArray("data"));
                            ((ImageView) entry.findViewById(R.id.entry_image)).setImageBitmap(image);
                        } catch (JSONException e) {
                            entry.findViewById(R.id.entry_image).setVisibility(View.GONE);
                            System.out.println("No image provided");
                        }
                        entry.setLayoutParams(lp);
                        if (getActivity() != null)
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ((LinearLayout) content.findViewById(R.id.bird_scroll)).addView(entry);
                                }
                            });
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            content.findViewById(R.id.bird_loading).setVisibility(View.GONE);
                        }
                    });
                } catch (JSONException e1) {
                    e1.printStackTrace();
                } catch (Exception e) {
                    System.out.println("Something went wrong:");
                    e.printStackTrace();
                }
            }
        }).start();
        return content;
    }
}


