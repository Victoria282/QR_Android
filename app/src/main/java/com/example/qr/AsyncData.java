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

    public AsyncData(Context context, TextView text, ImageView ivAvatar, String code) {
        this.context = context;
        this.tvName = text;
        this.code = code;
        this.ivAvatar = ivAvatar;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            String link = "https://1984year.000webhostapp.com/android.php";
            String data = URLEncoder.encode("code", "UTF-8") + "=" + URLEncoder.encode(code, "UTF-8");
            URL url = new URL(link);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(data);
            wr.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder result = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                result.append(line);
                break;
            }
            return  result.toString();

        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
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