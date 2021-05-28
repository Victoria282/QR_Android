package com.example.qr;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class AsyncQuery extends AsyncTask<String, Void, String> {

    private final String API_LOGIN = "android";
    private final String API_PASS = "KU986iu1";
    private Context context;

    public AsyncQuery(Context context)
    {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            String studentCode = strings[0];
            String apiLink = "https://team1984.nlobashov.ru/api2.php";
            String params = String.format("login=%s&pass=%s&code=%s", API_LOGIN, API_PASS, studentCode);

            URL url = new URL(apiLink);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(params);
            wr.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder result = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                result.append(line);
                break;
            }
            reader.close();
            return  result.toString();

        } catch (Exception e) {
            return new String("Сервер не отвечает");
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s.equals("complete"))
        {
            Toast.makeText(context, "Потверждено" , Toast.LENGTH_SHORT).show();
        }
    }
}
