package com.ut.meipai.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.ut.meipai.R;
import com.ut.meipai.base.BaseActivity;
import com.ut.meipai.entity.VideoTabNamesEntity;
import com.ut.meipai.fragment.ChannelFragment;
import com.ut.meipai.fragment.MineFragment;
import com.ut.meipai.fragment.MyFocusFragment;
import com.ut.meipai.fragment.VideoFragment;
import com.ut.meipai.manager.AppManager;
import com.ut.meipai.widget.DropDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 任和 on 2017/04/12 15:58
 * Function: 首页
 * Desc:
 */
public class MainActivity extends BaseActivity{

    @BindView(R.id.tablayout)CommonTabLayout mTabLayout;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntity=new ArrayList<>();

    @BindView(R.id.btn_main_meipai)
    Button mMpBtn;
    private DropDialog mCreateDialog;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle var) {
        ButterKnife.bind(this);
        initTab();
        setDefault();
//        int width = (int) (getResources().getDisplayMetrics().widthPixels*0.2);
//        mMpBtn.setWidth(width);
    }

    @Override
    public void loadPageData() {
    }

    private void setDefault() {
        mTabLayout.setTabData(mTabEntity,this,R.id.fLayout_container,mFragments);

    }

    private void initTab() {
        mTabEntity.add(new VideoTabNamesEntity("美拍",R.drawable.main_meipai_select,R.drawable.main_meipai_noselect));
        mTabEntity.add(new VideoTabNamesEntity("关注",R.drawable.main_concern_select,R.drawable.main_concern_noselect));
        mTabEntity.add(new VideoTabNamesEntity("  ",R.drawable.main_meipai_select,R.drawable.main_meipai_noselect));
        mTabEntity.add(new VideoTabNamesEntity("频道",R.drawable.main_channel_select,R.drawable.main_channel_noselect));
        mTabEntity.add(new VideoTabNamesEntity("我",R.drawable.main_personal_select,R.drawable.main_personal_noselect));
//        BadgeItem badgeItem = new BadgeItem();
//        badgeItem.setHideOnSelect(false)
//                .setText("10")
//                .setBackgroundColorResource(R.color.orange)
//                .setBorderWidth(0);
//        mBottomNavBar.setMode(BottomNavigationBar.MODE_FIXED);
//        mBottomNavBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
//        mBottomNavBar.addItem(new BottomNavigationItem(R.drawable.main_meipai_noselect, R.string.tab_video).setActiveColorResource(R.color.pink))
//                .addItem(new BottomNavigationItem(R.drawable.main_concern_noselect, R.string.tab_focus).setActiveColorResource(R.color.pink))
//                .addItem(new BottomNavigationItem(R.drawable.main_meipai_nopress,"   "))
//                .addItem(new BottomNavigationItem(R.drawable.main_channel_noselect, R.string.tab_channel).setActiveColorResource(R.color.pink))
//                .addItem(new BottomNavigationItem(R.drawable.main_personal_noselect, R.string.tab_mine).setActiveColorResource(R.color.pink))
//                .setFirstSelectedPosition(0)
//                .initialise();
//
//        mBottomNavBar.setTabSelectedListener(this);
        mFragments.add(new VideoFragment());
        mFragments.add(new MyFocusFragment());
        mFragments.add(new Fragment());
        mFragments.add(new ChannelFragment());
        mFragments.add(new MineFragment());
    }

    /**
     * 底部弹出框选择拍摄类型
     */
    private void showCreateDialog() {
        if (mCreateDialog == null) {
            mCreateDialog = new DropDialog(this, R.layout.dialog_create_video, Gravity.BOTTOM, false);
        }
        mCreateDialog.show();
        View createView = mCreateDialog.getContentView();
        if (createView != null) {
            createView.findViewById(R.id.tv_record_short_video).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    redirect(v);
                }
            });
            createView.findViewById(R.id.tv_take_photo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    redirect(v);
                }
            });
        }
    }

    @OnClick({R.id.btn_main_meipai})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.btn_main_meipai:
                showCreateDialog();
                break;
        }
    }
    /**
     * 跳转到对应功能页
     * @param v
     */
    private void redirect(View v) {
        Class<?> clazz = v.getId() == R.id.tv_take_photo ? TakePhotoActivity.class : RecordVideoActivity.class;
        AppManager.redirectPage(this, clazz);

        if (mCreateDialog != null) {
            mCreateDialog.dismiss();
        }
    }

}
