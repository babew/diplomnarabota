package com.gradski.transport.varna.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gradski.transport.varna.R;

import java.util.ArrayList;

/**
 * Created by lyubomir.babev on 06/06/2017.
 */

public class BusLinesAdapter extends ArrayAdapter<String> {

    private Context             mContext;
    private ArrayList<String>   mBusLinesArrayList;
    private int                 mDisplayHeight;

    public BusLinesAdapter(@NonNull Context context, ArrayList<String> busLines) {
        super(context, R.layout.item_bus_line, busLines);
        this.mContext           = context;
        this.mBusLinesArrayList = busLines;

        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mDisplayHeight = displayMetrics.heightPixels;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.item_bus_line, null);
        }

        final LinearLayout  busLineLayout       = (LinearLayout)    view.findViewById(R.id.bus_line_layout);
        TextView            busLineTextView     = (TextView)        view.findViewById(R.id.bus_line_text_view);

        busLineLayout.setVisibility(View.VISIBLE);
        busLineTextView.setText(mBusLinesArrayList.get(position));

        return view;
    }

    @Override
    public int getCount() {
        return mBusLinesArrayList.size();
    }


}
