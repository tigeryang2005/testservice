package com.tiger.example.testservice;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Date;

/**
 * Created by tiger on 16/10/27.
 */

public class MyIntentService extends IntentService {

    private static String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //打印当前线程id
        Log.d(TAG, "onHandleIntent: Child Thread id is " + Thread.currentThread().getId());
        Log.d(TAG, "onHandleIntent: executed at" + new Date().toString());
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int halfminite = 5 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + halfminite;
        Intent newIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, newIntent, 0);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mBuilder.setContentTitle("测试标题")
                    .setContentText("测试内容")
                    //  .setNumber(number) //设置通知集合的数量
                    .setContentIntent(pendingIntent)//设置通知栏点击意图 可以加一个别的activity
                    .setTicker("测试通知来了")//通知首次出现在通知栏，带上升动画效果
                    .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                    .setPriority(Notification.PRIORITY_DEFAULT)//设置该通知优先级
                    //  .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                    .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                    //                Notification.DEFAULT_VIBRATE    //添加默认震动提醒  需要 VIBRATE permission
                    //                Notification.DEFAULT_SOUND    // 添加默认声音提醒
                    //                Notification.DEFAULT_LIGHTS// 添加默认三色灯提醒
                    //                Notification.DEFAULT_ALL// 添加默认以上3种全部提醒
                    .setDefaults(Notification.DEFAULT_ALL)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                    .setSmallIcon(R.mipmap.ic_launcher);//设置通知小ICON
        }
        notificationManager.notify(0, mBuilder.build());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: executed");
    }
}
