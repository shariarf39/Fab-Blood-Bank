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
        searchView = findViewById(R.id.searchView);

        s2d_progress = findViewById(R.id.s2d_progress);
        nav = findViewById(R.id.navmenu);

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
                        Intent intent = new Intent(Home.this, Hospital_Map.class);
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



        RequestQueue requestQueue = Volley.newRequestQueue(Home.this);
        s2d_progress.setVisibility(View.VISIBLE);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "http://shariarfahimf39.000webhostapp.com/apps/blood_list.php", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                s2d_progress.setVisibility(View.GONE);




                for (int x=0; x < response.length();  x++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(x);
                  //      String id = jsonObject.getString("id");

                        String namee = jsonObject.getString("name");
                        String phonee = jsonObject.getString("phone");
                        String emaile = jsonObject.getString("email");
                        String addresse= jsonObject.getString("address");
                        String groupe = jsonObject.getString("blood_group");
                        String sexe = jsonObject.getString("sex");
                        String divisione = jsonObject.getString("division");





                        // Hash Map
                        hashMap = new HashMap<>();
                       // hashMap.put("id", id);
                        hashMap.put("name", namee);
                        hashMap.put("phone", phonee);
                        hashMap.put("email", emaile);
                        hashMap.put("address", addresse);
                        hashMap.put("blood_group", groupe);
                        hashMap.put("sex", sexe);
                        hashMap.put("division", divisione);

                        arrayList.add(hashMap);



                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (arrayList.size()>0){
                    Home.Myadapter myadapter = new Home.Myadapter();
                    s2d_listview.setAdapter(myadapter);



                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("serverRes",error.toString());

            }
        });



        requestQueue.add(jsonArrayRequest);
        ////////////////////////////////////////////


        ////////////////////////////////////////////




    }





    public class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayList.size();
        }



        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View myview = layoutInflater.inflate(R.layout.item, null);

            // TextView tvid =myview.findViewById(R.id.tvid);
            TextView name =myview.findViewById(R.id.tvname);
            TextView phone =myview.findViewById(R.id.tvplan);
            TextView email =myview.findViewById(R.id.bl_email);
            TextView address =myview.findViewById(R.id.bl_address);
            TextView group =myview.findViewById(R.id.bl_group);
            TextView sex =myview.findViewById(R.id.bl_sex);
            TextView division =myview.findViewById(R.id.bl_division);
            ImageView call =myview.findViewById(R.id.call);

         //  TextView tvcomment =myview.findViewById(R.id.tvcomment);

            hashMap = arrayList.get(position);
            //String id = hashMap.get("id");
            String names = hashMap.get("name");
            String plan = hashMap.get("phone");
            String g_email = hashMap.get("email");
            String g_address = hashMap.get("address");
            String g_group = hashMap.get("blood_group");
            String g_sex = hashMap.get("sex");
            String g_division = hashMap.get("division");

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", plan, null));
                    startActivity(intent);
                }
            });


            // tvid.setText(id);
            name.setText(names);
            group.setText("Blood Group: "+g_group);
            phone.setText(plan);
            email.setText(g_email);
            address.setText(g_address);

            sex.setText(g_sex);
            division.setText(g_division);
            //  tvcomment.setText(comment);

            return myview;
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