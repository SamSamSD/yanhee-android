package com.example.helloworld.yhhospital;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Constraints;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class HomeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    Button btn;
    SharePreference gg = new SharePreference();
    int checkedNo = 0;
    LinearLayout linearLayout;
    private ZXingScannerView zXingScannerView;
    boolean status = false;
    private JSONObject obj;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.setTitle("สถานที่ตรวจ");
        btn = findViewById(R.id.scan);
        linearLayout = findViewById(R.id.linearLayout2);
        getCheckService();
    }

    @Override
    public void handleResult(com.google.zxing.Result result) {
        String idCard = result.getText();
        postCheckId(idCard);
    }

    public void handleScan(View view) {
        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        status = true;
        zXingScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(status == true) {
            zXingScannerView.stopCamera();
        }
    }

    public void getCheckService() {
        String url1 = MainActivity.url+"app_show_check_service.php";
        TextView tv = new TextView(getApplicationContext());
        tv.setTypeface(null, Typeface.BOLD);
        tv.setTextSize(22);
        tv.setText("เลือกสถานที่ตรวจ");
        linearLayout.addView(tv);

        StringRequest postRequest = new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            final JSONArray res = new JSONArray(response);

                            final CheckBox[] buttons = new CheckBox[res.length()];
                            for (int i=0; i<res.length(); i++) {
                                JSONObject obj = res.getJSONObject(i);
                                String cs_no = obj.getString("cs_no");
                                String name = obj.getString("name");
                                String add = obj.getString("address");
                                int x = Integer.parseInt(cs_no);

                                CheckBox cb = new CheckBox(getApplicationContext());
                                cb.setId(x);

                                cb.setTextColor(Color.BLACK);
                                cb.setText(name+" "+add);
                                cb.setTextSize(18);
                                cb.setBottom(18);
                                linearLayout.addView(cb);
                                buttons[i] = cb;

                            }
                            final int length = res.length();
                            for (int i=0; i<length; i++) {
                                buttons[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                        for (int j=0; j<length; j++) {
//                                            Log.i("cs_no", String.valueOf(buttons[j].getId()));
//                                            Log.i("gg",gg.getStringData(getApplicationContext(),"csNo"));
//                                            if (gg.getStringData(getApplicationContext(), "csNo").equals(String.valueOf(buttons[j].getId()))) {
//                                                Log.i("test","1234");
//                                                buttons[j].setChecked(true);
//                                            }
//                                            else {
//                                                buttons[j].setChecked(false);
//                                            }
//                                            String csNoPref = gg.getStringData(getApplicationContext(), "csNo");
//                                            int test = buttons[j].getId();
                                            buttons[j].setChecked(false);
                                        }
                                        if(isChecked == true) {
                                            checkedNo = buttonView.getId();
                                            gg.setStringData(getApplicationContext(), "csNo", String.valueOf(checkedNo));
                                            buttonView.setChecked(true);
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
            public Map<String, String> getHeaders() {
                Map<String,String> params = new HashMap<>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);
    }

    public void postCheckId(final String idCard) {
        String checkIdUrl = MainActivity.url+"app_check_idcard.php";

        StringRequest postRequest = new StringRequest(Request.Method.POST, checkIdUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray res = new JSONArray(response);
                            obj = res.getJSONObject(0);
                            String status = obj.getString("status");
                            if (status.equals("true")) {
                                Intent home = new Intent(HomeActivity.this, DashBoardActivity.class);
                                startActivity(home);
                            } else {
                                Toast.makeText(HomeActivity.this, "ไม่ถูกต้อง", Toast.LENGTH_SHORT).show();
                                Intent home = new Intent(HomeActivity.this, HomeActivity.class);
                                startActivity(home);
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
                params.put("idCard", idCard);
                params.put("csNo", String.valueOf(checkedNo));
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
