package com.example.gps_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener ll = new lokasiListener();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
        PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //todo: Consider Calling
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 200, ll);
    }

    private class lokasiListener implements LocationListener{

        @Override
        public void onLocationChanged(@NonNull Location location) {
            TextView latitude = (TextView) findViewById(R.id.latitude);
            TextView longitude = (TextView) findViewById(R.id.longitude);

            latitude.setText(String.valueOf(location.getLatitude()));
            longitude.setText(String.valueOf(location.getLongitude()));
            Toast.makeText(getBaseContext(), "GPS Captured", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {

        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {

        }
    }
}