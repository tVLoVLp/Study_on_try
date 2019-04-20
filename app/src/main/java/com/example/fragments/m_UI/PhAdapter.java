package com.example.fragments.m_UI;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fragments.MthFormyluActivity;
import com.example.fragments.PhFormulasActivity;
import com.example.fragments.R;

import java.util.ArrayList;

public class PhAdapter extends  RecyclerView.Adapter<MyViewHolder> {
    public Context context;
    public ArrayList<String> spacecrafts;

    public PhAdapter(Context context, ArrayList<String> spacecrafts) {
        this.context = context;
        this.spacecrafts = spacecrafts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.group_item,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {
        myViewHolder.nameTxt.setText(spacecrafts.get(position));
        myViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id;
                Toast.makeText(context, spacecrafts.get(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, PhFormulasActivity.class);
                intent.putExtra("ph_form_name", spacecrafts.get(position));
                id=spacecrafts.get(position);
                intent.putExtra("id", id);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return spacecrafts.size();
    }
}
