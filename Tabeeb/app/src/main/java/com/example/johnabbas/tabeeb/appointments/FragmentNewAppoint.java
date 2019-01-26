package com.example.johnabbas.tabeeb.appointments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.johnabbas.tabeeb.R;
import com.example.johnabbas.tabeeb.searchDoctor.SearchDocs.SubFramentListDoctors;

public class FragmentNewAppoint extends Fragment {

    private String comments,Name,Hours,Fee,caller,Hospital;
    private FragmentManager mFragmentManager;
    private int Special;
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

        ((TextView)mView.findViewById(R.id.tvDrName)).setText(Name);
        ((TextView)mView.findViewById(R.id.tvVisitHours)).setText(Hours);
        ((TextView)mView.findViewById(R.id.tvFee)).setText(Fee);

        mFragmentManager = getFragmentManager();
        comments = ((EditText)mView.findViewById(R.id.et_comments)).getText().toString();

        ((Button) mView.findViewById(R.id.confirm_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ((Button) mView.findViewById(R.id.cancel_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                /*
                 * IMPORTANT: The following lines allow us to add the fragment
                 * to the stack and return to it later, by pressing back
                 */
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);

                trans.commit();
            }
        });

        return mView;
    }
}
