package com.gura.step15httprequest3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener,Util.RequestListener{
    //필요한 필드 정의하기
    private EditText inputUrl,console;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //res/layout/activity_main.xml문서를 전개해서 화면구성하기
        setContentView(R.layout.activity_main);
        // id가 requestBtn이 Button의 객체의 참조값을 얻어와서 requestBtn이라는 지역변수에 담기
        Button requestBtn=findViewById(R.id.requestBtn);
        //버튼에 리스너 등록하기
        requestBtn.setOnClickListener(this);
        //참조값을 읽어와서 필드값에 저장 > 참조값을 다른 메소드에서도 사용하기위해서 필드에 저장한다.
        inputUrl=findViewById(R.id.inputUrl);
        console=findViewById(R.id.console);
    }

    @Override
    public void onClick(View v) {
        //입력한 url주소를 읽어온다
        String urlAddr=inputUrl.getText().toString();
        //Util 클래스 메소드를 이용해서 GET방식 요청을 한다.
        // .sendGetRequest(요청 id, 요청 url, 요청파라미터, 요청리스너)
       Util.sendGetRequest(0,urlAddr,null,this);//리스너가 RequestListener 타입이여야한다. 인터페이스를 상속받자!
    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        //응답코드값
        int responseCode=(int)result.get("code");
        //응답된 문자열
        String data=(String)result.get("data");
        //여기는 UI스레드이기 때문에 UI를 업데이트 할수있다
        console.setText(data);//콘솔에 출력
    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {
        //예외 메세지 읽어오기
        String data=(String)result.get("data");
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
    }
}
