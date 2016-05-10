package com.yan.smartthing.View.LoginView;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.yan.smartthing.Model.LoginModel;

/**
 * LoginView接口
 * Created by a7501 on 2016/5/10.
 */
public interface LoginView extends MvpView {
    LoginModel onGetEditTextString();
    void onSetLoginMessage(String account,String password);
}
