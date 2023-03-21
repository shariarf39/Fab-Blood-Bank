package com.example.fabred;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class Home extends AppCompatActivity {

    EditText s2d_date, s2d_notice;
    Button s2d_upload, noti;
    ProgressBar s2d_progress;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    Toolbar toolbar;

    ListView s2d_listview;
    TextView notis;

    SearchView searchView;

    LinearLayout blood_donner, blood_amb, donner_map, help_line, blood_req, feed;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    HashMap <String, String> hashMap;
    NavigationView nav;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    String[] name;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        s2d_listview = findViewById(R.id.s2d_plan_listview);
        blood_req = findViewById(R.id.blood_req);
        blood_donner = findViewById(R.id.blood_donner);
        donner_map = findViewById(R.id.donner_map);
        searchView = findViewById(R.id.searchView);
        help_line = findViewById(R.id.help_line);
        feed = findViewById(R.id.feed);

        s2d_progress = findViewById(R.id.s2d_progress);
        nav = findViewById(R.id.navmenu);
        blood_amb = findViewById(R.id.blood_amb);

        toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);


        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        sharedPreferences = getSharedPreferences("Fab_Red",Context.MODE_PRIVATE);

        editor= sharedPreferences.edit();
        ///////////


        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.menu_home:
                        Toast.makeText(getApplicationContext(),"Under Development",Toast.LENGTH_SHORT).show();
                        drawerLayout.openDrawer(GravityCompat.START);
                        new AlertDialog.Builder(Home.this)
                                .setTitle("Home")
                                .setMessage("Under Development")
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Whatever...
                                        dialog.cancel();
                                    }
                                }).show();


                        break;
                    case R.id.menu_set:
                        Toast.makeText(getApplicationContext(),"Under Development",Toast.LENGTH_SHORT).show();

                        new AlertDialog.Builder(Home.this)
                                .setTitle("Setting")
                                .setMessage("Under Development")
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Whatever...
                                        dialog.cancel();
                                    }
                                }).show();

                        break;
                    case R.id.menu_contact:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Toast.makeText(getApplicationContext(),"Near By Hospital open",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Home.this, Blood_Donor_List.class);
                        startActivity(intent);





                        break;
                    case R.id.nub_blood:

                        Toast.makeText(getApplicationContext(),"Blood Bank \nUnder Development",Toast.LENGTH_SHORT).show();

                        new AlertDialog.Builder(Home.this)
                                .setTitle("Blood Bank")
                                .setMessage("Under Development")
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Whatever...
                                        dialog.cancel();
                                    }
                                }).show();

                        break;
                    case R.id.dev_info:
                        Toast.makeText(getApplicationContext(),"Developer Info",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        new AlertDialog.Builder(Home.this)
                                .setTitle("Developer Info")
                               // .setIcon(R.drawable.developer_pic)
                                .setMessage("* Md Fahim Shariar\n* Email: shariarf39@gmail.com\n* No. (+8801892382840)\n* Satkhira Bangladesh\n* Computer Science and Engineering at Study" +
                                        " \n* Northern University Bangladesh")
                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Whatever...
                                        dialog.cancel();
                                    }
                                }).show();

                        break;
                    case R.id.LogOut:
                        Toast.makeText(getApplicationContext(),"Succesfully Log Out",Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);

                        editor.clear();
                        editor.commit();
                        Intent mintent = new Intent(Home.this, Log_in.class);
                        startActivity(mintent);






                        break;
                }





                return true;
            }
        });

///////////
        blood_donner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Blood_Donor_List.class);
                startActivity(intent);
            }
        });

        blood_amb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Ambulance.class);
                startActivity(intent);
            }
        });
        donner_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Donner_Map.class);
                startActivity(intent);
            }
        });
        help_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Help_.class);
                startActivity(intent);
            }
        });
        blood_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Registration_activity.class);
                startActivity(intent);
            }
        });
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Req_Activity.class);
                startActivity(intent);
            }
        });


        //////////

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){


        }else{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            notis.setText("No Internet");
            new AlertDialog.Builder(Home.this)
                    .setTitle("Connection Error")
                    .setMessage("Check your Internet Connection")
                    .show();
            s2d_progress.setVisibility(View.GONE);

        }

    }
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired
    private long mBackPressed;

    // When user click bakpress button this method is called
    @Override
    public void onBackPressed() {

        // When landing in home screen

        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else {

            Toast.makeText(getBaseContext(), "Press again to exit",
                    Toast.LENGTH_SHORT).show();
        }

        mBackPressed = System.currentTimeMillis();

    }
}