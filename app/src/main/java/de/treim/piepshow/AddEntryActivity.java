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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
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
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;


public class AddEntryActivity extends AppCompatActivity {
    private byte[] byteimage;
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
        //Send
        if (kind.equals("news")) {
            try {
                final JSONObject post=new JSONObject();
                post.put("title", ((EditText) findViewById(R.id.add_title_name)).getText().toString());
                post.put("content", ((EditText) findViewById(R.id.add_description_content)).getText().toString());
                post.put("author", "Dummy");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(post.toString());
                            HttpURLConnection con = (HttpURLConnection) new URL("http://treim.de:3000/" + kind).openConnection();
                            con.setRequestMethod("POST");
                            con.setRequestProperty("Content-Type", "application/json");
                            con.setDoInput(true);
                            con.setDoOutput(true);
                            OutputStream os=con.getOutputStream();
                            os.write(post.toString().getBytes("UTF-8"));
                            System.out.println(con.getResponseCode());
                            con.disconnect();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (kind.equals("birds")) {
            final HttpClient httpClient = new DefaultHttpClient();
            final HttpPost post = new HttpPost("http://treim.de:3000/birds");
            final MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
            try {
                reqEntity.addPart("name", new StringBody(((EditText)findViewById(R.id.add_title_name)).getText().toString()));
                reqEntity.addPart("species", new StringBody("dummy"));
                reqEntity.addPart("description", new StringBody(((EditText)findViewById(R.id.add_description_content)).getText().toString()));
                reqEntity.addPart("image", new ByteArrayBody(byteimage,"image/jpeg","image"));
                post.setEntity(reqEntity);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpResponse response = null;
                        try {
                            response = httpClient.execute(post);
                            final HttpEntity resEntity= response.getEntity();
                            String response_str = EntityUtils.toString(resEntity);
                            System.out.println(response_str);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


        }


        return true;
    }
}
