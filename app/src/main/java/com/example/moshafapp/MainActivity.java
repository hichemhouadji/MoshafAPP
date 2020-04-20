package com.example.moshafapp;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String JSON_LIST_SURAT = "data-surat.json";

    private SuratAdapter suratAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init() {
        List<BaseSurah> list = new ArrayList<BaseSurah>();
        String json = getAssetsJSON(JSON_LIST_SURAT);

        JSONArray array= null;
        try {
            array = new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (array.length() > 0) {
            Gson gson = new Gson();
            int i = 0;
            while (i < array.length()) {
                try {
                    list.add(gson.fromJson(array.getJSONObject(i).toString(), BaseSurah.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                i++;
            }
        } else {
            Toast.makeText(this, "No Objects", Toast.LENGTH_LONG).show();
        }


        listView = findViewById(R.id.listView);
        suratAdapter = new SuratAdapter(MainActivity.this, list);
        listView.setAdapter(suratAdapter);
    }

    public String getAssetsJSON(String fileName) {
        String json = null;
        try {
            InputStream inputStream = this.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }
}
