package com.example.fabred;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Log_in extends AppCompatActivity {

    soup.neumorphism.NeumorphTextView registration;


    String stn;
    Button login;
    EditText nub_id, nub_password;
    CheckBox checkBox;
    ProgressBar progressBar;
    ListView listView;
    // public static String PREFS_NAME=""

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        registration = findViewById(R.id.registration);







        registration = findViewById(R.id.registration);
        login= findViewById(R.id.login_button);
        nub_id = findViewById(R.id.nub_id);
        nub_password = findViewById(R.id.nub_password);
        // checkBox = findViewById(R.id.checkbox);
        progressBar = findViewById(R.id.progress);


        ///---------------------Login




        RequestQueue requestQueue = Volley.newRequestQueue(Log_in.this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if(networkInfo != null && networkInfo.isConnected()){


                }else{
                    Toast.makeText(Log_in.this, "No Internet", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    new AlertDialog.Builder(Log_in.this)
                            .setTitle("Connection Error")
                            .setMessage("Check your Internet Connection...")
                            .show();

                }

                String admins = nub_id.getText().toString();
                String passw = nub_password.getText().toString();
                if (TextUtils.isEmpty(admins)) {
                    nub_id.setError("Please Enter NUB ID");
                    nub_id.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (TextUtils.isEmpty(passw)) {
                    nub_password.setError("Please Enter Your Password");
                    nub_password.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }







                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "http://shariarfahimf39.000webhostapp.com/apps/login.php", null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        for (int x=0; x < response.length();  x++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(x);
                            //    String id = jsonObject.getString("id");

                              //  String name = jsonObject.getString("nubid");
                                //String plan = jsonObject.getString("password");
                                //String comment = jsonObject.getString("comment");
                                // Hash Map
                                //    hashMap = new HashMap<>();
                                //   hashMap.put("id", id);
                                //   hashMap.put("date", name);
                                //   hashMap.put("notice", plan);
                                // hashMap.put("comment", comment);
                                //    arrayList.add(hashMap);




                                String app = "1";
                                String apps = "0";
                                String block = "2";


                                if (admins.equals(jsonObject.getString("email")) && passw.equals(jsonObject.getString("password"))){
                                    //    if (passw.equals(jsonObject.getString("password"))){

                                    progressBar.setVisibility(View.GONE);

                                    Toast.makeText(Log_in.this, "Welcome", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Log_in.this, Home.class);
                                    startActivity(intent);
                                    finish();



                                }else{
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(Log_in.this, "Your E-mail Password Wrong", Toast.LENGTH_SHORT).show();
                                }






//
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }



                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("serverRes",error.toString());

                    }
                });
                requestQueue.add(jsonArrayRequest);



            }
        });



























        ////////////////////
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Log_in.this, Registration_activity.class);
                startActivity(intent);
            }
        });

    }
}