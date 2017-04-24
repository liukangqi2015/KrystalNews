package com.liu.krystalnews.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 知乎日报Retrofit工具类
 * Created by liu on 2017/3/16.
 */

public class ZhihuRetrofitUtil {
    private static final String BAE_URL="http://news-at.zhihu.com/";
    private ZhihuRetrofitUtil() {
    }

    public static ZhiHuApi getZhihuRetrofit(){
        return ZhuihuRetrofit.zhihuRetrofit;
    }

    private static class ZhuihuRetrofit{
        private static final ZhiHuApi zhihuRetrofit=getInstance();
        private static ZhiHuApi getInstance(){
            return new Retrofit.Builder().client(RetrofitHelper.getOkHttpClient()).baseUrl(BAE_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build().create(ZhiHuApi.class);
        }
    }

}
