package com.example.helloworld.yhhospital;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    TextView textView1;
    final String name = "keb";
    Button btn_dash1,btn_dash2,btn_dash3,btn_dash4;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Bundle bundle = getIntent().getExtras();
        textView1 = findViewById(R.id.show_employee);

        String idCard = bundle.getString("idCard");
        String csNo = bundle.getString("csNo");
        postDashBoard(idCard,csNo);

        pref = getSharedPreferences(name, Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.putString("emp_id",idCard);
        editor.commit();
        btn_dash1=(Button)findViewById(R.id.btn_dash1);
        btn_dash1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inform = new Intent(DashBoardActivity.this, InformationActivity.class);
                startActivity(inform);
            }
        });
        btn_dash2=(Button)findViewById(R.id.btn_dash2);
        btn_dash2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inform = new Intent(DashBoardActivity.this, PersonalActivity.class);
                startActivity(inform);
            }
        });
        btn_dash3=(Button)findViewById(R.id.btn_dash3);
        btn_dash3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inform = new Intent(DashBoardActivity.this, FamilyActivity.class);
                startActivity(inform);
            }
        });
        btn_dash4=(Button)findViewById(R.id.btn_dash4);
        btn_dash4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent inform = new Intent(DashBoardActivity.this, PEActivity.class);
//                startActivity(inform);
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
                        textView1.setText(response2[0]+" "+response2[1]+" "+response2[2]+" "
                                +response2[3]+" "+response2[4]+" "+response2[5]+" "+response2[6]
                                +" "+response2[7]+response2[8]+" "+response2[9]+" "+response2[10]
                                +" "+response2[11]);
                        pref = getSharedPreferences(name, Context.MODE_PRIVATE);
                        editor = pref.edit();
                        editor.putString("csd_no",response2[12]);
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
