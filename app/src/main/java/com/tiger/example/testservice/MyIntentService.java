package com.tiger.example.testservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

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
        Log.d(TAG, "onHandleIntent: Thread id is " + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: executed");
    }
}
