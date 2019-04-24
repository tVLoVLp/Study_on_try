package com.example.fragments.m_UI;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.fragments.R;
import com.example.fragments.WordsActivity;

import java.util.ArrayList;

import io.realm.Realm;

import static android.content.ContentValues.TAG;

public class WordAdapter extends RecyclerView.Adapter<WordViewHolder> {
    public Context context;
    Realm realm;
    public ArrayList<String> spacecrafts;

    public WordAdapter(Context context, ArrayList<String> spacecrafts) {
        this.context = context;
        this.spacecrafts = spacecrafts;
    }


    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.word_fr_rv_item,viewGroup,false);
        return new WordViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder wordViewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called.");
        wordViewHolder.wordTxt.setText(spacecrafts.get(i));
    }

    @Override
    public int getItemCount() {
        return spacecrafts.size();
    }



}