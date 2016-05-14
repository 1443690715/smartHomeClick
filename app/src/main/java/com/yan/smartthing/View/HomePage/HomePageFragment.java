package com.yan.smartthing.View.HomePage;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.yan.smartthing.Model.BlueToothModel;
import com.yan.smartthing.Model.Environment;
import com.yan.smartthing.Presenter.HomePagePresenter;
import com.yan.smartthing.R;
import com.yan.smartthing.View.MainView.MainActivity;

/**
 * 主页
 * Created by a7501 on 2016/5/12.
 */
public class HomePageFragment extends MvpFragment<HomePage, HomePagePresenter> implements HomePage {

    private BlueToothModel blueToothModel;
    private TextView myDrivesState;
    private boolean BlueToothIsOnline = false;

    private HomePageInterface homePageInterface;
    private View view;

    private boolean is = true;
    private Button addDrives;
    private static HomePageFragment homePageFragment;
    private TableLayout tableLayout;
    private TextView wendu;
    private TextView shidu;
    private TextView shuiwei;


    public interface HomePageInterface {
        boolean isBlueToothOnline();
    }

    public void setHomePageInterface(HomePageInterface homePageInterface) {
        this.homePageInterface = homePageInterface;
    }

    @Override
    public HomePagePresenter createPresenter() {
        if (blueToothModel != null) {
            return new HomePagePresenter(blueToothModel,getActivity().getApplicationContext());
        }
        return new HomePagePresenter(null,getActivity().getApplicationContext());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        blueToothModel = BlueToothModel.getInstance(getActivity());
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.e("HomeFragment", "onCreateView");
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        myDrivesState = (TextView) view.findViewById(R.id.text_my_drives);
        addDrives = (Button) view.findViewById(R.id.button_add_drives);

        tableLayout = (TableLayout) view.findViewById(R.id.home_table_layout);
        tableLayout.setStretchAllColumns(true);

        wendu = (TextView) view.findViewById(R.id.text_table_wendu);
        shidu = (TextView) view.findViewById(R.id.text_table_shidu);
        shuiwei = (TextView) view.findViewById(R.id.text_table_shuiwei);

        addDrives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("HomePage", "123456");
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void setMyDriverState(String text) {
        myDrivesState.setText(text);
        Toast.makeText(getActivity(), "设备连接成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean BlueToothIsOnline() {
        return homePageInterface.isBlueToothOnline();
    }

    @Override
    public void setEnvironment(Environment environment) {
        if (environment != null) {
            wendu.setText(String.valueOf(environment.getTemperature()));
            shidu.setText(String.valueOf(environment.getHumidity()));
            shuiwei.setText(String.valueOf(environment.getWater()));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("HomeFragment", "onResume");
        getPresenter().chickDrives();

    }

    public static HomePageFragment getInstance() {

        if (homePageFragment == null)
            homePageFragment = new HomePageFragment();

        return homePageFragment;
    }


}







