package com.example.android.aayur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class confirmedBooking extends AppCompatActivity {

    String doc_id, main_pat_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed_booking);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            doc_id = extras.getString("d_id");
            main_pat_id = extras.getString("main_pat_id");
        }


    }
}
