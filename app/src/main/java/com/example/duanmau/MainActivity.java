package com.example.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.duanmau.dao.SachDAO;
import com.example.duanmau.dao.ThuThuDAO;
import com.example.duanmau.fragment.QLLS;
import com.example.duanmau.fragment.QLPM;
import com.example.duanmau.fragment.QLTV;
import com.example.duanmau.fragment.ThongKeDoanhThu;
import com.example.duanmau.fragment.ThongKeTop10;
import com.google.android.material.navigation.NavigationView;

import java.nio.file.Files;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        NavigationView navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                if(item.getItemId() == R.id.mQLPM){
                    fragment = new QLPM();
                } else if (item.getItemId() == R.id.mQLLS) {
                    fragment = new QLLS();
                }else if(item.getItemId() == R.id.mThoat){
                    Intent intent = new Intent(MainActivity.this, DangNhapActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.mDoimk) {
                    showDialogDoimk();
                    return true;
                } else if (item.getItemId() == R.id.mTop10) {
                    fragment = new ThongKeTop10();
                } else if (item.getItemId() == R.id.mDoanhThu) {
                    fragment = new ThongKeDoanhThu();
                } else if (item.getItemId() == R.id.mQLTV) {
                    fragment = new QLTV();
                } else{
                    fragment = new QLPM();
                }

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();

                drawerLayout.closeDrawer(GravityCompat.START);
                toolbar.setTitle(item.getTitle());
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
    public void showDialogDoimk(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doimk, null);

        EditText edtOldpass = view.findViewById(R.id.edtOldPass);
        EditText edtNewpass = view.findViewById(R.id.edtNewPass);
        EditText edtRenewpass = view.findViewById(R.id.edtRenewpass);

        builder.setView(view);

        builder.setPositiveButton("huy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("cap nhap", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String oldpass = edtOldpass.getText().toString();
                String newpass = edtNewpass.getText().toString();
                String renewpass = edtRenewpass.getText().toString();
                if(newpass.equals(renewpass)){
                    SharedPreferences sharedPreferences = getSharedPreferences("infor", MODE_PRIVATE);
                    String matt = sharedPreferences.getString("matt","");
                    ThuThuDAO thuThuDAO = new ThuThuDAO(MainActivity.this);
                    boolean check = thuThuDAO.updateMk(matt,oldpass,newpass);
                    if(check){
                        Toast.makeText(MainActivity.this, "cap nhat mat khau thanh cong", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, DangNhapActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "That bai", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Nhap lai mat khau khong khop", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}