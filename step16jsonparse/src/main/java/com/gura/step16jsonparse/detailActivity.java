package com.gura.step16jsonparse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class detailActivity extends AppCompatActivity implements Util.RequestListener{
    private TextView textNum,textName,textAddr;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Intent 객체에 "num"이라는 키값으로 담긴 회원의 번호 얻어내기
        int num=getIntent().getIntExtra("num",0);//한번에 Dto에 담아서 넘길수도 있겟지만 수많은 정보가 있을시에는시간이 많이 들어간테니깐...ㅎ default:0은 널포인트이셉션 방지
        //회원번호를 Map객체에 담는다.
        Map<String,String> map=new HashMap<>();
        map.put("num",Integer.toString( num));
        String urlAddr="http://192.168.0.36:8888/spring05/android/member/detail.do";
        //Util을 이용해서 요청을한다
        Util.sendGetRequest(0,urlAddr,map,this);

        //textView의 참조값 얻어내서 필드에 저장하기
        textNum=findViewById(R.id.textNum);
        textName=findViewById(R.id.textName);
        textAddr=findViewById(R.id.textAddr);
    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        String data=(String)result.get("data");
        try {
            JSONObject obj=new JSONObject(data);
            //data는 {"num":1,"name":"김구라","addr":"노량진"} 형식의 JSON 문자열이다.
            int num=obj.getInt("num");
            String name=obj.getString("name");
            String addr=obj.getString("addr");
//            String info="번호 : "+num+" 이름 : "+name+" addr : "+addr;
//            textView.setText(info);
            //textView에 출력하기
            textNum.setText(Integer.toString(num));//setText()에는 int를 전달해서는 안된다. int를 전달할경우 id의 참조값을 전달한다
            textName.setText(name);
            textAddr.setText(addr);
        }catch (JSONException je){}

    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {

    }
}
