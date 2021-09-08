package com.example.rteupazar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.sql.Blob;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String db_Name ="RteuPazari";
    private static RteuPazar rteuPazar ;
    public DatabaseHelper(@Nullable Context context) {
        super(context, db_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Pazar(ID INTEGER PRIMARY KEY,Fiyat VARCHAR, Ozellik VARCHAR, image BLOB, email VARCHAR,adres VARCHAR)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Uye(ID INTEGER PRIMARY KEY,AD VARCHAR, SOYAD VARCHAR, Email VARCHAR, Password VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean kayitkontrol(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM Uye WHERE Email=?",new String[]{email});
            if (cursor.getCount()>0){
                Log.e("hesap oluşmazzz",email);

                return false;

            }else {
                Log.e("hesap oluşturulabilir",email);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();

        }

        return false;
    }
    public ArrayList<RteuPazar> Listele(){
        ArrayList<RteuPazar> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
       try {
           Cursor cursor = db.rawQuery("SELECT * FROM Pazar",null);
           if (cursor.getCount()>0){
               while (cursor.moveToNext()){
                   int id = cursor.getInt(cursor.getColumnIndex("ID"));
                   String fiyat = cursor.getString(cursor.getColumnIndex("Fiyat"));
                   String ozellik = cursor.getString(cursor.getColumnIndex("Ozellik"));
                   String email = cursor.getString(cursor.getColumnIndex("email"));
                   String adres = cursor.getString(cursor.getColumnIndex("adres"));
                   byte[] img = cursor.getBlob(cursor.getColumnIndex("image"));
                   list.add(new RteuPazar(id,fiyat,ozellik,email,adres,img));

               }
           }
           cursor.close();

       }catch (Exception e){
           e.printStackTrace();
       }
        return list;
    }
    public ArrayList<RteuPazar> secVeri(String adress){
        ArrayList<RteuPazar> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM Pazar WHERE adres=?",new String[]{adress});
            if (cursor.getCount()>0){
                while (cursor.moveToNext()){
                    int id = cursor.getInt(cursor.getColumnIndex("ID"));
                    String fiyat = cursor.getString(cursor.getColumnIndex("Fiyat"));
                    String ozellik = cursor.getString(cursor.getColumnIndex("Ozellik"));
                    String email = cursor.getString(cursor.getColumnIndex("email"));
                    String adres = cursor.getString(cursor.getColumnIndex("adres"));
                    byte[] img = cursor.getBlob(cursor.getColumnIndex("image"));
                    list.add(new RteuPazar(id,fiyat,ozellik,email,adres,img));

                }
            }
            cursor.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public void UyeKayit (String name, String surname, String email, String password) {
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            String sqlString= "INSERT INTO Uye (AD,SOYAD,Email,Password) VALUES (?,?,?,?)";
            SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
            sqLiteStatement.bindString(1,name);
            sqLiteStatement.bindString(2,surname);
            sqLiteStatement.bindString(3,email);
            sqLiteStatement.bindString(4,password);
            sqLiteStatement.execute();
            database.close();
            Log.e("Kayıt Başarılı","başarılı");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  boolean UyeGiris (String mail, String sifre){
        try {
            SQLiteDatabase database = this.getReadableDatabase();
            Cursor cursor =  database.rawQuery("SELECT * FROM Uye WHERE Email=? AND Password=?",new String[]{mail,sifre});
            if (cursor != null){
                if (cursor.getCount()>0){
                    return true;

                }else {
                    return false ;

                }


            }else {
                Log.e("Boş  ","cursor");
            }
            cursor.close();

        }catch (Exception e ){
            e.printStackTrace();
        }
        return false;
    }
    public void UrunPaylas(String fiyat, String ozellik,byte[] image, String email,String adres){
       try {
           SQLiteDatabase database = this.getWritableDatabase();
           String sqlString = "INSERT INTO Pazar(Fiyat,Ozellik,image,email,adres) VALUES (?,?,?,?,?)";
           SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
           sqLiteStatement.bindString(1, fiyat);
           sqLiteStatement.bindString(2, ozellik);
           sqLiteStatement.bindBlob(3, image);
           sqLiteStatement.bindString(4, email);
           sqLiteStatement.bindString(5, adres);

           sqLiteStatement.execute();
           sqLiteStatement.close();
       }catch (Exception e){
           e.printStackTrace();
       }


    }

    
}
