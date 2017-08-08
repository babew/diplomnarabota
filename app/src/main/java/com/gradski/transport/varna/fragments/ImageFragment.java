package com.gradski.transport.varna.fragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.gradski.transport.varna.R;
import com.gradski.transport.varna.globalClasses.Utils;

/**
 * Created by lyubomir.babev on 07/07/2017.
 */

public class ImageFragment extends BaseFragment {

    private View        mView;
    private ImageView   mImageView;

    private int mStartingPosition   = 0;
    private int mCurrentPosition    = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStartingPosition   = getArguments().getInt(Utils.INTENT_EXTRA_START_IMAGE_POSITION);
        mCurrentPosition    = getArguments().getInt(Utils.INTENT_EXTRA_CURRENT_IMAGE_POSITION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_certificate, container, false);
        init();
        return mView;
    }

    private void init() {
        int imageResource = getArguments().getInt(Utils.INTENT_EXTRA_IMAGE_RESOURCE);
        mImageView = (ImageView) mView.findViewById(R.id.image_view);
        mImageView.setImageResource(imageResource);
        mImageView.setTransitionName(String.valueOf(imageResource));

        startPostponedEnterTransition();
    }

    public void startPostponedEnterTransition() {
        if (mCurrentPosition == mStartingPosition) {
            mImageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    mImageView.getViewTreeObserver().removeOnPreDrawListener(this);
                    getActivity().startPostponedEnterTransition();
                    return true;
                }
            });
        }
    }

    @Nullable
    public ImageView getImageView() {
        if (isViewInBounds(getActivity().getWindow().getDecorView(), mImageView)) {
            return mImageView;
        }
        return null;
    }

    private boolean isViewInBounds(@NonNull View container, @NonNull View view) {
        Rect containerBounds = new Rect();
        container.getHitRect(containerBounds);
        return view.getLocalVisibleRect(containerBounds);
    }
}
