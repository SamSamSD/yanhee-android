package com.example.helloworld.yhhospital;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FamilyActivity extends AppCompatActivity {
    private Button cb10, cb11, cb20, cb21, cb30, cb31, cb40, cb41, cb50, cb51;
    private TextView x1,x2,x3,x4,x5;
    Button submit_family;
    ProgressDialog dialog;
    private JSONObject obj;
    SharePreference gg = new SharePreference();
    String[] family_checked = new String[]{"0", "0", "0", "0", "0"};
    BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
        this.setTitle("ข้อมูลครอบครัว");

        dialog = ProgressDialog.show(FamilyActivity.this, null,"Loading...", false,true);
        final String csd_no = gg.getStringData(getApplicationContext(), "csd_no");
        final String emp_id = gg.getStringData(getApplicationContext(),"idCard");
        x1 = findViewById(R.id.textView112);
        x2 = findViewById(R.id.textView113);
        x3 = findViewById(R.id.textView114);
        x4 = findViewById(R.id.textView115);
        x5 = findViewById(R.id.textView116);
        cb10 = findViewById(R.id.btn11);
        cb11 = findViewById(R.id.btn12);
        cb20 = findViewById(R.id.btn13);
        cb21 = findViewById(R.id.btn14);
        cb30 = findViewById(R.id.btn15);
        cb31 = findViewById(R.id.btn16);
        cb40 = findViewById(R.id.btn17);
        cb41 = findViewById(R.id.btn18);
        cb50 = findViewById(R.id.btn19);
        cb51 = findViewById(R.id.btn110);
        submit_family = findViewById(R.id.submit_family);
        getFamily(emp_id,csd_no);


        submit_family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postFamily();
            }
        });

        nav = findViewById(R.id.bottom_nav_view);
        nav.setSelectedItemId(R.id.item_3);

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_0:
                        Intent inform0 = new Intent(FamilyActivity.this, DashBoardActivity.class);
                        startActivity(inform0);
                        return true;
                    case R.id.item_1:
                        Intent inform2 = new Intent(FamilyActivity.this, InformationActivity.class);
                        startActivity(inform2);
                        return true;
                    case R.id.item_2:
                        Intent inform3 = new Intent(FamilyActivity.this, PersonalActivity.class);
                        startActivity(inform3);
                        return true;
                    case R.id.item_3:
                        return true;
                    case R.id.item_4:
                        Intent inform4 = new Intent(FamilyActivity.this, PEActivity.class);
                        startActivity(inform4);
                        return true;
                }
                return false;
            }
        });
        handleButton();
    }

    public void handleButton() {

        cb10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                family_checked[0] = "1";
                cb10.setBackgroundColor(Color.rgb(255, 77, 77));
                cb11.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                family_checked[0] = "0";
                cb11.setBackgroundColor(Color.rgb(0,255,153));
                cb10.setBackgroundColor(android.R.drawable.btn_default);
            }
        });
        cb20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                family_checked[1] = "1";
                cb20.setBackgroundColor(Color.rgb(255, 77, 77));
                cb21.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                family_checked[1] = "0";
                cb21.setBackgroundColor(Color.rgb(0,255,153));
                cb20.setBackgroundColor(android.R.drawable.btn_default);
            }
        });
        cb30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                family_checked[2] = "1";
                cb30.setBackgroundColor(Color.rgb(255, 77, 77));
                cb31.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                family_checked[2] = "0";
                cb31.setBackgroundColor(Color.rgb(0,255,153));
                cb30.setBackgroundColor(android.R.drawable.btn_default);
            }
        });
        cb40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                family_checked[3] = "1";
                cb40.setBackgroundColor(Color.rgb(255, 77, 77));
                cb41.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                family_checked[3] = "0";
                cb41.setBackgroundColor(Color.rgb(0,255,153));
                cb40.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                family_checked[4] = "1";
                cb50.setBackgroundColor(Color.rgb(255, 77, 77));
                cb51.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                family_checked[4] = "0";
                cb51.setBackgroundColor(Color.rgb(0,255,153));
                cb50.setBackgroundColor(android.R.drawable.btn_default);
            }
        });
    }

    public void getFamily(final String emp_id,final String csd_no) {
        String familyUrl = MainActivity.url+"app_get_family.php";

        StringRequest postRequest = new StringRequest(Request.Method.POST, familyUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dialog.dismiss();
                        try {
                            JSONArray res = new JSONArray(response);
                            x1.setText(res.getJSONObject(0).getString("name"));
                            x2.setText(res.getJSONObject(1).getString("name"));
                            x3.setText(res.getJSONObject(2).getString("name"));
                            x4.setText(res.getJSONObject(3).getString("name"));
                            x5.setText(res.getJSONObject(4).getString("name"));

                            if(res.getJSONObject(0).getString("status").equals("0")) {
                                cb11.setBackgroundColor(Color.rgb(0,255,153));
                            }else if(res.getJSONObject(0).getString("status").equals("1")) {
                                cb10.setBackgroundColor(Color.rgb(255, 77, 77));
                            }

                            if(res.getJSONObject(1).getString("status").equals("0")) {
                                cb21.setBackgroundColor(Color.rgb(0,255,153));
                            }else if(res.getJSONObject(1).getString("status").equals("1")) {
                                cb20.setBackgroundColor(Color.rgb(255, 77, 77));
                            }

                            if(res.getJSONObject(2).getString("status").equals("0")) {
                                cb31.setBackgroundColor(Color.rgb(0,255,153));
                            }else if(res.getJSONObject(2).getString("status").equals("1")) {
                                cb30.setBackgroundColor(Color.rgb(255, 77, 77));
                            }

                            if(res.getJSONObject(3).getString("status").equals("0")) {
                                cb41.setBackgroundColor(Color.rgb(0,255,153));
                            }else if(res.getJSONObject(3).getString("status").equals("1")) {
                                cb40.setBackgroundColor(Color.rgb(255, 77, 77));
                            }

                            if(res.getJSONObject(4).getString("status").equals("0")) {
                                cb51.setBackgroundColor(Color.rgb(0,255,153));
                            }else if(res.getJSONObject(4).getString("status").equals("1")) {
                                cb50.setBackgroundColor(Color.rgb(255, 77, 77));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
                params.put("idCard", emp_id);
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

    public void postFamily() {
        String checkIdUrl = MainActivity.url+"app_family.php";
        final String emp_id = gg.getStringData(this,"idCard");
        final String csd_no = gg.getStringData(this,"csd_no");

        StringRequest postRequest = new StringRequest(Request.Method.POST, checkIdUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent inform = new Intent(FamilyActivity.this, DashBoardActivity.class);
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
                params.put("idCard", emp_id);
                params.put("csd_no", csd_no);
                params.put("x1", family_checked[0]);
                params.put("x2", family_checked[1]);
                params.put("x3", family_checked[2]);
                params.put("x4", family_checked[3]);
                params.put("x5", family_checked[4]);
                params.put("user",gg.getStringData(getApplicationContext(),"user"));
                Log.i("hhh",params.toString());
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
