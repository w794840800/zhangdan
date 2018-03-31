package com.beidou.ybz.accountbook.retrofit;


import android.content.Context;
import android.util.Log;


import com.beidou.ybz.accountbook.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by WuXiaolong on 2016/3/24.
 * github:https://github.com/WuXiaolong/
 * 微信公众号：吴小龙同学
 * 个人博客：http://wuxiaolong.me/
 */
public class AppClient {
    public static Retrofit mRetrofit,mSearchRetrofit;
    private static final int DEFAULT_TIME_OUT = 20;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private static Context mContext;
    public static Retrofit retrofit(Context context) {
        mContext = context;
        if (mRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
            // builder.writeTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//写操作 超时时间
            builder.readTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//读操作超时时间

            if (BuildConfig.DEBUG) {
                // Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.i("aaa", "message====" + message);
                    }
                });
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置 Debug Log 模式
                builder.addInterceptor(httpLoggingInterceptor);
                builder.addInterceptor(new TokenInterceptor(mContext));
            }
            OkHttpClient okHttpClient = builder.build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiStores.API_SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit;
    }

    public static Retrofit searchRetrofit(Context context) {
        mContext = context;
        if (mSearchRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
            // builder.writeTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//写操作 超时时间
            builder.readTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//读操作超时时间

            if (BuildConfig.DEBUG) {
                // Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.i("aaa", "message====" + message);
                    }
                });
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置 Debug Log 模式
                builder.addInterceptor(httpLoggingInterceptor);
//                builder.addInterceptor(new TokenInterceptor(mContext));
            }
            OkHttpClient okHttpClient = builder.build();
            mSearchRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiStores.API_SEARCH_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mSearchRetrofit;
    }

}
