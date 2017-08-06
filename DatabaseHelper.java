package com.example.ashish.loginregister;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Ashish on 3/26/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table if not exists users " +
                        "(id integer primary key AUTOINCREMENT NOT NULL, username text ,password text, type text )"
        );
        db.execSQL(
                "create table if not exists products " +
                        "(id integer primary key AUTOINCREMENT NOT NULL, product text,seller text)"
        );
        db.execSQL("create table if not exists currentuser (user text)");
        this.db = db;


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS products");
        db.execSQL("DROP TABLE IF EXISTS currentuser");
        this.onCreate(db);

    }

    public boolean insertThisRecord(UsersAndProducts usr) {
        db = this.getWritableDatabase();
        int id = 0;
        Cursor crs = db.rawQuery("select count(*) from users",null);
        if(crs == null || crs.getCount()==0)
            id=0;
        else
            id = crs.getCount()+1;
        if(!(verifyThisUser(usr.getUserName(), usr.getUserType()))){
            ContentValues cv = new ContentValues();
            cv.put("username", usr.getUserName());
            cv.put("password", usr.getUserPassword());
            cv.put("type", usr.getUserType());
            db.insert("users", null, cv);
            db.close();
            return true;
        }
        return false;
    }

    private boolean verifyThisUser(String userName, String userType) {
        db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("select * from users where username = '"+userName+"' and type = '"+userType+"'", null);
        if(cr.getCount() == 1){

            return true;
        }

        return false;
    }

    public boolean verifyThisUser(String s, String s1, String type) {
        db=this.getWritableDatabase();
        db.execSQL("create table if not exists currentuser (user text)");
        ContentValues cv = new ContentValues();
        cv.put("user",s);
        db.insert("currentuser",null,cv);
        db.close();
        db = this.getReadableDatabase();
        Cursor cr = db.rawQuery("select * from users where username = '"+s+"' and password = '"+s1+"' and type = '"+type+"'", null);
        if(cr.getCount() == 1)
            return true;
        return false;
    }

    public boolean addThisProduct(String product) {
        db=this.getReadableDatabase();
        Cursor cr = db.rawQuery("select * from currentuser", null);
        String user=null;
        if(cr != null && cr.moveToFirst()) {
            user = cr.getString(cr.getColumnIndex("user"));
        }
        db.close();
        db = this.getWritableDatabase();
        int id = 0;
        Cursor crs = db.rawQuery("select count(*) from products",null);
        if(crs == null || crs.getCount()==0)
            id=1;
        else
            id = crs.getCount()+1;
        ContentValues cv = new ContentValues();
        cv.put("product", product);
        cv.put("seller", user);
        db.insert("products", null, cv);
        db.close();
        return true;
    }

    public void clearCurrentUser() {
        db = this.getWritableDatabase();
        db.execSQL("delete from currentuser");
        //db.execSQL("drop table products");
        //db.execSQL("drop table users");

        db.close();
    }

    public int getRowCountProd() {
        db = this.getReadableDatabase();
        Cursor c1 = db.rawQuery("select user from currentuser",null);
        String user=null;
        if(c1 != null && c1.moveToFirst()) {
            user = c1.getString(c1.getColumnIndex("user"));
        }
        Cursor cr = db.rawQuery("select * from products where seller!='"+user+"'",null);
        return cr.getCount();
    }

    public ArrayList<String> getAllRowsFor(String thisObj) {
        db=this.getReadableDatabase();
        int rows = getRowCountProd();
        Cursor c1 = db.rawQuery("select user from currentuser",null);
        String user=null;
        if(c1 != null && c1.moveToFirst()) {
            user = c1.getString(c1.getColumnIndex("user"));
        }
        Cursor c2 = db.rawQuery("select product,seller from products where seller!='"+user+"'",null);
        ArrayList<String> temp = new ArrayList<>();
        if(c2 != null && c2.moveToFirst()) {
            do{
                temp.add(c2.getString(c2.getColumnIndex(thisObj)));

            }while(c2.moveToNext());
        }
        return temp;

    }


    public int getRowCountProdForSeller() {
        db = this.getReadableDatabase();
        Cursor c1 = db.rawQuery("select user from currentuser",null);
        String user=null;
        if(c1 != null && c1.moveToFirst()) {
            user = c1.getString(c1.getColumnIndex("user"));
        }
        Cursor cr = db.rawQuery("select * from products where seller='"+user+"'",null);
        return cr.getCount();
    }

    public ArrayList<String> getAllRowsForForSeller(String product) {
        db=this.getReadableDatabase();
        int rows = getRowCountProd();
        Cursor c1 = db.rawQuery("select user from currentuser",null);
        String user=null;
        if(c1 != null && c1.moveToFirst()) {
            user = c1.getString(c1.getColumnIndex("user"));
        }
        Cursor c2 = db.rawQuery("select product,seller from products where seller='"+user+"'",null);
        ArrayList<String> temp = new ArrayList<>();
        if(c2 != null && c2.moveToFirst()) {
            do{
                temp.add(c2.getString(c2.getColumnIndex(product)));

            }while(c2.moveToNext());
        }
        return temp;
    }

    public void deleteAllProd() {
        db=this.getReadableDatabase();
        int rows = getRowCountProd();
        Cursor c1 = db.rawQuery("select user from currentuser",null);
        String user=null;
        if(c1 != null && c1.moveToFirst()) {
            user = c1.getString(c1.getColumnIndex("user"));
        }
        db=this.getWritableDatabase();
        db.execSQL("delete from products where seller='"+user+"'");
        db.close();
    }
}
