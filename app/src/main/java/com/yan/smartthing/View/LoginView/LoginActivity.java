package com.yan.smartthing.View.LoginView;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.yan.smartthing.Model.AccountModel;
import com.yan.smartthing.Model.LoginModel;
import com.yan.smartthing.Presenter.LoginPresenter;
import com.yan.smartthing.R;
import com.yan.smartthing.View.MainView.MainActivity;
import com.yan.smartthing.View.SignInView.SignInActivity;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class LoginActivity extends MvpActivity<LoginView,LoginPresenter> implements LoginView{


    private Button login;
    private EditText password;
    private EditText account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "eaa7078629b7640ca8b89ee0237036a7");
        AccountModel userInfo = BmobUser.getCurrentUser(this,AccountModel.class);
        if (userInfo !=null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }else {
            initView();
        }

    }

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        account = (EditText) findViewById(R.id.edit_account);
        password = (EditText) findViewById(R.id.edit_password);
        login = (Button) findViewById(R.id.button_login);
        TextView sign = (TextView) findViewById(R.id.text_sign);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginModel loginModel = new LoginModel();
                String StringAccount = account.getText().toString();
                String StringPassword = password.getText().toString();
                loginModel.setAccount(StringAccount);
                loginModel.setPassword(StringPassword);
                Log.e("11",loginModel.toString());
                getPresenter().onLogin(LoginActivity.this,loginModel);
            }
        });


        assert sign != null;
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignInActivity.class));
            }
        });

    }


    @Override
    public LoginModel onGetEditTextString() {
        LoginModel loginModel = new LoginModel();
        return loginModel;
    }

    @Override
    public void onSetLoginMessage(String account, String password) {

    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onLoginError() {

    }

    @Override
    public void onShowToast() {

    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
