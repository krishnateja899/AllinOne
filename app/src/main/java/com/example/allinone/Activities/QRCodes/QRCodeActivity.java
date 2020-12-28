package com.example.allinone.Activities.QRCodes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.allinone.R;

public class QRCodeActivity extends AppCompatActivity {
    private Button qr_code1, qr_code2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r_codes);
        qr_code1 = findViewById(R.id.qr_code1);
        qr_code2 = findViewById(R.id.qr_code2);

        qr_code1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //https://github.com/yuriy-budiyev/code-scanner
                Intent i = new Intent(QRCodeActivity.this, QRCode1.class);
                startActivity(i);
            }
        });

        qr_code2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //https://github.com/dm77/barcodescanner
                Intent i = new Intent(QRCodeActivity.this, QRCode2.class);
                startActivity(i);
            }
        });
    }
}