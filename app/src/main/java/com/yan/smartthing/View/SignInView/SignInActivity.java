package com.yan.smartthing.View.SignInView;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.yan.smartthing.Model.AccountModel;
import com.yan.smartthing.Presenter.SignPresenter;
import com.yan.smartthing.R;


//注册活动
public class SignInActivity extends MvpActivity<SignInView,SignPresenter>implements SignInView {

    private EditText email;
    private EditText passwordTwice;
    private EditText password;
    private EditText userName;
    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_sigin_up);
        userName = (EditText) findViewById(R.id.edit_sign_username);
        password = (EditText) findViewById(R.id.edit_sign_password);
        passwordTwice = (EditText) findViewById(R.id.edit_sign_password_twice);
        phone = (EditText) findViewById(R.id.edit_sign_phone_number);
        email = (EditText) findViewById(R.id.edit_sign_email);
        Button sign = (Button) findViewById(R.id.button_sign_sign);

        assert sign != null;
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!userName.getText().toString().isEmpty()||!password.getText().toString().isEmpty()) {

                    if (password.getText().toString().equals(passwordTwice.getText().toString())) {
                        AccountModel accountModel = new AccountModel();
                        accountModel.setUsername(userName.getText().toString());
                        accountModel.setPassword(password.getText().toString());
                        accountModel.setEmail(email.getText().toString());
                        accountModel.setMobilePhoneNumber(phone.getText().toString());
                        getPresenter().onSign(SignInActivity.this, accountModel);
                    } else {
                        Toast.makeText(SignInActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(SignInActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    @NonNull
    @Override
    public SignPresenter createPresenter() {
        return new SignPresenter();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInputError() {

    }
}
