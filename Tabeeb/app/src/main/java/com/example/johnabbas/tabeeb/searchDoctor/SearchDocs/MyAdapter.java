package com.example.johnabbas.tabeeb.searchDoctor.SearchDocs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.johnabbas.tabeeb.R;
import com.example.johnabbas.tabeeb.appointments.FragmentNewAppoint;

public class MyAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private FragmentManager mFragmentManager;
    private String Root,Hospital;
    private int Special;

    public MyAdapter(Context mContext, FragmentManager mFragmentManager,String Root,int Special,String Hospital){
        this.mFragmentManager = mFragmentManager;
        this.mContext = mContext;
        this.Root = Root;
        this.Special = Special;
        this.Hospital = Hospital;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new DoctorViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((DoctorViewHolder) holder).bindView(position);

        ((DoctorViewHolder) holder).layout.setOnClickListener((view)->{

            FragmentTransaction trans = mFragmentManager.beginTransaction();
            /*
             * IMPORTANT: We use the "root frame" defined in
             * "root_fragment.xml" as the reference to replace fragment
             */
            FragmentNewAppoint mFragment = new FragmentNewAppoint();
            Bundle mBundle = new Bundle();
            mBundle.putString("Name",((DoctorViewHolder) holder).name.getText().toString());
            mBundle.putString("Fee",((DoctorViewHolder) holder).fee.getText().toString());
            mBundle.putString("Hours",((DoctorViewHolder) holder).hours.getText().toString());
            mBundle.putString("Caller",Root);
            mBundle.putInt("Special",Special);
            mBundle.putString("Hospital",Hospital);
            mFragment.setArguments(mBundle);

            if(Root.equals("Docs")) {
                trans.replace(R.id.frag_root, mFragment);
            }
            else if(Root.equals("Hosp")) {
                trans.replace(R.id.frag_root1, mFragment);
            }
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
        return SubFramentListDoctors.doctors.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder{
        private View mView;
        public TextView name;
        public TextView fee;
        public TextView hours;
        public LinearLayout layout;

        public DoctorViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            name = (TextView) itemView.findViewById(R.id.tvDrName);
            fee = (TextView) itemView.findViewById(R.id.tvFee);
            hours = (TextView) itemView.findViewById(R.id.tvVisitHours);
            layout = (LinearLayout) itemView.findViewById(R.id.linearDocItem);
        }


        public void bindView(int position){
            name.setText(SubFramentListDoctors.doctors.get(position).getName());
            fee.setText(SubFramentListDoctors.doctors.get(position).getFee());
            hours.setText(SubFramentListDoctors.doctors.get(position).getHours());
        }

    }
}
