package com.example.fragments.m_UI;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fragments.Main2Activity;
import com.example.fragments.R;
import com.example.fragments.UpdateActivity;

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
    TextView nameTxt,optionDigit;
    RelativeLayout parentLayout;
    private OnItemClickListener mListener;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTxt=itemView.findViewById(R.id.text_view_title);
        optionDigit=itemView.findViewById(R.id.textViewOptions);
        parentLayout = itemView.findViewById(R.id.parent_layout);

        itemView.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select Action");
        MenuItem change=menu.add(Menu.NONE,1,1,"Change name");
        MenuItem delete=menu.add(Menu.NONE,2,2,"Delete");

        change.setOnMenuItemClickListener(this);
        delete.setOnMenuItemClickListener(this);

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (mListener != null) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                switch (item.getItemId()){
                    case 1:
                        mListener.onChangeClick(position);
                        return true;
                    case 2:
                        mListener.onDeleteClick(position);
                        return true;
                }
            }
        }
        return false;
    }
    public interface OnItemClickListener {

        void onChangeClick(int position);

        void onDeleteClick(int position);
    }
}
