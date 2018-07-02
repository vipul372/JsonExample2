package com.example.vipul.jsonexmaple2;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonFunction {
    public static JSONObject getJsonFromUrlParam(String url,String param){

        JSONObject jsonObject = null;

        try {

            URL jsonUrl= new URL(url);
            HttpURLConnection connection=(HttpURLConnection)jsonUrl.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(6000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setUseCaches(false);
            connection.connect();

            OutputStream outputStream = new BufferedOutputStream(connection.getOutputStream());
            outputStream.write(param.getBytes());
            outputStream.flush();

            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer =new StringBuffer();
            String line;
            while ((line=bufferedReader.readLine())!=null)
                stringBuffer.append(line).append("\n");
            inputStream.close();

            Log.i("json",stringBuffer.toString());
            jsonObject = new JSONObject(stringBuffer.toString());
            Log.i("json",""+jsonObject);


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
