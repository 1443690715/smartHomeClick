package com.yan.smartthing.View.HomePage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.yan.smartthing.Model.BlueToothModel;
import com.yan.smartthing.Presenter.HomePagePresenter;
import com.yan.smartthing.R;

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


    public interface HomePageInterface {
        boolean isBlueToothOnline();
    }

    public void setHomePageInterface(HomePageInterface homePageInterface) {
        this.homePageInterface = homePageInterface;
    }

    @Override
    public HomePagePresenter createPresenter() {
        if (blueToothModel != null) {
            return new HomePagePresenter(blueToothModel);
        }
        return new HomePagePresenter(null);
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

        view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        myDrivesState = (TextView) view.findViewById(R.id.text_my_drives);
        Button addDrives = (Button) view.findViewById(R.id.button_add_drives);

        addDrives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDrivesState.setText("78852");
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        //  getPresenter().chickDrives();
    }

    @Override
    public void setMyDriverState(String text) {
        myDrivesState.setText(text);
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean BlueToothIsOnline() {
        // Log.e("HomeFragment", "004" + homePageInterface.isBlueToothOnline());
        return homePageInterface.isBlueToothOnline();
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.e("HomeFragment", "006");
            myDrivesState.setText("aaaa");

            Toast.makeText(getActivity(), "1111", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        //  getPresenter().chickDrives();
        new Thread() {
            @Override
            public void run() {
                while (!BlueToothIsOnline()) {
                }
                handler.sendEmptyMessage(0);
                Log.e("HomeFragment", "007");
            }
        }.start();
    }
}
