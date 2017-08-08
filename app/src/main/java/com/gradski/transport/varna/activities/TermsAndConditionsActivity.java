package com.gradski.transport.varna.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.gradski.transport.varna.R;


/**
 * Created by lyubomir.babev on 01/06/2017.
 */

public class TermsAndConditionsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_terms_and_conditions);

        findViewById(R.id.back_arrow_image_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TermsAndConditionsActivity.super.onBackPressed();
            }
        });
    }
}
