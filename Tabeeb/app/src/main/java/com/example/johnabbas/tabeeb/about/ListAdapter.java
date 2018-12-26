package com.example.johnabbas.tabeeb.about;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johnabbas.tabeeb.R;
import com.example.johnabbas.tabeeb.about.aboutItems;

import static android.support.v7.widget.RecyclerView.*;

public class ListAdapter extends RecyclerView.Adapter{
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new ListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.about_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return aboutItems.name.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvDesignation;
        private TextView tvName;
        private TextView tvEmail;
        private ImageView mImageView;

        public ListViewHolder(View ItemView){
            super(ItemView);
            tvDesignation = (TextView) ItemView.findViewById(R.id.desigItem);
            tvName = (TextView) ItemView.findViewById(R.id.nameItem);
            tvEmail = (TextView) ItemView.findViewById(R.id.emailItem);
            mImageView = (ImageView) ItemView.findViewById(R.id.imageItem);
            ItemView.setOnClickListener(this);
        }

        public void bindView(int position){
            tvDesignation.setText(aboutItems.designation[position]);
            tvName.setText(aboutItems.name[position]);
            tvEmail.setText(aboutItems.emailId[position]);
            mImageView.setImageResource(aboutItems.images[position]);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(),"You have clicked " + view.getId() ,Toast.LENGTH_SHORT);
        }
    }
}
