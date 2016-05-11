package com.yan.smartthing.Presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.yan.smartthing.Model.AccountModel;
import com.yan.smartthing.Model.LoginModel;
import com.yan.smartthing.View.LoginView.LoginView;
import com.yan.smartthing.View.MainView.MainActivity;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by a7501 on 2016/5/10.
 */
public class LoginPresenter extends MvpBasePresenter<LoginView> {


    @Override
    public void attachView(LoginView view) {
        super.attachView(view);

    }

    @Override
    public void detachView(boolean retainInstance) {

    }

    public void onLogin(final Context context, LoginModel loginModel) {

        String account = loginModel.getAccount();
        String password = loginModel.getPassword();

        AccountModel accountModel = new AccountModel();
        accountModel.setUsername(account);
        accountModel.setPassword(password);
        accountModel.login(context, new SaveListener() {
            @Override
            public void onSuccess() {
                context.startActivity(new Intent(context, MainActivity.class));
            }

            @Override
            public void onFailure(int i, String s) {

            }
        });

        if (getView() != null)
            Log.e("00", loginModel.toString());

    }
}
