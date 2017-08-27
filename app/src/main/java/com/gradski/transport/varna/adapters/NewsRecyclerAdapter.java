package com.gradski.transport.varna.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gradski.transport.varna.R;
import com.gradski.transport.varna.models.News;

import java.util.ArrayList;

/**
 * Created by Lyubomir Babev on 8/21/2017.
 */

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder> {

    private Context             mContext;
    private ArrayList<News>     mNewsesArrayList;

    public NewsRecyclerAdapter(Context context, ArrayList<News> newses) {
        this.mContext         = context;
        this.mNewsesArrayList = newses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        News news = mNewsesArrayList.get(position);

        if (!news.getImageUrl().equalsIgnoreCase(""))
            Glide.with(mContext).load(news.getImageUrl()).into(holder.mImageView);

        holder.mTitleTextView.setText(news.getTitle());
        holder.mDateTextView.setText(news.getDate());
        holder.mMessageTextView.setText(news.getMessage());
    }

    @Override
    public int getItemCount() {
        return mNewsesArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView       mImageView;
        private TextView        mTitleTextView;
        private TextView        mDateTextView;
        private TextView        mMessageTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mImageView         = (ImageView)       itemView.findViewById(R.id.news_image_view);
            this.mTitleTextView     = (TextView)        itemView.findViewById(R.id.title_text_view);
            this.mDateTextView      = (TextView)        itemView.findViewById(R.id.date_text_view);
            this.mMessageTextView   = (TextView)        itemView.findViewById(R.id.message_text_view);
        }
    }
}
