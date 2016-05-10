package com.yan.smartthing.Presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.yan.smartthing.Model.LoginModel;
import com.yan.smartthing.View.LoginView.LoginView;

/**
 *
 * Created by a7501 on 2016/5/10.
 */
public class LoginPresenter extends MvpBasePresenter<LoginView> {


    @Override
    public void attachView(LoginView view) {

    }

    @Override
    public void detachView(boolean retainInstance) {

    }

    public void onLogin(){
        LoginView view = getView();
        if (view!=null){
            LoginModel loginModel = view.onGetEditTextString();
            String account = loginModel.getAccount();
            String password = loginModel.getPassword();
        }
    }
}
