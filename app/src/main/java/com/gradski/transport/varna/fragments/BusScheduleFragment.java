package com.gradski.transport.varna.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.gradski.transport.varna.R;
import com.gradski.transport.varna.globalClasses.Utils;
import com.gradski.transport.varna.models.StopSchedule;

import java.util.ArrayList;

/**
 * Created by Lyubomir Babev on 8/22/2017.
 */

public class BusScheduleFragment extends BaseFragment {

    private View            mView;
    private TableLayout     mOneWayTableLayout;
    private TableLayout     mOtherWayTableLayout;

    private ArrayList<StopSchedule> mOneWaySchedulesArrayList;
    private ArrayList<StopSchedule> mOtherWaySchedulesArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_bus_schedule, container, false);

        mOneWaySchedulesArrayList      = getArguments().getParcelableArrayList(Utils.INTENT_EXTRA_ONE_WAY_BUS_SCHEDULE);
        mOtherWaySchedulesArrayList    = getArguments().getParcelableArrayList(Utils.INTENT_EXTRA_OTHER_WAY_BUS_SCHEDULE);

        init();

        return mView;
    }

    private void init() {
        mOneWayTableLayout      = (TableLayout) mView.findViewById(R.id.one_way_table_layout);
        mOtherWayTableLayout    = (TableLayout) mView.findViewById(R.id.other_way_table_layout);

        setTableLayout(mOneWaySchedulesArrayList, mOneWayTableLayout);
        setTableLayout(mOtherWaySchedulesArrayList, mOtherWayTableLayout);
    }

    private void setTableLayout(ArrayList<StopSchedule> stopSchedules, TableLayout tableLayout) {
        for (int i = 0; i < stopSchedules.size(); i++) {
            TableRow tableRow = new TableRow(getContext());
            ArrayList<String> stopTimes = stopSchedules.get(i).getStopTimes();
            tableRow.addView(createTextView(i, stopSchedules.get(i).getName()));
            for (int j = 0; j < stopTimes.size(); j++)
                tableRow.addView(createTextView(i, stopTimes.get(j)));

            tableLayout.addView(tableRow);
        }
    }

    private TextView createTextView(int stopSchedulePosition, String stopTimeText) {
        int padding = (int) getResources().getDisplayMetrics().density * 2;
        TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TextView textView = new TextView(getContext());
        textView.setLayoutParams(params);
        textView.setText(stopTimeText);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(padding, padding, padding, padding);
        textView.setBackgroundResource(R.drawable.divider_list);
        if (stopSchedulePosition == 0) {
            textView.setTextColor(Color.BLACK);
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        }

        return textView;
    }
}
