package com.example.android.aayur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    DatabaseHelper databaseHelper;

    private static int SPLASH_TIME_OUT = 4000;
    LinearLayout signup_ll, login_ll, doctor_signup1, doctor_signup2, category_ll, patient_signup1, patient_signup2, doctor_login, patient_login;
    RadioGroup category;
    TextView category_text;

    TextInputEditText mail, pass, d_mail, d_pass, d_name, d_degree, d_fees, d_mobile, d_clinicNm, d_location, d_opentym, d_closetym;
    TextInputEditText p_mail, p_pass, p_name, p_age, p_gender, p_bloodgrp, p_city, p_medicalHist;
    Button btn_login, btn_d_signup_next, btn_p_signup_next, btn_d_signup, btn_p_signup;

    ContentValues contentDValues = new ContentValues();
    ContentValues contentPValues = new ContentValues();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_ll = (LinearLayout)findViewById(R.id.login_ll);
        signup_ll = (LinearLayout)findViewById(R.id.signup_ll);
        category_ll = (LinearLayout)findViewById(R.id.signup_category_ll);
        doctor_signup1 = (LinearLayout)findViewById(R.id.su_doctor_ll1);
        doctor_signup2 = (LinearLayout)findViewById(R.id.su_doctor_ll2);
        patient_signup1 = (LinearLayout)findViewById(R.id.su_pat_ll1);
        patient_signup2 = (LinearLayout)findViewById(R.id.su_pat_ll2);
        category_text = (TextView) findViewById(R.id.category_text);

        mail = (TextInputEditText) findViewById(R.id.mail_login);
        pass = (TextInputEditText) findViewById(R.id.password_login);
        d_mail = (TextInputEditText) findViewById(R.id.mail_signup_d);
        d_pass = (TextInputEditText) findViewById(R.id.password_signup_d);
        d_name = (TextInputEditText) findViewById(R.id.name_signup_d);
        d_degree = (TextInputEditText) findViewById(R.id.degree_signup_d);
        d_fees = (TextInputEditText) findViewById(R.id.fees_signup_d);
        d_mobile = (TextInputEditText) findViewById(R.id.mobile_signup_d);
        d_clinicNm = (TextInputEditText) findViewById(R.id.clinicNm_signup_d);
        d_location = (TextInputEditText) findViewById(R.id.location_signup_d);
        d_opentym = (TextInputEditText) findViewById(R.id.Open_signup_d);
        d_closetym = (TextInputEditText) findViewById(R.id.Close_signup_d);

        p_mail = (TextInputEditText) findViewById(R.id.email_signup_p);
        p_pass = (TextInputEditText) findViewById(R.id.pass_signup_p);
        p_name = (TextInputEditText) findViewById(R.id.name_signup_p);
        p_age = (TextInputEditText) findViewById(R.id.age_signup_p);
        p_gender = (TextInputEditText) findViewById(R.id.gender_signup_p);
        p_bloodgrp = (TextInputEditText) findViewById(R.id.bloodgrp_signup_p);
        p_city = (TextInputEditText) findViewById(R.id.city_signup_p);
        p_medicalHist = (TextInputEditText) findViewById(R.id.medicalhist_signup_p);

        btn_login = (Button) findViewById(R.id.LOGIN);
        btn_d_signup_next = (Button) findViewById(R.id.btn_su_doctor_next);
        btn_d_signup = (Button) findViewById(R.id.d_sigupBtn);
        btn_p_signup_next = (Button) findViewById(R.id.su_pat_next);
        btn_p_signup = (Button) findViewById(R.id.p_signup);

        databaseHelper = new DatabaseHelper(this);



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String db_mail=null;
                String db_pass=null;
                try{
                    db_mail = mail.getText().toString();
                    db_pass = pass.getText().toString();

                }catch (NullPointerException ignored){

                }

                RadioButton doc_radio = (RadioButton) findViewById(R.id.Doctor);
                RadioButton pat_radio = (RadioButton) findViewById(R.id.Patient);

                if(doc_radio.isChecked()){
                    if(databaseHelper.isDLoginValid(db_mail,db_pass)){
                        String main_doc_id = databaseHelper.setMainDocId(db_mail,db_pass);
                        Intent i = new Intent(MainActivity.this, D_dashboard.class);
                        i.putExtra("main_doc_id",main_doc_id);
                        startActivity(i);
                        Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Invalid Doctor Login or Password!", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(pat_radio.isChecked()){
                    if(databaseHelper.isPLoginValid(db_mail,db_pass)){
                        String main_pat_id = databaseHelper.setMainPatId(db_mail,db_pass);

                        Intent i = new Intent(MainActivity.this, P_dashboard.class);
                        i.putExtra("main_pat_id",main_pat_id);
                        startActivity(i);
                        Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Invalid Patient Login or Password!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn_d_signup_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "Doctor";
                String db_d_mail = d_mail.getText().toString();
                String db_d_pass = d_pass.getText().toString();
                String db_d_name = d_name.getText().toString();
                String db_d_degree = d_degree.getText().toString();
                String db_d_fees = d_fees.getText().toString();
                String db_d_mobile = d_mobile.getText().toString();

                if(db_d_mail.length() > 1){
                    contentDValues.put("d_mail", db_d_mail);
                    contentDValues.put("d_pass", db_d_pass);
                    contentDValues.put("d_name", db_d_name);
                    contentDValues.put("d_degree", db_d_degree);
                    contentDValues.put("d_fees", db_d_fees);
                    contentDValues.put("d_mobile", db_d_mobile);

                    doctor_signup1.setVisibility(View.GONE);
                    doctor_signup2.setVisibility(View.VISIBLE);


                }
                else{
                    Toast.makeText(MainActivity.this,"Enter the values!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_p_signup_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "Patient";
                String db_p_mail=null;
                String db_p_pass=null;
                String db_p_name=null;
                String db_p_age=null;
                String db_p_gender=null;

                try{
                    db_p_mail = String.valueOf(p_mail.getText());
                    db_p_pass = String.valueOf(p_pass.getText());
                    db_p_name = String.valueOf(p_name.getText());
                    db_p_age = String.valueOf(p_age.getText());
                    db_p_gender = String.valueOf(p_gender.getText());
                }catch(NullPointerException ignored){

                }


                if(db_p_mail.length() > 1){
                    contentPValues.put("p_mail", db_p_mail);
                    contentPValues.put("p_pass", db_p_pass);
                    contentPValues.put("p_name", db_p_name);
                    contentPValues.put("p_age", db_p_age);
                    contentPValues.put("p_gender", db_p_gender);

                    patient_signup1.setVisibility(View.GONE);
                    patient_signup2.setVisibility(View.VISIBLE);


                }
                else{
                    Toast.makeText(MainActivity.this,"Enter the values!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_d_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String db_d_clinic = d_clinicNm.getText().toString();
                String db_d_location = d_location.getText().toString();
                String db_d_open = d_opentym.getText().toString();
                String db_d_close = d_closetym.getText().toString();

                if(db_d_clinic.length()>1){
                    contentDValues.put("d_clinicNm", db_d_clinic);
                    contentDValues.put("d_location", db_d_location);
                    contentDValues.put("d_opentym", db_d_open);
                    contentDValues.put("d_closetym", db_d_close);

                    databaseHelper.insertDoctor(contentDValues);
                    Toast.makeText(MainActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();

                    signup_ll.setVisibility(View.GONE);
                    login_ll.setVisibility(View.VISIBLE);


                }
                else{
                    Toast.makeText(MainActivity.this,"Enter the values!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_p_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String db_p_bloodgrp = p_bloodgrp.getText().toString();
                String db_p_city = p_city.getText().toString();
                String db_p_medicalHist = p_medicalHist.getText().toString();

                if(db_p_bloodgrp.length()>1){
                    contentPValues.put("p_bloodgrp", db_p_bloodgrp);
                    contentPValues.put("p_city", db_p_city);
                    contentPValues.put("p_medicalHist", db_p_medicalHist);

                    databaseHelper.insertPatient(contentPValues);
                    Toast.makeText(MainActivity.this,"Registered Successfully1",Toast.LENGTH_SHORT).show();

                    signup_ll.setVisibility(View.GONE);
                    login_ll.setVisibility(View.VISIBLE);


                }
                else{
                    Toast.makeText(MainActivity.this,"Enter the values!",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
    

    public void textloginToSignup(View view) {
        signup_ll.setVisibility(View.VISIBLE);
        login_ll.setVisibility(View.GONE);
    }

    public void radioBtn_Clicked(View view) {
        boolean checked = ((RadioButton)view).isChecked();

        switch(view.getId()){
            case R.id.radioBtn_Doctor_signup:
                if (checked) {
                    doctor_signup1.setVisibility(View.VISIBLE);
                    patient_signup1.setVisibility(View.GONE);
                    category_ll.setVisibility(View.GONE);
                }
                break;
            case R.id.radioBtn_Patient_signup:
                if(checked) {
                    doctor_signup1.setVisibility(View.GONE);
                    patient_signup1.setVisibility(View.VISIBLE);
                    category_ll.setVisibility(View.GONE);
                }
                break;

        }

    }

}

