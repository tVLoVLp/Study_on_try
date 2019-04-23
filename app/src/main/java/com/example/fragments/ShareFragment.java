package com.example.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.fragments.m_UI.HistoryAdapter;

public class ShareFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final RelativeLayout mRelativeLayout =(RelativeLayout)inflater.inflate(R.layout.fragment_share,container,false);

        recyclerView=mRelativeLayout.findViewById(R.id.recycler_view_history);
        //recyclerView.setHasFixedSize(true);
        HistoryAdapter historyAdapter=new HistoryAdapter();
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return mRelativeLayout;
    }
}
