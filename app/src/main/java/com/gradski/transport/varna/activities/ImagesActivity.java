package com.gradski.transport.varna.activities;

import android.app.ActivityOptions;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.GridView;

import com.gradski.transport.varna.BuildConfig;
import com.gradski.transport.varna.R;
import com.gradski.transport.varna.adapters.ImagesGridAdapter;
import com.gradski.transport.varna.globalClasses.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lyubomir.babev on 02/06/2017.
 */

public class ImagesActivity extends BaseActivity implements View.OnClickListener {

    private GridView                mGridView;
    private ImagesGridAdapter       mAdapter;
    private ArrayList<Integer>      mImagesArrayList = new ArrayList<>();
    private boolean                 mIsForGallery;

    private Bundle      mTmpReenterState;
    private boolean     mIsDetailsActivityStarted;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_certificates);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            setExitSharedElementCallback(createSharedCallback());

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsDetailsActivityStarted = false;
    }

    @Override
    public void onActivityReenter(int requestCode, Intent data) {
        super.onActivityReenter(requestCode, data);
        mTmpReenterState        = new Bundle(data.getExtras());
        int startingPosition    = mTmpReenterState.getInt(Utils.INTENT_EXTRA_START_IMAGE_POSITION);
        int currentPosition     = mTmpReenterState.getInt(Utils.INTENT_EXTRA_CURRENT_IMAGE_POSITION);

        if (startingPosition != currentPosition)
            mGridView.setSelection(currentPosition);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            postponeEnterTransition();

        mGridView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mGridView.getViewTreeObserver().removeOnPreDrawListener(this);
                mGridView.requestLayout();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    startPostponedEnterTransition();

                return true;
            }
        });
    }

    private void init() {
        mGridView       = (GridView) findViewById(R.id.grid_view);
        mIsForGallery   = getIntent().getBooleanExtra(Utils.INTENT_EXTRA_IS_FOR_GALLERY, false);

        TypedArray certificates = getResources().obtainTypedArray(mIsForGallery ? R.array.galleries_array : R.array.certificates_array);
        for (int i = 0; i < certificates.length(); i++)
            mImagesArrayList.add(certificates.getResourceId(i, 0));

        mAdapter = new ImagesGridAdapter(this, mImagesArrayList, mIsForGallery);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startImageZoomActivity(position, view.findViewById(R.id.image_view));
            }
        });

        findViewById(R.id.back_arrow_image_view).setOnClickListener(this);
    }

    private SharedElementCallback createSharedCallback() {
        SharedElementCallback sharedElementCallback = new SharedElementCallback() {
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                if (mTmpReenterState != null) {
                    int startingPosition = mTmpReenterState.getInt(Utils.INTENT_EXTRA_START_IMAGE_POSITION);
                    int currentPosition = mTmpReenterState.getInt(Utils.INTENT_EXTRA_CURRENT_IMAGE_POSITION);
                    if (startingPosition != currentPosition) {
                        String newTransitionName = mImagesArrayList.get(currentPosition).toString();
                        View newSharedElement = mGridView.findViewWithTag(newTransitionName);
                        if (newSharedElement != null) {
                            names.clear();
                            names.add(newTransitionName);
                            sharedElements.clear();
                            sharedElements.put(newTransitionName, newSharedElement);
                        }
                    }

                    mTmpReenterState = null;
                } else {
                    View navigationBar  = findViewById(android.R.id.navigationBarBackground);
                    View statusBar      = findViewById(android.R.id.statusBarBackground);
                    if (navigationBar != null) {
                        names.add(navigationBar.getTransitionName());
                        sharedElements.put(navigationBar.getTransitionName(), navigationBar);
                    }
                    if (statusBar != null) {
                        names.add(statusBar.getTransitionName());
                        sharedElements.put(statusBar.getTransitionName(), statusBar);
                    }
                }
            }
        };
        return sharedElementCallback;
    }

    private void startImageZoomActivity(int position, View transitionView) {
        Intent intent = new Intent(this, ImageZoomActivity.class);
        intent.putIntegerArrayListExtra(Utils.INTENT_EXTRA_IMAGES_LIST, mImagesArrayList);
        intent.putExtra(Utils.INTENT_EXTRA_IMAGE_POSITION, position);
        if (!mIsDetailsActivityStarted) {
            mIsDetailsActivityStarted = true;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(ImagesActivity.this, transitionView, transitionView.getTransitionName()).toBundle());
            else
                startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_arrow_image_view)
            super.onBackPressed();
    }
}
