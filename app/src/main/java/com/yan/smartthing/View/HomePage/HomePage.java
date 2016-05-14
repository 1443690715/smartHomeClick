package com.yan.smartthing.View.HomePage;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.yan.smartthing.Model.Environment;

/**
 * 首页接口
 * Created by a7501 on 2016/5/12.
 */
public interface HomePage extends MvpView {
    void setMyDriverState(String text);
    boolean BlueToothIsOnline();
    void setEnvironment(Environment environment);
}
