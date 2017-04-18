package com.ut.meipai.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ut.meipai.R;
import com.ut.meipai.util.DensityUtil;


public class DropDialog extends Dialog {
    private Context context;
    private View    contentView;
    private int     layoutRes;
    private int     gravity;
    private boolean hasMargin = false;

    /**
     * 自定义dialog的构造方法 用于底部弹出的对话框(默认无边距)
     */
    public DropDialog(Context context, int resLayout) {
        super(context, R.style.dropDialog);
        this.context = context;
        this.layoutRes = resLayout;
        gravity = Gravity.BOTTOM;
    }

    /**
     * 自定义dialog的构造方法
     */
    public DropDialog(Context context, int resLayout, int gravity, boolean hasMargin) {
        super(context, R.style.dropDialog);
        this.context = context;
        this.gravity = gravity;
        this.layoutRes = resLayout;
        this.hasMargin = hasMargin;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (contentView == null) {
            try {
                contentView = LayoutInflater.from(context).inflate(layoutRes, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (null == contentView) {
            return;
        }

        this.setContentView(contentView);

        Window dialogWindow = getWindow();
        if (dialogWindow != null) {
            dialogWindow.setGravity(gravity); // 位置
            dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);// 背景取消
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            int width_window = DensityUtil.getWindowWidth();
            if (hasMargin) {
                int xOffset = 100;
                lp.width = width_window - xOffset;
            } else {
                lp.width = width_window;
            }

            dialogWindow.setAttributes(lp);

            dialogWindow.setWindowAnimations(R.style.dialogPushUpInAnimation);// 加载动画
        }
    }

    public View getContentView() {
        if (contentView == null) {
            contentView = LayoutInflater.from(context).inflate(layoutRes, null);
        }
        return contentView;
    }
}
