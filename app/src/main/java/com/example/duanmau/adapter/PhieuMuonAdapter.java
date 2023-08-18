package com.example.duanmau.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;
import com.example.duanmau.dao.PhieuMuonDAO;
import com.example.duanmau.model.PhieuMuon;

import java.util.ArrayList;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolder> {

    private ArrayList<PhieuMuon> list;
    Context context;

    public PhieuMuonAdapter(ArrayList<PhieuMuon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycler_pm, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mapm.setText("ma phieu muon: " + list.get(position).getMapm());
        holder.matv.setText("ma thanh vien: " + list.get(position).getMatv());
        holder.tentv.setText("ten thanh vien: " + list.get(position).getTentv());
        holder.matt.setText("ma thu thu: " + list.get(position).getMatt());
        holder.tentt.setText("ten thu thu: " + list.get(position).getTentt());
        holder.masach.setText("ma sach: " + list.get(position).getMasach());
        holder.tensach.setText("ten sach: " + list.get(position).getTensach());
        holder.ngay.setText("ngay: " + list.get(position).getNgay());
        String trangthai = "";
        if(list.get(position).getTrasach() == 1){
            trangthai = "da tra sach";
            holder.btnTraSach.setVisibility(View.GONE);
        }else{
            trangthai = "chua tra sach";
            holder.btnTraSach.setVisibility(View.VISIBLE);
        }
        holder.trangthai.setText("trang thai: " + trangthai);
        holder.tien.setText("tien thue: " + list.get(position).getTienthue());

        holder.btnTraSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(context);
                boolean kiemtra = phieuMuonDAO.thayDoiTrangThai(list.get(holder.getAdapterPosition()).getMapm());
                if(kiemtra){
                    list.clear();
                    list = phieuMuonDAO.getDs();
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "thay doi trang thai khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mapm, matv, tentv, matt, tentt, masach, tensach, ngay, trangthai, tien;
        Button btnTraSach;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mapm = itemView.findViewById(R.id.txtmapm);
            matv = itemView.findViewById(R.id.txtmatv);
            tentv = itemView.findViewById(R.id.txttentv);
            matt = itemView.findViewById(R.id.txtmatt);
            tentt = itemView.findViewById(R.id.txttentt);
            masach = itemView.findViewById(R.id.txtmasach);
            tensach = itemView.findViewById(R.id.txttensach);
            ngay = itemView.findViewById(R.id.txtngay);
            trangthai = itemView.findViewById(R.id.txttrangthai);
            tien = itemView.findViewById(R.id.txttienthue);
            btnTraSach = itemView.findViewById(R.id.btnTrasach);
        }
    }
}
