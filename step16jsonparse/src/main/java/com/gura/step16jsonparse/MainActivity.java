package com.gura.step16jsonparse;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Util.RequestListener {
    //필드
    private ArrayAdapter<String> adapter;
    private List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //모델
        names=new ArrayList<>();
        //어댑터
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        //ListView
        ListView listView=findViewById(R.id.listView);
        listView.setAdapter(adapter);
        //스프링 웹서버에 요청하기
        String urlAddr="http://192.168.0.36:8888/spring05/android/getnames.do";
        Util.sendGetRequest(0,urlAddr,null,this);//여기서 this는 MainActivity를 의미한다.
    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        //["김구라","해골","원숭이"] 형태의 json 문자열이다
        String data=(String)result.get("data");
        //[]형식의 JSONArray에 대응된다
        try {
            // 문자열을 바꾸지 않고 정규식을 이용해서 문자열을 얻어올수도 있지만 복잡해지는 경우가 발생할수도 있다.
            // JSONArray type이여야지 쉽게 문자열을 읽어올수있다.
            JSONArray arr=new JSONArray(data);//잘못된 JSON문자열이 들어올경우도 있기 때문에 이셉션이 발생한다
            //반복문을 돌면서
            for (int i=0;i<arr.length();i++){
                //JSONArray에서 i번째 String(문자열을 얻어낸다)
                String tmp=arr.getString(i);
                //모델에 추가
                names.add(tmp);
            }
            //어댑터에 모델의 데이터가 바뀌었다고 알려서 ListView(UI)가 업데이트 되도록한다
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {
        String data=(String)result.get("data");
        Toast.makeText(this,"실패!",Toast.LENGTH_SHORT).show();
    }
}
