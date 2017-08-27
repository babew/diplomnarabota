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
import com.gradski.transport.varna.adapters.BusLiveAdapter;
import com.gradski.transport.varna.globalClasses.MapTypePopup;
import com.gradski.transport.varna.models.BusLive;
import com.gradski.transport.varna.models.BusStop;

import java.util.ArrayList;

/**
 * Created by Lyubomir Babev on 8/21/2017.
 */

public class    BusLiveActivity extends BaseActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, View.OnClickListener, BusLiveAdapter.BusLiveAdapterListener, GoogleMap.OnCameraMoveListener {

    private GoogleMap       mGoogleMap;
    private ListView        mListView;
    private BusLiveAdapter  mAdapter;

    private ArrayList<BusStop>          mBusStopsArrayList          = new ArrayList<>();
    private ArrayList<BusLive>          mBusLiveArrayList           = new ArrayList<>();
    private ArrayList<Marker>           mMarkersArrayList           = new ArrayList<>();
    private ArrayList<LatLng>           mRouteLocationsArrayList    = new ArrayList<>();

    private long        mStartTime          = 0;
    private boolean     mShouldUpdateData   = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bus_line);

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
        mListView   = (ListView) findViewById(R.id.list_view);

        setAdapter();

        findViewById(R.id.back_arrow_image_view).setOnClickListener(this);
        findViewById(R.id.map_image_view).setOnClickListener(this);
    }

    private void setAdapter() {
        mAdapter = new BusLiveAdapter(BusLiveActivity.this, mBusStopsArrayList, mBusLiveArrayList, BusLiveActivity.this);
        mListView.setAdapter(mAdapter);
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
        mBusStopsArrayList.add(new BusStop(0, "Бл 407/Вл-во/", "17:00", new LatLng(43.251626, 27.850909)));
        mBusStopsArrayList.add(new BusStop(1, "Бл 407/Вл-во/-2", "17:01", new LatLng(43.251044, 27.849490)));
        mBusStopsArrayList.add(new BusStop(2, "кап.ПЕТКО ВОЙВОДА-2", "17:04", new LatLng(43.246077, 27.848341)));
        mBusStopsArrayList.add(new BusStop(3, "ХИМИК-2", "17:06", new LatLng(43.245908, 27.852288)));
        mBusStopsArrayList.add(new BusStop(4, "ДЕВНЯ-2", "17:09", new LatLng(43.245120, 27.857390)));
        mBusStopsArrayList.add(new BusStop(5, "МУРГАШ-2", "17:13", new LatLng(43.241424, 27.860526)));
        mBusStopsArrayList.add(new BusStop(6, "АРМЕЙСКА-2", "17:15", new LatLng(43.240844, 27.866297)));
        mBusStopsArrayList.add(new BusStop(7, "ТИС СЕВЕР-2", "17:17", new LatLng(43.238805, 27.871124)));
        mBusStopsArrayList.add(new BusStop(8, "КАТ-2", "17:19", new LatLng(43.234762, 27.870277)));
        mBusStopsArrayList.add(new BusStop(9, "ГЕОРГИ ГЕОРГИЕВ-2", "17:21", new LatLng(43.234106, 27.873902)));
        mBusStopsArrayList.add(new BusStop(10, "ХЕМУС-2", "17:24", new LatLng(43.233791, 27.882641)));
        mBusStopsArrayList.add(new BusStop(11, "ВЪЗРАЖДАНЕ-2", "17:25", new LatLng(43.233284, 27.886231)));
        mBusStopsArrayList.add(new BusStop(12, "ЦЕНТРАЛНА АВТОБАЗА-2", "17:27", new LatLng(43.231343, 27.889912)));
        mBusStopsArrayList.add(new BusStop(13, "ДОМ МЛАДОСТ-2", "17:29", new LatLng(43.226156, 27.889506)));
        mBusStopsArrayList.add(new BusStop(14, "ПКИ/МОЛ Варна/-2", "17:31", new LatLng(43.220786, 27.889084)));
        mBusStopsArrayList.add(new BusStop(15, "ИВАН РИЛСКИ-2", "17:33", new LatLng(43.218882, 27.888906)));
        mBusStopsArrayList.add(new BusStop(16, "РОДИНА-2", "17:34", new LatLng(43.216899, 27.893280)));
        mBusStopsArrayList.add(new BusStop(17, "АВТОГАРА-2", "17:35", new LatLng(43.215582, 27.896242)));
        mBusStopsArrayList.add(new BusStop(18, "ПАТРИАРХ ЕВТИМИЙ-2", "17:36", new LatLng(43.213728, 27.900460)));
        mBusStopsArrayList.add(new BusStop(19, "ТРАКИЯ-2", "17:38", new LatLng(43.211285, 27.907274)));
        mBusStopsArrayList.add(new BusStop(20, "КАТЕДРАЛАТА-2", "17:41", new LatLng(43.205967, 27.910430)));
        mBusStopsArrayList.add(new BusStop(21, "МУЗЕЯ", "17:43", new LatLng(43.207615, 27.917971)));
        mBusStopsArrayList.add(new BusStop(22, "Пл. СЪЕДИНЕНИЕ", "17:45", new LatLng(43.209996, 27.921185)));
        mBusStopsArrayList.add(new BusStop(23, "ЧАТАЛДЖА", "17:47", new LatLng(43.212773, 27.924719)));
        mBusStopsArrayList.add(new BusStop(24, "ПАМЕТНИКА", "17:49", new LatLng(43.215458, 27.928676)));
        mBusStopsArrayList.add(new BusStop(25, "ЯВОР", "17:51", new LatLng(43.218307, 27.933927)));
        mBusStopsArrayList.add(new BusStop(26, "СПОРТИСТ-2", "17:52", new LatLng(43.219640, 27.938652)));
        mBusStopsArrayList.add(new BusStop(27, "СТАДИОН ВАРНА-2", "17:53", new LatLng(43.219158, 27.942432)));
        mBusStopsArrayList.add(new BusStop(28, "СРЕДНОШКОЛСКА-2", "17:54", new LatLng(43.216534, 27.948386)));
        mBusStopsArrayList.add(new BusStop(29, "КАРИН ДОМ", "17:55", new LatLng(43.214869, 27.953277)));
        mBusStopsArrayList.add(new BusStop(30, "УЧЕНИЧЕСКИ К-С-2", "17:59", new LatLng(43.218141, 27.953355)));
        mBusStopsArrayList.add(new BusStop(31, "ПОЧИВКА/Обръщач/", "18:00", new LatLng(43.218454, 27.956768)));
        BusLive busLive1 = new BusLive(0, 148, 0, 1, "17:00", "+3:01", 0, mRouteLocationsArrayList.get(0), true, 0);
        busLive1.setOffsetMeters(getOffsetMeters(busLive1));
        BusLive busLive2 = new BusLive(1, 148, 5, 6, "17:15", "+1:06", 0, mRouteLocationsArrayList.get(34), false, 34);
        busLive2.setOffsetMeters(getOffsetMeters(busLive2));
        BusLive busLive3 = new BusLive(2, 148, 13, 14, "17:30", "-0:27", 0, mRouteLocationsArrayList.get(73), false, 73);
        busLive3.setOffsetMeters(getOffsetMeters(busLive3));
        BusLive busLive4 = new BusLive(3, 148, 20, 21, "17:45", "-0:27", 0, mRouteLocationsArrayList.get(124), false, 124);
        busLive4.setOffsetMeters(getOffsetMeters(busLive4));
        BusLive busLive5 = new BusLive(4, 148, 26, 27, "18:00", "-1:15", 0, mRouteLocationsArrayList.get(152), false, 152);
        busLive5.setOffsetMeters(getOffsetMeters(busLive5));
        mBusLiveArrayList.add(busLive1);
        mBusLiveArrayList.add(busLive2);
        mBusLiveArrayList.add(busLive3);
        mBusLiveArrayList.add(busLive4);
        mBusLiveArrayList.add(busLive5);
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
                                    BusStop nextBusStop     = null;
                                    boolean busStopped      = false;
                                    busLive.setLocation(mRouteLocationsArrayList.get(busLive.getLastLocationId() + 1));
                                    for (int j = 0; j < mBusStopsArrayList.size(); j++) {
                                        if (mBusStopsArrayList.get(j).getId() == busLive.getNextStopIdId())
                                            nextBusStop = mBusStopsArrayList.get(j);

                                        if (busLive.getLocation().latitude == mBusStopsArrayList.get(j).getLocation().latitude &&
                                                busLive.getLocation().longitude == mBusStopsArrayList.get(j).getLocation().longitude) {
                                            busLive.setLastStopId(busLive.getLastStopId() + 1);
                                            busLive.setNextStopId(busLive.getNextStopIdId() + 1);
                                            busStopped = true;
                                        }
                                    }

                                    busLive.setLastLocationId(busLive.getLastLocationId() + 1);
                                    busLive.setBusStopped(busStopped);
                                    if (nextBusStop != null && !busStopped)
                                        busLive.setTime(nextBusStop.getNextBusLiveStopTime());

                                    if (busLive.getLastLocationId() + 1 == mRouteLocationsArrayList.size()) {
                                        removeBusLiveMarker(mBusLiveArrayList.get(i));
                                        mBusLiveArrayList.remove(i);
                                    }
                                    else {
                                        busLive.setOffsetMeters(getOffsetMeters(busLive));
                                        mBusLiveArrayList.set(i, busLive);
                                    }

                                }
                                mAdapter.setBusLives(mBusLiveArrayList);
                                mAdapter.notifyDataSetChanged();
                                updateBusLiveMarkers();
                            }
                        });
                    }
                }
            }
        }).start();
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

    private void removeBusLiveMarker(BusLive busLive) {
        for (int i = 0; i < mMarkersArrayList.size(); i++) {
            if (mMarkersArrayList.get(i).getTag().equals(busLive)) {
                mMarkersArrayList.get(i).remove();
                mMarkersArrayList.remove(i);
                break;
            }
        }
    }

    private int getOffsetMeters(BusLive busLive) {
        int offsetMeters = 0;
        Location nextStopLocation = new Location("");
        for (int i = 0; i < mBusStopsArrayList.size(); i++) {
            if (mBusStopsArrayList.get(i).getId() == busLive.getNextStopIdId()) {
                nextStopLocation.setLongitude(mBusStopsArrayList.get(i).getLocation().longitude);
                nextStopLocation.setLatitude(mBusStopsArrayList.get(i).getLocation().latitude);
            }
        }
        for (int i = busLive.getLastLocationId(); i < mRouteLocationsArrayList.size() - 1; i++) {
            Location lastLocation = new Location("");
            lastLocation.setLongitude(mRouteLocationsArrayList.get(i).longitude);
            lastLocation.setLatitude(mRouteLocationsArrayList.get(i).latitude);
            Location nextLocation = new Location("");
            lastLocation.setLongitude(mRouteLocationsArrayList.get(i + 1).longitude);
            lastLocation.setLatitude(mRouteLocationsArrayList.get(i + 1).latitude);
            offsetMeters += lastLocation.distanceTo(nextLocation);
            if (mRouteLocationsArrayList.get(i + 1).longitude == nextStopLocation.getLongitude() &&
                    mRouteLocationsArrayList.get(i + 1).latitude == nextStopLocation.getLatitude())
                break;
        }

        return offsetMeters / (100 * 1000);
    }

    private void setLocationEnabled() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
        else
            mGoogleMap.setMyLocationEnabled(true);

    }

    private void addMarkers() {
        addBusStopsMarkers();
        addBusLivesMarkers();
    }

    private void addBusStopsMarkers() {
        for (int i = 0; i < mBusStopsArrayList.size(); i++) {
            BusStop         busStop         = mBusStopsArrayList.get(i);
            MarkerOptions   markerOptions   = new MarkerOptions().position(busStop.getLocation()).icon(getMarkerIconFromDrawable(ContextCompat.getDrawable(this, R.drawable.bus_live_stop_icon)));
            Marker          marker          = mGoogleMap.addMarker(markerOptions);

            marker.setTag(busStop);
            mMarkersArrayList.add(marker);
        }
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
        setCameraPosition(43.226238, 27.889245, 12);
        mGoogleMap.setOnCameraMoveListener(this);
        updateBusLives();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            mGoogleMap.setMyLocationEnabled(true);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        BusLive busLive = (BusLive) marker.getTag();
        if (busLive == null)
            return false;
        for (int i = 0; i < mBusStopsArrayList.size(); i++) {
            if (mBusStopsArrayList.get(i).getId() == busLive.getLastStopId()) {
                mListView.smoothScrollToPosition(i);
                break;
            }

        }
        return false;
    }

    @Override
    public void onStopClicked(BusStop busStop) {

    }

    @Override
    public void onBusClicked(BusLive busLive) {
        setCameraPosition(busLive.getLocation().latitude, busLive.getLocation().longitude, 15);
    }

    @Override
    public void onCameraMove() {
        for (int i = 0; i < mMarkersArrayList.size(); i++)
            mMarkersArrayList.get(i).setVisible(mGoogleMap.getCameraPosition().zoom > 11.5);
    }
}
