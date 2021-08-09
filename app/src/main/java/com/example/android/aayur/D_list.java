package com.example.android.aayur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class D_list extends AppCompatActivity {

    String main_pat_id;
    RecyclerView recyclerView;
    DatabaseHelper myDB;
    ArrayList<String> d_id, d_name, d_degree, d_fees, d_clinic, d_location, d_open, d_close;
    D_CustomAdapter d_customAdapter;
    private D_CustomAdapter.RecyclerViewClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_list);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            main_pat_id = extras.getString("main_pat_id");
        }

        recyclerView = findViewById(R.id.rv_doc);

        myDB = new DatabaseHelper(D_list.this);
        d_id = new ArrayList<>();
        d_name = new ArrayList<>();
        d_degree = new ArrayList<>();
        d_fees = new ArrayList<>();
        d_clinic = new ArrayList<>();
        d_location = new ArrayList<>();
        d_open = new ArrayList<>();
        d_close = new ArrayList<>();

        storeDocDataArray();

        setOnClickListener();
        d_customAdapter = new D_CustomAdapter(D_list.this, d_id, d_name, d_degree, d_fees, d_clinic, d_location, d_open, d_close, listener);
        recyclerView.setAdapter(d_customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(D_list.this));

    }

    private void setOnClickListener() {
        listener = new D_CustomAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), bookAppointment.class);
                intent.putExtra("id", d_id.get(position));
                intent.putExtra("main_pat_id",main_pat_id);
                startActivity(intent);
            }
        };
    }

    void storeDocDataArray(){
        Cursor cursor = myDB.readDocData();
        if(cursor.getCount()==0){
            Toast.makeText(this,"No doctors found",Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                    d_id .add(cursor.getString(0));
                    d_name.add(cursor.getString(3));
                    d_degree.add(cursor.getString(4));
                    d_fees.add(cursor.getString(5));
                    d_clinic.add(cursor.getString(7));
                    d_location.add(cursor.getString(8));
                    d_open.add(cursor.getString(9));
                    d_close.add(cursor.getString(10));
            }
        }
    }
}
