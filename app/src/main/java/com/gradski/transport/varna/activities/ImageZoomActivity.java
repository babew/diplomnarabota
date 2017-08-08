package com.gradski.transport.varna.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.view.ViewPager;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gradski.transport.varna.R;
import com.gradski.transport.varna.fragments.ImageFragment;
import com.gradski.transport.varna.globalClasses.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lyubomir.babev on 07/07/2017.
 */

public class ImageZoomActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager           mViewPager;
    private ArrayList<Integer>  mImagesArrayList;
    private ImageFragment       mCurrentFragment;

    private int mStartingPosition   = 0;
    private int mCurrentPosition    = 0;
    private boolean mIsReturning;

    private final SharedElementCallback mCallback = new SharedElementCallback() {
        @Override
        public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
            if (mIsReturning) {
                ImageView sharedElement = mCurrentFragment.getImageView();
                if (sharedElement == null) {
                    names.clear();
                    sharedElements.clear();
                } else if (mStartingPosition != mViewPager.getCurrentItem()) {
                    names.clear();
                    names.add(sharedElement.getTransitionName());
                    sharedElements.clear();
                    sharedElements.put(sharedElement.getTransitionName(), sharedElement);
                }
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_certificates_zoom);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition();
            setEnterSharedElementCallback(mCallback);
        }

        init();
    }

    @Override
    public void finishAfterTransition() {
        mIsReturning = true;
        Intent data = new Intent();
        data.putExtra(Utils.INTENT_EXTRA_START_IMAGE_POSITION, mStartingPosition);
        data.putExtra(Utils.INTENT_EXTRA_CURRENT_IMAGE_POSITION, mCurrentPosition);
        setResult(RESULT_OK, data);
        super.finishAfterTransition();
    }

    private void init() {
        mViewPager              = (ViewPager) findViewById(R.id.view_pager);
        mImagesArrayList        = getIntent().getIntegerArrayListExtra(Utils.INTENT_EXTRA_IMAGES_LIST);
        mStartingPosition       = mCurrentPosition = getIntent().getIntExtra(Utils.INTENT_EXTRA_IMAGE_POSITION, 0);

        setViewPagerAdapter();

        findViewById(R.id.back_arrow_image_view).setOnClickListener(this);
    }

    private void setViewPagerAdapter() {
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOffscreenPageLimit(mImagesArrayList.size());
        mViewPager.setCurrentItem(mCurrentPosition);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_arrow_image_view)
            super.onBackPressed();
    }

    private class PagerAdapter extends FragmentPagerAdapter {

        private PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            int imageResource = mImagesArrayList.get(pos);
            Bundle bundle = new Bundle();
            bundle.putInt(Utils.INTENT_EXTRA_IMAGE_RESOURCE, imageResource);
            ImageFragment imageFragment = new ImageFragment();
            imageFragment.setArguments(bundle);
            return imageFragment;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            mCurrentFragment = (ImageFragment) object;
        }

        @Override
        public int getCount() {
            return mImagesArrayList.size();
        }

    }

}
