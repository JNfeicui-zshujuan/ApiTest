package com.example.l.apitest;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = "mytag";
    String MyApi;
    private ListView listView;
    private String url = "http://gank.io/api/random/data/福利/20";
    private List<String> urls;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lv_listview);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    getApi();
                    display(MyApi);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void display(String response) {
        urls = new ArrayList<>();
        try {
            JSONObject result = new JSONObject(MyApi);
            JSONArray jsonArray = result.getJSONArray("results");
            for (int i = 0; i <jsonArray.length();i++){
                JSONObject temp=jsonArray.getJSONObject(i);
                urls.add(temp.getString("url"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter=new ListViewAdapter(MainActivity.this,urls);
        Log.d(TAG, "display: urls********"+urls.toString());
        listView.setAdapter(adapter);
    }

    //}
    public void getApi() throws IOException {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        final String HTTP = "http://gank.io/api/random/data/Android/20";
        Uri builtUri = Uri.parse(HTTP).buildUpon().build();
        Log.d(TAG, "getApi:builtUri****** " + builtUri);
        URL url = new URL(builtUri.toString());
        Log.d(TAG, "getApi:url***** " + url);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        Log.d(TAG, "getApi: " + urlConnection);
        //輸入流獲取請求內容
        InputStream inputStream = urlConnection.getInputStream();
        Log.d(TAG, "getApi: inputStream********");
        StringBuffer buffer = new StringBuffer();
        if (inputStream == null) {
            Log.d(TAG, "getApi: 第一個if");
            return;

        }
        reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line + "\n");
            Log.d(TAG, "getApi: 第一個while****" + buffer);
        }
        if (buffer.length() == 0) {
            Log.d(TAG, "getApi: 第二個if********");
            return;
        }
        Log.d(TAG, "getApi: 最後的buffer****");
        MyApi = buffer.toString();
        Log.d(TAG, "getApi: MyApi******" + MyApi);
    }
}
