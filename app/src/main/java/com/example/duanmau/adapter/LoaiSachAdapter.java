package com.example.duanmau.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;
import com.example.duanmau.dao.LoaiSachDAO;
import com.example.duanmau.model.LoaiSach;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHoder>{

    Context context;
    ArrayList<LoaiSach> list;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rc_loaisach, parent,false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        holder.maloai.setText("ma loai: " + String.valueOf(list.get(position).getId()));
        holder.tenLoai.setText("ten loai: " + String.valueOf(list.get(position).getTenloai()));
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
                int check = loaiSachDAO.xoaLoaiSach(list.get(holder.getAdapterPosition()).getId());
                if(check == 1){
                    list.clear();
                    list = loaiSachDAO.getDSLoaisach();
                    notifyDataSetChanged();
                } else if (check == -1) {
                    Toast.makeText(context, "khong the xoa loai sach nay", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "xoa loai sach khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder{
        TextView maloai,tenLoai;
        ImageView ivDelete;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            maloai = itemView.findViewById(R.id.txtMaloai);
            tenLoai = itemView.findViewById(R.id.txtTenloai);
            ivDelete = itemView.findViewById(R.id.ivDelete);

        }
    }
}
