package com.example.qr;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class InformationActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);


        ListView listView = findViewById(R.id.listView);

// определяем строковый массив
        final String[] Developers = new String[] { "Буренкова Анастасия \n(Frontend-deb/web-designer)",
                "Вилкова Виктория \n(Android-Developer)", "Зуева Дарья \n (Вёрстка)", "Зубов Сергей \n(QA Engineer)",
                "Лобашов Николай \n(Android-Developer)", "Михайленко Дмитрий \n(Project Manager/Backend-dev/Team-lead)",
                "Петрайтис Инна \n(Android UI/UX-designer)", "Сироткин Тимофей \n(Backend-dev)",
                "Харченко Анастасия \n(Frontend-deb/web-designer)"
        };

        final String[] position = new String[] { "Frontend-deb/web-designer ", "Android-Developer ",
                "Верстка", "QA Engineer ", "Android-Developer", "Project Manager/Backend-dev/Team-lead",
                "Android UI/UX-designer ", "Backend-dev ", "Frontend-deb/web-designer "};

// используем адаптер данных
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1, Developers);

        listView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.back) {
            startActivity(new Intent(InformationActivity.this, MainActivity.class));
            return true;
        }
        return false;
    }
}
