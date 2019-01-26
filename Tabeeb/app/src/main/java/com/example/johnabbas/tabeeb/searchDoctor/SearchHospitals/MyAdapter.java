package com.example.johnabbas.tabeeb.searchDoctor.SearchHospitals;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.johnabbas.tabeeb.R;
import com.example.johnabbas.tabeeb.searchDoctor.SearchCustom.SubFragmentCustom;
import com.example.johnabbas.tabeeb.searchDoctor.SearchDocs.SubFramentListDoctors;

public class MyAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private FragmentManager mFragmentManager;

    public MyAdapter(Context mContext, FragmentManager mFragmentManager){
        this.mFragmentManager = mFragmentManager;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new MyAdapter.ListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
        ((ListViewHolder) holder).linearSearchDoc.setOnClickListener((view)->{

            FragmentTransaction trans = mFragmentManager.beginTransaction();
            /*
             * IMPORTANT: We use the "root frame" defined in
             * "root_fragment.xml" as the reference to replace fragment
             */
            SubFramentListDoctors mFragment = new SubFramentListDoctors();
            Bundle mBundle = new Bundle();
            mBundle.putInt("Specialization",position);
            mBundle.putString("Hospital",SubFragmentHospital.hospitals.get(position).getKey());
            mBundle.putString("Caller","Hosp");
            mFragment.setArguments(mBundle);

            trans.replace(R.id.frag_root1, mFragment);

            /*
             * IMPORTANT: The following lines allow us to add the fragment
             * to the stack and return to it later, by pressing back
             */
            trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            trans.addToBackStack(null);

            trans.commit();

        });
    }

    @Override
    public int getItemCount() {
        return SubFragmentHospital.hospitals.size();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private ImageView mImageView;
        private LinearLayout linearSearchDoc;

        public ListViewHolder(View ItemView){
            super(ItemView);
            tvName = (TextView) ItemView.findViewById(R.id.tvName);
            mImageView = (ImageView) ItemView.findViewById(R.id.image);
            linearSearchDoc = (LinearLayout) ItemView.findViewById(R.id.linearHospital);

        }

        public void bindView(int position){
            if(SubFragmentHospital.hospitals.get(position).getVerification() == 0)
            {
                tvName.setText(SubFragmentHospital.hospitals.get(position).getName());
                mImageView.setImageResource(R.drawable.ic_hospital);
            }
            else
            {
                tvName.setText("Unverified");
                mImageView.setImageResource(R.drawable.ic_unknown);
            }

        }



    }
}
