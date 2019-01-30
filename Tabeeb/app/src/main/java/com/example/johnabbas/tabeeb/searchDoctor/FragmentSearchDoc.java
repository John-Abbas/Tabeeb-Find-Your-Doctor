package com.example.johnabbas.tabeeb.searchDoctor;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.johnabbas.tabeeb.R;
import com.example.johnabbas.tabeeb.searchDoctor.SearchCustom.SubFragmentCustom;
import com.example.johnabbas.tabeeb.searchDoctor.SearchDocs.RootSubFragment;
import com.example.johnabbas.tabeeb.searchDoctor.SearchDocs.SubFragmentSpecial;
import com.example.johnabbas.tabeeb.searchDoctor.SearchHospitals.SubFragmentHospital;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearchDoc extends Fragment {


    SlidePagerAdapter mPagerAdapter;
    ViewPager vpPager;
    TabLayout tbLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView =  inflater.inflate(R.layout.fragment_search_doc,container,false);

        vpPager = (ViewPager)mView.findViewById(R.id.container_doc);
        tbLayout = (TabLayout) mView.findViewById(R.id.view_pager_tab);

        mPagerAdapter = new SlidePagerAdapter(getChildFragmentManager());
        mPagerAdapter.addFragment(new SubFragmentSpecial(),"Specialization");
        mPagerAdapter.addFragment(new SubFragmentHospital(),"Hospital");
        mPagerAdapter.addFragment(new SubFragmentCustom(),"Custom");
        vpPager.setAdapter(mPagerAdapter);

        vpPager.setOffscreenPageLimit(2);

        Log.v("Trace","Fragment Search Doc Created");

        return mView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Fetch data or something...
        }
    }

    /* PagerAdapter class */
    public class SlidePagerAdapter extends FragmentPagerAdapter {

        static final int NUM_ITEMS = 3;

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment,String title){
            mFragmentTitleList.add(title);
            mFragmentList.add(fragment);
        }



        @Override
        public Fragment getItem(int position) {
            /*
             * IMPORTANT: This is the point. We create a RootFragment acting as
             * a container for other fragments
             */
            Bundle mBundle = new Bundle();

            Log.v("Trace","Search Doc get Item Clicked " + Integer.toString(position));

            switch(position)
            {
                case 0:
                    RootSubFragment fragment = new RootSubFragment();
                    mBundle.putString("caller","doc");
                    fragment.setArguments(mBundle);
                    return fragment;
                case 1:
                    com.example.johnabbas.tabeeb.searchDoctor.SearchHospitals.RootSubFragment fragHosp = new com.example.johnabbas.tabeeb.searchDoctor.SearchHospitals.RootSubFragment();
                    return fragHosp;
                case 2:
                    //mBundle.putString("caller","custom");
                    return new SubFragmentCustom();
                default:
                    return null;
            }



        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }


    }

}
