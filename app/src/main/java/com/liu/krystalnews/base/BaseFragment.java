package com.liu.krystalnews.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Fragment基类
 * Created by liu on 2017/3/9.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView{
    protected T mPresenter;
    //Fragment的上下文
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
        mPresenter=initPresenter();
        if (mPresenter!=null){
            mPresenter.attachView(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(getLayoutID(),container,false);
        ButterKnife.bind(this,rootView);
        initView(rootView,savedInstanceState);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mPresenter!=null){
            mPresenter.onCreate();
        }
    }

    @Override
    public void onDetach() {
        if (mPresenter!=null){
            mPresenter.detachView();
        }
        super.onDetach();
    }

    protected Context getHoldContext(){
        return mContext;
    }

    /**
     * 初始化View
     * @param rootView rootView
     * @param savedInstanceState savedInstanceState
     */
    protected abstract void initView(View rootView, Bundle savedInstanceState);

    /**
     * 创建presenter
     * @return presenter
     */
    protected abstract T initPresenter();


    protected abstract int getLayoutID();
}
