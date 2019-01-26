package com.example.johnabbas.tabeeb.searchDoctor.SearchDocs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.johnabbas.tabeeb.R;
import com.example.johnabbas.tabeeb.searchDoctor.SearchHospitals.Hospital;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class SubFramentListDoctors extends Fragment {

    private RecyclerView mRecyclerView;
    private int specialization;
    private String hospital,caller;

    public static List<doctorItem> doctors = new ArrayList<doctorItem>();

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_docs, container, false);

        Bundle bundle = getArguments();
        specialization = bundle.getInt("Specialization");
        hospital = bundle.getString("Hospital");
        caller = bundle.getString("Caller");

        mRecyclerView = (RecyclerView) view.findViewById(R.id.listDocRecycler);
        firebaseDoctorSearch();


        Log.v("Trace","Doctor List Fragment Created");
        return view;
    }

    private void firebaseDoctorSearch() {
        if(hospital.equals("none")){
            searchBySpecialization();
        }
        else {
            searchByHospital();
        }
    }

    private void searchBySpecialization(){
        doctors.clear();
        DatabaseReference userDetRef = FirebaseDatabase.getInstance().getReference("Doctors");
        userDetRef.orderByChild("Special").equalTo(specialization).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    doctorItem rec = messageSnapshot.getValue(doctorItem.class);
                    doctors.add(rec);
                    Log.v("Retrieved Data",rec.getName() + " " + rec.getLocation());
                }
                Log.v("Retrieved Data",  Integer.toString(doctors.size()));

                MyAdapter mListAdapter = new MyAdapter(getActivity(),getFragmentManager(),caller,specialization,hospital);
                mRecyclerView.setAdapter(mListAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                mRecyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void searchByHospital(){
        Log.v("Retrieved Data",  hospital);
        doctors.clear();
        DatabaseReference userDetRef = FirebaseDatabase.getInstance().getReference("Doctors");
        userDetRef.orderByChild("Location").equalTo(hospital).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    doctorItem rec = messageSnapshot.getValue(doctorItem.class);
                    doctors.add(rec);
                    Log.v("Retrieved Data",rec.getName() + " " + rec.getLocation());
                }
                Log.v("Retrieved Data",  Integer.toString(doctors.size()));

                MyAdapter mListAdapter = new MyAdapter(getActivity(),getFragmentManager(),caller,specialization,hospital);
                mRecyclerView.setAdapter(mListAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                mRecyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
