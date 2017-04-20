package com.ut.meipai.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 任和 on 2017/04/12 15:58
 * Function:
 * Desc:
 * todo 懒加载未做，内容页第一次访问数据未处理
 */
public abstract class BaseFragment extends Fragment {

    protected Activity mContext;
    private Unbinder mUnbinder;
    protected View mView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         mView = inflater.inflate(getLayoutId(), container, false);
        mUnbinder=ButterKnife.bind(this,mView);
        this.beforeInitView();
        this.initView(mView, savedInstanceState);
        return mView;
    }

    protected abstract void initView(View view, Bundle savedInstanceState);

    protected void beforeInitView(){

    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = (Activity)context;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    /**
     * 获取Fragment布局文件ID
     */
    protected abstract int getLayoutId();
}
