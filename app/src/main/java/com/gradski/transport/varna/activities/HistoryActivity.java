package com.gradski.transport.varna.activities;

import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;

import com.gradski.transport.varna.R;
import com.gradski.transport.varna.globalClasses.Utils;

import java.io.IOException;

/**
 * Created by lyubomir.babev on 02/06/2017.
 */

public class HistoryActivity extends BaseActivity implements MediaPlayer.OnPreparedListener, TextureView.SurfaceTextureListener, View.OnClickListener, MediaController.MediaPlayerControl {

    private MediaPlayer         mMediaPlayer;
    private MediaController     mMediaController;
    private TextureView         mTextureView;
    private RelativeLayout      mVideoLayout;
    private static final double ASPECT_RATIO = 1.68;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_history);

        init();
    }

    private void init() {
        mMediaPlayer    = new MediaPlayer();
        mTextureView    = (TextureView)     findViewById(R.id.texture_view);
        mVideoLayout    = (RelativeLayout)  findViewById(R.id.video_layout);

        mMediaController = new MediaController(this);
        mTextureView.setSurfaceTextureListener(this);
        mVideoLayout.setOnClickListener(this);
        findViewById(R.id.back_arrow_image_view).setOnClickListener(this);
    }

    private void startVideoInTextureView(SurfaceTexture surfaceTexture) {
        Surface                         surface     = new Surface(surfaceTexture);
        LinearLayout.LayoutParams       params      = new LinearLayout.LayoutParams(getDisplayWidth(), (int)(getDisplayWidth() / ASPECT_RATIO));

        mVideoLayout.setLayoutParams(params);

        try {
            AssetFileDescriptor assetFileDescriptor = getAssets().openFd(Utils.HISTORY_VIDEO_FILE_NAME);
            mMediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            mMediaPlayer.setSurface(surface);
            mMediaPlayer.setLooping(true);
            mMediaPlayer.setOnPreparedListener(this);
            assetFileDescriptor.close();
            mMediaPlayer.prepare();

        } catch (IllegalArgumentException | SecurityException | IOException | IllegalStateException e) {
            e.printStackTrace();
        }
    }

    private int getDisplayWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_arrow_image_view)
            super.onBackPressed();
        else if (v.getId() == mVideoLayout.getId())
            mMediaController.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mMediaPlayer != null && !mMediaPlayer.isPlaying())
            mMediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mMediaPlayer != null) {
            mMediaController.removeAllViews();
            mMediaController.hide();
            mMediaPlayer.release();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mMediaPlayer.start();
        mMediaController.setMediaPlayer(this);
        mMediaController.setAnchorView(mTextureView);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        startVideoInTextureView(surface);
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    @Override
    public void start() {
        mMediaPlayer.start();
    }

    @Override
    public void pause() {
        mMediaPlayer.pause();
    }

    @Override
    public int getDuration() {
        return mMediaPlayer.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return mMediaPlayer.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        mMediaPlayer.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }
}
