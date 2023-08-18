package com.example.duanmau.adapter;

import static android.media.CamcorderProfile.get;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;
import com.example.duanmau.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder>{
    private Context context;
    private ArrayList<ThanhVien> list;

    public ThanhVienAdapter(Context context, ArrayList<ThanhVien> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rc_thanhvien,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvMatv.setText("Ma tv: " + list.get(position).getMatv());
        holder.tvHoten.setText("Ho ten: " + list.get(position).getHoten());
        holder.tvNamsinh.setText("Nam sinh: " + list.get(position).getNamsinh());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvMatv,tvHoten,tvNamsinh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMatv = itemView.findViewById(R.id.tvMatv);
            tvHoten = itemView.findViewById(R.id.tvHoten);
            tvNamsinh = itemView.findViewById(R.id.tvNamsinh);
        }
    }
}
