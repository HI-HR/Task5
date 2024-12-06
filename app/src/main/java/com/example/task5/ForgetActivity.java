package com.example.task5;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class ForgetActivity extends AppCompatActivity {

    private TextInputEditText mEtusername;
    private TextInputEditText mEtpassword;
    private Button mBtnForget;


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ForgetActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        initView();
        initEvent();

    }

    private void initView() {
        mEtpassword = findViewById(R.id.et_forget_password);
        mEtusername = findViewById(R.id.et_forget_username);
        mBtnForget = findViewById(R.id.btn_forget_forget);
    }

    private void initEvent() {
        mBtnForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Forget();
            }
        });


    }

    private void Forget() {
        String username = mEtusername.getText().toString();
        String userpassword = mEtpassword.getText().toString();
        //如果输入的用户名不为空，且用户名存在，密码不为空则重置密码
        if (username.isEmpty() || !SharedPreferencesUtils.isExist(ForgetActivity.this, username)) {
            Toast.makeText(ForgetActivity.this, "未找到该用户", Toast.LENGTH_SHORT).show();
        } else if (!checkInput(mEtpassword)) {
            Toast.makeText(ForgetActivity.this, "密码需大于6位，且含有数字和字母", Toast.LENGTH_SHORT).show();
            mEtpassword.setText("");
        } else {
            SharedPreferencesUtils.ChangeUserInfo(ForgetActivity.this, username, userpassword);
            Toast.makeText(ForgetActivity.this, "更改成功", Toast.LENGTH_SHORT).show();
            finish();

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