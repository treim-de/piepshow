package de.treim.piepshow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class AddEntryActivity extends AppCompatActivity {
    private String path="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Eintrag hinzufügen");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.send, menu);
        return true;
    }

    public void onCameraClick(View view){
        //do nuffin
    }
    public void onSelectClick(View view){
        //still nuffin
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Send
        if(path.equals("")){
            Toast.makeText(this,"Kein Bild ausgewählt",Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
