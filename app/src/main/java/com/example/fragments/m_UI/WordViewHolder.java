package com.example.fragments.m_UI;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fragments.R;

public class WordViewHolder extends RecyclerView.ViewHolder {
    TextView wordTxt;
    RelativeLayout parentLayout;
    public WordViewHolder(@NonNull View itemView) {
        super(itemView);
        wordTxt=itemView.findViewById(R.id.text_view_title_word);
        parentLayout = itemView.findViewById(R.id.parent_layout_word_rv);
    }
}
