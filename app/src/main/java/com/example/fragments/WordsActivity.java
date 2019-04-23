package com.example.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragments.m_Realm.RealmHelper;
import com.example.fragments.m_Realm.Spacecraft;
import com.example.fragments.m_Realm.SpacecraftWords;
import com.example.fragments.m_Realm.WordsRealmHelper;
import com.example.fragments.m_UI.MyAdapter;
import com.example.fragments.m_UI.WordsAdapter;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;

public class WordsActivity extends AppCompatActivity {
    TextView textView;
    Realm realm;
    Button back,start;
    public ArrayList<SpacecraftWords> spacecraftWords;//empty
    public ArrayList<String> value;
    private ArrayList<String> transete;
    RealmChangeListener realmChangeListener;
    ArrayList<SpacecraftWords> values;
    ArrayList<String> spacecrafts;
    WordsAdapter adapter;
    RecyclerView rv;
    EditText wordEditTxt,trsEdit,translateEdit;
    private static final String TAG="WordsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        Log.d(TAG, "onCreate: started.");
        FloatingActionButton fab=findViewById(R.id.button_add_words);
        getIncomingIntent();

        rv=findViewById(R.id.recycler_view_words);
        back=findViewById(R.id.btn_back);
        start=findViewById(R.id.start_words);
        //Button Back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WordsActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        //Buu
        rv.setLayoutManager(new LinearLayoutManager(this));
        final String position=getIntent().getStringExtra("id");
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(position)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        realm=Realm.getInstance(realmConfiguration);

        final WordsRealmHelper helper=new WordsRealmHelper(realm);
        helper.retrieveDB();

        adapter=new WordsAdapter(this,helper.refresh());
        rv.setAdapter(adapter);
        spacecraftWords=helper.refresh();

        realmChangeListener=new RealmChangeListener() {
            @Override
            public void onChange(Object o) {
                adapter=new WordsAdapter(WordsActivity.this,helper.refresh());
                rv.setAdapter(adapter);
            }
        };
         realm.addChangeListener(realmChangeListener);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInputDialog();
            }
        });
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



        //Button Start
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value.size()<=6) {
                    Toast.makeText(WordsActivity.this, "You should enter more then 6 items", Toast.LENGTH_SHORT).show();
                    helper.retrieveDB();
                }
                else {
                    Intent intent = new Intent(WordsActivity.this, TestActivity.class);
                    intent.putExtra("subject_id", position);
                    startActivity(intent);
                }
            }
        });

    }
    private void displayInputDialog(){
        Dialog d=new Dialog(this);
        d.setTitle("Save to realm");
        d.setContentView(R.layout.words_dialog);

        wordEditTxt=d.findViewById(R.id.wordEditText);
        trsEdit=d.findViewById(R.id.transcrEditText);
        translateEdit=d.findViewById(R.id.translateEditText);
        Button saveBtnWord=d.findViewById(R.id.create_word);
        saveBtnWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = wordEditTxt.getText().toString();
                String trs = trsEdit.getText().toString();
                String translate = translateEdit.getText().toString();
                if (word != null && word.length() > 0) {
                    SpacecraftWords swo = new SpacecraftWords();

                    swo.setWord(word);
                    swo.setTranscription(trs);
                    swo.setTranslate(translate);


                    WordsRealmHelper helper = new WordsRealmHelper(realm);
                    if (helper.save(swo)) {
                        wordEditTxt.setText("");
                        trsEdit.setText("");
                        translateEdit.setText("");
                    } else{
                        Toast.makeText(WordsActivity.this, "Invalid Data", Toast.LENGTH_SHORT).show();
                }
            }else{
                    Toast.makeText(WordsActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                }
               // helper.save(swo);
                //nameEditTxt.setText("");

              //  spacecrafts=helper.retrieve();
               // adapter=new MyAdapter(WordsActivity.this,spacecrafts);
                //rv.setAdapter(adapter);
            }
        });
        d.show();
    }
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents");
        if(getIntent().hasExtra("group_name")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");
            String title=getIntent().getStringExtra("group_name");
            setTitle(title);
        }
    }
    private void setTitle(String name){
        Log.d(TAG, "setTitle: setting name of group");
        textView=findViewById(R.id.textView);
        textView.setText(name);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        realm.removeChangeListener(realmChangeListener);
        realm.close();
    }
}