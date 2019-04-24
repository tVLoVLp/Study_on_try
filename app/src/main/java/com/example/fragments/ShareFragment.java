package com.example.fragments;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;

import com.example.fragments.m_UI.HistoryAdapter;

public class ShareFragment extends Fragment {
    TextView advise;
    ArrayList<Integer> result = new ArrayList<Integer>();
    ArrayList<Integer> datae = new ArrayList<Integer>();
    LineChartView lineChartView;
    PieChartView pieChartView;
    ScrollView scrollView;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final RelativeLayout mRelativeLayout =(RelativeLayout)inflater.inflate(R.layout.graphics,container,false);
       // Bundle b3=getArguments();
       // int result_count=b3.getInt("value");
      //  int position=getActivity().getIntent().getExtras().getInt("new_value");
        result.add(0, 34);
        result.add(1, 20);
        result.add(2, 30);
        result.add(3, 25);
        result.add(4, 20);
       // result.add(5,result_count);


        datae.add(0, 1);
        datae.add(1, 2);
        datae.add(2, 3);
        datae.add(3, 4);
        datae.add(4, 5);


        TextView advise = mRelativeLayout.findViewById(R.id.advise_1);



        lineChartView = mRelativeLayout.findViewById(R.id.chart);


        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();


        Line line = new Line(yAxisValues).setColor(getResources().getColor(R.color.gold));

        for (int i = 0; i < datae.size(); i++) {
            axisValues.add(i, new AxisValue(i).setLabel(String.valueOf(datae.get(i))));
        }

        for (int i = 0; i < result.size(); i++) {
            yAxisValues.add(new PointValue(i, result.get(i)));
        }

        List lines = new ArrayList();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        axis.setTextSize(16);
        axis.setName(getResources().getString(R.string.data));
        axis.setTextColor(getResources().getColor(R.color.black));
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        yAxis.setName(getResources().getString(R.string.result));
        yAxis.setTextColor(getResources().getColor(R.color.black));
        yAxis.setTextSize(16);
        data.setAxisYLeft(yAxis);

        lineChartView.setLineChartData(data);
        Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
        viewport.top = 110;
        lineChartView.setMaximumViewport(viewport);
        lineChartView.setCurrentViewport(viewport);



        pieChartView = mRelativeLayout.findViewById(R.id.pieChartView);

        List pieData = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            pieData.add(new SliceValue(15, getActivity().getColor(R.color.redd)).setLabel("Math"));
            pieData.add(new SliceValue(25,  getActivity().getColor(R.color.pieData_second_color)).setLabel("English"));
            pieData.add(new SliceValue(10,  getActivity().getColor(R.color.pieData_third_color)).setLabel("Physics"));
        }



        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartData.setCenterText1(getResources().getString(R.string.subjects));
        pieChartData.setHasCenterCircle(true).setCenterText1FontSize(20).setCenterText1Color(Color.BLACK);
        pieChartView.setPieChartData(pieChartData);






        for (int i = 0; i < result.size(); i++) {
            if (result.get(i) <= 10) advise.setText(R.string.advise_1);
            else advise.setText(R.string.advise_1_1);
        }
        return mRelativeLayout;
    }

    }

