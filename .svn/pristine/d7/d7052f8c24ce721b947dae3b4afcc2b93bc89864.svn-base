package com.beidou.ybz.accountbook.util;


import com.orhanobut.logger.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * debug模式下打印
 * 正式情况下不打印
 * Created by bob on 2016/11/1.
 */
public class LogUtils {
    /**
     * @param log
     */
    public static void loge(String log) {
        /*if(BuildConfig.DEBUG)*/ Logger.e(log);
    }

    /**
     * @param log
     */
    public static void logi(String log) {
        if(BuildConfig.DEBUG) Logger.i(log);
    }

    /**
     * @param log
     */
    public static void logd(String log) {
        /*if(BuildConfig.DEBUG)*/ Logger.d(log);
    }

    /**
     * @param log
     */
    public static void logJson(String log) {
        /*if(BuildConfig.DEBUG)*/ Logger.json(log);
    }


}
