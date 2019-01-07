package com.example.johnabbas.tabeeb.about;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.example.johnabbas.tabeeb.R;
import com.example.johnabbas.tabeeb.about.ListAdapter;

public class FragmentAbout extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView =  inflater.inflate(R.layout.fragment_about,container,false);

        RecyclerView mRecyclerView = (RecyclerView) mView.findViewById(R.id.aboutRecycler);
        ListAdapter mListAdapter = new ListAdapter();
        mRecyclerView.setAdapter(mListAdapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        final ScrollView sv = (ScrollView) mView.findViewById(R.id.scrollAbout);
        sv.post(new Runnable() {
            public void run() {
                sv.smoothScrollTo(0, 0);
            }
        });
        return mView;
    }
}
