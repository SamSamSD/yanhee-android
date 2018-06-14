package com.example.helloworld.yhhospital;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class DashBoardActivity extends AppCompatActivity{
    TextView textView1,textView2,textView3;
    final String name = "keb";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Bundle bundle = getIntent().getExtras();

        String idCard = bundle.getString("idCard");
        String csNo = bundle.getString("csNo");
        postDashBoard(idCard,csNo);

        //getActionBar().setHomeButtonEnabled(true);


        textView1 = findViewById(R.id.show_employee);
        textView2 = findViewById(R.id.show_employee2);
        textView3 = findViewById(R.id.show_employee3);
        nav = findViewById(R.id.bottom_nav_view);
        pref = getSharedPreferences(name, Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.putString("emp_id",idCard);
        editor.commit();

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_1:
                        Intent inform1 = new Intent(DashBoardActivity.this, InformationActivity.class);
                        startActivity(inform1);
                        return true;
                    case R.id.item_2:
                        Intent inform2 = new Intent(DashBoardActivity.this, PersonalActivity.class);
                        startActivity(inform2);
                        return true;
                    case R.id.item_3:
                        Intent inform3 = new Intent(DashBoardActivity.this, FamilyActivity.class);
                        startActivity(inform3);
                        return true;
                    case R.id.item_4:
                        Intent inform4 = new Intent(DashBoardActivity.this, PEActivity.class);
                        startActivity(inform4);
                        return true;
                }
                return false;
            }
        });
    }

    public void postDashBoard(final String idCard,final String csNo) {
        String checkIdUrl = MainActivity.url+"app_dashboard1.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, checkIdUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String[] response2 = response.split(" ");
                        textView1.setText(String.valueOf(response2[0]+" "+response2[1]+" "+response2[2]+" " +response2[3]+" "+response2[4]));
                        textView2.setText(String.valueOf(response2[5]+" "+response2[6] +" "+response2[7]+response2[8]+" "+response2[9]));
                        textView3.setText(String.valueOf(response2[10]+" "+response2[11]));
                        pref = getSharedPreferences(name, Context.MODE_PRIVATE);
                        Log.i("abs",response2[12]);
                        editor = pref.edit();
                        editor.putString("csd_no", response2[12]);
                        editor.commit();
                        Log.i("res", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError err) {
                        Log.d("Error.Response", err.getMessage());
                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("idCard", idCard);
                params.put("csNo", csNo);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String,String> params = new HashMap<>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);
    }



}
