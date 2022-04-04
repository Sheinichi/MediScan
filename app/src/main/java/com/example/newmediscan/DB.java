package com.example.newmediscan;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {

    public static  final String medname  = "MEDNAME";
    public static final String quantity = "QUANTITY";
    public static final String total_price ="TOTAL_PRICE";
    public static final String DBNAME = "Login.db";

    public DB(Context context) {
        super(context, "Login.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        MyDB.execSQL("Create Table users(username TEXT primary key, password TEXT)");
        MyDB.execSQL("Create Table inventory(medname TEXT primary key, quantity TEXT, price TEXT)");
        MyDB.execSQL("Create Table transactions(transNo TEXT primary key, medname TEXT, tquantity TEXT, total_price text, date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {

        MyDB.execSQL("Drop Table if exists users");
        MyDB.execSQL("Drop Table if exists inventory");
        MyDB.execSQL("Drop Table if exists transactions");
    }


    //INVENTORY
    public Boolean insertInventoryData(String medname, String quantity, String total_price) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("medname", medname);
        contentValues.put("quantity", quantity);
        contentValues.put("price", total_price);
        long results = MyDB.insert("inventory", null, contentValues);
        if (results == 1) return false;
        else
            return true;
    }

    public boolean checkmedname(String medname) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from inventory where medname=?", new String[]{medname});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean checkmednameinfo(String medname, String quantity, String price) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from inventory where medname = ?", new String[]{medname, quantity, price});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    //LOGIN USERS DB
    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long results = MyDB.insert("users", null, contentValues);
        if (results == 1) return false;
        else
            return true;

    }
    //CHECK IF THE USER EXIST IN THE TABLE UWU

    public boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username=?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ? ", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }



    //Transactions
//    public Boolean insertTransData(String transNo, String items_purchased, String fquantity, String total_price,String date) {
//        SQLiteDatabase MyDB = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("transNo", transNo);
//        contentValues.put("items purchased", items_purchased);
//        contentValues.put("quantity", fquantity);
//        contentValues.put("total price", total_price);
//        contentValues.put("date", date);
//        long results = MyDB.insert("transactions", null, contentValues);
//        if (results == 1) return false;
//        else
//            return true;
//    }

    public Cursor viewData(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query ="Select + from" + db;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;


    }

    public boolean insert(String medname, String quantity,String total_price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(medname, medname);
        contentValues.put(quantity, quantity);
        contentValues.put(total_price, total_price);

        long result = db.insert("transactions", null, contentValues);
        return result != -1;
    }

}

















//    public boolean checktransactions(String transNo) {
//        SQLiteDatabase MyDB = this.getWritableDatabase();
//        Cursor cursor = MyDB.rawQuery("Select * from transactions where transNo=?", new String[]{transNo});
//        if (cursor.getCount() > 0)
//            return true;
//        else
//            return false;
//    }
//
//    public boolean checktransinfo(String transNo, String date) {
//        SQLiteDatabase MyDB = this.getWritableDatabase();
//        Cursor cursor = MyDB.rawQuery("Select * from transactions where medname = ?", new String[]{transNo, date});
//        if (cursor.getCount() > 0)
//            return true;
//        else
//            return false;
//
//    }