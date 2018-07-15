package com.example.helloworld.yhhospital;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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

import java.util.HashMap;
import java.util.Map;

public class DashBoardActivity extends AppCompatActivity {
    ProgressDialog dialog;
    TextView textView1, textView2, textView3;
    LinearLayout la;
    final String name = "keb";
    SharedPreferences pref;
    SharePreference gg = new SharePreference();
    BottomNavigationView nav;
    JSONObject obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        this.setTitle("หน้าหลัก");
        dialog = ProgressDialog.show(DashBoardActivity.this, null,"Loading...", false,true);
        textView1 = findViewById(R.id.show_employee);
        textView2 = findViewById(R.id.show_employee2);
        textView3 = findViewById(R.id.show_employee3);
        nav = findViewById(R.id.bottom_nav_view);
        la = (LinearLayout) findViewById(R.id.all_buttons);

        pref = getSharedPreferences(name, Context.MODE_PRIVATE);

        final String idCard = pref.getString("idCard", "");
        final String csNo = pref.getString("csNo", "");
        //postDashBoard จะได้ค่า csd_no มาแล้วเก็บลง sharePref
        postDashBoard(idCard, csNo);

        nav.setSelectedItemId(R.id.item_0);
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_0:
                        Intent inform12 = new Intent(DashBoardActivity.this, DashBoardActivity.class);
                        startActivity(inform12);
                        return true;
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
                        Intent inform445 = new Intent(DashBoardActivity.this, PEActivity.class);
                        startActivity(inform445);
                        return true;
                }
                return false;
            }
        });
    }

    public void postDashBoard(final String idCard, final String csNo) {
        String checkIdUrl = MainActivity.url + "app_dashboard1.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, checkIdUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dialog.dismiss();
                        try {
                            JSONArray res = new JSONArray(response);
                            JSONObject obj = res.getJSONObject(0);
                            String no = obj.getString("no");
                            String name = obj.getString("name");
                            String age = obj.getString("age");
                            String bd = obj.getString("bd");
                            String pro = obj.getString("pro");
                            String csd = obj.getString("csd");
                            gg.setStringData(getApplicationContext(),"csd_no",csd);
                            textView1.setText(String.valueOf("ลำดับ "+no+" "+name));
                            textView2.setText(String.valueOf("อายุ "+age+" วันเกิด "+bd));
                            textView3.setText(String.valueOf(pro));
                            getButton();

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
                params.put("idCard", idCard);
                params.put("csNo", csNo);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);
    }

    public void postCL(final int tag_id) {
        String checkIdUrl = MainActivity.url + "app_check_list.php";
        final String idCard = gg.getStringData(this, "idCard");
        final String csd_no = gg.getStringData(this,"csd_no");

        StringRequest postRequest = new StringRequest(Request.Method.POST, checkIdUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

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
                params.put("tag_id", String.valueOf(tag_id));
                params.put("user",gg.getStringData(getApplicationContext(),"user"));
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navv, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.go_to_scan) {
            Intent i = new Intent(DashBoardActivity.this, HomeActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getButton() {
        String url = MainActivity.url + "app_show_button.php";

        final String idCard = gg.getStringData(this, "idCard");
        final String csd_no = gg.getStringData(this,"csd_no");
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray res = new JSONArray(response);
                            final Button[] buttons = new Button[res.length()];
                            for(int i=0; i<res.length(); i++) {
                                obj = res.getJSONObject(i);
                                String id = obj.getString("id");
                                final String name = obj.getString("name");
                                String status = obj.getString("status");
                                int status1 = Integer.parseInt(status);
                                int ct_id = Integer.parseInt(id);

                                Button b = new Button(getApplicationContext());
                                b.setId(ct_id);
                                b.setText(name);
                                la.addView(b);
                                buttons[i] = b;
                                if (status1 == 1){
                                    b.setBackgroundColor(Color.rgb(0,255,0));
                                }

                                b.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (name.equals(gg.getStringData(getApplicationContext(),"tag"))){
                                            v.setBackgroundColor(Color.rgb(0,255,0));
                                            postCL(v.getId());
                                        }
                                        else {
                                            Toast.makeText(DashBoardActivity.this, "โปรดเลือกเฉพาะจุดตรวจของคุณ", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
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
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("idCard", idCard);
                params.put("csd_no", csd_no);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);
    }
}