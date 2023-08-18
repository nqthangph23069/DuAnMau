package com.example.duanmau.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;
import com.example.duanmau.model.Sach;

import java.util.ArrayList;

public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.ViewHoder>{
    private Context context;
    private ArrayList<Sach> list;

    public Top10Adapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rc_top10,parent,false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        holder.txtMasach.setText(String.valueOf(list.get(position).getMasach()));
        holder.txtTensach.setText(String.valueOf(list.get(position).getTensach()));
        holder.txtSlmuon.setText(String.valueOf(list.get(position).getSoluongmuon()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder{
        TextView txtMasach,txtTensach,txtSlmuon;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            txtMasach = itemView.findViewById(R.id.txtms);
            txtTensach = itemView.findViewById(R.id.txtts);
            txtSlmuon = itemView.findViewById(R.id.txtslmuon);

        }
    }
}
