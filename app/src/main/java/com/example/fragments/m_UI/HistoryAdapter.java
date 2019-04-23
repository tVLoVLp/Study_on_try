package com.example.fragments.m_UI;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fragments.OurData;
import com.example.fragments.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private ArrayList<ListItem> listItems;
    private Context context;

    public HistoryAdapter() {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.history_item,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
       /* ListItem listItem =listItems.get(i);
        viewHolder.textViewNameTest.setText(listItem.getHead());
        viewHolder.textViewData.setText(listItem.getData());
        viewHolder.textViewResult.setText(listItem.getResults());*/
        ((ViewHolder)viewHolder).bindView(i);


    }

    @Override
    public int getItemCount() {
        return OurData.nameTest.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewNameTest,textViewResult,textViewData;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNameTest=itemView.findViewById(R.id.nameTest);
            textViewResult=itemView.findViewById(R.id.result);
            textViewData=itemView.findViewById(R.id.data);
        }
        public void bindView(int position){
            textViewNameTest.setText(OurData.nameTest[position]);
            textViewResult.setText(OurData.results[position]);
            textViewData.setText(OurData.data[position]);

        }
    }

}
