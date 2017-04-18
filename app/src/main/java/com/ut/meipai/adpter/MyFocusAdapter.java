package com.ut.meipai.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ut.meipai.R;
import com.ut.meipai.entity.MyFocusEntity;
import com.ut.meipai.widget.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by 任和 on 2017/04/17 16:41
 * Function:  我的关注-视频列表
 * Desc:
 */
public class MyFocusAdapter extends RecyclerView.Adapter<MyFocusAdapter.MyFocusViewHolder> {
    private Context             mContext;
    private List<MyFocusEntity> mFocusEntities;

    public MyFocusAdapter(Context context, List<MyFocusEntity> focusEntities) {
        mContext = context;
        mFocusEntities = focusEntities;
    }

    @Override
    public MyFocusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyFocusViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_my_focus,
                parent, false));
    }

    @Override
    public void onBindViewHolder(MyFocusViewHolder holder, int position) {
        MyFocusEntity focusEntity = mFocusEntities.get(position);
        Glide.with(mContext).load(focusEntity.getUserAvatar()).into(holder.userAvatar);
        holder.userName.setText(focusEntity.getUserName());
        holder.createDate.setText(focusEntity.getCreateDate());
        holder.jsPlayer.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4",
                JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
        holder.jsPlayer.thumbImageView.setImageResource(R.drawable.ic_video_default_thumb);
    }

    @Override
    public int getItemCount() {
        return mFocusEntities.size();
    }

    class MyFocusViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_user_avatar_focusFragment)
        CircleImageView       userAvatar;
        @BindView(R.id.tv_userName_focusFragment)
        TextView              userName;
        @BindView(R.id.tv_date_focusFragment)
        TextView              createDate;
        @BindView(R.id.jc_player_focusFragment)
        JCVideoPlayerStandard jsPlayer;

        MyFocusViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
