package com.example.duanmau.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;
import com.example.duanmau.adapter.LoaiSachAdapter;
import com.example.duanmau.dao.LoaiSachDAO;
import com.example.duanmau.model.LoaiSach;

import java.util.ArrayList;

public class QLLS extends Fragment {
    RecyclerView recyclerView;
    LoaiSachDAO dao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlls, container,false);
        recyclerView = view.findViewById(R.id.rcLoaisach);
        EditText edtNhap = view.findViewById(R.id.edtThemLoaiSach);
        Button btnThem = view.findViewById(R.id.btnThem);

        dao = new LoaiSachDAO(getContext());

        loadData();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenloai = edtNhap.getText().toString();
                if(dao.themLoaisach(tenloai)){
                    loadData();
                    edtNhap.setText("");
                }else{
                    Toast.makeText(getContext(), "that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    public void loadData(){
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        dao = new LoaiSachDAO(getContext());
        ArrayList<LoaiSach> list = dao.getDSLoaisach();
        LoaiSachAdapter adapter = new LoaiSachAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
    }
}
