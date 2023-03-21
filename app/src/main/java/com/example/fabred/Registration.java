package com.example.fabred;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
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
import java.net.URLEncoder;

public class Registration extends AppCompatActivity {

    EditText name, phone, email, address, password, cpassword;
    String[] blood_group, devision_name, sexs;
    private Spinner blood_select, devison, sex_select;
    Button idBtnRegister;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);
        idBtnRegister = findViewById(R.id.idBtnRegister);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        password = findViewById(R.id.Password);
        progressBar = findViewById(R.id.respros);








        idBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //////////////
                String names = name.getText().toString();
                String phones = phone. getText().toString();
                String emails = email.getText().toString();
                String addresss = address.getText().toString();
                String passwords = password.getText().toString();


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
                    Toast.makeText(Registration.this,"Enter At least 7 digit",Toast.LENGTH_SHORT).show();
                    password.setError("Enter At least 7 digit");
                    password.requestFocus();

                    return;

                }









                String url = "https://shariarfahimf39.000webhostapp.com/apps/singup.php?name=" + names + "&phone=" + phones + "&email=" + emails + "&address=" + addresss + "&password=" +passwords;
                progressBar.setVisibility(View.VISIBLE);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);

                        Toast.makeText(Registration.this, "Registration Done", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Registration.this, Log_in.class);
                        startActivity(intent);


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


                RequestQueue requestQueue = Volley.newRequestQueue(Registration.this);
                requestQueue.add(stringRequest);






                /////////////
            }
        });

    }
}