package com.example.duanmau.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;
import com.example.duanmau.adapter.PhieuMuonAdapter;
import com.example.duanmau.dao.PhieuMuonDAO;
import com.example.duanmau.dao.SachDAO;
import com.example.duanmau.dao.ThanhVienDAO;
import com.example.duanmau.model.PhieuMuon;
import com.example.duanmau.model.Sach;
import com.example.duanmau.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class QLPM extends Fragment {
    PhieuMuonDAO phieuMuonDAO;
    RecyclerView rcView;
    ArrayList<PhieuMuon> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlpm, container,false);

        rcView = view.findViewById(R.id.recyclerQLPM);
        FloatingActionButton floadAdd = view.findViewById(R.id.floatAdd);
        loadData();

        floadAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        return view;
    }
    public void loadData(){
        phieuMuonDAO = new PhieuMuonDAO(getContext());
        list = phieuMuonDAO.getDs();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rcView.setLayoutManager(manager);
        PhieuMuonAdapter phieuMuonAdapter = new PhieuMuonAdapter(list, getContext());
        rcView.setAdapter(phieuMuonAdapter);
    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_pm, null);
        Spinner spntv = view.findViewById(R.id.spntv);
        Spinner spnsach = view.findViewById(R.id.spnsach);
        EditText edtTien = view.findViewById(R.id.edtTien);
        getDataTv(spntv);
        getDataSach(spnsach);
        builder.setView(view);

        builder.setPositiveButton("them", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                HashMap<String,Object> hstv = (HashMap<String, Object>) spntv.getSelectedItem();
                int matv = (int) hstv.get("matv");

                HashMap<String,Object> hssach = (HashMap<String, Object>) spnsach.getSelectedItem();
                int masach = (int) hssach.get("masach");

                int tien = Integer.parseInt(edtTien.getText().toString());

                themPhieuMuon(matv,masach,tien);
            }
        });
        builder.setNegativeButton("huy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void getDataTv(Spinner spinnertv){
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(getContext());
        ArrayList<ThanhVien> list = thanhVienDAO.getDstv();

        ArrayList<HashMap<String, Object>> listHm = new ArrayList<>();
        for (ThanhVien tv: list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("matv", tv.getMatv());
            hs.put("hoten", tv.getHoten());
            listHm.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),listHm, android.R.layout.simple_list_item_1, new String []{"hoten"},new int []{android.R.id.text1});
        spinnertv.setAdapter(simpleAdapter);
    }
    private void getDataSach(Spinner spinnersach){
        SachDAO sachDAO = new SachDAO(getContext());
        ArrayList<Sach> list = sachDAO.getAllSach();

        ArrayList<HashMap<String, Object>> listHm = new ArrayList<>();
        for (Sach sach: list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("masach", sach.getMasach());
            hs.put("tensach", sach.getTensach());
            listHm.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),listHm, android.R.layout.simple_list_item_1, new String []{"tensach"},new int []{android.R.id.text1});
        spinnersach.setAdapter(simpleAdapter);
    }
    private void themPhieuMuon(int matv, int masach, int tien){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("infor", Context.MODE_PRIVATE);
        String matt = sharedPreferences.getString("matt", "");
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault());
        String ngay = simpleDateFormat.format(currentTime);

        PhieuMuon phieuMuon = new PhieuMuon(matv, matt, masach, ngay, 0, tien);
        boolean kiemtra = phieuMuonDAO.themPm(phieuMuon);
        if(kiemtra){
            Toast.makeText(getContext(), "them phieu muon thanh cong ", Toast.LENGTH_SHORT).show();
            loadData();
        }else {
            Toast.makeText(getContext(), "them phieu muon that bai", Toast.LENGTH_SHORT).show();
        }
    }

}
