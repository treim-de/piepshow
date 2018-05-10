package de.treim.piepshow;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class AddEntryActivity extends AppCompatActivity {
    private JSONArray byteimage;
    private String kind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        kind = getIntent().getExtras().getString("kind");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (kind.equals("birds")) getSupportActionBar().setTitle("Vogel hinzufügen");
        else
            getSupportActionBar().setTitle("Eintrag hinzufügen");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.send, menu);
        return true;
    }

    public void onCameraClick(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 427);
        }
    }

    public void onSelectClick(View view) {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 428);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 427 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            byteimage = M.encodeBmp(imageBitmap);
        } else if (requestCode == 428 && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            try {
                // Do whatever you want with this bitmap (image)
                Bitmap bitmapImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                byteimage = M.encodeBmp(bitmapImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String tBody="",tContentType="";
        //Send
        if (kind.equals("news")) {
            tContentType="application/json";
            try {
                JSONObject post=new JSONObject();
                post.put("title", ((EditText) findViewById(R.id.add_title_name)).getText().toString());
                post.put("content", ((EditText) findViewById(R.id.add_description_content)).getText().toString());
                post.put("author", "Dummy");
                tBody=post.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (kind.equals("birds")) {
            String twoHyphens = "--";
            String boundary = "*****" + Long.toString(System.currentTimeMillis()) + "*****";
            String lineEnd = "\r\n";
            tContentType="multipart/form-data; boundary=" + boundary;
            if (byteimage != null) {
                try {
                    String pBody="";
                    pBody+=twoHyphens+boundary+lineEnd;
                    pBody+="Content-Disposition: form-data; name=\"name\"" + lineEnd+lineEnd;
                    pBody+=((EditText) findViewById(R.id.add_title_name)).getText().toString()+lineEnd;
                    post.put("species", "Dummy");
                    post.put("description", ((EditText) findViewById(R.id.add_description_content)).getText().toString());
                    post.put("image", byteimage);
                    body=pBody;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        final String body=tBody;
        final String contenttype=tContentType;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(body);
                    HttpURLConnection con = (HttpURLConnection) new URL("http://treim.de:3000/" + kind).openConnection();
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type", "application/json");
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    OutputStream os=con.getOutputStream();
                    os.write(body.getBytes("UTF-8"));
                    System.out.println(con.getResponseCode());
                    con.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        return true;
    }
}
