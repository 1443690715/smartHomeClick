package com.yan.smartthing.Presenter;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.yan.smartthing.Model.AccountModel;
import com.yan.smartthing.View.MainView.MainView;

import cn.bmob.v3.BmobUser;

/**
 * 主页
 * Created by a7501 on 2016/5/11.
 */
public class MainPresenter extends MvpBasePresenter<MainView> {

    public AccountModel getUserInfo(Context context){
        AccountModel accountModel = BmobUser.getCurrentUser(context,AccountModel.class);
        return accountModel;
    }

}
