package com.example.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragments.m_Realm.SpacecraftWords;
import com.example.fragments.m_Realm.WordsRealmHelper;

import java.util.ArrayList;
import java.util.Arrays;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TestActivity extends AppCompatActivity /*implements View.OnClickListener*/ {

    private static int RESULT = 0 ;
    private Button answer_one,answer_two,answer_three,next;
    private TextView right_word,result;
    public ArrayList<SpacecraftWords> spacecraftWords;//empty
    public ArrayList<String> value;
    private ArrayList<String> transete;
    private String rightWord;
    private String result_bal;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Test");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),WordsActivity.class));
                finish();
            }
        });

        String subject=getIntent().getStringExtra("subject_id");

        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(subject)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        realm=Realm.getInstance(realmConfiguration);

        final WordsRealmHelper helper=new WordsRealmHelper(realm);
        helper.retrieveDB();
        spacecraftWords=helper.refresh();


        answer_one = findViewById(R.id.answer_one);
        answer_two = findViewById(R.id.answer_two);
        answer_three = findViewById(R.id.answer_three);
        right_word = findViewById(R.id.right_word);
        result = findViewById(R.id.test_result);
        next = findViewById(R.id.next);
        value=new ArrayList<>();
        transete=new ArrayList<>();
        for(int i=0;i<spacecraftWords.size();i++) {
            SpacecraftWords sw = spacecraftWords.get(i);
            String word =sw.getWord();
            Log.d("Word",word);
            value.add(i,word);
            String translate=sw.getTranslate();
            Log.d("Translate",translate);
            transete.add(translate);
        }


       /* String position=getIntent().getStringExtra("subject_id");
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(position)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        realm=Realm.getInstance(realmConfiguration);*/


            final Vocabluari group_one = new Vocabluari();


            //value = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.word_value)));
            //transete = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.word_translete)));

            group_one.work(value, transete, right_word, answer_one, answer_two, answer_three);


            //rightWord = group_one.work(value, transete, right_word, answer_one, answer_two, answer_three);


            next.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    answer_one.setBackground(getResources().getDrawable(R.drawable.rounded_button));
                    answer_two.setBackground(getResources().getDrawable(R.drawable.rounded_button));
                    answer_three.setBackground(getResources().getDrawable(R.drawable.rounded_button));

                    Vocabluari next_vob = new Vocabluari();
                    //next_vob.work(value, transete, right_word, answer_one, answer_two, answer_three);

                    result_bal = group_one.work(value, transete, right_word, answer_one, answer_two, answer_three);
                    result.setText(result_bal);


                }
            });
        }





}



