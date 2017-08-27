package com.gradski.transport.varna.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gradski.transport.varna.R;
import com.gradski.transport.varna.models.BusLive;
import com.gradski.transport.varna.models.BusStop;

import java.util.ArrayList;
/**
 * Created by Lyubomir Babev on 8/22/2017.
 */

public class BusLiveAdapter extends ArrayAdapter<BusStop> {

    private Context             mContext;
    private ArrayList<BusStop>  mBusStopsArrayList;
    private ArrayList<BusLive>  mBusLivesArrayList;

    private BusLiveAdapterListener mListener;

    public interface BusLiveAdapterListener {
        void onStopClicked(BusStop busStop);
        void onBusClicked(BusLive busLive);
    }

    public BusLiveAdapter(Context context, ArrayList<BusStop> busStops, ArrayList<BusLive> busLives, BusLiveAdapterListener listener) {
        super(context, R.layout.item_bus_live);
        this.mContext           = context;
        this.mBusStopsArrayList = busStops;
        this.mBusLivesArrayList = busLives;
        this.mListener          = listener;
    }

    public void setBusLives(ArrayList<BusLive> busLives) {
        this.mBusLivesArrayList = busLives;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bus_live, null);

        View            topView             =                   convertView.findViewById(R.id.top_line_view);
        View            bottomView          =                   convertView.findViewById(R.id.bottom_line_view);
        TextView        nameTextView        = (TextView)        convertView.findViewById(R.id.stop_name_text_view);
        TextView        timeTextView        = (TextView)        convertView.findViewById(R.id.time_text_view);
        LinearLayout    cardViewsContainer  = (LinearLayout)    convertView.findViewById(R.id.card_views_container);
        ImageView       circleImageView     = (ImageView)       convertView.findViewById(R.id.circle_image_view);

        boolean isCardContainerVisible  = false;
        final BusStop busStop           = mBusStopsArrayList.get(position);

        topView.setVisibility(position == 0 ? View.INVISIBLE : View.VISIBLE);
        bottomView.setVisibility(position == mBusStopsArrayList.size() - 1 ? View.INVISIBLE : View.VISIBLE);
        nameTextView.setText(busStop.getName());
        timeTextView.setText(busStop.getNextBusLiveStopTime());
        circleImageView.setImageResource(R.drawable.circle_drawable);
        cardViewsContainer.removeAllViews();

        for (int i = 0; i < mBusLivesArrayList.size(); i++) {
            final BusLive busLive = mBusLivesArrayList.get(i);
            if (!isCardContainerVisible)
                isCardContainerVisible = busLive.getLastStopId() == busStop.getId() && position != mBusStopsArrayList.size() - 1;

            cardViewsContainer.setVisibility(isCardContainerVisible ? View.VISIBLE : View.GONE);
            if (busLive.getLastStopId() == busStop.getId() && position != mBusStopsArrayList.size() - 1) {
                View        cardView                = LayoutInflater.from(mContext).inflate(R.layout.layout_card_view, null);
                TextView    busTimeTextView         = (TextView)    cardView.findViewById(R.id.bus_time_text_view);
                TextView    offsetTimeTextView      = (TextView)    cardView.findViewById(R.id.offset_time_text_view);
                TextView    offsetMeterTextView     = (TextView)    cardView.findViewById(R.id.offset_meters_text_view);
                ImageView   busImageView            = (ImageView)   cardView.findViewById(R.id.bus_image_view);

                busTimeTextView.setText(busLive.getTime());
                offsetTimeTextView.setText(busLive.getOffsetTime());
                offsetTimeTextView.setTextColor(busLive.getOffsetTime().startsWith("+") ? Color.RED : Color.GREEN);
                circleImageView.setImageResource(busLive.isBusStopped() ? R.drawable.circle_selected_drawable : R.drawable.circle_drawable);
                busImageView.setImageResource(busLive.isBusStopped() ? R.drawable.bus_live_stopped_icon : R.drawable.bus_live_icon);
                offsetMeterTextView.setVisibility(busLive.isBusStopped() ? View.GONE : View.VISIBLE);
                offsetMeterTextView.setText(busLive.getOffsetMeters() + "м.");
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null)
                            mListener.onBusClicked(busLive);
                    }
                });

                cardViewsContainer.addView(cardView);
            }

            convertView.findViewById(R.id.stop_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null)
                        mListener.onStopClicked(busStop);
                }
            });
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return mBusStopsArrayList.size();
    }

}
