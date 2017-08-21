package com.gradski.transport.varna.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.gradski.transport.varna.R;
import com.gradski.transport.varna.fragments.ImportantFragment;
import com.gradski.transport.varna.fragments.NewsFragment;
import com.gradski.transport.varna.fragments.RouteChangesFragment;
import com.gradski.transport.varna.globalClasses.RevealLayout;
import com.gradski.transport.varna.globalClasses.Utils;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final int FRAGMENT_NEWS              = 0;
    private static final int FRAGMENT_IMPORTANT         = 1;
    private static final int FRAGMENT_ROUTE_CHANGES     = 2;
    private static final int TABS_COUNT                 = 3;

    private DrawerLayout            mDrawer;
    private TabLayout               mTabLayout;
    private ViewPager               mViewPager;
    private LinearLayout            mPointersAndPricesSubLayout;
    private LinearLayout            mForUsSubLayout;
    private RevealLayout            mRevealLayout;
    private View                    mRevealView;
    private FloatingActionButton    mFab;

    private NewsFragment            mNewsFragment;
    private ImportantFragment       mImportantFragment;
    private RouteChangesFragment    mRouteChangesFragment;

    private int mFromWidth;
    private int mFromHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mDrawer                     = (DrawerLayout)            findViewById(R.id.drawer_layout);
        mTabLayout                  = (TabLayout)               findViewById(R.id.tab_layout);
        mViewPager                  = (ViewPager)               findViewById(R.id.view_pager);
        mPointersAndPricesSubLayout = (LinearLayout)            findViewById(R.id.points_and_prices_sub_layout);
        mForUsSubLayout             = (LinearLayout)            findViewById(R.id.for_us_sub_layout);
        mRevealLayout               = (RevealLayout)            findViewById(R.id.reveal_layout);
        mRevealView                 =                           findViewById(R.id.reveal_layout_view);
        mFab                        = (FloatingActionButton)    findViewById(R.id.bus_fab);

        setDrawerListener();
        setViewPagerAdapter();
        setOnClickListeners();
    }

    private void setDrawerListener() {
        mDrawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View view, float v) {}

            @Override
            public void onDrawerOpened(View view) {}

            @Override
            public void onDrawerClosed(View view) {}

            @Override
            public void onDrawerStateChanged(int i) {
                hideSubLayouts();}
        });
    }

    private void setViewPagerAdapter() {
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffsetPixels > 0 && mFab.isShown())
                    mFab.hide();
                else if (positionOffsetPixels == 0)
                    mFab.show();

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setOnClickListeners() {
        findViewById(R.id.menu_image_view).setOnClickListener(this);
        findViewById(R.id.live_layout).setOnClickListener(this);
        findViewById(R.id.schedules_layout).setOnClickListener(this);
        findViewById(R.id.points_and_prices_layout).setOnClickListener(this);
        findViewById(R.id.complaints_and_signals_layout).setOnClickListener(this);
        findViewById(R.id.for_us_layout).setOnClickListener(this);
        findViewById(R.id.card_prices_layout).setOnClickListener(this);
        findViewById(R.id.points_for_sales_layout).setOnClickListener(this);
        findViewById(R.id.transport_documents_layout).setOnClickListener(this);
        findViewById(R.id.terms_and_conditions_layout).setOnClickListener(this);
        findViewById(R.id.documents_layout).setOnClickListener(this);
        findViewById(R.id.sertificats_layout).setOnClickListener(this);
        findViewById(R.id.history_layout).setOnClickListener(this);
        findViewById(R.id.careers_layout).setOnClickListener(this);
        findViewById(R.id.adversting_layout).setOnClickListener(this);
        findViewById(R.id.gallery_layout).setOnClickListener(this);
        findViewById(R.id.bus_fab).setOnClickListener(this);
    }

    private void showOrHideSubLayout(boolean isForUsSubLayout) {
        if (isForUsSubLayout && mPointersAndPricesSubLayout.getVisibility() == View.VISIBLE)
            Utils.startCollapseAnimation(mPointersAndPricesSubLayout);
        else if (!isForUsSubLayout && mForUsSubLayout.getVisibility() == View.VISIBLE)
            Utils.startCollapseAnimation(mForUsSubLayout);

        if (isForUsSubLayout && mForUsSubLayout.getVisibility() == View.GONE)
            Utils.startExpandAnimation(mForUsSubLayout);
        else if (isForUsSubLayout)
            Utils.startCollapseAnimation(mForUsSubLayout);
        else if (mPointersAndPricesSubLayout.getVisibility() == View.GONE)
            Utils.startExpandAnimation(mPointersAndPricesSubLayout);
        else
            Utils.startCollapseAnimation(mPointersAndPricesSubLayout);
    }

    private void hideSubLayouts() {
        if (mPointersAndPricesSubLayout.getVisibility() == View.VISIBLE)
            Utils.startCollapseAnimation(mPointersAndPricesSubLayout);
        else if (mForUsSubLayout.getVisibility() == View.VISIBLE)
            Utils.startCollapseAnimation(mForUsSubLayout);
    }

    private void startRevealAnimation (View animatedView) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        int     animationDuration           = 400;
        int     widthLocation               = 0;
        int     heightLocation              = 1;
        int     halfPixels                  = 2;
        int[]   location                    = new int[2];

        animatedView.getLocationOnScreen(location);
        mFromWidth      = location[widthLocation] + animatedView.getWidth() / halfPixels;
        mFromHeight     = location[heightLocation];

        final Intent intent = new Intent(MainActivity.this, ChooseBusLineActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        mRevealLayout.setVisibility(View.VISIBLE);
        mRevealView.setVisibility(View.VISIBLE);
        mRevealLayout.show(mFromWidth, mFromHeight);

        animatedView.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivityForResult(intent, Utils.REQUEST_CODE_BUS_LINE);
            }
        }, animationDuration);
    }

    private void hideRevealAnimation() {
        final int animationDuration = 400;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRevealLayout.hide(mFromWidth, mFromHeight);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mDrawer.isDrawerOpen(findViewById(R.id.drawer_view)))
                            mDrawer.closeDrawer(findViewById(R.id.drawer_view));
                    }
                }, animationDuration);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        }, animationDuration);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        hideRevealAnimation();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.menu_image_view) {
            if (mDrawer.isDrawerOpen(findViewById(R.id.drawer_view)))
                mDrawer.closeDrawer(findViewById(R.id.drawer_view));
            else
                mDrawer.openDrawer(findViewById(R.id.drawer_view));
        } else if (v.getId() == R.id.live_layout) {
            startRevealAnimation(findViewById(R.id.live_layout));
        } else if (v.getId() == R.id.schedules_layout) {
            startRevealAnimation(findViewById(R.id.schedules_layout));
        } else if (v.getId() == R.id.points_and_prices_layout) {
            showOrHideSubLayout(false);
        } else if (v.getId() == R.id.complaints_and_signals_layout) {
            mDrawer.closeDrawer(findViewById(R.id.drawer_view));
            startActivityFromDrawer(ComplaintsAndSignalsActivity.class);
        } else if (v.getId() == R.id.for_us_layout) {
            showOrHideSubLayout(true);
        } else if (v.getId() == R.id.card_prices_layout) {
            mPointersAndPricesSubLayout.setVisibility(View.GONE);
            mDrawer.closeDrawer(findViewById(R.id.drawer_view));
            startActivityFromDrawer(CardPricesActivity.class);
        } else if (v.getId() == R.id.points_for_sales_layout) {
            mPointersAndPricesSubLayout.setVisibility(View.GONE);
            mDrawer.closeDrawer(findViewById(R.id.drawer_view));
            startActivityFromDrawer(PointsListActivity.class);
        } else if (v.getId() == R.id.transport_documents_layout) {
            mPointersAndPricesSubLayout.setVisibility(View.GONE);
            mDrawer.closeDrawer(findViewById(R.id.drawer_view));
            startActivityFromDrawer(TransportDocumentsActivity.class);
        } else if (v.getId() == R.id.terms_and_conditions_layout) {
            mPointersAndPricesSubLayout.setVisibility(View.GONE);
            mDrawer.closeDrawer(findViewById(R.id.drawer_view));
            startActivityFromDrawer(TermsAndConditionsActivity.class);
        } else if (v.getId() == R.id.documents_layout) {
            mPointersAndPricesSubLayout.setVisibility(View.GONE);
            mDrawer.closeDrawer(findViewById(R.id.drawer_view));
            startActivityFromDrawer(DocumentsActivity.class);
        } else if (v.getId() == R.id.sertificats_layout) {
            mForUsSubLayout.setVisibility(View.GONE);
            mDrawer.closeDrawer(findViewById(R.id.drawer_view));
            startImagesActivity(false);
        } else if (v.getId() == R.id.history_layout) {
            mForUsSubLayout.setVisibility(View.GONE);
            mDrawer.closeDrawer(findViewById(R.id.drawer_view));
            startActivityFromDrawer(HistoryActivity.class);
        } else if (v.getId() == R.id.careers_layout) {
            mForUsSubLayout.setVisibility(View.GONE);
            mDrawer.closeDrawer(findViewById(R.id.drawer_view));
            startActivityFromDrawer(CareersActivity.class);
        } else if (v.getId() == R.id.adversting_layout) {
            mForUsSubLayout.setVisibility(View.GONE);
            mDrawer.closeDrawer(findViewById(R.id.drawer_view));
            startActivityFromDrawer(AdvertisingActivity.class);
        } else if (v.getId() == R.id.gallery_layout) {
            mForUsSubLayout.setVisibility(View.GONE);
            mDrawer.closeDrawer(findViewById(R.id.drawer_view));
            startImagesActivity(true);
        } else if (v.getId() == R.id.bus_fab) {
            startRevealAnimation(mFab);
        }
    }

    private void startActivityFromDrawer(final Class chosenClass) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, chosenClass);
                startActivity(intent);
            }
        }, 200);
    }

    private void startImagesActivity(final boolean isForGallery) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, ImagesActivity.class);
                intent.putExtra(Utils.INTENT_EXTRA_IS_FOR_GALLERY, isForGallery);
                startActivity(intent);
            }
        }, 200);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(findViewById(R.id.drawer_view)))
            mDrawer.closeDrawer(findViewById(R.id.drawer_view));
        else
            super.onBackPressed();
    }

    private class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {
                case FRAGMENT_NEWS:
                    mNewsFragment = new NewsFragment();
                    return mNewsFragment;
                case FRAGMENT_IMPORTANT:
                    mImportantFragment = new ImportantFragment();
                    return mImportantFragment;
                case FRAGMENT_ROUTE_CHANGES:
                    mRouteChangesFragment = new RouteChangesFragment();
                    return mRouteChangesFragment;
                default:
                    mNewsFragment = new NewsFragment();
                    return mNewsFragment;
            }
        }

        @Override
        public int getCount() {
            return TABS_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == FRAGMENT_NEWS)
                return "Новини";
            else if (position == FRAGMENT_IMPORTANT)
                return "Важно";
            else if (position == FRAGMENT_ROUTE_CHANGES)
                return "Маршрутни промени";
            return "Новини";
        }
    }

}
