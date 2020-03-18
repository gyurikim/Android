package com.gura.step16jsonparse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener,Util.RequestListener {
    //필드정의
    private ListView result;
    private ArrayAdapter<String> adapter;
    private List<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        result=findViewById(R.id.result);
        names=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        Button getBtn=findViewById(R.id.getBtn);
        result.setAdapter(adapter);
        getBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String urlAddr="http://192.168.0.36:8888/spring05/android/getmember.do";
        Util.sendGetRequest(0,urlAddr,null,this);//요청파라미터가 존재한다면 Map(key.value)에 담아서 전달하면 된다
    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        // [{xxx},{xxx},{xxx},...] 문자열이 들어있다.
        String data=(String) result.get("data");
        try {
            JSONArray arr=new JSONArray(data);//잘못된 JSON문자열이 들어올경우도 있기 때문에 이셉션이 발생한다
            for (int i=0;i<arr.length();i++){
                JSONObject obj=arr.getJSONObject(i);
                int num=obj.getInt("NUM");
                String name=obj.getString("NAME");
                String addr=obj.getString("ADDR");
                String info="번호 : "+num+" 이름 : "+name+" addr : "+addr;
                names.add(info);
            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {

    }
}
