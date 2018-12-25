package com.example.johnabbas.tabeeb;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

        private TextView mTextView;
        private ImageView mImageView;

        public ListViewHolder(View ItemView){
            super(ItemView);
            mTextView = (TextView) itemView.findViewById(R.id.textItem);
            mImageView = (ImageView) itemView.findViewById(R.id.imageItem);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position){
            mTextView.setText(aboutItems.name[position]);
            mImageView.setImageResource(aboutItems.images[position]);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
