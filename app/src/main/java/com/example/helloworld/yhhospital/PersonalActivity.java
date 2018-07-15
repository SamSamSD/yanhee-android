package com.example.helloworld.yhhospital;

import android.app.Presentation;
import android.app.ProgressDialog;
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
import android.widget.CompoundButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class PersonalActivity extends AppCompatActivity {
    final String name = "keb";
    private TextView x1,x2,x3,x4,x5,x6,x7;
    SharePreference gg = new SharePreference();
    CheckBox cb10,cb11,cb20,cb21,cb30,cb31,cb40,cb41,cb50,cb51,cb60,cb61,cb70,cb71;
    Button submit_ps;
    ProgressDialog dialog;
    String[] ps_checked = new String[]{"0", "0", "0", "0", "0", "0", "0"};
    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        this.setTitle("ข้อมูลส่วนตัว");

        dialog = ProgressDialog.show(PersonalActivity.this, null,"Loading...", false,true);
        x1 = findViewById(R.id.textView2);
        x2 = findViewById(R.id.textView3);
        x3 = findViewById(R.id.textView4);
        x4 = findViewById(R.id.textView5);
        x5 = findViewById(R.id.textView6);
        x6 = findViewById(R.id.textView7);
        x7 = findViewById(R.id.textView8);
        cb10 = findViewById(R.id.checkBox2);
        cb11 = findViewById(R.id.checkBox);
        cb20 = findViewById(R.id.checkBox3);
        cb21 = findViewById(R.id.checkBox4);
        cb30 = findViewById(R.id.checkBox5);
        cb31 = findViewById(R.id.checkBox6);
        cb40 = findViewById(R.id.checkBox7);
        cb41 = findViewById(R.id.checkBox8);
        cb50 = findViewById(R.id.checkBox9);
        cb51 = findViewById(R.id.checkBox10);
        cb60 = findViewById(R.id.checkBox11);
        cb61 = findViewById(R.id.checkBox12);
        cb70 = findViewById(R.id.checkBox13);
        cb71 = findViewById(R.id.checkBox14);
        submit_ps = findViewById(R.id.submit_ps);
        final String csd_no = gg.getStringData(getApplicationContext(),"csd_no");
        final String emp_id = gg.getStringData(getApplicationContext(),"idCard");
        getPersonal(emp_id,csd_no);

        nav = findViewById(R.id.bottom_nav_view);
        nav.setSelectedItemId(R.id.item_2);
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_0:
                        Intent inform0 = new Intent(PersonalActivity.this, DashBoardActivity.class);
                        startActivity(inform0);
                        return true;
                    case R.id.item_2:
                        return true;
                    case R.id.item_1:
                        Intent inform2 = new Intent(PersonalActivity.this, InformationActivity.class);
                        startActivity(inform2);
                        return true;
                    case R.id.item_3:
                        Intent inform3 = new Intent(PersonalActivity.this, FamilyActivity.class);
                        startActivity(inform3);
                        return true;
                    case R.id.item_4:
                        Intent inform4 = new Intent(PersonalActivity.this, PEActivity.class);
                        startActivity(inform4);
                        return true;
                }
                return false;
            }
        });
        handleButton();


    }

    public void handleButton() {

        cb10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    cb11.setChecked(false);
                    cb11.setSelected(false);
                    ps_checked[0] = "1";

                }
            }
        });
        cb11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    cb10.setChecked(false);
                    cb10.setSelected(false);
                    ps_checked[0] = "0";
                }
            }
        });
        cb20.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    cb21.setChecked(false);
                    cb21.setSelected(false);
                    ps_checked[1] = "1";
                }
            }
        });
        cb21.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    cb20.setChecked(false);
                    cb20.setSelected(false);
                    ps_checked[1] = "0";
                }
            }
        });
        cb30.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    cb31.setChecked(false);
                    cb31.setSelected(false);
                    ps_checked[2] = "1";
                }
            }
        });
        cb31.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    cb30.setChecked(false);
                    cb30.setSelected(false);
                    ps_checked[2] = "0";
                }
            }
        });
        cb40.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    cb41.setChecked(false);
                    cb41.setSelected(false);
                    ps_checked[3] = "1";
                }
            }
        });
        cb41.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    cb40.setChecked(false);
                    cb40.setSelected(false);
                    ps_checked[3] = "0";
                }
            }
        });
        cb50.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    cb51.setChecked(false);
                    cb51.setSelected(false);
                    ps_checked[4] = "1";
                }
            }
        });
        cb51.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    cb50.setChecked(false);
                    cb50.setSelected(false);
                    ps_checked[4] = "0";
                }
            }
        });
        cb60.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    cb61.setChecked(false);
                    cb61.setSelected(false);
                    ps_checked[5] = "1";
                }
            }
        });
        cb61.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    cb60.setChecked(false);
                    cb60.setSelected(false);
                    ps_checked[5] = "0";
                }
            }
        });
        cb70.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    cb71.setChecked(false);
                    cb71.setSelected(false);
                    ps_checked[6] = "1";
                }
            }
        });
        cb71.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    cb70.setChecked(false);
                    cb70.setSelected(false);
                    ps_checked[6] ="0";
                }
            }
        });
        submit_ps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postPersonal();
            }
        });
    }

    public void getPersonal(final String emp_id,final String csd_no) {
        String checkIdUrl = MainActivity.url+"app_get_personal.php";

        StringRequest postRequest = new StringRequest(Request.Method.POST, checkIdUrl,
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
                            x6.setText(res.getJSONObject(5).getString("name"));
                            x7.setText(res.getJSONObject(6).getString("name"));

                            if(res.getJSONObject(0).getString("status").equals("0")) {
                                cb11.setChecked(true);
                            }else if(res.getJSONObject(0).getString("status").equals("1")) {
                                cb10.setChecked(true);
                            }

                            if(res.getJSONObject(1).getString("status").equals("0")) {
                                cb21.setChecked(true);
                            }else if(res.getJSONObject(1).getString("status").equals("1")) {
                                cb20.setChecked(true);
                            }

                            if(res.getJSONObject(2).getString("status").equals("0")) {
                                cb31.setChecked(true);
                            }else if(res.getJSONObject(2).getString("status").equals("1")) {
                                cb30.setChecked(true);
                            }

                            if(res.getJSONObject(3).getString("status").equals("0")) {
                                cb41.setChecked(true);
                            }else if(res.getJSONObject(3).getString("status").equals("1")) {
                                cb40.setChecked(true);
                            }

                            if(res.getJSONObject(4).getString("status").equals("0")) {
                                cb51.setChecked(true);
                            }else if(res.getJSONObject(4).getString("status").equals("1")) {
                                cb50.setChecked(true);
                            }

                            if(res.getJSONObject(5).getString("status").equals("0")) {
                                cb61.setChecked(true);
                            }else if(res.getJSONObject(5).getString("status").equals("1")) {
                                cb60.setChecked(true);
                            }

                            if(res.getJSONObject(6).getString("status").equals("0")) {
                                cb71.setChecked(true);
                            }else if(res.getJSONObject(6).getString("status").equals("1")) {
                                cb70.setChecked(true);
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

    public void postPersonal() {
        String checkIdUrl = MainActivity.url+"app_ps.php";
        final String emp_id = gg.getStringData(this,"idCard");
        final String csd_no = gg.getStringData(this,"csd_no");

        StringRequest postRequest = new StringRequest(Request.Method.POST, checkIdUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent inform = new Intent(PersonalActivity.this, DashBoardActivity.class);
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
                params.put("c1", ps_checked[0]);
                params.put("c2", ps_checked[1]);
                params.put("c3", ps_checked[2]);
                params.put("c4", ps_checked[3]);
                params.put("c5", ps_checked[4]);
                params.put("c6", ps_checked[5]);
                params.put("c7", ps_checked[6]);
                params.put("user",gg.getStringData(getApplicationContext(),"user"));
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
