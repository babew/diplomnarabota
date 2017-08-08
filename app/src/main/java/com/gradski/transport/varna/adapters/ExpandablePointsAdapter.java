package com.gradski.transport.varna.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gradski.transport.varna.R;
import com.gradski.transport.varna.globalClasses.AnimatedExpandableListView;
import com.gradski.transport.varna.models.Point;

import java.util.ArrayList;

/**
 * Created by lyubomir.babev on 15/06/2017.
 */
public class ExpandablePointsAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {

    private ArrayList<Point> mPointsArrayList;

    public ExpandablePointsAdapter(ArrayList<Point> pointsArrayList, OnAnimationEndListener listener) {
        this.mPointsArrayList = pointsArrayList;
        setOnAnimationEndListenr(listener);
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return mPointsArrayList.get(listPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_point_info, null);

        TextView        address             = (TextView)        convertView.findViewById(R.id.address_text_view);
        TextView        weekdayWorkTime     = (TextView)        convertView.findViewById(R.id.weekday_work_time_text_view);
        TextView        weekdayBreakTime    = (TextView)        convertView.findViewById(R.id.weekday_break_time_text_view);
        TextView        saturdayWorkTime    = (TextView)        convertView.findViewById(R.id.saturday_work_time_text_view);
        TextView        saturdayBreakTime   = (TextView)        convertView.findViewById(R.id.saturday_break_time_text_view);
        TextView        sundayWorkTime      = (TextView)        convertView.findViewById(R.id.sunday_work_time_text_view);
        TextView        sundayBreakTime     = (TextView)        convertView.findViewById(R.id.sunday_break_time_text_view);

        Point point = mPointsArrayList.get(groupPosition);

        address.setText(point.getAddress());
        weekdayWorkTime.setText(point.getWeekdayWorkTime());
        weekdayBreakTime.setText(point.getWeekdayBreakTime());
        saturdayWorkTime.setText(point.getSaturdayWorkTime().equalsIgnoreCase("") ? "Почивен ден" : point.getSaturdayWorkTime());
        convertView.findViewById(R.id.saturday_break_time_layout).setVisibility(point.getSaturdayWorkTime().equalsIgnoreCase("") ? View.GONE : View.VISIBLE);
        convertView.findViewById(R.id.saturday_break_time_separator).setVisibility(point.getSaturdayWorkTime().equalsIgnoreCase("") ? View.GONE : View.VISIBLE);
        saturdayBreakTime.setText(point.getSaturdayBreakTime());
        sundayWorkTime.setText(point.getSundayWorkTime().equalsIgnoreCase("") ? "Почивен ден" : point.getSundayWorkTime());
        convertView.findViewById(R.id.sunday_break_time_layout).setVisibility(point.getSundayWorkTime().equalsIgnoreCase("") ? View.GONE : View.VISIBLE);
        convertView.findViewById(R.id.sunday_separator).setVisibility(point.getSundayWorkTime().equalsIgnoreCase("") ? View.GONE : View.VISIBLE);
        sundayBreakTime.setText(point.getSundayBreakTime());

        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.mPointsArrayList.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.mPointsArrayList.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_point, null);

        TextView name = (TextView) convertView.findViewById(R.id.name_text_view);

        name.setText(mPointsArrayList.get(listPosition).getName());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return false;
    }
}