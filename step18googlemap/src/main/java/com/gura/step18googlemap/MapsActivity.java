package com.gura.step18googlemap;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //activity_my.xml에 있는 SurportMapFragment 객체의 참조값 얻어내기
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);//프레그먼트 자체는 뷰가 아니기 때문에 findViewById()메소드를 통해서는 참조값을 알아낼수없다.
        //지도가 동작할 준비가 완료됐는지 확인할 리스너 객체 등록하기
        mapFragment.getMapAsync(this);//OnMapReadyCallback type을 전달...?
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
    public void onMapReady(GoogleMap googleMap) {//지도가 준비되면 호출되는 메소드
        mMap = googleMap;

        // 에이콘 아카데미 위도 경로정보를 가지고있는 LatLng객체
        LatLng acorn = new LatLng(37.498928, 127.031579);
        //마커 옵션 객체
        MarkerOptions options=new MarkerOptions();
        options.position(acorn);
        options.title("Acorn Academy");
        //지도상에 마커 표시하기
        mMap.addMarker(options);

        //지정한 위치와 배율로 카메라 이동하기
        CameraUpdate cu=CameraUpdateFactory.newLatLngZoom(acorn,18);
        mMap.animateCamera(cu);
    }

    @Override
    public void onClick(View v) {
        // 우리집 위도 경로정보를 가지고있는 LatLng객체
        LatLng home = new LatLng(37.405777, 126.901059);
        //마커 옵션 객체
        MarkerOptions options=new MarkerOptions();
        options.position(home);
        options.title("My Home");
        //지도상에 마커 표시하기
        mMap.addMarker(options);

        //지정한 위치와 배율로 카메라 이동하기
        CameraUpdate cu=CameraUpdateFactory.newLatLngZoom(home,18);
        mMap.animateCamera(cu);//animateCamera() 위치를 이동하는 경로를 보여줌 / moveCamera()는 이동된위치를 바로 과정없이 보여줌
    }
}
