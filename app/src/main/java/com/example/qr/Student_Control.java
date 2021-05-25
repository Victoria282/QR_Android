package com.example.qr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toolbar;

public class Student_Control extends AppCompatActivity {

    Button Inform_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__control);

        Bundle arguments = getIntent().getExtras();
        String studentCode = arguments.get("ean13").toString();
        ((AppCompatActivity) Student_Control.this).getSupportActionBar().setTitle("EAN-13:" + studentCode);

        Inform_button = findViewById(R.id.Inform_button);
        Inform_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Student_Control.this, Student_Information.class));
            }
        });
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
}