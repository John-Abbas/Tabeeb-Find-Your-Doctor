package com.example.johnabbas.tabeeb.searchDoctor.SearchDocs;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.johnabbas.tabeeb.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new MyAdapter.DoctorViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((DoctorViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return SubFramentListDoctors.doctors.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder{
        private View mView;
        private TextView name;
        private TextView fee;
        private TextView hours;

        public DoctorViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            name = (TextView) itemView.findViewById(R.id.tvDrName);
            fee = (TextView) itemView.findViewById(R.id.tvFee);
            hours = (TextView) itemView.findViewById(R.id.tvVisitHours);
        }


        public void bindView(int position){
            name.setText(SubFramentListDoctors.doctors.get(position).getName());
            fee.setText(SubFramentListDoctors.doctors.get(position).getFee());
            hours.setText(SubFramentListDoctors.doctors.get(position).getHours());
        }

    }
}
