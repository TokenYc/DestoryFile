package com.example.cc.fileutil;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.jiongbull.jlog.JLog;

import java.io.File;
import java.io.IOException;

/**
 * 开机时启动服务
 */
public class MyService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
        JLog.d("onCreate");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        JLog.d("onStartCommand");

        new Handler().postDelayed(new Runnable() {//开机后等待10s，等tencent文件夹创建好后删除
            @Override
            public void run() {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        File file = new File(FileUtil.SDCARD_PATH + File.separator + "tencent");
                        FileUtil.deleteFolder(FileUtil.SDCARD_PATH + File.separator + "tencent");
                        try {
                            file.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        }, 10 * 1000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        JLog.d("onDestroy");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        JLog.d("onBind");
        return null;
    }


}
