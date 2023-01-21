package com.example.carmonitor;

import static android.content.Intent.*;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class intro extends AppCompatActivity {
    Button nxt,skp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_intro);
        nxt=findViewById(R.id.next);
        skp=findViewById(R.id.skip);
        final Notification notification ;
        final NotificationManagerCompat notificationManagerCompat;
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                    NotificationChannel channel=new NotificationChannel("mych","my channel", NotificationManager.IMPORTANCE_DEFAULT);
                    NotificationManager manager=getSystemService(NotificationManager.class);
                    manager.createNotificationChannel(channel);
                }
                NotificationCompat.Builder builder=new NotificationCompat.Builder(intro.this,"mych")
                        .setSmallIcon(android.R.drawable.stat_notify_sync)
                        .setContentTitle("my notification")
                        .setContentText("hello there");
                //notification =builder.build();
                //notificationManagerCompat=NotificationManagerCompat.from(intro.this);
               // notificationManagerCompat.notify(1,notification);
            }

            /*@Override
                        public void onClick(View view) {
                            startActivity(new Intent(intro.this,intro2.class));
            }
            // Create an Intent for the activity you want to start*/

        });
        skp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(intro.this,setup.class));
            }
        });

    }
}