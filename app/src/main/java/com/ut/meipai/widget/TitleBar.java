package com.ut.meipai.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ut.meipai.R;
import com.ut.meipai.manager.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 任和 on 2017/04/13 13:16
 * Function:  自定义页面标题栏
 * Desc:
 */
//// TODO: 2017/4/13 待扩展 任和
public class TitleBar extends RelativeLayout {
    /**
     * 当前页是否显示返回按钮，默认显示
     */
    private boolean mCanBack;

    @BindView(R.id.tv_page_title)
    TextView mTvTitle;
    @BindView(R.id.bt_page_back)
    Button mBtBack;

    @OnClick(R.id.bt_page_back)
    void back() {
        if (mCanBack) {
            AppManager.getAppManager().finishActivity();
        }
    }

    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        View.inflate(context, R.layout.layout_title_bar, TitleBar.this);
        ButterKnife.bind(TitleBar.this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.titleBar);

        String title = ta.getString(R.styleable.titleBar_title);
        mCanBack = ta.getBoolean(R.styleable.titleBar_canBack, true);
        // 设置当前页面标题
        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }
        // 控制是否显示返回按钮
        mBtBack.setVisibility(mCanBack ? View.VISIBLE : View.INVISIBLE);

        ta.recycle();
    }
}
