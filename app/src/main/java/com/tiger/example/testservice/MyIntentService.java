package com.tiger.example.testservice;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: executed");
    }
}
