package com.gura.step13service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //서비스 시작버튼을 눌렀을때 실행되는 메소드
    public void start(View v){
        Intent intent=new Intent(this,MyService.class);
        //intent객체를 이용해서 서비스를 시작시킨다
        startService(intent);
    }

    //서비스 종료버튼을 눌렀을때 실행되는 메소드
    public void end(View v){
        Intent intent=new Intent(this,MyService.class);
        //intent객체를 이용해서 서비스를 종료시킨다
        stopService(intent);
    }
}
