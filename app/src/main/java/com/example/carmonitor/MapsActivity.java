 package com.example.carmonitor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    SupportMapFragment mapFragment;
    //String phoneNo=getIntent().getStringExtra("carnumber");
    //String carna=getIntent().getStringExtra("carname");
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.brokes) {
            sendSMSMessage("stop the car");
            Toast.makeText(this,"the car have been blocked",Toast.LENGTH_SHORT).show();

        }else if(id==R.id.current){
            sendSMSMessage("get location");
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(-1.958128, 30.064463))
                            .title("toyota picnic")
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-1.958128, 30.064463), 10));
                }
            });
            return true;


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-1.958128, 30.064463), 10));

    }
    protected void sendSMSMessage(String messag) {

        message=messag.toString();
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("+250780599648", null, message, null, null);
    }
}