package org.sltpaya.cartoon.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

/**
 * Author: SLTPAYA
 * Date: 2017/7/20
 */
public class RemoteService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("服务创建了！");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("服务开启了！");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("服务被终止了！");
    }

}
