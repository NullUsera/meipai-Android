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
    private boolean isCanBack;
    /**
     * 是否有扩展功能
     */
    private boolean hasExtensionFunc;

    @BindView(R.id.tv_page_title)
    TextView mTvTitle;
    @BindView(R.id.bt_page_back)
    Button   mBtBack;
    @BindView(R.id.bt_extension_func)
<<<<<<< HEAD
    TextView mExtensionFunc;

    @OnClick(R.id.bt_page_back)
    public void back() {
=======
    Button   mExtensionFunc;

    @OnClick(R.id.bt_page_back)
    void back() {
>>>>>>> 220c4005b2995784087b422e6c344bf8c8325271
        if (isCanBack) {
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
        isCanBack = ta.getBoolean(R.styleable.titleBar_canBack, true);
        String extensionText = ta.getString(R.styleable.titleBar_extension_text);
        // 设置当前页面标题
        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }
        // 是否显示返回按钮
        mBtBack.setVisibility(isCanBack ? View.VISIBLE : View.INVISIBLE);
        // 是否有扩展功能
        hasExtensionFunc = !TextUtils.isEmpty(extensionText);
        if (hasExtensionFunc) {
            mExtensionFunc.setText(extensionText);
        }

        ta.recycle();
    }

    /**
     * 扩展功能的点击事件
     *
     * @param listener {@link OnExtensionListener}
     */
    public void setOnExtensionListener(final OnExtensionListener listener) {
        if (hasExtensionFunc)
            mExtensionFunc.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onExtensionClick(v);
                }
            });
    }

    public interface OnExtensionListener {
        void onExtensionClick(View v);
    }
}
