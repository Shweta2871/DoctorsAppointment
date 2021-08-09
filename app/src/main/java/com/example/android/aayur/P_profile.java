package com.example.android.aayur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class P_profile extends AppCompatActivity {

    DatabaseHelper db;
    TextView name, mail, age,gender, city, medicalHist, bloogGrp;
    String main_pat_id, p_name,p_mail,p_age,p_gender,p_bloodGrp, p_city,p_medicalHist;
    Button gotoDashboard, Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_profile);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            main_pat_id = extras.getString("main_pat_id");
        }

        db = new DatabaseHelper(this);
        name = findViewById(R.id.name);
        mail = findViewById(R.id.profile_mail);
        age = findViewById(R.id.profile_age);
        gender = findViewById(R.id.profile_gender);
        bloogGrp = findViewById(R.id.profile_bloodgrp);
        city = findViewById(R.id.profile_city);
        medicalHist = findViewById(R.id.profile_medicalHist);

        gotoDashboard = findViewById(R.id.P_DASH);
        Logout = findViewById(R.id.Logout);


        Cursor cursor = db.profilePat(main_pat_id);
        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();
        }
        else {
            cursor.moveToFirst();
            p_name = cursor.getString(3);
            p_mail = cursor.getString(1);
            p_age = cursor.getString(4);
            p_gender=cursor.getString(5);
            p_bloodGrp=cursor.getString(6);
            p_city = cursor.getString(7);
            p_medicalHist = cursor.getString(8);

            try{
                name.setText(p_name);
                mail.setText(p_mail);
                age.setText(p_age);
                gender.setText(p_gender);
                bloogGrp.setText(p_bloodGrp);
                city.setText(p_city);
                medicalHist.setText(p_medicalHist);

            }catch (NullPointerException ignored){

            }

        }


        gotoDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(P_profile.this,P_dashboard.class);
                i.putExtra("main_pat_id",main_pat_id);
                startActivity(i);
                finish();
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(P_profile.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
