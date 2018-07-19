package com.example.helloworld.yhhospital;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import java.util.Objects;

public class FragmentTwo extends Fragment{

    private TextView x1, x2, x3, x4, x5;
    SharePreference gg = new SharePreference();
    private Button cb10, cb11, cb20, cb21, cb30, cb31, cb40, cb41, cb50, cb51;
    ProgressDialog dialog;
    Button submit_pe;
    BottomNavigationView nav;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.la_2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dialog = ProgressDialog.show(
                this.getActivity(),
                null,
                "Loading...",
                false,true
        );
        x1 = view.findViewById(R.id.t22);
        x2 = view.findViewById(R.id.t23);
        x3 = view.findViewById(R.id.t24);
        x4 = view.findViewById(R.id.t25);
        x5 = view.findViewById(R.id.t26);
        cb10 = view.findViewById(R.id.btn31);
        cb11 = view.findViewById(R.id.btn32);
        cb20 = view.findViewById(R.id.btn33);
        cb21 = view.findViewById(R.id.btn34);
        cb30 = view.findViewById(R.id.btn35);
        cb31 = view.findViewById(R.id.btn36);
        cb40 = view.findViewById(R.id.btn37);
        cb41 = view.findViewById(R.id.btn38);
        cb50 = view.findViewById(R.id.btn39);
        cb51 = view.findViewById(R.id.btn310);
        submit_pe = view.findViewById(R.id.btn_submit_pe);

        final String csd_no = gg.getStringData(this.getActivity(), "csd_no");
        final String emp_id = gg.getStringData(this.getActivity(), "idCard");
        getPE(emp_id,csd_no);
        submit_pe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postPe();
            }
        });

        handleButton();
    }

    public void handleButton() {

        cb10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg.setStringData(getActivity(),"pe7","1");
                cb10.setBackgroundColor(Color.rgb(255, 77, 77));
                cb11.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg.setStringData(getActivity(),"pe7","0");
                cb11.setBackgroundColor(Color.rgb(0, 255, 153));
                cb11.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg.setStringData(getActivity(),"pe8","1");
                cb20.setBackgroundColor(Color.rgb(255, 77, 77));
                cb21.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg.setStringData(getActivity(),"pe8","0");
                cb21.setBackgroundColor(Color.rgb(0, 255, 153));
                cb20.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg.setStringData(getActivity(),"pe9","1");
                cb30.setBackgroundColor(Color.rgb(255, 77, 77));
                cb31.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg.setStringData(getActivity(),"pe9","0");
                cb31.setBackgroundColor(Color.rgb(0, 255, 153));
                cb30.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg.setStringData(getActivity(),"pe10","1");
                cb40.setBackgroundColor(Color.rgb(255, 77, 77));
                cb41.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg.setStringData(getActivity(),"pe10","0");
                cb41.setBackgroundColor(Color.rgb(0, 255, 153));
                cb40.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg.setStringData(getActivity(),"pe11","1");
                cb50.setBackgroundColor(Color.rgb(255, 77, 77));
                cb51.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg.setStringData(getActivity(),"pe11","0");
                cb51.setBackgroundColor(Color.rgb(0, 255, 153));
                cb50.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

    }

    public void getPE(final String emp_id,final String csd_no) {
        String checkIdUrl = MainActivity.url+"app_get_pe.php";

        StringRequest postRequest = new StringRequest(Request.Method.POST, checkIdUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dialog.dismiss();
                        try {
                            JSONArray res = new JSONArray(response);
                            x1.setText(res.getJSONObject(6).getString("name"));
                            x2.setText(res.getJSONObject(7).getString("name"));
                            x3.setText(res.getJSONObject(8).getString("name"));
                            x4.setText(res.getJSONObject(9).getString("name"));
                            x5.setText(res.getJSONObject(10).getString("name"));

                            if(res.getJSONObject(6).getString("status").equals("0")) {
                                cb11.setBackgroundColor(Color.rgb(0,255,153));
                            }else if(res.getJSONObject(6).getString("status").equals("1")) {
                                cb10.setBackgroundColor(Color.rgb(255, 77, 77));
                            }

                            if(res.getJSONObject(7).getString("status").equals("0")) {
                                cb21.setBackgroundColor(Color.rgb(0,255,153));
                            }else if(res.getJSONObject(7).getString("status").equals("1")) {
                                cb20.setBackgroundColor(Color.rgb(255, 77, 77));
                            }

                            if(res.getJSONObject(8).getString("status").equals("0")) {
                                cb31.setBackgroundColor(Color.rgb(0,255,153));
                            }else if(res.getJSONObject(8).getString("status").equals("1")) {
                                cb30.setBackgroundColor(Color.rgb(255, 77, 77));
                            }

                            if(res.getJSONObject(9).getString("status").equals("0")) {
                                cb41.setBackgroundColor(Color.rgb(0,255,153));
                            }else if(res.getJSONObject(9).getString("status").equals("1")) {
                                cb40.setBackgroundColor(Color.rgb(255, 77, 77));
                            }

                            if(res.getJSONObject(10).getString("status").equals("0")) {
                                cb51.setBackgroundColor(Color.rgb(0,255,153));
                            }else if(res.getJSONObject(10).getString("status").equals("1")) {
                                cb50.setBackgroundColor(Color.rgb(255, 77, 77));
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
        Volley.newRequestQueue(Objects.requireNonNull(this.getActivity())).add(postRequest);
    }

    public void postPe() {
        String checkIdUrl = MainActivity.url+"app_pe.php";
        final String emp_id = gg.getStringData(getActivity(),"idCard");
        final String csd_no = gg.getStringData(getActivity(),"csd_no");

        StringRequest postRequest = new StringRequest(Request.Method.POST, checkIdUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent inform = new Intent(FragmentTwo.this.getActivity(), DashBoardActivity.class);
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
                params.put("pe1", gg.getStringData(getActivity(),"pe1"));
                params.put("pe2", gg.getStringData(getActivity(),"pe2"));
                params.put("pe3", gg.getStringData(getActivity(),"pe3"));
                params.put("pe4", gg.getStringData(getActivity(),"pe4"));
                params.put("pe5", gg.getStringData(getActivity(),"pe5"));
                params.put("pe6", gg.getStringData(getActivity(),"pe6"));
                params.put("pe7", gg.getStringData(getActivity(),"pe7"));
                params.put("pe8", gg.getStringData(getActivity(),"pe8"));
                params.put("pe9", gg.getStringData(getActivity(),"pe9"));
                params.put("pe10", gg.getStringData(getActivity(),"pe10"));
                params.put("pe11", gg.getStringData(getActivity(),"pe11"));
                params.put("user",gg.getStringData(getActivity(),"user"));
                Log.i("hhh",params.toString());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String,String> params = new HashMap<>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        Volley.newRequestQueue(getActivity()).add(postRequest);
    }
}
