package com.yan.smartthing.Presenter;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.yan.smartthing.Model.AccountModel;
import com.yan.smartthing.View.SignInView.SignInView;

import cn.bmob.v3.listener.SaveListener;

/**
 * 注册
 * Created by a7501 on 2016/5/11.
 */
public class SignPresenter extends MvpBasePresenter<SignInView> {
    @Override
    public void attachView(SignInView view) {
        super.attachView(view);
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }

    public void onSign(Context context, AccountModel accountModel){
       accountModel.signUp(context, new SaveListener() {
           @Override
           public void onSuccess() {
               getView().onSuccess();
           }

           @Override
           public void onFailure(int i, String s) {
                getView().onError(s);
           }
       });
    }
}
