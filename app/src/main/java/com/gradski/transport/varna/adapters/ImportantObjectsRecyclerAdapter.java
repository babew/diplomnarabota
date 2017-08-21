package com.gradski.transport.varna.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gradski.transport.varna.R;
import com.gradski.transport.varna.globalClasses.Utils;
import com.gradski.transport.varna.models.ImportantObject;
import com.gradski.transport.varna.models.ImportantText;

import java.util.ArrayList;

/**
 * Created by lyubomir.babev on 16/08/2017.
 */

public class ImportantObjectsRecyclerAdapter extends RecyclerView.Adapter<ImportantObjectsRecyclerAdapter.ViewHolder> {

    private Context                     mContext;
    private ArrayList<ImportantObject>  mImportantObjectsArrayList;

    public ImportantObjectsRecyclerAdapter(Context context, ArrayList<ImportantObject> importantObjects) {
        this.mContext                   = context;
        this.mImportantObjectsArrayList = importantObjects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_important_object, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ImportantObject importantObject = mImportantObjectsArrayList.get(position);
        holder.mTitleTextView.setText(importantObject.getTitle());
        setTexts(holder, importantObject.getTexts());
    }

    private void setTexts(ViewHolder holder, ArrayList<ImportantText> texts) {
        int counter = 1;
        for (int i = 0; i < texts.size(); i++) {
            ImportantText importantText   = texts.get(i);
            TextView                    textView        = new TextView(mContext);
            LinearLayout.LayoutParams   layoutParams    = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            int                         margin          = (int)(mContext.getResources().getDisplayMetrics().density * 10);
            int                         numberMargin    = (int)(mContext.getResources().getDisplayMetrics().density * 20);

            if (importantText.getType() == Utils.IMPORTANT_TEXT_TYPE_LIST){
                layoutParams.setMargins(numberMargin, 0, margin, i == texts.size() - 1 || (i != texts.size() - 1 && importantText.getType() != texts.get(i + 1).getType()) ? margin : 0);
                textView.setText(counter + ". " + importantText.getText());
                counter++;
            } else {
                layoutParams.setMargins(margin, 0, margin, i == texts.size() - 1 || (i != texts.size() - 1 && importantText.getType() != texts.get(i + 1).getType()) ? margin : 0);
                textView.setText(importantText.getText());
                counter = 1;
            }

            textView.setLayoutParams(layoutParams);
            holder.mTextsLayout.addView(textView);
        }
    }
    @Override
    public int getItemCount() {
        return mImportantObjectsArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout    mTextsLayout;
        private TextView        mTitleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mTextsLayout       = (LinearLayout)    itemView.findViewById(R.id.texts_layout);
            this.mTitleTextView     = (TextView)        itemView.findViewById(R.id.title_text_view);
        }
    }
}
