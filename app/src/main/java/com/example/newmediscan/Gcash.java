package com.example.newmediscan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Gcash extends AppCompatActivity {

    private TextView c_custName;
    private TextView c_price;
    private TextView c_transNo;
    private Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gcash);

        c_price = findViewById(R.id.c_price);
        c_transNo = findViewById(R.id.c_transNo);
        c_custName = findViewById(R.id.c_custName);
        done  = findViewById(R.id.done);

        String custName = getIntent().getStringExtra("Customer Name");
        String totalPrice = getIntent().getStringExtra("total_price");
        String transNo = getIntent().getStringExtra("transNo");

        c_custName.setText(custName);
        c_price.setText(totalPrice);
        c_transNo.setText(transNo);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Gcash.this, ScanCheck.class);
                startActivity(intent);
            }
        });


        Random random = new Random();
        int val = random.nextInt(100000);
        c_transNo.setText(Integer.toString(val));


//
//        String user = c_custName.getText().toString();
//
//        if (DB.checkusername(c_custName.getText().toString().trim() {
//            Intent accountsIntent = new Intent(activity, Gcash.class);
//            accountsIntent.putExtra("NAME", DB.checkusername(c_custName.getText().toString().trim()));
//            Intent idIntent = new Intent(activity, UsersActivity.class);
//            String ID = String.valueOf(databaseHelper.getUserId(textInputEditTextEmail.getText().toString().trim()));
//            idIntent.putExtra("ID", ID);
//            emptyInputEditText();
//            startActivity(accountsIntent);
//
//    }
//        if (DB.checkIfUserExit(DB.TABLE_USER , textInputEditTextEmail.getText().toString().trim())){
//            Intent accountsIntent = new Intent(activity, UsersActivity.class);
//            accountsIntent.putExtra("NAME",
//                    databaseHelper.GetUserUserName(databaseHelper.TABLE_USER ,textInputEditTextEmail.getText().toString().trim()));
//            String ID = String.valueOf(databaseHelper.GetUserID(databaseHelper.TABLE_USER ,textInputEditTextEmail.getText().toString().trim()));
//            accountsIntent.putExtra("ID", ID);
//            emptyInputEditText();
//            startActivity(accountsIntent);
//        } else {
//            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
//        }
    }
}
