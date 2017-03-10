package com.liu.krystalnews.module.gank;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.liu.krystalnews.R;
import com.liu.krystalnews.base.BaseFragment;
import com.liu.krystalnews.base.BasePresenter;
import com.liu.krystalnews.module.common.SimpleFragment;

import butterknife.BindArray;
import butterknife.BindView;

/**
 * 日报干货Fragment
 * Created by liu on 2017/3/10.
 */

public class GankFragment extends BaseFragment {


    @BindView(R.id.gank_tl)
    TabLayout gankTl;
    @BindView(R.id.gank_vp)
    ViewPager gankVp;
    @BindArray(R.array.gank_titles)
    String[] titles;

    @Override
    protected void initView(View rootView, Bundle savedInstanceState) {
        gankVp.setAdapter(new GankPagerAdapter(getChildFragmentManager()));
        gankTl.setupWithViewPager(gankVp);
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_gank;
    }

    private class GankPagerAdapter extends FragmentStatePagerAdapter{

        public GankPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return SimpleFragment.getInstance(titles[position]);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }
}
