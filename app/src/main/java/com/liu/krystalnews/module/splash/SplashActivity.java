package com.liu.krystalnews.module.splash;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.liu.krystalnews.module.common.MainActivity;
import com.liu.krystalnews.R;
import com.liu.krystalnews.base.BaseActivity;
import com.liu.krystalnews.base.BasePresenter;

import butterknife.BindView;

/**
 * 启动页
 * Created by liu on 2017/3/8.
 */

public class SplashActivity extends BaseActivity{
    @BindView(R.id.splash_iv)
    ImageView mSplashImageView;

    private static final int ANIMATION_TIME = 2000;
    private static final float SCALE_END = 1.13F;

    @Override
    protected void initView(Bundle savedInstanceState) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mSplashImageView, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mSplashImageView, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIMATION_TIME).play(animatorX).with(animatorY);
        set.start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                SplashActivity.this.finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    protected BasePresenter initPresenter() {
        //简单页面没必要建立Presenter
        return null;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activtiy_splash;
    }

}
