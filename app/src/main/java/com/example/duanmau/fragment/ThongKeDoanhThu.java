package com.example.duanmau.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duanmau.R;
import com.example.duanmau.dao.ThongKeDAO;

import java.util.Calendar;

public class ThongKeDoanhThu extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doanhthu,container,false);
        EditText edtStart = view.findViewById(R.id.edtStart);
        EditText edtEnd = view.findViewById(R.id.edtEnd);
        Button btnThongKe = view.findViewById(R.id.btnThongKe);
        TextView txtKq = view.findViewById(R.id.txtKq);

        Calendar calendar = Calendar.getInstance();

        edtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                edtStart.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );

                datePickerDialog.show();
            }
        });
        edtEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                edtEnd.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );

                datePickerDialog.show();
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThongKeDAO thongKeDAO = new ThongKeDAO(getContext());
                String ngaybd = edtStart.getText().toString();
                String ngaykt = edtEnd.getText().toString();
                int doanhthu = thongKeDAO.getDoanhThu(ngaybd,ngaykt);
                txtKq.setText(doanhthu + "VND");

            }
        });
        return view;

    }
}
