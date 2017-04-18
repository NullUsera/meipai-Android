package com.ut.meipai.widget;

import android.view.MotionEvent;
import android.view.View;

import com.ut.meipai.util.DensityUtil;

/**
 * Created by 刘金伟 on 17/4/17 13:44
 * Function：
 * Desc：
 */
public class RecordBtnWrapper implements View.OnTouchListener {
    private View mBtnRecord;

    private IRecordCallback mCallback;

    private boolean isLongClicked;

    private float mDownX;
    private float mDownY;
    private int mSlop;

    public RecordBtnWrapper(View btnRecord, IRecordCallback callback) {
        this.mBtnRecord = btnRecord;
        this.mCallback = callback;

        init();
    }

    private void init() {
        mBtnRecord.setOnTouchListener(this);
        mSlop = DensityUtil.dp2px(mBtnRecord.getContext(), 8);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getRawX();
                mDownY = event.getRawY();
                isLongClicked = true;
                mBtnRecord.postDelayed(mLongClickRunnable, 500);
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(mDownX - event.getRawX()) > mSlop || Math.abs(mDownY - event.getRawY()) > mSlop) {
                    isLongClicked = false;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isLongClicked = false;
                mCallback.onLift();
                break;
        }
        return true;
    }

    private Runnable mLongClickRunnable = new Runnable() {
        @Override
        public void run() {
            if (isLongClicked) {
                mCallback.onLongClicked();
            }
        }
    };

    public interface IRecordCallback {
        void onLongClicked();

        void onLift();
    }
}
