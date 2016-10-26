package com.tiger.example.testservice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button startServiceButton;
    private Button stopServiceButton;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startServiceButton = (Button) findViewById(R.id.start_service);
        stopServiceButton = (Button) findViewById(R.id.stop_service);
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
    }
}
