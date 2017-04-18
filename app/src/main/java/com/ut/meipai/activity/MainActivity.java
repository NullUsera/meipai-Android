package com.ut.meipai.activity;

import android.support.v4.app.FragmentTransaction;
<<<<<<< HEAD
import android.widget.Button;
=======
import android.view.Gravity;
import android.view.View;
>>>>>>> master

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ut.meipai.R;
import com.ut.meipai.base.BaseActivity;
import com.ut.meipai.fragment.FragmentFactory;
import com.ut.meipai.manager.AppManager;
import com.ut.meipai.widget.DropDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任和 on 2017/04/12 15:58
 * Function: 首页
 * Desc:
 */
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.bottom_bar_mainActivity)
    BottomNavigationBar mBottomNavBar;
<<<<<<< HEAD
    @BindView(R.id.btn_meipai_main)
    Button mMpBtn;
=======
    private DropDialog mCreateDialog;
>>>>>>> master

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initBottomNavBar();
        setDefaultFragment();
//        int width = (int) (getResources().getDisplayMetrics().widthPixels*0.2);
//        mMpBtn.setWidth(width);
    }

    @Override
    public void loadPageData() {
    }

    private void setDefaultFragment() {
        onTabSelected(0);
    }

    private void initBottomNavBar() {

<<<<<<< HEAD
//        BadgeItem badgeItem = new BadgeItem();
//        badgeItem.setHideOnSelect(false)
//                .setText("10")
//                .setBackgroundColorResource(R.color.orange)
//                .setBorderWidth(0);

        mBottomNavBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavBar.addItem(new BottomNavigationItem(R.drawable.main_meipai_noselect, R.string.tab_video).setActiveColorResource(R.color.pink))
                .addItem(new BottomNavigationItem(R.drawable.main_concern_noselect, R.string.tab_focus).setActiveColorResource(R.color.pink))
                .addItem(new BottomNavigationItem(R.drawable.main_meipai_nopress,"   "))
                .addItem(new BottomNavigationItem(R.drawable.main_channel_noselect, R.string.tab_channel).setActiveColorResource(R.color.pink))
                .addItem(new BottomNavigationItem(R.drawable.main_personal_noselect, R.string.tab_mine).setActiveColorResource(R.color.pink))
=======
        BadgeItem badgeItem = new BadgeItem();
        badgeItem.setHideOnSelect(false)
                .setText("5")
                .setBackgroundColorResource(R.color.colorAccent)
                .setBorderWidth(0);

        mBottomNavBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavBar.addItem(new BottomNavigationItem(R.drawable.ic_main_meipai_normal, R.string.tab_video)).setActiveColor(R.color.colorAccent)
                .addItem(new BottomNavigationItem(R.drawable.ic_main_focus_normal, R.string.tab_focus).setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.ic_main_meipai_normal, "拍拍拍")).setActiveColor(R.color.colorAccent)
                .addItem(new BottomNavigationItem(R.drawable.ic_main_channel_normal, R.string.tab_channel).setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.ic_main_personal_normal, R.string.tab_mine).setActiveColorResource(R.color.colorAccent).setBadgeItem(badgeItem))
>>>>>>> master
                .setFirstSelectedPosition(0)
                .initialise();

        mBottomNavBar.setTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(int position) {
        if (position == 2) {
<<<<<<< HEAD
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_fragment_mainActivity, FragmentFactory.create(position)).commit();
=======
            showCreateDialog();
        } else {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fl_fragment_mainActivity, FragmentFactory.create(position)).commit();
        }
>>>>>>> master
    }

    @Override
    public void onTabUnselected(int position) {
    }

    @Override
    public void onTabReselected(int position) {
        showCreateDialog();
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
