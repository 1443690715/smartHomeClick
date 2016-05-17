package com.yan.smartthing.View.ListPage;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.yan.smartthing.Model.Environment;
import com.yan.smartthing.R;
import java.util.ArrayList;
import java.util.List;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class ListPage extends AppCompatActivity {

    private LineChart chart;
    private List<Environment> environmentList;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            getLineData();
        }
    };
    private LineChart shidu;
    private LineChart shuiwei;
    private LineChart pm2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_list_page);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("历史数据");
        chart = (LineChart) findViewById(R.id.chart_wendu);
        shidu = (LineChart) findViewById(R.id.chart_shidu);
        shuiwei = (LineChart) findViewById(R.id.chart_shuiwei);
        pm2 = (LineChart) findViewById(R.id.chart_pm_2_5);

        Button rushNew = (Button) findViewById(R.id.button_rush_new);

        assert rushNew != null;
        rushNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWenduData();


            }
        });

        initMyChart(chart);
        chart.setDescription("温度");
        initMyChart(shidu);
        shidu.setDescription("湿度");
        initMyChart(shuiwei);
        shuiwei.setDescription("水位");
        initMyChart(pm2);
        pm2.setDescription("PM2.5");
    }

    private void initMyChart(LineChart chart) {
        assert chart != null;
        chart.setDescriptionColor(Color.BLACK);
        chart.setNoDataText("请刷新");
        chart.setNoDataTextDescription("无数据");
        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setPinchZoom(true);
        chart.setDrawGridBackground(true);


        XAxis x = chart.getXAxis();
        x.setEnabled(true);
        x.resetLabelsToSkip();


        YAxis y = chart.getAxisLeft();
        chart.getAxisRight().setEnabled(false);
        y.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

       // LineData lineData = getLineData();
    }


    private LineData getLineData(){
        ArrayList<Entry> wendu = new ArrayList<Entry>();
        ArrayList<Entry> EntryShidu = new ArrayList<Entry>();
        ArrayList<Entry> entryShuiwei = new ArrayList<Entry>();
        ArrayList<Entry> entryPm2 = new ArrayList<Entry>();
        int count = 0;
        if (environmentList!=null)
        for (Environment environment :environmentList){
            Entry entry_wendu = new Entry(environment.getTemperature(),count);
            wendu.add(entry_wendu);
            Entry entry_shidu = new Entry(environment.getHumidity(),count);
            EntryShidu.add(entry_shidu);
            Entry entry_shuiwei = new Entry(environment.getWater(),count);
            entryShuiwei.add(entry_shuiwei);
            Entry entry_pm2 = new Entry(environment.getPm2(),count);
            entryPm2.add(entry_pm2);
            count++;
        }

        LineDataSet setComp1 = new LineDataSet(wendu, "温度");

        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp1.setDrawCircles(false);
        setComp1.setDrawValues(false);
        setComp1.setDrawCubic(true);
        setComp1.setDrawFilled(true);

        LineDataSet setShidu = new LineDataSet(EntryShidu, "湿度");

        setShidu.setAxisDependency(YAxis.AxisDependency.LEFT);
        setShidu.setDrawCircles(false);
        setShidu.setDrawValues(false);
        setShidu.setDrawCubic(true);
        setShidu.setDrawFilled(true);

        LineDataSet setShuiwei = new LineDataSet(entryShuiwei, "水位");

        setShuiwei.setAxisDependency(YAxis.AxisDependency.LEFT);
        setShuiwei.setDrawCircles(false);
        setShuiwei.setDrawValues(false);
        setShuiwei.setDrawCubic(true);
        setShuiwei.setDrawFilled(true);


        LineDataSet setPm2 = new LineDataSet(entryPm2, "PM2.5");

        setPm2.setAxisDependency(YAxis.AxisDependency.LEFT);
        setPm2.setDrawCircles(false);
        setPm2.setDrawValues(false);
        setPm2.setDrawCubic(true);
        setPm2.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(setComp1);

        ArrayList<ILineDataSet> dataSetsShidu = new ArrayList<ILineDataSet>();
        dataSetsShidu.add(setShidu);

        ArrayList<ILineDataSet> dataSetShuiwei = new ArrayList<ILineDataSet>();
        dataSetShuiwei.add(setShuiwei);

        ArrayList<ILineDataSet> dataSetPm2 = new ArrayList<ILineDataSet>();
        dataSetPm2.add(setPm2);

        ArrayList<String> xVals = new ArrayList<String>();

        for (int m = environmentList.size()-1;m>=0;m--){
            Environment environment = environmentList.get(m);
            xVals.add(environment.getCreatedAt().substring(5,16));
        }

        LineData data = new LineData(xVals, dataSets);

        LineData dataShidu = new LineData(xVals,dataSetsShidu);

        LineData dataShuiwei = new LineData(xVals,dataSetShuiwei);

        LineData dataPm2 = new LineData(xVals,dataSetPm2);
        chart.setData(data);
        chart.invalidate(); // refresh

        shidu.setData(dataShidu);
        shidu.invalidate();

        shuiwei.setData(dataShuiwei);
        shuiwei.invalidate();

        pm2.setData(dataPm2);
        pm2.invalidate();
        return data;

    }

    private void getWenduData(){
        BmobQuery<Environment> query = new BmobQuery<Environment>();
        query.addWhereEqualTo("temperature",null);
        query.setLimit(10);
        query.order("-createdAt");
        query.findObjects(this, new FindListener<Environment>() {
            @Override
            public void onSuccess(List<Environment> list) {
                environmentList = list;
                Log.e("List",""+environmentList.size()+environmentList.get(0).getCreatedAt());
                handler.sendEmptyMessage(0);


            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }


}
