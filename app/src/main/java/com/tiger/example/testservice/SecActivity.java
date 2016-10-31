package com.tiger.example.testservice;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by tiger on 16/10/31.
 */

public class SecActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("secActivity", "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);

    }
}
