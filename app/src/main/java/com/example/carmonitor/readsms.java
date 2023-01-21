package com.example.carmonitor;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class readsms extends AppCompatActivity {

    private TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reads);

        myTextView = findViewById(R.id.textView);
        ActivityCompat.requestPermissions(readsms.this, new String[]{Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
    }

    public void Read_SMS(View view){

        Cursor cursor = getContentResolver().query(Uri.parse("content://sms"), null, null,null,null);
        cursor.moveToFirst();

        myTextView.setText(cursor.getString(12));

    }
}
