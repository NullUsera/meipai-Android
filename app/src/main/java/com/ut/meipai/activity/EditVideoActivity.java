package com.ut.meipai.activity;

import android.media.MediaPlayer;
import android.os.Bundle;

import com.ut.meipai.R;
import com.ut.meipai.base.BaseActivity;
import com.ut.meipai.widget.MyVideoView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 刘金伟 on 17/4/18 11:43
 * Function：
 * Desc：
 */
public class EditVideoActivity extends BaseActivity {
    @BindView(R.id.video_content_editVideoActivity)
    MyVideoView videoContentEditVideoActivity;

    @Override
    public void initView() {
        setContentView(R.layout.activity_edit_video);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        videoContentEditVideoActivity.setVideoPath(getIntent().getStringExtra("path"));

        videoContentEditVideoActivity.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoContentEditVideoActivity.setLooping(true);
                videoContentEditVideoActivity.start();
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        videoContentEditVideoActivity.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoContentEditVideoActivity.start();
    }


}
