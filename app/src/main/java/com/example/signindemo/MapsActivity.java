package com.example.signindemo;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.signindemo.databinding.ActivityMapsBinding;

import java.util.Vector;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    LatLng centerlocation;
    Vector<MarkerOptions> markerOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //marker location
        centerlocation = new LatLng(3, 181);

        markerOptions = new Vector<>();

        markerOptions.add( new MarkerOptions().title("Hospital Tuanku Fauziah Perlis")
                .position(new LatLng(6.476, 100.260))
                .snippet("Open during MCO : 24 hours")
        );

        markerOptions.add( new MarkerOptions().title("Hospital Sultanah Bahiyah Kedah")
                .position(new LatLng(6.116, 100.365))
                .snippet("Open during MCO : 24 hours")
        );

        markerOptions.add( new MarkerOptions().title("Hospital Pulau Pinang")
                .position(new LatLng(5.416, 100.311))
                .snippet("Open during MCO : 24 hours")
        );

        markerOptions.add( new MarkerOptions().title("Hospital Raja Permaisuri Bainon Perak")
                .position(new LatLng(4.602, 101.090))
                .snippet("Open during MCO : 24 hours")
        );

        markerOptions.add( new MarkerOptions().title("Hospital Tengku Ampuan Rahimah Klang")
                .position(new LatLng(3.019, 101.436))
                .snippet("Open during MCO : 24 hours")
        );

        markerOptions.add( new MarkerOptions().title("Hospital Kuala Lumpur")
                .position(new LatLng(3.019, 101.701))
                .snippet("Open during MCO : 24 hours")
        );

        markerOptions.add( new MarkerOptions().title("Hospital Melaka")
                .position(new LatLng(2.217, 102.261))
                .snippet("Open during MCO : 24 hours")
        );

        markerOptions.add( new MarkerOptions().title("Hospital Tuanku Ja'afar Seremban")
                .position(new LatLng(2.693, 101.929))
                .snippet("Open during MCO : 24 hours")
        );

        markerOptions.add( new MarkerOptions().title("Hospital Raja Perempuan Zainab II Kelantan")
                .position(new LatLng(6.124, 102.245))
                .snippet("Open during MCO : 24 hours")
        );

        markerOptions.add( new MarkerOptions().title("Hospital Sultanah Nur Zahirah Terengganu")
                .position(new LatLng(4.228, 103.428))
                .snippet("Open during MCO : 24 hours")
        );

        markerOptions.add( new MarkerOptions().title("Hospital Sultan Haji Ahmad Shah Pahang")
                .position(new LatLng(3.452, 102.451))
                .snippet("Open during MCO : 24 hours")
        );

        markerOptions.add( new MarkerOptions().title("Hospital Sultan Ismail Johor Bahru")
                .position(new LatLng(1.546, 103.790))
                .snippet("Open during MCO : 24 hours")
        );
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker
        for (MarkerOptions mark : markerOptions){
            mMap.addMarker(mark);
        }
        enableMyLocation();

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerlocation, 6));
    }
    //Enables the My Location layer if the fine location permission has been granted.

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
            }
        } else {
            String perms[] = {"android.permission.ACCESS_FINE_LOCATION"};
            // Permission to access the location is missing. Show rationale and request permission
            ActivityCompat.requestPermissions(this,perms, 200);
        }
    }
}