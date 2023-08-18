package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau.dao.ThuThuDAO;

import java.nio.file.Files;

public class DangNhapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        EditText edtUser = findViewById(R.id.edtUser);
        EditText edtPass = findViewById(R.id.edtPass);
        Button btnOK = findViewById(R.id.btnOk);

        ThuThuDAO thuThuDAO = new ThuThuDAO(this);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
                if(thuThuDAO.checkLogin(user, pass)){
                    SharedPreferences sharedPreferences = getSharedPreferences("infor", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("matt", user);
                    editor.commit();

                    startActivity(new Intent(DangNhapActivity.this, MainActivity.class));
                }else{
                    Toast.makeText(DangNhapActivity.this, "user name hoac mat khau khong dung", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}