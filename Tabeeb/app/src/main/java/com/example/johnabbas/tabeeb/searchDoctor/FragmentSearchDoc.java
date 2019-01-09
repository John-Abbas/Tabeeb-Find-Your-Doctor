package com.example.johnabbas.tabeeb.searchDoctor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.johnabbas.tabeeb.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearchDoc extends Fragment {


    SlidePagerAdapter mPagerAdapter;
    ViewPager vpPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView =  inflater.inflate(R.layout.fragment_search_doc,container,false);

        vpPager = (ViewPager)mView.findViewById(R.id.container_doc);
        mPagerAdapter = new SlidePagerAdapter(getChildFragmentManager());
        mPagerAdapter.addFragment(new SubFragmentSpecial(),"Specialization");
        mPagerAdapter.addFragment(new SubFragmentHospital(),"Hospital");
        mPagerAdapter.addFragment(new SubFragmentCustom(),"Custom");
        vpPager.setAdapter(mPagerAdapter);

        return mView;
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
            switch(position)
            {
                case 0:
                    return new RootSubFragment();

                case 1:
                    return new SubFragmentHospital();

                case 2:
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
