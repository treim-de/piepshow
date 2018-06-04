package de.treim.piepshow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import uk.co.deanwild.flowtextview.FlowTextView;

public class WebcamFragment extends Fragment {
    View content;
    LayoutInflater inflater;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater=inflater;
        content=inflater.inflate(R.layout.fragment_webcam,container,false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Livestream");
        return content;
    }
}


