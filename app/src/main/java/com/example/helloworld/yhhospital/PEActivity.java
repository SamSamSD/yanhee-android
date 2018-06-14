package com.example.helloworld.yhhospital;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class PEActivity extends AppCompatActivity {
    TextView txt_head,txt_doc,txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10,txt11;
    CheckBox ch10,ch11,ch20,ch21,ch30,ch31,ch40,ch41,ch50,ch51,ch60,ch61,ch70,ch71,ch80,ch81,ch90,ch91,ch100,ch101,ch110,ch111;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pe);

        txt_head = findViewById(R.id.textView11);
        txt_doc =findViewById(R.id.textView19);
        txt1 = findViewById(R.id.textView20);
        txt2 = findViewById(R.id.textView21);
        txt3 = findViewById(R.id.textView27);
        txt4 = findViewById(R.id.textView22);
        txt5 = findViewById(R.id.textView24);
        txt6 = findViewById(R.id.textView25);
        txt7 = findViewById(R.id.textView26);
        txt8 = findViewById(R.id.textView28);
        txt9 = findViewById(R.id.textView29);
        txt10 = findViewById(R.id.textView30);
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

    }
}