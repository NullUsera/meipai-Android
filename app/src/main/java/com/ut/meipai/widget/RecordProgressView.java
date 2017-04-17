package com.ut.meipai.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.ut.meipai.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘金伟 on 17/4/17 13:52
 * Function：
 * Desc：
 */
public class RecordProgressView extends ProgressBar {
    final int DEFAULT_MS = 10 * 1000;
    final int LONG_MS = 5 * 60 * 1000;
    /**
     * 存储每个间隔之间的那个进度
     */
    private List<Integer> mSpiltIndex;

    private Paint mSpiltPaint;

    private int mRecordDuring;

    private IRecordOverCallback mRecordOver;


    public RecordProgressView(Context context) {
        super(context);
        init();
    }


    public RecordProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RecordProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RecordProgressView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    protected void init() {
        mSpiltIndex = new ArrayList<>();

        mSpiltPaint = new Paint();
        mSpiltPaint.setAntiAlias(true);
        //这个颜色之后可以自定义
        mSpiltPaint.setColor(Color.parseColor("#cccccc"));

        setDefaultRecord();

        reset();
    }

    public void setLongRecord() {
        this.mRecordDuring = LONG_MS;
        reset();
    }

    public void setDefaultRecord() {
        this.mRecordDuring = DEFAULT_MS;
    }

    private void reset() {
        setMax(mRecordDuring);
        setProgress(0);
        clearAll();
    }

    public void addSpilt() {
        mSpiltIndex.add(getProgress());
        invalidate();
    }

    public void clearAll() {
        mSpiltIndex.clear();
    }

    public void clearLastStep() {
        if (mSpiltIndex.size() > 0) {
            mSpiltIndex.remove(mSpiltIndex.size() - 1);
        }
        if (mSpiltIndex.size() > 0) {
            setProgress(mSpiltIndex.get(mSpiltIndex.size() - 1));
        } else {
            setProgress(0);
        }
    }


    public void setRecordOverCallback(IRecordOverCallback recordOverCallback) {
        this.mRecordOver = recordOverCallback;
    }

    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
        if (progress >= getMax()) {
            if (mRecordOver != null) {
                mRecordOver.recordOver();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Integer integer : mSpiltIndex) {

            float left = ((float) integer / getMax()) * getMeasuredWidth();
            float right = left + DensityUtil.dp2px(getContext(), 2);
            float top = 0;
            float bottom = getMeasuredHeight();

            canvas.drawRect(left, top, right, bottom, mSpiltPaint);
        }

        //添加光标效果
    }

    public interface IRecordOverCallback {
        void recordOver();
    }
}
