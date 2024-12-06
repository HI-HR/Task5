package com.example.task5;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView mUsename;


    public static void startActivity(Context context, String usename) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra("usename", usename);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initEvent();
    }

    private void initView() {
        mUsename = findViewById(R.id.tv_main_usename);
    }

    private void initEvent() {
        Intent intent = getIntent();
        String inf = String.format("欢迎%s", intent.getStringExtra("usename"));
        mUsename.setText(inf);
    }

}