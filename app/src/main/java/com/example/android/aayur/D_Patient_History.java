package com.example.android.aayur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class D_Patient_History extends AppCompatActivity {

    TextView tvDate;
    DatePickerDialog.OnDateSetListener setListener;
    String main_doc_id, date, date1;
    RecyclerView rv_patHist;
    D_HistoryAdapter d_historyAdapter;
    Button show;
    Integer day1;

    DatabaseHelper db;
    ArrayList<String> id, name, symptom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d__patient__history);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            main_doc_id = extras.getString("main_doc_id");
        }

        rv_patHist = findViewById(R.id.rv_patHist);
        tvDate = findViewById(R.id.date);
        show = findViewById(R.id.show);


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int  day = calendar.get(Calendar.DAY_OF_MONTH);


        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(D_Patient_History.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                if(day<=9){
                    date1=String.valueOf(day);
                    date1="0"+date1;
                    date = date1+"/"+month+"/"+year;
                    tvDate.setText(date);
                }
                else{
                    date = day+"/"+month+"/"+year;
                    tvDate.setText(date);
                }
            }
        };

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                date = tvDate.getText().toString();
                Toast.makeText(getApplicationContext(),"Date="+date,Toast.LENGTH_SHORT).show();
                rv_patHist.setVisibility(View.VISIBLE);

                db = new DatabaseHelper(D_Patient_History.this);
                id = new ArrayList<>();
                name = new ArrayList<>();
                symptom = new ArrayList<>();

                storeDataInArray(date);

                d_historyAdapter = new D_HistoryAdapter(D_Patient_History.this, id, name, symptom);
                rv_patHist.setAdapter(d_historyAdapter);
                rv_patHist.setLayoutManager(new LinearLayoutManager(D_Patient_History.this));


            }
        });


    }

    void storeDataInArray(String date){
        Cursor cursor = db.readPatData(main_doc_id, date);
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
