package com.example.vipul.jsonexmaple2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    JSONObject j1=null;
    ArrayList titleList = new ArrayList();
    ListView listView;
    ArrayAdapter arrayAdapter;
    String url = "http://androindian.com/apps/blog_links/api.php";
    String titleUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);
        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, titleList);
        JSONObject jsonObject =new JSONObject();
        try {
            jsonObject.put("action","get_all_links");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonList jsonList = new JsonList();
        jsonList.execute(jsonObject.toString());
    }

    private class JsonList extends AsyncTask<String,String,String>{

        ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Please Wait");
            progressDialog.setMessage("Content Loading");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected String doInBackground(String... params) {
            j1=JsonFunction.getJsonFromUrlParam(url,params[0]);
            Log.i("Result",j1.toString());
            return j1.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();

            try {
                JSONObject j2 = new JSONObject(j1.toString());
                JSONArray jsonArray = j2.getJSONArray("data");
                Log.i("Ja", jsonArray.toString());

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject j3 = jsonArray.getJSONObject(i);

                    String Title = j3.getString("title");
                    Log.i("title", Title);
                    titleList.add(Title);

                    titleUrl = j3.getString("url");
                }
                listView.setAdapter(arrayAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MainActivity.this, Discription.class);
                        intent.putExtra("uRl", titleUrl);
                        startActivity(intent);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
