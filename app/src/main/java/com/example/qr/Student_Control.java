package com.example.qr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.UriMatcher;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Student_Control extends AppCompatActivity {

    TextView tvName;
    ImageView ivPhoto;
    Button Inform_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__control);

        Bundle arguments = getIntent().getExtras();
        String studentCode = arguments.get("ean13").toString();
        ((AppCompatActivity) Student_Control.this).getSupportActionBar().setTitle("EAN-13:" + studentCode);

        tvName = (TextView)findViewById(R.id.StudentName);
        ivPhoto = (ImageView)findViewById(R.id.ProfilePic);
        Inform_button = findViewById(R.id.Inform_button);
        Inform_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Student_Control.this, Student_Information.class));
            }
        });
        new AsyncData().execute(studentCode);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.back) {
            startActivity(new Intent(Student_Control.this, MainActivity.class));
            return true;
        }
        return false;
    }

    // Класс для получения данных в фоновом потоке
    class AsyncData extends AsyncTask<String, Void, String> {

        private final String API_LOGIN = "android";
        private final String API_PASS = "KU986iu1";

        @Override
        protected String doInBackground(String... strings) {
            try {
                String studentCode = strings[0];
                String apiLink = "https://team1984.nlobashov.ru/api.php";
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

                while ((line = reader.readLine()) != null)
                {
                    result.append(line);
                    break;
                }
                reader.close();
                return  result.toString();

            } catch (Exception e) {
                return new String("Сервер не отвечает");
            }
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result.equals("false"))
            {
                tvName.setText("Студент не найден");
            }
            else
            {
                tvName.setText(result);
                ivPhoto.setImageDrawable(getDrawable(R.drawable.rango));
            }
        }
    }
}