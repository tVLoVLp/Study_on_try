package com.example.fragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseTest extends AppCompatActivity {
    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_test);
        btn1=findViewById(R.id.word_translate);
        btn2=findViewById(R.id.translate_word);
        final String position=getIntent().getStringExtra("subject_id_ch");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChooseTest.this,TestActivity.class);
                intent.putExtra("subject_id",position);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChooseTest.this,TranslateTestActivity.class);
                startActivity(intent);
            }
        });

    }
}
