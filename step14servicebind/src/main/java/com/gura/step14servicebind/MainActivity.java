package com.gura.step14servicebind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //필요한 멤버필드 정의하기
    MessengerService mService;
    //서비스에 연결되었는지 여부
    boolean mServiceConnected=false;
    EditText console;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        console = (EditText)findViewById(R.id.editText);
        //메신저서비스를 시작 시킨다.
        Intent intent=new Intent(this, MessengerService.class);
        startService(intent);
    }
    //서비스 종료 버튼을 눌렀을때
    public void end(View v){
        if(mServiceConnected){//서비스에 바인딩 된 상태라면
            //바인딩을 해제해준다.
            unbindService(mConn);
            mServiceConnected=false;
        }
        Intent intent=new Intent(this, MessengerService.class);
        stopService(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //서비스 바인딩하기 >> 서비스랑 소통하기 위해서 바인딩하는것
        Intent intent=new Intent(this, MessengerService.class);
        bindService(intent, mConn, Context.BIND_AUTO_CREATE);//비동기서비스(리스너라고 생각하면 쉽다)
    }

    @Override
    protected void onStop() {
        if(mServiceConnected){//서비스에 바인딩 된 상태라면
            //바인딩을 해제시키지 않으면 액티비티가 종료되었는데 다른 액티비티에서 서비스를 쓰고자한다면 오류가 발생한다
            //바인딩을 해제해준다.
            unbindService(mConn);
            mServiceConnected=false;
        }
        super.onStop();
    }
    //서비스로 부터 문자열을 전달받을 메소드
    public void setMsg(final String msg){
        /* 변수에 final을 작성하지 않으면 오류가 발생한다 : 익명클래스에서는 값이 변하지않는 final 상수값만 참조할수있다.
           생명주기가 달라지면 동작의 안전성을 보장하지 못하므로 지역변수를 사용할수 없다*/

        //UI는 UI스레드(Main스레드)에서만 건드릴수있다. 아래 코드를 작성하지 않으면 다른스레드에서 건드렸을때 이 앱은 죽게된다
        //UI 스레드에서 동작할수 있도록 한다.
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //콘솔에 출력하기
                console.append(msg+"\n");
            }
        });
    }
    //서비스 연결객체
    ServiceConnection mConn=new ServiceConnection() {//익명클래스로 만들기 : class ? extends ServiceConnection{ xxx }
        //서비스와 연결되었을때 호출되는 메소드
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {// IBinder는 연결 역할을 하는 핵심객체이다!
            //서비스의 onBind() 메소드에서 리턴해주는 IBinder 객체가 들어온다.
            MessengerService.LocalBinder
                    localBinder = (MessengerService.LocalBinder)service;
            //MessengerService 의 참조값을 맴버필드에 저장한다.
            //참조값을 액티비티에서 생성한게아니라 서비스에서 생성했기 때문에 위의 작업을 통해서 참조값을 가져와야한다
            mService=localBinder.getService();      //액티비티에서 서비스의 참조값을 갖고있는게 핵심이다!!!!!! > 참조값을 가져와서 필드에 저장
            //서비스에 액티비티의 참조값을 전달한다.
            localBinder.setActivity(MainActivity.this);//그냥 this를 쓰지않은 이유는 : ServiceConnection의 참조값이 아닌 바깥클래스의 참조값을 의미하므로
            //서비스와 연결되었다고 표시한다.
            mServiceConnected=true;

        }
        //서비스와 연결 해제 되었을때 호출되는 메소드
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceConnected=false;
            mService=null;
        }
    };
}
