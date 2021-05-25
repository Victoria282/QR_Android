package com.example.qr;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public Button scanBtn;
    public TextView formatTxt, contentTxt, scanResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        scanBtn = (Button)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        scanResult = (TextView)findViewById(R.id.scan_result);

        scanBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        scanCode();
    }

    private void scanCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.initiateScan();

    }
    // Обработка результата сканирования
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        // Не пустой результат
        if (scanningResult != null) {
            if(scanningResult.getContents() != null) {
                // Результат сканирования в виде строки
                String scanContent = scanningResult.getContents();
                // Вид barcode
                String scanFormat = scanningResult.getFormatName();
                // Запись в textView результата
                // formatTxt.setText(scanFormat);
                // contentTxt.setText(scanContent);
                // scanResult.setText("Результат: ");
                startActivity(new Intent(MainActivity.this, Student_Control.class));

            }
            else {
                Toast toast = Toast.makeText(getApplicationContext(), "Данные не получены!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, intent);
        }
    }
    // Создание меню в ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_about) {
            startActivity(new Intent(MainActivity.this, InformationActivity.class));
            return true;
        }
        return false;
    }
}
