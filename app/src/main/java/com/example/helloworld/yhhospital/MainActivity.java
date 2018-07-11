package com.example.helloworld.yhhospital;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class MainActivity extends AppCompatActivity {
    public static String url = "http://172.22.1.53/YH_project/androidPHP2/";
//    public static String url = "http://192.168.43.142/YH_project/androidPHP2/";

    private JSONObject obj;
    EditText editPassword, editName;
    Button btnSignIn;
    SharePreference gg = new SharePreference();

    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        editName=(EditText)findViewById(R.id.editName);
        editPassword=(EditText)findViewById(R.id.editPassword);
        btnSignIn=(Button)findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postLogin();
            }
        });
    }

    public void postLogin() {
        String x = url+"app_login.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, x,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("test",response);
                        try {
                            JSONArray res = new JSONArray(response);
                            obj = res.getJSONObject(0);
                            String status = obj.getString("status");
                            String user = obj.getString("user");
                            String tag = obj.getString("tag");
                            if (status.equals("true")){
                                gg.setStringData(getApplicationContext(),"user",user);
                                gg.setStringData(getApplicationContext(),"tag",tag);
                                Intent home = new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(home);
                            }
                            else{
                                Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
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
                params.put("username", editName.getText().toString());
                params.put("password", editPassword.getText().toString());
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