package com.example.johnabbas.tabeeb.appointments.appointItemAdapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.johnabbas.tabeeb.R;
import com.example.johnabbas.tabeeb.appointments.FragmentAppointment;
import com.example.johnabbas.tabeeb.appointments.FragmentNewAppoint;
import com.example.johnabbas.tabeeb.searchDoctor.SearchDocs.SubFramentListDoctors;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private RecyclerView mRecycler;
    private TextView mMessage;
    private FragmentManager mFragmentManager;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference appRef;

    public MyAdapter(Context mContext, FragmentManager mFragmentManager,RecyclerView mRecycler,TextView mMessage){
        this.mFragmentManager = mFragmentManager;
        this.mContext = mContext;
        appRef = FirebaseDatabase.getInstance().getReference("Appointments");
        this.mRecycler = mRecycler;
        this.mMessage = mMessage;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new AppointmentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((AppointmentViewHolder) holder).bindView(position);

        ((AppointmentViewHolder) holder).cancel.setOnClickListener((view)->{
            appRef.child(FragmentAppointment.appointments.get(position).getKey()).removeValue();
            //mRecycler.removeViewAt(position);
            FragmentAppointment.appointments.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, FragmentAppointment.appointments.size());

            if(FragmentAppointment.appointments.size() == 0){
                mRecycler.setVisibility(View.GONE);
                mMessage.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return FragmentAppointment.appointments.size();
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder{
        private View mView;
        public TextView name,status,date,time;
        public Button cancel;

        public AppointmentViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            name = (TextView) itemView.findViewById(R.id.tvDrName);
            status = (TextView) itemView.findViewById(R.id.tvStatus);
            date = (TextView) itemView.findViewById(R.id.tvDate);
            time = (TextView) itemView.findViewById(R.id.tvTime);
            cancel = (Button) itemView.findViewById(R.id.delete_btn);
        }


        public void bindView(int position){
            name.setText(FragmentAppointment.appointments.get(position).getDocName());
            status.setText(FragmentAppointment.appointments.get(position).getStatus());

            if(FragmentAppointment.appointments.get(position).getStatus().equals("Pending")){
                status.setBackground(mContext.getDrawable(R.drawable.status_pending));
            }
            else if(FragmentAppointment.appointments.get(position).getStatus().equals("Approved")){
                status.setBackground(mContext.getDrawable(R.drawable.status_approved));
            }

            String timeApp = FragmentAppointment.appointments.get(position).getAppTime();
            if(timeApp.equals("none")){
                date.setText("-");
                time.setText("-");
            }
            else{
                String[] dateTime = timeApp.split("T");
                date.setText(dateTime[0]);
                time.setText(dateTime[1]);
            }

        }

    }
}
