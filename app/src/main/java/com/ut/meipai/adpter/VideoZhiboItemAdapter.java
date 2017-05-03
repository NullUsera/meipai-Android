package com.ut.meipai.adpter;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ut.meipai.R;
import com.ut.meipai.entity.VideoZhiboItemEntity;
import com.ut.meipai.util.GlideUtil;

import java.util.List;

/**
 * Created by LZM on 2017/5/3.
 * Function:
 * Desc:
 */

public class VideoZhiboItemAdapter extends BaseQuickAdapter<VideoZhiboItemEntity, BaseViewHolder> {

    public VideoZhiboItemAdapter(List data) {
        super(R.layout.item_video_zhibo_content, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoZhiboItemEntity entity) {
        if (entity == null) return;
        helper.setText(R.id.tv_item_zhibo_introduce, entity.introduce);
        helper.setText(R.id.tv_item_zhibo_name, entity.name);
        helper.setText(R.id.tv_item_zhibo_audiences, entity.nowAudiences);
        helper.setText(R.id.tv_item_zhibo_concern, entity.concern);
        GlideUtil.loadCircleImg(entity.logo, (ImageView) helper.getView(R.id.iv_item_zhibo_logo));
        GlideUtil.loadImg(entity.cover, (ImageView) helper.getView(R.id.iv_item_zhibo_cover));
    }
}
