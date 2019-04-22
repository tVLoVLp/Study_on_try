package com.example.fragments.m_UI;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.fragments.Main2Activity;
import com.example.fragments.R;
import com.example.fragments.UpdateActivity;
import com.example.fragments.WordsActivity;
import com.example.fragments.m_Realm.Spacecraft;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private MyViewHolder.OnItemClickListener mListener;
    public Context context;
    Realm realm;
    public ArrayList<String> spacecrafts;

    public MyAdapter(Context context, ArrayList<String> spacecrafts) {
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
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        myViewHolder.nameTxt.setText(spacecrafts.get(position));
        myViewHolder.optionDigit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   final String mjay=spacecrafts.get(myViewHolder.nameTxt.getId());
                PopupMenu popupMenu=new PopupMenu(context,myViewHolder.optionDigit);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.mnu_item_change:
                              //  Intent intent =new Intent(context, UpdateActivity.class);
                                //intent.putExtra("key_id",mjay);
                               // intent.putExtra("key_name",)
                             //   context.startActivity(intent);
                                Toast.makeText(context,"Change",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.mnu_item_delete:
                                    /*int id=myViewHolder.getAdapterPosition();
                                    realm.beginTransaction();
                                        RealmResults<Spacecraft> spacecrafts=realm.where(Spacecraft.class).equalTo("id",id).findAll();
                                        spacecrafts.deleteAllFromRealm();
                                        realm.commitTransaction();
                              //  notifyDataSetChanged();*/
                                Toast.makeText(context,"Delete",Toast.LENGTH_SHORT).show();
                                break;
                                default: break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        myViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id;
                Log.d(TAG, "onClick: clicked on: " + spacecrafts.get(position));

                Toast.makeText(context, spacecrafts.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, WordsActivity.class);
                intent.putExtra("group_name", spacecrafts.get(position));
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

    public void setOnItemClickListener(MyViewHolder.OnItemClickListener listener) {
        mListener = listener;
    }

}

