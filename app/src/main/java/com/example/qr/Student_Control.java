package com.example.qr;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

public class Student_Control extends AppCompatActivity {

    TextView tvName;
    Button Inform_button;
    ImageView ivAvatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__control);

        Bundle arguments = getIntent().getExtras();
        String userCode = arguments.get("code").toString();

        ((AppCompatActivity) Student_Control.this).getSupportActionBar().setTitle("EAN-13:" + userCode);

        Inform_button = findViewById(R.id.Inform_button);
        Inform_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Student_Control.this, Student_Information.class));
            }
        });

        tvName = (TextView)findViewById(R.id.StudentName);
        ivAvatar = (ImageView)findViewById(R.id.ProfilePic);
        new AsyncData(this, tvName, ivAvatar, userCode).execute();
    }
}