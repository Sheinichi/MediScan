package com.example.newmediscan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

public class Receipt extends AppCompatActivity {

    private Button c_done;
    private Button c_generate;
    private ImageView qr;
    private TextView c_itemName;
    private TextView c_quantity;
    private TextView c_price;
    private TextView c_totalPrice;
    ArrayList<String> listItem;

    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        c_totalPrice = findViewById(R.id.c_totalPrice);
        c_itemName = findViewById(R.id.c_itemName);
        c_quantity = findViewById(R.id.c_quantity);
        c_price = findViewById(R.id.c_price);
        c_done = findViewById(R.id.c_done);
        c_generate = findViewById(R.id.c_generate);
        qr = findViewById(R.id.qr);

        String itemName = getIntent().getStringExtra("item_name");
        String price = getIntent().getStringExtra("price");
        String totalPrice = getIntent().getStringExtra("total_price");
        String quantity = getIntent().getStringExtra("quantity");

        c_itemName.setText(itemName);
        c_price.setText(price);
        c_quantity.setText("1");
        c_totalPrice.setText(totalPrice);

        c_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String store = "MediScan";

                //Get input value from texts
//                String price = c_price.getText().toString().trim();

                //Initialize Multi format writer
                MultiFormatWriter writer = new MultiFormatWriter();
                //Initialize bit matrix
                try {
                    BitMatrix matrix3 = writer.encode(store, BarcodeFormat.QR_CODE, 500,500);
                    //Initialize Barcode encoder
                    BarcodeEncoder encoder = new BarcodeEncoder();
                    Bitmap bitmap3 = encoder.createBitmap(matrix3);

                    qr.setImageBitmap(bitmap3);

                    InputMethodManager manager = (InputMethodManager) getSystemService(
                            Context.INPUT_METHOD_SERVICE
                    );
                    manager.hideSoftInputFromWindow(c_price.getApplicationWindowToken(),0);



                } catch (WriterException e) {
                    e.printStackTrace();
                }



            }
        });

        c_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Receipt.this, ScanCheck.class);
                startActivity(intent);
//                String medname = c_itemName.getText().toString();
//                String quantity = c_quantity.getText().toString();
//                String total_price = c_totalPrice.getText().toString();
//
//                listItem.clear();
//                db.viewData();=
//                db.insert(medname, quantity,total_price);

                Toast.makeText(Receipt.this, "Paid successfully", Toast.LENGTH_SHORT).show();
            }
        });


    }
}