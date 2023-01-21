package com.example.carmonitor;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class controls extends Activity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    Button brokeBtn,location;
   // String phoneNo=getIntent().getStringExtra("carnumber");
    //String carna=getIntent().getStringExtra("carname");
    String message;
    TextView s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls);

        ActivityCompat.requestPermissions(controls.this, new String[]{Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
        brokeBtn = (Button) findViewById(R.id.brokeb);
        location = (Button) findViewById(R.id.requestb);
        s = (TextView) findViewById(R.id.smst);

       /* brokeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSMessage("stop the car");
            }
        });
        /*location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMSMessage("get location");
                Intent intent=new Intent(controls.this,MapsActivity.class);
                intent.putExtra("carname",carna);
                startActivity(intent);
            }
        });*/

            Cursor cursor = getContentResolver().query(Uri.parse("content://sms"), null, null,null,null);
            cursor.moveToFirst();

            s.setText(cursor.getString(cursor.getColumnIndexOrThrow("address")));

    }


    /*protected void sendSMSMessage(String messag) {

        message=messag.toString();
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNo, null, message, null, null);
        Toast.makeText(getApplicationContext(), "SMS sent.",
                Toast.LENGTH_LONG).show();
    }*/

}