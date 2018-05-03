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

public class NewsFragment extends Fragment {
    View content;
    LayoutInflater inflater;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater=inflater;
        content=inflater.inflate(R.layout.fragment_bird,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Neuigkeiten");
        View entry=inflater.inflate(R.layout.bird_entry,null);
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(15,15,15,15);
        FlowTextView flowTextView = entry.findViewById(R.id.bird_entry_text);
        Spanned html = Html.fromHtml("<html><b>Überschrift</b><br>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.");
        flowTextView.setText(html);
        View entry2=inflater.inflate(R.layout.bird_entry,null);
        FlowTextView flowTextView2 = entry2.findViewById(R.id.bird_entry_text);
        flowTextView2.setText(html);
        entry.setLayoutParams(lp);
        entry2.setLayoutParams(lp);
        ((LinearLayout)content.findViewById(R.id.bird_scroll)).addView(entry);
        ((LinearLayout)content.findViewById(R.id.bird_scroll)).addView(entry2);
        return content;
    }
}


