package com.example.android.aayur;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{

    String main_doc_id;
    String main_pat_id;
    static String name = "database";
    static int version = 1;


    String createDoctor = "CREATE TABLE if not exists `doctor` (`id` INTEGER PRIMARY KEY AUTOINCREMENT ,`d_mail`	TEXT,`d_pass`	TEXT,`d_name`	TEXT, `d_degree`	TEXT,`d_fees`	TEXT, `d_mobile`	TEXT, " +
            "`d_clinicNm`	TEXT, `d_location`	TEXT, `d_opentym`	TEXT, `d_closetym`	TEXT)";

    String createPatient = "CREATE TABLE if not exists `patient` ( `id`	INTEGER PRIMARY KEY AUTOINCREMENT, `p_mail`	TEXT, `p_pass`	TEXT, `p_name`	TEXT, `p_age`	TEXT, `p_gender`	TEXT, `p_bloodgrp`	TEXT," +
            " `p_city`	TEXT, `p_medicalHist`	TEXT )";

    String createAppointment = "CREATE TABLE if not exists `appointment` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `d_id` TEXT, `p_id` TEXT, `symptomps` TEXT, `attended` TEXT, `date` TEXT)";


    public DatabaseHelper(Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(createDoctor);
        getWritableDatabase().execSQL(createPatient);
        getWritableDatabase().execSQL(createAppointment);
    }

    public void insertDoctor(ContentValues contentDValues){
        getWritableDatabase().insert("doctor","",contentDValues);
    }

    public void insertPatient(ContentValues contentPValues){
        getWritableDatabase().insert("patient","",contentPValues);
    }


    public boolean insertAppointment(String d_id, String main_pat_id, String symptomp, String attended, String date){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("d_id",d_id);
        contentValues.put("p_id",main_pat_id);
        contentValues.put("symptomps",symptomp);
        contentValues.put("attended",attended);
        contentValues.put("date",date);

        long result = DB.insert("appointment",null,contentValues);
        if(result == -1){
            return false;
        }else {
            return true;
        }

    }


    public boolean isDLoginValid(String mail, String password){
        String sql = "Select count(*) from Doctor where d_mail='"+ mail + "' and d_pass='" +password +"'";
        String getDocId =  "Select id from Doctor where d_mail='"+mail+"' and d_pass='"+password+"'";
        SQLiteStatement state = getReadableDatabase().compileStatement(getDocId);
        main_doc_id = state.toString();

        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        long l =  statement.simpleQueryForLong();
        statement.close();

        if(l == 1){
            return  true;
        }
        else{
            return false;
        }
    }

    public boolean isPLoginValid(String mail, String password){
        String sql = "Select count(*) from Patient where p_mail='"+ mail + "' and p_pass='" +password +"'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        long l =  statement.simpleQueryForLong();
        statement.close();

        if(l == 1){
            return  true;
        }
        else{
            return false;
        }
    }

    public String setMainDocId(String mail, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from doctor where d_mail='"+mail+"' and d_pass='"+password+"'",null);
        cursor.moveToFirst();
        main_doc_id = cursor.getString(0);
        return main_doc_id;
        //Log.v(String.valueOf(bookAppointment.class),main_doc_id);
    }

    public String setMainPatId(String mail, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from patient where p_mail='"+mail+"' and p_pass='"+password+"'",null);
        cursor.moveToFirst();
        main_pat_id = cursor.getString(0);
        return main_pat_id;

    }

    /*public String getTokenNumber(String main_pat_id, String doc_id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * ROW_NUMBER() OVER (ORDER BY id) AS rowNo from appointment where d_id='"+doc_id+"' and p_id='"+main_pat_id+"'", null);
        cursor.moveToFirst();
        String token = cursor.getString(5);
        return token;

    }*/

    Cursor readDocData(){
        String query = "SELECT * FROM doctor";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db!=null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    public Cursor alldata(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from doctor where id = "+id , null);
        return cursor;
    }


    public Cursor getPatHistory(String main_pat_id){
        Log.v(String.valueOf(bookAppointment.class),"patient id"+main_pat_id);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select doctor.d_clinicNm, doctor.d_name, appointment.date, appointment.symptomps from appointment INNER JOIN doctor ON appointment.d_id = doctor.id where appointment.p_id = '"+main_pat_id+"' order by appointment.id desc limit 3" , null);
        return cursor;
    }

    public Cursor readPatData(String main_doc_id, String date){
        String query = "select appointment.id, patient.p_name, appointment.symptomps from appointment inner join patient on appointment.p_id = patient.id where d_id='"+main_doc_id+"' and date='"+date+"'" ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db!= null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor profilePat(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from patient where id = "+id , null);
        return cursor;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor profileDoc(String main_doc_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from doctor where id = "+main_doc_id , null);
        return cursor;
    }
}
