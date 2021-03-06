package com.example.helloworld.yhhospital;

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
import android.widget.EditText;
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

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class InformationActivity extends AppCompatActivity {
    final String name = "keb";
    private JSONObject obj;
    SharePreference gg = new SharePreference();
    TextView textWeight,textHeight,textBp,textBp2;
    EditText edittextWeight,edittextHeight,edittextBp,edittextBp2;
    Button btn_submit_info;
    ProgressDialog dialog;
    BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("ข้อมูลเบื้องต้น");
        setContentView(R.layout.activity_information);

        final String csd_no = gg.getStringData(getApplicationContext(), "csd_no");
        final String emp_id = gg.getStringData(getApplicationContext(),"idCard");
        dialog = ProgressDialog.show(InformationActivity.this, null,"Loading...", false,true);
        textWeight = findViewById(R.id.textWeight);
        textHeight = findViewById(R.id.textHeight);
        textBp = findViewById(R.id.textBp);
        textBp2 = findViewById(R.id.textBp2);
        edittextWeight = findViewById(R.id.edittextWeight);
        edittextHeight = findViewById(R.id.edittextHeight);
        edittextBp = findViewById(R.id.edittextBp);
        edittextBp2 = findViewById(R.id.edittextBp2);
        btn_submit_info = findViewById(R.id.btn_submit_info);
//        edittextBp2.setFocusable(false);
        getInfo(emp_id, csd_no);

        btn_submit_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postInfo(emp_id, csd_no);
            }
        });
        nav = findViewById(R.id.bottom_nav_view);
        nav.setSelectedItemId(R.id.item_1);
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_0:
                        Intent inform0 = new Intent(InformationActivity.this, DashBoardActivity.class);
                        startActivity(inform0);
                        return true;
                    case R.id.item_1:
                        return true;
                    case R.id.item_2:
                        Intent inform2 = new Intent(InformationActivity.this, PersonalActivity.class);
                        startActivity(inform2);
                        return true;
                    case R.id.item_3:
                        Intent inform3 = new Intent(InformationActivity.this, FamilyActivity.class);
                        startActivity(inform3);
                        return true;
                    case R.id.item_4:
                        Intent inform4 = new Intent(InformationActivity.this, PEActivity.class);
                        startActivity(inform4);
                        return true;
                }
                return false;
            }
        });
    }
    public void getInfo(final String emp_id,final String csd_no) {
        String InfoUrl = MainActivity.url+"app_get_info.php";

        StringRequest postRequest = new StringRequest(Request.Method.POST, InfoUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dialog.dismiss();
                        try {
                            JSONArray res = new JSONArray(response);
                            obj = res.getJSONObject(0);
                            String weight = obj.getString(String.valueOf(textWeight.getText()));
                            String height = obj.getString(String.valueOf(textHeight.getText()));
                            String bp = obj.getString(String.valueOf(textBp.getText()));
                            String bp2 = obj.getString(String.valueOf(textBp2.getText()));
                            edittextWeight.setText(weight);
                            edittextHeight.setText(height);
                            edittextBp.setText(bp);
                            edittextBp2.setText(bp2);
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

    public void postInfo(final String emp_id,final String csd_no) {
        String checkIdUrl = MainActivity.url+"app_info.php";

        StringRequest postRequest = new StringRequest(Request.Method.POST, checkIdUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
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
