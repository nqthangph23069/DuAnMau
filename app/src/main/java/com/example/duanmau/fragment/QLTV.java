package com.example.duanmau.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;
import com.example.duanmau.adapter.ThanhVienAdapter;
import com.example.duanmau.dao.ThanhVienDAO;
import com.example.duanmau.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QLTV extends Fragment {
    ThanhVienDAO dao;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qltv,container,false);
         recyclerView = view.findViewById(R.id.rcThanhvien);

        FloatingActionButton floatadd = view.findViewById(R.id.floatAdd);

         dao = new ThanhVienDAO(getContext());
        loadData();

        floatadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        return view;
    }
    private void loadData(){
        ArrayList<ThanhVien> list = dao.getDstv();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        ThanhVienAdapter adapter = new ThanhVienAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_thanhvien, null);
        builder.setView(view);
        EditText edtHoten = view.findViewById(R.id.edtHoten);
        EditText edtNamsinh = view.findViewById(R.id.edtNamsinh);
        builder.setNegativeButton("them", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String hoten = edtHoten.getText().toString();
                String namsinh = edtNamsinh.getText().toString();
                boolean check = dao.themThanhVien(hoten,namsinh);
                if(check){
                    Toast.makeText(getContext(), "them thanh cong", Toast.LENGTH_SHORT).show();
                    loadData();
                }else {
                    Toast.makeText(getContext(), "them that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setPositiveButton("huy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
