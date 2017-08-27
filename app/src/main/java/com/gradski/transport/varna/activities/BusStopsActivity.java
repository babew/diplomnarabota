package com.gradski.transport.varna.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.gradski.transport.varna.R;
import com.gradski.transport.varna.adapters.BusStopAdapter;
import com.gradski.transport.varna.globalClasses.MapTypePopup;
import com.gradski.transport.varna.models.BusLive;
import com.gradski.transport.varna.models.BusSchedule;
import com.gradski.transport.varna.models.BusStop;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Lyubomir Babev on 8/21/2017.
 */

public class BusStopsActivity extends BaseActivity implements OnMapReadyCallback, View.OnClickListener, GoogleMap.OnCameraMoveListener {

    private GoogleMap               mGoogleMap;
    private ListView                mLiveListView;
    private BusStopAdapter          mAdapter;

    private BusStop                     mBusStop;
    private ArrayList<BusLive>          mBusLiveArrayList           = new ArrayList<>();
    private ArrayList<Marker>           mMarkersArrayList           = new ArrayList<>();
    private ArrayList<BusSchedule>      mSchedulesArrayList         = new ArrayList<>();
    private ArrayList<LatLng>           mRouteLocationsArrayList    = new ArrayList<>();

    private long        mStartTime          = 0;
    private boolean     mShouldUpdateData   = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bus_stops);

        getData();
        init();
        setMapFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mShouldUpdateData = true;
        updateBusLives();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mShouldUpdateData = false;
    }

    private void init() {
        mLiveListView       = (ListView) findViewById(R.id.live_list_view);

        setAdapter();

        findViewById(R.id.back_arrow_image_view).setOnClickListener(this);
        findViewById(R.id.map_image_view).setOnClickListener(this);
    }

    private void getData() {
        mRouteLocationsArrayList.add(new LatLng(43.251626, 27.850909));
        mRouteLocationsArrayList.add(new LatLng(43.251762, 27.850212));
        mRouteLocationsArrayList.add(new LatLng(43.251442, 27.849893));
        mRouteLocationsArrayList.add(new LatLng(43.251044, 27.849490));
        mRouteLocationsArrayList.add(new LatLng(43.250600, 27.849146));
        mRouteLocationsArrayList.add(new LatLng(43.250247, 27.848929));
        mRouteLocationsArrayList.add(new LatLng(43.249778, 27.848610));
        mRouteLocationsArrayList.add(new LatLng(43.249400, 27.848417));
        mRouteLocationsArrayList.add(new LatLng(43.248909, 27.848172));
        mRouteLocationsArrayList.add(new LatLng(43.248301, 27.847891));
        mRouteLocationsArrayList.add(new LatLng(43.247677, 27.847679));
        mRouteLocationsArrayList.add(new LatLng(43.247112, 27.847555));
        mRouteLocationsArrayList.add(new LatLng(43.246834, 27.847510));
        mRouteLocationsArrayList.add(new LatLng(43.246430, 27.847566));
        mRouteLocationsArrayList.add(new LatLng(43.246137, 27.847808));
        mRouteLocationsArrayList.add(new LatLng(43.246077, 27.848341));
        mRouteLocationsArrayList.add(new LatLng(43.246106, 27.849154));
        mRouteLocationsArrayList.add(new LatLng(43.246079, 27.850008));
        mRouteLocationsArrayList.add(new LatLng(43.246052, 27.851073));
        mRouteLocationsArrayList.add(new LatLng(43.245908, 27.852288));
        mRouteLocationsArrayList.add(new LatLng(43.245886, 27.853409));
        mRouteLocationsArrayList.add(new LatLng(43.245812, 27.854255));
        mRouteLocationsArrayList.add(new LatLng(43.245720, 27.855154));
        mRouteLocationsArrayList.add(new LatLng(43.245527, 27.856349));
        mRouteLocationsArrayList.add(new LatLng(43.245120, 27.857390));
        mRouteLocationsArrayList.add(new LatLng(43.244893, 27.857841));
        mRouteLocationsArrayList.add(new LatLng(43.244291, 27.857813));
        mRouteLocationsArrayList.add(new LatLng(43.243776, 27.857765));
        mRouteLocationsArrayList.add(new LatLng(43.243236, 27.857724));
        mRouteLocationsArrayList.add(new LatLng(43.242454, 27.857692));
        mRouteLocationsArrayList.add(new LatLng(43.241684, 27.857638));
        mRouteLocationsArrayList.add(new LatLng(43.241469, 27.858054));
        mRouteLocationsArrayList.add(new LatLng(43.241424, 27.860526));
        mRouteLocationsArrayList.add(new LatLng(43.241455, 27.862133));
        mRouteLocationsArrayList.add(new LatLng(43.241446, 27.863668));
        mRouteLocationsArrayList.add(new LatLng(43.241314, 27.864748));
        mRouteLocationsArrayList.add(new LatLng(43.240844, 27.866297));
        mRouteLocationsArrayList.add(new LatLng(43.240150, 27.868480));
        mRouteLocationsArrayList.add(new LatLng(43.239586, 27.870213));
        mRouteLocationsArrayList.add(new LatLng(43.239290, 27.871245));
        mRouteLocationsArrayList.add(new LatLng(43.238805, 27.871124));
        mRouteLocationsArrayList.add(new LatLng(43.237608, 27.870885));
        mRouteLocationsArrayList.add(new LatLng(43.236434, 27.870657));
        mRouteLocationsArrayList.add(new LatLng(43.235837, 27.870554));
        mRouteLocationsArrayList.add(new LatLng(43.234762, 27.870277));
        mRouteLocationsArrayList.add(new LatLng(43.233729, 27.869979));
        mRouteLocationsArrayList.add(new LatLng(43.233811, 27.871433));
        mRouteLocationsArrayList.add(new LatLng(43.233961, 27.872615));
        mRouteLocationsArrayList.add(new LatLng(43.234106, 27.873902));
        mRouteLocationsArrayList.add(new LatLng(43.234306, 27.875444));
        mRouteLocationsArrayList.add(new LatLng(43.234384, 27.876665));
        mRouteLocationsArrayList.add(new LatLng(43.234374, 27.878035));
        mRouteLocationsArrayList.add(new LatLng(43.234262, 27.879332));
        mRouteLocationsArrayList.add(new LatLng(43.234123, 27.880402));
        mRouteLocationsArrayList.add(new LatLng(43.234028, 27.881212));
        mRouteLocationsArrayList.add(new LatLng(43.233791, 27.882641));
        mRouteLocationsArrayList.add(new LatLng(43.233663, 27.883629));
        mRouteLocationsArrayList.add(new LatLng(43.233447, 27.885209));
        mRouteLocationsArrayList.add(new LatLng(43.233284, 27.886231));
        mRouteLocationsArrayList.add(new LatLng(43.233174, 27.887307));
        mRouteLocationsArrayList.add(new LatLng(43.233016, 27.888414));
        mRouteLocationsArrayList.add(new LatLng(43.232651, 27.890071));
        mRouteLocationsArrayList.add(new LatLng(43.232013, 27.890075));
        mRouteLocationsArrayList.add(new LatLng(43.231343, 27.889912));
        mRouteLocationsArrayList.add(new LatLng(43.230667, 27.889836));
        mRouteLocationsArrayList.add(new LatLng(43.229889, 27.889642));
        mRouteLocationsArrayList.add(new LatLng(43.229260, 27.889480));
        mRouteLocationsArrayList.add(new LatLng(43.228405, 27.889293));
        mRouteLocationsArrayList.add(new LatLng(43.227671, 27.889168));
        mRouteLocationsArrayList.add(new LatLng(43.226702, 27.888754));
        mRouteLocationsArrayList.add(new LatLng(43.226479, 27.888900));
        mRouteLocationsArrayList.add(new LatLng(43.226156, 27.889506));
        mRouteLocationsArrayList.add(new LatLng(43.225722, 27.890187));
        mRouteLocationsArrayList.add(new LatLng(43.225083, 27.891126));
        mRouteLocationsArrayList.add(new LatLng(43.224079, 27.892250));
        mRouteLocationsArrayList.add(new LatLng(43.223268, 27.891074));
        mRouteLocationsArrayList.add(new LatLng(43.222715, 27.890479));
        mRouteLocationsArrayList.add(new LatLng(43.221679, 27.889781));
        mRouteLocationsArrayList.add(new LatLng(43.220786, 27.889084));
        mRouteLocationsArrayList.add(new LatLng(43.220232, 27.888683));
        mRouteLocationsArrayList.add(new LatLng(43.219719, 27.888325));
        mRouteLocationsArrayList.add(new LatLng(43.219262, 27.888118));
        mRouteLocationsArrayList.add(new LatLng(43.218882, 27.888906));
        mRouteLocationsArrayList.add(new LatLng(43.218514, 27.889802));
        mRouteLocationsArrayList.add(new LatLng(43.217733, 27.891498));
        mRouteLocationsArrayList.add(new LatLng(43.217447, 27.892133));
        mRouteLocationsArrayList.add(new LatLng(43.217110, 27.892870));
        mRouteLocationsArrayList.add(new LatLng(43.216899, 27.893280));
        mRouteLocationsArrayList.add(new LatLng(43.216664, 27.893894));
        mRouteLocationsArrayList.add(new LatLng(43.216353, 27.894585));
        mRouteLocationsArrayList.add(new LatLng(43.216016, 27.895337));
        mRouteLocationsArrayList.add(new LatLng(43.215582, 27.896242));
        mRouteLocationsArrayList.add(new LatLng(43.215328, 27.896917));
        mRouteLocationsArrayList.add(new LatLng(43.214922, 27.897807));
        mRouteLocationsArrayList.add(new LatLng(43.214605, 27.898528));
        mRouteLocationsArrayList.add(new LatLng(43.214370, 27.899075));
        mRouteLocationsArrayList.add(new LatLng(43.213728, 27.900460));
        mRouteLocationsArrayList.add(new LatLng(43.213436, 27.901190));
        mRouteLocationsArrayList.add(new LatLng(43.213132, 27.901950));
        mRouteLocationsArrayList.add(new LatLng(43.212886, 27.902725));
        mRouteLocationsArrayList.add(new LatLng(43.212588, 27.903885));
        mRouteLocationsArrayList.add(new LatLng(43.212212, 27.905039));
        mRouteLocationsArrayList.add(new LatLng(43.211900, 27.906058));
        mRouteLocationsArrayList.add(new LatLng(43.211285, 27.907274));
        mRouteLocationsArrayList.add(new LatLng(43.210902, 27.907704));
        mRouteLocationsArrayList.add(new LatLng(43.210469, 27.907957));
        mRouteLocationsArrayList.add(new LatLng(43.209910, 27.908361));
        mRouteLocationsArrayList.add(new LatLng(43.209448, 27.908615));
        mRouteLocationsArrayList.add(new LatLng(43.208943, 27.908929));
        mRouteLocationsArrayList.add(new LatLng(43.208407, 27.909250));
        mRouteLocationsArrayList.add(new LatLng(43.207848, 27.909593));
        mRouteLocationsArrayList.add(new LatLng(43.207262, 27.909896));
        mRouteLocationsArrayList.add(new LatLng(43.206574, 27.910305));
        mRouteLocationsArrayList.add(new LatLng(43.205967, 27.910430));
        mRouteLocationsArrayList.add(new LatLng(43.205026, 27.910632));
        mRouteLocationsArrayList.add(new LatLng(43.204920, 27.911186));
        mRouteLocationsArrayList.add(new LatLng(43.205179, 27.911934));
        mRouteLocationsArrayList.add(new LatLng(43.205494, 27.912799));
        mRouteLocationsArrayList.add(new LatLng(43.205762, 27.913467));
        mRouteLocationsArrayList.add(new LatLng(43.206211, 27.914621));
        mRouteLocationsArrayList.add(new LatLng(43.206529, 27.915575));
        mRouteLocationsArrayList.add(new LatLng(43.206899, 27.916550));
        mRouteLocationsArrayList.add(new LatLng(43.207252, 27.917458));
        mRouteLocationsArrayList.add(new LatLng(43.207615, 27.917971));
        mRouteLocationsArrayList.add(new LatLng(43.208217, 27.918757));
        mRouteLocationsArrayList.add(new LatLng(43.208798, 27.919565));
        mRouteLocationsArrayList.add(new LatLng(43.209283, 27.920177));
        mRouteLocationsArrayList.add(new LatLng(43.209996, 27.921185));
        mRouteLocationsArrayList.add(new LatLng(43.210549, 27.921847));
        mRouteLocationsArrayList.add(new LatLng(43.211095, 27.922558));
        mRouteLocationsArrayList.add(new LatLng(43.211683, 27.923345));
        mRouteLocationsArrayList.add(new LatLng(43.212254, 27.924098));
        mRouteLocationsArrayList.add(new LatLng(43.212773, 27.924719));
        mRouteLocationsArrayList.add(new LatLng(43.213305, 27.925254));
        mRouteLocationsArrayList.add(new LatLng(43.213811, 27.925888));
        mRouteLocationsArrayList.add(new LatLng(43.214443, 27.926774));
        mRouteLocationsArrayList.add(new LatLng(43.215012, 27.927783));
        mRouteLocationsArrayList.add(new LatLng(43.215458, 27.928676));
        mRouteLocationsArrayList.add(new LatLng(43.215977, 27.929578));
        mRouteLocationsArrayList.add(new LatLng(43.216536, 27.930612));
        mRouteLocationsArrayList.add(new LatLng(43.217064, 27.931611));
        mRouteLocationsArrayList.add(new LatLng(43.217589, 27.932527));
        mRouteLocationsArrayList.add(new LatLng(43.218823, 27.934886));
        mRouteLocationsArrayList.add(new LatLng(43.219169, 27.935705));
        mRouteLocationsArrayList.add(new LatLng(43.219535, 27.936737));
        mRouteLocationsArrayList.add(new LatLng(43.219668, 27.937635));
        mRouteLocationsArrayList.add(new LatLng(43.219640, 27.938652));
        mRouteLocationsArrayList.add(new LatLng(43.219554, 27.939547));
        mRouteLocationsArrayList.add(new LatLng(43.219448, 27.940357));
        mRouteLocationsArrayList.add(new LatLng(43.219300, 27.941358));
        mRouteLocationsArrayList.add(new LatLng(43.219158, 27.942432));
        mRouteLocationsArrayList.add(new LatLng(43.218989, 27.943727));
        mRouteLocationsArrayList.add(new LatLng(43.218626, 27.945039));
        mRouteLocationsArrayList.add(new LatLng(43.218116, 27.946080));
        mRouteLocationsArrayList.add(new LatLng(43.217493, 27.947003));
        mRouteLocationsArrayList.add(new LatLng(43.217010, 27.947740));
        mRouteLocationsArrayList.add(new LatLng(43.216534, 27.948386));
        mRouteLocationsArrayList.add(new LatLng(43.215859, 27.949512));
        mRouteLocationsArrayList.add(new LatLng(43.215203, 27.950506));
        mRouteLocationsArrayList.add(new LatLng(43.214755, 27.951667));
        mRouteLocationsArrayList.add(new LatLng(43.214724, 27.952401));
        mRouteLocationsArrayList.add(new LatLng(43.214869, 27.953277));
        mRouteLocationsArrayList.add(new LatLng(43.215327, 27.953915));
        mRouteLocationsArrayList.add(new LatLng(43.215799, 27.953616));
        mRouteLocationsArrayList.add(new LatLng(43.215807, 27.952658));
        mRouteLocationsArrayList.add(new LatLng(43.215490, 27.951528));
        mRouteLocationsArrayList.add(new LatLng(43.215736, 27.951306));
        mRouteLocationsArrayList.add(new LatLng(43.216143, 27.951944));
        mRouteLocationsArrayList.add(new LatLng(43.216442, 27.952303));
        mRouteLocationsArrayList.add(new LatLng(43.217109, 27.951951));
        mRouteLocationsArrayList.add(new LatLng(43.217793, 27.951550));
        mRouteLocationsArrayList.add(new LatLng(43.218160, 27.951588));
        mRouteLocationsArrayList.add(new LatLng(43.218151, 27.952695));
        mRouteLocationsArrayList.add(new LatLng(43.218141, 27.953355));
        mRouteLocationsArrayList.add(new LatLng(43.218126, 27.954349));
        mRouteLocationsArrayList.add(new LatLng(43.218172, 27.955532));
        mRouteLocationsArrayList.add(new LatLng(43.218454, 27.956768));
        mBusStop = new BusStop(28, "СРЕДНОШКОЛСКА-2", "17:54", new LatLng(43.216534, 27.948386));
        BusLive busLive1 = new BusLive(2, 148, 13, 14, "17:30", "-0:27", 0, mRouteLocationsArrayList.get(73), false, 73);
        busLive1.setOffsetMeters(getOffsetMeters(busLive1));
        busLive1.setOffsetTime(getOffsetTime(busLive1));
        BusLive busLive2 = new BusLive(3, 148, 20, 21, "17:45", "-0:27", 0, mRouteLocationsArrayList.get(124), false, 124);
        busLive2.setOffsetMeters(getOffsetMeters(busLive2));
        busLive2.setOffsetTime(getOffsetTime(busLive2));
        mBusLiveArrayList.add(busLive1);
        mBusLiveArrayList.add(busLive2);
        String schedulesString = "";
        ArrayList<String> schedules = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules2)));
        for (int i = 0; i < schedules.size(); i++)
            if (getTimeInMilliseconds(mBusLiveArrayList.get(0).getTime()) < getTimeInMilliseconds(schedules.get(i)))
                schedulesString += i == schedules.size() - 1 ? schedules.get(i) : schedules.get(i) + ", ";
        mSchedulesArrayList.add(new BusSchedule(148, schedulesString));
        mSchedulesArrayList.add(new BusSchedule(148, schedulesString));
        mSchedulesArrayList.add(new BusSchedule(148, schedulesString));
    }

    private long getTimeInMilliseconds(String time) {
        int     hours           = Integer.parseInt(time.substring(0, 2));
        int     minutes         = Integer.parseInt(time.substring(3, 5));
        long    milliseconds    = hours * 60 * 60 * 1000 + minutes * 60 * 1000;

        return milliseconds;
    }

    private void updateBusLives() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mStartTime = SystemClock.currentThreadTimeMillis();
                while (mShouldUpdateData) {
                    if (SystemClock.currentThreadTimeMillis() == mStartTime + 5000) {
                        mStartTime = SystemClock.currentThreadTimeMillis();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                for (int i = 0; i < mBusLiveArrayList.size(); i++) {
                                    BusLive busLive         = mBusLiveArrayList.get(i);

                                    if (busLive.isBusStopped()) {
                                        removeBusLiveMarker(mBusLiveArrayList.get(i));
                                        mBusLiveArrayList.remove(i);
                                        continue;
                                    }

                                    busLive.setLocation(mRouteLocationsArrayList.get(busLive.getLastLocationId() + 1));
                                    busLive.setLastLocationId(busLive.getLastLocationId() + 1);
                                    busLive.setBusStopped(busLive.getLocation().latitude == mBusStop.getLocation().latitude && busLive.getLocation().longitude == mBusStop.getLocation().longitude);
                                    busLive.setOffsetMeters(getOffsetMeters(busLive));
                                    busLive.setOffsetTime(getOffsetTime(busLive));
                                    mBusLiveArrayList.set(i, busLive);

                                }
                                mAdapter.setData(mBusLiveArrayList, mSchedulesArrayList);
                                mAdapter.notifyDataSetChanged();
                                updateBusLiveMarkers();
                            }
                        });
                    }
                }
            }
        }).start();
    }

    private void removeBusLiveMarker(BusLive busLive) {
        for (int i = 0; i < mMarkersArrayList.size(); i++) {
            if (mMarkersArrayList.get(i).getTag().equals(busLive)) {
                mMarkersArrayList.get(i).remove();
                mMarkersArrayList.remove(i);
                break;
            }
        }
    }

    private void updateBusLiveMarkers() {
        for (int i = 0; i < mMarkersArrayList.size(); i++) {
            for (int j = 0; j < mBusLiveArrayList.size(); j++) {
                if (mMarkersArrayList.get(i).getTag().equals(mBusLiveArrayList.get(j))) {
                    mMarkersArrayList.get(i).setPosition(mBusLiveArrayList.get(j).getLocation());
                    mMarkersArrayList.get(i).setIcon(getMarkerIconFromDrawable(ContextCompat.getDrawable(this, mBusLiveArrayList.get(j).isBusStopped() ? R.drawable.bus_live_stopped_icon : R.drawable.bus_live_icon)));
                }
            }
        }
    }

    private String getOffsetTime(BusLive busLive) {
        double speed = 30.0 * 1000.0 / 3600.0;
        int milliseconds = (int)(busLive.getOffsetMeters() / speed) * 1000;
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        return dateFormat.format(milliseconds);
    }

    private int getOffsetMeters(BusLive busLive) {
        int offsetMeters = 0;
        for (int i = busLive.getLastLocationId(); i < mRouteLocationsArrayList.size() - 1; i++) {
            Location lastLocation = new Location("");
            lastLocation.setLongitude(mRouteLocationsArrayList.get(i).longitude);
            lastLocation.setLatitude(mRouteLocationsArrayList.get(i).latitude);
            Location nextLocation = new Location("");
            lastLocation.setLongitude(mRouteLocationsArrayList.get(i + 1).longitude);
            lastLocation.setLatitude(mRouteLocationsArrayList.get(i + 1).latitude);
            offsetMeters += lastLocation.distanceTo(nextLocation);
            if (mRouteLocationsArrayList.get(i + 1).longitude == mBusStop.getLocation().longitude &&
                    mRouteLocationsArrayList.get(i + 1).latitude == mBusStop.getLocation().latitude)
                break;
        }

        return offsetMeters / (100 * 1000);
    }

    private void setAdapter() {
        mAdapter = new BusStopAdapter(BusStopsActivity.this, mBusLiveArrayList, mSchedulesArrayList);
        mLiveListView.setAdapter(mAdapter);
    }

    private void setMapFragment() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
    }

    private void setMapUiSettings() {
        mGoogleMap.getUiSettings().setRotateGesturesEnabled(false);
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        mGoogleMap.getUiSettings().setZoomGesturesEnabled(true);
        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
    }

    private void setLocationEnabled() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
        else
            mGoogleMap.setMyLocationEnabled(true);

    }

    private void setCameraPosition(double latitude, double longitude, int zoom) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(zoom).build();
        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
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

    private void addMarkers() {
        addBusStopsMarkers();
        addBusLivesMarkers();
    }

    private void addBusStopsMarkers() {
        MarkerOptions markerOptions   = new MarkerOptions().position(mBusStop.getLocation()).icon(getMarkerIconFromDrawable(ContextCompat.getDrawable(this, R.drawable.bus_live_stop_icon)));
        Marker          marker          = mGoogleMap.addMarker(markerOptions);

        marker.setTag(mBusStop);
        mMarkersArrayList.add(marker);
    }

    private void addBusLivesMarkers() {
        for (int i = 0; i < mBusLiveArrayList.size(); i++) {
            BusLive         busLive         = mBusLiveArrayList.get(i);
            MarkerOptions   markerOptions   = new MarkerOptions().position(busLive.getLocation()).zIndex(0.1f).icon(getMarkerIconFromDrawable(ContextCompat.getDrawable(this, busLive.isBusStopped() ? R.drawable.bus_live_stopped_icon : R.drawable.bus_live_icon)));
            Marker          marker          = mGoogleMap.addMarker(markerOptions);

            marker.setTag(busLive);
            mMarkersArrayList.add(marker);
        }
    }

    private BitmapDescriptor getMarkerIconFromDrawable(Drawable drawable) {
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
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
        addMarkers();
        setLocationEnabled();
        setCameraPosition(mBusStop.getLocation().latitude, mBusStop.getLocation().longitude, 14);
        mGoogleMap.setOnCameraMoveListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            mGoogleMap.setMyLocationEnabled(true);
    }

    @Override
    public void onCameraMove() {
        for (int i = 0; i < mMarkersArrayList.size(); i++)
            mMarkersArrayList.get(i).setVisible(mGoogleMap.getCameraPosition().zoom > 11.5);
    }
}
