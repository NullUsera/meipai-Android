package com.ut.meipai.activity;

import android.os.Bundle;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import com.ut.meipai.R;
import com.ut.meipai.app.MeiPaiApplication;
import com.ut.meipai.base.BaseActivity;
import com.ut.meipai.listeners.IWeakHandler;
import com.ut.meipai.util.FileUtil;
import com.ut.meipai.util.WeakHandler;
import com.ut.meipai.widget.FocusSurfaceView;
import com.ut.meipai.widget.RecordBtnWrapper;
import com.ut.meipai.widget.RecordProgressView;
import com.ut.meipai.widget.RoundProgressDialog;
import com.yixia.camera.MediaRecorderNative;
import com.yixia.camera.VCamera;
import com.yixia.camera.model.MediaObject;
import com.yixia.videoeditor.adapter.UtilityAdapter;

import java.io.File;
import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 任和 on 2017/04/16 22:10
 * Function:  视频录制页面
 * Desc:
 */

public class RecordVideoActivity extends BaseActivity implements RecordBtnWrapper.IRecordCallback, RecordProgressView.IRecordOverCallback, IWeakHandler {
    final String RECORD_TEMP_FILE_NAME = "/recordTemp";

    final int MSG_PROGRESS = 1001;
    final int MSG_DEAL_VIDEO = 1002;

    final int DELAY_TIME = 30;

    @BindView(R.id.img_back_recordVideoActivity)
    ImageView imgBackRecordVideoActivity;
    @BindView(R.id.img_flash_light_recordVideoActivity)
    ImageView imgFlashLightRecordVideoActivity;
    @BindView(R.id.img_turn_camera_recordVideoActivity)
    ImageView imgTurnCameraRecordVideoActivity;
    @BindView(R.id.sv_focus_recordVideoActivity)
    FocusSurfaceView svFocusRecordVideoActivity;
    @BindView(R.id.tv_record_type)
    TextView tvRecordType;
    @BindView(R.id.btn_record_recordVideoActivity)
    ImageView btnRecordRecordVideoActivity;
    @BindView(R.id.img_record_back_recordVideoActivity)
    ImageView imgRecordBackRecordVideoActivity;
    @BindView(R.id.img_record_ok_recordVideoActivity)
    ImageView imgRecordOkRecordVideoActivity;
    @BindView(R.id.pv_progress_recordVideoActivity)
    RecordProgressView pvProgressRecordVideoActivity;

    private MediaRecorderNative mMediaRecorder;
    private MediaObject mMediaObject;

    private RoundProgressDialog mDealDialog;

    private WeakHandler mHandler;

    private String mOutPath;
    private String mVideoPath;

    private boolean isOver = false;

    /**
     * 初始化录制对象
     */
    private void initMediaRecorder() {
        mMediaRecorder = new MediaRecorderNative();
        String key = String.valueOf(System.currentTimeMillis());
        //设置缓存文件夹
        mMediaObject = mMediaRecorder.setOutputDirectory(key, VCamera.getVideoCachePath());
        //设置视频预览源
        mMediaRecorder.setSurfaceHolder(svFocusRecordVideoActivity.getHolder());
        //准备
        mMediaRecorder.prepare();
        //滤波器相关
        UtilityAdapter.freeFilterParser();
        UtilityAdapter.initFilterParser();

    }

    private void initVCamera() {
        mVideoPath = FileUtil.getCacheDir(this) + RECORD_TEMP_FILE_NAME + "/" + System.currentTimeMillis();
        File file = new File(mVideoPath);
        if (!file.exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.mkdirs();
        }

        VCamera.setVideoCachePath(mVideoPath);
        VCamera.setDebugMode(true);
        VCamera.initialize(getApplicationContext());
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_record_video;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMediaRecorder.startPreview();
    }

