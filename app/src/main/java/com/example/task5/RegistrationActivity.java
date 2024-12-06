package com.example.task5;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {
    private Button mBtnReg;
    private EditText mEtPassword;
    private EditText mEtUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initView();
        initEvent();
    }

    private void initView() {
        mBtnReg = findViewById(R.id.btn_reg_reg);
        mEtUsername = findViewById(R.id.et_reg_username);
        mEtPassword = findViewById(R.id.et_reg_password);
    }

    private void initEvent() {
        mBtnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registration();
            }
        });

    }


    /***
     * 注册函数
     *      检查用户名是否存在
     *
     *
     */
    private void Registration() {
        if (mEtUsername.getText().toString().isEmpty()) {
            Toast.makeText(this, "账号不能为空", Toast.LENGTH_LONG).show();
        }
//        else if (mPassword.getText().toString().isEmpty()){
//            Toast.makeText(this,"密码不能为空",Toast.LENGTH_LONG).show();
//        }
        else if (!checkInput(mEtPassword)) {
            Toast.makeText(RegistrationActivity.this, "密码需大于6位，且含有数字和字母", Toast.LENGTH_SHORT).show();
            mEtPassword.setText("");
        } else {
            if (SharedPreferencesUtils.isExist(this, mEtUsername.getText().toString())) {
                Toast.makeText(this, "用户已存在，请更换用户名", Toast.LENGTH_LONG).show();
            } else {

                SharedPreferencesUtils.SaveUserInfo(this, mEtUsername.getText().toString(), mEtPassword.getText().toString());
                Toast.makeText(RegistrationActivity.this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }

    /**
     * 用于检测输入的密码是否符合规则
     * 密码大于6位，且含有数字和字母
     *
     * @return
     */
    private boolean checkInput(EditText mEtPassword) {
        int Digit = 0;//数字个数
        int Letter = 0;//字母个数
        boolean flag = false;
        String tempPassWord = mEtPassword.getText().toString();
        if (tempPassWord.length() > 6) {
            for (char c : tempPassWord.toCharArray()) {
                if (Character.isDigit(c)) {
                    Digit++;
                } else if (Character.isLetter(c)) {
                    Letter++;
                }
            }
        }

        if (Digit > 0 && Letter > 0) {
            flag = true;
        }
        return flag;
    }


}