package com.example.task5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private TextView mTvReg;
    private Button mBtnLogin;
    private EditText mEtPassword;
    private EditText mEtUsername;
    private CheckBox mCkRemember;
    private boolean shouldRememberPassword;
    private TextView mTvForget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        initView();
        initEvent();

    }

    private void initView() {
        mBtnLogin = findViewById(R.id.btn_main_login);
        mEtUsername = findViewById(R.id.et_main_username);
        mEtPassword = findViewById(R.id.et_main_password);
        mTvReg = findViewById(R.id.tv_main_reg);
        mCkRemember = findViewById(R.id.ck_main_remember);
        mTvForget = findViewById(R.id.tv_main_forget);
    }

    private void initEvent() {
        displaySavedPassword();


        mBtnLogin.setOnClickListener(v -> login());
        mTvReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
        mTvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgetActivity.startActivity(LoginActivity.this);
            }
        });

    }


    private void login() {
        shouldRememberPassword = mCkRemember.isChecked();
        String username = mEtUsername.getText().toString();
        String password = mEtPassword.getText().toString();

        //输入账号不为空，用户名存在且密码相等
        if (!username.isEmpty() && SharedPreferencesUtils.isExist(this, username) && password.equals(SharedPreferencesUtils.getUserInfo(this, username))) {
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();

            if (shouldRememberPassword) {
                rememberPassword(username, password);
            } else {
                clearSaved();
            }
            HomeActivity.startActivity(this, mEtUsername.getText().toString());
        } else {
            Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
            mEtPassword.setText("");
        }

    }

    public void displaySavedPassword() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("username", null);
        String savedPassword = sharedPreferences.getString("password", null);
        if (savedUsername != null && savedPassword != null) {
            mEtUsername.setText(savedUsername);
            mEtPassword.setText(savedPassword);
        } else {
            //mEtUsername.setText("");
            mEtUsername.setText(savedUsername);
            mEtPassword.setText("");
        }
    }

    private void rememberPassword(String username, String password) {
        SharedPreferences.Editor editor = getSharedPreferences("MyAppPreferences", MODE_PRIVATE).edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }

    private void clearSaved() {
        SharedPreferences.Editor editor = getSharedPreferences("MyAppPreferences", MODE_PRIVATE).edit();
        //editor.remove("username");
        editor.remove("password");
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        displaySavedPassword();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        shouldRememberPassword = mCkRemember.isChecked();
        if (!shouldRememberPassword){
            clearSaved();
        }

    }
}