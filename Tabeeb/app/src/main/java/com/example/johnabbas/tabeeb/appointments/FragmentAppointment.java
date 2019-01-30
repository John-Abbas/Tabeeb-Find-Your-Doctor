package com.example.johnabbas.tabeeb.appointments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.johnabbas.tabeeb.R;
import com.example.johnabbas.tabeeb.appointments.appointItemAdapter.MyAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class FragmentAppointment extends Fragment {

    public static List<appointmentDetails> appointments;
    private RecyclerView mRecyclerView;
    private TextView mMessage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_appoint,container,false);
        appointments = new ArrayList<appointmentDetails>();
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.appointRecycler);
        mMessage = (TextView) mView.findViewById(R.id.tv_message);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        searchAppointments(user.getUid());
        return mView;
    }


    private void searchAppointments(String id){
        appointments.clear();
        DatabaseReference userDetRef = FirebaseDatabase.getInstance().getReference("Appointments");
        userDetRef.orderByChild("userID").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    appointmentDetails rec = messageSnapshot.getValue(appointmentDetails.class);
                    rec.setKey(messageSnapshot.getKey());
                    appointments.add(rec);
                }

                if(appointments.size() == 0){
                    mMessage.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.GONE);
                }
                else{
                    mMessage.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    MyAdapter mListAdapter = new MyAdapter(getActivity(),getFragmentManager(),mRecyclerView,mMessage);
                    mRecyclerView.setAdapter(mListAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    mRecyclerView.setLayoutManager(layoutManager);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
