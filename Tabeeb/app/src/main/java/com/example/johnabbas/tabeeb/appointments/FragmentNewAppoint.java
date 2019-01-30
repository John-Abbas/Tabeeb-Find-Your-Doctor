package com.example.johnabbas.tabeeb.appointments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.johnabbas.tabeeb.R;
import com.example.johnabbas.tabeeb.searchDoctor.SearchDocs.SubFramentListDoctors;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FragmentNewAppoint extends Fragment {

    private String comments,Name,Hours,Fee,caller,Hospital,DocId;
    private FragmentManager mFragmentManager;
    private int Special;
    private EditText et_comment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView =  inflater.inflate(R.layout.fragment_new_appoint,container,false);

        Bundle bundle = getArguments();
        Name = bundle.getString("Name");
        Hours = bundle.getString("Hours");
        Fee = bundle.getString("Fee");
        caller = bundle.getString("Caller");
        Special = bundle.getInt("Special");
        Hospital = bundle.getString("Hospital");
        DocId = bundle.getString("DocId");

        ((TextView)mView.findViewById(R.id.tvDrName)).setText(Name);
        ((TextView)mView.findViewById(R.id.tvVisitHours)).setText(Hours);
        ((TextView)mView.findViewById(R.id.tvFee)).setText(Fee);

        mFragmentManager = getFragmentManager();
        et_comment = (EditText)mView.findViewById(R.id.et_comments);

        ((Button) mView.findViewById(R.id.confirm_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAppointment();
            }
        });

        ((Button) mView.findViewById(R.id.cancel_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        return mView;
    }

    private void goBack(){

        FragmentTransaction trans = mFragmentManager.beginTransaction();
        Bundle mBundle = new Bundle();
        mBundle.putInt("Specialization",Special);
        mBundle.putString("Hospital",Hospital);
        mBundle.putString("Caller",caller);
        SubFramentListDoctors mFragment = new SubFramentListDoctors();
        mFragment.setArguments(mBundle);

        if(caller.equals("Docs")) {

            trans.replace(R.id.frag_root, mFragment);
        }
        else if(caller.equals("Hosp")) {

            trans.replace(R.id.frag_root1, mFragment);
        }

        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        trans.addToBackStack(null);

        trans.commit();
    }


    private void registerAppointment(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference appointments = FirebaseDatabase.getInstance().getReference("Appointments");
        appointmentDetails newAppointment = new appointmentDetails(user.getUid(),DocId,"Pending","none",et_comment.getText().toString(),Name);
        appointments.push().setValue(newAppointment);

        goBack();
    }

}
