package com.example.android.aayur;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class P_dashboard extends AppCompatActivity {

    DatabaseHelper db;
    String main_pat_id;
    CardView homeRemedy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_dashboard);
        db = new DatabaseHelper(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            main_pat_id = extras.getString("main_pat_id");
        }

    }



    public void openBookAppointment(View view) {
        Intent i = new Intent(P_dashboard.this, D_list.class);
        i.putExtra("main_pat_id",main_pat_id);
        startActivity(i);
    }


    public void showHistory(View view) {
        StringBuffer buffer = new StringBuffer();
        Cursor res = db.getPatHistory(main_pat_id);
        if(res.getCount()==0){
            Toast.makeText(P_dashboard.this,"No Appointments!", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            //res.moveToFirst();
            while (res.moveToNext()){
                buffer.append("Clinic Name: "+res.getString(0)+"\n");
                buffer.append("Doctor Name: "+res.getString(1)+"\n");
                buffer.append("Date: "+res.getString(2)+"\n");
                buffer.append("Symptom: "+res.getString(3)+"\n"+"\n");
            }
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(P_dashboard.this);
        builder.setCancelable(true);
        builder.setTitle("History of Appointments");
        builder.setMessage(buffer.toString());
        builder.show();
    }

    public void openProfilePat(View view) {
        Intent i = new Intent(P_dashboard.this, P_profile.class);
        i.putExtra("main_pat_id",main_pat_id);
        startActivity(i);
    }

    public void openHomeRemedy(View view) {
        Intent i = new Intent(P_dashboard.this, homeRemedy.class);
        startActivity(i);
    }
}
