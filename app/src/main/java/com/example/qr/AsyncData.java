package com.example.qr;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;

public class AsyncData extends AsyncTask<String, Void, String> {

    private Context context;
    private TextView tvName;
    private String code;
    private ImageView ivAvatar;

    //Константы для подключения к БД
    private final String DB_LOGIN = "android";
    private final String DB_PASS = "KU986iu1";
    private final String CHARSET = "UTF-8";

    public AsyncData(Context context, TextView text, ImageView ivAvatar, String code) {
        this.context = context;
        this.tvName = text;
        this.code = code;
        this.ivAvatar = ivAvatar;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            String apiLink = "https://1984year.000webhostapp.com/api/android/getdata.php";
            String params = String.format("login=%s&pass=%s&code=%s",
                    URLEncoder.encode(DB_LOGIN, CHARSET),
                    URLEncoder.encode(DB_PASS, CHARSET),
                    URLEncoder.encode(code, CHARSET));

            URL url = new URL(apiLink);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(params);
            wr.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder result = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                result.append(line);
                break;
            }
            reader.close();
            return  result.toString();

        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (result.equals("false"))
        {
            Toast.makeText(context, "Студент не найден", Toast.LENGTH_SHORT).show();
            this.tvName.setText("Неизвестный");
        }
        else
        {

            this.tvName.setText(result);
            this.ivAvatar.setImageDrawable(context.getDrawable(R.drawable.rango));
        }
    }
}