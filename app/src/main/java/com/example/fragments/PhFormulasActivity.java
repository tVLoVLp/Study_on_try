package com.example.fragments;

import android.app.Dialog;
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

public class PhFormulasActivity extends AppCompatActivity {
    TextView textView;
    Realm realm;
    RealmChangeListener realmChangeListener;
    ArrayList<String> spacecrafts;
    WordsAdapter adapter;
    RecyclerView rv;
    EditText wordEditTxt,trsEdit,translateEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mth_formylu);
        FloatingActionButton fab=findViewById(R.id.button_add_mthform);
        getIncomingIntent();
        rv=findViewById(R.id.recycler_view_mthform);
        rv.setLayoutManager(new LinearLayoutManager(this));
        String position=getIntent().getStringExtra("id");
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(position+"Physic")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        realm=Realm.getInstance(realmConfiguration);

        final WordsRealmHelper helper=new WordsRealmHelper(realm);
        helper.retrieveDB();

        adapter=new WordsAdapter(this,helper.refresh());
        rv.setAdapter(adapter);

        realmChangeListener=new RealmChangeListener() {
            @Override
            public void onChange(Object o) {
                adapter=new WordsAdapter(PhFormulasActivity.this,helper.refresh());
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
    }
    private void displayInputDialog(){
        Dialog d=new Dialog(this);
        d.setTitle("Save to realm");
        d.setContentView(R.layout.mth_fotmulas_dialog);

        wordEditTxt=d.findViewById(R.id.formulaEditText);
        // trsEdit=d.findViewById(R.id.transcrEditText);
        translateEdit=d.findViewById(R.id.meaningEditText);
        Button saveBtnWord=d.findViewById(R.id.create_formula);
        saveBtnWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = wordEditTxt.getText().toString();
                //String trs = trsEdit.getText().toString();
                String translate = translateEdit.getText().toString();
                if (word != null && word.length() > 0) {
                    SpacecraftWords swo = new SpacecraftWords();

                    swo.setWord(word);
                    // swo.setTranscription(trs);
                    swo.setTranslate(translate);


                    WordsRealmHelper helper = new WordsRealmHelper(realm);
                    if (helper.save(swo)) {
                        wordEditTxt.setText("");
                        //  trsEdit.setText("");
                        translateEdit.setText("");
                    } else{
                        Toast.makeText(PhFormulasActivity.this, "Invalid Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(PhFormulasActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
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
        if(getIntent().hasExtra("ph_form_name")){
            String title=getIntent().getStringExtra("ph_form_name");
            setTitle(title);
        }
    }
    private void setTitle(String name){
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