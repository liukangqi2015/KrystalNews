package com.liu.krystalnews.module.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.liu.krystalnews.R;
import com.liu.krystalnews.base.BaseActivity;
import com.liu.krystalnews.base.BasePresenter;
import com.liu.krystalnews.module.gank.GankFragment;
import com.liu.krystalnews.utils.BlurBitmapUtil;

import butterknife.BindArray;
import butterknife.BindView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.draw_layout)
    DrawerLayout drawerLayout;
    private View head_layout;
    private ActionBarDrawerToggle toggle;

    private Fragment[] fragments;
    @BindArray(R.array.nav_menu_titles)
    String[] titles;

    private int currentTabIndex;

    private int index;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setNavigationView();
        initToolbar();
        initFragment();
    }

    private void setNavigationView() {
        //将NavigationView的headLayout的背景模糊
        head_layout = navigationView.inflateHeaderView(R.layout.head_layout);
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.head);
        Bitmap blurBitmap = BlurBitmapUtil.blurBitmap(this, decodeResource, 18f);
        head_layout.setBackground(new BitmapDrawable(getResources(), blurBitmap));
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initFragment() {
        GankFragment gankSimpleFragment = new GankFragment();
        SimpleFragment girlSimpleFragment = SimpleFragment.getInstance(getResources().getString(R.string.girls_image));
        fragments = new Fragment[]{gankSimpleFragment, girlSimpleFragment};
        clickItem(0);
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.nav_gank:
                clickItem(0);
                break;
            case R.id.nav_image:
                clickItem(1);
                break;
        }
        return true;
    }

    private void clickItem(int changeIndex) {
        index = changeIndex;
        navigationView.getMenu().getItem(changeIndex).setChecked(true);
        toolbar.setTitle(titles[changeIndex]);
        switchFragment();
        drawerLayout.closeDrawers();
    }

    private void switchFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(fragments[currentTabIndex]);
        if (!fragments[index].isAdded()) {
            fragmentTransaction.add(R.id.content, fragments[index]);
        }
        fragmentTransaction.show(fragments[index]).commit();
        currentTabIndex = index;
    }
}
