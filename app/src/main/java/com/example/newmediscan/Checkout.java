package com.example.newmediscan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Checkout extends AppCompatActivity {

    private TextView chk_medname;
    private TextView chk_quantity;
    private TextView chk_price;
    private TextView chk_totalPrice;
    private TextView chk_desc;
    private Button btn_checkout;
//    private TextView autogen;
    private RadioButton rb_gcash;
    private RadioButton rb_cashier;
    private int counter = 0;
    private String text;
    private TextView datetime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        chk_medname = findViewById(R.id.chk_medname);
        chk_quantity = findViewById(R.id.chk_quantity);
        chk_price = findViewById(R.id.chk_price);
        chk_totalPrice = findViewById(R.id.chk_totalPrice);
//      autogen = findViewById(R.id.autogen);
        btn_checkout = findViewById(R.id.btn_checkout);
        rb_cashier = findViewById(R.id.rb_cashier);
        rb_gcash = findViewById(R.id.rb_gcash);
        chk_desc = findViewById(R.id.chk_desc);
        datetime = findViewById(R.id.datetime);


        String name_med = getIntent().getStringExtra("keyname");
        String price_med = getIntent().getStringExtra("keyprice");
        String desc_med = getIntent().getStringExtra("keydesc");


        chk_medname.setText(name_med);
        chk_price.setText(price_med);
        chk_totalPrice.setText(price_med);
        chk_desc.setText(desc_med);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z");
        String currentDateandTime = sdf.format(new Date());

        datetime.setText(currentDateandTime);

        final DB helper = new DB(this);


//        Random random = new Random();
//        int val = random.nextInt(10000);
//        autogen.setText(Integer.toString(val));


//        chk_button_up.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                counter ++;
//                chk_pieces.setText(Integer.toString(counter));
//
////                calculateTotalPrice();
//
////                String string_price = chk_price.getText().toString();
////                int int_price = Integer.parseInt(string_price);
//
//
//            }
//        });



//        chk_button_down.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               counter --;
//                chk_pieces.setText(Integer.toString(counter));
//            }
//        });

        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rb_gcash.isChecked()) {


//                    Passing Gcash Package Name Here.
//                    Intent intent;
//                    intent = getPackageManager().getLaunchIntentForPackage(com.globe.gcash.android);
//                    startActivity(intent);

                    String med_name = chk_medname.getText().toString();
                    String total_price = chk_totalPrice.getText().toString();

                    Intent intent = new Intent(Checkout.this, QRScanner.class);
                    intent.putExtra("Item name", med_name );
                    intent.putExtra("total_price", total_price);
                    startActivity(intent);



                }else if (rb_cashier.isChecked()){
//                    openCashier();

                    Intent intent = new Intent(Checkout.this,Receipt.class);

//                    String trans_no = autogen.getText().toString();
                    String med_name = chk_medname.getText().toString();
                    String total_price = chk_totalPrice.getText().toString();
                    String price = chk_price.getText().toString();


                    intent.putExtra("item_name", med_name );
                    intent.putExtra("price", price);
                    intent.putExtra("total_price", total_price);
//                    intent.putExtra("trans_no",trans_no);
//
//                    if (!chk_medname.toString().isEmpty() && !chk_totalPrice.getText().toString().isEmpty()) {
//                        if (helper.insert(chk_medname.getText().toString(), chk_totalPrice.getText().toString())) {
//                            Toast.makeText(Checkout.this, "Inserted", Toast.LENGTH_LONG).show();
//                        } else {
//                            Toast.makeText(Checkout.this, "NOT Inserted", Toast.LENGTH_LONG).show();
//                        }
//                    } else {
//                        chk_medname.setError("Enter NAME");
//                        chk_totalPrice.setError("Enter Salary");
//                    }

                    startActivity(intent);
                }







            }
        });

    }

    protected void openGcash(){
        Intent intent = new Intent(this, Gcash.class);
        startActivity(intent);
    }

    protected void openCashier(){
        Intent intent = new Intent(this, Receipt.class);
        startActivity(intent);
    }

//    private void calculateTotalPrice(){
//
//            double totalPrice = 0.0;
//        int int_price = Integer.parseInt(chk_price.getText().toString());
//        int TotalPrice = int_price * counter;
//        chk_totalPrice.setText(TotalPrice);
//
//
//        for(int i=0; i<counter; i++) {
//            chk_price += counter.get(i).getPrice();
//        }
//
//        return totalPrice;
//
//    }



}