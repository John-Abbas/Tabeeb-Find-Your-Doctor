package com.example.johnabbas.tabeeb.searchDoctor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.johnabbas.tabeeb.R;

public class FragmentSearchDoc extends Fragment {

    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView =  inflater.inflate(R.layout.fragment_search_doc,container,false);

        mSectionPageAdapter = new SectionPageAdapter(getChildFragmentManager());
        mViewPager = (ViewPager) mView.findViewById(R.id.container_doc);
        setupViewPager(mViewPager);

        TabLayout tbLayout = (TabLayout) mView.findViewById(R.id.searchDocTab);
        tbLayout.setupWithViewPager(mViewPager);
        return mView;
    }

    private void setupViewPager(ViewPager vPager){
        SectionPageAdapter adapter = new SectionPageAdapter(getChildFragmentManager());
        adapter.addFragment(new SubFragmentSpecial(),"Doctors");
        adapter.addFragment(new SubFragmentHospital(),"Hospitals");
        adapter.addFragment(new SubFragmentCustom(),"Search");
        vPager.setAdapter(adapter);
    }
}
