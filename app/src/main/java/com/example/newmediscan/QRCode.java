package com.example.newmediscan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRCode extends AppCompatActivity {

    EditText qrvalue;
    Button generatebtn;
    Button scanbtn;
    ImageView qrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        qrvalue = findViewById(R.id.qrvalue);
        generatebtn = findViewById(R.id.generatebtn);
        scanbtn = findViewById(R.id.scanbtn);
        qrImage = findViewById(R.id.qrImage);
        generatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String data  = qrvalue.getText().toString();
//                MultiFormatWriter writer = new MultiFormatWriter();
//                if(data.isEmpty()){
//                    qrvalue.setError("Value required");
//                }else{
                            try {
                                String value  = qrvalue.getText().toString();
                                MultiFormatWriter writer = new MultiFormatWriter();
                                BitMatrix matrix3 = writer.encode(value, BarcodeFormat.QR_CODE, 350,350);
                                //Initialize Barcode encoder
                                BarcodeEncoder encoder = new BarcodeEncoder();
                                Bitmap bitmap3 = encoder.createBitmap(matrix3);

                                qrImage.setImageBitmap(bitmap3);

                                InputMethodManager manager = (InputMethodManager) getSystemService(
                                        Context.INPUT_METHOD_SERVICE
                                );
                                manager.hideSoftInputFromWindow(qrvalue.getApplicationWindowToken(),0);

                            } catch (WriterException e) {
                                e.printStackTrace();
                            }



                        }
                    });
        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),QRScanner.class));
            }
        });
                }
            }
