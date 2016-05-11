package com.yan.smartthing.View.SignInView;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * 注册
 * Created by a7501 on 2016/5/11.
 */
public interface SignInView extends MvpView {
    void onSuccess();
    void onError(String error);
    void onInputError();
}
