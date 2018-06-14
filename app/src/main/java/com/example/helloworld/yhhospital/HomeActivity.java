package com.example.helloworld.yhhospital;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Constraints;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class HomeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    Button btn;
    String checkedNo = "";
    LinearLayout linearLayout;
    private ZXingScannerView zXingScannerView;
    boolean status = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn = findViewById(R.id.scan);
        linearLayout = findViewById(R.id.linearLayout2);
        getCheckService();
    }


    @Override
    public void handleResult(com.google.zxing.Result result) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("hello");
//        builder.setMessage(result.getText());
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();

        //Intent home = new Intent(home.this, HomeActivity.class);
        //startActivity(home);
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
//0 1 2  //0 2 4
    public void getCheckService() {
        Log.i("xxx", "getCheckService");
        String url1 = MainActivity.url+"app_show_check_service.php";
        StringRequest postRequest = new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        final String[] places = response.split(" ");
                        TextView tv = new TextView(getApplicationContext());
                        tv.setTypeface(null, Typeface.BOLD);
                        tv.setTextSize(22);
                        tv.setText("เลือกสถานที่ตรวจ");
                        linearLayout.addView(tv);
                        final CheckBox[] buttons = new CheckBox[places.length/2];
                        for(int i = 0; i<places.length/2; i++){
                            CheckBox cb = new CheckBox(getApplicationContext());
                            cb.setId(i);
                            cb.setText(places[i*2+1]);
                            cb.setTextSize(18);
                            cb.setBottom(18);
                            linearLayout.addView(cb);
                            buttons[i] = cb;
                        }
                        for(int i = 0; i<places.length/2; i++){
                            buttons[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    if(isChecked == true) {
                                        for(int j=0; j<places.length/2; j++) {
                                            buttons[j].setChecked(false);
                                        }
                                        buttonView.setChecked(true);
                                        int id = buttonView.getId();
                                        checkedNo = places[id*2];
                                        Log.i("number", checkedNo);
                                    }
                                }
                            });
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
                        if(response.equals("true")) {
                            Intent home = new Intent(HomeActivity.this, DashBoardActivity.class);
                            home.putExtra("idCard", idCard);
                            home.putExtra("csNo", checkedNo);
                            startActivity(home);
                        } else{
                            Toast.makeText(HomeActivity.this, "อย่าสแกนเล่นสิจ๊ะ", Toast.LENGTH_SHORT).show();
                            Intent home = new Intent(HomeActivity.this, HomeActivity.class);
                            startActivity(home);
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
                params.put("csNo", checkedNo);
                Log.i("xxx", params.toString());
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
