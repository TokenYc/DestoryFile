package com.example.cc.fileutil;

import android.app.Application;

import com.jiongbull.jlog.JLog;

/**
 * Created by .cc on 2016/3/9.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        JLog.init(this).setDebug(BuildConfig.DEBUG);
    }
}
