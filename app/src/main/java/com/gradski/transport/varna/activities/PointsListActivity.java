package com.gradski.transport.varna.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.gradski.transport.varna.R;
import com.gradski.transport.varna.adapters.ExpandablePointsAdapter;
import com.gradski.transport.varna.globalClasses.AnimatedExpandableListView;
import com.gradski.transport.varna.globalClasses.MapTypePopup;
import com.gradski.transport.varna.models.Point;

import java.util.ArrayList;

/**
 * Created by lyubomir.babev on 01/06/2017.
 */

public class PointsListActivity extends BaseActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, AnimatedExpandableListView.AnimatedExpandableListAdapter.OnAnimationEndListener, View.OnClickListener {

    private GoogleMap                   mGoogleMap;
    private ArrayList<Point>            mPointsArrayList    = new ArrayList<>();
    private AnimatedExpandableListView  mPointsListView;
    private ExpandablePointsAdapter     mAdapter;

    private int         mLastExpandedPosition   = -1;
    private boolean     mNeedSetSelection       = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_points_list);

        getPoints();
        init();
        setMapFragment();
    }

    private void init() {
        mPointsListView     = (AnimatedExpandableListView) findViewById(R.id.points_list_view);
        mAdapter            = new ExpandablePointsAdapter(mPointsArrayList, this);

        mPointsListView.setAdapter(mAdapter);
        mPointsListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                onPointSelected(groupPosition);
                return true;
            }

        });
        mPointsListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                mPointsListView.setSelection(0);
            }
        });
        mPointsListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (mNeedSetSelection)
                    mPointsListView.setSelection(mLastExpandedPosition);
            }
        });
        findViewById(R.id.back_arrow_image_view).setOnClickListener(this);
        findViewById(R.id.map_image_view).setOnClickListener(this);
    }

    private void setMapFragment() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
    }

    private void setMapUiSettings() {
        mGoogleMap.setOnMarkerClickListener(this);
        mGoogleMap.getUiSettings().setRotateGesturesEnabled(false);
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        mGoogleMap.getUiSettings().setZoomGesturesEnabled(true);
        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
    }

    private void getPoints() {
        mPointsArrayList.add(new Point(0, "Пункт Централна станция", 43.203639, 27.908372, "ул.\"Русе\" ( обръщача до училище “Димчо Дебелянов”)", "08.00 - 18.45 часа", "12.00 - 14.00 часа", "08.00 - 16.30 часа", "12.00 - 12.30 часа", "08.00 - 16.30 часа", "12.00 - 12.30 часа"));
        mPointsArrayList.add(new Point(1, "Пункт Гаража", 43.229560, 27.890827, "Варна, ул. \"Тролейна\" 48", "09.00 - 16.30 часа", "12.00 - 12.30 часа", "", "", "", ""));
        mPointsArrayList.add(new Point(2, "Пункт кв.Галата, сп. Центъра", 43.164821, 27.934127, "ул.\"Кап. 1-ви ранг Г. Купов\", сп. \"Галата център\"", "08.30 - 18.00 часа", "12.00 - 12.30 часа и от 16.00 - 16.30 часа", "08.30 - 16.30 часа", "12.00 - 12.30 часа  и от 15.00 - 15.30 часа", "", ""));
        mPointsArrayList.add(new Point(3, "Пункт Аспарухово", 43.184110, 27.892207, "кв. \"Аспарухово\", бул. \"Народни будители\", сп.ДКЦ 2", "08.30 - 18.00 часа", "12.00 - 12.30 часа и от 16.00 - 16.30 часа", "08.30 - 16.30 часа", "12.00 - 12.30 часа  и от 15.00 - 15.30 часа", "", ""));
        mPointsArrayList.add(new Point(4, "Пункт Вл. Варненчик", 43.245183, 27.847358, "ж.к.\"Вл. Варненчик\", бул. \"Св. Елена\" - пред ПТТС 23", "08.30 - 18.00 часа", "12.00 - 12.30 часа и от 16.00 - 16.30 часа", "08.30 - 16.00 часа", "12.00 - 12.30 часа и от 15.00 - 15.30 часа", "", ""));
        mPointsArrayList.add(new Point(5, "Пункт Север", 43.239435, 27.872045, "бул. \"3-ти март\", сп.ТИС Север", "08.30 - 18.00 часа", "12.00 - 12.30 часа и от 16.00 - 16.30 часа", "08.30 - 16.00 часа", "12.00 - 12.30 часа и от 15.00 - 15.30 часа", "", ""));
        mPointsArrayList.add(new Point(6, "Пункт Младост", 43.226238, 27.889245, "бул. \"Сливница\" , спирка Младост", "08.30 - 18.00 часа ", "12.00 - 12.30 часа  и от 16.00 - 16.30 часа", "08.30 - 16.00 часа", "12.00 - 12.30 часа и от 15.00 - 15.30 часа", "", ""));
    }

    private void setLocationEnabled() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
        else
            mGoogleMap.setMyLocationEnabled(true);

    }

    private void addPointsToMap() {
        for (int i = 0; i <= mPointsArrayList.size() - 1; i++) {
            Point currentPoint = mPointsArrayList.get(i);
            mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(currentPoint.getLatitude(), currentPoint.getLongitude())).title(currentPoint.getName()));
        }
    }

    private void setCameraPosition(double latitude, double longitude, int zoom) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(zoom).build();
        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void onPointSelected(final int position) {
        Handler handler = new Handler();
        if (mPointsListView.isGroupExpanded(position)) {
            mPointsListView.collapseGroupWithAnimation(position);
            setCameraPosition(43.226238, 27.889245, 11);
        } else {
            if (mLastExpandedPosition != -1 && position != mLastExpandedPosition)
                mPointsListView.collapseGroup(mLastExpandedPosition);
            mLastExpandedPosition = position;
            setCameraPosition(mPointsArrayList.get(position).getLatitude(), mPointsArrayList.get(position).getLongitude(), 15);
            if (position < mPointsListView.getFirstVisiblePosition()) {
                mPointsListView.setSelection(position);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mNeedSetSelection = false;
                        mPointsListView.expandGroupWithAnimation(position);
                    }
                }, 100);
            } else {
                mNeedSetSelection = true;
                mPointsListView.expandGroupWithAnimation(position);
            }
        }
    }

    private void showMapTypePopup(View view) {
        MapTypePopup popup = new MapTypePopup(this, mGoogleMap);
        popup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popup.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popup.setFocusable(true);
        popup.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.popup_background));
        popup.setAnimationStyle(R.style.PopupAnimationGoDown);
        popup.showAsDropDown(view, view.getWidth(), -view.getHeight());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_arrow_image_view:
                super.onBackPressed();
                break;
            case R.id.map_image_view:
                showMapTypePopup(view);
                break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        setMapUiSettings();
        addPointsToMap();
        setLocationEnabled();
        setCameraPosition(43.226238, 27.889245, 11);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            mGoogleMap.setMyLocationEnabled(true);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        for (int i = 0; i <= mPointsArrayList.size() - 1; i++) {
            if (marker.getTitle().equalsIgnoreCase(mPointsArrayList.get(i).getName()))
                onPointSelected(i);
        }
        return true;
    }

    @Override
    public void onExpandAnimationEnd() {
        if (mNeedSetSelection)
            mPointsListView.setSelection(mLastExpandedPosition);
    }

    @Override
    public void onCollapseAnimationEnd() {
        mPointsListView.setSelection(0);
    }
}
