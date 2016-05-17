package com.yan.smartthing.View.MainView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.yan.smartthing.Model.AccountModel;
import com.yan.smartthing.Model.BlueToothModel;
import com.yan.smartthing.Presenter.MainPresenter;
import com.yan.smartthing.R;
import com.yan.smartthing.View.HomePage.HomePage;
import com.yan.smartthing.View.HomePage.HomePageFragment;
import com.yan.smartthing.View.ListPage.ListFragment;
import com.yan.smartthing.View.ListPage.ListPage;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class MainActivity extends MvpActivity<MainView, MainPresenter>
        implements MainView {

    private TextView leftTitleUserName;
    private TextView leftTitleEmail;
    private AccountModel userInfo;

    private BlueToothModel blueToothModel;
    private HomePageFragment homePageFragment;

    private BlueToothOnline blueToothOnline;
    private static boolean first = true;
    private ListFragment listFragment;
    private FragmentTransaction fragmentTransaction;

    public void setBlueToothOnline(BlueToothOnline blueToothOnline) {
        this.blueToothOnline = blueToothOnline;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    private void initView() {
        setContentView(R.layout.activity_main_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("首页");
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

       // NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
       // navigationView.setNavigationItemSelectedListener(this);


      //  View headerView = navigationView.getHeaderView(0);
      //  leftTitleUserName = (TextView) headerView.findViewById(R.id.text_left_title_username);
      //  leftTitleEmail = (TextView) headerView.findViewById(R.id.text_left_title_email);

        blueToothModel = BlueToothModel.getInstance(getApplicationContext());

        Log.e("blueModel", "" + blueToothModel.toString());

        initTitle();
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onResume() {
        super.onResume();
        initFragment();
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        fragmentTransaction = supportFragmentManager.beginTransaction();


        homePageFragment = HomePageFragment.getInstance();
     //   listFragment = ListFragment.getListFragment();

        Log.e("mainFragment", homePageFragment.toString());

        if (first) {
            fragmentTransaction.replace(R.id.frame_layout_main, homePageFragment);
            // fragmentTransaction.add(R.id.frame_layout_main, listFragment);
        }

        fragmentTransaction.commit();
        first = false;

        homePageFragment.setHomePageInterface(new HomePageFragment.HomePageInterface() {
            @Override
            public boolean isBlueToothOnline() {
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            blueToothModel.send("6666", true);
            blueToothData();
        }
        if (id == R.id.action_select_bluetooth) {
            Intent intent = new Intent(getApplicationContext(), DeviceList.class);
            startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
        }

        if (id == R.id.action_select_list){
            startActivity(new Intent(this, ListPage.class));
        }

        return true;
    }

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//
//            HomePageFragment homePageFragment = new HomePageFragment();
//            FragmentManager supportFragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
//            fragmentTransaction.add(R.id.frame_layout_main, homePageFragment);
//            fragmentTransaction.commit();
//
//
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//            Log.e("Main", "89562");
//            listFragment = new ListFragment();
//             {
//                FragmentManager supportFragmentManager = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
//                fragmentTransaction.add(R.id.frame_layout_main, listFragment);
//                fragmentTransaction.commit();
//            }
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//
//
//        return true;
//    }

    /**
     * 对左侧滑栏标题初始化
     */
    @Override
    public void initTitle() {
       // userInfo = getPresenter().getUserInfo(this);
       // leftTitleUserName.setText(userInfo.getUsername());
       // leftTitleEmail.setText(userInfo.getEmail());

    }

    @Override
    public boolean isBlueToothOnline() {
        return false;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK) {
                blueToothModel.connect(data);
                homePageFragment.setHomePageInterface(new HomePageFragment.HomePageInterface() {
                    @Override
                    public boolean isBlueToothOnline() {
                        Log.e("Main", "003" + "\n");
                        return true;
                    }
                });
            }
            blueToothData();
        } else if (requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                blueToothModel.setupService();
                blueToothModel.startService(BluetoothState.DEVICE_OTHER);
                Log.e("Main", "002" + "\n");
                blueToothData();
            } else {
                // Do something if user doesn't choose any device (Pressed back)
            }

        }
    }


    private void blueToothData() {

    }


    public interface BlueToothOnline {
        void bluetoohisonline(boolean is);
    }
}
