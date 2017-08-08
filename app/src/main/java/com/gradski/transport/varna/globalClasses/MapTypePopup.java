package com.gradski.transport.varna.globalClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.google.android.gms.maps.GoogleMap;
import com.gradski.transport.varna.R;

/**
 * Created by lyubomir.babev on 16/06/2017.
 */

public class MapTypePopup extends PopupWindow implements View.OnClickListener {
    private Context     mContext;
    private GoogleMap   mGoogleMap;

    public MapTypePopup(Context context, GoogleMap map) {
        super(context, null);
        this.mContext = context;
        this.mGoogleMap = map;

        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_map_type, null);
        setContentView(view);

        view.findViewById(R.id.normal_button).setOnClickListener(this);
        view.findViewById(R.id.satellite_button).setOnClickListener(this);
        view.findViewById(R.id.terrain_button).setOnClickListener(this);
        view.findViewById(R.id.hybrid_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normal_button:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                dismiss();
                break;
            case R.id.satellite_button:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                dismiss();
                break;
            case R.id.terrain_button:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                dismiss();
                break;
            case R.id.hybrid_button:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                dismiss();
                break;
        }
    }
}
