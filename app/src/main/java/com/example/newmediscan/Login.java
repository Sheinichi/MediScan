package com.example.newmediscan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {



    private EditText username1,password1;
    private Button login1;
    private TextView registerHere;
    DB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerHere = findViewById(R.id.registerHere);
        username1 = findViewById(R.id.username1);
        password1 = findViewById(R.id.password1);
        login1 = findViewById(R.id.login1);
        db = new DB (this);

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username1.getText().toString();
                String pass = password1.getText().toString();

                if(user.equals("") || pass.equals(""))
                    Toast.makeText(Login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = db.checkusernamepassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(Login.this, "Login succesfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ScanCheck.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }

                }
            }


        });


        registerHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

    }
}