package model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String COLUMN_ID = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_AGE + " INT, " + COLUMN_ACTIVE_CUSTOMER + " BOOL)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(CustomerModel cModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CUSTOMER_NAME, cModel.getName());
        cv.put(COLUMN_CUSTOMER_AGE, cModel.getAge());
        cv.put(COLUMN_ACTIVE_CUSTOMER, cModel.isActive());
        long insert = db.insert(CUSTOMER_TABLE, null , cv);
        if(insert == -1){
            return false;
        }
        else{
            return true;
        }

    }

    public boolean deleteOne(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CUSTOMER_TABLE, COLUMN_ID + "=" + id, null) > 0;
    }
//    public boolean deleteOne(CustomerModel customerModel){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String qrString = "DELETE FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_ID + " = " + customerModel.getId();
//        Cursor cursor = db.rawQuery(qrString,null);
//        if(cursor.moveToFirst()){
//            return true;
//        }
//        else{
//            return false;
//        }
//
//    }

    public void deleteAll(){
        String queryString = "DELETE FROM " + CUSTOMER_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(queryString);
    }
    public List<CustomerModel> getEveryone(){
        List<CustomerModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do{
                int customerID = cursor.getInt(0);
                String customerName = cursor.getString(1);
                int customerAge = cursor.getInt(2);
                boolean customerActive = cursor.getInt(3) == 1 ? true:false;
                CustomerModel cModel = new CustomerModel(customerID, customerName, customerAge,  customerActive);
                returnList.add(cModel);
            }
            while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
//        System.out.println("List ****" + returnList);
        return returnList;

    }
}