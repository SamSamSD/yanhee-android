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
import android.widget.CompoundButton;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class PersonalActivity extends AppCompatActivity {
    final String name = "keb";
    SharedPreferences pref;
    //SharedPreferences.Editor editor;
//    final String csd_no = pref.getString("csd_no","");
//    final String emp_id = pref.getString("emp_id","");
    CheckBox cb10,cb11,cb20,cb21,cb30,cb31,cb40,cb41,cb50,cb51,cb60,cb61,cb70,cb71;
    Button submit_ps;
    String[] ps_checked = new String[]{"0","0","0","0","0","0","0"};
    BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        cb10 = findViewById(R.id.cb10);
        cb11 = findViewById(R.id.cb11);
        cb20 = findViewById(R.id.cb20);
        cb21 = findViewById(R.id.cb21);
        cb30 = findViewById(R.id.cb30);
        cb31 = findViewById(R.id.cb31);
        cb40 = findViewById(R.id.cb40);
        cb41 = findViewById(R.id.cb41);
        cb50 = findViewById(R.id.cb50);
        cb51 = findViewById(R.id.cb51);
        cb60 = findViewById(R.id.cb60);
        cb61 = findViewById(R.id.cb61);
        cb70 = findViewById(R.id.cb70);
        cb71 = findViewById(R.id.cb71);
        submit_ps = findViewById(R.id.submit_ps);

        nav = findViewById(R.id.bottom_nav_view);
        nav.setSelectedItemId(R.id.item_2);
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
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
    public void postPersonal() {
        String checkIdUrl = MainActivity.url+"app_ps.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, checkIdUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("response",response);
                        Intent inform = new Intent(PersonalActivity.this, DashBoardActivity.class);
//                        inform.putExtra("response", response);
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
//                params.put("idCard", emp_id);
//                params.put("csd_no", csd_no);
                params.put("checked0", ps_checked[0]);
                params.put("checked1", ps_checked[1]);
                params.put("checked2", ps_checked[2]);
                params.put("checked3", ps_checked[3]);
                params.put("checked4", ps_checked[4]);
                params.put("checked5", ps_checked[5]);
                params.put("checked6", ps_checked[6]);
                Log.i("x",params.toString());
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
