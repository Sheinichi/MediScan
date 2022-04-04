package com.example.newmediscan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transaction extends AppCompatActivity {

    DB db;
    private TextView DateTime;
    ArrayList<String>  ListItem;
    ArrayAdapter adapter;
    ListView userlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        DateTime = findViewById(R.id.DateTime);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        String currentDateandTime = sdf.format(new Date());

        DateTime.setText(currentDateandTime);

        ListItem = new ArrayList<>();

        viewData();

        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = userlist.getItemAtPosition(1).toString();
                Toast.makeText(Transaction.this,"" +text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void viewData() {
        Cursor cursor = db.viewData();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data to show", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                ListItem.add(cursor.getString(1));
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ListItem);
            userlist.setAdapter(adapter);
        }

    }

}





//    private RecyclerView rView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.Adapter LayoutManager;
//    List<DB> TransactionList = new ArrayList();
//
//    private
//    Button btn_home;
//    String trn_id;
//    String itemName;
//    int itemCount;
//    int totalPrice;
//    int date;
//
////
////    public Transaction(ArrayList<Transaction> transactionHistory){
////        this.transactionHistory = transactionHistory;
////    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_transaction);
//
//
//
//        btn_home = findViewById(R.id.btn_home);
//
//        btn_home.setOnClickListener(new View.OnClickListener(){
//                                        @Override
//                                        public void onClick(View v) {
//                                            Intent intent = new Intent(getApplicationContext(), ScanCheck.class);
//                                            startActivity(intent);
//                                            finish();
//                                        }
//                                    }
//        );
//    }