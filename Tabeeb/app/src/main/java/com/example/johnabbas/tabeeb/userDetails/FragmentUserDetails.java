package com.example.johnabbas.tabeeb.userDetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.johnabbas.tabeeb.R;
import com.example.johnabbas.tabeeb.userInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FragmentUserDetails extends Fragment {

    private EditText etEmail,etName,etAge,etPhoneNum;
    private RadioGroup radioSex,radioUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView =  inflater.inflate(R.layout.activity_user_info,container,false);
        etEmail = (EditText) mView.findViewById(R.id.etEmailId);
        etName = (EditText) mView.findViewById(R.id.etName);
        radioSex = (RadioGroup) mView.findViewById(R.id.radioSex);
        etAge = (EditText) mView.findViewById(R.id.etAge);
        etPhoneNum = (EditText) mView.findViewById(R.id.etPhoneNum);
        radioUser = (RadioGroup) mView.findViewById(R.id.radioUserType);

        ((Button) mView.findViewById(R.id.btn_signUp)).setVisibility(View.GONE);

        etName.setEnabled(false);
        radioSex.getChildAt(0).setEnabled(false);
        radioSex.getChildAt(1).setEnabled(false);
        etAge.setEnabled(false);
        etPhoneNum.setEnabled(false);
        radioUser.setEnabled(false);
        radioUser.getChildAt(0).setEnabled(false);
        radioUser.getChildAt(1).setEnabled(false);

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        searchUser(userID);
        return mView;
    }


    private void searchUser(String userID){
        DatabaseReference userDetRef = FirebaseDatabase.getInstance().getReference("UserDetails");
        userDetRef.orderByKey().equalTo(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()) {

                }
                else {
                    DataSnapshot dataSnap = dataSnapshot.child(userID);
                    userInfo rec = dataSnap.getValue(userInfo.class);

                    etEmail.setText(rec.getEmail());
                    etName.setText(rec.getName());
                    etAge.setText(Integer.toString(rec.getAge()));
                    etPhoneNum.setText(rec.getPhoneNum());

                    if(rec.getGender().equals("Male")){ ((RadioButton)radioSex.getChildAt(0)).setChecked(true);}
                    else{((RadioButton)radioSex.getChildAt(1)).setChecked(true);}

                    if(rec.getUserType().equals("Doctor")){((RadioButton)radioUser.getChildAt(0)).setChecked(true);}
                    else{((RadioButton)radioUser.getChildAt(1)).setChecked(true);}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
