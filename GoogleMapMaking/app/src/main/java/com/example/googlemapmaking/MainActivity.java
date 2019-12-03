package com.example.googlemapmaking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap gMap;
    GroundOverlayOptions cctvMark;
    ArrayList<GroundOverlayOptions> cctvList = new ArrayList<GroundOverlayOptions>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        gMap = googleMap;
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                cctvMark = new GroundOverlayOptions().image(BitmapDescriptorFactory.fromResource(R.drawable.hib)).position(latLng, 100f, 100f);

                cctvList.add(cctvMark);

                for(int i = 0 ; i < cctvList.size() ; i++){
                    gMap.addGroundOverlay(cctvList.get(i));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "위성 지도");
        menu.add(0, 2, 0, "일반 지도");
        menu.add(0, 3, 0, "바로전 CCTV 지우기");
        menu.add(0, 4, 0, "모든 CCTV 지우기");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                gMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case 2:
                gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case 3:
                gMap.clear();
                cctvList.remove(cctvList.size() - 1);
                for(int i=0; i<cctvList.size();i++)
                    gMap.addGroundOverlay(cctvList.get(i));
                return true;
            case 4:
                gMap.clear();
                cctvList.removeAll(cctvList);
                return true;
        }
        return false;
    }

}


//    private FragmentManager fragmentManager;
//    private MapFragment mapFragment;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        fragmentManager=getFragmentManager();
//        mapFragment=(MapFragment)fragmentManager.findFragmentById(R.id.fgGoogleMap);
//        mapFragment.getMapAsync(this);
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
////구글맵에 가서 위도 경도를 가져올것  37.562349, 127.035154
//        LatLng location = new LatLng(37.562349, 127.035154);
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.title("미래능력개발교육원");
//        //세부적인 내용
//        markerOptions.snippet("안웹앱8기가 머물던곳");
//        markerOptions.position(location);
//        googleMap.addMarker(markerOptions);
//        //  googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
//        //켜질때 카메라 살짝 멀리서 서서히 오듯 이동하는것이 보임
//        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,16));
//
//    }
