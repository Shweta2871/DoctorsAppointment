package com.example.android.aayur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.database.Cursor;


import android.os.Bundle;

import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class P_list extends AppCompatActivity {

    DatabaseHelper db;
    ArrayList<String> id, name, symptom;
    String main_doc_id, value, dateformat, date;
    P_CustomAdapter p_customAdapter;


    RecyclerView rvpat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_list);

        rvpat = findViewById(R.id.rv_pat);
        db = new DatabaseHelper(P_list.this);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            main_doc_id = extras.getString("main_doc_id");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        dateformat = sdf.format(System.currentTimeMillis());

        id = new ArrayList<>();
        name = new ArrayList<>();
        symptom = new ArrayList<>();


        storeDataInArray();

        p_customAdapter = new P_CustomAdapter(P_list.this, id, name, symptom);
        rvpat.setAdapter(p_customAdapter);
        rvpat.setLayoutManager(new LinearLayoutManager(P_list.this));
    }

    void storeDataInArray(){
        Cursor cursor = db.readPatData(main_doc_id, dateformat);
        if(cursor.getCount()==0){
            Toast.makeText(this, "No appointments!", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                symptom.add(cursor.getString(2 ));
            }
        }
    }
}
