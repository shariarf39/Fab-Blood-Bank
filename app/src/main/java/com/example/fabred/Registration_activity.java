package com.example.fabred;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Registration_activity extends AppCompatActivity {

    EditText name, phone, email, address, password, cpassword;
    String[] blood_group, devision_name, sexs;
    private Spinner blood_select, devison, sex_select;
    Button idBtnRegister;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        blood_select = findViewById(R.id.bloodgroup);
        devison = findViewById(R.id.devison);
        sex_select = findViewById(R.id.sex_select);
        idBtnRegister = findViewById(R.id.idBtnRegister);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        password = findViewById(R.id.Password);
        progressBar = findViewById(R.id.respros);
        cpassword = findViewById(R.id.idEdtConfromPassword);

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


                ///////////
                try {
                    value = URLEncoder.encode(value,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }

                //////////////
              String names = name.getText().toString();
              String phones = phone. getText().toString();
              String emails = email.getText().toString();
              String addresss = address.getText().toString();
              String passwords = password.getText().toString();
              String cpasswords = cpassword.getText().toString();

                if (TextUtils.isEmpty(names)) {
                    name.setError("Please Enter Your Name");
                    name.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(phones)){
                    phone.setError("Enter Your Phone Number");
                    phone.requestFocus();
                    return;
                }



                String mobileNumber = "+8801000000000";
                if (!Patterns.PHONE.matcher(phones).matches()) {

                    Toast.makeText(getApplicationContext(),"Invalid Number",Toast.LENGTH_SHORT).show();
                    phone.setError("Invalid Number");
                    phone.requestFocus();
                }




                    if(TextUtils.isEmpty(emails)){
                    email.setError("Enter Your E-mail");
                    email.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(emails).matches())
                {
                    Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                    email.setError("Invalid email address");
                    email.requestFocus();
                    // or
                    //  textView.setText("valid email");
                    return;
                }


                if (TextUtils.isEmpty(addresss)){
                    address.setError("Enter Your Address");
                    address.requestFocus();
                    return;
                }  if (TextUtils.isEmpty(passwords)){
                    password.setError("Enter Your Address");
                    password.requestFocus();
                    return;
                }

                if (passwords.length()<6){
                    Toast.makeText(Registration_activity.this,"Enter At least 7 digit",Toast.LENGTH_SHORT).show();
                    password.setError("Enter At least 7 digit");
                    password.requestFocus();

                    return;

                }





                if (TextUtils.isEmpty(cpasswords)){
                    cpassword.setError("Enter Your Address");
                    cpassword.requestFocus();
                    return;
                }
                if(!passwords.equals(cpasswords)){
                    Toast.makeText(Registration_activity.this, "Password Dot Match", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(Registration_activity.this)
                            .setTitle("Check Again")
                            .setMessage("Password Don't Match")
                            .show();
                    return;
                }




                    String url = "https://shariarfahimf39.000webhostapp.com/apps/singup.php?name=" + names + "&phone=" + phones + "&email=" + emails + "&address=" + addresss +"&group=" +value +"&sex="+ sex +"&division=" +values+ "&password=" +passwords;
                progressBar.setVisibility(View.VISIBLE);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);

                        Toast.makeText(Registration_activity.this, "Registration Done", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Registration_activity.this, Log_in.class);
                        startActivity(intent);


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


                RequestQueue requestQueue = Volley.newRequestQueue(Registration_activity.this);
                requestQueue.add(stringRequest);






                /////////////
            }
        });

    }
}