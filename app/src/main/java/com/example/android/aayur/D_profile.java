package com.example.android.aayur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class D_profile extends AppCompatActivity {

    DatabaseHelper db;
    TextView name, mail, clinic,degree, location, fees, contact;
    String main_doc_id, d_name,d_mail,d_clinic, d_degree, d_location, d_fees, d_contact;
    Button gotoDashboard, Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_profile);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            main_doc_id = extras.getString("main_doc_id");
        }

        db = new DatabaseHelper(this);
        name = findViewById(R.id.name);
        mail = findViewById(R.id.profile_mail);
        clinic = findViewById(R.id.profile_clinic);
        degree = findViewById(R.id.profile_degree);
        location = findViewById(R.id.profile_location);
        fees = findViewById(R.id.profile_fees);
        contact = findViewById(R.id.profile_contact);

        gotoDashboard = findViewById(R.id.P_DASH);
        Logout = findViewById(R.id.Logout);

        Cursor cursor = db.profileDoc(main_doc_id);
        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();
        }
        else {
            cursor.moveToFirst();
            d_name = cursor.getString(3);
            d_mail = cursor.getString(1);
            d_clinic = cursor.getString(7);
            d_degree =cursor.getString(4);
            d_location =cursor.getString(8);
            d_fees = cursor.getString(5);
            d_contact = cursor.getString(6);

            try{
                name.setText(d_name);
                mail.setText(d_mail);
                clinic.setText(d_clinic);
                degree.setText(d_degree);
                location.setText(d_location);
                fees.setText(d_fees);
                contact.setText(d_contact);

            }catch (NullPointerException ignored){

            }

        }


        gotoDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(D_profile.this,D_dashboard.class);
                i.putExtra("main_pat_id",main_doc_id);
                startActivity(i);
                finish();
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(D_profile.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }


}
