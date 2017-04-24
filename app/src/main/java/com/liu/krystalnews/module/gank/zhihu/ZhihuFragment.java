package com.liu.krystalnews.module.gank.zhihu;

import android.os.Bundle;
import android.view.View;

import com.liu.krystalnews.base.BaseFragment;

/**
 * Created by Administrator on 2017/3/16.
 */

public class ZhihuFragment extends BaseFragment<ZhiHuPresenter> {
    @Override
    protected void initView(View rootView, Bundle savedInstanceState) {

    }

    @Override
    protected ZhiHuPresenter initPresenter() {
        return new ZhiHuPresenter();
    }

    @Override
    protected int getLayoutID() {
        return 0;
    }
}
