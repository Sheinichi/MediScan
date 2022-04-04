package com.example.newmediscan;

public class DBAdapter {

    private static String Medicine;
    private static String trn_id;
    private static int itemCount;
    private static int date;

    public DBAdapter(String medicine, String trn_id, int itemCount, int date) {
        Medicine = medicine;
        this.trn_id = trn_id;
        this.itemCount = itemCount;
        this.date = date;
    }

    public static String getMedicine() {
        return Medicine;
    }

    public static String getTrn_id() {
        return trn_id;
    }

    public static int getItemCount() {
        return itemCount;
    }

    public static int getDate() {
        return date;
    }
}
