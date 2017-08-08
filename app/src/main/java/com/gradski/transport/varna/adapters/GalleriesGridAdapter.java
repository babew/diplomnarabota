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
 * Created by lyubomir.babev on 14/07/2017.
 */

public class GalleriesGridAdapter extends ArrayAdapter<Integer> {

    private ArrayList<Integer> mGalleriesArrayList;

    public GalleriesGridAdapter(@NonNull Context context, ArrayList<Integer> galleries) {
        super(context, R.layout.item_gallery, galleries);
        this.mGalleriesArrayList = galleries;
    }

    @Override
    public int getCount() {
        return mGalleriesArrayList.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, null);

        final ImageView imageView = (ImageView) convertView.findViewById(R.id.gallery_image_view);
        imageView.setImageResource(mGalleriesArrayList.get(position));
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)parent.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(displayMetrics.widthPixels / 2, ViewGroup.LayoutParams.WRAP_CONTENT));

        return convertView;
    }
}
