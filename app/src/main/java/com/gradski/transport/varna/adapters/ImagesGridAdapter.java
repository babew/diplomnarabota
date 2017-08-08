package com.gradski.transport.varna.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gradski.transport.varna.R;

import java.util.ArrayList;

/**
 * Created by lyubomir.babev on 06/07/2017.
 */

public class ImagesGridAdapter extends ArrayAdapter<Integer> {

    private ArrayList<Integer>  mImagesArrayList;
    private boolean             mIsForGallery;

    public ImagesGridAdapter(@NonNull Context context, ArrayList<Integer> images, boolean isForGallery) {
        super(context, R.layout.item_image, images);
        this.mImagesArrayList   = images;
        this.mIsForGallery      = isForGallery;
    }

    @Override
    public int getCount() {
        return mImagesArrayList.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, null);

        float density = parent.getContext().getResources().getDisplayMetrics().density;
        final ImageView imageView = (ImageView) convertView.findViewById(R.id.image_view);
        imageView.setImageResource(mImagesArrayList.get(position));
        imageView.setTransitionName(mImagesArrayList.get(position).toString());
        imageView.setTag(mImagesArrayList.get(position).toString());
        if (!mIsForGallery) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) parent.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            params.height = (int) ((displayMetrics.heightPixels - 52 * density) / 2);
            imageView.setLayoutParams(params);
        }

        return convertView;
    }
}
