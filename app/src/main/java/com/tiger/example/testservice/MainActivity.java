package com.tiger.example.testservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    private Button startServiceButton;
    private Button stopServiceButton;
    private Context context;
    private Button bindServiceButton;
    private Button unbindServiceButton;
    private Button startIntentService;
    private MyServiece.DownlaodBinder downlaodBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            downlaodBinder = (MyServiece.DownlaodBinder) iBinder;
            downlaodBinder.startDownload();
            downlaodBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: executed");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startServiceButton = (Button) findViewById(R.id.start_service);
        stopServiceButton = (Button) findViewById(R.id.stop_service);
        bindServiceButton = (Button) findViewById(R.id.bind_service);
        unbindServiceButton = (Button) findViewById(R.id.unbind_service);
        startIntentService = (Button) findViewById(R.id.start_intent_service);
        context = this.getApplicationContext();
        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(context, MyServiece.class);
                startService(startIntent);
            }
        });
        stopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stopIntent = new Intent(context, MyServiece.class);
                stopService(stopIntent);
            }
        });
        bindServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bindIntent = new Intent(context, MyServiece.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
            }
        });
        unbindServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(connection);
            }
        });
        startIntentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打印主线程id
                Log.d(TAG, "onClick: Thread id is " + Thread.currentThread().getId());
                Intent intentService = new Intent(context, MyIntentService.class);
                startService(intentService);
            }
        });
    }
}
