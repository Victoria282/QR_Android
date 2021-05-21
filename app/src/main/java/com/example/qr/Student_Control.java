package com.example.qr;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

        ((AppCompatActivity) Student_Control.this).getSupportActionBar().setTitle("EAN-13:" + "0000000000017");

        Inform_button = findViewById(R.id.Inform_button);
        Inform_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Student_Control.this, Student_Information.class));
            }
        });
    }
}