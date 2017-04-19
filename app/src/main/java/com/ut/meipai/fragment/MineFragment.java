package com.ut.meipai.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ut.meipai.R;
import com.ut.meipai.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 任和 on 2017/04/12 16:08
 * Function:  个人中心
 * Desc:
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.tv_mine_level)TextView mTvLevel;
    @BindView(R.id.ll_mine_message)LinearLayout mLlMessage;
    @BindView(R.id.ll_mine_done_message)LinearLayout mLlDoneMessage;
    private boolean isLogin;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this,view);
        hideView();
    }

    private void hideView() {
        if (!isLogin){
//            mLlMessage.setVisibility(View.GONE);
//            mLlDoneMessage.setVisibility(View.GONE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @OnClick({R.id.stv_mine_draft,R.id.stv_mine_friend,R.id.stv_mine_money,R.id.stv_mine_praised
    ,R.id.stv_mine_school,R.id.stv_mine_setting,R.id.stv_mine_video})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.stv_mine_draft:
                break;
            case R.id.stv_mine_friend:
                break;
            case R.id.stv_mine_money:
                break;
            case R.id.stv_mine_praised:
                break;
            case R.id.stv_mine_school:
                break;
            case R.id.stv_mine_setting:
                break;
            case R.id.stv_mine_video:
                break;
        }
    }
}
