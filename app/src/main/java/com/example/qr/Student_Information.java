package com.example.qr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Student_Information extends AppCompatActivity {

    TextView Surname, Name, LastName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__information);


        Surname = findViewById(R.id.StudentSurname);
        Name = findViewById(R.id.StudentName);
        LastName = findViewById(R.id.StudentLastName);


        ((AppCompatActivity) Student_Information.this).getSupportActionBar().setTitle("EAN-13:" + "0000000000017");
        String data = getIntent().getStringExtra("Data");
        //System.out.println("VIKA" + data);
        String [] Array = data.split(" ");

        Surname.setText(Array[0]);
        Name.setText(Array[1]);
        LastName.setText(Array[2]);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.back) {
            startActivity(new Intent(Student_Information.this, Student_Control.class));
            return true;
        }
        return false;
    }
}