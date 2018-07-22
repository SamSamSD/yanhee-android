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
import android.view.MenuItem;
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

public class FragmentOne extends Fragment {

    private TextView x1, x2, x3, x4, x5, x6;
    SharePreference gg = new SharePreference();
    private Button cb10, cb11, cb20, cb21, cb30, cb31, cb40, cb41, cb50, cb51, cb60, cb61;
    ProgressDialog dialog;
    BottomNavigationView nav;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.la_1, container, false);


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
        x1 = view.findViewById(R.id.t2);
        x2 = view.findViewById(R.id.t3);
        x3 = view.findViewById(R.id.t4);
        x4 = view.findViewById(R.id.t5);
        x5 = view.findViewById(R.id.t6);
        x6 = view.findViewById(R.id.t7);
        cb10 = view.findViewById(R.id.btn21);
        cb11 = view.findViewById(R.id.btn22);
        cb20 = view.findViewById(R.id.btn23);
        cb21 = view.findViewById(R.id.btn24);
        cb30 = view.findViewById(R.id.btn25);
        cb31 = view.findViewById(R.id.btn26);
        cb40 = view.findViewById(R.id.btn27);
        cb41 = view.findViewById(R.id.btn28);
        cb50 = view.findViewById(R.id.btn29);
        cb51 = view.findViewById(R.id.btn210);
        cb60 = view.findViewById(R.id.btn211);
        cb61 = view.findViewById(R.id.btn212);

        final String csd_no = gg.getStringData(this.getActivity(), "csd_no");
        final String emp_id = gg.getStringData(this.getActivity(), "idCard");
        getPE(emp_id,csd_no);

        handleButton();
    }

    public void handleButton() {

        cb10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg.setStringData(getActivity(),"pe1","1");
                cb10.setBackgroundColor(Color.rgb(255, 77, 77));
                cb11.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg.setStringData(getActivity(),"pe1","0");
                cb11.setBackgroundColor(Color.rgb(0, 255, 153));
                cb10.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg.setStringData(getActivity(),"pe2","1");
                cb20.setBackgroundColor(Color.rgb(255, 77, 77));
                cb21.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg.setStringData(getActivity(),"pe2","0");
                cb21.setBackgroundColor(Color.rgb(0, 255, 153));
                cb20.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg.setStringData(getActivity(),"pe3","1");
                cb30.setBackgroundColor(Color.rgb(255, 77, 77));
                cb31.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg.setStringData(getActivity(),"pe3","0");
                cb31.setBackgroundColor(Color.rgb(0, 255, 153));
                cb30.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg.setStringData(getActivity(),"pe4","1");
                cb40.setBackgroundColor(Color.rgb(255, 77, 77));
                cb41.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg.setStringData(getActivity(),"pe4","0");
                cb41.setBackgroundColor(Color.rgb(0, 255, 153));
                cb40.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg.setStringData(getActivity(),"pe5","1");
                cb50.setBackgroundColor(Color.rgb(255, 77, 77));
                cb51.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg.setStringData(getActivity(),"pe5","0");
                cb51.setBackgroundColor(Color.rgb(0, 255, 153));
                cb50.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg.setStringData(getActivity(),"pe6","1");
                cb60.setBackgroundColor(Color.rgb(255, 77, 77));
                cb61.setBackgroundColor(android.R.drawable.btn_default);
            }
        });

        cb61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gg.setStringData(getActivity(),"pe6","0");
                cb61.setBackgroundColor(Color.rgb(0, 255, 153));
                cb60.setBackgroundColor(android.R.drawable.btn_default);
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
                            x1.setText(res.getJSONObject(0).getString("name"));
                            x2.setText(res.getJSONObject(1).getString("name"));
                            x3.setText(res.getJSONObject(2).getString("name"));
                            x4.setText(res.getJSONObject(3).getString("name"));
                            x5.setText(res.getJSONObject(4).getString("name"));
                            x6.setText(res.getJSONObject(5).getString("name"));

                            if(res.getJSONObject(0).getString("status").equals("0")) {
                                cb11.setBackgroundColor(Color.rgb(0,255,153));
                                gg.setStringData(getActivity(),"pe1","0");
                            }else if(res.getJSONObject(0).getString("status").equals("1")) {
                                cb10.setBackgroundColor(Color.rgb(255, 77, 77));
                                gg.setStringData(getActivity(),"pe1","1");
                            }

                            if(res.getJSONObject(1).getString("status").equals("0")) {
                                cb21.setBackgroundColor(Color.rgb(0,255,153));
                                gg.setStringData(getActivity(),"pe2","0");
                            }else if(res.getJSONObject(1).getString("status").equals("1")) {
                                cb20.setBackgroundColor(Color.rgb(255, 77, 77));
                                gg.setStringData(getActivity(),"pe2","1");
                            }

                            if(res.getJSONObject(2).getString("status").equals("0")) {
                                cb31.setBackgroundColor(Color.rgb(0,255,153));
                                gg.setStringData(getActivity(),"pe3","0");
                            }else if(res.getJSONObject(2).getString("status").equals("1")) {
                                cb30.setBackgroundColor(Color.rgb(255, 77, 77));
                                gg.setStringData(getActivity(),"pe3","1");
                            }

                            if(res.getJSONObject(3).getString("status").equals("0")) {
                                cb41.setBackgroundColor(Color.rgb(0,255,153));
                                gg.setStringData(getActivity(),"pe4","0");
                            }else if(res.getJSONObject(3).getString("status").equals("1")) {
                                cb40.setBackgroundColor(Color.rgb(255, 77, 77));
                                gg.setStringData(getActivity(),"pe4","1");
                            }

                            if(res.getJSONObject(4).getString("status").equals("0")) {
                                cb51.setBackgroundColor(Color.rgb(0,255,153));
                                gg.setStringData(getActivity(),"pe5","0");
                            }else if(res.getJSONObject(4).getString("status").equals("1")) {
                                cb50.setBackgroundColor(Color.rgb(255, 77, 77));
                                gg.setStringData(getActivity(),"pe5","1");
                            }

                            if(res.getJSONObject(5).getString("status").equals("0")) {
                                cb61.setBackgroundColor(Color.rgb(0,255,153));
                                gg.setStringData(getActivity(),"pe6","0");
                            }else if(res.getJSONObject(5).getString("status").equals("1")) {
                                cb60.setBackgroundColor(Color.rgb(255, 77, 77));
                                gg.setStringData(getActivity(),"pe6","1");
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
}
