package com.example.android.aayur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class bookAppointment extends AppCompatActivity {

    ContentValues contentAppointValues = new ContentValues();

    TextView clinicName, docName, degree, city, mobile, fee, openTime, closeTime, date;
    EditText symptomps;
    Button confirm;
    DatabaseHelper db;

    String doc_id = "not set", dateformat, main_pat_id;
    String symp = null, d_id = null, d_name = null , d_degree = null, d_fees = null, d_clinic = null, d_mobile = null,  d_location = null, d_open = null, d_close = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            doc_id = extras.getString("id");
            main_pat_id = extras.getString("main_pat_id");
        }

        clinicName = (TextView) findViewById(R.id.clinicName);
        docName = (TextView) findViewById(R.id.name);
        degree = (TextView) findViewById(R.id.degree);
        city = (TextView) findViewById(R.id.city);
        mobile = (TextView) findViewById(R.id.mobile);
        fee = (TextView) findViewById(R.id.fee);
        openTime = (TextView) findViewById(R.id.openTime);
        closeTime = (TextView) findViewById(R.id.closeTime);
        symptomps = (EditText) findViewById(R.id.Symptom);
        confirm = (Button) findViewById(R.id.confirmBooking);
        date = (TextView) findViewById(R.id.date);

        db = new DatabaseHelper(this);


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        dateformat = sdf.format(System.currentTimeMillis());
        date.setText(dateformat);



        Cursor cursor = db.alldata(doc_id);
        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();
        }
        else {
            cursor.moveToFirst();
            d_id = cursor.getString(0);
            d_name = cursor.getString(3);
            d_degree = cursor.getString(4);
            d_fees = cursor.getString(5);
            d_mobile=cursor.getString(6);
            d_clinic = cursor.getString(7);
            d_location = cursor.getString(8);
            d_open = cursor.getString(9);
            d_close = cursor.getString(10);

            try{
                clinicName.setText(d_clinic);
                docName.setText(d_name);
                degree.setText(d_degree);
                city.setText(d_location);
                mobile.setText(d_mobile);
                fee.setText(d_fees);
                openTime.setText(d_open);
                closeTime.setText(d_close);

            }catch (NullPointerException ignored){

            }

        }

    }


    public void BtnConfirm(View view) {
        String attend = "no";
        symp = symptomps.getText().toString();

        Boolean checkInsertData = db.insertAppointment(d_id, main_pat_id, symp, attend, dateformat);
        if(checkInsertData == true){
            Toast.makeText(bookAppointment.this,"Appointment Confirmed!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(bookAppointment.this,"Error in making appointment!",Toast.LENGTH_SHORT).show();
        }

        Intent i = new Intent(bookAppointment.this, confirmedBooking.class);
        i.putExtra("d_id",d_id);
        i.putExtra("main_pat_id",main_pat_id);
        startActivity(i);
        finish();
    }
}
