package com.gura.step13service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/*
    [UI없이 백그라운드에서 동작할수있는 Service]

    1. Service 추상클래스를 상속받는다
    2. onBind() 메소드 오버라이딩
    3. 추가로 필요한 메소드를 오버라이딩해서 작업한다
 */
public class MyService extends Service {
    //4대 컴포넌트는 메니페스드에 등록이 되어있어야지만 실행된다!!!!!

    //특정 Activity와 연결이 되었을때 호출되는 메소드(이 예제에서는 할 작업이 없다)
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override//초기화할 작업을 하는 메소드
    public void onCreate() {
        super.onCreate();

    }

    @Override//서비스에서 수행해야할 main작업(활성화(작업)될때)을 하는 메소드
    public int onStartCommand(Intent intent, int flags, int startId) {//객체가 생성되고 활성화(작업)될때 호출되는 메소드
        handler.sendEmptyMessage(0);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override//서비스가 종료될때 마무리 작업을 하는 메소드
    public void onDestroy() {
        super.onDestroy();
        //핸들러가 동작하지않도록 삭제
        handler.removeMessages(0);
    }

    //카운트값을 저장할 필드
    int count=0;

    //핸들러
    Handler handler=new Handler(){
        @Override//추상메소드로 오버라이드
        public void handleMessage(@NonNull Message msg) {
            count++;
            //getApplicationContext()메소드는 service를 상속받았기 때문에 사용할수있고 한번호출하면 계속 반복하는 메소드다
            Toast.makeText(getApplicationContext(),count+"번 호출",Toast.LENGTH_LONG).show();//service는  context가 아니다 그러기 때문에 따로 불러사용해야한다
            handler.sendEmptyMessageDelayed(0,5000);
        }
    };
}
