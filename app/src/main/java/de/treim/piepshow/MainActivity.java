package de.treim.piepshow;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs;
    Fragment current;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_bird:
                    setCurrentFragment(new BirdFragment());
                    return true;
                case R.id.navigation_news:
                    setCurrentFragment(new NewsFragment());
                    return true;
                case R.id.navigation_emergency:
                    setCurrentFragment(new EmergencyFragment());
                    return true;
                case R.id.navigation_webcam:
                    setCurrentFragment(new WebcamFragment());
                    return true;
            }
            return false;
        }
    };

    public void setFloatingButton(){
        if((current instanceof BirdFragment || current instanceof NewsFragment) && prefs.getBoolean("login",false)){
            findViewById(R.id.fab).setVisibility(View.VISIBLE);
        }
        else{
            findViewById(R.id.fab).setVisibility(View.GONE);
        }
    }

    public void onAddClick(View view){
        startActivity(new Intent(this,AddEntryActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setCurrentFragment(new NewsFragment());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    public void setCurrentFragment(Fragment f){
        current=f;
        setFloatingButton();
        getSupportFragmentManager().beginTransaction().replace(R.id.content, current).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final View view=getLayoutInflater().inflate(R.layout.dialog,null);
        new AlertDialog.Builder(this).setTitle("Einrichtung")
                .setView(view)
                .setPositiveButton("Anmelden", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(((EditText)view.findViewById(R.id.login_token)).getText().toString().equals("abc123")){
                            SharedPreferences.Editor ed=prefs.edit();
                            ed.putBoolean("login",true);
                            ed.apply();
                        }
                        setFloatingButton();
                    }
                }).setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
        return true;
    }
}
