package com.example.cc.fileutil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jiongbull.jlog.JLog;

/**
 * 用于接收开机事件的广播接收器
 */
public class BootBroadcastReceiver extends BroadcastReceiver {
    public BootBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //启动服务
        Intent intent1 = new Intent(context, MyService.class);
        context.startService(intent1);
        throw new UnsupportedOperationException("Not yet implemented");

    }
}
