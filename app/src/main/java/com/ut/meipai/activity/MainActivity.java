package com.ut.meipai.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.orhanobut.logger.Logger;
import com.ut.meipai.R;
import com.ut.meipai.fragment.FragmentFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.bottom_bar_mainActivity)
    BottomNavigationBar mBottomNavBar;
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initBottomNavBar();
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.fl_fragment_mainActivity, FragmentFactory.create(0)).commit();
    }

    private void initBottomNavBar() {

        BadgeItem badgeItem = new BadgeItem();
        badgeItem.setHideOnSelect(false)
                .setText("10")
                .setBackgroundColorResource(R.color.orange)
                .setBorderWidth(0);

        mBottomNavBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavBar.addItem(new BottomNavigationItem(R.drawable.icon_one, R.string.tab_one).setActiveColorResource(R.color.green).setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.drawable.icon_two, R.string.tab_two).setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.icon_three, R.string.tab_three).setActiveColorResource(R.color.lime))
                .addItem(new BottomNavigationItem(R.drawable.icon_four, R.string.tab_four))
                .setFirstSelectedPosition(0)
                .initialise();

        mBottomNavBar.setTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(int position) {
        mFragmentTransaction.replace(R.id.fl_fragment_mainActivity, FragmentFactory.create(position));
    }

    @Override
    public void onTabUnselected(int position) {
        Logger.d("onTabUnselected: " + position);
    }

    @Override
    public void onTabReselected(int position) {
        Logger.d("onTabSelected: " + position);
    }
}
