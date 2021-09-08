package com.example.rteupazar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PostHolder> {
    ArrayList<RteuPazar> list;

    public RecyclerAdapter(ArrayList<RteuPazar> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tek_post,parent,false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        int duzen = list.size()- position -1;
    holder.fiyat.setText(list.get(duzen).getFiyat()+"TL");
    holder.tvKonum.setText(list.get(duzen).getAdres());
    holder.email.setText(list.get(duzen).getEmail());
    Bitmap bitmap = BitmapFactory.decodeByteArray(list.get(duzen).getResim(), 0, list.get(duzen).getResim().length);
    holder.imgUrun.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder{
        ImageView imgUrun,imgKonum;
        TextView fiyat,email,tvKonum;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            tvKonum = itemView.findViewById(R.id.tekPosttvKonum);
            imgKonum = itemView.findViewById(R.id.tekPostimgUrun);
            imgUrun = itemView.findViewById(R.id.tekPostimgUrun);
            fiyat = itemView.findViewById(R.id.tekPosttvFiyat);
            email = itemView.findViewById(R.id.tekPostetvEmail);
        }
    }
}
