package com.liu.krystalnews.module.common;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.liu.krystalnews.R;
import com.liu.krystalnews.base.BaseFragment;
import com.liu.krystalnews.base.BasePresenter;

import butterknife.BindView;

/**
 * 测试的Fragment
 * Created by liu on 2017/3/9.
 */

public class SimpleFragment extends BaseFragment {
    private String mText;
    @BindView(R.id.tv)
    TextView tv;

    public static SimpleFragment getInstance(String mText){
        SimpleFragment simpleFragment=new SimpleFragment();
        simpleFragment.mText=mText;
        return simpleFragment;
    }

    @Override
    protected void initView(View rootView, Bundle savedInstanceState) {
        tv.setText(mText);
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.frag_simple;
    }
}
