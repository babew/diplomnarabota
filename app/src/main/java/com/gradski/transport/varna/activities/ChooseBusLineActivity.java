package com.gradski.transport.varna.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.gradski.transport.varna.R;
import com.gradski.transport.varna.adapters.BusLinesAdapter;
import com.gradski.transport.varna.globalClasses.Utils;

import java.util.ArrayList;

/**
 * Created by lyubomir.babev on 01/06/2017.
 */

public class ChooseBusLineActivity extends BaseActivity {

    private GridView            mGridView;
    private View                mToolbar;
    private TextView            mChooseBusTextView;
    private ArrayList<String>   mBusLinesArrayList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_choose_bus_line);

        init();
    }

    private void init() {
        mGridView           = (GridView)    findViewById(R.id.grid_view);
        mToolbar            =               findViewById(R.id.toolbar);
        mChooseBusTextView  = (TextView)    findViewById(R.id.choose_bus_text_view);

        startAnimations();
        setOnClickListeners();
        setOnItemClickListeners();
    }

    private void startAnimations() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startDownToUpAnimation(mToolbar, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        startDownToUpAnimation(mChooseBusTextView, new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                getBusLines();
                                BusLinesAdapter adapter = new BusLinesAdapter(ChooseBusLineActivity.this, mBusLinesArrayList);
                                mGridView.setAdapter(adapter);
                                startDownToUpAnimation(mGridView, null);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }, getResources().getInteger(android.R.integer.config_mediumAnimTime));
    }

    private void setOnClickListeners() {
        findViewById(R.id.back_arrow_image_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseBusLineActivity.super.onBackPressed();
            }
        });
    }

    private void setOnItemClickListeners() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean isForLive = getIntent().getBooleanExtra(Utils.INTENT_EXTRA_IS_FOR_LIVE, false);
                Intent intent = new Intent(ChooseBusLineActivity.this, isForLive ? BusLiveActivity.class : SchedulesActivity.class);
                intent.putExtra(Utils.INTENT_EXTRA_BUS_LINE_NUMBER, mBusLinesArrayList.get(position));
                startActivity(intent);
            }
        });
    }

    private void startDownToUpAnimation (View animatedView, Animation.AnimationListener animationListener) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final Animation downToUpAnimation = new TranslateAnimation(0, 0, displayMetrics.heightPixels, 0);
        downToUpAnimation.setDuration(300);
        downToUpAnimation.setAnimationListener(animationListener);
        animatedView.setVisibility(View.VISIBLE);
        animatedView.startAnimation(downToUpAnimation);
    }

    private void getBusLines() {
        mBusLinesArrayList.add("1");
        mBusLinesArrayList.add("7");
        mBusLinesArrayList.add("9");
        mBusLinesArrayList.add("10");
        mBusLinesArrayList.add("12");
        mBusLinesArrayList.add("13");
        mBusLinesArrayList.add("14");
        mBusLinesArrayList.add("14A");
        mBusLinesArrayList.add("17");
        mBusLinesArrayList.add("17A");
        mBusLinesArrayList.add("18");
        mBusLinesArrayList.add("18A");
        mBusLinesArrayList.add("20");
        mBusLinesArrayList.add("22");
        mBusLinesArrayList.add("23");
        mBusLinesArrayList.add("24");
        mBusLinesArrayList.add("25");
        mBusLinesArrayList.add("26");
        mBusLinesArrayList.add("27");
        mBusLinesArrayList.add("28");
        mBusLinesArrayList.add("29");
        mBusLinesArrayList.add("31A");
        mBusLinesArrayList.add("32");
        mBusLinesArrayList.add("33");
        mBusLinesArrayList.add("35");
        mBusLinesArrayList.add("36");
        mBusLinesArrayList.add("37");
        mBusLinesArrayList.add("39");
        mBusLinesArrayList.add("40");
        mBusLinesArrayList.add("41");
        mBusLinesArrayList.add("43");
        mBusLinesArrayList.add("46");
        mBusLinesArrayList.add("48");
        mBusLinesArrayList.add("49");
        mBusLinesArrayList.add("52");
        mBusLinesArrayList.add("54");
        mBusLinesArrayList.add("55");
        mBusLinesArrayList.add("60");
        mBusLinesArrayList.add("82");
        mBusLinesArrayList.add("83");
        mBusLinesArrayList.add("86");
        mBusLinesArrayList.add("88");
        mBusLinesArrayList.add("109");
        mBusLinesArrayList.add("118");
        mBusLinesArrayList.add("118A");
        mBusLinesArrayList.add("121");
        mBusLinesArrayList.add("122");
        mBusLinesArrayList.add("148");
        mBusLinesArrayList.add("209");
        mBusLinesArrayList.add("209B");
        mBusLinesArrayList.add("409");
    }
}
