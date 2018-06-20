package com.example.helloworld.yhhospital;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    TextView textView1, textView2, textView3;
    LinearLayout la;
    final String name = "keb";
    SharedPreferences pref;
    SharePreference gg = new SharePreference(this);
    SharedPreferences.Editor editor;
    BottomNavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        textView1 = findViewById(R.id.show_employee);
        textView2 = findViewById(R.id.show_employee2);
        textView3 = findViewById(R.id.show_employee3);
        nav = findViewById(R.id.bottom_nav_view);
        la = (LinearLayout) findViewById(R.id.all_buttons);

        pref = getSharedPreferences(name, Context.MODE_PRIVATE);
        editor = pref.edit();

        final String idCard = pref.getString("idCard", "");
        final String csNo = pref.getString("csNo", "");
        //postDashBoard จะได้ค่า csd_no มาแล้วเก็บลง sharePref
        postDashBoard(idCard, csNo);
        getButton();

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
//                        Log.i("back","back");
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
                        String[] response2 = response.split(" ");
                        textView1.setText(String.valueOf(response2[0] + " " + response2[1] + " " + response2[2] + " " + response2[3] + " " + response2[4]));
                        textView2.setText(String.valueOf(response2[5] + " " + response2[6] + " " + response2[7] + response2[8] + " " + response2[9]));
                        textView3.setText(String.valueOf(response2[10] + " " + response2[11] + " " + response2[12]));
                        pref = getSharedPreferences(name, Context.MODE_PRIVATE);
                        editor = pref.edit();
                        editor.putString("csd_no", response2[13]);
                        editor.commit();
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

//    public void postButtonDashBoard(final String idCard) {
//        String checkIdUrl = MainActivity.url+"app_check_btn_dashboard.php";
//        pref = getSharedPreferences(name, Context.MODE_PRIVATE);
//        editor = pref.edit();
//
//        final String csd_no_get = pref.getString("csd_no","");
//        StringRequest postRequest = new StringRequest(Request.Method.POST, checkIdUrl,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        if(response.equals("false")) {
//                            Log.i("res","false");
//                        }else {
//                            String[] res = response.split(" ");
//                            if(res[2].equals("1")) {
//                                b1.setBackgroundColor(Color.rgb(0,255,0));
//                            }
//                            if(res[3].equals("1")) {
//                                b2.setBackgroundColor(Color.rgb(0,255,0));
//                            }
//                            if(res[4].equals("1")) {
//                                b3.setBackgroundColor(Color.rgb(0,255,0));
//                            }
//                            if(res[5].equals("1")) {
//                                b4.setBackgroundColor(Color.rgb(0,255,0));
//                            }
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError err) {
//                        Log.d("Error.Response", err.getMessage());
//                    }
//                }
//        ) {
//
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//                params.put("idCard", idCard);
//                params.put("csd_no", csd_no_get);
//                return params;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() {
//                Map<String,String> params = new HashMap<>();
//                params.put("Content-Type","application/x-www-form-urlencoded");
//                return params;
//            }
//        };
//        Volley.newRequestQueue(this).add(postRequest);
//    }

    public void postCL(final String idCard, final String btn) {
        String checkIdUrl = MainActivity.url + "app_check_list.php";
        pref = getSharedPreferences(name, Context.MODE_PRIVATE);
        editor = pref.edit();
        final String csd_no_get = pref.getString("csd_no", "");

        StringRequest postRequest = new StringRequest(Request.Method.POST, checkIdUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        if(response.equals("1")) {
//                            b1.setBackgroundColor(Color.rgb(0,255,0));
//                        }
//                        else if(response.equals("2")) {
//                            b2.setBackgroundColor(Color.rgb(0,255,0));
//                        }
//                        else if(response.equals("3")) {
//                            b3.setBackgroundColor(Color.rgb(0,255,0));
//                        }
//                        else if(response.equals("4")) {
//                            b4.setBackgroundColor(Color.rgb(0,255,0));
//                        }
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
                params.put("csd_no", csd_no_get);
                params.put("case", btn);
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

        pref = getSharedPreferences(name, Context.MODE_PRIVATE);
        final String idCard = gg.getStringData(this, "idCard");
        final String csd_no = gg.getStringData(this,"csd_no");
//        final String idCard = pref.getString("idCard", "");
//        final String csd_no = pref.getString("csd_no", "");

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray res = new JSONArray(response);
                            final Button[] buttons = new Button[res.length()];
                            for(int i=0; i<res.length(); i++) {
                                JSONObject obj = res.getJSONObject(i);
                                String name = obj.getString("name");

                                Button b = new Button(getApplicationContext());
                                b.setId(i);
                                b.setText(name);
                                la.addView(b);
                                buttons[i] = b;

//                                b.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//
//                                    }
//                                });
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