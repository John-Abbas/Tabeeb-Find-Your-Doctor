package com.example.johnabbas.tabeeb.searchDoctor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;

import com.example.johnabbas.tabeeb.R;
import com.example.johnabbas.tabeeb.about.ListAdapter;
import com.example.johnabbas.tabeeb.searchDoctor.MyAdapter.MyAdapter;

public class SubFragmentSpecial extends Fragment {

    RecyclerView mRecyclerView;
    GridLayoutManager layoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView =  inflater.inflate(R.layout.fragment_search_special,container,false);


        mRecyclerView = (RecyclerView) mView.findViewById(R.id.specDocRecycler);
        MyAdapter mListAdapter = new MyAdapter(getContext());
        mRecyclerView.setAdapter(mListAdapter);
        layoutManager = new GridLayoutManager(getActivity(),2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);

        return mView;
    }
}
