package com.example.qr;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    public Button scanBtn;
    public TextView formatTxt, contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        scanBtn = (Button)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Получаем разрешение на камеру для Android 6+
                int permissionStatus = ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CAMERA);
                final int MY_CAMERA_REQUEST_CODE = 100;
                if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[] {android.Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
                }

                else {
                    IntentIntegrator scanIntegrator = new IntentIntegrator(MainActivity.this);
                    scanIntegrator.setOrientationLocked(false);
                    // Процедура для сканирования
                    scanIntegrator.initiateScan();
                }
            }

            // Обработка результата сканирования
            public void onActivityResult(int requestCode, int resultCode, Intent intent) {

                IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
                // Не пустой результат
                if (scanningResult != null) {
                    // Результат сканирования в виде строки
                    String scanContent = scanningResult.getContents();
                    // Вид barcode
                    String scanFormat = scanningResult.getFormatName();

                    // Запись в textView результата
                    formatTxt.setText("FORMAT: " + scanFormat);
                    contentTxt.setText("CONTENT: " + scanContent);
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Данные не получены!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

        });
    }
}