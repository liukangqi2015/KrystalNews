package com.liu.krystalnews.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import butterknife.ButterKnife;


/**
 * Activity的基类
 * Created by liu on 2017/3/7.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView{
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView();
        initView(savedInstanceState);
        //创建Presenter
        mPresenter=initPresenter();
        if (mPresenter!=null){
            //绑定View
            mPresenter.attachView(this);
            //初始化Presenter
            mPresenter.onCreate();
        }
    }

    /**
     * 初始化ContentView
     */
    protected void initContentView(){
        //设置布局
        setContentView(getLayoutID());
        ButterKnife.bind(this);
        //状态栏透明（改style还是有些机型不生效）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter!=null){
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    /**
     * 初始化view
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 创建presenter
     * @return presenter
     */
    protected abstract T initPresenter();

    /**
     * 得到布局ID
     * @return
     */
    protected abstract int getLayoutID();

}
