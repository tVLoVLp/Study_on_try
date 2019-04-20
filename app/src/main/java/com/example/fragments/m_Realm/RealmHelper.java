package com.example.fragments.m_Realm;

import android.util.Log;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

public class RealmHelper {
    Realm realm;
    Boolean saved=null;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }
    public Boolean save(final Spacecraft spacecraft){
        if(spacecraft==null){
            saved=false;
        }else {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    try {
                        Spacecraft s=realm.copyToRealm(spacecraft);
                        saved=true;
                    }catch (RealmException e){
                        e.printStackTrace();
                        saved=false;
                    }
                }
            });
        }
        return saved;
    }
    public ArrayList<String> retrieve(){
    ArrayList<String> spacecraftNames=new ArrayList<>();
        RealmResults<Spacecraft>spacecrafts=realm.where(Spacecraft.class).findAll();

        for (Spacecraft s:spacecrafts)
        {
            spacecraftNames.add(s.getName());
        }
        return spacecraftNames;
    }

}
