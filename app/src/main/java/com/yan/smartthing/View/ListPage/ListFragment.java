package com.yan.smartthing.View.ListPage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yan.smartthing.R;

/**
 *
 * Created by a7501 on 2016/5/15.
 */
public class ListFragment extends Fragment {

   // private static ListFragment listFragment;

//    public static ListFragment getListFragment(){
//        if (listFragment == null)
//            listFragment = new ListFragment();
//
//        return listFragment;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        return view;
    }
}
