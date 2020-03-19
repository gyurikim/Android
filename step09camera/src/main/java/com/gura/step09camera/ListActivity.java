package com.gura.step09camera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListActivity extends AppCompatActivity
        implements Util.RequestListener, View.OnClickListener {
    public static final String IMAGELIST_URL="http://192.168.0.15:8888/spring05/android/image/list.do";;
    private ListView listView;
    private ImageAdapter adapter;
    private List<ImageDto> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView=findViewById(R.id.listView);
        list=new ArrayList<>();
        adapter=new ImageAdapter(this, R.layout.listview_cell, list);
        listView.setAdapter(adapter);
        Util.sendGetRequest(0, IMAGELIST_URL, null, this);
        //버튼의 참조값얻어와서
        Button takePicBtn=findViewById(R.id.takePicBtn);
        Button refreshBtn=findViewById(R.id.refreshBtn);
        //리스너 등록하기
        takePicBtn.setOnClickListener(this);
        refreshBtn.setOnClickListener(this);
    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        //모델 클리어
        list.clear();

        String data=(String)result.get("data");
        try{
            JSONArray arr=new JSONArray(data);
            for(int i=0; i<arr.length(); i++){
                JSONObject obj=arr.getJSONObject(i);
                int num=obj.getInt("num");
                String writer=obj.getString("writer");
                String imagePath=obj.getString("imagePath");
                String regdate=obj.getString("regdate");
                ImageDto dto=new ImageDto();
                dto.setNum(num);
                dto.setImageUrl("http://192.168.0.15:8888/spring05"+imagePath);
                dto.setWriter(writer);
                dto.setRegdate(regdate);
                //ImageDto객체를 list에 추가
                list.add(dto);
            }
            adapter.notifyDataSetChanged();
        }catch (JSONException je){

        }

    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.takePicBtn:
                //사진찍는 액티비티로 이동하기
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.refreshBtn:
                //다시 목록 받아오기
                Util.sendGetRequest(0, IMAGELIST_URL, null, this);
                break;
        }
    }
}