package com.ut.meipai.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import com.ut.meipai.R;
import com.ut.meipai.util.DensityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by arvin on 16/11/26 17:35
 * email：1035407623@qq.com
 */
public class RoundProgressDialog extends Dialog {
    @BindView(R.id.progress)
    RoundProgressBar progressBar;

    public RoundProgressDialog(Context context) {
        super(context, R.style.normal_dialog_white);
        setContentView(R.layout.dialog_progress);
        ButterKnife.bind(this);
        progressBar.setMax(100);

        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = DensityUtil.getScreenWidth(context) * 8 / 10;//宽高可设置具体大小
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }

    public void setProgress(int progress) {
        progressBar.setProgress(progress);
    }
}
