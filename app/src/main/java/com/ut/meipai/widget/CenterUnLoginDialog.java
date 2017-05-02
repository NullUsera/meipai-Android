package com.ut.meipai.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by LZM on 2017/4/21.
 * Function:
 * Desc:
 */

public class CenterUnLoginDialog extends Dialog {

    private Context context;
    private View contentView;
    private int gravity;
    private int layoutRes;

    public CenterUnLoginDialog(Context context, int resLayout) {
        super(context, resLayout);
        this.context = context;
        this.layoutRes = resLayout;
        gravity = Gravity.CENTER;
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
    }
}
