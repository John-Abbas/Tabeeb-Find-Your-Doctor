package com.example.johnabbas.tabeeb.searchDoctor.SearchHospitals;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.johnabbas.tabeeb.R;
import com.example.johnabbas.tabeeb.searchDoctor.SearchHospitals.Hospital;
import com.example.johnabbas.tabeeb.searchDoctor.SearchHospitals.MyAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SubFragmentHospital extends Fragment {

    public static List<Hospital> hospitals = new ArrayList<Hospital>();
    public RecyclerView mRecyclerView;
    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_hospital, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.hospitalRecycler);
        firebaseHospitalSearch();
        Log.v("Trace","Hospital Fragment Created");
        return view;
    }

    private void firebaseHospitalSearch() {
        hospitals.clear();
        DatabaseReference userDetRef = FirebaseDatabase.getInstance().getReference("Hospitals");
        Log.v("Trace",userDetRef.toString());
        userDetRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    Hospital rec = messageSnapshot.getValue(Hospital.class);
                    rec.setKey(messageSnapshot.getKey());
                    hospitals.add(rec);
                    Log.v("Retrieved Data",rec.getName() + " " + messageSnapshot.getKey());
                }
                Log.v("Retrieved Data",  Integer.toString(hospitals.size()));

                MyAdapter mListAdapter = new MyAdapter(getActivity(),getFragmentManager());
                mRecyclerView.setAdapter(mListAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                mRecyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.v("Error",  Integer.toString(hospitals.size()));
            }
        });
    }
}
