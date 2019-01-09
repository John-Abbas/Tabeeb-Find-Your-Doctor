package com.example.johnabbas.tabeeb.searchDoctor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.johnabbas.tabeeb.R;
import com.example.johnabbas.tabeeb.searchDoctor.MyAdapter.MyAdapter;

public class SubFragmentSpecial extends Fragment {

    RecyclerView mRecyclerView;
    GridLayoutManager layoutManager;

    // Store instance variables
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static SubFragmentSpecial newInstance(int page, String title) {
        SubFragmentSpecial fragmentFirst = new SubFragmentSpecial();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_special, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.specDocRecycler);
        MyAdapter mListAdapter = new MyAdapter(getActivity(),getFragmentManager());
        mRecyclerView.setAdapter(mListAdapter);
        layoutManager = new GridLayoutManager(getActivity(),2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        return view;
    }

}
