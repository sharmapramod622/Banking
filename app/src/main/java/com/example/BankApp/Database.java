package com.example.BankApp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public Database(@Nullable Context context) {
        super(context, "Banking", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9820534674,'Abhishek',5540.12,'abhi238@gmail.com','XXXXXXXXXXXXX2308','ABHI9820534')");
        db.execSQL("insert into user_table values(7593953586,'Pramod',1745.90,'pramod269@gmail.com','XXXXXXXXXXXXX2609','PRAM7593953')");
        db.execSQL("insert into user_table values(8893485784,'Sonu',1359.56,'sonu117@gmail.com','XXXXXXXXXXXX1107','SONU8893485')");
        db.execSQL("insert into user_table values(7666564435,'Rahul',550.22,'rahul810@gmail.com','XXXXXXXXXXXX0810','RAHU7666564')");
        db.execSQL("insert into user_table values(9867744764,'Prince',4003.56,'prince263@gmail.com','XXXXXXXXXXXX2603','PRIN9867744')");
        db.execSQL("insert into user_table values(7776868432,'Akash',1002.66,'akash37@gmail.com','XXXXXXXXXXXX0307','AKAS7776868')");
        db.execSQL("insert into user_table values(8967675343,'Raj',3034.01,'raj97@gmail.com','XXXXXXXXXXXX0907','RAJS8967675')");
        db.execSQL("insert into user_table values(8764523445,'Ajay',788.99,'ajay78@gmail.com','XXXXXXXXXXXX0708','AJAY8764523')");
        db.execSQL("insert into user_table values(9886645376,'Sachin',5674.54,'sachin712@gmail.com','XXXXXXXXXXXX0712','SACH9886645')");
        db.execSQL("insert into user_table values(7757642345,'Rohit',100.00,'rohit96@gmail.com','XXXXXXXXXXXX0906','ROHI7757642')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
