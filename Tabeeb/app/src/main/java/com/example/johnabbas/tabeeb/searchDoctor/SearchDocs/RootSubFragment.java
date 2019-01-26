package com.example.johnabbas.tabeeb.searchDoctor.SearchDocs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.johnabbas.tabeeb.R;
import com.example.johnabbas.tabeeb.searchDoctor.SearchCustom.SubFragmentCustom;
import com.example.johnabbas.tabeeb.searchDoctor.SearchHospitals.SubFragmentHospital;

public class RootSubFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.root_fragment, container, false);

        Bundle mBundle = getArguments();
        String caller = mBundle.getString("caller");
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();

        Log.v("Trace","Root Fragment Created" + caller);

        if(caller == "doc")
            transaction.replace(R.id.frag_root, new SubFragmentSpecial());
        else if(caller == "hosp")
            transaction.replace(R.id.frag_root, new SubFragmentHospital());
        else if(caller == "custom")
            transaction.replace(R.id.frag_root, new SubFragmentCustom());
        else
            transaction.replace(R.id.frag_root, new SubFragmentCustom());

        transaction.commit();

        return view;
    }
}
