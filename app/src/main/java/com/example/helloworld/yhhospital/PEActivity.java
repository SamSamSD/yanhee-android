package com.example.helloworld.yhhospital;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class PEActivity extends AppCompatActivity {
    CheckBox ch10,ch11,ch20,ch21,ch30,ch31,ch40,ch41,ch50,ch51,ch60,ch61,ch70,ch71,ch80,ch81,ch90,ch91,ch100,ch101,ch110,ch111;
    Button btn;
    BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pe);

        ch10 = findViewById(R.id.checkBox2);
        ch11 = findViewById(R.id.checkBox5);
        ch20 = findViewById(R.id.checkBox3);
        ch21 = findViewById(R.id.checkBox6);
        ch30 = findViewById(R.id.checkBox7);
        ch31 = findViewById(R.id.checkBox4);
        ch40 = findViewById(R.id.checkBox9);
        ch41 = findViewById(R.id.checkBox);
        ch50 = findViewById(R.id.checkBox10);
        ch51 = findViewById(R.id.checkBox11);
        ch60 = findViewById(R.id.checkBox13);
        ch61 = findViewById(R.id.checkBox12);
        ch70 = findViewById(R.id.checkBox15);
        ch71 = findViewById(R.id.checkBox8);
        ch80 = findViewById(R.id.checkBox17);
        ch81 = findViewById(R.id.checkBox14);
        ch90 = findViewById(R.id.checkBox19);
        ch91 = findViewById(R.id.checkBox16);
        ch100 = findViewById(R.id.checkBox18);
        ch101 = findViewById(R.id.checkBox20);
        //btn = findViewById(R.id.);
        nav = findViewById(R.id.bottom_nav_view);
        nav.setSelectedItemId(R.id.item_4);
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.item_0:
                        Intent inform0 = new Intent(PEActivity.this, DashBoardActivity.class);
                        startActivity(inform0);
                        return true;
                    case R.id.item_4:
                        return true;
                    case R.id.item_2:
                        Intent inform2 = new Intent(PEActivity.this, PersonalActivity.class);
                        startActivity(inform2);
                        return true;
                    case R.id.item_3:
                        Intent inform3 = new Intent(PEActivity.this, FamilyActivity.class);
                        startActivity(inform3);
                        return true;
                    case R.id.item_1:
                        Intent inform4 = new Intent(PEActivity.this, InformationActivity.class);
                        startActivity(inform4);
                        return true;
                }
                return false;
            }
        });

    }
}