package com.example.johnabbas.tabeeb.searchDoctor.SearchHospitals;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.johnabbas.tabeeb.R;
import com.example.johnabbas.tabeeb.searchDoctor.SearchCustom.SubFragmentCustom;
import com.example.johnabbas.tabeeb.searchDoctor.SearchDocs.SubFragmentSpecial;

public class RootSubFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.root_fragment1, container, false);

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();

        transaction.replace(R.id.frag_root1, new SubFragmentHospital());
        transaction.commit();

        return view;
    }
}
