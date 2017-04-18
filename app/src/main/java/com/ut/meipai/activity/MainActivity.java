package com.ut.meipai.activity;

import android.support.v4.app.FragmentTransaction;
import android.widget.Button;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ut.meipai.R;
import com.ut.meipai.base.BaseActivity;
import com.ut.meipai.fragment.FragmentFactory;

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
    @BindView(R.id.btn_meipai_main)
    Button mMpBtn;

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
    public void getPageData() {
    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_fragment_mainActivity, FragmentFactory.create(0)).commit();
    }

    private void initBottomNavBar() {

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
                .setFirstSelectedPosition(0)
                .initialise();

        mBottomNavBar.setTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(int position) {
        if (position == 2) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_fragment_mainActivity, FragmentFactory.create(position)).commit();
    }

    @Override
    public void onTabUnselected(int position) {
    }

    @Override
    public void onTabReselected(int position) {
    }
}
