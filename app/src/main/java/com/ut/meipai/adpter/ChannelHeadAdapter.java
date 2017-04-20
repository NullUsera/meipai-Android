package com.ut.meipai.adpter;

import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ut.meipai.R;
import com.ut.meipai.entity.ChannelHeadViewEntity;
import com.ut.meipai.util.GlideUtil;

import java.util.List;

/**
 * Created by LZM on 2017/4/20.
 * Function:
 * Desc:
 */

public class ChannelHeadAdapter extends BaseQuickAdapter<ChannelHeadViewEntity, BaseViewHolder> {

    public ChannelHeadAdapter(List<ChannelHeadViewEntity> data) {
        super(R.layout.item_channel_head, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChannelHeadViewEntity entity) {
        if (entity == null) return;
        ((TextView)helper.getView(R.id.tv_channel_head_type)).setText(entity.type);
        GlideUtil.loadImg(entity.image, (ImageView) helper.getView(R.id.iv_channel_head_image));
    }

}