    @Override
    protected void initView(Bundle var1) {
        initVCamera();

        initMediaRecorder();

        svFocusRecordVideoActivity.setTouchFocus(mMediaRecorder);

        //处理事件
        new RecordBtnWrapper(btnRecordRecordVideoActivity, this);

        pvProgressRecordVideoActivity.setRecordOverCallback(this);

        mHandler = new WeakHandler(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMediaRecorder.stopPreview();
    }

    @Override
    public void onLongClicked() {
        startRecord();
    }

    @Override
    public void onLift() {
        stopRecord(true);
    }

    @Override
    public void recordOver() {
        endRecord();
    }

    private void startRecord() {
        isOver = false;

        mMediaRecorder.startRecord();
        mHandler.sendEmptyMessageDelayed(MSG_PROGRESS, DELAY_TIME);
    }

    private void stopRecord(boolean isAddSpilt) {
        isOver = true;

        if (isAddSpilt) {
            pvProgressRecordVideoActivity.addSpilt();
        }

        mMediaRecorder.stopRecord();

    }

    private void endRecord() {
        stopRecord(false);

        showDealDialog();

        mHandler.sendEmptyMessage(MSG_DEAL_VIDEO);
    }

    private void showDealDialog() {
        if (mDealDialog == null) {
            mDealDialog = new RoundProgressDialog(this);
        }
        mDealDialog.show();
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.what == MSG_DEAL_VIDEO) {
            int progress = UtilityAdapter.FilterParserAction("", UtilityAdapter.PARSERACTION_PROGRESS);
            mDealDialog.setProgress(progress);
            if (progress == 100) {
                dealVideo();
            } else if (progress == -1) {
                showToast("视屏合成失败");
            } else {
                mHandler.sendEmptyMessageDelayed(MSG_DEAL_VIDEO, DELAY_TIME);
            }
        } else if (msg.what == MSG_PROGRESS) {
            if (!isOver) {
                pvProgressRecordVideoActivity.setProgress(mMediaObject.getDuration());
                mHandler.sendEmptyMessageDelayed(MSG_PROGRESS, DELAY_TIME);
            }
        }
    }

    /**
     * 合成视频
     */
    private void dealVideo() {
        //ffmpeg -i "concat:ts0.ts|ts1.ts|ts2.ts|ts3.ts" -c copy -bsf:a aac_adtstoasc out2.mp4
        StringBuilder sb = new StringBuilder("ffmpeg");
        sb.append(" -i concat:");

        int size = mMediaObject.getMediaParts().size();
        for (int i = 0; i < size; i++) {
            MediaObject.MediaPart part = mMediaObject.getPart(i);
            sb.append(part.mediaPath);
            if (i != size - 1) {
                sb.append("|");
            }
        }

        mOutPath = getOutPath();

        sb.append(" -c")
                .append(" copy")
                .append(" -bsf:a")
                .append(" aac_adtstoasc")
                .append(" -y")
                .append(" " + mOutPath);

        int isSuccess = UtilityAdapter.FFmpegRun("", sb.toString());

        mDealDialog.dismiss();

        if (isSuccess == 0) {
            //to EditVideoActivity
            showToast("视频合成成功");
        } else {
            showToast("视频合成失败");
        }
    }

    private String getOutPath() {
        return mVideoPath + "/deal.mp4";
    }

    @OnClick(R.id.img_record_ok_recordVideoActivity)
    public void onFinishRecordClicked() {
        endRecord();
    }

    @OnClick(R.id.img_record_back_recordVideoActivity)
    public void onRecordBackClicked() {
        pvProgressRecordVideoActivity.clearLastStep();

        if (mMediaObject.getMediaParts().size() > 0) {
            MediaObject.MediaPart lastPart = mMediaObject.getPart(mMediaObject.getMediaParts().size() - 1);
            mMediaObject.removePart(lastPart, true);
        }
    }

    @Override
    protected void onDestroy() {
        mMediaRecorder.release();
        mHandler.removeMessages(MSG_PROGRESS);
        mHandler.removeMessages(MSG_DEAL_VIDEO);
        super.onDestroy();
    }
}
