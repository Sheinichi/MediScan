package com.example.newmediscan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import java.text.BreakIterator;

public class QRScanner extends AppCompatActivity {

    CodeScanner codeScanner;
    CodeScannerView scannView;
//    TextView resultData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);

        scannView = findViewById(R.id.scannerView);
        codeScanner = new CodeScanner(this, scannView);
//        resultData = findViewById(R.id.resultData);
//

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        resultData.setText(result.getText());
                        String url = "https://play.google.com/store/apps/details?id=com.globe.gcash.android&hl=en&gl=US";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);

                    }
                });
            }
        });
    }


    @Override
    protected void onResume(){
        super.onResume();
        codeScanner.startPreview();
        //Toast.makeText(QRScanner.this, "Scanned successfully", Toast.LENGTH_SHORT).show();

    }
}