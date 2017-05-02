package com.ut.meipai.activity;

import android.content.Context;

import com.ut.meipai.R;
import com.ut.meipai.entity.VideoZhiboHeaderEntity;
import com.ut.meipai.util.GlideUtil;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by LZM on 2017/4/28.
 * Function:
 * Desc:
 */

public class MyHeadZhiboAdapter extends BGAAdapterViewAdapter<VideoZhiboHeaderEntity> {


    public MyHeadZhiboAdapter(Context context) {
        super(context, R.layout.item_zhibo_bga_head);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, VideoZhiboHeaderEntity model) {
        if (model.type == 0){
            helper.setText(R.id.tv_item_head_chart,"本周撒币榜");
        }else{
            helper.setText(R.id.tv_item_head_chart,"本周制杖榜");
        }
        GlideUtil.loadCircleImg(model.imageOne,helper.getImageView(R.id.iv_right_one));
        GlideUtil.loadCircleImg(model.imageTwo,helper.getImageView(R.id.iv_right_two));
        GlideUtil.loadCircleImg(model.imageThree,helper.getImageView(R.id.iv_right_three));
    }
}
