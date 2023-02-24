package com.example.fabred;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class Registration_activity extends AppCompatActivity {


    String[] blood_group, devision_name, sexs;
    private Spinner blood_select, devison, sex_select;
    Button idBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        blood_select = findViewById(R.id.bloodgroup);
        devison = findViewById(R.id.devison);
        sex_select = findViewById(R.id.sex_select);
        idBtnRegister = findViewById(R.id.idBtnRegister);

        blood_group = getResources().getStringArray(R.array.blood_group);
        devision_name = getResources().getStringArray(R.array.division_name);
        sexs = getResources().getStringArray(R.array.sex);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.sample_spinner,R.id.spinner_sampleview, blood_group);
        ArrayAdapter<String> arrayAdapters = new ArrayAdapter<String>(this,R.layout.sample_spinner,R.id.spinner_division_view, devision_name);
        ArrayAdapter<String> sex = new ArrayAdapter<String>(this,R.layout.sample_spinner,R.id.spinner_sex,sexs);
        blood_select.setAdapter(arrayAdapter);
        devison.setAdapter(arrayAdapters);
        sex_select.setAdapter(sex);
        idBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value =blood_select.getSelectedItem().toString();
                String values =devison.getSelectedItem().toString();
                String sex =sex_select.getSelectedItem().toString();
                idBtnRegister.setText(values);
                idBtnRegister.setText(value);
                idBtnRegister.setText(sex);
            }
        });

    }
}