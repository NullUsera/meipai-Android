package com.ut.meipai.adpter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.orhanobut.logger.Logger;
import com.ut.meipai.R;
import com.ut.meipai.entity.ChannelHotEntity;
import com.ut.meipai.util.GlideUtil;

import java.util.List;

/**
 * Created by LZM on 2017/4/19.
 * Function:
 * Desc:
 */

public class ChannelHotAdapter extends BaseQuickAdapter<ChannelHotEntity, BaseViewHolder> {

    public ChannelHotAdapter(List<ChannelHotEntity> data) {
        super(R.layout.item_channel_hot, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChannelHotEntity entity) {
        Logger.e("___adapter进入");
        if (entity == null) return;
        if (entity.isAward) helper.getView(R.id.tv_channel_hot_award).setVisibility(View.VISIBLE);
        if (entity.isHot) helper.getView(R.id.tv_channel_hot_extra).setVisibility(View.VISIBLE);
        ((TextView) helper.getView(R.id.tv_channel_hot_name)).setText("#" + entity.name + "#");
        ((TextView) helper.getView(R.id.tv_channel_hot_number)).setText(entity.playNumber + "万播放");
        GlideUtil.loadImg(entity.logo, (ImageView) helper.getView(R.id.iv_channel_hot_logo));
    }

}
