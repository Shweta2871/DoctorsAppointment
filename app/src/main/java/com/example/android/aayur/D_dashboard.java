package com.example.android.aayur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class D_dashboard extends AppCompatActivity {

    String main_doc_id;
    CardView cardLiveQueue, PatientHistory, DocProfile, homeRemedy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_dashboard);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            main_doc_id = extras.getString("main_doc_id");
        }

        cardLiveQueue = (CardView) findViewById(R.id.LiveQueue);
        PatientHistory = (CardView) findViewById(R.id.History);
        DocProfile = (CardView) findViewById(R.id.doc_profile);
        homeRemedy = (CardView) findViewById(R.id.homeRemedy);


        cardLiveQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(D_dashboard.this , P_list.class);
                i.putExtra("main_doc_id",main_doc_id);
                startActivity(i);
            }
        });

        PatientHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(D_dashboard.this, D_Patient_History.class);
                i.putExtra("main_doc_id",main_doc_id);
                startActivity(i);
            }
        });

        DocProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(D_dashboard.this, D_profile.class);
                i.putExtra("main_doc_id",main_doc_id);
                startActivity(i);
            }
        });

        homeRemedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(D_dashboard.this, homeRemedy.class);
                startActivity(i);
            }
        });
    }
}
