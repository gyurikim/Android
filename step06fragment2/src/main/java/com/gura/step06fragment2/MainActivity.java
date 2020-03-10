package com.gura.step06fragment2;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gura.step06fragment2.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //ViewPager에서 사용할 모델
    List<CountryDto> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //레이아웃 구성하기
        setContentView(R.layout.activity_main);

        countries=new ArrayList<>();
        countries.add(new CountryDto(R.drawable.korea,"대한한국","어쩌구 저쩌구"));
        countries.add(new CountryDto(R.drawable.austria,"오스트리아","어쩌구 저쩌구"));
        countries.add(new CountryDto(R.drawable.belgium,"벨기에","어쩌구 저쩌구"));
        countries.add(new CountryDto(R.drawable.brazil,"브라질","어쩌구 저쩌구"));
        countries.add(new CountryDto(R.drawable.france,"프랑스","어쩌구 저쩌구"));
        countries.add(new CountryDto(R.drawable.germany,"독일","어쩌구 저쩌구"));
        countries.add(new CountryDto(R.drawable.greece,"그리스","어쩌구 저쩌구"));
        countries.add(new CountryDto(R.drawable.israel,"이스라엘","어쩌구 저쩌구"));
        countries.add(new CountryDto(R.drawable.italy,"이탈리아","어쩌구 저쩌구"));
        countries.add(new CountryDto(R.drawable.japan,"일본","어쩌구 저쩌구"));
        countries.add(new CountryDto(R.drawable.poland,"폴란드","어쩌구 저쩌구"));
        countries.add(new CountryDto(R.drawable.spain,"스페인","어쩌구 저쩌구"));
        countries.add(new CountryDto(R.drawable.usa,"미국","어쩌구 저쩌구"));


        //Pager는 옆으로 슬라이드했을때 넘어갈수있도록 하는 객체를 의미한다.....?
        //Pager어댑터 객체 생성하기 > ViewPager에 프레그먼트를 공급해주는 어댑터
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(countries, getSupportFragmentManager());
        //ViewPager객체의 참조값 얻어오기 > 어려개의 프레그먼트를 페이징처럼 보여 줄 어댑터..?
        ViewPager viewPager = findViewById(R.id.view_pager);
        //ViewPager에 PagerAdapter객체 연결하기
        viewPager.setAdapter(sectionsPagerAdapter);
        //Tap레이아웃 객페의 참조값 얻어오기 > 탭을 없애고 싶으면 작성 안하면 됨
        TabLayout tabs = findViewById(R.id.tabs);
        //Tap과 ViewPager를 함꼐 연계해서 쓰도록 설정
        tabs.setupWithViewPager(viewPager);

        //스네이크바에관한 액션리스너는 여기서 정의하면 된다
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}