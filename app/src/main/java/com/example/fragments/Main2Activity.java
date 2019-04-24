package com.example.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fragments.m_Realm.RealmHelper;
import com.example.fragments.m_Realm.Spacecraft;
import com.example.fragments.m_UI.MyAdapter;
import com.example.fragments.m_UI.MyViewHolder;


import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

import static com.example.fragments.m_UI.MyViewHolder.*;

public class Main2Activity extends AppCompatActivity implements MyViewHolder.OnItemClickListener {
    Realm realm;
    ArrayList<String> spacecrafts;
    MyAdapter adapter;
    RecyclerView rv;
    EditText nameEditTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Groups");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

        FloatingActionButton fab=findViewById(R.id.button_add_group);

        rv=findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
//String position=getIntent().getStringExtra("id");
        //String subject =getIntent().getStringExtra("subject_name");
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("English")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        realm=Realm.getInstance(realmConfiguration);

        RealmHelper helper=new RealmHelper(realm);
        spacecrafts=helper.retrieve();
        spacecrafts.add(0,"Essential words");
        spacecrafts.add(1,"Elementary words");
        spacecrafts.add(2,"intermediate words");

        adapter=new MyAdapter(this,spacecrafts);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(Main2Activity.this);

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
        d.setContentView(R.layout.input_dialog);

        nameEditTxt=d.findViewById(R.id.nameEditText);
        Button saveBtn=d.findViewById(R.id.create);
         saveBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Spacecraft s=new Spacecraft();
                 s.setName(nameEditTxt.getText().toString());

                 if(nameEditTxt!=null && nameEditTxt.length()>0){
                     RealmHelper helper=new RealmHelper(realm);
                     if(helper.save(s)){
                         nameEditTxt.setText("");
                         spacecrafts=helper.retrieve();
                         adapter=new MyAdapter(Main2Activity.this,spacecrafts);
                         rv.setAdapter(adapter);

                     }else{
                         Toast.makeText(Main2Activity.this,"Name must be unique",Toast.LENGTH_SHORT).show();
                     }
                 }else {
                     Toast.makeText(Main2Activity.this,"Name cannot be empty ",Toast.LENGTH_SHORT).show();
                 }

                 Intent userscreen=new Intent(Main2Activity.this, Main2Activity.class);
                 startActivity(userscreen);
             }
         });
         d.show();
    }


    @Override
    public void onChangeClick(int position) {
        //Toast.makeText(this, "Whatever click at position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(final int position) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Spacecraft>spacecrafts=realm.where(Spacecraft.class).equalTo("id",position).findAll();
                spacecrafts.deleteFirstFromRealm();
            }
        });
    }
}
