package com.gradski.transport.varna.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gradski.transport.varna.R;
import com.gradski.transport.varna.models.BusLive;
import com.gradski.transport.varna.models.BusSchedule;

import java.util.ArrayList;

/**
 * Created by Lyubomir Babev on 8/26/2017.
 */

public class BusStopAdapter extends ArrayAdapter<Object> {
    private static final int TYPE_BUS_LIVE      = 0;
    private static final int TYPE_BUS_SCHEDULE  = 1;
    private static final int TYPES_COUNT        = 2;

    private Context                 mContext;
    private ArrayList<Object>       mItems = new ArrayList<>();

    public BusStopAdapter(Context context, ArrayList<BusLive> busLives, ArrayList<BusSchedule> schedules) {
        super(context, R.layout.item_bus_live_stop);
        this.mContext = context;
        mItems.addAll(busLives);
        mItems.addAll(schedules);
    }

    public void setData(ArrayList<BusLive> busLives, ArrayList<BusSchedule> schedules) {
        mItems.clear();
        mItems.addAll(busLives);
        mItems.addAll(schedules);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        int type;
        if (convertView == null) {
            holder = new ViewHolder();
            type = getItemViewType(position);
            if (type == TYPE_BUS_LIVE) {
                convertView                 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bus_live_stop, null);
                holder.numberTextView       = (TextView) convertView.findViewById(R.id.bus_number_text_view);
                holder.timeTextView         = (TextView) convertView.findViewById(R.id.bus_time_text_view);
                holder.offsetTimeTextView   = (TextView) convertView.findViewById(R.id.offset_time_text_view);
                holder.offsetMetersTextView = (TextView) convertView.findViewById(R.id.offset_meters_text_view);
            } else {
                convertView                 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stop_schedule, null);
                holder.numberTextView       = (TextView) convertView.findViewById(R.id.bus_number_text_view);
                holder.schedulesTextView    = (TextView) convertView.findViewById(R.id.schedules_text_view);
            }
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder)convertView.getTag();

        if (mItems.get(position) instanceof BusLive) {
            BusLive busLive = (BusLive)mItems.get(position);

            holder.numberTextView.setText("№" + String.valueOf(busLive.getNumber()));
            holder.timeTextView.setText("Разписание: " + busLive.getTime());
            holder.offsetTimeTextView.setText("След: " + busLive.getOffsetTime());
            holder.offsetMetersTextView.setText("Дистанция: " + String.valueOf(busLive.getOffsetMeters()) + "м");
        } else {
            BusSchedule busSchedule = (BusSchedule)mItems.get(position);

            holder.numberTextView.setText("№" + String.valueOf(busSchedule.getNumber()));
            holder.schedulesTextView.setText(busSchedule.getSchedules());
        }

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position) instanceof BusLive ? TYPE_BUS_LIVE : TYPE_BUS_SCHEDULE;
    }

    @Override
    public int getViewTypeCount() {
        return TYPES_COUNT;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView numberTextView;
        public TextView timeTextView;
        public TextView offsetTimeTextView;
        public TextView offsetMetersTextView;
        public TextView schedulesTextView;
    }
}
