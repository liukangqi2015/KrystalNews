package com.liu.krystalnews.base;

/**
 * Presenter基类
 * Created by liu on 2017/3/7.
 */

public abstract class BasePresenter<T extends BaseView> {
    protected T mView;

    /**
     * 绑定View
     * @param view view
     */
    public void attachView(T view){
        mView=view;
    }

    /**
     * View销毁的时候，解绑
     */
    public void detachView(){
        mView=null;
    }

    /**
     * 逻辑开始
     */
    public abstract void onCreate();
}
