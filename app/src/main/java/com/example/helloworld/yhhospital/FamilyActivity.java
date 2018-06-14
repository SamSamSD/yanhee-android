package com.example.helloworld.yhhospital;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class FamilyActivity extends AppCompatActivity {
    CheckBox cb10, cb11, cb20, cb21, cb30, cb31, cb40, cb41, cb50, cb51;
    Button submit_family;
    String[] family_checked = new String[]{"0", "0", "0", "0", "0", "0", "0"};
    BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
        cb10 = findViewById(R.id.cb10);
        cb11 = findViewById(R.id.cb11);
        cb20 = findViewById(R.id.cb20);
        cb21 = findViewById(R.id.cb21);
        cb30 = findViewById(R.id.cb30);
        cb31 = findViewById(R.id.cb31);
        cb40 = findViewById(R.id.cb40);
        cb41 = findViewById(R.id.cb41);
        cb50 = findViewById(R.id.cb50);
        cb51 = findViewById(R.id.cb51);
        submit_family = findViewById(R.id.submit_family);


        nav = findViewById(R.id.bottom_nav_view);
        nav.setSelectedItemId(R.id.item_3);

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.item_1:
                        Intent inform2 = new Intent(FamilyActivity.this, InformationActivity.class);
                        startActivity(inform2);
                        return true;
                    case R.id.item_2:
                        Intent inform3 = new Intent(FamilyActivity.this, PersonalActivity.class);
                        startActivity(inform3);
                        return true;
                    case R.id.item_3:
                        return true;
                    case R.id.item_4:
                        Intent inform4 = new Intent(FamilyActivity.this, PEActivity.class);
                        startActivity(inform4);
                        return true;
                }
                return false;
            }
        });
        handleButton();
    }

    public void handleButton() {
        cb10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb11.setChecked(false);
                    cb11.setSelected(false);
                    family_checked[0] = "1";

                }
            }
        });
        cb11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb10.setChecked(false);
                    cb10.setSelected(false);
                    family_checked[0] = "0";
                }
            }
        });
        cb20.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb21.setChecked(false);
                    cb21.setSelected(false);
                    family_checked[1] = "1";
                }
            }
        });
        cb21.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb20.setChecked(false);
                    cb20.setSelected(false);
                    family_checked[1] = "0";
                }
            }
        });
        cb30.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb31.setChecked(false);
                    cb31.setSelected(false);
                    family_checked[2] = "1";
                }
            }
        });
        cb31.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb30.setChecked(false);
                    cb30.setSelected(false);
                    family_checked[2] = "0";
                }
            }
        });
        cb40.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb41.setChecked(false);
                    cb41.setSelected(false);
                    family_checked[3] = "1";
                }
            }
        });
        cb41.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb40.setChecked(false);
                    cb40.setSelected(false);
                    family_checked[3] = "0";
                }
            }
        });
        cb50.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb51.setChecked(false);
                    cb51.setSelected(false);
                    family_checked[4] = "1";
                }
            }
        });
        cb51.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb50.setChecked(false);
                    cb50.setSelected(false);
                    family_checked[4] = "0";
                }
            }
        });
    }
}
