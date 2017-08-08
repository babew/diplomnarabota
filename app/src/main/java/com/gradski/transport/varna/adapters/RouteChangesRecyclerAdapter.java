package com.gradski.transport.varna.adapters;

import android.animation.LayoutTransition;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gradski.transport.varna.R;
import com.gradski.transport.varna.globalClasses.Utils;
import com.gradski.transport.varna.models.RouteChange;

import java.util.ArrayList;

/**
 * Created by lyubomir.babev on 21/07/2017.
 */

public class RouteChangesRecyclerAdapter extends RecyclerView.Adapter<RouteChangesRecyclerAdapter.ViewHolder> {

    private ArrayList<RouteChange>  mRouteChangesArrayList;

    public RouteChangesRecyclerAdapter(ArrayList<RouteChange> routeChanges) {
        this.mRouteChangesArrayList = routeChanges;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_route_change, parent, false);
        return new RouteChangesRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        RouteChange routeChange = mRouteChangesArrayList.get(position);
        holder.mImageView.setImageResource(Utils.getRouteChangeImageResource(routeChange.getImageType()));
        holder.mTitleTextView.setText(routeChange.getTitle());
        holder.mMessageTextView.setText(routeChange.getMessage());
        holder.mRouteChangeLayout.setOnClickListener(new View.OnClickListener() {
            boolean isClicked = false;
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    holder.mLinearLayout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
                    holder.mLinearLayout.getLayoutTransition().addTransitionListener(new LayoutTransition.TransitionListener() {
                        @Override
                        public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {

                        }

                        @Override
                        public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                            holder.mLinearLayout.getLayoutTransition().disableTransitionType(LayoutTransition.CHANGING);
                        }
                    });
                }
                holder.mMessageTextView.setMaxLines(isClicked ? 2 : Integer.MAX_VALUE);
                isClicked = !isClicked;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRouteChangesArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout    mLinearLayout;
        private LinearLayout    mRouteChangeLayout;
        private ImageView       mImageView;
        private TextView        mTitleTextView;
        private TextView        mMessageTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mLinearLayout      = (LinearLayout)    itemView.findViewById(R.id.linear_layout);
            this.mRouteChangeLayout = (LinearLayout)    itemView.findViewById(R.id.route_change_layout);
            this.mImageView         = (ImageView)       itemView.findViewById(R.id.route_change_image_view);
            this.mTitleTextView     = (TextView)        itemView.findViewById(R.id.title_text_view);
            this.mMessageTextView   = (TextView)        itemView.findViewById(R.id.message_text_view);
        }
    }
}
