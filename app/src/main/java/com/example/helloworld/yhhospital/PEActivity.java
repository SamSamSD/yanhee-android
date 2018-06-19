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
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class PEActivity extends AppCompatActivity {
    BottomNavigationView nav;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Button submit_pe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pe);
        pref = getSharedPreferences("keb", Context.MODE_PRIVATE);

        final String idCard = pref.getString("idCard","");
        final String csd_no = pref.getString("csd_no","");

        submit_pe = findViewById(R.id.submit_pe);
        nav = findViewById(R.id.bottom_nav_view);
        nav.setSelectedItemId(R.id.item_4);

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_0:
                        Intent inform0 = new Intent(PEActivity.this, DashBoardActivity.class);
                        startActivity(inform0);
                        return true;
                    case R.id.item_4:
                        return true;
                    case R.id.item_2:
                        Intent inform2 = new Intent(PEActivity.this, PersonalActivity.class);
                        startActivity(inform2);
                        return true;
                    case R.id.item_3:
                        Intent inform3 = new Intent(PEActivity.this, FamilyActivity.class);
                        startActivity(inform3);
                        return true;
                    case R.id.item_1:
                        Intent inform4 = new Intent(PEActivity.this, InformationActivity.class);
                        startActivity(inform4);
                        return true;
                }
                return false;
            }
        });

        submit_pe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("submit","submit");
                postPE(idCard, csd_no);
            }
        });
    }

    public void postPE(final String idCard,final String csd_no) {
        String checkIdUrl = MainActivity.url+"app_pe.php";

        StringRequest postRequest = new StringRequest(Request.Method.POST, checkIdUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("okk",response);

                        Intent inform = new Intent(PEActivity.this, DashBoardActivity.class);
                        inform.putExtra("response", response);
                        startActivity(inform);
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
                params.put("csd_no", csd_no);
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