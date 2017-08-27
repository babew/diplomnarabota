package com.gradski.transport.varna.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.gradski.transport.varna.R;
import com.gradski.transport.varna.fragments.BusScheduleFragment;
import com.gradski.transport.varna.globalClasses.Utils;
import com.gradski.transport.varna.models.StopSchedule;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Lyubomir Babev on 8/21/2017.
 */

public class SchedulesActivity extends BaseActivity {

    private static final int FRAGMENT_WORK_DAY   = 0;
    private static final int FRAGMENT_SATURDAY   = 1;
    private static final int FRAGMENT_SUNDAY     = 2;
    private static final int TABS_COUNT          = 3;

    private TabLayout   mTabLayout;
    private ViewPager   mViewPager;

    private String                      mBusNumber;
    private ArrayList<StopSchedule>    mStopSchedulesArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_schedules);

        mBusNumber = getIntent().getStringExtra(Utils.INTENT_EXTRA_BUS_LINE_NUMBER);

        getData();

        init();
    }

    private void init() {
        mTabLayout  = (TabLayout)  findViewById(R.id.tab_layout);
        mViewPager  = (ViewPager)  findViewById(R.id.view_pager);

        setViewPagerAdapter();

        findViewById(R.id.back_arrow_image_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SchedulesActivity.super.onBackPressed();
            }
        });
    }

    private void setViewPagerAdapter() {
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(TABS_COUNT);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void getData() {
        mStopSchedulesArrayList = new ArrayList<>();
        mStopSchedulesArrayList.add(new StopSchedule(0, "Спирка", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules0)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "ПОЧИВКА", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules0)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "УЧЕНИЧЕСКИ К-С", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules1)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "СРЕДНОШКОЛСКА", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules2)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "СТАДИОН ВАРНА", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules3)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "СПОРТИСТ", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules4)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "ЯВОР", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules5)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "ПАМЕТНИКА-2", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules6)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "ЧАТАЛДЖА-2", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules7)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "Пл .СЪЕДИНЕНИЕ-2", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules8)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "МУЗЕЯ", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules9)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "КАТЕДРАЛАТА", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules10)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "ТРАКИЯ", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules11)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "ПАТРИАРХ ЕВТИМИЙ", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules12)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "АВТОГАРА", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules13)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "РОДИНА", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules14)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "ПКИ/МОЛ Варна/", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules15)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "ДОМ МЛАДОСТ/ул.Вяра/", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules16)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "ЦЕНТРАЛНА АВТОБАЗА", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules17)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "ВЪЗРАЖДАНЕ", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules18)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "ХЕМУС", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules19)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "ГЕОРГИ ГЕОРГИЕВ", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules20)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "КАТ", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules21)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "ТИС СЕВЕР", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules22)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "АРМЕЙСКА", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules23)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "МУРГАШ", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules24)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "ДЕВНЯ", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules25)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "ХИМИК", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules26)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "кап.ПЕТКО ВОЙВОДА", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules27)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "СКАЛАТА", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules28)))));
        mStopSchedulesArrayList.add(new StopSchedule(0, "Бл 407 /Вл-вово/", new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.schedules29)))));
    }

    private class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            BusScheduleFragment busScheduleFragment = new BusScheduleFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(Utils.INTENT_EXTRA_ONE_WAY_BUS_SCHEDULE, mStopSchedulesArrayList);
            bundle.putParcelableArrayList(Utils.INTENT_EXTRA_OTHER_WAY_BUS_SCHEDULE, mStopSchedulesArrayList);
            busScheduleFragment.setArguments(bundle);
            return busScheduleFragment;
        }

        @Override
        public int getCount() {
            return TABS_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == FRAGMENT_WORK_DAY)
                return "Делник";
            else if (position == FRAGMENT_SATURDAY)
                return "Събота";
            else if (position == FRAGMENT_SUNDAY)
                return "Неделя";
            return "Делник";
        }
    }


}
