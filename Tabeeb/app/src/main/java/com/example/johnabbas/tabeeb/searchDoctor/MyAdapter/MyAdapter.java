package com.example.johnabbas.tabeeb.searchDoctor.MyAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johnabbas.tabeeb.R;

public class MyAdapter extends RecyclerView.Adapter {
    private Context mContext;

    public MyAdapter(Context mContext){
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new MyAdapter.ListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.doc_search_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        ((ListViewHolder) holder).bindView(position);

        ((ListViewHolder) holder).linearSearchDoc.setOnClickListener((view)->{

                Toast.makeText(mContext,"You have clicked " + position ,Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return layoutObject.specialization.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder {

        private TextView tvSpecialization;
        private ImageView mImageView;
        private LinearLayout linearSearchDoc;

        public ListViewHolder(View ItemView){
            super(ItemView);
            tvSpecialization = (TextView) ItemView.findViewById(R.id.tvSpecialization);
            mImageView = (ImageView) ItemView.findViewById(R.id.btnSpecialization);
            linearSearchDoc = (LinearLayout) ItemView.findViewById(R.id.linearSearchDoc);

        }

        public void bindView(int position){
            tvSpecialization.setText(layoutObject.specialization[position]);
            mImageView.setImageResource(layoutObject.images[position]);
        }

    }
}
