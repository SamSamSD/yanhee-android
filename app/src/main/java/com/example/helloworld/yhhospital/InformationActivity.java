package com.example.helloworld.yhhospital;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class InformationActivity extends AppCompatActivity {
    final String name = "keb";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    TextView textWeight,textHeight,textBp,textBp2;
    EditText edittextWeight,edittextHeight,edittextBp,edittextBp2;
    Button btn_submit_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        pref = getSharedPreferences(name, Context.MODE_PRIVATE);
        final String csd_no = pref.getString("csd_no","");
        final String emp_id = pref.getString("emp_id","");

        textWeight = findViewById(R.id.textWeight);
        textHeight = findViewById(R.id.textHeight);
        textBp = findViewById(R.id.textBp);
        textBp2 = findViewById(R.id.textBp2);
        edittextWeight = findViewById(R.id.edittextWeight);
        edittextHeight = findViewById(R.id.edittextHeight);
        edittextBp = findViewById(R.id.edittextBp);
        edittextBp2 = findViewById(R.id.edittextBp2);
        btn_submit_info = findViewById(R.id.btn_submit_info);
        edittextBp2.setFocusable(false);

        btn_submit_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postInfo(emp_id, csd_no);
            }
        });

    }
    public void postInfo(final String emp_id,final String csd_no) {
        String checkIdUrl = MainActivity.url+"app_info.php";

        StringRequest postRequest = new StringRequest(Request.Method.POST, checkIdUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("asd",response);

                        Intent inform = new Intent(InformationActivity.this, DashBoardActivity.class);
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
                params.put("weight",edittextWeight.getText().toString());
                params.put("height",edittextHeight.getText().toString());
                params.put("Bp",edittextBp.getText().toString());
                params.put("Bp2",edittextBp2.getText().toString());
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
