package com.gradski.transport.varna.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.gradski.transport.varna.R;

/**
 * Created by lyubomir.babev on 28/08/2017.
 */

public class SplashActivity extends BaseActivity {
    private boolean mShouldStartNextActivity = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        startHandler();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mShouldStartNextActivity) {
            mShouldStartNextActivity = true;
            startHandler();
        }
    }

    @Override
    protected void onPause() {
        mShouldStartNextActivity = false;
        super.onPause();
    }

    private void startHandler() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mShouldStartNextActivity) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 2000);
    }
}
